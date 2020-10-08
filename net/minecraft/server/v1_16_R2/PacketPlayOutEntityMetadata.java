/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityMetadata
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private List<DataWatcher.Item<?>> b;
/*    */   
/*    */   public PacketPlayOutEntityMetadata() {}
/*    */   
/*    */   public PacketPlayOutEntityMetadata(int var0, DataWatcher var1, boolean var2) {
/* 19 */     this.a = var0;
/* 20 */     if (var2) {
/* 21 */       this.b = var1.c();
/* 22 */       var1.e();
/*    */     } else {
/* 24 */       this.b = var1.b();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 30 */     this.a = var0.i();
/* 31 */     this.b = DataWatcher.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.d(this.a);
/* 37 */     DataWatcher.a(this.b, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 42 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */