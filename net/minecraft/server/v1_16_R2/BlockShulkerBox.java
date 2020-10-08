/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.function.Consumer;
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
/*     */ public class BlockShulkerBox
/*     */   extends BlockTileEntity
/*     */ {
/*  50 */   public static final BlockStateEnum<EnumDirection> a = BlockDirectional.FACING;
/*     */   
/*  52 */   public static final MinecraftKey b = new MinecraftKey("contents");
/*     */   
/*     */   @Nullable
/*     */   public final EnumColor color;
/*     */   
/*     */   public BlockShulkerBox(@Nullable EnumColor var0, BlockBase.Info var1) {
/*  58 */     super(var1);
/*  59 */     this.color = var0;
/*  60 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.UP));
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess var0) {
/*  65 */     return new TileEntityShulkerBox(this.color);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData var0) {
/*  70 */     return EnumRenderType.ENTITYBLOCK_ANIMATED;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3, EnumHand var4, MovingObjectPositionBlock var5) {
/*  75 */     if (var1.isClientSide) {
/*  76 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*     */     
/*  79 */     if (var3.isSpectator()) {
/*  80 */       return EnumInteractionResult.CONSUME;
/*     */     }
/*     */     
/*  83 */     TileEntity var6 = var1.getTileEntity(var2);
/*  84 */     if (var6 instanceof TileEntityShulkerBox) {
/*  85 */       boolean var8; TileEntityShulkerBox var7 = (TileEntityShulkerBox)var6;
/*     */       
/*  87 */       if (var7.j() == TileEntityShulkerBox.AnimationPhase.CLOSED) {
/*  88 */         EnumDirection var9 = (EnumDirection)var0.get(a);
/*  89 */         var8 = var1.b(ShulkerUtil.a(var2, var9));
/*     */       } else {
/*  91 */         var8 = true;
/*     */       } 
/*  93 */       if (var8) {
/*  94 */         var3.openContainer(var7);
/*  95 */         var3.a(StatisticList.OPEN_SHULKER_BOX);
/*  96 */         PiglinAI.a(var3, true);
/*     */       } 
/*  98 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/* 100 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext var0) {
/* 105 */     return getBlockData().set(a, var0.getClickedFace());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> var0) {
/* 110 */     var0.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World var0, BlockPosition var1, IBlockData var2, EntityHuman var3) {
/* 115 */     TileEntity var4 = var0.getTileEntity(var1);
/* 116 */     if (var4 instanceof TileEntityShulkerBox) {
/* 117 */       TileEntityShulkerBox var5 = (TileEntityShulkerBox)var4;
/* 118 */       if (!var0.isClientSide && var3.isCreative() && !var5.isEmpty()) {
/*     */         
/* 120 */         ItemStack var6 = b(c());
/* 121 */         NBTTagCompound var7 = var5.e(new NBTTagCompound());
/* 122 */         if (!var7.isEmpty()) {
/* 123 */           var6.a("BlockEntityTag", var7);
/*     */         }
/*     */         
/* 126 */         if (var5.hasCustomName()) {
/* 127 */           var6.a(var5.getCustomName());
/*     */         }
/*     */         
/* 130 */         EntityItem var8 = new EntityItem(var0, var1.getX() + 0.5D, var1.getY() + 0.5D, var1.getZ() + 0.5D, var6);
/* 131 */         var8.defaultPickupDelay();
/* 132 */         var0.addEntity(var8);
/*     */       } else {
/* 134 */         var5.d(var3);
/*     */       } 
/*     */     } 
/* 137 */     super.a(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ItemStack> a(IBlockData var0, LootTableInfo.Builder var1) {
/* 142 */     TileEntity var2 = var1.<TileEntity>b(LootContextParameters.BLOCK_ENTITY);
/*     */     
/* 144 */     if (var2 instanceof TileEntityShulkerBox) {
/* 145 */       TileEntityShulkerBox var3 = (TileEntityShulkerBox)var2;
/* 146 */       var1 = var1.a(b, (var1, var2) -> {
/*     */             for (int var3 = 0; var3 < var0.getSize(); var3++) {
/*     */               var2.accept(var0.getItem(var3));
/*     */             }
/*     */           });
/*     */     } 
/* 152 */     return super.a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, EntityLiving var3, ItemStack var4) {
/* 157 */     if (var4.hasName()) {
/* 158 */       TileEntity var5 = var0.getTileEntity(var1);
/* 159 */       if (var5 instanceof TileEntityShulkerBox) {
/* 160 */         ((TileEntityShulkerBox)var5).setCustomName(var4.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(IBlockData var0, World var1, BlockPosition var2, IBlockData var3, boolean var4) {
/* 167 */     if (var0.a(var3.getBlock())) {
/*     */       return;
/*     */     }
/* 170 */     TileEntity var5 = var1.getTileEntity(var2);
/*     */     
/* 172 */     if (var5 instanceof TileEntityShulkerBox) {
/* 173 */       var1.updateAdjacentComparators(var2, var0.getBlock());
/*     */     }
/*     */     
/* 176 */     super.remove(var0, var1, var2, var3, var4);
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
/*     */   public EnumPistonReaction getPushReaction(IBlockData var0) {
/* 217 */     return EnumPistonReaction.DESTROY;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData var0, IBlockAccess var1, BlockPosition var2, VoxelShapeCollision var3) {
/* 222 */     TileEntity var4 = var1.getTileEntity(var2);
/* 223 */     if (var4 instanceof TileEntityShulkerBox) {
/* 224 */       return VoxelShapes.a(((TileEntityShulkerBox)var4).a(var0));
/*     */     }
/* 226 */     return VoxelShapes.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData var0) {
/* 231 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData var0, World var1, BlockPosition var2) {
/* 236 */     return Container.b((IInventory)var1.getTileEntity(var2));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Block a(@Nullable EnumColor var0) {
/* 267 */     if (var0 == null) {
/* 268 */       return Blocks.SHULKER_BOX;
/*     */     }
/* 270 */     switch (null.a[var0.ordinal()])
/*     */     { case 1:
/* 272 */         return Blocks.WHITE_SHULKER_BOX;
/*     */       case 2:
/* 274 */         return Blocks.ORANGE_SHULKER_BOX;
/*     */       case 3:
/* 276 */         return Blocks.MAGENTA_SHULKER_BOX;
/*     */       case 4:
/* 278 */         return Blocks.LIGHT_BLUE_SHULKER_BOX;
/*     */       case 5:
/* 280 */         return Blocks.YELLOW_SHULKER_BOX;
/*     */       case 6:
/* 282 */         return Blocks.LIME_SHULKER_BOX;
/*     */       case 7:
/* 284 */         return Blocks.PINK_SHULKER_BOX;
/*     */       case 8:
/* 286 */         return Blocks.GRAY_SHULKER_BOX;
/*     */       case 9:
/* 288 */         return Blocks.LIGHT_GRAY_SHULKER_BOX;
/*     */       case 10:
/* 290 */         return Blocks.CYAN_SHULKER_BOX;
/*     */       
/*     */       default:
/* 293 */         return Blocks.PURPLE_SHULKER_BOX;
/*     */       case 12:
/* 295 */         return Blocks.BLUE_SHULKER_BOX;
/*     */       case 13:
/* 297 */         return Blocks.BROWN_SHULKER_BOX;
/*     */       case 14:
/* 299 */         return Blocks.GREEN_SHULKER_BOX;
/*     */       case 15:
/* 301 */         return Blocks.RED_SHULKER_BOX;
/*     */       case 16:
/* 303 */         break; }  return Blocks.BLACK_SHULKER_BOX;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EnumColor c() {
/* 309 */     return this.color;
/*     */   }
/*     */   
/*     */   public static ItemStack b(@Nullable EnumColor var0) {
/* 313 */     return new ItemStack(a(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockRotation var1) {
/* 318 */     return var0.set(a, var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData var0, EnumBlockMirror var1) {
/* 323 */     return var0.a(var1.a((EnumDirection)var0.get(a)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */