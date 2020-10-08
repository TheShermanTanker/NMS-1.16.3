/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutOpenWindowMerchant
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private MerchantRecipeList b;
/*    */   private int c;
/*    */   private int d;
/*    */   private boolean e;
/*    */   private boolean f;
/*    */   
/*    */   public PacketPlayOutOpenWindowMerchant() {}
/*    */   
/*    */   public PacketPlayOutOpenWindowMerchant(int var0, MerchantRecipeList var1, int var2, int var3, boolean var4, boolean var5) {
/* 22 */     this.a = var0;
/* 23 */     this.b = var1;
/* 24 */     this.c = var2;
/* 25 */     this.d = var3;
/* 26 */     this.e = var4;
/* 27 */     this.f = var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 32 */     this.a = var0.i();
/* 33 */     this.b = MerchantRecipeList.b(var0);
/* 34 */     this.c = var0.i();
/* 35 */     this.d = var0.i();
/* 36 */     this.e = var0.readBoolean();
/* 37 */     this.f = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 42 */     var0.d(this.a);
/* 43 */     this.b.a(var0);
/* 44 */     var0.d(this.c);
/* 45 */     var0.d(this.d);
/* 46 */     var0.writeBoolean(this.e);
/* 47 */     var0.writeBoolean(this.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 52 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutOpenWindowMerchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */