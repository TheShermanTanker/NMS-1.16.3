/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityFish;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityTropicalFish;
/*     */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.TropicalFish;
/*     */ 
/*     */ public class CraftTropicalFish extends CraftFish implements TropicalFish {
/*     */   public CraftTropicalFish(CraftServer server, EntityTropicalFish entity) {
/*  14 */     super(server, (EntityFish)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityTropicalFish getHandle() {
/*  19 */     return (EntityTropicalFish)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  24 */     return "CraftTropicalFish";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  29 */     return EntityType.TROPICAL_FISH;
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getPatternColor() {
/*  34 */     return getPatternColor(getHandle().getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPatternColor(DyeColor color) {
/*  39 */     getHandle().setVariant(getData(color, getBodyColor(), getPattern()));
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getBodyColor() {
/*  44 */     return getBodyColor(getHandle().getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBodyColor(DyeColor color) {
/*  49 */     getHandle().setVariant(getData(getPatternColor(), color, getPattern()));
/*     */   }
/*     */ 
/*     */   
/*     */   public TropicalFish.Pattern getPattern() {
/*  54 */     return getPattern(getHandle().getVariant());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPattern(TropicalFish.Pattern pattern) {
/*  59 */     getHandle().setVariant(getData(getPatternColor(), getBodyColor(), pattern));
/*     */   }
/*     */   
/*     */   public enum CraftPattern {
/*  63 */     KOB(0, false),
/*  64 */     SUNSTREAK(1, false),
/*  65 */     SNOOPER(2, false),
/*  66 */     DASHER(3, false),
/*  67 */     BRINELY(4, false),
/*  68 */     SPOTTY(5, false),
/*  69 */     FLOPPER(0, true),
/*  70 */     STRIPEY(1, true),
/*  71 */     GLITTER(2, true),
/*  72 */     BLOCKFISH(3, true),
/*  73 */     BETTY(4, true),
/*  74 */     CLAYFISH(5, true);
/*     */     
/*     */     private final int variant;
/*     */     
/*     */     private final boolean large;
/*     */     
/*  80 */     private static final Map<Integer, TropicalFish.Pattern> BY_DATA = new HashMap<>();
/*     */     
/*     */     static {
/*  83 */       for (CraftPattern type : values()) {
/*  84 */         BY_DATA.put(Integer.valueOf(type.getDataValue()), TropicalFish.Pattern.values()[type.ordinal()]);
/*     */       }
/*     */     }
/*     */     
/*     */     public static TropicalFish.Pattern fromData(int data) {
/*  89 */       return BY_DATA.get(Integer.valueOf(data));
/*     */     }
/*     */     
/*     */     CraftPattern(int variant, boolean large) {
/*  93 */       this.variant = variant;
/*  94 */       this.large = large;
/*     */     }
/*     */     
/*     */     public int getDataValue() {
/*  98 */       return this.variant << 8 | (this.large ? 1 : 0);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int getData(DyeColor patternColor, DyeColor bodyColor, TropicalFish.Pattern type) {
/* 103 */     return patternColor.getWoolData() << 24 | bodyColor.getWoolData() << 16 | CraftPattern.values()[type.ordinal()].getDataValue();
/*     */   }
/*     */   
/*     */   public static DyeColor getPatternColor(int data) {
/* 107 */     return DyeColor.getByWoolData((byte)(data >> 24 & 0xFF));
/*     */   }
/*     */   
/*     */   public static DyeColor getBodyColor(int data) {
/* 111 */     return DyeColor.getByWoolData((byte)(data >> 16 & 0xFF));
/*     */   }
/*     */   
/*     */   public static TropicalFish.Pattern getPattern(int data) {
/* 115 */     return CraftPattern.fromData(data & 0xFFFF);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTropicalFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */