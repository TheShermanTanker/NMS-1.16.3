/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
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
/*     */ public class WorldGenFeatureRuinedPortal
/*     */   extends StructureGenerator<WorldGenFeatureRuinedPortalConfiguration>
/*     */ {
/*  34 */   private static final String[] u = new String[] { "ruined_portal/portal_1", "ruined_portal/portal_2", "ruined_portal/portal_3", "ruined_portal/portal_4", "ruined_portal/portal_5", "ruined_portal/portal_6", "ruined_portal/portal_7", "ruined_portal/portal_8", "ruined_portal/portal_9", "ruined_portal/portal_10" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private static final String[] v = new String[] { "ruined_portal/giant_portal_1", "ruined_portal/giant_portal_2", "ruined_portal/giant_portal_3" };
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
/*     */   public WorldGenFeatureRuinedPortal(Codec<WorldGenFeatureRuinedPortalConfiguration> var0) {
/*  52 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureGenerator.a<WorldGenFeatureRuinedPortalConfiguration> a() {
/*  57 */     return a::new;
/*     */   }
/*     */   
/*     */   public static class a extends StructureStart<WorldGenFeatureRuinedPortalConfiguration> {
/*     */     protected a(StructureGenerator<WorldGenFeatureRuinedPortalConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/*  62 */       super(var0, var1, var2, var3, var4, var5);
/*     */     }
/*     */     
/*     */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureRuinedPortalConfiguration var6) {
/*     */       WorldGenFeatureRuinedPortalPieces.Position var7;
/*     */       MinecraftKey var9;
/*  68 */       WorldGenFeatureRuinedPortalPieces.a var8 = new WorldGenFeatureRuinedPortalPieces.a();
/*     */       
/*  70 */       if (var6.b == WorldGenFeatureRuinedPortal.Type.DESERT) {
/*  71 */         var7 = WorldGenFeatureRuinedPortalPieces.Position.PARTLY_BURIED;
/*  72 */         var8.d = false;
/*  73 */         var8.c = 0.0F;
/*  74 */       } else if (var6.b == WorldGenFeatureRuinedPortal.Type.JUNGLE) {
/*  75 */         var7 = WorldGenFeatureRuinedPortalPieces.Position.ON_LAND_SURFACE;
/*  76 */         var8.d = (this.d.nextFloat() < 0.5F);
/*  77 */         var8.c = 0.8F;
/*  78 */         var8.e = true;
/*  79 */         var8.f = true;
/*  80 */       } else if (var6.b == WorldGenFeatureRuinedPortal.Type.SWAMP) {
/*  81 */         var7 = WorldGenFeatureRuinedPortalPieces.Position.ON_OCEAN_FLOOR;
/*  82 */         var8.d = false;
/*  83 */         var8.c = 0.5F;
/*  84 */         var8.f = true;
/*  85 */       } else if (var6.b == WorldGenFeatureRuinedPortal.Type.MOUNTAIN) {
/*  86 */         boolean bool = (this.d.nextFloat() < 0.5F);
/*  87 */         var7 = bool ? WorldGenFeatureRuinedPortalPieces.Position.IN_MOUNTAIN : WorldGenFeatureRuinedPortalPieces.Position.ON_LAND_SURFACE;
/*  88 */         var8.d = (bool || this.d.nextFloat() < 0.5F);
/*  89 */       } else if (var6.b == WorldGenFeatureRuinedPortal.Type.OCEAN) {
/*  90 */         var7 = WorldGenFeatureRuinedPortalPieces.Position.ON_OCEAN_FLOOR;
/*  91 */         var8.d = false;
/*  92 */         var8.c = 0.8F;
/*  93 */       } else if (var6.b == WorldGenFeatureRuinedPortal.Type.NETHER) {
/*  94 */         var7 = WorldGenFeatureRuinedPortalPieces.Position.IN_NETHER;
/*  95 */         var8.d = (this.d.nextFloat() < 0.5F);
/*  96 */         var8.c = 0.0F;
/*  97 */         var8.g = true;
/*     */       } else {
/*  99 */         boolean bool = (this.d.nextFloat() < 0.5F);
/* 100 */         var7 = bool ? WorldGenFeatureRuinedPortalPieces.Position.UNDERGROUND : WorldGenFeatureRuinedPortalPieces.Position.ON_LAND_SURFACE;
/* 101 */         var8.d = (bool || this.d.nextFloat() < 0.5F);
/*     */       } 
/*     */ 
/*     */       
/* 105 */       if (this.d.nextFloat() < 0.05F) {
/* 106 */         var9 = new MinecraftKey(WorldGenFeatureRuinedPortal.d()[this.d.nextInt((WorldGenFeatureRuinedPortal.d()).length)]);
/*     */       } else {
/* 108 */         var9 = new MinecraftKey(WorldGenFeatureRuinedPortal.e()[this.d.nextInt((WorldGenFeatureRuinedPortal.e()).length)]);
/*     */       } 
/*     */       
/* 111 */       DefinedStructure var10 = var2.a(var9);
/* 112 */       EnumBlockRotation var11 = SystemUtils.<EnumBlockRotation>a(EnumBlockRotation.values(), this.d);
/* 113 */       EnumBlockMirror var12 = (this.d.nextFloat() < 0.5F) ? EnumBlockMirror.NONE : EnumBlockMirror.FRONT_BACK;
/* 114 */       BlockPosition var13 = new BlockPosition(var10.a().getX() / 2, 0, var10.a().getZ() / 2);
/*     */       
/* 116 */       BlockPosition var14 = (new ChunkCoordIntPair(var3, var4)).l();
/* 117 */       StructureBoundingBox var15 = var10.a(var14, var11, var13, var12);
/* 118 */       BaseBlockPosition var16 = var15.g();
/* 119 */       int var17 = var16.getX();
/* 120 */       int var18 = var16.getZ();
/* 121 */       int var19 = var1.getBaseHeight(var17, var18, WorldGenFeatureRuinedPortalPieces.a(var7)) - 1;
/* 122 */       int var20 = WorldGenFeatureRuinedPortal.a(this.d, var1, var7, var8.d, var19, var15.e(), var15);
/*     */       
/* 124 */       BlockPosition var21 = new BlockPosition(var14.getX(), var20, var14.getZ());
/*     */       
/* 126 */       if (var6.b == WorldGenFeatureRuinedPortal.Type.MOUNTAIN || var6.b == WorldGenFeatureRuinedPortal.Type.OCEAN || var6.b == WorldGenFeatureRuinedPortal.Type.STANDARD) {
/* 127 */         var8.b = WorldGenFeatureRuinedPortal.a(var21, var5);
/*     */       }
/*     */       
/* 130 */       this.b.add(new WorldGenFeatureRuinedPortalPieces(var21, var7, var8, var9, var10, var11, var12, var13));
/* 131 */       b();
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean b(BlockPosition var0, BiomeBase var1) {
/* 136 */     return (var1.getAdjustedTemperature(var0) < 0.15F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int b(Random var0, ChunkGenerator var1, WorldGenFeatureRuinedPortalPieces.Position var2, boolean var3, int var4, int var5, StructureBoundingBox var6) {
/*     */     int var7;
/* 143 */     if (var2 == WorldGenFeatureRuinedPortalPieces.Position.IN_NETHER) {
/* 144 */       if (var3) {
/*     */         
/* 146 */         var7 = a(var0, 32, 100);
/*     */       }
/* 148 */       else if (var0.nextFloat() < 0.5F) {
/*     */         
/* 150 */         var7 = a(var0, 27, 29);
/*     */       } else {
/*     */         
/* 153 */         var7 = a(var0, 29, 100);
/*     */       }
/*     */     
/* 156 */     } else if (var2 == WorldGenFeatureRuinedPortalPieces.Position.IN_MOUNTAIN) {
/* 157 */       int var8 = var4 - var5;
/* 158 */       var7 = b(var0, 70, var8);
/* 159 */     } else if (var2 == WorldGenFeatureRuinedPortalPieces.Position.UNDERGROUND) {
/* 160 */       int var8 = var4 - var5;
/* 161 */       var7 = b(var0, 15, var8);
/* 162 */     } else if (var2 == WorldGenFeatureRuinedPortalPieces.Position.PARTLY_BURIED) {
/* 163 */       var7 = var4 - var5 + a(var0, 2, 8);
/*     */     } else {
/* 165 */       var7 = var4;
/*     */     } 
/*     */     
/* 168 */     ImmutableList immutableList = ImmutableList.of(new BlockPosition(var6.a, 0, var6.c), new BlockPosition(var6.d, 0, var6.c), new BlockPosition(var6.a, 0, var6.f), new BlockPosition(var6.d, 0, var6.f));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     List<IBlockAccess> var9 = (List<IBlockAccess>)immutableList.stream().map(var1 -> var0.a(var1.getX(), var1.getZ())).collect(Collectors.toList());
/*     */     
/* 177 */     HeightMap.Type var10 = (var2 == WorldGenFeatureRuinedPortalPieces.Position.ON_OCEAN_FLOOR) ? HeightMap.Type.OCEAN_FLOOR_WG : HeightMap.Type.WORLD_SURFACE_WG;
/*     */     
/* 179 */     BlockPosition.MutableBlockPosition var11 = new BlockPosition.MutableBlockPosition();
/* 180 */     int var12 = var7;
/*     */ 
/*     */ 
/*     */     
/* 184 */     label39: while (var12 > 15) {
/* 185 */       int var13 = 0;
/* 186 */       var11.d(0, var12, 0);
/* 187 */       for (IBlockAccess var15 : var9) {
/*     */         
/* 189 */         IBlockData var16 = var15.getType(var11);
/*     */         
/* 191 */         var13++;
/* 192 */         if (var16 != null && var10.e().test(var16) && var13 == 3) {
/*     */           break label39;
/*     */         }
/*     */       } 
/*     */       
/* 197 */       var12--;
/*     */     } 
/* 199 */     return var12;
/*     */   }
/*     */   
/*     */   private static int a(Random var0, int var1, int var2) {
/* 203 */     return var0.nextInt(var2 - var1 + 1) + var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int b(Random var0, int var1, int var2) {
/* 208 */     if (var1 < var2) {
/* 209 */       return a(var0, var1, var2);
/*     */     }
/* 211 */     return var2;
/*     */   }
/*     */   
/*     */   public enum Type
/*     */     implements INamable {
/* 216 */     STANDARD("standard"),
/* 217 */     DESERT("desert"),
/* 218 */     JUNGLE("jungle"),
/* 219 */     SWAMP("swamp"),
/* 220 */     MOUNTAIN("mountain"),
/* 221 */     OCEAN("ocean"),
/* 222 */     NETHER("nether");
/*     */     
/* 224 */     public static final Codec<Type> h = INamable.a(Type::values, Type::a); private static final Map<String, Type> i;
/*     */     static {
/* 226 */       i = (Map<String, Type>)Arrays.<Type>stream(values()).collect(Collectors.toMap(Type::b, var0 -> var0));
/*     */     }
/*     */     private final String j;
/*     */     Type(String var2) {
/* 230 */       this.j = var2;
/*     */     }
/*     */     
/*     */     public String b() {
/* 234 */       return this.j;
/*     */     }
/*     */     
/*     */     public static Type a(String var0) {
/* 238 */       return i.get(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 243 */       return this.j;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRuinedPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */