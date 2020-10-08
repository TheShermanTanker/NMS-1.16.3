/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*    */ import org.bukkit.event.entity.EntityPortalEnterEvent;
/*    */ 
/*    */ public class BlockPortal
/*    */   extends Block
/*    */ {
/* 14 */   public static final BlockStateEnum<EnumDirection.EnumAxis> AXIS = BlockProperties.E;
/* 15 */   protected static final VoxelShape b = Block.a(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
/* 16 */   protected static final VoxelShape c = Block.a(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);
/*    */   
/*    */   public BlockPortal(BlockBase.Info blockbase_info) {
/* 19 */     super(blockbase_info);
/* 20 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AXIS, EnumDirection.EnumAxis.X));
/*    */   }
/*    */ 
/*    */   
/*    */   public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
/* 25 */     switch ((EnumDirection.EnumAxis)iblockdata.get(AXIS)) {
/*    */       case COUNTERCLOCKWISE_90:
/* 27 */         return c;
/*    */     } 
/*    */     
/* 30 */     return b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/* 36 */     if (worldserver.spigotConfig.enableZombiePigmenPortalSpawns && worldserver.getDimensionManager().isNatural() && worldserver.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING) && random.nextInt(2000) < worldserver.getDifficulty().a()) {
/* 37 */       while (worldserver.getType(blockposition).a(this)) {
/* 38 */         blockposition = blockposition.down();
/*    */       }
/*    */       
/* 41 */       if (worldserver.getType(blockposition).a(worldserver, blockposition, EntityTypes.ZOMBIFIED_PIGLIN)) {
/*    */         
/* 43 */         Entity entity = EntityTypes.ZOMBIFIED_PIGLIN.spawnCreature(worldserver, (NBTTagCompound)null, (IChatBaseComponent)null, (EntityHuman)null, blockposition.up(), EnumMobSpawn.STRUCTURE, false, false, CreatureSpawnEvent.SpawnReason.NETHER_PORTAL);
/*    */         
/* 45 */         if (entity != null) {
/* 46 */           entity.resetPortalCooldown();
/* 47 */           entity.fromNetherPortal = true;
/* 48 */           if (worldserver.paperConfig.nerfNetherPortalPigmen) ((EntityInsentient)entity).aware = false;
/*    */         
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 57 */     EnumDirection.EnumAxis enumdirection_enumaxis = enumdirection.n();
/* 58 */     EnumDirection.EnumAxis enumdirection_enumaxis1 = (EnumDirection.EnumAxis)iblockdata.get(AXIS);
/* 59 */     boolean flag = (enumdirection_enumaxis1 != enumdirection_enumaxis && enumdirection_enumaxis.d());
/*    */     
/* 61 */     return (!flag && !iblockdata1.a(this) && !(new BlockPortalShape(generatoraccess, blockposition, enumdirection_enumaxis1)).c()) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 66 */     if (!entity.isPassenger() && !entity.isVehicle() && entity.canPortal()) {
/*    */       
/* 68 */       EntityPortalEnterEvent event = new EntityPortalEnterEvent((Entity)entity.getBukkitEntity(), new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 69 */       world.getServer().getPluginManager().callEvent((Event)event);
/*    */       
/* 71 */       entity.d(blockposition);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 78 */     switch (enumblockrotation) {
/*    */       case COUNTERCLOCKWISE_90:
/*    */       case CLOCKWISE_90:
/* 81 */         switch ((EnumDirection.EnumAxis)iblockdata.get(AXIS)) {
/*    */           case COUNTERCLOCKWISE_90:
/* 83 */             return iblockdata.set(AXIS, EnumDirection.EnumAxis.X);
/*    */           case CLOCKWISE_90:
/* 85 */             return iblockdata.set(AXIS, EnumDirection.EnumAxis.Z);
/*    */         } 
/* 87 */         return iblockdata;
/*    */     } 
/*    */     
/* 90 */     return iblockdata;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 96 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AXIS });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPortal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */