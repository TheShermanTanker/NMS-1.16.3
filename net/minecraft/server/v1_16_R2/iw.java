/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class iw
/*    */ {
/*    */   @Deprecated
/*    */   public static MinecraftKey a(String var0) {
/* 12 */     return new MinecraftKey("minecraft", "block/" + var0);
/*    */   }
/*    */   
/*    */   public static MinecraftKey b(String var0) {
/* 16 */     return new MinecraftKey("minecraft", "item/" + var0);
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(Block var0, String var1) {
/* 20 */     MinecraftKey var2 = IRegistry.BLOCK.getKey(var0);
/* 21 */     return new MinecraftKey(var2.getNamespace(), "block/" + var2.getKey() + var1);
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(Block var0) {
/* 25 */     MinecraftKey var1 = IRegistry.BLOCK.getKey(var0);
/* 26 */     return new MinecraftKey(var1.getNamespace(), "block/" + var1.getKey());
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(Item var0) {
/* 30 */     MinecraftKey var1 = IRegistry.ITEM.getKey(var0);
/* 31 */     return new MinecraftKey(var1.getNamespace(), "item/" + var1.getKey());
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(Item var0, String var1) {
/* 35 */     MinecraftKey var2 = IRegistry.ITEM.getKey(var0);
/* 36 */     return new MinecraftKey(var2.getNamespace(), "item/" + var2.getKey() + var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\iw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */