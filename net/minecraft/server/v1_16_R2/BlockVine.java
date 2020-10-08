/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockVine
/*     */   extends Block
/*     */ {
/*  15 */   public static final BlockStateBoolean UP = BlockSprawling.e;
/*  16 */   public static final BlockStateBoolean NORTH = BlockSprawling.a;
/*  17 */   public static final BlockStateBoolean EAST = BlockSprawling.b;
/*  18 */   public static final BlockStateBoolean SOUTH = BlockSprawling.c;
/*  19 */   public static final BlockStateBoolean WEST = BlockSprawling.d; public static final Map<EnumDirection, BlockStateBoolean> f;
/*     */   
/*     */   static {
/*  22 */     f = (Map<EnumDirection, BlockStateBoolean>)BlockSprawling.g.entrySet().stream().filter(entry -> (entry.getKey() != EnumDirection.DOWN)).collect(SystemUtils.a());
/*  23 */   } private static final VoxelShape g = Block.a(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  24 */   private static final VoxelShape h = Block.a(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
/*  25 */   private static final VoxelShape i = Block.a(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
/*  26 */   private static final VoxelShape j = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
/*  27 */   private static final VoxelShape k = Block.a(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
/*     */   private final Map<IBlockData, VoxelShape> o;
/*     */   
/*     */   public BlockVine(BlockBase.Info blockbase_info) {
/*  31 */     super(blockbase_info);
/*  32 */     j(((IBlockData)this.blockStateList.getBlockData()).set(UP, Boolean.valueOf(false)).set(NORTH, Boolean.valueOf(false)).set(EAST, Boolean.valueOf(false)).set(SOUTH, Boolean.valueOf(false)).set(WEST, Boolean.valueOf(false)));
/*  33 */     this.o = (Map<IBlockData, VoxelShape>)ImmutableMap.copyOf((Map)this.blockStateList.a().stream().collect(Collectors.toMap(Function.identity(), BlockVine::h)));
/*     */   }
/*     */   
/*     */   private static VoxelShape h(IBlockData iblockdata) {
/*  37 */     VoxelShape voxelshape = VoxelShapes.a();
/*     */     
/*  39 */     if (((Boolean)iblockdata.get(UP)).booleanValue()) {
/*  40 */       voxelshape = g;
/*     */     }
/*     */     
/*  43 */     if (((Boolean)iblockdata.get(NORTH)).booleanValue()) {
/*  44 */       voxelshape = VoxelShapes.a(voxelshape, j);
/*     */     }
/*     */     
/*  47 */     if (((Boolean)iblockdata.get(SOUTH)).booleanValue()) {
/*  48 */       voxelshape = VoxelShapes.a(voxelshape, k);
/*     */     }
/*     */     
/*  51 */     if (((Boolean)iblockdata.get(EAST)).booleanValue()) {
/*  52 */       voxelshape = VoxelShapes.a(voxelshape, i);
/*     */     }
/*     */     
/*  55 */     if (((Boolean)iblockdata.get(WEST)).booleanValue()) {
/*  56 */       voxelshape = VoxelShapes.a(voxelshape, h);
/*     */     }
/*     */     
/*  59 */     return voxelshape;
/*     */   }
/*     */ 
/*     */   
/*     */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/*  64 */     return this.o.get(iblockdata);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/*  69 */     return l(g(iblockdata, iworldreader, blockposition));
/*     */   }
/*     */   
/*     */   private boolean l(IBlockData iblockdata) {
/*  73 */     return (m(iblockdata) > 0);
/*     */   }
/*     */   
/*     */   private int m(IBlockData iblockdata) {
/*  77 */     int i = 0;
/*  78 */     Iterator<BlockStateBoolean> iterator = f.values().iterator();
/*     */     
/*  80 */     while (iterator.hasNext()) {
/*  81 */       BlockStateBoolean blockstateboolean = iterator.next();
/*     */       
/*  83 */       if (((Boolean)iblockdata.get(blockstateboolean)).booleanValue()) {
/*  84 */         i++;
/*     */       }
/*     */     } 
/*     */     
/*  88 */     return i;
/*     */   }
/*     */   
/*     */   private boolean b(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/*  92 */     if (enumdirection == EnumDirection.DOWN) {
/*  93 */       return false;
/*     */     }
/*  95 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */     
/*  97 */     if (a(iblockaccess, blockposition1, enumdirection))
/*  98 */       return true; 
/*  99 */     if (enumdirection.n() == EnumDirection.EnumAxis.Y) {
/* 100 */       return false;
/*     */     }
/* 102 */     BlockStateBoolean blockstateboolean = f.get(enumdirection);
/* 103 */     IBlockData iblockdata = iblockaccess.getType(blockposition.up());
/*     */     
/* 105 */     return (iblockdata.a(this) && ((Boolean)iblockdata.get(blockstateboolean)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
/* 111 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/*     */     
/* 113 */     return Block.a(iblockdata.getCollisionShape(iblockaccess, blockposition), enumdirection.opposite());
/*     */   }
/*     */   
/*     */   private IBlockData g(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 117 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/* 119 */     if (((Boolean)iblockdata.get(UP)).booleanValue()) {
/* 120 */       iblockdata = iblockdata.set(UP, Boolean.valueOf(a(iblockaccess, blockposition1, EnumDirection.DOWN)));
/*     */     }
/*     */     
/* 123 */     IBlockData iblockdata1 = null;
/* 124 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 126 */     while (iterator.hasNext()) {
/* 127 */       EnumDirection enumdirection = iterator.next();
/* 128 */       BlockStateBoolean blockstateboolean = getDirection(enumdirection);
/*     */       
/* 130 */       if (((Boolean)iblockdata.get(blockstateboolean)).booleanValue()) {
/* 131 */         boolean flag = b(iblockaccess, blockposition, enumdirection);
/*     */         
/* 133 */         if (!flag) {
/* 134 */           if (iblockdata1 == null) {
/* 135 */             iblockdata1 = iblockaccess.getType(blockposition1);
/*     */           }
/*     */           
/* 138 */           flag = (iblockdata1.a(this) && ((Boolean)iblockdata1.get(blockstateboolean)).booleanValue());
/*     */         } 
/*     */         
/* 141 */         iblockdata = iblockdata.set(blockstateboolean, Boolean.valueOf(flag));
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return iblockdata;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 150 */     if (enumdirection == EnumDirection.DOWN) {
/* 151 */       return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */     }
/* 153 */     IBlockData iblockdata2 = g(iblockdata, generatoraccess, blockposition);
/*     */     
/* 155 */     return !l(iblockdata2) ? Blocks.AIR.getBlockData() : iblockdata2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 161 */     if (worldserver.random.nextInt(Math.max(1, (int)(100.0F / worldserver.spigotConfig.vineModifier) * 4)) == 0) {
/* 162 */       EnumDirection enumdirection = EnumDirection.a(random);
/* 163 */       BlockPosition blockposition1 = blockposition.up();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 168 */       if (enumdirection.n().d() && !((Boolean)iblockdata.get(getDirection(enumdirection))).booleanValue()) {
/* 169 */         if (a(worldserver, blockposition)) {
/* 170 */           BlockPosition blockposition2 = blockposition.shift(enumdirection);
/* 171 */           IBlockData iblockdata1 = worldserver.getType(blockposition2);
/* 172 */           if (iblockdata1.isAir()) {
/* 173 */             EnumDirection enumdirection1 = enumdirection.g();
/* 174 */             EnumDirection enumdirection2 = enumdirection.h();
/* 175 */             boolean flag = ((Boolean)iblockdata.get(getDirection(enumdirection1))).booleanValue();
/* 176 */             boolean flag1 = ((Boolean)iblockdata.get(getDirection(enumdirection2))).booleanValue();
/* 177 */             BlockPosition blockposition3 = blockposition2.shift(enumdirection1);
/* 178 */             BlockPosition blockposition4 = blockposition2.shift(enumdirection2);
/*     */ 
/*     */             
/* 181 */             BlockPosition source = blockposition;
/*     */             
/* 183 */             if (flag && a(worldserver, blockposition3, enumdirection1)) {
/* 184 */               CraftEventFactory.handleBlockSpreadEvent(worldserver, source, blockposition2, getBlockData().set(getDirection(enumdirection1), Boolean.valueOf(true)), 2);
/* 185 */             } else if (flag1 && a(worldserver, blockposition4, enumdirection2)) {
/* 186 */               CraftEventFactory.handleBlockSpreadEvent(worldserver, source, blockposition2, getBlockData().set(getDirection(enumdirection2), Boolean.valueOf(true)), 2);
/*     */             } else {
/* 188 */               EnumDirection enumdirection3 = enumdirection.opposite();
/*     */               
/* 190 */               if (flag && worldserver.isEmpty(blockposition3) && a(worldserver, blockposition.shift(enumdirection1), enumdirection3)) {
/* 191 */                 CraftEventFactory.handleBlockSpreadEvent(worldserver, source, blockposition3, getBlockData().set(getDirection(enumdirection3), Boolean.valueOf(true)), 2);
/* 192 */               } else if (flag1 && worldserver.isEmpty(blockposition4) && a(worldserver, blockposition.shift(enumdirection2), enumdirection3)) {
/* 193 */                 CraftEventFactory.handleBlockSpreadEvent(worldserver, source, blockposition4, getBlockData().set(getDirection(enumdirection3), Boolean.valueOf(true)), 2);
/* 194 */               } else if (worldserver.random.nextFloat() < 0.05D && a(worldserver, blockposition2.up(), EnumDirection.UP)) {
/* 195 */                 CraftEventFactory.handleBlockSpreadEvent(worldserver, source, blockposition2, getBlockData().set(UP, Boolean.valueOf(true)), 2);
/*     */               }
/*     */             
/*     */             } 
/* 199 */           } else if (a(worldserver, blockposition2, enumdirection)) {
/* 200 */             worldserver.setTypeAndData(blockposition, iblockdata.set(getDirection(enumdirection), Boolean.valueOf(true)), 2);
/*     */           } 
/*     */         } 
/*     */       } else {
/*     */         
/* 205 */         if (enumdirection == EnumDirection.UP && blockposition.getY() < 255) {
/* 206 */           if (b(worldserver, blockposition, enumdirection)) {
/* 207 */             worldserver.setTypeAndData(blockposition, iblockdata.set(UP, Boolean.valueOf(true)), 2);
/*     */             
/*     */             return;
/*     */           } 
/* 211 */           if (worldserver.isEmpty(blockposition1)) {
/* 212 */             if (!a(worldserver, blockposition)) {
/*     */               return;
/*     */             }
/*     */             
/* 216 */             IBlockData iblockdata2 = iblockdata;
/* 217 */             Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */             
/* 219 */             while (iterator.hasNext()) {
/* 220 */               EnumDirection enumdirection1 = iterator.next();
/* 221 */               if (random.nextBoolean() || !a(worldserver, blockposition1.shift(enumdirection1), EnumDirection.UP)) {
/* 222 */                 iblockdata2 = iblockdata2.set(getDirection(enumdirection1), Boolean.valueOf(false));
/*     */               }
/*     */             } 
/*     */             
/* 226 */             if (canSpread(iblockdata2)) {
/* 227 */               CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition1, iblockdata2, 2);
/*     */             }
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         
/* 234 */         if (blockposition.getY() > 0) {
/* 235 */           BlockPosition blockposition2 = blockposition.down();
/* 236 */           IBlockData iblockdata1 = worldserver.getType(blockposition2);
/* 237 */           if (iblockdata1.isAir() || iblockdata1.a(this)) {
/* 238 */             IBlockData iblockdata3 = iblockdata1.isAir() ? getBlockData() : iblockdata1;
/* 239 */             IBlockData iblockdata4 = a(iblockdata, iblockdata3, random);
/*     */             
/* 241 */             if (iblockdata3 != iblockdata4 && canSpread(iblockdata4)) {
/* 242 */               CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition2, iblockdata4, 2);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockData a(IBlockData iblockdata, IBlockData iblockdata1, Random random) {
/* 252 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */     
/* 254 */     while (iterator.hasNext()) {
/* 255 */       EnumDirection enumdirection = iterator.next();
/*     */       
/* 257 */       if (random.nextBoolean()) {
/* 258 */         BlockStateBoolean blockstateboolean = getDirection(enumdirection);
/*     */         
/* 260 */         if (((Boolean)iblockdata.get(blockstateboolean)).booleanValue()) {
/* 261 */           iblockdata1 = iblockdata1.set(blockstateboolean, Boolean.valueOf(true));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   private boolean canSpread(IBlockData iblockdata) {
/* 270 */     return (((Boolean)iblockdata.get(NORTH)).booleanValue() || ((Boolean)iblockdata.get(EAST)).booleanValue() || ((Boolean)iblockdata.get(SOUTH)).booleanValue() || ((Boolean)iblockdata.get(WEST)).booleanValue());
/*     */   }
/*     */   
/*     */   private boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 274 */     boolean flag = true;
/* 275 */     Iterable<BlockPosition> iterable = BlockPosition.b(blockposition.getX() - 4, blockposition.getY() - 1, blockposition.getZ() - 4, blockposition.getX() + 4, blockposition.getY() + 1, blockposition.getZ() + 4);
/* 276 */     int i = 5;
/* 277 */     Iterator<BlockPosition> iterator = iterable.iterator();
/*     */     
/* 279 */     while (iterator.hasNext()) {
/* 280 */       BlockPosition blockposition1 = iterator.next();
/*     */ 
/*     */       
/* 283 */       i--;
/* 284 */       if (iblockaccess.getType(blockposition1).a(this) && i <= 0) {
/* 285 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockData iblockdata, BlockActionContext blockactioncontext) {
/* 295 */     IBlockData iblockdata1 = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition());
/*     */     
/* 297 */     return iblockdata1.a(this) ? ((m(iblockdata1) < f.size())) : super.a(iblockdata, blockactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 303 */     IBlockData iblockdata = blockactioncontext.getWorld().getType(blockactioncontext.getClickPosition());
/* 304 */     boolean flag = iblockdata.a(this);
/* 305 */     IBlockData iblockdata1 = flag ? iblockdata : getBlockData();
/* 306 */     EnumDirection[] aenumdirection = blockactioncontext.e();
/* 307 */     int i = aenumdirection.length;
/*     */     
/* 309 */     for (int j = 0; j < i; j++) {
/* 310 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/* 312 */       if (enumdirection != EnumDirection.DOWN) {
/* 313 */         BlockStateBoolean blockstateboolean = getDirection(enumdirection);
/* 314 */         boolean flag1 = (flag && ((Boolean)iblockdata.get(blockstateboolean)).booleanValue());
/*     */         
/* 316 */         if (!flag1 && b(blockactioncontext.getWorld(), blockactioncontext.getClickPosition(), enumdirection)) {
/* 317 */           return iblockdata1.set(blockstateboolean, Boolean.valueOf(true));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 322 */     return flag ? iblockdata1 : null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 327 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { UP, NORTH, EAST, SOUTH, WEST });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 332 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 334 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(EAST, iblockdata.get(WEST)).set(SOUTH, iblockdata.get(NORTH)).set(WEST, iblockdata.get(EAST));
/*     */       case FRONT_BACK:
/* 336 */         return iblockdata.set(NORTH, iblockdata.get(EAST)).set(EAST, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(WEST)).set(WEST, iblockdata.get(NORTH));
/*     */       case null:
/* 338 */         return iblockdata.set(NORTH, iblockdata.get(WEST)).set(EAST, iblockdata.get(NORTH)).set(SOUTH, iblockdata.get(EAST)).set(WEST, iblockdata.get(SOUTH));
/*     */     } 
/* 340 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 346 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 348 */         return iblockdata.set(NORTH, iblockdata.get(SOUTH)).set(SOUTH, iblockdata.get(NORTH));
/*     */       case FRONT_BACK:
/* 350 */         return iblockdata.set(EAST, iblockdata.get(WEST)).set(WEST, iblockdata.get(EAST));
/*     */     } 
/* 352 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   public static BlockStateBoolean getDirection(EnumDirection enumdirection) {
/* 357 */     return f.get(enumdirection);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockVine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */