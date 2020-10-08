/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import co.aikar.timings.Timing;
/*    */ import io.netty.util.concurrent.Future;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.ConcurrentLinkedDeque;
/*    */ import java.util.concurrent.atomic.AtomicLong;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class PlayerConnectionUtils {
/* 10 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public static <T extends PacketListener> void ensureMainThread(Packet<T> packet, T t0, WorldServer worldserver) throws CancelledPacketHandleException {
/* 13 */     ensureMainThread(packet, t0, worldserver.getMinecraftServer());
/*    */   }
/*    */ 
/*    */   
/* 17 */   private static final ConcurrentLinkedDeque<PacketListener> packetProcessing = new ConcurrentLinkedDeque<>();
/* 18 */   private static final AtomicLong totalMainThreadPacketsProcessed = new AtomicLong();
/*    */   
/*    */   public static long getTotalProcessedPackets() {
/* 21 */     return totalMainThreadPacketsProcessed.get();
/*    */   }
/*    */   
/*    */   public static List<PacketListener> getCurrentPacketProcessors() {
/* 25 */     List<PacketListener> ret = new ArrayList<>(4);
/* 26 */     for (PacketListener listener : packetProcessing) {
/* 27 */       ret.add(listener);
/*    */     }
/*    */     
/* 30 */     return ret;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends PacketListener> void ensureMainThread(Packet<T> packet, T t0, IAsyncTaskHandler<?> iasynctaskhandler) throws CancelledPacketHandleException {
/* 35 */     if (!iasynctaskhandler.isMainThread()) {
/* 36 */       Timing timing = MinecraftTimings.getPacketTiming(packet);
/* 37 */       iasynctaskhandler.execute(() -> { packetProcessing.push(t0); try { if (MinecraftServer.getServer().hasStopped() || (t0 instanceof PlayerConnection && ((PlayerConnection)t0).processedDisconnect))
/*    */                 return;  if (t0.a().isConnected()) { try {
/*    */                   Timing ignored = timing.startTiming(); 
/*    */                   try { packet.a(t0); if (ignored != null)
/*    */                       ignored.close();  }
/* 42 */                   catch (Throwable throwable) { if (ignored != null) try { ignored.close(); } catch (Throwable throwable1)
/*    */                       { throwable.addSuppressed(throwable1); }
/*    */                         throw throwable; }
/*    */                 
/* 46 */                 } catch (Exception e) {
/*    */                   NetworkManager networkmanager = t0.a();
/*    */                   
/*    */                   if (networkmanager.getPlayer() != null) {
/*    */                     LOGGER.error("Error whilst processing packet {} for {}[{}]", packet, networkmanager.getPlayer().getName(), networkmanager.getSocketAddress(), e);
/*    */                   } else {
/*    */                     LOGGER.error("Error whilst processing packet {} for connection from {}", packet, networkmanager.getSocketAddress(), e);
/*    */                   } 
/*    */                   
/*    */                   ChatComponentText error = new ChatComponentText("Packet processing error");
/*    */                   
/*    */                   networkmanager.sendPacket(new PacketPlayOutKickDisconnect(error), ());
/*    */                   
/*    */                   networkmanager.stopReading();
/*    */                 }  }
/*    */               else
/*    */               { LOGGER.debug("Ignoring packet due to disconnection: " + packet); }
/*    */                }
/*    */             finally
/*    */             { totalMainThreadPacketsProcessed.getAndIncrement();
/*    */               
/*    */               packetProcessing.pop(); }
/*    */           
/*    */           });
/*    */       
/* 71 */       throw CancelledPacketHandleException.INSTANCE;
/*    */     } 
/*    */     
/* 74 */     if (MinecraftServer.getServer().hasStopped() || (t0 instanceof PlayerConnection && ((PlayerConnection)t0).processedDisconnect))
/* 75 */       throw CancelledPacketHandleException.INSTANCE; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerConnectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */