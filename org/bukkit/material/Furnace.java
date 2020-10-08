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
/*    */ public class Furnace
/*    */   extends FurnaceAndDispenser
/*    */ {
/*    */   public Furnace() {
/* 16 */     super(Material.LEGACY_FURNACE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Furnace(BlockFace direction) {
/* 25 */     this();
/* 26 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public Furnace(Material type) {
/* 30 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Furnace(Material type, byte data) {
/* 40 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public Furnace clone() {
/* 45 */     return (Furnace)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Furnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */