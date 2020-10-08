/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.DyeColor;
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
/*    */ public interface TropicalFish
/*    */   extends Fish
/*    */ {
/*    */   @NotNull
/*    */   DyeColor getPatternColor();
/*    */   
/*    */   void setPatternColor(@NotNull DyeColor paramDyeColor);
/*    */   
/*    */   @NotNull
/*    */   DyeColor getBodyColor();
/*    */   
/*    */   void setBodyColor(@NotNull DyeColor paramDyeColor);
/*    */   
/*    */   @NotNull
/*    */   Pattern getPattern();
/*    */   
/*    */   void setPattern(@NotNull Pattern paramPattern);
/*    */   
/*    */   public enum Pattern
/*    */   {
/* 63 */     KOB,
/* 64 */     SUNSTREAK,
/* 65 */     SNOOPER,
/* 66 */     DASHER,
/* 67 */     BRINELY,
/* 68 */     SPOTTY,
/* 69 */     FLOPPER,
/* 70 */     STRIPEY,
/* 71 */     GLITTER,
/* 72 */     BLOCKFISH,
/* 73 */     BETTY,
/* 74 */     CLAYFISH;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\TropicalFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */