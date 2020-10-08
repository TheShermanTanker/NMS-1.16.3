/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Queues;
/*     */ import java.util.Deque;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableObject;
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
/*     */ public class WorldGenFeatureDefinedStructureJigsawPlacement
/*     */ {
/*  38 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   static final class b {
/*     */     private final WorldGenFeaturePillagerOutpostPoolPiece a;
/*     */     private final MutableObject<VoxelShape> b;
/*     */     private final int c;
/*     */     private final int d;
/*     */     
/*     */     private b(WorldGenFeaturePillagerOutpostPoolPiece var0, MutableObject<VoxelShape> var1, int var2, int var3) {
/*  47 */       this.a = var0;
/*  48 */       this.b = var1;
/*  49 */       this.c = var2;
/*  50 */       this.d = var3;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class c {
/*     */     private final IRegistry<WorldGenFeatureDefinedStructurePoolTemplate> a;
/*     */     private final int b;
/*     */     private final WorldGenFeatureDefinedStructureJigsawPlacement.a c;
/*     */     private final ChunkGenerator d;
/*     */     private final DefinedStructureManager e;
/*     */     private final List<? super WorldGenFeaturePillagerOutpostPoolPiece> f;
/*     */     private final Random g;
/*  62 */     private final Deque<WorldGenFeatureDefinedStructureJigsawPlacement.b> h = Queues.newArrayDeque();
/*     */     
/*     */     private c(IRegistry<WorldGenFeatureDefinedStructurePoolTemplate> var0, int var1, WorldGenFeatureDefinedStructureJigsawPlacement.a var2, ChunkGenerator var3, DefinedStructureManager var4, List<? super WorldGenFeaturePillagerOutpostPoolPiece> var5, Random var6) {
/*  65 */       this.a = var0;
/*  66 */       this.b = var1;
/*  67 */       this.c = var2;
/*  68 */       this.d = var3;
/*  69 */       this.e = var4;
/*  70 */       this.f = var5;
/*  71 */       this.g = var6;
/*     */     }
/*     */     
/*     */     private void a(WorldGenFeaturePillagerOutpostPoolPiece var0, MutableObject<VoxelShape> var1, int var2, int var3, boolean var4) {
/*  75 */       WorldGenFeatureDefinedStructurePoolStructure var5 = var0.b();
/*  76 */       BlockPosition var6 = var0.c();
/*  77 */       EnumBlockRotation var7 = var0.ap_();
/*     */       
/*  79 */       WorldGenFeatureDefinedStructurePoolTemplate.Matching var8 = var5.e();
/*  80 */       boolean var9 = (var8 == WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
/*     */       
/*  82 */       MutableObject<VoxelShape> var10 = new MutableObject();
/*     */       
/*  84 */       StructureBoundingBox var11 = var0.g();
/*  85 */       int var12 = var11.b;
/*     */       
/*  87 */       for (DefinedStructure.BlockInfo var14 : var5.a(this.e, var6, var7, this.g)) {
/*  88 */         MutableObject<VoxelShape> var24; int var25; EnumDirection var15 = BlockJigsaw.h(var14.b);
/*     */         
/*  90 */         BlockPosition var16 = var14.a;
/*  91 */         BlockPosition var17 = var16.shift(var15);
/*     */         
/*  93 */         int var18 = var16.getY() - var12;
/*  94 */         int var19 = -1;
/*     */         
/*  96 */         MinecraftKey var20 = new MinecraftKey(var14.c.getString("pool"));
/*  97 */         Optional<WorldGenFeatureDefinedStructurePoolTemplate> var21 = this.a.getOptional(var20);
/*     */         
/*  99 */         if (!var21.isPresent() || (((WorldGenFeatureDefinedStructurePoolTemplate)var21.get()).c() == 0 && !Objects.equals(var20, WorldGenFeaturePieces.a.a()))) {
/* 100 */           WorldGenFeatureDefinedStructureJigsawPlacement.a().warn("Empty or none existent pool: {}", var20);
/*     */           
/*     */           continue;
/*     */         } 
/* 104 */         MinecraftKey var22 = ((WorldGenFeatureDefinedStructurePoolTemplate)var21.get()).a();
/* 105 */         Optional<WorldGenFeatureDefinedStructurePoolTemplate> var23 = this.a.getOptional(var22);
/*     */         
/* 107 */         if (!var23.isPresent() || (((WorldGenFeatureDefinedStructurePoolTemplate)var23.get()).c() == 0 && !Objects.equals(var22, WorldGenFeaturePieces.a.a()))) {
/* 108 */           WorldGenFeatureDefinedStructureJigsawPlacement.a().warn("Empty or none existent fallback pool: {}", var22);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 115 */         boolean var26 = var11.b(var17);
/* 116 */         if (var26) {
/* 117 */           var24 = var10;
/* 118 */           var25 = var12;
/* 119 */           if (var10.getValue() == null) {
/* 120 */             var10.setValue(VoxelShapes.a(AxisAlignedBB.a(var11)));
/*     */           }
/*     */         } else {
/* 123 */           var24 = var1;
/* 124 */           var25 = var2;
/*     */         } 
/*     */ 
/*     */         
/* 128 */         List<WorldGenFeatureDefinedStructurePoolStructure> var27 = Lists.newArrayList();
/* 129 */         if (var3 != this.b) {
/* 130 */           var27.addAll(((WorldGenFeatureDefinedStructurePoolTemplate)var21.get()).b(this.g));
/*     */         }
/* 132 */         var27.addAll(((WorldGenFeatureDefinedStructurePoolTemplate)var23.get()).b(this.g));
/*     */ 
/*     */         
/* 135 */         for (WorldGenFeatureDefinedStructurePoolStructure var29 : var27) {
/* 136 */           if (var29 == WorldGenFeatureDefinedStructurePoolEmpty.b) {
/*     */             break;
/*     */           }
/*     */           
/* 140 */           for (EnumBlockRotation var31 : EnumBlockRotation.b(this.g)) {
/* 141 */             int var34; List<DefinedStructure.BlockInfo> var32 = var29.a(this.e, BlockPosition.ZERO, var31, this.g);
/* 142 */             StructureBoundingBox var33 = var29.a(this.e, BlockPosition.ZERO, var31);
/*     */ 
/*     */             
/* 145 */             if (!var4 || var33.e() > 16) {
/* 146 */               var34 = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             }
/*     */             else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 158 */               var34 = var32.stream().mapToInt(var1 -> { if (!var0.b(var1.a.shift(BlockJigsaw.h(var1.b)))) return 0;  MinecraftKey var2 = new MinecraftKey(var1.c.getString("pool")); Optional<WorldGenFeatureDefinedStructurePoolTemplate> var3 = this.a.getOptional(var2); Optional<WorldGenFeatureDefinedStructurePoolTemplate> var4 = var3.flatMap(()); int var5 = ((Integer)var3.<Integer>map(()).orElse(Integer.valueOf(0))).intValue(); int var6 = ((Integer)var4.<Integer>map(()).orElse(Integer.valueOf(0))).intValue(); return Math.max(var5, var6); }).max().orElse(0);
/*     */             } 
/*     */             
/* 161 */             for (DefinedStructure.BlockInfo var36 : var32) {
/* 162 */               int var45, var50, var52; if (!BlockJigsaw.a(var14, var36)) {
/*     */                 continue;
/*     */               }
/*     */               
/* 166 */               BlockPosition var37 = var36.a;
/*     */               
/* 168 */               BlockPosition var38 = new BlockPosition(var17.getX() - var37.getX(), var17.getY() - var37.getY(), var17.getZ() - var37.getZ());
/* 169 */               StructureBoundingBox var39 = var29.a(this.e, var38, var31);
/* 170 */               int var40 = var39.b;
/*     */               
/* 172 */               WorldGenFeatureDefinedStructurePoolTemplate.Matching var41 = var29.e();
/* 173 */               boolean var42 = (var41 == WorldGenFeatureDefinedStructurePoolTemplate.Matching.RIGID);
/*     */ 
/*     */               
/* 176 */               int var43 = var37.getY();
/*     */               
/* 178 */               int var44 = var18 - var43 + BlockJigsaw.h(var14.b).getAdjacentY();
/*     */ 
/*     */               
/* 181 */               if (var9 && var42) {
/* 182 */                 var45 = var12 + var44;
/*     */               } else {
/* 184 */                 if (var19 == -1) {
/* 185 */                   var19 = this.d.b(var16.getX(), var16.getZ(), HeightMap.Type.WORLD_SURFACE_WG);
/*     */                 }
/* 187 */                 var45 = var19 - var43;
/*     */               } 
/*     */               
/* 190 */               int var46 = var45 - var40;
/*     */               
/* 192 */               StructureBoundingBox var47 = var39.b(0, var46, 0);
/* 193 */               BlockPosition var48 = var38.b(0, var46, 0);
/*     */               
/* 195 */               if (var34 > 0) {
/* 196 */                 int i = Math.max(var34 + 1, var47.e - var47.b);
/* 197 */                 var47.e = var47.b + i;
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 202 */               if (VoxelShapes.c((VoxelShape)var24.getValue(), VoxelShapes.a(AxisAlignedBB.a(var47).shrink(0.25D)), OperatorBoolean.ONLY_SECOND)) {
/*     */                 continue;
/*     */               }
/*     */               
/* 206 */               var24.setValue(VoxelShapes.b((VoxelShape)var24.getValue(), VoxelShapes.a(AxisAlignedBB.a(var47)), OperatorBoolean.ONLY_FIRST));
/*     */               
/* 208 */               int var49 = var0.d();
/*     */               
/* 210 */               if (var42) {
/*     */                 
/* 212 */                 var50 = var49 - var44;
/*     */               } else {
/* 214 */                 var50 = var29.f();
/*     */               } 
/*     */               
/* 217 */               WorldGenFeaturePillagerOutpostPoolPiece var51 = this.c.create(this.e, var29, var48, var50, var31, var47);
/*     */ 
/*     */               
/* 220 */               if (var9) {
/* 221 */                 var52 = var12 + var18;
/* 222 */               } else if (var42) {
/* 223 */                 var52 = var45 + var43;
/*     */               } else {
/* 225 */                 if (var19 == -1) {
/* 226 */                   var19 = this.d.b(var16.getX(), var16.getZ(), HeightMap.Type.WORLD_SURFACE_WG);
/*     */                 }
/* 228 */                 var52 = var19 + var44 / 2;
/*     */               } 
/*     */               
/* 231 */               var0.a(new WorldGenFeatureDefinedStructureJigsawJunction(var17
/* 232 */                     .getX(), var52 - var18 + var49, var17
/*     */                     
/* 234 */                     .getZ(), var44, var41));
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 239 */               var51.a(new WorldGenFeatureDefinedStructureJigsawJunction(var16
/* 240 */                     .getX(), var52 - var43 + var50, var16
/*     */                     
/* 242 */                     .getZ(), -var44, var8));
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 247 */               this.f.add(var51);
/* 248 */               if (var3 + 1 <= this.b) {
/* 249 */                 this.h.addLast(new WorldGenFeatureDefinedStructureJigsawPlacement.b(var51, var24, var25, var3 + 1));
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(IRegistryCustom var0, WorldGenFeatureVillageConfiguration var1, a var2, ChunkGenerator var3, DefinedStructureManager var4, BlockPosition var5, List<? super WorldGenFeaturePillagerOutpostPoolPiece> var6, Random var7, boolean var8, boolean var9) {
/*     */     int var18;
/* 260 */     StructureGenerator.g();
/*     */     
/* 262 */     IRegistryWritable<WorldGenFeatureDefinedStructurePoolTemplate> var10 = var0.b(IRegistry.ax);
/*     */     
/* 264 */     EnumBlockRotation var11 = EnumBlockRotation.a(var7);
/* 265 */     WorldGenFeatureDefinedStructurePoolTemplate var12 = var1.c().get();
/* 266 */     WorldGenFeatureDefinedStructurePoolStructure var13 = var12.a(var7);
/* 267 */     WorldGenFeaturePillagerOutpostPoolPiece var14 = var2.create(var4, var13, var5, var13.f(), var11, var13.a(var4, var5, var11));
/* 268 */     StructureBoundingBox var15 = var14.g();
/* 269 */     int var16 = (var15.d + var15.a) / 2;
/* 270 */     int var17 = (var15.f + var15.c) / 2;
/*     */ 
/*     */ 
/*     */     
/* 274 */     if (var9) {
/* 275 */       var18 = var5.getY() + var3.b(var16, var17, HeightMap.Type.WORLD_SURFACE_WG);
/*     */     } else {
/* 277 */       var18 = var5.getY();
/*     */     } 
/*     */     
/* 280 */     int var19 = var15.b + var14.d();
/* 281 */     var14.a(0, var18 - var19, 0);
/*     */     
/* 283 */     var6.add(var14);
/* 284 */     if (var1.b() <= 0) {
/*     */       return;
/*     */     }
/*     */     
/* 288 */     int var20 = 80;
/*     */     
/* 290 */     AxisAlignedBB var21 = new AxisAlignedBB((var16 - 80), (var18 - 80), (var17 - 80), (var16 + 80 + 1), (var18 + 80 + 1), (var17 + 80 + 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     c var22 = new c(var10, var1.b(), var2, var3, var4, var6, var7);
/*     */     
/* 301 */     c.a(var22).addLast(new b(var14, new MutableObject(VoxelShapes.a(VoxelShapes.a(var21), VoxelShapes.a(AxisAlignedBB.a(var15)), OperatorBoolean.ONLY_FIRST)), var18 + 80, 0));
/*     */     
/* 303 */     while (!c.a(var22).isEmpty()) {
/* 304 */       b var23 = c.a(var22).removeFirst();
/* 305 */       c.a(var22, b.a(var23), b.b(var23), b.c(var23), b.d(var23), var8);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void a(IRegistryCustom var0, WorldGenFeaturePillagerOutpostPoolPiece var1, int var2, a var3, ChunkGenerator var4, DefinedStructureManager var5, List<? super WorldGenFeaturePillagerOutpostPoolPiece> var6, Random var7) {
/* 310 */     IRegistryWritable<WorldGenFeatureDefinedStructurePoolTemplate> var8 = var0.b(IRegistry.ax);
/* 311 */     c var9 = new c(var8, var2, var3, var4, var5, var6, var7);
/*     */     
/* 313 */     c.a(var9).addLast(new b(var1, new MutableObject(VoxelShapes.a), 0, 0));
/*     */     
/* 315 */     while (!c.a(var9).isEmpty()) {
/* 316 */       b var10 = c.a(var9).removeFirst();
/* 317 */       c.a(var9, b.a(var10), b.b(var10), b.c(var10), b.d(var10), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     WorldGenFeaturePillagerOutpostPoolPiece create(DefinedStructureManager param1DefinedStructureManager, WorldGenFeatureDefinedStructurePoolStructure param1WorldGenFeatureDefinedStructurePoolStructure, BlockPosition param1BlockPosition, int param1Int, EnumBlockRotation param1EnumBlockRotation, StructureBoundingBox param1StructureBoundingBox);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureDefinedStructureJigsawPlacement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */