/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutSuccess
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private GameProfile a;
/*    */   
/*    */   public PacketLoginOutSuccess() {}
/*    */   
/*    */   public PacketLoginOutSuccess(GameProfile var0) {
/* 19 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer var0) throws IOException {
/* 24 */     int[] var1 = new int[4];
/* 25 */     for (int i = 0; i < var1.length; i++) {
/* 26 */       var1[i] = var0.readInt();
/*    */     }
/* 28 */     UUID var2 = MinecraftSerializableUUID.a(var1);
/* 29 */     String var3 = var0.e(16);
/* 30 */     this.a = new GameProfile(var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer var0) throws IOException {
/* 35 */     for (int var4 : MinecraftSerializableUUID.a(this.a.getId())) {
/* 36 */       var0.writeInt(var4);
/*    */     }
/* 38 */     var0.a(this.a.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener var0) {
/* 43 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutSuccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */