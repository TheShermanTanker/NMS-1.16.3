/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PressurePlate
/*    */   extends MaterialData
/*    */   implements PressureSensor
/*    */ {
/*    */   public PressurePlate() {
/* 14 */     super(Material.LEGACY_WOOD_PLATE);
/*    */   }
/*    */   
/*    */   public PressurePlate(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public PressurePlate(Material type, byte data) {
/* 28 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPressed() {
/* 33 */     return (getData() == 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return super.toString() + (isPressed() ? " PRESSED" : "");
/*    */   }
/*    */ 
/*    */   
/*    */   public PressurePlate clone() {
/* 43 */     return (PressurePlate)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\PressurePlate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */