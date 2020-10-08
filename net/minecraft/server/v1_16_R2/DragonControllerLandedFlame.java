/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.EnderDragonFlameEvent;
/*     */ import org.bukkit.entity.AreaEffectCloud;
/*     */ import org.bukkit.entity.EnderDragon;
/*     */ 
/*     */ public class DragonControllerLandedFlame extends AbstractDragonControllerLanded {
/*     */   private int b;
/*     */   
/*     */   public DragonControllerLandedFlame(EntityEnderDragon entityenderdragon) {
/*  10 */     super(entityenderdragon);
/*     */   }
/*     */   private int c; private EntityAreaEffectCloud d;
/*     */   
/*     */   public void b() {
/*  15 */     this.b++;
/*  16 */     if (this.b % 2 == 0 && this.b < 10) {
/*  17 */       Vec3D vec3d = this.a.x(1.0F).d();
/*     */       
/*  19 */       vec3d.b(-0.7853982F);
/*  20 */       double d0 = this.a.bo.locX();
/*  21 */       double d1 = this.a.bo.e(0.5D);
/*  22 */       double d2 = this.a.bo.locZ();
/*     */       
/*  24 */       for (int i = 0; i < 8; i++) {
/*  25 */         double d3 = d0 + this.a.getRandom().nextGaussian() / 2.0D;
/*  26 */         double d4 = d1 + this.a.getRandom().nextGaussian() / 2.0D;
/*  27 */         double d5 = d2 + this.a.getRandom().nextGaussian() / 2.0D;
/*     */         
/*  29 */         for (int j = 0; j < 6; j++) {
/*  30 */           this.a.world.addParticle(Particles.DRAGON_BREATH, d3, d4, d5, -vec3d.x * 0.07999999821186066D * j, -vec3d.y * 0.6000000238418579D, -vec3d.z * 0.07999999821186066D * j);
/*     */         }
/*     */         
/*  33 */         vec3d.b(0.19634955F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/*  41 */     this.b++;
/*  42 */     if (this.b >= 200) {
/*  43 */       if (this.c >= 4) {
/*  44 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.TAKEOFF);
/*     */       } else {
/*  46 */         this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_SCANNING);
/*     */       } 
/*  48 */     } else if (this.b == 10) {
/*  49 */       Vec3D vec3d = (new Vec3D(this.a.bo.locX() - this.a.locX(), 0.0D, this.a.bo.locZ() - this.a.locZ())).d();
/*  50 */       float f = 5.0F;
/*  51 */       double d0 = this.a.bo.locX() + vec3d.x * 5.0D / 2.0D;
/*  52 */       double d1 = this.a.bo.locZ() + vec3d.z * 5.0D / 2.0D;
/*  53 */       double d2 = this.a.bo.e(0.5D);
/*  54 */       double d3 = d2;
/*  55 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(d0, d2, d1);
/*     */       
/*  57 */       while (this.a.world.isEmpty(blockposition_mutableblockposition) && d2 > 0.0D) {
/*  58 */         d3--;
/*  59 */         if (d3 < 0.0D) {
/*  60 */           d3 = d2;
/*     */           
/*     */           break;
/*     */         } 
/*  64 */         blockposition_mutableblockposition.c(d0, d3, d1);
/*     */       } 
/*     */       
/*  67 */       d3 = (MathHelper.floor(d3) + 1);
/*  68 */       this.d = new EntityAreaEffectCloud(this.a.world, d0, d3, d1);
/*  69 */       this.d.setSource(this.a);
/*  70 */       this.d.setRadius(5.0F);
/*  71 */       this.d.setDuration(200);
/*  72 */       this.d.setParticle(Particles.DRAGON_BREATH);
/*  73 */       this.d.addEffect(new MobEffect(MobEffects.HARM));
/*  74 */       if ((new EnderDragonFlameEvent((EnderDragon)this.a.getBukkitEntity(), (AreaEffectCloud)this.d.getBukkitEntity())).callEvent()) {
/*  75 */         this.a.world.addEntity(this.d);
/*     */       } else {
/*  77 */         removeAreaEffect();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void d() {
/*  85 */     this.b = 0;
/*  86 */     this.c++;
/*     */   }
/*     */   public final void removeAreaEffect() {
/*  89 */     e();
/*     */   } public void e() {
/*  91 */     if (this.d != null) {
/*  92 */       this.d.die();
/*  93 */       this.d = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DragonControllerPhase<DragonControllerLandedFlame> getControllerPhase() {
/* 100 */     return DragonControllerPhase.SITTING_FLAMING;
/*     */   }
/*     */   
/*     */   public void j() {
/* 104 */     this.c = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerLandedFlame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */