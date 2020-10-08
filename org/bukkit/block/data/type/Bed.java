/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface Bed
/*    */   extends Directional
/*    */ {
/*    */   @NotNull
/*    */   Part getPart();
/*    */   
/*    */   void setPart(@NotNull Part paramPart);
/*    */   
/*    */   boolean isOccupied();
/*    */   
/*    */   public enum Part
/*    */   {
/* 46 */     HEAD,
/*    */ 
/*    */ 
/*    */     
/* 50 */     FOOT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Bed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */