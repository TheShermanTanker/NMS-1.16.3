/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Consumer;
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
/*    */ public class GameTestHarnessTestFunction
/*    */ {
/*    */   private final String a;
/*    */   private final String b;
/*    */   private final String c;
/*    */   private final boolean d;
/*    */   private final Consumer<GameTestHarnessHelper> e;
/*    */   private final int f;
/*    */   private final long g;
/*    */   private final EnumBlockRotation h;
/*    */   
/*    */   public void a(GameTestHarnessHelper var0) {
/* 40 */     this.e.accept(var0);
/*    */   }
/*    */   
/*    */   public String a() {
/* 44 */     return this.b;
/*    */   }
/*    */   
/*    */   public String b() {
/* 48 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return this.b;
/*    */   }
/*    */   
/*    */   public int c() {
/* 57 */     return this.f;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 61 */     return this.d;
/*    */   }
/*    */   
/*    */   public String e() {
/* 65 */     return this.a;
/*    */   }
/*    */   
/*    */   public long f() {
/* 69 */     return this.g;
/*    */   }
/*    */   
/*    */   public EnumBlockRotation g() {
/* 73 */     return this.h;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessTestFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */