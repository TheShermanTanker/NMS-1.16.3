/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSerializationContext
/*    */ {
/* 10 */   public static final LootSerializationContext a = new LootSerializationContext();
/*    */   
/* 12 */   private final Gson b = LootSerialization.a().create();
/*    */   
/*    */   public final JsonElement a(LootItemCondition[] var0) {
/* 15 */     return this.b.toJsonTree(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSerializationContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */