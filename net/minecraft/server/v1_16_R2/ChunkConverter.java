/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectSet;
/*     */ import java.util.EnumSet;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class ChunkConverter
/*     */ {
/*  19 */   private static final Logger LOGGER = LogManager.getLogger();
/*  20 */   public static final ChunkConverter a = new ChunkConverter(); public static ChunkConverter getEmptyConverter() { return a; }
/*  21 */    private static final EnumDirection8[] c = EnumDirection8.values();
/*     */   private final EnumSet<EnumDirection8> d;
/*     */   private final int[][] e;
/*  24 */   private static final Map<Block, a> f = new IdentityHashMap<>();
/*  25 */   private static final Set<a> g = Sets.newHashSet();
/*     */   
/*     */   private ChunkConverter() {
/*  28 */     this.d = EnumSet.noneOf(EnumDirection8.class);
/*  29 */     this.e = new int[16][];
/*     */   }
/*     */   
/*     */   public ChunkConverter(NBTTagCompound nbttagcompound) {
/*  33 */     this();
/*  34 */     if (nbttagcompound.hasKeyOfType("Indices", 10)) {
/*  35 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Indices");
/*     */       
/*  37 */       for (int i = 0; i < this.e.length; i++) {
/*  38 */         String s = String.valueOf(i);
/*     */         
/*  40 */         if (nbttagcompound1.hasKeyOfType(s, 11)) {
/*  41 */           this.e[i] = nbttagcompound1.getIntArray(s);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  46 */     int j = nbttagcompound.getInt("Sides");
/*  47 */     EnumDirection8[] aenumdirection8 = EnumDirection8.values();
/*  48 */     int k = aenumdirection8.length;
/*     */     
/*  50 */     for (int l = 0; l < k; l++) {
/*  51 */       EnumDirection8 enumdirection8 = aenumdirection8[l];
/*     */       
/*  53 */       if ((j & 1 << enumdirection8.ordinal()) != 0) {
/*  54 */         this.d.add(enumdirection8);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Chunk chunk) {
/*  61 */     b(chunk);
/*  62 */     EnumDirection8[] aenumdirection8 = c;
/*  63 */     int i = aenumdirection8.length;
/*     */     
/*  65 */     for (int j = 0; j < i; j++) {
/*  66 */       EnumDirection8 enumdirection8 = aenumdirection8[j];
/*     */       
/*  68 */       a(chunk, enumdirection8);
/*     */     } 
/*     */     
/*  71 */     World world = chunk.getWorld();
/*     */     
/*  73 */     g.forEach(chunkconverter_a -> chunkconverter_a.a(world));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(Chunk chunk, EnumDirection8 enumdirection8) {
/*  79 */     World world = chunk.getWorld();
/*     */     
/*  81 */     if ((chunk.p()).d.remove(enumdirection8)) {
/*  82 */       Set<EnumDirection> set = enumdirection8.a();
/*  83 */       boolean flag = false;
/*  84 */       boolean flag1 = true;
/*  85 */       boolean flag2 = set.contains(EnumDirection.EAST);
/*  86 */       boolean flag3 = set.contains(EnumDirection.WEST);
/*  87 */       boolean flag4 = set.contains(EnumDirection.SOUTH);
/*  88 */       boolean flag5 = set.contains(EnumDirection.NORTH);
/*  89 */       boolean flag6 = (set.size() == 1);
/*  90 */       ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/*  91 */       int i = chunkcoordintpair.d() + ((flag6 && (flag5 || flag4)) ? 1 : (flag3 ? 0 : 15));
/*  92 */       int j = chunkcoordintpair.d() + ((flag6 && (flag5 || flag4)) ? 14 : (flag3 ? 0 : 15));
/*  93 */       int k = chunkcoordintpair.e() + ((flag6 && (flag2 || flag3)) ? 1 : (flag5 ? 0 : 15));
/*  94 */       int l = chunkcoordintpair.e() + ((flag6 && (flag2 || flag3)) ? 14 : (flag5 ? 0 : 15));
/*  95 */       EnumDirection[] aenumdirection = EnumDirection.values();
/*  96 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*  97 */       Iterator<BlockPosition> iterator = BlockPosition.b(i, 0, k, j, world.getBuildHeight() - 1, l).iterator();
/*     */       
/*  99 */       while (iterator.hasNext()) {
/* 100 */         BlockPosition blockposition = iterator.next();
/* 101 */         IBlockData iblockdata = world.getType(blockposition);
/* 102 */         IBlockData iblockdata1 = iblockdata;
/* 103 */         EnumDirection[] aenumdirection1 = aenumdirection;
/* 104 */         int i1 = aenumdirection.length;
/*     */         
/* 106 */         for (int j1 = 0; j1 < i1; j1++) {
/* 107 */           EnumDirection enumdirection = aenumdirection1[j1];
/*     */           
/* 109 */           blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 110 */           iblockdata1 = a(iblockdata1, enumdirection, world, blockposition, blockposition_mutableblockposition);
/*     */         } 
/*     */         
/* 113 */         Block.a(iblockdata, iblockdata1, world, blockposition, 18);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 120 */     return ((a)f.getOrDefault(iblockdata.getBlock(), Type.DEFAULT)).a(iblockdata, enumdirection, generatoraccess.getType(blockposition1), generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */   
/*     */   private void b(Chunk chunk) {
/* 124 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 125 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();
/* 126 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/* 127 */     World world = chunk.getWorld();
/*     */     
/*     */     int i;
/*     */     
/* 131 */     for (i = 0; i < 16; i++) {
/* 132 */       ChunkSection chunksection = chunk.getSections()[i];
/* 133 */       int[] aint = this.e[i];
/*     */       
/* 135 */       this.e[i] = null;
/* 136 */       if (chunksection != null && aint != null && aint.length > 0) {
/* 137 */         EnumDirection[] aenumdirection = EnumDirection.values();
/* 138 */         DataPaletteBlock<IBlockData> datapaletteblock = chunksection.getBlocks();
/* 139 */         int[] aint1 = aint;
/* 140 */         int j = aint.length;
/*     */         
/* 142 */         for (int k = 0; k < j; k++) {
/* 143 */           int l = aint1[k];
/* 144 */           int i1 = l & 0xF;
/* 145 */           int j1 = l >> 8 & 0xF;
/* 146 */           int k1 = l >> 4 & 0xF;
/*     */           
/* 148 */           blockposition_mutableblockposition.d(chunkcoordintpair.d() + i1, (i << 4) + j1, chunkcoordintpair.e() + k1);
/* 149 */           IBlockData iblockdata = datapaletteblock.a(l);
/* 150 */           IBlockData iblockdata1 = iblockdata;
/* 151 */           EnumDirection[] aenumdirection1 = aenumdirection;
/* 152 */           int l1 = aenumdirection.length;
/*     */           
/* 154 */           for (int i2 = 0; i2 < l1; i2++) {
/* 155 */             EnumDirection enumdirection = aenumdirection1[i2];
/*     */             
/* 157 */             blockposition_mutableblockposition1.a(blockposition_mutableblockposition, enumdirection);
/* 158 */             if (blockposition_mutableblockposition.getX() >> 4 == chunkcoordintpair.x && blockposition_mutableblockposition.getZ() >> 4 == chunkcoordintpair.z) {
/* 159 */               iblockdata1 = a(iblockdata1, enumdirection, world, blockposition_mutableblockposition, blockposition_mutableblockposition1);
/*     */             }
/*     */           } 
/*     */           
/* 163 */           Block.a(iblockdata, iblockdata1, world, blockposition_mutableblockposition, 18);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 168 */     for (i = 0; i < this.e.length; i++) {
/* 169 */       if (this.e[i] != null) {
/* 170 */         LOGGER.warn("Discarding update data for section {} for chunk ({} {})", Integer.valueOf(i), Integer.valueOf(chunkcoordintpair.x), Integer.valueOf(chunkcoordintpair.z));
/*     */       }
/*     */       
/* 173 */       this.e[i] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 179 */     int[][] aint = this.e;
/* 180 */     int i = aint.length;
/*     */     
/* 182 */     for (int j = 0; j < i; j++) {
/* 183 */       int[] aint1 = aint[j];
/*     */       
/* 185 */       if (aint1 != null) {
/* 186 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 190 */     return this.d.isEmpty();
/*     */   }
/*     */   
/*     */   public NBTTagCompound b() {
/* 194 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 195 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */     
/*     */     int i;
/*     */     
/* 199 */     for (i = 0; i < this.e.length; i++) {
/* 200 */       String s = String.valueOf(i);
/*     */       
/* 202 */       if (this.e[i] != null && (this.e[i]).length != 0) {
/* 203 */         nbttagcompound1.setIntArray(s, this.e[i]);
/*     */       }
/*     */     } 
/*     */     
/* 207 */     if (!nbttagcompound1.isEmpty()) {
/* 208 */       nbttagcompound.set("Indices", nbttagcompound1);
/*     */     }
/*     */     
/* 211 */     i = 0;
/*     */ 
/*     */ 
/*     */     
/* 215 */     for (Iterator<EnumDirection8> iterator = this.d.iterator(); iterator.hasNext(); i |= 1 << enumdirection8.ordinal()) {
/* 216 */       EnumDirection8 enumdirection8 = iterator.next();
/*     */     }
/*     */     
/* 219 */     nbttagcompound.setByte("Sides", (byte)i);
/* 220 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   enum Type
/*     */     implements a {
/* 225 */     BLACKLIST((String)new Block[] { Blocks.OBSERVER, Blocks.NETHER_PORTAL, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.ANVIL, Blocks.CHIPPED_ANVIL, Blocks.DAMAGED_ANVIL, Blocks.DRAGON_EGG, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND, Blocks.OAK_SIGN, Blocks.SPRUCE_SIGN, Blocks.BIRCH_SIGN, Blocks.ACACIA_SIGN, Blocks.JUNGLE_SIGN, Blocks.DARK_OAK_SIGN, Blocks.OAK_WALL_SIGN, Blocks.SPRUCE_WALL_SIGN, Blocks.BIRCH_WALL_SIGN, Blocks.ACACIA_WALL_SIGN, Blocks.JUNGLE_WALL_SIGN, Blocks.DARK_OAK_WALL_SIGN })
/*     */     {
/*     */       public IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 228 */         return iblockdata;
/*     */       }
/*     */     },
/* 231 */     DEFAULT((String)new Block[0])
/*     */     {
/*     */       public IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 234 */         return iblockdata.updateState(enumdirection, generatoraccess.getType(blockposition1), generatoraccess, blockposition, blockposition1);
/*     */       }
/*     */     },
/* 237 */     CHEST((String)new Block[] { Blocks.CHEST, Blocks.TRAPPED_CHEST })
/*     */     {
/*     */       public IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 240 */         if (iblockdata1.a(iblockdata.getBlock()) && enumdirection.n().d() && iblockdata.get(BlockChest.c) == BlockPropertyChestType.SINGLE && iblockdata1.get(BlockChest.c) == BlockPropertyChestType.SINGLE) {
/* 241 */           EnumDirection enumdirection1 = (EnumDirection)iblockdata.get(BlockChest.FACING);
/*     */           
/* 243 */           if (enumdirection.n() != enumdirection1.n() && enumdirection1 == iblockdata1.get(BlockChest.FACING)) {
/* 244 */             BlockPropertyChestType blockpropertychesttype = (enumdirection == enumdirection1.g()) ? BlockPropertyChestType.LEFT : BlockPropertyChestType.RIGHT;
/*     */             
/* 246 */             generatoraccess.setTypeAndData(blockposition1, iblockdata1.set(BlockChest.c, blockpropertychesttype.b()), 18);
/* 247 */             if (enumdirection1 == EnumDirection.NORTH || enumdirection1 == EnumDirection.EAST) {
/* 248 */               TileEntity tileentity = generatoraccess.getTileEntity(blockposition);
/* 249 */               TileEntity tileentity1 = generatoraccess.getTileEntity(blockposition1);
/*     */               
/* 251 */               if (tileentity instanceof TileEntityChest && tileentity1 instanceof TileEntityChest) {
/* 252 */                 TileEntityChest.a((TileEntityChest)tileentity, (TileEntityChest)tileentity1);
/*     */               }
/*     */             } 
/*     */             
/* 256 */             return iblockdata.set(BlockChest.c, blockpropertychesttype);
/*     */           } 
/*     */         } 
/*     */         
/* 260 */         return iblockdata;
/*     */       }
/*     */     },
/* 263 */     LEAVES(true, new Block[] { Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES }) {
/* 264 */       private final ThreadLocal<List<ObjectSet<BlockPosition>>> g = ThreadLocal.withInitial(() -> Lists.newArrayListWithCapacity(7));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 270 */         IBlockData iblockdata2 = iblockdata.updateState(enumdirection, generatoraccess.getType(blockposition1), generatoraccess, blockposition, blockposition1);
/*     */         
/* 272 */         if (iblockdata != iblockdata2) {
/* 273 */           int i = ((Integer)iblockdata2.get(BlockProperties.an)).intValue();
/* 274 */           List<ObjectSet<BlockPosition>> list = this.g.get();
/*     */           
/* 276 */           if (list.isEmpty()) {
/* 277 */             for (int j = 0; j < 7; j++) {
/* 278 */               list.add(new ObjectOpenHashSet());
/*     */             }
/*     */           }
/*     */           
/* 282 */           ((ObjectSet)list.get(i)).add(blockposition.immutableCopy());
/*     */         } 
/*     */         
/* 285 */         return iblockdata;
/*     */       }
/*     */ 
/*     */       
/*     */       public void a(GeneratorAccess generatoraccess) {
/* 290 */         BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 291 */         List<ObjectSet<BlockPosition>> list = this.g.get();
/*     */         
/* 293 */         for (int i = 2; i < list.size(); i++) {
/* 294 */           int j = i - 1;
/* 295 */           ObjectSet<BlockPosition> objectset = list.get(j);
/* 296 */           ObjectSet<BlockPosition> objectset1 = list.get(i);
/* 297 */           ObjectIterator objectiterator = objectset.iterator();
/*     */           
/* 299 */           while (objectiterator.hasNext()) {
/* 300 */             BlockPosition blockposition = (BlockPosition)objectiterator.next();
/* 301 */             IBlockData iblockdata = generatoraccess.getType(blockposition);
/*     */             
/* 303 */             if (((Integer)iblockdata.get(BlockProperties.an)).intValue() >= j) {
/* 304 */               generatoraccess.setTypeAndData(blockposition, iblockdata.set(BlockProperties.an, Integer.valueOf(j)), 18);
/* 305 */               if (i != 7) {
/* 306 */                 EnumDirection[] aenumdirection = f;
/* 307 */                 int k = aenumdirection.length;
/*     */                 
/* 309 */                 for (int l = 0; l < k; l++) {
/* 310 */                   EnumDirection enumdirection = aenumdirection[l];
/*     */                   
/* 312 */                   blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 313 */                   IBlockData iblockdata1 = generatoraccess.getType(blockposition_mutableblockposition);
/*     */                   
/* 315 */                   if (iblockdata1.b(BlockProperties.an) && ((Integer)iblockdata.get(BlockProperties.an)).intValue() > i) {
/* 316 */                     objectset1.add(blockposition_mutableblockposition.immutableCopy());
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 324 */         list.clear();
/*     */       }
/*     */     },
/* 327 */     STEM_BLOCK((String)new Block[] { Blocks.MELON_STEM, Blocks.PUMPKIN_STEM })
/*     */     {
/*     */       public IBlockData a(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 330 */         if (((Integer)iblockdata.get(BlockStem.AGE)).intValue() == 7) {
/* 331 */           BlockStemmed blockstemmed = ((BlockStem)iblockdata.getBlock()).d();
/*     */           
/* 333 */           if (iblockdata1.a(blockstemmed)) {
/* 334 */             return blockstemmed.d().getBlockData().set(BlockFacingHorizontal.FACING, enumdirection);
/*     */           }
/*     */         } 
/*     */         
/* 338 */         return iblockdata;
/*     */       }
/*     */     };
/*     */     
/* 342 */     public static final EnumDirection[] f = EnumDirection.values();
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     Type(boolean flag, Block... ablock) {
/* 349 */       Block[] ablock1 = ablock;
/* 350 */       int i = ablock.length;
/*     */       
/* 352 */       for (int j = 0; j < i; j++) {
/* 353 */         Block block = ablock1[j];
/*     */         
/* 355 */         ChunkConverter.f.put(block, this);
/*     */       } 
/*     */       
/* 358 */       if (flag)
/* 359 */         ChunkConverter.g.add(this); 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     IBlockData a(IBlockData param1IBlockData1, EnumDirection param1EnumDirection, IBlockData param1IBlockData2, GeneratorAccess param1GeneratorAccess, BlockPosition param1BlockPosition1, BlockPosition param1BlockPosition2);
/*     */     
/*     */     default void a(GeneratorAccess generatoraccess) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */