/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ChunkEmpty extends Chunk {
/*    */   static {
/* 10 */     b = SystemUtils.<BiomeBase[]>a(new BiomeBase[BiomeStorage.a], abiomebase -> Arrays.fill((Object[])abiomebase, BiomeRegistry.a));
/*    */   }
/*    */ 
/*    */   
/*    */   public ChunkEmpty(World world, ChunkCoordIntPair chunkcoordintpair) {
/* 15 */     super(world, chunkcoordintpair, new BiomeStorage(MinecraftServer.getServer().getCustomRegistry().b(IRegistry.ay), b));
/*    */   }
/*    */   private static final BiomeBase[] b;
/*    */   
/*    */   public IBlockData getType(int x, int y, int z) {
/* 20 */     return Blocks.VOID_AIR.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData getType(BlockPosition blockposition) {
/* 25 */     return Blocks.VOID_AIR.getBlockData();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public IBlockData setType(BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public Fluid getFluid(BlockPosition blockposition) {
/* 36 */     return FluidTypes.EMPTY.h();
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public LightEngine e() {
/* 42 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int g(BlockPosition blockposition) {
/* 47 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Entity entity) {}
/*    */ 
/*    */   
/*    */   public void b(Entity entity) {}
/*    */ 
/*    */   
/*    */   public void a(Entity entity, int i) {}
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public TileEntity a(BlockPosition blockposition, Chunk.EnumTileEntityState chunk_enumtileentitystate) {
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(TileEntity tileentity) {}
/*    */ 
/*    */   
/*    */   public void setTileEntity(BlockPosition blockposition, TileEntity tileentity) {}
/*    */ 
/*    */   
/*    */   public void removeTileEntity(BlockPosition blockposition) {}
/*    */ 
/*    */   
/*    */   public void markDirty() {}
/*    */ 
/*    */   
/*    */   public void a(@Nullable Entity entity, AxisAlignedBB axisalignedbb, List<Entity> list, Predicate<? super Entity> predicate) {}
/*    */ 
/*    */   
/*    */   public <T extends Entity> void a(Class<? extends T> oclass, AxisAlignedBB axisalignedbb, List<T> list, Predicate<? super T> predicate) {}
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 85 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(int i, int j) {
/* 90 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerChunk.State getState() {
/* 95 */     return PlayerChunk.State.BORDER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */