/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutExperience
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private float a;
/*    */   private int b;
/*    */   private int c;
/*    */   
/*    */   public PacketPlayOutExperience() {}
/*    */   
/*    */   public PacketPlayOutExperience(float var0, int var1, int var2) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/* 19 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.readFloat();
/* 25 */     this.c = var0.i();
/* 26 */     this.b = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 31 */     var0.writeFloat(this.a);
/* 32 */     var0.d(this.c);
/* 33 */     var0.d(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 38 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutExperience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */