/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ByteVector;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassReader;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassWriter;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
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
/*     */ public final class ModuleResolutionAttribute
/*     */   extends Attribute
/*     */ {
/*     */   public static final int RESOLUTION_DO_NOT_RESOLVE_BY_DEFAULT = 1;
/*     */   public static final int RESOLUTION_WARN_DEPRECATED = 2;
/*     */   public static final int RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL = 4;
/*     */   public static final int RESOLUTION_WARN_INCUBATING = 8;
/*     */   public int resolution;
/*     */   
/*     */   public ModuleResolutionAttribute(int resolution) {
/*  79 */     super("ModuleResolution");
/*  80 */     this.resolution = resolution;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleResolutionAttribute() {
/*  88 */     this(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Attribute read(ClassReader classReader, int offset, int length, char[] charBuffer, int codeOffset, Label[] labels) {
/*  99 */     return new ModuleResolutionAttribute(classReader.readUnsignedShort(offset));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ByteVector write(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 109 */     ByteVector byteVector = new ByteVector();
/* 110 */     byteVector.putShort(this.resolution);
/* 111 */     return byteVector;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\ModuleResolutionAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */