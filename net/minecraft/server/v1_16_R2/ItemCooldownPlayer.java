/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class ItemCooldownPlayer
/*    */   extends ItemCooldown
/*    */ {
/*    */   private final EntityPlayer a;
/*    */   
/*    */   public ItemCooldownPlayer(EntityPlayer var0) {
/* 10 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(Item var0, int var1) {
/* 15 */     super.b(var0, var1);
/* 16 */     this.a.playerConnection.sendPacket(new PacketPlayOutSetCooldown(var0, var1));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(Item var0) {
/* 21 */     super.c(var0);
/* 22 */     this.a.playerConnection.sendPacket(new PacketPlayOutSetCooldown(var0, 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemCooldownPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */