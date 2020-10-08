/*     */ package org.bukkit;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public interface WorldBorder
/*     */ {
/*     */   void reset();
/*     */   
/*     */   double getSize();
/*     */   
/*     */   void setSize(double paramDouble);
/*     */   
/*     */   void setSize(double paramDouble, long paramLong);
/*     */   
/*     */   @NotNull
/*     */   Location getCenter();
/*     */   
/*     */   void setCenter(double paramDouble1, double paramDouble2);
/*     */   
/*     */   void setCenter(@NotNull Location paramLocation);
/*     */   
/*     */   double getDamageBuffer();
/*     */   
/*     */   void setDamageBuffer(double paramDouble);
/*     */   
/*     */   double getDamageAmount();
/*     */   
/*     */   void setDamageAmount(double paramDouble);
/*     */   
/*     */   int getWarningTime();
/*     */   
/*     */   void setWarningTime(int paramInt);
/*     */   
/*     */   int getWarningDistance();
/*     */   
/*     */   void setWarningDistance(int paramInt);
/*     */   
/*     */   boolean isInside(@NotNull Location paramLocation);
/*     */   
/*     */   @Deprecated
/*     */   default boolean isInBounds(@NotNull Location location) {
/* 131 */     return isInside(location);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\WorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */