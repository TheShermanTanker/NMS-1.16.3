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
/*    */ 
/*    */ public class PacketPlayInSetCommandBlock
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private String b;
/*    */   private boolean c;
/*    */   private boolean d;
/*    */   private boolean e;
/*    */   private TileEntityCommand.Type f;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 36 */     this.a = var0.e();
/* 37 */     this.b = var0.e(32767);
/* 38 */     this.f = var0.<TileEntityCommand.Type>a(TileEntityCommand.Type.class);
/* 39 */     int var1 = var0.readByte();
/* 40 */     this.c = ((var1 & 0x1) != 0);
/* 41 */     this.d = ((var1 & 0x2) != 0);
/* 42 */     this.e = ((var1 & 0x4) != 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 47 */     var0.a(this.a);
/* 48 */     var0.a(this.b);
/* 49 */     var0.a(this.f);
/* 50 */     int var1 = 0;
/* 51 */     if (this.c) {
/* 52 */       var1 |= 0x1;
/*    */     }
/* 54 */     if (this.d) {
/* 55 */       var1 |= 0x2;
/*    */     }
/* 57 */     if (this.e) {
/* 58 */       var1 |= 0x4;
/*    */     }
/* 60 */     var0.writeByte(var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 65 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 69 */     return this.a;
/*    */   }
/*    */   
/*    */   public String c() {
/* 73 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 77 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 81 */     return this.d;
/*    */   }
/*    */   
/*    */   public boolean f() {
/* 85 */     return this.e;
/*    */   }
/*    */   
/*    */   public TileEntityCommand.Type g() {
/* 89 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSetCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */