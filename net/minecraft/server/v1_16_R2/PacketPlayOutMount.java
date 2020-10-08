/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutMount
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int[] b;
/*    */   
/*    */   public PacketPlayOutMount() {}
/*    */   
/*    */   public PacketPlayOutMount(Entity var0) {
/* 18 */     this.a = var0.getId();
/* 19 */     List<Entity> var1 = var0.getPassengers();
/* 20 */     this.b = new int[var1.size()];
/*    */     
/* 22 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/* 23 */       this.b[var2] = ((Entity)var1.get(var2)).getId();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.i();
/* 30 */     this.b = var0.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 35 */     var0.d(this.a);
/* 36 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 41 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutMount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */