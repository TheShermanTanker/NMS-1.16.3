/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutScoreboardScore
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/* 14 */   private String a = "";
/*    */   
/*    */   @Nullable
/*    */   private String b;
/*    */   
/*    */   private int c;
/*    */   
/*    */   private ScoreboardServer.Action d;
/*    */   
/*    */   public PacketPlayOutScoreboardScore(ScoreboardServer.Action var0, @Nullable String var1, String var2, int var3) {
/* 24 */     if (var0 != ScoreboardServer.Action.REMOVE && var1 == null) {
/* 25 */       throw new IllegalArgumentException("Need an objective name");
/*    */     }
/* 27 */     this.a = var2;
/* 28 */     this.b = var1;
/* 29 */     this.c = var3;
/* 30 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 35 */     this.a = var0.e(40);
/* 36 */     this.d = var0.<ScoreboardServer.Action>a(ScoreboardServer.Action.class);
/* 37 */     String var1 = var0.e(16);
/* 38 */     this.b = Objects.equals(var1, "") ? null : var1;
/*    */     
/* 40 */     if (this.d != ScoreboardServer.Action.REMOVE) {
/* 41 */       this.c = var0.i();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 47 */     var0.a(this.a);
/* 48 */     var0.a(this.d);
/* 49 */     var0.a((this.b == null) ? "" : this.b);
/*    */     
/* 51 */     if (this.d != ScoreboardServer.Action.REMOVE) {
/* 52 */       var0.d(this.c);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 58 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public PacketPlayOutScoreboardScore() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutScoreboardScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */