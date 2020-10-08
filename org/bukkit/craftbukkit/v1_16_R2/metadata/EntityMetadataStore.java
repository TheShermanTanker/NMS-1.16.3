/*    */ package org.bukkit.craftbukkit.v1_16_R2.metadata;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.metadata.MetadataStore;
/*    */ import org.bukkit.metadata.MetadataStoreBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMetadataStore
/*    */   extends MetadataStoreBase<Entity>
/*    */   implements MetadataStore<Entity>
/*    */ {
/*    */   protected String disambiguate(Entity entity, String metadataKey) {
/* 21 */     return entity.getUniqueId().toString() + ":" + metadataKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\metadata\EntityMetadataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */