/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.channel.ChannelFuture;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Packet<T extends PacketListener>
/*    */ {
/*    */   void a(PacketDataSerializer paramPacketDataSerializer) throws IOException;
/*    */   
/*    */   void b(PacketDataSerializer paramPacketDataSerializer) throws IOException;
/*    */   
/*    */   void a(T paramT);
/*    */   
/*    */   default void onPacketDispatch(@Nullable EntityPlayer player) {}
/*    */   
/*    */   default void onPacketDispatchFinish(@Nullable EntityPlayer player, @Nullable ChannelFuture future) {}
/*    */   
/*    */   default boolean hasFinishListener() {
/* 26 */     return false;
/* 27 */   } default boolean isReady() { return true; } default List<Packet> getExtraPackets() {
/* 28 */     return null;
/*    */   } default boolean packetTooLarge(NetworkManager manager) {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   default boolean a() {
/* 35 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Packet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */