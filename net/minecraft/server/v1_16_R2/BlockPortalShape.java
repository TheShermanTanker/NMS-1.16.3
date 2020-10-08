/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftPortalEvent;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.world.PortalCreateEvent;
/*     */ 
/*     */ public class BlockPortalShape {
/*     */   static {
/*  16 */     a = ((iblockdata, iblockaccess, blockposition) -> iblockdata.a(Blocks.OBSIDIAN));
/*     */   }
/*     */   private static final BlockBase.e a;
/*     */   private final GeneratorAccess b;
/*     */   private final EnumDirection.EnumAxis c;
/*     */   private final EnumDirection d;
/*     */   private int e;
/*     */   @Nullable
/*     */   private BlockPosition position;
/*     */   private int height;
/*     */   private int width;
/*  27 */   List<BlockState> blocks = new ArrayList<>();
/*     */   
/*     */   public static Optional<BlockPortalShape> a(GeneratorAccess generatoraccess, BlockPosition blockposition, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  30 */     return a(generatoraccess, blockposition, blockportalshape -> 
/*  31 */         (blockportalshape.a() && blockportalshape.e == 0), enumdirection_enumaxis);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Optional<BlockPortalShape> a(GeneratorAccess generatoraccess, BlockPosition blockposition, Predicate<BlockPortalShape> predicate, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  36 */     Optional<BlockPortalShape> optional = Optional.<BlockPortalShape>of(new BlockPortalShape(generatoraccess, blockposition, enumdirection_enumaxis)).filter(predicate);
/*     */     
/*  38 */     if (optional.isPresent()) {
/*  39 */       return optional;
/*     */     }
/*  41 */     EnumDirection.EnumAxis enumdirection_enumaxis1 = (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? EnumDirection.EnumAxis.Z : EnumDirection.EnumAxis.X;
/*     */     
/*  43 */     return Optional.<BlockPortalShape>of(new BlockPortalShape(generatoraccess, blockposition, enumdirection_enumaxis1)).filter(predicate);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPortalShape(GeneratorAccess generatoraccess, BlockPosition blockposition, EnumDirection.EnumAxis enumdirection_enumaxis) {
/*  48 */     this.b = generatoraccess;
/*  49 */     this.c = enumdirection_enumaxis;
/*  50 */     this.d = (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? EnumDirection.WEST : EnumDirection.SOUTH;
/*  51 */     this.position = a(blockposition);
/*  52 */     if (this.position == null) {
/*  53 */       this.position = blockposition;
/*  54 */       this.width = 1;
/*  55 */       this.height = 1;
/*     */     } else {
/*  57 */       this.width = d();
/*  58 */       if (this.width > 0) {
/*  59 */         this.height = e();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private BlockPosition a(BlockPosition blockposition) {
/*  67 */     for (int i = Math.max(0, blockposition.getY() - 21); blockposition.getY() > i && a(this.b.getType(blockposition.down())); blockposition = blockposition.down());
/*     */ 
/*     */ 
/*     */     
/*  71 */     EnumDirection enumdirection = this.d.opposite();
/*  72 */     int j = a(blockposition, enumdirection) - 1;
/*     */     
/*  74 */     return (j < 0) ? null : blockposition.shift(enumdirection, j);
/*     */   }
/*     */   
/*     */   private int d() {
/*  78 */     int i = a(this.position, this.d);
/*     */     
/*  80 */     return (i >= 2 && i <= 21) ? i : 0;
/*     */   }
/*     */   
/*     */   private int a(BlockPosition blockposition, EnumDirection enumdirection) {
/*  84 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*     */     
/*  86 */     for (int i = 0; i <= 21; i++) {
/*  87 */       blockposition_mutableblockposition.g(blockposition).c(enumdirection, i);
/*  88 */       IBlockData iblockdata = this.b.getType(blockposition_mutableblockposition);
/*     */       
/*  90 */       if (!a(iblockdata)) {
/*  91 */         if (a.test(iblockdata, this.b, blockposition_mutableblockposition)) {
/*  92 */           this.blocks.add(CraftBlock.at(this.b, blockposition_mutableblockposition).getState());
/*  93 */           return i;
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*  98 */       IBlockData iblockdata1 = this.b.getType(blockposition_mutableblockposition.c(EnumDirection.DOWN));
/*     */       
/* 100 */       if (!a.test(iblockdata1, this.b, blockposition_mutableblockposition)) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 105 */     return 0;
/*     */   }
/*     */   
/*     */   private int e() {
/* 109 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 110 */     int i = a(blockposition_mutableblockposition);
/*     */     
/* 112 */     return (i >= 3 && i <= 21 && a(blockposition_mutableblockposition, i)) ? i : 0;
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition.MutableBlockPosition blockposition_mutableblockposition, int i) {
/* 116 */     for (int j = 0; j < this.width; j++) {
/* 117 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = blockposition_mutableblockposition.g(this.position).c(EnumDirection.UP, i).c(this.d, j);
/*     */       
/* 119 */       if (!a.test(this.b.getType(blockposition_mutableblockposition1), this.b, blockposition_mutableblockposition1)) {
/* 120 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   private int a(BlockPosition.MutableBlockPosition blockposition_mutableblockposition) {
/* 128 */     for (int i = 0; i < 21; i++) {
/* 129 */       blockposition_mutableblockposition.g(this.position).c(EnumDirection.UP, i).c(this.d, -1);
/* 130 */       if (!a.test(this.b.getType(blockposition_mutableblockposition), this.b, blockposition_mutableblockposition)) {
/* 131 */         return i;
/*     */       }
/*     */       
/* 134 */       blockposition_mutableblockposition.g(this.position).c(EnumDirection.UP, i).c(this.d, this.width);
/* 135 */       if (!a.test(this.b.getType(blockposition_mutableblockposition), this.b, blockposition_mutableblockposition)) {
/* 136 */         return i;
/*     */       }
/*     */       
/* 139 */       for (int j = 0; j < this.width; j++) {
/* 140 */         blockposition_mutableblockposition.g(this.position).c(EnumDirection.UP, i).c(this.d, j);
/* 141 */         IBlockData iblockdata = this.b.getType(blockposition_mutableblockposition);
/*     */         
/* 143 */         if (!a(iblockdata)) {
/* 144 */           return i;
/*     */         }
/*     */         
/* 147 */         if (iblockdata.a(Blocks.NETHER_PORTAL)) {
/* 148 */           this.e++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     return 21;
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockData iblockdata) {
/* 157 */     return (iblockdata.isAir() || iblockdata.a(TagsBlock.FIRE) || iblockdata.a(Blocks.NETHER_PORTAL));
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 161 */     return (this.position != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean createPortal() {
/* 166 */     return createPortal(null);
/*     */   }
/*     */   public boolean createPortal(ItemActionContext itemActionContext) {
/* 169 */     CraftWorld craftWorld = this.b.getMinecraftWorld().getWorld();
/*     */ 
/*     */     
/* 172 */     IBlockData iblockdata = Blocks.NETHER_PORTAL.getBlockData().set(BlockPortal.AXIS, this.c);
/*     */     
/* 174 */     BlockPosition.a(this.position, this.position.shift(EnumDirection.UP, this.height - 1).shift(this.d, this.width - 1)).forEach(blockposition -> {
/*     */           CraftBlockState state = CraftBlockState.getBlockState(this.b.getMinecraftWorld(), blockposition, 18);
/*     */           
/*     */           state.setData(iblockdata);
/*     */           this.blocks.add(state);
/*     */         });
/* 180 */     PortalCreateEvent event = new PortalCreateEvent(this.blocks, (World)craftWorld, (itemActionContext == null || itemActionContext.getEntity() == null) ? null : (Entity)itemActionContext.getEntity().getBukkitEntity(), PortalCreateEvent.CreateReason.FIRE);
/* 181 */     (this.b.getMinecraftWorld().getMinecraftServer()).server.getPluginManager().callEvent((Event)event);
/*     */     
/* 183 */     if (event.isCancelled()) {
/* 184 */       return false;
/*     */     }
/*     */     
/* 187 */     BlockPosition.a(this.position, this.position.shift(EnumDirection.UP, this.height - 1).shift(this.d, this.width - 1)).forEach(blockposition -> this.b.setTypeAndData(blockposition, iblockdata, 18));
/*     */ 
/*     */     
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 194 */     return (a() && this.e == this.width * this.height);
/*     */   }
/*     */   
/*     */   public static Vec3D a(BlockUtil.Rectangle blockutil_rectangle, EnumDirection.EnumAxis enumdirection_enumaxis, Vec3D vec3d, EntitySize entitysize) {
/* 198 */     double d2, d3, d0 = blockutil_rectangle.side1 - entitysize.width;
/* 199 */     double d1 = blockutil_rectangle.side2 - entitysize.height;
/* 200 */     BlockPosition blockposition = blockutil_rectangle.origin;
/*     */ 
/*     */     
/* 203 */     if (d0 > 0.0D) {
/* 204 */       float f = blockposition.a(enumdirection_enumaxis) + entitysize.width / 2.0F;
/*     */       
/* 206 */       d2 = MathHelper.a(MathHelper.c(vec3d.a(enumdirection_enumaxis) - f, 0.0D, d0), 0.0D, 1.0D);
/*     */     } else {
/* 208 */       d2 = 0.5D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 214 */     if (d1 > 0.0D) {
/* 215 */       EnumDirection.EnumAxis enumAxis = EnumDirection.EnumAxis.Y;
/* 216 */       d3 = MathHelper.a(MathHelper.c(vec3d.a(enumAxis) - blockposition.a(enumAxis), 0.0D, d1), 0.0D, 1.0D);
/*     */     } else {
/* 218 */       d3 = 0.0D;
/*     */     } 
/*     */     
/* 221 */     EnumDirection.EnumAxis enumdirection_enumaxis1 = (enumdirection_enumaxis == EnumDirection.EnumAxis.X) ? EnumDirection.EnumAxis.Z : EnumDirection.EnumAxis.X;
/* 222 */     double d4 = vec3d.a(enumdirection_enumaxis1) - blockposition.a(enumdirection_enumaxis1) + 0.5D;
/*     */     
/* 224 */     return new Vec3D(d2, d3, d4);
/*     */   }
/*     */   
/*     */   public static ShapeDetectorShape a(WorldServer worldserver, BlockUtil.Rectangle blockutil_rectangle, EnumDirection.EnumAxis enumdirection_enumaxis, Vec3D vec3d, EntitySize entitysize, Vec3D vec3d1, float f, float f1, CraftPortalEvent portalEventInfo) {
/* 228 */     BlockPosition blockposition = blockutil_rectangle.origin;
/* 229 */     IBlockData iblockdata = worldserver.getType(blockposition);
/* 230 */     EnumDirection.EnumAxis enumdirection_enumaxis1 = (EnumDirection.EnumAxis)iblockdata.get(BlockProperties.E);
/* 231 */     double d0 = blockutil_rectangle.side1;
/* 232 */     double d1 = blockutil_rectangle.side2;
/* 233 */     int i = (enumdirection_enumaxis == enumdirection_enumaxis1) ? 0 : 90;
/* 234 */     Vec3D vec3d2 = (enumdirection_enumaxis == enumdirection_enumaxis1) ? vec3d1 : new Vec3D(vec3d1.z, vec3d1.y, -vec3d1.x);
/* 235 */     double d2 = entitysize.width / 2.0D + (d0 - entitysize.width) * vec3d.getX();
/* 236 */     double d3 = (d1 - entitysize.height) * vec3d.getY();
/* 237 */     double d4 = 0.5D + vec3d.getZ();
/* 238 */     boolean flag = (enumdirection_enumaxis1 == EnumDirection.EnumAxis.X);
/* 239 */     Vec3D vec3d3 = new Vec3D(blockposition.getX() + (flag ? d2 : d4), blockposition.getY() + d3, blockposition.getZ() + (flag ? d4 : d2));
/*     */     
/* 241 */     return new ShapeDetectorShape(vec3d3, vec3d2, f + i, f1, worldserver, portalEventInfo);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPortalShape.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */