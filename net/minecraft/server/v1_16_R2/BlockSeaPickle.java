/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockSeaPickle
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement, IBlockWaterlogged
/*     */ {
/*  28 */   public static final BlockStateInteger a = BlockProperties.ay;
/*  29 */   public static final BlockStateBoolean b = BlockProperties.C;
/*     */   
/*  31 */   protected static final VoxelShape c = Block.a(6.0D, 0.0D, 6.0D, 10.0D, 6.0D, 10.0D);
/*  32 */   protected static final VoxelShape d = Block.a(3.0D, 0.0D, 3.0D, 13.0D, 6.0D, 13.0D);
/*  33 */   protected static final VoxelShape e = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
/*  34 */   protected static final VoxelShape f = Block.a(2.0D, 0.0D, 2.0D, 14.0D, 7.0D, 14.0D);
/*     */   
/*     */   protected BlockSeaPickle(BlockBase.Info var0) {
/*  37 */     super(var0);
/*  38 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(1)).set(b, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/*  44 */     IBlockData var1 = var0.getWorld().getType(var0.getClickPosition());
/*  45 */     if (var1.a(this)) {
/*  46 */       return var1.set(a, Integer.valueOf(Math.min(4, ((Integer)var1.get(a)).intValue() + 1)));
/*     */     }
/*     */     
/*  49 */     Fluid var2 = var0.getWorld().getFluid(var0.getClickPosition());
/*  50 */     boolean var3 = (var2.getType() == FluidTypes.WATER);
/*  51 */     return super.getPlacedState(var0).set(b, Boolean.valueOf(var3));
/*     */   }
/*     */   
/*     */   public static boolean h(IBlockData var0) {
/*  55 */     return !((Boolean)var0.get(b)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean c(IBlockData var0, IBlockAccess var1, BlockPosition var2) {
/*  60 */     return (!var0.getCollisionShape(var1, var2).a(EnumDirection.UP).isEmpty() || var0.d(var1, var2, EnumDirection.UP));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData var0, IWorldReader var1, BlockPosition var2) {
/*  65 */     BlockPosition var3 = var2.down();
/*  66 */     return c(var1.getType(var3), var1, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData var0, EnumDirection var1, IBlockData var2, GeneratorAccess var3, BlockPosition var4, BlockPosition var5) {
/*  71 */     if (!var0.canPlace(var3, var4)) {
/*  72 */       return Blocks.AIR.getBlockData();
/*     */     }
/*     */     
/*  75 */     if (((Boolean)var0.get(b)).booleanValue()) {
/*  76 */       var3.getFluidTickList().a(var4, FluidTypes.WATER, FluidTypes.WATER.a(var3));
/*     */     }
/*     */     
/*  79 */     return super.updateState(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, BlockActionContext var1) {
/*  84 */     if (var1.getItemStack().getItem() == getItem() && ((Integer)var0.get(a)).intValue() < 4) {
/*  85 */       return true;
/*     */     }
/*  87 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/*  92 */     switch (((Integer)var0.get(a)).intValue())
/*     */     
/*     */     { default:
/*  95 */         return c;
/*     */       case 2:
/*  97 */         return d;
/*     */       case 3:
/*  99 */         return e;
/*     */       case 4:
/* 101 */         break; }  return f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid d(IBlockData var0) {
/* 107 */     if (((Boolean)var0.get(b)).booleanValue()) {
/* 108 */       return FluidTypes.WATER.a(false);
/*     */     }
/*     */     
/* 111 */     return super.d(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 116 */     var0.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess var0, BlockPosition var1, IBlockData var2, boolean var3) {
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 126 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer var0, Random var1, BlockPosition var2, IBlockData var3) {
/* 131 */     if (!h(var3) && var0.getType(var2.down()).a(TagsBlock.CORAL_BLOCKS)) {
/* 132 */       int var4 = 5;
/* 133 */       int var5 = 1;
/* 134 */       int var6 = 2;
/* 135 */       int var7 = 0;
/*     */       
/* 137 */       int var8 = var2.getX() - 2;
/* 138 */       int var9 = 0;
/*     */       
/* 140 */       for (int var10 = 0; var10 < 5; var10++) {
/* 141 */         for (int var11 = 0; var11 < var5; var11++) {
/* 142 */           int var12 = 2 + var2.getY() - 1;
/* 143 */           for (int var13 = var12 - 2; var13 < var12; var13++) {
/* 144 */             BlockPosition var14 = new BlockPosition(var8 + var10, var13, var2.getZ() - var9 + var11);
/* 145 */             if (var14 != var2)
/*     */             {
/*     */ 
/*     */               
/* 149 */               if (var1.nextInt(6) == 0 && var0.getType(var14).a(Blocks.WATER)) {
/* 150 */                 IBlockData var15 = var0.getType(var14.down());
/* 151 */                 if (var15.a(TagsBlock.CORAL_BLOCKS)) {
/* 152 */                   var0.setTypeAndData(var14, Blocks.SEA_PICKLE.getBlockData().set(a, Integer.valueOf(var1.nextInt(4) + 1)), 3);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/* 158 */         if (var7 < 2) {
/* 159 */           var5 += 2;
/* 160 */           var9++;
/*     */         } else {
/* 162 */           var5 -= 2;
/* 163 */           var9--;
/*     */         } 
/* 165 */         var7++;
/*     */       } 
/*     */       
/* 168 */       var0.setTypeAndData(var2, var3.set(a, Integer.valueOf(4)), 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData var0, IBlockAccess var1, BlockPosition var2, PathMode var3) {
/* 174 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSeaPickle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */