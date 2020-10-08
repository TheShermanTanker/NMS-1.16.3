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
/*    */ public class PacketPlayOutCustomSoundEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private MinecraftKey a;
/*    */   private SoundCategory b;
/*    */   private int c;
/* 17 */   private int d = Integer.MAX_VALUE;
/*    */   
/*    */   private int e;
/*    */   
/*    */   private float f;
/*    */   
/*    */   private float g;
/*    */   
/*    */   public PacketPlayOutCustomSoundEffect(MinecraftKey var0, SoundCategory var1, Vec3D var2, float var3, float var4) {
/* 26 */     this.a = var0;
/* 27 */     this.b = var1;
/* 28 */     this.c = (int)(var2.x * 8.0D);
/* 29 */     this.d = (int)(var2.y * 8.0D);
/* 30 */     this.e = (int)(var2.z * 8.0D);
/* 31 */     this.f = var3;
/* 32 */     this.g = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 37 */     this.a = var0.p();
/* 38 */     this.b = var0.<SoundCategory>a(SoundCategory.class);
/* 39 */     this.c = var0.readInt();
/* 40 */     this.d = var0.readInt();
/* 41 */     this.e = var0.readInt();
/* 42 */     this.f = var0.readFloat();
/* 43 */     this.g = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 48 */     var0.a(this.a);
/* 49 */     var0.a(this.b);
/* 50 */     var0.writeInt(this.c);
/* 51 */     var0.writeInt(this.d);
/* 52 */     var0.writeInt(this.e);
/* 53 */     var0.writeFloat(this.f);
/* 54 */     var0.writeFloat(this.g);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut var0) {
/* 87 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public PacketPlayOutCustomSoundEffect() {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCustomSoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */