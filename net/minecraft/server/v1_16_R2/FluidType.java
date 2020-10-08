/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class FluidType
/*    */ {
/* 21 */   public static final RegistryBlockID<Fluid> c = new RegistryBlockID<>();
/*    */   
/*    */   protected final BlockStateList<FluidType, Fluid> d;
/*    */   private Fluid a;
/*    */   
/*    */   protected FluidType() {
/* 27 */     BlockStateList.a<FluidType, Fluid> var0 = new BlockStateList.a<>(this);
/* 28 */     a(var0);
/* 29 */     this.d = var0.a(FluidType::h, Fluid::new);
/* 30 */     f(this.d.getBlockData());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<FluidType, Fluid> var0) {}
/*    */   
/*    */   public BlockStateList<FluidType, Fluid> g() {
/* 37 */     return this.d;
/*    */   }
/*    */   
/*    */   protected final void f(Fluid var0) {
/* 41 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public final Fluid h() {
/* 45 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract Item a();
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(World var0, BlockPosition var1, Fluid var2) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void b(World var0, BlockPosition var1, Fluid var2, Random var3) {}
/*    */ 
/*    */   
/*    */   protected abstract boolean a(Fluid paramFluid, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, FluidType paramFluidType, EnumDirection paramEnumDirection);
/*    */ 
/*    */   
/*    */   protected abstract Vec3D a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition, Fluid paramFluid);
/*    */ 
/*    */   
/*    */   public abstract int a(IWorldReader paramIWorldReader);
/*    */ 
/*    */   
/*    */   protected boolean j() {
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   protected boolean b() {
/* 75 */     return false;
/*    */   }
/*    */   
/*    */   protected abstract float c();
/*    */   
/*    */   public abstract float a(Fluid paramFluid, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
/*    */   
/*    */   public abstract float a(Fluid paramFluid);
/*    */   
/*    */   protected abstract IBlockData b(Fluid paramFluid);
/*    */   
/*    */   public abstract boolean c(Fluid paramFluid);
/*    */   
/*    */   public abstract int d(Fluid paramFluid);
/*    */   
/*    */   public boolean a(FluidType var0) {
/* 91 */     return (var0 == this);
/*    */   }
/*    */   
/*    */   public boolean a(Tag<FluidType> var0) {
/* 95 */     return var0.isTagged(this);
/*    */   }
/*    */   
/*    */   public abstract VoxelShape b(Fluid paramFluid, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */