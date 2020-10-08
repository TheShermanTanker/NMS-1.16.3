/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import java.util.Arrays;
/*    */ import java.util.UUID;
/*    */ import java.util.stream.IntStream;
/*    */ 
/*    */ public final class MinecraftSerializableUUID {
/*    */   static {
/* 11 */     a = Codec.INT_STREAM.comapFlatMap(var0 -> SystemUtils.a(var0, 4).map(MinecraftSerializableUUID::a), var0 -> Arrays.stream(a(var0)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<UUID> a;
/*    */ 
/*    */   
/*    */   public static UUID a(int[] var0) {
/* 20 */     return new UUID(var0[0] << 32L | var0[1] & 0xFFFFFFFFL, var0[2] << 32L | var0[3] & 0xFFFFFFFFL);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int[] a(UUID var0) {
/* 27 */     long var1 = var0.getMostSignificantBits();
/* 28 */     long var3 = var0.getLeastSignificantBits();
/* 29 */     return a(var1, var3);
/*    */   }
/*    */   
/*    */   private static int[] a(long var0, long var2) {
/* 33 */     return new int[] { (int)(var0 >> 32L), (int)var0, (int)(var2 >> 32L), (int)var2 };
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftSerializableUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */