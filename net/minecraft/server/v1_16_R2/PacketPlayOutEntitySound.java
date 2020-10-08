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
/*    */ public class PacketPlayOutEntitySound
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private SoundEffect a;
/*    */   private SoundCategory b;
/*    */   private int c;
/*    */   private float d;
/*    */   private float e;
/*    */   
/*    */   public PacketPlayOutEntitySound() {}
/*    */   
/*    */   public PacketPlayOutEntitySound(SoundEffect var0, SoundCategory var1, Entity var2, float var3, float var4) {
/* 24 */     Validate.notNull(var0, "sound", new Object[0]);
/* 25 */     this.a = var0;
/* 26 */     this.b = var1;
/* 27 */     this.c = var2.getId();
/* 28 */     this.d = var3;
/* 29 */     this.e = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 34 */     this.a = IRegistry.SOUND_EVENT.fromId(var0.i());
/* 35 */     this.b = var0.<SoundCategory>a(SoundCategory.class);
/* 36 */     this.c = var0.i();
/* 37 */     this.d = var0.readFloat();
/* 38 */     this.e = var0.readFloat();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 43 */     var0.d(IRegistry.SOUND_EVENT.a(this.a));
/* 44 */     var0.a(this.b);
/* 45 */     var0.d(this.c);
/* 46 */     var0.writeFloat(this.d);
/* 47 */     var0.writeFloat(this.e);
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
/*    */   public void a(PacketListenerPlayOut var0) {
/* 72 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutEntitySound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */