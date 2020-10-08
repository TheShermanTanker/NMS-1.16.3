/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
/*     */ import com.destroystokyo.paper.network.PaperLegacyStatusClient;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelFutureListener;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.channel.ChannelInboundHandlerAdapter;
/*     */ import io.netty.util.concurrent.GenericFutureListener;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class LegacyPingHandler extends ChannelInboundHandlerAdapter {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final ServerConnection b;
/*     */   private ByteBuf buf;
/*     */   
/*     */   public LegacyPingHandler(ServerConnection serverconnection) {
/*  22 */     this.b = serverconnection;
/*     */   }
/*     */   
/*     */   public void channelRead(ChannelHandlerContext channelhandlercontext, Object object) throws Exception {
/*  26 */     ByteBuf bytebuf = (ByteBuf)object;
/*     */ 
/*     */     
/*  29 */     if (this.buf != null) {
/*     */       try {
/*  31 */         readLegacy1_6(channelhandlercontext, bytebuf);
/*     */       } finally {
/*  33 */         bytebuf.release();
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  38 */     bytebuf.markReaderIndex();
/*  39 */     boolean flag = true; try {
/*     */       String s;
/*     */       PaperServerListPingEvent event;
/*  42 */       if (bytebuf.readUnsignedByte() != 254) {
/*     */         return;
/*     */       }
/*     */       
/*  46 */       InetSocketAddress inetsocketaddress = (InetSocketAddress)channelhandlercontext.channel().remoteAddress();
/*  47 */       MinecraftServer minecraftserver = this.b.d();
/*  48 */       int i = bytebuf.readableBytes();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       switch (i) {
/*     */         case 0:
/*  55 */           LOGGER.debug("Ping: (<1.3.x) from {}:{}", inetsocketaddress.getAddress(), Integer.valueOf(inetsocketaddress.getPort()));
/*     */           
/*  57 */           event = PaperLegacyStatusClient.processRequest(minecraftserver, inetsocketaddress, 39, null);
/*  58 */           if (event == null) {
/*  59 */             channelhandlercontext.close();
/*     */             break;
/*     */           } 
/*  62 */           s = String.format("%s§%d§%d", new Object[] { PaperLegacyStatusClient.getUnformattedMotd(event), Integer.valueOf(event.getNumPlayers()), Integer.valueOf(event.getMaxPlayers()) });
/*  63 */           a(channelhandlercontext, a(s));
/*     */           break;
/*     */         case 1:
/*  66 */           if (bytebuf.readUnsignedByte() != 1) {
/*     */             return;
/*     */           }
/*     */           
/*  70 */           LOGGER.debug("Ping: (1.4-1.5.x) from {}:{}", inetsocketaddress.getAddress(), Integer.valueOf(inetsocketaddress.getPort()));
/*     */           
/*  72 */           event = PaperLegacyStatusClient.processRequest(minecraftserver, inetsocketaddress, 127, null);
/*  73 */           if (event == null) {
/*  74 */             channelhandlercontext.close();
/*     */             break;
/*     */           } 
/*  77 */           s = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(event.getProtocolVersion()), minecraftserver.getVersion(), event.getMotd(), Integer.valueOf(event.getNumPlayers()), Integer.valueOf(event.getMaxPlayers()) });
/*     */           
/*  79 */           a(channelhandlercontext, a(s));
/*     */           break;
/*     */         
/*     */         default:
/*  83 */           if (bytebuf.readUnsignedByte() != 1 || bytebuf.readUnsignedByte() != 250)
/*  84 */             return;  readLegacy1_6(channelhandlercontext, bytebuf);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       bytebuf.release();
/* 113 */       flag = false;
/* 114 */     } catch (RuntimeException runtimeException) {
/*     */     
/*     */     } finally {
/* 117 */       if (flag) {
/* 118 */         bytebuf.resetReaderIndex();
/* 119 */         channelhandlercontext.channel().pipeline().remove("legacy_query");
/* 120 */         channelhandlercontext.fireChannelRead(object);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String readLegacyString(ByteBuf buf) {
/* 129 */     int size = buf.readShort() * 2;
/* 130 */     if (!buf.isReadable(size)) {
/* 131 */       return null;
/*     */     }
/*     */     
/* 134 */     String result = buf.toString(buf.readerIndex(), size, StandardCharsets.UTF_16BE);
/* 135 */     buf.skipBytes(size);
/* 136 */     return result;
/*     */   }
/*     */   
/*     */   private void readLegacy1_6(ChannelHandlerContext ctx, ByteBuf part) {
/* 140 */     ByteBuf buf = this.buf;
/*     */     
/* 142 */     if (buf == null) {
/* 143 */       this.buf = buf = ctx.alloc().buffer();
/* 144 */       buf.markReaderIndex();
/*     */     } else {
/* 146 */       buf.resetReaderIndex();
/*     */     } 
/*     */     
/* 149 */     buf.writeBytes(part);
/*     */     
/* 151 */     if (!buf.isReadable(11)) {
/*     */       return;
/*     */     }
/*     */     
/* 155 */     String s = readLegacyString(buf);
/* 156 */     if (s == null) {
/*     */       return;
/*     */     }
/*     */     
/* 160 */     if (!s.equals("MC|PingHost")) {
/* 161 */       removeHandler(ctx);
/*     */       
/*     */       return;
/*     */     } 
/* 165 */     if (!buf.isReadable(2) || !buf.isReadable(buf.readShort())) {
/*     */       return;
/*     */     }
/*     */     
/* 169 */     MinecraftServer server = this.b.d();
/* 170 */     int protocolVersion = buf.readByte();
/* 171 */     String host = readLegacyString(buf);
/* 172 */     if (host == null) {
/* 173 */       removeHandler(ctx);
/*     */       return;
/*     */     } 
/* 176 */     int port = buf.readInt();
/*     */     
/* 178 */     if (buf.isReadable()) {
/* 179 */       removeHandler(ctx);
/*     */       
/*     */       return;
/*     */     } 
/* 183 */     buf.release();
/* 184 */     this.buf = null;
/*     */     
/* 186 */     LOGGER.debug("Ping: (1.6) from {}", ctx.channel().remoteAddress());
/*     */     
/* 188 */     InetSocketAddress virtualHost = PaperNetworkClient.prepareVirtualHost(host, port);
/* 189 */     PaperServerListPingEvent event = PaperLegacyStatusClient.processRequest(server, (InetSocketAddress)ctx
/* 190 */         .channel().remoteAddress(), protocolVersion, virtualHost);
/* 191 */     if (event == null) {
/* 192 */       ctx.close();
/*     */       
/*     */       return;
/*     */     } 
/* 196 */     String response = String.format("§1\000%d\000%s\000%s\000%d\000%d", new Object[] { Integer.valueOf(event.getProtocolVersion()), event.getVersion(), 
/* 197 */           PaperLegacyStatusClient.getMotd(event), Integer.valueOf(event.getNumPlayers()), Integer.valueOf(event.getMaxPlayers()) });
/* 198 */     a(ctx, a(response));
/*     */   }
/*     */   
/*     */   private void removeHandler(ChannelHandlerContext ctx) {
/* 202 */     ByteBuf buf = this.buf;
/* 203 */     this.buf = null;
/*     */     
/* 205 */     buf.resetReaderIndex();
/* 206 */     ctx.pipeline().remove((ChannelHandler)this);
/* 207 */     ctx.fireChannelRead(buf);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlerRemoved(ChannelHandlerContext ctx) {
/* 212 */     if (this.buf != null) {
/* 213 */       this.buf.release();
/* 214 */       this.buf = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(ChannelHandlerContext channelhandlercontext, ByteBuf bytebuf) {
/* 220 */     channelhandlercontext.pipeline().firstContext().writeAndFlush(bytebuf).addListener((GenericFutureListener)ChannelFutureListener.CLOSE);
/*     */   }
/*     */   
/*     */   private ByteBuf a(String s) {
/* 224 */     ByteBuf bytebuf = Unpooled.buffer();
/*     */     
/* 226 */     bytebuf.writeByte(255);
/* 227 */     char[] achar = s.toCharArray();
/*     */     
/* 229 */     bytebuf.writeShort(achar.length);
/* 230 */     char[] achar1 = achar;
/* 231 */     int i = achar.length;
/*     */     
/* 233 */     for (int j = 0; j < i; j++) {
/* 234 */       char c0 = achar1[j];
/*     */       
/* 236 */       bytebuf.writeChar(c0);
/*     */     } 
/*     */     
/* 239 */     return bytebuf;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LegacyPingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */