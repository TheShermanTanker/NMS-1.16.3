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
/*    */ public class PacketPlayInEntityAction
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private EnumPlayerAction animation;
/*    */   private int c;
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 29 */     this.a = var0.i();
/* 30 */     this.animation = var0.<EnumPlayerAction>a(EnumPlayerAction.class);
/* 31 */     this.c = var0.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 36 */     var0.d(this.a);
/* 37 */     var0.a(this.animation);
/* 38 */     var0.d(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn var0) {
/* 43 */     var0.a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumPlayerAction c() {
/* 51 */     return this.animation;
/*    */   }
/*    */   
/*    */   public int d() {
/* 55 */     return this.c;
/*    */   }
/*    */   
/*    */   public enum EnumPlayerAction {
/* 59 */     PRESS_SHIFT_KEY,
/* 60 */     RELEASE_SHIFT_KEY,
/* 61 */     STOP_SLEEPING,
/* 62 */     START_SPRINTING,
/* 63 */     STOP_SPRINTING,
/* 64 */     START_RIDING_JUMP,
/* 65 */     STOP_RIDING_JUMP,
/* 66 */     OPEN_INVENTORY,
/* 67 */     START_FALL_FLYING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayInEntityAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */