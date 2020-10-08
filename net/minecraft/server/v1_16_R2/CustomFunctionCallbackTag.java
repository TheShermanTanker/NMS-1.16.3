/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomFunctionCallbackTag
/*    */   implements CustomFunctionCallbackTimer<MinecraftServer>
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public CustomFunctionCallbackTag(MinecraftKey var0) {
/* 14 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(MinecraftServer var0, CustomFunctionCallbackTimerQueue<MinecraftServer> var1, long var2) {
/* 19 */     CustomFunctionData var4 = var0.getFunctionData();
/* 20 */     Tag<CustomFunction> var5 = var4.b(this.a);
/* 21 */     for (CustomFunction var7 : var5.getTagged())
/* 22 */       var4.a(var7, var4.e()); 
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends CustomFunctionCallbackTimer.a<MinecraftServer, CustomFunctionCallbackTag> {
/*    */     public a() {
/* 28 */       super(new MinecraftKey("function_tag"), CustomFunctionCallbackTag.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(NBTTagCompound var0, CustomFunctionCallbackTag var1) {
/* 33 */       var0.setString("Name", CustomFunctionCallbackTag.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     public CustomFunctionCallbackTag b(NBTTagCompound var0) {
/* 38 */       MinecraftKey var1 = new MinecraftKey(var0.getString("Name"));
/* 39 */       return new CustomFunctionCallbackTag(var1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionCallbackTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */