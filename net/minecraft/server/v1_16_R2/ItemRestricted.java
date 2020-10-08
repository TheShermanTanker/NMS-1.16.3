/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemRestricted
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemRestricted(Block var0, Item.Info var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected IBlockData c(BlockActionContext var0) {
/* 18 */     EntityHuman var1 = var0.getEntity();
/* 19 */     return (var1 == null || var1.isCreativeAndOp()) ? super.c(var0) : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemRestricted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */