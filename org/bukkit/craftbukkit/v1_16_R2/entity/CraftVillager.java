/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import com.destroystokyo.paper.entity.villager.Reputation;
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityVillager;
/*     */ import net.minecraft.server.v1_16_R2.EntityVillagerAbstract;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.Reputation;
/*     */ import net.minecraft.server.v1_16_R2.VillagerProfession;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*     */ import org.bukkit.entity.Villager;
/*     */ 
/*     */ public class CraftVillager extends CraftAbstractVillager implements Villager {
/*     */   public CraftVillager(CraftServer server, EntityVillager entity) {
/*  25 */     super(server, (EntityVillagerAbstract)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityVillager getHandle() {
/*  30 */     return (EntityVillager)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  35 */     return "CraftVillager";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  40 */     return EntityType.VILLAGER;
/*     */   }
/*     */ 
/*     */   
/*     */   public Villager.Profession getProfession() {
/*  45 */     return nmsToBukkitProfession(getHandle().getVillagerData().getProfession());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProfession(Villager.Profession profession) {
/*  50 */     Validate.notNull(profession);
/*  51 */     getHandle().setVillagerData(getHandle().getVillagerData().withProfession(bukkitToNmsProfession(profession)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Villager.Type getVillagerType() {
/*  56 */     return Villager.Type.valueOf(IRegistry.VILLAGER_TYPE.getKey(getHandle().getVillagerData().getType()).getKey().toUpperCase(Locale.ROOT));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerType(Villager.Type type) {
/*  61 */     Validate.notNull(type);
/*  62 */     getHandle().setVillagerData(getHandle().getVillagerData().withType((VillagerType)IRegistry.VILLAGER_TYPE.get(CraftNamespacedKey.toMinecraft(type.getKey()))));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillagerLevel() {
/*  67 */     return getHandle().getVillagerData().getLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerLevel(int level) {
/*  72 */     Preconditions.checkArgument((1 <= level && level <= 5), "level must be between [1, 5]");
/*     */     
/*  74 */     getHandle().setVillagerData(getHandle().getVillagerData().withLevel(level));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getVillagerExperience() {
/*  79 */     return getHandle().getExperience();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVillagerExperience(int experience) {
/*  84 */     Preconditions.checkArgument((experience >= 0), "Experience must be positive");
/*     */     
/*  86 */     getHandle().setExperience(experience);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRestocksToday() {
/*  92 */     return getHandle().getRestocksToday();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestocksToday(int restocksToday) {
/*  97 */     getHandle().setRestocksToday(restocksToday);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean sleep(Location location) {
/* 103 */     Preconditions.checkArgument((location != null), "Location cannot be null");
/* 104 */     Preconditions.checkArgument((location.getWorld() != null), "Location needs to be in a world");
/* 105 */     Preconditions.checkArgument(location.getWorld().equals(getWorld()), "Cannot sleep across worlds");
/*     */     
/* 107 */     BlockPosition position = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/* 108 */     IBlockData iblockdata = (getHandle()).world.getType(position);
/* 109 */     if (!(iblockdata.getBlock() instanceof net.minecraft.server.v1_16_R2.BlockBed)) {
/* 110 */       return false;
/*     */     }
/*     */     
/* 113 */     getHandle().entitySleep(position);
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void wakeup() {
/* 119 */     Preconditions.checkState(isSleeping(), "Cannot wakeup if not sleeping");
/*     */     
/* 121 */     getHandle().entityWakeup();
/*     */   }
/*     */   
/*     */   public static Villager.Profession nmsToBukkitProfession(VillagerProfession nms) {
/* 125 */     return Villager.Profession.valueOf(IRegistry.VILLAGER_PROFESSION.getKey(nms).getKey().toUpperCase(Locale.ROOT));
/*     */   }
/*     */   
/*     */   public static VillagerProfession bukkitToNmsProfession(Villager.Profession bukkit) {
/* 129 */     return (VillagerProfession)IRegistry.VILLAGER_PROFESSION.get(CraftNamespacedKey.toMinecraft(bukkit.getKey()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Reputation getReputation(UUID uniqueId) {
/* 135 */     Reputation.a rep = (Reputation.a)getHandle().getReputation().getReputations().get(uniqueId);
/* 136 */     if (rep == null) {
/* 137 */       return new Reputation(Maps.newHashMap());
/*     */     }
/*     */     
/* 140 */     return rep.getPaperReputation();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<UUID, Reputation> getReputations() {
/* 145 */     return (Map<UUID, Reputation>)getHandle().getReputation().getReputations().entrySet()
/* 146 */       .stream()
/* 147 */       .collect(Collectors.toMap(Map.Entry::getKey, entry -> ((Reputation.a)entry.getValue()).getPaperReputation()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReputation(UUID uniqueId, Reputation reputation) {
/* 153 */     Reputation.a nmsReputation = getHandle().getReputation().getReputations().computeIfAbsent(uniqueId, key -> new Reputation.a());
/*     */ 
/*     */ 
/*     */     
/* 157 */     nmsReputation.assignFromPaperReputation(reputation);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReputations(Map<UUID, Reputation> reputations) {
/* 162 */     for (Map.Entry<UUID, Reputation> entry : reputations.entrySet()) {
/* 163 */       setReputation(entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearReputations() {
/* 169 */     getHandle().getReputation().getReputations().clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */