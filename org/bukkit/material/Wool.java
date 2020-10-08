/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Wool
/*    */   extends MaterialData
/*    */   implements Colorable
/*    */ {
/*    */   public Wool() {
/* 14 */     super(Material.LEGACY_WOOL);
/*    */   }
/*    */   
/*    */   public Wool(DyeColor color) {
/* 18 */     this();
/* 19 */     setColor(color);
/*    */   }
/*    */   
/*    */   public Wool(Material type) {
/* 23 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Wool(Material type, byte data) {
/* 33 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 43 */     return DyeColor.getByWoolData(getData());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColor(DyeColor color) {
/* 53 */     setData(color.getWoolData());
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 58 */     return getColor() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public Wool clone() {
/* 63 */     return (Wool)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Wool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */