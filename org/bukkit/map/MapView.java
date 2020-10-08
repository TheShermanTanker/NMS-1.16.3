/*    */ package org.bukkit.map;public interface MapView { int getId(); boolean isVirtual(); @NotNull
/*    */   Scale getScale(); void setScale(@NotNull Scale paramScale); int getCenterX(); int getCenterZ();
/*    */   void setCenterX(int paramInt);
/*    */   void setCenterZ(int paramInt);
/*    */   @Nullable
/*    */   World getWorld();
/*    */   void setWorld(@NotNull World paramWorld);
/*    */   @NotNull
/*    */   List<MapRenderer> getRenderers();
/*    */   void addRenderer(@NotNull MapRenderer paramMapRenderer);
/*    */   boolean removeRenderer(@Nullable MapRenderer paramMapRenderer);
/*    */   boolean isTrackingPosition();
/*    */   void setTrackingPosition(boolean paramBoolean);
/*    */   boolean isUnlimitedTracking();
/*    */   void setUnlimitedTracking(boolean paramBoolean);
/*    */   boolean isLocked();
/*    */   void setLocked(boolean paramBoolean);
/* 18 */   public enum Scale { CLOSEST(0),
/* 19 */     CLOSE(1),
/* 20 */     NORMAL(2),
/* 21 */     FAR(3),
/* 22 */     FARTHEST(4);
/*    */     
/*    */     private byte value;
/*    */     
/*    */     Scale(int value) {
/* 27 */       this.value = (byte)value;
/*    */     }
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
/*    */     @Deprecated
/*    */     public byte getValue() {
/* 58 */       return this.value;
/*    */     } }
/*    */    }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */