/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumResourcePackVersion
/*    */ {
/*  8 */   TOO_OLD("old"),
/*  9 */   TOO_NEW("new"),
/* 10 */   COMPATIBLE("compatible");
/*    */   
/*    */   private final IChatBaseComponent d;
/*    */   
/*    */   private final IChatBaseComponent e;
/*    */   
/*    */   EnumResourcePackVersion(String var2) {
/* 17 */     this.d = new ChatMessage("pack.incompatible." + var2);
/* 18 */     this.e = new ChatMessage("pack.incompatible.confirm." + var2);
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 22 */     return (this == COMPATIBLE);
/*    */   }
/*    */   
/*    */   public static EnumResourcePackVersion a(int var0) {
/* 26 */     if (var0 < SharedConstants.getGameVersion().getPackVersion())
/* 27 */       return TOO_OLD; 
/* 28 */     if (var0 > SharedConstants.getGameVersion().getPackVersion()) {
/* 29 */       return TOO_NEW;
/*    */     }
/* 31 */     return COMPATIBLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumResourcePackVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */