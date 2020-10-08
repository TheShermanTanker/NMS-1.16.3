/*    */ package com.destroystokyo.paper.event.executor;
/*    */ 
/*    */ import com.destroystokyo.paper.util.SneakyThrow;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.lang.invoke.MethodHandle;
/*    */ import java.lang.invoke.MethodHandles;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.EventExecutor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class StaticMethodHandleEventExecutor
/*    */   implements EventExecutor
/*    */ {
/*    */   private final Class<? extends Event> eventClass;
/*    */   private final MethodHandle handle;
/*    */   
/*    */   public StaticMethodHandleEventExecutor(@NotNull Class<? extends Event> eventClass, @NotNull Method m) {
/* 23 */     Preconditions.checkArgument(Modifier.isStatic(m.getModifiers()), "Not a static method: %s", m);
/* 24 */     Preconditions.checkArgument((eventClass != null), "eventClass is null");
/* 25 */     this.eventClass = eventClass;
/*    */     try {
/* 27 */       m.setAccessible(true);
/* 28 */       this.handle = MethodHandles.lookup().unreflect(m);
/* 29 */     } catch (IllegalAccessException e) {
/* 30 */       throw new AssertionError("Unable to set accessible", e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
/* 36 */     if (!this.eventClass.isInstance(event))
/*    */       return;  try {
/* 38 */       this.handle.invoke(event);
/* 39 */     } catch (Throwable throwable) {
/* 40 */       SneakyThrow.sneaky(throwable);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\executor\StaticMethodHandleEventExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */