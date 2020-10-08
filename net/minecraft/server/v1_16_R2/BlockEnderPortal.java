/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*    */ import org.bukkit.event.player.PlayerTeleportEvent;
/*    */ 
/*    */ public class BlockEnderPortal extends BlockTileEntity {
/* 10 */   protected static final VoxelShape a = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
/*    */   
/*    */   protected BlockEnderPortal(BlockBase.Info blockbase_info) {
/* 13 */     super(blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public TileEntity createTile(IBlockAccess iblockaccess) {
/* 18 */     return new TileEntityEnderPortal();
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 23 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 28 */     if (world instanceof WorldServer && !entity.isPassenger() && !entity.isVehicle() && entity.canPortal() && VoxelShapes.c(VoxelShapes.a(entity.getBoundingBox().d(-blockposition.getX(), -blockposition.getY(), -blockposition.getZ())), iblockdata.getShape(world, blockposition), OperatorBoolean.AND)) {
/* 29 */       ResourceKey<World> resourcekey = (world.getDimensionKey() == World.THE_END) ? World.OVERWORLD : World.THE_END;
/* 30 */       WorldServer worldserver = ((WorldServer)world).getMinecraftServer().getWorldServer(resourcekey);
/*    */       
/* 32 */       if (worldserver == null);
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 37 */       EntityPortalEnterEvent event = new EntityPortalEnterEvent((Entity)entity.getBukkitEntity(), new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 38 */       world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 40 */       if (entity instanceof EntityPlayer) {
/* 41 */         ((EntityPlayer)entity).b(worldserver, PlayerTeleportEvent.TeleportCause.END_PORTAL);
/*    */         
/*    */         return;
/*    */       } 
/* 45 */       entity.b(worldserver);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData iblockdata, FluidType fluidtype) {
/* 52 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockEnderPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */