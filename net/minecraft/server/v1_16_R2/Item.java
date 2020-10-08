/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class Item
/*     */   implements IMaterial {
/*  13 */   public static final Map<Block, Item> e = Maps.newHashMap();
/*  14 */   protected static final UUID f = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*  15 */   protected static final UUID g = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
/*  16 */   protected static final Random RANDOM = new Random();
/*     */   protected final CreativeModeTab i;
/*     */   private final EnumItemRarity a;
/*     */   private final int maxStackSize;
/*     */   private final int durability;
/*     */   private final boolean d;
/*     */   private final Item craftingResult;
/*     */   @Nullable
/*     */   private String name;
/*     */   @Nullable
/*     */   private final FoodInfo foodInfo;
/*     */   
/*     */   public static int getId(Item item) {
/*  29 */     return (item == null) ? 0 : IRegistry.ITEM.a(item);
/*     */   }
/*     */   
/*     */   public static Item getById(int i) {
/*  33 */     return IRegistry.ITEM.fromId(i);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static Item getItemOf(Block block) {
/*  38 */     return e.getOrDefault(block, Items.AIR);
/*     */   }
/*     */   
/*     */   public Item(Info item_info) {
/*  42 */     this.i = item_info.d;
/*  43 */     this.a = item_info.e;
/*  44 */     this.craftingResult = item_info.c;
/*  45 */     this.durability = item_info.b;
/*  46 */     this.maxStackSize = item_info.a;
/*  47 */     this.foodInfo = item_info.f;
/*  48 */     this.d = item_info.g;
/*     */   }
/*     */   
/*     */   public void a(World world, EntityLiving entityliving, ItemStack itemstack, int i) {}
/*     */   
/*     */   public boolean b(NBTTagCompound nbttagcompound) {
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem() {
/*  63 */     return this;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*  67 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
/*  71 */     return 1.0F;
/*     */   }
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  75 */     if (isFood()) {
/*  76 */       ItemStack itemstack = entityhuman.b(enumhand);
/*     */       
/*  78 */       if (entityhuman.q(getFoodInfo().d())) {
/*  79 */         entityhuman.c(enumhand);
/*  80 */         return InteractionResultWrapper.consume(itemstack);
/*     */       } 
/*  82 */       return InteractionResultWrapper.fail(itemstack);
/*     */     } 
/*     */     
/*  85 */     return InteractionResultWrapper.pass(entityhuman.b(enumhand));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
/*  90 */     return isFood() ? entityliving.a(world, itemstack) : itemstack;
/*     */   }
/*     */   
/*     */   public final int getMaxStackSize() {
/*  94 */     return this.maxStackSize;
/*     */   }
/*     */   
/*     */   public final int getMaxDurability() {
/*  98 */     return this.durability;
/*     */   }
/*     */   
/*     */   public boolean usesDurability() {
/* 102 */     return (this.durability > 0);
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
/* 106 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   public boolean canDestroySpecialBlock(IBlockData iblockdata) {
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
/* 118 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 122 */     return IRegistry.ITEM.getKey(this).getKey();
/*     */   }
/*     */   public String getOrCreateDescriptionId() {
/* 125 */     return m();
/*     */   } protected String m() {
/* 127 */     if (this.name == null) {
/* 128 */       this.name = SystemUtils.a("item", IRegistry.ITEM.getKey(this));
/*     */     }
/*     */     
/* 131 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 135 */     return m();
/*     */   }
/*     */   
/*     */   public String f(ItemStack itemstack) {
/* 139 */     return getName();
/*     */   }
/*     */   
/*     */   public boolean n() {
/* 143 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final Item getCraftingRemainingItem() {
/* 148 */     return this.craftingResult;
/*     */   }
/*     */   
/*     */   public boolean p() {
/* 152 */     return (this.craftingResult != null);
/*     */   }
/*     */   
/*     */   public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}
/*     */   
/*     */   public void b(ItemStack itemstack, World world, EntityHuman entityhuman) {}
/*     */   
/*     */   public boolean ac_() {
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public EnumAnimation d_(ItemStack itemstack) {
/* 164 */     return itemstack.getItem().isFood() ? EnumAnimation.EAT : EnumAnimation.NONE;
/*     */   }
/*     */   
/*     */   public int e_(ItemStack itemstack) {
/* 168 */     return itemstack.getItem().isFood() ? (getFoodInfo().e() ? 16 : 32) : 0;
/*     */   }
/*     */   
/*     */   public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {}
/*     */   
/*     */   public IChatBaseComponent h(ItemStack itemstack) {
/* 174 */     return new ChatMessage(f(itemstack));
/*     */   }
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 178 */     return itemstack.hasEnchantments();
/*     */   }
/*     */   
/*     */   public EnumItemRarity i(ItemStack itemstack) {
/* 182 */     if (!itemstack.hasEnchantments()) {
/* 183 */       return this.a;
/*     */     }
/* 185 */     switch (this.a) {
/*     */       case COMMON:
/*     */       case UNCOMMON:
/* 188 */         return EnumItemRarity.RARE;
/*     */       case RARE:
/* 190 */         return EnumItemRarity.EPIC;
/*     */     } 
/*     */     
/* 193 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean f_(ItemStack itemstack) {
/* 199 */     return (getMaxStackSize() == 1 && usesDurability());
/*     */   }
/*     */   
/*     */   protected static MovingObjectPositionBlock a(World world, EntityHuman entityhuman, RayTrace.FluidCollisionOption raytrace_fluidcollisionoption) {
/* 203 */     float f = entityhuman.pitch;
/* 204 */     float f1 = entityhuman.yaw;
/* 205 */     Vec3D vec3d = entityhuman.j(1.0F);
/* 206 */     float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
/* 207 */     float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
/* 208 */     float f4 = -MathHelper.cos(-f * 0.017453292F);
/* 209 */     float f5 = MathHelper.sin(-f * 0.017453292F);
/* 210 */     float f6 = f3 * f4;
/* 211 */     float f7 = f2 * f4;
/* 212 */     double d0 = 5.0D;
/* 213 */     Vec3D vec3d1 = vec3d.add(f6 * 5.0D, f5 * 5.0D, f7 * 5.0D);
/*     */     
/* 215 */     return world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.OUTLINE, raytrace_fluidcollisionoption, entityhuman));
/*     */   }
/*     */   
/*     */   public int c() {
/* 219 */     return 0;
/*     */   }
/*     */   
/*     */   public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
/* 223 */     if (a(creativemodetab)) {
/* 224 */       nonnulllist.add(new ItemStack(this));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(CreativeModeTab creativemodetab) {
/* 230 */     CreativeModeTab creativemodetab1 = q();
/*     */     
/* 232 */     return (creativemodetab1 != null && (creativemodetab == CreativeModeTab.g || creativemodetab == creativemodetab1));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final CreativeModeTab q() {
/* 237 */     return this.i;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot enumitemslot) {
/* 245 */     return (Multimap<AttributeBase, AttributeModifier>)ImmutableMultimap.of();
/*     */   }
/*     */   
/*     */   public boolean j(ItemStack itemstack) {
/* 249 */     return (itemstack.getItem() == Items.CROSSBOW);
/*     */   }
/*     */   
/*     */   public ItemStack createItemStack() {
/* 253 */     return new ItemStack(this);
/*     */   }
/*     */   
/*     */   public boolean a(Tag<Item> tag) {
/* 257 */     return tag.isTagged(this);
/*     */   }
/*     */   
/*     */   public boolean isFood() {
/* 261 */     return (this.foodInfo != null);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public FoodInfo getFoodInfo() {
/* 266 */     return this.foodInfo;
/*     */   }
/*     */   
/*     */   public SoundEffect ae_() {
/* 270 */     return SoundEffects.ENTITY_GENERIC_DRINK;
/*     */   }
/*     */   
/*     */   public SoundEffect ad_() {
/* 274 */     return SoundEffects.ENTITY_GENERIC_EAT;
/*     */   }
/*     */   
/*     */   public boolean u() {
/* 278 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean a(DamageSource damagesource) {
/* 282 */     return (!this.d || !damagesource.isFire());
/*     */   }
/*     */   
/*     */   public static class Info
/*     */   {
/* 287 */     private int a = 64;
/*     */     private int b;
/*     */     private Item c;
/*     */     private CreativeModeTab d;
/*     */     private EnumItemRarity e;
/*     */     private FoodInfo f;
/*     */     private boolean g;
/*     */     
/*     */     public Info() {
/* 296 */       this.e = EnumItemRarity.COMMON;
/*     */     }
/*     */     
/*     */     public Info a(FoodInfo foodinfo) {
/* 300 */       this.f = foodinfo;
/* 301 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(int i) {
/* 305 */       if (this.b > 0) {
/* 306 */         throw new RuntimeException("Unable to have damage AND stack.");
/*     */       }
/* 308 */       this.a = i;
/* 309 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public Info b(int i) {
/* 314 */       return (this.b == 0) ? c(i) : this;
/*     */     }
/*     */     
/*     */     public Info c(int i) {
/* 318 */       this.b = i;
/* 319 */       this.a = 1;
/* 320 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(Item item) {
/* 324 */       this.c = item;
/* 325 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(CreativeModeTab creativemodetab) {
/* 329 */       this.d = creativemodetab;
/* 330 */       return this;
/*     */     }
/*     */     
/*     */     public Info a(EnumItemRarity enumitemrarity) {
/* 334 */       this.e = enumitemrarity;
/* 335 */       return this;
/*     */     }
/*     */     
/*     */     public Info a() {
/* 339 */       this.g = true;
/* 340 */       return this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */