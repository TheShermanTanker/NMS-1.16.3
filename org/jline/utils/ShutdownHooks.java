/*    */ package org.jline.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Objects;
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
/*    */ public final class ShutdownHooks
/*    */ {
/* 23 */   private static final List<Task> tasks = new ArrayList<>();
/*    */   
/*    */   private static Thread hook;
/*    */   
/*    */   public static synchronized <T extends Task> T add(T task) {
/* 28 */     Objects.requireNonNull(task);
/*    */ 
/*    */     
/* 31 */     if (hook == null) {
/* 32 */       hook = addHook(new Thread("JLine Shutdown Hook")
/*    */           {
/*    */             public void run()
/*    */             {
/* 36 */               ShutdownHooks.runTasks();
/*    */             }
/*    */           });
/*    */     }
/*    */ 
/*    */     
/* 42 */     Log.debug(new Object[] { "Adding shutdown-hook task: ", task });
/* 43 */     tasks.add((Task)task);
/*    */     
/* 45 */     return task;
/*    */   }
/*    */   
/*    */   private static synchronized void runTasks() {
/* 49 */     Log.debug(new Object[] { "Running all shutdown-hook tasks" });
/*    */ 
/*    */     
/* 52 */     for (Task task : (Task[])tasks.<Task>toArray(new Task[tasks.size()])) {
/* 53 */       Log.debug(new Object[] { "Running task: ", task });
/*    */       try {
/* 55 */         task.run();
/*    */       }
/* 57 */       catch (Throwable e) {
/* 58 */         Log.warn(new Object[] { "Task failed", e });
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     tasks.clear();
/*    */   } public static interface Task {
/*    */     void run() throws Exception; }
/*    */   private static Thread addHook(Thread thread) {
/* 66 */     Log.debug(new Object[] { "Registering shutdown-hook: ", thread });
/* 67 */     Runtime.getRuntime().addShutdownHook(thread);
/* 68 */     return thread;
/*    */   }
/*    */   
/*    */   public static synchronized void remove(Task task) {
/* 72 */     Objects.requireNonNull(task);
/*    */ 
/*    */     
/* 75 */     if (hook == null) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 80 */     tasks.remove(task);
/*    */ 
/*    */     
/* 83 */     if (tasks.isEmpty()) {
/* 84 */       removeHook(hook);
/* 85 */       hook = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   private static void removeHook(Thread thread) {
/* 90 */     Log.debug(new Object[] { "Removing shutdown-hook: ", thread });
/*    */     
/*    */     try {
/* 93 */       Runtime.getRuntime().removeShutdownHook(thread);
/*    */     }
/* 95 */     catch (IllegalStateException illegalStateException) {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\ShutdownHooks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */