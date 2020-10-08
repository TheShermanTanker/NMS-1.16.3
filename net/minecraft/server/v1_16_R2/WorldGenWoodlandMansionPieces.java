/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.Lists;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class WorldGenWoodlandMansionPieces
/*      */ {
/*      */   public static class i
/*      */     extends DefinedStructurePiece
/*      */   {
/*      */     private final String d;
/*      */     private final EnumBlockRotation e;
/*      */     private final EnumBlockMirror f;
/*      */     
/*      */     public i(DefinedStructureManager var0, String var1, BlockPosition var2, EnumBlockRotation var3) {
/*   39 */       this(var0, var1, var2, var3, EnumBlockMirror.NONE);
/*      */     }
/*      */     
/*      */     public i(DefinedStructureManager var0, String var1, BlockPosition var2, EnumBlockRotation var3, EnumBlockMirror var4) {
/*   43 */       super(WorldGenFeatureStructurePieceType.Z, 0);
/*      */       
/*   45 */       this.d = var1;
/*   46 */       this.c = var2;
/*   47 */       this.e = var3;
/*   48 */       this.f = var4;
/*      */       
/*   50 */       a(var0);
/*      */     }
/*      */     
/*      */     public i(DefinedStructureManager var0, NBTTagCompound var1) {
/*   54 */       super(WorldGenFeatureStructurePieceType.Z, var1);
/*      */       
/*   56 */       this.d = var1.getString("Template");
/*   57 */       this.e = EnumBlockRotation.valueOf(var1.getString("Rot"));
/*   58 */       this.f = EnumBlockMirror.valueOf(var1.getString("Mi"));
/*      */       
/*   60 */       a(var0);
/*      */     }
/*      */     
/*      */     private void a(DefinedStructureManager var0) {
/*   64 */       DefinedStructure var1 = var0.a(new MinecraftKey("woodland_mansion/" + this.d));
/*   65 */       DefinedStructureInfo var2 = (new DefinedStructureInfo()).a(true).a(this.e).a(this.f).a(DefinedStructureProcessorBlockIgnore.b);
/*      */       
/*   67 */       a(var1, this.c, var2);
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(NBTTagCompound var0) {
/*   72 */       super.a(var0);
/*      */       
/*   74 */       var0.setString("Template", this.d);
/*   75 */       var0.setString("Rot", this.b.d().name());
/*   76 */       var0.setString("Mi", this.b.c().name());
/*      */     }
/*      */ 
/*      */     
/*      */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {
/*   81 */       if (var0.startsWith("Chest")) {
/*   82 */         EnumBlockRotation var5 = this.b.d();
/*   83 */         IBlockData var6 = Blocks.CHEST.getBlockData();
/*   84 */         if ("ChestWest".equals(var0)) {
/*   85 */           var6 = var6.set(BlockChest.FACING, var5.a(EnumDirection.WEST));
/*   86 */         } else if ("ChestEast".equals(var0)) {
/*   87 */           var6 = var6.set(BlockChest.FACING, var5.a(EnumDirection.EAST));
/*   88 */         } else if ("ChestSouth".equals(var0)) {
/*   89 */           var6 = var6.set(BlockChest.FACING, var5.a(EnumDirection.SOUTH));
/*   90 */         } else if ("ChestNorth".equals(var0)) {
/*   91 */           var6 = var6.set(BlockChest.FACING, var5.a(EnumDirection.NORTH));
/*      */         } 
/*   93 */         a(var2, var4, var3, var1, LootTables.D, var6);
/*      */       } else {
/*      */         EntityIllagerAbstract var5;
/*   96 */         switch (var0) {
/*      */           case "Mage":
/*   98 */             var5 = EntityTypes.EVOKER.a(var2.getMinecraftWorld());
/*      */             break;
/*      */           case "Warrior":
/*  101 */             var5 = EntityTypes.VINDICATOR.a(var2.getMinecraftWorld());
/*      */             break;
/*      */           
/*      */           default:
/*      */             return;
/*      */         } 
/*  107 */         var5.setPersistent();
/*  108 */         var5.setPositionRotation(var1, 0.0F, 0.0F);
/*  109 */         var5.prepare(var2, var2.getDamageScaler(var5.getChunkCoordinates()), EnumMobSpawn.STRUCTURE, (GroupDataEntity)null, (NBTTagCompound)null);
/*  110 */         var2.addAllEntities(var5);
/*  111 */         var2.setTypeAndData(var1, Blocks.AIR.getBlockData(), 2);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<i> var3, Random var4) {
/*  117 */     c var5 = new c(var4);
/*  118 */     d var6 = new d(var0, var4);
/*  119 */     var6.a(var1, var2, var3, var5);
/*      */   }
/*      */   
/*      */   static class e {
/*      */     public EnumBlockRotation a;
/*      */     public BlockPosition b;
/*      */     public String c;
/*      */     
/*      */     private e() {} }
/*      */   
/*      */   static class d {
/*      */     private final DefinedStructureManager a;
/*      */     private final Random b;
/*      */     private int c;
/*      */     private int d;
/*      */     
/*      */     public d(DefinedStructureManager var0, Random var1) {
/*  136 */       this.a = var0;
/*  137 */       this.b = var1;
/*      */     }
/*      */     
/*      */     public void a(BlockPosition var0, EnumBlockRotation var1, List<WorldGenWoodlandMansionPieces.i> var2, WorldGenWoodlandMansionPieces.c var3) {
/*  141 */       WorldGenWoodlandMansionPieces.e var4 = new WorldGenWoodlandMansionPieces.e();
/*  142 */       var4.b = var0;
/*  143 */       var4.a = var1;
/*  144 */       var4.c = "wall_flat";
/*      */       
/*  146 */       WorldGenWoodlandMansionPieces.e var5 = new WorldGenWoodlandMansionPieces.e();
/*      */ 
/*      */       
/*  149 */       a(var2, var4);
/*  150 */       var5.b = var4.b.up(8);
/*  151 */       var5.a = var4.a;
/*  152 */       var5.c = "wall_window";
/*      */       
/*  154 */       if (!var2.isEmpty());
/*      */ 
/*      */ 
/*      */       
/*  158 */       WorldGenWoodlandMansionPieces.g var6 = WorldGenWoodlandMansionPieces.c.a(var3);
/*  159 */       WorldGenWoodlandMansionPieces.g var7 = WorldGenWoodlandMansionPieces.c.b(var3);
/*      */       
/*  161 */       this.c = WorldGenWoodlandMansionPieces.c.c(var3) + 1;
/*  162 */       this.d = WorldGenWoodlandMansionPieces.c.d(var3) + 1;
/*  163 */       int var8 = WorldGenWoodlandMansionPieces.c.c(var3) + 1;
/*  164 */       int var9 = WorldGenWoodlandMansionPieces.c.d(var3);
/*      */       
/*  166 */       a(var2, var4, var6, EnumDirection.SOUTH, this.c, this.d, var8, var9);
/*  167 */       a(var2, var5, var6, EnumDirection.SOUTH, this.c, this.d, var8, var9);
/*      */ 
/*      */       
/*  170 */       WorldGenWoodlandMansionPieces.e var10 = new WorldGenWoodlandMansionPieces.e();
/*  171 */       var10.b = var4.b.up(19);
/*  172 */       var10.a = var4.a;
/*  173 */       var10.c = "wall_window";
/*      */       
/*  175 */       boolean var11 = false;
/*  176 */       for (int i = 0; i < WorldGenWoodlandMansionPieces.g.a(var7) && !var11; i++) {
/*  177 */         for (int j = WorldGenWoodlandMansionPieces.g.b(var7) - 1; j >= 0 && !var11; j--) {
/*  178 */           if (WorldGenWoodlandMansionPieces.c.a(var7, j, i)) {
/*  179 */             var10.b = var10.b.shift(var1.a(EnumDirection.SOUTH), 8 + (i - this.d) * 8);
/*  180 */             var10.b = var10.b.shift(var1.a(EnumDirection.EAST), (j - this.c) * 8);
/*  181 */             b(var2, var10);
/*  182 */             a(var2, var10, var7, EnumDirection.SOUTH, j, i, j, i);
/*  183 */             var11 = true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  189 */       a(var2, var0.up(16), var1, var6, var7);
/*  190 */       a(var2, var0.up(27), var1, var7, (WorldGenWoodlandMansionPieces.g)null);
/*      */       
/*  192 */       if (!var2.isEmpty());
/*      */ 
/*      */ 
/*      */       
/*  196 */       WorldGenWoodlandMansionPieces.b[] var12 = new WorldGenWoodlandMansionPieces.b[3];
/*  197 */       var12[0] = new WorldGenWoodlandMansionPieces.a();
/*  198 */       var12[1] = new WorldGenWoodlandMansionPieces.f();
/*  199 */       var12[2] = new WorldGenWoodlandMansionPieces.h();
/*      */       
/*  201 */       for (int var13 = 0; var13 < 3; var13++) {
/*  202 */         BlockPosition var14 = var0.up(8 * var13 + ((var13 == 2) ? 3 : 0));
/*  203 */         WorldGenWoodlandMansionPieces.g var15 = WorldGenWoodlandMansionPieces.c.e(var3)[var13];
/*  204 */         WorldGenWoodlandMansionPieces.g var16 = (var13 == 2) ? var7 : var6;
/*      */ 
/*      */         
/*  207 */         String var17 = (var13 == 0) ? "carpet_south_1" : "carpet_south_2";
/*  208 */         String var18 = (var13 == 0) ? "carpet_west_1" : "carpet_west_2";
/*  209 */         for (int j = 0; j < WorldGenWoodlandMansionPieces.g.a(var16); j++) {
/*  210 */           for (int k = 0; k < WorldGenWoodlandMansionPieces.g.b(var16); k++) {
/*  211 */             if (var16.a(k, j) == 1) {
/*  212 */               BlockPosition blockPosition = var14.shift(var1.a(EnumDirection.SOUTH), 8 + (j - this.d) * 8);
/*  213 */               blockPosition = blockPosition.shift(var1.a(EnumDirection.EAST), (k - this.c) * 8);
/*  214 */               var2.add(new WorldGenWoodlandMansionPieces.i(this.a, "corridor_floor", blockPosition, var1));
/*      */               
/*  216 */               if (var16.a(k, j - 1) == 1 || (var15.a(k, j - 1) & 0x800000) == 8388608) {
/*  217 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, "carpet_north", blockPosition.shift(var1.a(EnumDirection.EAST), 1).up(), var1));
/*      */               }
/*  219 */               if (var16.a(k + 1, j) == 1 || (var15.a(k + 1, j) & 0x800000) == 8388608) {
/*  220 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, "carpet_east", blockPosition.shift(var1.a(EnumDirection.SOUTH), 1).shift(var1.a(EnumDirection.EAST), 5).up(), var1));
/*      */               }
/*  222 */               if (var16.a(k, j + 1) == 1 || (var15.a(k, j + 1) & 0x800000) == 8388608) {
/*  223 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, var17, blockPosition.shift(var1.a(EnumDirection.SOUTH), 5).shift(var1.a(EnumDirection.WEST), 1), var1));
/*      */               }
/*  225 */               if (var16.a(k - 1, j) == 1 || (var15.a(k - 1, j) & 0x800000) == 8388608) {
/*  226 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, var18, blockPosition.shift(var1.a(EnumDirection.WEST), 1).shift(var1.a(EnumDirection.NORTH), 1), var1));
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  232 */         String var19 = (var13 == 0) ? "indoors_wall_1" : "indoors_wall_2";
/*  233 */         String var20 = (var13 == 0) ? "indoors_door_1" : "indoors_door_2";
/*  234 */         List<EnumDirection> var21 = Lists.newArrayList();
/*  235 */         for (int var22 = 0; var22 < WorldGenWoodlandMansionPieces.g.a(var16); var22++) {
/*  236 */           for (int var23 = 0; var23 < WorldGenWoodlandMansionPieces.g.b(var16); var23++) {
/*  237 */             boolean var24 = (var13 == 2 && var16.a(var23, var22) == 3);
/*  238 */             if (var16.a(var23, var22) == 2 || var24) {
/*  239 */               int var25 = var15.a(var23, var22);
/*  240 */               int var26 = var25 & 0xF0000;
/*  241 */               int var27 = var25 & 0xFFFF;
/*      */ 
/*      */               
/*  244 */               var24 = (var24 && (var25 & 0x800000) == 8388608);
/*      */               
/*  246 */               var21.clear();
/*  247 */               if ((var25 & 0x200000) == 2097152) {
/*  248 */                 for (EnumDirection enumDirection : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*  249 */                   if (var16.a(var23 + enumDirection.getAdjacentX(), var22 + enumDirection.getAdjacentZ()) == 1) {
/*  250 */                     var21.add(enumDirection);
/*      */                   }
/*      */                 } 
/*      */               }
/*  254 */               EnumDirection var28 = null;
/*  255 */               if (!var21.isEmpty()) {
/*  256 */                 var28 = var21.get(this.b.nextInt(var21.size()));
/*  257 */               } else if ((var25 & 0x100000) == 1048576) {
/*      */                 
/*  259 */                 var28 = EnumDirection.UP;
/*      */               } 
/*      */               
/*  262 */               BlockPosition var29 = var14.shift(var1.a(EnumDirection.SOUTH), 8 + (var22 - this.d) * 8);
/*  263 */               var29 = var29.shift(var1.a(EnumDirection.EAST), -1 + (var23 - this.c) * 8);
/*      */               
/*  265 */               if (WorldGenWoodlandMansionPieces.c.a(var16, var23 - 1, var22) && !var3.a(var16, var23 - 1, var22, var13, var27)) {
/*  266 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, (var28 == EnumDirection.WEST) ? var20 : var19, var29, var1));
/*      */               }
/*  268 */               if (var16.a(var23 + 1, var22) == 1 && !var24) {
/*  269 */                 BlockPosition var30 = var29.shift(var1.a(EnumDirection.EAST), 8);
/*  270 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, (var28 == EnumDirection.EAST) ? var20 : var19, var30, var1));
/*      */               } 
/*  272 */               if (WorldGenWoodlandMansionPieces.c.a(var16, var23, var22 + 1) && !var3.a(var16, var23, var22 + 1, var13, var27)) {
/*  273 */                 BlockPosition var30 = var29.shift(var1.a(EnumDirection.SOUTH), 7);
/*  274 */                 var30 = var30.shift(var1.a(EnumDirection.EAST), 7);
/*  275 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, (var28 == EnumDirection.SOUTH) ? var20 : var19, var30, var1.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */               } 
/*  277 */               if (var16.a(var23, var22 - 1) == 1 && !var24) {
/*  278 */                 BlockPosition var30 = var29.shift(var1.a(EnumDirection.NORTH), 1);
/*  279 */                 var30 = var30.shift(var1.a(EnumDirection.EAST), 7);
/*  280 */                 var2.add(new WorldGenWoodlandMansionPieces.i(this.a, (var28 == EnumDirection.NORTH) ? var20 : var19, var30, var1.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */               } 
/*      */               
/*  283 */               if (var26 == 65536) {
/*  284 */                 a(var2, var29, var1, var28, var12[var13]);
/*  285 */               } else if (var26 == 131072 && var28 != null) {
/*      */                 
/*  287 */                 EnumDirection var30 = var3.b(var16, var23, var22, var13, var27);
/*  288 */                 boolean var31 = ((var25 & 0x400000) == 4194304);
/*  289 */                 a(var2, var29, var1, var30, var28, var12[var13], var31);
/*  290 */               } else if (var26 == 262144 && var28 != null && var28 != EnumDirection.UP) {
/*      */                 
/*  292 */                 EnumDirection var30 = var28.g();
/*  293 */                 if (!var3.a(var16, var23 + var30.getAdjacentX(), var22 + var30.getAdjacentZ(), var13, var27)) {
/*  294 */                   var30 = var30.opposite();
/*      */                 }
/*  296 */                 a(var2, var29, var1, var30, var28, var12[var13]);
/*  297 */               } else if (var26 == 262144 && var28 == EnumDirection.UP) {
/*  298 */                 a(var2, var29, var1, var12[var13]);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, WorldGenWoodlandMansionPieces.e var1, WorldGenWoodlandMansionPieces.g var2, EnumDirection var3, int var4, int var5, int var6, int var7) {
/*  307 */       int var8 = var4;
/*  308 */       int var9 = var5;
/*  309 */       EnumDirection var10 = var3;
/*      */       
/*      */       do {
/*  312 */         if (!WorldGenWoodlandMansionPieces.c.a(var2, var8 + var3.getAdjacentX(), var9 + var3.getAdjacentZ())) {
/*      */           
/*  314 */           c(var0, var1);
/*  315 */           var3 = var3.g();
/*  316 */           if (var8 != var6 || var9 != var7 || var10 != var3) {
/*  317 */             b(var0, var1);
/*      */           }
/*  319 */         } else if (WorldGenWoodlandMansionPieces.c.a(var2, var8 + var3.getAdjacentX(), var9 + var3.getAdjacentZ()) && WorldGenWoodlandMansionPieces.c.a(var2, var8 + var3.getAdjacentX() + var3.h().getAdjacentX(), var9 + var3.getAdjacentZ() + var3.h().getAdjacentZ())) {
/*      */           
/*  321 */           d(var0, var1);
/*  322 */           var8 += var3.getAdjacentX();
/*  323 */           var9 += var3.getAdjacentZ();
/*  324 */           var3 = var3.h();
/*      */         } else {
/*  326 */           var8 += var3.getAdjacentX();
/*  327 */           var9 += var3.getAdjacentZ();
/*  328 */           if (var8 != var6 || var9 != var7 || var10 != var3) {
/*  329 */             b(var0, var1);
/*      */           }
/*      */         } 
/*  332 */       } while (var8 != var6 || var9 != var7 || var10 != var3);
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, BlockPosition var1, EnumBlockRotation var2, WorldGenWoodlandMansionPieces.g var3, @Nullable WorldGenWoodlandMansionPieces.g var4) {
/*      */       int var5;
/*  337 */       for (var5 = 0; var5 < WorldGenWoodlandMansionPieces.g.a(var3); var5++) {
/*  338 */         for (int var6 = 0; var6 < WorldGenWoodlandMansionPieces.g.b(var3); var6++) {
/*  339 */           BlockPosition var7 = var1;
/*  340 */           var7 = var7.shift(var2.a(EnumDirection.SOUTH), 8 + (var5 - this.d) * 8);
/*  341 */           var7 = var7.shift(var2.a(EnumDirection.EAST), (var6 - this.c) * 8);
/*      */ 
/*      */           
/*  344 */           boolean var8 = (var4 != null && WorldGenWoodlandMansionPieces.c.a(var4, var6, var5));
/*      */           
/*  346 */           if (WorldGenWoodlandMansionPieces.c.a(var3, var6, var5) && !var8) {
/*  347 */             var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof", var7.up(3), var2));
/*      */             
/*  349 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5)) {
/*  350 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 6);
/*  351 */               var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_front", var9, var2));
/*      */             } 
/*  353 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5)) {
/*  354 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 0);
/*  355 */               var9 = var9.shift(var2.a(EnumDirection.SOUTH), 7);
/*  356 */               var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_front", var9, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*      */             } 
/*  358 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  359 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.WEST), 1);
/*  360 */               var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_front", var9, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*      */             } 
/*  362 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  363 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 6);
/*  364 */               var9 = var9.shift(var2.a(EnumDirection.SOUTH), 6);
/*  365 */               var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_front", var9, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  371 */       if (var4 != null) {
/*  372 */         for (var5 = 0; var5 < WorldGenWoodlandMansionPieces.g.a(var3); var5++) {
/*  373 */           for (int var6 = 0; var6 < WorldGenWoodlandMansionPieces.g.b(var3); var6++) {
/*  374 */             BlockPosition var7 = var1;
/*  375 */             var7 = var7.shift(var2.a(EnumDirection.SOUTH), 8 + (var5 - this.d) * 8);
/*  376 */             var7 = var7.shift(var2.a(EnumDirection.EAST), (var6 - this.c) * 8);
/*      */ 
/*      */             
/*  379 */             boolean var8 = WorldGenWoodlandMansionPieces.c.a(var4, var6, var5);
/*      */             
/*  381 */             if (WorldGenWoodlandMansionPieces.c.a(var3, var6, var5) && var8) {
/*      */               
/*  383 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5)) {
/*  384 */                 BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 7);
/*  385 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall", var9, var2));
/*      */               } 
/*  387 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5)) {
/*  388 */                 BlockPosition var9 = var7.shift(var2.a(EnumDirection.WEST), 1);
/*  389 */                 var9 = var9.shift(var2.a(EnumDirection.SOUTH), 6);
/*  390 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall", var9, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*      */               } 
/*  392 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  393 */                 BlockPosition var9 = var7.shift(var2.a(EnumDirection.WEST), 0);
/*  394 */                 var9 = var9.shift(var2.a(EnumDirection.NORTH), 1);
/*  395 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall", var9, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*      */               } 
/*  397 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  398 */                 BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 6);
/*  399 */                 var9 = var9.shift(var2.a(EnumDirection.SOUTH), 7);
/*  400 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall", var9, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */               } 
/*      */               
/*  403 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5)) {
/*  404 */                 if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  405 */                   BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 7);
/*  406 */                   var9 = var9.shift(var2.a(EnumDirection.NORTH), 2);
/*  407 */                   var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall_corner", var9, var2));
/*      */                 } 
/*  409 */                 if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  410 */                   BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 8);
/*  411 */                   var9 = var9.shift(var2.a(EnumDirection.SOUTH), 7);
/*  412 */                   var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall_corner", var9, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */                 } 
/*      */               } 
/*  415 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5)) {
/*  416 */                 if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  417 */                   BlockPosition var9 = var7.shift(var2.a(EnumDirection.WEST), 2);
/*  418 */                   var9 = var9.shift(var2.a(EnumDirection.NORTH), 1);
/*  419 */                   var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall_corner", var9, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*      */                 } 
/*  421 */                 if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  422 */                   BlockPosition var9 = var7.shift(var2.a(EnumDirection.WEST), 1);
/*  423 */                   var9 = var9.shift(var2.a(EnumDirection.SOUTH), 8);
/*  424 */                   var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "small_wall_corner", var9, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  432 */       for (var5 = 0; var5 < WorldGenWoodlandMansionPieces.g.a(var3); var5++) {
/*  433 */         for (int var6 = 0; var6 < WorldGenWoodlandMansionPieces.g.b(var3); var6++) {
/*  434 */           BlockPosition var7 = var1;
/*  435 */           var7 = var7.shift(var2.a(EnumDirection.SOUTH), 8 + (var5 - this.d) * 8);
/*  436 */           var7 = var7.shift(var2.a(EnumDirection.EAST), (var6 - this.c) * 8);
/*      */ 
/*      */           
/*  439 */           boolean var8 = (var4 != null && WorldGenWoodlandMansionPieces.c.a(var4, var6, var5));
/*      */           
/*  441 */           if (WorldGenWoodlandMansionPieces.c.a(var3, var6, var5) && !var8) {
/*  442 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5)) {
/*  443 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 6);
/*  444 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  445 */                 BlockPosition var10 = var9.shift(var2.a(EnumDirection.SOUTH), 6);
/*  446 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_corner", var10, var2));
/*  447 */               } else if (WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5 + 1)) {
/*  448 */                 BlockPosition var10 = var9.shift(var2.a(EnumDirection.SOUTH), 5);
/*  449 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_inner_corner", var10, var2));
/*      */               } 
/*  451 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  452 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_corner", var9, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*  453 */               } else if (WorldGenWoodlandMansionPieces.c.a(var3, var6 + 1, var5 - 1)) {
/*  454 */                 BlockPosition var10 = var7.shift(var2.a(EnumDirection.EAST), 9);
/*  455 */                 var10 = var10.shift(var2.a(EnumDirection.NORTH), 2);
/*  456 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_inner_corner", var10, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*      */               } 
/*      */             } 
/*  459 */             if (!WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5)) {
/*  460 */               BlockPosition var9 = var7.shift(var2.a(EnumDirection.EAST), 0);
/*  461 */               var9 = var9.shift(var2.a(EnumDirection.SOUTH), 0);
/*  462 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 + 1)) {
/*  463 */                 BlockPosition var10 = var9.shift(var2.a(EnumDirection.SOUTH), 6);
/*  464 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_corner", var10, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*  465 */               } else if (WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5 + 1)) {
/*  466 */                 BlockPosition var10 = var9.shift(var2.a(EnumDirection.SOUTH), 8);
/*  467 */                 var10 = var10.shift(var2.a(EnumDirection.WEST), 3);
/*  468 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_inner_corner", var10, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*      */               } 
/*  470 */               if (!WorldGenWoodlandMansionPieces.c.a(var3, var6, var5 - 1)) {
/*  471 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_corner", var9, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*  472 */               } else if (WorldGenWoodlandMansionPieces.c.a(var3, var6 - 1, var5 - 1)) {
/*  473 */                 BlockPosition var10 = var9.shift(var2.a(EnumDirection.SOUTH), 1);
/*  474 */                 var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "roof_inner_corner", var10, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, WorldGenWoodlandMansionPieces.e var1) {
/*  483 */       EnumDirection var2 = var1.a.a(EnumDirection.WEST);
/*  484 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "entrance", var1.b.shift(var2, 9), var1.a));
/*  485 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.SOUTH), 16);
/*      */     }
/*      */     
/*      */     private void b(List<WorldGenWoodlandMansionPieces.i> var0, WorldGenWoodlandMansionPieces.e var1) {
/*  489 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var1.c, var1.b.shift(var1.a.a(EnumDirection.EAST), 7), var1.a));
/*  490 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.SOUTH), 8);
/*      */     }
/*      */     
/*      */     private void c(List<WorldGenWoodlandMansionPieces.i> var0, WorldGenWoodlandMansionPieces.e var1) {
/*  494 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.SOUTH), -1);
/*  495 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, "wall_corner", var1.b, var1.a));
/*  496 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.SOUTH), -7);
/*  497 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.WEST), -6);
/*  498 */       var1.a = var1.a.a(EnumBlockRotation.CLOCKWISE_90);
/*      */     }
/*      */     
/*      */     private void d(List<WorldGenWoodlandMansionPieces.i> var0, WorldGenWoodlandMansionPieces.e var1) {
/*  502 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.SOUTH), 6);
/*  503 */       var1.b = var1.b.shift(var1.a.a(EnumDirection.EAST), 8);
/*  504 */       var1.a = var1.a.a(EnumBlockRotation.COUNTERCLOCKWISE_90);
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, BlockPosition var1, EnumBlockRotation var2, EnumDirection var3, WorldGenWoodlandMansionPieces.b var4) {
/*  508 */       EnumBlockRotation var5 = EnumBlockRotation.NONE;
/*  509 */       String var6 = var4.a(this.b);
/*  510 */       if (var3 != EnumDirection.EAST) {
/*  511 */         if (var3 == EnumDirection.NORTH) {
/*  512 */           var5 = var5.a(EnumBlockRotation.COUNTERCLOCKWISE_90);
/*  513 */         } else if (var3 == EnumDirection.WEST) {
/*  514 */           var5 = var5.a(EnumBlockRotation.CLOCKWISE_180);
/*  515 */         } else if (var3 == EnumDirection.SOUTH) {
/*  516 */           var5 = var5.a(EnumBlockRotation.CLOCKWISE_90);
/*      */         } else {
/*      */           
/*  519 */           var6 = var4.b(this.b);
/*      */         } 
/*      */       }
/*  522 */       BlockPosition var7 = DefinedStructure.a(new BlockPosition(1, 0, 0), EnumBlockMirror.NONE, var5, 7, 7);
/*  523 */       var5 = var5.a(var2);
/*  524 */       var7 = var7.a(var2);
/*  525 */       BlockPosition var8 = var1.b(var7.getX(), 0, var7.getZ());
/*  526 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var6, var8, var5));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, BlockPosition var1, EnumBlockRotation var2, EnumDirection var3, EnumDirection var4, WorldGenWoodlandMansionPieces.b var5, boolean var6) {
/*  533 */       if (var4 == EnumDirection.EAST && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */         
/*  536 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  537 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2));
/*  538 */       } else if (var4 == EnumDirection.EAST && var3 == EnumDirection.NORTH) {
/*      */ 
/*      */         
/*  541 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  542 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 6);
/*  543 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2, EnumBlockMirror.LEFT_RIGHT));
/*  544 */       } else if (var4 == EnumDirection.WEST && var3 == EnumDirection.NORTH) {
/*      */ 
/*      */         
/*  547 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 7);
/*  548 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 6);
/*  549 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*  550 */       } else if (var4 == EnumDirection.WEST && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */         
/*  553 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 7);
/*  554 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2, EnumBlockMirror.FRONT_BACK));
/*  555 */       } else if (var4 == EnumDirection.SOUTH && var3 == EnumDirection.EAST) {
/*      */ 
/*      */         
/*  558 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  559 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_90), EnumBlockMirror.LEFT_RIGHT));
/*  560 */       } else if (var4 == EnumDirection.SOUTH && var3 == EnumDirection.WEST) {
/*      */ 
/*      */         
/*  563 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 7);
/*  564 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*  565 */       } else if (var4 == EnumDirection.NORTH && var3 == EnumDirection.WEST) {
/*      */ 
/*      */         
/*  568 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 7);
/*  569 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 6);
/*  570 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_90), EnumBlockMirror.FRONT_BACK));
/*  571 */       } else if (var4 == EnumDirection.NORTH && var3 == EnumDirection.EAST) {
/*      */ 
/*      */         
/*  574 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  575 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 6);
/*  576 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.a(this.b, var6), var7, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*  577 */       } else if (var4 == EnumDirection.SOUTH && var3 == EnumDirection.NORTH) {
/*      */ 
/*      */ 
/*      */         
/*  581 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  582 */         var7 = var7.shift(var2.a(EnumDirection.NORTH), 8);
/*  583 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.b(this.b, var6), var7, var2));
/*  584 */       } else if (var4 == EnumDirection.NORTH && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */ 
/*      */         
/*  588 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 7);
/*  589 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 14);
/*  590 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.b(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_180)));
/*  591 */       } else if (var4 == EnumDirection.WEST && var3 == EnumDirection.EAST) {
/*      */         
/*  593 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 15);
/*  594 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.b(this.b, var6), var7, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*  595 */       } else if (var4 == EnumDirection.EAST && var3 == EnumDirection.WEST) {
/*      */         
/*  597 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.WEST), 7);
/*  598 */         var7 = var7.shift(var2.a(EnumDirection.SOUTH), 6);
/*  599 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.b(this.b, var6), var7, var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90)));
/*  600 */       } else if (var4 == EnumDirection.UP && var3 == EnumDirection.EAST) {
/*      */         
/*  602 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 15);
/*  603 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.c(this.b), var7, var2.a(EnumBlockRotation.CLOCKWISE_90)));
/*  604 */       } else if (var4 == EnumDirection.UP && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */ 
/*      */         
/*  608 */         BlockPosition var7 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  609 */         var7 = var7.shift(var2.a(EnumDirection.NORTH), 0);
/*  610 */         var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.c(this.b), var7, var2));
/*      */       } 
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, BlockPosition var1, EnumBlockRotation var2, EnumDirection var3, EnumDirection var4, WorldGenWoodlandMansionPieces.b var5) {
/*  615 */       int var6 = 0;
/*  616 */       int var7 = 0;
/*  617 */       EnumBlockRotation var8 = var2;
/*  618 */       EnumBlockMirror var9 = EnumBlockMirror.NONE;
/*      */ 
/*      */ 
/*      */       
/*  622 */       if (var4 == EnumDirection.EAST && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */         
/*  625 */         var6 = -7;
/*  626 */       } else if (var4 == EnumDirection.EAST && var3 == EnumDirection.NORTH) {
/*      */ 
/*      */         
/*  629 */         var6 = -7;
/*  630 */         var7 = 6;
/*  631 */         var9 = EnumBlockMirror.LEFT_RIGHT;
/*  632 */       } else if (var4 == EnumDirection.NORTH && var3 == EnumDirection.EAST) {
/*      */ 
/*      */ 
/*      */         
/*  636 */         var6 = 1;
/*  637 */         var7 = 14;
/*  638 */         var8 = var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90);
/*  639 */       } else if (var4 == EnumDirection.NORTH && var3 == EnumDirection.WEST) {
/*      */ 
/*      */ 
/*      */         
/*  643 */         var6 = 7;
/*  644 */         var7 = 14;
/*  645 */         var8 = var2.a(EnumBlockRotation.COUNTERCLOCKWISE_90);
/*  646 */         var9 = EnumBlockMirror.LEFT_RIGHT;
/*  647 */       } else if (var4 == EnumDirection.SOUTH && var3 == EnumDirection.WEST) {
/*      */ 
/*      */ 
/*      */         
/*  651 */         var6 = 7;
/*  652 */         var7 = -8;
/*  653 */         var8 = var2.a(EnumBlockRotation.CLOCKWISE_90);
/*  654 */       } else if (var4 == EnumDirection.SOUTH && var3 == EnumDirection.EAST) {
/*      */ 
/*      */ 
/*      */         
/*  658 */         var6 = 1;
/*  659 */         var7 = -8;
/*  660 */         var8 = var2.a(EnumBlockRotation.CLOCKWISE_90);
/*  661 */         var9 = EnumBlockMirror.LEFT_RIGHT;
/*  662 */       } else if (var4 == EnumDirection.WEST && var3 == EnumDirection.NORTH) {
/*      */ 
/*      */         
/*  665 */         var6 = 15;
/*  666 */         var7 = 6;
/*  667 */         var8 = var2.a(EnumBlockRotation.CLOCKWISE_180);
/*  668 */       } else if (var4 == EnumDirection.WEST && var3 == EnumDirection.SOUTH) {
/*      */ 
/*      */         
/*  671 */         var6 = 15;
/*  672 */         var9 = EnumBlockMirror.FRONT_BACK;
/*      */       } 
/*      */       
/*  675 */       BlockPosition var10 = var1.shift(var2.a(EnumDirection.EAST), var6);
/*  676 */       var10 = var10.shift(var2.a(EnumDirection.SOUTH), var7);
/*  677 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var5.d(this.b), var10, var8, var9));
/*      */     }
/*      */     
/*      */     private void a(List<WorldGenWoodlandMansionPieces.i> var0, BlockPosition var1, EnumBlockRotation var2, WorldGenWoodlandMansionPieces.b var3) {
/*  681 */       BlockPosition var4 = var1.shift(var2.a(EnumDirection.EAST), 1);
/*  682 */       var0.add(new WorldGenWoodlandMansionPieces.i(this.a, var3.e(this.b), var4, var2, EnumBlockMirror.NONE));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class c
/*      */   {
/*      */     private final Random a;
/*      */ 
/*      */ 
/*      */     
/*      */     private final WorldGenWoodlandMansionPieces.g b;
/*      */ 
/*      */ 
/*      */     
/*      */     private final WorldGenWoodlandMansionPieces.g c;
/*      */ 
/*      */ 
/*      */     
/*      */     private final WorldGenWoodlandMansionPieces.g[] d;
/*      */ 
/*      */ 
/*      */     
/*      */     private final int e;
/*      */ 
/*      */ 
/*      */     
/*      */     private final int f;
/*      */ 
/*      */ 
/*      */     
/*      */     public c(Random var0) {
/*  716 */       this.a = var0;
/*      */       
/*  718 */       int var1 = 11;
/*  719 */       this.e = 7;
/*  720 */       this.f = 4;
/*      */       
/*  722 */       this.b = new WorldGenWoodlandMansionPieces.g(11, 11, 5);
/*  723 */       this.b.a(this.e, this.f, this.e + 1, this.f + 1, 3);
/*  724 */       this.b.a(this.e - 1, this.f, this.e - 1, this.f + 1, 2);
/*  725 */       this.b.a(this.e + 2, this.f - 2, this.e + 3, this.f + 3, 5);
/*  726 */       this.b.a(this.e + 1, this.f - 2, this.e + 1, this.f - 1, 1);
/*  727 */       this.b.a(this.e + 1, this.f + 2, this.e + 1, this.f + 3, 1);
/*  728 */       this.b.a(this.e - 1, this.f - 1, 1);
/*  729 */       this.b.a(this.e - 1, this.f + 2, 1);
/*      */       
/*  731 */       this.b.a(0, 0, 11, 1, 5);
/*  732 */       this.b.a(0, 9, 11, 11, 5);
/*      */       
/*  734 */       a(this.b, this.e, this.f - 2, EnumDirection.WEST, 6);
/*  735 */       a(this.b, this.e, this.f + 3, EnumDirection.WEST, 6);
/*  736 */       a(this.b, this.e - 2, this.f - 1, EnumDirection.WEST, 3);
/*  737 */       a(this.b, this.e - 2, this.f + 2, EnumDirection.WEST, 3);
/*  738 */       while (a(this.b));
/*      */ 
/*      */       
/*  741 */       this.d = new WorldGenWoodlandMansionPieces.g[3];
/*  742 */       this.d[0] = new WorldGenWoodlandMansionPieces.g(11, 11, 5);
/*  743 */       this.d[1] = new WorldGenWoodlandMansionPieces.g(11, 11, 5);
/*  744 */       this.d[2] = new WorldGenWoodlandMansionPieces.g(11, 11, 5);
/*  745 */       a(this.b, this.d[0]);
/*  746 */       a(this.b, this.d[1]);
/*      */ 
/*      */       
/*  749 */       this.d[0].a(this.e + 1, this.f, this.e + 1, this.f + 1, 8388608);
/*  750 */       this.d[1].a(this.e + 1, this.f, this.e + 1, this.f + 1, 8388608);
/*      */       
/*  752 */       this.c = new WorldGenWoodlandMansionPieces.g(WorldGenWoodlandMansionPieces.g.b(this.b), WorldGenWoodlandMansionPieces.g.a(this.b), 5);
/*  753 */       b();
/*  754 */       a(this.c, this.d[2]);
/*      */     }
/*      */     
/*      */     public static boolean a(WorldGenWoodlandMansionPieces.g var0, int var1, int var2) {
/*  758 */       int var3 = var0.a(var1, var2);
/*  759 */       return (var3 == 1 || var3 == 2 || var3 == 3 || var3 == 4);
/*      */     }
/*      */     
/*      */     public boolean a(WorldGenWoodlandMansionPieces.g var0, int var1, int var2, int var3, int var4) {
/*  763 */       return ((this.d[var3].a(var1, var2) & 0xFFFF) == var4);
/*      */     }
/*      */     
/*      */     @Nullable
/*      */     public EnumDirection b(WorldGenWoodlandMansionPieces.g var0, int var1, int var2, int var3, int var4) {
/*  768 */       for (EnumDirection var6 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*  769 */         if (a(var0, var1 + var6.getAdjacentX(), var2 + var6.getAdjacentZ(), var3, var4)) {
/*  770 */           return var6;
/*      */         }
/*      */       } 
/*  773 */       return null;
/*      */     }
/*      */     
/*      */     private void a(WorldGenWoodlandMansionPieces.g var0, int var1, int var2, EnumDirection var3, int var4) {
/*  777 */       if (var4 <= 0) {
/*      */         return;
/*      */       }
/*      */       
/*  781 */       var0.a(var1, var2, 1);
/*  782 */       var0.a(var1 + var3.getAdjacentX(), var2 + var3.getAdjacentZ(), 0, 1);
/*      */       
/*  784 */       for (int i = 0; i < 8; i++) {
/*  785 */         EnumDirection enumDirection = EnumDirection.fromType2(this.a.nextInt(4));
/*  786 */         if (enumDirection != var3.opposite())
/*      */         {
/*      */           
/*  789 */           if (enumDirection != EnumDirection.EAST || !this.a.nextBoolean()) {
/*      */ 
/*      */ 
/*      */             
/*  793 */             int var7 = var1 + var3.getAdjacentX();
/*  794 */             int var8 = var2 + var3.getAdjacentZ();
/*  795 */             if (var0.a(var7 + enumDirection.getAdjacentX(), var8 + enumDirection.getAdjacentZ()) == 0 && var0.a(var7 + enumDirection.getAdjacentX() * 2, var8 + enumDirection.getAdjacentZ() * 2) == 0) {
/*  796 */               a(var0, var1 + var3.getAdjacentX() + enumDirection.getAdjacentX(), var2 + var3.getAdjacentZ() + enumDirection.getAdjacentZ(), enumDirection, var4 - 1); break;
/*      */             } 
/*      */           }  } 
/*      */       } 
/*  800 */       EnumDirection var5 = var3.g();
/*  801 */       EnumDirection var6 = var3.h();
/*  802 */       var0.a(var1 + var5.getAdjacentX(), var2 + var5.getAdjacentZ(), 0, 2);
/*  803 */       var0.a(var1 + var6.getAdjacentX(), var2 + var6.getAdjacentZ(), 0, 2);
/*      */       
/*  805 */       var0.a(var1 + var3.getAdjacentX() + var5.getAdjacentX(), var2 + var3.getAdjacentZ() + var5.getAdjacentZ(), 0, 2);
/*  806 */       var0.a(var1 + var3.getAdjacentX() + var6.getAdjacentX(), var2 + var3.getAdjacentZ() + var6.getAdjacentZ(), 0, 2);
/*  807 */       var0.a(var1 + var3.getAdjacentX() * 2, var2 + var3.getAdjacentZ() * 2, 0, 2);
/*  808 */       var0.a(var1 + var5.getAdjacentX() * 2, var2 + var5.getAdjacentZ() * 2, 0, 2);
/*  809 */       var0.a(var1 + var6.getAdjacentX() * 2, var2 + var6.getAdjacentZ() * 2, 0, 2);
/*      */     }
/*      */     
/*      */     private boolean a(WorldGenWoodlandMansionPieces.g var0) {
/*  813 */       boolean var1 = false;
/*  814 */       for (int var2 = 0; var2 < WorldGenWoodlandMansionPieces.g.a(var0); var2++) {
/*  815 */         for (int var3 = 0; var3 < WorldGenWoodlandMansionPieces.g.b(var0); var3++) {
/*  816 */           if (var0.a(var3, var2) == 0) {
/*  817 */             int var4 = 0;
/*  818 */             var4 += a(var0, var3 + 1, var2) ? 1 : 0;
/*  819 */             var4 += a(var0, var3 - 1, var2) ? 1 : 0;
/*  820 */             var4 += a(var0, var3, var2 + 1) ? 1 : 0;
/*  821 */             var4 += a(var0, var3, var2 - 1) ? 1 : 0;
/*      */             
/*  823 */             if (var4 >= 3) {
/*      */               
/*  825 */               var0.a(var3, var2, 2);
/*  826 */               var1 = true;
/*  827 */             } else if (var4 == 2) {
/*      */               
/*  829 */               int var5 = 0;
/*  830 */               var5 += a(var0, var3 + 1, var2 + 1) ? 1 : 0;
/*  831 */               var5 += a(var0, var3 - 1, var2 + 1) ? 1 : 0;
/*  832 */               var5 += a(var0, var3 + 1, var2 - 1) ? 1 : 0;
/*  833 */               var5 += a(var0, var3 - 1, var2 - 1) ? 1 : 0;
/*  834 */               if (var5 <= 1) {
/*  835 */                 var0.a(var3, var2, 2);
/*  836 */                 var1 = true;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  842 */       return var1;
/*      */     }
/*      */ 
/*      */     
/*      */     private void b() {
/*  847 */       List<Tuple<Integer, Integer>> var0 = Lists.newArrayList();
/*  848 */       WorldGenWoodlandMansionPieces.g var1 = this.d[1];
/*  849 */       for (int i = 0; i < WorldGenWoodlandMansionPieces.g.a(this.c); i++) {
/*  850 */         for (int k = 0; k < WorldGenWoodlandMansionPieces.g.b(this.c); k++) {
/*  851 */           int m = var1.a(k, i);
/*  852 */           int n = m & 0xF0000;
/*  853 */           if (n == 131072 && (m & 0x200000) == 2097152) {
/*  854 */             var0.add(new Tuple<>(Integer.valueOf(k), Integer.valueOf(i)));
/*      */           }
/*      */         } 
/*      */       } 
/*      */       
/*  859 */       if (var0.isEmpty()) {
/*      */         
/*  861 */         this.c.a(0, 0, WorldGenWoodlandMansionPieces.g.b(this.c), WorldGenWoodlandMansionPieces.g.a(this.c), 5);
/*      */         
/*      */         return;
/*      */       } 
/*  865 */       Tuple<Integer, Integer> var2 = var0.get(this.a.nextInt(var0.size()));
/*  866 */       int var3 = var1.a(((Integer)var2.a()).intValue(), ((Integer)var2.b()).intValue());
/*  867 */       var1.a(((Integer)var2.a()).intValue(), ((Integer)var2.b()).intValue(), var3 | 0x400000);
/*  868 */       EnumDirection var4 = b(this.b, ((Integer)var2.a()).intValue(), ((Integer)var2.b()).intValue(), 1, var3 & 0xFFFF);
/*  869 */       int var5 = ((Integer)var2.a()).intValue() + var4.getAdjacentX();
/*  870 */       int var6 = ((Integer)var2.b()).intValue() + var4.getAdjacentZ();
/*      */       
/*  872 */       for (int j = 0; j < WorldGenWoodlandMansionPieces.g.a(this.c); j++) {
/*  873 */         for (int k = 0; k < WorldGenWoodlandMansionPieces.g.b(this.c); k++) {
/*  874 */           if (!a(this.b, k, j)) {
/*  875 */             this.c.a(k, j, 5);
/*  876 */           } else if (k == ((Integer)var2.a()).intValue() && j == ((Integer)var2.b()).intValue()) {
/*  877 */             this.c.a(k, j, 3);
/*  878 */           } else if (k == var5 && j == var6) {
/*  879 */             this.c.a(k, j, 3);
/*  880 */             this.d[2].a(k, j, 8388608);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  885 */       List<EnumDirection> var7 = Lists.newArrayList();
/*  886 */       for (EnumDirection var9 : EnumDirection.EnumDirectionLimit.HORIZONTAL) {
/*  887 */         if (this.c.a(var5 + var9.getAdjacentX(), var6 + var9.getAdjacentZ()) == 0) {
/*  888 */           var7.add(var9);
/*      */         }
/*      */       } 
/*      */       
/*  892 */       if (var7.isEmpty()) {
/*      */         
/*  894 */         this.c.a(0, 0, WorldGenWoodlandMansionPieces.g.b(this.c), WorldGenWoodlandMansionPieces.g.a(this.c), 5);
/*  895 */         var1.a(((Integer)var2.a()).intValue(), ((Integer)var2.b()).intValue(), var3);
/*      */         return;
/*      */       } 
/*  898 */       EnumDirection var8 = var7.get(this.a.nextInt(var7.size()));
/*  899 */       a(this.c, var5 + var8.getAdjacentX(), var6 + var8.getAdjacentZ(), var8, 4);
/*  900 */       while (a(this.c));
/*      */     }
/*      */ 
/*      */     
/*      */     private void a(WorldGenWoodlandMansionPieces.g var0, WorldGenWoodlandMansionPieces.g var1) {
/*  905 */       List<Tuple<Integer, Integer>> var2 = Lists.newArrayList(); int var3;
/*  906 */       for (var3 = 0; var3 < WorldGenWoodlandMansionPieces.g.a(var0); var3++) {
/*  907 */         for (int var4 = 0; var4 < WorldGenWoodlandMansionPieces.g.b(var0); var4++) {
/*  908 */           if (var0.a(var4, var3) == 2) {
/*  909 */             var2.add(new Tuple<>(Integer.valueOf(var4), Integer.valueOf(var3)));
/*      */           }
/*      */         } 
/*      */       } 
/*  913 */       Collections.shuffle(var2, this.a);
/*      */       
/*  915 */       var3 = 10;
/*  916 */       for (Tuple<Integer, Integer> var5 : var2) {
/*  917 */         int var6 = ((Integer)var5.a()).intValue();
/*  918 */         int var7 = ((Integer)var5.b()).intValue();
/*      */         
/*  920 */         if (var1.a(var6, var7) == 0) {
/*  921 */           int var8 = var6;
/*  922 */           int var9 = var6;
/*  923 */           int var10 = var7;
/*  924 */           int var11 = var7;
/*  925 */           int var12 = 65536;
/*  926 */           if (var1.a(var6 + 1, var7) == 0 && var1.a(var6, var7 + 1) == 0 && var1.a(var6 + 1, var7 + 1) == 0 && var0
/*  927 */             .a(var6 + 1, var7) == 2 && var0.a(var6, var7 + 1) == 2 && var0.a(var6 + 1, var7 + 1) == 2) {
/*      */             
/*  929 */             var9++;
/*  930 */             var11++;
/*  931 */             var12 = 262144;
/*  932 */           } else if (var1.a(var6 - 1, var7) == 0 && var1.a(var6, var7 + 1) == 0 && var1.a(var6 - 1, var7 + 1) == 0 && var0
/*  933 */             .a(var6 - 1, var7) == 2 && var0.a(var6, var7 + 1) == 2 && var0.a(var6 - 1, var7 + 1) == 2) {
/*      */             
/*  935 */             var8--;
/*  936 */             var11++;
/*  937 */             var12 = 262144;
/*  938 */           } else if (var1.a(var6 - 1, var7) == 0 && var1.a(var6, var7 - 1) == 0 && var1.a(var6 - 1, var7 - 1) == 0 && var0
/*  939 */             .a(var6 - 1, var7) == 2 && var0.a(var6, var7 - 1) == 2 && var0.a(var6 - 1, var7 - 1) == 2) {
/*      */             
/*  941 */             var8--;
/*  942 */             var10--;
/*  943 */             var12 = 262144;
/*  944 */           } else if (var1.a(var6 + 1, var7) == 0 && var0.a(var6 + 1, var7) == 2) {
/*  945 */             var9++;
/*  946 */             var12 = 131072;
/*  947 */           } else if (var1.a(var6, var7 + 1) == 0 && var0.a(var6, var7 + 1) == 2) {
/*  948 */             var11++;
/*  949 */             var12 = 131072;
/*  950 */           } else if (var1.a(var6 - 1, var7) == 0 && var0.a(var6 - 1, var7) == 2) {
/*  951 */             var8--;
/*  952 */             var12 = 131072;
/*  953 */           } else if (var1.a(var6, var7 - 1) == 0 && var0.a(var6, var7 - 1) == 2) {
/*  954 */             var10--;
/*  955 */             var12 = 131072;
/*      */           } 
/*      */ 
/*      */           
/*  959 */           int var13 = this.a.nextBoolean() ? var8 : var9;
/*  960 */           int var14 = this.a.nextBoolean() ? var10 : var11;
/*  961 */           int var15 = 2097152;
/*  962 */           if (!var0.b(var13, var14, 1)) {
/*  963 */             var13 = (var13 == var8) ? var9 : var8;
/*  964 */             var14 = (var14 == var10) ? var11 : var10;
/*  965 */             if (!var0.b(var13, var14, 1)) {
/*  966 */               var14 = (var14 == var10) ? var11 : var10;
/*  967 */               if (!var0.b(var13, var14, 1)) {
/*  968 */                 var13 = (var13 == var8) ? var9 : var8;
/*  969 */                 var14 = (var14 == var10) ? var11 : var10;
/*  970 */                 if (!var0.b(var13, var14, 1)) {
/*      */                   
/*  972 */                   var15 = 0;
/*  973 */                   var13 = var8;
/*  974 */                   var14 = var10;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*  979 */           for (int var16 = var10; var16 <= var11; var16++) {
/*  980 */             for (int var17 = var8; var17 <= var9; var17++) {
/*  981 */               if (var17 == var13 && var16 == var14) {
/*  982 */                 var1.a(var17, var16, 0x100000 | var15 | var12 | var3);
/*      */               } else {
/*  984 */                 var1.a(var17, var16, var12 | var3);
/*      */               } 
/*      */             } 
/*      */           } 
/*      */           
/*  989 */           var3++;
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class g
/*      */   {
/*      */     private final int[][] a;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final int b;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final int c;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final int d;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public g(int var0, int var1, int var2) {
/* 1028 */       this.b = var0;
/* 1029 */       this.c = var1;
/* 1030 */       this.d = var2;
/* 1031 */       this.a = new int[var0][var1];
/*      */     }
/*      */     
/*      */     public void a(int var0, int var1, int var2) {
/* 1035 */       if (var0 >= 0 && var0 < this.b && var1 >= 0 && var1 < this.c) {
/* 1036 */         this.a[var0][var1] = var2;
/*      */       }
/*      */     }
/*      */     
/*      */     public void a(int var0, int var1, int var2, int var3, int var4) {
/* 1041 */       for (int var5 = var1; var5 <= var3; var5++) {
/* 1042 */         for (int var6 = var0; var6 <= var2; var6++) {
/* 1043 */           a(var6, var5, var4);
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*      */     public int a(int var0, int var1) {
/* 1049 */       if (var0 >= 0 && var0 < this.b && var1 >= 0 && var1 < this.c) {
/* 1050 */         return this.a[var0][var1];
/*      */       }
/* 1052 */       return this.d;
/*      */     }
/*      */     
/*      */     public void a(int var0, int var1, int var2, int var3) {
/* 1056 */       if (a(var0, var1) == var2) {
/* 1057 */         a(var0, var1, var3);
/*      */       }
/*      */     }
/*      */     
/*      */     public boolean b(int var0, int var1, int var2) {
/* 1062 */       return (a(var0 - 1, var1) == var2 || a(var0 + 1, var1) == var2 || a(var0, var1 + 1) == var2 || a(var0, var1 - 1) == var2);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class b
/*      */   {
/*      */     private b() {}
/*      */ 
/*      */     
/*      */     public abstract String a(Random param1Random);
/*      */ 
/*      */     
/*      */     public abstract String b(Random param1Random);
/*      */ 
/*      */     
/*      */     public abstract String a(Random param1Random, boolean param1Boolean);
/*      */ 
/*      */     
/*      */     public abstract String b(Random param1Random, boolean param1Boolean);
/*      */ 
/*      */     
/*      */     public abstract String c(Random param1Random);
/*      */ 
/*      */     
/*      */     public abstract String d(Random param1Random);
/*      */ 
/*      */     
/*      */     public abstract String e(Random param1Random);
/*      */   }
/*      */ 
/*      */   
/*      */   static class a
/*      */     extends b
/*      */   {
/*      */     private a() {}
/*      */ 
/*      */     
/*      */     public String a(Random var0) {
/* 1101 */       return "1x1_a" + (var0.nextInt(5) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String b(Random var0) {
/* 1106 */       return "1x1_as" + (var0.nextInt(4) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String a(Random var0, boolean var1) {
/* 1111 */       return "1x2_a" + (var0.nextInt(9) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String b(Random var0, boolean var1) {
/* 1116 */       return "1x2_b" + (var0.nextInt(5) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String c(Random var0) {
/* 1121 */       return "1x2_s" + (var0.nextInt(2) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String d(Random var0) {
/* 1126 */       return "2x2_a" + (var0.nextInt(4) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String e(Random var0) {
/* 1131 */       return "2x2_s1";
/*      */     } }
/*      */   
/*      */   static class f extends b {
/*      */     private f() {}
/*      */     
/*      */     public String a(Random var0) {
/* 1138 */       return "1x1_b" + (var0.nextInt(4) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String b(Random var0) {
/* 1143 */       return "1x1_as" + (var0.nextInt(4) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String a(Random var0, boolean var1) {
/* 1148 */       if (var1) {
/* 1149 */         return "1x2_c_stairs";
/*      */       }
/* 1151 */       return "1x2_c" + (var0.nextInt(4) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String b(Random var0, boolean var1) {
/* 1156 */       if (var1) {
/* 1157 */         return "1x2_d_stairs";
/*      */       }
/* 1159 */       return "1x2_d" + (var0.nextInt(5) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String c(Random var0) {
/* 1164 */       return "1x2_se" + (var0.nextInt(1) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String d(Random var0) {
/* 1169 */       return "2x2_b" + (var0.nextInt(5) + 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public String e(Random var0) {
/* 1174 */       return "2x2_s1";
/*      */     }
/*      */   }
/*      */   
/*      */   static class h extends f {
/*      */     private h() {}
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenWoodlandMansionPieces.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */