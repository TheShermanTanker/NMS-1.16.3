/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ 
/*     */ public class BlockPressurePlateBinary extends BlockPressurePlateAbstract {
/*  10 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*     */   private final EnumMobType e;
/*     */   
/*     */   protected BlockPressurePlateBinary(EnumMobType blockpressureplatebinary_enummobtype, BlockBase.Info blockbase_info) {
/*  14 */     super(blockbase_info);
/*  15 */     j(((IBlockData)this.blockStateList.getBlockData()).set(POWERED, Boolean.valueOf(false)));
/*  16 */     this.e = blockpressureplatebinary_enummobtype;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getPower(IBlockData iblockdata) {
/*  21 */     return ((Boolean)iblockdata.get(POWERED)).booleanValue() ? 15 : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IBlockData a(IBlockData iblockdata, int i) {
/*  26 */     return iblockdata.set(POWERED, Boolean.valueOf((i > 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/*  31 */     if (this.material != Material.WOOD && this.material != Material.NETHER_WOOD) {
/*  32 */       generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
/*     */     } else {
/*  34 */       generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.8F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/*  41 */     if (this.material != Material.WOOD && this.material != Material.NETHER_WOOD) {
/*  42 */       generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
/*     */     } else {
/*  44 */       generatoraccess.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.7F);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int b(World world, BlockPosition blockposition) {
/*     */     List<Entity> list1;
/*     */     List<EntityLiving> list;
/*  51 */     AxisAlignedBB axisalignedbb = c.a(blockposition);
/*     */ 
/*     */     
/*  54 */     switch (this.e) {
/*     */       case EVERYTHING:
/*  56 */         list1 = world.getEntities((Entity)null, axisalignedbb);
/*     */         break;
/*     */       case MOBS:
/*  59 */         list = world.a(EntityLiving.class, axisalignedbb);
/*     */         break;
/*     */       default:
/*  62 */         return 0;
/*     */     } 
/*     */     
/*  65 */     if (!list.isEmpty()) {
/*  66 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/*  68 */       while (iterator.hasNext()) {
/*  69 */         Entity entity = iterator.next();
/*     */ 
/*     */         
/*  72 */         if (getPower(world.getType(blockposition)) == 0) {
/*  73 */           EntityInteractEvent entityInteractEvent; CraftWorld craftWorld = world.getWorld();
/*  74 */           PluginManager manager = world.getServer().getPluginManager();
/*     */ 
/*     */           
/*  77 */           if (entity instanceof EntityHuman) {
/*  78 */             PlayerInteractEvent playerInteractEvent = CraftEventFactory.callPlayerInteractEvent((EntityHuman)entity, Action.PHYSICAL, blockposition, null, null, null);
/*     */           } else {
/*  80 */             entityInteractEvent = new EntityInteractEvent((Entity)entity.getBukkitEntity(), craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()));
/*  81 */             manager.callEvent((Event)entityInteractEvent);
/*     */           } 
/*     */ 
/*     */           
/*  85 */           if (entityInteractEvent.isCancelled()) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/*  91 */         if (!entity.isIgnoreBlockTrigger()) {
/*  92 */           return 15;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  97 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 102 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { POWERED });
/*     */   }
/*     */   
/*     */   public enum EnumMobType
/*     */   {
/* 107 */     EVERYTHING, MOBS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPressurePlateBinary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */