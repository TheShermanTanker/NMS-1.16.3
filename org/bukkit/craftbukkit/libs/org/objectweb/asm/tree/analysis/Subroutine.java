/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.JumpInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LabelNode;
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
/*     */ final class Subroutine
/*     */ {
/*     */   final LabelNode start;
/*     */   final boolean[] localsUsed;
/*     */   final List<JumpInsnNode> callers;
/*     */   
/*     */   Subroutine(LabelNode start, int maxLocals, JumpInsnNode caller) {
/*  62 */     this.start = start;
/*  63 */     this.localsUsed = new boolean[maxLocals];
/*  64 */     this.callers = new ArrayList<JumpInsnNode>();
/*  65 */     this.callers.add(caller);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Subroutine(Subroutine subroutine) {
/*  74 */     this.start = subroutine.start;
/*  75 */     this.localsUsed = (boolean[])subroutine.localsUsed.clone();
/*  76 */     this.callers = new ArrayList<JumpInsnNode>(subroutine.callers);
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
/*     */   public boolean merge(Subroutine subroutine) {
/*  88 */     boolean changed = false; int i;
/*  89 */     for (i = 0; i < this.localsUsed.length; i++) {
/*  90 */       if (subroutine.localsUsed[i] && !this.localsUsed[i]) {
/*  91 */         this.localsUsed[i] = true;
/*  92 */         changed = true;
/*     */       } 
/*     */     } 
/*  95 */     if (subroutine.start == this.start) {
/*  96 */       for (i = 0; i < subroutine.callers.size(); i++) {
/*  97 */         JumpInsnNode caller = subroutine.callers.get(i);
/*  98 */         if (!this.callers.contains(caller)) {
/*  99 */           this.callers.add(caller);
/* 100 */           changed = true;
/*     */         } 
/*     */       } 
/*     */     }
/* 104 */     return changed;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\Subroutine.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */