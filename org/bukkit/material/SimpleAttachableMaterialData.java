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
/*    */ public abstract class SimpleAttachableMaterialData
/*    */   extends MaterialData
/*    */   implements Attachable
/*    */ {
/*    */   public SimpleAttachableMaterialData(Material type, BlockFace direction) {
/* 16 */     this(type);
/* 17 */     setFacingDirection(direction);
/*    */   }
/*    */   
/*    */   public SimpleAttachableMaterialData(Material type) {
/* 21 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public SimpleAttachableMaterialData(Material type, byte data) {
/* 31 */     super(type, data);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 36 */     BlockFace attachedFace = getAttachedFace();
/* 37 */     return (attachedFace == null) ? null : attachedFace.getOppositeFace();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return super.toString() + " facing " + getFacing();
/*    */   }
/*    */ 
/*    */   
/*    */   public SimpleAttachableMaterialData clone() {
/* 47 */     return (SimpleAttachableMaterialData)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\SimpleAttachableMaterialData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */