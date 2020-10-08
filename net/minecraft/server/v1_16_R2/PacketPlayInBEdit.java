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
/*    */ public class PacketPlayInBEdit
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private ItemStack a;
/*    */   private boolean b;
/*    */   private EnumHand c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.n();
/* 27 */     this.b = var0.readBoolean();
/* 28 */     this.c = var0.<EnumHand>a(EnumHand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.a(this.a);
/* 34 */     var0.writeBoolean(this.b);
/* 35 */     var0.a(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 40 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public ItemStack b() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public EnumHand d() {
/* 52 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInBEdit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */