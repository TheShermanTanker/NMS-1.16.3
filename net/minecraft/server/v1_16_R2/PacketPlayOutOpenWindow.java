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
/*    */ public class PacketPlayOutOpenWindow
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private IChatBaseComponent c;
/*    */   
/*    */   public PacketPlayOutOpenWindow() {}
/*    */   
/*    */   public PacketPlayOutOpenWindow(int var0, Containers<?> var1, IChatBaseComponent var2) {
/* 21 */     this.a = var0;
/* 22 */     this.b = IRegistry.MENU.a(var1);
/* 23 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     this.a = var0.i();
/* 29 */     this.b = var0.i();
/* 30 */     this.c = var0.h();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 35 */     var0.d(this.a);
/* 36 */     var0.d(this.b);
/* 37 */     var0.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 42 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutOpenWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */