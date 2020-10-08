/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.InvalidPathException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class DefinedStructureManager
/*     */ {
/*  21 */   private static final Logger LOGGER = LogManager.getLogger();
/*  22 */   private final Map<MinecraftKey, DefinedStructure> b = Maps.newConcurrentMap();
/*     */   private final DataFixer c;
/*     */   private IResourceManager d;
/*     */   private final Path e;
/*     */   
/*     */   public DefinedStructureManager(IResourceManager iresourcemanager, Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer) {
/*  28 */     this.d = iresourcemanager;
/*  29 */     this.c = datafixer;
/*  30 */     this.e = convertable_conversionsession.getWorldFolder(SavedFile.GENERATED).normalize();
/*     */   }
/*     */   
/*     */   public DefinedStructure a(MinecraftKey minecraftkey) {
/*  34 */     DefinedStructure definedstructure = b(minecraftkey);
/*     */     
/*  36 */     if (definedstructure == null) {
/*  37 */       definedstructure = new DefinedStructure();
/*  38 */       this.b.put(minecraftkey, definedstructure);
/*     */     } 
/*     */     
/*  41 */     return definedstructure;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DefinedStructure b(MinecraftKey minecraftkey) {
/*  46 */     return this.b.computeIfAbsent(minecraftkey, minecraftkey1 -> {
/*     */           DefinedStructure definedstructure = f(minecraftkey1);
/*     */           return (definedstructure != null) ? definedstructure : e(minecraftkey1);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IResourceManager iresourcemanager) {
/*  54 */     this.d = iresourcemanager;
/*  55 */     this.b.clear();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private DefinedStructure e(MinecraftKey minecraftkey) {
/*  60 */     MinecraftKey minecraftkey1 = new MinecraftKey(minecraftkey.getNamespace(), "structures/" + minecraftkey.getKey() + ".nbt");
/*     */     try {
/*     */       DefinedStructure definedstructure;
/*  63 */       IResource iresource = this.d.a(minecraftkey1);
/*  64 */       Throwable throwable = null;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  69 */         definedstructure = a(iresource.b());
/*  70 */       } catch (Throwable throwable1) {
/*  71 */         throwable = throwable1;
/*  72 */         throw throwable1;
/*     */       } finally {
/*  74 */         if (iresource != null) {
/*  75 */           if (throwable != null) {
/*     */             try {
/*  77 */               iresource.close();
/*  78 */             } catch (Throwable throwable2) {
/*  79 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/*  82 */             iresource.close();
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  88 */       return definedstructure;
/*  89 */     } catch (FileNotFoundException filenotfoundexception) {
/*  90 */       return null;
/*  91 */     } catch (Throwable throwable3) {
/*  92 */       LOGGER.error("Couldn't load structure {}: {}", minecraftkey, throwable3.toString());
/*  93 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private DefinedStructure f(MinecraftKey minecraftkey) {
/*  99 */     if (!this.e.toFile().isDirectory()) {
/* 100 */       return null;
/*     */     }
/* 102 */     Path java_nio_file_path = b(minecraftkey, ".nbt");
/*     */     try {
/*     */       DefinedStructure definedstructure;
/* 105 */       FileInputStream fileinputstream = new FileInputStream(java_nio_file_path.toFile());
/* 106 */       Throwable throwable = null;
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 111 */         definedstructure = a(fileinputstream);
/* 112 */       } catch (Throwable throwable1) {
/* 113 */         throwable = throwable1;
/* 114 */         throw throwable1;
/*     */       } finally {
/* 116 */         if (fileinputstream != null) {
/* 117 */           if (throwable != null) {
/*     */             try {
/* 119 */               fileinputstream.close();
/* 120 */             } catch (Throwable throwable2) {
/* 121 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 124 */             fileinputstream.close();
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 130 */       return definedstructure;
/* 131 */     } catch (FileNotFoundException filenotfoundexception) {
/* 132 */       return null;
/* 133 */     } catch (IOException ioexception) {
/* 134 */       LOGGER.error("Couldn't load structure from {}", java_nio_file_path, ioexception);
/* 135 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private DefinedStructure a(InputStream inputstream) throws IOException {
/* 141 */     NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a(inputstream);
/*     */     
/* 143 */     return a(nbttagcompound);
/*     */   }
/*     */   
/*     */   public DefinedStructure a(NBTTagCompound nbttagcompound) {
/* 147 */     if (!nbttagcompound.hasKeyOfType("DataVersion", 99)) {
/* 148 */       nbttagcompound.setInt("DataVersion", 500);
/*     */     }
/*     */     
/* 151 */     DefinedStructure definedstructure = new DefinedStructure();
/*     */     
/* 153 */     definedstructure.b(GameProfileSerializer.a(this.c, DataFixTypes.STRUCTURE, nbttagcompound, nbttagcompound.getInt("DataVersion")));
/* 154 */     return definedstructure;
/*     */   }
/*     */   
/*     */   public boolean c(MinecraftKey minecraftkey) {
/* 158 */     DefinedStructure definedstructure = this.b.get(minecraftkey);
/*     */     
/* 160 */     if (definedstructure == null) {
/* 161 */       return false;
/*     */     }
/* 163 */     Path java_nio_file_path = b(minecraftkey, ".nbt");
/* 164 */     Path java_nio_file_path1 = java_nio_file_path.getParent();
/*     */     
/* 166 */     if (java_nio_file_path1 == null) {
/* 167 */       return false;
/*     */     }
/*     */     try {
/* 170 */       Files.createDirectories(Files.exists(java_nio_file_path1, new java.nio.file.LinkOption[0]) ? java_nio_file_path1.toRealPath(new java.nio.file.LinkOption[0]) : java_nio_file_path1, (FileAttribute<?>[])new FileAttribute[0]);
/* 171 */     } catch (IOException ioexception) {
/* 172 */       LOGGER.error("Failed to create parent directory: {}", java_nio_file_path1);
/* 173 */       return false;
/*     */     } 
/*     */     
/* 176 */     NBTTagCompound nbttagcompound = definedstructure.a(new NBTTagCompound());
/*     */     
/*     */     try {
/* 179 */       FileOutputStream fileoutputstream = new FileOutputStream(java_nio_file_path.toFile());
/* 180 */       Throwable throwable = null;
/*     */       
/*     */       try {
/* 183 */         NBTCompressedStreamTools.a(nbttagcompound, fileoutputstream);
/* 184 */       } catch (Throwable throwable1) {
/* 185 */         throwable = throwable1;
/* 186 */         throw throwable1;
/*     */       } finally {
/* 188 */         if (fileoutputstream != null) {
/* 189 */           if (throwable != null) {
/*     */             try {
/* 191 */               fileoutputstream.close();
/* 192 */             } catch (Throwable throwable2) {
/* 193 */               throwable.addSuppressed(throwable2);
/*     */             } 
/*     */           } else {
/* 196 */             fileoutputstream.close();
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 202 */       return true;
/* 203 */     } catch (Throwable throwable3) {
/* 204 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Path a(MinecraftKey minecraftkey, String s) {
/*     */     try {
/* 212 */       Path java_nio_file_path = this.e.resolve(minecraftkey.getNamespace());
/* 213 */       Path java_nio_file_path1 = java_nio_file_path.resolve("structures");
/*     */       
/* 215 */       return FileUtils.b(java_nio_file_path1, minecraftkey.getKey(), s);
/* 216 */     } catch (InvalidPathException invalidpathexception) {
/* 217 */       throw new ResourceKeyInvalidException("Invalid resource path: " + minecraftkey, invalidpathexception);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Path b(MinecraftKey minecraftkey, String s) {
/* 222 */     if (minecraftkey.getKey().contains("//")) {
/* 223 */       throw new ResourceKeyInvalidException("Invalid resource path: " + minecraftkey);
/*     */     }
/* 225 */     Path java_nio_file_path = a(minecraftkey, s);
/*     */     
/* 227 */     if (java_nio_file_path.startsWith(this.e) && FileUtils.a(java_nio_file_path) && FileUtils.b(java_nio_file_path)) {
/* 228 */       return java_nio_file_path;
/*     */     }
/* 230 */     throw new ResourceKeyInvalidException("Invalid resource path: " + java_nio_file_path);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(MinecraftKey minecraftkey) {
/* 236 */     this.b.remove(minecraftkey);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */