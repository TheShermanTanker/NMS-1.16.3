/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ public final class ModuleHashesAttribute
/*     */   extends Attribute
/*     */ {
/*     */   public String algorithm;
/*     */   public List<String> modules;
/*     */   public List<byte[]> hashes;
/*     */   
/*     */   public ModuleHashesAttribute(String algorithm, List<String> modules, List<byte[]> hashes) {
/*  64 */     super("ModuleHashes");
/*  65 */     this.algorithm = algorithm;
/*  66 */     this.modules = modules;
/*  67 */     this.hashes = hashes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleHashesAttribute() {
/*  75 */     this(null, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Attribute read(ClassReader classReader, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
/*  86 */     int currentOffset = offset;
/*     */     
/*  88 */     String hashAlgorithm = classReader.readUTF8(currentOffset, charBuffer);
/*  89 */     currentOffset += 2;
/*     */     
/*  91 */     int numModules = classReader.readUnsignedShort(currentOffset);
/*  92 */     currentOffset += 2;
/*     */     
/*  94 */     ArrayList<String> moduleList = new ArrayList<String>(numModules);
/*  95 */     ArrayList<byte[]> hashList = (ArrayList)new ArrayList<byte>(numModules);
/*     */     
/*  97 */     for (int i = 0; i < numModules; i++) {
/*  98 */       String module = classReader.readModule(currentOffset, charBuffer);
/*  99 */       currentOffset += 2;
/* 100 */       moduleList.add(module);
/*     */       
/* 102 */       int hashLength = classReader.readUnsignedShort(currentOffset);
/* 103 */       currentOffset += 2;
/* 104 */       byte[] hash = new byte[hashLength];
/* 105 */       for (int j = 0; j < hashLength; j++) {
/* 106 */         hash[j] = (byte)(classReader.readByte(currentOffset) & 0xFF);
/* 107 */         currentOffset++;
/*     */       } 
/* 109 */       hashList.add(hash);
/*     */     } 
/* 111 */     return new ModuleHashesAttribute(hashAlgorithm, moduleList, (List<byte[]>)hashList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ByteVector write(ClassWriter classWriter, byte[] code, int codeLength, int maxStack, int maxLocals) {
/* 121 */     ByteVector byteVector = new ByteVector();
/* 122 */     byteVector.putShort(classWriter.newUTF8(this.algorithm));
/* 123 */     if (this.modules == null) {
/* 124 */       byteVector.putShort(0);
/*     */     } else {
/* 126 */       int numModules = this.modules.size();
/* 127 */       byteVector.putShort(numModules);
/* 128 */       for (int i = 0; i < numModules; i++) {
/* 129 */         String module = this.modules.get(i);
/* 130 */         byte[] hash = this.hashes.get(i);
/* 131 */         byteVector
/* 132 */           .putShort(classWriter.newModule(module))
/* 133 */           .putShort(hash.length)
/* 134 */           .putByteArray(hash, 0, hash.length);
/*     */       } 
/*     */     } 
/* 137 */     return byteVector;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\ModuleHashesAttribute.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */