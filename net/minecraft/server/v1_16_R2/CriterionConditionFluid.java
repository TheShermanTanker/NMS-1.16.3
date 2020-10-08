/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
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
/*    */ 
/*    */ public class CriterionConditionFluid
/*    */ {
/* 20 */   public static final CriterionConditionFluid a = new CriterionConditionFluid(null, null, CriterionTriggerProperties.a);
/*    */   
/*    */   @Nullable
/*    */   private final Tag<FluidType> b;
/*    */   @Nullable
/*    */   private final FluidType c;
/*    */   private final CriterionTriggerProperties d;
/*    */   
/*    */   public CriterionConditionFluid(@Nullable Tag<FluidType> var0, @Nullable FluidType var1, CriterionTriggerProperties var2) {
/* 29 */     this.b = var0;
/* 30 */     this.c = var1;
/* 31 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public boolean a(WorldServer var0, BlockPosition var1) {
/* 35 */     if (this == a) {
/* 36 */       return true;
/*    */     }
/* 38 */     if (!var0.p(var1)) {
/* 39 */       return false;
/*    */     }
/* 41 */     Fluid var2 = var0.getFluid(var1);
/*    */     
/* 43 */     FluidType var3 = var2.getType();
/* 44 */     if (this.b != null && !this.b.isTagged(var3)) {
/* 45 */       return false;
/*    */     }
/* 47 */     if (this.c != null && var3 != this.c) {
/* 48 */       return false;
/*    */     }
/* 50 */     if (!this.d.a(var2)) {
/* 51 */       return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public static CriterionConditionFluid a(@Nullable JsonElement var0) {
/* 57 */     if (var0 == null || var0.isJsonNull()) {
/* 58 */       return a;
/*    */     }
/* 60 */     JsonObject var1 = ChatDeserializer.m(var0, "fluid");
/*    */     
/* 62 */     FluidType var2 = null;
/* 63 */     if (var1.has("fluid")) {
/* 64 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "fluid"));
/* 65 */       var2 = IRegistry.FLUID.get(minecraftKey);
/*    */     } 
/*    */     
/* 68 */     Tag<FluidType> var3 = null;
/* 69 */     if (var1.has("tag")) {
/* 70 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "tag"));
/* 71 */       var3 = TagsInstance.a().getFluidTags().a(minecraftKey);
/* 72 */       if (var3 == null) {
/* 73 */         throw new JsonSyntaxException("Unknown fluid tag '" + minecraftKey + "'");
/*    */       }
/*    */     } 
/* 76 */     CriterionTriggerProperties var4 = CriterionTriggerProperties.a(var1.get("state"));
/* 77 */     return new CriterionConditionFluid(var3, var2, var4);
/*    */   }
/*    */   
/*    */   public JsonElement a() {
/* 81 */     if (this == a) {
/* 82 */       return (JsonElement)JsonNull.INSTANCE;
/*    */     }
/*    */     
/* 85 */     JsonObject var0 = new JsonObject();
/* 86 */     if (this.c != null) {
/* 87 */       var0.addProperty("fluid", IRegistry.FLUID.getKey(this.c).toString());
/*    */     }
/* 89 */     if (this.b != null) {
/* 90 */       var0.addProperty("tag", TagsInstance.a().getFluidTags().b(this.b).toString());
/*    */     }
/* 92 */     var0.add("state", this.d.a());
/*    */     
/* 94 */     return (JsonElement)var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionFluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */