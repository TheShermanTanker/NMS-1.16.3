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
/*     */ public class BlockHoney
/*     */   extends BlockHalfTransparent
/*     */ {
/*  61 */   protected static final VoxelShape a = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
/*     */   
/*     */   public BlockHoney(BlockBase.Info var0) {
/*  64 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean c(Entity var0) {
/*  69 */     return (var0 instanceof EntityLiving || var0 instanceof EntityMinecartAbstract || var0 instanceof EntityTNTPrimed || var0 instanceof EntityBoat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  77 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fallOn(World var0, BlockPosition var1, Entity var2, float var3) {
/*  82 */     var2.playSound(SoundEffects.BLOCK_HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
/*     */     
/*  84 */     if (!var0.isClientSide)
/*     */     {
/*     */       
/*  87 */       var0.broadcastEntityEffect(var2, (byte)54);
/*     */     }
/*     */     
/*  90 */     if (var2.b(var3, 0.2F)) {
/*  91 */       var2.playSound(this.stepSound.g(), this.stepSound.a() * 0.5F, this.stepSound.b() * 0.75F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData var0, World var1, BlockPosition var2, Entity var3) {
/*  97 */     if (a(var2, var3)) {
/*  98 */       a(var3, var2);
/*  99 */       d(var3);
/* 100 */       a(var1, var3);
/*     */     } 
/* 102 */     super.a(var0, var1, var2, var3);
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0, Entity var1) {
/* 106 */     if (var1.isOnGround()) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (var1.locY() > var0.getY() + 0.9375D - 1.0E-7D)
/*     */     {
/* 111 */       return false;
/*     */     }
/* 113 */     if ((var1.getMot()).y >= -0.08D) {
/* 114 */       return false;
/*     */     }
/*     */     
/* 117 */     double var2 = Math.abs(var0.getX() + 0.5D - var1.locX());
/* 118 */     double var4 = Math.abs(var0.getZ() + 0.5D - var1.locZ());
/*     */     
/* 120 */     double var6 = 0.4375D + (var1.getWidth() / 2.0F);
/*     */     
/* 122 */     return (var2 + 1.0E-7D > var6 || var4 + 1.0E-7D > var6);
/*     */   }
/*     */   
/*     */   private void a(Entity var0, BlockPosition var1) {
/* 126 */     if (var0 instanceof EntityPlayer && var0.world.getTime() % 20L == 0L)
/*     */     {
/* 128 */       CriterionTriggers.J.a((EntityPlayer)var0, var0.world.getType(var1));
/*     */     }
/*     */   }
/*     */   
/*     */   private void d(Entity var0) {
/* 133 */     Vec3D var1 = var0.getMot();
/* 134 */     if (var1.y < -0.13D) {
/*     */ 
/*     */ 
/*     */       
/* 138 */       double var2 = -0.05D / var1.y;
/* 139 */       var0.setMot(new Vec3D(var1.x * var2, -0.05D, var1.z * var2));
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 145 */       var0.setMot(new Vec3D(var1.x, -0.05D, var1.z));
/*     */     } 
/* 147 */     var0.fallDistance = 0.0F;
/*     */   }
/*     */   
/*     */   private void a(World var0, Entity var1) {
/* 151 */     if (c(var1)) {
/* 152 */       if (var0.random.nextInt(5) == 0)
/*     */       {
/* 154 */         var1.playSound(SoundEffects.BLOCK_HONEY_BLOCK_SLIDE, 1.0F, 1.0F);
/*     */       }
/*     */       
/* 157 */       if (!var0.isClientSide && var0.random.nextInt(5) == 0)
/*     */       {
/* 159 */         var0.broadcastEntityEffect(var1, (byte)53);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockHoney.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */