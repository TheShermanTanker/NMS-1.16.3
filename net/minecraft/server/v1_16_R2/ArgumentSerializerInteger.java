/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentSerializerInteger
/*    */   implements ArgumentSerializer<IntegerArgumentType>
/*    */ {
/*    */   public void a(IntegerArgumentType var0, PacketDataSerializer var1) {
/* 15 */     boolean var2 = (var0.getMinimum() != Integer.MIN_VALUE);
/* 16 */     boolean var3 = (var0.getMaximum() != Integer.MAX_VALUE);
/* 17 */     var1.writeByte(ArgumentSerializers.a(var2, var3));
/* 18 */     if (var2) {
/* 19 */       var1.writeInt(var0.getMinimum());
/*    */     }
/* 21 */     if (var3) {
/* 22 */       var1.writeInt(var0.getMaximum());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public IntegerArgumentType b(PacketDataSerializer var0) {
/* 28 */     byte var1 = var0.readByte();
/* 29 */     int var2 = ArgumentSerializers.a(var1) ? var0.readInt() : Integer.MIN_VALUE;
/* 30 */     int var3 = ArgumentSerializers.b(var1) ? var0.readInt() : Integer.MAX_VALUE;
/* 31 */     return IntegerArgumentType.integer(var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IntegerArgumentType var0, JsonObject var1) {
/* 36 */     if (var0.getMinimum() != Integer.MIN_VALUE) {
/* 37 */       var1.addProperty("min", Integer.valueOf(var0.getMinimum()));
/*    */     }
/* 39 */     if (var0.getMaximum() != Integer.MAX_VALUE)
/* 40 */       var1.addProperty("max", Integer.valueOf(var0.getMaximum())); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializerInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */