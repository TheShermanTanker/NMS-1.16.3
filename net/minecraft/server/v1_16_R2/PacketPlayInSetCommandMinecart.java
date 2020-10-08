/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
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
/*    */ public class PacketPlayInSetCommandMinecart
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private String b;
/*    */   private boolean c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.i();
/* 30 */     this.b = var0.e(32767);
/* 31 */     this.c = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.d(this.a);
/* 37 */     var0.a(this.b);
/* 38 */     var0.writeBoolean(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 43 */     var0.a(this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public CommandBlockListenerAbstract a(World var0) {
/* 48 */     Entity var1 = var0.getEntity(this.a);
/* 49 */     if (var1 instanceof EntityMinecartCommandBlock) {
/* 50 */       return ((EntityMinecartCommandBlock)var1).getCommandBlock();
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String b() {
/* 57 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 61 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSetCommandMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */