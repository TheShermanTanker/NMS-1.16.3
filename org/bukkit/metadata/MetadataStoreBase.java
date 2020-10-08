/*     */ package org.bukkit.metadata;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public abstract class MetadataStoreBase<T> {
/*  16 */   private Map<String, Map<Plugin, MetadataValue>> metadataMap = new ConcurrentHashMap<>();
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
/*     */   
/*     */   public synchronized void setMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
/*  41 */     Validate.notNull(newMetadataValue, "Value cannot be null");
/*  42 */     Plugin owningPlugin = newMetadataValue.getOwningPlugin();
/*  43 */     Validate.notNull(owningPlugin, "Plugin cannot be null");
/*  44 */     String key = disambiguate(subject, metadataKey);
/*  45 */     Map<Plugin, MetadataValue> entry = this.metadataMap.get(key);
/*  46 */     if (entry == null) {
/*  47 */       entry = new WeakHashMap<>(1);
/*  48 */       this.metadataMap.put(key, entry);
/*     */     } 
/*  50 */     synchronized (entry) {
/*  51 */       entry.put(owningPlugin, newMetadataValue);
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
/*     */   @NotNull
/*     */   public List<MetadataValue> getMetadata(@NotNull T subject, @NotNull String metadataKey) {
/*  67 */     String key = disambiguate(subject, metadataKey);
/*  68 */     Map<Plugin, MetadataValue> entry = this.metadataMap.get(key);
/*  69 */     if (entry != null) {
/*  70 */       Collection<MetadataValue> values = entry.values();
/*  71 */       return Collections.unmodifiableList(new ArrayList<>(values));
/*     */     } 
/*  73 */     return Collections.emptyList();
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
/*     */   public boolean hasMetadata(@NotNull T subject, @NotNull String metadataKey) {
/*  86 */     String key = disambiguate(subject, metadataKey);
/*  87 */     return this.metadataMap.containsKey(key);
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
/*     */   
/*     */   public void removeMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull Plugin owningPlugin) {
/* 102 */     Validate.notNull(owningPlugin, "Plugin cannot be null");
/* 103 */     String key = disambiguate(subject, metadataKey);
/* 104 */     Map<Plugin, MetadataValue> entry = this.metadataMap.get(key);
/* 105 */     if (entry == null) {
/*     */       return;
/*     */     }
/* 108 */     synchronized (entry) {
/* 109 */       entry.remove(owningPlugin);
/* 110 */       if (entry.isEmpty()) {
/* 111 */         this.metadataMap.remove(key);
/*     */       }
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
/*     */   public void invalidateAll(@NotNull Plugin owningPlugin) {
/* 126 */     Validate.notNull(owningPlugin, "Plugin cannot be null");
/* 127 */     for (Map<Plugin, MetadataValue> values : this.metadataMap.values()) {
/* 128 */       if (values.containsKey(owningPlugin)) {
/* 129 */         ((MetadataValue)values.get(owningPlugin)).invalidate();
/*     */       }
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
/*     */   public void removeAll(@NotNull Plugin owningPlugin) {
/* 142 */     Validate.notNull(owningPlugin, "Plugin cannot be null");
/* 143 */     for (Iterator<Map<Plugin, MetadataValue>> iterator = this.metadataMap.values().iterator(); iterator.hasNext(); ) {
/* 144 */       Map<Plugin, MetadataValue> values = iterator.next();
/* 145 */       if (values.containsKey(owningPlugin)) {
/* 146 */         values.remove(owningPlugin);
/*     */       }
/* 148 */       if (values.isEmpty())
/* 149 */         iterator.remove(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   protected abstract String disambiguate(@NotNull T paramT, @NotNull String paramString);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\MetadataStoreBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */