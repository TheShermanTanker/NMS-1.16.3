/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSpawnEntityExperienceOrb
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private double b;
/*    */   private double c;
/*    */   private double d;
/*    */   private int e;
/*    */   
/*    */   public PacketPlayOutSpawnEntityExperienceOrb() {}
/*    */   
/*    */   public PacketPlayOutSpawnEntityExperienceOrb(EntityExperienceOrb var0) {
/* 21 */     this.a = var0.getId();
/* 22 */     this.b = var0.locX();
/* 23 */     this.c = var0.locY();
/* 24 */     this.d = var0.locZ();
/* 25 */     this.e = var0.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 30 */     this.a = var0.i();
/* 31 */     this.b = var0.readDouble();
/* 32 */     this.c = var0.readDouble();
/* 33 */     this.d = var0.readDouble();
/* 34 */     this.e = var0.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 39 */     var0.d(this.a);
/* 40 */     var0.writeDouble(this.b);
/* 41 */     var0.writeDouble(this.c);
/* 42 */     var0.writeDouble(this.d);
/* 43 */     var0.writeShort(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 48 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSpawnEntityExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */