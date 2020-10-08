/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ @FunctionalInterface
/*    */ public interface CustomFunctionCallbackTimer<T>
/*    */ {
/*    */   void a(T paramT, CustomFunctionCallbackTimerQueue<T> paramCustomFunctionCallbackTimerQueue, long paramLong);
/*    */   
/*    */   public static abstract class a<T, C extends CustomFunctionCallbackTimer<T>>
/*    */   {
/*    */     private final MinecraftKey a;
/*    */     private final Class<?> b;
/*    */     
/*    */     public a(MinecraftKey var0, Class<?> var1) {
/* 15 */       this.a = var0;
/* 16 */       this.b = var1;
/*    */     }
/*    */     
/*    */     public MinecraftKey a() {
/* 20 */       return this.a;
/*    */     }
/*    */     
/*    */     public Class<?> b() {
/* 24 */       return this.b;
/*    */     }
/*    */     
/*    */     public abstract void a(NBTTagCompound param1NBTTagCompound, C param1C);
/*    */     
/*    */     public abstract C b(NBTTagCompound param1NBTTagCompound);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionCallbackTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */