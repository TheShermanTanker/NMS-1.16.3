/*    */ package org.bukkit.event.raid;
/*    */ 
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.world.WorldEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class RaidEvent
/*    */   extends WorldEvent
/*    */ {
/*    */   private final Raid raid;
/*    */   
/*    */   protected RaidEvent(@NotNull Raid raid, @NotNull World world) {
/* 16 */     super(world);
/* 17 */     this.raid = raid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Raid getRaid() {
/* 27 */     return this.raid;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\raid\RaidEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */