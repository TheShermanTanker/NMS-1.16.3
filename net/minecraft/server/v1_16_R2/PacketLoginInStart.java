/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginInStart
/*    */   implements Packet<PacketLoginInListener>
/*    */ {
/*    */   private GameProfile a;
/*    */   
/*    */   public PacketLoginInStart() {}
/*    */   
/*    */   public PacketLoginInStart(GameProfile var0) {
/* 17 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 22 */     this.a = new GameProfile(null, var0.e(16));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 27 */     var0.a(this.a.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginInListener var0) {
/* 32 */     var0.a(this);
/*    */   }
/*    */   
/*    */   public GameProfile b() {
/* 36 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginInStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */