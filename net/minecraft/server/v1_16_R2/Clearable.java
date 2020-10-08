/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public interface Clearable {
/*    */   void clear();
/*    */   
/*    */   static void a(@Nullable Object var0) {
/*  9 */     if (var0 instanceof Clearable)
/* 10 */       ((Clearable)var0).clear(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Clearable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */