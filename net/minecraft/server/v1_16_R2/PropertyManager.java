/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Objects;
/*     */ import java.util.Properties;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.IntFunction;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.function.UnaryOperator;
/*     */ import javax.annotation.Nullable;
/*     */ import joptsimple.OptionSet;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public abstract class PropertyManager<T extends PropertyManager<T>>
/*     */ {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public final Properties properties;
/*  25 */   private OptionSet options = null;
/*     */   
/*     */   public PropertyManager(Properties properties, OptionSet options) {
/*  28 */     this.properties = properties;
/*     */     
/*  30 */     this.options = options;
/*     */   }
/*     */   
/*     */   private String getOverride(String name, String value) {
/*  34 */     if (this.options != null && this.options.has(name) && !name.equals("online-mode")) {
/*  35 */       return String.valueOf(this.options.valueOf(name));
/*     */     }
/*     */     
/*  38 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Properties loadPropertiesFile(Path java_nio_file_path) {
/*  43 */     Properties properties = new Properties();
/*     */     
/*     */     try {
/*  46 */       InputStream inputstream = Files.newInputStream(java_nio_file_path, new java.nio.file.OpenOption[0]);
/*  47 */       Throwable throwable = null;
/*     */       
/*     */       try {
/*  50 */         properties.load(inputstream);
/*  51 */       } catch (Throwable throwable1) {
/*  52 */         throwable = throwable1;
/*  53 */         throw throwable1;
/*     */       } finally {
/*  55 */         if (inputstream != null) {
/*  56 */           if (throwable != null) {
/*     */             try {
/*  58 */               inputstream.close();
/*  59 */             } catch (Throwable throwable2) {
/*  60 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/*  63 */             inputstream.close();
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/*  68 */     } catch (IOException ioexception) {
/*  69 */       LOGGER.error("Failed to load properties from file: " + java_nio_file_path);
/*     */     } 
/*     */     
/*  72 */     return properties;
/*     */   }
/*     */ 
/*     */   
/*     */   public void savePropertiesFile(Path java_nio_file_path) {
/*     */     try {
/*  78 */       if (java_nio_file_path.toFile().exists() && !java_nio_file_path.toFile().canWrite()) {
/*     */         return;
/*     */       }
/*     */       
/*  82 */       OutputStream outputstream = Files.newOutputStream(java_nio_file_path, new java.nio.file.OpenOption[0]);
/*  83 */       Throwable throwable = null;
/*     */       
/*     */       try {
/*  86 */         this.properties.store(outputstream, "Minecraft server properties");
/*  87 */       } catch (Throwable throwable1) {
/*  88 */         throwable = throwable1;
/*  89 */         throw throwable1;
/*     */       } finally {
/*  91 */         if (outputstream != null) {
/*  92 */           if (throwable != null) {
/*     */             try {
/*  94 */               outputstream.close();
/*  95 */             } catch (Throwable throwable2) {
/*  96 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/*  99 */             outputstream.close();
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 104 */     } catch (IOException ioexception) {
/* 105 */       LOGGER.error("Failed to store properties to file: " + java_nio_file_path);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static <V extends Number> Function<String, V> a(Function<String, V> function) {
/* 111 */     return s -> {
/*     */         try {
/*     */           return function.apply(s);
/* 114 */         } catch (NumberFormatException numberformatexception) {
/*     */           return null;
/*     */         } 
/*     */       };
/*     */   }
/*     */   
/*     */   protected static <V> Function<String, V> a(IntFunction<V> intfunction, Function<String, V> function) {
/* 121 */     return s -> {
/*     */         try {
/*     */           return intfunction.apply(Integer.parseInt(s));
/* 124 */         } catch (NumberFormatException numberformatexception) {
/*     */           return function.apply(s);
/*     */         } 
/*     */       };
/*     */   }
/*     */   @Nullable
/* 130 */   String getSettingIfExists(String path) { return c(path); } @Nullable
/*     */   private String c(String s) {
/* 132 */     return getOverride(s, this.properties.getProperty(s));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected <V> V a(String s, Function<String, V> function) {
/* 137 */     String s1 = c(s);
/*     */     
/* 139 */     if (s1 == null) {
/* 140 */       return null;
/*     */     }
/* 142 */     this.properties.remove(s);
/* 143 */     return function.apply(s1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected <V> V a(String s, Function<String, V> function, Function<V, String> function1, V v0) {
/* 148 */     String s1 = c(s);
/* 149 */     V v1 = (V)MoreObjects.firstNonNull((s1 != null) ? function.apply(s1) : null, v0);
/*     */     
/* 151 */     this.properties.put(s, function1.apply(v1));
/* 152 */     return v1;
/*     */   }
/*     */   
/*     */   protected <V> EditableProperty<V> b(String s, Function<String, V> function, Function<V, String> function1, V v0) {
/* 156 */     String s1 = c(s);
/* 157 */     V v1 = (V)MoreObjects.firstNonNull((s1 != null) ? function.apply(s1) : null, v0);
/*     */     
/* 159 */     this.properties.put(s, function1.apply(v1));
/* 160 */     return new EditableProperty<>(s, v1, function1);
/*     */   }
/*     */   
/*     */   protected <V> V a(String s, Function<String, V> function, UnaryOperator<V> unaryoperator, Function<V, String> function1, V v0) {
/* 164 */     return a(s, s1 -> { V v1 = function.apply(s1); return (v1 != null) ? unaryoperator.apply(v1) : null; }function1, v0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected <V> V a(String s, Function<String, V> function, V v0) {
/* 172 */     return a(s, function, Objects::toString, v0);
/*     */   }
/*     */   
/*     */   protected <V> EditableProperty<V> b(String s, Function<String, V> function, V v0) {
/* 176 */     return b(s, function, Objects::toString, v0);
/*     */   }
/*     */   
/*     */   protected String getString(String s, String s1) {
/* 180 */     return a(s, Function.identity(), Function.identity(), s1);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected String a(String s) {
/* 185 */     return a(s, Function.identity());
/*     */   }
/*     */   
/*     */   protected int getInt(String s, int i) {
/* 189 */     return ((Integer)a(s, a(Integer::parseInt), Integer.valueOf(i))).intValue();
/*     */   }
/*     */   
/*     */   protected EditableProperty<Integer> b(String s, int i) {
/* 193 */     return b(s, a(Integer::parseInt), Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   protected int a(String s, UnaryOperator<Integer> unaryoperator, int i) {
/* 197 */     return ((Integer)a(s, a(Integer::parseInt), unaryoperator, Objects::toString, Integer.valueOf(i))).intValue();
/*     */   }
/*     */   
/*     */   protected long getLong(String s, long i) {
/* 201 */     return ((Long)a(s, a(Long::parseLong), Long.valueOf(i))).longValue();
/*     */   }
/*     */   
/*     */   protected boolean getBoolean(String s, boolean flag) {
/* 205 */     return ((Boolean)a(s, Boolean::valueOf, Boolean.valueOf(flag))).booleanValue();
/*     */   }
/*     */   
/*     */   protected EditableProperty<Boolean> b(String s, boolean flag) {
/* 209 */     return b(s, Boolean::valueOf, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected Boolean b(String s) {
/* 214 */     return a(s, Boolean::valueOf);
/*     */   }
/*     */   
/*     */   protected Properties a() {
/* 218 */     Properties properties = new Properties();
/*     */     
/* 220 */     properties.putAll(this.properties);
/* 221 */     return properties;
/*     */   }
/*     */   
/*     */   protected abstract T reload(IRegistryCustom paramIRegistryCustom, Properties paramProperties, OptionSet paramOptionSet);
/*     */   
/*     */   public class EditableProperty<V>
/*     */     implements Supplier<V> {
/*     */     private final String b;
/*     */     private final V c;
/*     */     private final Function<V, String> d;
/*     */     
/*     */     private EditableProperty(String s, V object, Function<V, String> function) {
/* 233 */       this.b = s;
/* 234 */       this.c = object;
/* 235 */       this.d = function;
/*     */     }
/*     */     
/*     */     public V get() {
/* 239 */       return this.c;
/*     */     }
/*     */     
/*     */     public T set(IRegistryCustom iregistrycustom, V v0) {
/* 243 */       Properties properties = PropertyManager.this.a();
/*     */       
/* 245 */       properties.put(this.b, this.d.apply(v0));
/* 246 */       return PropertyManager.this.reload(iregistrycustom, properties, PropertyManager.this.options);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PropertyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */