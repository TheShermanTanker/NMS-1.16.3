/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ public class ItemBookAndQuill
/*    */   extends Item
/*    */ {
/*    */   public ItemBookAndQuill(Item.Info var0) {
/* 22 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext var0) {
/* 27 */     World var1 = var0.getWorld();
/* 28 */     BlockPosition var2 = var0.getClickPosition();
/* 29 */     IBlockData var3 = var1.getType(var2);
/*    */     
/* 31 */     if (var3.a(Blocks.LECTERN)) {
/* 32 */       return BlockLectern.a(var1, var2, var3, var0.getItemStack()) ? EnumInteractionResult.a(var1.isClientSide) : EnumInteractionResult.PASS;
/*    */     }
/*    */     
/* 35 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 40 */     ItemStack var3 = var1.b(var2);
/* 41 */     var1.openBook(var3, var2);
/* 42 */     var1.b(StatisticList.ITEM_USED.b(this));
/* 43 */     return InteractionResultWrapper.a(var3, var0.s_());
/*    */   }
/*    */   
/*    */   public static boolean a(@Nullable NBTTagCompound var0) {
/* 47 */     if (var0 == null) {
/* 48 */       return false;
/*    */     }
/* 50 */     if (!var0.hasKeyOfType("pages", 9)) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     NBTTagList var1 = var0.getList("pages", 8);
/* 55 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/* 56 */       String var3 = var1.getString(var2);
/*    */       
/* 58 */       if (var3.length() > 32767) {
/* 59 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 63 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBookAndQuill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */