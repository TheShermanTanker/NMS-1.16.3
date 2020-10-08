/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegisteredListener
/*    */ {
/*    */   private final Listener listener;
/*    */   private final EventPriority priority;
/*    */   private final Plugin plugin;
/*    */   private final EventExecutor executor;
/*    */   private final boolean ignoreCancelled;
/*    */   
/*    */   public RegisteredListener(@NotNull Listener listener, @NotNull EventExecutor executor, @NotNull EventPriority priority, @NotNull Plugin plugin, boolean ignoreCancelled) {
/* 21 */     this.listener = listener;
/* 22 */     this.priority = priority;
/* 23 */     this.plugin = plugin;
/* 24 */     this.executor = executor;
/* 25 */     this.ignoreCancelled = ignoreCancelled;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Listener getListener() {
/* 35 */     return this.listener;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Plugin getPlugin() {
/* 45 */     return this.plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public EventPriority getPriority() {
/* 55 */     return this.priority;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void callEvent(@NotNull Event event) throws EventException {
/* 65 */     if (event instanceof Cancellable && (
/* 66 */       (Cancellable)event).isCancelled() && isIgnoringCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 70 */     this.executor.execute(this.listener, event);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isIgnoringCancelled() {
/* 79 */     return this.ignoreCancelled;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\RegisteredListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */