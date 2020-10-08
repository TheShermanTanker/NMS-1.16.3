/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
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
/*     */ public abstract class StructureStart<C extends WorldGenFeatureConfiguration>
/*     */ {
/*  26 */   public static final StructureStart<?> a = new StructureStart<WorldGenMineshaftConfiguration>(StructureGenerator.MINESHAFT, 0, 0, StructureBoundingBox.a(), 0, 0L)
/*     */     {
/*     */       public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenMineshaftConfiguration var6) {}
/*     */     };
/*     */ 
/*     */   
/*     */   private final StructureGenerator<C> e;
/*  33 */   protected final List<StructurePiece> b = Lists.newArrayList();
/*     */   
/*     */   protected StructureBoundingBox c;
/*     */   private final int f;
/*     */   private final int g;
/*     */   private int h;
/*     */   protected final SeededRandom d;
/*     */   
/*     */   public StructureStart(StructureGenerator<C> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/*  42 */     this.e = var0;
/*  43 */     this.f = var1;
/*  44 */     this.g = var2;
/*  45 */     this.h = var4;
/*  46 */     this.d = new SeededRandom();
/*  47 */     this.d.c(var5, var1, var2);
/*  48 */     this.c = var3;
/*     */   }
/*     */   
/*     */   public abstract void a(IRegistryCustom paramIRegistryCustom, ChunkGenerator paramChunkGenerator, DefinedStructureManager paramDefinedStructureManager, int paramInt1, int paramInt2, BiomeBase paramBiomeBase, C paramC);
/*     */   
/*     */   public StructureBoundingBox c() {
/*  54 */     return this.c;
/*     */   }
/*     */   
/*     */   public List<StructurePiece> d() {
/*  58 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5) {
/*  62 */     synchronized (this.b) {
/*  63 */       if (this.b.isEmpty()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  69 */       StructureBoundingBox var7 = ((StructurePiece)this.b.get(0)).n;
/*  70 */       BaseBlockPosition var8 = var7.g();
/*  71 */       BlockPosition var9 = new BlockPosition(var8.getX(), var7.b, var8.getZ());
/*  72 */       Iterator<StructurePiece> var10 = this.b.iterator();
/*  73 */       while (var10.hasNext()) {
/*  74 */         StructurePiece var11 = var10.next();
/*  75 */         if (var11.g().b(var4) && 
/*  76 */           !var11.a(var0, var1, var2, var3, var4, var5, var9)) {
/*  77 */           var10.remove();
/*     */         }
/*     */       } 
/*     */       
/*  81 */       b();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void b() {
/*  86 */     this.c = StructureBoundingBox.a();
/*     */     
/*  88 */     for (StructurePiece var1 : this.b) {
/*  89 */       this.c.c(var1.g());
/*     */     }
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(int var0, int var1) {
/*  94 */     NBTTagCompound var2 = new NBTTagCompound();
/*     */     
/*  96 */     if (e()) {
/*  97 */       var2.setString("id", IRegistry.STRUCTURE_FEATURE.getKey(l()).toString());
/*     */     } else {
/*  99 */       var2.setString("id", "INVALID");
/* 100 */       return var2;
/*     */     } 
/* 102 */     var2.setInt("ChunkX", var0);
/* 103 */     var2.setInt("ChunkZ", var1);
/* 104 */     var2.setInt("references", this.h);
/* 105 */     var2.set("BB", this.c.h());
/*     */     
/* 107 */     NBTTagList var3 = new NBTTagList();
/* 108 */     synchronized (this.b) {
/* 109 */       for (StructurePiece var6 : this.b) {
/* 110 */         var3.add(var6.f());
/*     */       }
/*     */     } 
/* 113 */     var2.set("Children", var3);
/*     */     
/* 115 */     return var2;
/*     */   }
/*     */   
/*     */   protected void a(int var0, Random var1, int var2) {
/* 119 */     int var3 = var0 - var2;
/*     */ 
/*     */     
/* 122 */     int var4 = this.c.e() + 1;
/*     */     
/* 124 */     if (var4 < var3) {
/* 125 */       var4 += var1.nextInt(var3 - var4);
/*     */     }
/*     */ 
/*     */     
/* 129 */     int var5 = var4 - this.c.e;
/* 130 */     this.c.a(0, var5, 0);
/* 131 */     for (StructurePiece var7 : this.b) {
/* 132 */       var7.a(0, var5, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void a(Random var0, int var1, int var2) {
/* 137 */     int var4, var3 = var2 - var1 + 1 - this.c.e();
/*     */ 
/*     */     
/* 140 */     if (var3 > 1) {
/* 141 */       var4 = var1 + var0.nextInt(var3);
/*     */     } else {
/* 143 */       var4 = var1;
/*     */     } 
/*     */ 
/*     */     
/* 147 */     int var5 = var4 - this.c.b;
/* 148 */     this.c.a(0, var5, 0);
/* 149 */     for (StructurePiece var7 : this.b) {
/* 150 */       var7.a(0, var5, 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean e() {
/* 155 */     return !this.b.isEmpty();
/*     */   }
/*     */   
/*     */   public int f() {
/* 159 */     return this.f;
/*     */   }
/*     */   
/*     */   public int g() {
/* 163 */     return this.g;
/*     */   }
/*     */   
/*     */   public BlockPosition a() {
/* 167 */     return new BlockPosition(this.f << 4, 0, this.g << 4);
/*     */   }
/*     */   
/*     */   public boolean h() {
/* 171 */     return (this.h < k());
/*     */   }
/*     */   
/*     */   public void i() {
/* 175 */     this.h++;
/*     */   }
/*     */   
/*     */   public int j() {
/* 179 */     return this.h;
/*     */   }
/*     */   
/*     */   protected int k() {
/* 183 */     return 1;
/*     */   }
/*     */   
/*     */   public StructureGenerator<?> l() {
/* 187 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */