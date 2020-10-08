/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.Date;
/*    */ import java.util.Objects;
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class GameProfileBanEntry
/*    */   extends ExpirableListEntry<GameProfile> {
/*    */   public GameProfileBanEntry(GameProfile gameprofile) {
/* 13 */     this(gameprofile, (Date)null, (String)null, (Date)null, (String)null);
/*    */   }
/*    */   
/*    */   public GameProfileBanEntry(GameProfile gameprofile, @Nullable Date date, @Nullable String s, @Nullable Date date1, @Nullable String s1) {
/* 17 */     super(gameprofile, date, s, date1, s1);
/*    */   }
/*    */   
/*    */   public GameProfileBanEntry(JsonObject jsonobject) {
/* 21 */     super(b(jsonobject), jsonobject);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject jsonobject) {
/* 26 */     if (getKey() != null) {
/* 27 */       jsonobject.addProperty("uuid", (getKey().getId() == null) ? "" : getKey().getId().toString());
/* 28 */       jsonobject.addProperty("name", getKey().getName());
/* 29 */       super.a(jsonobject);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent e() {
/* 35 */     GameProfile gameprofile = getKey();
/*    */     
/* 37 */     return new ChatComponentText((gameprofile.getName() != null) ? gameprofile.getName() : Objects.toString(gameprofile.getId(), "(Unknown)"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static GameProfile b(JsonObject jsonobject) {
/* 43 */     UUID uuid = null;
/* 44 */     String name = null;
/* 45 */     if (jsonobject.has("uuid")) {
/* 46 */       String s = jsonobject.get("uuid").getAsString();
/*    */       
/*    */       try {
/* 49 */         uuid = UUID.fromString(s);
/* 50 */       } catch (Throwable throwable) {}
/*    */     } 
/*    */ 
/*    */     
/* 54 */     if (jsonobject.has("name"))
/*    */     {
/* 56 */       name = jsonobject.get("name").getAsString();
/*    */     }
/* 58 */     if (uuid != null || name != null)
/*    */     {
/* 60 */       return new GameProfile(uuid, name);
/*    */     }
/* 62 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameProfileBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */