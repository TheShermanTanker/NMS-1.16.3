/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInClientCommand
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private EnumClientCommand a;
/*    */   
/*    */   public PacketPlayInClientCommand() {}
/*    */   
/*    */   public PacketPlayInClientCommand(EnumClientCommand var0) {
/* 15 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 20 */     this.a = var0.<EnumClientCommand>a(EnumClientCommand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 25 */     var0.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 30 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public EnumClientCommand b() {
/* 34 */     return this.a;
/*    */   }
/*    */   
/*    */   public enum EnumClientCommand {
/* 38 */     PERFORM_RESPAWN,
/* 39 */     REQUEST_STATS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInClientCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */