/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonNull;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CriterionConditionEntityType
/*    */ {
/* 18 */   public static final CriterionConditionEntityType a = new CriterionConditionEntityType()
/*    */     {
/*    */       public boolean a(EntityTypes<?> var0) {
/* 21 */         return true;
/*    */       }
/*    */ 
/*    */       
/*    */       public JsonElement a() {
/* 26 */         return (JsonElement)JsonNull.INSTANCE;
/*    */       }
/*    */     };
/*    */   
/* 30 */   private static final Joiner b = Joiner.on(", ");
/*    */ 
/*    */   
/*    */   static class b
/*    */     extends CriterionConditionEntityType
/*    */   {
/*    */     private final EntityTypes<?> b;
/*    */ 
/*    */     
/*    */     public b(EntityTypes<?> var0) {
/* 40 */       this.b = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean a(EntityTypes<?> var0) {
/* 45 */       return (this.b == var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a() {
/* 50 */       return (JsonElement)new JsonPrimitive(IRegistry.ENTITY_TYPE.getKey(this.b).toString());
/*    */     }
/*    */   }
/*    */   
/*    */   static class a extends CriterionConditionEntityType {
/*    */     private final Tag<EntityTypes<?>> b;
/*    */     
/*    */     public a(Tag<EntityTypes<?>> var0) {
/* 58 */       this.b = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean a(EntityTypes<?> var0) {
/* 63 */       return this.b.isTagged(var0);
/*    */     }
/*    */ 
/*    */     
/*    */     public JsonElement a() {
/* 68 */       return (JsonElement)new JsonPrimitive("#" + TagsInstance.a().getEntityTags().b(this.b));
/*    */     }
/*    */   }
/*    */   
/*    */   public static CriterionConditionEntityType a(@Nullable JsonElement var0) {
/* 73 */     if (var0 == null || var0.isJsonNull()) {
/* 74 */       return a;
/*    */     }
/*    */     
/* 77 */     String var1 = ChatDeserializer.a(var0, "type");
/*    */     
/* 79 */     if (var1.startsWith("#")) {
/* 80 */       MinecraftKey minecraftKey = new MinecraftKey(var1.substring(1));
/* 81 */       return new a(TagsInstance.a().getEntityTags().b(minecraftKey));
/*    */     } 
/* 83 */     MinecraftKey var2 = new MinecraftKey(var1);
/*    */     
/* 85 */     EntityTypes<?> var3 = (EntityTypes)IRegistry.ENTITY_TYPE.getOptional(var2).orElseThrow(() -> new JsonSyntaxException("Unknown entity type '" + var0 + "', valid types are: " + b.join(IRegistry.ENTITY_TYPE.keySet())));
/* 86 */     return new b(var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CriterionConditionEntityType b(EntityTypes<?> var0) {
/* 91 */     return new b(var0);
/*    */   }
/*    */   
/*    */   public static CriterionConditionEntityType a(Tag<EntityTypes<?>> var0) {
/* 95 */     return new a(var0);
/*    */   }
/*    */   
/*    */   public abstract boolean a(EntityTypes<?> paramEntityTypes);
/*    */   
/*    */   public abstract JsonElement a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionEntityType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */