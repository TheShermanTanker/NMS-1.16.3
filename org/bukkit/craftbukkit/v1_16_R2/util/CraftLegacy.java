/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public final class CraftLegacy
/*    */ {
/*    */   public static Material fromLegacy(Material material) {
/* 18 */     if (material == null || !material.isLegacy()) {
/* 19 */       return material;
/*    */     }
/*    */     
/* 22 */     return org.bukkit.craftbukkit.v1_16_R2.legacy.CraftLegacy.fromLegacy(material);
/*    */   }
/*    */   
/*    */   public static Material fromLegacy(MaterialData materialData) {
/* 26 */     return org.bukkit.craftbukkit.v1_16_R2.legacy.CraftLegacy.fromLegacy(materialData);
/*    */   }
/*    */   
/*    */   public static Material[] modern_values() {
/* 30 */     Material[] values = Material.values();
/* 31 */     return Arrays.<Material>copyOfRange(values, 0, Material.LEGACY_AIR.ordinal());
/*    */   }
/*    */   
/*    */   public static int modern_ordinal(Material material) {
/* 35 */     if (material.isLegacy())
/*    */     {
/* 37 */       throw new NoSuchFieldError("Legacy field ordinal: " + material);
/*    */     }
/*    */     
/* 40 */     return material.ordinal();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */