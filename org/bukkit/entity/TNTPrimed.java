/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface TNTPrimed
/*    */   extends Explosive
/*    */ {
/*    */   void setFuseTicks(int paramInt);
/*    */   
/*    */   int getFuseTicks();
/*    */   
/*    */   @Nullable
/*    */   Entity getSource();
/*    */   
/*    */   @Deprecated
/*    */   default Location getSourceLoc() {
/* 51 */     return getOrigin();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\TNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */