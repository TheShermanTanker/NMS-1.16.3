/*    */ package com.destroystokyo.paper.event.executor;
/*    */ 
/*    */ import com.destroystokyo.paper.util.SneakyThrow;
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.reflect.Method;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.EventExecutor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class MethodHandleEventExecutor
/*    */   implements EventExecutor {
/*    */   private final Class<? extends Event> eventClass;
/*    */   private final MethodHandle handle;
/*    */   
/*    */   public MethodHandleEventExecutor(@NotNull Class<? extends Event> eventClass, @NotNull MethodHandle handle) {
/* 19 */     this.eventClass = eventClass;
/* 20 */     this.handle = handle;
/*    */   }
/*    */   
/*    */   public MethodHandleEventExecutor(@NotNull Class<? extends Event> eventClass, @NotNull Method m) {
/* 24 */     this.eventClass = eventClass;
/*    */     try {
/* 26 */       m.setAccessible(true);
/* 27 */       this.handle = MethodHandles.lookup().unreflect(m);
/* 28 */     } catch (IllegalAccessException e) {
/* 29 */       throw new AssertionError("Unable to set accessible", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
/* 35 */     if (!this.eventClass.isInstance(event))
/*    */       return;  try {
/* 37 */       this.handle.invoke(listener, event);
/* 38 */     } catch (Throwable t) {
/* 39 */       SneakyThrow.sneaky(t);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\executor\MethodHandleEventExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */