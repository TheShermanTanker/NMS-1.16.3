/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.Socket;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class RemoteControlSession
/*     */   extends RemoteConnectionThread
/*     */ {
/*  16 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*     */   private boolean e;
/*     */ 
/*     */   
/*     */   private final Socket f;
/*     */   
/*  24 */   private final byte[] g = new byte[1460];
/*     */   private final String h;
/*     */   private final IMinecraftServer i;
/*     */   
/*     */   RemoteControlSession(IMinecraftServer var0, String var1, Socket var2) {
/*  29 */     super("RCON Client " + var2.getInetAddress());
/*  30 */     this.i = var0;
/*  31 */     this.f = var2;
/*     */     
/*     */     try {
/*  34 */       this.f.setSoTimeout(0);
/*  35 */     } catch (Exception var3) {
/*  36 */       this.a = false;
/*     */     } 
/*     */     
/*  39 */     this.h = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/*     */     
/*  45 */     try { while (this.a) {
/*  46 */         String var6; BufferedInputStream var0 = new BufferedInputStream(this.f.getInputStream());
/*  47 */         int var1 = var0.read(this.g, 0, 1460);
/*     */         
/*  49 */         if (10 > var1) {
/*     */           return;
/*     */         }
/*     */         
/*  53 */         int var2 = 0;
/*  54 */         int var3 = StatusChallengeUtils.b(this.g, 0, var1);
/*  55 */         if (var3 != var1 - 4) {
/*     */           return;
/*     */         }
/*     */         
/*  59 */         var2 += 4;
/*  60 */         int var4 = StatusChallengeUtils.b(this.g, var2, var1);
/*  61 */         var2 += 4;
/*     */         
/*  63 */         int var5 = StatusChallengeUtils.a(this.g, var2);
/*  64 */         var2 += 4;
/*  65 */         switch (var5) {
/*     */           case 3:
/*  67 */             var6 = StatusChallengeUtils.a(this.g, var2, var1);
/*  68 */             var2 += var6.length();
/*  69 */             if (!var6.isEmpty() && var6.equals(this.h)) {
/*  70 */               this.e = true;
/*  71 */               a(var4, 2, ""); continue;
/*     */             } 
/*  73 */             this.e = false;
/*  74 */             d();
/*     */             continue;
/*     */           
/*     */           case 2:
/*  78 */             if (this.e) {
/*  79 */               String var7 = StatusChallengeUtils.a(this.g, var2, var1);
/*     */               try {
/*  81 */                 a(var4, this.i.executeRemoteCommand(var7));
/*  82 */               } catch (Exception var8) {
/*  83 */                 a(var4, "Error executing: " + var7 + " (" + var8.getMessage() + ")");
/*     */               }  continue;
/*     */             } 
/*  86 */             d();
/*     */             continue;
/*     */         } 
/*     */         
/*  90 */         a(var4, String.format("Unknown request %s", new Object[] { Integer.toHexString(var5) }));
/*     */       }
/*     */        }
/*  93 */     catch (IOException iOException) {  }
/*  94 */     catch (Exception var0)
/*  95 */     { LOGGER.error("Exception whilst parsing RCON input", var0); }
/*     */     finally
/*  97 */     { e();
/*  98 */       LOGGER.info("Thread {} shutting down", this.b);
/*  99 */       this.a = false; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(int var0, int var1, String var2) throws IOException {
/* 106 */     ByteArrayOutputStream var3 = new ByteArrayOutputStream(1248);
/* 107 */     DataOutputStream var4 = new DataOutputStream(var3);
/* 108 */     byte[] var5 = var2.getBytes(StandardCharsets.UTF_8);
/* 109 */     var4.writeInt(Integer.reverseBytes(var5.length + 10));
/* 110 */     var4.writeInt(Integer.reverseBytes(var0));
/* 111 */     var4.writeInt(Integer.reverseBytes(var1));
/* 112 */     var4.write(var5);
/* 113 */     var4.write(0);
/* 114 */     var4.write(0);
/* 115 */     this.f.getOutputStream().write(var3.toByteArray());
/*     */   }
/*     */   
/*     */   private void d() throws IOException {
/* 119 */     a(-1, 2, "");
/*     */   }
/*     */   
/*     */   private void a(int var0, String var1) throws IOException {
/* 123 */     int var2 = var1.length();
/*     */     
/*     */     do {
/* 126 */       int var3 = (4096 <= var2) ? 4096 : var2;
/* 127 */       a(var0, 0, var1.substring(0, var3));
/* 128 */       var1 = var1.substring(var3);
/* 129 */       var2 = var1.length();
/* 130 */     } while (0 != var2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/* 138 */     this.a = false;
/* 139 */     e();
/* 140 */     super.b();
/*     */   }
/*     */   
/*     */   private void e() {
/*     */     try {
/* 145 */       this.f.close();
/* 146 */     } catch (IOException var0) {
/* 147 */       LOGGER.warn("Failed to close socket", var0);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RemoteControlSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */