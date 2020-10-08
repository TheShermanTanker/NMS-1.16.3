/*    */ package org.bukkit;
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
/*    */ public enum Rotation
/*    */ {
/* 15 */   NONE,
/*    */ 
/*    */ 
/*    */   
/* 19 */   CLOCKWISE_45,
/*    */ 
/*    */ 
/*    */   
/* 23 */   CLOCKWISE,
/*    */ 
/*    */ 
/*    */   
/* 27 */   CLOCKWISE_135,
/*    */ 
/*    */ 
/*    */   
/* 31 */   FLIPPED,
/*    */ 
/*    */ 
/*    */   
/* 35 */   FLIPPED_45,
/*    */ 
/*    */ 
/*    */   
/* 39 */   COUNTER_CLOCKWISE,
/*    */ 
/*    */ 
/*    */   
/* 43 */   COUNTER_CLOCKWISE_45;
/*    */   
/*    */   static {
/* 46 */     rotations = values();
/*    */   }
/*    */ 
/*    */   
/*    */   private static final Rotation[] rotations;
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Rotation rotateClockwise() {
/* 55 */     return rotations[ordinal() + 1 & 0x7];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Rotation rotateCounterClockwise() {
/* 65 */     return rotations[ordinal() - 1 & 0x7];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Rotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */