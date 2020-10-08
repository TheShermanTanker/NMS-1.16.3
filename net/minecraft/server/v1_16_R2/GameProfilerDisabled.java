/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class GameProfilerDisabled implements GameProfilerFillerActive {
/*  6 */   public static final GameProfilerDisabled a = new GameProfilerDisabled();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void b() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void enter(String var0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(Supplier<String> var0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void exit() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void exitEnter(String var0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void c(String var0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void c(Supplier<String> var0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public MethodProfilerResults d() {
/* 49 */     return MethodProfilerResultsEmpty.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfilerDisabled.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */