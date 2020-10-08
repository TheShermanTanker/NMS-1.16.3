/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MobSpawnerCat
/*    */   implements MobSpawner
/*    */ {
/*    */   private int a;
/*    */   
/*    */   public int a(WorldServer var0, boolean var1, boolean var2) {
/* 30 */     if (!var2 || !var0.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
/* 31 */       return 0;
/*    */     }
/*    */     
/* 34 */     this.a--;
/* 35 */     if (this.a > 0) {
/* 36 */       return 0;
/*    */     }
/*    */     
/* 39 */     this.a = 1200;
/*    */     
/* 41 */     EntityHuman var3 = var0.q_();
/* 42 */     if (var3 == null) {
/* 43 */       return 0;
/*    */     }
/*    */     
/* 46 */     Random var4 = var0.random;
/* 47 */     int var5 = (8 + var4.nextInt(24)) * (var4.nextBoolean() ? -1 : 1);
/* 48 */     int var6 = (8 + var4.nextInt(24)) * (var4.nextBoolean() ? -1 : 1);
/* 49 */     BlockPosition var7 = var3.getChunkCoordinates().b(var5, 0, var6);
/*    */     
/* 51 */     if (!var0.isAreaLoaded(var7.getX() - 10, var7.getY() - 10, var7.getZ() - 10, var7.getX() + 10, var7.getY() + 10, var7.getZ() + 10)) {
/* 52 */       return 0;
/*    */     }
/*    */     
/* 55 */     if (SpawnerCreature.a(EntityPositionTypes.Surface.ON_GROUND, var0, var7, EntityTypes.CAT)) {
/* 56 */       if (var0.a(var7, 2)) {
/* 57 */         return a(var0, var7);
/*    */       }
/*    */       
/* 60 */       if (var0.getStructureManager().a(var7, true, StructureGenerator.SWAMP_HUT).e()) {
/* 61 */         return b(var0, var7);
/*    */       }
/*    */     } 
/*    */     
/* 65 */     return 0;
/*    */   }
/*    */   
/*    */   private int a(WorldServer var0, BlockPosition var1) {
/* 69 */     int var2 = 48;
/* 70 */     if (var0.y().a(VillagePlaceType.r.c(), var1, 48, VillagePlace.Occupancy.IS_OCCUPIED) > 4L) {
/* 71 */       List<EntityCat> var3 = var0.a(EntityCat.class, (new AxisAlignedBB(var1)).grow(48.0D, 8.0D, 48.0D));
/* 72 */       if (var3.size() < 5) {
/* 73 */         return a(var1, var0);
/*    */       }
/*    */     } 
/* 76 */     return 0;
/*    */   }
/*    */   
/*    */   private int b(WorldServer var0, BlockPosition var1) {
/* 80 */     int var2 = 16;
/* 81 */     List<EntityCat> var3 = var0.a(EntityCat.class, (new AxisAlignedBB(var1)).grow(16.0D, 8.0D, 16.0D));
/* 82 */     if (var3.size() < 1) {
/* 83 */       return a(var1, var0);
/*    */     }
/*    */     
/* 86 */     return 0;
/*    */   }
/*    */   
/*    */   private int a(BlockPosition var0, WorldServer var1) {
/* 90 */     EntityCat var2 = EntityTypes.CAT.a(var1);
/* 91 */     if (var2 == null) {
/* 92 */       return 0;
/*    */     }
/*    */     
/* 95 */     var2.prepare(var1, var1.getDamageScaler(var0), EnumMobSpawn.NATURAL, (GroupDataEntity)null, (NBTTagCompound)null);
/* 96 */     var2.setPositionRotation(var0, 0.0F, 0.0F);
/* 97 */     var1.addAllEntities(var2);
/* 98 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerCat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */