/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class DetectorRail
/*    */   extends ExtendedRails
/*    */   implements PressureSensor
/*    */ {
/*    */   public DetectorRail() {
/* 14 */     super(Material.LEGACY_DETECTOR_RAIL);
/*    */   }
/*    */   
/*    */   public DetectorRail(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public DetectorRail(Material type, byte data) {
/* 28 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPressed() {
/* 33 */     return ((getData() & 0x8) == 8);
/*    */   }
/*    */   
/*    */   public void setPressed(boolean isPressed) {
/* 37 */     setData((byte)(isPressed ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*    */   }
/*    */ 
/*    */   
/*    */   public DetectorRail clone() {
/* 42 */     return (DetectorRail)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\DetectorRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */