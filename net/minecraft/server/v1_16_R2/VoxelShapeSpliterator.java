/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ public class VoxelShapeSpliterator extends Spliterators.AbstractSpliterator<VoxelShape> {
/*     */   @Nullable
/*     */   private final Entity a;
/*     */   private final AxisAlignedBB b;
/*     */   private final VoxelShapeCollision c;
/*     */   private final CursorPosition d;
/*     */   
/*     */   final Entity getEntity() {
/*  12 */     return this.a;
/*     */   }
/*     */   private final BlockPosition.MutableBlockPosition e; private final VoxelShape f; private final ICollisionAccess g; private boolean h; private final BiPredicate<IBlockData, BlockPosition> i;
/*     */   final BlockPosition.MutableBlockPosition getMutablePos() {
/*  16 */     return this.e;
/*     */   } final ICollisionAccess getCollisionAccess() {
/*  18 */     return this.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShapeSpliterator(ICollisionAccess icollisionaccess, @Nullable Entity entity, AxisAlignedBB axisalignedbb) {
/*  23 */     this(icollisionaccess, entity, axisalignedbb, (iblockdata, blockposition) -> true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public VoxelShapeSpliterator(ICollisionAccess icollisionaccess, @Nullable Entity entity, AxisAlignedBB axisalignedbb, BiPredicate<IBlockData, BlockPosition> bipredicate) {
/*  29 */     super(Long.MAX_VALUE, 1280);
/*  30 */     this.c = (entity == null) ? VoxelShapeCollision.a() : VoxelShapeCollision.a(entity);
/*  31 */     this.e = new BlockPosition.MutableBlockPosition();
/*  32 */     this.f = VoxelShapes.a(axisalignedbb);
/*  33 */     this.g = icollisionaccess;
/*  34 */     this.h = (entity != null);
/*  35 */     this.a = entity;
/*  36 */     this.b = axisalignedbb;
/*  37 */     this.i = bipredicate;
/*  38 */     int i = MathHelper.floor(axisalignedbb.minX - 1.0E-7D) - 1;
/*  39 */     int j = MathHelper.floor(axisalignedbb.maxX + 1.0E-7D) + 1;
/*  40 */     int k = MathHelper.floor(axisalignedbb.minY - 1.0E-7D) - 1;
/*  41 */     int l = MathHelper.floor(axisalignedbb.maxY + 1.0E-7D) + 1;
/*  42 */     int i1 = MathHelper.floor(axisalignedbb.minZ - 1.0E-7D) - 1;
/*  43 */     int j1 = MathHelper.floor(axisalignedbb.maxZ + 1.0E-7D) + 1;
/*     */     
/*  45 */     this.d = new CursorPosition(i, k, i1, j, l, j1);
/*     */   }
/*     */   
/*     */   public boolean tryAdvance(Consumer<? super VoxelShape> consumer) {
/*  49 */     return ((this.h && b(consumer)) || a(consumer));
/*     */   }
/*     */ 
/*     */   
/*     */   boolean a(Consumer<? super VoxelShape> consumer) {
/*  54 */     while (this.d.a()) {
/*  55 */       int i = this.d.b(), x = i;
/*  56 */       int j = this.d.c(), y = j;
/*  57 */       int k = this.d.d(), z = k;
/*  58 */       int l = this.d.e();
/*     */       
/*  60 */       if (l == 3) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/*  65 */       Entity entity = getEntity();
/*  66 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = getMutablePos();
/*  67 */       boolean far = (entity != null && MCUtil.distanceSq(entity.locX(), y, entity.locZ(), x, y, z) > 14.0D);
/*  68 */       blockposition_mutableblockposition.setValues(x, y, z);
/*     */       
/*  70 */       boolean isRegionLimited = getCollisionAccess() instanceof RegionLimitedWorldAccess;
/*     */ 
/*     */       
/*  73 */       IBlockData iblockdata = isRegionLimited ? Blocks.VOID_AIR.getBlockData() : (((!far && entity instanceof EntityPlayer) || (entity != null && entity.collisionLoadChunks)) ? getCollisionAccess().getType(blockposition_mutableblockposition) : getCollisionAccess().getTypeIfLoaded(blockposition_mutableblockposition));
/*     */ 
/*     */       
/*  76 */       if (iblockdata == null) {
/*  77 */         if (!(entity instanceof EntityPlayer) || entity.world.paperConfig.preventMovingIntoUnloadedChunks) {
/*  78 */           VoxelShape voxelshape3 = VoxelShapes.of(far ? entity.getBoundingBox() : new AxisAlignedBB(new BlockPosition(x, y, z)));
/*  79 */           consumer.accept(voxelshape3);
/*  80 */           return true;
/*     */         } 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/*  87 */       if (!this.i.test(iblockdata, this.e) || (l == 1 && !iblockdata.d()) || (l == 2 && !iblockdata.a(Blocks.MOVING_PISTON))) {
/*     */         continue;
/*     */       }
/*     */       
/*  91 */       VoxelShape voxelshape = iblockdata.b(this.g, this.e, this.c);
/*     */       
/*  93 */       if (voxelshape == VoxelShapes.b()) {
/*  94 */         if (!this.b.voxelShapeIntersect(i, j, k, i + 1.0D, j + 1.0D, k + 1.0D)) {
/*     */           continue;
/*     */         }
/*     */         
/*  98 */         consumer.accept(voxelshape.a(i, j, k));
/*  99 */         return true;
/*     */       } 
/*     */       
/* 102 */       VoxelShape voxelshape1 = voxelshape.a(i, j, k);
/*     */       
/* 104 */       if (!VoxelShapes.c(voxelshape1, this.f, OperatorBoolean.AND)) {
/*     */         continue;
/*     */       }
/*     */       
/* 108 */       consumer.accept(voxelshape1);
/* 109 */       return true;
/*     */     } 
/*     */     
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private IBlockAccess a(int i, int j) {
/* 118 */     int k = i >> 4;
/* 119 */     int l = j >> 4;
/*     */     
/* 121 */     return this.g.c(k, l);
/*     */   }
/*     */   
/*     */   boolean b(Consumer<? super VoxelShape> consumer) {
/* 125 */     Objects.requireNonNull(this.a);
/* 126 */     this.h = false;
/* 127 */     WorldBorder worldborder = this.g.getWorldBorder();
/* 128 */     AxisAlignedBB axisalignedbb = this.a.getBoundingBox();
/*     */     
/* 130 */     if (!a(worldborder, axisalignedbb))
/*     */     {
/* 132 */       if (worldborder.isInBounds(axisalignedbb.shrink(1.0E-7D)) && !worldborder.isInBounds(axisalignedbb.grow(1.0E-7D))) {
/* 133 */         consumer.accept(worldborder.asVoxelShape());
/*     */         
/* 135 */         return true;
/*     */       } 
/*     */     }
/*     */     
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean a(VoxelShape voxelshape, AxisAlignedBB axisalignedbb) {
/* 143 */     return VoxelShapes.c(voxelshape, VoxelShapes.a(axisalignedbb.g(1.0E-7D)), OperatorBoolean.AND);
/*     */   }
/*     */   
/*     */   private static boolean b(VoxelShape voxelshape, AxisAlignedBB axisalignedbb) {
/* 147 */     return VoxelShapes.c(voxelshape, VoxelShapes.a(axisalignedbb.shrink(1.0E-7D)), OperatorBoolean.AND);
/*     */   }
/*     */   
/*     */   public static boolean a(WorldBorder worldborder, AxisAlignedBB axisalignedbb) {
/* 151 */     double d0 = MathHelper.floor(worldborder.e());
/* 152 */     double d1 = MathHelper.floor(worldborder.f());
/* 153 */     double d2 = MathHelper.f(worldborder.g());
/* 154 */     double d3 = MathHelper.f(worldborder.h());
/*     */     
/* 156 */     return (axisalignedbb.minX > d0 && axisalignedbb.minX < d2 && axisalignedbb.minZ > d1 && axisalignedbb.minZ < d3 && axisalignedbb.maxX > d0 && axisalignedbb.maxX < d2 && axisalignedbb.maxZ > d1 && axisalignedbb.maxZ < d3);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeSpliterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */