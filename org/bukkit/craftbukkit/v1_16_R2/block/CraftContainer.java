/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.ChestLock;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityContainer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Container;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ 
/*    */ public abstract class CraftContainer<T extends TileEntityContainer> extends CraftBlockEntityState<T> implements Container {
/*    */   public CraftContainer(Block block, Class<T> tileEntityClass) {
/* 13 */     super(block, tileEntityClass);
/*    */   }
/*    */   
/*    */   public CraftContainer(Material material, T tileEntity) {
/* 17 */     super(material, tileEntity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLocked() {
/* 22 */     return !((TileEntityContainer)getSnapshot()).chestLock.key.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getLock() {
/* 27 */     return ((TileEntityContainer)getSnapshot()).chestLock.key;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLock(String key) {
/* 32 */     ((TileEntityContainer)getSnapshot()).chestLock = (key == null) ? ChestLock.a : new ChestLock(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCustomName() {
/* 37 */     TileEntityContainer tileEntityContainer = (TileEntityContainer)getSnapshot();
/* 38 */     return (tileEntityContainer.customName != null) ? CraftChatMessage.fromComponent(tileEntityContainer.getCustomName()) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustomName(String name) {
/* 43 */     ((TileEntityContainer)getSnapshot()).setCustomName(CraftChatMessage.fromStringOrNull(name));
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTo(T container) {
/* 48 */     super.applyTo(container);
/*    */     
/* 50 */     if (((TileEntityContainer)getSnapshot()).customName == null)
/* 51 */       container.setCustomName(null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */