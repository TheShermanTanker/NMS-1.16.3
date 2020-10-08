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
/*    */ public class PacketPlayOutLookAt
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private double a;
/*    */   private double b;
/*    */   private double c;
/*    */   private int d;
/*    */   private ArgumentAnchor.Anchor e;
/*    */   private ArgumentAnchor.Anchor f;
/*    */   private boolean g;
/*    */   
/*    */   public PacketPlayOutLookAt() {}
/*    */   
/*    */   public PacketPlayOutLookAt(ArgumentAnchor.Anchor var0, double var1, double var3, double var5) {
/* 26 */     this.e = var0;
/* 27 */     this.a = var1;
/* 28 */     this.b = var3;
/* 29 */     this.c = var5;
/*    */   }
/*    */   
/*    */   public PacketPlayOutLookAt(ArgumentAnchor.Anchor var0, Entity var1, ArgumentAnchor.Anchor var2) {
/* 33 */     this.e = var0;
/* 34 */     this.d = var1.getId();
/* 35 */     this.f = var2;
/* 36 */     Vec3D var3 = var2.a(var1);
/* 37 */     this.a = var3.x;
/* 38 */     this.b = var3.y;
/* 39 */     this.c = var3.z;
/* 40 */     this.g = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 45 */     this.e = var0.<ArgumentAnchor.Anchor>a(ArgumentAnchor.Anchor.class);
/* 46 */     this.a = var0.readDouble();
/* 47 */     this.b = var0.readDouble();
/* 48 */     this.c = var0.readDouble();
/* 49 */     if (var0.readBoolean()) {
/* 50 */       this.g = true;
/* 51 */       this.d = var0.i();
/* 52 */       this.f = var0.<ArgumentAnchor.Anchor>a(ArgumentAnchor.Anchor.class);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 58 */     var0.a(this.e);
/* 59 */     var0.writeDouble(this.a);
/* 60 */     var0.writeDouble(this.b);
/* 61 */     var0.writeDouble(this.c);
/* 62 */     var0.writeBoolean(this.g);
/* 63 */     if (this.g) {
/* 64 */       var0.d(this.d);
/* 65 */       var0.a(this.f);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 71 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutLookAt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */