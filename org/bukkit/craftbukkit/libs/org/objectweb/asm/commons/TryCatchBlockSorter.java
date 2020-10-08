/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TryCatchBlockNode;
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
/*     */ public class TryCatchBlockSorter
/*     */   extends MethodNode
/*     */ {
/*     */   public TryCatchBlockSorter(MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/*  73 */     this(524288, methodVisitor, access, name, descriptor, signature, exceptions);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (getClass() != TryCatchBlockSorter.class) {
/*  82 */       throw new IllegalStateException();
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
/*     */   protected TryCatchBlockSorter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/*  94 */     super(api, access, name, descriptor, signature, exceptions);
/*  95 */     this.mv = methodVisitor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 101 */     Collections.sort(this.tryCatchBlocks, new Comparator<TryCatchBlockNode>()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public int compare(TryCatchBlockNode tryCatchBlockNode1, TryCatchBlockNode tryCatchBlockNode2)
/*     */           {
/* 109 */             return blockLength(tryCatchBlockNode1) - blockLength(tryCatchBlockNode2);
/*     */           }
/*     */           
/*     */           private int blockLength(TryCatchBlockNode tryCatchBlockNode) {
/* 113 */             int startIndex = TryCatchBlockSorter.this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.start);
/* 114 */             int endIndex = TryCatchBlockSorter.this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.end);
/* 115 */             return endIndex - startIndex;
/*     */           }
/*     */         });
/*     */     
/* 119 */     for (int i = 0; i < this.tryCatchBlocks.size(); i++) {
/* 120 */       ((TryCatchBlockNode)this.tryCatchBlocks.get(i)).updateIndex(i);
/*     */     }
/* 122 */     if (this.mv != null)
/* 123 */       accept(this.mv); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\TryCatchBlockSorter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */