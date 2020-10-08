/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemAir
/*    */   extends Item
/*    */ {
/*    */   private final Block a;
/*    */   
/*    */   public ItemAir(Block var0, Item.Info var1) {
/* 14 */     super(var1);
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 20 */     return this.a.i();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemAir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */