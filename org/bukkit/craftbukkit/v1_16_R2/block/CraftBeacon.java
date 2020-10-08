/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.server.v1_16_R2.ChestLock;
/*    */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*    */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBeacon;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Beacon;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.potion.PotionEffect;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class CraftBeacon
/*    */   extends CraftBlockEntityState<TileEntityBeacon> implements Beacon {
/*    */   public CraftBeacon(Block block) {
/* 21 */     super(block, TileEntityBeacon.class);
/*    */   }
/*    */   
/*    */   public CraftBeacon(Material material, TileEntityBeacon te) {
/* 25 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<LivingEntity> getEntitiesInRange() {
/* 30 */     TileEntity tileEntity = getTileEntityFromWorld();
/* 31 */     if (tileEntity instanceof TileEntityBeacon) {
/* 32 */       TileEntityBeacon beacon = (TileEntityBeacon)tileEntity;
/*    */       
/* 34 */       Collection<EntityHuman> nms = beacon.getHumansInRange();
/* 35 */       Collection<LivingEntity> bukkit = new ArrayList<>(nms.size());
/*    */       
/* 37 */       for (EntityHuman human : nms) {
/* 38 */         bukkit.add(human.getBukkitEntity());
/*    */       }
/*    */       
/* 41 */       return bukkit;
/*    */     } 
/*    */ 
/*    */     
/* 45 */     return new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTier() {
/* 50 */     return (getSnapshot()).levels;
/*    */   }
/*    */ 
/*    */   
/*    */   public PotionEffect getPrimaryEffect() {
/* 55 */     return getSnapshot().getPrimaryEffect();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPrimaryEffect(PotionEffectType effect) {
/* 60 */     (getSnapshot()).primaryEffect = (effect != null) ? MobEffectList.fromId(effect.getId()) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public PotionEffect getSecondaryEffect() {
/* 65 */     return getSnapshot().getSecondaryEffect();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSecondaryEffect(PotionEffectType effect) {
/* 70 */     (getSnapshot()).secondaryEffect = (effect != null) ? MobEffectList.fromId(effect.getId()) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCustomName() {
/* 75 */     TileEntityBeacon beacon = getSnapshot();
/* 76 */     return (beacon.customName != null) ? CraftChatMessage.fromComponent(beacon.customName) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustomName(String name) {
/* 81 */     getSnapshot().setCustomName(CraftChatMessage.fromStringOrNull(name));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 86 */     return !(getSnapshot()).chestLock.key.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLock() {
/* 91 */     return (getSnapshot()).chestLock.key;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLock(String key) {
/* 96 */     (getSnapshot()).chestLock = (key == null) ? ChestLock.a : new ChestLock(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */