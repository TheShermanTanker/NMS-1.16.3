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
/*    */ public class Chest
/*    */   extends DirectionalContainer
/*    */ {
/*    */   public Chest() {
/* 16 */     super(Material.LEGACY_CHEST);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Chest(BlockFace direction) {
/* 25 */     this();
/* 26 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public Chest(Material type) {
/* 30 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Chest(Material type, byte data) {
/* 40 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public Chest clone() {
/* 45 */     return (Chest)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Chest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */