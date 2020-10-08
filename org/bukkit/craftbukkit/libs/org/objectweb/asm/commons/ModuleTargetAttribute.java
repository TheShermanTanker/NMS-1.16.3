/*    */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ByteVector;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassReader;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassWriter;
/*    */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ModuleTargetAttribute
/*    */   extends Attribute
/*    */ {
/*    */   public String platform;
/*    */   
/*    */   public ModuleTargetAttribute(String platform) {
/* 53 */     super("ModuleTarget");
/* 54 */     this.platform = platform;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ModuleTargetAttribute() {
/* 62 */     this(null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Attribute read(ClassReader classReader, int offset, int length, char[] charBuffer, int codeOffset, Label[] labels) {
/* 73 */     return new ModuleTargetAttribute(classReader.readUTF8(offset, charBuffer));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ByteVector write(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 83 */     ByteVector byteVector = new ByteVector();
/* 84 */     byteVector.putShort((this.platform == null) ? 0 : classWriter.newUTF8(this.platform));
/* 85 */     return byteVector;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\ModuleTargetAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */