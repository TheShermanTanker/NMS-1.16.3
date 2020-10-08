/*    */ package org.bukkit.craftbukkit.v1_16_R2.metadata;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.metadata.MetadataStore;
/*    */ import org.bukkit.metadata.MetadataStoreBase;
/*    */ import org.bukkit.metadata.MetadataValue;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockMetadataStore
/*    */   extends MetadataStoreBase<Block>
/*    */   implements MetadataStore<Block>
/*    */ {
/*    */   private final World owningWorld;
/*    */   
/*    */   public BlockMetadataStore(World owningWorld) {
/* 23 */     this.owningWorld = owningWorld;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String disambiguate(Block block, String metadataKey) {
/* 35 */     return Integer.toString(block.getX()) + ":" + Integer.toString(block.getY()) + ":" + Integer.toString(block.getZ()) + ":" + metadataKey;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<MetadataValue> getMetadata(Block block, String metadataKey) {
/* 45 */     if (block.getWorld() == this.owningWorld) {
/* 46 */       return super.getMetadata(block, metadataKey);
/*    */     }
/* 48 */     throw new IllegalArgumentException("Block does not belong to world " + this.owningWorld.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasMetadata(Block block, String metadataKey) {
/* 59 */     if (block.getWorld() == this.owningWorld) {
/* 60 */       return super.hasMetadata(block, metadataKey);
/*    */     }
/* 62 */     throw new IllegalArgumentException("Block does not belong to world " + this.owningWorld.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void removeMetadata(Block block, String metadataKey, Plugin owningPlugin) {
/* 73 */     if (block.getWorld() == this.owningWorld) {
/* 74 */       super.removeMetadata(block, metadataKey, owningPlugin);
/*    */     } else {
/* 76 */       throw new IllegalArgumentException("Block does not belong to world " + this.owningWorld.getName());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMetadata(Block block, String metadataKey, MetadataValue newMetadataValue) {
/* 87 */     if (block.getWorld() == this.owningWorld) {
/* 88 */       super.setMetadata(block, metadataKey, newMetadataValue);
/*    */     } else {
/* 90 */       throw new IllegalArgumentException("Block does not belong to world " + this.owningWorld.getName());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\metadata\BlockMetadataStore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */