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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityMinecartTNT
/*     */   extends EntityMinecartAbstract
/*     */ {
/*  25 */   private int b = -1;
/*     */   
/*     */   public EntityMinecartTNT(EntityTypes<? extends EntityMinecartTNT> var0, World var1) {
/*  28 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   public EntityMinecartTNT(World var0, double var1, double var3, double var5) {
/*  32 */     super(EntityTypes.TNT_MINECART, var0, var1, var3, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/*  37 */     return EntityMinecartAbstract.EnumMinecartType.TNT;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData q() {
/*  42 */     return Blocks.TNT.getBlockData();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  47 */     super.tick();
/*     */     
/*  49 */     if (this.b > 0) {
/*  50 */       this.b--;
/*  51 */       this.world.addParticle(Particles.SMOKE, locX(), locY() + 0.5D, locZ(), 0.0D, 0.0D, 0.0D);
/*  52 */     } else if (this.b == 0) {
/*  53 */       h(c(getMot()));
/*     */     } 
/*     */     
/*  56 */     if (this.positionChanged) {
/*  57 */       double var0 = c(getMot());
/*     */       
/*  59 */       if (var0 >= 0.009999999776482582D) {
/*  60 */         h(var0);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource var0, float var1) {
/*  67 */     Entity var2 = var0.j();
/*  68 */     if (var2 instanceof EntityArrow) {
/*  69 */       EntityArrow var3 = (EntityArrow)var2;
/*  70 */       if (var3.isBurning()) {
/*  71 */         h(var3.getMot().g());
/*     */       }
/*     */     } 
/*  74 */     return super.damageEntity(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DamageSource var0) {
/*  79 */     double var1 = c(getMot());
/*     */     
/*  81 */     if (var0.isFire() || var0.isExplosion() || var1 >= 0.009999999776482582D) {
/*  82 */       if (this.b < 0) {
/*  83 */         u();
/*  84 */         this.b = this.random.nextInt(20) + this.random.nextInt(20);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  89 */     super.a(var0);
/*     */     
/*  91 */     if (!var0.isExplosion() && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/*  92 */       a(Blocks.TNT);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void h(double var0) {
/*  97 */     if (!this.world.isClientSide) {
/*  98 */       double var2 = Math.sqrt(var0);
/*  99 */       if (var2 > 5.0D) {
/* 100 */         var2 = 5.0D;
/*     */       }
/* 102 */       this.world.explode(this, locX(), locY(), locZ(), (float)(4.0D + this.random.nextDouble() * 1.5D * var2), Explosion.Effect.BREAK);
/* 103 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float var0, float var1) {
/* 109 */     if (var0 >= 3.0F) {
/* 110 */       float var2 = var0 / 10.0F;
/* 111 */       h((var2 * var2));
/*     */     } 
/*     */     
/* 114 */     return super.b(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int var0, int var1, int var2, boolean var3) {
/* 119 */     if (var3 && this.b < 0) {
/* 120 */       u();
/*     */     }
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
/*     */   public void u() {
/* 134 */     this.b = 80;
/*     */     
/* 136 */     if (!this.world.isClientSide) {
/* 137 */       this.world.broadcastEntityEffect(this, (byte)10);
/* 138 */       if (!isSilent()) {
/* 139 */         this.world.playSound(null, locX(), locY(), locZ(), SoundEffects.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean x() {
/* 149 */     return (this.b > -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, Fluid var4, float var5) {
/* 154 */     if (x() && (var3.a(TagsBlock.RAILS) || var1.getType(var2.up()).a(TagsBlock.RAILS))) {
/* 155 */       return 0.0F;
/*     */     }
/*     */     
/* 158 */     return super.a(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, float var4) {
/* 163 */     if (x() && (var3.a(TagsBlock.RAILS) || var1.getType(var2.up()).a(TagsBlock.RAILS))) {
/* 164 */       return false;
/*     */     }
/*     */     
/* 167 */     return super.a(var0, var1, var2, var3, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound var0) {
/* 172 */     super.loadData(var0);
/* 173 */     if (var0.hasKeyOfType("TNTFuse", 99)) {
/* 174 */       this.b = var0.getInt("TNTFuse");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound var0) {
/* 180 */     super.saveData(var0);
/* 181 */     var0.setInt("TNTFuse", this.b);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */