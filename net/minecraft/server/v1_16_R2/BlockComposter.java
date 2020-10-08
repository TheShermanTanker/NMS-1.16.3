/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.objects.Object2FloatMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftBlockInventoryHolder;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.DummyGeneratorAccess;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class BlockComposter
/*     */   extends Block implements IInventoryHolder {
/*  14 */   public static final BlockStateInteger a = BlockProperties.as;
/*  15 */   public static final Object2FloatMap<IMaterial> b = (Object2FloatMap<IMaterial>)new Object2FloatOpenHashMap();
/*  16 */   private static final VoxelShape c = VoxelShapes.b(); static {
/*  17 */     d = SystemUtils.<VoxelShape[]>a(new VoxelShape[9], avoxelshape -> {
/*     */           for (int i = 0; i < 8; i++)
/*     */             avoxelshape[i] = VoxelShapes.a(c, Block.a(2.0D, Math.max(2, 1 + i * 2), 2.0D, 14.0D, 16.0D, 14.0D), OperatorBoolean.ONLY_FIRST); 
/*     */           avoxelshape[8] = avoxelshape[7];
/*     */         });
/*     */   }
/*     */   private static final VoxelShape[] d;
/*     */   
/*     */   public static void c() {
/*  26 */     b.defaultReturnValue(-1.0F);
/*  27 */     float f = 0.3F;
/*  28 */     float f1 = 0.5F;
/*  29 */     float f2 = 0.65F;
/*  30 */     float f3 = 0.85F;
/*  31 */     float f4 = 1.0F;
/*     */     
/*  33 */     a(0.3F, Items.au);
/*  34 */     a(0.3F, Items.ar);
/*  35 */     a(0.3F, Items.as);
/*  36 */     a(0.3F, Items.aw);
/*  37 */     a(0.3F, Items.av);
/*  38 */     a(0.3F, Items.at);
/*  39 */     a(0.3F, Items.x);
/*  40 */     a(0.3F, Items.y);
/*  41 */     a(0.3F, Items.z);
/*  42 */     a(0.3F, Items.A);
/*  43 */     a(0.3F, Items.B);
/*  44 */     a(0.3F, Items.C);
/*  45 */     a(0.3F, Items.BEETROOT_SEEDS);
/*  46 */     a(0.3F, Items.DRIED_KELP);
/*  47 */     a(0.3F, Items.aL);
/*  48 */     a(0.3F, Items.bE);
/*  49 */     a(0.3F, Items.MELON_SEEDS);
/*  50 */     a(0.3F, Items.PUMPKIN_SEEDS);
/*  51 */     a(0.3F, Items.aO);
/*  52 */     a(0.3F, Items.SWEET_BERRIES);
/*  53 */     a(0.3F, Items.WHEAT_SEEDS);
/*  54 */     a(0.5F, Items.ma);
/*  55 */     a(0.5F, Items.gn);
/*  56 */     a(0.5F, Items.cX);
/*  57 */     a(0.5F, Items.bD);
/*  58 */     a(0.5F, Items.dR);
/*  59 */     a(0.5F, Items.bA);
/*  60 */     a(0.5F, Items.bB);
/*  61 */     a(0.5F, Items.bC);
/*  62 */     a(0.5F, Items.MELON_SLICE);
/*  63 */     a(0.65F, Items.aP);
/*  64 */     a(0.65F, Items.ed);
/*  65 */     a(0.65F, Items.di);
/*  66 */     a(0.65F, Items.dj);
/*  67 */     a(0.65F, Items.dQ);
/*  68 */     a(0.65F, Items.APPLE);
/*  69 */     a(0.65F, Items.BEETROOT);
/*  70 */     a(0.65F, Items.CARROT);
/*  71 */     a(0.65F, Items.COCOA_BEANS);
/*  72 */     a(0.65F, Items.POTATO);
/*  73 */     a(0.65F, Items.WHEAT);
/*  74 */     a(0.65F, Items.bu);
/*  75 */     a(0.65F, Items.bv);
/*  76 */     a(0.65F, Items.dM);
/*  77 */     a(0.65F, Items.bw);
/*  78 */     a(0.65F, Items.bx);
/*  79 */     a(0.65F, Items.NETHER_WART);
/*  80 */     a(0.65F, Items.by);
/*  81 */     a(0.65F, Items.bz);
/*  82 */     a(0.65F, Items.rp);
/*  83 */     a(0.65F, Items.bh);
/*  84 */     a(0.65F, Items.bi);
/*  85 */     a(0.65F, Items.bj);
/*  86 */     a(0.65F, Items.bk);
/*  87 */     a(0.65F, Items.bl);
/*  88 */     a(0.65F, Items.bm);
/*  89 */     a(0.65F, Items.bn);
/*  90 */     a(0.65F, Items.bo);
/*  91 */     a(0.65F, Items.bp);
/*  92 */     a(0.65F, Items.bq);
/*  93 */     a(0.65F, Items.br);
/*  94 */     a(0.65F, Items.bs);
/*  95 */     a(0.65F, Items.bt);
/*  96 */     a(0.65F, Items.aM);
/*  97 */     a(0.65F, Items.gj);
/*  98 */     a(0.65F, Items.gk);
/*  99 */     a(0.65F, Items.gl);
/* 100 */     a(0.65F, Items.gm);
/* 101 */     a(0.65F, Items.go);
/* 102 */     a(0.85F, Items.fL);
/* 103 */     a(0.85F, Items.dK);
/* 104 */     a(0.85F, Items.dL);
/* 105 */     a(0.85F, Items.hj);
/* 106 */     a(0.85F, Items.hk);
/* 107 */     a(0.85F, Items.BREAD);
/* 108 */     a(0.85F, Items.BAKED_POTATO);
/* 109 */     a(0.85F, Items.COOKIE);
/* 110 */     a(1.0F, Items.mN);
/* 111 */     a(1.0F, Items.PUMPKIN_PIE);
/*     */   }
/*     */   
/*     */   private static void a(float f, IMaterial imaterial) {
/* 115 */     b.put(imaterial.getItem(), f);
/*     */   }
/*     */   
/*     */   public BlockComposter(BlockBase.Info blockbase_info) {
/* 119 */     super(blockbase_info);
/* 120 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 125 */     return d[((Integer)iblockdata.get(a)).intValue()];
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 130 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 135 */     return d[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/* 140 */     if (((Integer)iblockdata.get(a)).intValue() == 7) {
/* 141 */       world.getBlockTickList().a(blockposition, iblockdata.getBlock(), 20);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 148 */     int i = ((Integer)iblockdata.get(a)).intValue();
/* 149 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 151 */     if (i < 8 && b.containsKey(itemstack.getItem())) {
/* 152 */       if (i < 7 && !world.isClientSide) {
/* 153 */         IBlockData iblockdata1 = b(iblockdata, world, blockposition, itemstack);
/*     */         
/* 155 */         world.triggerEffect(1500, blockposition, (iblockdata != iblockdata1) ? 1 : 0);
/* 156 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 157 */           itemstack.subtract(1);
/*     */         }
/*     */       } 
/*     */       
/* 161 */       return EnumInteractionResult.a(world.isClientSide);
/* 162 */     }  if (i == 8) {
/* 163 */       d(iblockdata, world, blockposition, (Entity)null);
/* 164 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/* 166 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBlockData a(IBlockData iblockdata, WorldServer worldserver, ItemStack itemstack, BlockPosition blockposition, Entity entity) {
/* 171 */     int i = ((Integer)iblockdata.get(a)).intValue();
/*     */     
/* 173 */     if (i < 7 && b.containsKey(itemstack.getItem())) {
/*     */       
/* 175 */       double rand = worldserver.getRandom().nextDouble();
/* 176 */       IBlockData iblockdata1 = b(iblockdata, DummyGeneratorAccess.INSTANCE, blockposition, itemstack, rand);
/* 177 */       if (iblockdata == iblockdata1 || CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, iblockdata1).isCancelled()) {
/* 178 */         return iblockdata;
/*     */       }
/* 180 */       iblockdata1 = b(iblockdata, worldserver, blockposition, itemstack, rand);
/*     */ 
/*     */       
/* 183 */       itemstack.subtract(1);
/* 184 */       return iblockdata1;
/*     */     } 
/* 186 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static IBlockData d(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 192 */     if (entity != null) {
/* 193 */       IBlockData iBlockData = d(iblockdata, DummyGeneratorAccess.INSTANCE, blockposition);
/* 194 */       if (CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, iBlockData).isCancelled()) {
/* 195 */         return iblockdata;
/*     */       }
/*     */     } 
/*     */     
/* 199 */     if (!world.isClientSide) {
/* 200 */       float f = 0.7F;
/* 201 */       double d0 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/* 202 */       double d1 = (world.random.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
/* 203 */       double d2 = (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
/* 204 */       EntityItem entityitem = new EntityItem(world, blockposition.getX() + d0, blockposition.getY() + d1, blockposition.getZ() + d2, new ItemStack(Items.BONE_MEAL));
/*     */       
/* 206 */       entityitem.defaultPickupDelay();
/* 207 */       world.addEntity(entityitem);
/*     */     } 
/*     */     
/* 210 */     IBlockData iblockdata1 = d(iblockdata, world, blockposition);
/*     */     
/* 212 */     world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 213 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   private static IBlockData d(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 217 */     IBlockData iblockdata1 = iblockdata.set(a, Integer.valueOf(0));
/*     */     
/* 219 */     generatoraccess.setTypeAndData(blockposition, iblockdata1, 3);
/* 220 */     return iblockdata1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBlockData b(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, ItemStack itemstack) {
/* 225 */     return b(iblockdata, generatoraccess, blockposition, itemstack, generatoraccess.getRandom().nextDouble());
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBlockData b(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, ItemStack itemstack, double rand) {
/* 230 */     int i = ((Integer)iblockdata.get(a)).intValue();
/* 231 */     float f = b.getFloat(itemstack.getItem());
/*     */     
/* 233 */     if ((i != 0 || f <= 0.0F) && rand >= f) {
/* 234 */       return iblockdata;
/*     */     }
/* 236 */     int j = i + 1;
/* 237 */     IBlockData iblockdata1 = iblockdata.set(a, Integer.valueOf(j));
/*     */     
/* 239 */     generatoraccess.setTypeAndData(blockposition, iblockdata1, 3);
/* 240 */     if (j == 7) {
/* 241 */       generatoraccess.getBlockTickList().a(blockposition, iblockdata.getBlock(), 20);
/*     */     }
/*     */     
/* 244 */     return iblockdata1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 250 */     if (((Integer)iblockdata.get(a)).intValue() == 7) {
/* 251 */       worldserver.setTypeAndData(blockposition, iblockdata.a(a), 3);
/* 252 */       worldserver.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_COMPOSTER_READY, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 259 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 264 */     return ((Integer)iblockdata.get(a)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 269 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/* 274 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IWorldInventory a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 279 */     int i = ((Integer)iblockdata.get(a)).intValue();
/*     */ 
/*     */     
/* 282 */     return (i == 8) ? new ContainerOutput(iblockdata, generatoraccess, blockposition, new ItemStack(Items.BONE_MEAL)) : ((i < 7) ? new ContainerInput(iblockdata, generatoraccess, blockposition) : new ContainerEmpty(generatoraccess, blockposition));
/*     */   }
/*     */   
/*     */   static class ContainerInput
/*     */     extends InventorySubcontainer implements IWorldInventory {
/*     */     private final IBlockData a;
/*     */     private final GeneratorAccess b;
/*     */     private final BlockPosition c;
/*     */     private boolean d;
/*     */     
/*     */     public ContainerInput(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 293 */       super(1);
/* 294 */       this.bukkitOwner = (InventoryHolder)new CraftBlockInventoryHolder(generatoraccess, blockposition, this);
/* 295 */       this.a = iblockdata;
/* 296 */       this.b = generatoraccess;
/* 297 */       this.c = blockposition;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 302 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 307 */       (new int[1])[0] = 0; return (enumdirection == EnumDirection.UP) ? new int[1] : new int[0];
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 312 */       return (!this.d && enumdirection == EnumDirection.UP && BlockComposter.b.containsKey(itemstack.getItem()));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 317 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public void update() {
/* 322 */       ItemStack itemstack = getItem(0);
/*     */       
/* 324 */       if (!itemstack.isEmpty()) {
/* 325 */         this.d = true;
/* 326 */         IBlockData iblockdata = BlockComposter.b(this.a, this.b, this.c, itemstack);
/*     */         
/* 328 */         this.b.triggerEffect(1500, this.c, (iblockdata != this.a) ? 1 : 0);
/* 329 */         splitWithoutUpdate(0);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class ContainerOutput
/*     */     extends InventorySubcontainer
/*     */     implements IWorldInventory {
/*     */     private final IBlockData blockData;
/*     */     private final GeneratorAccess generatorAccess;
/*     */     private final BlockPosition blockPosition;
/*     */     private boolean emptied;
/*     */     
/*     */     public ContainerOutput(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, ItemStack itemstack) {
/* 343 */       super(new ItemStack[] { itemstack });
/* 344 */       this.blockData = iblockdata;
/* 345 */       this.generatorAccess = generatoraccess;
/* 346 */       this.blockPosition = blockposition;
/* 347 */       this.bukkitOwner = (InventoryHolder)new CraftBlockInventoryHolder(generatoraccess, blockposition, this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 352 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 357 */       (new int[1])[0] = 0; return (enumdirection == EnumDirection.DOWN) ? new int[1] : new int[0];
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 362 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 367 */       return (!this.emptied && enumdirection == EnumDirection.DOWN && itemstack.getItem() == Items.BONE_MEAL);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void update() {
/* 373 */       if (isEmpty()) {
/* 374 */         BlockComposter.d(this.blockData, this.generatorAccess, this.blockPosition);
/* 375 */         this.emptied = true;
/*     */       } else {
/* 377 */         this.generatorAccess.setTypeAndData(this.blockPosition, this.blockData, 3);
/* 378 */         this.emptied = false;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class ContainerEmpty
/*     */     extends InventorySubcontainer
/*     */     implements IWorldInventory {
/*     */     public ContainerEmpty(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 387 */       super(0);
/* 388 */       this.bukkitOwner = (InventoryHolder)new CraftBlockInventoryHolder(generatoraccess, blockposition, this);
/*     */     }
/*     */ 
/*     */     
/*     */     public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 393 */       return new int[0];
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 398 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 403 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockComposter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */