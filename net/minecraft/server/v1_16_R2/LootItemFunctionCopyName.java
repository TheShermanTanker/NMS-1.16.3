/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionCopyName
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final Source a;
/*    */   
/*    */   private LootItemFunctionCopyName(LootItemCondition[] var0, Source var1) {
/* 21 */     super(var0);
/* 22 */     this.a = var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 28 */     return LootItemFunctions.m;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 33 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(this.a.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 38 */     Object var2 = var1.getContextParameter(this.a.f);
/* 39 */     if (var2 instanceof INamableTileEntity) {
/* 40 */       INamableTileEntity var3 = (INamableTileEntity)var2;
/* 41 */       if (var3.hasCustomName()) {
/* 42 */         var0.a(var3.getScoreboardDisplayName());
/*    */       }
/*    */     } 
/* 45 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> a(Source var0) {
/* 49 */     return a(var1 -> new LootItemFunctionCopyName(var1, var0));
/*    */   }
/*    */   
/*    */   public enum Source {
/* 53 */     THIS("this", LootContextParameters.THIS_ENTITY),
/* 54 */     KILLER("killer", LootContextParameters.KILLER_ENTITY),
/* 55 */     KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER),
/* 56 */     BLOCK_ENTITY("block_entity", LootContextParameters.BLOCK_ENTITY);
/*    */     
/*    */     public final String e;
/*    */     public final LootContextParameter<?> f;
/*    */     
/*    */     Source(String var2, LootContextParameter<?> var3) {
/* 62 */       this.e = var2;
/* 63 */       this.f = var3;
/*    */     }
/*    */     
/*    */     public static Source a(String var0) {
/* 67 */       for (Source var4 : values()) {
/* 68 */         if (var4.e.equals(var0)) {
/* 69 */           return var4;
/*    */         }
/*    */       } 
/* 72 */       throw new IllegalArgumentException("Invalid name source " + var0);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class b
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionCopyName> {
/*    */     public void a(JsonObject var0, LootItemFunctionCopyName var1, JsonSerializationContext var2) {
/* 79 */       super.a(var0, var1, var2);
/*    */       
/* 81 */       var0.addProperty("source", (LootItemFunctionCopyName.a(var1)).e);
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionCopyName b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 86 */       LootItemFunctionCopyName.Source var3 = LootItemFunctionCopyName.Source.a(ChatDeserializer.h(var0, "source"));
/* 87 */       return new LootItemFunctionCopyName(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionCopyName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */