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
/*    */ public class Torch
/*    */   extends SimpleAttachableMaterialData
/*    */ {
/*    */   public Torch() {
/* 15 */     super(Material.LEGACY_TORCH);
/*    */   }
/*    */   
/*    */   public Torch(Material type) {
/* 19 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Torch(Material type, byte data) {
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
/*    */       case 1:
/* 43 */         return BlockFace.WEST;
/*    */       
/*    */       case 2:
/* 46 */         return BlockFace.EAST;
/*    */       
/*    */       case 3:
/* 49 */         return BlockFace.NORTH;
/*    */       
/*    */       case 4:
/* 52 */         return BlockFace.SOUTH;
/*    */     } 
/*    */ 
/*    */     
/* 56 */     return BlockFace.DOWN;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/*    */     byte data;
/* 64 */     switch (face) {
/*    */       case EAST:
/* 66 */         data = 1;
/*    */         break;
/*    */       
/*    */       case WEST:
/* 70 */         data = 2;
/*    */         break;
/*    */       
/*    */       case SOUTH:
/* 74 */         data = 3;
/*    */         break;
/*    */       
/*    */       case NORTH:
/* 78 */         data = 4;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 83 */         data = 5;
/*    */         break;
/*    */     } 
/* 86 */     setData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public Torch clone() {
/* 91 */     return (Torch)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Torch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */