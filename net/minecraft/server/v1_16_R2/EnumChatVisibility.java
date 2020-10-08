/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ 
/*    */ public enum EnumChatVisibility
/*    */ {
/*  9 */   FULL(0, "options.chat.visibility.full"),
/* 10 */   SYSTEM(1, "options.chat.visibility.system"),
/* 11 */   HIDDEN(2, "options.chat.visibility.hidden"); private static final EnumChatVisibility[] d;
/*    */   static {
/* 13 */     d = (EnumChatVisibility[])Arrays.<EnumChatVisibility>stream(values()).sorted(Comparator.comparingInt(EnumChatVisibility::a)).toArray(var0 -> new EnumChatVisibility[var0]);
/*    */   }
/*    */   private final int e; private final String f;
/*    */   
/*    */   EnumChatVisibility(int var2, String var3) {
/* 18 */     this.e = var2;
/* 19 */     this.f = var3;
/*    */   }
/*    */   
/*    */   public int a() {
/* 23 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumChatVisibility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */