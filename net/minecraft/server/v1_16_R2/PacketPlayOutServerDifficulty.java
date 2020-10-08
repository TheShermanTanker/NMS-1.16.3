/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutServerDifficulty
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private EnumDifficulty a;
/*    */   private boolean b;
/*    */   
/*    */   public PacketPlayOutServerDifficulty() {}
/*    */   
/*    */   public PacketPlayOutServerDifficulty(EnumDifficulty var0, boolean var1) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 23 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     this.a = EnumDifficulty.getById(var0.readUnsignedByte());
/* 29 */     this.b = var0.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 34 */     var0.writeByte(this.a.a());
/* 35 */     var0.writeBoolean(this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutServerDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */