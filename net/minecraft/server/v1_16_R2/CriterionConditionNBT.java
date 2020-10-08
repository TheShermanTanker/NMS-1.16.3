/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionConditionNBT
/*    */ {
/* 20 */   public static final CriterionConditionNBT a = new CriterionConditionNBT(null);
/*    */   
/*    */   @Nullable
/*    */   private final NBTTagCompound b;
/*    */   
/*    */   public CriterionConditionNBT(@Nullable NBTTagCompound var0) {
/* 26 */     this.b = var0;
/*    */   }
/*    */   
/*    */   public boolean a(ItemStack var0) {
/* 30 */     if (this == a) {
/* 31 */       return true;
/*    */     }
/* 33 */     return a(var0.getTag());
/*    */   }
/*    */   
/*    */   public boolean a(Entity var0) {
/* 37 */     if (this == a) {
/* 38 */       return true;
/*    */     }
/* 40 */     return a(b(var0));
/*    */   }
/*    */   
/*    */   public boolean a(@Nullable NBTBase var0) {
/* 44 */     if (var0 == null) {
/* 45 */       return (this == a);
/*    */     }
/*    */     
/* 48 */     if (this.b != null && !GameProfileSerializer.a(this.b, var0, true)) {
/* 49 */       return false;
/*    */     }
/*    */     
/* 52 */     return true;
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 56 */     if (this == a || this.b == null) {
/* 57 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 60 */     return (JsonElement)new JsonPrimitive(this.b.toString());
/*    */   }
/*    */   public static CriterionConditionNBT a(@Nullable JsonElement var0) {
/*    */     NBTTagCompound var1;
/* 64 */     if (var0 == null || var0.isJsonNull()) {
/* 65 */       return a;
/*    */     }
/*    */     
/*    */     try {
/* 69 */       var1 = MojangsonParser.parse(ChatDeserializer.a(var0, "nbt"));
/* 70 */     } catch (CommandSyntaxException var2) {
/* 71 */       throw new JsonSyntaxException("Invalid nbt tag: " + var2.getMessage());
/*    */     } 
/* 73 */     return new CriterionConditionNBT(var1);
/*    */   }
/*    */   
/*    */   public static NBTTagCompound b(Entity var0) {
/* 77 */     NBTTagCompound var1 = var0.save(new NBTTagCompound());
/* 78 */     if (var0 instanceof EntityHuman) {
/* 79 */       ItemStack var2 = ((EntityHuman)var0).inventory.getItemInHand();
/* 80 */       if (!var2.isEmpty()) {
/* 81 */         var1.set("SelectedItem", var2.save(new NBTTagCompound()));
/*    */       }
/*    */     } 
/* 84 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */