/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*    */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentSerializers
/*    */ {
/*    */   public static void a() {
/* 18 */     ArgumentRegistry.a("brigadier:bool", BoolArgumentType.class, new ArgumentSerializerVoid<>(BoolArgumentType::bool));
/* 19 */     ArgumentRegistry.a("brigadier:float", FloatArgumentType.class, new ArgumentSerializerFloat());
/* 20 */     ArgumentRegistry.a("brigadier:double", DoubleArgumentType.class, new ArgumentSerializerDouble());
/* 21 */     ArgumentRegistry.a("brigadier:integer", IntegerArgumentType.class, new ArgumentSerializerInteger());
/* 22 */     ArgumentRegistry.a("brigadier:long", LongArgumentType.class, new ArgumentSerializerLong());
/* 23 */     ArgumentRegistry.a("brigadier:string", StringArgumentType.class, new ArgumentSerializerString());
/*    */   }
/*    */   
/*    */   public static byte a(boolean var0, boolean var1) {
/* 27 */     byte var2 = 0;
/* 28 */     if (var0) {
/* 29 */       var2 = (byte)(var2 | 0x1);
/*    */     }
/* 31 */     if (var1) {
/* 32 */       var2 = (byte)(var2 | 0x2);
/*    */     }
/* 34 */     return var2;
/*    */   }
/*    */   
/*    */   public static boolean a(byte var0) {
/* 38 */     return ((var0 & 0x1) != 0);
/*    */   }
/*    */   
/*    */   public static boolean b(byte var0) {
/* 42 */     return ((var0 & 0x2) != 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */