/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.File;
/*    */ 
/*    */ public class OpList
/*    */   extends JsonList<GameProfile, OpListEntry>
/*    */ {
/*    */   public OpList(File var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected JsonListEntry<GameProfile> a(JsonObject var0) {
/* 16 */     return new OpListEntry(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getEntries() {
/* 21 */     String[] var0 = new String[d().size()];
/* 22 */     int var1 = 0;
/* 23 */     for (JsonListEntry<GameProfile> var3 : d()) {
/* 24 */       var0[var1++] = ((GameProfile)var3.getKey()).getName();
/*    */     }
/* 26 */     return var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean b(GameProfile var0) {
/* 40 */     OpListEntry var1 = get(var0);
/*    */     
/* 42 */     if (var1 != null) {
/* 43 */       return var1.b();
/*    */     }
/*    */     
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(GameProfile var0) {
/* 51 */     return var0.getId().toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\OpList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */