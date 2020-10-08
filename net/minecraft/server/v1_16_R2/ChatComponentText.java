/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ChatComponentText extends ChatBaseComponent {
/*  4 */   public static final IChatBaseComponent d = new ChatComponentText("");
/*    */   
/*    */   private final String e;
/*    */   
/*    */   public ChatComponentText(String var0) {
/*  9 */     this.e = var0;
/*    */   }
/*    */   
/*    */   public String h() {
/* 13 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 18 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public ChatComponentText g() {
/* 23 */     return new ChatComponentText(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 28 */     if (this == var0) {
/* 29 */       return true;
/*    */     }
/*    */     
/* 32 */     if (var0 instanceof ChatComponentText) {
/* 33 */       ChatComponentText var1 = (ChatComponentText)var0;
/* 34 */       return (this.e.equals(var1.h()) && super.equals(var0));
/*    */     } 
/*    */     
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "TextComponent{text='" + this.e + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*    */ 
/*    */       
/* 45 */       getChatModifier() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */