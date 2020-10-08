/*     */ package org.bukkit.entity;public interface Horse extends AbstractHorse { @NotNull
/*     */   Color getColor();
/*     */   void setColor(@NotNull Color paramColor);
/*     */   @NotNull
/*     */   Style getStyle();
/*     */   
/*     */   void setStyle(@NotNull Style paramStyle);
/*     */   
/*     */   @Deprecated
/*     */   boolean isCarryingChest();
/*     */   
/*     */   @Deprecated
/*     */   void setCarryingChest(boolean paramBoolean);
/*     */   
/*     */   @NotNull
/*     */   HorseInventory getInventory();
/*     */   
/*     */   @Deprecated
/*  19 */   public enum Variant { HORSE,
/*     */ 
/*     */ 
/*     */     
/*  23 */     DONKEY,
/*     */ 
/*     */ 
/*     */     
/*  27 */     MULE,
/*     */ 
/*     */ 
/*     */     
/*  31 */     UNDEAD_HORSE,
/*     */ 
/*     */ 
/*     */     
/*  35 */     SKELETON_HORSE,
/*     */ 
/*     */ 
/*     */     
/*  39 */     LLAMA; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Color
/*     */   {
/*  50 */     WHITE,
/*     */ 
/*     */ 
/*     */     
/*  54 */     CREAMY,
/*     */ 
/*     */ 
/*     */     
/*  58 */     CHESTNUT,
/*     */ 
/*     */ 
/*     */     
/*  62 */     BROWN,
/*     */ 
/*     */ 
/*     */     
/*  66 */     BLACK,
/*     */ 
/*     */ 
/*     */     
/*  70 */     GRAY,
/*     */ 
/*     */ 
/*     */     
/*  74 */     DARK_BROWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Style
/*     */   {
/*  85 */     NONE,
/*     */ 
/*     */ 
/*     */     
/*  89 */     WHITE,
/*     */ 
/*     */ 
/*     */     
/*  93 */     WHITEFIELD,
/*     */ 
/*     */ 
/*     */     
/*  97 */     WHITE_DOTS,
/*     */ 
/*     */ 
/*     */     
/* 101 */     BLACK_DOTS;
/*     */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Horse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */