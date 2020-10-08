/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum AdvancementFrameType
/*    */ {
/*  8 */   TASK("task", 0, EnumChatFormat.GREEN),
/*  9 */   CHALLENGE("challenge", 26, EnumChatFormat.DARK_PURPLE),
/* 10 */   GOAL("goal", 52, EnumChatFormat.GREEN);
/*    */   
/*    */   private final String d;
/*    */   
/*    */   private final int e;
/*    */   private final EnumChatFormat f;
/*    */   private final IChatBaseComponent g;
/*    */   
/*    */   AdvancementFrameType(String var2, int var3, EnumChatFormat var4) {
/* 19 */     this.d = var2;
/* 20 */     this.e = var3;
/* 21 */     this.f = var4;
/* 22 */     this.g = new ChatMessage("advancements.toast." + var2);
/*    */   }
/*    */   
/*    */   public String a() {
/* 26 */     return this.d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static AdvancementFrameType a(String var0) {
/* 34 */     for (AdvancementFrameType var4 : values()) {
/* 35 */       if (var4.d.equals(var0)) {
/* 36 */         return var4;
/*    */       }
/*    */     } 
/* 39 */     throw new IllegalArgumentException("Unknown frame type '" + var0 + "'");
/*    */   }
/*    */   
/*    */   public EnumChatFormat c() {
/* 43 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementFrameType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */