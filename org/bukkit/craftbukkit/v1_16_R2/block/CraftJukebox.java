/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import net.minecraft.server.v1_16_R2.BlockJukeBox;
/*    */ import net.minecraft.server.v1_16_R2.Blocks;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityJukeBox;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftJukebox extends CraftBlockEntityState<TileEntityJukeBox> implements Jukebox {
/*    */   public CraftJukebox(Block block) {
/* 19 */     super(block, TileEntityJukeBox.class);
/*    */   }
/*    */   
/*    */   public CraftJukebox(Material material, TileEntityJukeBox te) {
/* 23 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean update(boolean force, boolean applyPhysics) {
/* 28 */     boolean result = super.update(force, applyPhysics);
/*    */     
/* 30 */     if (result && isPlaced() && getType() == Material.JUKEBOX) {
/* 31 */       CraftWorld world = (CraftWorld)getWorld();
/* 32 */       Material record = getPlaying();
/* 33 */       if (record == Material.AIR) {
/* 34 */         world.getHandle().setTypeAndData(getPosition(), (IBlockData)Blocks.JUKEBOX.getBlockData().set((IBlockState)BlockJukeBox.HAS_RECORD, Boolean.valueOf(false)), 3);
/*    */       } else {
/* 36 */         world.getHandle().setTypeAndData(getPosition(), (IBlockData)Blocks.JUKEBOX.getBlockData().set((IBlockState)BlockJukeBox.HAS_RECORD, Boolean.valueOf(true)), 3);
/*    */       } 
/* 38 */       world.playEffect(getLocation(), Effect.RECORD_PLAY, record);
/*    */     } 
/*    */     
/* 41 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getPlaying() {
/* 46 */     return getRecord().getType();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPlaying(Material record) {
/* 51 */     if (record == null || CraftMagicNumbers.getItem(record) == null) {
/* 52 */       record = Material.AIR;
/*    */     }
/*    */     
/* 55 */     setRecord(new ItemStack(record));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getRecord() {
/* 60 */     ItemStack record = getSnapshot().getRecord();
/* 61 */     return CraftItemStack.asBukkitCopy(record);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRecord(ItemStack record) {
/* 66 */     ItemStack nms = CraftItemStack.asNMSCopy(record);
/* 67 */     getSnapshot().setRecord(nms);
/* 68 */     if (nms.isEmpty()) {
/* 69 */       this.data = (IBlockData)this.data.set((IBlockState)BlockJukeBox.HAS_RECORD, Boolean.valueOf(false));
/*    */     } else {
/* 71 */       this.data = (IBlockData)this.data.set((IBlockState)BlockJukeBox.HAS_RECORD, Boolean.valueOf(true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPlaying() {
/* 77 */     return ((Boolean)getHandle().get((IBlockState)BlockJukeBox.HAS_RECORD)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void stopPlaying() {
/* 82 */     getWorld().playEffect(getLocation(), Effect.RECORD_PLAY, Material.AIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean eject() {
/* 87 */     requirePlaced();
/* 88 */     TileEntity tileEntity = getTileEntityFromWorld();
/* 89 */     if (!(tileEntity instanceof TileEntityJukeBox)) return false;
/*    */     
/* 91 */     TileEntityJukeBox jukebox = (TileEntityJukeBox)tileEntity;
/* 92 */     boolean result = !jukebox.getRecord().isEmpty();
/* 93 */     CraftWorld world = (CraftWorld)getWorld();
/* 94 */     ((BlockJukeBox)Blocks.JUKEBOX).dropRecord((World)world.getHandle(), getPosition());
/* 95 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */