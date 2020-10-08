/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import com.destroystokyo.paper.event.executor.MethodHandleEventExecutor;
/*    */ import com.destroystokyo.paper.event.executor.StaticMethodHandleEventExecutor;
/*    */ import com.destroystokyo.paper.event.executor.asm.ASMEventExecutorGenerator;
/*    */ import com.destroystokyo.paper.event.executor.asm.ClassDefiner;
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Modifier;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import java.util.concurrent.ConcurrentMap;
/*    */ import java.util.function.Function;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface EventExecutor
/*    */ {
/* 29 */   public static final ConcurrentMap<Method, Class<? extends EventExecutor>> eventExecutorMap = new ConcurrentHashMap<Method, Class<? extends EventExecutor>>()
/*    */     {
/*    */       @NotNull
/*    */       public Class<? extends EventExecutor> computeIfAbsent(@NotNull Method key, @NotNull Function<? super Method, ? extends Class<? extends EventExecutor>> mappingFunction) {
/* 33 */         Class<? extends EventExecutor> executorClass = get(key);
/* 34 */         if (executorClass != null) {
/* 35 */           return executorClass;
/*    */         }
/*    */         
/* 38 */         synchronized (key) {
/* 39 */           executorClass = get(key);
/* 40 */           if (executorClass != null) {
/* 41 */             return executorClass;
/*    */           }
/* 43 */           return super.computeIfAbsent(key, mappingFunction);
/*    */         } 
/*    */       }
/*    */     };
/*    */   
/*    */   @NotNull
/*    */   static EventExecutor create(@NotNull Method m, @NotNull Class<? extends Event> eventClass) {
/* 50 */     Preconditions.checkNotNull(m, "Null method");
/* 51 */     Preconditions.checkArgument((m.getParameterCount() != 0), "Incorrect number of arguments %s", m.getParameterCount());
/* 52 */     Preconditions.checkArgument((m.getParameterTypes()[0] == eventClass), "First parameter %s doesn't match event class %s", m.getParameterTypes()[0], eventClass);
/* 53 */     ClassDefiner definer = ClassDefiner.getInstance();
/* 54 */     if (Modifier.isStatic(m.getModifiers()))
/* 55 */       return (EventExecutor)new StaticMethodHandleEventExecutor(eventClass, m); 
/* 56 */     if (definer.isBypassAccessChecks() || (Modifier.isPublic(m.getDeclaringClass().getModifiers()) && Modifier.isPublic(m.getModifiers()))) {
/*    */       
/* 58 */       Class<? extends EventExecutor> executorClass = eventExecutorMap.computeIfAbsent(m, __ -> {
/*    */             String name = ASMEventExecutorGenerator.generateName();
/*    */             
/*    */             byte[] classData = ASMEventExecutorGenerator.generateEventExecutor(m, name);
/*    */             return definer.defineClass(m.getDeclaringClass().getClassLoader(), name, classData).asSubclass(EventExecutor.class);
/*    */           });
/*    */       try {
/* 65 */         EventExecutor asmExecutor = executorClass.newInstance();
/*    */         
/* 67 */         return (listener, event) -> {
/*    */             if (!eventClass.isInstance(event))
/*    */               return;  asmExecutor.execute(listener, event);
/*    */           };
/* 71 */       } catch (InstantiationException|IllegalAccessException e) {
/* 72 */         throw new AssertionError("Unable to initialize generated event executor", e);
/*    */       } 
/*    */     } 
/* 75 */     return (EventExecutor)new MethodHandleEventExecutor(eventClass, m);
/*    */   }
/*    */   
/*    */   void execute(@NotNull Listener paramListener, @NotNull Event paramEvent) throws EventException;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\EventExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */