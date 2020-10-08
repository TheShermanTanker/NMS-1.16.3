/*    */ package co.aikar.timings;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.EventException;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.EventExecutor;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimedEventExecutor
/*    */   implements EventExecutor
/*    */ {
/*    */   private final EventExecutor executor;
/*    */   private final Timing timings;
/*    */   
/*    */   public TimedEventExecutor(@NotNull EventExecutor executor, @NotNull Plugin plugin, @Nullable Method method, @NotNull Class<? extends Event> eventClass) {
/*    */     String id;
/* 51 */     this.executor = executor;
/*    */ 
/*    */     
/* 54 */     if (method == null && 
/* 55 */       executor.getClass().getEnclosingClass() != null) {
/* 56 */       method = executor.getClass().getEnclosingMethod();
/*    */     }
/*    */ 
/*    */     
/* 60 */     if (method != null) {
/* 61 */       id = method.getDeclaringClass().getName();
/*    */     } else {
/* 63 */       id = executor.getClass().getName();
/*    */     } 
/*    */ 
/*    */     
/* 67 */     String eventName = eventClass.getSimpleName();
/* 68 */     boolean verbose = "BlockPhysicsEvent".equals(eventName);
/* 69 */     this.timings = Timings.ofSafe(plugin, (verbose ? "## " : "") + "Event: " + id + " (" + eventName + ")");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
/* 75 */     if (event.isAsynchronous() || !Timings.timingsEnabled || !Bukkit.isPrimaryThread()) {
/* 76 */       this.executor.execute(listener, event);
/*    */       return;
/*    */     } 
/* 79 */     Timing ignored = this.timings.startTiming(); try {
/* 80 */       this.executor.execute(listener, event);
/* 81 */       if (ignored != null) ignored.close(); 
/*    */     } catch (Throwable throwable) {
/*    */       if (ignored != null)
/*    */         try {
/*    */           ignored.close();
/*    */         } catch (Throwable throwable1) {
/*    */           throwable.addSuppressed(throwable1);
/*    */         }  
/*    */       throw throwable;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimedEventExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */