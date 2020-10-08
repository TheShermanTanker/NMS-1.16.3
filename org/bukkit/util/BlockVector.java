/*     */ package org.bukkit.util;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.bukkit.configuration.serialization.SerializableAs;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @SerializableAs("BlockVector")
/*     */ public class BlockVector
/*     */   extends Vector
/*     */ {
/*     */   public BlockVector() {
/*  20 */     this.x = 0.0D;
/*  21 */     this.y = 0.0D;
/*  22 */     this.z = 0.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockVector(@NotNull Vector vec) {
/*  31 */     this.x = vec.getX();
/*  32 */     this.y = vec.getY();
/*  33 */     this.z = vec.getZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockVector(int x, int y, int z) {
/*  44 */     this.x = x;
/*  45 */     this.y = y;
/*  46 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockVector(double x, double y, double z) {
/*  57 */     this.x = x;
/*  58 */     this.y = y;
/*  59 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockVector(float x, float y, float z) {
/*  70 */     this.x = x;
/*  71 */     this.y = y;
/*  72 */     this.z = z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  83 */     if (!(obj instanceof BlockVector)) {
/*  84 */       return false;
/*     */     }
/*  86 */     BlockVector other = (BlockVector)obj;
/*     */     
/*  88 */     return ((int)other.getX() == (int)this.x && (int)other.getY() == (int)this.y && (int)other.getZ() == (int)this.z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  99 */     return Integer.valueOf((int)this.x).hashCode() >> 13 ^ Integer.valueOf((int)this.y).hashCode() >> 7 ^ Integer.valueOf((int)this.z).hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockVector clone() {
/* 109 */     return (BlockVector)super.clone();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static BlockVector deserialize(@NotNull Map<String, Object> args) {
/* 114 */     double x = 0.0D;
/* 115 */     double y = 0.0D;
/* 116 */     double z = 0.0D;
/*     */     
/* 118 */     if (args.containsKey("x")) {
/* 119 */       x = ((Double)args.get("x")).doubleValue();
/*     */     }
/* 121 */     if (args.containsKey("y")) {
/* 122 */       y = ((Double)args.get("y")).doubleValue();
/*     */     }
/* 124 */     if (args.containsKey("z")) {
/* 125 */       z = ((Double)args.get("z")).doubleValue();
/*     */     }
/*     */     
/* 128 */     return new BlockVector(x, y, z);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\BlockVector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */