/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInCustomPayload
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/* 13 */   public static final MinecraftKey a = new MinecraftKey("brand");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MinecraftKey tag;
/*    */ 
/*    */ 
/*    */   
/*    */   public PacketDataSerializer data;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     this.tag = var0.p();
/* 29 */     int var1 = var0.readableBytes();
/* 30 */     if (var1 < 0 || var1 > 32767) {
/* 31 */       throw new IOException("Payload may not be larger than 32767 bytes");
/*    */     }
/* 33 */     this.data = new PacketDataSerializer(var0.readBytes(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 38 */     var0.a(this.tag);
/* 39 */     var0.writeBytes(this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 44 */     var0.a(this);
/* 45 */     if (this.data != null)
/* 46 */       this.data.release(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInCustomPayload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */