/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import io.netty.bootstrap.ServerBootstrap;
/*     */ import io.netty.channel.Channel;
/*     */ import io.netty.channel.ChannelException;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelInitializer;
/*     */ import io.netty.channel.ChannelOption;
/*     */ import io.netty.channel.EventLoopGroup;
/*     */ import io.netty.channel.epoll.Epoll;
/*     */ import io.netty.channel.epoll.EpollEventLoopGroup;
/*     */ import io.netty.channel.epoll.EpollServerSocketChannel;
/*     */ import io.netty.channel.nio.NioEventLoopGroup;
/*     */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*     */ import io.netty.handler.flush.FlushConsolidationHandler;
/*     */ import io.netty.handler.timeout.ReadTimeoutHandler;
/*     */ import io.netty.util.concurrent.Future;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class ServerConnection {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger();
/*  33 */   public static final LazyInitVar<NioEventLoopGroup> a = new LazyInitVar<>(() -> new NioEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Server IO #%d").setDaemon(true).build()));
/*     */ 
/*     */   
/*  36 */   public static final LazyInitVar<EpollEventLoopGroup> b = new LazyInitVar<>(() -> new EpollEventLoopGroup(0, (new ThreadFactoryBuilder()).setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build()));
/*     */   
/*     */   private final MinecraftServer e;
/*     */   
/*     */   public volatile boolean c;
/*  41 */   private final List<ChannelFuture> listeningChannels = Collections.synchronizedList(Lists.newArrayList());
/*  42 */   private final List<NetworkManager> connectedChannels = Collections.synchronizedList(Lists.newArrayList());
/*     */   
/*  44 */   private final Queue<NetworkManager> pending = new ConcurrentLinkedQueue<>();
/*  45 */   private static final boolean disableFlushConsolidation = Boolean.getBoolean("Paper.disableFlushConsolidate");
/*     */   private void addPending() {
/*  47 */     NetworkManager manager = null;
/*  48 */     while ((manager = this.pending.poll()) != null) {
/*  49 */       this.connectedChannels.add(manager);
/*  50 */       manager.isPending = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ServerConnection(MinecraftServer minecraftserver) {
/*  56 */     this.e = minecraftserver;
/*  57 */     this.c = true;
/*     */   }
/*     */   
/*     */   public void a(@Nullable InetAddress inetaddress, int i) throws IOException {
/*  61 */     List<ChannelFuture> list = this.listeningChannels;
/*     */     
/*  63 */     synchronized (this.listeningChannels) {
/*     */       Class<NioServerSocketChannel> oclass;
/*     */       
/*     */       LazyInitVar<NioEventLoopGroup> lazyinitvar;
/*  67 */       if (Epoll.isAvailable() && this.e.l()) {
/*  68 */         Class<EpollServerSocketChannel> clazz = EpollServerSocketChannel.class;
/*  69 */         LazyInitVar<EpollEventLoopGroup> lazyInitVar = b;
/*  70 */         LOGGER.info("Using epoll channel type");
/*     */       } else {
/*  72 */         oclass = NioServerSocketChannel.class;
/*  73 */         lazyinitvar = a;
/*  74 */         LOGGER.info("Using default channel type");
/*     */       } 
/*     */       
/*  77 */       this.listeningChannels.add(((ServerBootstrap)((ServerBootstrap)((ServerBootstrap)(new ServerBootstrap()).channel(oclass)).childHandler((ChannelHandler)new ChannelInitializer<Channel>() {
/*     */               protected void initChannel(Channel channel) throws Exception {
/*     */                 try {
/*  80 */                   channel.config().setOption(ChannelOption.TCP_NODELAY, Boolean.valueOf(true));
/*  81 */                 } catch (ChannelException channelException) {}
/*     */ 
/*     */ 
/*     */                 
/*  85 */                 if (!ServerConnection.disableFlushConsolidation) channel.pipeline().addFirst(new ChannelHandler[] { (ChannelHandler)new FlushConsolidationHandler() }); 
/*  86 */                 channel.pipeline().addLast("timeout", (ChannelHandler)new ReadTimeoutHandler(30)).addLast("legacy_query", (ChannelHandler)new LegacyPingHandler(ServerConnection.this)).addLast("splitter", (ChannelHandler)new PacketSplitter()).addLast("decoder", (ChannelHandler)new PacketDecoder(EnumProtocolDirection.SERVERBOUND)).addLast("prepender", (ChannelHandler)new PacketPrepender()).addLast("encoder", (ChannelHandler)new PacketEncoder(EnumProtocolDirection.CLIENTBOUND));
/*  87 */                 int j = ServerConnection.this.e.k();
/*  88 */                 Object object = (j > 0) ? new NetworkManagerServer(j) : new NetworkManager(EnumProtocolDirection.SERVERBOUND);
/*     */ 
/*     */                 
/*  91 */                 ServerConnection.this.pending.add((NetworkManager)object);
/*  92 */                 channel.pipeline().addLast("packet_handler", (ChannelHandler)object);
/*  93 */                 ((NetworkManager)object).setPacketListener(new HandshakeListener(ServerConnection.this.e, (NetworkManager)object));
/*     */               }
/*  95 */             }).group((EventLoopGroup)lazyinitvar.a()).localAddress(inetaddress, i)).option(ChannelOption.AUTO_READ, Boolean.valueOf(false))).bind().syncUninterruptibly());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void acceptConnections() {
/* 101 */     synchronized (this.listeningChannels) {
/* 102 */       for (ChannelFuture future : this.listeningChannels) {
/* 103 */         future.channel().config().setAutoRead(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/* 110 */     this.c = false;
/* 111 */     Iterator<ChannelFuture> iterator = this.listeningChannels.iterator();
/*     */     
/* 113 */     while (iterator.hasNext()) {
/* 114 */       ChannelFuture channelfuture = iterator.next();
/*     */       
/*     */       try {
/* 117 */         channelfuture.channel().close().sync();
/* 118 */       } catch (InterruptedException interruptedexception) {
/* 119 */         LOGGER.error("Interrupted whilst closing channel");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/* 126 */     List<NetworkManager> list = this.connectedChannels;
/*     */     
/* 128 */     synchronized (this.connectedChannels) {
/*     */       
/* 130 */       addPending();
/*     */       
/* 132 */       if (SpigotConfig.playerShuffle > 0 && MinecraftServer.currentTick % SpigotConfig.playerShuffle == 0)
/*     */       {
/* 134 */         Collections.shuffle(this.connectedChannels);
/*     */       }
/*     */       
/* 137 */       Iterator<NetworkManager> iterator = this.connectedChannels.iterator();
/*     */       
/* 139 */       while (iterator.hasNext()) {
/* 140 */         NetworkManager networkmanager = iterator.next();
/*     */         
/* 142 */         if (!networkmanager.i()) {
/* 143 */           if (networkmanager.isConnected()) {
/*     */             try {
/* 145 */               networkmanager.a();
/* 146 */             } catch (Exception exception) {
/* 147 */               if (networkmanager.isLocal()) {
/* 148 */                 throw new ReportedException(CrashReport.a(exception, "Ticking memory connection"));
/*     */               }
/*     */               
/* 151 */               LOGGER.warn("Failed to handle packet for {}", networkmanager.getSocketAddress(), exception);
/* 152 */               ChatComponentText chatcomponenttext = new ChatComponentText("Internal server error");
/*     */               
/* 154 */               networkmanager.sendPacket(new PacketPlayOutKickDisconnect(chatcomponenttext), future -> networkmanager.close(chatcomponenttext));
/*     */ 
/*     */               
/* 157 */               networkmanager.stopReading();
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/* 162 */           if (networkmanager.preparing)
/*     */             continue; 
/* 164 */           iterator.remove();
/* 165 */           networkmanager.handleDisconnection();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MinecraftServer d() {
/* 174 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ServerConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */