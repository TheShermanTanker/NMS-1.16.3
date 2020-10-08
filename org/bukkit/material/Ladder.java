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
/*    */ public class Ladder
/*    */   extends SimpleAttachableMaterialData
/*    */ {
/*    */   public Ladder() {
/* 15 */     super(Material.LEGACY_LADDER);
/*    */   }
/*    */   
/*    */   public Ladder(Material type) {
/* 19 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Ladder(Material type, byte data) {
/* 29 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockFace getAttachedFace() {
/* 39 */     byte data = getData();
/*    */     
/* 41 */     switch (data) {
/*    */       case 2:
/* 43 */         return BlockFace.SOUTH;
/*    */       
/*    */       case 3:
/* 46 */         return BlockFace.NORTH;
/*    */       
/*    */       case 4:
/* 49 */         return BlockFace.EAST;
/*    */       
/*    */       case 5:
/* 52 */         return BlockFace.WEST;
/*    */     } 
/*    */     
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/* 63 */     byte data = 0;
/*    */     
/* 65 */     switch (face) {
/*    */       case SOUTH:
/* 67 */         data = 2;
/*    */         break;
/*    */       
/*    */       case NORTH:
/* 71 */         data = 3;
/*    */         break;
/*    */       
/*    */       case EAST:
/* 75 */         data = 4;
/*    */         break;
/*    */       
/*    */       case WEST:
/* 79 */         data = 5;
/*    */         break;
/*    */     } 
/*    */     
/* 83 */     setData(data);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Ladder clone() {
/* 89 */     return (Ladder)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Ladder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */