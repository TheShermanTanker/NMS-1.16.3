/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutViewCentre
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutViewCentre() {}
/*    */   
/*    */   public PacketPlayOutViewCentre(int var0, int var1) {
/* 16 */     this.a = var0;
/* 17 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 22 */     this.a = var0.i();
/* 23 */     this.b = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.d(this.a);
/* 29 */     var0.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 34 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutViewCentre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */