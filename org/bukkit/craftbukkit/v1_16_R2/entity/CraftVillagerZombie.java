/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import java.util.Locale;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*    */ import net.minecraft.server.v1_16_R2.EntityZombieVillager;
/*    */ import net.minecraft.server.v1_16_R2.IRegistry;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.VillagerType;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Villager;
/*    */ import org.bukkit.entity.ZombieVillager;
/*    */ 
/*    */ public class CraftVillagerZombie extends CraftZombie implements ZombieVillager {
/*    */   public CraftVillagerZombie(CraftServer server, EntityZombieVillager entity) {
/* 22 */     super(server, (EntityZombie)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityZombieVillager getHandle() {
/* 27 */     return (EntityZombieVillager)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     return "CraftVillagerZombie";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 37 */     return EntityType.ZOMBIE_VILLAGER;
/*    */   }
/*    */ 
/*    */   
/*    */   public Villager.Profession getVillagerProfession() {
/* 42 */     return Villager.Profession.valueOf(IRegistry.VILLAGER_PROFESSION.getKey(getHandle().getVillagerData().getProfession()).getKey().toUpperCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVillagerProfession(Villager.Profession profession) {
/* 47 */     Validate.notNull(profession);
/* 48 */     getHandle().setVillagerData(getHandle().getVillagerData().withProfession((VillagerProfession)IRegistry.VILLAGER_PROFESSION.get(new MinecraftKey(profession.name().toLowerCase(Locale.ROOT)))));
/*    */   }
/*    */ 
/*    */   
/*    */   public Villager.Type getVillagerType() {
/* 53 */     return Villager.Type.valueOf(IRegistry.VILLAGER_TYPE.getKey(getHandle().getVillagerData().getType()).getKey().toUpperCase(Locale.ROOT));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setVillagerType(Villager.Type type) {
/* 58 */     Validate.notNull(type);
/* 59 */     getHandle().setVillagerData(getHandle().getVillagerData().withType((VillagerType)IRegistry.VILLAGER_TYPE.get(CraftNamespacedKey.toMinecraft(type.getKey()))));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConverting() {
/* 64 */     return getHandle().isConverting();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConversionTime() {
/* 69 */     Preconditions.checkState(isConverting(), "Entity not converting");
/*    */     
/* 71 */     return (getHandle()).conversionTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversionTime(int time) {
/* 76 */     if (time < 0) {
/* 77 */       (getHandle()).conversionTime = -1;
/* 78 */       getHandle().getDataWatcher().set(EntityZombieVillager.CONVERTING, Boolean.valueOf(false));
/* 79 */       (getHandle()).persistent = false;
/* 80 */       (getHandle()).conversionPlayer = null;
/* 81 */       getHandle().removeEffect(MobEffects.INCREASE_DAMAGE, EntityPotionEffectEvent.Cause.CONVERSION);
/*    */     } else {
/* 83 */       getHandle().startConversion((UUID)null, time);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public OfflinePlayer getConversionPlayer() {
/* 89 */     return ((getHandle()).conversionPlayer == null) ? null : Bukkit.getOfflinePlayer((getHandle()).conversionPlayer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversionPlayer(OfflinePlayer conversionPlayer) {
/* 94 */     if (!isConverting())
/* 95 */       return;  (getHandle()).conversionPlayer = (conversionPlayer == null) ? null : conversionPlayer.getUniqueId();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftVillagerZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */