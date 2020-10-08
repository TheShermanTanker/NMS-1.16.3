/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.signature.SignatureVisitor;
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
/*     */ public class SignatureRemapper
/*     */   extends SignatureVisitor
/*     */ {
/*     */   private final SignatureVisitor signatureVisitor;
/*     */   private final Remapper remapper;
/*  46 */   private ArrayList<String> classNames = new ArrayList<String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SignatureRemapper(SignatureVisitor signatureVisitor, Remapper remapper) {
/*  56 */     this(524288, signatureVisitor, remapper);
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
/*     */   protected SignatureRemapper(int api, SignatureVisitor signatureVisitor, Remapper remapper) {
/*  71 */     super(api);
/*  72 */     this.signatureVisitor = signatureVisitor;
/*  73 */     this.remapper = remapper;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitClassType(String name) {
/*  78 */     this.classNames.add(name);
/*  79 */     this.signatureVisitor.visitClassType(this.remapper.mapType(name));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInnerClassType(String name) {
/*  84 */     String outerClassName = this.classNames.remove(this.classNames.size() - 1);
/*  85 */     String className = outerClassName + '$' + name;
/*  86 */     this.classNames.add(className);
/*  87 */     String remappedOuter = this.remapper.mapType(outerClassName) + '$';
/*  88 */     String remappedName = this.remapper.mapType(className);
/*     */ 
/*     */ 
/*     */     
/*  92 */     int index = remappedName.startsWith(remappedOuter) ? remappedOuter.length() : (remappedName.lastIndexOf('$') + 1);
/*  93 */     this.signatureVisitor.visitInnerClassType(remappedName.substring(index));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitFormalTypeParameter(String name) {
/*  98 */     this.signatureVisitor.visitFormalTypeParameter(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeVariable(String name) {
/* 103 */     this.signatureVisitor.visitTypeVariable(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitArrayType() {
/* 108 */     this.signatureVisitor.visitArrayType();
/* 109 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitBaseType(char descriptor) {
/* 114 */     this.signatureVisitor.visitBaseType(descriptor);
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitClassBound() {
/* 119 */     this.signatureVisitor.visitClassBound();
/* 120 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitExceptionType() {
/* 125 */     this.signatureVisitor.visitExceptionType();
/* 126 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitInterface() {
/* 131 */     this.signatureVisitor.visitInterface();
/* 132 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitInterfaceBound() {
/* 137 */     this.signatureVisitor.visitInterfaceBound();
/* 138 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitParameterType() {
/* 143 */     this.signatureVisitor.visitParameterType();
/* 144 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitReturnType() {
/* 149 */     this.signatureVisitor.visitReturnType();
/* 150 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitSuperclass() {
/* 155 */     this.signatureVisitor.visitSuperclass();
/* 156 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeArgument() {
/* 161 */     this.signatureVisitor.visitTypeArgument();
/*     */   }
/*     */ 
/*     */   
/*     */   public SignatureVisitor visitTypeArgument(char wildcard) {
/* 166 */     this.signatureVisitor.visitTypeArgument(wildcard);
/* 167 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 172 */     this.signatureVisitor.visitEnd();
/* 173 */     this.classNames.remove(this.classNames.size() - 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\SignatureRemapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */