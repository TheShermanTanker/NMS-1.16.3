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
/*    */ public class PacketPlayOutBlockChange
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private BlockPosition a;
/*    */   public IBlockData block;
/*    */   
/*    */   public PacketPlayOutBlockChange() {}
/*    */   
/*    */   public PacketPlayOutBlockChange(BlockPosition var0, IBlockData var1) {
/* 21 */     this.a = var0;
/* 22 */     this.block = var1;
/*    */   }
/*    */   
/*    */   public PacketPlayOutBlockChange(IBlockAccess var0, BlockPosition var1) {
/* 26 */     this(var1, var0.getType(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 31 */     this.a = var0.e();
/* 32 */     this.block = Block.REGISTRY_ID.fromId(var0.i());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 37 */     var0.a(this.a);
/* 38 */     var0.d(Block.getCombinedId(this.block));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 43 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */