/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class DirectionalContainer
/*    */   extends MaterialData
/*    */   implements Directional
/*    */ {
/*    */   public DirectionalContainer(Material type) {
/* 16 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public DirectionalContainer(Material type, byte data) {
/* 26 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/*    */     byte data;
/* 33 */     switch (face) {
/*    */       case NORTH:
/* 35 */         data = 2;
/*    */         break;
/*    */       
/*    */       case SOUTH:
/* 39 */         data = 3;
/*    */         break;
/*    */       
/*    */       case WEST:
/* 43 */         data = 4;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 48 */         data = 5;
/*    */         break;
/*    */     } 
/* 51 */     setData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 56 */     byte data = getData();
/*    */     
/* 58 */     switch (data) {
/*    */       case 2:
/* 60 */         return BlockFace.NORTH;
/*    */       
/*    */       case 3:
/* 63 */         return BlockFace.SOUTH;
/*    */       
/*    */       case 4:
/* 66 */         return BlockFace.WEST;
/*    */     } 
/*    */ 
/*    */     
/* 70 */     return BlockFace.EAST;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 76 */     return super.toString() + " facing " + getFacing();
/*    */   }
/*    */ 
/*    */   
/*    */   public DirectionalContainer clone() {
/* 81 */     return (DirectionalContainer)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\DirectionalContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */