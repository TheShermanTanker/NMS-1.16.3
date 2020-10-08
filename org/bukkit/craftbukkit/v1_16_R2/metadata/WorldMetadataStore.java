/*    */ package org.bukkit.craftbukkit.v1_16_R2.metadata;
/*    */ 
/*    */ import org.bukkit.World;
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
/*    */ public class WorldMetadataStore
/*    */   extends MetadataStoreBase<World>
/*    */   implements MetadataStore<World>
/*    */ {
/*    */   protected String disambiguate(World world, String metadataKey) {
/* 20 */     return world.getUID().toString() + ":" + metadataKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\metadata\WorldMetadataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */