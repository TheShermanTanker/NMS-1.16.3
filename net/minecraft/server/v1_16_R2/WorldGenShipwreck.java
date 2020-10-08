/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ public class WorldGenShipwreck
/*     */ {
/*  29 */   private static final BlockPosition a = new BlockPosition(4, 0, 15);
/*     */   
/*  31 */   private static final MinecraftKey[] b = new MinecraftKey[] { new MinecraftKey("shipwreck/with_mast"), new MinecraftKey("shipwreck/sideways_full"), new MinecraftKey("shipwreck/sideways_fronthalf"), new MinecraftKey("shipwreck/sideways_backhalf"), new MinecraftKey("shipwreck/rightsideup_full"), new MinecraftKey("shipwreck/rightsideup_fronthalf"), new MinecraftKey("shipwreck/rightsideup_backhalf"), new MinecraftKey("shipwreck/with_mast_degraded"), new MinecraftKey("shipwreck/rightsideup_full_degraded"), new MinecraftKey("shipwreck/rightsideup_fronthalf_degraded"), new MinecraftKey("shipwreck/rightsideup_backhalf_degraded") };
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
/*  45 */   private static final MinecraftKey[] c = new MinecraftKey[] { new MinecraftKey("shipwreck/with_mast"), new MinecraftKey("shipwreck/upsidedown_full"), new MinecraftKey("shipwreck/upsidedown_fronthalf"), new MinecraftKey("shipwreck/upsidedown_backhalf"), new MinecraftKey("shipwreck/sideways_full"), new MinecraftKey("shipwreck/sideways_fronthalf"), new MinecraftKey("shipwreck/sideways_backhalf"), new MinecraftKey("shipwreck/rightsideup_full"), new MinecraftKey("shipwreck/rightsideup_fronthalf"), new MinecraftKey("shipwreck/rightsideup_backhalf"), new MinecraftKey("shipwreck/with_mast_degraded"), new MinecraftKey("shipwreck/upsidedown_full_degraded"), new MinecraftKey("shipwreck/upsidedown_fronthalf_degraded"), new MinecraftKey("shipwreck/upsidedown_backhalf_degraded"), new MinecraftKey("shipwreck/sideways_full_degraded"), new MinecraftKey("shipwreck/sideways_fronthalf_degraded"), new MinecraftKey("shipwreck/sideways_backhalf_degraded"), new MinecraftKey("shipwreck/rightsideup_full_degraded"), new MinecraftKey("shipwreck/rightsideup_fronthalf_degraded"), new MinecraftKey("shipwreck/rightsideup_backhalf_degraded") };
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
/*     */   public static void a(DefinedStructureManager var0, BlockPosition var1, EnumBlockRotation var2, List<StructurePiece> var3, Random var4, WorldGenFeatureShipwreckConfiguration var5) {
/*  69 */     MinecraftKey var6 = SystemUtils.<MinecraftKey>a(var5.b ? b : c, var4);
/*  70 */     var3.add(new a(var0, var6, var1, var2, var5.b));
/*     */   }
/*     */   
/*     */   public static class a extends DefinedStructurePiece {
/*     */     private final EnumBlockRotation d;
/*     */     private final MinecraftKey e;
/*     */     private final boolean f;
/*     */     
/*     */     public a(DefinedStructureManager var0, MinecraftKey var1, BlockPosition var2, EnumBlockRotation var3, boolean var4) {
/*  79 */       super(WorldGenFeatureStructurePieceType.ab, 0);
/*     */       
/*  81 */       this.c = var2;
/*  82 */       this.d = var3;
/*  83 */       this.e = var1;
/*  84 */       this.f = var4;
/*  85 */       a(var0);
/*     */     }
/*     */     
/*     */     public a(DefinedStructureManager var0, NBTTagCompound var1) {
/*  89 */       super(WorldGenFeatureStructurePieceType.ab, var1);
/*  90 */       this.e = new MinecraftKey(var1.getString("Template"));
/*  91 */       this.f = var1.getBoolean("isBeached");
/*  92 */       this.d = EnumBlockRotation.valueOf(var1.getString("Rot"));
/*  93 */       a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(NBTTagCompound var0) {
/*  98 */       super.a(var0);
/*  99 */       var0.setString("Template", this.e.toString());
/* 100 */       var0.setBoolean("isBeached", this.f);
/* 101 */       var0.setString("Rot", this.d.name());
/*     */     }
/*     */     
/*     */     private void a(DefinedStructureManager var0) {
/* 105 */       DefinedStructure var1 = var0.a(this.e);
/* 106 */       DefinedStructureInfo var2 = (new DefinedStructureInfo()).a(this.d).a(EnumBlockMirror.NONE).a(WorldGenShipwreck.a()).a(DefinedStructureProcessorBlockIgnore.d);
/* 107 */       a(var1, this.c, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void a(String var0, BlockPosition var1, WorldAccess var2, Random var3, StructureBoundingBox var4) {
/* 112 */       if ("map_chest".equals(var0)) {
/* 113 */         TileEntityLootable.a(var2, var3, var1.down(), LootTables.H);
/* 114 */       } else if ("treasure_chest".equals(var0)) {
/* 115 */         TileEntityLootable.a(var2, var3, var1.down(), LootTables.J);
/* 116 */       } else if ("supply_chest".equals(var0)) {
/* 117 */         TileEntityLootable.a(var2, var3, var1.down(), LootTables.I);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/* 123 */       int var7 = 256;
/* 124 */       int var8 = 0;
/* 125 */       BlockPosition var9 = this.a.a();
/* 126 */       HeightMap.Type var10 = this.f ? HeightMap.Type.WORLD_SURFACE_WG : HeightMap.Type.OCEAN_FLOOR_WG;
/* 127 */       int var11 = var9.getX() * var9.getZ();
/* 128 */       if (var11 == 0) {
/* 129 */         var8 = var0.a(var10, this.c.getX(), this.c.getZ());
/*     */       } else {
/* 131 */         BlockPosition blockPosition = this.c.b(var9.getX() - 1, 0, var9.getZ() - 1);
/* 132 */         for (BlockPosition var14 : BlockPosition.a(this.c, blockPosition)) {
/* 133 */           int var15 = var0.a(var10, var14.getX(), var14.getZ());
/* 134 */           var8 += var15;
/* 135 */           var7 = Math.min(var7, var15);
/*     */         } 
/* 137 */         var8 /= var11;
/*     */       } 
/*     */       
/* 140 */       int var12 = this.f ? (var7 - var9.getY() / 2 - var3.nextInt(3)) : var8;
/* 141 */       this.c = new BlockPosition(this.c.getX(), var12, this.c.getZ());
/* 142 */       return super.a(var0, var1, var2, var3, var4, var5, var6);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenShipwreck.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */