/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import com.google.common.io.ByteStreams;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.zip.ZipEntry;
/*     */ import javax.annotation.Nonnull;
/*     */ import joptsimple.ArgumentAcceptingOptionSpec;
/*     */ import joptsimple.OptionParser;
/*     */ import joptsimple.OptionSet;
/*     */ import joptsimple.OptionSpec;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassReader;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassWriter;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.FieldVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.plugin.AuthorNagException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Commodore
/*     */ {
/*  43 */   private static final Set<String> EVIL = new HashSet<>(Arrays.asList(new String[] { "org/bukkit/World (III)I getBlockTypeIdAt", "org/bukkit/World (Lorg/bukkit/Location;)I getBlockTypeIdAt", "org/bukkit/block/Block ()I getTypeId", "org/bukkit/block/Block (I)Z setTypeId", "org/bukkit/block/Block (IZ)Z setTypeId", "org/bukkit/block/Block (IBZ)Z setTypeIdAndData", "org/bukkit/block/Block (B)V setData", "org/bukkit/block/Block (BZ)V setData", "org/bukkit/inventory/ItemStack ()I getTypeId", "org/bukkit/inventory/ItemStack (I)V setTypeId" }));
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
/*  57 */   private static final Map<String, String> SEARCH_AND_REMOVE = initReplacementsMap();
/*     */   
/*     */   private static Map<String, String> initReplacementsMap() {
/*  60 */     Map<String, String> getAndRemove = new HashMap<>();
/*     */     
/*  62 */     getAndRemove.put("org/bukkit/".concat("craftbukkit/libs/it/unimi/dsi/fastutil/"), "org/bukkit/".concat("craftbukkit/libs/"));
/*     */     
/*  64 */     if (Boolean.getBoolean("debug.rewriteForIde")) {
/*     */ 
/*     */       
/*  67 */       String NMS_REVISION_PACKAGE = "v1_16_R2/";
/*     */       
/*  69 */       getAndRemove.put("net/minecraft/".concat("server/v1_16_R2/"), "v1_16_R2/");
/*  70 */       getAndRemove.put("org/bukkit/".concat("craftbukkit/v1_16_R2/"), "v1_16_R2/");
/*     */     } 
/*     */     
/*  73 */     return getAndRemove;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   private static String getOriginalOrRewrite(@Nonnull String original) {
/*  79 */     String rewrite = null;
/*  80 */     for (Map.Entry<String, String> entry : SEARCH_AND_REMOVE.entrySet()) {
/*     */       
/*  82 */       if (original.contains(entry.getKey()))
/*     */       {
/*  84 */         rewrite = original.replace(entry.getValue(), "");
/*     */       }
/*     */     } 
/*     */     
/*  88 */     return (rewrite != null) ? rewrite : original;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  94 */     OptionParser parser = new OptionParser();
/*  95 */     ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec1 = parser.acceptsAll(Arrays.asList(new String[] { "i", "input" })).withRequiredArg().ofType(File.class).required();
/*  96 */     ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec2 = parser.acceptsAll(Arrays.asList(new String[] { "o", "output" })).withRequiredArg().ofType(File.class).required();
/*     */     
/*  98 */     OptionSet options = parser.parse(args);
/*     */     
/* 100 */     File input = (File)options.valueOf((OptionSpec)argumentAcceptingOptionSpec1);
/* 101 */     File output = (File)options.valueOf((OptionSpec)argumentAcceptingOptionSpec2);
/*     */     
/* 103 */     if (input.isDirectory()) {
/*     */       
/* 105 */       if (!output.isDirectory()) {
/*     */         
/* 107 */         System.err.println("If input directory specified, output directory required too");
/*     */         
/*     */         return;
/*     */       } 
/* 111 */       for (File in : input.listFiles()) {
/*     */         
/* 113 */         if (in.getName().endsWith(".jar"))
/*     */         {
/* 115 */           convert(in, new File(output, in.getName()));
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 120 */       convert(input, output);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void convert(File in, File out) {
/* 126 */     System.out.println("Attempting to convert " + in + " to " + out);
/*     */ 
/*     */     
/*     */     try {
/* 130 */       JarFile inJar = new JarFile(in, false);
/*     */       
/* 132 */       try { JarEntry entry = inJar.getJarEntry(".commodore");
/* 133 */         if (entry != null)
/*     */         
/*     */         { 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 161 */           inJar.close(); return; }  JarOutputStream outJar = new JarOutputStream(new FileOutputStream(out)); try { for (Enumeration<JarEntry> entries = inJar.entries(); entries.hasMoreElements(); ) { entry = entries.nextElement(); InputStream is = inJar.getInputStream(entry); try { byte[] b = ByteStreams.toByteArray(is); if (entry.getName().endsWith(".class")) { b = convert(b, false); entry = new JarEntry(entry.getName()); }  outJar.putNextEntry(entry); outJar.write(b); if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  }  outJar.putNextEntry(new ZipEntry(".commodore")); outJar.close(); } catch (Throwable throwable) { try { outJar.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  inJar.close(); } catch (Throwable throwable) { try { inJar.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; } 
/* 162 */     } catch (Exception ex) {
/*     */       
/* 164 */       System.err.println("Fatal error trying to convert " + in);
/* 165 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte[] convert(byte[] b, final boolean modern) {
/* 171 */     ClassReader cr = new ClassReader(b);
/* 172 */     ClassWriter cw = new ClassWriter(cr, 0);
/*     */     
/* 174 */     cr.accept(new ClassVisitor(524288, (ClassVisitor)cw)
/*     */         {
/*     */ 
/*     */           
/*     */           public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
/*     */           {
/* 180 */             desc = Commodore.getOriginalOrRewrite(desc);
/* 181 */             if (signature != null) {
/* 182 */               signature = Commodore.getOriginalOrRewrite(signature);
/*     */             }
/*     */             
/* 185 */             return super.visitField(access, name, desc, signature, value);
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
/* 192 */             return new MethodVisitor(this.api, super.visitMethod(access, name, desc, signature, exceptions))
/*     */               {
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void visitInvokeDynamicInsn(String name, String desc, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments)
/*     */                 {
/* 199 */                   name = Commodore.getOriginalOrRewrite(name);
/* 200 */                   if (desc != null)
/*     */                   {
/* 202 */                     desc = Commodore.getOriginalOrRewrite(desc);
/*     */                   }
/*     */ 
/*     */                   
/* 206 */                   super.visitInvokeDynamicInsn(name, desc, bootstrapMethodHandle, bootstrapMethodArguments);
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void visitTypeInsn(int opcode, String type) {
/* 212 */                   type = Commodore.getOriginalOrRewrite(type);
/*     */                   
/* 214 */                   super.visitTypeInsn(opcode, type);
/*     */                 }
/*     */                 
/*     */                 public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
/*     */                   int i;
/* 219 */                   for (i = 0; i < local.length; i++) {
/*     */                     
/* 221 */                     if (local[i] instanceof String)
/*     */                     {
/* 223 */                       local[i] = Commodore.getOriginalOrRewrite((String)local[i]);
/*     */                     }
/*     */                   } 
/* 226 */                   for (i = 0; i < stack.length; i++) {
/*     */                     
/* 228 */                     if (stack[i] instanceof String)
/*     */                     {
/* 230 */                       stack[i] = Commodore.getOriginalOrRewrite((String)stack[i]);
/*     */                     }
/*     */                   } 
/* 233 */                   super.visitFrame(type, nLocal, local, nStack, stack);
/*     */                 }
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 239 */                   descriptor = Commodore.getOriginalOrRewrite(descriptor);
/*     */                   
/* 241 */                   super.visitLocalVariable(name, descriptor, signature, start, end, index);
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void visitFieldInsn(int opcode, String owner, String name, String desc) {
/* 249 */                   owner = Commodore.getOriginalOrRewrite(owner);
/* 250 */                   if (desc != null)
/*     */                   {
/* 252 */                     desc = Commodore.getOriginalOrRewrite(desc);
/*     */                   }
/*     */ 
/*     */                   
/* 256 */                   if (owner.equals("org/bukkit/block/Biome"))
/*     */                   {
/* 258 */                     switch (name) {
/*     */                       
/*     */                       case "NETHER":
/* 261 */                         super.visitFieldInsn(opcode, owner, "NETHER_WASTES", desc);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   }
/* 266 */                   if (owner.equals("org/bukkit/entity/EntityType"))
/*     */                   {
/* 268 */                     switch (name) {
/*     */                       
/*     */                       case "PIG_ZOMBIE":
/* 271 */                         super.visitFieldInsn(opcode, owner, "ZOMBIFIED_PIGLIN", desc);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   }
/* 276 */                   if (modern) {
/*     */                     
/* 278 */                     if (owner.equals("org/bukkit/Material"))
/*     */                     {
/* 280 */                       switch (name) {
/*     */                         
/*     */                         case "CACTUS_GREEN":
/* 283 */                           name = "GREEN_DYE";
/*     */                           break;
/*     */                         case "DANDELION_YELLOW":
/* 286 */                           name = "YELLOW_DYE";
/*     */                           break;
/*     */                         case "ROSE_RED":
/* 289 */                           name = "RED_DYE";
/*     */                           break;
/*     */                         case "SIGN":
/* 292 */                           name = "OAK_SIGN";
/*     */                           break;
/*     */                         case "WALL_SIGN":
/* 295 */                           name = "OAK_WALL_SIGN";
/*     */                           break;
/*     */                         case "ZOMBIE_PIGMAN_SPAWN_EGG":
/* 298 */                           name = "ZOMBIFIED_PIGLIN_SPAWN_EGG";
/*     */                           break;
/*     */                       } 
/*     */                     
/*     */                     }
/* 303 */                     super.visitFieldInsn(opcode, owner, name, desc);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 307 */                   if (owner.equals("org/bukkit/Material")) {
/*     */ 
/*     */                     
/*     */                     try {
/* 311 */                       Material.valueOf("LEGACY_" + name);
/* 312 */                     } catch (IllegalArgumentException ex) {
/*     */                       
/* 314 */                       throw new AuthorNagException("No legacy enum constant for " + name + ". Did you forget to define a modern (1.13+) api-version in your plugin.yml?");
/*     */                     } 
/*     */                     
/* 317 */                     super.visitFieldInsn(opcode, owner, "LEGACY_" + name, desc);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 321 */                   if (owner.equals("org/bukkit/Art"))
/*     */                   {
/* 323 */                     switch (name) {
/*     */                       
/*     */                       case "BURNINGSKULL":
/* 326 */                         super.visitFieldInsn(opcode, owner, "BURNING_SKULL", desc);
/*     */                         return;
/*     */                       case "DONKEYKONG":
/* 329 */                         super.visitFieldInsn(opcode, owner, "DONKEY_KONG", desc);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   }
/* 334 */                   if (owner.equals("org/bukkit/DyeColor"))
/*     */                   {
/* 336 */                     switch (name) {
/*     */                       
/*     */                       case "SILVER":
/* 339 */                         super.visitFieldInsn(opcode, owner, "LIGHT_GRAY", desc);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   }
/* 344 */                   if (owner.equals("org/bukkit/Particle"))
/*     */                   {
/* 346 */                     switch (name) {
/*     */                       
/*     */                       case "BLOCK_CRACK":
/*     */                       case "BLOCK_DUST":
/*     */                       case "FALLING_DUST":
/* 351 */                         super.visitFieldInsn(opcode, owner, "LEGACY_" + name, desc);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   }
/* 356 */                   super.visitFieldInsn(opcode, owner, name, desc);
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
/* 363 */                   if (owner.equals("org/bukkit/map/MapView") && name.equals("getId") && desc.equals("()S")) {
/*     */ 
/*     */                     
/* 366 */                     super.visitMethodInsn(opcode, owner, name, "()I", itf);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 370 */                   if ((owner.equals("org/bukkit/Bukkit") || owner.equals("org/bukkit/Server")) && name.equals("getMap") && desc.equals("(S)Lorg/bukkit/map/MapView;")) {
/*     */ 
/*     */                     
/* 373 */                     super.visitMethodInsn(opcode, owner, name, "(I)Lorg/bukkit/map/MapView;", itf);
/*     */                     
/*     */                     return;
/*     */                   } 
/*     */                   
/* 378 */                   owner = Commodore.getOriginalOrRewrite(owner);
/* 379 */                   if (desc != null)
/*     */                   {
/* 381 */                     desc = Commodore.getOriginalOrRewrite(desc);
/*     */                   }
/*     */ 
/*     */                   
/* 385 */                   if (modern) {
/*     */                     
/* 387 */                     if (owner.equals("org/bukkit/Material"))
/*     */                     {
/* 389 */                       switch (name) {
/*     */                         
/*     */                         case "values":
/* 392 */                           super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_16_R2/util/CraftLegacy", "modern_" + name, desc, itf);
/*     */                           return;
/*     */                         case "ordinal":
/* 395 */                           super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_16_R2/util/CraftLegacy", "modern_" + name, "(Lorg/bukkit/Material;)I", false);
/*     */                           return;
/*     */                       } 
/*     */                     
/*     */                     }
/* 400 */                     super.visitMethodInsn(opcode, owner, name, desc, itf);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 404 */                   if (owner.equals("org/bukkit/ChunkSnapshot") && name.equals("getBlockData") && desc.equals("(III)I")) {
/*     */                     
/* 406 */                     super.visitMethodInsn(opcode, owner, "getData", desc, itf);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 410 */                   Type retType = Type.getReturnType(desc);
/*     */                   
/* 412 */                   if (Commodore.EVIL.contains(owner + " " + desc + " " + name) || (owner
/* 413 */                     .startsWith("org/bukkit/block/") && (desc + " " + name).equals("()I getTypeId")) || (owner
/* 414 */                     .startsWith("org/bukkit/block/") && (desc + " " + name).equals("(I)Z setTypeId")) || (owner
/* 415 */                     .startsWith("org/bukkit/block/") && (desc + " " + name).equals("()Lorg/bukkit/Material; getType"))) {
/*     */                     
/* 417 */                     Type[] args = Type.getArgumentTypes(desc);
/* 418 */                     Type[] newArgs = new Type[args.length + 1];
/* 419 */                     newArgs[0] = Type.getObjectType(owner);
/* 420 */                     System.arraycopy(args, 0, newArgs, 1, args.length);
/*     */                     
/* 422 */                     super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftEvil", name, Type.getMethodDescriptor(retType, newArgs), false);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 426 */                   if (owner.equals("org/bukkit/DyeColor"))
/*     */                   {
/* 428 */                     if (name.equals("valueOf") && desc.equals("(Ljava/lang/String;)Lorg/bukkit/DyeColor;")) {
/*     */                       
/* 430 */                       super.visitMethodInsn(opcode, owner, "legacyValueOf", desc, itf);
/*     */                       
/*     */                       return;
/*     */                     } 
/*     */                   }
/* 435 */                   if (owner.equals("org/bukkit/Material")) {
/*     */                     
/* 437 */                     if (name.equals("getMaterial") && desc.equals("(I)Lorg/bukkit/Material;")) {
/*     */                       
/* 439 */                       super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftEvil", name, desc, itf);
/*     */                       
/*     */                       return;
/*     */                     } 
/* 443 */                     switch (name) {
/*     */                       
/*     */                       case "values":
/*     */                       case "valueOf":
/*     */                       case "getMaterial":
/*     */                       case "matchMaterial":
/* 449 */                         super.visitMethodInsn(opcode, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftLegacy", name, desc, itf);
/*     */                         return;
/*     */                       case "ordinal":
/* 452 */                         super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftLegacy", "ordinal", "(Lorg/bukkit/Material;)I", false);
/*     */                         return;
/*     */                       case "name":
/*     */                       case "toString":
/* 456 */                         super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftLegacy", name, "(Lorg/bukkit/Material;)Ljava/lang/String;", false);
/*     */                         return;
/*     */                     } 
/*     */                   
/*     */                   } 
/* 461 */                   if (retType.getSort() == 10 && retType.getInternalName().equals("org/bukkit/Material") && owner.startsWith("org/bukkit")) {
/*     */                     
/* 463 */                     super.visitMethodInsn(opcode, owner, name, desc, itf);
/* 464 */                     super.visitMethodInsn(184, "org/bukkit/craftbukkit/v1_16_R2/legacy/CraftLegacy", "toLegacy", "(Lorg/bukkit/Material;)Lorg/bukkit/Material;", false);
/*     */                     
/*     */                     return;
/*     */                   } 
/* 468 */                   super.visitMethodInsn(opcode, owner, name, desc, itf);
/*     */                 }
/*     */               };
/*     */           }
/*     */         }0);
/*     */     
/* 474 */     return cw.toByteArray();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\Commodore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */