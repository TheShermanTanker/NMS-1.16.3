/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInDifficultyChange
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private EnumDifficulty a;
/*    */   
/*    */   public PacketPlayInDifficultyChange() {}
/*    */   
/*    */   public PacketPlayInDifficultyChange(EnumDifficulty var0) {
/* 16 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 21 */     var0.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 26 */     this.a = EnumDifficulty.getById(var0.readUnsignedByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 31 */     var0.writeByte(this.a.a());
/*    */   }
/*    */   
/*    */   public EnumDifficulty b() {
/* 35 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInDifficultyChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */