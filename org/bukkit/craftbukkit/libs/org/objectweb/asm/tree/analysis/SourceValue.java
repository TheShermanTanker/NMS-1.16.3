/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
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
/*     */ public class SourceValue
/*     */   implements Value
/*     */ {
/*     */   public final int size;
/*     */   public final Set<AbstractInsnNode> insns;
/*     */   
/*     */   public SourceValue(int size) {
/*  68 */     this(size, new SmallSet<AbstractInsnNode>());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue(int size, AbstractInsnNode insnNode) {
/*  79 */     this.size = size;
/*  80 */     this.insns = new SmallSet<AbstractInsnNode>(insnNode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SourceValue(int size, Set<AbstractInsnNode> insnSet) {
/*  91 */     this.size = size;
/*  92 */     this.insns = insnSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 103 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object value) {
/* 108 */     if (!(value instanceof SourceValue)) {
/* 109 */       return false;
/*     */     }
/* 111 */     SourceValue sourceValue = (SourceValue)value;
/* 112 */     return (this.size == sourceValue.size && this.insns.equals(sourceValue.insns));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     return this.insns.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\SourceValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */