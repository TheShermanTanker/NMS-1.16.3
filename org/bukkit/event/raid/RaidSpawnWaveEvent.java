/*    */ package org.bukkit.event.raid;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Raider;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RaidSpawnWaveEvent
/*    */   extends RaidEvent
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final List<Raider> raiders;
/*    */   private final Raider leader;
/*    */   
/*    */   public RaidSpawnWaveEvent(@NotNull Raid raid, @NotNull World world, @Nullable Raider leader, @NotNull List<Raider> raiders) {
/* 23 */     super(raid, world);
/* 24 */     this.raiders = raiders;
/* 25 */     this.leader = leader;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Raider getPatrolLeader() {
/* 35 */     return this.leader;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<Raider> getRaiders() {
/* 45 */     return Collections.unmodifiableList(this.raiders);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 51 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 56 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\raid\RaidSpawnWaveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */