/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.util.Optional;
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
/*     */ public class BlockRespawnAnchor
/*     */   extends Block
/*     */ {
/*  42 */   public static final BlockStateInteger a = BlockProperties.aC;
/*     */   
/*  44 */   private static final ImmutableList<BaseBlockPosition> b = ImmutableList.of(new BaseBlockPosition(0, 0, -1), new BaseBlockPosition(-1, 0, 0), new BaseBlockPosition(0, 0, 1), new BaseBlockPosition(1, 0, 0), new BaseBlockPosition(-1, 0, -1), new BaseBlockPosition(1, 0, -1), new BaseBlockPosition(-1, 0, 1), new BaseBlockPosition(1, 0, 1));
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
/*  55 */   private static final ImmutableList<BaseBlockPosition> c = (new ImmutableList.Builder())
/*  56 */     .addAll((Iterable)b)
/*  57 */     .addAll(b.stream().map(BaseBlockPosition::down).iterator())
/*  58 */     .addAll(b.stream().map(BaseBlockPosition::up).iterator())
/*  59 */     .add(new BaseBlockPosition(0, 1, 0))
/*  60 */     .build();
/*     */   
/*     */   public BlockRespawnAnchor(BlockBase.Info var0) {
/*  63 */     super(var0);
/*  64 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  69 */     ItemStack var6 = var3.b(var4);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     if (var4 == EnumHand.MAIN_HAND && 
/*  75 */       !a(var6) && 
/*  76 */       a(var3.b(EnumHand.OFF_HAND))) {
/*  77 */       return EnumInteractionResult.PASS;
/*     */     }
/*     */     
/*  80 */     if (a(var6) && 
/*  81 */       h(var0)) {
/*  82 */       a(var1, var2, var0);
/*  83 */       if (!var3.abilities.canInstantlyBuild) {
/*  84 */         var6.subtract(1);
/*     */       }
/*     */       
/*  87 */       return EnumInteractionResult.a(var1.isClientSide);
/*     */     } 
/*     */ 
/*     */     
/*  91 */     if (((Integer)var0.get(a)).intValue() == 0) {
/*  92 */       return EnumInteractionResult.PASS;
/*     */     }
/*     */     
/*  95 */     if (a(var1)) {
/*  96 */       if (!var1.isClientSide) {
/*  97 */         EntityPlayer var7 = (EntityPlayer)var3;
/*  98 */         if (var7.getSpawnDimension() != var1.getDimensionKey() || !var7.getSpawn().equals(var2)) {
/*  99 */           var7.setRespawnPosition(var1.getDimensionKey(), var2, 0.0F, false, true);
/* 100 */           var1.playSound(null, var2.getX() + 0.5D, var2.getY() + 0.5D, var2.getZ() + 0.5D, SoundEffects.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 101 */           return EnumInteractionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */       
/* 105 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/* 107 */     if (!var1.isClientSide) {
/* 108 */       d(var0, var1, var2);
/*     */     }
/* 110 */     return EnumInteractionResult.a(var1.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(ItemStack var0) {
/* 115 */     return (var0.getItem() == Items.dq);
/*     */   }
/*     */   
/*     */   private static boolean h(IBlockData var0) {
/* 119 */     return (((Integer)var0.get(a)).intValue() < 4);
/*     */   }
/*     */   
/*     */   private static boolean a(BlockPosition var0, World var1) {
/* 123 */     Fluid var2 = var1.getFluid(var0);
/* 124 */     if (!var2.a(TagsFluid.WATER)) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (var2.isSource()) {
/* 128 */       return true;
/*     */     }
/* 130 */     float var3 = var2.e();
/* 131 */     if (var3 < 2.0F) {
/* 132 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 136 */     Fluid var4 = var1.getFluid(var0.down());
/* 137 */     return !var4.a(TagsFluid.WATER);
/*     */   }
/*     */   
/*     */   private void d(IBlockData var0, World var1, BlockPosition var2) {
/* 141 */     var1.a(var2, false);
/*     */     
/* 143 */     boolean var3 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a().map(var2::shift).anyMatch(var1 -> a(var1, var0));
/* 144 */     boolean var4 = (var3 || var1.getFluid(var2.up()).a(TagsFluid.WATER));
/* 145 */     ExplosionDamageCalculator var5 = new ExplosionDamageCalculator(this, var2, var4)
/*     */       {
/*     */         public Optional<Float> a(Explosion var0, IBlockAccess var1, BlockPosition var2, IBlockData var3, Fluid var4) {
/* 148 */           if (var2.equals(this.a) && this.b)
/*     */           {
/* 150 */             return Optional.of(Float.valueOf(Blocks.WATER.getDurability()));
/*     */           }
/* 152 */           return super.a(var0, var1, var2, var3, var4);
/*     */         }
/*     */       };
/* 155 */     var1.createExplosion(null, DamageSource.a(), var5, var2.getX() + 0.5D, var2.getY() + 0.5D, var2.getZ() + 0.5D, 5.0F, true, Explosion.Effect.DESTROY);
/*     */   }
/*     */   
/*     */   public static boolean a(World var0) {
/* 159 */     return var0.getDimensionManager().isRespawnAnchorWorks();
/*     */   }
/*     */   
/*     */   public static void a(World var0, BlockPosition var1, IBlockData var2) {
/* 163 */     var0.setTypeAndData(var1, var2.set(a, Integer.valueOf(((Integer)var2.get(a)).intValue() + 1)), 3);
/* 164 */     var0.playSound(null, var1.getX() + 0.5D, var1.getY() + 0.5D, var1.getZ() + 0.5D, SoundEffects.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 187 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/* 192 */     return true;
/*     */   }
/*     */   
/*     */   public static int a(IBlockData var0, int var1) {
/* 196 */     return MathHelper.d((((Integer)var0.get(a)).intValue() - 0) / 4.0F * var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/* 201 */     return a(var0, 15);
/*     */   }
/*     */   
/*     */   public static Optional<Vec3D> a(EntityTypes<?> var0, ICollisionAccess var1, BlockPosition var2) {
/* 205 */     Optional<Vec3D> var3 = a(var0, var1, var2, true);
/* 206 */     if (var3.isPresent()) {
/* 207 */       return var3;
/*     */     }
/* 209 */     return a(var0, var1, var2, false);
/*     */   }
/*     */   
/*     */   private static Optional<Vec3D> a(EntityTypes<?> var0, ICollisionAccess var1, BlockPosition var2, boolean var3) {
/* 213 */     BlockPosition.MutableBlockPosition var4 = new BlockPosition.MutableBlockPosition();
/* 214 */     for (UnmodifiableIterator<BaseBlockPosition> unmodifiableIterator = c.iterator(); unmodifiableIterator.hasNext(); ) { BaseBlockPosition var6 = unmodifiableIterator.next();
/* 215 */       var4.g(var2).h(var6);
/*     */       
/* 217 */       Vec3D var7 = DismountUtil.a(var0, var1, var4, var3);
/* 218 */       if (var7 != null) {
/* 219 */         return Optional.of(var7);
/*     */       } }
/*     */     
/* 222 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 227 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockRespawnAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */