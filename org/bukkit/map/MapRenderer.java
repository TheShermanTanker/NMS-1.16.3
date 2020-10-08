/*    */ package org.bukkit.map;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class MapRenderer
/*    */ {
/*    */   private boolean contextual;
/*    */   
/*    */   public MapRenderer() {
/* 18 */     this(false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MapRenderer(boolean contextual) {
/* 28 */     this.contextual = contextual;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final boolean isContextual() {
/* 38 */     return this.contextual;
/*    */   }
/*    */   
/*    */   public void initialize(@NotNull MapView map) {}
/*    */   
/*    */   public abstract void render(@NotNull MapView paramMapView, @NotNull MapCanvas paramMapCanvas, @NotNull Player paramPlayer);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */