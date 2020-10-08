/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutBlockAction
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private int b;
/*    */   private int c;
/*    */   private Block d;
/*    */   
/*    */   public PacketPlayOutBlockAction() {}
/*    */   
/*    */   public PacketPlayOutBlockAction(BlockPosition var0, Block var1, int var2, int var3) {
/* 21 */     this.a = var0;
/* 22 */     this.d = var1;
/* 23 */     this.b = var2;
/* 24 */     this.c = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.e();
/* 30 */     this.b = var0.readUnsignedByte();
/* 31 */     this.c = var0.readUnsignedByte();
/*    */     
/* 33 */     this.d = IRegistry.BLOCK.fromId(var0.i());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 38 */     var0.a(this.a);
/* 39 */     var0.writeByte(this.b);
/* 40 */     var0.writeByte(this.c);
/* 41 */     var0.d(IRegistry.BLOCK.a(this.d));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 46 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutBlockAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */