/*    */ package org.bukkit.craftbukkit.v1_16_R2.persistence;
/*    */ 
/*    */ import org.bukkit.persistence.PersistentDataAdapterContext;
/*    */ import org.bukkit.persistence.PersistentDataContainer;
/*    */ 
/*    */ public final class CraftPersistentDataAdapterContext implements PersistentDataAdapterContext {
/*    */   private final CraftPersistentDataTypeRegistry registry;
/*    */   
/*    */   public CraftPersistentDataAdapterContext(CraftPersistentDataTypeRegistry registry) {
/* 10 */     this.registry = registry;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CraftPersistentDataContainer newPersistentDataContainer() {
/* 20 */     return new CraftPersistentDataContainer(this.registry);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\persistence\CraftPersistentDataAdapterContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */