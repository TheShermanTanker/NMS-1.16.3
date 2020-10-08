/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.destroystokyo.paper.event.entity.PhantomPreSpawnEvent;
/*    */ import java.util.Iterator;
/*    */ import java.util.Random;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ 
/*    */ public class MobSpawnerPhantom
/*    */   implements MobSpawner {
/*    */   private int a;
/*    */   
/*    */   public int a(WorldServer worldserver, boolean flag, boolean flag1) {
/* 14 */     if (!flag)
/* 15 */       return 0; 
/* 16 */     if (!worldserver.getGameRules().getBoolean(GameRules.DO_INSOMNIA)) {
/* 17 */       return 0;
/*    */     }
/* 19 */     Random random = worldserver.random;
/*    */     
/* 21 */     this.a--;
/* 22 */     if (this.a > 0) {
/* 23 */       return 0;
/*    */     }
/* 25 */     this.a += (60 + random.nextInt(60)) * 20;
/* 26 */     if (worldserver.c() < 5 && worldserver.getDimensionManager().hasSkyLight()) {
/* 27 */       return 0;
/*    */     }
/* 29 */     int i = 0;
/* 30 */     Iterator<EntityPlayer> iterator = worldserver.getPlayers().iterator();
/*    */     
/* 32 */     while (iterator.hasNext()) {
/* 33 */       EntityHuman entityhuman = iterator.next();
/*    */       
/* 35 */       if (!entityhuman.isSpectator() && (!worldserver.paperConfig.phantomIgnoreCreative || !entityhuman.isCreative())) {
/* 36 */         BlockPosition blockposition = entityhuman.getChunkCoordinates();
/*    */         
/* 38 */         if (!worldserver.getDimensionManager().hasSkyLight() || (blockposition.getY() >= worldserver.getSeaLevel() && worldserver.e(blockposition))) {
/* 39 */           DifficultyDamageScaler difficultydamagescaler = worldserver.getDamageScaler(blockposition);
/*    */           
/* 41 */           if (difficultydamagescaler.a(random.nextFloat() * 3.0F)) {
/* 42 */             ServerStatisticManager serverstatisticmanager = ((EntityPlayer)entityhuman).getStatisticManager();
/* 43 */             int j = MathHelper.clamp(serverstatisticmanager.getStatisticValue(StatisticList.CUSTOM.b(StatisticList.TIME_SINCE_REST)), 1, 2147483647);
/* 44 */             boolean flag2 = true;
/*    */             
/* 46 */             if (random.nextInt(j) >= 72000) {
/* 47 */               BlockPosition blockposition1 = blockposition.up(20 + random.nextInt(15)).east(-10 + random.nextInt(21)).south(-10 + random.nextInt(21));
/* 48 */               IBlockData iblockdata = worldserver.getType(blockposition1);
/* 49 */               Fluid fluid = worldserver.getFluid(blockposition1);
/*    */               
/* 51 */               if (SpawnerCreature.a(worldserver, blockposition1, iblockdata, fluid, EntityTypes.PHANTOM)) {
/* 52 */                 GroupDataEntity groupdataentity = null;
/* 53 */                 int k = 1 + random.nextInt(difficultydamagescaler.a().a() + 1);
/*    */                 
/* 55 */                 for (int l = 0; l < k; l++) {
/*    */                   
/* 57 */                   PhantomPreSpawnEvent event = new PhantomPreSpawnEvent(MCUtil.toLocation(worldserver, blockposition1), (Entity)((EntityPlayer)entityhuman).getBukkitEntity(), CreatureSpawnEvent.SpawnReason.NATURAL);
/* 58 */                   if (!event.callEvent()) {
/* 59 */                     if (event.shouldAbortSpawn()) {
/*    */                       break;
/*    */                     }
/*    */                   }
/*    */                   else {
/*    */                     
/* 65 */                     EntityPhantom entityphantom = EntityTypes.PHANTOM.a(worldserver);
/* 66 */                     entityphantom.spawningEntity = entityhuman.uniqueID;
/* 67 */                     entityphantom.setPositionRotation(blockposition1, 0.0F, 0.0F);
/* 68 */                     groupdataentity = entityphantom.prepare(worldserver, difficultydamagescaler, EnumMobSpawn.NATURAL, groupdataentity, (NBTTagCompound)null);
/* 69 */                     worldserver.addAllEntities(entityphantom, CreatureSpawnEvent.SpawnReason.NATURAL);
/*    */                   } 
/*    */                 } 
/* 72 */                 i += k;
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 80 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerPhantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */