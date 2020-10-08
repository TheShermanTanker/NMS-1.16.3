/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.UnaryOperator;
/*    */ 
/*    */ 
/*    */ public interface IChatMutableComponent
/*    */   extends IChatBaseComponent
/*    */ {
/*    */   IChatMutableComponent setChatModifier(ChatModifier paramChatModifier);
/*    */   
/*    */   default IChatMutableComponent c(String var0) {
/* 12 */     return addSibling(new ChatComponentText(var0));
/*    */   }
/*    */   
/*    */   IChatMutableComponent addSibling(IChatBaseComponent paramIChatBaseComponent);
/*    */   
/*    */   default IChatMutableComponent format(UnaryOperator<ChatModifier> var0) {
/* 18 */     setChatModifier(var0.apply(getChatModifier()));
/* 19 */     return this;
/*    */   }
/*    */   
/*    */   default IChatMutableComponent c(ChatModifier var0) {
/* 23 */     setChatModifier(var0.setChatModifier(getChatModifier()));
/* 24 */     return this;
/*    */   }
/*    */   
/*    */   IChatMutableComponent a(EnumChatFormat... var0) {
/* 28 */     setChatModifier(getChatModifier().a(var0));
/* 29 */     return this;
/*    */   }
/*    */   
/*    */   default IChatMutableComponent a(EnumChatFormat var0) {
/* 33 */     setChatModifier(getChatModifier().b(var0));
/* 34 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChatMutableComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */