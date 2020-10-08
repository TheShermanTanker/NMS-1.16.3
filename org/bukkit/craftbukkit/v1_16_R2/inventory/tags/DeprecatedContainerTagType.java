/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.tags;
/*    */ 
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataContainer;
/*    */ import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
/*    */ import org.bukkit.inventory.meta.tags.ItemTagType;
/*    */ import org.bukkit.persistence.PersistentDataAdapterContext;
/*    */ import org.bukkit.persistence.PersistentDataContainer;
/*    */ import org.bukkit.persistence.PersistentDataType;
/*    */ 
/*    */ public final class DeprecatedContainerTagType<Z>
/*    */   implements PersistentDataType<PersistentDataContainer, Z> {
/*    */   private final ItemTagType<CustomItemTagContainer, Z> deprecated;
/*    */   
/*    */   DeprecatedContainerTagType(ItemTagType<CustomItemTagContainer, Z> deprecated) {
/* 16 */     this.deprecated = deprecated;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<PersistentDataContainer> getPrimitiveType() {
/* 21 */     return PersistentDataContainer.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<Z> getComplexType() {
/* 26 */     return this.deprecated.getComplexType();
/*    */   }
/*    */ 
/*    */   
/*    */   public PersistentDataContainer toPrimitive(Z complex, PersistentDataAdapterContext context) {
/* 31 */     CustomItemTagContainer deprecated = (CustomItemTagContainer)this.deprecated.toPrimitive(complex, new DeprecatedItemAdapterContext(context));
/* 32 */     Validate.isInstanceOf(DeprecatedCustomTagContainer.class, deprecated, "Could not wrap deprecated API due to foreign CustomItemTagContainer implementation %s", new Object[] { deprecated.getClass().getSimpleName() });
/*    */     
/* 34 */     DeprecatedCustomTagContainer tagContainer = (DeprecatedCustomTagContainer)deprecated;
/* 35 */     PersistentDataContainer wrapped = tagContainer.getWrapped();
/* 36 */     Validate.isInstanceOf(CraftPersistentDataContainer.class, wrapped, "Could not wrap deprecated API due to wrong deprecation wrapper %s", new Object[] { deprecated.getClass().getSimpleName() });
/*    */     
/* 38 */     CraftPersistentDataContainer craftTagContainer = (CraftPersistentDataContainer)wrapped;
/* 39 */     return (PersistentDataContainer)new CraftPersistentDataContainer(craftTagContainer.getRaw(), craftTagContainer.getDataTagTypeRegistry());
/*    */   }
/*    */ 
/*    */   
/*    */   public Z fromPrimitive(PersistentDataContainer primitive, PersistentDataAdapterContext context) {
/* 44 */     Validate.isInstanceOf(CraftPersistentDataContainer.class, primitive, "Could not wrap deprecated API due to foreign PersistentMetadataContainer implementation %s", new Object[] { primitive.getClass().getSimpleName() });
/*    */     
/* 46 */     return (Z)this.deprecated.fromPrimitive(new DeprecatedCustomTagContainer(primitive), new DeprecatedItemAdapterContext(context));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\tags\DeprecatedContainerTagType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */