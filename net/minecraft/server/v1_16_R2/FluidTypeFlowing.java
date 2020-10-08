/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2BooleanMap;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2BooleanOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockFromToEvent;
/*     */ import org.bukkit.event.block.FluidLevelChangeEvent;
/*     */ 
/*     */ public abstract class FluidTypeFlowing
/*     */   extends FluidType
/*     */ {
/*  24 */   public static final BlockStateBoolean FALLING = BlockProperties.i; private static final ThreadLocal<Object2ByteLinkedOpenHashMap<Block.a>> e;
/*  25 */   public static final BlockStateInteger LEVEL = BlockProperties.at; static {
/*  26 */     e = ThreadLocal.withInitial(() -> {
/*     */           Object2ByteLinkedOpenHashMap<Block.a> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<Block.a>(200) { protected void rehash(int i) {} }
/*     */             ;
/*     */           object2bytelinkedopenhashmap.defaultReturnValue(127);
/*     */           return object2bytelinkedopenhashmap;
/*     */         });
/*     */   }
/*     */   
/*  34 */   private final Map<Fluid, VoxelShape> f = Maps.newIdentityHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<FluidType, Fluid> blockstatelist_a) {
/*  40 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { FALLING });
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D a(IBlockAccess iblockaccess, BlockPosition blockposition, Fluid fluid) {
/*  45 */     double d0 = 0.0D;
/*  46 */     double d1 = 0.0D;
/*  47 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*  48 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/*  50 */     while (iterator.hasNext()) {
/*  51 */       EnumDirection enumdirection = iterator.next();
/*     */       
/*  53 */       blockposition_mutableblockposition.a(blockposition, enumdirection);
/*  54 */       Fluid fluid1 = iblockaccess.getFluid(blockposition_mutableblockposition);
/*     */       
/*  56 */       if (g(fluid1)) {
/*  57 */         float f = fluid1.d();
/*  58 */         float f1 = 0.0F;
/*     */         
/*  60 */         if (f == 0.0F) {
/*  61 */           if (!iblockaccess.getType(blockposition_mutableblockposition).getMaterial().isSolid()) {
/*  62 */             BlockPosition blockposition1 = blockposition_mutableblockposition.down();
/*  63 */             Fluid fluid2 = iblockaccess.getFluid(blockposition1);
/*     */             
/*  65 */             if (g(fluid2)) {
/*  66 */               f = fluid2.d();
/*  67 */               if (f > 0.0F) {
/*  68 */                 f1 = fluid.d() - f - 0.8888889F;
/*     */               }
/*     */             } 
/*     */           } 
/*  72 */         } else if (f > 0.0F) {
/*  73 */           f1 = fluid.d() - f;
/*     */         } 
/*     */         
/*  76 */         if (f1 != 0.0F) {
/*  77 */           d0 += (enumdirection.getAdjacentX() * f1);
/*  78 */           d1 += (enumdirection.getAdjacentZ() * f1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     Vec3D vec3d = new Vec3D(d0, 0.0D, d1);
/*     */     
/*  85 */     if (((Boolean)fluid.get(FALLING)).booleanValue()) {
/*  86 */       Iterator<EnumDirection> iterator1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/*  88 */       while (iterator1.hasNext()) {
/*  89 */         EnumDirection enumdirection1 = iterator1.next();
/*     */         
/*  91 */         blockposition_mutableblockposition.a(blockposition, enumdirection1);
/*  92 */         if (a(iblockaccess, blockposition_mutableblockposition, enumdirection1) || a(iblockaccess, blockposition_mutableblockposition.up(), enumdirection1)) {
/*  93 */           vec3d = vec3d.d().add(0.0D, -6.0D, 0.0D);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  99 */     return vec3d.d();
/*     */   }
/*     */   
/*     */   private boolean g(Fluid fluid) {
/* 103 */     return (fluid.isEmpty() || fluid.getType().a(this));
/*     */   }
/*     */   
/*     */   protected boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 107 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/* 108 */     Fluid fluid = iblockaccess.getFluid(blockposition);
/*     */     
/* 110 */     return fluid.getType().a(this) ? false : ((enumdirection == EnumDirection.UP) ? true : ((iblockdata.getMaterial() == Material.ICE) ? false : iblockdata.d(iblockaccess, blockposition, enumdirection)));
/*     */   }
/*     */   
/*     */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition, Fluid fluid) {
/* 114 */     if (!fluid.isEmpty()) {
/* 115 */       IBlockData iblockdata = generatoraccess.getType(blockposition);
/* 116 */       BlockPosition blockposition1 = blockposition.down();
/* 117 */       IBlockData iblockdata1 = generatoraccess.getType(blockposition1);
/* 118 */       Fluid fluid1 = a(generatoraccess, blockposition1, iblockdata1);
/*     */       
/* 120 */       if (a(generatoraccess, blockposition, iblockdata, EnumDirection.DOWN, blockposition1, iblockdata1, generatoraccess.getFluid(blockposition1), fluid1.getType())) {
/*     */         
/* 122 */         CraftBlock craftBlock = CraftBlock.at(generatoraccess, blockposition);
/* 123 */         BlockFromToEvent event = new BlockFromToEvent((Block)craftBlock, BlockFace.DOWN);
/* 124 */         generatoraccess.getMinecraftWorld().getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 126 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/* 130 */         a(generatoraccess, blockposition1, iblockdata1, EnumDirection.DOWN, fluid1);
/* 131 */         if (a(generatoraccess, blockposition) >= 3) {
/* 132 */           a(generatoraccess, blockposition, fluid, iblockdata);
/*     */         }
/* 134 */       } else if (fluid.isSource() || !a(generatoraccess, fluid1.getType(), blockposition, iblockdata, blockposition1, iblockdata1)) {
/* 135 */         a(generatoraccess, blockposition, fluid, iblockdata);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(GeneratorAccess generatoraccess, BlockPosition blockposition, Fluid fluid, IBlockData iblockdata) {
/* 142 */     int i = fluid.e() - c(generatoraccess);
/*     */     
/* 144 */     if (((Boolean)fluid.get(FALLING)).booleanValue()) {
/* 145 */       i = 7;
/*     */     }
/*     */     
/* 148 */     if (i > 0) {
/* 149 */       Map<EnumDirection, Fluid> map = b(generatoraccess, blockposition, iblockdata);
/* 150 */       Iterator<Map.Entry<EnumDirection, Fluid>> iterator = map.entrySet().iterator();
/*     */       
/* 152 */       while (iterator.hasNext()) {
/* 153 */         Map.Entry<EnumDirection, Fluid> entry = iterator.next();
/* 154 */         EnumDirection enumdirection = entry.getKey();
/* 155 */         Fluid fluid1 = entry.getValue();
/* 156 */         BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 157 */         IBlockData iblockdata1 = generatoraccess.getTypeIfLoaded(blockposition1);
/* 158 */         if (iblockdata1 == null)
/*     */           continue; 
/* 160 */         if (a(generatoraccess, blockposition, iblockdata, enumdirection, blockposition1, iblockdata1, generatoraccess.getFluid(blockposition1), fluid1.getType())) {
/*     */           
/* 162 */           CraftBlock craftBlock = CraftBlock.at(generatoraccess, blockposition);
/* 163 */           BlockFromToEvent event = new BlockFromToEvent((Block)craftBlock, CraftBlock.notchToBlockFace(enumdirection));
/* 164 */           generatoraccess.getMinecraftWorld().getServer().getPluginManager().callEvent((Event)event);
/*     */           
/* 166 */           if (event.isCancelled()) {
/*     */             continue;
/*     */           }
/*     */           
/* 170 */           a(generatoraccess, blockposition1, iblockdata1, enumdirection, fluid1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Fluid a(IWorldReader iworldreader, BlockPosition blockposition, IBlockData iblockdata) {
/* 178 */     int i = 0;
/* 179 */     int j = 0;
/* 180 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 182 */     while (iterator.hasNext()) {
/* 183 */       EnumDirection enumdirection = iterator.next();
/* 184 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 185 */       IBlockData iblockdata1 = iworldreader.getTypeIfLoaded(blockposition1);
/* 186 */       if (iblockdata1 == null)
/* 187 */         continue;  Fluid fluid = iblockdata1.getFluid();
/*     */       
/* 189 */       if (fluid.getType().a(this) && a(enumdirection, iworldreader, blockposition, iblockdata, blockposition1, iblockdata1)) {
/* 190 */         if (fluid.isSource()) {
/* 191 */           j++;
/*     */         }
/*     */         
/* 194 */         i = Math.max(i, fluid.e());
/*     */       } 
/*     */     } 
/*     */     
/* 198 */     if (f() && j >= 2) {
/* 199 */       IBlockData iblockdata2 = iworldreader.getType(blockposition.down());
/* 200 */       Fluid fluid1 = iblockdata2.getFluid();
/*     */       
/* 202 */       if (iblockdata2.getMaterial().isBuildable() || h(fluid1)) {
/* 203 */         return a(false);
/*     */       }
/*     */     } 
/*     */     
/* 207 */     BlockPosition blockposition2 = blockposition.up();
/* 208 */     IBlockData iblockdata3 = iworldreader.getType(blockposition2);
/* 209 */     Fluid fluid2 = iblockdata3.getFluid();
/*     */     
/* 211 */     if (!fluid2.isEmpty() && fluid2.getType().a(this) && a(EnumDirection.UP, iworldreader, blockposition, iblockdata, blockposition2, iblockdata3)) {
/* 212 */       return a(8, true);
/*     */     }
/* 214 */     int k = i - c(iworldreader);
/*     */     
/* 216 */     return (k <= 0) ? FluidTypes.EMPTY.h() : a(k, false);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(EnumDirection enumdirection, IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, BlockPosition blockposition1, IBlockData iblockdata1) {
/*     */     Object2ByteLinkedOpenHashMap object2bytelinkedopenhashmap;
/*     */     Block.a block_a;
/* 223 */     if (!iblockdata.getBlock().o() && !iblockdata1.getBlock().o()) {
/* 224 */       object2bytelinkedopenhashmap = e.get();
/*     */     } else {
/* 226 */       object2bytelinkedopenhashmap = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 231 */     if (object2bytelinkedopenhashmap != null) {
/* 232 */       block_a = new Block.a(iblockdata, iblockdata1, enumdirection);
/* 233 */       byte b0 = object2bytelinkedopenhashmap.getAndMoveToFirst(block_a);
/*     */       
/* 235 */       if (b0 != Byte.MAX_VALUE) {
/* 236 */         return (b0 != 0);
/*     */       }
/*     */     } else {
/* 239 */       block_a = null;
/*     */     } 
/*     */     
/* 242 */     VoxelShape voxelshape = iblockdata.getCollisionShape(iblockaccess, blockposition);
/* 243 */     VoxelShape voxelshape1 = iblockdata1.getCollisionShape(iblockaccess, blockposition1);
/* 244 */     boolean flag = !VoxelShapes.b(voxelshape, voxelshape1, enumdirection);
/*     */     
/* 246 */     if (object2bytelinkedopenhashmap != null) {
/* 247 */       if (object2bytelinkedopenhashmap.size() == 200) {
/* 248 */         object2bytelinkedopenhashmap.removeLastByte();
/*     */       }
/*     */       
/* 251 */       object2bytelinkedopenhashmap.putAndMoveToFirst(block_a, (byte)(flag ? 1 : 0));
/*     */     } 
/*     */     
/* 254 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid a(int i, boolean flag) {
/* 260 */     return d().h().set(LEVEL, Integer.valueOf(i)).set(FALLING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Fluid a(boolean flag) {
/* 266 */     return e().h().set(FALLING, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection, Fluid fluid) {
/* 272 */     if (iblockdata.getBlock() instanceof IFluidContainer) {
/* 273 */       ((IFluidContainer)iblockdata.getBlock()).place(generatoraccess, blockposition, iblockdata, fluid);
/*     */     } else {
/* 275 */       if (!iblockdata.isAir()) {
/* 276 */         a(generatoraccess, blockposition, iblockdata);
/*     */       }
/*     */       
/* 279 */       generatoraccess.setTypeAndData(blockposition, fluid.getBlockData(), 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static short a(BlockPosition blockposition, BlockPosition blockposition1) {
/* 287 */     int i = blockposition1.getX() - blockposition.getX();
/* 288 */     int j = blockposition1.getZ() - blockposition.getZ();
/*     */     
/* 290 */     return (short)((i + 128 & 0xFF) << 8 | j + 128 & 0xFF);
/*     */   }
/*     */   
/*     */   protected int a(IWorldReader iworldreader, BlockPosition blockposition, int i, EnumDirection enumdirection, IBlockData iblockdata, BlockPosition blockposition1, Short2ObjectMap<Pair<IBlockData, Fluid>> short2objectmap, Short2BooleanMap short2booleanmap) {
/* 294 */     int j = 1000;
/* 295 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 297 */     while (iterator.hasNext()) {
/* 298 */       EnumDirection enumdirection1 = iterator.next();
/*     */       
/* 300 */       if (enumdirection1 != enumdirection) {
/* 301 */         BlockPosition blockposition2 = blockposition.shift(enumdirection1);
/* 302 */         short short0 = a(blockposition1, blockposition2);
/*     */         
/* 304 */         Pair<IBlockData, Fluid> pair = (Pair<IBlockData, Fluid>)short2objectmap.get(short0);
/* 305 */         if (pair == null) {
/* 306 */           IBlockData iblockdatax = iworldreader.getTypeIfLoaded(blockposition2);
/* 307 */           if (iblockdatax == null) {
/*     */             continue;
/*     */           }
/*     */           
/* 311 */           pair = Pair.of(iblockdatax, iblockdatax.getFluid());
/* 312 */           short2objectmap.put(short0, pair);
/*     */         } 
/*     */         
/* 315 */         IBlockData iblockdata1 = (IBlockData)pair.getFirst();
/* 316 */         Fluid fluid = (Fluid)pair.getSecond();
/*     */         
/* 318 */         if (a(iworldreader, d(), blockposition, iblockdata, enumdirection1, blockposition2, iblockdata1, fluid)) {
/* 319 */           boolean flag = short2booleanmap.computeIfAbsent(short0, k -> {
/*     */                 BlockPosition blockposition3 = blockposition2.down();
/*     */                 
/*     */                 IBlockData iblockdata2 = iworldreader.getType(blockposition3);
/*     */                 
/*     */                 return a(iworldreader, d(), blockposition2, iblockdata1, blockposition3, iblockdata2);
/*     */               });
/* 326 */           if (flag) {
/* 327 */             return i;
/*     */           }
/*     */           
/* 330 */           if (i < b(iworldreader)) {
/* 331 */             int k = a(iworldreader, blockposition2, i + 1, enumdirection1.opposite(), iblockdata1, blockposition1, short2objectmap, short2booleanmap);
/*     */             
/* 333 */             if (k < j) {
/* 334 */               j = k;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 341 */     return j;
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, FluidType fluidtype, BlockPosition blockposition, IBlockData iblockdata, BlockPosition blockposition1, IBlockData iblockdata1) {
/* 345 */     return !a(EnumDirection.DOWN, iblockaccess, blockposition, iblockdata, blockposition1, iblockdata1) ? false : (iblockdata1.getFluid().getType().a(this) ? true : a(iblockaccess, blockposition1, iblockdata1, fluidtype));
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, FluidType fluidtype, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection, BlockPosition blockposition1, IBlockData iblockdata1, Fluid fluid) {
/* 349 */     return (!h(fluid) && a(enumdirection, iblockaccess, blockposition, iblockdata, blockposition1, iblockdata1) && a(iblockaccess, blockposition1, iblockdata1, fluidtype));
/*     */   }
/*     */   
/*     */   private boolean h(Fluid fluid) {
/* 353 */     return (fluid.getType().a(this) && fluid.isSource());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 359 */     int i = 0;
/* 360 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 362 */     while (iterator.hasNext()) {
/* 363 */       EnumDirection enumdirection = iterator.next();
/* 364 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 365 */       Fluid fluid = iworldreader.getFluid(blockposition1);
/*     */       
/* 367 */       if (h(fluid)) {
/* 368 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 372 */     return i;
/*     */   }
/*     */   
/*     */   protected Map<EnumDirection, Fluid> b(IWorldReader iworldreader, BlockPosition blockposition, IBlockData iblockdata) {
/* 376 */     int i = 1000;
/* 377 */     Map<EnumDirection, Fluid> map = Maps.newEnumMap(EnumDirection.class);
/* 378 */     Short2ObjectOpenHashMap short2ObjectOpenHashMap = new Short2ObjectOpenHashMap();
/* 379 */     Short2BooleanOpenHashMap short2booleanopenhashmap = new Short2BooleanOpenHashMap();
/* 380 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 382 */     while (iterator.hasNext()) {
/* 383 */       EnumDirection enumdirection = iterator.next();
/* 384 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 385 */       short short0 = a(blockposition, blockposition1);
/*     */       
/* 387 */       Pair pair = (Pair)short2ObjectOpenHashMap.get(short0);
/* 388 */       if (pair == null) {
/* 389 */         IBlockData iblockdatax = iworldreader.getTypeIfLoaded(blockposition1);
/* 390 */         if (iblockdatax == null)
/*     */           continue; 
/* 392 */         pair = Pair.of(iblockdatax, iblockdatax.getFluid());
/* 393 */         short2ObjectOpenHashMap.put(short0, pair);
/*     */       } 
/*     */       
/* 396 */       IBlockData iblockdata1 = (IBlockData)pair.getFirst();
/* 397 */       Fluid fluid = (Fluid)pair.getSecond();
/* 398 */       Fluid fluid1 = a(iworldreader, blockposition1, iblockdata1);
/*     */       
/* 400 */       if (a(iworldreader, fluid1.getType(), blockposition, iblockdata, enumdirection, blockposition1, iblockdata1, fluid)) {
/* 401 */         int j; BlockPosition blockposition2 = blockposition1.down();
/* 402 */         boolean flag = short2booleanopenhashmap.computeIfAbsent(short0, j -> {
/*     */               IBlockData iblockdata2 = iworldreader.getType(blockposition2);
/*     */ 
/*     */               
/*     */               return a(iworldreader, d(), blockposition1, iblockdata1, blockposition2, iblockdata2);
/*     */             });
/*     */         
/* 409 */         if (flag) {
/* 410 */           j = 0;
/*     */         } else {
/* 412 */           j = a(iworldreader, blockposition1, 1, enumdirection.opposite(), iblockdata1, blockposition, (Short2ObjectMap<Pair<IBlockData, Fluid>>)short2ObjectOpenHashMap, (Short2BooleanMap)short2booleanopenhashmap);
/*     */         } 
/*     */         
/* 415 */         if (j < i) {
/* 416 */           map.clear();
/*     */         }
/*     */         
/* 419 */         if (j <= i) {
/* 420 */           map.put(enumdirection, fluid1);
/* 421 */           i = j;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 426 */     return map;
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, FluidType fluidtype) {
/* 430 */     Block block = iblockdata.getBlock();
/*     */     
/* 432 */     if (block instanceof IFluidContainer)
/* 433 */       return ((IFluidContainer)block).canPlace(iblockaccess, blockposition, iblockdata, fluidtype); 
/* 434 */     if (!(block instanceof BlockDoor) && !block.a(TagsBlock.SIGNS) && block != Blocks.LADDER && block != Blocks.SUGAR_CANE && block != Blocks.BUBBLE_COLUMN) {
/* 435 */       Material material = iblockdata.getMaterial();
/*     */       
/* 437 */       return (material != Material.PORTAL && material != Material.STRUCTURE_VOID && material != Material.WATER_PLANT && material != Material.REPLACEABLE_WATER_PLANT) ? (!material.isSolid()) : false;
/*     */     } 
/* 439 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection, BlockPosition blockposition1, IBlockData iblockdata1, Fluid fluid, FluidType fluidtype) {
/* 444 */     return (fluid.a(iblockaccess, blockposition1, fluidtype, enumdirection) && a(enumdirection, iblockaccess, blockposition, iblockdata, blockposition1, iblockdata1) && a(iblockaccess, blockposition1, iblockdata1, fluidtype));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int a(World world, BlockPosition blockposition, Fluid fluid, Fluid fluid1) {
/* 450 */     return a(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, Fluid fluid) {
/* 455 */     if (!fluid.isSource()) {
/* 456 */       Fluid fluid1 = a(world, blockposition, world.getType(blockposition));
/* 457 */       int i = a(world, blockposition, fluid, fluid1);
/*     */       
/* 459 */       if (fluid1.isEmpty()) {
/* 460 */         fluid = fluid1;
/*     */         
/* 462 */         FluidLevelChangeEvent event = CraftEventFactory.callFluidLevelChangeEvent(world, blockposition, Blocks.AIR.getBlockData());
/* 463 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/* 466 */         world.setTypeAndData(blockposition, ((CraftBlockData)event.getNewData()).getState(), 3);
/*     */       }
/* 468 */       else if (!fluid1.equals(fluid)) {
/* 469 */         fluid = fluid1;
/* 470 */         IBlockData iblockdata = fluid1.getBlockData();
/*     */         
/* 472 */         FluidLevelChangeEvent event = CraftEventFactory.callFluidLevelChangeEvent(world, blockposition, iblockdata);
/* 473 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/* 476 */         world.setTypeAndData(blockposition, ((CraftBlockData)event.getNewData()).getState(), 2);
/*     */         
/* 478 */         world.getFluidTickList().a(blockposition, fluid1.getType(), i);
/* 479 */         world.applyPhysics(blockposition, iblockdata.getBlock());
/*     */       } 
/*     */     } 
/*     */     
/* 483 */     a(world, blockposition, fluid);
/*     */   }
/*     */   
/*     */   protected static int e(Fluid fluid) {
/* 487 */     return fluid.isSource() ? 0 : (8 - Math.min(fluid.e(), 8) + (((Boolean)fluid.get(FALLING)).booleanValue() ? 8 : 0));
/*     */   }
/*     */   
/*     */   private static boolean c(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 491 */     return fluid.getType().a(iblockaccess.getFluid(blockposition.up()).getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 496 */     return c(fluid, iblockaccess, blockposition) ? 1.0F : fluid.d();
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(Fluid fluid) {
/* 501 */     return fluid.e() / 9.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 506 */     return (fluid.e() == 9 && c(fluid, iblockaccess, blockposition)) ? VoxelShapes.b() : this.f.computeIfAbsent(fluid, fluid1 -> VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, fluid1.getHeight(iblockaccess, blockposition), 1.0D));
/*     */   }
/*     */   
/*     */   public abstract FluidType d();
/*     */   
/*     */   public abstract FluidType e();
/*     */   
/*     */   protected abstract boolean f();
/*     */   
/*     */   protected abstract void a(GeneratorAccess paramGeneratorAccess, BlockPosition paramBlockPosition, IBlockData paramIBlockData);
/*     */   
/*     */   protected abstract int b(IWorldReader paramIWorldReader);
/*     */   
/*     */   protected abstract int c(IWorldReader paramIWorldReader);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidTypeFlowing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */