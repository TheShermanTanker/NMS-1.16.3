/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.EnderDragonShootFireballEvent;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.DragonFireball;
/*     */ import org.bukkit.entity.EnderDragon;
/*     */ 
/*     */ public class DragonControllerStrafe extends AbstractDragonController {
/*   9 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private int c;
/*     */   private PathEntity d;
/*     */   private Vec3D e;
/*     */   private EntityLiving f;
/*     */   private boolean g;
/*     */   
/*     */   public DragonControllerStrafe(EntityEnderDragon entityenderdragon) {
/*  17 */     super(entityenderdragon);
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  22 */     if (this.f == null) {
/*  23 */       LOGGER.warn("Skipping player strafe phase because no player was found");
/*  24 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  30 */       if (this.d != null && this.d.c()) {
/*  31 */         double d6 = this.f.locX();
/*  32 */         double d7 = this.f.locZ();
/*  33 */         double d3 = d6 - this.a.locX();
/*  34 */         double d4 = d7 - this.a.locZ();
/*     */         
/*  36 */         double d2 = MathHelper.sqrt(d3 * d3 + d4 * d4);
/*  37 */         double d5 = Math.min(0.4000000059604645D + d2 / 80.0D - 1.0D, 10.0D);
/*     */         
/*  39 */         this.e = new Vec3D(d6, this.f.locY() + d5, d7);
/*     */       } 
/*     */       
/*  42 */       double d0 = (this.e == null) ? 0.0D : this.e.c(this.a.locX(), this.a.locY(), this.a.locZ());
/*  43 */       if (d0 < 100.0D || d0 > 22500.0D) {
/*  44 */         j();
/*     */       }
/*     */       
/*  47 */       double d1 = 64.0D;
/*  48 */       if (this.f.h(this.a) < 4096.0D) {
/*  49 */         if (this.a.hasLineOfSight(this.f)) {
/*  50 */           this.c++;
/*  51 */           Vec3D vec3d = (new Vec3D(this.f.locX() - this.a.locX(), 0.0D, this.f.locZ() - this.a.locZ())).d();
/*  52 */           Vec3D vec3d1 = (new Vec3D(MathHelper.sin(this.a.yaw * 0.017453292F), 0.0D, -MathHelper.cos(this.a.yaw * 0.017453292F))).d();
/*  53 */           float f = (float)vec3d1.b(vec3d);
/*  54 */           float f1 = (float)(Math.acos(f) * 57.2957763671875D);
/*     */           
/*  56 */           f1 += 0.5F;
/*  57 */           if (this.c >= 5 && f1 >= 0.0F && f1 < 10.0F) {
/*  58 */             double d2 = 1.0D;
/*  59 */             Vec3D vec3d2 = this.a.f(1.0F);
/*  60 */             double d6 = this.a.bo.locX() - vec3d2.x * 1.0D;
/*  61 */             double d7 = this.a.bo.e(0.5D) + 0.5D;
/*  62 */             double d8 = this.a.bo.locZ() - vec3d2.z * 1.0D;
/*  63 */             double d9 = this.f.locX() - d6;
/*  64 */             double d10 = this.f.e(0.5D) - d7;
/*  65 */             double d11 = this.f.locZ() - d8;
/*     */             
/*  67 */             if (!this.a.isSilent()) {
/*  68 */               this.a.world.a((EntityHuman)null, 1017, this.a.getChunkCoordinates(), 0);
/*     */             }
/*     */             
/*  71 */             EntityDragonFireball entitydragonfireball = new EntityDragonFireball(this.a.world, this.a, d9, d10, d11);
/*     */             
/*  73 */             entitydragonfireball.setPositionRotation(d6, d7, d8, 0.0F, 0.0F);
/*  74 */             if ((new EnderDragonShootFireballEvent((EnderDragon)this.a.getBukkitEntity(), (DragonFireball)entitydragonfireball.getBukkitEntity())).callEvent())
/*  75 */             { this.a.world.addEntity(entitydragonfireball); }
/*  76 */             else { entitydragonfireball.die(); }
/*  77 */              this.c = 0;
/*  78 */             if (this.d != null) {
/*  79 */               while (!this.d.c()) {
/*  80 */                 this.d.a();
/*     */               }
/*     */             }
/*     */             
/*  84 */             this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/*     */           } 
/*  86 */         } else if (this.c > 0) {
/*  87 */           this.c--;
/*     */         } 
/*  89 */       } else if (this.c > 0) {
/*  90 */         this.c--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void j() {
/*  97 */     if (this.d == null || this.d.c()) {
/*  98 */       int i = this.a.eI();
/*  99 */       int j = i;
/*     */       
/* 101 */       if (this.a.getRandom().nextInt(8) == 0) {
/* 102 */         this.g = !this.g;
/* 103 */         j = i + 6;
/*     */       } 
/*     */       
/* 106 */       if (this.g) {
/* 107 */         j++;
/*     */       } else {
/* 109 */         j--;
/*     */       } 
/*     */       
/* 112 */       if (this.a.getEnderDragonBattle() != null && this.a.getEnderDragonBattle().c() > 0) {
/* 113 */         j %= 12;
/* 114 */         if (j < 0) {
/* 115 */           j += 12;
/*     */         }
/*     */       } else {
/* 118 */         j -= 12;
/* 119 */         j &= 0x7;
/* 120 */         j += 12;
/*     */       } 
/*     */       
/* 123 */       this.d = this.a.a(i, j, (PathPoint)null);
/* 124 */       if (this.d != null) {
/* 125 */         this.d.a();
/*     */       }
/*     */     } 
/*     */     
/* 129 */     k();
/*     */   }
/*     */   
/*     */   private void k() {
/* 133 */     if (this.d != null && !this.d.c()) {
/* 134 */       double d2; BlockPosition blockposition = this.d.g();
/*     */       
/* 136 */       this.d.a();
/* 137 */       double d0 = blockposition.getX();
/* 138 */       double d1 = blockposition.getZ();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 143 */         d2 = (blockposition.getY() + this.a.getRandom().nextFloat() * 20.0F);
/* 144 */       } while (d2 < blockposition.getY());
/*     */       
/* 146 */       this.e = new Vec3D(d0, d2, d1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void d() {
/* 153 */     this.c = 0;
/* 154 */     this.e = null;
/* 155 */     this.d = null;
/* 156 */     this.f = null;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving) {
/* 160 */     this.f = entityliving;
/* 161 */     int i = this.a.eI();
/* 162 */     int j = this.a.p(this.f.locX(), this.f.locY(), this.f.locZ());
/* 163 */     int k = MathHelper.floor(this.f.locX());
/* 164 */     int l = MathHelper.floor(this.f.locZ());
/* 165 */     double d0 = k - this.a.locX();
/* 166 */     double d1 = l - this.a.locZ();
/* 167 */     double d2 = MathHelper.sqrt(d0 * d0 + d1 * d1);
/* 168 */     double d3 = Math.min(0.4000000059604645D + d2 / 80.0D - 1.0D, 10.0D);
/* 169 */     int i1 = MathHelper.floor(this.f.locY() + d3);
/* 170 */     PathPoint pathpoint = new PathPoint(k, i1, l);
/*     */     
/* 172 */     this.d = this.a.a(i, j, pathpoint);
/* 173 */     if (this.d != null) {
/* 174 */       this.d.a();
/* 175 */       k();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vec3D g() {
/* 183 */     return this.e;
/*     */   }
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerStrafe> getControllerPhase() {
/* 188 */     return DragonControllerPhase.STRAFE_PLAYER;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerStrafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */