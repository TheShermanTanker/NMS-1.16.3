/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class RedstoneTorch
/*    */   extends Torch
/*    */   implements Redstone
/*    */ {
/*    */   public RedstoneTorch() {
/* 14 */     super(Material.LEGACY_REDSTONE_TORCH_ON);
/*    */   }
/*    */   
/*    */   public RedstoneTorch(Material type) {
/* 18 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public RedstoneTorch(Material type, byte data) {
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
/* 39 */     return (getItemType() == Material.LEGACY_REDSTONE_TORCH_ON);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 44 */     return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
/*    */   }
/*    */ 
/*    */   
/*    */   public RedstoneTorch clone() {
/* 49 */     return (RedstoneTorch)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\RedstoneTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */