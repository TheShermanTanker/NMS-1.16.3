/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import java.util.Map;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityDamageByEntityEvent
/*    */   extends EntityDamageEvent
/*    */ {
/*    */   private final Entity damager;
/*    */   
/*    */   public EntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, double damage) {
/* 15 */     super(damagee, cause, damage);
/* 16 */     this.damager = damager;
/*    */   }
/*    */   
/*    */   public EntityDamageByEntityEvent(@NotNull Entity damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull Map<EntityDamageEvent.DamageModifier, Double> modifiers, @NotNull Map<EntityDamageEvent.DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
/* 20 */     super(damagee, cause, modifiers, modifierFunctions);
/* 21 */     this.damager = damager;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getDamager() {
/* 31 */     return this.damager;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityDamageByEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */