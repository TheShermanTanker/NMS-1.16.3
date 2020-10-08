/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.tags;
/*    */ 
/*    */ import org.bukkit.inventory.meta.tags.ItemTagType;
/*    */ import org.bukkit.persistence.PersistentDataAdapterContext;
/*    */ import org.bukkit.persistence.PersistentDataType;
/*    */ 
/*    */ public final class DeprecatedItemTagType<T, Z>
/*    */   implements PersistentDataType<T, Z> {
/*    */   private final ItemTagType<T, Z> deprecated;
/*    */   
/*    */   public DeprecatedItemTagType(ItemTagType<T, Z> deprecated) {
/* 12 */     this.deprecated = deprecated;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<T> getPrimitiveType() {
/* 17 */     return this.deprecated.getPrimitiveType();
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<Z> getComplexType() {
/* 22 */     return this.deprecated.getComplexType();
/*    */   }
/*    */ 
/*    */   
/*    */   public T toPrimitive(Z complex, PersistentDataAdapterContext context) {
/* 27 */     return (T)this.deprecated.toPrimitive(complex, new DeprecatedItemAdapterContext(context));
/*    */   }
/*    */ 
/*    */   
/*    */   public Z fromPrimitive(T primitive, PersistentDataAdapterContext context) {
/* 32 */     return (Z)this.deprecated.fromPrimitive(primitive, new DeprecatedItemAdapterContext(context));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\tags\DeprecatedItemTagType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */