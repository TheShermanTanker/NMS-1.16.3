/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeSerializerCooking<T extends RecipeCooking>
/*    */   implements RecipeSerializer<T>
/*    */ {
/*    */   private final int v;
/*    */   private final a<T> w;
/*    */   
/*    */   public RecipeSerializerCooking(a<T> var0, int var1) {
/* 20 */     this.v = var1;
/* 21 */     this.w = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public T a(MinecraftKey var0, JsonObject var1) {
/* 26 */     String var2 = ChatDeserializer.a(var1, "group", "");
/*    */     
/* 28 */     JsonElement var3 = ChatDeserializer.d(var1, "ingredient") ? (JsonElement)ChatDeserializer.u(var1, "ingredient") : (JsonElement)ChatDeserializer.t(var1, "ingredient");
/* 29 */     RecipeItemStack var4 = RecipeItemStack.a(var3);
/*    */     
/* 31 */     String var5 = ChatDeserializer.h(var1, "result");
/* 32 */     MinecraftKey var6 = new MinecraftKey(var5);
/* 33 */     ItemStack var7 = new ItemStack((IMaterial)IRegistry.ITEM.getOptional(var6).orElseThrow(() -> new IllegalStateException("Item: " + var0 + " does not exist")));
/* 34 */     float var8 = ChatDeserializer.a(var1, "experience", 0.0F);
/* 35 */     int var9 = ChatDeserializer.a(var1, "cookingtime", this.v);
/*    */     
/* 37 */     return this.w.create(var0, var2, var4, var7, var8, var9);
/*    */   }
/*    */ 
/*    */   
/*    */   public T a(MinecraftKey var0, PacketDataSerializer var1) {
/* 42 */     String var2 = var1.e(32767);
/* 43 */     RecipeItemStack var3 = RecipeItemStack.b(var1);
/* 44 */     ItemStack var4 = var1.n();
/* 45 */     float var5 = var1.readFloat();
/* 46 */     int var6 = var1.i();
/* 47 */     return this.w.create(var0, var2, var3, var4, var5, var6);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0, T var1) {
/* 52 */     var0.a(((RecipeCooking)var1).group);
/* 53 */     ((RecipeCooking)var1).ingredient.a(var0);
/* 54 */     var0.a(((RecipeCooking)var1).result);
/* 55 */     var0.writeFloat(((RecipeCooking)var1).experience);
/* 56 */     var0.d(((RecipeCooking)var1).cookingTime);
/*    */   }
/*    */   
/*    */   static interface a<T extends RecipeCooking> {
/*    */     T create(MinecraftKey param1MinecraftKey, String param1String, RecipeItemStack param1RecipeItemStack, ItemStack param1ItemStack, float param1Float, int param1Int);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeSerializerCooking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */