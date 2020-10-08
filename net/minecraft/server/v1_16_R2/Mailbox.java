/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Mailbox<Msg>
/*    */   extends AutoCloseable
/*    */ {
/*    */   default void close() {}
/*    */   
/*    */   default <Source> CompletableFuture<Source> b(Function<? super Mailbox<Source>, ? extends Msg> var0) {
/* 19 */     CompletableFuture<Source> var1 = new CompletableFuture<>();
/* 20 */     Msg var2 = var0.apply(a("ask future procesor handle", var1::complete));
/* 21 */     a(var2);
/* 22 */     return var1;
/*    */   }
/*    */   
/*    */   default <Source> CompletableFuture<Source> c(Function<? super Mailbox<Either<Source, Exception>>, ? extends Msg> var0) {
/* 26 */     CompletableFuture<Source> var1 = new CompletableFuture<>();
/* 27 */     Msg var2 = var0.apply(a("ask future procesor handle", var1 -> {
/*    */             var1.ifLeft(var0::complete);
/*    */             var1.ifRight(var0::completeExceptionally);
/*    */           }));
/* 31 */     a(var2);
/* 32 */     return var1;
/*    */   }
/*    */   
/*    */   static <Msg> Mailbox<Msg> a(String var0, Consumer<Msg> var1) {
/* 36 */     return new Mailbox<Msg>(var0, var1)
/*    */       {
/*    */         public String bi() {
/* 39 */           return this.a;
/*    */         }
/*    */ 
/*    */         
/*    */         public void a(Msg var0) {
/* 44 */           this.b.accept(var0);
/*    */         }
/*    */ 
/*    */         
/*    */         public String toString() {
/* 49 */           return this.a;
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   String bi();
/*    */   
/*    */   void a(Msg paramMsg);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Mailbox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */