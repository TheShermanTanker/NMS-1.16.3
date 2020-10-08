/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutScoreboardDisplayObjective
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private String b;
/*    */   
/*    */   public PacketPlayOutScoreboardDisplayObjective() {}
/*    */   
/*    */   public PacketPlayOutScoreboardDisplayObjective(int var0, @Nullable ScoreboardObjective var1) {
/* 19 */     this.a = var0;
/*    */     
/* 21 */     if (var1 == null) {
/* 22 */       this.b = "";
/*    */     } else {
/* 24 */       this.b = var1.getName();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 30 */     this.a = var0.readByte();
/* 31 */     this.b = var0.e(16);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.writeByte(this.a);
/* 37 */     var0.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 42 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutScoreboardDisplayObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */