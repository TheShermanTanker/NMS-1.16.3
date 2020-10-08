/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.util.Set;
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
/*    */ public class LootItemFunctionFillPlayerHead
/*    */   extends LootItemFunctionConditional
/*    */ {
/*    */   private final LootTableInfo.EntityTarget a;
/*    */   
/*    */   public LootItemFunctionFillPlayerHead(LootItemCondition[] var0, LootTableInfo.EntityTarget var1) {
/* 26 */     super(var0);
/* 27 */     this.a = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootItemFunctionType b() {
/* 32 */     return LootItemFunctions.t;
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> a() {
/* 37 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(this.a.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 42 */     if (var0.getItem() == Items.PLAYER_HEAD) {
/* 43 */       Entity var2 = var1.<Entity>getContextParameter((LootContextParameter)this.a.a());
/* 44 */       if (var2 instanceof EntityHuman) {
/* 45 */         GameProfile var3 = ((EntityHuman)var2).getProfile();
/* 46 */         var0.getOrCreateTag().set("SkullOwner", GameProfileSerializer.serialize(new NBTTagCompound(), var3));
/*    */       } 
/*    */     } 
/* 49 */     return var0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class a
/*    */     extends LootItemFunctionConditional.c<LootItemFunctionFillPlayerHead>
/*    */   {
/*    */     public void a(JsonObject var0, LootItemFunctionFillPlayerHead var1, JsonSerializationContext var2) {
/* 59 */       super.a(var0, var1, var2);
/* 60 */       var0.add("entity", var2.serialize(LootItemFunctionFillPlayerHead.a(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     public LootItemFunctionFillPlayerHead b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 65 */       LootTableInfo.EntityTarget var3 = ChatDeserializer.<LootTableInfo.EntityTarget>a(var0, "entity", var1, LootTableInfo.EntityTarget.class);
/* 66 */       return new LootItemFunctionFillPlayerHead(var2, var3);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionFillPlayerHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */