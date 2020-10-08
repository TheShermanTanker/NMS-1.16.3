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
/*    */ public class PacketPlayInSetJigsaw
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private BlockPosition a;
/*    */   private MinecraftKey b;
/*    */   private MinecraftKey c;
/*    */   private MinecraftKey d;
/*    */   private String e;
/*    */   private TileEntityJigsaw.JointType f;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 33 */     this.a = var0.e();
/* 34 */     this.b = var0.p();
/* 35 */     this.c = var0.p();
/* 36 */     this.d = var0.p();
/* 37 */     this.e = var0.e(32767);
/* 38 */     this.f = TileEntityJigsaw.JointType.a(var0.e(32767)).orElse(TileEntityJigsaw.JointType.ALIGNED);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 43 */     var0.a(this.a);
/* 44 */     var0.a(this.b);
/* 45 */     var0.a(this.c);
/* 46 */     var0.a(this.d);
/* 47 */     var0.a(this.e);
/* 48 */     var0.a(this.f.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 53 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 57 */     return this.a;
/*    */   }
/*    */   
/*    */   public MinecraftKey c() {
/* 61 */     return this.b;
/*    */   }
/*    */   
/*    */   public MinecraftKey d() {
/* 65 */     return this.c;
/*    */   }
/*    */   
/*    */   public MinecraftKey e() {
/* 69 */     return this.d;
/*    */   }
/*    */   
/*    */   public String f() {
/* 73 */     return this.e;
/*    */   }
/*    */   
/*    */   public TileEntityJigsaw.JointType g() {
/* 77 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInSetJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */