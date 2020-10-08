/*     */ package org.bukkit.plugin;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.ServiceRegisterEvent;
/*     */ import org.bukkit.event.server.ServiceUnregisterEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimpleServicesManager
/*     */   implements ServicesManager
/*     */ {
/*  27 */   private final Map<Class<?>, List<RegisteredServiceProvider<?>>> providers = new HashMap<>();
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
/*     */   public <T> void register(@NotNull Class<T> service, @NotNull T provider, @NotNull Plugin plugin, @NotNull ServicePriority priority) {
/*  40 */     RegisteredServiceProvider<T> registeredProvider = null;
/*  41 */     synchronized (this.providers) {
/*  42 */       List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
/*  43 */       if (registered == null) {
/*  44 */         registered = new ArrayList<>();
/*  45 */         this.providers.put(service, registered);
/*     */       } 
/*     */       
/*  48 */       registeredProvider = new RegisteredServiceProvider<>(service, provider, priority, plugin);
/*     */ 
/*     */       
/*  51 */       int position = Collections.binarySearch((List)registered, registeredProvider);
/*  52 */       if (position < 0) {
/*  53 */         registered.add(-(position + 1), registeredProvider);
/*     */       } else {
/*  55 */         registered.add(position, registeredProvider);
/*     */       } 
/*     */     } 
/*     */     
/*  59 */     Bukkit.getServer().getPluginManager().callEvent((Event)new ServiceRegisterEvent(registeredProvider));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregisterAll(@NotNull Plugin plugin) {
/*  69 */     ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<>();
/*  70 */     synchronized (this.providers) {
/*  71 */       Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
/*     */       
/*     */       try {
/*  74 */         while (it.hasNext()) {
/*  75 */           Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
/*  76 */           Iterator<RegisteredServiceProvider<?>> it2 = ((List<RegisteredServiceProvider<?>>)entry.getValue()).iterator();
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/*  81 */             while (it2.hasNext()) {
/*  82 */               RegisteredServiceProvider<?> registered = it2.next();
/*     */               
/*  84 */               if (registered.getPlugin().equals(plugin)) {
/*  85 */                 it2.remove();
/*  86 */                 unregisteredEvents.add(new ServiceUnregisterEvent(registered));
/*     */               } 
/*     */             } 
/*  89 */           } catch (NoSuchElementException noSuchElementException) {}
/*     */ 
/*     */ 
/*     */           
/*  93 */           if (((List)entry.getValue()).size() == 0) {
/*  94 */             it.remove();
/*     */           }
/*     */         } 
/*  97 */       } catch (NoSuchElementException noSuchElementException) {}
/*     */     } 
/*  99 */     for (ServiceUnregisterEvent event : unregisteredEvents) {
/* 100 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister(@NotNull Class<?> service, @NotNull Object provider) {
/* 112 */     ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<>();
/* 113 */     synchronized (this.providers) {
/* 114 */       Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
/*     */       
/*     */       try {
/* 117 */         while (it.hasNext()) {
/* 118 */           Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
/*     */ 
/*     */           
/* 121 */           if (entry.getKey() != service) {
/*     */             continue;
/*     */           }
/*     */           
/* 125 */           Iterator<RegisteredServiceProvider<?>> it2 = ((List<RegisteredServiceProvider<?>>)entry.getValue()).iterator();
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 130 */             while (it2.hasNext()) {
/* 131 */               RegisteredServiceProvider<?> registered = it2.next();
/*     */               
/* 133 */               if (registered.getProvider() == provider) {
/* 134 */                 it2.remove();
/* 135 */                 unregisteredEvents.add(new ServiceUnregisterEvent(registered));
/*     */               } 
/*     */             } 
/* 138 */           } catch (NoSuchElementException noSuchElementException) {}
/*     */ 
/*     */ 
/*     */           
/* 142 */           if (((List)entry.getValue()).size() == 0) {
/* 143 */             it.remove();
/*     */           }
/*     */         } 
/* 146 */       } catch (NoSuchElementException noSuchElementException) {}
/*     */     } 
/* 148 */     for (ServiceUnregisterEvent event : unregisteredEvents) {
/* 149 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unregister(@NotNull Object provider) {
/* 160 */     ArrayList<ServiceUnregisterEvent> unregisteredEvents = new ArrayList<>();
/* 161 */     synchronized (this.providers) {
/* 162 */       Iterator<Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>>> it = this.providers.entrySet().iterator();
/*     */       
/*     */       try {
/* 165 */         while (it.hasNext()) {
/* 166 */           Map.Entry<Class<?>, List<RegisteredServiceProvider<?>>> entry = it.next();
/* 167 */           Iterator<RegisteredServiceProvider<?>> it2 = ((List<RegisteredServiceProvider<?>>)entry.getValue()).iterator();
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 172 */             while (it2.hasNext()) {
/* 173 */               RegisteredServiceProvider<?> registered = it2.next();
/*     */               
/* 175 */               if (registered.getProvider().equals(provider)) {
/* 176 */                 it2.remove();
/* 177 */                 unregisteredEvents.add(new ServiceUnregisterEvent(registered));
/*     */               } 
/*     */             } 
/* 180 */           } catch (NoSuchElementException noSuchElementException) {}
/*     */ 
/*     */ 
/*     */           
/* 184 */           if (((List)entry.getValue()).size() == 0) {
/* 185 */             it.remove();
/*     */           }
/*     */         } 
/* 188 */       } catch (NoSuchElementException noSuchElementException) {}
/*     */     } 
/* 190 */     for (ServiceUnregisterEvent event : unregisteredEvents) {
/* 191 */       Bukkit.getServer().getPluginManager().callEvent((Event)event);
/*     */     }
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
/*     */   @Nullable
/*     */   public <T> T load(@NotNull Class<T> service) {
/* 206 */     synchronized (this.providers) {
/* 207 */       List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
/*     */       
/* 209 */       if (registered == null) {
/* 210 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 214 */       return service.cast(((RegisteredServiceProvider)registered.get(0)).getProvider());
/*     */     } 
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
/*     */   @Nullable
/*     */   public <T> RegisteredServiceProvider<T> getRegistration(@NotNull Class<T> service) {
/* 230 */     synchronized (this.providers) {
/* 231 */       List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
/*     */       
/* 233 */       if (registered == null) {
/* 234 */         return null;
/*     */       }
/*     */ 
/*     */       
/* 238 */       return (RegisteredServiceProvider<T>)registered.get(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<RegisteredServiceProvider<?>> getRegistrations(@NotNull Plugin plugin) {
/* 251 */     ImmutableList.Builder<RegisteredServiceProvider<?>> ret = ImmutableList.builder();
/* 252 */     synchronized (this.providers) {
/* 253 */       for (List<RegisteredServiceProvider<?>> registered : this.providers.values()) {
/* 254 */         for (RegisteredServiceProvider<?> provider : registered) {
/* 255 */           if (provider.getPlugin().equals(plugin)) {
/* 256 */             ret.add(provider);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 261 */     return (List<RegisteredServiceProvider<?>>)ret.build();
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
/*     */   @NotNull
/*     */   public <T> List<RegisteredServiceProvider<T>> getRegistrations(@NotNull Class<T> service) {
/*     */     ImmutableList.Builder<RegisteredServiceProvider<T>> ret;
/* 277 */     synchronized (this.providers) {
/* 278 */       List<RegisteredServiceProvider<?>> registered = this.providers.get(service);
/*     */       
/* 280 */       if (registered == null) {
/* 281 */         return (List<RegisteredServiceProvider<T>>)ImmutableList.of();
/*     */       }
/*     */       
/* 284 */       ret = ImmutableList.builder();
/*     */       
/* 286 */       for (RegisteredServiceProvider<?> provider : registered) {
/* 287 */         ret.add(provider);
/*     */       }
/*     */     } 
/*     */     
/* 291 */     return (List<RegisteredServiceProvider<T>>)ret.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Class<?>> getKnownServices() {
/* 303 */     synchronized (this.providers) {
/* 304 */       return (Set<Class<?>>)ImmutableSet.copyOf(this.providers.keySet());
/*     */     } 
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
/*     */   public <T> boolean isProvidedFor(@NotNull Class<T> service) {
/* 317 */     synchronized (this.providers) {
/* 318 */       return this.providers.containsKey(service);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\SimpleServicesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */