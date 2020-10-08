/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface PackSource
/*    */ {
/*  8 */   public static final PackSource a = a();
/*  9 */   public static final PackSource b = a("pack.source.builtin");
/*    */ 
/*    */   
/* 12 */   public static final PackSource c = a("pack.source.world");
/* 13 */   public static final PackSource d = a("pack.source.server");
/*    */ 
/*    */ 
/*    */   
/*    */   static PackSource a() {
/* 18 */     return var0 -> var0;
/*    */   }
/*    */   
/*    */   static PackSource a(String var0) {
/* 22 */     IChatBaseComponent var1 = new ChatMessage(var0);
/* 23 */     return var1 -> new ChatMessage("pack.nameAndSource", new Object[] { var1, var0 });
/*    */   }
/*    */   
/*    */   IChatBaseComponent decorate(IChatBaseComponent paramIChatBaseComponent);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PackSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */