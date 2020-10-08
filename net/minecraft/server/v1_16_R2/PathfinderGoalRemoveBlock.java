/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityInteractEvent;
/*     */ 
/*     */ public class PathfinderGoalRemoveBlock extends PathfinderGoalGotoTarget {
/*     */   private final Block g;
/*     */   private final EntityInsentient entity;
/*     */   private int i;
/*     */   private World world;
/*     */   
/*     */   public PathfinderGoalRemoveBlock(Block block, EntityCreature entitycreature, double d0, int i) {
/*  18 */     super(entitycreature, d0, 24, i);
/*  19 */     this.g = block;
/*  20 */     this.entity = entitycreature;
/*  21 */     this.world = entitycreature.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  26 */     if (!this.entity.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING))
/*  27 */       return false; 
/*  28 */     if (this.c > 0) {
/*  29 */       this.c--;
/*  30 */       return false;
/*  31 */     }  if (n()) {
/*  32 */       this.c = 20;
/*  33 */       return true;
/*     */     } 
/*  35 */     this.c = a(this.a);
/*  36 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean n() {
/*  41 */     return (this.e != null && a(this.a.world, this.e)) ? true : m();
/*     */   }
/*     */ 
/*     */   
/*     */   public void d() {
/*  46 */     super.d();
/*  47 */     this.entity.fallDistance = 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c() {
/*  52 */     super.c();
/*  53 */     this.i = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {}
/*     */   
/*     */   public void a(World world, BlockPosition blockposition) {}
/*     */   
/*     */   public void e() {
/*  62 */     super.e();
/*  63 */     World world = this.entity.world;
/*  64 */     BlockPosition blockposition = this.entity.getChunkCoordinates();
/*  65 */     BlockPosition blockposition1 = a(blockposition, world);
/*  66 */     Random random = this.entity.getRandom();
/*     */     
/*  68 */     if (l() && blockposition1 != null) {
/*     */ 
/*     */ 
/*     */       
/*  72 */       if (this.i > 0) {
/*  73 */         Vec3D vec3d = this.entity.getMot();
/*  74 */         this.entity.setMot(vec3d.x, 0.3D, vec3d.z);
/*  75 */         if (!world.isClientSide) {
/*  76 */           double d0 = 0.08D;
/*  77 */           ((WorldServer)world).a(new ParticleParamItem(Particles.ITEM, new ItemStack(Items.EGG)), blockposition1.getX() + 0.5D, blockposition1.getY() + 0.7D, blockposition1.getZ() + 0.5D, 3, (random.nextFloat() - 0.5D) * 0.08D, (random.nextFloat() - 0.5D) * 0.08D, (random.nextFloat() - 0.5D) * 0.08D, 0.15000000596046448D);
/*     */         } 
/*     */       } 
/*     */       
/*  81 */       if (this.i % 2 == 0) {
/*  82 */         Vec3D vec3d = this.entity.getMot();
/*  83 */         this.entity.setMot(vec3d.x, -0.3D, vec3d.z);
/*  84 */         if (this.i % 6 == 0) {
/*  85 */           a(world, this.e);
/*     */         }
/*     */       } 
/*     */       
/*  89 */       if (this.i > 60) {
/*     */         
/*  91 */         EntityInteractEvent event = new EntityInteractEvent((Entity)this.entity.getBukkitEntity(), (Block)CraftBlock.at(world, blockposition1));
/*  92 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/*  94 */         if (event.isCancelled()) {
/*     */           return;
/*     */         }
/*     */         
/*  98 */         world.a(blockposition1, false);
/*  99 */         if (!world.isClientSide) {
/* 100 */           for (int i = 0; i < 20; i++) {
/* 101 */             double d0 = random.nextGaussian() * 0.02D;
/* 102 */             double d1 = random.nextGaussian() * 0.02D;
/* 103 */             double d2 = random.nextGaussian() * 0.02D;
/*     */             
/* 105 */             ((WorldServer)world).a(Particles.POOF, blockposition1.getX() + 0.5D, blockposition1.getY(), blockposition1.getZ() + 0.5D, 1, d0, d1, d2, 0.15000000596046448D);
/*     */           } 
/*     */           
/* 108 */           a(world, blockposition1);
/*     */         } 
/*     */       } 
/*     */       
/* 112 */       this.i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(BlockPosition blockposition, IBlockAccess iblockaccess) {
/* 119 */     Block block = this.world.getBlockIfLoaded(blockposition);
/* 120 */     if (block == null) return null; 
/* 121 */     if (block.a(this.g)) {
/* 122 */       return blockposition;
/*     */     }
/* 124 */     BlockPosition[] ablockposition = { blockposition.down(), blockposition.west(), blockposition.east(), blockposition.north(), blockposition.south(), blockposition.down().down() };
/* 125 */     BlockPosition[] ablockposition1 = ablockposition;
/* 126 */     int i = ablockposition.length;
/*     */     
/* 128 */     for (int j = 0; j < i; j++) {
/* 129 */       BlockPosition blockposition1 = ablockposition1[j];
/*     */       
/* 131 */       if (iblockaccess.getBlockIfLoaded(blockposition1).a(this.g)) {
/* 132 */         return blockposition1;
/*     */       }
/*     */     } 
/*     */     
/* 136 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/* 142 */     IChunkAccess ichunkaccess = iworldreader.getChunkIfLoadedImmediately(blockposition.getX() >> 4, blockposition.getZ() >> 4);
/*     */     
/* 144 */     return (ichunkaccess == null) ? false : ((ichunkaccess.getType(blockposition).a(this.g) && ichunkaccess.getType(blockposition.up()).isAir() && ichunkaccess.getType(blockposition.up(2)).isAir()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalRemoveBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */