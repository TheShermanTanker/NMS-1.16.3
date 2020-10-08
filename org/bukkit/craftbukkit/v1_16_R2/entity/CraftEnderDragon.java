/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.DragonControllerPhase;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityComplexPart;
/*    */ import net.minecraft.server.v1_16_R2.EntityEnderDragon;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.boss.DragonBattle;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.boss.CraftDragonBattle;
/*    */ import org.bukkit.entity.ComplexEntityPart;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftEnderDragon extends CraftComplexLivingEntity implements EnderDragon {
/*    */   public CraftEnderDragon(CraftServer server, EntityEnderDragon entity) {
/* 20 */     super(server, (EntityInsentient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<ComplexEntityPart> getParts() {
/* 25 */     ImmutableSet.Builder<ComplexEntityPart> builder = ImmutableSet.builder();
/*    */     
/* 27 */     for (EntityComplexPart part : (getHandle()).children) {
/* 28 */       builder.add(part.getBukkitEntity());
/*    */     }
/*    */     
/* 31 */     return (Set<ComplexEntityPart>)builder.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderDragon getHandle() {
/* 36 */     return (EntityEnderDragon)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 41 */     return "CraftEnderDragon";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 46 */     return EntityType.ENDER_DRAGON;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnderDragon.Phase getPhase() {
/* 51 */     return EnderDragon.Phase.values()[((Integer)getHandle().getDataWatcher().get(EntityEnderDragon.PHASE)).intValue()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPhase(EnderDragon.Phase phase) {
/* 56 */     getHandle().getDragonControllerManager().setControllerPhase(getMinecraftPhase(phase));
/*    */   }
/*    */   
/*    */   public static EnderDragon.Phase getBukkitPhase(DragonControllerPhase phase) {
/* 60 */     return EnderDragon.Phase.values()[phase.b()];
/*    */   }
/*    */   
/*    */   public static DragonControllerPhase getMinecraftPhase(EnderDragon.Phase phase) {
/* 64 */     return DragonControllerPhase.getById(phase.ordinal());
/*    */   }
/*    */ 
/*    */   
/*    */   public BossBar getBossBar() {
/* 69 */     return getDragonBattle().getBossBar();
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonBattle getDragonBattle() {
/* 74 */     return (DragonBattle)new CraftDragonBattle(getHandle().getEnderDragonBattle());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getDeathAnimationTicks() {
/* 79 */     return (getHandle()).deathAnimationTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderDragon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */