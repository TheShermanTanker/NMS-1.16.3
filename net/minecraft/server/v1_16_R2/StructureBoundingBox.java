/*     */ package net.minecraft.server.v1_16_R2;
/*     */ public class StructureBoundingBox {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   
/*   7 */   public final int getMinX() { return this.a; } public int d; public int e; public int f; public final int getMinY() {
/*   8 */     return this.b; }
/*   9 */   public final int getMinZ() { return this.c; }
/*  10 */   public final int getMaxX() { return this.d; }
/*  11 */   public final int getMaxY() { return this.e; } public final int getMaxZ() {
/*  12 */     return this.f;
/*     */   }
/*     */   public StructureBoundingBox() {}
/*     */   
/*     */   public StructureBoundingBox(int[] aint) {
/*  17 */     if (aint.length == 6) {
/*  18 */       this.a = aint[0];
/*  19 */       this.b = aint[1];
/*  20 */       this.c = aint[2];
/*  21 */       this.d = aint[3];
/*  22 */       this.e = aint[4];
/*  23 */       this.f = aint[5];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox a() {
/*  29 */     return new StructureBoundingBox(2147483647, 2147483647, 2147483647, -2147483648, -2147483648, -2147483648);
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox b() {
/*  33 */     return new StructureBoundingBox(-2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647);
/*     */   }
/*     */   
/*     */   public static StructureBoundingBox a(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, EnumDirection enumdirection) {
/*  37 */     switch (enumdirection) {
/*     */       case NORTH:
/*  39 */         return new StructureBoundingBox(i + l, j + i1, k - i2 + 1 + j1, i + k1 - 1 + l, j + l1 - 1 + i1, k + j1);
/*     */       case SOUTH:
/*  41 */         return new StructureBoundingBox(i + l, j + i1, k + j1, i + k1 - 1 + l, j + l1 - 1 + i1, k + i2 - 1 + j1);
/*     */       case WEST:
/*  43 */         return new StructureBoundingBox(i - i2 + 1 + j1, j + i1, k + l, i + j1, j + l1 - 1 + i1, k + k1 - 1 + l);
/*     */       case EAST:
/*  45 */         return new StructureBoundingBox(i + j1, j + i1, k + l, i + i2 - 1 + j1, j + l1 - 1 + i1, k + k1 - 1 + l);
/*     */     } 
/*  47 */     return new StructureBoundingBox(i + l, j + i1, k + j1, i + k1 - 1 + l, j + l1 - 1 + i1, k + i2 - 1 + j1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static StructureBoundingBox a(int i, int j, int k, int l, int i1, int j1) {
/*  52 */     return new StructureBoundingBox(Math.min(i, l), Math.min(j, i1), Math.min(k, j1), Math.max(i, l), Math.max(j, i1), Math.max(k, j1));
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(StructureBoundingBox structureboundingbox) {
/*  56 */     this.a = structureboundingbox.a;
/*  57 */     this.b = structureboundingbox.b;
/*  58 */     this.c = structureboundingbox.c;
/*  59 */     this.d = structureboundingbox.d;
/*  60 */     this.e = structureboundingbox.e;
/*  61 */     this.f = structureboundingbox.f;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(int i, int j, int k, int l, int i1, int j1) {
/*  65 */     this.a = i;
/*  66 */     this.b = j;
/*  67 */     this.c = k;
/*  68 */     this.d = l;
/*  69 */     this.e = i1;
/*  70 */     this.f = j1;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(BaseBlockPosition baseblockposition, BaseBlockPosition baseblockposition1) {
/*  74 */     this.a = Math.min(baseblockposition.getX(), baseblockposition1.getX());
/*  75 */     this.b = Math.min(baseblockposition.getY(), baseblockposition1.getY());
/*  76 */     this.c = Math.min(baseblockposition.getZ(), baseblockposition1.getZ());
/*  77 */     this.d = Math.max(baseblockposition.getX(), baseblockposition1.getX());
/*  78 */     this.e = Math.max(baseblockposition.getY(), baseblockposition1.getY());
/*  79 */     this.f = Math.max(baseblockposition.getZ(), baseblockposition1.getZ());
/*     */   }
/*     */   
/*     */   public StructureBoundingBox(int i, int j, int k, int l) {
/*  83 */     this.a = i;
/*  84 */     this.c = j;
/*  85 */     this.d = k;
/*  86 */     this.f = l;
/*  87 */     this.b = 1;
/*  88 */     this.e = 512;
/*     */   }
/*     */   public final boolean intersects(StructureBoundingBox boundingBox) {
/*  91 */     return b(boundingBox);
/*     */   } public boolean b(StructureBoundingBox structureboundingbox) {
/*  93 */     return (this.d >= structureboundingbox.a && this.a <= structureboundingbox.d && this.f >= structureboundingbox.c && this.c <= structureboundingbox.f && this.e >= structureboundingbox.b && this.b <= structureboundingbox.e);
/*     */   }
/*     */   
/*     */   public boolean a(int i, int j, int k, int l) {
/*  97 */     return (this.d >= i && this.a <= k && this.f >= j && this.c <= l);
/*     */   }
/*     */   
/*     */   public void c(StructureBoundingBox structureboundingbox) {
/* 101 */     this.a = Math.min(this.a, structureboundingbox.a);
/* 102 */     this.b = Math.min(this.b, structureboundingbox.b);
/* 103 */     this.c = Math.min(this.c, structureboundingbox.c);
/* 104 */     this.d = Math.max(this.d, structureboundingbox.d);
/* 105 */     this.e = Math.max(this.e, structureboundingbox.e);
/* 106 */     this.f = Math.max(this.f, structureboundingbox.f);
/*     */   }
/*     */   
/*     */   public void a(int i, int j, int k) {
/* 110 */     this.a += i;
/* 111 */     this.b += j;
/* 112 */     this.c += k;
/* 113 */     this.d += i;
/* 114 */     this.e += j;
/* 115 */     this.f += k;
/*     */   }
/*     */   
/*     */   public StructureBoundingBox b(int i, int j, int k) {
/* 119 */     return new StructureBoundingBox(this.a + i, this.b + j, this.c + k, this.d + i, this.e + j, this.f + k);
/*     */   }
/*     */   
/*     */   public void a(BaseBlockPosition baseblockposition) {
/* 123 */     a(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ());
/*     */   }
/*     */   public final boolean hasPoint(BaseBlockPosition baseblockposition) {
/* 126 */     return b(baseblockposition);
/*     */   } public boolean b(BaseBlockPosition baseblockposition) {
/* 128 */     return (baseblockposition.getX() >= this.a && baseblockposition.getX() <= this.d && baseblockposition.getZ() >= this.c && baseblockposition.getZ() <= this.f && baseblockposition.getY() >= this.b && baseblockposition.getY() <= this.e);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition c() {
/* 132 */     return new BaseBlockPosition(this.d - this.a, this.e - this.b, this.f - this.c);
/*     */   }
/*     */   
/*     */   public int d() {
/* 136 */     return this.d - this.a + 1;
/*     */   }
/*     */   
/*     */   public int e() {
/* 140 */     return this.e - this.b + 1;
/*     */   }
/*     */   
/*     */   public int f() {
/* 144 */     return this.f - this.c + 1;
/*     */   }
/*     */   
/*     */   public BaseBlockPosition g() {
/* 148 */     return new BlockPosition(this.a + (this.d - this.a + 1) / 2, this.b + (this.e - this.b + 1) / 2, this.c + (this.f - this.c + 1) / 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 152 */     return MoreObjects.toStringHelper(this).add("x0", this.a).add("y0", this.b).add("z0", this.c).add("x1", this.d).add("y1", this.e).add("z1", this.f).toString();
/*     */   }
/*     */   
/*     */   public NBTTagIntArray h() {
/* 156 */     return new NBTTagIntArray(new int[] { this.a, this.b, this.c, this.d, this.e, this.f });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureBoundingBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */