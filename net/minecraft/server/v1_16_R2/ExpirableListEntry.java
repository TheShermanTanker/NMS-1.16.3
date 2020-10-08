/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public abstract class ExpirableListEntry<T>
/*    */   extends JsonListEntry<T> {
/* 11 */   public static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*    */   protected final Date b;
/*    */   protected final String c;
/*    */   protected final Date d;
/*    */   protected final String e;
/*    */   
/*    */   public ExpirableListEntry(T t0, @Nullable Date date, @Nullable String s, @Nullable Date date1, @Nullable String s1) {
/* 18 */     super(t0);
/* 19 */     this.b = (date == null) ? new Date() : date;
/* 20 */     this.c = (s == null) ? "(Unknown)" : s;
/* 21 */     this.d = date1;
/* 22 */     this.e = (s1 == null) ? "Banned by an operator." : s1;
/*    */   }
/*    */   
/*    */   protected ExpirableListEntry(T t0, JsonObject jsonobject) {
/* 26 */     super(checkExpiry(t0, jsonobject));
/*    */     
/*    */     Date date, date1;
/*    */     
/*    */     try {
/* 31 */       date = jsonobject.has("created") ? a.parse(jsonobject.get("created").getAsString()) : new Date();
/* 32 */     } catch (ParseException parseexception) {
/* 33 */       date = new Date();
/*    */     } 
/*    */     
/* 36 */     this.b = date;
/* 37 */     this.c = jsonobject.has("source") ? jsonobject.get("source").getAsString() : "(Unknown)";
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 42 */       date1 = jsonobject.has("expires") ? a.parse(jsonobject.get("expires").getAsString()) : null;
/* 43 */     } catch (ParseException parseexception1) {
/* 44 */       date1 = null;
/*    */     } 
/*    */     
/* 47 */     this.d = date1;
/* 48 */     this.e = jsonobject.has("reason") ? jsonobject.get("reason").getAsString() : "Banned by an operator.";
/*    */   }
/*    */   
/*    */   public String getSource() {
/* 52 */     return this.c;
/*    */   }
/*    */   
/*    */   public Date getExpires() {
/* 56 */     return this.d;
/*    */   }
/*    */   
/*    */   public String getReason() {
/* 60 */     return this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract IChatBaseComponent e();
/*    */   
/*    */   boolean hasExpired() {
/* 67 */     return (this.d == null) ? false : this.d.before(new Date());
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(JsonObject jsonobject) {
/* 72 */     jsonobject.addProperty("created", a.format(this.b));
/* 73 */     jsonobject.addProperty("source", this.c);
/* 74 */     jsonobject.addProperty("expires", (this.d == null) ? "forever" : a.format(this.d));
/* 75 */     jsonobject.addProperty("reason", this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public Date getCreated() {
/* 80 */     return this.b;
/*    */   }
/*    */   
/*    */   private static <T> T checkExpiry(T object, JsonObject jsonobject) {
/* 84 */     Date expires = null;
/*    */     
/*    */     try {
/* 87 */       expires = jsonobject.has("expires") ? a.parse(jsonobject.get("expires").getAsString()) : null;
/* 88 */     } catch (ParseException parseException) {}
/*    */ 
/*    */ 
/*    */     
/* 92 */     if (expires == null || expires.after(new Date())) {
/* 93 */       return object;
/*    */     }
/* 95 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ExpirableListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */