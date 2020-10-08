/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.exception.ServerInternalException;
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class VillageSiege
/*     */   implements MobSpawner {
/*  12 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean b;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   private State c = State.SIEGE_DONE;
/*     */   private int d;
/*     */   private int e;
/*     */   
/*     */   public int a(WorldServer worldserver, boolean flag, boolean flag1) {
/*  27 */     if (!worldserver.isDay() && flag) {
/*  28 */       float f = worldserver.f(0.0F);
/*     */       
/*  30 */       if (f == 0.5D) {
/*  31 */         this.c = (worldserver.random.nextInt(10) == 0) ? State.SIEGE_TONIGHT : State.SIEGE_DONE;
/*     */       }
/*     */       
/*  34 */       if (this.c == State.SIEGE_DONE) {
/*  35 */         return 0;
/*     */       }
/*  37 */       if (!this.b) {
/*  38 */         if (!a(worldserver)) {
/*  39 */           return 0;
/*     */         }
/*     */         
/*  42 */         this.b = true;
/*     */       } 
/*     */       
/*  45 */       if (this.e > 0) {
/*  46 */         this.e--;
/*  47 */         return 0;
/*     */       } 
/*  49 */       this.e = 2;
/*  50 */       if (this.d > 0) {
/*  51 */         b(worldserver);
/*  52 */         this.d--;
/*     */       } else {
/*  54 */         this.c = State.SIEGE_DONE;
/*     */       } 
/*     */       
/*  57 */       return 1;
/*     */     } 
/*     */ 
/*     */     
/*  61 */     this.c = State.SIEGE_DONE;
/*  62 */     this.b = false;
/*  63 */     return 0;
/*     */   }
/*     */   private int f; private int g; private int h;
/*     */   
/*     */   private boolean a(WorldServer worldserver) {
/*  68 */     Iterator<EntityPlayer> iterator = worldserver.getPlayers().iterator();
/*     */     
/*  70 */     while (iterator.hasNext()) {
/*  71 */       EntityHuman entityhuman = iterator.next();
/*     */       
/*  73 */       if (!entityhuman.isSpectator()) {
/*  74 */         BlockPosition blockposition = entityhuman.getChunkCoordinates();
/*     */         
/*  76 */         if (worldserver.a_(blockposition) && worldserver.getBiome(blockposition).t() != BiomeBase.Geography.MUSHROOM) {
/*  77 */           for (int i = 0; i < 10; i++) {
/*  78 */             float f = worldserver.random.nextFloat() * 6.2831855F;
/*     */             
/*  80 */             this.f = blockposition.getX() + MathHelper.d(MathHelper.cos(f) * 32.0F);
/*  81 */             this.g = blockposition.getY();
/*  82 */             this.h = blockposition.getZ() + MathHelper.d(MathHelper.sin(f) * 32.0F);
/*  83 */             if (a(worldserver, new BlockPosition(this.f, this.g, this.h)) != null) {
/*  84 */               this.e = 0;
/*  85 */               this.d = 20;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*  90 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     return false;
/*     */   }
/*     */   
/*     */   private void b(WorldServer worldserver) {
/*  99 */     Vec3D vec3d = a(worldserver, new BlockPosition(this.f, this.g, this.h));
/*     */     
/* 101 */     if (vec3d != null) {
/*     */       EntityZombie entityzombie;
/*     */       
/*     */       try {
/* 105 */         entityzombie = new EntityZombie(worldserver);
/* 106 */         entityzombie.prepare(worldserver, worldserver.getDamageScaler(entityzombie.getChunkCoordinates()), EnumMobSpawn.EVENT, (GroupDataEntity)null, (NBTTagCompound)null);
/* 107 */       } catch (Exception exception) {
/* 108 */         LOGGER.warn("Failed to create zombie for village siege at {}", vec3d, exception);
/* 109 */         ServerInternalException.reportInternalException(exception);
/*     */         
/*     */         return;
/*     */       } 
/* 113 */       entityzombie.setPositionRotation(vec3d.x, vec3d.y, vec3d.z, worldserver.random.nextFloat() * 360.0F, 0.0F);
/* 114 */       worldserver.addAllEntities(entityzombie, CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private Vec3D a(WorldServer worldserver, BlockPosition blockposition) {
/* 120 */     for (int i = 0; i < 10; i++) {
/* 121 */       int j = blockposition.getX() + worldserver.random.nextInt(16) - 8;
/* 122 */       int k = blockposition.getZ() + worldserver.random.nextInt(16) - 8;
/* 123 */       int l = worldserver.a(HeightMap.Type.WORLD_SURFACE, j, k);
/* 124 */       BlockPosition blockposition1 = new BlockPosition(j, l, k);
/*     */       
/* 126 */       if (worldserver.a_(blockposition1) && EntityMonster.b((EntityTypes)EntityTypes.ZOMBIE, worldserver, EnumMobSpawn.EVENT, blockposition1, worldserver.random)) {
/* 127 */         return Vec3D.c(blockposition1);
/*     */       }
/*     */     } 
/*     */     
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   enum State
/*     */   {
/* 136 */     SIEGE_CAN_ACTIVATE, SIEGE_TONIGHT, SIEGE_DONE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillageSiege.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */