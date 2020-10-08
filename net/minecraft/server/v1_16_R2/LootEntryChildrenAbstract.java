/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LootEntryChildrenAbstract
/*    */   extends LootEntryAbstract
/*    */ {
/*    */   protected final LootEntryAbstract[] c;
/*    */   private final LootEntryChildren e;
/*    */   
/*    */   protected LootEntryChildrenAbstract(LootEntryAbstract[] var0, LootItemCondition[] var1) {
/* 18 */     super(var1);
/* 19 */     this.c = var0;
/* 20 */     this.e = a((LootEntryChildren[])var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 25 */     super.a(var0);
/*    */     
/* 27 */     if (this.c.length == 0) {
/* 28 */       var0.a("Empty children list");
/*    */     }
/*    */     
/* 31 */     for (int var1 = 0; var1 < this.c.length; var1++) {
/* 32 */       this.c[var1].a(var0.b(".entry[" + var1 + "]"));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected abstract LootEntryChildren a(LootEntryChildren[] paramArrayOfLootEntryChildren);
/*    */   
/*    */   public final boolean expand(LootTableInfo var0, Consumer<LootEntry> var1) {
/* 40 */     if (!a(var0)) {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     return this.e.expand(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends LootEntryChildrenAbstract> LootEntryAbstract.Serializer<T> a(a<T> var0) {
/* 53 */     return new LootEntryAbstract.Serializer<T>(var0)
/*    */       {
/*    */         public void a(JsonObject var0, T var1, JsonSerializationContext var2) {
/* 56 */           var0.add("children", var2.serialize(((LootEntryChildrenAbstract)var1).c));
/*    */         }
/*    */ 
/*    */         
/*    */         public final T deserializeType(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 61 */           LootEntryAbstract[] var3 = ChatDeserializer.<LootEntryAbstract[]>a(var0, "children", var1, (Class)LootEntryAbstract[].class);
/* 62 */           return (T)this.a.create(var3, var2);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface a<T extends LootEntryChildrenAbstract> {
/*    */     T create(LootEntryAbstract[] param1ArrayOfLootEntryAbstract, LootItemCondition[] param1ArrayOfLootItemCondition);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntryChildrenAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */