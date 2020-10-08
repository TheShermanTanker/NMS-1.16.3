/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public interface IWorldWriter {
/*    */   boolean a(BlockPosition paramBlockPosition, IBlockData paramIBlockData, int paramInt1, int paramInt2);
/*    */   
/*    */   default boolean setTypeAndData(BlockPosition blockposition, IBlockData iblockdata, int i) {
/* 10 */     return a(blockposition, iblockdata, i, 512);
/*    */   }
/*    */   
/*    */   boolean a(BlockPosition paramBlockPosition, boolean paramBoolean);
/*    */   
/*    */   default boolean b(BlockPosition blockposition, boolean flag) {
/* 16 */     return a(blockposition, flag, (Entity)null);
/*    */   }
/*    */   
/*    */   default boolean a(BlockPosition blockposition, boolean flag, @Nullable Entity entity) {
/* 20 */     return a(blockposition, flag, entity, 512);
/*    */   }
/*    */   
/*    */   boolean a(BlockPosition paramBlockPosition, boolean paramBoolean, @Nullable Entity paramEntity, int paramInt);
/*    */   
/*    */   default boolean addEntity(Entity entity) {
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean addEntity(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
/* 31 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */