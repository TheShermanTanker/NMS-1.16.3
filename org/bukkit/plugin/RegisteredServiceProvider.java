/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegisteredServiceProvider<T>
/*    */   implements Comparable<RegisteredServiceProvider<?>>
/*    */ {
/*    */   private Class<T> service;
/*    */   private Plugin plugin;
/*    */   private T provider;
/*    */   private ServicePriority priority;
/*    */   
/*    */   public RegisteredServiceProvider(@NotNull Class<T> service, @NotNull T provider, @NotNull ServicePriority priority, @NotNull Plugin plugin) {
/* 18 */     this.service = service;
/* 19 */     this.plugin = plugin;
/* 20 */     this.provider = provider;
/* 21 */     this.priority = priority;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Class<T> getService() {
/* 26 */     return this.service;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Plugin getPlugin() {
/* 31 */     return this.plugin;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public T getProvider() {
/* 36 */     return this.provider;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ServicePriority getPriority() {
/* 41 */     return this.priority;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(@NotNull RegisteredServiceProvider<?> other) {
/* 46 */     if (this.priority.ordinal() == other.getPriority().ordinal()) {
/* 47 */       return 0;
/*    */     }
/* 49 */     return (this.priority.ordinal() < other.getPriority().ordinal()) ? 1 : -1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\RegisteredServiceProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */