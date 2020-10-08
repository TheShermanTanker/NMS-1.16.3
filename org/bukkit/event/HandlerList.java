/*     */ package org.bukkit.event;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredListener;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HandlerList
/*     */ {
/*  22 */   private volatile RegisteredListener[] handlers = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlerslots;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   private static ArrayList<HandlerList> allLists = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void bakeAll() {
/*  42 */     synchronized (allLists) {
/*  43 */       for (HandlerList h : allLists) {
/*  44 */         h.bake();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterAll() {
/*  53 */     synchronized (allLists) {
/*  54 */       for (HandlerList h : allLists) {
/*  55 */         synchronized (h) {
/*  56 */           for (List<RegisteredListener> list : h.handlerslots.values()) {
/*  57 */             list.clear();
/*     */           }
/*  59 */           h.handlers = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterAll(@NotNull Plugin plugin) {
/*  71 */     synchronized (allLists) {
/*  72 */       for (HandlerList h : allLists) {
/*  73 */         h.unregister(plugin);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void unregisterAll(@NotNull Listener listener) {
/*  84 */     synchronized (allLists) {
/*  85 */       for (HandlerList h : allLists) {
/*  86 */         h.unregister(listener);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HandlerList() {
/*  97 */     this.handlerslots = new EnumMap<>(EventPriority.class);
/*  98 */     for (EventPriority o : EventPriority.values()) {
/*  99 */       this.handlerslots.put(o, new ArrayList<>());
/*     */     }
/* 101 */     synchronized (allLists) {
/* 102 */       allLists.add(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void register(@NotNull RegisteredListener listener) {
/* 112 */     if (((ArrayList)this.handlerslots.get(listener.getPriority())).contains(listener))
/* 113 */       throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString()); 
/* 114 */     this.handlers = null;
/* 115 */     ((ArrayList<RegisteredListener>)this.handlerslots.get(listener.getPriority())).add(listener);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerAll(@NotNull Collection<RegisteredListener> listeners) {
/* 124 */     for (RegisteredListener listener : listeners) {
/* 125 */       register(listener);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void unregister(@NotNull RegisteredListener listener) {
/* 135 */     if (((ArrayList)this.handlerslots.get(listener.getPriority())).remove(listener)) {
/* 136 */       this.handlers = null;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void unregister(@NotNull Plugin plugin) {
/* 146 */     boolean changed = false;
/* 147 */     for (List<RegisteredListener> list : this.handlerslots.values()) {
/* 148 */       for (ListIterator<RegisteredListener> i = list.listIterator(); i.hasNext();) {
/* 149 */         if (((RegisteredListener)i.next()).getPlugin().equals(plugin)) {
/* 150 */           i.remove();
/* 151 */           changed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 155 */     if (changed) this.handlers = null;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void unregister(@NotNull Listener listener) {
/* 164 */     boolean changed = false;
/* 165 */     for (List<RegisteredListener> list : this.handlerslots.values()) {
/* 166 */       for (ListIterator<RegisteredListener> i = list.listIterator(); i.hasNext();) {
/* 167 */         if (((RegisteredListener)i.next()).getListener().equals(listener)) {
/* 168 */           i.remove();
/* 169 */           changed = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 173 */     if (changed) this.handlers = null;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void bake() {
/* 180 */     if (this.handlers != null)
/* 181 */       return;  List<RegisteredListener> entries = new ArrayList<>();
/* 182 */     for (Map.Entry<EventPriority, ArrayList<RegisteredListener>> entry : this.handlerslots.entrySet()) {
/* 183 */       entries.addAll(entry.getValue());
/*     */     }
/* 185 */     this.handlers = entries.<RegisteredListener>toArray(new RegisteredListener[entries.size()]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RegisteredListener[] getRegisteredListeners() {
/*     */     RegisteredListener[] handlers;
/* 196 */     for (; (handlers = this.handlers) == null; bake());
/* 197 */     return handlers;
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
/*     */   public static ArrayList<RegisteredListener> getRegisteredListeners(@NotNull Plugin plugin) {
/* 209 */     ArrayList<RegisteredListener> listeners = new ArrayList<>();
/* 210 */     synchronized (allLists) {
/* 211 */       for (HandlerList h : allLists) {
/* 212 */         synchronized (h) {
/* 213 */           for (List<RegisteredListener> list : h.handlerslots.values()) {
/* 214 */             for (RegisteredListener listener : list) {
/* 215 */               if (listener.getPlugin().equals(plugin)) {
/* 216 */                 listeners.add(listener);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 223 */     return listeners;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static ArrayList<HandlerList> getHandlerLists() {
/* 234 */     synchronized (allLists) {
/* 235 */       return (ArrayList<HandlerList>)allLists.clone();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\HandlerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */