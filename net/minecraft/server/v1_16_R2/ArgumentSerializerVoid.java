/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class ArgumentSerializerVoid<T extends ArgumentType<?>>
/*    */   implements ArgumentSerializer<T>
/*    */ {
/*    */   private final Supplier<T> a;
/*    */   
/*    */   public ArgumentSerializerVoid(Supplier<T> var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(T var0, PacketDataSerializer var1) {}
/*    */ 
/*    */   
/*    */   public T b(PacketDataSerializer var0) {
/* 22 */     return this.a.get();
/*    */   }
/*    */   
/*    */   public void a(T var0, JsonObject var1) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentSerializerVoid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */