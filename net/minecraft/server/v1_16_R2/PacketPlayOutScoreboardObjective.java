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
/*    */ public class PacketPlayOutScoreboardObjective
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private String a;
/*    */   private IChatBaseComponent b;
/*    */   private IScoreboardCriteria.EnumScoreboardHealthDisplay c;
/*    */   private int d;
/*    */   
/*    */   public PacketPlayOutScoreboardObjective() {}
/*    */   
/*    */   public PacketPlayOutScoreboardObjective(ScoreboardObjective var0, int var1) {
/* 25 */     this.a = var0.getName();
/* 26 */     this.b = var0.getDisplayName();
/* 27 */     this.c = var0.getRenderType();
/* 28 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 33 */     this.a = var0.e(16);
/* 34 */     this.d = var0.readByte();
/*    */     
/* 36 */     if (this.d == 0 || this.d == 2) {
/* 37 */       this.b = var0.h();
/* 38 */       this.c = var0.<IScoreboardCriteria.EnumScoreboardHealthDisplay>a(IScoreboardCriteria.EnumScoreboardHealthDisplay.class);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 44 */     var0.a(this.a);
/* 45 */     var0.writeByte(this.d);
/*    */     
/* 47 */     if (this.d == 0 || this.d == 2) {
/* 48 */       var0.a(this.b);
/* 49 */       var0.a(this.c);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 55 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutScoreboardObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */