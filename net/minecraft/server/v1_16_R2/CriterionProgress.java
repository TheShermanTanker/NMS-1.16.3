/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.text.ParseException;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ 
/*    */ 
/*    */ public class CriterionProgress
/*    */ {
/* 14 */   private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/*    */   
/*    */   private Date b;
/*    */   
/*    */   public boolean a() {
/* 19 */     return (this.b != null);
/*    */   }
/*    */   
/*    */   public void b() {
/* 23 */     this.b = new Date();
/*    */   }
/*    */   
/*    */   public void c() {
/* 27 */     this.b = null;
/*    */   }
/*    */   
/*    */   public Date getDate() {
/* 31 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return "CriterionProgress{obtained=" + ((this.b == null) ? "false" : (String)this.b) + '}';
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {
/* 42 */     var0.writeBoolean((this.b != null));
/* 43 */     if (this.b != null) {
/* 44 */       var0.a(this.b);
/*    */     }
/*    */   }
/*    */   
/*    */   public JsonElement e() {
/* 49 */     if (this.b != null) {
/* 50 */       return (JsonElement)new JsonPrimitive(a.format(this.b));
/*    */     }
/* 52 */     return (JsonElement)JsonNull.INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   public static CriterionProgress b(PacketDataSerializer var0) {
/* 57 */     CriterionProgress var1 = new CriterionProgress();
/* 58 */     if (var0.readBoolean()) {
/* 59 */       var1.b = var0.q();
/*    */     }
/* 61 */     return var1;
/*    */   }
/*    */   
/*    */   public static CriterionProgress a(String var0) {
/* 65 */     CriterionProgress var1 = new CriterionProgress();
/*    */     try {
/* 67 */       var1.b = a.parse(var0);
/* 68 */     } catch (ParseException var2) {
/* 69 */       throw new JsonSyntaxException("Invalid datetime: " + var0, var2);
/*    */     } 
/* 71 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */