/*    */ package org.bukkit.block.data;
/*    */ 
/*    */ import java.util.Set;
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
/*    */ public interface Rail
/*    */   extends BlockData
/*    */ {
/*    */   @NotNull
/*    */   Shape getShape();
/*    */   
/*    */   void setShape(@NotNull Shape paramShape);
/*    */   
/*    */   @NotNull
/*    */   Set<Shape> getShapes();
/*    */   
/*    */   public enum Shape
/*    */   {
/* 45 */     NORTH_SOUTH,
/*    */ 
/*    */ 
/*    */     
/* 49 */     EAST_WEST,
/*    */ 
/*    */ 
/*    */     
/* 53 */     ASCENDING_EAST,
/*    */ 
/*    */ 
/*    */     
/* 57 */     ASCENDING_WEST,
/*    */ 
/*    */ 
/*    */     
/* 61 */     ASCENDING_NORTH,
/*    */ 
/*    */ 
/*    */     
/* 65 */     ASCENDING_SOUTH,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 70 */     SOUTH_EAST,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 75 */     SOUTH_WEST,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 80 */     NORTH_WEST,
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 85 */     NORTH_EAST;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\Rail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */