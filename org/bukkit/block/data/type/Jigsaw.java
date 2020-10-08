/*    */ package org.bukkit.block.data.type;
/*    */ 
/*    */ import org.bukkit.block.data.BlockData;
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
/*    */ public interface Jigsaw
/*    */   extends BlockData
/*    */ {
/*    */   @NotNull
/*    */   Orientation getOrientation();
/*    */   
/*    */   void setOrientation(@NotNull Orientation paramOrientation);
/*    */   
/*    */   public enum Orientation
/*    */   {
/* 31 */     DOWN_EAST,
/* 32 */     DOWN_NORTH,
/* 33 */     DOWN_SOUTH,
/* 34 */     DOWN_WEST,
/* 35 */     UP_EAST,
/* 36 */     UP_NORTH,
/* 37 */     UP_SOUTH,
/* 38 */     UP_WEST,
/* 39 */     WEST_UP,
/* 40 */     EAST_UP,
/* 41 */     NORTH_UP,
/* 42 */     SOUTH_UP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\Jigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */