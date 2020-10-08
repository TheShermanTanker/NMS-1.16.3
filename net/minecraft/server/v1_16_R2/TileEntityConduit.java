/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class TileEntityConduit
/*     */   extends TileEntity implements ITickable {
/*  16 */   private static final Block[] b = new Block[] { Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS, Blocks.SEA_LANTERN, Blocks.DARK_PRISMARINE };
/*     */   public int a;
/*     */   private float c;
/*     */   private boolean g;
/*     */   private boolean h;
/*     */   private final List<BlockPosition> i;
/*     */   @Nullable
/*     */   private EntityLiving target;
/*     */   @Nullable
/*     */   private UUID k;
/*     */   private long l;
/*     */   
/*     */   public TileEntityConduit() {
/*  29 */     this(TileEntityTypes.CONDUIT);
/*     */   }
/*     */   
/*     */   public TileEntityConduit(TileEntityTypes<?> tileentitytypes) {
/*  33 */     super(tileentitytypes);
/*  34 */     this.i = Lists.newArrayList();
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  39 */     super.load(iblockdata, nbttagcompound);
/*  40 */     if (nbttagcompound.b("Target")) {
/*  41 */       this.k = nbttagcompound.a("Target");
/*     */     } else {
/*  43 */       this.k = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  50 */     super.save(nbttagcompound);
/*  51 */     if (this.target != null) {
/*  52 */       nbttagcompound.a("Target", this.target.getUniqueID());
/*     */     }
/*     */     
/*  55 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/*  61 */     return new PacketPlayOutTileEntityData(this.position, 5, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/*  66 */     return save(new NBTTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     this.a++;
/*  72 */     long i = this.world.getTime();
/*     */     
/*  74 */     if (i % 40L == 0L) {
/*  75 */       a(h());
/*  76 */       if (!this.world.isClientSide && d()) {
/*  77 */         j();
/*  78 */         k();
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     if (i % 80L == 0L && d()) {
/*  83 */       a(SoundEffects.BLOCK_CONDUIT_AMBIENT);
/*     */     }
/*     */     
/*  86 */     if (i > this.l && d()) {
/*  87 */       this.l = i + 60L + this.world.getRandom().nextInt(40);
/*  88 */       a(SoundEffects.BLOCK_CONDUIT_AMBIENT_SHORT);
/*     */     } 
/*     */     
/*  91 */     if (this.world.isClientSide) {
/*  92 */       l();
/*  93 */       y();
/*  94 */       if (d()) {
/*  95 */         this.c++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean h() {
/* 102 */     this.i.clear();
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */     
/* 108 */     for (i = -1; i <= 1; i++) {
/* 109 */       for (int j = -1; j <= 1; j++) {
/* 110 */         for (int k = -1; k <= 1; k++) {
/* 111 */           BlockPosition blockposition = this.position.b(i, j, k);
/*     */           
/* 113 */           if (!this.world.A(blockposition)) {
/* 114 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 120 */     for (i = -2; i <= 2; i++) {
/* 121 */       for (int j = -2; j <= 2; j++) {
/* 122 */         for (int k = -2; k <= 2; k++) {
/* 123 */           int l = Math.abs(i);
/* 124 */           int i1 = Math.abs(j);
/* 125 */           int j1 = Math.abs(k);
/*     */           
/* 127 */           if ((l > 1 || i1 > 1 || j1 > 1) && ((i == 0 && (i1 == 2 || j1 == 2)) || (j == 0 && (l == 2 || j1 == 2)) || (k == 0 && (l == 2 || i1 == 2)))) {
/* 128 */             BlockPosition blockposition1 = this.position.b(i, j, k);
/* 129 */             IBlockData iblockdata = this.world.getType(blockposition1);
/* 130 */             Block[] ablock = b;
/* 131 */             int k1 = ablock.length;
/*     */             
/* 133 */             for (int l1 = 0; l1 < k1; l1++) {
/* 134 */               Block block = ablock[l1];
/*     */               
/* 136 */               if (iblockdata.a(block)) {
/* 137 */                 this.i.add(blockposition1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     b((this.i.size() >= 42));
/* 146 */     return (this.i.size() >= 16);
/*     */   }
/*     */   
/*     */   private void j() {
/* 150 */     int i = this.i.size();
/* 151 */     int j = i / 7 * 16;
/* 152 */     int k = this.position.getX();
/* 153 */     int l = this.position.getY();
/* 154 */     int i1 = this.position.getZ();
/* 155 */     AxisAlignedBB axisalignedbb = (new AxisAlignedBB(k, l, i1, (k + 1), (l + 1), (i1 + 1))).g(j).b(0.0D, this.world.getBuildHeight(), 0.0D);
/* 156 */     List<EntityHuman> list = this.world.a(EntityHuman.class, axisalignedbb);
/*     */     
/* 158 */     if (!list.isEmpty()) {
/* 159 */       Iterator<EntityHuman> iterator = list.iterator();
/*     */       
/* 161 */       while (iterator.hasNext()) {
/* 162 */         EntityHuman entityhuman = iterator.next();
/*     */         
/* 164 */         if (this.position.a(entityhuman.getChunkCoordinates(), j) && entityhuman.isInWaterOrRain()) {
/* 165 */           entityhuman.addEffect(new MobEffect(MobEffects.CONDUIT_POWER, 260, 0, true, true), EntityPotionEffectEvent.Cause.CONDUIT);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void k() {
/* 173 */     EntityLiving entityliving = this.target;
/* 174 */     int i = this.i.size();
/*     */     
/* 176 */     if (i < 42) {
/* 177 */       this.target = null;
/* 178 */     } else if (this.target == null && this.k != null) {
/* 179 */       this.target = x();
/* 180 */       this.k = null;
/* 181 */     } else if (this.target == null) {
/* 182 */       List<EntityLiving> list = this.world.a(EntityLiving.class, m(), entityliving1 -> 
/* 183 */           (entityliving1 instanceof IMonster && entityliving1.isInWaterOrRain()));
/*     */ 
/*     */       
/* 186 */       if (!list.isEmpty()) {
/* 187 */         this.target = list.get(this.world.random.nextInt(list.size()));
/*     */       }
/* 189 */     } else if (!this.target.isAlive() || !this.position.a(this.target.getChunkCoordinates(), 8.0D)) {
/* 190 */       this.target = null;
/*     */     } 
/*     */     
/* 193 */     if (this.target != null) {
/*     */       
/* 195 */       CraftEventFactory.blockDamage = (Block)CraftBlock.at(this.world, this.position);
/* 196 */       if (this.target.damageEntity(DamageSource.MAGIC, 4.0F)) {
/* 197 */         this.world.playSound((EntityHuman)null, this.target.locX(), this.target.locY(), this.target.locZ(), SoundEffects.BLOCK_CONDUIT_ATTACK_TARGET, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       }
/* 199 */       CraftEventFactory.blockDamage = null;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (entityliving != this.target) {
/* 204 */       IBlockData iblockdata = getBlock();
/*     */       
/* 206 */       this.world.notify(this.position, iblockdata, iblockdata, 2);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void l() {
/* 212 */     if (this.k == null) {
/* 213 */       this.target = null;
/* 214 */     } else if (this.target == null || !this.target.getUniqueID().equals(this.k)) {
/* 215 */       this.target = x();
/* 216 */       if (this.target == null) {
/* 217 */         this.k = null;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private AxisAlignedBB m() {
/* 224 */     int i = this.position.getX();
/* 225 */     int j = this.position.getY();
/* 226 */     int k = this.position.getZ();
/*     */     
/* 228 */     return (new AxisAlignedBB(i, j, k, (i + 1), (j + 1), (k + 1))).g(8.0D);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntityLiving x() {
/* 233 */     List<EntityLiving> list = this.world.a(EntityLiving.class, m(), entityliving -> entityliving.getUniqueID().equals(this.k));
/*     */ 
/*     */ 
/*     */     
/* 237 */     return (list.size() == 1) ? list.get(0) : null;
/*     */   }
/*     */   
/*     */   private void y() {
/* 241 */     Random random = this.world.random;
/* 242 */     double d0 = (MathHelper.sin((this.a + 35) * 0.1F) / 2.0F + 0.5F);
/*     */     
/* 244 */     d0 = (d0 * d0 + d0) * 0.30000001192092896D;
/* 245 */     Vec3D vec3d = new Vec3D(this.position.getX() + 0.5D, this.position.getY() + 1.5D + d0, this.position.getZ() + 0.5D);
/* 246 */     Iterator<BlockPosition> iterator = this.i.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     while (iterator.hasNext()) {
/* 252 */       BlockPosition blockposition = iterator.next();
/*     */       
/* 254 */       if (random.nextInt(50) == 0) {
/* 255 */         float f = -0.5F + random.nextFloat();
/* 256 */         float f1 = -2.0F + random.nextFloat();
/* 257 */         float f2 = -0.5F + random.nextFloat();
/* 258 */         BlockPosition blockposition1 = blockposition.b(this.position);
/* 259 */         Vec3D vec3d1 = (new Vec3D(f, f1, f2)).add(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ());
/*     */         
/* 261 */         this.world.addParticle(Particles.NAUTILUS, vec3d.x, vec3d.y, vec3d.z, vec3d1.x, vec3d1.y, vec3d1.z);
/*     */       } 
/*     */     } 
/*     */     
/* 265 */     if (this.target != null) {
/* 266 */       Vec3D vec3d2 = new Vec3D(this.target.locX(), this.target.getHeadY(), this.target.locZ());
/* 267 */       float f3 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
/*     */       
/* 269 */       float f = -1.0F + random.nextFloat() * this.target.getHeight();
/* 270 */       float f1 = (-0.5F + random.nextFloat()) * (3.0F + this.target.getWidth());
/* 271 */       Vec3D vec3d3 = new Vec3D(f3, f, f1);
/*     */       
/* 273 */       this.world.addParticle(Particles.NAUTILUS, vec3d2.x, vec3d2.y, vec3d2.z, vec3d3.x, vec3d3.y, vec3d3.z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean d() {
/* 279 */     return this.g;
/*     */   }
/*     */   
/*     */   private void a(boolean flag) {
/* 283 */     if (flag != this.g) {
/* 284 */       a(flag ? SoundEffects.BLOCK_CONDUIT_ACTIVATE : SoundEffects.BLOCK_CONDUIT_DEACTIVATE);
/*     */     }
/*     */     
/* 287 */     this.g = flag;
/*     */   }
/*     */   
/*     */   private void b(boolean flag) {
/* 291 */     this.h = flag;
/*     */   }
/*     */   
/*     */   public void a(SoundEffect soundeffect) {
/* 295 */     this.world.playSound((EntityHuman)null, this.position, soundeffect, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityConduit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */