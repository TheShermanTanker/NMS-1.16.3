/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.MessageToByteEncoder;
/*    */ import java.io.IOException;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ public class PacketEncoder
/*    */   extends MessageToByteEncoder<Packet<?>> {
/* 14 */   private static final Logger LOGGER = LogManager.getLogger();
/* 15 */   private static final Marker b = MarkerManager.getMarker("PACKET_SENT", NetworkManager.b);
/*    */   private final EnumProtocolDirection c;
/*    */   
/*    */   public PacketEncoder(EnumProtocolDirection enumprotocoldirection) {
/* 19 */     this.c = enumprotocoldirection;
/*    */   }
/*    */   
/*    */   protected void encode(ChannelHandlerContext channelhandlercontext, Packet<?> packet, ByteBuf bytebuf) throws Exception {
/* 23 */     EnumProtocol enumprotocol = (EnumProtocol)channelhandlercontext.channel().attr(NetworkManager.c).get();
/*    */     
/* 25 */     if (enumprotocol == null) {
/* 26 */       throw new RuntimeException("ConnectionProtocol unknown: " + packet);
/*    */     }
/* 28 */     Integer integer = enumprotocol.a(this.c, packet);
/*    */     
/* 30 */     if (LOGGER.isDebugEnabled()) {
/* 31 */       LOGGER.debug(b, "OUT: [{}:{}] {}", channelhandlercontext.channel().attr(NetworkManager.c).get(), integer, packet.getClass().getName());
/*    */     }
/*    */     
/* 34 */     if (integer == null) {
/* 35 */       throw new IOException("Can't serialize unregistered packet");
/*    */     }
/* 37 */     PacketDataSerializer packetdataserializer = new PacketDataSerializer(bytebuf);
/*    */     
/* 39 */     packetdataserializer.d(integer.intValue());
/*    */     
/*    */     try {
/* 42 */       packet.b(packetdataserializer);
/* 43 */     } catch (Throwable throwable) {
/* 44 */       LOGGER.error(throwable);
/* 45 */       throwable.printStackTrace();
/* 46 */       if (packet.a()) {
/* 47 */         throw new SkipEncodeException(throwable);
/*    */       }
/* 49 */       throw throwable;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 54 */     int packetLength = bytebuf.readableBytes();
/* 55 */     if (packetLength > MAX_PACKET_SIZE) {
/* 56 */       throw new PacketTooLargeException(packet, packetLength);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   private static int MAX_PACKET_SIZE = 2097152;
/*    */   
/*    */   public static class PacketTooLargeException extends RuntimeException {
/*    */     private final Packet<?> packet;
/*    */     
/*    */     PacketTooLargeException(Packet<?> packet, int packetLength) {
/* 70 */       super("PacketTooLarge - " + packet.getClass().getSimpleName() + " is " + packetLength + ". Max is " + PacketEncoder.MAX_PACKET_SIZE);
/* 71 */       this.packet = packet;
/*    */     }
/*    */     
/*    */     public Packet<?> getPacket() {
/* 75 */       return this.packet;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketEncoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */