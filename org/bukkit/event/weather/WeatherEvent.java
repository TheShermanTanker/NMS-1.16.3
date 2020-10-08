/*    */ package org.bukkit.event.weather;
/*    */ 
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class WeatherEvent
/*    */   extends Event
/*    */ {
/*    */   protected World world;
/*    */   
/*    */   public WeatherEvent(@NotNull World where) {
/* 14 */     this.world = where;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final World getWorld() {
/* 24 */     return this.world;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\weather\WeatherEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */