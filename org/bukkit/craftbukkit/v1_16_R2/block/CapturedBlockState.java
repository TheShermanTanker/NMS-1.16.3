/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityBee;
/*    */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBeehive;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import net.minecraft.server.v1_16_R2.WorldServer;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public final class CapturedBlockState extends CraftBlockState {
/*    */   private final boolean treeBlock;
/*    */   
/*    */   public CapturedBlockState(Block block, int flag, boolean treeBlock) {
/* 19 */     super(block, flag);
/*    */     
/* 21 */     this.treeBlock = treeBlock;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean update(boolean force, boolean applyPhysics) {
/* 26 */     boolean result = super.update(force, applyPhysics);
/*    */ 
/*    */     
/* 29 */     if (this.treeBlock && getType() == Material.BEE_NEST) {
/* 30 */       WorldServer worldServer = this.world.getHandle();
/* 31 */       BlockPosition blockposition1 = getPosition();
/* 32 */       Random random = worldServer.getRandom();
/*    */ 
/*    */       
/* 35 */       TileEntity tileentity = worldServer.getTileEntity(blockposition1);
/*    */       
/* 37 */       if (tileentity instanceof TileEntityBeehive) {
/* 38 */         TileEntityBeehive tileentitybeehive = (TileEntityBeehive)tileentity;
/* 39 */         int j = 2 + random.nextInt(2);
/*    */         
/* 41 */         for (int k = 0; k < j; k++) {
/* 42 */           EntityBee entitybee = new EntityBee(EntityTypes.BEE, (World)worldServer.getMinecraftWorld());
/*    */           
/* 44 */           tileentitybeehive.a((Entity)entitybee, false, random.nextInt(599));
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 50 */     return result;
/*    */   }
/*    */   
/*    */   public static CapturedBlockState getBlockState(World world, BlockPosition pos, int flag) {
/* 54 */     return new CapturedBlockState(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), flag, false);
/*    */   }
/*    */   
/*    */   public static CapturedBlockState getTreeBlockState(World world, BlockPosition pos, int flag) {
/* 58 */     return new CapturedBlockState(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), flag, true);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CapturedBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */