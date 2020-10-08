/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import io.netty.util.concurrent.Future;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class NetworkManagerServer
/*    */   extends NetworkManager
/*    */ {
/* 11 */   private static final Logger LOGGER = LogManager.getLogger();
/* 12 */   private static final IChatBaseComponent h = new ChatMessage("disconnect.exceeded_packet_rate");
/*    */   
/*    */   private final int i;
/*    */   
/*    */   public NetworkManagerServer(int var0) {
/* 17 */     super(EnumProtocolDirection.SERVERBOUND);
/* 18 */     this.i = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b() {
/* 23 */     super.b();
/*    */     
/* 25 */     float var0 = n();
/* 26 */     if (var0 > this.i) {
/* 27 */       LOGGER.warn("Player exceeded rate-limit (sent {} packets per second)", Float.valueOf(var0));
/*    */       
/* 29 */       sendPacket(new PacketPlayOutKickDisconnect(h), var0 -> close(h));
/* 30 */       stopReading();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NetworkManagerServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */