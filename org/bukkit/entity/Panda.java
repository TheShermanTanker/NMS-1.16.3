/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface Panda
/*    */   extends Animals
/*    */ {
/*    */   @NotNull
/*    */   Gene getMainGene();
/*    */   
/*    */   void setMainGene(@NotNull Gene paramGene);
/*    */   
/*    */   @NotNull
/*    */   Gene getHiddenGene();
/*    */   
/*    */   void setHiddenGene(@NotNull Gene paramGene);
/*    */   
/*    */   public enum Gene
/*    */   {
/* 42 */     NORMAL(false),
/* 43 */     LAZY(false),
/* 44 */     WORRIED(false),
/* 45 */     PLAYFUL(false),
/* 46 */     BROWN(true),
/* 47 */     WEAK(true),
/* 48 */     AGGRESSIVE(false);
/*    */     
/*    */     private final boolean recessive;
/*    */     
/*    */     Gene(boolean recessive) {
/* 53 */       this.recessive = recessive;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean isRecessive() {
/* 63 */       return this.recessive;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Panda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */