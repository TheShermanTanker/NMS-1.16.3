/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.stream.IntStream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ 
/*     */ public class TileEntityShulkerBox
/*     */   extends TileEntityLootable
/*     */   implements IWorldInventory, ITickable {
/*  13 */   private static final int[] a = IntStream.range(0, 27).toArray();
/*     */   
/*     */   private NonNullList<ItemStack> contents;
/*     */   
/*     */   public int viewingCount;
/*     */   private AnimationPhase i;
/*     */   private float j;
/*     */   private float k;
/*     */   @Nullable
/*     */   private EnumColor l;
/*     */   private boolean m;
/*  24 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  25 */   private int maxStack = 64;
/*     */   public boolean opened;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  29 */     return this.contents;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  33 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  37 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  41 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  46 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  50 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntityShulkerBox(@Nullable EnumColor enumcolor) {
/*  55 */     super(TileEntityTypes.SHULKER_BOX);
/*  56 */     this.contents = NonNullList.a(27, ItemStack.b);
/*  57 */     this.i = AnimationPhase.CLOSED;
/*  58 */     this.l = enumcolor;
/*     */   }
/*     */   
/*     */   public TileEntityShulkerBox() {
/*  62 */     this((EnumColor)null);
/*  63 */     this.m = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  68 */     h();
/*  69 */     if (this.i == AnimationPhase.OPENING || this.i == AnimationPhase.CLOSING) {
/*  70 */       m();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void h() {
/*  76 */     this.k = this.j;
/*  77 */     switch (this.i) {
/*     */       case X:
/*  79 */         this.j = 0.0F;
/*     */         break;
/*     */       case Y:
/*  82 */         this.j += 0.1F;
/*  83 */         if (this.j >= 1.0F) {
/*  84 */           m();
/*  85 */           this.i = AnimationPhase.OPENED;
/*  86 */           this.j = 1.0F;
/*  87 */           x();
/*     */         } 
/*     */         break;
/*     */       case Z:
/*  91 */         this.j -= 0.1F;
/*  92 */         if (this.j <= 0.0F) {
/*  93 */           this.i = AnimationPhase.CLOSED;
/*  94 */           this.j = 0.0F;
/*  95 */           x();
/*     */         } 
/*     */         break;
/*     */       case null:
/*  99 */         this.j = 1.0F;
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public AnimationPhase j() {
/* 105 */     return this.i;
/*     */   }
/*     */   
/*     */   public AxisAlignedBB a(IBlockData iblockdata) {
/* 109 */     return b((EnumDirection)iblockdata.get(BlockShulkerBox.a));
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(EnumDirection enumdirection) {
/* 113 */     float f = a(1.0F);
/*     */     
/* 115 */     return VoxelShapes.b().getBoundingBox().b((0.5F * f * enumdirection.getAdjacentX()), (0.5F * f * enumdirection.getAdjacentY()), (0.5F * f * enumdirection.getAdjacentZ()));
/*     */   }
/*     */   
/*     */   private AxisAlignedBB c(EnumDirection enumdirection) {
/* 119 */     EnumDirection enumdirection1 = enumdirection.opposite();
/*     */     
/* 121 */     return b(enumdirection).a(enumdirection1.getAdjacentX(), enumdirection1.getAdjacentY(), enumdirection1.getAdjacentZ());
/*     */   }
/*     */   
/*     */   private void m() {
/* 125 */     IBlockData iblockdata = this.world.getType(getPosition());
/*     */     
/* 127 */     if (iblockdata.getBlock() instanceof BlockShulkerBox) {
/* 128 */       EnumDirection enumdirection = (EnumDirection)iblockdata.get(BlockShulkerBox.a);
/* 129 */       AxisAlignedBB axisalignedbb = c(enumdirection).a(this.position);
/* 130 */       List<Entity> list = this.world.getEntities((Entity)null, axisalignedbb);
/*     */       
/* 132 */       if (!list.isEmpty()) {
/* 133 */         for (int i = 0; i < list.size(); i++) {
/* 134 */           Entity entity = list.get(i);
/*     */           
/* 136 */           if (entity.getPushReaction() != EnumPistonReaction.IGNORE) {
/* 137 */             double d0 = 0.0D;
/* 138 */             double d1 = 0.0D;
/* 139 */             double d2 = 0.0D;
/* 140 */             AxisAlignedBB axisalignedbb1 = entity.getBoundingBox();
/*     */             
/* 142 */             switch (enumdirection.n()) {
/*     */               case X:
/* 144 */                 if (enumdirection.e() == EnumDirection.EnumAxisDirection.POSITIVE) {
/* 145 */                   d0 = axisalignedbb.maxX - axisalignedbb1.minX;
/*     */                 } else {
/* 147 */                   d0 = axisalignedbb1.maxX - axisalignedbb.minX;
/*     */                 } 
/*     */                 
/* 150 */                 d0 += 0.01D;
/*     */                 break;
/*     */               case Y:
/* 153 */                 if (enumdirection.e() == EnumDirection.EnumAxisDirection.POSITIVE) {
/* 154 */                   d1 = axisalignedbb.maxY - axisalignedbb1.minY;
/*     */                 } else {
/* 156 */                   d1 = axisalignedbb1.maxY - axisalignedbb.minY;
/*     */                 } 
/*     */                 
/* 159 */                 d1 += 0.01D;
/*     */                 break;
/*     */               case Z:
/* 162 */                 if (enumdirection.e() == EnumDirection.EnumAxisDirection.POSITIVE) {
/* 163 */                   d2 = axisalignedbb.maxZ - axisalignedbb1.minZ;
/*     */                 } else {
/* 165 */                   d2 = axisalignedbb1.maxZ - axisalignedbb.minZ;
/*     */                 } 
/*     */                 
/* 168 */                 d2 += 0.01D;
/*     */                 break;
/*     */             } 
/* 171 */             entity.move(EnumMoveType.SHULKER_BOX, new Vec3D(d0 * enumdirection.getAdjacentX(), d1 * enumdirection.getAdjacentY(), d2 * enumdirection.getAdjacentZ()));
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 181 */     return this.contents.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setProperty(int i, int j) {
/* 186 */     if (i == 1) {
/* 187 */       this.viewingCount = j;
/* 188 */       if (j == 0) {
/* 189 */         this.i = AnimationPhase.CLOSING;
/* 190 */         x();
/*     */       } 
/*     */       
/* 193 */       if (j == 1) {
/* 194 */         this.i = AnimationPhase.OPENING;
/* 195 */         x();
/*     */       } 
/*     */       
/* 198 */       return true;
/*     */     } 
/* 200 */     return super.setProperty(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   private void x() {
/* 205 */     getBlock().a(getWorld(), getPosition(), 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void startOpen(EntityHuman entityhuman) {
/* 210 */     if (!entityhuman.isSpectator()) {
/* 211 */       if (this.viewingCount < 0) {
/* 212 */         this.viewingCount = 0;
/*     */       }
/*     */       
/* 215 */       this.viewingCount++;
/* 216 */       if (this.opened)
/* 217 */         return;  this.world.playBlockAction(this.position, getBlock().getBlock(), 1, this.viewingCount);
/* 218 */       if (this.viewingCount == 1) {
/* 219 */         this.world.playSound((EntityHuman)null, this.position, SoundEffects.BLOCK_SHULKER_BOX_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void closeContainer(EntityHuman entityhuman) {
/* 227 */     if (!entityhuman.isSpectator()) {
/* 228 */       this.viewingCount--;
/* 229 */       if (this.opened)
/* 230 */         return;  this.world.playBlockAction(this.position, getBlock().getBlock(), 1, this.viewingCount);
/* 231 */       if (this.viewingCount <= 0) {
/* 232 */         this.world.playSound((EntityHuman)null, this.position, SoundEffects.BLOCK_SHULKER_BOX_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/* 240 */     return new ChatMessage("container.shulkerBox");
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 245 */     super.load(iblockdata, nbttagcompound);
/* 246 */     d(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 251 */     super.save(nbttagcompound);
/* 252 */     return e(nbttagcompound);
/*     */   }
/*     */   
/*     */   public void d(NBTTagCompound nbttagcompound) {
/* 256 */     this.contents = NonNullList.a(getSize(), ItemStack.b);
/* 257 */     if (!b(nbttagcompound) && nbttagcompound.hasKeyOfType("Items", 9)) {
/* 258 */       ContainerUtil.b(nbttagcompound, this.contents);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound e(NBTTagCompound nbttagcompound) {
/* 264 */     if (!c(nbttagcompound)) {
/* 265 */       ContainerUtil.a(nbttagcompound, this.contents, false);
/*     */     }
/*     */     
/* 268 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> f() {
/* 273 */     return this.contents;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NonNullList<ItemStack> nonnulllist) {
/* 278 */     this.contents = nonnulllist;
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 283 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 288 */     return !(Block.asBlock(itemstack.getItem()) instanceof BlockShulkerBox);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 293 */     return true;
/*     */   }
/*     */   
/*     */   public float a(float f) {
/* 297 */     return MathHelper.g(f, this.k, this.j);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 302 */     return new ContainerShulkerBox(i, playerinventory, this);
/*     */   }
/*     */   
/*     */   public boolean l() {
/* 306 */     return (this.i == AnimationPhase.CLOSED);
/*     */   }
/*     */   
/*     */   public enum AnimationPhase
/*     */   {
/* 311 */     CLOSED, OPENING, OPENED, CLOSING;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityShulkerBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */