/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DragonControllerHold
/*     */   extends AbstractDragonController
/*     */ {
/*  19 */   private static final PathfinderTargetCondition b = (new PathfinderTargetCondition()).a(64.0D);
/*     */   
/*     */   private PathEntity c;
/*     */   private Vec3D d;
/*     */   private boolean e;
/*     */   
/*     */   public DragonControllerHold(EntityEnderDragon var0) {
/*  26 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerHold> getControllerPhase() {
/*  31 */     return DragonControllerPhase.HOLDING_PATTERN;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  36 */     double var0 = (this.d == null) ? 0.0D : this.d.c(this.a.locX(), this.a.locY(), this.a.locZ());
/*  37 */     if (var0 < 100.0D || var0 > 22500.0D || this.a.positionChanged || this.a.v) {
/*  38 */       j();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  44 */     this.c = null;
/*  45 */     this.d = null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Vec3D g() {
/*  51 */     return this.d;
/*     */   }
/*     */   
/*     */   private void j() {
/*  55 */     if (this.c != null && this.c.c()) {
/*  56 */       BlockPosition var0 = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPosition(WorldGenEndTrophy.a));
/*     */ 
/*     */ 
/*     */       
/*  60 */       int var1 = (this.a.getEnderDragonBattle() == null) ? 0 : this.a.getEnderDragonBattle().c();
/*     */       
/*  62 */       if (this.a.getRandom().nextInt(var1 + 3) == 0) {
/*  63 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.LANDING_APPROACH);
/*     */         return;
/*     */       } 
/*  66 */       double var2 = 64.0D;
/*  67 */       EntityHuman var4 = this.a.world.a(b, var0.getX(), var0.getY(), var0.getZ());
/*  68 */       if (var4 != null) {
/*  69 */         var2 = var0.a(var4.getPositionVector(), true) / 512.0D;
/*     */       }
/*  71 */       if (var4 != null && !var4.abilities.isInvulnerable && (this.a.getRandom().nextInt(MathHelper.a((int)var2) + 2) == 0 || this.a.getRandom().nextInt(var1 + 2) == 0)) {
/*     */         
/*  73 */         a(var4);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     if (this.c == null || this.c.c()) {
/*  80 */       int var0 = this.a.eI();
/*  81 */       int var1 = var0;
/*     */       
/*  83 */       if (this.a.getRandom().nextInt(8) == 0) {
/*  84 */         this.e = !this.e;
/*  85 */         var1 += 6;
/*     */       } 
/*     */       
/*  88 */       if (this.e) {
/*  89 */         var1++;
/*     */       } else {
/*  91 */         var1--;
/*     */       } 
/*     */       
/*  94 */       if (this.a.getEnderDragonBattle() == null || this.a.getEnderDragonBattle().c() < 0) {
/*     */         
/*  96 */         var1 -= 12;
/*  97 */         var1 &= 0x7;
/*  98 */         var1 += 12;
/*     */       } else {
/*     */         
/* 101 */         var1 %= 12;
/* 102 */         if (var1 < 0) {
/* 103 */           var1 += 12;
/*     */         }
/*     */       } 
/*     */       
/* 107 */       this.c = this.a.a(var0, var1, (PathPoint)null);
/* 108 */       if (this.c != null) {
/* 109 */         this.c.a();
/*     */       }
/*     */     } 
/*     */     
/* 113 */     k();
/*     */   }
/*     */   
/*     */   private void a(EntityHuman var0) {
/* 117 */     this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.STRAFE_PLAYER);
/* 118 */     ((DragonControllerStrafe)this.a.getDragonControllerManager().<DragonControllerStrafe>b(DragonControllerPhase.STRAFE_PLAYER)).a(var0);
/*     */   }
/*     */   
/*     */   private void k() {
/* 122 */     if (this.c != null && !this.c.c()) {
/* 123 */       double var5; BaseBlockPosition var0 = this.c.g();
/*     */       
/* 125 */       this.c.a();
/* 126 */       double var1 = var0.getX();
/* 127 */       double var3 = var0.getZ();
/*     */ 
/*     */       
/*     */       do {
/* 131 */         var5 = (var0.getY() + this.a.getRandom().nextFloat() * 20.0F);
/* 132 */       } while (var5 < var0.getY());
/*     */       
/* 134 */       this.d = new Vec3D(var1, var5, var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityEnderCrystal var0, BlockPosition var1, DamageSource var2, @Nullable EntityHuman var3) {
/* 140 */     if (var3 != null && !var3.abilities.isInvulnerable)
/* 141 */       a(var3); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerHold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */