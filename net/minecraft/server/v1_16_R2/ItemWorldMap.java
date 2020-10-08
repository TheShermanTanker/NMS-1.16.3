/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.LinkedHashMultiset;
/*     */ import com.google.common.collect.Multiset;
/*     */ import com.google.common.collect.Multisets;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.MapInitializeEvent;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public class ItemWorldMap
/*     */   extends ItemWorldMapBase
/*     */ {
/*     */   public ItemWorldMap(Item.Info item_info) {
/*  17 */     super(item_info);
/*     */   }
/*     */   
/*     */   public static ItemStack createFilledMapView(World world, int i, int j, byte b0, boolean flag, boolean flag1) {
/*  21 */     ItemStack itemstack = new ItemStack(Items.FILLED_MAP);
/*     */     
/*  23 */     a(itemstack, world, i, j, b0, flag, flag1, world.getDimensionKey());
/*  24 */     return itemstack;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static WorldMap a(ItemStack itemstack, World world) {
/*  29 */     return world.a(a(d(itemstack)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static WorldMap getSavedMap(ItemStack itemstack, World world) {
/*  34 */     WorldMap worldmap = a(itemstack, world);
/*     */     
/*  36 */     if (worldmap == null && world instanceof WorldServer) {
/*  37 */       worldmap = a(itemstack, world, world.getWorldData().a(), world.getWorldData().c(), 3, false, false, world.getDimensionKey());
/*     */     }
/*     */     
/*  40 */     return worldmap;
/*     */   }
/*     */   
/*     */   public static int d(ItemStack itemstack) {
/*  44 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/*  46 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("map", 99)) ? nbttagcompound.getInt("map") : -1;
/*     */   }
/*     */   
/*     */   private static WorldMap a(ItemStack itemstack, World world, int i, int j, int k, boolean flag, boolean flag1, ResourceKey<World> resourcekey) {
/*  50 */     int l = world.getWorldMapCount();
/*  51 */     WorldMap worldmap = new WorldMap(a(l));
/*     */     
/*  53 */     worldmap.a(i, j, k, flag, flag1, resourcekey);
/*  54 */     world.a(worldmap);
/*  55 */     itemstack.getOrCreateTag().setInt("map", l);
/*     */ 
/*     */     
/*  58 */     MapInitializeEvent event = new MapInitializeEvent((MapView)worldmap.mapView);
/*  59 */     Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     
/*  61 */     return worldmap;
/*     */   }
/*     */   
/*     */   public static String a(int i) {
/*  65 */     return "map_" + i;
/*     */   }
/*     */   
/*     */   public void a(World world, Entity entity, WorldMap worldmap) {
/*  69 */     if (world.getDimensionKey() == worldmap.map && entity instanceof EntityHuman) {
/*  70 */       int i = 1 << worldmap.scale;
/*  71 */       int j = worldmap.centerX;
/*  72 */       int k = worldmap.centerZ;
/*  73 */       int l = MathHelper.floor(entity.locX() - j) / i + 64;
/*  74 */       int i1 = MathHelper.floor(entity.locZ() - k) / i + 64;
/*  75 */       int j1 = 128 / i;
/*     */       
/*  77 */       if (world.getDimensionManager().hasCeiling()) {
/*  78 */         j1 /= 2;
/*     */       }
/*     */       
/*  81 */       WorldMap.WorldMapHumanTracker worldmap_worldmaphumantracker = worldmap.a((EntityHuman)entity);
/*     */       
/*  83 */       worldmap_worldmaphumantracker.b++;
/*  84 */       boolean flag = false;
/*     */       
/*  86 */       for (int k1 = l - j1 + 1; k1 < l + j1; k1++) {
/*  87 */         if ((k1 & 0xF) == (worldmap_worldmaphumantracker.b & 0xF) || flag) {
/*  88 */           flag = false;
/*  89 */           double d0 = 0.0D;
/*     */           
/*  91 */           for (int l1 = i1 - j1 - 1; l1 < i1 + j1; l1++) {
/*  92 */             if (k1 >= 0 && l1 >= -1 && k1 < 128 && l1 < 128) {
/*  93 */               int i2 = k1 - l;
/*  94 */               int j2 = l1 - i1;
/*  95 */               boolean flag1 = (i2 * i2 + j2 * j2 > (j1 - 2) * (j1 - 2));
/*  96 */               int k2 = (j / i + k1 - 64) * i;
/*  97 */               int l2 = (k / i + l1 - 64) * i;
/*  98 */               LinkedHashMultiset linkedHashMultiset = LinkedHashMultiset.create();
/*  99 */               Chunk chunk = world.getChunkIfLoaded(new BlockPosition(k2, 0, l2));
/*     */               
/* 101 */               if (chunk != null && !chunk.isEmpty()) {
/* 102 */                 ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/* 103 */                 int i3 = k2 & 0xF;
/* 104 */                 int j3 = l2 & 0xF;
/* 105 */                 int k3 = 0;
/* 106 */                 double d1 = 0.0D;
/*     */                 
/* 108 */                 if (world.getDimensionManager().hasCeiling()) {
/* 109 */                   int l3 = k2 + l2 * 231871;
/*     */                   
/* 111 */                   l3 = l3 * l3 * 31287121 + l3 * 11;
/* 112 */                   if ((l3 >> 20 & 0x1) == 0) {
/* 113 */                     linkedHashMultiset.add(Blocks.DIRT.getBlockData().d(world, BlockPosition.ZERO), 10);
/*     */                   } else {
/* 115 */                     linkedHashMultiset.add(Blocks.STONE.getBlockData().d(world, BlockPosition.ZERO), 100);
/*     */                   } 
/*     */                   
/* 118 */                   d1 = 100.0D;
/*     */                 } else {
/* 120 */                   BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 121 */                   BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();
/*     */                   
/* 123 */                   for (int i4 = 0; i4 < i; i4++) {
/* 124 */                     for (int j4 = 0; j4 < i; j4++) {
/* 125 */                       IBlockData iblockdata; int k4 = chunk.getHighestBlock(HeightMap.Type.WORLD_SURFACE, i4 + i3, j4 + j3) + 1;
/*     */ 
/*     */                       
/* 128 */                       if (k4 > 1) {
/*     */                         do {
/* 130 */                           k4--;
/* 131 */                           blockposition_mutableblockposition.d(chunkcoordintpair.d() + i4 + i3, k4, chunkcoordintpair.e() + j4 + j3);
/* 132 */                           iblockdata = chunk.getType(blockposition_mutableblockposition);
/* 133 */                         } while (iblockdata.d(world, blockposition_mutableblockposition) == MaterialMapColor.b && k4 > 0);
/*     */                         
/* 135 */                         if (k4 > 0 && !iblockdata.getFluid().isEmpty()) {
/* 136 */                           IBlockData iblockdata1; int l4 = k4 - 1;
/*     */                           
/* 138 */                           blockposition_mutableblockposition1.g(blockposition_mutableblockposition);
/*     */ 
/*     */ 
/*     */                           
/*     */                           do {
/* 143 */                             blockposition_mutableblockposition1.p(l4--);
/* 144 */                             iblockdata1 = chunk.getType(blockposition_mutableblockposition1);
/* 145 */                             k3++;
/* 146 */                           } while (l4 > 0 && !iblockdata1.getFluid().isEmpty());
/*     */                           
/* 148 */                           iblockdata = a(world, iblockdata, blockposition_mutableblockposition);
/*     */                         } 
/*     */                       } else {
/* 151 */                         iblockdata = Blocks.BEDROCK.getBlockData();
/*     */                       } 
/*     */                       
/* 154 */                       worldmap.a(world, chunkcoordintpair.d() + i4 + i3, chunkcoordintpair.e() + j4 + j3);
/* 155 */                       d1 += k4 / (i * i);
/* 156 */                       linkedHashMultiset.add(iblockdata.d(world, blockposition_mutableblockposition));
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */                 
/* 161 */                 k3 /= i * i;
/* 162 */                 double d2 = (d1 - d0) * 4.0D / (i + 4) + ((k1 + l1 & 0x1) - 0.5D) * 0.4D;
/* 163 */                 byte b0 = 1;
/*     */                 
/* 165 */                 if (d2 > 0.6D) {
/* 166 */                   b0 = 2;
/*     */                 }
/*     */                 
/* 169 */                 if (d2 < -0.6D) {
/* 170 */                   b0 = 0;
/*     */                 }
/*     */                 
/* 173 */                 MaterialMapColor materialmapcolor = (MaterialMapColor)Iterables.getFirst((Iterable)Multisets.copyHighestCountFirst((Multiset)linkedHashMultiset), MaterialMapColor.b);
/*     */                 
/* 175 */                 if (materialmapcolor == MaterialMapColor.n) {
/* 176 */                   d2 = k3 * 0.1D + (k1 + l1 & 0x1) * 0.2D;
/* 177 */                   b0 = 1;
/* 178 */                   if (d2 < 0.5D) {
/* 179 */                     b0 = 2;
/*     */                   }
/*     */                   
/* 182 */                   if (d2 > 0.9D) {
/* 183 */                     b0 = 0;
/*     */                   }
/*     */                 } 
/*     */                 
/* 187 */                 d0 = d1;
/* 188 */                 if (l1 >= 0 && i2 * i2 + j2 * j2 < j1 * j1 && (!flag1 || (k1 + l1 & 0x1) != 0)) {
/* 189 */                   byte b1 = worldmap.colors[k1 + l1 * 128];
/* 190 */                   byte b2 = (byte)(materialmapcolor.aj * 4 + b0);
/*     */                   
/* 192 */                   if (b1 != b2) {
/* 193 */                     worldmap.colors[k1 + l1 * 128] = b2;
/* 194 */                     worldmap.flagDirty(k1, l1);
/* 195 */                     flag = true;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private IBlockData a(World world, IBlockData iblockdata, BlockPosition blockposition) {
/* 208 */     Fluid fluid = iblockdata.getFluid();
/*     */     
/* 210 */     return (!fluid.isEmpty() && !iblockdata.d(world, blockposition, EnumDirection.UP)) ? fluid.getBlockData() : iblockdata;
/*     */   }
/*     */   
/*     */   private static boolean a(BiomeBase[] abiomebase, int i, int j, int k) {
/* 214 */     return (abiomebase[j * i + k * i * 128 * i].h() >= 0.0F);
/*     */   }
/*     */   
/*     */   public static void applySepiaFilter(WorldServer worldserver, ItemStack itemstack) {
/* 218 */     WorldMap worldmap = getSavedMap(itemstack, worldserver);
/*     */     
/* 220 */     if (worldmap != null && 
/* 221 */       worldserver.getDimensionKey() == worldmap.map) {
/* 222 */       int i = 1 << worldmap.scale;
/* 223 */       int j = worldmap.centerX;
/* 224 */       int k = worldmap.centerZ;
/* 225 */       BiomeBase[] abiomebase = new BiomeBase[128 * i * 128 * i];
/*     */ 
/*     */       
/*     */       int l;
/*     */       
/* 230 */       for (l = 0; l < 128 * i; l++) {
/* 231 */         for (int i1 = 0; i1 < 128 * i; i1++) {
/* 232 */           abiomebase[l * 128 * i + i1] = worldserver.getBiomeBySeed((j / i - 64) * i + i1, 0, (k / i - 64) * i + l);
/*     */         }
/*     */       } 
/*     */       
/* 236 */       for (l = 0; l < 128; l++) {
/* 237 */         for (int i1 = 0; i1 < 128; i1++) {
/* 238 */           if (l > 0 && i1 > 0 && l < 127 && i1 < 127) {
/* 239 */             BiomeBase biomebase = abiomebase[l * i + i1 * i * 128 * i];
/* 240 */             int j1 = 8;
/*     */             
/* 242 */             if (a(abiomebase, i, l - 1, i1 - 1)) {
/* 243 */               j1--;
/*     */             }
/*     */             
/* 246 */             if (a(abiomebase, i, l - 1, i1 + 1)) {
/* 247 */               j1--;
/*     */             }
/*     */             
/* 250 */             if (a(abiomebase, i, l - 1, i1)) {
/* 251 */               j1--;
/*     */             }
/*     */             
/* 254 */             if (a(abiomebase, i, l + 1, i1 - 1)) {
/* 255 */               j1--;
/*     */             }
/*     */             
/* 258 */             if (a(abiomebase, i, l + 1, i1 + 1)) {
/* 259 */               j1--;
/*     */             }
/*     */             
/* 262 */             if (a(abiomebase, i, l + 1, i1)) {
/* 263 */               j1--;
/*     */             }
/*     */             
/* 266 */             if (a(abiomebase, i, l, i1 - 1)) {
/* 267 */               j1--;
/*     */             }
/*     */             
/* 270 */             if (a(abiomebase, i, l, i1 + 1)) {
/* 271 */               j1--;
/*     */             }
/*     */             
/* 274 */             int k1 = 3;
/* 275 */             MaterialMapColor materialmapcolor = MaterialMapColor.b;
/*     */             
/* 277 */             if (biomebase.h() < 0.0F) {
/* 278 */               materialmapcolor = MaterialMapColor.q;
/* 279 */               if (j1 > 7 && i1 % 2 == 0) {
/* 280 */                 k1 = (l + (int)(MathHelper.sin(i1 + 0.0F) * 7.0F)) / 8 % 5;
/* 281 */                 if (k1 == 3) {
/* 282 */                   k1 = 1;
/* 283 */                 } else if (k1 == 4) {
/* 284 */                   k1 = 0;
/*     */                 } 
/* 286 */               } else if (j1 > 7) {
/* 287 */                 materialmapcolor = MaterialMapColor.b;
/* 288 */               } else if (j1 > 5) {
/* 289 */                 k1 = 1;
/* 290 */               } else if (j1 > 3) {
/* 291 */                 k1 = 0;
/* 292 */               } else if (j1 > 1) {
/* 293 */                 k1 = 0;
/*     */               } 
/* 295 */             } else if (j1 > 0) {
/* 296 */               materialmapcolor = MaterialMapColor.B;
/* 297 */               if (j1 > 3) {
/* 298 */                 k1 = 1;
/*     */               } else {
/* 300 */                 k1 = 3;
/*     */               } 
/*     */             } 
/*     */             
/* 304 */             if (materialmapcolor != MaterialMapColor.b) {
/* 305 */               worldmap.colors[l + i1 * 128] = (byte)(materialmapcolor.aj * 4 + k1);
/* 306 */               worldmap.flagDirty(l, i1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {
/* 318 */     if (!world.isClientSide) {
/* 319 */       WorldMap worldmap = getSavedMap(itemstack, world);
/*     */       
/* 321 */       if (worldmap != null) {
/* 322 */         if (entity instanceof EntityHuman) {
/* 323 */           EntityHuman entityhuman = (EntityHuman)entity;
/*     */           
/* 325 */           worldmap.a(entityhuman, itemstack);
/*     */         } 
/*     */         
/* 328 */         if (!worldmap.locked && (flag || (entity instanceof EntityHuman && ((EntityHuman)entity).getItemInOffHand() == itemstack))) {
/* 329 */           a(world, entity, worldmap);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Packet<?> a(ItemStack itemstack, World world, EntityHuman entityhuman) {
/* 339 */     return getSavedMap(itemstack, world).a(itemstack, world, entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(ItemStack itemstack, World world, EntityHuman entityhuman) {
/* 344 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 346 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("map_scale_direction", 99)) {
/* 347 */       a(itemstack, world, nbttagcompound.getInt("map_scale_direction"));
/* 348 */       nbttagcompound.remove("map_scale_direction");
/* 349 */     } else if (nbttagcompound != null && nbttagcompound.hasKeyOfType("map_to_lock", 1) && nbttagcompound.getBoolean("map_to_lock")) {
/* 350 */       a(world, itemstack);
/* 351 */       nbttagcompound.remove("map_to_lock");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(ItemStack itemstack, World world, int i) {
/* 357 */     WorldMap worldmap = getSavedMap(itemstack, world);
/*     */     
/* 359 */     if (worldmap != null) {
/* 360 */       a(itemstack, world, worldmap.centerX, worldmap.centerZ, MathHelper.clamp(worldmap.scale + i, 0, 4), worldmap.track, worldmap.unlimitedTracking, worldmap.map);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, ItemStack itemstack) {
/* 366 */     WorldMap worldmap = getSavedMap(itemstack, world);
/*     */     
/* 368 */     if (worldmap != null) {
/* 369 */       WorldMap worldmap1 = a(itemstack, world, 0, 0, worldmap.scale, worldmap.track, worldmap.unlimitedTracking, worldmap.map);
/*     */       
/* 371 */       worldmap1.a(worldmap);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 378 */     IBlockData iblockdata = itemactioncontext.getWorld().getType(itemactioncontext.getClickPosition());
/*     */     
/* 380 */     if (iblockdata.a(TagsBlock.BANNERS)) {
/* 381 */       if (!(itemactioncontext.getWorld()).isClientSide) {
/* 382 */         WorldMap worldmap = getSavedMap(itemactioncontext.getItemStack(), itemactioncontext.getWorld());
/*     */         
/* 384 */         worldmap.a(itemactioncontext.getWorld(), itemactioncontext.getClickPosition());
/*     */       } 
/*     */       
/* 387 */       return EnumInteractionResult.a((itemactioncontext.getWorld()).isClientSide);
/*     */     } 
/* 389 */     return super.a(itemactioncontext);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemWorldMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */