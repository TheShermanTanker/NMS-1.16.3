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
/*    */ public class PacketPlayOutAbilities
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private boolean a;
/*    */   private boolean b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   private float e;
/*    */   private float f;
/*    */   
/*    */   public PacketPlayOutAbilities() {}
/*    */   
/*    */   public PacketPlayOutAbilities(PlayerAbilities var0) {
/* 27 */     this.a = var0.isInvulnerable;
/* 28 */     this.b = var0.isFlying;
/* 29 */     this.c = var0.canFly;
/* 30 */     this.d = var0.canInstantlyBuild;
/* 31 */     this.e = var0.a();
/* 32 */     this.f = var0.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 37 */     byte var1 = var0.readByte();
/*    */     
/* 39 */     this.a = ((var1 & 0x1) != 0);
/* 40 */     this.b = ((var1 & 0x2) != 0);
/* 41 */     this.c = ((var1 & 0x4) != 0);
/* 42 */     this.d = ((var1 & 0x8) != 0);
/* 43 */     this.e = var0.readFloat();
/* 44 */     this.f = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 49 */     byte var1 = 0;
/*    */     
/* 51 */     if (this.a) {
/* 52 */       var1 = (byte)(var1 | 0x1);
/*    */     }
/* 54 */     if (this.b) {
/* 55 */       var1 = (byte)(var1 | 0x2);
/*    */     }
/* 57 */     if (this.c) {
/* 58 */       var1 = (byte)(var1 | 0x4);
/*    */     }
/* 60 */     if (this.d) {
/* 61 */       var1 = (byte)(var1 | 0x8);
/*    */     }
/*    */     
/* 64 */     var0.writeByte(var1);
/* 65 */     var0.writeFloat(this.e);
/* 66 */     var0.writeFloat(this.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 71 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */