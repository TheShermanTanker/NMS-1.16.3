/*    */ package org.bukkit.craftbukkit.v1_16_R2.boss;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BossBattleCustom;
/*    */ import net.minecraft.server.v1_16_R2.BossBattleServer;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.boss.KeyedBossBar;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ 
/*    */ public class CraftKeyedBossbar extends CraftBossBar implements KeyedBossBar {
/*    */   public CraftKeyedBossbar(BossBattleCustom bossBattleCustom) {
/* 11 */     super((BossBattleServer)bossBattleCustom);
/*    */   }
/*    */ 
/*    */   
/*    */   public NamespacedKey getKey() {
/* 16 */     return CraftNamespacedKey.fromMinecraft(getHandle().getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public BossBattleCustom getHandle() {
/* 21 */     return (BossBattleCustom)super.getHandle();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\boss\CraftKeyedBossbar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */