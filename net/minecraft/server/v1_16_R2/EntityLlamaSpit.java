/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityLlamaSpit
/*     */   extends IProjectile
/*     */ {
/*     */   public EntityLlamaSpit(EntityTypes<? extends EntityLlamaSpit> var0, World var1) {
/*  21 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */   
/*     */   public EntityLlamaSpit(World var0, EntityLlama var1) {
/*  25 */     this(EntityTypes.LLAMA_SPIT, var0);
/*  26 */     setShooter(var1);
/*  27 */     setPosition(var1.locX() - (var1.getWidth() + 1.0F) * 0.5D * MathHelper.sin(var1.aA * 0.017453292F), var1.getHeadY() - 0.10000000149011612D, var1.locZ() + (var1.getWidth() + 1.0F) * 0.5D * MathHelper.cos(var1.aA * 0.017453292F));
/*     */   }
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
/*     */ 
/*     */   
/*     */   public void tick() {
/*  44 */     super.tick();
/*     */     
/*  46 */     Vec3D var0 = getMot();
/*  47 */     MovingObjectPosition var1 = ProjectileHelper.a(this, this::a);
/*     */     
/*  49 */     if (var1 != null) {
/*  50 */       a(var1);
/*     */     }
/*     */     
/*  53 */     double var2 = locX() + var0.x;
/*  54 */     double var4 = locY() + var0.y;
/*  55 */     double var6 = locZ() + var0.z;
/*     */     
/*  57 */     x();
/*     */     
/*  59 */     float var8 = 0.99F;
/*  60 */     float var9 = 0.06F;
/*     */     
/*  62 */     if (this.world.a(getBoundingBox()).noneMatch(BlockBase.BlockData::isAir)) {
/*  63 */       die();
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     if (aG()) {
/*  68 */       die();
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     setMot(var0.a(0.9900000095367432D));
/*  73 */     if (!isNoGravity()) {
/*  74 */       setMot(getMot().add(0.0D, -0.05999999865889549D, 0.0D));
/*     */     }
/*     */     
/*  77 */     setPosition(var2, var4, var6);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity var0) {
/*  82 */     super.a(var0);
/*  83 */     Entity var1 = getShooter();
/*  84 */     if (var1 instanceof EntityLiving) {
/*  85 */       var0.getEntity().damageEntity(DamageSource.a(this, (EntityLiving)var1).c(), 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock var0) {
/*  91 */     super.a(var0);
/*     */     
/*  93 */     if (!this.world.isClientSide) {
/*  94 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 104 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLlamaSpit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */