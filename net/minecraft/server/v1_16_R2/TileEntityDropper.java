/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class TileEntityDropper
/*    */   extends TileEntityDispenser
/*    */ {
/*    */   public TileEntityDropper() {
/*  8 */     super(TileEntityTypes.DROPPER);
/*    */   }
/*    */ 
/*    */   
/*    */   protected IChatBaseComponent getContainerName() {
/* 13 */     return new ChatMessage("container.dropper");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityDropper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */