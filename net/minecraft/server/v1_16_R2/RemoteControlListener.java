/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.ServerSocket;
/*     */ import java.net.Socket;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class RemoteControlListener
/*     */   extends RemoteConnectionThread {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final ServerSocket e;
/*     */   private final String f;
/*  20 */   private final List<RemoteControlSession> g = Lists.newArrayList();
/*     */   private final IMinecraftServer h;
/*     */   
/*     */   private RemoteControlListener(IMinecraftServer iminecraftserver, ServerSocket serversocket, String s) {
/*  24 */     super("RCON Listener");
/*  25 */     this.h = iminecraftserver;
/*  26 */     this.e = serversocket;
/*  27 */     this.f = s;
/*     */   }
/*     */   
/*     */   private void d() {
/*  31 */     this.g.removeIf(remotecontrolsession -> !remotecontrolsession.c());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     try {
/*  38 */       while (this.a) {
/*     */         try {
/*  40 */           Socket socket = this.e.accept();
/*  41 */           RemoteControlSession remotecontrolsession = new RemoteControlSession(this.h, this.f, socket);
/*     */           
/*  43 */           remotecontrolsession.a();
/*  44 */           this.g.add(remotecontrolsession);
/*  45 */           d();
/*  46 */         } catch (SocketTimeoutException sockettimeoutexception) {
/*  47 */           d();
/*  48 */         } catch (IOException ioexception) {
/*  49 */           if (this.a) {
/*  50 */             LOGGER.info("IO exception: ", ioexception);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } finally {
/*  55 */       a(this.e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static RemoteControlListener a(IMinecraftServer iminecraftserver) {
/*  62 */     DedicatedServerProperties dedicatedserverproperties = iminecraftserver.getDedicatedServerProperties();
/*  63 */     String s = dedicatedserverproperties.rconIp;
/*     */     
/*  65 */     if (s.isEmpty()) {
/*  66 */       s = "0.0.0.0";
/*     */     }
/*     */     
/*  69 */     int i = dedicatedserverproperties.rconPort;
/*     */     
/*  71 */     if (0 < i && 65535 >= i) {
/*  72 */       String s1 = dedicatedserverproperties.rconPassword;
/*     */       
/*  74 */       if (s1.isEmpty()) {
/*  75 */         LOGGER.warn("No rcon password set in server.properties, rcon disabled!");
/*  76 */         return null;
/*     */       } 
/*     */       try {
/*  79 */         ServerSocket serversocket = new ServerSocket(i, 0, InetAddress.getByName(s));
/*     */         
/*  81 */         serversocket.setSoTimeout(500);
/*  82 */         RemoteControlListener remotecontrollistener = new RemoteControlListener(iminecraftserver, serversocket, s1);
/*     */         
/*  84 */         if (!remotecontrollistener.a()) {
/*  85 */           return null;
/*     */         }
/*  87 */         LOGGER.info("RCON running on {}:{}", s, Integer.valueOf(i));
/*  88 */         return remotecontrollistener;
/*     */       }
/*  90 */       catch (IOException ioexception) {
/*  91 */         LOGGER.warn("Unable to initialise RCON on {}:{}", s, Integer.valueOf(i), ioexception);
/*  92 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     LOGGER.warn("Invalid rcon port {} found in server.properties, rcon disabled!", Integer.valueOf(i));
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/* 103 */     this.a = false;
/* 104 */     a(this.e);
/* 105 */     super.b();
/* 106 */     Iterator<RemoteControlSession> iterator = this.g.iterator();
/*     */     
/* 108 */     while (iterator.hasNext()) {
/* 109 */       RemoteControlSession remotecontrolsession = iterator.next();
/*     */       
/* 111 */       if (remotecontrolsession.c()) {
/* 112 */         remotecontrolsession.b();
/*     */       }
/*     */     } 
/*     */     
/* 116 */     this.g.clear();
/*     */   }
/*     */   
/*     */   private void a(ServerSocket serversocket) {
/* 120 */     LOGGER.debug("closeSocket: {}", serversocket);
/*     */     
/*     */     try {
/* 123 */       serversocket.close();
/* 124 */     } catch (IOException ioexception) {
/* 125 */       LOGGER.warn("Failed to close socket", ioexception);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteControlListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */