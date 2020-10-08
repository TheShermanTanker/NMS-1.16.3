/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootItemFunctionSetTag
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final NBTTagCompound a;
/*    */   
/*    */   private LootItemFunctionSetTag(LootItemCondition[] var0, NBTTagCompound var1) {
/* 19 */     super(var0);
/* 20 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 25 */     return LootItemFunctions.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 30 */     var0.getOrCreateTag().a(this.a);
/* 31 */     return var0;
/*    */   }
/*    */   
/*    */   public static LootItemFunctionConditional.a<?> a(NBTTagCompound var0) {
/* 35 */     return a(var1 -> new LootItemFunctionSetTag(var1, var0));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionSetTag> {
/*    */     public void a(JsonObject var0, LootItemFunctionSetTag var1, JsonSerializationContext var2) {
/* 41 */       super.a(var0, var1, var2);
/*    */       
/* 43 */       var0.addProperty("tag", LootItemFunctionSetTag.a(var1).toString());
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionSetTag b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/*    */       try {
/* 49 */         NBTTagCompound var3 = MojangsonParser.parse(ChatDeserializer.h(var0, "tag"));
/* 50 */         return new LootItemFunctionSetTag(var2, var3);
/* 51 */       } catch (CommandSyntaxException var3) {
/* 52 */         throw new JsonSyntaxException(var3.getMessage());
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */