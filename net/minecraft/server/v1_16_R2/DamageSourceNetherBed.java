/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DamageSourceNetherBed
/*    */   extends DamageSource
/*    */ {
/*    */   protected DamageSourceNetherBed() {
/* 13 */     super("badRespawnPoint");
/* 14 */     r();
/* 15 */     setExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving var0) {
/* 20 */     IChatBaseComponent var1 = ChatComponentUtils.a(new ChatMessage("death.attack.badRespawnPoint.link")).format(var0 -> var0.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.OPEN_URL, "https://bugs.mojang.com/browse/MCPE-28723")).setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatComponentText("MCPE-28723"))));
/*    */ 
/*    */ 
/*    */     
/* 24 */     return new ChatMessage("death.attack.badRespawnPoint.message", new Object[] { var0.getScoreboardDisplayName(), var1 });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DamageSourceNetherBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */