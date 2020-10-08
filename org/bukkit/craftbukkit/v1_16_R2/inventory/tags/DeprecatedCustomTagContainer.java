/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.tags;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
/*    */ import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
/*    */ import org.bukkit.inventory.meta.tags.ItemTagType;
/*    */ import org.bukkit.persistence.PersistentDataContainer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DeprecatedCustomTagContainer
/*    */   implements CustomItemTagContainer
/*    */ {
/*    */   private final PersistentDataContainer wrapped;
/*    */   
/*    */   public DeprecatedCustomTagContainer(PersistentDataContainer wrapped) {
/* 20 */     this.wrapped = wrapped;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T, Z> void setCustomTag(NamespacedKey key, ItemTagType<T, Z> type, Z value) {
/* 25 */     if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
/* 26 */       this.wrapped.set(key, new DeprecatedContainerTagType<>((ItemTagType)type), value);
/*    */     } else {
/* 28 */       this.wrapped.set(key, new DeprecatedItemTagType<>(type), value);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public <T, Z> boolean hasCustomTag(NamespacedKey key, ItemTagType<T, Z> type) {
/* 34 */     if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
/* 35 */       return this.wrapped.has(key, new DeprecatedContainerTagType<>((ItemTagType)type));
/*    */     }
/* 37 */     return this.wrapped.has(key, new DeprecatedItemTagType<>(type));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <T, Z> Z getCustomTag(NamespacedKey key, ItemTagType<T, Z> type) {
/* 43 */     if (Objects.equals(CustomItemTagContainer.class, type.getPrimitiveType())) {
/* 44 */       return (Z)this.wrapped.get(key, new DeprecatedContainerTagType<>((ItemTagType)type));
/*    */     }
/* 46 */     return (Z)this.wrapped.get(key, new DeprecatedItemTagType<>(type));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeCustomTag(NamespacedKey key) {
/* 52 */     this.wrapped.remove(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 57 */     return this.wrapped.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemTagAdapterContext getAdapterContext() {
/* 62 */     return new DeprecatedItemAdapterContext(this.wrapped.getAdapterContext());
/*    */   }
/*    */   
/*    */   public PersistentDataContainer getWrapped() {
/* 66 */     return this.wrapped;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\tags\DeprecatedCustomTagContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */