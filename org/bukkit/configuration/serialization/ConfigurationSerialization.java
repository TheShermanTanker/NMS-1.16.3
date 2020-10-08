/*     */ package org.bukkit.configuration.serialization;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.FireworkEffect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.block.banner.Pattern;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.util.BlockVector;
/*     */ import org.bukkit.util.BoundingBox;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigurationSerialization
/*     */ {
/*     */   public static final String SERIALIZED_TYPE_KEY = "==";
/*     */   private final Class<? extends ConfigurationSerializable> clazz;
/*  32 */   private static Map<String, Class<? extends ConfigurationSerializable>> aliases = new HashMap<>();
/*     */   
/*     */   static {
/*  35 */     registerClass((Class)Vector.class);
/*  36 */     registerClass((Class)BlockVector.class);
/*  37 */     registerClass((Class)ItemStack.class);
/*  38 */     registerClass((Class)Color.class);
/*  39 */     registerClass((Class)PotionEffect.class);
/*  40 */     registerClass((Class)FireworkEffect.class);
/*  41 */     registerClass((Class)Pattern.class);
/*  42 */     registerClass((Class)Location.class);
/*  43 */     registerClass((Class)AttributeModifier.class);
/*  44 */     registerClass((Class)BoundingBox.class);
/*     */   }
/*     */   
/*     */   protected ConfigurationSerialization(@NotNull Class<? extends ConfigurationSerializable> clazz) {
/*  48 */     this.clazz = clazz;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected Method getMethod(@NotNull String name, boolean isStatic) {
/*     */     try {
/*  54 */       Method method = this.clazz.getDeclaredMethod(name, new Class[] { Map.class });
/*     */       
/*  56 */       if (!ConfigurationSerializable.class.isAssignableFrom(method.getReturnType())) {
/*  57 */         return null;
/*     */       }
/*  59 */       if (Modifier.isStatic(method.getModifiers()) != isStatic) {
/*  60 */         return null;
/*     */       }
/*     */       
/*  63 */       return method;
/*  64 */     } catch (NoSuchMethodException ex) {
/*  65 */       return null;
/*  66 */     } catch (SecurityException ex) {
/*  67 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected Constructor<? extends ConfigurationSerializable> getConstructor() {
/*     */     try {
/*  74 */       return this.clazz.getConstructor(new Class[] { Map.class });
/*  75 */     } catch (NoSuchMethodException ex) {
/*  76 */       return null;
/*  77 */     } catch (SecurityException ex) {
/*  78 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected ConfigurationSerializable deserializeViaMethod(@NotNull Method method, @NotNull Map<String, ?> args) {
/*     */     try {
/*  85 */       ConfigurationSerializable result = (ConfigurationSerializable)method.invoke(null, new Object[] { args });
/*     */       
/*  87 */       if (result == null) {
/*  88 */         Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call method '" + method.toString() + "' of " + this.clazz + " for deserialization: method returned null");
/*     */       } else {
/*  90 */         return result;
/*     */       } 
/*  92 */     } catch (Throwable ex) {
/*  93 */       Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call method '" + method
/*     */           
/*  95 */           .toString() + "' of " + this.clazz + " for deserialization", 
/*  96 */           (ex instanceof java.lang.reflect.InvocationTargetException) ? ex.getCause() : ex);
/*     */     } 
/*     */     
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected ConfigurationSerializable deserializeViaCtor(@NotNull Constructor<? extends ConfigurationSerializable> ctor, @NotNull Map<String, ?> args) {
/*     */     try {
/* 105 */       return ctor.newInstance(new Object[] { args });
/* 106 */     } catch (Throwable ex) {
/* 107 */       Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call constructor '" + ctor
/*     */           
/* 109 */           .toString() + "' of " + this.clazz + " for deserialization", 
/* 110 */           (ex instanceof java.lang.reflect.InvocationTargetException) ? ex.getCause() : ex);
/*     */ 
/*     */       
/* 113 */       return null;
/*     */     } 
/*     */   }
/*     */   @Nullable
/*     */   public ConfigurationSerializable deserialize(@NotNull Map<String, ?> args) {
/* 118 */     Validate.notNull(args, "Args must not be null");
/*     */     
/* 120 */     ConfigurationSerializable result = null;
/* 121 */     Method method = null;
/*     */     
/* 123 */     if (result == null) {
/* 124 */       method = getMethod("deserialize", true);
/*     */       
/* 126 */       if (method != null) {
/* 127 */         result = deserializeViaMethod(method, args);
/*     */       }
/*     */     } 
/*     */     
/* 131 */     if (result == null) {
/* 132 */       method = getMethod("valueOf", true);
/*     */       
/* 134 */       if (method != null) {
/* 135 */         result = deserializeViaMethod(method, args);
/*     */       }
/*     */     } 
/*     */     
/* 139 */     if (result == null) {
/* 140 */       Constructor<? extends ConfigurationSerializable> constructor = getConstructor();
/*     */       
/* 142 */       if (constructor != null) {
/* 143 */         result = deserializeViaCtor(constructor, args);
/*     */       }
/*     */     } 
/*     */     
/* 147 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ConfigurationSerializable deserializeObject(@NotNull Map<String, ?> args, @NotNull Class<? extends ConfigurationSerializable> clazz) {
/* 167 */     return (new ConfigurationSerialization(clazz)).deserialize(args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ConfigurationSerializable deserializeObject(@NotNull Map<String, ?> args) {
/* 186 */     Class<? extends ConfigurationSerializable> clazz = null;
/*     */     
/* 188 */     if (args.containsKey("==")) {
/*     */       try {
/* 190 */         String alias = (String)args.get("==");
/*     */         
/* 192 */         if (alias == null) {
/* 193 */           throw new IllegalArgumentException("Cannot have null alias");
/*     */         }
/* 195 */         clazz = getClassByAlias(alias);
/* 196 */         if (clazz == null) {
/* 197 */           throw new IllegalArgumentException("Specified class does not exist ('" + alias + "')");
/*     */         }
/* 199 */       } catch (ClassCastException ex) {
/* 200 */         ex.fillInStackTrace();
/* 201 */         throw ex;
/*     */       } 
/*     */     } else {
/* 204 */       throw new IllegalArgumentException("Args doesn't contain type key ('==')");
/*     */     } 
/*     */     
/* 207 */     return (new ConfigurationSerialization(clazz)).deserialize(args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClass(@NotNull Class<? extends ConfigurationSerializable> clazz) {
/* 217 */     DelegateDeserialization delegate = clazz.<DelegateDeserialization>getAnnotation(DelegateDeserialization.class);
/*     */     
/* 219 */     if (delegate == null) {
/* 220 */       registerClass(clazz, getAlias(clazz));
/* 221 */       registerClass(clazz, clazz.getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerClass(@NotNull Class<? extends ConfigurationSerializable> clazz, @NotNull String alias) {
/* 234 */     aliases.put(alias, clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterClass(@NotNull String alias) {
/* 243 */     aliases.remove(alias);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterClass(@NotNull Class<? extends ConfigurationSerializable> clazz) {
/* 253 */     while (aliases.values().remove(clazz));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Class<? extends ConfigurationSerializable> getClassByAlias(@NotNull String alias) {
/* 267 */     return aliases.get(alias);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static String getAlias(@NotNull Class<? extends ConfigurationSerializable> clazz) {
/* 279 */     DelegateDeserialization delegate = clazz.<DelegateDeserialization>getAnnotation(DelegateDeserialization.class);
/*     */     
/* 281 */     if (delegate != null) {
/* 282 */       if (delegate.value() == null || delegate.value() == clazz) {
/* 283 */         delegate = null;
/*     */       } else {
/* 285 */         return getAlias(delegate.value());
/*     */       } 
/*     */     }
/*     */     
/* 289 */     if (delegate == null) {
/* 290 */       SerializableAs alias = clazz.<SerializableAs>getAnnotation(SerializableAs.class);
/*     */       
/* 292 */       if (alias != null && alias.value() != null) {
/* 293 */         return alias.value();
/*     */       }
/*     */     } 
/*     */     
/* 297 */     return clazz.getName();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\serialization\ConfigurationSerialization.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */