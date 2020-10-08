/*     */ package org.bukkit.event;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Event
/*     */ {
/*     */   private String name;
/*     */   private final boolean async;
/*     */   
/*     */   public Event() {
/*  24 */     this(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Event(boolean isAsync) {
/*  35 */     this.async = isAsync;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean callEvent() {
/*  45 */     Bukkit.getPluginManager().callEvent(this);
/*  46 */     if (this instanceof Cancellable) {
/*  47 */       return !((Cancellable)this).isCancelled();
/*     */     }
/*  49 */     return true;
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
/*     */   
/*     */   @NotNull
/*     */   public String getEventName() {
/*  63 */     if (this.name == null) {
/*  64 */       this.name = getClass().getSimpleName();
/*     */     }
/*  66 */     return this.name;
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
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public abstract HandlerList getHandlers();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isAsynchronous() {
/*  95 */     return this.async;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Result
/*     */   {
/* 105 */     DENY,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     DEFAULT,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     ALLOW;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */