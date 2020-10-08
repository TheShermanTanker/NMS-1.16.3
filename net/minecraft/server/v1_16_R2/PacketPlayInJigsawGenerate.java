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
/*    */ public class PacketPlayInJigsawGenerate
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private int b;
/*    */   private boolean c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 25 */     this.a = var0.e();
/* 26 */     this.b = var0.i();
/* 27 */     this.c = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.a(this.a);
/* 33 */     var0.d(this.b);
/* 34 */     var0.writeBoolean(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 39 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 43 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 47 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 51 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInJigsawGenerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */