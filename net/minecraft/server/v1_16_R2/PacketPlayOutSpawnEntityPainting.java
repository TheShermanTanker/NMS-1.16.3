/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSpawnEntityPainting
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private UUID b;
/*    */   private BlockPosition c;
/*    */   private EnumDirection d;
/*    */   private int e;
/*    */   
/*    */   public PacketPlayOutSpawnEntityPainting() {}
/*    */   
/*    */   public PacketPlayOutSpawnEntityPainting(EntityPainting var0) {
/* 26 */     this.a = var0.getId();
/* 27 */     this.b = var0.getUniqueID();
/* 28 */     this.c = var0.getBlockPosition();
/* 29 */     this.d = var0.getDirection();
/* 30 */     this.e = IRegistry.MOTIVE.a(var0.art);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 35 */     this.a = var0.i();
/* 36 */     this.b = var0.k();
/* 37 */     this.e = var0.i();
/* 38 */     this.c = var0.e();
/* 39 */     this.d = EnumDirection.fromType2(var0.readUnsignedByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 44 */     var0.d(this.a);
/* 45 */     var0.a(this.b);
/* 46 */     var0.d(this.e);
/* 47 */     var0.a(this.c);
/* 48 */     var0.writeByte(this.d.get2DRotationValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 53 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSpawnEntityPainting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */