/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class EnderChest
/*    */   extends DirectionalContainer
/*    */ {
/*    */   public EnderChest() {
/* 16 */     super(Material.LEGACY_ENDER_CHEST);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnderChest(BlockFace direction) {
/* 25 */     this();
/* 26 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public EnderChest(Material type) {
/* 30 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public EnderChest(Material type, byte data) {
/* 40 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnderChest clone() {
/* 45 */     return (EnderChest)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\EnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */