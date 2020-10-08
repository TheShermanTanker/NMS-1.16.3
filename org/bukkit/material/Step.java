/*     */ package org.bukkit.material;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Step
/*     */   extends TexturedMaterial
/*     */ {
/*  15 */   private static final List<Material> textures = new ArrayList<>();
/*     */   static {
/*  17 */     textures.add(Material.LEGACY_STONE);
/*  18 */     textures.add(Material.LEGACY_SANDSTONE);
/*  19 */     textures.add(Material.LEGACY_WOOD);
/*  20 */     textures.add(Material.LEGACY_COBBLESTONE);
/*  21 */     textures.add(Material.LEGACY_BRICK);
/*  22 */     textures.add(Material.LEGACY_SMOOTH_BRICK);
/*  23 */     textures.add(Material.LEGACY_NETHER_BRICK);
/*  24 */     textures.add(Material.LEGACY_QUARTZ_BLOCK);
/*     */   }
/*     */   
/*     */   public Step() {
/*  28 */     super(Material.LEGACY_STEP);
/*     */   }
/*     */   
/*     */   public Step(Material type) {
/*  32 */     super(textures.contains(type) ? Material.LEGACY_STEP : type);
/*  33 */     if (textures.contains(type)) {
/*  34 */       setMaterial(type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Step(Material type, byte data) {
/*  45 */     super(type, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Material> getTextures() {
/*  50 */     return textures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInverted() {
/*  59 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInverted(boolean inv) {
/*  69 */     int dat = getData() & 0x7;
/*  70 */     if (inv) {
/*  71 */       dat |= 0x8;
/*     */     }
/*  73 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTextureIndex() {
/*  83 */     return getData() & 0x7;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   protected void setTextureIndex(int idx) {
/*  94 */     setData((byte)(getData() & 0x8 | idx));
/*     */   }
/*     */ 
/*     */   
/*     */   public Step clone() {
/*  99 */     return (Step)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 104 */     return super.toString() + (isInverted() ? "inverted" : "");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Step.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */