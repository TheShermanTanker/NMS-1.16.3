/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PoweredRail
/*    */   extends ExtendedRails
/*    */   implements Redstone
/*    */ {
/*    */   public PoweredRail() {
/* 14 */     super(Material.LEGACY_POWERED_RAIL);
/*    */   }
/*    */   
/*    */   public PoweredRail(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public PoweredRail(Material type, byte data) {
/* 28 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 33 */     return ((getData() & 0x8) == 8);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setPowered(boolean isPowered) {
/* 42 */     setData((byte)(isPowered ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*    */   }
/*    */ 
/*    */   
/*    */   public PoweredRail clone() {
/* 47 */     return (PoweredRail)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\PoweredRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */