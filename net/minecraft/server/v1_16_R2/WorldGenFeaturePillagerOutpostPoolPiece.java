/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class WorldGenFeaturePillagerOutpostPoolPiece
/*     */   extends StructurePiece
/*     */ {
/*  27 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   protected final WorldGenFeatureDefinedStructurePoolStructure a;
/*     */   protected BlockPosition b;
/*     */   private final int e;
/*     */   protected final EnumBlockRotation c;
/*  33 */   private final List<WorldGenFeatureDefinedStructureJigsawJunction> f = Lists.newArrayList();
/*     */   private final DefinedStructureManager g;
/*     */   
/*     */   public WorldGenFeaturePillagerOutpostPoolPiece(DefinedStructureManager var0, WorldGenFeatureDefinedStructurePoolStructure var1, BlockPosition var2, int var3, EnumBlockRotation var4, StructureBoundingBox var5) {
/*  37 */     super(WorldGenFeatureStructurePieceType.ad, 0);
/*  38 */     this.g = var0;
/*  39 */     this.a = var1;
/*  40 */     this.b = var2;
/*  41 */     this.e = var3;
/*  42 */     this.c = var4;
/*  43 */     this.n = var5;
/*     */   }
/*     */   
/*     */   public WorldGenFeaturePillagerOutpostPoolPiece(DefinedStructureManager var0, NBTTagCompound var1) {
/*  47 */     super(WorldGenFeatureStructurePieceType.ad, var1);
/*  48 */     this.g = var0;
/*  49 */     this.b = new BlockPosition(var1.getInt("PosX"), var1.getInt("PosY"), var1.getInt("PosZ"));
/*  50 */     this.e = var1.getInt("ground_level_delta");
/*  51 */     this.a = WorldGenFeatureDefinedStructurePoolStructure.e.parse(DynamicOpsNBT.a, var1.getCompound("pool_element")).resultOrPartial(LOGGER::error).orElse(WorldGenFeatureDefinedStructurePoolEmpty.b);
/*  52 */     this.c = EnumBlockRotation.valueOf(var1.getString("rotation"));
/*  53 */     this.n = this.a.a(var0, this.b, this.c);
/*     */     
/*  55 */     NBTTagList var2 = var1.getList("junctions", 10);
/*  56 */     this.f.clear();
/*  57 */     var2.forEach(var0 -> this.f.add(WorldGenFeatureDefinedStructureJigsawJunction.a(new Dynamic(DynamicOpsNBT.a, var0))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagCompound var0) {
/*  62 */     var0.setInt("PosX", this.b.getX());
/*  63 */     var0.setInt("PosY", this.b.getY());
/*  64 */     var0.setInt("PosZ", this.b.getZ());
/*  65 */     var0.setInt("ground_level_delta", this.e);
/*  66 */     WorldGenFeatureDefinedStructurePoolStructure.e.encodeStart(DynamicOpsNBT.a, this.a).resultOrPartial(LOGGER::error).ifPresent(var1 -> var0.set("pool_element", var1));
/*     */ 
/*     */     
/*  69 */     var0.setString("rotation", this.c.name());
/*  70 */     NBTTagList var1 = new NBTTagList();
/*  71 */     for (WorldGenFeatureDefinedStructureJigsawJunction var3 : this.f) {
/*  72 */       var1.add((NBTBase)var3.<T>a(DynamicOpsNBT.a).getValue());
/*     */     }
/*  74 */     var0.set("junctions", var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5, BlockPosition var6) {
/*  79 */     return a(var0, var1, var2, var3, var4, var6, false);
/*     */   }
/*     */   
/*     */   public boolean a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, BlockPosition var5, boolean var6) {
/*  83 */     return this.a.a(this.g, var0, var1, var2, this.b, var5, this.c, var4, var3, var6);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int var0, int var1, int var2) {
/*  88 */     super.a(var0, var1, var2);
/*  89 */     this.b = this.b.b(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockRotation ap_() {
/*  94 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     return String.format("<%s | %s | %s | %s>", new Object[] { getClass().getSimpleName(), this.b, this.c, this.a });
/*     */   }
/*     */   
/*     */   public WorldGenFeatureDefinedStructurePoolStructure b() {
/* 103 */     return this.a;
/*     */   }
/*     */   
/*     */   public BlockPosition c() {
/* 107 */     return this.b;
/*     */   }
/*     */   
/*     */   public int d() {
/* 111 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(WorldGenFeatureDefinedStructureJigsawJunction var0) {
/* 115 */     this.f.add(var0);
/*     */   }
/*     */   
/*     */   public List<WorldGenFeatureDefinedStructureJigsawJunction> e() {
/* 119 */     return this.f;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeaturePillagerOutpostPoolPiece.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */