/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class WorldGenEndCityPieces
/*     */ {
/*  30 */   private static final DefinedStructureInfo a = (new DefinedStructureInfo()).a(true).a(DefinedStructureProcessorBlockIgnore.b);
/*  31 */   private static final DefinedStructureInfo b = (new DefinedStructureInfo()).a(true).a(DefinedStructureProcessorBlockIgnore.d);
/*     */   
/*     */   private static Piece b(DefinedStructureManager var0, Piece var1, BlockPosition var2, String var3, EnumBlockRotation var4, boolean var5) {
/*  34 */     Piece var6 = new Piece(var0, var3, var1.c, var4, var5);
/*  35 */     BlockPosition var7 = var1.a.a(var1.b, var2, var6.b, BlockPosition.ZERO);
/*  36 */     var6.a(var7.getX(), var7.getY(), var7.getZ());
/*     */     
/*  38 */     return var6;
/*     */   }
/*     */   
/*     */   public static class Piece extends DefinedStructurePiece {
/*     */     private final String d;
/*     */     private final EnumBlockRotation e;
/*     */     private final boolean f;
/*     */     
/*     */     public Piece(DefinedStructureManager var0, String var1, BlockPosition var2, EnumBlockRotation var3, boolean var4) {
/*  47 */       super(WorldGenFeatureStructurePieceType.Y, 0);
/*     */       
/*  49 */       this.d = var1;
/*  50 */       this.c = var2;
/*  51 */       this.e = var3;
/*  52 */       this.f = var4;
/*     */       
/*  54 */       a(var0);
/*     */     }
/*     */     
/*     */     public Piece(DefinedStructureManager var0, NBTTagCompound var1) {
/*  58 */       super(WorldGenFeatureStructurePieceType.Y, var1);
/*     */       
/*  60 */       this.d = var1.getString("Template");
/*  61 */       this.e = EnumBlockRotation.valueOf(var1.getString("Rot"));
/*  62 */       this.f = var1.getBoolean("OW");
/*     */       
/*  64 */       a(var0);
/*     */     }
/*     */     
/*     */     private void a(DefinedStructureManager var0) {
/*  68 */       DefinedStructure var1 = var0.a(new MinecraftKey("end_city/" + this.d));
/*  69 */       DefinedStructureInfo var2 = (this.f ? WorldGenEndCityPieces.a() : WorldGenEndCityPieces.b()).a().a(this.e);
/*     */       
/*  71 */       a(var1, this.c, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/*  76 */       super.a(var0);
/*     */       
/*  78 */       var0.setString("Template", this.d);
/*  79 */       var0.setString("Rot", this.e.name());
/*  80 */       var0.setBoolean("OW", this.f);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {
/*  85 */       if (var0.startsWith("Chest")) {
/*  86 */         BlockPosition var5 = var1.down();
/*     */         
/*  88 */         if (var4.b(var5)) {
/*  89 */           TileEntityLootable.a(var2, var3, var5, LootTables.c);
/*     */         }
/*  91 */       } else if (var0.startsWith("Sentry")) {
/*  92 */         EntityShulker var5 = EntityTypes.SHULKER.a(var2.getMinecraftWorld());
/*  93 */         var5.setPosition(var1.getX() + 0.5D, var1.getY() + 0.5D, var1.getZ() + 0.5D);
/*  94 */         var5.h(var1);
/*  95 */         var2.addEntity(var5);
/*  96 */       } else if (var0.startsWith("Elytra")) {
/*  97 */         EntityItemFrame var5 = new EntityItemFrame(var2.getMinecraftWorld(), var1, this.e.a(EnumDirection.SOUTH));
/*  98 */         var5.setItem(new ItemStack(Items.ELYTRA), false);
/*  99 */         var2.addEntity(var5);
/*     */       } 
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
/*     */   public static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<StructurePiece> var3, Random var4) {
/* 113 */     h.a();
/* 114 */     c.a();
/* 115 */     f.a();
/* 116 */     e.a();
/*     */     
/* 118 */     Piece var5 = b(var3, new Piece(var0, "base_floor", var1, var2, true));
/* 119 */     var5 = b(var3, b(var0, var5, new BlockPosition(-1, 0, -1), "second_floor_1", var2, false));
/* 120 */     var5 = b(var3, b(var0, var5, new BlockPosition(-1, 4, -1), "third_floor_1", var2, false));
/* 121 */     var5 = b(var3, b(var0, var5, new BlockPosition(-1, 8, -1), "third_roof", var2, true));
/*     */     
/* 123 */     b(var0, e, 1, var5, null, var3, var4);
/*     */   }
/*     */   
/*     */   private static Piece b(List<StructurePiece> var0, Piece var1) {
/* 127 */     var0.add(var1);
/* 128 */     return var1;
/*     */   }
/*     */   
/*     */   private static boolean b(DefinedStructureManager var0, PieceGenerator var1, int var2, Piece var3, BlockPosition var4, List<StructurePiece> var5, Random var6) {
/* 132 */     if (var2 > 8) {
/* 133 */       return false;
/*     */     }
/*     */     
/* 136 */     List<StructurePiece> var7 = Lists.newArrayList();
/* 137 */     if (var1.a(var0, var2, var3, var4, var7, var6)) {
/*     */       
/* 139 */       boolean var8 = false;
/* 140 */       int var9 = var6.nextInt();
/* 141 */       for (StructurePiece var11 : var7) {
/* 142 */         var11.o = var9;
/* 143 */         StructurePiece var12 = StructurePiece.a(var5, var11.g());
/* 144 */         if (var12 != null && var12.o != var3.o) {
/* 145 */           var8 = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 149 */       if (!var8) {
/* 150 */         var5.addAll(var7);
/* 151 */         return true;
/*     */       } 
/*     */     } 
/* 154 */     return false;
/*     */   }
/*     */   
/* 157 */   private static final PieceGenerator c = new PieceGenerator()
/*     */     {
/*     */       public void a() {}
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean a(DefinedStructureManager var0, int var1, WorldGenEndCityPieces.Piece var2, BlockPosition var3, List<StructurePiece> var4, Random var5) {
/* 164 */         if (var1 > 8) {
/* 165 */           return false;
/*     */         }
/*     */         
/* 168 */         EnumBlockRotation var6 = var2.b.d();
/* 169 */         WorldGenEndCityPieces.Piece var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var2, var3, "base_floor", var6, true));
/*     */         
/* 171 */         int var8 = var5.nextInt(3);
/* 172 */         if (var8 == 0) {
/* 173 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 4, -1), "base_roof", var6, true));
/* 174 */         } else if (var8 == 1) {
/* 175 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 0, -1), "second_floor_2", var6, false));
/* 176 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 8, -1), "second_roof", var6, false));
/*     */           
/* 178 */           WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.c(), var1 + 1, var7, null, var4, var5);
/* 179 */         } else if (var8 == 2) {
/* 180 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 0, -1), "second_floor_2", var6, false));
/* 181 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 4, -1), "third_floor_2", var6, false));
/* 182 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 8, -1), "third_roof", var6, true));
/*     */           
/* 184 */           WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.c(), var1 + 1, var7, null, var4, var5);
/*     */         } 
/* 186 */         return true;
/*     */       }
/*     */     };
/*     */   
/* 190 */   private static final List<Tuple<EnumBlockRotation, BlockPosition>> d = Lists.newArrayList((Object[])new Tuple[] { new Tuple<>(EnumBlockRotation.NONE, new BlockPosition(1, -1, 0)), new Tuple<>(EnumBlockRotation.CLOCKWISE_90, new BlockPosition(6, -1, 1)), new Tuple<>(EnumBlockRotation.COUNTERCLOCKWISE_90, new BlockPosition(0, -1, 5)), new Tuple<>(EnumBlockRotation.CLOCKWISE_180, new BlockPosition(5, -1, 6)) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   private static final PieceGenerator e = new PieceGenerator()
/*     */     {
/*     */       public void a() {}
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean a(DefinedStructureManager var0, int var1, WorldGenEndCityPieces.Piece var2, BlockPosition var3, List<StructurePiece> var4, Random var5) {
/* 204 */         EnumBlockRotation var6 = var2.b.d();
/* 205 */         WorldGenEndCityPieces.Piece var7 = var2;
/* 206 */         var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(3 + var5.nextInt(2), -3, 3 + var5.nextInt(2)), "tower_base", var6, true));
/* 207 */         var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(0, 7, 0), "tower_piece", var6, true));
/*     */         
/* 209 */         WorldGenEndCityPieces.Piece var8 = (var5.nextInt(3) == 0) ? var7 : null;
/*     */         
/* 211 */         int var9 = 1 + var5.nextInt(3);
/* 212 */         for (int var10 = 0; var10 < var9; var10++) {
/* 213 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(0, 4, 0), "tower_piece", var6, true));
/* 214 */           if (var10 < var9 - 1 && var5.nextBoolean()) {
/* 215 */             var8 = var7;
/*     */           }
/*     */         } 
/*     */         
/* 219 */         if (var8 != null) {
/* 220 */           for (Tuple<EnumBlockRotation, BlockPosition> var11 : (Iterable<Tuple<EnumBlockRotation, BlockPosition>>)WorldGenEndCityPieces.d()) {
/* 221 */             if (var5.nextBoolean()) {
/*     */               
/* 223 */               WorldGenEndCityPieces.Piece var12 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, var11.b(), "bridge_end", var6.a(var11.a()), true));
/* 224 */               WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.e(), var1 + 1, var12, null, var4, var5);
/*     */             } 
/*     */           } 
/*     */           
/* 228 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 4, -1), "tower_top", var6, true));
/*     */         }
/* 230 */         else if (var1 == 7) {
/* 231 */           var7 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var7, new BlockPosition(-1, 4, -1), "tower_top", var6, true));
/*     */         } else {
/* 233 */           return WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.f(), var1 + 1, var7, null, var4, var5);
/*     */         } 
/*     */         
/* 236 */         return true;
/*     */       }
/*     */     };
/*     */   
/* 240 */   private static final PieceGenerator f = new PieceGenerator()
/*     */     {
/*     */       public boolean a;
/*     */       
/*     */       public void a() {
/* 245 */         this.a = false;
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean a(DefinedStructureManager var0, int var1, WorldGenEndCityPieces.Piece var2, BlockPosition var3, List<StructurePiece> var4, Random var5) {
/* 250 */         EnumBlockRotation var6 = var2.b.d();
/* 251 */         int var7 = var5.nextInt(4) + 1;
/*     */         
/* 253 */         WorldGenEndCityPieces.Piece var8 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var2, new BlockPosition(0, 0, -4), "bridge_piece", var6, true));
/* 254 */         var8.o = -1;
/* 255 */         int var9 = 0;
/* 256 */         for (int var10 = 0; var10 < var7; var10++) {
/* 257 */           if (var5.nextBoolean()) {
/* 258 */             var8 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, new BlockPosition(0, var9, -4), "bridge_piece", var6, true));
/* 259 */             var9 = 0;
/*     */           } else {
/* 261 */             if (var5.nextBoolean()) {
/* 262 */               var8 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, new BlockPosition(0, var9, -4), "bridge_steep_stairs", var6, true));
/*     */             } else {
/* 264 */               var8 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, new BlockPosition(0, var9, -8), "bridge_gentle_stairs", var6, true));
/*     */             } 
/* 266 */             var9 = 4;
/*     */           } 
/*     */         } 
/*     */         
/* 270 */         if (this.a || var5.nextInt(10 - var1) != 0) {
/* 271 */           if (!WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.g(), var1 + 1, var8, new BlockPosition(-3, var9 + 1, -11), var4, var5)) {
/* 272 */             return false;
/*     */           }
/*     */         } else {
/*     */           
/* 276 */           WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, new BlockPosition(-8 + var5.nextInt(8), var9, -70 + var5.nextInt(10)), "ship", var6, true));
/* 277 */           this.a = true;
/*     */         } 
/*     */ 
/*     */         
/* 281 */         var8 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var8, new BlockPosition(4, var9, 0), "bridge_end", var6.a(EnumBlockRotation.CLOCKWISE_180), true));
/* 282 */         var8.o = -1;
/*     */         
/* 284 */         return true;
/*     */       }
/*     */     };
/*     */   
/* 288 */   private static final List<Tuple<EnumBlockRotation, BlockPosition>> g = Lists.newArrayList((Object[])new Tuple[] { new Tuple<>(EnumBlockRotation.NONE, new BlockPosition(4, -1, 0)), new Tuple<>(EnumBlockRotation.CLOCKWISE_90, new BlockPosition(12, -1, 4)), new Tuple<>(EnumBlockRotation.COUNTERCLOCKWISE_90, new BlockPosition(0, -1, 8)), new Tuple<>(EnumBlockRotation.CLOCKWISE_180, new BlockPosition(8, -1, 12)) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   private static final PieceGenerator h = new PieceGenerator()
/*     */     {
/*     */       public void a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public boolean a(DefinedStructureManager var0, int var1, WorldGenEndCityPieces.Piece var2, BlockPosition var3, List<StructurePiece> var4, Random var5) {
/* 303 */         EnumBlockRotation var7 = var2.b.d();
/*     */         
/* 305 */         WorldGenEndCityPieces.Piece var6 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var2, new BlockPosition(-3, 4, -3), "fat_tower_base", var7, true));
/* 306 */         var6 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var6, new BlockPosition(0, 4, 0), "fat_tower_middle", var7, true));
/* 307 */         for (int var8 = 0; var8 < 2 && 
/* 308 */           var5.nextInt(3) != 0; var8++) {
/*     */ 
/*     */           
/* 311 */           var6 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var6, new BlockPosition(0, 8, 0), "fat_tower_middle", var7, true));
/*     */           
/* 313 */           for (Tuple<EnumBlockRotation, BlockPosition> var10 : (Iterable<Tuple<EnumBlockRotation, BlockPosition>>)WorldGenEndCityPieces.h()) {
/* 314 */             if (var5.nextBoolean()) {
/*     */               
/* 316 */               WorldGenEndCityPieces.Piece var11 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var6, var10.b(), "bridge_end", var7.a(var10.a()), true));
/* 317 */               WorldGenEndCityPieces.a(var0, WorldGenEndCityPieces.e(), var1 + 1, var11, null, var4, var5);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 322 */         var6 = WorldGenEndCityPieces.a(var4, WorldGenEndCityPieces.a(var0, var6, new BlockPosition(-2, 8, -2), "fat_tower_top", var7, true));
/* 323 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   static interface PieceGenerator {
/*     */     void a();
/*     */     
/*     */     boolean a(DefinedStructureManager param1DefinedStructureManager, int param1Int, WorldGenEndCityPieces.Piece param1Piece, BlockPosition param1BlockPosition, List<StructurePiece> param1List, Random param1Random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndCityPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */