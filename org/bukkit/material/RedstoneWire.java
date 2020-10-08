/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class RedstoneWire
/*    */   extends MaterialData
/*    */   implements Redstone
/*    */ {
/*    */   public RedstoneWire() {
/* 14 */     super(Material.LEGACY_REDSTONE_WIRE);
/*    */   }
/*    */   
/*    */   public RedstoneWire(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public RedstoneWire(Material type, byte data) {
/* 28 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 39 */     return (getData() > 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
/*    */   }
/*    */ 
/*    */   
/*    */   public RedstoneWire clone() {
/* 49 */     return (RedstoneWire)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\RedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */