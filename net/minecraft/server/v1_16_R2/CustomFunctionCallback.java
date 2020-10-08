/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CustomFunctionCallback
/*    */   implements CustomFunctionCallbackTimer<MinecraftServer>
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public CustomFunctionCallback(MinecraftKey var0) {
/* 12 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(MinecraftServer var0, CustomFunctionCallbackTimerQueue<MinecraftServer> var1, long var2) {
/* 17 */     CustomFunctionData var4 = var0.getFunctionData();
/* 18 */     var4.a(this.a).ifPresent(var1 -> var0.a(var1, var0.e()));
/*    */   }
/*    */   
/*    */   public static class a extends CustomFunctionCallbackTimer.a<MinecraftServer, CustomFunctionCallback> {
/*    */     public a() {
/* 23 */       super(new MinecraftKey("function"), CustomFunctionCallback.class);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(NBTTagCompound var0, CustomFunctionCallback var1) {
/* 28 */       var0.setString("Name", CustomFunctionCallback.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     public CustomFunctionCallback b(NBTTagCompound var0) {
/* 33 */       MinecraftKey var1 = new MinecraftKey(var0.getString("Name"));
/* 34 */       return new CustomFunctionCallback(var1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */