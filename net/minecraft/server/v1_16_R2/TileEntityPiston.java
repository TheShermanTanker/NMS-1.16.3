/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class TileEntityPiston extends TileEntity implements ITickable {
/*     */   private IBlockData a;
/*     */   private EnumDirection b;
/*     */   private boolean c;
/*     */   private boolean g;
/*  12 */   private static final ThreadLocal<EnumDirection> h = ThreadLocal.withInitial(() -> null);
/*     */   
/*     */   private float i;
/*     */   
/*     */   private float j;
/*     */   private long k;
/*     */   private int l;
/*     */   
/*     */   public TileEntityPiston() {
/*  21 */     super(TileEntityTypes.PISTON);
/*     */   }
/*     */   
/*     */   public TileEntityPiston(IBlockData iblockdata, EnumDirection enumdirection, boolean flag, boolean flag1) {
/*  25 */     this();
/*  26 */     this.a = iblockdata;
/*  27 */     this.b = enumdirection;
/*  28 */     this.c = flag;
/*  29 */     this.g = flag1;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/*  34 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  38 */     return this.c;
/*     */   }
/*     */   
/*     */   public EnumDirection f() {
/*  42 */     return this.b;
/*     */   }
/*     */   public final boolean isHead() {
/*  45 */     return h();
/*     */   }
/*     */   public boolean h() {
/*  48 */     return this.g;
/*     */   }
/*     */   
/*     */   public float a(float f) {
/*  52 */     if (f > 1.0F) {
/*  53 */       f = 1.0F;
/*     */     }
/*     */     
/*  56 */     return MathHelper.g(f, this.j, this.i);
/*     */   }
/*     */   
/*     */   private float e(float f) {
/*  60 */     return this.c ? (f - 1.0F) : (1.0F - f);
/*     */   }
/*     */   
/*     */   private IBlockData x() {
/*  64 */     return (!d() && h() && this.a.getBlock() instanceof BlockPiston) ? Blocks.PISTON_HEAD.getBlockData().set(BlockPistonExtension.SHORT, Boolean.valueOf((this.i > 0.25F))).set(BlockPistonExtension.TYPE, this.a.a(Blocks.STICKY_PISTON) ? BlockPropertyPistonType.STICKY : BlockPropertyPistonType.DEFAULT).set(BlockPistonExtension.FACING, this.a.get(BlockPiston.FACING)) : this.a;
/*     */   }
/*     */   
/*     */   private void f(float f) {
/*  68 */     EnumDirection enumdirection = j();
/*  69 */     double d0 = (f - this.i);
/*  70 */     VoxelShape voxelshape = x().getCollisionShape(this.world, getPosition());
/*     */     
/*  72 */     if (!voxelshape.isEmpty()) {
/*  73 */       AxisAlignedBB axisalignedbb = a(voxelshape.getBoundingBox());
/*  74 */       List<Entity> list = this.world.getEntities((Entity)null, PistonUtil.a(axisalignedbb, enumdirection, d0).b(axisalignedbb));
/*     */       
/*  76 */       if (!list.isEmpty()) {
/*  77 */         List<AxisAlignedBB> list1 = voxelshape.d();
/*  78 */         boolean flag = this.a.a(Blocks.SLIME_BLOCK);
/*  79 */         Iterator<Entity> iterator = list.iterator();
/*     */         
/*  81 */         while (iterator.hasNext()) {
/*  82 */           Entity entity = iterator.next();
/*     */           
/*  84 */           if (entity.getPushReaction() != EnumPistonReaction.IGNORE) {
/*  85 */             if (flag) {
/*  86 */               if (entity instanceof EntityPlayer) {
/*     */                 continue;
/*     */               }
/*     */               
/*  90 */               Vec3D vec3d = entity.getMot();
/*  91 */               double d1 = vec3d.x;
/*  92 */               double d2 = vec3d.y;
/*  93 */               double d3 = vec3d.z;
/*     */               
/*  95 */               switch (enumdirection.n()) {
/*     */                 case EAST:
/*  97 */                   d1 = enumdirection.getAdjacentX();
/*     */                   break;
/*     */                 case WEST:
/* 100 */                   d2 = enumdirection.getAdjacentY();
/*     */                   break;
/*     */                 case UP:
/* 103 */                   d3 = enumdirection.getAdjacentZ();
/*     */                   break;
/*     */               } 
/* 106 */               entity.setMot(d1, d2, d3);
/*     */             } 
/*     */             
/* 109 */             double d4 = 0.0D;
/* 110 */             Iterator<AxisAlignedBB> iterator1 = list1.iterator();
/*     */             
/* 112 */             while (iterator1.hasNext()) {
/* 113 */               AxisAlignedBB axisalignedbb1 = iterator1.next();
/* 114 */               AxisAlignedBB axisalignedbb2 = PistonUtil.a(a(axisalignedbb1), enumdirection, d0);
/* 115 */               AxisAlignedBB axisalignedbb3 = entity.getBoundingBox();
/*     */               
/* 117 */               if (axisalignedbb2.c(axisalignedbb3)) {
/* 118 */                 d4 = Math.max(d4, a(axisalignedbb2, enumdirection, axisalignedbb3));
/* 119 */                 if (d4 >= d0) {
/*     */                   break;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 125 */             if (d4 > 0.0D) {
/* 126 */               d4 = Math.min(d4, d0) + 0.01D;
/* 127 */               a(enumdirection, entity, d4, enumdirection);
/* 128 */               if (!this.c && this.g) {
/* 129 */                 a(entity, enumdirection, d0);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(EnumDirection enumdirection, Entity entity, double d0, EnumDirection enumdirection1) {
/* 140 */     h.set(enumdirection);
/* 141 */     entity.move(EnumMoveType.PISTON, new Vec3D(d0 * enumdirection1.getAdjacentX(), d0 * enumdirection1.getAdjacentY(), d0 * enumdirection1.getAdjacentZ()));
/* 142 */     h.set(null);
/*     */   }
/*     */   
/*     */   private void g(float f) {
/* 146 */     if (y()) {
/* 147 */       EnumDirection enumdirection = j();
/*     */       
/* 149 */       if (enumdirection.n().d()) {
/* 150 */         double d0 = this.a.getCollisionShape(this.world, this.position).c(EnumDirection.EnumAxis.Y);
/* 151 */         AxisAlignedBB axisalignedbb = a(new AxisAlignedBB(0.0D, d0, 0.0D, 1.0D, 1.5000000999999998D, 1.0D));
/* 152 */         double d1 = (f - this.i);
/* 153 */         List<Entity> list = this.world.getEntities((Entity)null, axisalignedbb, entity -> a(axisalignedbb, entity));
/*     */ 
/*     */         
/* 156 */         Iterator<Entity> iterator = list.iterator();
/*     */         
/* 158 */         while (iterator.hasNext()) {
/* 159 */           Entity entity = iterator.next();
/*     */           
/* 161 */           a(enumdirection, entity, d1, enumdirection);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(AxisAlignedBB axisalignedbb, Entity entity) {
/* 169 */     return (entity.getPushReaction() == EnumPistonReaction.NORMAL && entity.isOnGround() && entity.locX() >= axisalignedbb.minX && entity.locX() <= axisalignedbb.maxX && entity.locZ() >= axisalignedbb.minZ && entity.locZ() <= axisalignedbb.maxZ);
/*     */   }
/*     */   
/*     */   private boolean y() {
/* 173 */     return this.a.a(Blocks.HONEY_BLOCK);
/*     */   }
/*     */   
/*     */   public EnumDirection j() {
/* 177 */     return this.c ? this.b : this.b.opposite();
/*     */   }
/*     */   
/*     */   private static double a(AxisAlignedBB axisalignedbb, EnumDirection enumdirection, AxisAlignedBB axisalignedbb1) {
/* 181 */     switch (enumdirection)
/*     */     { case EAST:
/* 183 */         return axisalignedbb.maxX - axisalignedbb1.minX;
/*     */       case WEST:
/* 185 */         return axisalignedbb1.maxX - axisalignedbb.minX;
/*     */       
/*     */       default:
/* 188 */         return axisalignedbb.maxY - axisalignedbb1.minY;
/*     */       case DOWN:
/* 190 */         return axisalignedbb1.maxY - axisalignedbb.minY;
/*     */       case SOUTH:
/* 192 */         return axisalignedbb.maxZ - axisalignedbb1.minZ;
/*     */       case NORTH:
/* 194 */         break; }  return axisalignedbb1.maxZ - axisalignedbb.minZ;
/*     */   }
/*     */ 
/*     */   
/*     */   private AxisAlignedBB a(AxisAlignedBB axisalignedbb) {
/* 199 */     double d0 = e(this.i);
/*     */     
/* 201 */     return axisalignedbb.d(this.position.getX() + d0 * this.b.getAdjacentX(), this.position.getY() + d0 * this.b.getAdjacentY(), this.position.getZ() + d0 * this.b.getAdjacentZ());
/*     */   }
/*     */   
/*     */   private void a(Entity entity, EnumDirection enumdirection, double d0) {
/* 205 */     AxisAlignedBB axisalignedbb = entity.getBoundingBox();
/* 206 */     AxisAlignedBB axisalignedbb1 = VoxelShapes.b().getBoundingBox().a(this.position);
/*     */     
/* 208 */     if (axisalignedbb.c(axisalignedbb1)) {
/* 209 */       EnumDirection enumdirection1 = enumdirection.opposite();
/* 210 */       double d1 = a(axisalignedbb1, enumdirection1, axisalignedbb) + 0.01D;
/* 211 */       double d2 = a(axisalignedbb1, enumdirection1, axisalignedbb.a(axisalignedbb1)) + 0.01D;
/*     */       
/* 213 */       if (Math.abs(d1 - d2) < 0.01D) {
/* 214 */         d1 = Math.min(d1, d0) + 0.01D;
/* 215 */         a(enumdirection, entity, d1, enumdirection1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData k() {
/* 222 */     return this.a;
/*     */   }
/*     */   
/*     */   public void l() {
/* 226 */     if (this.world != null && (this.j < 1.0F || this.world.isClientSide)) {
/* 227 */       this.i = 1.0F;
/* 228 */       this.j = this.i;
/* 229 */       this.world.removeTileEntity(this.position);
/* 230 */       al_();
/* 231 */       if (this.world.getType(this.position).a(Blocks.MOVING_PISTON)) {
/*     */         IBlockData iblockdata;
/*     */         
/* 234 */         if (this.g) {
/* 235 */           iblockdata = Blocks.AIR.getBlockData();
/*     */         } else {
/* 237 */           iblockdata = Block.b(this.a, this.world, this.position);
/*     */         } 
/*     */         
/* 240 */         this.world.setTypeAndData(this.position, iblockdata, 3);
/* 241 */         this.world.a(this.position, iblockdata.getBlock(), this.position);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 249 */     this.k = this.world.getTime();
/* 250 */     this.j = this.i;
/* 251 */     if (this.j >= 1.0F) {
/* 252 */       if (this.world.isClientSide && this.l < 5) {
/* 253 */         this.l++;
/*     */       } else {
/* 255 */         this.world.removeTileEntity(this.position);
/* 256 */         al_();
/* 257 */         if (this.a != null && this.world.getType(this.position).a(Blocks.MOVING_PISTON)) {
/* 258 */           IBlockData iblockdata = Block.b(this.a, this.world, this.position);
/*     */           
/* 260 */           if (iblockdata.isAir()) {
/* 261 */             this.world.setTypeAndData(this.position, this.a, PaperConfig.allowPistonDuplication ? 84 : 86);
/* 262 */             Block.a(this.a, iblockdata, this.world, this.position, 3);
/*     */           } else {
/* 264 */             if (iblockdata.b(BlockProperties.C) && ((Boolean)iblockdata.get(BlockProperties.C)).booleanValue()) {
/* 265 */               iblockdata = iblockdata.set(BlockProperties.C, Boolean.valueOf(false));
/*     */             }
/*     */             
/* 268 */             this.world.setTypeAndData(this.position, iblockdata, 67);
/* 269 */             this.world.a(this.position, iblockdata.getBlock(), this.position);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 275 */       float f = this.i + 0.5F;
/*     */       
/* 277 */       f(f);
/* 278 */       g(f);
/* 279 */       this.i = f;
/* 280 */       if (this.i >= 1.0F) {
/* 281 */         this.i = 1.0F;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 289 */     super.load(iblockdata, nbttagcompound);
/* 290 */     this.a = GameProfileSerializer.c(nbttagcompound.getCompound("blockState"));
/* 291 */     this.b = EnumDirection.fromType1(nbttagcompound.getInt("facing"));
/* 292 */     this.i = nbttagcompound.getFloat("progress");
/* 293 */     this.j = this.i;
/* 294 */     this.c = nbttagcompound.getBoolean("extending");
/* 295 */     this.g = nbttagcompound.getBoolean("source");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 300 */     super.save(nbttagcompound);
/* 301 */     nbttagcompound.set("blockState", GameProfileSerializer.a(this.a));
/* 302 */     nbttagcompound.setInt("facing", this.b.c());
/* 303 */     nbttagcompound.setFloat("progress", this.j);
/* 304 */     nbttagcompound.setBoolean("extending", this.c);
/* 305 */     nbttagcompound.setBoolean("source", this.g);
/* 306 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public VoxelShape a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*     */     VoxelShape voxelshape;
/*     */     IBlockData iblockdata;
/* 312 */     if (!this.c && this.g) {
/* 313 */       voxelshape = this.a.set(BlockPiston.EXTENDED, Boolean.valueOf(true)).getCollisionShape(iblockaccess, blockposition);
/*     */     } else {
/* 315 */       voxelshape = VoxelShapes.a();
/*     */     } 
/*     */     
/* 318 */     EnumDirection enumdirection = h.get();
/*     */     
/* 320 */     if (this.i < 1.0D && enumdirection == j()) {
/* 321 */       return voxelshape;
/*     */     }
/*     */ 
/*     */     
/* 325 */     if (h()) {
/* 326 */       iblockdata = Blocks.PISTON_HEAD.getBlockData().set(BlockPistonExtension.FACING, this.b).set(BlockPistonExtension.SHORT, Boolean.valueOf((this.c != ((1.0F - this.i < 0.25F)))));
/*     */     } else {
/* 328 */       iblockdata = this.a;
/*     */     } 
/*     */     
/* 331 */     float f = e(this.i);
/* 332 */     double d0 = (this.b.getAdjacentX() * f);
/* 333 */     double d1 = (this.b.getAdjacentY() * f);
/* 334 */     double d2 = (this.b.getAdjacentZ() * f);
/*     */     
/* 336 */     return VoxelShapes.a(voxelshape, iblockdata.getCollisionShape(iblockaccess, blockposition).a(d0, d1, d2));
/*     */   }
/*     */ 
/*     */   
/*     */   public long m() {
/* 341 */     return this.k;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityPiston.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */