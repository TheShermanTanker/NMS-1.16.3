/*    */ package org.bukkit.craftbukkit.v1_16_R2.map;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MapIcon;
/*    */ import net.minecraft.server.v1_16_R2.WorldMap;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.map.MapCanvas;
/*    */ import org.bukkit.map.MapCursorCollection;
/*    */ import org.bukkit.map.MapRenderer;
/*    */ import org.bukkit.map.MapView;
/*    */ 
/*    */ public class CraftMapRenderer
/*    */   extends MapRenderer {
/*    */   private final WorldMap worldMap;
/*    */   
/*    */   public CraftMapRenderer(CraftMapView mapView, WorldMap worldMap) {
/* 18 */     super(false);
/* 19 */     this.worldMap = worldMap;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(MapView map, MapCanvas canvas, Player player) {
/* 25 */     for (int x = 0; x < 128; x++) {
/* 26 */       for (int y = 0; y < 128; y++) {
/* 27 */         canvas.setPixel(x, y, this.worldMap.colors[y * 128 + x]);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 32 */     MapCursorCollection cursors = canvas.getCursors();
/* 33 */     while (cursors.size() > 0) {
/* 34 */       cursors.removeCursor(cursors.getCursor(0));
/*    */     }
/*    */     
/* 37 */     for (Object key : this.worldMap.decorations.keySet()) {
/*    */       
/* 39 */       Player other = Bukkit.getPlayerExact((String)key);
/* 40 */       if (other != null && !player.canSee(other)) {
/*    */         continue;
/*    */       }
/*    */       
/* 44 */       MapIcon decoration = (MapIcon)this.worldMap.decorations.get(key);
/* 45 */       cursors.addCursor(decoration.getX(), decoration.getY(), (byte)(decoration.getRotation() & 0xF), decoration.getType().a(), true, CraftChatMessage.fromComponent(decoration.getName()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\map\CraftMapRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */