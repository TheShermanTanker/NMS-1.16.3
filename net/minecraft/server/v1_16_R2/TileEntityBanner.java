/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class TileEntityBanner
/*     */   extends TileEntity implements INamableTileEntity {
/*     */   @Nullable
/*     */   private IChatBaseComponent a;
/*     */   @Nullable
/*     */   public EnumColor color;
/*     */   @Nullable
/*     */   public NBTTagList patterns;
/*     */   private boolean g;
/*     */   @Nullable
/*     */   private List<Pair<EnumBannerPatternType, EnumColor>> h;
/*     */   
/*     */   public TileEntityBanner() {
/*  21 */     super(TileEntityTypes.BANNER);
/*  22 */     this.color = EnumColor.WHITE;
/*     */   }
/*     */   
/*     */   public TileEntityBanner(EnumColor enumcolor) {
/*  26 */     this();
/*  27 */     this.color = enumcolor;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getDisplayName() {
/*  32 */     return (this.a != null) ? this.a : new ChatMessage("block.minecraft.banner");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChatBaseComponent getCustomName() {
/*  38 */     return this.a;
/*     */   }
/*     */   
/*     */   public void a(IChatBaseComponent ichatbasecomponent) {
/*  42 */     this.a = ichatbasecomponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  47 */     super.save(nbttagcompound);
/*  48 */     if (this.patterns != null) {
/*  49 */       nbttagcompound.set("Patterns", this.patterns);
/*     */     }
/*     */     
/*  52 */     if (this.a != null) {
/*  53 */       nbttagcompound.setString("CustomName", IChatBaseComponent.ChatSerializer.a(this.a));
/*     */     }
/*     */     
/*  56 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  61 */     super.load(iblockdata, nbttagcompound);
/*  62 */     if (nbttagcompound.hasKeyOfType("CustomName", 8)) {
/*  63 */       this.a = MCUtil.getBaseComponentFromNbt("CustomName", nbttagcompound);
/*     */     }
/*     */     
/*  66 */     if (hasWorld()) {
/*  67 */       this.color = ((BlockBannerAbstract)getBlock().getBlock()).getColor();
/*     */     } else {
/*  69 */       this.color = null;
/*     */     } 
/*     */     
/*  72 */     this.patterns = nbttagcompound.getList("Patterns", 10);
/*     */     
/*  74 */     while (this.patterns.size() > 20) {
/*  75 */       this.patterns.remove(20);
/*     */     }
/*     */     
/*  78 */     this.h = null;
/*  79 */     this.g = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/*  85 */     return new PacketPlayOutTileEntityData(this.position, 6, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/*  90 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public static int b(ItemStack itemstack) {
/*  94 */     NBTTagCompound nbttagcompound = itemstack.b("BlockEntityTag");
/*     */     
/*  96 */     return (nbttagcompound != null && nbttagcompound.hasKey("Patterns")) ? nbttagcompound.getList("Patterns", 10).size() : 0;
/*     */   }
/*     */   
/*     */   public static void c(ItemStack itemstack) {
/* 100 */     NBTTagCompound nbttagcompound = itemstack.b("BlockEntityTag");
/*     */     
/* 102 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Patterns", 9)) {
/* 103 */       NBTTagList nbttaglist = nbttagcompound.getList("Patterns", 10);
/*     */       
/* 105 */       if (!nbttaglist.isEmpty()) {
/* 106 */         nbttaglist.remove(nbttaglist.size() - 1);
/* 107 */         if (nbttaglist.isEmpty()) {
/* 108 */           itemstack.removeTag("BlockEntityTag");
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumColor a(Supplier<IBlockData> supplier) {
/* 116 */     if (this.color == null) {
/* 117 */       this.color = ((BlockBannerAbstract)((IBlockData)supplier.get()).getBlock()).getColor();
/*     */     }
/*     */     
/* 120 */     return this.color;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */