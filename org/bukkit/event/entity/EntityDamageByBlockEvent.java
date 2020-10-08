/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import java.util.Map;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class EntityDamageByBlockEvent
/*    */   extends EntityDamageEvent
/*    */ {
/*    */   private final Block damager;
/*    */   
/*    */   public EntityDamageByBlockEvent(@Nullable Block damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, double damage) {
/* 17 */     super(damagee, cause, damage);
/* 18 */     this.damager = damager;
/*    */   }
/*    */   
/*    */   public EntityDamageByBlockEvent(@Nullable Block damager, @NotNull Entity damagee, @NotNull EntityDamageEvent.DamageCause cause, @NotNull Map<EntityDamageEvent.DamageModifier, Double> modifiers, @NotNull Map<EntityDamageEvent.DamageModifier, ? extends Function<? super Double, Double>> modifierFunctions) {
/* 22 */     super(damagee, cause, modifiers, modifierFunctions);
/* 23 */     this.damager = damager;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Block getDamager() {
/* 33 */     return this.damager;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityDamageByBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */