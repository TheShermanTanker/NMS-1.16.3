/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseZombie;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ import org.bukkit.entity.ZombieHorse;
/*    */ 
/*    */ public class CraftZombieHorse extends CraftAbstractHorse implements ZombieHorse {
/*    */   public CraftZombieHorse(CraftServer server, EntityHorseZombie entity) {
/* 12 */     super(server, (EntityHorseAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 17 */     return "CraftZombieHorse";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 22 */     return EntityType.ZOMBIE_HORSE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 27 */     return Horse.Variant.UNDEAD_HORSE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftZombieHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */