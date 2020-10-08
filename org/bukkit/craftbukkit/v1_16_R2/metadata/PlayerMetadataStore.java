/*    */ package org.bukkit.craftbukkit.v1_16_R2.metadata;
/*    */ 
/*    */ import org.bukkit.OfflinePlayer;
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
/*    */ public class PlayerMetadataStore
/*    */   extends MetadataStoreBase<OfflinePlayer>
/*    */   implements MetadataStore<OfflinePlayer>
/*    */ {
/*    */   protected String disambiguate(OfflinePlayer player, String metadataKey) {
/* 21 */     return player.getUniqueId() + ":" + metadataKey;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\metadata\PlayerMetadataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */