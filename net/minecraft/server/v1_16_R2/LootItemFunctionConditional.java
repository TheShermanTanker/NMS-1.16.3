/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LootItemFunctionConditional
/*    */   implements LootItemFunction
/*    */ {
/*    */   protected final LootItemCondition[] c;
/*    */   private final Predicate<LootTableInfo> a;
/*    */   
/*    */   protected LootItemFunctionConditional(LootItemCondition[] var0) {
/* 25 */     this.c = var0;
/* 26 */     this.a = LootItemConditions.a((Predicate<LootTableInfo>[])var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public final ItemStack apply(ItemStack var0, LootTableInfo var1) {
/* 31 */     return this.a.test(var1) ? a(var0, var1) : var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 38 */     super.a(var0);
/*    */     
/* 40 */     for (int var1 = 0; var1 < this.c.length; var1++)
/* 41 */       this.c[var1].a(var0.b(".conditions[" + var1 + "]")); 
/*    */   }
/*    */   
/*    */   public static abstract class a<T extends a<T>>
/*    */     implements LootItemFunction.a, LootItemConditionUser<T> {
/* 46 */     private final List<LootItemCondition> a = Lists.newArrayList();
/*    */ 
/*    */     
/*    */     public T b(LootItemCondition.a var0) {
/* 50 */       this.a.add(var0.build());
/* 51 */       return d();
/*    */     }
/*    */ 
/*    */     
/*    */     public final T c() {
/* 56 */       return d();
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     protected LootItemCondition[] g() {
/* 62 */       return this.a.<LootItemCondition>toArray(new LootItemCondition[0]);
/*    */     }
/*    */     
/*    */     protected abstract T d();
/*    */   }
/*    */   
/*    */   static final class b extends a<b> {
/*    */     public b(Function<LootItemCondition[], LootItemFunction> var0) {
/* 70 */       this.a = var0;
/*    */     }
/*    */     private final Function<LootItemCondition[], LootItemFunction> a;
/*    */     
/*    */     protected b d() {
/* 75 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunction b() {
/* 80 */       return this.a.apply(g());
/*    */     }
/*    */   }
/*    */   
/*    */   protected static a<?> a(Function<LootItemCondition[], LootItemFunction> var0) {
/* 85 */     return new b(var0);
/*    */   }
/*    */   
/*    */   protected abstract ItemStack a(ItemStack paramItemStack, LootTableInfo paramLootTableInfo);
/*    */   
/*    */   public static abstract class c<T extends LootItemFunctionConditional> implements LootSerializer<T> { public void a(JsonObject var0, T var1, JsonSerializationContext var2) {
/* 91 */       if (!ArrayUtils.isEmpty((Object[])((LootItemFunctionConditional)var1).c)) {
/* 92 */         var0.add("conditions", var2.serialize(((LootItemFunctionConditional)var1).c));
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     public final T a(JsonObject var0, JsonDeserializationContext var1) {
/* 98 */       LootItemCondition[] var2 = ChatDeserializer.<LootItemCondition[]>a(var0, "conditions", new LootItemCondition[0], var1, (Class)LootItemCondition[].class);
/* 99 */       return b(var0, var1, var2);
/*    */     }
/*    */     
/*    */     public abstract T b(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition); }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionConditional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */