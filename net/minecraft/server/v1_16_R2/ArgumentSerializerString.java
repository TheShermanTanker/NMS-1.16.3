/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ 
/*    */ public class ArgumentSerializerString
/*    */   implements ArgumentSerializer<StringArgumentType>
/*    */ {
/*    */   public void a(StringArgumentType var0, PacketDataSerializer var1) {
/* 11 */     var1.a((Enum<?>)var0.getType());
/*    */   }
/*    */ 
/*    */   
/*    */   public StringArgumentType b(PacketDataSerializer var0) {
/* 16 */     StringArgumentType.StringType var1 = var0.<StringArgumentType.StringType>a(StringArgumentType.StringType.class);
/* 17 */     switch (null.a[var1.ordinal()]) {
/*    */       case 1:
/* 19 */         return StringArgumentType.word();
/*    */       case 2:
/* 21 */         return StringArgumentType.string();
/*    */     } 
/*    */     
/* 24 */     return StringArgumentType.greedyString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(StringArgumentType var0, JsonObject var1) {
/* 30 */     switch (null.a[var0.getType().ordinal()]) {
/*    */       case 1:
/* 32 */         var1.addProperty("type", "word");
/*    */         return;
/*    */       case 2:
/* 35 */         var1.addProperty("type", "phrase");
/*    */         return;
/*    */     } 
/*    */     
/* 39 */     var1.addProperty("type", "greedy");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializerString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */