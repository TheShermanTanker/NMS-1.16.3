/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.io.File;
/*    */ import java.net.SocketAddress;
/*    */ 
/*    */ public class IpBanList
/*    */   extends JsonList<String, IpBanEntry> {
/*    */   public IpBanList(File var0) {
/* 10 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<String> a(JsonObject var0) {
/* 15 */     return new IpBanEntry(var0);
/*    */   }
/*    */   
/*    */   public boolean isBanned(SocketAddress var0) {
/* 19 */     String var1 = c(var0);
/* 20 */     return d(var1);
/*    */   }
/*    */   
/*    */   public boolean a(String var0) {
/* 24 */     return d(var0);
/*    */   }
/*    */   
/*    */   public IpBanEntry get(SocketAddress var0) {
/* 28 */     String var1 = c(var0);
/* 29 */     return get(var1);
/*    */   }
/*    */   
/*    */   private String c(SocketAddress var0) {
/* 33 */     String var1 = var0.toString();
/* 34 */     if (var1.contains("/")) {
/* 35 */       var1 = var1.substring(var1.indexOf('/') + 1);
/*    */     }
/* 37 */     if (var1.contains(":")) {
/* 38 */       var1 = var1.substring(0, var1.indexOf(':'));
/*    */     }
/* 40 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IpBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */