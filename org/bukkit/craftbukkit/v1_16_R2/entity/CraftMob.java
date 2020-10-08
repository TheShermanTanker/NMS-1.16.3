/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.destroystokyo.paper.entity.PaperPathfinder;
/*    */ import com.destroystokyo.paper.entity.Pathfinder;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.loot.LootTable;
/*    */ 
/*    */ public abstract class CraftMob extends CraftLivingEntity implements Mob {
/*    */   public CraftMob(CraftServer server, EntityInsentient entity) {
/* 14 */     super(server, (EntityLiving)entity);
/* 15 */     this.paperPathfinder = new PaperPathfinder(entity);
/*    */   }
/*    */   private final PaperPathfinder paperPathfinder;
/*    */   public Pathfinder getPathfinder() {
/* 19 */     return (Pathfinder)this.paperPathfinder;
/*    */   }
/*    */   public void setTarget(LivingEntity target) {
/* 22 */     EntityInsentient entity = getHandle();
/* 23 */     if (target == null) {
/* 24 */       entity.setGoalTarget(null, null, false);
/* 25 */     } else if (target instanceof CraftLivingEntity) {
/* 26 */       entity.setGoalTarget(((CraftLivingEntity)target).getHandle(), null, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CraftLivingEntity getTarget() {
/* 32 */     if (getHandle().getGoalTarget() == null) return null;
/*    */     
/* 34 */     return (CraftLivingEntity)getHandle().getGoalTarget().getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAware(boolean aware) {
/* 39 */     (getHandle()).aware = aware;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAware() {
/* 44 */     return (getHandle()).aware;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityInsentient getHandle() {
/* 49 */     return (EntityInsentient)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return "CraftMob";
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLootTable(LootTable table) {
/* 59 */     (getHandle()).lootTableKey = (table == null) ? null : CraftNamespacedKey.toMinecraft(table.getKey());
/*    */   }
/*    */ 
/*    */   
/*    */   public LootTable getLootTable() {
/* 64 */     if ((getHandle()).lootTableKey == null) {
/* 65 */       (getHandle()).lootTableKey = getHandle().getLootTable();
/*    */     }
/*    */     
/* 68 */     NamespacedKey key = CraftNamespacedKey.fromMinecraft((getHandle()).lootTableKey);
/* 69 */     return Bukkit.getLootTable(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSeed(long seed) {
/* 74 */     (getHandle()).lootTableSeed = seed;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSeed() {
/* 79 */     return (getHandle()).lootTableSeed;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInDaylight() {
/* 85 */     return getHandle().isInDaylight();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */