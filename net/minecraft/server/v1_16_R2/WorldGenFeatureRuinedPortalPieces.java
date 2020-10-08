/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function6;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class WorldGenFeatureRuinedPortalPieces
/*     */   extends DefinedStructurePiece
/*     */ {
/*  51 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final MinecraftKey e;
/*     */   
/*     */   private final EnumBlockRotation f;
/*     */   
/*     */   private final EnumBlockMirror g;
/*     */   private final Position h;
/*     */   private final a i;
/*     */   
/*     */   public static class a
/*     */   {
/*     */     public static final Codec<a> a;
/*     */     public boolean b;
/*     */     
/*     */     static {
/*  67 */       a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.BOOL.fieldOf("cold").forGetter(()), (App)Codec.FLOAT.fieldOf("mossiness").forGetter(()), (App)Codec.BOOL.fieldOf("air_pocket").forGetter(()), (App)Codec.BOOL.fieldOf("overgrown").forGetter(()), (App)Codec.BOOL.fieldOf("vines").forGetter(()), (App)Codec.BOOL.fieldOf("replace_with_blackstone").forGetter(())).apply((Applicative)var0, a::new));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     public float c = 0.2F;
/*     */     
/*     */     public boolean d;
/*     */     public boolean e;
/*     */     public boolean f;
/*     */     public boolean g;
/*     */     
/*     */     public a() {}
/*     */     
/*     */     public <T> a(boolean var0, float var1, boolean var2, boolean var3, boolean var4, boolean var5) {
/*  87 */       this.b = var0;
/*  88 */       this.c = var1;
/*  89 */       this.d = var2;
/*  90 */       this.e = var3;
/*  91 */       this.f = var4;
/*  92 */       this.g = var5;
/*     */     }
/*     */   }
/*     */   
/*     */   public WorldGenFeatureRuinedPortalPieces(BlockPosition var0, Position var1, a var2, MinecraftKey var3, DefinedStructure var4, EnumBlockRotation var5, EnumBlockMirror var6, BlockPosition var7) {
/*  97 */     super(WorldGenFeatureStructurePieceType.J, 0);
/*     */     
/*  99 */     this.c = var0;
/* 100 */     this.e = var3;
/* 101 */     this.f = var5;
/* 102 */     this.g = var6;
/*     */     
/* 104 */     this.h = var1;
/* 105 */     this.i = var2;
/*     */     
/* 107 */     a(var4, var7);
/*     */   }
/*     */   
/*     */   public WorldGenFeatureRuinedPortalPieces(DefinedStructureManager var0, NBTTagCompound var1) {
/* 111 */     super(WorldGenFeatureStructurePieceType.J, var1);
/* 112 */     this.e = new MinecraftKey(var1.getString("Template"));
/* 113 */     this.f = EnumBlockRotation.valueOf(var1.getString("Rotation"));
/* 114 */     this.g = EnumBlockMirror.valueOf(var1.getString("Mirror"));
/* 115 */     this.h = Position.a(var1.getString("VerticalPlacement"));
/* 116 */     this.i = (a)a.a.parse(new Dynamic(DynamicOpsNBT.a, var1.get("Properties"))).getOrThrow(true, LOGGER::error);
/*     */     
/* 118 */     DefinedStructure var2 = var0.a(this.e);
/* 119 */     a(var2, new BlockPosition(var2.a().getX() / 2, 0, var2.a().getZ() / 2));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound var0) {
/* 124 */     super.a(var0);
/* 125 */     var0.setString("Template", this.e.toString());
/* 126 */     var0.setString("Rotation", this.f.name());
/* 127 */     var0.setString("Mirror", this.g.name());
/* 128 */     var0.setString("VerticalPlacement", this.h.a());
/* 129 */     a.a.encodeStart(DynamicOpsNBT.a, this.i).resultOrPartial(LOGGER::error).ifPresent(var1 -> var0.set("Properties", var1));
/*     */   }
/*     */   
/*     */   private void a(DefinedStructure var0, BlockPosition var1) {
/* 133 */     DefinedStructureProcessorBlockIgnore var2 = this.i.d ? DefinedStructureProcessorBlockIgnore.b : DefinedStructureProcessorBlockIgnore.d;
/*     */     
/* 135 */     List<DefinedStructureProcessorPredicates> var3 = Lists.newArrayList();
/* 136 */     var3.add(a(Blocks.GOLD_BLOCK, 0.3F, Blocks.AIR));
/* 137 */     var3.add(c());
/* 138 */     if (!this.i.b) {
/* 139 */       var3.add(a(Blocks.NETHERRACK, 0.07F, Blocks.MAGMA_BLOCK));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 149 */     DefinedStructureInfo var4 = (new DefinedStructureInfo()).a(this.f).a(this.g).a(var1).a(var2).a(new DefinedStructureProcessorRule(var3)).a(new DefinedStructureProcessorBlockAge(this.i.c)).a(new DefinedStructureProcessorLavaSubmergedBlock());
/*     */     
/* 151 */     if (this.i.g) {
/* 152 */       var4.a(DefinedStructureProcessorBlackstoneReplace.b);
/*     */     }
/* 154 */     a(var0, this.c, var4);
/*     */   }
/*     */   
/*     */   private DefinedStructureProcessorPredicates c() {
/* 158 */     if (this.h == Position.ON_OCEAN_FLOOR)
/* 159 */       return a(Blocks.LAVA, Blocks.MAGMA_BLOCK); 
/* 160 */     if (this.i.b) {
/* 161 */       return a(Blocks.LAVA, Blocks.NETHERRACK);
/*     */     }
/* 163 */     return a(Blocks.LAVA, 0.2F, Blocks.MAGMA_BLOCK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 170 */     if (!var4.b(this.c))
/*     */     {
/* 172 */       return true;
/*     */     }
/* 174 */     var4.c(this.a.b(this.b, this.c));
/*     */     
/* 176 */     boolean var7 = super.a(var0, var1, var2, var3, var4, var5, var6);
/*     */     
/* 178 */     b(var3, var0);
/* 179 */     a(var3, var0);
/*     */     
/* 181 */     if (this.i.f || this.i.e) {
/* 182 */       BlockPosition.a(g()).forEach(var2 -> {
/*     */             if (this.i.f) {
/*     */               a(var0, var1, var2);
/*     */             }
/*     */             
/*     */             if (this.i.e) {
/*     */               b(var0, var1, var2);
/*     */             }
/*     */           });
/*     */     }
/* 192 */     return var7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {}
/*     */ 
/*     */   
/*     */   private void a(Random var0, GeneratorAccess var1, BlockPosition var2) {
/* 201 */     IBlockData var3 = var1.getType(var2);
/* 202 */     if (var3.isAir() || var3.a(Blocks.VINE)) {
/*     */       return;
/*     */     }
/*     */     
/* 206 */     EnumDirection var4 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var0);
/* 207 */     BlockPosition var5 = var2.shift(var4);
/* 208 */     IBlockData var6 = var1.getType(var5);
/* 209 */     if (!var6.isAir()) {
/*     */       return;
/*     */     }
/* 212 */     if (!Block.a(var3.getCollisionShape(var1, var2), var4)) {
/*     */       return;
/*     */     }
/* 215 */     BlockStateBoolean var7 = BlockVine.getDirection(var4.opposite());
/* 216 */     var1.setTypeAndData(var5, Blocks.VINE.getBlockData().set(var7, Boolean.valueOf(true)), 3);
/*     */   }
/*     */   
/*     */   private void b(Random var0, GeneratorAccess var1, BlockPosition var2) {
/* 220 */     if (var0.nextFloat() < 0.5F && var1.getType(var2).a(Blocks.NETHERRACK) && var1.getType(var2.up()).isAir()) {
/* 221 */       var1.setTypeAndData(var2.up(), Blocks.JUNGLE_LEAVES.getBlockData().set(BlockLeaves.PERSISTENT, Boolean.valueOf(true)), 3);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(Random var0, GeneratorAccess var1) {
/* 226 */     for (int var2 = this.n.a + 1; var2 < this.n.d; var2++) {
/* 227 */       for (int var3 = this.n.c + 1; var3 < this.n.f; var3++) {
/* 228 */         BlockPosition var4 = new BlockPosition(var2, this.n.b, var3);
/* 229 */         if (var1.getType(var4).a(Blocks.NETHERRACK)) {
/* 230 */           c(var0, var1, var4.down());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void c(Random var0, GeneratorAccess var1, BlockPosition var2) {
/* 237 */     BlockPosition.MutableBlockPosition var3 = var2.i();
/* 238 */     d(var0, var1, var3);
/* 239 */     int var4 = 8;
/* 240 */     while (var4 > 0 && var0.nextFloat() < 0.5F) {
/* 241 */       var3.c(EnumDirection.DOWN);
/* 242 */       var4--;
/* 243 */       d(var0, var1, var3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void b(Random var0, GeneratorAccess var1) {
/* 248 */     boolean var2 = (this.h == Position.ON_LAND_SURFACE || this.h == Position.ON_OCEAN_FLOOR);
/*     */     
/* 250 */     BaseBlockPosition var3 = this.n.g();
/* 251 */     int var4 = var3.getX();
/* 252 */     int var5 = var3.getZ();
/*     */     
/* 254 */     float[] var6 = { 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.9F, 0.9F, 0.8F, 0.7F, 0.6F, 0.4F, 0.2F };
/* 255 */     int var7 = var6.length;
/* 256 */     int var8 = (this.n.d() + this.n.f()) / 2;
/* 257 */     int var9 = var0.nextInt(Math.max(1, 8 - var8 / 2));
/* 258 */     int var10 = 3;
/* 259 */     BlockPosition.MutableBlockPosition var11 = BlockPosition.ZERO.i();
/* 260 */     for (int var12 = var4 - var7; var12 <= var4 + var7; var12++) {
/* 261 */       for (int var13 = var5 - var7; var13 <= var5 + var7; var13++) {
/* 262 */         int var14 = Math.abs(var12 - var4) + Math.abs(var13 - var5);
/* 263 */         int var15 = Math.max(0, var14 + var9);
/* 264 */         if (var15 < var7) {
/*     */ 
/*     */           
/* 267 */           float var16 = var6[var15];
/* 268 */           if (var0.nextDouble() < var16) {
/* 269 */             int var17 = a(var1, var12, var13, this.h);
/* 270 */             int var18 = var2 ? var17 : Math.min(this.n.b, var17);
/* 271 */             var11.d(var12, var18, var13);
/* 272 */             if (Math.abs(var18 - this.n.b) <= 3 && a(var1, var11)) {
/* 273 */               d(var0, var1, var11);
/* 274 */               if (this.i.e) {
/* 275 */                 b(var0, var1, var11);
/*     */               }
/* 277 */               c(var0, var1, var11.down());
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private boolean a(GeneratorAccess var0, BlockPosition var1) {
/* 285 */     IBlockData var2 = var0.getType(var1);
/* 286 */     return (!var2.a(Blocks.AIR) && 
/* 287 */       !var2.a(Blocks.OBSIDIAN) && 
/* 288 */       !var2.a(Blocks.CHEST) && (this.h == Position.IN_NETHER || 
/* 289 */       !var2.a(Blocks.LAVA)));
/*     */   }
/*     */   
/*     */   private void d(Random var0, GeneratorAccess var1, BlockPosition var2) {
/* 293 */     if (!this.i.b && var0.nextFloat() < 0.07F) {
/* 294 */       var1.setTypeAndData(var2, Blocks.MAGMA_BLOCK.getBlockData(), 3);
/*     */     } else {
/* 296 */       var1.setTypeAndData(var2, Blocks.NETHERRACK.getBlockData(), 3);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int a(GeneratorAccess var0, int var1, int var2, Position var3) {
/* 301 */     return var0.a(a(var3), var1, var2) - 1;
/*     */   }
/*     */   
/*     */   public static HeightMap.Type a(Position var0) {
/* 305 */     return (var0 == Position.ON_OCEAN_FLOOR) ? HeightMap.Type.OCEAN_FLOOR_WG : HeightMap.Type.WORLD_SURFACE_WG;
/*     */   }
/*     */   
/*     */   private static DefinedStructureProcessorPredicates a(Block var0, float var1, Block var2) {
/* 309 */     return new DefinedStructureProcessorPredicates(new DefinedStructureTestRandomBlock(var0, var1), DefinedStructureTestTrue.b, var2.getBlockData());
/*     */   }
/*     */   
/*     */   private static DefinedStructureProcessorPredicates a(Block var0, Block var1) {
/* 313 */     return new DefinedStructureProcessorPredicates(new DefinedStructureTestBlock(var0), DefinedStructureTestTrue.b, var1.getBlockData());
/*     */   }
/*     */   
/*     */   public enum Position {
/* 317 */     ON_LAND_SURFACE("on_land_surface"),
/* 318 */     PARTLY_BURIED("partly_buried"),
/* 319 */     ON_OCEAN_FLOOR("on_ocean_floor"),
/* 320 */     IN_MOUNTAIN("in_mountain"),
/* 321 */     UNDERGROUND("underground"),
/* 322 */     IN_NETHER("in_nether"); private static final Map<String, Position> g;
/*     */     
/*     */     static {
/* 325 */       g = (Map<String, Position>)Arrays.<Position>stream(values()).collect(Collectors.toMap(Position::a, var0 -> var0));
/*     */     }
/*     */     private final String h;
/*     */     Position(String var2) {
/* 329 */       this.h = var2;
/*     */     }
/*     */     
/*     */     public String a() {
/* 333 */       return this.h;
/*     */     }
/*     */     
/*     */     public static Position a(String var0) {
/* 337 */       return g.get(var0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRuinedPortalPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */