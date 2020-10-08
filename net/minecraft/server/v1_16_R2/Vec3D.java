/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ 
/*     */ public class Vec3D implements IPosition {
/*     */   public final double x;
/*   7 */   public static final Vec3D ORIGIN = new Vec3D(0.0D, 0.0D, 0.0D); public static Vec3D getZeroVector() { return ORIGIN; }
/*     */   
/*     */   public final double y;
/*     */   public final double z;
/*     */   
/*     */   public static Vec3D a(BaseBlockPosition baseblockposition) {
/*  13 */     return new Vec3D(baseblockposition.getX() + 0.5D, baseblockposition.getY() + 0.5D, baseblockposition.getZ() + 0.5D);
/*     */   }
/*     */   
/*     */   public static Vec3D b(BaseBlockPosition baseblockposition) {
/*  17 */     return new Vec3D(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ());
/*     */   }
/*     */   
/*     */   public static Vec3D c(BaseBlockPosition baseblockposition) {
/*  21 */     return new Vec3D(baseblockposition.getX() + 0.5D, baseblockposition.getY(), baseblockposition.getZ() + 0.5D);
/*     */   }
/*     */   
/*     */   public static Vec3D a(BaseBlockPosition baseblockposition, double d0) {
/*  25 */     return new Vec3D(baseblockposition.getX() + 0.5D, baseblockposition.getY() + d0, baseblockposition.getZ() + 0.5D);
/*     */   }
/*     */   
/*     */   public Vec3D(double d0, double d1, double d2) {
/*  29 */     this.x = d0;
/*  30 */     this.y = d1;
/*  31 */     this.z = d2;
/*     */   }
/*     */   
/*     */   public Vec3D(Vector3fa vector3fa) {
/*  35 */     this(vector3fa.a(), vector3fa.b(), vector3fa.c());
/*     */   }
/*     */   
/*     */   public Vec3D a(Vec3D vec3d) {
/*  39 */     return new Vec3D(vec3d.x - this.x, vec3d.y - this.y, vec3d.z - this.z);
/*     */   }
/*     */   
/*     */   public Vec3D d() {
/*  43 */     double d0 = MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */     
/*  45 */     return (d0 < 1.0E-4D) ? ORIGIN : new Vec3D(this.x / d0, this.y / d0, this.z / d0);
/*     */   }
/*     */   
/*     */   public double b(Vec3D vec3d) {
/*  49 */     return this.x * vec3d.x + this.y * vec3d.y + this.z * vec3d.z;
/*     */   }
/*     */   
/*     */   public Vec3D c(Vec3D vec3d) {
/*  53 */     return new Vec3D(this.y * vec3d.z - this.z * vec3d.y, this.z * vec3d.x - this.x * vec3d.z, this.x * vec3d.y - this.y * vec3d.x);
/*     */   }
/*     */   
/*     */   public Vec3D d(Vec3D vec3d) {
/*  57 */     return a(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   
/*     */   public Vec3D a(double d0, double d1, double d2) {
/*  61 */     return add(-d0, -d1, -d2);
/*     */   }
/*     */   public final Vec3D add(Vec3D vec3d) {
/*  64 */     return e(vec3d);
/*     */   } public Vec3D e(Vec3D vec3d) {
/*  66 */     return add(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   
/*     */   public Vec3D add(double d0, double d1, double d2) {
/*  70 */     return new Vec3D(this.x + d0, this.y + d1, this.z + d2);
/*     */   }
/*     */   
/*     */   public boolean a(IPosition iposition, double d0) {
/*  74 */     return (c(iposition.getX(), iposition.getY(), iposition.getZ()) < d0 * d0);
/*     */   }
/*     */   
/*     */   public double f(Vec3D vec3d) {
/*  78 */     double d0 = vec3d.x - this.x;
/*  79 */     double d1 = vec3d.y - this.y;
/*  80 */     double d2 = vec3d.z - this.z;
/*     */     
/*  82 */     return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
/*     */   }
/*     */   
/*     */   public double distanceSquared(Vec3D vec3d) {
/*  86 */     double d0 = vec3d.x - this.x;
/*  87 */     double d1 = vec3d.y - this.y;
/*  88 */     double d2 = vec3d.z - this.z;
/*     */     
/*  90 */     return d0 * d0 + d1 * d1 + d2 * d2;
/*     */   }
/*     */   
/*     */   public double c(double d0, double d1, double d2) {
/*  94 */     double d3 = d0 - this.x;
/*  95 */     double d4 = d1 - this.y;
/*  96 */     double d5 = d2 - this.z;
/*     */     
/*  98 */     return d3 * d3 + d4 * d4 + d5 * d5;
/*     */   }
/*     */   
/*     */   public Vec3D a(double d0) {
/* 102 */     return d(d0, d0, d0);
/*     */   }
/*     */   
/*     */   public Vec3D h(Vec3D vec3d) {
/* 106 */     return d(vec3d.x, vec3d.y, vec3d.z);
/*     */   }
/*     */   
/*     */   public Vec3D d(double d0, double d1, double d2) {
/* 110 */     return new Vec3D(this.x * d0, this.y * d1, this.z * d2);
/*     */   }
/*     */   public final double magnitude() {
/* 113 */     return f();
/*     */   } public double f() {
/* 115 */     return MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
/*     */   }
/*     */   public final double magnitudeSquared() {
/* 118 */     return g();
/*     */   } public double g() {
/* 120 */     return this.x * this.x + this.y * this.y + this.z * this.z;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 124 */     if (this == object)
/* 125 */       return true; 
/* 126 */     if (!(object instanceof Vec3D)) {
/* 127 */       return false;
/*     */     }
/* 129 */     Vec3D vec3d = (Vec3D)object;
/*     */     
/* 131 */     return (Double.compare(vec3d.x, this.x) != 0) ? false : ((Double.compare(vec3d.y, this.y) != 0) ? false : ((Double.compare(vec3d.z, this.z) == 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 136 */     long i = Double.doubleToLongBits(this.x);
/* 137 */     int j = (int)(i ^ i >>> 32L);
/*     */     
/* 139 */     i = Double.doubleToLongBits(this.y);
/* 140 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 141 */     i = Double.doubleToLongBits(this.z);
/* 142 */     j = 31 * j + (int)(i ^ i >>> 32L);
/* 143 */     return j;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 147 */     return "(" + this.x + ", " + this.y + ", " + this.z + ")";
/*     */   }
/*     */   
/*     */   public Vec3D a(float f) {
/* 151 */     float f1 = MathHelper.cos(f);
/* 152 */     float f2 = MathHelper.sin(f);
/* 153 */     double d0 = this.x;
/* 154 */     double d1 = this.y * f1 + this.z * f2;
/* 155 */     double d2 = this.z * f1 - this.y * f2;
/*     */     
/* 157 */     return new Vec3D(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public Vec3D b(float f) {
/* 161 */     float f1 = MathHelper.cos(f);
/* 162 */     float f2 = MathHelper.sin(f);
/* 163 */     double d0 = this.x * f1 + this.z * f2;
/* 164 */     double d1 = this.y;
/* 165 */     double d2 = this.z * f1 - this.x * f2;
/*     */     
/* 167 */     return new Vec3D(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public Vec3D a(EnumSet<EnumDirection.EnumAxis> enumset) {
/* 171 */     double d0 = enumset.contains(EnumDirection.EnumAxis.X) ? MathHelper.floor(this.x) : this.x;
/* 172 */     double d1 = enumset.contains(EnumDirection.EnumAxis.Y) ? MathHelper.floor(this.y) : this.y;
/* 173 */     double d2 = enumset.contains(EnumDirection.EnumAxis.Z) ? MathHelper.floor(this.z) : this.z;
/*     */     
/* 175 */     return new Vec3D(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public double a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/* 179 */     return enumdirection_enumaxis.a(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */   
/*     */   public final double getX() {
/* 184 */     return this.x;
/*     */   }
/*     */ 
/*     */   
/*     */   public final double getY() {
/* 189 */     return this.y;
/*     */   }
/*     */ 
/*     */   
/*     */   public final double getZ() {
/* 194 */     return this.z;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Vec3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */