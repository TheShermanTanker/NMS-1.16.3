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
/*    */ public class Pumpkin
/*    */   extends MaterialData
/*    */   implements Directional
/*    */ {
/*    */   public Pumpkin() {
/* 16 */     super(Material.LEGACY_PUMPKIN);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Pumpkin(BlockFace direction) {
/* 25 */     this();
/* 26 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public Pumpkin(Material type) {
/* 30 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Pumpkin(Material type, byte data) {
/* 40 */     super(type, data);
/*    */   }
/*    */   
/*    */   public boolean isLit() {
/* 44 */     return (getItemType() == Material.LEGACY_JACK_O_LANTERN);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/*    */     byte data;
/* 51 */     switch (face) {
/*    */       case NORTH:
/* 53 */         data = 0;
/*    */         break;
/*    */       
/*    */       case EAST:
/* 57 */         data = 1;
/*    */         break;
/*    */       
/*    */       case SOUTH:
/* 61 */         data = 2;
/*    */         break;
/*    */ 
/*    */       
/*    */       default:
/* 66 */         data = 3;
/*    */         break;
/*    */     } 
/* 69 */     setData(data);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 74 */     byte data = getData();
/*    */     
/* 76 */     switch (data) {
/*    */       case 0:
/* 78 */         return BlockFace.NORTH;
/*    */       
/*    */       case 1:
/* 81 */         return BlockFace.EAST;
/*    */       
/*    */       case 2:
/* 84 */         return BlockFace.SOUTH;
/*    */     } 
/*    */ 
/*    */     
/* 88 */     return BlockFace.EAST;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 94 */     return super.toString() + " facing " + getFacing() + " " + (isLit() ? "" : "NOT ") + "LIT";
/*    */   }
/*    */ 
/*    */   
/*    */   public Pumpkin clone() {
/* 99 */     return (Pumpkin)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Pumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */