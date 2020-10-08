/*     */ package org.bukkit.util;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.primitives.Doubles;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SerializableAs("Vector")
/*     */ public class Vector
/*     */   implements Cloneable, ConfigurationSerializable
/*     */ {
/*     */   private static final long serialVersionUID = -2657651106777219169L;
/*  24 */   private static Random random = new Random();
/*     */ 
/*     */   
/*     */   private static final double epsilon = 1.0E-6D;
/*     */ 
/*     */   
/*     */   protected double x;
/*     */ 
/*     */   
/*     */   protected double y;
/*     */   
/*     */   protected double z;
/*     */ 
/*     */   
/*     */   public Vector() {
/*  39 */     this.x = 0.0D;
/*  40 */     this.y = 0.0D;
/*  41 */     this.z = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector(int x, int y, int z) {
/*  52 */     this.x = x;
/*  53 */     this.y = y;
/*  54 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector(double x, double y, double z) {
/*  65 */     this.x = x;
/*  66 */     this.y = y;
/*  67 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector(float x, float y, float z) {
/*  78 */     this.x = x;
/*  79 */     this.y = y;
/*  80 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector add(@NotNull Vector vec) {
/*  91 */     this.x += vec.x;
/*  92 */     this.y += vec.y;
/*  93 */     this.z += vec.z;
/*  94 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector subtract(@NotNull Vector vec) {
/* 105 */     this.x -= vec.x;
/* 106 */     this.y -= vec.y;
/* 107 */     this.z -= vec.z;
/* 108 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector multiply(@NotNull Vector vec) {
/* 119 */     this.x *= vec.x;
/* 120 */     this.y *= vec.y;
/* 121 */     this.z *= vec.z;
/* 122 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector divide(@NotNull Vector vec) {
/* 133 */     this.x /= vec.x;
/* 134 */     this.y /= vec.y;
/* 135 */     this.z /= vec.z;
/* 136 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector copy(@NotNull Vector vec) {
/* 147 */     this.x = vec.x;
/* 148 */     this.y = vec.y;
/* 149 */     this.z = vec.z;
/* 150 */     return this;
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
/*     */   
/*     */   public double length() {
/* 163 */     return Math.sqrt(NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double lengthSquared() {
/* 172 */     return NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z);
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
/*     */ 
/*     */   
/*     */   public double distance(@NotNull Vector o) {
/* 186 */     return Math.sqrt(NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanceSquared(@NotNull Vector o) {
/* 196 */     return NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float angle(@NotNull Vector other) {
/* 206 */     double dot = Doubles.constrainToRange(dot(other) / length() * other.length(), -1.0D, 1.0D);
/*     */     
/* 208 */     return (float)Math.acos(dot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector midpoint(@NotNull Vector other) {
/* 219 */     this.x = (this.x + other.x) / 2.0D;
/* 220 */     this.y = (this.y + other.y) / 2.0D;
/* 221 */     this.z = (this.z + other.z) / 2.0D;
/* 222 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector getMidpoint(@NotNull Vector other) {
/* 233 */     double x = (this.x + other.x) / 2.0D;
/* 234 */     double y = (this.y + other.y) / 2.0D;
/* 235 */     double z = (this.z + other.z) / 2.0D;
/* 236 */     return new Vector(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector multiply(int m) {
/* 248 */     this.x *= m;
/* 249 */     this.y *= m;
/* 250 */     this.z *= m;
/* 251 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector multiply(double m) {
/* 263 */     this.x *= m;
/* 264 */     this.y *= m;
/* 265 */     this.z *= m;
/* 266 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector multiply(float m) {
/* 278 */     this.x *= m;
/* 279 */     this.y *= m;
/* 280 */     this.z *= m;
/* 281 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double dot(@NotNull Vector other) {
/* 292 */     return this.x * other.x + this.y * other.y + this.z * other.z;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector crossProduct(@NotNull Vector o) {
/* 309 */     double newX = this.y * o.z - o.y * this.z;
/* 310 */     double newY = this.z * o.x - o.z * this.x;
/* 311 */     double newZ = this.x * o.y - o.x * this.y;
/*     */     
/* 313 */     this.x = newX;
/* 314 */     this.y = newY;
/* 315 */     this.z = newZ;
/* 316 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector getCrossProduct(@NotNull Vector o) {
/* 333 */     double x = this.y * o.z - o.y * this.z;
/* 334 */     double y = this.z * o.x - o.z * this.x;
/* 335 */     double z = this.x * o.y - o.x * this.y;
/* 336 */     return new Vector(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector normalize() {
/* 346 */     double length = length();
/*     */     
/* 348 */     this.x /= length;
/* 349 */     this.y /= length;
/* 350 */     this.z /= length;
/*     */     
/* 352 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector zero() {
/* 362 */     this.x = 0.0D;
/* 363 */     this.y = 0.0D;
/* 364 */     this.z = 0.0D;
/* 365 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Vector normalizeZeros() {
/* 375 */     if (this.x == -0.0D) this.x = 0.0D; 
/* 376 */     if (this.y == -0.0D) this.y = 0.0D; 
/* 377 */     if (this.z == -0.0D) this.z = 0.0D; 
/* 378 */     return this;
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
/*     */ 
/*     */   
/*     */   public boolean isInAABB(@NotNull Vector min, @NotNull Vector max) {
/* 392 */     return (this.x >= min.x && this.x <= max.x && this.y >= min.y && this.y <= max.y && this.z >= min.z && this.z <= max.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInSphere(@NotNull Vector origin, double radius) {
/* 403 */     return (NumberConversions.square(origin.x - this.x) + NumberConversions.square(origin.y - this.y) + NumberConversions.square(origin.z - this.z) <= NumberConversions.square(radius));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNormalized() {
/* 412 */     return (Math.abs(lengthSquared() - 1.0D) < getEpsilon());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector rotateAroundX(double angle) {
/* 429 */     double angleCos = Math.cos(angle);
/* 430 */     double angleSin = Math.sin(angle);
/*     */     
/* 432 */     double y = angleCos * getY() - angleSin * getZ();
/* 433 */     double z = angleSin * getY() + angleCos * getZ();
/* 434 */     return setY(y).setZ(z);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector rotateAroundY(double angle) {
/* 451 */     double angleCos = Math.cos(angle);
/* 452 */     double angleSin = Math.sin(angle);
/*     */     
/* 454 */     double x = angleCos * getX() + angleSin * getZ();
/* 455 */     double z = -angleSin * getX() + angleCos * getZ();
/* 456 */     return setX(x).setZ(z);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector rotateAroundZ(double angle) {
/* 473 */     double angleCos = Math.cos(angle);
/* 474 */     double angleSin = Math.sin(angle);
/*     */     
/* 476 */     double x = angleCos * getX() - angleSin * getY();
/* 477 */     double y = angleSin * getX() + angleCos * getY();
/* 478 */     return setX(x).setY(y);
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
/*     */   @NotNull
/*     */   public Vector rotateAroundAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
/* 504 */     Preconditions.checkArgument((axis != null), "The provided axis vector was null");
/*     */     
/* 506 */     return rotateAroundNonUnitAxis(axis.isNormalized() ? axis : axis.clone().normalize(), angle);
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
/*     */   @NotNull
/*     */   public Vector rotateAroundNonUnitAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
/* 531 */     Preconditions.checkArgument((axis != null), "The provided axis vector was null");
/*     */     
/* 533 */     double x = getX(), y = getY(), z = getZ();
/* 534 */     double x2 = axis.getX(), y2 = axis.getY(), z2 = axis.getZ();
/*     */     
/* 536 */     double cosTheta = Math.cos(angle);
/* 537 */     double sinTheta = Math.sin(angle);
/* 538 */     double dotProduct = dot(axis);
/*     */     
/* 540 */     double xPrime = x2 * dotProduct * (1.0D - cosTheta) + x * cosTheta + (-z2 * y + y2 * z) * sinTheta;
/*     */ 
/*     */     
/* 543 */     double yPrime = y2 * dotProduct * (1.0D - cosTheta) + y * cosTheta + (z2 * x - x2 * z) * sinTheta;
/*     */ 
/*     */     
/* 546 */     double zPrime = z2 * dotProduct * (1.0D - cosTheta) + z * cosTheta + (-y2 * x + x2 * y) * sinTheta;
/*     */ 
/*     */ 
/*     */     
/* 550 */     return setX(xPrime).setY(yPrime).setZ(zPrime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getX() {
/* 559 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockX() {
/* 569 */     return NumberConversions.floor(this.x);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getY() {
/* 578 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockY() {
/* 588 */     return NumberConversions.floor(this.y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getZ() {
/* 597 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBlockZ() {
/* 607 */     return NumberConversions.floor(this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setX(int x) {
/* 618 */     this.x = x;
/* 619 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setX(double x) {
/* 630 */     this.x = x;
/* 631 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setX(float x) {
/* 642 */     this.x = x;
/* 643 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setY(int y) {
/* 654 */     this.y = y;
/* 655 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setY(double y) {
/* 666 */     this.y = y;
/* 667 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setY(float y) {
/* 678 */     this.y = y;
/* 679 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setZ(int z) {
/* 690 */     this.z = z;
/* 691 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setZ(double z) {
/* 702 */     this.z = z;
/* 703 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector setZ(float z) {
/* 714 */     this.z = z;
/* 715 */     return this;
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
/*     */   public boolean equals(Object obj) {
/* 727 */     if (!(obj instanceof Vector)) {
/* 728 */       return false;
/*     */     }
/*     */     
/* 731 */     Vector other = (Vector)obj;
/*     */     
/* 733 */     return (Math.abs(this.x - other.x) < 1.0E-6D && Math.abs(this.y - other.y) < 1.0E-6D && Math.abs(this.z - other.z) < 1.0E-6D && getClass().equals(obj.getClass()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 743 */     int hash = 7;
/*     */     
/* 745 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32L);
/* 746 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32L);
/* 747 */     hash = 79 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32L);
/* 748 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector clone() {
/*     */     try {
/* 760 */       return (Vector)super.clone();
/* 761 */     } catch (CloneNotSupportedException e) {
/* 762 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 771 */     return this.x + "," + this.y + "," + this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Location toLocation(@NotNull World world) {
/* 782 */     return new Location(world, this.x, this.y, this.z);
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
/*     */   @NotNull
/*     */   public Location toLocation(@NotNull World world, float yaw, float pitch) {
/* 795 */     return new Location(world, this.x, this.y, this.z, yaw, pitch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockVector toBlockVector() {
/* 805 */     return new BlockVector(this.x, this.y, this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void checkFinite() throws IllegalArgumentException {
/* 814 */     NumberConversions.checkFinite(this.x, "x not finite");
/* 815 */     NumberConversions.checkFinite(this.y, "y not finite");
/* 816 */     NumberConversions.checkFinite(this.z, "z not finite");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getEpsilon() {
/* 825 */     return 1.0E-6D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Vector getMinimum(@NotNull Vector v1, @NotNull Vector v2) {
/* 837 */     return new Vector(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Vector getMaximum(@NotNull Vector v1, @NotNull Vector v2) {
/* 849 */     return new Vector(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Vector getRandom() {
/* 860 */     return new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/* 866 */     Map<String, Object> result = new LinkedHashMap<>();
/*     */     
/* 868 */     result.put("x", Double.valueOf(getX()));
/* 869 */     result.put("y", Double.valueOf(getY()));
/* 870 */     result.put("z", Double.valueOf(getZ()));
/*     */     
/* 872 */     return result;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static Vector deserialize(@NotNull Map<String, Object> args) {
/* 877 */     double x = 0.0D;
/* 878 */     double y = 0.0D;
/* 879 */     double z = 0.0D;
/*     */     
/* 881 */     if (args.containsKey("x")) {
/* 882 */       x = ((Double)args.get("x")).doubleValue();
/*     */     }
/* 884 */     if (args.containsKey("y")) {
/* 885 */       y = ((Double)args.get("y")).doubleValue();
/*     */     }
/* 887 */     if (args.containsKey("z")) {
/* 888 */       z = ((Double)args.get("z")).doubleValue();
/*     */     }
/*     */     
/* 891 */     return new Vector(x, y, z);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\Vector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */