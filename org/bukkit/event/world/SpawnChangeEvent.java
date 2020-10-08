/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpawnChangeEvent
/*    */   extends WorldEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Location previousLocation;
/*    */   
/*    */   public SpawnChangeEvent(@NotNull World world, @NotNull Location previousLocation) {
/* 17 */     super(world);
/* 18 */     this.previousLocation = previousLocation;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Location getPreviousLocation() {
/* 28 */     return this.previousLocation;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 34 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 39 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\SpawnChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */