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
/*    */ public class PacketPlayOutRemoveEntityEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private MobEffectList b;
/*    */   
/*    */   public PacketPlayOutRemoveEntityEffect() {}
/*    */   
/*    */   public PacketPlayOutRemoveEntityEffect(int var0, MobEffectList var1) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.i();
/* 27 */     this.b = MobEffectList.fromId(var0.readUnsignedByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.d(this.a);
/* 33 */     var0.writeByte(MobEffectList.getId(this.b));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 38 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutRemoveEntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */