/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutNamedSoundEffect
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private SoundEffect a;
/*    */   private SoundCategory b;
/*    */   private int c;
/*    */   private int d;
/*    */   private int e;
/*    */   private float f;
/*    */   private float g;
/*    */   
/*    */   public PacketPlayOutNamedSoundEffect() {}
/*    */   
/*    */   public PacketPlayOutNamedSoundEffect(SoundEffect var0, SoundCategory var1, double var2, double var4, double var6, float var8, float var9) {
/* 27 */     Validate.notNull(var0, "sound", new Object[0]);
/* 28 */     this.a = var0;
/* 29 */     this.b = var1;
/* 30 */     this.c = (int)(var2 * 8.0D);
/* 31 */     this.d = (int)(var4 * 8.0D);
/* 32 */     this.e = (int)(var6 * 8.0D);
/* 33 */     this.f = var8;
/* 34 */     this.g = var9;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 39 */     this.a = IRegistry.SOUND_EVENT.fromId(var0.i());
/* 40 */     this.b = var0.<SoundCategory>a(SoundCategory.class);
/* 41 */     this.c = var0.readInt();
/* 42 */     this.d = var0.readInt();
/* 43 */     this.e = var0.readInt();
/* 44 */     this.f = var0.readFloat();
/* 45 */     this.g = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 50 */     var0.d(IRegistry.SOUND_EVENT.a(this.a));
/* 51 */     var0.a(this.b);
/* 52 */     var0.writeInt(this.c);
/* 53 */     var0.writeInt(this.d);
/* 54 */     var0.writeInt(this.e);
/* 55 */     var0.writeFloat(this.f);
/* 56 */     var0.writeFloat(this.g);
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
/* 89 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutNamedSoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */