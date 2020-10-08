/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Spellcaster;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class EntitySpellCastEvent
/*    */   extends EntityEvent
/*    */   implements Cancellable
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled = false;
/*    */   private final Spellcaster.Spell spell;
/*    */   
/*    */   public EntitySpellCastEvent(@NotNull Spellcaster what, @NotNull Spellcaster.Spell spell) {
/* 19 */     super((Entity)what);
/* 20 */     this.spell = spell;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Spellcaster getEntity() {
/* 26 */     return (Spellcaster)this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Spellcaster.Spell getSpell() {
/* 39 */     return this.spell;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 44 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 49 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 55 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 60 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntitySpellCastEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */