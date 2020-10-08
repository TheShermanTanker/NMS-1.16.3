/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class InteractionResultWrapper<T>
/*    */ {
/*    */   private final EnumInteractionResult a;
/*    */   private final T b;
/*    */   
/*    */   public InteractionResultWrapper(EnumInteractionResult enuminteractionresult, T t0) {
/*  9 */     this.a = enuminteractionresult;
/* 10 */     this.b = t0;
/*    */   }
/*    */   public EnumInteractionResult getResult() {
/* 13 */     return a();
/*    */   } public EnumInteractionResult a() {
/* 15 */     return this.a;
/*    */   }
/*    */   
/*    */   public T b() {
/* 19 */     return this.b;
/*    */   }
/*    */   
/*    */   public static <T> InteractionResultWrapper<T> success(T t0) {
/* 23 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, t0);
/*    */   }
/*    */   
/*    */   public static <T> InteractionResultWrapper<T> consume(T t0) {
/* 27 */     return new InteractionResultWrapper<>(EnumInteractionResult.CONSUME, t0);
/*    */   }
/*    */   
/*    */   public static <T> InteractionResultWrapper<T> pass(T t0) {
/* 31 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, t0);
/*    */   }
/*    */   
/*    */   public static <T> InteractionResultWrapper<T> fail(T t0) {
/* 35 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, t0);
/*    */   }
/*    */   
/*    */   public static <T> InteractionResultWrapper<T> a(T t0, boolean flag) {
/* 39 */     return flag ? success(t0) : consume(t0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InteractionResultWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */