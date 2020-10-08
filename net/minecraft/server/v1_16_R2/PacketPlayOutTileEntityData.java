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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutTileEntityData
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private int b;
/*    */   private NBTTagCompound c;
/*    */   
/*    */   public PacketPlayOutTileEntityData() {}
/*    */   
/*    */   public PacketPlayOutTileEntityData(BlockPosition var0, int var1, NBTTagCompound var2) {
/* 34 */     this.a = var0;
/* 35 */     this.b = var1;
/* 36 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 41 */     this.a = var0.e();
/* 42 */     this.b = var0.readUnsignedByte();
/* 43 */     this.c = var0.l();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 48 */     var0.a(this.a);
/* 49 */     var0.writeByte((byte)this.b);
/* 50 */     var0.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 55 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutTileEntityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */