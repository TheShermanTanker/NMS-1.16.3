/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockBamboo extends Block implements IBlockFragilePlantElement {
/*   8 */   protected static final VoxelShape a = Block.a(5.0D, 0.0D, 5.0D, 11.0D, 16.0D, 11.0D);
/*   9 */   protected static final VoxelShape b = Block.a(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
/*  10 */   protected static final VoxelShape c = Block.a(6.5D, 0.0D, 6.5D, 9.5D, 16.0D, 9.5D);
/*  11 */   public static final BlockStateInteger d = BlockProperties.ae;
/*  12 */   public static final BlockStateEnum<BlockPropertyBambooSize> e = BlockProperties.aN;
/*  13 */   public static final BlockStateInteger f = BlockProperties.aA;
/*     */   
/*     */   public BlockBamboo(BlockBase.Info blockbase_info) {
/*  16 */     super(blockbase_info);
/*  17 */     j(((IBlockData)this.blockStateList.getBlockData()).set(d, Integer.valueOf(0)).set(e, BlockPropertyBambooSize.NONE).set(f, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/*  22 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { d, e, f });
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockBase.EnumRandomOffset ah_() {
/*  27 */     return BlockBase.EnumRandomOffset.XZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  32 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  37 */     VoxelShape voxelshape = (iblockdata.get(e) == BlockPropertyBambooSize.LARGE) ? b : a;
/*  38 */     Vec3D vec3d = iblockdata.n(iblockaccess, blockposition);
/*     */     
/*  40 */     return voxelshape.a(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  50 */     Vec3D vec3d = iblockdata.n(iblockaccess, blockposition);
/*     */     
/*  52 */     return c.a(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  58 */     Fluid fluid = blockactioncontext.getWorld().getFluid(blockactioncontext.getClickPosition());
/*     */     
/*  60 */     if (!fluid.isEmpty()) {
/*  61 */       return null;
/*     */     }
/*  63 */     IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition().down());
/*     */     
/*  65 */     if (iblockdata.a(TagsBlock.BAMBOO_PLANTABLE_ON)) {
/*  66 */       if (iblockdata.a(Blocks.BAMBOO_SAPLING))
/*  67 */         return getBlockData().set(d, Integer.valueOf(0)); 
/*  68 */       if (iblockdata.a(Blocks.BAMBOO)) {
/*  69 */         int i = (((Integer)iblockdata.get(d)).intValue() > 0) ? 1 : 0;
/*     */         
/*  71 */         return getBlockData().set(d, Integer.valueOf(i));
/*     */       } 
/*  73 */       IBlockData iblockdata1 = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition().up());
/*     */       
/*  75 */       return (!iblockdata1.a(Blocks.BAMBOO) && !iblockdata1.a(Blocks.BAMBOO_SAPLING)) ? Blocks.BAMBOO_SAPLING.getBlockData() : getBlockData().set(d, iblockdata1.get(d));
/*     */     } 
/*     */     
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  85 */     if (!iblockdata.canPlace(worldserver, blockposition)) {
/*  86 */       worldserver.b(blockposition, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  93 */     return (((Integer)iblockdata.get(f)).intValue() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  98 */     if (((Integer)iblockdata.get(f)).intValue() == 0 && 
/*  99 */       random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.bambooModifier) * 3)) == 0 && worldserver.isEmpty(blockposition.up()) && worldserver.getLightLevel(blockposition.up(), 0) >= 9) {
/* 100 */       int i = b(worldserver, blockposition) + 1;
/*     */       
/* 102 */       if (i < 16) {
/* 103 */         a(iblockdata, worldserver, blockposition, random, i);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 112 */     return iworldreader.getType(blockposition.down()).a(TagsBlock.BAMBOO_PLANTABLE_ON);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 117 */     if (!iblockdata.canPlace(generatoraccess, blockposition)) {
/* 118 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */     
/* 121 */     if (enumdirection == EnumDirection.UP && iblockdata1.a(Blocks.BAMBOO) && ((Integer)iblockdata1.get(d)).intValue() > ((Integer)iblockdata.get(d)).intValue()) {
/* 122 */       generatoraccess.setTypeAndData(blockposition, iblockdata.a(d), 2);
/*     */     }
/*     */     
/* 125 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 130 */     int i = a(iblockaccess, blockposition);
/* 131 */     int j = b(iblockaccess, blockposition);
/*     */     
/* 133 */     return (i + j + 1 < 16 && ((Integer)iblockaccess.getType(blockposition.up(i)).get(f)).intValue() != 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 138 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 143 */     int i = a(worldserver, blockposition);
/* 144 */     int j = b(worldserver, blockposition);
/* 145 */     int k = i + j + 1;
/* 146 */     int l = 1 + random.nextInt(2);
/*     */     
/* 148 */     for (int i1 = 0; i1 < l; i1++) {
/* 149 */       BlockPosition blockposition1 = blockposition.up(i);
/* 150 */       IBlockData iblockdata1 = worldserver.getType(blockposition1);
/*     */       
/* 152 */       if (k >= 16 || ((Integer)iblockdata1.get(f)).intValue() == 1 || !worldserver.isEmpty(blockposition1.up())) {
/*     */         return;
/*     */       }
/*     */       
/* 156 */       a(iblockdata1, worldserver, blockposition1, random, k);
/* 157 */       i++;
/* 158 */       k++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDamage(IBlockData iblockdata, EntityHuman entityhuman, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 165 */     return (entityhuman.getItemInMainHand().getItem() instanceof ItemSword) ? 1.0F : super.getDamage(iblockdata, entityhuman, iblockaccess, blockposition);
/*     */   }
/*     */   
/*     */   protected void a(IBlockData iblockdata, World world, BlockPosition blockposition, Random random, int i) {
/* 169 */     IBlockData iblockdata1 = world.getType(blockposition.down());
/* 170 */     BlockPosition blockposition1 = blockposition.down(2);
/* 171 */     IBlockData iblockdata2 = world.getType(blockposition1);
/* 172 */     BlockPropertyBambooSize blockpropertybamboosize = BlockPropertyBambooSize.NONE;
/* 173 */     boolean shouldUpdateOthers = false;
/*     */     
/* 175 */     if (i >= 1) {
/* 176 */       if (iblockdata1.a(Blocks.BAMBOO) && iblockdata1.get(e) != BlockPropertyBambooSize.NONE) {
/* 177 */         if (iblockdata1.a(Blocks.BAMBOO) && iblockdata1.get(e) != BlockPropertyBambooSize.NONE) {
/* 178 */           blockpropertybamboosize = BlockPropertyBambooSize.LARGE;
/* 179 */           if (iblockdata2.a(Blocks.BAMBOO))
/*     */           {
/*     */ 
/*     */             
/* 183 */             shouldUpdateOthers = true;
/*     */           }
/*     */         } 
/*     */       } else {
/*     */         
/* 188 */         blockpropertybamboosize = BlockPropertyBambooSize.SMALL;
/*     */       } 
/*     */     }
/*     */     
/* 192 */     int j = (((Integer)iblockdata.get(d)).intValue() != 1 && !iblockdata2.a(Blocks.BAMBOO)) ? 0 : 1;
/* 193 */     int k = ((i < 11 || random.nextFloat() >= 0.25F) && i != 15) ? 0 : 1;
/*     */ 
/*     */     
/* 196 */     if (CraftEventFactory.handleBlockSpreadEvent(world, blockposition, blockposition.up(), getBlockData().set(d, Integer.valueOf(j)).set(e, blockpropertybamboosize).set(f, Integer.valueOf(k)), 3) && 
/* 197 */       shouldUpdateOthers) {
/* 198 */       world.setTypeAndData(blockposition.down(), iblockdata1.set(e, BlockPropertyBambooSize.SMALL), 3);
/* 199 */       world.setTypeAndData(blockposition1, iblockdata2.set(e, BlockPropertyBambooSize.NONE), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*     */     int i;
/* 208 */     for (i = 0; i < 16 && iblockaccess.getType(blockposition.up(i + 1)).a(Blocks.BAMBOO); i++);
/*     */ 
/*     */ 
/*     */     
/* 212 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*     */     int i;
/* 218 */     for (i = 0; i < 16 && iblockaccess.getType(blockposition.down(i + 1)).a(Blocks.BAMBOO); i++);
/*     */ 
/*     */ 
/*     */     
/* 222 */     return i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */