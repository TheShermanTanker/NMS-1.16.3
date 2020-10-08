/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutCollect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutCollect() {}
/*    */   
/*    */   public PacketPlayOutCollect(int var0, int var1, int var2) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/* 19 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.i();
/* 25 */     this.b = var0.i();
/* 26 */     this.c = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 31 */     var0.d(this.a);
/* 32 */     var0.d(this.b);
/* 33 */     var0.d(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 38 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCollect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */