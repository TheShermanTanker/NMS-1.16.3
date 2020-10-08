/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public interface DataWatcherSerializer<T>
/*    */ {
/*    */   void a(PacketDataSerializer paramPacketDataSerializer, T paramT);
/*    */   
/*    */   T a(PacketDataSerializer paramPacketDataSerializer);
/*    */   
/*    */   default DataWatcherObject<T> a(int var0) {
/* 11 */     return new DataWatcherObject<>(var0, this);
/*    */   }
/*    */   
/*    */   T a(T paramT);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataWatcherSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */