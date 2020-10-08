/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ 
/*    */ public class WhiteList
/*    */   extends JsonList<GameProfile, WhiteListEntry> {
/*    */   public WhiteList(File var0) {
/* 10 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<GameProfile> a(JsonObject var0) {
/* 15 */     return new WhiteListEntry(var0);
/*    */   }
/*    */   
/*    */   public boolean isWhitelisted(GameProfile var0) {
/* 19 */     return d(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getEntries() {
/* 24 */     String[] var0 = new String[d().size()];
/* 25 */     int var1 = 0;
/* 26 */     for (JsonListEntry<GameProfile> var3 : d()) {
/* 27 */       var0[var1++] = ((GameProfile)var3.getKey()).getName();
/*    */     }
/* 29 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(GameProfile var0) {
/* 34 */     return var0.getId().toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WhiteList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */