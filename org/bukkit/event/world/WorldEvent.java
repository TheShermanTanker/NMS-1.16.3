/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class WorldEvent
/*    */   extends Event
/*    */ {
/*    */   private final World world;
/*    */   
/*    */   public WorldEvent(@NotNull World world) {
/* 14 */     this.world = world;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public World getWorld() {
/* 24 */     return this.world;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\WorldEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */