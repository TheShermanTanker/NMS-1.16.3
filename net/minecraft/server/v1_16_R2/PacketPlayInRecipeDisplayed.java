/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInRecipeDisplayed
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private MinecraftKey a;
/*    */   
/*    */   public PacketPlayInRecipeDisplayed() {}
/*    */   
/*    */   public PacketPlayInRecipeDisplayed(IRecipe<?> var0) {
/* 17 */     this.a = var0.getKey();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 23 */     this.a = var0.p();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 28 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 33 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public MinecraftKey b() {
/* 37 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInRecipeDisplayed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */