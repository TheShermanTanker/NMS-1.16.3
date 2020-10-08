/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutSelectAdvancementTab
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   @Nullable
/*    */   private MinecraftKey a;
/*    */   
/*    */   public PacketPlayOutSelectAdvancementTab() {}
/*    */   
/*    */   public PacketPlayOutSelectAdvancementTab(@Nullable MinecraftKey var0) {
/* 18 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 23 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     if (var0.readBoolean()) {
/* 29 */       this.a = var0.p();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 35 */     var0.writeBoolean((this.a != null));
/* 36 */     if (this.a != null)
/* 37 */       var0.a(this.a); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutSelectAdvancementTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */