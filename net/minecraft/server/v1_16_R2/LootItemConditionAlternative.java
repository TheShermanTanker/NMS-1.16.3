/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemConditionAlternative
/*    */   implements LootItemCondition
/*    */ {
/*    */   private final LootItemCondition[] a;
/*    */   private final Predicate<LootTableInfo> b;
/*    */   
/*    */   private LootItemConditionAlternative(LootItemCondition[] var0) {
/* 20 */     this.a = var0;
/* 21 */     this.b = LootItemConditions.b((Predicate<LootTableInfo>[])var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemConditionType b() {
/* 26 */     return LootItemConditions.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean test(LootTableInfo var0) {
/* 31 */     return this.b.test(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 36 */     super.a(var0);
/*    */     
/* 38 */     for (int var1 = 0; var1 < this.a.length; var1++)
/* 39 */       this.a[var1].a(var0.b(".term[" + var1 + "]")); 
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements LootItemCondition.a {
/* 44 */     private final List<LootItemCondition> a = Lists.newArrayList();
/*    */     
/*    */     public a(LootItemCondition.a... var0) {
/* 47 */       for (LootItemCondition.a var4 : var0) {
/* 48 */         this.a.add(var4.build());
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public a a(LootItemCondition.a var0) {
/* 54 */       this.a.add(var0.build());
/* 55 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemCondition build() {
/* 60 */       return new LootItemConditionAlternative(this.a.<LootItemCondition>toArray(new LootItemCondition[0]));
/*    */     }
/*    */   }
/*    */   
/*    */   public static a a(LootItemCondition.a... var0) {
/* 65 */     return new a(var0);
/*    */   }
/*    */   
/*    */   public static class b
/*    */     implements LootSerializer<LootItemConditionAlternative> {
/*    */     public void a(JsonObject var0, LootItemConditionAlternative var1, JsonSerializationContext var2) {
/* 71 */       var0.add("terms", var2.serialize(LootItemConditionAlternative.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemConditionAlternative a(JsonObject var0, JsonDeserializationContext var1) {
/* 76 */       LootItemCondition[] var2 = ChatDeserializer.<LootItemCondition[]>a(var0, "terms", var1, (Class)LootItemCondition[].class);
/* 77 */       return new LootItemConditionAlternative(var2);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemConditionAlternative.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */