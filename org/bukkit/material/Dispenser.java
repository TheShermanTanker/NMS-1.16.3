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
/*    */ public class Dispenser
/*    */   extends FurnaceAndDispenser
/*    */ {
/*    */   public Dispenser() {
/* 16 */     super(Material.LEGACY_DISPENSER);
/*    */   }
/*    */   
/*    */   public Dispenser(BlockFace direction) {
/* 20 */     this();
/* 21 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public Dispenser(Material type) {
/* 25 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Dispenser(Material type, byte data) {
/* 35 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/*    */     byte data;
/* 42 */     switch (face) {
/*    */       case DOWN:
/* 44 */         data = 0;
/*    */         break;
/*    */       
/*    */       case UP:
/* 48 */         data = 1;
/*    */         break;
/*    */       
/*    */       case NORTH:
/* 52 */         data = 2;
/*    */         break;
/*    */       
/*    */       case SOUTH:
/* 56 */         data = 3;
/*    */         break;
/*    */       
/*    */       case WEST:
/* 60 */         data = 4;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 65 */         data = 5;
/*    */         break;
/*    */     } 
/* 68 */     setData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 73 */     int data = getData() & 0x7;
/*    */     
/* 75 */     switch (data) {
/*    */       case 0:
/* 77 */         return BlockFace.DOWN;
/*    */       
/*    */       case 1:
/* 80 */         return BlockFace.UP;
/*    */       
/*    */       case 2:
/* 83 */         return BlockFace.NORTH;
/*    */       
/*    */       case 3:
/* 86 */         return BlockFace.SOUTH;
/*    */       
/*    */       case 4:
/* 89 */         return BlockFace.WEST;
/*    */     } 
/*    */ 
/*    */     
/* 93 */     return BlockFace.EAST;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dispenser clone() {
/* 99 */     return (Dispenser)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Dispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */