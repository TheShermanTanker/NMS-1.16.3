/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutAttachEntity
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutAttachEntity() {}
/*    */   
/*    */   public PacketPlayOutAttachEntity(Entity var0, @Nullable Entity var1) {
/* 18 */     this.a = var0.getId();
/* 19 */     this.b = (var1 != null) ? var1.getId() : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     this.a = var0.readInt();
/* 25 */     this.b = var0.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 30 */     var0.writeInt(this.a);
/* 31 */     var0.writeInt(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 36 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutAttachEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */