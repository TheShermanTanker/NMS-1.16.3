/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.entity.EntityInteractEvent;
/*    */ 
/*    */ public class BlockPressurePlateWeighted extends BlockPressurePlateAbstract {
/*  7 */   public static final BlockStateInteger POWER = BlockProperties.az;
/*    */   private final int weight;
/*    */   
/*    */   protected BlockPressurePlateWeighted(int i, BlockBase.Info blockbase_info) {
/* 11 */     super(blockbase_info);
/* 12 */     j(((IBlockData)this.blockStateList.getBlockData()).set(POWER, Integer.valueOf(0)));
/* 13 */     this.weight = i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected int b(World world, BlockPosition blockposition) {
/* 20 */     int i = 0;
/* 21 */     Iterator<Entity> iterator = world.<Entity>a(Entity.class, c.a(blockposition)).iterator();
/*    */     
/* 23 */     while (iterator.hasNext()) {
/* 24 */       EntityInteractEvent entityInteractEvent; Entity entity = iterator.next();
/*    */ 
/*    */ 
/*    */       
/* 28 */       if (entity instanceof EntityHuman) {
/* 29 */         PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*    */       } else {
/* 31 */         entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/* 32 */         world.getServer().getPluginManager().callEvent((Event)entityInteractEvent);
/*    */       } 
/*    */ 
/*    */       
/* 36 */       if (!entityInteractEvent.isCancelled()) {
/* 37 */         i++;
/*    */       }
/*    */     } 
/*    */     
/* 41 */     i = Math.min(i, this.weight);
/*    */ 
/*    */     
/* 44 */     if (i > 0) {
/* 45 */       float f = Math.min(this.weight, i) / this.weight;
/*    */       
/* 47 */       return MathHelper.f(f * 15.0F);
/*    */     } 
/* 49 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 55 */     generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.90000004F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 60 */     generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.75F);
/*    */   }
/*    */ 
/*    */   
/*    */   protected int getPower(IBlockData iblockdata) {
/* 65 */     return ((Integer)iblockdata.get(POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockData a(IBlockData iblockdata, int i) {
/* 70 */     return iblockdata.set(POWER, Integer.valueOf(i));
/*    */   }
/*    */ 
/*    */   
/*    */   protected int c() {
/* 75 */     return 10;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 80 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { POWER });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPressurePlateWeighted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */