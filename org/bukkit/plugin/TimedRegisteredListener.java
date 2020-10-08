/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class TimedRegisteredListener
/*    */   extends RegisteredListener
/*    */ {
/*    */   private int count;
/*    */   private long totalTime;
/*    */   private Class<? extends Event> eventClass;
/*    */   private boolean multiple = false;
/*    */   
/*    */   public TimedRegisteredListener(@NotNull Listener pluginListener, @NotNull EventExecutor eventExecutor, @NotNull EventPriority eventPriority, @NotNull Plugin registeredPlugin, boolean listenCancelled) {
/* 20 */     super(pluginListener, eventExecutor, eventPriority, registeredPlugin, listenCancelled);
/*    */   }
/*    */ 
/*    */   
/*    */   public void callEvent(@NotNull Event event) throws EventException {
/* 25 */     if (event.isAsynchronous()) {
/* 26 */       super.callEvent(event);
/*    */       return;
/*    */     } 
/* 29 */     this.count++;
/* 30 */     Class<? extends Event> newEventClass = (Class)event.getClass();
/* 31 */     if (this.eventClass == null) {
/* 32 */       this.eventClass = newEventClass;
/* 33 */     } else if (!this.eventClass.equals(newEventClass)) {
/* 34 */       this.multiple = true;
/* 35 */       this.eventClass = getCommonSuperclass(newEventClass, this.eventClass).asSubclass(Event.class);
/*    */     } 
/* 37 */     long start = System.nanoTime();
/* 38 */     super.callEvent(event);
/* 39 */     this.totalTime += System.nanoTime() - start;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   private static Class<?> getCommonSuperclass(@NotNull Class<?> class1, @NotNull Class<?> class2) {
/* 44 */     while (!class1.isAssignableFrom(class2)) {
/* 45 */       class1 = class1.getSuperclass();
/*    */     }
/* 47 */     return class1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {
/* 54 */     this.count = 0;
/* 55 */     this.totalTime = 0L;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCount() {
/* 64 */     return this.count;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getTotalTime() {
/* 73 */     return this.totalTime;
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
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Class<? extends Event> getEventClass() {
/* 89 */     return this.eventClass;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMultiple() {
/* 99 */     return this.multiple;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\TimedRegisteredListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */