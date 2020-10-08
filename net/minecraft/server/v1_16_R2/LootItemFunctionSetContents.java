/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetContents
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final List<LootEntryAbstract> a;
/*    */   
/*    */   private LootItemFunctionSetContents(LootItemCondition[] var0, List<LootEntryAbstract> var1) {
/* 27 */     super(var0);
/* 28 */     this.a = (List<LootEntryAbstract>)ImmutableList.copyOf(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 33 */     return LootItemFunctions.n;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 38 */     if (var0.isEmpty()) {
/* 39 */       return var0;
/*    */     }
/*    */     
/* 42 */     NonNullList<ItemStack> var2 = NonNullList.a();
/* 43 */     this.a.forEach(var2 -> var2.expand(var0, ()));
/*    */     
/* 45 */     NBTTagCompound var3 = new NBTTagCompound();
/* 46 */     ContainerUtil.a(var3, var2);
/* 47 */     NBTTagCompound var4 = var0.getOrCreateTag();
/* 48 */     var4.set("BlockEntityTag", var3.a(var4.getCompound("BlockEntityTag")));
/* 49 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 54 */     super.a(var0);
/*    */     
/* 56 */     for (int var1 = 0; var1 < this.a.size(); var1++)
/* 57 */       ((LootEntryAbstract)this.a.get(var1)).a(var0.b(".entry[" + var1 + "]")); 
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.a<a> {
/* 62 */     private final List<LootEntryAbstract> a = Lists.newArrayList();
/*    */ 
/*    */     
/*    */     protected a d() {
/* 66 */       return this;
/*    */     }
/*    */     
/*    */     public a a(LootEntryAbstract.a<?> var0) {
/* 70 */       this.a.add(var0.b());
/* 71 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunction b() {
/* 76 */       return new LootItemFunctionSetContents(g(), this.a);
/*    */     }
/*    */   }
/*    */   
/*    */   public static a c() {
/* 81 */     return new a();
/*    */   }
/*    */   
/*    */   public static class b
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSetContents> {
/*    */     public void a(JsonObject var0, LootItemFunctionSetContents var1, JsonSerializationContext var2) {
/* 87 */       super.a(var0, var1, var2);
/*    */       
/* 89 */       var0.add("entries", var2.serialize(LootItemFunctionSetContents.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetContents b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 94 */       LootEntryAbstract[] var3 = ChatDeserializer.<LootEntryAbstract[]>a(var0, "entries", var1, (Class)LootEntryAbstract[].class);
/* 95 */       return new LootItemFunctionSetContents(var2, Arrays.asList(var3));
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetContents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */