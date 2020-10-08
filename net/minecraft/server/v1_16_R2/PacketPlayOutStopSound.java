/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutStopSound
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private MinecraftKey a;
/*    */   private SoundCategory b;
/*    */   
/*    */   public PacketPlayOutStopSound() {}
/*    */   
/*    */   public PacketPlayOutStopSound(@Nullable MinecraftKey var0, @Nullable SoundCategory var1) {
/* 22 */     this.a = var0;
/* 23 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 28 */     int var1 = var0.readByte();
/* 29 */     if ((var1 & 0x1) > 0) {
/* 30 */       this.b = var0.<SoundCategory>a(SoundCategory.class);
/*    */     }
/* 32 */     if ((var1 & 0x2) > 0) {
/* 33 */       this.a = var0.p();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 39 */     if (this.b != null) {
/* 40 */       if (this.a != null) {
/* 41 */         var0.writeByte(3);
/* 42 */         var0.a(this.b);
/* 43 */         var0.a(this.a);
/*    */       } else {
/* 45 */         var0.writeByte(1);
/* 46 */         var0.a(this.b);
/*    */       }
/*    */     
/* 49 */     } else if (this.a != null) {
/* 50 */       var0.writeByte(2);
/* 51 */       var0.a(this.a);
/*    */     } else {
/* 53 */       var0.writeByte(0);
/*    */     } 
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
/*    */   public void a(PacketListenerPlayOut var0) {
/* 70 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutStopSound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */