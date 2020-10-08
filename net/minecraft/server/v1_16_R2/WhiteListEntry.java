/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class WhiteListEntry
/*    */   extends JsonListEntry<GameProfile> {
/*    */   public WhiteListEntry(GameProfile var0) {
/* 10 */     super(var0);
/*    */   }
/*    */   
/*    */   public WhiteListEntry(JsonObject var0) {
/* 14 */     super(b(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject var0) {
/* 19 */     if (getKey() == null) {
/*    */       return;
/*    */     }
/* 22 */     var0.addProperty("uuid", (getKey().getId() == null) ? "" : getKey().getId().toString());
/* 23 */     var0.addProperty("name", getKey().getName());
/*    */   }
/*    */   private static GameProfile b(JsonObject var0) {
/*    */     UUID var2;
/* 27 */     if (!var0.has("uuid") || !var0.has("name")) {
/* 28 */       return null;
/*    */     }
/* 30 */     String var1 = var0.get("uuid").getAsString();
/*    */     
/*    */     try {
/* 33 */       var2 = UUID.fromString(var1);
/* 34 */     } catch (Throwable var3) {
/* 35 */       return null;
/*    */     } 
/* 37 */     return new GameProfile(var2, var0.get("name").getAsString());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WhiteListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */