/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory.tags;
/*    */ 
/*    */ import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
/*    */ import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
/*    */ import org.bukkit.persistence.PersistentDataAdapterContext;
/*    */ 
/*    */ public final class DeprecatedItemAdapterContext
/*    */   implements ItemTagAdapterContext {
/*    */   private final PersistentDataAdapterContext context;
/*    */   
/*    */   public DeprecatedItemAdapterContext(PersistentDataAdapterContext context) {
/* 12 */     this.context = context;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CustomItemTagContainer newTagContainer() {
/* 22 */     return new DeprecatedCustomTagContainer(this.context.newPersistentDataContainer());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\tags\DeprecatedItemAdapterContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */