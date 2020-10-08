/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityVelocity
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   private int d;
/*    */   
/*    */   public PacketPlayOutEntityVelocity() {}
/*    */   
/*    */   public PacketPlayOutEntityVelocity(Entity var0) {
/* 21 */     this(var0.getId(), var0.getMot());
/*    */   }
/*    */   
/*    */   public PacketPlayOutEntityVelocity(int var0, Vec3D var1) {
/* 25 */     this.a = var0;
/* 26 */     double var2 = 3.9D;
/* 27 */     double var4 = MathHelper.a(var1.x, -3.9D, 3.9D);
/* 28 */     double var6 = MathHelper.a(var1.y, -3.9D, 3.9D);
/* 29 */     double var8 = MathHelper.a(var1.z, -3.9D, 3.9D);
/* 30 */     this.b = (int)(var4 * 8000.0D);
/* 31 */     this.c = (int)(var6 * 8000.0D);
/* 32 */     this.d = (int)(var8 * 8000.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 37 */     this.a = var0.i();
/* 38 */     this.b = var0.readShort();
/* 39 */     this.c = var0.readShort();
/* 40 */     this.d = var0.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 45 */     var0.d(this.a);
/* 46 */     var0.writeShort(this.b);
/* 47 */     var0.writeShort(this.c);
/* 48 */     var0.writeShort(this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 53 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntityVelocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */