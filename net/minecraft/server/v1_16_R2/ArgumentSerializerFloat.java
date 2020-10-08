/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentSerializerFloat
/*    */   implements ArgumentSerializer<FloatArgumentType>
/*    */ {
/*    */   public void a(FloatArgumentType var0, PacketDataSerializer var1) {
/* 15 */     boolean var2 = (var0.getMinimum() != -3.4028235E38F);
/* 16 */     boolean var3 = (var0.getMaximum() != Float.MAX_VALUE);
/* 17 */     var1.writeByte(ArgumentSerializers.a(var2, var3));
/* 18 */     if (var2) {
/* 19 */       var1.writeFloat(var0.getMinimum());
/*    */     }
/* 21 */     if (var3) {
/* 22 */       var1.writeFloat(var0.getMaximum());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public FloatArgumentType b(PacketDataSerializer var0) {
/* 28 */     byte var1 = var0.readByte();
/* 29 */     float var2 = ArgumentSerializers.a(var1) ? var0.readFloat() : -3.4028235E38F;
/* 30 */     float var3 = ArgumentSerializers.b(var1) ? var0.readFloat() : Float.MAX_VALUE;
/* 31 */     return FloatArgumentType.floatArg(var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(FloatArgumentType var0, JsonObject var1) {
/* 36 */     if (var0.getMinimum() != -3.4028235E38F) {
/* 37 */       var1.addProperty("min", Float.valueOf(var0.getMinimum()));
/*    */     }
/* 39 */     if (var0.getMaximum() != Float.MAX_VALUE)
/* 40 */       var1.addProperty("max", Float.valueOf(var0.getMaximum())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializerFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */