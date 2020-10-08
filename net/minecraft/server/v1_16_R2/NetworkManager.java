/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.DefaultEventLoopGroup;
/*     */ import io.netty.channel.SimpleChannelInboundHandler;
/*     */ import io.netty.channel.epoll.EpollEventLoopGroup;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.util.AttributeKey;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketAddress;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.crypto.SecretKey;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ 
/*     */ public class NetworkManager extends SimpleChannelInboundHandler<Packet<?>> {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger();
/*  33 */   public static final Marker a = MarkerManager.getMarker("NETWORK");
/*  34 */   public static final Marker b = MarkerManager.getMarker("NETWORK_PACKETS", a);
/*  35 */   public static final AttributeKey<EnumProtocol> c = AttributeKey.valueOf("protocol");
/*  36 */   public static final LazyInitVar<NioEventLoopGroup> d = new LazyInitVar<>(() -> new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Client IO #%d").setDaemon(true).build()));
/*     */ 
/*     */   
/*  39 */   public static final LazyInitVar<EpollEventLoopGroup> e = new LazyInitVar<>(() -> new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build()));
/*     */ 
/*     */   
/*  42 */   public static final LazyInitVar<DefaultEventLoopGroup> f = new LazyInitVar<>(() -> new DefaultEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Local Client IO #%d").setDaemon(true).build()));
/*     */   
/*     */   private final EnumProtocolDirection h;
/*     */   
/*  46 */   private final Queue<QueuedPacket> packetQueue = Queues.newConcurrentLinkedQueue();
/*     */   
/*     */   public Channel channel;
/*     */   
/*     */   public SocketAddress socketAddress;
/*     */   
/*     */   public UUID spoofedUUID;
/*     */   public Property[] spoofedProfile;
/*     */   public boolean preparing = true;
/*     */   private PacketListener packetListener;
/*     */   private IChatBaseComponent m;
/*     */   private boolean n;
/*     */   private boolean o;
/*     */   private int p;
/*     */   private int q;
/*     */   private float r;
/*     */   private float s;
/*     */   private int t;
/*     */   private boolean u;
/*     */   public int protocolVersion;
/*     */   public InetSocketAddress virtualHost;
/*  67 */   private static boolean enableExplicitFlush = Boolean.getBoolean("paper.explicit-flush");
/*     */   
/*     */   boolean isPending = true;
/*     */   
/*     */   boolean queueImmunity = false;
/*     */   
/*     */   EnumProtocol protocol;
/*     */   
/*     */   volatile boolean canFlush = true;
/*  76 */   private final AtomicInteger packetWrites = new AtomicInteger();
/*     */   private int flushPacketsStart;
/*  78 */   private final Object flushLock = new Object();
/*     */   
/*     */   void disableAutomaticFlush() {
/*  81 */     synchronized (this.flushLock) {
/*  82 */       this.flushPacketsStart = this.packetWrites.get();
/*  83 */       this.canFlush = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   void enableAutomaticFlush() {
/*  88 */     synchronized (this.flushLock) {
/*  89 */       this.canFlush = true;
/*  90 */       if (this.packetWrites.get() != this.flushPacketsStart) {
/*  91 */         flush();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private final void flush() {
/*  97 */     if (this.channel.eventLoop().inEventLoop()) {
/*  98 */       this.channel.flush();
/*     */     } else {
/* 100 */       this.channel.eventLoop().execute(() -> this.channel.flush());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NetworkManager(EnumProtocolDirection enumprotocoldirection) {
/* 108 */     this.h = enumprotocoldirection;
/*     */   }
/*     */   
/*     */   public void channelActive(ChannelHandlerContext channelhandlercontext) throws Exception {
/* 112 */     super.channelActive(channelhandlercontext);
/* 113 */     this.channel = channelhandlercontext.channel();
/* 114 */     this.socketAddress = this.channel.remoteAddress();
/*     */     
/* 116 */     this.preparing = false;
/*     */ 
/*     */     
/*     */     try {
/* 120 */       setProtocol(EnumProtocol.HANDSHAKING);
/* 121 */     } catch (Throwable throwable) {
/* 122 */       LOGGER.fatal(throwable);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProtocol(EnumProtocol enumprotocol) {
/* 128 */     this.protocol = enumprotocol;
/* 129 */     this.channel.attr(c).set(enumprotocol);
/* 130 */     this.channel.config().setAutoRead(true);
/* 131 */     LOGGER.debug("Enabled auto read");
/*     */   }
/*     */   
/*     */   public void channelInactive(ChannelHandlerContext channelhandlercontext) throws Exception {
/* 135 */     close(new ChatMessage("disconnect.endOfStream"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void exceptionCaught(ChannelHandlerContext channelhandlercontext, Throwable throwable) {
/* 140 */     if (throwable instanceof io.netty.handler.codec.EncoderException && throwable.getCause() instanceof PacketEncoder.PacketTooLargeException) {
/* 141 */       if (((PacketEncoder.PacketTooLargeException)throwable.getCause()).getPacket().packetTooLarge(this)) {
/*     */         return;
/*     */       }
/* 144 */       throwable = throwable.getCause();
/*     */     } 
/*     */ 
/*     */     
/* 148 */     if (throwable instanceof SkipEncodeException) {
/* 149 */       LOGGER.debug("Skipping packet due to errors", throwable.getCause());
/*     */     } else {
/* 151 */       boolean flag = !this.u;
/*     */       
/* 153 */       this.u = true;
/* 154 */       if (this.channel.isOpen()) {
/* 155 */         if (throwable instanceof io.netty.handler.timeout.TimeoutException) {
/* 156 */           LOGGER.debug("Timeout", throwable);
/* 157 */           close(new ChatMessage("disconnect.timeout"));
/*     */         } else {
/* 159 */           ChatMessage chatmessage = new ChatMessage("disconnect.genericReason", new Object[] { "Internal Exception: " + throwable });
/*     */           
/* 161 */           if (flag) {
/* 162 */             LOGGER.debug("Failed to sent packet", throwable);
/* 163 */             sendPacket(new PacketPlayOutKickDisconnect(chatmessage), future -> close(chatmessage));
/*     */ 
/*     */             
/* 166 */             stopReading();
/*     */           } else {
/* 168 */             LOGGER.debug("Double fault", throwable);
/* 169 */             close(chatmessage);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 175 */     if (MinecraftServer.getServer().isDebugging()) throwable.printStackTrace(); 
/*     */   }
/*     */   
/*     */   protected void channelRead0(ChannelHandlerContext channelhandlercontext, Packet<?> packet) throws Exception {
/* 179 */     if (this.channel.isOpen()) {
/*     */       try {
/* 181 */         a(packet, this.packetListener);
/* 182 */       } catch (CancelledPacketHandleException cancelledPacketHandleException) {}
/*     */ 
/*     */ 
/*     */       
/* 186 */       this.p++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static <T extends PacketListener> void a(Packet<T> packet, PacketListener packetlistener) {
/* 192 */     packet.a((T)packetlistener);
/*     */   }
/*     */   
/*     */   public void setPacketListener(PacketListener packetlistener) {
/* 196 */     Validate.notNull(packetlistener, "packetListener", new Object[0]);
/* 197 */     this.packetListener = packetlistener;
/*     */   }
/*     */   
/*     */   EntityPlayer getPlayer() {
/* 201 */     if (this.packetListener instanceof PlayerConnection) {
/* 202 */       return ((PlayerConnection)this.packetListener).player;
/*     */     }
/* 204 */     return null;
/*     */   }
/*     */   
/*     */   private static class InnerUtil {
/*     */     private static List<Packet> buildExtraPackets(Packet packet) {
/* 209 */       List<Packet> extra = packet.getExtraPackets();
/* 210 */       if (extra == null || extra.isEmpty()) {
/* 211 */         return null;
/*     */       }
/* 213 */       List<Packet> ret = new ArrayList<>(1 + extra.size());
/* 214 */       buildExtraPackets0(extra, ret);
/* 215 */       return ret;
/*     */     }
/*     */     
/*     */     private static void buildExtraPackets0(List<Packet> extraPackets, List<Packet> into) {
/* 219 */       for (Packet extra : extraPackets) {
/* 220 */         into.add(extra);
/* 221 */         List<Packet> extraExtra = extra.getExtraPackets();
/* 222 */         if (extraExtra != null && !extraExtra.isEmpty()) {
/* 223 */           buildExtraPackets0(extraExtra, into);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*     */     private static boolean canSendImmediate(NetworkManager networkManager, Packet<?> packet) {
/* 229 */       return (networkManager.isPending || networkManager.protocol != EnumProtocol.PLAY || packet instanceof PacketPlayOutKeepAlive || packet instanceof PacketPlayOutChat || packet instanceof PacketPlayOutTabComplete);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet<?> packet) {
/* 239 */     sendPacket(packet, (GenericFutureListener<? extends Future<? super Void>>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener) {
/* 244 */     boolean connected = isConnected();
/* 245 */     if (!connected && !this.preparing) {
/*     */       return;
/*     */     }
/* 248 */     packet.onPacketDispatch(getPlayer());
/* 249 */     if (connected && (InnerUtil.canSendImmediate(this, packet) || (
/* 250 */       MCUtil.isMainThread() && packet.isReady() && this.packetQueue.isEmpty() && (packet
/* 251 */       .getExtraPackets() == null || packet.getExtraPackets().isEmpty())))) {
/*     */       
/* 253 */       writePacket(packet, genericfuturelistener, null);
/*     */       
/*     */       return;
/*     */     } 
/* 257 */     List<Packet> extraPackets = InnerUtil.buildExtraPackets(packet);
/* 258 */     boolean hasExtraPackets = (extraPackets != null && !extraPackets.isEmpty());
/* 259 */     if (!hasExtraPackets) {
/* 260 */       this.packetQueue.add(new QueuedPacket(packet, genericfuturelistener));
/*     */     } else {
/* 262 */       List<QueuedPacket> packets = new ArrayList<>(1 + extraPackets.size());
/* 263 */       packets.add(new QueuedPacket(packet, null));
/*     */       
/* 265 */       for (int i = 0, len = extraPackets.size(); i < len; ) {
/* 266 */         Packet<?> extra = extraPackets.get(i);
/* 267 */         boolean end = (++i == len);
/* 268 */         packets.add(new QueuedPacket(extra, end ? genericfuturelistener : null));
/*     */       } 
/*     */       
/* 271 */       this.packetQueue.addAll(packets);
/*     */     } 
/* 273 */     sendPacketQueue();
/*     */   }
/*     */   
/*     */   private void dispatchPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericFutureListener) {
/* 277 */     b(packet, genericFutureListener);
/*     */   }
/*     */   private void b(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener) {
/* 280 */     writePacket(packet, genericfuturelistener, Boolean.TRUE);
/*     */   }
/*     */   private void writePacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener, Boolean flushConditional) {
/* 283 */     this.packetWrites.getAndIncrement();
/* 284 */     boolean effectiveFlush = (flushConditional == null) ? this.canFlush : flushConditional.booleanValue();
/* 285 */     boolean flush = (effectiveFlush || packet instanceof PacketPlayOutKeepAlive || packet instanceof PacketPlayOutKickDisconnect);
/*     */     
/* 287 */     EnumProtocol enumprotocol = EnumProtocol.a(packet);
/* 288 */     EnumProtocol enumprotocol1 = (EnumProtocol)this.channel.attr(c).get();
/*     */     
/* 290 */     this.q++;
/* 291 */     if (enumprotocol1 != enumprotocol) {
/* 292 */       LOGGER.debug("Disabled auto read");
/* 293 */       this.channel.config().setAutoRead(false);
/*     */     } 
/*     */     
/* 296 */     EntityPlayer player = getPlayer();
/* 297 */     if (this.channel.eventLoop().inEventLoop()) {
/* 298 */       if (enumprotocol != enumprotocol1) {
/* 299 */         setProtocol(enumprotocol);
/*     */       }
/*     */       
/* 302 */       if (!isConnected()) {
/* 303 */         packet.onPacketDispatchFinish(player, null);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*     */       try {
/* 309 */         ChannelFuture channelfuture = flush ? this.channel.writeAndFlush(packet) : this.channel.write(packet);
/*     */         
/* 311 */         if (genericfuturelistener != null) {
/* 312 */           channelfuture.addListener(genericfuturelistener);
/*     */         }
/*     */         
/* 315 */         if (packet.hasFinishListener()) {
/* 316 */           channelfuture.addListener((GenericFutureListener)(channelFuture -> packet.onPacketDispatchFinish(player, channelFuture)));
/*     */         }
/*     */ 
/*     */         
/* 320 */         channelfuture.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/*     */       }
/* 322 */       catch (Exception e) {
/* 323 */         LOGGER.error("NetworkException: " + player, e);
/* 324 */         close(new ChatMessage("disconnect.genericReason", new Object[] { "Internal Exception: " + e.getMessage() }));
/* 325 */         packet.onPacketDispatchFinish(player, null);
/*     */       } 
/*     */     } else {
/*     */       
/* 329 */       this.channel.eventLoop().execute(() -> {
/*     */             if (enumprotocol != enumprotocol1) {
/*     */               setProtocol(enumprotocol);
/*     */             }
/*     */ 
/*     */             
/*     */             if (!isConnected()) {
/*     */               packet.onPacketDispatchFinish(player, null);
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */ 
/*     */             
/*     */             try {
/*     */               ChannelFuture channelfuture1 = flush ? this.channel.writeAndFlush(packet) : this.channel.write(packet);
/*     */               
/*     */               if (genericfuturelistener != null) {
/*     */                 channelfuture1.addListener(genericfuturelistener);
/*     */               }
/*     */               
/*     */               if (packet.hasFinishListener()) {
/*     */                 channelfuture1.addListener((GenericFutureListener)(()));
/*     */               }
/*     */               
/*     */               channelfuture1.addListener((GenericFutureListener)ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
/* 355 */             } catch (Exception e) {
/*     */               LOGGER.error("NetworkException: " + player, e);
/*     */               close(new ChatMessage("disconnect.genericReason", new Object[] { "Internal Exception: " + e.getMessage() }));
/*     */               packet.onPacketDispatchFinish(player, null);
/*     */             } 
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean sendPacketQueue() {
/* 367 */     return p();
/*     */   } private boolean p() {
/* 369 */     if (!isConnected()) {
/* 370 */       return true;
/*     */     }
/* 372 */     if (MCUtil.isMainThread())
/* 373 */       return processQueue(); 
/* 374 */     if (this.isPending)
/*     */     {
/* 376 */       synchronized (this.packetQueue) {
/* 377 */         return processQueue();
/*     */       } 
/*     */     }
/* 380 */     return false;
/*     */   }
/*     */   private boolean processQueue() {
/* 383 */     if (this.packetQueue.isEmpty()) return true; 
/* 384 */     boolean needsFlush = this.canFlush;
/* 385 */     boolean hasWrotePacket = false;
/*     */ 
/*     */     
/* 388 */     Iterator<QueuedPacket> iterator = this.packetQueue.iterator();
/* 389 */     while (iterator.hasNext()) {
/* 390 */       QueuedPacket queued = iterator.next();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 397 */       Packet<?> packet = queued.getPacket();
/* 398 */       if (!packet.isReady()) {
/*     */         
/* 400 */         if (hasWrotePacket && (needsFlush || this.canFlush)) {
/* 401 */           flush();
/*     */         }
/*     */         
/* 404 */         return false;
/*     */       } 
/* 406 */       iterator.remove();
/* 407 */       writePacket(packet, queued.getGenericFutureListener(), (!iterator.hasNext() && (needsFlush || this.canFlush)) ? Boolean.TRUE : Boolean.FALSE);
/* 408 */       hasWrotePacket = true;
/*     */     } 
/*     */     
/* 411 */     return true;
/*     */   }
/*     */ 
/*     */   
/* 415 */   private static final int MAX_PER_TICK = PaperConfig.maxJoinsPerTick; private static int joinAttemptsThisTick;
/*     */   private static int currTick;
/*     */   
/*     */   public void a() {
/* 419 */     p();
/*     */     
/* 421 */     if (currTick != MinecraftServer.currentTick) {
/* 422 */       currTick = MinecraftServer.currentTick;
/* 423 */       joinAttemptsThisTick = 0;
/*     */     } 
/*     */     
/* 426 */     if (this.packetListener instanceof LoginListener && ((
/* 427 */       (LoginListener)this.packetListener).getLoginState() != LoginListener.EnumProtocolState.READY_TO_ACCEPT || joinAttemptsThisTick++ < MAX_PER_TICK))
/*     */     {
/* 429 */       ((LoginListener)this.packetListener).tick();
/*     */     }
/*     */ 
/*     */     
/* 433 */     if (this.packetListener instanceof PlayerConnection) {
/* 434 */       ((PlayerConnection)this.packetListener).tick();
/*     */     }
/*     */     
/* 437 */     if (this.channel != null && 
/* 438 */       enableExplicitFlush) this.channel.eventLoop().execute(() -> this.channel.flush());
/*     */ 
/*     */     
/* 441 */     if (this.t++ % 20 == 0) {
/* 442 */       b();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b() {
/* 448 */     this.s = MathHelper.g(0.75F, this.q, this.s);
/* 449 */     this.r = MathHelper.g(0.75F, this.p, this.r);
/* 450 */     this.q = 0;
/* 451 */     this.p = 0;
/*     */   }
/*     */   
/*     */   public SocketAddress getSocketAddress() {
/* 455 */     return this.socketAddress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearPacketQueue() {
/* 460 */     EntityPlayer player = getPlayer();
/* 461 */     this.packetQueue.forEach(queuedPacket -> {
/*     */           Packet<?> packet = queuedPacket.getPacket();
/*     */           if (packet.hasFinishListener()) {
/*     */             packet.onPacketDispatchFinish(player, null);
/*     */           }
/*     */         });
/* 467 */     this.packetQueue.clear();
/*     */   }
/*     */   
/*     */   public void close(IChatBaseComponent ichatbasecomponent) {
/* 471 */     this.preparing = false;
/* 472 */     clearPacketQueue();
/*     */     
/* 474 */     if (this.channel.isOpen()) {
/* 475 */       this.channel.close();
/* 476 */       this.m = ichatbasecomponent;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocal() {
/* 482 */     return (this.channel instanceof io.netty.channel.local.LocalChannel || this.channel instanceof io.netty.channel.local.LocalServerChannel);
/*     */   }
/*     */   
/*     */   public void a(SecretKey secretkey) {
/* 486 */     this.n = true;
/* 487 */     this.channel.pipeline().addBefore("splitter", "decrypt", (ChannelHandler)new PacketDecrypter(MinecraftEncryption.a(2, secretkey)));
/* 488 */     this.channel.pipeline().addBefore("prepender", "encrypt", (ChannelHandler)new PacketEncrypter(MinecraftEncryption.a(1, secretkey)));
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/* 492 */     return (this.channel != null && this.channel.isOpen());
/*     */   }
/*     */   
/*     */   public boolean i() {
/* 496 */     return (this.channel == null);
/*     */   }
/*     */   
/*     */   public PacketListener j() {
/* 500 */     return this.packetListener;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IChatBaseComponent k() {
/* 505 */     return this.m;
/*     */   }
/*     */   
/*     */   public void stopReading() {
/* 509 */     this.channel.config().setAutoRead(false);
/*     */   }
/*     */   
/*     */   public void setCompressionLevel(int i) {
/* 513 */     if (i >= 0) {
/* 514 */       if (this.channel.pipeline().get("decompress") instanceof PacketDecompressor) {
/* 515 */         ((PacketDecompressor)this.channel.pipeline().get("decompress")).a(i);
/*     */       } else {
/* 517 */         this.channel.pipeline().addBefore("decoder", "decompress", (ChannelHandler)new PacketDecompressor(i));
/*     */       } 
/*     */       
/* 520 */       if (this.channel.pipeline().get("compress") instanceof PacketCompressor) {
/* 521 */         ((PacketCompressor)this.channel.pipeline().get("compress")).a(i);
/*     */       } else {
/* 523 */         this.channel.pipeline().addBefore("encoder", "compress", (ChannelHandler)new PacketCompressor(i));
/*     */       } 
/*     */     } else {
/* 526 */       if (this.channel.pipeline().get("decompress") instanceof PacketDecompressor) {
/* 527 */         this.channel.pipeline().remove("decompress");
/*     */       }
/*     */       
/* 530 */       if (this.channel.pipeline().get("compress") instanceof PacketCompressor) {
/* 531 */         this.channel.pipeline().remove("compress");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDisconnection() {
/* 538 */     if (this.channel != null && !this.channel.isOpen() && 
/* 539 */       !this.o) {
/*     */ 
/*     */       
/* 542 */       this.o = true;
/* 543 */       if (k() != null) {
/* 544 */         j().a(k());
/* 545 */       } else if (j() != null) {
/* 546 */         j().a(new ChatMessage("multiplayer.disconnect.generic"));
/*     */       } 
/* 548 */       clearPacketQueue();
/*     */       
/* 550 */       PacketListener packetListener = j();
/* 551 */       if (packetListener instanceof PlayerConnection) {
/*     */         
/* 553 */         PlayerConnection playerConnection = (PlayerConnection)packetListener;
/* 554 */         (new PlayerConnectionCloseEvent(playerConnection.player.uniqueID, playerConnection.player
/* 555 */             .getName(), ((InetSocketAddress)this.socketAddress).getAddress(), false)).callEvent();
/* 556 */       } else if (packetListener instanceof LoginListener) {
/*     */         GameProfile profile;
/* 558 */         LoginListener loginListener = (LoginListener)packetListener;
/* 559 */         switch (loginListener.getLoginState()) {
/*     */           case READY_TO_ACCEPT:
/*     */           case DELAY_ACCEPT:
/*     */           case ACCEPTED:
/* 563 */             profile = loginListener.getGameProfile();
/* 564 */             (new PlayerConnectionCloseEvent(profile.getId(), profile.getName(), ((InetSocketAddress)this.socketAddress)
/* 565 */                 .getAddress(), false)).callEvent();
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float n() {
/* 575 */     return this.r;
/*     */   }
/*     */   static class QueuedPacket { private final Packet<?> a; @Nullable
/*     */     private final GenericFutureListener<? extends Future<? super Void>> b;
/*     */     private final Packet<?> getPacket() {
/* 580 */       return this.a;
/*     */     } private final GenericFutureListener<? extends Future<? super Void>> getGenericFutureListener() {
/* 582 */       return this.b;
/*     */     }
/*     */     public QueuedPacket(Packet<?> packet, @Nullable GenericFutureListener<? extends Future<? super Void>> genericfuturelistener) {
/* 585 */       this.a = packet;
/* 586 */       this.b = genericfuturelistener;
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SocketAddress getRawAddress() {
/* 593 */     return this.channel.remoteAddress();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NetworkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */