/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.List;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*    */ 
/*    */ public abstract class LootEntryAbstract implements LootEntryChildren {
/*    */   protected final LootItemCondition[] d;
/*    */   private final Predicate<LootTableInfo> c;
/*    */   
/*    */   protected LootEntryAbstract(LootItemCondition[] alootitemcondition) {
/* 16 */     this.d = alootitemcondition;
/* 17 */     this.c = LootItemConditions.a((Predicate<LootTableInfo>[])alootitemcondition);
/*    */   }
/*    */   
/*    */   public void a(LootCollector lootcollector) {
/* 21 */     for (int i = 0; i < this.d.length; i++) {
/* 22 */       this.d[i].a(lootcollector.b(".condition[" + i + "]"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected final boolean a(LootTableInfo loottableinfo) {
/* 28 */     return this.c.test(loottableinfo);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract LootEntryType a();
/*    */ 
/*    */   
/*    */   public static abstract class Serializer<T extends LootEntryAbstract>
/*    */     implements LootSerializer<T>
/*    */   {
/*    */     public void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext) {
/* 40 */       if (!ArrayUtils.isEmpty((Object[])((LootEntryAbstract)t0).d)) {
/* 41 */         jsonobject.add("conditions", jsonserializationcontext.serialize(((LootEntryAbstract)t0).d));
/*    */       }
/*    */       
/* 44 */       serializeType(jsonobject, t0, jsonserializationcontext);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public final T a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
/* 50 */       LootItemCondition[] alootitemcondition = ChatDeserializer.<LootItemCondition[]>a(jsonobject, "conditions", new LootItemCondition[0], jsondeserializationcontext, (Class)LootItemCondition[].class);
/*    */       
/* 52 */       return deserializeType(jsonobject, jsondeserializationcontext, alootitemcondition);
/*    */     }
/*    */     
/*    */     public abstract void serializeType(JsonObject param1JsonObject, T param1T, JsonSerializationContext param1JsonSerializationContext);
/*    */     
/*    */     public abstract T deserializeType(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, LootItemCondition[] param1ArrayOfLootItemCondition);
/*    */   }
/*    */   
/*    */   public static abstract class a<T extends a<T>>
/*    */     implements LootItemConditionUser<T> {
/* 62 */     private final List<LootItemCondition> a = Lists.newArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public T b(LootItemCondition.a lootitemcondition_a) {
/* 70 */       this.a.add(lootitemcondition_a.build());
/* 71 */       return d();
/*    */     }
/*    */ 
/*    */     
/*    */     public final T c() {
/* 76 */       return d();
/*    */     }
/*    */     
/*    */     protected LootItemCondition[] f() {
/* 80 */       return this.a.<LootItemCondition>toArray(new LootItemCondition[0]);
/*    */     }
/*    */     
/*    */     public LootEntryAlternatives.a a(a<?> lootentryabstract_a) {
/* 84 */       return new LootEntryAlternatives.a((a<?>[])new a[] { this, lootentryabstract_a });
/*    */     }
/*    */     
/*    */     protected abstract T d();
/*    */     
/*    */     public abstract LootEntryAbstract b();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntryAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */