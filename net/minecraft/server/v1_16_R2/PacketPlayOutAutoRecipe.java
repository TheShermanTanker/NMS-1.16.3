/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutAutoRecipe
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private MinecraftKey b;
/*    */   
/*    */   public PacketPlayOutAutoRecipe() {}
/*    */   
/*    */   public PacketPlayOutAutoRecipe(int var0, IRecipe<?> var1) {
/* 18 */     this.a = var0;
/* 19 */     this.b = var1.getKey();
/*    */   }
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
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 32 */     this.a = var0.readByte();
/* 33 */     this.b = var0.p();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 38 */     var0.writeByte(this.a);
/* 39 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 44 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutAutoRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */