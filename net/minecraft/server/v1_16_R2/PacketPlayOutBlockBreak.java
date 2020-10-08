/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutBlockBreak
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/* 15 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private BlockPosition c;
/*    */   
/*    */   private IBlockData d;
/*    */   
/*    */   PacketPlayInBlockDig.EnumPlayerDigType a;
/*    */   
/*    */   private boolean e;
/*    */ 
/*    */   
/*    */   public PacketPlayOutBlockBreak() {}
/*    */ 
/*    */   
/*    */   public PacketPlayOutBlockBreak(BlockPosition var0, IBlockData var1, PacketPlayInBlockDig.EnumPlayerDigType var2, boolean var3, String var4) {
/* 30 */     this.c = var0.immutableCopy();
/* 31 */     this.d = var1;
/* 32 */     this.a = var2;
/* 33 */     this.e = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 38 */     this.c = var0.e();
/* 39 */     this.d = Block.REGISTRY_ID.fromId(var0.i());
/* 40 */     this.a = var0.<PacketPlayInBlockDig.EnumPlayerDigType>a(PacketPlayInBlockDig.EnumPlayerDigType.class);
/* 41 */     this.e = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 46 */     var0.a(this.c);
/* 47 */     var0.d(Block.getCombinedId(this.d));
/* 48 */     var0.a(this.a);
/* 49 */     var0.writeBoolean(this.e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 54 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutBlockBreak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */