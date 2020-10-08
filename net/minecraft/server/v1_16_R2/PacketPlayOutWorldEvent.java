/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutWorldEvent
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private BlockPosition b;
/*    */   private int c;
/*    */   private boolean d;
/*    */   
/*    */   public PacketPlayOutWorldEvent() {}
/*    */   
/*    */   public PacketPlayOutWorldEvent(int var0, BlockPosition var1, int var2, boolean var3) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1.immutableCopy();
/* 22 */     this.c = var2;
/* 23 */     this.d = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     this.a = var0.readInt();
/* 29 */     this.b = var0.e();
/* 30 */     this.c = var0.readInt();
/* 31 */     this.d = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.writeInt(this.a);
/* 37 */     var0.a(this.b);
/* 38 */     var0.writeInt(this.c);
/* 39 */     var0.writeBoolean(this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 44 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutWorldEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */