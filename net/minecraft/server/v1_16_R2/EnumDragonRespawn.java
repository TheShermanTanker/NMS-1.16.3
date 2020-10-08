/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
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
/*    */ public enum EnumDragonRespawn
/*    */ {
/* 18 */   START
/*    */   {
/*    */     public void a(WorldServer var0, EnderDragonBattle var1, List<EntityEnderCrystal> var2, int var3, BlockPosition var4) {
/* 21 */       BlockPosition var5 = new BlockPosition(0, 128, 0);
/* 22 */       for (EntityEnderCrystal var7 : var2) {
/* 23 */         var7.setBeamTarget(var5);
/*    */       }
/* 25 */       var1.setRespawnPhase(PREPARING_TO_SUMMON_PILLARS);
/*    */     }
/*    */   },
/* 28 */   PREPARING_TO_SUMMON_PILLARS
/*    */   {
/*    */     public void a(WorldServer var0, EnderDragonBattle var1, List<EntityEnderCrystal> var2, int var3, BlockPosition var4) {
/* 31 */       if (var3 < 100) {
/* 32 */         if (var3 == 0 || var3 == 50 || var3 == 51 || var3 == 52 || var3 >= 95) {
/* 33 */           var0.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/*    */         }
/*    */       } else {
/* 36 */         var1.setRespawnPhase(SUMMONING_PILLARS);
/*    */       } 
/*    */     }
/*    */   },
/* 40 */   SUMMONING_PILLARS
/*    */   {
/*    */     public void a(WorldServer var0, EnderDragonBattle var1, List<EntityEnderCrystal> var2, int var3, BlockPosition var4) {
/* 43 */       int var5 = 40;
/* 44 */       boolean var6 = (var3 % 40 == 0);
/* 45 */       boolean var7 = (var3 % 40 == 39);
/* 46 */       if (var6 || var7) {
/* 47 */         List<WorldGenEnder.Spike> var8 = WorldGenEnder.a(var0);
/* 48 */         int var9 = var3 / 40;
/* 49 */         if (var9 < var8.size()) {
/* 50 */           WorldGenEnder.Spike var10 = var8.get(var9);
/*    */           
/* 52 */           if (var6) {
/* 53 */             for (EntityEnderCrystal var12 : var2) {
/* 54 */               var12.setBeamTarget(new BlockPosition(var10.a(), var10.d() + 1, var10.b()));
/*    */             }
/*    */           } else {
/* 57 */             int var11 = 10;
/* 58 */             for (BlockPosition var13 : BlockPosition.a(new BlockPosition(var10
/* 59 */                   .a() - 10, var10.d() - 10, var10.b() - 10), new BlockPosition(var10
/* 60 */                   .a() + 10, var10.d() + 10, var10.b() + 10)))
/*    */             {
/* 62 */               var0.a(var13, false);
/*    */             }
/* 64 */             var0.explode((Entity)null, (var10.a() + 0.5F), var10.d(), (var10.b() + 0.5F), 5.0F, Explosion.Effect.DESTROY);
/*    */             
/* 66 */             WorldGenFeatureEndSpikeConfiguration var12 = new WorldGenFeatureEndSpikeConfiguration(true, (List<WorldGenEnder.Spike>)ImmutableList.of(var10), new BlockPosition(0, 128, 0));
/* 67 */             WorldGenerator.END_SPIKE.b(var12).a(var0, var0.getChunkProvider().getChunkGenerator(), new Random(), new BlockPosition(var10.a(), 45, var10.b()));
/*    */           } 
/* 69 */         } else if (var6) {
/* 70 */           var1.setRespawnPhase(SUMMONING_DRAGON);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   },
/* 75 */   SUMMONING_DRAGON
/*    */   {
/*    */     public void a(WorldServer var0, EnderDragonBattle var1, List<EntityEnderCrystal> var2, int var3, BlockPosition var4) {
/* 78 */       if (var3 >= 100) {
/* 79 */         var1.setRespawnPhase(END);
/* 80 */         var1.resetCrystals();
/* 81 */         for (EntityEnderCrystal var6 : var2) {
/* 82 */           var6.setBeamTarget((BlockPosition)null);
/* 83 */           var0.explode(var6, var6.locX(), var6.locY(), var6.locZ(), 6.0F, Explosion.Effect.NONE);
/* 84 */           var6.die();
/*    */         } 
/* 86 */       } else if (var3 >= 80) {
/* 87 */         var0.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/* 88 */       } else if (var3 == 0) {
/* 89 */         for (EntityEnderCrystal var6 : var2) {
/* 90 */           var6.setBeamTarget(new BlockPosition(0, 128, 0));
/*    */         }
/* 92 */       } else if (var3 < 5) {
/* 93 */         var0.triggerEffect(3001, new BlockPosition(0, 128, 0), 0);
/*    */       } 
/*    */     }
/*    */   },
/* 97 */   END {
/*    */     public void a(WorldServer var0, EnderDragonBattle var1, List<EntityEnderCrystal> var2, int var3, BlockPosition var4) {}
/*    */   };
/*    */   
/*    */   public abstract void a(WorldServer paramWorldServer, EnderDragonBattle paramEnderDragonBattle, List<EntityEnderCrystal> paramList, int paramInt, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumDragonRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */