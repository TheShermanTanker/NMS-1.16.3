/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DefinedStructure
/*     */ {
/*  18 */   private final List<a> a = Lists.newArrayList();
/*  19 */   private final List<EntityInfo> b = Lists.newArrayList();
/*     */   private BlockPosition c;
/*     */   private String d;
/*     */   
/*     */   public DefinedStructure() {
/*  24 */     this.c = BlockPosition.ZERO;
/*  25 */     this.d = "?";
/*     */   }
/*     */   
/*     */   public BlockPosition a() {
/*  29 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(String s) {
/*  33 */     this.d = s;
/*     */   }
/*     */   
/*     */   public String b() {
/*  37 */     return this.d;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, BlockPosition blockposition1, boolean flag, @Nullable Block block) {
/*  41 */     if (blockposition1.getX() >= 1 && blockposition1.getY() >= 1 && blockposition1.getZ() >= 1) {
/*  42 */       BlockPosition blockposition2 = blockposition.a(blockposition1).b(-1, -1, -1);
/*  43 */       List<BlockInfo> list = Lists.newArrayList();
/*  44 */       List<BlockInfo> list1 = Lists.newArrayList();
/*  45 */       List<BlockInfo> list2 = Lists.newArrayList();
/*  46 */       BlockPosition blockposition3 = new BlockPosition(Math.min(blockposition.getX(), blockposition2.getX()), Math.min(blockposition.getY(), blockposition2.getY()), Math.min(blockposition.getZ(), blockposition2.getZ()));
/*  47 */       BlockPosition blockposition4 = new BlockPosition(Math.max(blockposition.getX(), blockposition2.getX()), Math.max(blockposition.getY(), blockposition2.getY()), Math.max(blockposition.getZ(), blockposition2.getZ()));
/*     */       
/*  49 */       this.c = blockposition1;
/*  50 */       Iterator<BlockPosition> iterator = BlockPosition.a(blockposition3, blockposition4).iterator();
/*     */       
/*  52 */       while (iterator.hasNext()) {
/*  53 */         BlockPosition blockposition5 = iterator.next();
/*  54 */         BlockPosition blockposition6 = blockposition5.b(blockposition3);
/*  55 */         IBlockData iblockdata = world.getType(blockposition5);
/*     */         
/*  57 */         if (block == null || block != iblockdata.getBlock()) {
/*  58 */           BlockInfo definedstructure_blockinfo; TileEntity tileentity = world.getTileEntity(blockposition5);
/*     */ 
/*     */           
/*  61 */           if (tileentity != null) {
/*  62 */             NBTTagCompound nbttagcompound = tileentity.save(new NBTTagCompound());
/*     */             
/*  64 */             nbttagcompound.remove("x");
/*  65 */             nbttagcompound.remove("y");
/*  66 */             nbttagcompound.remove("z");
/*  67 */             definedstructure_blockinfo = new BlockInfo(blockposition6, iblockdata, nbttagcompound.clone());
/*     */           } else {
/*  69 */             definedstructure_blockinfo = new BlockInfo(blockposition6, iblockdata, (NBTTagCompound)null);
/*     */           } 
/*     */           
/*  72 */           a(definedstructure_blockinfo, list, list1, list2);
/*     */         } 
/*     */       } 
/*     */       
/*  76 */       List<BlockInfo> list3 = a(list, list1, list2);
/*     */       
/*  78 */       this.a.clear();
/*  79 */       this.a.add(new a(list3));
/*  80 */       if (flag) {
/*  81 */         a(world, blockposition3, blockposition4.b(1, 1, 1));
/*     */       } else {
/*  83 */         this.b.clear();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(BlockInfo definedstructure_blockinfo, List<BlockInfo> list, List<BlockInfo> list1, List<BlockInfo> list2) {
/*  90 */     if (definedstructure_blockinfo.c != null) {
/*  91 */       list1.add(definedstructure_blockinfo);
/*  92 */     } else if (!definedstructure_blockinfo.b.getBlock().o() && definedstructure_blockinfo.b.r(BlockAccessAir.INSTANCE, BlockPosition.ZERO)) {
/*  93 */       list.add(definedstructure_blockinfo);
/*     */     } else {
/*  95 */       list2.add(definedstructure_blockinfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<BlockInfo> a(List<BlockInfo> list, List<BlockInfo> list1, List<BlockInfo> list2) {
/* 105 */     Comparator<BlockInfo> comparator = Comparator.<BlockInfo>comparingInt(definedstructure_blockinfo -> definedstructure_blockinfo.a.getY()).thenComparingInt(definedstructure_blockinfo -> definedstructure_blockinfo.a.getX()).thenComparingInt(definedstructure_blockinfo -> definedstructure_blockinfo.a.getZ());
/*     */ 
/*     */ 
/*     */     
/* 109 */     list.sort(comparator);
/* 110 */     list2.sort(comparator);
/* 111 */     list1.sort(comparator);
/* 112 */     List<BlockInfo> list3 = Lists.newArrayList();
/*     */     
/* 114 */     list3.addAll(list);
/* 115 */     list3.addAll(list2);
/* 116 */     list3.addAll(list1);
/* 117 */     return list3;
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, BlockPosition blockposition1) {
/* 121 */     List<Entity> list = world.a(Entity.class, new AxisAlignedBB(blockposition, blockposition1), entity -> !(entity instanceof EntityHuman));
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.b.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     for (Iterator<Entity> iterator = list.iterator(); iterator.hasNext(); this.b.add(new EntityInfo(vec3d, blockposition2, nbttagcompound.clone()))) {
/* 132 */       BlockPosition blockposition2; Entity entity = iterator.next();
/*     */       
/* 134 */       Vec3D vec3d = new Vec3D(entity.locX() - blockposition.getX(), entity.locY() - blockposition.getY(), entity.locZ() - blockposition.getZ());
/* 135 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 136 */       entity.d(nbttagcompound);
/* 137 */       if (entity instanceof EntityPainting) {
/* 138 */         blockposition2 = ((EntityPainting)entity).getBlockPosition().b(blockposition);
/*     */       } else {
/* 140 */         blockposition2 = new BlockPosition(vec3d);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<BlockInfo> a(BlockPosition blockposition, DefinedStructureInfo definedstructureinfo, Block block) {
/* 147 */     return a(blockposition, definedstructureinfo, block, true);
/*     */   }
/*     */   
/*     */   public List<BlockInfo> a(BlockPosition blockposition, DefinedStructureInfo definedstructureinfo, Block block, boolean flag) {
/* 151 */     List<BlockInfo> list = Lists.newArrayList();
/* 152 */     StructureBoundingBox structureboundingbox = definedstructureinfo.h();
/*     */     
/* 154 */     if (this.a.isEmpty()) {
/* 155 */       return Collections.emptyList();
/*     */     }
/* 157 */     Iterator<BlockInfo> iterator = definedstructureinfo.a(this.a, blockposition).a(block).iterator();
/*     */     
/* 159 */     while (iterator.hasNext()) {
/* 160 */       BlockInfo definedstructure_blockinfo = iterator.next();
/* 161 */       BlockPosition blockposition1 = flag ? a(definedstructureinfo, definedstructure_blockinfo.a).a(blockposition) : definedstructure_blockinfo.a;
/*     */       
/* 163 */       if (structureboundingbox == null || structureboundingbox.b(blockposition1)) {
/* 164 */         list.add(new BlockInfo(blockposition1, definedstructure_blockinfo.b.a(definedstructureinfo.d()), definedstructure_blockinfo.c));
/*     */       }
/*     */     } 
/*     */     
/* 168 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(DefinedStructureInfo definedstructureinfo, BlockPosition blockposition, DefinedStructureInfo definedstructureinfo1, BlockPosition blockposition1) {
/* 173 */     BlockPosition blockposition2 = a(definedstructureinfo, blockposition);
/* 174 */     BlockPosition blockposition3 = a(definedstructureinfo1, blockposition1);
/*     */     
/* 176 */     return blockposition2.b(blockposition3);
/*     */   }
/*     */   
/*     */   public static BlockPosition a(DefinedStructureInfo definedstructureinfo, BlockPosition blockposition) {
/* 180 */     return a(blockposition, definedstructureinfo.c(), definedstructureinfo.d(), definedstructureinfo.e());
/*     */   }
/*     */   
/*     */   public void a(WorldAccess worldaccess, BlockPosition blockposition, DefinedStructureInfo definedstructureinfo, Random random) {
/* 184 */     definedstructureinfo.k();
/* 185 */     b(worldaccess, blockposition, definedstructureinfo, random);
/*     */   }
/*     */   
/*     */   public void b(WorldAccess worldaccess, BlockPosition blockposition, DefinedStructureInfo definedstructureinfo, Random random) {
/* 189 */     a(worldaccess, blockposition, blockposition, definedstructureinfo, random, 2);
/*     */   }
/*     */   
/*     */   public boolean a(WorldAccess worldaccess, BlockPosition blockposition, BlockPosition blockposition1, DefinedStructureInfo definedstructureinfo, Random random, int i) {
/* 193 */     if (this.a.isEmpty()) {
/* 194 */       return false;
/*     */     }
/* 196 */     List<BlockInfo> list = definedstructureinfo.a(this.a, blockposition).a();
/*     */     
/* 198 */     if ((!list.isEmpty() || (!definedstructureinfo.g() && !this.b.isEmpty())) && this.c.getX() >= 1 && this.c.getY() >= 1 && this.c.getZ() >= 1) {
/* 199 */       StructureBoundingBox structureboundingbox = definedstructureinfo.h();
/* 200 */       List<BlockPosition> list1 = Lists.newArrayListWithCapacity(definedstructureinfo.l() ? list.size() : 0);
/* 201 */       List<Pair<BlockPosition, NBTTagCompound>> list2 = Lists.newArrayListWithCapacity(list.size());
/* 202 */       int j = Integer.MAX_VALUE;
/* 203 */       int k = Integer.MAX_VALUE;
/* 204 */       int l = Integer.MAX_VALUE;
/* 205 */       int i1 = Integer.MIN_VALUE;
/* 206 */       int j1 = Integer.MIN_VALUE;
/* 207 */       int k1 = Integer.MIN_VALUE;
/* 208 */       List<BlockInfo> list3 = a(worldaccess, blockposition, blockposition1, definedstructureinfo, list);
/* 209 */       Iterator<BlockInfo> iterator = list3.iterator();
/*     */ 
/*     */ 
/*     */       
/* 213 */       while (iterator.hasNext()) {
/* 214 */         BlockInfo definedstructure_blockinfo = iterator.next();
/* 215 */         BlockPosition blockposition2 = definedstructure_blockinfo.a;
/*     */         
/* 217 */         if (structureboundingbox == null || structureboundingbox.b(blockposition2)) {
/* 218 */           Fluid fluid = definedstructureinfo.l() ? worldaccess.getFluid(blockposition2) : null;
/* 219 */           IBlockData iblockdata = definedstructure_blockinfo.b.a(definedstructureinfo.c()).a(definedstructureinfo.d());
/*     */           
/* 221 */           if (definedstructure_blockinfo.c != null) {
/* 222 */             TileEntity tileentity = worldaccess.getTileEntity(blockposition2);
/* 223 */             Clearable.a(tileentity);
/* 224 */             worldaccess.setTypeAndData(blockposition2, Blocks.BARRIER.getBlockData(), 20);
/*     */           } 
/*     */           
/* 227 */           if (worldaccess.setTypeAndData(blockposition2, iblockdata, i)) {
/* 228 */             j = Math.min(j, blockposition2.getX());
/* 229 */             k = Math.min(k, blockposition2.getY());
/* 230 */             l = Math.min(l, blockposition2.getZ());
/* 231 */             i1 = Math.max(i1, blockposition2.getX());
/* 232 */             j1 = Math.max(j1, blockposition2.getY());
/* 233 */             k1 = Math.max(k1, blockposition2.getZ());
/* 234 */             list2.add(Pair.of(blockposition2, definedstructure_blockinfo.c));
/* 235 */             if (definedstructure_blockinfo.c != null) {
/* 236 */               TileEntity tileentity = worldaccess.getTileEntity(blockposition2);
/* 237 */               if (tileentity != null) {
/* 238 */                 definedstructure_blockinfo.c.setInt("x", blockposition2.getX());
/* 239 */                 definedstructure_blockinfo.c.setInt("y", blockposition2.getY());
/* 240 */                 definedstructure_blockinfo.c.setInt("z", blockposition2.getZ());
/* 241 */                 if (tileentity instanceof TileEntityLootable) {
/* 242 */                   definedstructure_blockinfo.c.setLong("LootTableSeed", random.nextLong());
/*     */                 }
/*     */                 
/* 245 */                 tileentity.isLoadingStructure = true;
/* 246 */                 tileentity.load(definedstructure_blockinfo.b, definedstructure_blockinfo.c);
/* 247 */                 tileentity.a(definedstructureinfo.c());
/* 248 */                 tileentity.a(definedstructureinfo.d());
/* 249 */                 tileentity.isLoadingStructure = false;
/*     */               } 
/*     */             } 
/*     */             
/* 253 */             if (fluid != null && iblockdata.getBlock() instanceof IFluidContainer) {
/* 254 */               ((IFluidContainer)iblockdata.getBlock()).place(worldaccess, blockposition2, iblockdata, fluid);
/* 255 */               if (!fluid.isSource()) {
/* 256 */                 list1.add(blockposition2);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 263 */       boolean flag = true;
/* 264 */       EnumDirection[] aenumdirection = { EnumDirection.UP, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 270 */       while (flag && !list1.isEmpty()) {
/* 271 */         flag = false;
/* 272 */         Iterator<BlockPosition> iterator1 = list1.iterator();
/*     */         
/* 274 */         while (iterator1.hasNext()) {
/* 275 */           BlockPosition blockposition4 = iterator1.next();
/*     */           
/* 277 */           BlockPosition blockposition3 = blockposition4;
/* 278 */           Fluid fluid1 = worldaccess.getFluid(blockposition4);
/*     */           
/* 280 */           for (int l1 = 0; l1 < aenumdirection.length && !fluid1.isSource(); l1++) {
/* 281 */             BlockPosition blockposition5 = blockposition3.shift(aenumdirection[l1]);
/* 282 */             Fluid fluid2 = worldaccess.getFluid(blockposition5);
/*     */             
/* 284 */             if (fluid2.getHeight(worldaccess, blockposition5) > fluid1.getHeight(worldaccess, blockposition3) || (fluid2.isSource() && !fluid1.isSource())) {
/* 285 */               fluid1 = fluid2;
/* 286 */               blockposition3 = blockposition5;
/*     */             } 
/*     */           } 
/*     */           
/* 290 */           if (fluid1.isSource()) {
/* 291 */             IBlockData iblockdata1 = worldaccess.getType(blockposition4);
/* 292 */             Block block = iblockdata1.getBlock();
/*     */             
/* 294 */             if (block instanceof IFluidContainer) {
/* 295 */               ((IFluidContainer)block).place(worldaccess, blockposition4, iblockdata1, fluid1);
/* 296 */               flag = true;
/* 297 */               iterator1.remove();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 303 */       if (j <= i1) {
/* 304 */         if (!definedstructureinfo.i()) {
/* 305 */           VoxelShapeBitSet voxelshapebitset = new VoxelShapeBitSet(i1 - j + 1, j1 - k + 1, k1 - l + 1);
/* 306 */           int i2 = j;
/* 307 */           int j2 = k;
/* 308 */           int k2 = l;
/* 309 */           Iterator<Pair<BlockPosition, NBTTagCompound>> iterator2 = list2.iterator();
/*     */           
/* 311 */           while (iterator2.hasNext()) {
/* 312 */             Pair<BlockPosition, NBTTagCompound> pair = iterator2.next();
/* 313 */             BlockPosition blockposition6 = (BlockPosition)pair.getFirst();
/*     */             
/* 315 */             voxelshapebitset.a(blockposition6.getX() - i2, blockposition6.getY() - j2, blockposition6.getZ() - k2, true, true);
/*     */           } 
/*     */           
/* 318 */           a(worldaccess, i, voxelshapebitset, i2, j2, k2);
/*     */         } 
/*     */         
/* 321 */         Iterator<Pair<BlockPosition, NBTTagCompound>> iterator1 = list2.iterator();
/*     */         
/* 323 */         while (iterator1.hasNext()) {
/* 324 */           Pair<BlockPosition, NBTTagCompound> pair1 = iterator1.next();
/*     */           
/* 326 */           BlockPosition blockposition3 = (BlockPosition)pair1.getFirst();
/* 327 */           if (!definedstructureinfo.i()) {
/* 328 */             IBlockData iblockdata2 = worldaccess.getType(blockposition3);
/*     */             
/* 330 */             IBlockData iblockdata1 = Block.b(iblockdata2, worldaccess, blockposition3);
/* 331 */             if (iblockdata2 != iblockdata1) {
/* 332 */               worldaccess.setTypeAndData(blockposition3, iblockdata1, i & 0xFFFFFFFE | 0x10);
/*     */             }
/*     */             
/* 335 */             worldaccess.update(blockposition3, iblockdata1.getBlock());
/*     */           } 
/*     */           
/* 338 */           if (pair1.getSecond() != null) {
/* 339 */             TileEntity tileentity = worldaccess.getTileEntity(blockposition3);
/* 340 */             if (tileentity != null) {
/* 341 */               tileentity.update();
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 347 */       if (!definedstructureinfo.g()) {
/* 348 */         a(worldaccess, blockposition, definedstructureinfo.c(), definedstructureinfo.d(), definedstructureinfo.e(), structureboundingbox, definedstructureinfo.m());
/*     */       }
/*     */       
/* 351 */       return true;
/*     */     } 
/* 353 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(GeneratorAccess generatoraccess, int i, VoxelShapeDiscrete voxelshapediscrete, int j, int k, int l) {
/* 359 */     voxelshapediscrete.a((enumdirection, i1, j1, k1) -> {
/*     */           BlockPosition blockposition = new BlockPosition(j + i1, k + j1, l + k1);
/*     */           BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */           IBlockData iblockdata = generatoraccess.getType(blockposition);
/*     */           IBlockData iblockdata1 = generatoraccess.getType(blockposition1);
/*     */           IBlockData iblockdata2 = iblockdata.updateState(enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */           if (iblockdata != iblockdata2) {
/*     */             generatoraccess.setTypeAndData(blockposition, iblockdata2, i & 0xFFFFFFFE);
/*     */           }
/*     */           IBlockData iblockdata3 = iblockdata1.updateState(enumdirection.opposite(), iblockdata2, generatoraccess, blockposition1, blockposition);
/*     */           if (iblockdata1 != iblockdata3) {
/*     */             generatoraccess.setTypeAndData(blockposition1, iblockdata3, i & 0xFFFFFFFE);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<BlockInfo> a(GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1, DefinedStructureInfo definedstructureinfo, List<BlockInfo> list) {
/* 380 */     List<BlockInfo> list1 = Lists.newArrayList();
/* 381 */     Iterator<BlockInfo> iterator = list.iterator();
/*     */     
/* 383 */     while (iterator.hasNext()) {
/* 384 */       BlockInfo definedstructure_blockinfo = iterator.next();
/* 385 */       BlockPosition blockposition2 = a(definedstructureinfo, definedstructure_blockinfo.a).a(blockposition);
/* 386 */       BlockInfo definedstructure_blockinfo1 = new BlockInfo(blockposition2, definedstructure_blockinfo.b, (definedstructure_blockinfo.c != null) ? definedstructure_blockinfo.c.clone() : null);
/*     */       
/* 388 */       for (Iterator<DefinedStructureProcessor> iterator1 = definedstructureinfo.j().iterator(); definedstructure_blockinfo1 != null && iterator1.hasNext(); definedstructure_blockinfo1 = ((DefinedStructureProcessor)iterator1.next()).a(generatoraccess, blockposition, blockposition1, definedstructure_blockinfo, definedstructure_blockinfo1, definedstructureinfo));
/*     */ 
/*     */ 
/*     */       
/* 392 */       if (definedstructure_blockinfo1 != null) {
/* 393 */         list1.add(definedstructure_blockinfo1);
/*     */       }
/*     */     } 
/*     */     
/* 397 */     return list1;
/*     */   }
/*     */   
/*     */   private void a(WorldAccess worldaccess, BlockPosition blockposition, EnumBlockMirror enumblockmirror, EnumBlockRotation enumblockrotation, BlockPosition blockposition1, @Nullable StructureBoundingBox structureboundingbox, boolean flag) {
/* 401 */     Iterator<EntityInfo> iterator = this.b.iterator();
/*     */     
/* 403 */     while (iterator.hasNext()) {
/* 404 */       EntityInfo definedstructure_entityinfo = iterator.next();
/* 405 */       BlockPosition blockposition2 = a(definedstructure_entityinfo.b, enumblockmirror, enumblockrotation, blockposition1).a(blockposition);
/*     */       
/* 407 */       if (structureboundingbox == null || structureboundingbox.b(blockposition2)) {
/* 408 */         NBTTagCompound nbttagcompound = definedstructure_entityinfo.c.clone();
/* 409 */         Vec3D vec3d = a(definedstructure_entityinfo.a, enumblockmirror, enumblockrotation, blockposition1);
/* 410 */         Vec3D vec3d1 = vec3d.add(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 411 */         NBTTagList nbttaglist = new NBTTagList();
/*     */         
/* 413 */         nbttaglist.add(NBTTagDouble.a(vec3d1.x));
/* 414 */         nbttaglist.add(NBTTagDouble.a(vec3d1.y));
/* 415 */         nbttaglist.add(NBTTagDouble.a(vec3d1.z));
/* 416 */         nbttagcompound.set("Pos", nbttaglist);
/* 417 */         nbttagcompound.remove("UUID");
/* 418 */         a(worldaccess, nbttagcompound).ifPresent(entity -> {
/*     */               float f = entity.a(enumblockmirror);
/*     */               f += entity.yaw - entity.a(enumblockrotation);
/*     */               entity.setPositionRotation(vec3d1.x, vec3d1.y, vec3d1.z, f, entity.pitch);
/*     */               if (flag && entity instanceof EntityInsentient) {
/*     */                 ((EntityInsentient)entity).prepare(worldaccess, worldaccess.getDamageScaler(new BlockPosition(vec3d1)), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, nbttagcompound);
/*     */               }
/*     */               worldaccess.addAllEntities(entity);
/*     */             });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Optional<Entity> a(WorldAccess worldaccess, NBTTagCompound nbttagcompound) {
/* 437 */     return EntityTypes.a(nbttagcompound, worldaccess.getMinecraftWorld());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockPosition a(EnumBlockRotation enumblockrotation) {
/* 445 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/*     */       case FRONT_BACK:
/* 448 */         return new BlockPosition(this.c.getZ(), this.c.getY(), this.c.getX());
/*     */     } 
/* 450 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockPosition a(BlockPosition blockposition, EnumBlockMirror enumblockmirror, EnumBlockRotation enumblockrotation, BlockPosition blockposition1) {
/* 455 */     int i = blockposition.getX();
/* 456 */     int j = blockposition.getY();
/* 457 */     int k = blockposition.getZ();
/* 458 */     boolean flag = true;
/*     */     
/* 460 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 462 */         k = -k;
/*     */         break;
/*     */       case FRONT_BACK:
/* 465 */         i = -i;
/*     */         break;
/*     */       default:
/* 468 */         flag = false;
/*     */         break;
/*     */     } 
/* 471 */     int l = blockposition1.getX();
/* 472 */     int i1 = blockposition1.getZ();
/*     */     
/* 474 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 476 */         return new BlockPosition(l - i1 + k, j, l + i1 - i);
/*     */       case FRONT_BACK:
/* 478 */         return new BlockPosition(l + i1 - k, j, i1 - l + i);
/*     */       case NONE:
/* 480 */         return new BlockPosition(l + l - i, j, i1 + i1 - k);
/*     */     } 
/* 482 */     return flag ? new BlockPosition(i, j, k) : blockposition;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Vec3D a(Vec3D vec3d, EnumBlockMirror enumblockmirror, EnumBlockRotation enumblockrotation, BlockPosition blockposition) {
/* 487 */     double d0 = vec3d.x;
/* 488 */     double d1 = vec3d.y;
/* 489 */     double d2 = vec3d.z;
/* 490 */     boolean flag = true;
/*     */     
/* 492 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 494 */         d2 = 1.0D - d2;
/*     */         break;
/*     */       case FRONT_BACK:
/* 497 */         d0 = 1.0D - d0;
/*     */         break;
/*     */       default:
/* 500 */         flag = false;
/*     */         break;
/*     */     } 
/* 503 */     int i = blockposition.getX();
/* 504 */     int j = blockposition.getZ();
/*     */     
/* 506 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 508 */         return new Vec3D((i - j) + d2, d1, (i + j + 1) - d0);
/*     */       case FRONT_BACK:
/* 510 */         return new Vec3D((i + j + 1) - d2, d1, (j - i) + d0);
/*     */       case NONE:
/* 512 */         return new Vec3D((i + i + 1) - d0, d1, (j + j + 1) - d2);
/*     */     } 
/* 514 */     return flag ? new Vec3D(d0, d1, d2) : vec3d;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a(BlockPosition blockposition, EnumBlockMirror enumblockmirror, EnumBlockRotation enumblockrotation) {
/* 519 */     return a(blockposition, enumblockmirror, enumblockrotation, a().getX(), a().getZ());
/*     */   }
/*     */   
/*     */   public static BlockPosition a(BlockPosition blockposition, EnumBlockMirror enumblockmirror, EnumBlockRotation enumblockrotation, int i, int j) {
/* 523 */     i--;
/* 524 */     j--;
/* 525 */     int k = (enumblockmirror == EnumBlockMirror.FRONT_BACK) ? i : 0;
/* 526 */     int l = (enumblockmirror == EnumBlockMirror.LEFT_RIGHT) ? j : 0;
/* 527 */     BlockPosition blockposition1 = blockposition;
/*     */     
/* 529 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 531 */         blockposition1 = blockposition.b(l, 0, i - k);
/*     */         break;
/*     */       case FRONT_BACK:
/* 534 */         blockposition1 = blockposition.b(j - l, 0, k);
/*     */         break;
/*     */       case NONE:
/* 537 */         blockposition1 = blockposition.b(i - k, 0, j - l);
/*     */         break;
/*     */       case null:
/* 540 */         blockposition1 = blockposition.b(k, 0, l);
/*     */         break;
/*     */     } 
/* 543 */     return blockposition1;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox b(DefinedStructureInfo definedstructureinfo, BlockPosition blockposition) {
/* 547 */     return a(blockposition, definedstructureinfo.d(), definedstructureinfo.e(), definedstructureinfo.c());
/*     */   }
/*     */   
/*     */   public StructureBoundingBox a(BlockPosition blockposition, EnumBlockRotation enumblockrotation, BlockPosition blockposition1, EnumBlockMirror enumblockmirror) {
/* 551 */     BlockPosition blockposition2 = a(enumblockrotation);
/* 552 */     int i = blockposition1.getX();
/* 553 */     int j = blockposition1.getZ();
/* 554 */     int k = blockposition2.getX() - 1;
/* 555 */     int l = blockposition2.getY() - 1;
/* 556 */     int i1 = blockposition2.getZ() - 1;
/* 557 */     StructureBoundingBox structureboundingbox = new StructureBoundingBox(0, 0, 0, 0, 0, 0);
/*     */     
/* 559 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 561 */         structureboundingbox = new StructureBoundingBox(i - j, 0, i + j - i1, i - j + k, l, i + j);
/*     */         break;
/*     */       case FRONT_BACK:
/* 564 */         structureboundingbox = new StructureBoundingBox(i + j - k, 0, j - i, i + j, l, j - i + i1);
/*     */         break;
/*     */       case NONE:
/* 567 */         structureboundingbox = new StructureBoundingBox(i + i - k, 0, j + j - i1, i + i, l, j + j);
/*     */         break;
/*     */       case null:
/* 570 */         structureboundingbox = new StructureBoundingBox(0, 0, 0, k, l, i1);
/*     */         break;
/*     */     } 
/* 573 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 575 */         a(enumblockrotation, i1, k, structureboundingbox, EnumDirection.NORTH, EnumDirection.SOUTH);
/*     */         break;
/*     */       case FRONT_BACK:
/* 578 */         a(enumblockrotation, k, i1, structureboundingbox, EnumDirection.WEST, EnumDirection.EAST);
/*     */         break;
/*     */     } 
/*     */     
/* 582 */     structureboundingbox.a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/* 583 */     return structureboundingbox;
/*     */   }
/*     */   
/*     */   private void a(EnumBlockRotation enumblockrotation, int i, int j, StructureBoundingBox structureboundingbox, EnumDirection enumdirection, EnumDirection enumdirection1) {
/* 587 */     BlockPosition blockposition = BlockPosition.ZERO;
/*     */     
/* 589 */     if (enumblockrotation != EnumBlockRotation.CLOCKWISE_90 && enumblockrotation != EnumBlockRotation.COUNTERCLOCKWISE_90) {
/* 590 */       if (enumblockrotation == EnumBlockRotation.CLOCKWISE_180) {
/* 591 */         blockposition = blockposition.shift(enumdirection1, i);
/*     */       } else {
/* 593 */         blockposition = blockposition.shift(enumdirection, i);
/*     */       } 
/*     */     } else {
/* 596 */       blockposition = blockposition.shift(enumblockrotation.a(enumdirection), j);
/*     */     } 
/*     */     
/* 599 */     structureboundingbox.a(blockposition.getX(), 0, blockposition.getZ());
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/* 603 */     if (this.a.isEmpty()) {
/* 604 */       nbttagcompound.set("blocks", new NBTTagList());
/* 605 */       nbttagcompound.set("palette", new NBTTagList());
/*     */     } else {
/* 607 */       List<b> list = Lists.newArrayList();
/* 608 */       b definedstructure_b = new b();
/*     */       
/* 610 */       list.add(definedstructure_b);
/*     */       
/* 612 */       for (int i = 1; i < this.a.size(); i++) {
/* 613 */         list.add(new b());
/*     */       }
/*     */       
/* 616 */       NBTTagList nbttaglist = new NBTTagList();
/* 617 */       List<BlockInfo> list1 = ((a)this.a.get(0)).a();
/*     */       
/* 619 */       for (int j = 0; j < list1.size(); j++) {
/* 620 */         BlockInfo definedstructure_blockinfo = list1.get(j);
/* 621 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */         
/* 623 */         nbttagcompound1.set("pos", a(new int[] { definedstructure_blockinfo.a.getX(), definedstructure_blockinfo.a.getY(), definedstructure_blockinfo.a.getZ() }));
/* 624 */         int k = definedstructure_b.a(definedstructure_blockinfo.b);
/*     */         
/* 626 */         nbttagcompound1.setInt("state", k);
/* 627 */         if (definedstructure_blockinfo.c != null) {
/* 628 */           nbttagcompound1.set("nbt", definedstructure_blockinfo.c);
/*     */         }
/*     */         
/* 631 */         nbttaglist.add(nbttagcompound1);
/*     */         
/* 633 */         for (int l = 1; l < this.a.size(); l++) {
/* 634 */           b definedstructure_b1 = list.get(l);
/*     */           
/* 636 */           definedstructure_b1.a(((BlockInfo)((a)this.a.get(l)).a().get(j)).b, k);
/*     */         } 
/*     */       } 
/*     */       
/* 640 */       nbttagcompound.set("blocks", nbttaglist);
/*     */ 
/*     */ 
/*     */       
/* 644 */       if (list.size() == 1) {
/* 645 */         NBTTagList nbttaglist1 = new NBTTagList();
/* 646 */         Iterator<IBlockData> iterator = definedstructure_b.iterator();
/*     */         
/* 648 */         while (iterator.hasNext()) {
/* 649 */           IBlockData iblockdata = iterator.next();
/*     */           
/* 651 */           nbttaglist1.add(GameProfileSerializer.a(iblockdata));
/*     */         } 
/*     */         
/* 654 */         nbttagcompound.set("palette", nbttaglist1);
/*     */       } else {
/* 656 */         NBTTagList nbttaglist1 = new NBTTagList();
/* 657 */         Iterator<b> iterator = list.iterator();
/*     */         
/* 659 */         while (iterator.hasNext()) {
/* 660 */           b definedstructure_b2 = iterator.next();
/* 661 */           NBTTagList nbttaglist2 = new NBTTagList();
/* 662 */           Iterator<IBlockData> iterator1 = definedstructure_b2.iterator();
/*     */           
/* 664 */           while (iterator1.hasNext()) {
/* 665 */             IBlockData iblockdata1 = iterator1.next();
/*     */             
/* 667 */             nbttaglist2.add(GameProfileSerializer.a(iblockdata1));
/*     */           } 
/*     */           
/* 670 */           nbttaglist1.add(nbttaglist2);
/*     */         } 
/*     */         
/* 673 */         nbttagcompound.set("palettes", nbttaglist1);
/*     */       } 
/*     */     } 
/*     */     
/* 677 */     NBTTagList nbttaglist3 = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 681 */     for (Iterator<EntityInfo> iterator2 = this.b.iterator(); iterator2.hasNext(); nbttaglist3.add(nbttagcompound2)) {
/* 682 */       EntityInfo definedstructure_entityinfo = iterator2.next();
/*     */       
/* 684 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 685 */       nbttagcompound2.set("pos", a(new double[] { definedstructure_entityinfo.a.x, definedstructure_entityinfo.a.y, definedstructure_entityinfo.a.z }));
/* 686 */       nbttagcompound2.set("blockPos", a(new int[] { definedstructure_entityinfo.b.getX(), definedstructure_entityinfo.b.getY(), definedstructure_entityinfo.b.getZ() }));
/* 687 */       if (definedstructure_entityinfo.c != null) {
/* 688 */         nbttagcompound2.set("nbt", definedstructure_entityinfo.c);
/*     */       }
/*     */     } 
/*     */     
/* 692 */     nbttagcompound.set("entities", nbttaglist3);
/* 693 */     nbttagcompound.set("size", a(new int[] { this.c.getX(), this.c.getY(), this.c.getZ() }));
/* 694 */     nbttagcompound.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/* 695 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 699 */     this.a.clear();
/* 700 */     this.b.clear();
/* 701 */     NBTTagList nbttaglist = nbttagcompound.getList("size", 3);
/*     */     
/* 703 */     this.c = new BlockPosition(nbttaglist.e(0), nbttaglist.e(1), nbttaglist.e(2));
/* 704 */     NBTTagList nbttaglist1 = nbttagcompound.getList("blocks", 10);
/*     */ 
/*     */ 
/*     */     
/* 708 */     if (nbttagcompound.hasKeyOfType("palettes", 9)) {
/* 709 */       NBTTagList nBTTagList = nbttagcompound.getList("palettes", 9);
/*     */       
/* 711 */       for (int j = 0; j < nBTTagList.size(); j++) {
/* 712 */         a(nBTTagList.b(j), nbttaglist1);
/*     */       }
/*     */     } else {
/* 715 */       a(nbttagcompound.getList("palette", 10), nbttaglist1);
/*     */     } 
/*     */     
/* 718 */     NBTTagList nbttaglist2 = nbttagcompound.getList("entities", 10);
/*     */     
/* 720 */     for (int i = 0; i < nbttaglist2.size(); i++) {
/* 721 */       NBTTagCompound nbttagcompound1 = nbttaglist2.getCompound(i);
/* 722 */       NBTTagList nbttaglist3 = nbttagcompound1.getList("pos", 6);
/* 723 */       Vec3D vec3d = new Vec3D(nbttaglist3.h(0), nbttaglist3.h(1), nbttaglist3.h(2));
/* 724 */       NBTTagList nbttaglist4 = nbttagcompound1.getList("blockPos", 3);
/* 725 */       BlockPosition blockposition = new BlockPosition(nbttaglist4.e(0), nbttaglist4.e(1), nbttaglist4.e(2));
/*     */       
/* 727 */       if (nbttagcompound1.hasKey("nbt")) {
/* 728 */         NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound("nbt");
/*     */         
/* 730 */         this.b.add(new EntityInfo(vec3d, blockposition, nbttagcompound2));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(NBTTagList nbttaglist, NBTTagList nbttaglist1) {
/* 737 */     b definedstructure_b = new b();
/*     */     
/* 739 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 740 */       definedstructure_b.a(GameProfileSerializer.c(nbttaglist.getCompound(i)), i);
/*     */     }
/*     */     
/* 743 */     List<BlockInfo> list = Lists.newArrayList();
/* 744 */     List<BlockInfo> list1 = Lists.newArrayList();
/* 745 */     List<BlockInfo> list2 = Lists.newArrayList();
/*     */     
/* 747 */     for (int j = 0; j < nbttaglist1.size(); j++) {
/* 748 */       NBTTagCompound nbttagcompound1, nbttagcompound = nbttaglist1.getCompound(j);
/* 749 */       NBTTagList nbttaglist2 = nbttagcompound.getList("pos", 3);
/* 750 */       BlockPosition blockposition = new BlockPosition(nbttaglist2.e(0), nbttaglist2.e(1), nbttaglist2.e(2));
/* 751 */       IBlockData iblockdata = definedstructure_b.a(nbttagcompound.getInt("state"));
/*     */ 
/*     */       
/* 754 */       if (nbttagcompound.hasKey("nbt")) {
/* 755 */         nbttagcompound1 = nbttagcompound.getCompound("nbt");
/*     */       } else {
/* 757 */         nbttagcompound1 = null;
/*     */       } 
/*     */       
/* 760 */       BlockInfo definedstructure_blockinfo = new BlockInfo(blockposition, iblockdata, nbttagcompound1);
/*     */       
/* 762 */       a(definedstructure_blockinfo, list, list1, list2);
/*     */     } 
/*     */     
/* 765 */     List<BlockInfo> list3 = a(list, list1, list2);
/*     */     
/* 767 */     this.a.add(new a(list3));
/*     */   }
/*     */   
/*     */   private NBTTagList a(int... aint) {
/* 771 */     NBTTagList nbttaglist = new NBTTagList();
/* 772 */     int[] aint1 = aint;
/* 773 */     int i = aint.length;
/*     */     
/* 775 */     for (int j = 0; j < i; j++) {
/* 776 */       int k = aint1[j];
/*     */       
/* 778 */       nbttaglist.add(NBTTagInt.a(k));
/*     */     } 
/*     */     
/* 781 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   private NBTTagList a(double... adouble) {
/* 785 */     NBTTagList nbttaglist = new NBTTagList();
/* 786 */     double[] adouble1 = adouble;
/* 787 */     int i = adouble.length;
/*     */     
/* 789 */     for (int j = 0; j < i; j++) {
/* 790 */       double d0 = adouble1[j];
/*     */       
/* 792 */       nbttaglist.add(NBTTagDouble.a(d0));
/*     */     } 
/*     */     
/* 795 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   public static final class a
/*     */   {
/*     */     private final List<DefinedStructure.BlockInfo> a;
/*     */     private final Map<Block, List<DefinedStructure.BlockInfo>> b;
/*     */     
/*     */     private a(List<DefinedStructure.BlockInfo> list) {
/* 804 */       this.b = Maps.newHashMap();
/* 805 */       this.a = list;
/*     */     }
/*     */     
/*     */     public List<DefinedStructure.BlockInfo> a() {
/* 809 */       return this.a;
/*     */     }
/*     */     
/*     */     public List<DefinedStructure.BlockInfo> a(Block block) {
/* 813 */       return this.b.computeIfAbsent(block, block1 -> (List)this.a.stream().filter(()).collect(Collectors.toList()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class EntityInfo
/*     */   {
/*     */     public final Vec3D a;
/*     */     
/*     */     public final BlockPosition b;
/*     */     
/*     */     public final NBTTagCompound c;
/*     */ 
/*     */     
/*     */     public EntityInfo(Vec3D vec3d, BlockPosition blockposition, NBTTagCompound nbttagcompound) {
/* 828 */       this.a = vec3d;
/* 829 */       this.b = blockposition;
/* 830 */       this.c = nbttagcompound;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BlockInfo
/*     */   {
/*     */     public final BlockPosition a;
/*     */     public final IBlockData b;
/*     */     public final NBTTagCompound c;
/*     */     
/*     */     public BlockInfo(BlockPosition blockposition, IBlockData iblockdata, @Nullable NBTTagCompound nbttagcompound) {
/* 841 */       this.a = blockposition;
/* 842 */       this.b = iblockdata;
/* 843 */       this.c = nbttagcompound;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 847 */       return String.format("<StructureBlockInfo | %s | %s | %s>", new Object[] { this.a, this.b, this.c });
/*     */     }
/*     */   }
/*     */   
/*     */   static class b
/*     */     implements Iterable<IBlockData> {
/* 853 */     public static final IBlockData a = Blocks.AIR.getBlockData();
/*     */     private final RegistryBlockID<IBlockData> b;
/*     */     private int c;
/*     */     
/*     */     private b() {
/* 858 */       this.b = new RegistryBlockID<>(16);
/*     */     }
/*     */     
/*     */     public int a(IBlockData iblockdata) {
/* 862 */       int i = this.b.getId(iblockdata);
/*     */       
/* 864 */       if (i == -1) {
/* 865 */         i = this.c++;
/* 866 */         this.b.a(iblockdata, i);
/*     */       } 
/*     */       
/* 869 */       return i;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public IBlockData a(int i) {
/* 874 */       IBlockData iblockdata = this.b.fromId(i);
/*     */       
/* 876 */       return (iblockdata == null) ? a : iblockdata;
/*     */     }
/*     */     
/*     */     public Iterator<IBlockData> iterator() {
/* 880 */       return this.b.iterator();
/*     */     }
/*     */     
/*     */     public void a(IBlockData iblockdata, int i) {
/* 884 */       this.b.a(iblockdata, i);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */