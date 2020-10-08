/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInAbilities
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private boolean a;
/*    */   
/*    */   public PacketPlayInAbilities() {}
/*    */   
/*    */   public PacketPlayInAbilities(PlayerAbilities var0) {
/* 18 */     this.a = var0.isFlying;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     byte var1 = var0.readByte();
/* 24 */     this.a = ((var1 & 0x2) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 29 */     byte var1 = 0;
/* 30 */     if (this.a) {
/* 31 */       var1 = (byte)(var1 | 0x2);
/*    */     }
/* 33 */     var0.writeByte(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 38 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public boolean isFlying() {
/* 42 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInAbilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */