/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import io.netty.handler.codec.ByteToMessageDecoder;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.apache.logging.log4j.Marker;
/*    */ import org.apache.logging.log4j.MarkerManager;
/*    */ 
/*    */ 
/*    */ public class PacketDecoder
/*    */   extends ByteToMessageDecoder
/*    */ {
/* 17 */   private static final Logger LOGGER = LogManager.getLogger();
/* 18 */   private static final Marker b = MarkerManager.getMarker("PACKET_RECEIVED", NetworkManager.b);
/*    */   
/*    */   private final EnumProtocolDirection c;
/*    */   
/*    */   public PacketDecoder(EnumProtocolDirection var0) {
/* 23 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void decode(ChannelHandlerContext var0, ByteBuf var1, List<Object> var2) throws Exception {
/* 28 */     if (var1.readableBytes() == 0) {
/*    */       return;
/*    */     }
/*    */     
/* 32 */     PacketDataSerializer var3 = new PacketDataSerializer(var1);
/* 33 */     int var4 = var3.i();
/* 34 */     Packet<?> var5 = ((EnumProtocol)var0.channel().attr(NetworkManager.c).get()).a(this.c, var4);
/*    */     
/* 36 */     if (var5 == null) {
/* 37 */       throw new IOException("Bad packet id " + var4);
/*    */     }
/*    */     
/* 40 */     var5.a(var3);
/* 41 */     if (var3.readableBytes() > 0) {
/* 42 */       throw new IOException("Packet " + ((EnumProtocol)var0.channel().attr(NetworkManager.c).get()).a() + "/" + var4 + " (" + var5.getClass().getSimpleName() + ") was larger than I expected, found " + var3.readableBytes() + " bytes extra whilst reading packet " + var4);
/*    */     }
/* 44 */     var2.add(var5);
/*    */ 
/*    */     
/* 47 */     if (LOGGER.isDebugEnabled())
/* 48 */       LOGGER.debug(b, " IN: [{}:{}] {}", var0.channel().attr(NetworkManager.c).get(), Integer.valueOf(var4), var5.getClass().getName()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketDecoder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */