/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class IpBanEntry
/*    */   extends ExpirableListEntry<String>
/*    */ {
/*    */   public IpBanEntry(String var0) {
/* 12 */     this(var0, null, null, null, null);
/*    */   }
/*    */   
/*    */   public IpBanEntry(String var0, @Nullable Date var1, @Nullable String var2, @Nullable Date var3, @Nullable String var4) {
/* 16 */     super(var0, var1, var2, var3, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent e() {
/* 21 */     return new ChatComponentText(getKey());
/*    */   }
/*    */   
/*    */   public IpBanEntry(JsonObject var0) {
/* 25 */     super(b(var0), var0);
/*    */   }
/*    */   
/*    */   private static String b(JsonObject var0) {
/* 29 */     return var0.has("ip") ? var0.get("ip").getAsString() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject var0) {
/* 34 */     if (getKey() == null) {
/*    */       return;
/*    */     }
/* 37 */     var0.addProperty("ip", getKey());
/* 38 */     super.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IpBanEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */