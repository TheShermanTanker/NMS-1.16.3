/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public interface INamableTileEntity
/*    */ {
/*    */   IChatBaseComponent getDisplayName();
/*    */   
/*    */   default boolean hasCustomName() {
/* 11 */     return (getCustomName() != null);
/*    */   }
/*    */   
/*    */   default IChatBaseComponent getScoreboardDisplayName() {
/* 15 */     return getDisplayName();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   default IChatBaseComponent getCustomName() {
/* 20 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\INamableTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */