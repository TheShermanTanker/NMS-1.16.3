/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class EntityTransformEvent
/*     */   extends EntityEvent
/*     */   implements Cancellable
/*     */ {
/*  15 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancelled;
/*     */   private final Entity converted;
/*     */   private final List<Entity> convertedList;
/*     */   private final TransformReason transformReason;
/*     */   
/*     */   public EntityTransformEvent(@NotNull Entity original, @NotNull List<Entity> convertedList, @NotNull TransformReason transformReason) {
/*  22 */     super(original);
/*  23 */     this.convertedList = Collections.unmodifiableList(convertedList);
/*  24 */     this.converted = convertedList.get(0);
/*  25 */     this.transformReason = transformReason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Entity getTransformedEntity() {
/*  38 */     return this.converted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Entity> getTransformedEntities() {
/*  48 */     return this.convertedList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public TransformReason getTransformReason() {
/*  58 */     return this.transformReason;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  63 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  68 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  74 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  79 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TransformReason
/*     */   {
/*  86 */     CURED,
/*     */ 
/*     */ 
/*     */     
/*  90 */     INFECTION,
/*     */ 
/*     */ 
/*     */     
/*  94 */     DROWNED,
/*     */ 
/*     */ 
/*     */     
/*  98 */     SHEARED,
/*     */ 
/*     */ 
/*     */     
/* 102 */     LIGHTNING,
/*     */ 
/*     */ 
/*     */     
/* 106 */     SPLIT,
/*     */ 
/*     */ 
/*     */     
/* 110 */     UNKNOWN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityTransformEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */