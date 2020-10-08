/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
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
/*     */ public class DefinedStructureInfo
/*     */ {
/*  18 */   private EnumBlockMirror a = EnumBlockMirror.NONE;
/*  19 */   private EnumBlockRotation b = EnumBlockRotation.NONE;
/*  20 */   private BlockPosition c = BlockPosition.ZERO;
/*     */   private boolean d;
/*     */   @Nullable
/*     */   private ChunkCoordIntPair e;
/*     */   @Nullable
/*     */   private StructureBoundingBox f;
/*     */   private boolean g = true;
/*     */   @Nullable
/*     */   private Random h;
/*     */   @Nullable
/*     */   private int i;
/*  31 */   private final List<DefinedStructureProcessor> j = Lists.newArrayList();
/*     */   private boolean k;
/*     */   private boolean l;
/*     */   
/*     */   public DefinedStructureInfo a() {
/*  36 */     DefinedStructureInfo var0 = new DefinedStructureInfo();
/*  37 */     var0.a = this.a;
/*  38 */     var0.b = this.b;
/*  39 */     var0.c = this.c;
/*  40 */     var0.d = this.d;
/*  41 */     var0.e = this.e;
/*  42 */     var0.f = this.f;
/*  43 */     var0.g = this.g;
/*  44 */     var0.h = this.h;
/*  45 */     var0.i = this.i;
/*  46 */     var0.j.addAll(this.j);
/*  47 */     var0.k = this.k;
/*  48 */     var0.l = this.l;
/*  49 */     return var0;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(EnumBlockMirror var0) {
/*  53 */     this.a = var0;
/*  54 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(EnumBlockRotation var0) {
/*  58 */     this.b = var0;
/*  59 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(BlockPosition var0) {
/*  63 */     this.c = var0;
/*  64 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(boolean var0) {
/*  68 */     this.d = var0;
/*  69 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(ChunkCoordIntPair var0) {
/*  73 */     this.e = var0;
/*  74 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(StructureBoundingBox var0) {
/*  78 */     this.f = var0;
/*  79 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(@Nullable Random var0) {
/*  83 */     this.h = var0;
/*  84 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefinedStructureInfo c(boolean var0) {
/*  93 */     this.k = var0;
/*  94 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo b() {
/*  98 */     this.j.clear();
/*  99 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo a(DefinedStructureProcessor var0) {
/* 103 */     this.j.add(var0);
/* 104 */     return this;
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo b(DefinedStructureProcessor var0) {
/* 108 */     this.j.remove(var0);
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public EnumBlockMirror c() {
/* 113 */     return this.a;
/*     */   }
/*     */   
/*     */   public EnumBlockRotation d() {
/* 117 */     return this.b;
/*     */   }
/*     */   
/*     */   public BlockPosition e() {
/* 121 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Random b(@Nullable BlockPosition var0) {
/* 130 */     if (this.h != null) {
/* 131 */       return this.h;
/*     */     }
/*     */     
/* 134 */     if (var0 == null) {
/* 135 */       return new Random(SystemUtils.getMonotonicMillis());
/*     */     }
/*     */     
/* 138 */     return new Random(MathHelper.a(var0));
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 142 */     return this.d;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public StructureBoundingBox h() {
/* 147 */     if (this.f == null && this.e != null) {
/* 148 */       k();
/*     */     }
/* 150 */     return this.f;
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 154 */     return this.k;
/*     */   }
/*     */   
/*     */   public List<DefinedStructureProcessor> j() {
/* 158 */     return this.j;
/*     */   }
/*     */   
/*     */   void k() {
/* 162 */     if (this.e != null) {
/* 163 */       this.f = b(this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean l() {
/* 168 */     return this.g;
/*     */   }
/*     */   
/*     */   public DefinedStructure.a a(List<DefinedStructure.a> var0, @Nullable BlockPosition var1) {
/* 172 */     int var2 = var0.size();
/* 173 */     if (var2 == 0)
/*     */     {
/* 175 */       throw new IllegalStateException("No palettes");
/*     */     }
/* 177 */     return var0.get(b(var1).nextInt(var2));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private StructureBoundingBox b(@Nullable ChunkCoordIntPair var0) {
/* 182 */     if (var0 == null) {
/* 183 */       return this.f;
/*     */     }
/* 185 */     int var1 = var0.x * 16;
/* 186 */     int var2 = var0.z * 16;
/* 187 */     return new StructureBoundingBox(var1, 0, var2, var1 + 16 - 1, 255, var2 + 16 - 1);
/*     */   }
/*     */   
/*     */   public DefinedStructureInfo d(boolean var0) {
/* 191 */     this.l = var0;
/* 192 */     return this;
/*     */   }
/*     */   
/*     */   public boolean m() {
/* 196 */     return this.l;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */