/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Dye
/*    */   extends MaterialData
/*    */   implements Colorable
/*    */ {
/*    */   public Dye() {
/* 15 */     super(Material.LEGACY_INK_SACK);
/*    */   }
/*    */   
/*    */   public Dye(Material type) {
/* 19 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Dye(Material type, byte data) {
/* 29 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Dye(DyeColor color) {
/* 36 */     super(Material.LEGACY_INK_SACK, color.getDyeData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 46 */     return DyeColor.getByDyeData(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColor(DyeColor color) {
/* 56 */     setData(color.getDyeData());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return getColor() + " DYE(" + getData() + ")";
/*    */   }
/*    */ 
/*    */   
/*    */   public Dye clone() {
/* 66 */     return (Dye)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Dye.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */