/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInAdvancements
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private Status a;
/*    */   private MinecraftKey b;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 33 */     this.a = var0.<Status>a(Status.class);
/* 34 */     if (this.a == Status.OPENED_TAB) {
/* 35 */       this.b = var0.p();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 41 */     var0.a(this.a);
/* 42 */     if (this.a == Status.OPENED_TAB) {
/* 43 */       var0.a(this.b);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 49 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public Status c() {
/* 53 */     return this.a;
/*    */   }
/*    */   
/*    */   public MinecraftKey d() {
/* 57 */     return this.b;
/*    */   }
/*    */   
/*    */   public enum Status {
/* 61 */     OPENED_TAB,
/* 62 */     CLOSED_SCREEN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */