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
/*    */ 
/*    */ @Deprecated
/*    */ public class ExtendedRails
/*    */   extends Rails
/*    */ {
/*    */   public ExtendedRails(Material type) {
/* 17 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public ExtendedRails(Material type, byte data) {
/* 27 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCurve() {
/* 32 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   protected byte getConvertedData() {
/* 43 */     return (byte)(getData() & 0x7);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDirection(BlockFace face, boolean isOnSlope) {
/* 48 */     boolean extraBitSet = ((getData() & 0x8) == 8);
/*    */     
/* 50 */     if (face != BlockFace.WEST && face != BlockFace.EAST && face != BlockFace.NORTH && face != BlockFace.SOUTH) {
/* 51 */       throw new IllegalArgumentException("Detector rails and powered rails cannot be set on a curve!");
/*    */     }
/*    */     
/* 54 */     super.setDirection(face, isOnSlope);
/* 55 */     setData((byte)(extraBitSet ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*    */   }
/*    */ 
/*    */   
/*    */   public ExtendedRails clone() {
/* 60 */     return (ExtendedRails)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\ExtendedRails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */