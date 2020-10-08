/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class MobSpawnerTrader implements MobSpawner {
/*  10 */   private final Random a = new Random();
/*     */   private final IWorldDataServer b;
/*     */   private int c;
/*     */   private int d;
/*     */   private int e;
/*     */   
/*     */   public MobSpawnerTrader(IWorldDataServer iworlddataserver) {
/*  17 */     this.b = iworlddataserver;
/*  18 */     this.c = 1200;
/*  19 */     this.d = iworlddataserver.v();
/*  20 */     this.e = iworlddataserver.w();
/*  21 */     if (this.d == 0 && this.e == 0) {
/*  22 */       this.d = 24000;
/*  23 */       iworlddataserver.g(this.d);
/*  24 */       this.e = 25;
/*  25 */       iworlddataserver.h(this.e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(WorldServer worldserver, boolean flag, boolean flag1) {
/*  32 */     if (!worldserver.getGameRules().getBoolean(GameRules.DO_TRADER_SPAWNING))
/*  33 */       return 0; 
/*  34 */     if (--this.c > 0) {
/*  35 */       return 0;
/*     */     }
/*  37 */     this.c = 1200;
/*  38 */     this.d -= 1200;
/*  39 */     this.b.g(this.d);
/*  40 */     if (this.d > 0) {
/*  41 */       return 0;
/*     */     }
/*  43 */     this.d = 24000;
/*     */     
/*  45 */     int i = this.e;
/*     */     
/*  47 */     this.e = MathHelper.clamp(this.e + 25, 25, 75);
/*  48 */     this.b.h(this.e);
/*  49 */     if (this.a.nextInt(100) > i)
/*  50 */       return 0; 
/*  51 */     if (a(worldserver)) {
/*  52 */       this.e = 25;
/*  53 */       return 1;
/*     */     } 
/*  55 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(WorldServer worldserver) {
/*  63 */     EntityPlayer entityplayer = worldserver.q_();
/*     */     
/*  65 */     if (entityplayer == null)
/*  66 */       return true; 
/*  67 */     if (this.a.nextInt(10) != 0) {
/*  68 */       return false;
/*     */     }
/*  70 */     BlockPosition blockposition = entityplayer.getChunkCoordinates();
/*  71 */     boolean flag = true;
/*  72 */     VillagePlace villageplace = worldserver.y();
/*  73 */     Optional<BlockPosition> optional = villageplace.c(VillagePlaceType.s.c(), blockposition1 -> true, blockposition, 48, VillagePlace.Occupancy.ANY);
/*     */ 
/*     */     
/*  76 */     BlockPosition blockposition1 = optional.orElse(blockposition);
/*  77 */     BlockPosition blockposition2 = a(worldserver, blockposition1, 48);
/*     */     
/*  79 */     if (blockposition2 != null && a(worldserver, blockposition2)) {
/*  80 */       if (worldserver.i(blockposition2).equals(Optional.of(Biomes.THE_VOID))) {
/*  81 */         return false;
/*     */       }
/*     */       
/*  84 */       EntityVillagerTrader entityvillagertrader = EntityTypes.WANDERING_TRADER.spawnCreature(worldserver, (NBTTagCompound)null, (IChatBaseComponent)null, (EntityHuman)null, blockposition2, EnumMobSpawn.EVENT, false, false, CreatureSpawnEvent.SpawnReason.NATURAL);
/*     */       
/*  86 */       if (entityvillagertrader != null) {
/*  87 */         for (int i = 0; i < 2; i++) {
/*  88 */           a(worldserver, entityvillagertrader, 4);
/*     */         }
/*     */         
/*  91 */         this.b.a(entityvillagertrader.getUniqueID());
/*  92 */         entityvillagertrader.u(48000);
/*  93 */         entityvillagertrader.g(blockposition1);
/*  94 */         entityvillagertrader.a(blockposition1, 16);
/*  95 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(WorldServer worldserver, EntityVillagerTrader entityvillagertrader, int i) {
/* 104 */     BlockPosition blockposition = a(worldserver, entityvillagertrader.getChunkCoordinates(), i);
/*     */     
/* 106 */     if (blockposition != null) {
/* 107 */       EntityLlamaTrader entityllamatrader = EntityTypes.TRADER_LLAMA.spawnCreature(worldserver, (NBTTagCompound)null, (IChatBaseComponent)null, (EntityHuman)null, blockposition, EnumMobSpawn.EVENT, false, false, CreatureSpawnEvent.SpawnReason.NATURAL);
/*     */       
/* 109 */       if (entityllamatrader != null) {
/* 110 */         entityllamatrader.setLeashHolder(entityvillagertrader, true);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(IWorldReader iworldreader, BlockPosition blockposition, int i) {
/* 117 */     BlockPosition blockposition1 = null;
/*     */     
/* 119 */     for (int j = 0; j < 10; j++) {
/* 120 */       int k = blockposition.getX() + this.a.nextInt(i * 2) - i;
/* 121 */       int l = blockposition.getZ() + this.a.nextInt(i * 2) - i;
/* 122 */       int i1 = iworldreader.a(HeightMap.Type.WORLD_SURFACE, k, l);
/* 123 */       BlockPosition blockposition2 = new BlockPosition(k, i1, l);
/*     */       
/* 125 */       if (SpawnerCreature.a(EntityPositionTypes.Surface.ON_GROUND, iworldreader, blockposition2, EntityTypes.WANDERING_TRADER)) {
/* 126 */         blockposition1 = blockposition2;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 131 */     return blockposition1;
/*     */   }
/*     */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*     */     BlockPosition blockposition1;
/* 135 */     Iterator<BlockPosition> iterator = BlockPosition.a(blockposition, blockposition.b(1, 2, 1)).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 140 */       if (!iterator.hasNext()) {
/* 141 */         return true;
/*     */       }
/*     */       
/* 144 */       blockposition1 = iterator.next();
/* 145 */     } while (iblockaccess.getType(blockposition1).getCollisionShape(iblockaccess, blockposition1).isEmpty());
/*     */     
/* 147 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobSpawnerTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */