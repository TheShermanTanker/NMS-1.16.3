/*     */ package org.bukkit.craftbukkit.v1_16_R2.persistence;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNBTTagConfigSerializer;
/*     */ import org.bukkit.persistence.PersistentDataAdapterContext;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ import org.bukkit.persistence.PersistentDataType;
/*     */ 
/*     */ public final class CraftPersistentDataContainer
/*     */   implements PersistentDataContainer
/*     */ {
/*  20 */   private final Map<String, NBTBase> customDataTags = new HashMap<>();
/*     */   private final CraftPersistentDataTypeRegistry registry;
/*     */   private final CraftPersistentDataAdapterContext adapterContext;
/*     */   
/*     */   public CraftPersistentDataContainer(Map<String, NBTBase> customTags, CraftPersistentDataTypeRegistry registry) {
/*  25 */     this(registry);
/*  26 */     this.customDataTags.putAll(customTags);
/*     */   }
/*     */   
/*     */   public CraftPersistentDataContainer(CraftPersistentDataTypeRegistry registry) {
/*  30 */     this.registry = registry;
/*  31 */     this.adapterContext = new CraftPersistentDataAdapterContext(this.registry);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
/*  36 */     Validate.notNull(key, "The provided key for the custom value was null");
/*  37 */     Validate.notNull(type, "The provided type for the custom value was null");
/*  38 */     Validate.notNull(value, "The provided value for the custom value was null");
/*     */     
/*  40 */     this.customDataTags.put(key.toString(), this.registry.wrap(type.getPrimitiveType(), type.toPrimitive(value, this.adapterContext)));
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
/*  45 */     Validate.notNull(key, "The provided key for the custom value was null");
/*  46 */     Validate.notNull(type, "The provided type for the custom value was null");
/*     */     
/*  48 */     NBTBase value = this.customDataTags.get(key.toString());
/*  49 */     if (value == null) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     return this.registry.isInstanceOf(type.getPrimitiveType(), value);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
/*  58 */     Validate.notNull(key, "The provided key for the custom value was null");
/*  59 */     Validate.notNull(type, "The provided type for the custom value was null");
/*     */     
/*  61 */     NBTBase value = this.customDataTags.get(key.toString());
/*  62 */     if (value == null) {
/*  63 */       return null;
/*     */     }
/*     */     
/*  66 */     return (Z)type.fromPrimitive(this.registry.extract(type.getPrimitiveType(), value), this.adapterContext);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T, Z> Z getOrDefault(NamespacedKey key, PersistentDataType<T, Z> type, Z defaultValue) {
/*  71 */     Z z = get(key, type);
/*  72 */     return (z != null) ? z : defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<NamespacedKey> getKeys() {
/*  77 */     Set<NamespacedKey> keys = new HashSet<>();
/*     */     
/*  79 */     this.customDataTags.keySet().forEach(key -> {
/*     */           String[] keyData = key.split(":", 2);
/*     */           
/*     */           if (keyData.length == 2) {
/*     */             keys.add(new NamespacedKey(keyData[0], keyData[1]));
/*     */           }
/*     */         });
/*  86 */     return keys;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove(NamespacedKey key) {
/*  91 */     Validate.notNull(key, "The provided key for the custom value was null");
/*     */     
/*  93 */     this.customDataTags.remove(key.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  98 */     return this.customDataTags.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public PersistentDataAdapterContext getAdapterContext() {
/* 103 */     return this.adapterContext;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 108 */     if (!(obj instanceof CraftPersistentDataContainer)) {
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     Map<String, NBTBase> myRawMap = getRaw();
/* 113 */     Map<String, NBTBase> theirRawMap = ((CraftPersistentDataContainer)obj).getRaw();
/*     */     
/* 115 */     return Objects.equals(myRawMap, theirRawMap);
/*     */   }
/*     */   
/*     */   public NBTTagCompound toTagCompound() {
/* 119 */     NBTTagCompound tag = new NBTTagCompound();
/* 120 */     for (Map.Entry<String, NBTBase> entry : this.customDataTags.entrySet()) {
/* 121 */       tag.set(entry.getKey(), entry.getValue());
/*     */     }
/* 123 */     return tag;
/*     */   }
/*     */   
/*     */   public void put(String key, NBTBase base) {
/* 127 */     this.customDataTags.put(key, base);
/*     */   }
/*     */   
/*     */   public void putAll(Map<String, NBTBase> map) {
/* 131 */     this.customDataTags.putAll(map);
/*     */   }
/*     */   
/*     */   public void putAll(NBTTagCompound compound) {
/* 135 */     for (String key : compound.getKeys()) {
/* 136 */       this.customDataTags.put(key, compound.get(key));
/*     */     }
/*     */   }
/*     */   
/*     */   public Map<String, NBTBase> getRaw() {
/* 141 */     return this.customDataTags;
/*     */   }
/*     */   
/*     */   public CraftPersistentDataTypeRegistry getDataTagTypeRegistry() {
/* 145 */     return this.registry;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 150 */     int hashCode = 3;
/* 151 */     hashCode += this.customDataTags.hashCode();
/* 152 */     return hashCode;
/*     */   }
/*     */   
/*     */   public Map<String, Object> serialize() {
/* 156 */     return (Map<String, Object>)CraftNBTTagConfigSerializer.serialize((NBTBase)toTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 161 */     this.customDataTags.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\persistence\CraftPersistentDataContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */