/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class OpListEntry
/*    */   extends JsonListEntry<GameProfile>
/*    */ {
/*    */   private final int a;
/*    */   private final boolean b;
/*    */   
/*    */   public OpListEntry(GameProfile var0, int var1, boolean var2) {
/* 14 */     super(var0);
/* 15 */     this.a = var1;
/* 16 */     this.b = var2;
/*    */   }
/*    */   
/*    */   public OpListEntry(JsonObject var0) {
/* 20 */     super(b(var0));
/* 21 */     this.a = var0.has("level") ? var0.get("level").getAsInt() : 0;
/* 22 */     this.b = (var0.has("bypassesPlayerLimit") && var0.get("bypassesPlayerLimit").getAsBoolean());
/*    */   }
/*    */   
/*    */   public int a() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 30 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject var0) {
/* 35 */     if (getKey() == null) {
/*    */       return;
/*    */     }
/* 38 */     var0.addProperty("uuid", (getKey().getId() == null) ? "" : getKey().getId().toString());
/* 39 */     var0.addProperty("name", getKey().getName());
/* 40 */     var0.addProperty("level", Integer.valueOf(this.a));
/* 41 */     var0.addProperty("bypassesPlayerLimit", Boolean.valueOf(this.b));
/*    */   }
/*    */   private static GameProfile b(JsonObject var0) {
/*    */     UUID var2;
/* 45 */     if (!var0.has("uuid") || !var0.has("name")) {
/* 46 */       return null;
/*    */     }
/* 48 */     String var1 = var0.get("uuid").getAsString();
/*    */     
/*    */     try {
/* 51 */       var2 = UUID.fromString(var1);
/* 52 */     } catch (Throwable var3) {
/* 53 */       return null;
/*    */     } 
/* 55 */     return new GameProfile(var2, var0.get("name").getAsString());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\OpListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */