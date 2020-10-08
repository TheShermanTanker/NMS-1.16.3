/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CriterionTriggerChangedDimension
/*    */   extends CriterionTriggerAbstract<CriterionTriggerChangedDimension.a>
/*    */ {
/* 14 */   private static final MinecraftKey a = new MinecraftKey("changed_dimension");
/*    */ 
/*    */   
/*    */   public MinecraftKey a() {
/* 18 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/* 23 */     ResourceKey<World> var3 = var0.has("from") ? ResourceKey.<World>a(IRegistry.L, new MinecraftKey(ChatDeserializer.h(var0, "from"))) : null;
/* 24 */     ResourceKey<World> var4 = var0.has("to") ? ResourceKey.<World>a(IRegistry.L, new MinecraftKey(ChatDeserializer.h(var0, "to"))) : null;
/* 25 */     return new a(var1, var3, var4);
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0, ResourceKey<World> var1, ResourceKey<World> var2) {
/* 29 */     a(var0, var2 -> var2.b(var0, var1));
/*    */   }
/*    */   
/*    */   public static class a extends CriterionInstanceAbstract {
/*    */     @Nullable
/*    */     private final ResourceKey<World> a;
/*    */     @Nullable
/*    */     private final ResourceKey<World> b;
/*    */     
/*    */     public a(CriterionConditionEntity.b var0, @Nullable ResourceKey<World> var1, @Nullable ResourceKey<World> var2) {
/* 39 */       super(CriterionTriggerChangedDimension.b(), var0);
/* 40 */       this.a = var1;
/* 41 */       this.b = var2;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public static a a(ResourceKey<World> var0) {
/* 53 */       return new a(CriterionConditionEntity.b.a, null, var0);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean b(ResourceKey<World> var0, ResourceKey<World> var1) {
/* 61 */       if (this.a != null && this.a != var0) {
/* 62 */         return false;
/*    */       }
/* 64 */       if (this.b != null && this.b != var1) {
/* 65 */         return false;
/*    */       }
/* 67 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonObject a(LootSerializationContext var0) {
/* 72 */       JsonObject var1 = super.a(var0);
/* 73 */       if (this.a != null) {
/* 74 */         var1.addProperty("from", this.a.a().toString());
/*    */       }
/* 76 */       if (this.b != null) {
/* 77 */         var1.addProperty("to", this.b.a().toString());
/*    */       }
/* 79 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerChangedDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */