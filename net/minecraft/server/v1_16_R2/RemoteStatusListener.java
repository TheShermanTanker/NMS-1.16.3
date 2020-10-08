/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.io.IOException;
/*     */ import java.net.DatagramPacket;
/*     */ import java.net.DatagramSocket;
/*     */ import java.net.InetAddress;
/*     */ import java.net.PortUnreachableException;
/*     */ import java.net.SocketAddress;
/*     */ import java.net.SocketTimeoutException;
/*     */ import java.net.UnknownHostException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Date;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RemoteStatusListener
/*     */   extends RemoteConnectionThread
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private long e;
/*     */   
/*     */   private final int f;
/*     */   
/*     */   private final int g;
/*     */   
/*     */   private final int h;
/*     */   private final String i;
/*     */   private final String j;
/*     */   private DatagramSocket k;
/*  38 */   private final byte[] l = new byte[1460];
/*     */   private String m;
/*     */   private String n;
/*     */   private final Map<SocketAddress, RemoteStatusChallenge> o;
/*     */   private final RemoteStatusReply p;
/*     */   private long q;
/*     */   private final IMinecraftServer r;
/*     */   
/*     */   private RemoteStatusListener(IMinecraftServer var0, int var1) {
/*  47 */     super("Query Listener");
/*  48 */     this.r = var0;
/*     */     
/*  50 */     this.f = var1;
/*  51 */     this.n = var0.h_();
/*  52 */     this.g = var0.p();
/*  53 */     this.i = var0.i_();
/*  54 */     this.h = var0.getMaxPlayers();
/*  55 */     this.j = var0.getWorld();
/*     */ 
/*     */     
/*  58 */     this.q = 0L;
/*     */     
/*  60 */     this.m = "0.0.0.0";
/*     */ 
/*     */     
/*  63 */     if (this.n.isEmpty() || this.m.equals(this.n)) {
/*     */       
/*  65 */       this.n = "0.0.0.0";
/*     */       try {
/*  67 */         InetAddress var2 = InetAddress.getLocalHost();
/*  68 */         this.m = var2.getHostAddress();
/*  69 */       } catch (UnknownHostException var2) {
/*  70 */         LOGGER.warn("Unable to determine local host IP, please set server-ip in server.properties", var2);
/*     */       } 
/*     */     } else {
/*  73 */       this.m = this.n;
/*     */     } 
/*     */ 
/*     */     
/*  77 */     this.p = new RemoteStatusReply(1460);
/*  78 */     this.o = Maps.newHashMap();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static RemoteStatusListener a(IMinecraftServer var0) {
/*  83 */     int var1 = (var0.getDedicatedServerProperties()).queryPort;
/*  84 */     if (0 >= var1 || 65535 < var1) {
/*  85 */       LOGGER.warn("Invalid query port {} found in server.properties (queries disabled)", Integer.valueOf(var1));
/*  86 */       return null;
/*     */     } 
/*     */     
/*  89 */     RemoteStatusListener var2 = new RemoteStatusListener(var0, var1);
/*  90 */     if (!var2.a()) {
/*  91 */       return null;
/*     */     }
/*  93 */     return var2;
/*     */   }
/*     */   
/*     */   private void a(byte[] var0, DatagramPacket var1) throws IOException {
/*  97 */     this.k.send(new DatagramPacket(var0, var0.length, var1.getSocketAddress()));
/*     */   }
/*     */   private boolean a(DatagramPacket var0) throws IOException {
/*     */     RemoteStatusReply var4;
/* 101 */     byte[] var1 = var0.getData();
/* 102 */     int var2 = var0.getLength();
/* 103 */     SocketAddress var3 = var0.getSocketAddress();
/* 104 */     LOGGER.debug("Packet len {} [{}]", Integer.valueOf(var2), var3);
/* 105 */     if (3 > var2 || -2 != var1[0] || -3 != var1[1]) {
/*     */       
/* 107 */       LOGGER.debug("Invalid packet [{}]", var3);
/* 108 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 112 */     LOGGER.debug("Packet '{}' [{}]", StatusChallengeUtils.a(var1[2]), var3);
/* 113 */     switch (var1[2]) {
/*     */       
/*     */       case 9:
/* 116 */         d(var0);
/* 117 */         LOGGER.debug("Challenge [{}]", var3);
/* 118 */         return true;
/*     */ 
/*     */       
/*     */       case 0:
/* 122 */         if (!c(var0).booleanValue()) {
/* 123 */           LOGGER.debug("Invalid challenge [{}]", var3);
/* 124 */           return false;
/*     */         } 
/*     */         
/* 127 */         if (15 == var2) {
/*     */           
/* 129 */           a(b(var0), var0);
/* 130 */           LOGGER.debug("Rules [{}]", var3);
/*     */           break;
/*     */         } 
/* 133 */         var4 = new RemoteStatusReply(1460);
/* 134 */         var4.a(0);
/* 135 */         var4.a(a(var0.getSocketAddress()));
/* 136 */         var4.a(this.i);
/* 137 */         var4.a("SMP");
/* 138 */         var4.a(this.j);
/* 139 */         var4.a(Integer.toString(this.r.getPlayerCount()));
/* 140 */         var4.a(Integer.toString(this.h));
/* 141 */         var4.a((short)this.g);
/* 142 */         var4.a(this.m);
/*     */         
/* 144 */         a(var4.a(), var0);
/* 145 */         LOGGER.debug("Status [{}]", var3);
/*     */         break;
/*     */     } 
/*     */     
/* 149 */     return true;
/*     */   }
/*     */   
/*     */   private byte[] b(DatagramPacket var0) throws IOException {
/* 153 */     long var1 = SystemUtils.getMonotonicMillis();
/* 154 */     if (var1 < this.q + 5000L) {
/*     */       
/* 156 */       byte[] arrayOfByte1 = this.p.a();
/* 157 */       byte[] var4 = a(var0.getSocketAddress());
/* 158 */       arrayOfByte1[1] = var4[0];
/* 159 */       arrayOfByte1[2] = var4[1];
/* 160 */       arrayOfByte1[3] = var4[2];
/* 161 */       arrayOfByte1[4] = var4[3];
/*     */       
/* 163 */       return arrayOfByte1;
/*     */     } 
/*     */     
/* 166 */     this.q = var1;
/*     */     
/* 168 */     this.p.b();
/* 169 */     this.p.a(0);
/* 170 */     this.p.a(a(var0.getSocketAddress()));
/* 171 */     this.p.a("splitnum");
/* 172 */     this.p.a(128);
/* 173 */     this.p.a(0);
/*     */ 
/*     */     
/* 176 */     this.p.a("hostname");
/* 177 */     this.p.a(this.i);
/* 178 */     this.p.a("gametype");
/* 179 */     this.p.a("SMP");
/* 180 */     this.p.a("game_id");
/* 181 */     this.p.a("MINECRAFT");
/* 182 */     this.p.a("version");
/* 183 */     this.p.a(this.r.getVersion());
/* 184 */     this.p.a("plugins");
/* 185 */     this.p.a(this.r.getPlugins());
/* 186 */     this.p.a("map");
/* 187 */     this.p.a(this.j);
/* 188 */     this.p.a("numplayers");
/* 189 */     this.p.a("" + this.r.getPlayerCount());
/* 190 */     this.p.a("maxplayers");
/* 191 */     this.p.a("" + this.h);
/* 192 */     this.p.a("hostport");
/* 193 */     this.p.a("" + this.g);
/* 194 */     this.p.a("hostip");
/* 195 */     this.p.a(this.m);
/* 196 */     this.p.a(0);
/* 197 */     this.p.a(1);
/*     */ 
/*     */ 
/*     */     
/* 201 */     this.p.a("player_");
/* 202 */     this.p.a(0);
/*     */     
/* 204 */     String[] var3 = this.r.getPlayers();
/* 205 */     for (String var7 : var3) {
/* 206 */       this.p.a(var7);
/*     */     }
/* 208 */     this.p.a(0);
/*     */     
/* 210 */     return this.p.a();
/*     */   }
/*     */   
/*     */   private byte[] a(SocketAddress var0) {
/* 214 */     return ((RemoteStatusChallenge)this.o.get(var0)).c();
/*     */   }
/*     */   
/*     */   private Boolean c(DatagramPacket var0) {
/* 218 */     SocketAddress var1 = var0.getSocketAddress();
/* 219 */     if (!this.o.containsKey(var1))
/*     */     {
/* 221 */       return Boolean.valueOf(false);
/*     */     }
/*     */     
/* 224 */     byte[] var2 = var0.getData();
/* 225 */     return Boolean.valueOf((((RemoteStatusChallenge)this.o.get(var1)).a() == StatusChallengeUtils.c(var2, 7, var0.getLength())));
/*     */   }
/*     */   
/*     */   private void d(DatagramPacket var0) throws IOException {
/* 229 */     RemoteStatusChallenge var1 = new RemoteStatusChallenge(var0);
/* 230 */     this.o.put(var0.getSocketAddress(), var1);
/*     */     
/* 232 */     a(var1.b(), var0);
/*     */   }
/*     */   
/*     */   private void d() {
/* 236 */     if (!this.a) {
/*     */       return;
/*     */     }
/*     */     
/* 240 */     long var0 = SystemUtils.getMonotonicMillis();
/* 241 */     if (var0 < this.e + 30000L) {
/*     */       return;
/*     */     }
/* 244 */     this.e = var0;
/*     */     
/* 246 */     this.o.values().removeIf(var2 -> var2.a(var0).booleanValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 251 */     LOGGER.info("Query running on {}:{}", this.n, Integer.valueOf(this.f));
/* 252 */     this.e = SystemUtils.getMonotonicMillis();
/* 253 */     DatagramPacket var0 = new DatagramPacket(this.l, this.l.length);
/*     */     
/*     */     try {
/* 256 */       while (this.a) {
/*     */         try {
/* 258 */           this.k.receive(var0);
/*     */ 
/*     */           
/* 261 */           d();
/*     */ 
/*     */           
/* 264 */           a(var0);
/* 265 */         } catch (SocketTimeoutException var1) {
/*     */           
/* 267 */           d();
/* 268 */         } catch (PortUnreachableException portUnreachableException) {
/*     */         
/* 270 */         } catch (IOException var1) {
/*     */           
/* 272 */           a(var1);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 276 */       LOGGER.debug("closeSocket: {}:{}", this.n, Integer.valueOf(this.f));
/* 277 */       this.k.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 283 */     if (this.a) {
/* 284 */       return true;
/*     */     }
/*     */     
/* 287 */     if (!e()) {
/* 288 */       return false;
/*     */     }
/*     */     
/* 291 */     return super.a();
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Exception var0) {
/* 296 */     if (!this.a) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 301 */     LOGGER.warn("Unexpected exception", var0);
/*     */ 
/*     */     
/* 304 */     if (!e()) {
/* 305 */       LOGGER.error("Failed to recover from exception, shutting down!");
/* 306 */       this.a = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean e() {
/*     */     try {
/* 312 */       this.k = new DatagramSocket(this.f, InetAddress.getByName(this.n));
/* 313 */       this.k.setSoTimeout(500);
/* 314 */       return true;
/* 315 */     } catch (Exception var0) {
/* 316 */       LOGGER.warn("Unable to initialise query system on {}:{}", this.n, Integer.valueOf(this.f), var0);
/*     */       
/* 318 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class RemoteStatusChallenge
/*     */   {
/* 329 */     private final long time = (new Date()).getTime(); private final int token; private final byte[] identity; public RemoteStatusChallenge(DatagramPacket var0) {
/* 330 */       byte[] var1 = var0.getData();
/* 331 */       this.identity = new byte[4];
/* 332 */       this.identity[0] = var1[3];
/* 333 */       this.identity[1] = var1[4];
/* 334 */       this.identity[2] = var1[5];
/* 335 */       this.identity[3] = var1[6];
/* 336 */       this.e = new String(this.identity, StandardCharsets.UTF_8);
/* 337 */       this.token = (new Random()).nextInt(16777216);
/* 338 */       this.d = String.format("\t%s%d\000", new Object[] { this.e, Integer.valueOf(this.token) }).getBytes(StandardCharsets.UTF_8);
/*     */     }
/*     */     private final byte[] d; private final String e;
/*     */     public Boolean a(long var0) {
/* 342 */       return Boolean.valueOf((this.time < var0));
/*     */     }
/*     */     
/*     */     public int a() {
/* 346 */       return this.token;
/*     */     }
/*     */     
/*     */     public byte[] b() {
/* 350 */       return this.d;
/*     */     }
/*     */     
/*     */     public byte[] c() {
/* 354 */       return this.identity;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteStatusListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */