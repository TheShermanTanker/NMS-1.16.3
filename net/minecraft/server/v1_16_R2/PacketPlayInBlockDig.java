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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInBlockDig
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private EnumDirection b;
/*    */   private EnumPlayerDigType c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.c = var0.<EnumPlayerDigType>a(EnumPlayerDigType.class);
/* 27 */     this.a = var0.e();
/* 28 */     this.b = EnumDirection.fromType1(var0.readUnsignedByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.a(this.c);
/* 34 */     var0.a(this.a);
/* 35 */     var0.writeByte(this.b.c());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 40 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public EnumDirection c() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public EnumPlayerDigType d() {
/* 52 */     return this.c;
/*    */   }
/*    */   
/*    */   public enum EnumPlayerDigType {
/* 56 */     START_DESTROY_BLOCK,
/* 57 */     ABORT_DESTROY_BLOCK,
/* 58 */     STOP_DESTROY_BLOCK,
/* 59 */     DROP_ALL_ITEMS,
/* 60 */     DROP_ITEM,
/* 61 */     RELEASE_USE_ITEM,
/* 62 */     SWAP_ITEM_WITH_OFFHAND;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInBlockDig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */