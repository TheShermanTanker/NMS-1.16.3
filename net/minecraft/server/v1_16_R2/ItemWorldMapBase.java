/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemWorldMapBase
/*    */   extends Item
/*    */ {
/*    */   public ItemWorldMapBase(Item.Info var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean ac_() {
/* 16 */     return true;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Packet<?> a(ItemStack var0, World var1, EntityHuman var2) {
/* 21 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemWorldMapBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */