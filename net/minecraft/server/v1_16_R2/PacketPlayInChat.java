/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*    */ import java.io.IOException;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ 
/*    */ public class PacketPlayInChat implements Packet<PacketListenerPlayIn> {
/*    */   private String a;
/*    */   
/*    */   public PacketPlayInChat(String s) {
/* 12 */     if (s.length() > 256) {
/* 13 */       s = s.substring(0, 256);
/*    */     }
/*    */     
/* 16 */     this.a = s;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 21 */     this.a = packetdataserializer.e(256);
/*    */   }
/*    */   public PacketPlayInChat() {}
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 26 */     packetdataserializer.a(this.a);
/*    */   }
/*    */ 
/*    */   
/* 30 */   private static final ExecutorService executors = Executors.newCachedThreadPool((new ThreadFactoryBuilder())
/* 31 */       .setDaemon(true).setNameFormat("Async Chat Thread - #%d").build());
/*    */   public void a(final PacketListenerPlayIn packetlistenerplayin) {
/* 33 */     if (!this.a.startsWith("/")) {
/*    */       
/* 35 */       executors.submit(new Runnable()
/*    */           {
/*    */ 
/*    */             
/*    */             public void run()
/*    */             {
/* 41 */               packetlistenerplayin.a(PacketPlayInChat.this);
/*    */             }
/*    */           });
/*    */       
/*    */       return;
/*    */     } 
/* 47 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public String b() {
/* 51 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */