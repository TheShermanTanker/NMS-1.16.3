/*    */ package com.destroystokyo.paper.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.SoundEffectType;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftSound;
/*    */ 
/*    */ public class CraftBlockSoundGroup implements BlockSoundGroup {
/*    */   private final SoundEffectType soundEffectType;
/*    */   
/*    */   public CraftBlockSoundGroup(SoundEffectType soundEffectType) {
/* 11 */     this.soundEffectType = soundEffectType;
/*    */   }
/*    */ 
/*    */   
/*    */   public Sound getBreakSound() {
/* 16 */     return CraftSound.getSoundByEffect(this.soundEffectType.getBreakSound());
/*    */   }
/*    */ 
/*    */   
/*    */   public Sound getStepSound() {
/* 21 */     return CraftSound.getSoundByEffect(this.soundEffectType.getStepSound());
/*    */   }
/*    */ 
/*    */   
/*    */   public Sound getPlaceSound() {
/* 26 */     return CraftSound.getSoundByEffect(this.soundEffectType.getPlaceSound());
/*    */   }
/*    */ 
/*    */   
/*    */   public Sound getHitSound() {
/* 31 */     return CraftSound.getSoundByEffect(this.soundEffectType.getHitSound());
/*    */   }
/*    */ 
/*    */   
/*    */   public Sound getFallSound() {
/* 36 */     return CraftSound.getSoundByEffect(this.soundEffectType.getFallSound());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\block\CraftBlockSoundGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */