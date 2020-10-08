/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public abstract class BlockGrowingTop extends BlockGrowingAbstract implements IBlockFragilePlantElement {
/*  7 */   public static final BlockStateInteger d = BlockProperties.ak;
/*    */   private final double e;
/*    */   
/*    */   protected BlockGrowingTop(BlockBase.Info blockbase_info, EnumDirection enumdirection, VoxelShape voxelshape, boolean flag, double d0) {
/* 11 */     super(blockbase_info, enumdirection, voxelshape, flag);
/* 12 */     this.e = d0;
/* 13 */     j(((IBlockData)this.blockStateList.getBlockData()).set(d, Integer.valueOf(0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(GeneratorAccess generatoraccess) {
/* 18 */     return getBlockData().set(d, Integer.valueOf(generatoraccess.getRandom().nextInt(25)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTicking(IBlockData iblockdata) {
/* 23 */     return (((Integer)iblockdata.get(d)).intValue() < 25);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 28 */     if (((Integer)iblockdata.get(d)).intValue() < 25 && random.nextDouble() < 100.0D / worldserver.spigotConfig.kelpModifier * this.e) {
/* 29 */       BlockPosition blockposition1 = blockposition.shift(this.a);
/*    */       
/* 31 */       if (h(worldserver.getType(blockposition1))) {
/* 32 */         CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition1, iblockdata.a(d));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 40 */     if (enumdirection == this.a.opposite() && !iblockdata.canPlace(generatoraccess, blockposition)) {
/* 41 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*    */     }
/*    */     
/* 44 */     if (enumdirection == this.a && (iblockdata1.a(this) || iblockdata1.a(d()))) {
/* 45 */       return d().getBlockData();
/*    */     }
/* 47 */     if (this.b) {
/* 48 */       generatoraccess.getFluidTickList().a(blockposition, FluidTypes.WATER, FluidTypes.WATER.a(generatoraccess));
/*    */     }
/*    */     
/* 51 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 57 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { d });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 62 */     return h(iblockaccess.getType(blockposition.shift(this.a)));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(WorldServer worldserver, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 72 */     BlockPosition blockposition1 = blockposition.shift(this.a);
/* 73 */     int i = Math.min(((Integer)iblockdata.get(d)).intValue() + 1, 25);
/* 74 */     int j = a(random);
/*    */     
/* 76 */     for (int k = 0; k < j && h(worldserver.getType(blockposition1)); k++) {
/* 77 */       worldserver.setTypeUpdate(blockposition1, iblockdata.set(d, Integer.valueOf(i)));
/* 78 */       blockposition1 = blockposition1.shift(this.a);
/* 79 */       i = Math.min(i + 1, 25);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract int a(Random paramRandom);
/*    */ 
/*    */   
/*    */   protected abstract boolean h(IBlockData paramIBlockData);
/*    */   
/*    */   protected BlockGrowingTop c() {
/* 90 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockGrowingTop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */