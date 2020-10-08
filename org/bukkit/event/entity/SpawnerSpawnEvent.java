/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.block.CreatureSpawner;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SpawnerSpawnEvent
/*    */   extends EntitySpawnEvent
/*    */ {
/*    */   private final CreatureSpawner spawner;
/*    */   
/*    */   public SpawnerSpawnEvent(@NotNull Entity spawnee, @NotNull CreatureSpawner spawner) {
/* 16 */     super(spawnee);
/* 17 */     this.spawner = spawner;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public CreatureSpawner getSpawner() {
/* 22 */     return this.spawner;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\SpawnerSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */