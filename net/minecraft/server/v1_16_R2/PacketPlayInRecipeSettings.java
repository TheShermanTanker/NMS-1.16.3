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
/*    */ public class PacketPlayInRecipeSettings
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private RecipeBookType a;
/*    */   private boolean b;
/*    */   private boolean c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 25 */     this.a = var0.<RecipeBookType>a(RecipeBookType.class);
/* 26 */     this.b = var0.readBoolean();
/* 27 */     this.c = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 32 */     var0.a(this.a);
/* 33 */     var0.writeBoolean(this.b);
/* 34 */     var0.writeBoolean(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 39 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public RecipeBookType b() {
/* 43 */     return this.a;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 47 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 51 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInRecipeSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */