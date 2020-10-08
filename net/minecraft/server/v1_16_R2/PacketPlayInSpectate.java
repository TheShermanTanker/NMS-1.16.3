/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSpectate
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private UUID a;
/*    */   
/*    */   public PacketPlayInSpectate() {}
/*    */   
/*    */   public PacketPlayInSpectate(UUID var0) {
/* 19 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.k();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 29 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 34 */     var0.a(this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity a(WorldServer var0) {
/* 39 */     return var0.getEntity(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSpectate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */