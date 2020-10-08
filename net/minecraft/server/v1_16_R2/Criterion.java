/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonSyntaxException;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Criterion
/*    */ {
/*    */   private final CriterionInstance a;
/*    */   
/*    */   public Criterion(CriterionInstance var0) {
/* 20 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public Criterion() {
/* 24 */     this.a = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) {}
/*    */   
/*    */   public static Criterion a(JsonObject var0, LootDeserializationContext var1) {
/* 31 */     MinecraftKey var2 = new MinecraftKey(ChatDeserializer.h(var0, "trigger"));
/* 32 */     CriterionTrigger<?> var3 = CriterionTriggers.a(var2);
/* 33 */     if (var3 == null) {
/* 34 */       throw new JsonSyntaxException("Invalid criterion trigger: " + var2);
/*    */     }
/* 36 */     CriterionInstance var4 = (CriterionInstance)var3.a(ChatDeserializer.a(var0, "conditions", new JsonObject()), var1);
/* 37 */     return new Criterion(var4);
/*    */   }
/*    */   
/*    */   public static Criterion b(PacketDataSerializer var0) {
/* 41 */     return new Criterion();
/*    */   }
/*    */   
/*    */   public static Map<String, Criterion> b(JsonObject var0, LootDeserializationContext var1) {
/* 45 */     Map<String, Criterion> var2 = Maps.newHashMap();
/* 46 */     for (Map.Entry<String, JsonElement> var4 : (Iterable<Map.Entry<String, JsonElement>>)var0.entrySet()) {
/* 47 */       var2.put(var4.getKey(), a(ChatDeserializer.m(var4.getValue(), "criterion"), var1));
/*    */     }
/* 49 */     return var2;
/*    */   }
/*    */   
/*    */   public static Map<String, Criterion> c(PacketDataSerializer var0) {
/* 53 */     Map<String, Criterion> var1 = Maps.newHashMap();
/* 54 */     int var2 = var0.i();
/* 55 */     for (int var3 = 0; var3 < var2; var3++) {
/* 56 */       var1.put(var0.e(32767), b(var0));
/*    */     }
/* 58 */     return var1;
/*    */   }
/*    */   
/*    */   public static void a(Map<String, Criterion> var0, PacketDataSerializer var1) {
/* 62 */     var1.d(var0.size());
/* 63 */     for (Map.Entry<String, Criterion> var3 : var0.entrySet()) {
/* 64 */       var1.a(var3.getKey());
/* 65 */       ((Criterion)var3.getValue()).a(var1);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CriterionInstance a() {
/* 71 */     return this.a;
/*    */   }
/*    */   
/*    */   public JsonElement b() {
/* 75 */     JsonObject var0 = new JsonObject();
/* 76 */     var0.addProperty("trigger", this.a.a().toString());
/* 77 */     JsonObject var1 = this.a.a(LootSerializationContext.a);
/* 78 */     if (var1.size() != 0) {
/* 79 */       var0.add("conditions", (JsonElement)var1);
/*    */     }
/* 81 */     return (JsonElement)var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Criterion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */