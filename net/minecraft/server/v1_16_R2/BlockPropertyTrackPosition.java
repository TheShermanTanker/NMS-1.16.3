/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyTrackPosition
/*    */   implements INamable
/*    */ {
/*  6 */   NORTH_SOUTH("north_south"),
/*  7 */   EAST_WEST("east_west"),
/*  8 */   ASCENDING_EAST("ascending_east"),
/*  9 */   ASCENDING_WEST("ascending_west"),
/* 10 */   ASCENDING_NORTH("ascending_north"),
/* 11 */   ASCENDING_SOUTH("ascending_south"),
/* 12 */   SOUTH_EAST("south_east"),
/* 13 */   SOUTH_WEST("south_west"),
/* 14 */   NORTH_WEST("north_west"),
/* 15 */   NORTH_EAST("north_east");
/*    */   
/*    */   private final String k;
/*    */ 
/*    */   
/*    */   BlockPropertyTrackPosition(String var2) {
/* 21 */     this.k = var2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return this.k;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return (this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 39 */     return this.k;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyTrackPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */