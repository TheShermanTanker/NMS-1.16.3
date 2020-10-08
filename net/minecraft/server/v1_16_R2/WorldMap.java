/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.map.CraftMapView;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.map.RenderData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.map.MapCursor;
/*     */ import org.bukkit.map.MapRenderer;
/*     */ 
/*     */ public class WorldMap extends PersistentBase {
/*  25 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   public int centerX;
/*     */   public int centerZ;
/*     */   public ResourceKey<World> map;
/*     */   public boolean track;
/*     */   public boolean unlimitedTracking;
/*     */   public byte scale;
/*  32 */   public byte[] colors = new byte[16384];
/*     */   public boolean locked;
/*  34 */   public final List<WorldMapHumanTracker> i = Lists.newArrayList();
/*  35 */   public final Map<EntityHuman, WorldMapHumanTracker> humans = Maps.newHashMap();
/*  36 */   private final Map<String, MapIconBanner> m = Maps.newHashMap();
/*  37 */   public final Map<String, MapIcon> decorations = Maps.newLinkedHashMap();
/*  38 */   private final Map<String, WorldMapFrame> n = Maps.newHashMap();
/*  39 */   private RenderData vanillaRender = new RenderData();
/*     */   
/*     */   public final CraftMapView mapView;
/*     */   
/*     */   private CraftServer server;
/*  44 */   private UUID uniqueId = null;
/*     */ 
/*     */   
/*     */   public WorldMap(String s) {
/*  48 */     super(s);
/*     */     
/*  50 */     this.mapView = new CraftMapView(this);
/*  51 */     this.server = (CraftServer)Bukkit.getServer();
/*  52 */     this.vanillaRender.buffer = this.colors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, int j, int k, boolean flag, boolean flag1, ResourceKey<World> resourcekey) {
/*  57 */     this.scale = (byte)k;
/*  58 */     a(i, j, this.scale);
/*  59 */     this.map = resourcekey;
/*  60 */     this.track = flag;
/*  61 */     this.unlimitedTracking = flag1;
/*  62 */     b();
/*     */   }
/*     */   
/*     */   public void a(double d0, double d1, int i) {
/*  66 */     int j = 128 * (1 << i);
/*  67 */     int k = MathHelper.floor((d0 + 64.0D) / j);
/*  68 */     int l = MathHelper.floor((d1 + 64.0D) / j);
/*     */     
/*  70 */     this.centerX = k * j + j / 2 - 64;
/*  71 */     this.centerZ = l * j + j / 2 - 64;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  76 */     DataResult<ResourceKey<World>> dataresult = DimensionManager.a(new Dynamic(DynamicOpsNBT.a, nbttagcompound.get("dimension")));
/*  77 */     Logger logger = LOGGER;
/*     */     
/*  79 */     logger.getClass();
/*     */     
/*  81 */     Objects.requireNonNull(logger); this.map = dataresult.resultOrPartial(logger::error).orElseGet(() -> {
/*     */           long least = nbttagcompound.getLong("UUIDLeast");
/*     */ 
/*     */           
/*     */           long most = nbttagcompound.getLong("UUIDMost");
/*     */ 
/*     */           
/*     */           if (least != 0L && most != 0L) {
/*     */             this.uniqueId = new UUID(most, least);
/*     */             
/*     */             CraftWorld world = (CraftWorld)this.server.getWorld(this.uniqueId);
/*     */             
/*     */             if (world != null) {
/*     */               return world.getHandle().getDimensionKey();
/*     */             }
/*     */           } 
/*     */           
/*     */           throw new IllegalArgumentException("Invalid map dimension: " + nbttagcompound.get("dimension"));
/*     */         });
/*     */     
/* 101 */     this.centerX = nbttagcompound.getInt("xCenter");
/* 102 */     this.centerZ = nbttagcompound.getInt("zCenter");
/* 103 */     this.scale = (byte)MathHelper.clamp(nbttagcompound.getByte("scale"), 0, 4);
/* 104 */     this.track = (!nbttagcompound.hasKeyOfType("trackingPosition", 1) || nbttagcompound.getBoolean("trackingPosition"));
/* 105 */     this.unlimitedTracking = nbttagcompound.getBoolean("unlimitedTracking");
/* 106 */     this.locked = nbttagcompound.getBoolean("locked");
/* 107 */     this.colors = nbttagcompound.getByteArray("colors");
/* 108 */     if (this.colors.length != 16384) {
/* 109 */       this.colors = new byte[16384];
/*     */     }
/*     */     
/* 112 */     NBTTagList nbttaglist = nbttagcompound.getList("banners", 10);
/*     */     
/* 114 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 115 */       MapIconBanner mapiconbanner = MapIconBanner.a(nbttaglist.getCompound(i));
/*     */       
/* 117 */       this.m.put(mapiconbanner.f(), mapiconbanner);
/* 118 */       a(mapiconbanner.c(), (GeneratorAccess)null, mapiconbanner.f(), mapiconbanner.a().getX(), mapiconbanner.a().getZ(), 180.0D, mapiconbanner.d());
/*     */     } 
/* 120 */     this.vanillaRender.buffer = this.colors;
/*     */     
/* 122 */     NBTTagList nbttaglist1 = nbttagcompound.getList("frames", 10);
/*     */     
/* 124 */     for (int j = 0; j < nbttaglist1.size(); j++) {
/* 125 */       WorldMapFrame worldmapframe = WorldMapFrame.a(nbttaglist1.getCompound(j));
/*     */       
/* 127 */       this.n.put(worldmapframe.e(), worldmapframe);
/* 128 */       a(MapIcon.Type.FRAME, (GeneratorAccess)null, "frame-" + worldmapframe.d(), worldmapframe.b().getX(), worldmapframe.b().getZ(), worldmapframe.c(), (IChatBaseComponent)null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 135 */     DataResult<NBTBase> dataresult = MinecraftKey.a.encodeStart(DynamicOpsNBT.a, this.map.a());
/* 136 */     Logger logger = LOGGER;
/*     */     
/* 138 */     logger.getClass();
/* 139 */     Objects.requireNonNull(logger); dataresult.resultOrPartial(logger::error).ifPresent(nbtbase -> nbttagcompound.set("dimension", nbtbase));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (this.uniqueId == null) {
/* 145 */       for (World world : this.server.getWorlds()) {
/* 146 */         CraftWorld cWorld = (CraftWorld)world;
/* 147 */         if (cWorld.getHandle().getDimensionKey() == this.map) {
/* 148 */           this.uniqueId = cWorld.getUID();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 155 */     if (this.uniqueId != null) {
/* 156 */       nbttagcompound.setLong("UUIDLeast", this.uniqueId.getLeastSignificantBits());
/* 157 */       nbttagcompound.setLong("UUIDMost", this.uniqueId.getMostSignificantBits());
/*     */     } 
/*     */ 
/*     */     
/* 161 */     nbttagcompound.setInt("xCenter", this.centerX);
/* 162 */     nbttagcompound.setInt("zCenter", this.centerZ);
/* 163 */     nbttagcompound.setByte("scale", this.scale);
/* 164 */     nbttagcompound.setByteArray("colors", this.colors);
/* 165 */     nbttagcompound.setBoolean("trackingPosition", this.track);
/* 166 */     nbttagcompound.setBoolean("unlimitedTracking", this.unlimitedTracking);
/* 167 */     nbttagcompound.setBoolean("locked", this.locked);
/* 168 */     NBTTagList nbttaglist = new NBTTagList();
/* 169 */     Iterator<MapIconBanner> iterator = this.m.values().iterator();
/*     */     
/* 171 */     while (iterator.hasNext()) {
/* 172 */       MapIconBanner mapiconbanner = iterator.next();
/*     */       
/* 174 */       nbttaglist.add(mapiconbanner.e());
/*     */     } 
/*     */     
/* 177 */     nbttagcompound.set("banners", nbttaglist);
/* 178 */     NBTTagList nbttaglist1 = new NBTTagList();
/* 179 */     Iterator<WorldMapFrame> iterator1 = this.n.values().iterator();
/*     */     
/* 181 */     while (iterator1.hasNext()) {
/* 182 */       WorldMapFrame worldmapframe = iterator1.next();
/*     */       
/* 184 */       nbttaglist1.add(worldmapframe.a());
/*     */     } 
/*     */     
/* 187 */     nbttagcompound.set("frames", nbttaglist1);
/* 188 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public void a(WorldMap worldmap) {
/* 192 */     this.locked = true;
/* 193 */     this.centerX = worldmap.centerX;
/* 194 */     this.centerZ = worldmap.centerZ;
/* 195 */     this.m.putAll(worldmap.m);
/* 196 */     this.decorations.putAll(worldmap.decorations);
/* 197 */     System.arraycopy(worldmap.colors, 0, this.colors, 0, worldmap.colors.length);
/* 198 */     b();
/*     */   }
/*     */   public void updateSeenPlayers(EntityHuman entityhuman, ItemStack itemstack) {
/* 201 */     a(entityhuman, itemstack);
/*     */   } public void a(EntityHuman entityhuman, ItemStack itemstack) {
/* 203 */     if (!this.humans.containsKey(entityhuman)) {
/* 204 */       WorldMapHumanTracker worldmap_worldmaphumantracker = new WorldMapHumanTracker(entityhuman);
/*     */       
/* 206 */       this.humans.put(entityhuman, worldmap_worldmaphumantracker);
/* 207 */       this.i.add(worldmap_worldmaphumantracker);
/*     */     } 
/*     */     
/* 210 */     if (!entityhuman.inventory.h(itemstack)) {
/* 211 */       this.decorations.remove(entityhuman.getDisplayName().getString());
/*     */     }
/*     */     
/* 214 */     for (int i = 0; i < this.i.size(); i++) {
/* 215 */       WorldMapHumanTracker worldmap_worldmaphumantracker1 = this.i.get(i);
/* 216 */       String s = worldmap_worldmaphumantracker1.trackee.getDisplayName().getString();
/*     */       
/* 218 */       if (!worldmap_worldmaphumantracker1.trackee.dead && (worldmap_worldmaphumantracker1.trackee.inventory.h(itemstack) || itemstack.y())) {
/* 219 */         if (!itemstack.y() && worldmap_worldmaphumantracker1.trackee.world.getDimensionKey() == this.map && this.track) {
/* 220 */           a(MapIcon.Type.PLAYER, worldmap_worldmaphumantracker1.trackee.world, s, worldmap_worldmaphumantracker1.trackee.locX(), worldmap_worldmaphumantracker1.trackee.locZ(), worldmap_worldmaphumantracker1.trackee.yaw, (IChatBaseComponent)null);
/*     */         }
/*     */       } else {
/* 223 */         this.humans.remove(worldmap_worldmaphumantracker1.trackee);
/* 224 */         this.i.remove(worldmap_worldmaphumantracker1);
/* 225 */         this.decorations.remove(s);
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (itemstack.y() && this.track) {
/* 230 */       EntityItemFrame entityitemframe = itemstack.z();
/* 231 */       BlockPosition blockposition = entityitemframe.getBlockPosition();
/* 232 */       WorldMapFrame worldmapframe = this.n.get(WorldMapFrame.a(blockposition));
/*     */       
/* 234 */       if (worldmapframe != null && entityitemframe.getId() != worldmapframe.d() && this.n.containsKey(worldmapframe.e())) {
/* 235 */         this.decorations.remove("frame-" + worldmapframe.d());
/*     */       }
/*     */       
/* 238 */       WorldMapFrame worldmapframe1 = new WorldMapFrame(blockposition, entityitemframe.getDirection().get2DRotationValue() * 90, entityitemframe.getId());
/*     */       
/* 240 */       a(MapIcon.Type.FRAME, entityhuman.world, "frame-" + entityitemframe.getId(), blockposition.getX(), blockposition.getZ(), (entityitemframe.getDirection().get2DRotationValue() * 90), (IChatBaseComponent)null);
/* 241 */       this.n.put(worldmapframe1.e(), worldmapframe1);
/*     */     } 
/*     */     
/* 244 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 246 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Decorations", 9)) {
/* 247 */       NBTTagList nbttaglist = nbttagcompound.getList("Decorations", 10);
/*     */       
/* 249 */       for (int j = 0; j < nbttaglist.size(); j++) {
/* 250 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(j);
/*     */         
/* 252 */         if (!this.decorations.containsKey(nbttagcompound1.getString("id"))) {
/* 253 */           a(MapIcon.Type.a(nbttagcompound1.getByte("type")), entityhuman.world, nbttagcompound1.getString("id"), nbttagcompound1.getDouble("x"), nbttagcompound1.getDouble("z"), nbttagcompound1.getDouble("rot"), (IChatBaseComponent)null);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void decorateMap(ItemStack itemstack, BlockPosition blockposition, String s, MapIcon.Type mapicon_type) {
/*     */     NBTTagList nbttaglist;
/* 263 */     if (itemstack.hasTag() && itemstack.getTag().hasKeyOfType("Decorations", 9)) {
/* 264 */       nbttaglist = itemstack.getTag().getList("Decorations", 10);
/*     */     } else {
/* 266 */       nbttaglist = new NBTTagList();
/* 267 */       itemstack.a("Decorations", nbttaglist);
/*     */     } 
/*     */     
/* 270 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 272 */     nbttagcompound.setByte("type", mapicon_type.a());
/* 273 */     nbttagcompound.setString("id", s);
/* 274 */     nbttagcompound.setDouble("x", blockposition.getX());
/* 275 */     nbttagcompound.setDouble("z", blockposition.getZ());
/* 276 */     nbttagcompound.setDouble("rot", 180.0D);
/* 277 */     nbttaglist.add(nbttagcompound);
/* 278 */     if (mapicon_type.c()) {
/* 279 */       NBTTagCompound nbttagcompound1 = itemstack.a("display");
/*     */       
/* 281 */       nbttagcompound1.setInt("MapColor", mapicon_type.d());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(MapIcon.Type mapicon_type, @Nullable GeneratorAccess generatoraccess, String s, double d0, double d1, double d2, @Nullable IChatBaseComponent ichatbasecomponent) {
/*     */     byte b2;
/* 287 */     int i = 1 << this.scale;
/* 288 */     float f = (float)(d0 - this.centerX) / i;
/* 289 */     float f1 = (float)(d1 - this.centerZ) / i;
/* 290 */     byte b0 = (byte)(int)((f * 2.0F) + 0.5D);
/* 291 */     byte b1 = (byte)(int)((f1 * 2.0F) + 0.5D);
/* 292 */     boolean flag = true;
/*     */ 
/*     */     
/* 295 */     if (f >= -63.0F && f1 >= -63.0F && f <= 63.0F && f1 <= 63.0F) {
/* 296 */       d2 += (d2 < 0.0D) ? -8.0D : 8.0D;
/* 297 */       b2 = (byte)(int)(d2 * 16.0D / 360.0D);
/* 298 */       if (this.map == World.THE_NETHER && generatoraccess != null) {
/* 299 */         int j = (int)(generatoraccess.getWorldData().getDayTime() / 10L);
/*     */         
/* 301 */         b2 = (byte)(j * j * 34187121 + j * 121 >> 15 & 0xF);
/*     */       } 
/*     */     } else {
/* 304 */       if (mapicon_type != MapIcon.Type.PLAYER) {
/* 305 */         this.decorations.remove(s);
/*     */         
/*     */         return;
/*     */       } 
/* 309 */       boolean flag1 = true;
/*     */       
/* 311 */       if (Math.abs(f) < 320.0F && Math.abs(f1) < 320.0F) {
/* 312 */         mapicon_type = MapIcon.Type.PLAYER_OFF_MAP;
/*     */       } else {
/* 314 */         if (!this.unlimitedTracking) {
/* 315 */           this.decorations.remove(s);
/*     */           
/*     */           return;
/*     */         } 
/* 319 */         mapicon_type = MapIcon.Type.PLAYER_OFF_LIMITS;
/*     */       } 
/*     */       
/* 322 */       b2 = 0;
/* 323 */       if (f <= -63.0F) {
/* 324 */         b0 = Byte.MIN_VALUE;
/*     */       }
/*     */       
/* 327 */       if (f1 <= -63.0F) {
/* 328 */         b1 = Byte.MIN_VALUE;
/*     */       }
/*     */       
/* 331 */       if (f >= 63.0F) {
/* 332 */         b0 = Byte.MAX_VALUE;
/*     */       }
/*     */       
/* 335 */       if (f1 >= 63.0F) {
/* 336 */         b1 = Byte.MAX_VALUE;
/*     */       }
/*     */     } 
/*     */     
/* 340 */     this.decorations.put(s, new MapIcon(mapicon_type, b0, b1, b2, ichatbasecomponent));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Packet<?> a(ItemStack itemstack, IBlockAccess iblockaccess, EntityHuman entityhuman) {
/* 345 */     WorldMapHumanTracker worldmap_worldmaphumantracker = this.humans.get(entityhuman);
/*     */     
/* 347 */     return (worldmap_worldmaphumantracker == null) ? null : worldmap_worldmaphumantracker.a(itemstack);
/*     */   }
/*     */   
/*     */   public void flagDirty(int i, int j) {
/* 351 */     b();
/* 352 */     Iterator<WorldMapHumanTracker> iterator = this.i.iterator();
/*     */     
/* 354 */     while (iterator.hasNext()) {
/* 355 */       WorldMapHumanTracker worldmap_worldmaphumantracker = iterator.next();
/*     */       
/* 357 */       worldmap_worldmaphumantracker.a(i, j);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldMapHumanTracker a(EntityHuman entityhuman) {
/* 363 */     WorldMapHumanTracker worldmap_worldmaphumantracker = this.humans.get(entityhuman);
/*     */     
/* 365 */     if (worldmap_worldmaphumantracker == null) {
/* 366 */       worldmap_worldmaphumantracker = new WorldMapHumanTracker(entityhuman);
/* 367 */       this.humans.put(entityhuman, worldmap_worldmaphumantracker);
/* 368 */       this.i.add(worldmap_worldmaphumantracker);
/*     */     } 
/*     */     
/* 371 */     return worldmap_worldmaphumantracker;
/*     */   }
/*     */   
/*     */   public void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 375 */     double d0 = blockposition.getX() + 0.5D;
/* 376 */     double d1 = blockposition.getZ() + 0.5D;
/* 377 */     int i = 1 << this.scale;
/* 378 */     double d2 = (d0 - this.centerX) / i;
/* 379 */     double d3 = (d1 - this.centerZ) / i;
/* 380 */     boolean flag = true;
/* 381 */     boolean flag1 = false;
/*     */     
/* 383 */     if (d2 >= -63.0D && d3 >= -63.0D && d2 <= 63.0D && d3 <= 63.0D) {
/* 384 */       MapIconBanner mapiconbanner = MapIconBanner.a(generatoraccess, blockposition);
/*     */       
/* 386 */       if (mapiconbanner == null) {
/*     */         return;
/*     */       }
/*     */       
/* 390 */       boolean flag2 = true;
/*     */       
/* 392 */       if (this.m.containsKey(mapiconbanner.f()) && ((MapIconBanner)this.m.get(mapiconbanner.f())).equals(mapiconbanner)) {
/* 393 */         this.m.remove(mapiconbanner.f());
/* 394 */         this.decorations.remove(mapiconbanner.f());
/* 395 */         flag2 = false;
/* 396 */         flag1 = true;
/*     */       } 
/*     */       
/* 399 */       if (flag2) {
/* 400 */         this.m.put(mapiconbanner.f(), mapiconbanner);
/* 401 */         a(mapiconbanner.c(), generatoraccess, mapiconbanner.f(), d0, d1, 180.0D, mapiconbanner.d());
/* 402 */         flag1 = true;
/*     */       } 
/*     */       
/* 405 */       if (flag1) {
/* 406 */         b();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockAccess iblockaccess, int i, int j) {
/* 413 */     Iterator<MapIconBanner> iterator = this.m.values().iterator();
/*     */     
/* 415 */     while (iterator.hasNext()) {
/* 416 */       MapIconBanner mapiconbanner = iterator.next();
/*     */       
/* 418 */       if (mapiconbanner.a().getX() == i && mapiconbanner.a().getZ() == j) {
/* 419 */         MapIconBanner mapiconbanner1 = MapIconBanner.a(iblockaccess, mapiconbanner.a());
/*     */         
/* 421 */         if (!mapiconbanner.equals(mapiconbanner1)) {
/* 422 */           iterator.remove();
/* 423 */           this.decorations.remove(mapiconbanner.f());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, int i) {
/* 431 */     this.decorations.remove("frame-" + i);
/* 432 */     this.n.remove(WorldMapFrame.a(blockposition));
/*     */   }
/*     */   
/*     */   public class WorldMapHumanTracker {
/*     */     public final EntityHuman trackee;
/*     */     
/*     */     private void addSeenPlayers(Collection<MapIcon> icons) {
/* 439 */       Player player = (Player)this.trackee.getBukkitEntity();
/* 440 */       WorldMap.this.decorations.forEach((name, mapIcon) -> {
/*     */             Player other = Bukkit.getPlayerExact(name);
/*     */             if (other == null || player.canSee(other)) {
/*     */               icons.add(mapIcon);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*     */     private boolean shouldUseVanillaMap() {
/* 449 */       return (WorldMap.this.mapView.getRenderers().size() == 1 && ((MapRenderer)WorldMap.this.mapView.getRenderers().get(0)).getClass() == CraftMapRenderer.class);
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean d = true;
/*     */     private int e;
/*     */     private int f;
/* 456 */     private int g = 127;
/* 457 */     private int h = 127;
/*     */     private int i;
/*     */     public int b;
/*     */     
/*     */     public WorldMapHumanTracker(EntityHuman entityhuman) {
/* 462 */       this.trackee = entityhuman;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public Packet<?> a(ItemStack itemstack) {
/* 468 */       if (!this.d && this.i % 5 != 0) { this.i++; return null; }
/* 469 */        boolean vanillaMaps = shouldUseVanillaMap();
/* 470 */       RenderData render = !vanillaMaps ? WorldMap.this.mapView.render((CraftPlayer)this.trackee.getBukkitEntity()) : WorldMap.this.vanillaRender;
/*     */       
/* 472 */       Collection<MapIcon> icons = new ArrayList<>();
/* 473 */       if (vanillaMaps) addSeenPlayers(icons);
/*     */       
/* 475 */       for (MapCursor cursor : render.cursors) {
/*     */         
/* 477 */         if (cursor.isVisible()) {
/* 478 */           icons.add(new MapIcon(MapIcon.Type.a(cursor.getRawType()), cursor.getX(), cursor.getY(), cursor.getDirection(), CraftChatMessage.fromStringOrNull(cursor.getCaption())));
/*     */         }
/*     */       } 
/*     */       
/* 482 */       if (this.d) {
/* 483 */         this.d = false;
/* 484 */         return new PacketPlayOutMap(ItemWorldMap.d(itemstack), WorldMap.this.scale, WorldMap.this.track, WorldMap.this.locked, icons, render.buffer, this.e, this.f, this.g + 1 - this.e, this.h + 1 - this.f);
/*     */       } 
/* 486 */       return (this.i++ % 5 == 0) ? new PacketPlayOutMap(ItemWorldMap.d(itemstack), WorldMap.this.scale, WorldMap.this.track, WorldMap.this.locked, icons, render.buffer, 0, 0, 0, 0) : null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(int i, int j) {
/* 492 */       if (this.d) {
/* 493 */         this.e = Math.min(this.e, i);
/* 494 */         this.f = Math.min(this.f, j);
/* 495 */         this.g = Math.max(this.g, i);
/* 496 */         this.h = Math.max(this.h, j);
/*     */       } else {
/* 498 */         this.d = true;
/* 499 */         this.e = i;
/* 500 */         this.f = j;
/* 501 */         this.g = i;
/* 502 */         this.h = j;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */