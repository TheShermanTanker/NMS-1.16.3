/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityStructure
/*     */   extends TileEntity
/*     */ {
/*     */   private MinecraftKey structureName;
/*  46 */   public String author = "";
/*  47 */   public String metadata = "";
/*  48 */   public BlockPosition relativePosition = new BlockPosition(0, 1, 0);
/*  49 */   public BlockPosition size = BlockPosition.ZERO;
/*  50 */   public EnumBlockMirror mirror = EnumBlockMirror.NONE;
/*  51 */   public EnumBlockRotation rotation = EnumBlockRotation.NONE;
/*  52 */   public BlockPropertyStructureMode usageMode = BlockPropertyStructureMode.DATA;
/*     */   public boolean ignoreEntities = true;
/*     */   private boolean powered;
/*     */   public boolean showAir;
/*     */   public boolean showBoundingBox = true;
/*  57 */   public float integrity = 1.0F;
/*     */   public long seed;
/*     */   
/*     */   public TileEntityStructure() {
/*  61 */     super(TileEntityTypes.STRUCTURE_BLOCK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound var0) {
/*  71 */     super.save(var0);
/*  72 */     var0.setString("name", getStructureName());
/*  73 */     var0.setString("author", this.author);
/*  74 */     var0.setString("metadata", this.metadata);
/*  75 */     var0.setInt("posX", this.relativePosition.getX());
/*  76 */     var0.setInt("posY", this.relativePosition.getY());
/*  77 */     var0.setInt("posZ", this.relativePosition.getZ());
/*  78 */     var0.setInt("sizeX", this.size.getX());
/*  79 */     var0.setInt("sizeY", this.size.getY());
/*  80 */     var0.setInt("sizeZ", this.size.getZ());
/*  81 */     var0.setString("rotation", this.rotation.toString());
/*  82 */     var0.setString("mirror", this.mirror.toString());
/*  83 */     var0.setString("mode", this.usageMode.toString());
/*  84 */     var0.setBoolean("ignoreEntities", this.ignoreEntities);
/*  85 */     var0.setBoolean("powered", this.powered);
/*  86 */     var0.setBoolean("showair", this.showAir);
/*  87 */     var0.setBoolean("showboundingbox", this.showBoundingBox);
/*  88 */     var0.setFloat("integrity", this.integrity);
/*  89 */     var0.setLong("seed", this.seed);
/*  90 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData var0, NBTTagCompound var1) {
/*  95 */     super.load(var0, var1);
/*  96 */     setStructureName(var1.getString("name"));
/*  97 */     this.author = var1.getString("author");
/*  98 */     this.metadata = var1.getString("metadata");
/*  99 */     int var2 = MathHelper.clamp(var1.getInt("posX"), -48, 48);
/* 100 */     int var3 = MathHelper.clamp(var1.getInt("posY"), -48, 48);
/* 101 */     int var4 = MathHelper.clamp(var1.getInt("posZ"), -48, 48);
/* 102 */     this.relativePosition = new BlockPosition(var2, var3, var4);
/* 103 */     int var5 = MathHelper.clamp(var1.getInt("sizeX"), 0, 48);
/* 104 */     int var6 = MathHelper.clamp(var1.getInt("sizeY"), 0, 48);
/* 105 */     int var7 = MathHelper.clamp(var1.getInt("sizeZ"), 0, 48);
/* 106 */     this.size = new BlockPosition(var5, var6, var7);
/*     */     try {
/* 108 */       this.rotation = EnumBlockRotation.valueOf(var1.getString("rotation"));
/* 109 */     } catch (IllegalArgumentException var8) {
/* 110 */       this.rotation = EnumBlockRotation.NONE;
/*     */     } 
/*     */     try {
/* 113 */       this.mirror = EnumBlockMirror.valueOf(var1.getString("mirror"));
/* 114 */     } catch (IllegalArgumentException var8) {
/* 115 */       this.mirror = EnumBlockMirror.NONE;
/*     */     } 
/*     */     try {
/* 118 */       this.usageMode = BlockPropertyStructureMode.valueOf(var1.getString("mode"));
/* 119 */     } catch (IllegalArgumentException var8) {
/* 120 */       this.usageMode = BlockPropertyStructureMode.DATA;
/*     */     } 
/* 122 */     this.ignoreEntities = var1.getBoolean("ignoreEntities");
/* 123 */     this.powered = var1.getBoolean("powered");
/* 124 */     this.showAir = var1.getBoolean("showair");
/* 125 */     this.showBoundingBox = var1.getBoolean("showboundingbox");
/* 126 */     if (var1.hasKey("integrity")) {
/* 127 */       this.integrity = var1.getFloat("integrity");
/*     */     } else {
/* 129 */       this.integrity = 1.0F;
/*     */     } 
/* 131 */     this.seed = var1.getLong("seed");
/* 132 */     K();
/*     */   }
/*     */   
/*     */   private void K() {
/* 136 */     if (this.world == null) {
/*     */       return;
/*     */     }
/* 139 */     BlockPosition var0 = getPosition();
/* 140 */     IBlockData var1 = this.world.getType(var0);
/* 141 */     if (var1.a(Blocks.STRUCTURE_BLOCK)) {
/* 142 */       this.world.setTypeAndData(var0, var1.set(BlockStructure.a, this.usageMode), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 149 */     return new PacketPlayOutTileEntityData(this.position, 7, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 154 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public boolean a(EntityHuman var0) {
/* 158 */     if (!var0.isCreativeAndOp()) {
/* 159 */       return false;
/*     */     }
/* 161 */     if ((var0.getWorld()).isClientSide) {
/* 162 */       var0.a(this);
/*     */     }
/* 164 */     return true;
/*     */   }
/*     */   
/*     */   public String getStructureName() {
/* 168 */     return (this.structureName == null) ? "" : this.structureName.toString();
/*     */   }
/*     */   
/*     */   public String f() {
/* 172 */     return (this.structureName == null) ? "" : this.structureName.getKey();
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 176 */     return (this.structureName != null);
/*     */   }
/*     */   
/*     */   public void setStructureName(@Nullable String var0) {
/* 180 */     a(UtilColor.b(var0) ? null : MinecraftKey.a(var0));
/*     */   }
/*     */   
/*     */   public void a(@Nullable MinecraftKey var0) {
/* 184 */     this.structureName = var0;
/*     */   }
/*     */   
/*     */   public void setAuthor(EntityLiving var0) {
/* 188 */     this.author = var0.getDisplayName().getString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(BlockPosition var0) {
/* 196 */     this.relativePosition = var0;
/*     */   }
/*     */   
/*     */   public BlockPosition j() {
/* 200 */     return this.size;
/*     */   }
/*     */   
/*     */   public void c(BlockPosition var0) {
/* 204 */     this.size = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(EnumBlockMirror var0) {
/* 212 */     this.mirror = var0;
/*     */   }
/*     */   
/*     */   public EnumBlockRotation l() {
/* 216 */     return this.rotation;
/*     */   }
/*     */   
/*     */   public void b(EnumBlockRotation var0) {
/* 220 */     this.rotation = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String var0) {
/* 228 */     this.metadata = var0;
/*     */   }
/*     */   
/*     */   public BlockPropertyStructureMode getUsageMode() {
/* 232 */     return this.usageMode;
/*     */   }
/*     */   
/*     */   public void setUsageMode(BlockPropertyStructureMode var0) {
/* 236 */     this.usageMode = var0;
/* 237 */     IBlockData var1 = this.world.getType(getPosition());
/* 238 */     if (var1.a(Blocks.STRUCTURE_BLOCK)) {
/* 239 */       this.world.setTypeAndData(getPosition(), var1.set(BlockStructure.a, var0), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(boolean var0) {
/* 265 */     this.ignoreEntities = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float var0) {
/* 273 */     this.integrity = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(long var0) {
/* 281 */     this.seed = var0;
/*     */   }
/*     */   
/*     */   public boolean C() {
/* 285 */     if (this.usageMode != BlockPropertyStructureMode.SAVE) {
/* 286 */       return false;
/*     */     }
/* 288 */     BlockPosition var0 = getPosition();
/* 289 */     int var1 = 80;
/* 290 */     BlockPosition var2 = new BlockPosition(var0.getX() - 80, 0, var0.getZ() - 80);
/* 291 */     BlockPosition var3 = new BlockPosition(var0.getX() + 80, 255, var0.getZ() + 80);
/*     */     
/* 293 */     List<TileEntityStructure> var4 = a(var2, var3);
/* 294 */     List<TileEntityStructure> var5 = a(var4);
/* 295 */     if (var5.size() < 1) {
/* 296 */       return false;
/*     */     }
/*     */     
/* 299 */     StructureBoundingBox var6 = a(var0, var5);
/* 300 */     if (var6.d - var6.a > 1 && var6.e - var6.b > 1 && var6.f - var6.c > 1) {
/* 301 */       this.relativePosition = new BlockPosition(var6.a - var0.getX() + 1, var6.b - var0.getY() + 1, var6.c - var0.getZ() + 1);
/* 302 */       this.size = new BlockPosition(var6.d - var6.a - 1, var6.e - var6.b - 1, var6.f - var6.c - 1);
/* 303 */       update();
/* 304 */       IBlockData var7 = this.world.getType(var0);
/* 305 */       this.world.notify(var0, var7, var7, 3);
/* 306 */       return true;
/*     */     } 
/* 308 */     return false;
/*     */   }
/*     */   
/*     */   private List<TileEntityStructure> a(List<TileEntityStructure> var0) {
/* 312 */     Predicate<TileEntityStructure> var1 = var0 -> (var0.usageMode == BlockPropertyStructureMode.CORNER && Objects.equals(this.structureName, var0.structureName));
/* 313 */     return (List<TileEntityStructure>)var0.stream().filter(var1).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   private List<TileEntityStructure> a(BlockPosition var0, BlockPosition var1) {
/* 317 */     List<TileEntityStructure> var2 = Lists.newArrayList();
/* 318 */     for (BlockPosition var4 : BlockPosition.a(var0, var1)) {
/* 319 */       IBlockData var5 = this.world.getType(var4);
/* 320 */       if (!var5.a(Blocks.STRUCTURE_BLOCK)) {
/*     */         continue;
/*     */       }
/* 323 */       TileEntity var6 = this.world.getTileEntity(var4);
/* 324 */       if (var6 != null && var6 instanceof TileEntityStructure) {
/* 325 */         var2.add((TileEntityStructure)var6);
/*     */       }
/*     */     } 
/* 328 */     return var2;
/*     */   }
/*     */   
/*     */   private StructureBoundingBox a(BlockPosition var0, List<TileEntityStructure> var1) {
/*     */     StructureBoundingBox var2;
/* 333 */     if (var1.size() > 1) {
/* 334 */       BlockPosition var3 = ((TileEntityStructure)var1.get(0)).getPosition();
/* 335 */       var2 = new StructureBoundingBox(var3, var3);
/*     */     } else {
/* 337 */       var2 = new StructureBoundingBox(var0, var0);
/*     */     } 
/*     */     
/* 340 */     for (TileEntityStructure var4 : var1) {
/* 341 */       BlockPosition var5 = var4.getPosition();
/* 342 */       if (var5.getX() < var2.a) {
/* 343 */         var2.a = var5.getX();
/* 344 */       } else if (var5.getX() > var2.d) {
/* 345 */         var2.d = var5.getX();
/*     */       } 
/* 347 */       if (var5.getY() < var2.b) {
/* 348 */         var2.b = var5.getY();
/* 349 */       } else if (var5.getY() > var2.e) {
/* 350 */         var2.e = var5.getY();
/*     */       } 
/* 352 */       if (var5.getZ() < var2.c) {
/* 353 */         var2.c = var5.getZ(); continue;
/* 354 */       }  if (var5.getZ() > var2.f) {
/* 355 */         var2.f = var5.getZ();
/*     */       }
/*     */     } 
/* 358 */     return var2;
/*     */   }
/*     */   
/*     */   public boolean D() {
/* 362 */     return b(true);
/*     */   }
/*     */   public boolean b(boolean var0) {
/*     */     DefinedStructure var4;
/* 366 */     if (this.usageMode != BlockPropertyStructureMode.SAVE || this.world.isClientSide || this.structureName == null) {
/* 367 */       return false;
/*     */     }
/* 369 */     BlockPosition var1 = getPosition().a(this.relativePosition);
/*     */     
/* 371 */     WorldServer var2 = (WorldServer)this.world;
/* 372 */     DefinedStructureManager var3 = var2.n();
/*     */     
/*     */     try {
/* 375 */       var4 = var3.a(this.structureName);
/* 376 */     } catch (ResourceKeyInvalidException var5) {
/* 377 */       return false;
/*     */     } 
/*     */     
/* 380 */     var4.a(this.world, var1, this.size, !this.ignoreEntities, Blocks.STRUCTURE_VOID);
/* 381 */     var4.a(this.author);
/* 382 */     if (var0) {
/*     */       try {
/* 384 */         return var3.c(this.structureName);
/* 385 */       } catch (ResourceKeyInvalidException var5) {
/* 386 */         return false;
/*     */       } 
/*     */     }
/* 389 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0) {
/* 393 */     return a(var0, true);
/*     */   }
/*     */   
/*     */   private static Random b(long var0) {
/* 397 */     if (var0 == 0L) {
/* 398 */       return new Random(SystemUtils.getMonotonicMillis());
/*     */     }
/* 400 */     return new Random(var0);
/*     */   }
/*     */   public boolean a(WorldServer var0, boolean var1) {
/*     */     DefinedStructure var3;
/* 404 */     if (this.usageMode != BlockPropertyStructureMode.LOAD || this.structureName == null) {
/* 405 */       return false;
/*     */     }
/* 407 */     DefinedStructureManager var2 = var0.n();
/*     */     
/*     */     try {
/* 410 */       var3 = var2.b(this.structureName);
/* 411 */     } catch (ResourceKeyInvalidException var4) {
/* 412 */       return false;
/*     */     } 
/*     */     
/* 415 */     if (var3 == null) {
/* 416 */       return false;
/*     */     }
/*     */     
/* 419 */     return a(var0, var1, var3);
/*     */   }
/*     */   
/*     */   public boolean a(WorldServer var0, boolean var1, DefinedStructure var2) {
/* 423 */     BlockPosition var3 = getPosition();
/*     */     
/* 425 */     if (!UtilColor.b(var2.b())) {
/* 426 */       this.author = var2.b();
/*     */     }
/*     */     
/* 429 */     BlockPosition var4 = var2.a();
/* 430 */     boolean var5 = this.size.equals(var4);
/*     */     
/* 432 */     if (!var5) {
/* 433 */       this.size = var4;
/* 434 */       update();
/* 435 */       IBlockData var6 = var0.getType(var3);
/* 436 */       var0.notify(var3, var6, var6, 3);
/*     */     } 
/*     */     
/* 439 */     if (!var1 || var5) {
/* 440 */       DefinedStructureInfo var6 = (new DefinedStructureInfo()).a(this.mirror).a(this.rotation).a(this.ignoreEntities).a((ChunkCoordIntPair)null);
/* 441 */       if (this.integrity < 1.0F) {
/* 442 */         var6.b().a(new DefinedStructureProcessorRotation(MathHelper.a(this.integrity, 0.0F, 1.0F))).a(b(this.seed));
/*     */       }
/* 444 */       BlockPosition var7 = var3.a(this.relativePosition);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 458 */       var2.a(var0, var7, var6, b(this.seed));
/* 459 */       return true;
/*     */     } 
/* 461 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void E() {
/* 466 */     if (this.structureName == null) {
/*     */       return;
/*     */     }
/* 469 */     WorldServer var0 = (WorldServer)this.world;
/* 470 */     DefinedStructureManager var1 = var0.n();
/* 471 */     var1.d(this.structureName);
/*     */   }
/*     */   
/*     */   public boolean F() {
/* 475 */     if (this.usageMode != BlockPropertyStructureMode.LOAD || this.world.isClientSide || this.structureName == null) {
/* 476 */       return false;
/*     */     }
/* 478 */     WorldServer var0 = (WorldServer)this.world;
/* 479 */     DefinedStructureManager var1 = var0.n();
/*     */     try {
/* 481 */       return (var1.b(this.structureName) != null);
/* 482 */     } catch (ResourceKeyInvalidException var2) {
/* 483 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean G() {
/* 488 */     return this.powered;
/*     */   }
/*     */   
/*     */   public void c(boolean var0) {
/* 492 */     this.powered = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(boolean var0) {
/* 500 */     this.showAir = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(boolean var0) {
/* 508 */     this.showBoundingBox = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum UpdateType
/*     */   {
/* 516 */     UPDATE_DATA,
/* 517 */     SAVE_AREA,
/* 518 */     LOAD_AREA,
/* 519 */     SCAN_AREA;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */