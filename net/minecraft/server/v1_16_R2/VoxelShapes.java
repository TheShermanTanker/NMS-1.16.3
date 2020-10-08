/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.math.DoubleMath;
/*     */ import com.tuinity.tuinity.voxel.AABBVoxelShape;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*     */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public final class VoxelShapes {
/*     */   private static final VoxelShape b;
/*     */   
/*  15 */   static { b = SystemUtils.<VoxelShape>a(() -> {
/*     */           VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(1, 1, 1);
/*     */           voxelshapebitset.a(0, 0, 0, true, true);
/*     */           return new VoxelShapeCube(voxelshapebitset);
/*     */         }); } public static final VoxelShape getFullUnoptimisedCube() {
/*  20 */     return b;
/*  21 */   } public static final VoxelShape a = create(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*  22 */   private static final VoxelShape c = new VoxelShapeArray(new VoxelShapeBitSet(0, 0, 0), (DoubleList)new DoubleArrayList(new double[] { 0.0D }, ), (DoubleList)new DoubleArrayList(new double[] { 0.0D }, ), (DoubleList)new DoubleArrayList(new double[] { 0.0D })); static final VoxelShape getEmptyShape() { return c; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isEmpty(VoxelShape voxelshape) {
/*  27 */     return (voxelshape == getEmptyShape() || voxelshape.isEmpty());
/*     */   }
/*     */   
/*     */   public static final VoxelShape empty() {
/*  31 */     return a();
/*     */   } public static VoxelShape a() {
/*  33 */     return c;
/*     */   }
/*     */   
/*  36 */   static final AABBVoxelShape optimisedFullCube = new AABBVoxelShape(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D));
/*     */ 
/*     */   
/*     */   public static void addBoxesToIfIntersects(VoxelShape shape, AxisAlignedBB aabb, List<AxisAlignedBB> list) {
/*  40 */     if (shape instanceof AABBVoxelShape) {
/*  41 */       AABBVoxelShape shapeCasted = (AABBVoxelShape)shape;
/*  42 */       if (shapeCasted.aabb.voxelShapeIntersect(aabb)) {
/*  43 */         list.add(shapeCasted.aabb);
/*     */       }
/*  45 */     } else if (shape instanceof VoxelShapeArray) {
/*  46 */       VoxelShapeArray shapeCasted = (VoxelShapeArray)shape;
/*     */ 
/*     */       
/*  49 */       double offX = shapeCasted.offsetX;
/*  50 */       double offY = shapeCasted.offsetY;
/*  51 */       double offZ = shapeCasted.offsetZ;
/*     */       
/*  53 */       for (AxisAlignedBB boundingBox : shapeCasted.boundingBoxesRepresentation) {
/*     */         double minX; double minY; double minZ; double maxX; double maxY; double maxZ;
/*  55 */         if (aabb.voxelShapeIntersect(minX = boundingBox.minX + offX, minY = boundingBox.minY + offY, minZ = boundingBox.minZ + offZ, maxX = boundingBox.maxX + offX, maxY = boundingBox.maxY + offY, maxZ = boundingBox.maxZ + offZ))
/*     */         {
/*  57 */           list.add(new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ, false));
/*     */         }
/*     */       } 
/*     */     } else {
/*  61 */       List<AxisAlignedBB> boxes = shape.getBoundingBoxesRepresentation();
/*  62 */       for (int i = 0, len = boxes.size(); i < len; i++) {
/*  63 */         AxisAlignedBB box = boxes.get(i);
/*  64 */         if (box.voxelShapeIntersect(aabb)) {
/*  65 */           list.add(box);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addBoxesTo(VoxelShape shape, List<AxisAlignedBB> list) {
/*  72 */     if (shape instanceof AABBVoxelShape) {
/*  73 */       AABBVoxelShape shapeCasted = (AABBVoxelShape)shape;
/*  74 */       list.add(shapeCasted.aabb);
/*  75 */     } else if (shape instanceof VoxelShapeArray) {
/*  76 */       VoxelShapeArray shapeCasted = (VoxelShapeArray)shape;
/*     */       
/*  78 */       for (AxisAlignedBB boundingBox : shapeCasted.boundingBoxesRepresentation) {
/*  79 */         list.add(boundingBox.offset(shapeCasted.offsetX, shapeCasted.offsetY, shapeCasted.offsetZ));
/*     */       }
/*     */     } else {
/*  82 */       List<AxisAlignedBB> boxes = shape.getBoundingBoxesRepresentation();
/*  83 */       for (int i = 0, len = boxes.size(); i < len; i++) {
/*  84 */         AxisAlignedBB box = boxes.get(i);
/*  85 */         list.add(box);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final VoxelShape fullCube() {
/*  91 */     return b();
/*     */   } public static VoxelShape b() {
/*  93 */     return (VoxelShape)optimisedFullCube;
/*     */   }
/*     */   
/*     */   public static VoxelShape create(double d0, double d1, double d2, double d3, double d4, double d5) {
/*  97 */     return a(new AxisAlignedBB(d0, d1, d2, d3, d4, d5));
/*     */   }
/*     */   public static final VoxelShape of(AxisAlignedBB axisAlignedbb) {
/* 100 */     return a(axisAlignedbb);
/*     */   } public static VoxelShape a(AxisAlignedBB axisalignedbb) {
/* 102 */     int i = a(axisalignedbb.minX, axisalignedbb.maxX);
/* 103 */     int j = a(axisalignedbb.minY, axisalignedbb.maxY);
/* 104 */     int k = a(axisalignedbb.minZ, axisalignedbb.maxZ);
/*     */     
/* 106 */     if (i >= 0 && j >= 0 && k >= 0) {
/* 107 */       if (i == 0 && j == 0 && k == 0) {
/* 108 */         return axisalignedbb.e(0.5D, 0.5D, 0.5D) ? b() : a();
/*     */       }
/* 110 */       int l = 1 << i;
/* 111 */       int i1 = 1 << j;
/* 112 */       int j1 = 1 << k;
/* 113 */       int k1 = (int)Math.round(axisalignedbb.minX * l);
/* 114 */       int l1 = (int)Math.round(axisalignedbb.maxX * l);
/* 115 */       int i2 = (int)Math.round(axisalignedbb.minY * i1);
/* 116 */       int j2 = (int)Math.round(axisalignedbb.maxY * i1);
/* 117 */       int k2 = (int)Math.round(axisalignedbb.minZ * j1);
/* 118 */       int l2 = (int)Math.round(axisalignedbb.maxZ * j1);
/* 119 */       VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(l, i1, j1, k1, i2, k2, l1, j2, l2);
/*     */       long i3;
/* 121 */       for (i3 = k1; i3 < l1; i3++) {
/* 122 */         long j3; for (j3 = i2; j3 < j2; j3++) {
/* 123 */           long k3; for (k3 = k2; k3 < l2; k3++) {
/* 124 */             voxelshapebitset.a((int)i3, (int)j3, (int)k3, false, true);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 129 */       return new VoxelShapeCube(voxelshapebitset);
/*     */     } 
/*     */     
/* 132 */     return (VoxelShape)new AABBVoxelShape(axisalignedbb);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(double d0, double d1) {
/* 137 */     if (d0 >= -1.0E-7D && d1 <= 1.0000001D) {
/* 138 */       for (int i = 0; i <= 3; i++) {
/* 139 */         double d2 = d0 * (1 << i);
/* 140 */         double d3 = d1 * (1 << i);
/* 141 */         boolean flag = (Math.abs(d2 - Math.floor(d2)) < 1.0E-7D);
/* 142 */         boolean flag1 = (Math.abs(d3 - Math.floor(d3)) < 1.0E-7D);
/*     */         
/* 144 */         if (flag && flag1) {
/* 145 */           return i;
/*     */         }
/*     */       } 
/*     */       
/* 149 */       return -1;
/*     */     } 
/* 151 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static long a(int i, int j) {
/* 156 */     return i * (j / IntMath.gcd(i, j));
/*     */   }
/*     */   
/*     */   public static VoxelShape a(VoxelShape voxelshape, VoxelShape voxelshape1) {
/* 160 */     return a(voxelshape, voxelshape1, OperatorBoolean.OR);
/*     */   }
/*     */   
/*     */   public static VoxelShape a(VoxelShape voxelshape, VoxelShape... avoxelshape) {
/* 164 */     return Arrays.<VoxelShape>stream(avoxelshape).reduce(voxelshape, VoxelShapes::a);
/*     */   }
/*     */   
/*     */   public static VoxelShape a(VoxelShape voxelshape, VoxelShape voxelshape1, OperatorBoolean operatorboolean) {
/* 168 */     return b(voxelshape, voxelshape1, operatorboolean).c();
/*     */   }
/*     */   
/*     */   public static VoxelShape b(VoxelShape voxelshape, VoxelShape voxelshape1, OperatorBoolean operatorboolean) {
/* 172 */     if (operatorboolean.apply(false, false))
/* 173 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException()); 
/* 174 */     if (voxelshape == voxelshape1) {
/* 175 */       return operatorboolean.apply(true, true) ? voxelshape : a();
/*     */     }
/* 177 */     boolean flag = operatorboolean.apply(true, false);
/* 178 */     boolean flag1 = operatorboolean.apply(false, true);
/*     */     
/* 180 */     if (voxelshape.isEmpty())
/* 181 */       return flag1 ? voxelshape1 : a(); 
/* 182 */     if (voxelshape1.isEmpty()) {
/* 183 */       return flag ? voxelshape : a();
/*     */     }
/* 185 */     VoxelShapeMerger voxelshapemerger = a(1, voxelshape.a(EnumDirection.EnumAxis.X), voxelshape1.a(EnumDirection.EnumAxis.X), flag, flag1);
/* 186 */     VoxelShapeMerger voxelshapemerger1 = a(voxelshapemerger.a().size() - 1, voxelshape.a(EnumDirection.EnumAxis.Y), voxelshape1.a(EnumDirection.EnumAxis.Y), flag, flag1);
/* 187 */     VoxelShapeMerger voxelshapemerger2 = a((voxelshapemerger.a().size() - 1) * (voxelshapemerger1.a().size() - 1), voxelshape.a(EnumDirection.EnumAxis.Z), voxelshape1.a(EnumDirection.EnumAxis.Z), flag, flag1);
/* 188 */     VoxelShapeBitSet voxelshapebitset = VoxelShapeBitSet.a(voxelshape.a, voxelshape1.a, voxelshapemerger, voxelshapemerger1, voxelshapemerger2, operatorboolean);
/*     */     
/* 190 */     return (voxelshapemerger instanceof VoxelShapeCubeMerger && voxelshapemerger1 instanceof VoxelShapeCubeMerger && voxelshapemerger2 instanceof VoxelShapeCubeMerger) ? new VoxelShapeCube(voxelshapebitset) : new VoxelShapeArray(voxelshapebitset, voxelshapemerger.a(), voxelshapemerger1.a(), voxelshapemerger2.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public static final boolean applyOperation(VoxelShape voxelshape, VoxelShape voxelshape1, OperatorBoolean operatorboolean) {
/* 195 */     return c(voxelshape, voxelshape1, operatorboolean);
/*     */   }
/*     */   public static boolean c(VoxelShape voxelshape, VoxelShape voxelshape1, OperatorBoolean operatorboolean) {
/* 198 */     if (operatorboolean == OperatorBoolean.AND) {
/* 199 */       if (voxelshape instanceof AABBVoxelShape && voxelshape1 instanceof AABBVoxelShape)
/* 200 */         return ((AABBVoxelShape)voxelshape).aabb.voxelShapeIntersect(((AABBVoxelShape)voxelshape1).aabb); 
/* 201 */       if (voxelshape instanceof AABBVoxelShape && voxelshape1 instanceof VoxelShapeArray)
/* 202 */         return ((VoxelShapeArray)voxelshape1).intersects(((AABBVoxelShape)voxelshape).aabb); 
/* 203 */       if (voxelshape1 instanceof AABBVoxelShape && voxelshape instanceof VoxelShapeArray) {
/* 204 */         return ((VoxelShapeArray)voxelshape).intersects(((AABBVoxelShape)voxelshape1).aabb);
/*     */       }
/*     */     } 
/* 207 */     return abstract_c(voxelshape, voxelshape1, operatorboolean);
/*     */   }
/*     */   
/*     */   public static boolean abstract_c(VoxelShape voxelshape, VoxelShape voxelshape1, OperatorBoolean operatorboolean) {
/* 211 */     if (operatorboolean.apply(false, false))
/* 212 */       throw (IllegalArgumentException)SystemUtils.c(new IllegalArgumentException()); 
/* 213 */     if (voxelshape == voxelshape1)
/* 214 */       return operatorboolean.apply(true, true); 
/* 215 */     if (voxelshape.isEmpty())
/* 216 */       return operatorboolean.apply(false, !voxelshape1.isEmpty()); 
/* 217 */     if (voxelshape1.isEmpty()) {
/* 218 */       return operatorboolean.apply(!voxelshape.isEmpty(), false);
/*     */     }
/* 220 */     boolean flag = operatorboolean.apply(true, false);
/* 221 */     boolean flag1 = operatorboolean.apply(false, true);
/* 222 */     EnumDirection.EnumAxis[] aenumdirection_enumaxis = EnumAxisCycle.d;
/* 223 */     int i = aenumdirection_enumaxis.length;
/*     */     
/* 225 */     for (int j = 0; j < i; j++) {
/* 226 */       EnumDirection.EnumAxis enumdirection_enumaxis = aenumdirection_enumaxis[j];
/*     */       
/* 228 */       if (voxelshape.c(enumdirection_enumaxis) < voxelshape1.b(enumdirection_enumaxis) - 1.0E-7D) {
/* 229 */         return (flag || flag1);
/*     */       }
/*     */       
/* 232 */       if (voxelshape1.c(enumdirection_enumaxis) < voxelshape.b(enumdirection_enumaxis) - 1.0E-7D) {
/* 233 */         return (flag || flag1);
/*     */       }
/*     */     } 
/*     */     
/* 237 */     VoxelShapeMerger voxelshapemerger = a(1, voxelshape.a(EnumDirection.EnumAxis.X), voxelshape1.a(EnumDirection.EnumAxis.X), flag, flag1);
/* 238 */     VoxelShapeMerger voxelshapemerger1 = a(voxelshapemerger.a().size() - 1, voxelshape.a(EnumDirection.EnumAxis.Y), voxelshape1.a(EnumDirection.EnumAxis.Y), flag, flag1);
/* 239 */     VoxelShapeMerger voxelshapemerger2 = a((voxelshapemerger.a().size() - 1) * (voxelshapemerger1.a().size() - 1), voxelshape.a(EnumDirection.EnumAxis.Z), voxelshape1.a(EnumDirection.EnumAxis.Z), flag, flag1);
/*     */     
/* 241 */     return a(voxelshapemerger, voxelshapemerger1, voxelshapemerger2, voxelshape.a, voxelshape1.a, operatorboolean);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(VoxelShapeMerger voxelshapemerger, VoxelShapeMerger voxelshapemerger1, VoxelShapeMerger voxelshapemerger2, VoxelShapeDiscrete voxelshapediscrete, VoxelShapeDiscrete voxelshapediscrete1, OperatorBoolean operatorboolean) {
/* 246 */     return !voxelshapemerger.a((i, j, k) -> voxelshapemerger1.a(()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double a(EnumDirection.EnumAxis enumdirection_enumaxis, AxisAlignedBB axisalignedbb, Stream<VoxelShape> stream, double d0) {
/* 256 */     for (Iterator<VoxelShape> iterator = stream.iterator(); iterator.hasNext(); d0 = ((VoxelShape)iterator.next()).a(enumdirection_enumaxis, axisalignedbb, d0)) {
/* 257 */       if (Math.abs(d0) < 1.0E-7D) {
/* 258 */         return 0.0D;
/*     */       }
/*     */     } 
/*     */     
/* 262 */     return d0;
/*     */   }
/*     */   
/*     */   public static double a(EnumDirection.EnumAxis enumdirection_enumaxis, AxisAlignedBB axisalignedbb, IWorldReader iworldreader, double d0, VoxelShapeCollision voxelshapecollision, Stream<VoxelShape> stream) {
/* 266 */     return a(axisalignedbb, iworldreader, d0, voxelshapecollision, EnumAxisCycle.a(enumdirection_enumaxis, EnumDirection.EnumAxis.Z), stream);
/*     */   }
/*     */   
/*     */   private static double a(AxisAlignedBB axisalignedbb, IWorldReader iworldreader, double d0, VoxelShapeCollision voxelshapecollision, EnumAxisCycle enumaxiscycle, Stream<VoxelShape> stream) {
/* 270 */     if (axisalignedbb.b() >= 1.0E-6D && axisalignedbb.c() >= 1.0E-6D && axisalignedbb.d() >= 1.0E-6D) {
/* 271 */       if (Math.abs(d0) < 1.0E-7D) {
/* 272 */         return 0.0D;
/*     */       }
/* 274 */       EnumAxisCycle enumaxiscycle1 = enumaxiscycle.a();
/* 275 */       EnumDirection.EnumAxis enumdirection_enumaxis = enumaxiscycle1.a(EnumDirection.EnumAxis.X);
/* 276 */       EnumDirection.EnumAxis enumdirection_enumaxis1 = enumaxiscycle1.a(EnumDirection.EnumAxis.Y);
/* 277 */       EnumDirection.EnumAxis enumdirection_enumaxis2 = enumaxiscycle1.a(EnumDirection.EnumAxis.Z);
/* 278 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 279 */       int i = MathHelper.floor(axisalignedbb.a(enumdirection_enumaxis) - 1.0E-7D) - 1;
/* 280 */       int j = MathHelper.floor(axisalignedbb.b(enumdirection_enumaxis) + 1.0E-7D) + 1;
/* 281 */       int k = MathHelper.floor(axisalignedbb.a(enumdirection_enumaxis1) - 1.0E-7D) - 1;
/* 282 */       int l = MathHelper.floor(axisalignedbb.b(enumdirection_enumaxis1) + 1.0E-7D) + 1;
/* 283 */       double d1 = axisalignedbb.a(enumdirection_enumaxis2) - 1.0E-7D;
/* 284 */       double d2 = axisalignedbb.b(enumdirection_enumaxis2) + 1.0E-7D;
/* 285 */       boolean flag = (d0 > 0.0D);
/* 286 */       int i1 = flag ? (MathHelper.floor(axisalignedbb.b(enumdirection_enumaxis2) - 1.0E-7D) - 1) : (MathHelper.floor(axisalignedbb.a(enumdirection_enumaxis2) + 1.0E-7D) + 1);
/* 287 */       int j1 = a(d0, d1, d2);
/* 288 */       int k1 = flag ? 1 : -1;
/* 289 */       int l1 = i1;
/*     */ 
/*     */       
/* 292 */       while (flag ? (
/* 293 */         l1 > j1) : (
/*     */ 
/*     */         
/* 296 */         l1 < j1)) {
/*     */ 
/*     */ 
/*     */         
/* 300 */         for (int i2 = i; i2 <= j; i2++) {
/* 301 */           for (int j2 = k; j2 <= l; j2++) {
/* 302 */             int k2 = 0;
/*     */             
/* 304 */             if (i2 == i || i2 == j) {
/* 305 */               k2++;
/*     */             }
/*     */             
/* 308 */             if (j2 == k || j2 == l) {
/* 309 */               k2++;
/*     */             }
/*     */             
/* 312 */             if (l1 == i1 || l1 == j1) {
/* 313 */               k2++;
/*     */             }
/*     */             
/* 316 */             if (k2 < 3) {
/* 317 */               blockposition_mutableblockposition.a(enumaxiscycle1, i2, j2, l1);
/* 318 */               IBlockData iblockdata = iworldreader.getTypeIfLoaded(blockposition_mutableblockposition);
/* 319 */               if (iblockdata == null) return 0.0D;
/*     */               
/* 321 */               if (!iblockdata.isAir() && (k2 != 1 || iblockdata.d()) && (k2 != 2 || iblockdata.a(Blocks.MOVING_PISTON))) {
/* 322 */                 d0 = iblockdata.b(iworldreader, blockposition_mutableblockposition, voxelshapecollision).a(enumdirection_enumaxis2, axisalignedbb.d(-blockposition_mutableblockposition.getX(), -blockposition_mutableblockposition.getY(), -blockposition_mutableblockposition.getZ()), d0);
/* 323 */                 if (Math.abs(d0) < 1.0E-7D) {
/* 324 */                   return 0.0D;
/*     */                 }
/*     */                 
/* 327 */                 j1 = a(d0, d1, d2);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 333 */         l1 += k1;
/*     */       } 
/*     */       
/* 336 */       double[] adouble = { d0 };
/*     */       
/* 338 */       stream.forEach(voxelshape -> adouble[0] = voxelshape.a(enumdirection_enumaxis2, axisalignedbb, adouble[0]));
/*     */ 
/*     */       
/* 341 */       return adouble[0];
/*     */     } 
/*     */     
/* 344 */     return d0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(double d0, double d1, double d2) {
/* 349 */     return (d0 > 0.0D) ? (MathHelper.floor(d2 + d0) + 1) : (MathHelper.floor(d1 + d0) - 1);
/*     */   } public static VoxelShape a(VoxelShape voxelshape, EnumDirection enumdirection) {
/*     */     boolean flag;
/*     */     int i;
/* 353 */     if (voxelshape == b()) {
/* 354 */       return b();
/*     */     }
/* 356 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/*     */ 
/*     */ 
/*     */     
/* 360 */     if (enumdirection.e() == EnumDirection.EnumAxisDirection.POSITIVE) {
/* 361 */       flag = DoubleMath.fuzzyEquals(voxelshape.c(enumdirection_enumaxis), 1.0D, 1.0E-7D);
/* 362 */       i = voxelshape.a.c(enumdirection_enumaxis) - 1;
/*     */     } else {
/* 364 */       flag = DoubleMath.fuzzyEquals(voxelshape.b(enumdirection_enumaxis), 0.0D, 1.0E-7D);
/* 365 */       i = 0;
/*     */     } 
/*     */     
/* 368 */     return !flag ? a() : new VoxelShapeSlice(voxelshape, enumdirection_enumaxis, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(VoxelShape voxelshape, VoxelShape voxelshape1, EnumDirection enumdirection) {
/* 373 */     if (voxelshape != b() && voxelshape1 != b()) {
/* 374 */       EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/* 375 */       EnumDirection.EnumAxisDirection enumdirection_enumaxisdirection = enumdirection.e();
/* 376 */       VoxelShape voxelshape2 = (enumdirection_enumaxisdirection == EnumDirection.EnumAxisDirection.POSITIVE) ? voxelshape : voxelshape1;
/* 377 */       VoxelShape voxelshape3 = (enumdirection_enumaxisdirection == EnumDirection.EnumAxisDirection.POSITIVE) ? voxelshape1 : voxelshape;
/*     */       
/* 379 */       if (!DoubleMath.fuzzyEquals(voxelshape2.c(enumdirection_enumaxis), 1.0D, 1.0E-7D)) {
/* 380 */         voxelshape2 = a();
/*     */       }
/*     */       
/* 383 */       if (!DoubleMath.fuzzyEquals(voxelshape3.b(enumdirection_enumaxis), 0.0D, 1.0E-7D)) {
/* 384 */         voxelshape3 = a();
/*     */       }
/*     */       
/* 387 */       return !c(b(), b(new VoxelShapeSlice(voxelshape2, enumdirection_enumaxis, voxelshape2.a.c(enumdirection_enumaxis) - 1), new VoxelShapeSlice(voxelshape3, enumdirection_enumaxis, 0), OperatorBoolean.OR), OperatorBoolean.ONLY_FIRST);
/*     */     } 
/* 389 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean combinationOccludes(VoxelShape voxelshape, VoxelShape voxelshape1) {
/* 393 */     return b(voxelshape, voxelshape1);
/*     */   } public static boolean b(VoxelShape voxelshape, VoxelShape voxelshape1) {
/* 395 */     return (voxelshape != b() && voxelshape1 != b()) ? ((voxelshape.isEmpty() && voxelshape1.isEmpty()) ? false : (!c(b(), b(voxelshape, voxelshape1, OperatorBoolean.OR), OperatorBoolean.ONLY_FIRST))) : true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @VisibleForTesting
/*     */   private static VoxelShapeMerger a(int i, DoubleList doublelist, DoubleList doublelist1, boolean flag, boolean flag1) {
/* 403 */     if (doublelist.getDouble(0) == Double.NEGATIVE_INFINITY && doublelist.getDouble(doublelist.size() - 1) == Double.POSITIVE_INFINITY) {
/* 404 */       return new VoxelShapeMergerList(doublelist, doublelist1, flag, flag1);
/*     */     }
/*     */     
/* 407 */     return lessCommonMerge(i, doublelist, doublelist1, flag, flag1);
/*     */   }
/*     */   
/*     */   private static VoxelShapeMerger lessCommonMerge(int i, DoubleList doublelist, DoubleList doublelist1, boolean flag, boolean flag1) {
/* 411 */     int j = doublelist.size() - 1;
/* 412 */     int k = doublelist1.size() - 1;
/*     */ 
/*     */     
/* 415 */     if (doublelist instanceof VoxelShapeCubePoint && doublelist1 instanceof VoxelShapeCubePoint) {
/* 416 */       long l = a(j, k);
/*     */       
/* 418 */       if (i * l <= 256L) {
/* 419 */         return new VoxelShapeCubeMerger(j, k);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 424 */     if (j == k && Objects.equals(doublelist, doublelist1)) {
/* 425 */       if (doublelist instanceof VoxelShapeMergerIdentical)
/* 426 */         return (VoxelShapeMerger)doublelist; 
/* 427 */       if (doublelist1 instanceof VoxelShapeMergerIdentical) {
/* 428 */         return (VoxelShapeMerger)doublelist1;
/*     */       }
/* 430 */       return new VoxelShapeMergerIdentical(doublelist);
/* 431 */     }  if (doublelist.getDouble(j) < doublelist1.getDouble(0) - 1.0E-7D)
/* 432 */       return new VoxelShapeMergerDisjoint(doublelist, doublelist1, false); 
/* 433 */     if (doublelist1.getDouble(k) < doublelist.getDouble(0) - 1.0E-7D) {
/* 434 */       return new VoxelShapeMergerDisjoint(doublelist1, doublelist, true);
/*     */     }
/* 436 */     return new VoxelShapeMergerList(doublelist, doublelist1, flag, flag1);
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     void consume(double param1Double1, double param1Double2, double param1Double3, double param1Double4, double param1Double5, double param1Double6);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */