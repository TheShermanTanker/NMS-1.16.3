/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ public interface CriterionTrigger<T extends CriterionInstance>
/*    */ {
/*    */   MinecraftKey a();
/*    */   
/*    */   void a(AdvancementDataPlayer paramAdvancementDataPlayer, a<T> parama);
/*    */   
/*    */   void b(AdvancementDataPlayer paramAdvancementDataPlayer, a<T> parama);
/*    */   
/*    */   void a(AdvancementDataPlayer paramAdvancementDataPlayer);
/*    */   
/*    */   T a(JsonObject paramJsonObject, LootDeserializationContext paramLootDeserializationContext);
/*    */   
/*    */   public static class a<T extends CriterionInstance>
/*    */   {
/*    */     private final T a;
/*    */     private final Advancement b;
/*    */     private final String c;
/*    */     
/*    */     public a(T var0, Advancement var1, String var2) {
/* 25 */       this.a = var0;
/* 26 */       this.b = var1;
/* 27 */       this.c = var2;
/*    */     }
/*    */     
/*    */     public T a() {
/* 31 */       return this.a;
/*    */     }
/*    */     
/*    */     public void a(AdvancementDataPlayer var0) {
/* 35 */       var0.grantCriteria(this.b, this.c);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean equals(Object var0) {
/* 40 */       if (this == var0) {
/* 41 */         return true;
/*    */       }
/* 43 */       if (var0 == null || getClass() != var0.getClass()) {
/* 44 */         return false;
/*    */       }
/*    */       
/* 47 */       a<?> var1 = (a)var0;
/*    */       
/* 49 */       if (!this.a.equals(var1.a)) {
/* 50 */         return false;
/*    */       }
/* 52 */       if (!this.b.equals(var1.b)) {
/* 53 */         return false;
/*    */       }
/* 55 */       return this.c.equals(var1.c);
/*    */     }
/*    */ 
/*    */     
/*    */     public int hashCode() {
/* 60 */       int var0 = this.a.hashCode();
/* 61 */       var0 = 31 * var0 + this.b.hashCode();
/* 62 */       var0 = 31 * var0 + this.c.hashCode();
/* 63 */       return var0;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */