/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public interface WorldAccess extends GeneratorAccess {
/*    */   WorldServer getMinecraftWorld();
/*    */   
/*    */   default void addAllEntities(Entity entity) {
/*  9 */     addAllEntities(entity, CreatureSpawnEvent.SpawnReason.DEFAULT);
/*    */   }
/*    */   
/*    */   default boolean addAllEntities(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 13 */     entity.co().forEach(e -> addEntity(e, reason));
/* 14 */     return !entity.dead;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */