/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.GrassSpecies;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class LongGrass
/*    */   extends MaterialData
/*    */ {
/*    */   public LongGrass() {
/* 15 */     super(Material.LEGACY_LONG_GRASS);
/*    */   }
/*    */   
/*    */   public LongGrass(GrassSpecies species) {
/* 19 */     this();
/* 20 */     setSpecies(species);
/*    */   }
/*    */   
/*    */   public LongGrass(Material type) {
/* 24 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public LongGrass(Material type, byte data) {
/* 34 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GrassSpecies getSpecies() {
/* 43 */     return GrassSpecies.getByData(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSpecies(GrassSpecies species) {
/* 52 */     setData(species.getData());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return getSpecies() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public LongGrass clone() {
/* 62 */     return (LongGrass)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\LongGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */