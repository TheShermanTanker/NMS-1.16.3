/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentSerializerDouble
/*    */   implements ArgumentSerializer<DoubleArgumentType>
/*    */ {
/*    */   public void a(DoubleArgumentType var0, PacketDataSerializer var1) {
/* 15 */     boolean var2 = (var0.getMinimum() != -1.7976931348623157E308D);
/* 16 */     boolean var3 = (var0.getMaximum() != Double.MAX_VALUE);
/* 17 */     var1.writeByte(ArgumentSerializers.a(var2, var3));
/* 18 */     if (var2) {
/* 19 */       var1.writeDouble(var0.getMinimum());
/*    */     }
/* 21 */     if (var3) {
/* 22 */       var1.writeDouble(var0.getMaximum());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleArgumentType b(PacketDataSerializer var0) {
/* 28 */     byte var1 = var0.readByte();
/* 29 */     double var2 = ArgumentSerializers.a(var1) ? var0.readDouble() : -1.7976931348623157E308D;
/* 30 */     double var4 = ArgumentSerializers.b(var1) ? var0.readDouble() : Double.MAX_VALUE;
/* 31 */     return DoubleArgumentType.doubleArg(var2, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(DoubleArgumentType var0, JsonObject var1) {
/* 36 */     if (var0.getMinimum() != -1.7976931348623157E308D) {
/* 37 */       var1.addProperty("min", Double.valueOf(var0.getMinimum()));
/*    */     }
/* 39 */     if (var0.getMaximum() != Double.MAX_VALUE)
/* 40 */       var1.addProperty("max", Double.valueOf(var0.getMaximum())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializerDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */