/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import java.util.Random;
/*    */ 
/*    */ public final class Fluid
/*    */   extends IBlockDataHolder<FluidType, Fluid> {
/* 10 */   public static final Codec<Fluid> a = a(IRegistry.FLUID, FluidType::h).stable();
/*    */   
/*    */   protected final boolean isEmpty;
/*    */ 
/*    */   
/*    */   public Fluid(FluidType fluidtype, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap, MapCodec<Fluid> mapcodec) {
/* 16 */     super(fluidtype, immutablemap, mapcodec);
/* 17 */     this.isEmpty = fluidtype.b();
/*    */   }
/*    */   
/*    */   public FluidType getType() {
/* 21 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean isSource() {
/* 25 */     return getType().c(this);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 29 */     return this.isEmpty;
/*    */   }
/*    */   
/*    */   public float getHeight(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 33 */     return getType().a(this, iblockaccess, blockposition);
/*    */   }
/*    */   
/*    */   public float d() {
/* 37 */     return getType().a(this);
/*    */   }
/*    */   
/*    */   public int e() {
/* 41 */     return getType().d(this);
/*    */   }
/*    */   
/*    */   public void a(World world, BlockPosition blockposition) {
/* 45 */     getType().a(world, blockposition, this);
/*    */   }
/*    */   
/*    */   public boolean f() {
/* 49 */     return getType().j();
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, Random random) {
/* 53 */     getType().b(world, blockposition, this, random);
/*    */   }
/*    */   
/*    */   public Vec3D c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 57 */     return getType().a(iblockaccess, blockposition, this);
/*    */   }
/*    */   
/*    */   public IBlockData getBlockData() {
/* 61 */     return getType().b(this);
/*    */   }
/*    */   
/*    */   public boolean a(Tag<FluidType> tag) {
/* 65 */     return getType().a(tag);
/*    */   }
/*    */   
/*    */   public float i() {
/* 69 */     return getType().c();
/*    */   }
/*    */   
/*    */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection) {
/* 73 */     return getType().a(this, iblockaccess, blockposition, fluidtype, enumdirection);
/*    */   }
/*    */   
/*    */   public VoxelShape d(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 77 */     return getType().b(this, iblockaccess, blockposition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Fluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */