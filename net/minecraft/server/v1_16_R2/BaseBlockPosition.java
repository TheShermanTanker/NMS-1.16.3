/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.util.stream.IntStream;
/*     */ import javax.annotation.concurrent.Immutable;
/*     */ 
/*     */ @Immutable
/*     */ public class BaseBlockPosition implements Comparable<BaseBlockPosition> {
/*     */   static {
/*  11 */     c = Codec.INT_STREAM.comapFlatMap(intstream -> SystemUtils.a(intstream, 3).map(()), baseblockposition -> IntStream.of(new int[] { baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ() }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final Codec<BaseBlockPosition> c;
/*     */   
/*  18 */   public static final BaseBlockPosition ZERO = new BaseBlockPosition(0, 0, 0);
/*     */   
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected int e;
/*     */   
/*     */   public boolean isValidLocation() {
/*  25 */     return (getX() >= -30000000 && getZ() >= -30000000 && getX() < 30000000 && getZ() < 30000000 && getY() >= 0 && getY() < 256);
/*     */   }
/*     */   public boolean isInvalidYLocation() {
/*  28 */     return (this.b < 0 || this.b >= 256);
/*     */   }
/*     */ 
/*     */   
/*     */   public BaseBlockPosition(int i, int j, int k) {
/*  33 */     this.a = i;
/*  34 */     this.b = j;
/*  35 */     this.e = k;
/*     */   }
/*     */   
/*     */   public BaseBlockPosition(double d0, double d1, double d2) {
/*  39 */     this(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2));
/*     */   }
/*     */   
/*     */   public final boolean equals(Object object) {
/*  43 */     if (this == object)
/*  44 */       return true; 
/*  45 */     if (!(object instanceof BaseBlockPosition)) {
/*  46 */       return false;
/*     */     }
/*  48 */     BaseBlockPosition baseblockposition = (BaseBlockPosition)object;
/*     */     
/*  50 */     return (getX() != baseblockposition.getX()) ? false : ((getY() != baseblockposition.getY()) ? false : ((getZ() == baseblockposition.getZ())));
/*     */   }
/*     */ 
/*     */   
/*     */   public final int hashCode() {
/*  55 */     return (getY() + getZ() * 31) * 31 + getX();
/*     */   }
/*     */   
/*     */   public int compareTo(BaseBlockPosition baseblockposition) {
/*  59 */     return (getY() == baseblockposition.getY()) ? ((getZ() == baseblockposition.getZ()) ? (getX() - baseblockposition.getX()) : (getZ() - baseblockposition.getZ())) : (getY() - baseblockposition.getY());
/*     */   }
/*     */   
/*     */   public final int getX() {
/*  63 */     return this.a;
/*     */   }
/*     */   
/*     */   public final int getY() {
/*  67 */     return this.b;
/*     */   }
/*     */   
/*     */   public final int getZ() {
/*  71 */     return this.e;
/*     */   }
/*     */   
/*     */   protected void o_unused(int i) {
/*  75 */     this.a = i;
/*     */   }
/*     */   
/*     */   protected void p_unused(int i) {
/*  79 */     this.b = i;
/*     */   }
/*     */   
/*     */   protected void q_unused(int i) {
/*  83 */     this.e = i;
/*     */   }
/*     */   
/*     */   public BaseBlockPosition up() {
/*  87 */     return up(1);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition up(int i) {
/*  91 */     return shift(EnumDirection.UP, i);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition down() {
/*  95 */     return down(1);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition down(int i) {
/*  99 */     return shift(EnumDirection.DOWN, i);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition shift(EnumDirection enumdirection, int i) {
/* 103 */     return (i == 0) ? this : new BaseBlockPosition(getX() + enumdirection.getAdjacentX() * i, getY() + enumdirection.getAdjacentY() * i, getZ() + enumdirection.getAdjacentZ() * i);
/*     */   }
/*     */   
/*     */   public BaseBlockPosition d(BaseBlockPosition baseblockposition) {
/* 107 */     return new BaseBlockPosition(getY() * baseblockposition.getZ() - getZ() * baseblockposition.getY(), getZ() * baseblockposition.getX() - getX() * baseblockposition.getZ(), getX() * baseblockposition.getY() - getY() * baseblockposition.getX());
/*     */   }
/*     */   
/*     */   public boolean a(BaseBlockPosition baseblockposition, double d0) {
/* 111 */     return (distanceSquared(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ(), false) < d0 * d0);
/*     */   }
/*     */   
/*     */   public boolean a(IPosition iposition, double d0) {
/* 115 */     return (distanceSquared(iposition.getX(), iposition.getY(), iposition.getZ(), true) < d0 * d0);
/*     */   }
/*     */   public final double distanceSquared(BaseBlockPosition baseblockposition) {
/* 118 */     return j(baseblockposition);
/*     */   } public double j(BaseBlockPosition baseblockposition) {
/* 120 */     return distanceSquared(baseblockposition.getX(), baseblockposition.getY(), baseblockposition.getZ(), true);
/*     */   }
/*     */   
/*     */   public double a(IPosition iposition, boolean flag) {
/* 124 */     return distanceSquared(iposition.getX(), iposition.getY(), iposition.getZ(), flag);
/*     */   }
/*     */   
/*     */   public double distanceSquared(double d0, double d1, double d2, boolean flag) {
/* 128 */     double d3 = flag ? 0.5D : 0.0D;
/* 129 */     double d4 = getX() + d3 - d0;
/* 130 */     double d5 = getY() + d3 - d1;
/* 131 */     double d6 = getZ() + d3 - d2;
/*     */     
/* 133 */     return d4 * d4 + d5 * d5 + d6 * d6;
/*     */   }
/*     */   
/*     */   public int k(BaseBlockPosition baseblockposition) {
/* 137 */     float f = Math.abs(baseblockposition.getX() - getX());
/* 138 */     float f1 = Math.abs(baseblockposition.getY() - getY());
/* 139 */     float f2 = Math.abs(baseblockposition.getZ() - getZ());
/*     */     
/* 141 */     return (int)(f + f1 + f2);
/*     */   }
/*     */   
/*     */   public int a(EnumDirection.EnumAxis enumdirection_enumaxis) {
/* 145 */     return enumdirection_enumaxis.a(this.a, this.b, this.e);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 149 */     return MoreObjects.toStringHelper(this).add("x", getX()).add("y", getY()).add("z", getZ()).toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BaseBlockPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */