/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityDestroy
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int[] a;
/*    */   
/*    */   public PacketPlayOutEntityDestroy() {}
/*    */   
/*    */   public PacketPlayOutEntityDestroy(int... var0) {
/* 18 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.a = new int[var0.i()];
/*    */     
/* 25 */     for (int var1 = 0; var1 < this.a.length; var1++) {
/* 26 */       this.a[var1] = var0.i();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.d(this.a.length);
/*    */     
/* 34 */     for (int var4 : this.a) {
/* 35 */       var0.d(var4);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 41 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityDestroy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */