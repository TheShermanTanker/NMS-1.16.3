/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IChatFormatted
/*    */ {
/* 10 */   public static final Optional<Unit> b = Optional.of(Unit.INSTANCE);
/*    */   
/* 12 */   public static final IChatFormatted c = new IChatFormatted()
/*    */     {
/*    */       public <T> Optional<T> a(IChatFormatted.a<T> var0) {
/* 15 */         return Optional.empty();
/*    */       }
/*    */     };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   <T> Optional<T> a(a<T> parama);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static IChatFormatted b(String var0) {
/* 29 */     return new IChatFormatted(var0)
/*    */       {
/*    */         public <T> Optional<T> a(IChatFormatted.a<T> var0) {
/* 32 */           return var0.accept(this.a);
/*    */         }
/*    */       };
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   default String getString() {
/* 89 */     StringBuilder var0 = new StringBuilder();
/*    */     
/* 91 */     a(var1 -> {
/*    */           var0.append(var1);
/*    */           
/*    */           return Optional.empty();
/*    */         });
/* 96 */     return var0.toString();
/*    */   }
/*    */   
/*    */   public static interface a<T> {
/*    */     Optional<T> accept(String param1String);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChatFormatted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */