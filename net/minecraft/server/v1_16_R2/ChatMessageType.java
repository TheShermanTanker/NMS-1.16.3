/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum ChatMessageType {
/*  4 */   CHAT((byte)0, false),
/*  5 */   SYSTEM((byte)1, true),
/*  6 */   GAME_INFO((byte)2, true);
/*    */   
/*    */   private final byte d;
/*    */   
/*    */   private final boolean e;
/*    */   
/*    */   ChatMessageType(byte var2, boolean var3) {
/* 13 */     this.d = var2;
/* 14 */     this.e = var3;
/*    */   }
/*    */   
/*    */   public byte a() {
/* 18 */     return this.d;
/*    */   }
/*    */   
/*    */   public static ChatMessageType a(byte var0) {
/* 22 */     for (ChatMessageType var4 : values()) {
/* 23 */       if (var0 == var4.d) {
/* 24 */         return var4;
/*    */       }
/*    */     } 
/* 27 */     return CHAT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatMessageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */