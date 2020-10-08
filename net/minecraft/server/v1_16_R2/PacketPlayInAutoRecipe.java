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
/*    */ public class PacketPlayInAutoRecipe
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private MinecraftKey b;
/*    */   private boolean c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = var0.readByte();
/* 27 */     this.b = var0.p();
/* 28 */     this.c = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 33 */     var0.writeByte(this.a);
/* 34 */     var0.a(this.b);
/* 35 */     var0.writeBoolean(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 40 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public int b() {
/* 44 */     return this.a;
/*    */   }
/*    */   
/*    */   public MinecraftKey c() {
/* 48 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 52 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInAutoRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */