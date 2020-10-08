/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.Sheep;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.SheepRegrowWoolEvent;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class EntitySheep extends EntityAnimal implements IShearable {
/*  19 */   private static final DataWatcherObject<Byte> bo = DataWatcher.a((Class)EntitySheep.class, DataWatcherRegistry.a); private static final Map<EnumColor, IMaterial> bp; static {
/*  20 */     bp = SystemUtils.<Map<EnumColor, IMaterial>>a(Maps.newEnumMap(EnumColor.class), enummap -> {
/*     */           enummap.put(EnumColor.WHITE, Blocks.WHITE_WOOL);
/*     */           enummap.put(EnumColor.ORANGE, Blocks.ORANGE_WOOL);
/*     */           enummap.put(EnumColor.MAGENTA, Blocks.MAGENTA_WOOL);
/*     */           enummap.put(EnumColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
/*     */           enummap.put(EnumColor.YELLOW, Blocks.YELLOW_WOOL);
/*     */           enummap.put(EnumColor.LIME, Blocks.LIME_WOOL);
/*     */           enummap.put(EnumColor.PINK, Blocks.PINK_WOOL);
/*     */           enummap.put(EnumColor.GRAY, Blocks.GRAY_WOOL);
/*     */           enummap.put(EnumColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
/*     */           enummap.put(EnumColor.CYAN, Blocks.CYAN_WOOL);
/*     */           enummap.put(EnumColor.PURPLE, Blocks.PURPLE_WOOL);
/*     */           enummap.put(EnumColor.BLUE, Blocks.BLUE_WOOL);
/*     */           enummap.put(EnumColor.BROWN, Blocks.BROWN_WOOL);
/*     */           enummap.put(EnumColor.GREEN, Blocks.GREEN_WOOL);
/*     */           enummap.put(EnumColor.RED, Blocks.RED_WOOL);
/*     */           enummap.put(EnumColor.BLACK, Blocks.BLACK_WOOL);
/*     */         });
/*  38 */     bq = Maps.newEnumMap((Map)Arrays.<EnumColor>stream(EnumColor.values()).collect(Collectors.toMap(enumcolor -> enumcolor, EntitySheep::c)));
/*     */   }
/*     */   private static final Map<EnumColor, float[]> bq;
/*     */   private int br;
/*     */   private PathfinderGoalEatTile bs;
/*     */   
/*     */   private static float[] c(EnumColor enumcolor) {
/*  45 */     if (enumcolor == EnumColor.WHITE) {
/*  46 */       return new float[] { 0.9019608F, 0.9019608F, 0.9019608F };
/*     */     }
/*  48 */     float[] afloat = enumcolor.getColor();
/*  49 */     float f = 0.75F;
/*     */     
/*  51 */     return new float[] { afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F };
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySheep(EntityTypes<? extends EntitySheep> entitytypes, World world) {
/*  56 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  61 */     this.bs = new PathfinderGoalEatTile(this);
/*  62 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  63 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 1.25D));
/*  64 */     this.goalSelector.a(2, new PathfinderGoalBreed(this, 1.0D));
/*  65 */     this.goalSelector.a(3, new PathfinderGoalTempt(this, 1.1D, RecipeItemStack.a(new IMaterial[] { Items.WHEAT }, ), false));
/*  66 */     this.goalSelector.a(4, new PathfinderGoalFollowParent(this, 1.1D));
/*  67 */     this.goalSelector.a(5, this.bs);
/*  68 */     this.goalSelector.a(6, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  69 */     this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 6.0F));
/*  70 */     this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  75 */     this.br = this.bs.g();
/*  76 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  81 */     if (this.world.isClientSide) {
/*  82 */       this.br = Math.max(0, this.br - 1);
/*     */     }
/*     */     
/*  85 */     super.movementTick();
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  89 */     return EntityInsentient.p().a(GenericAttributes.MAX_HEALTH, 8.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.23000000417232513D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  94 */     super.initDatawatcher();
/*  95 */     this.datawatcher.register(bo, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */   
/*     */   public MinecraftKey getDefaultLootTable() {
/* 100 */     if (isSheared()) {
/* 101 */       return getEntityType().i();
/*     */     }
/* 103 */     switch (getColor())
/*     */     
/*     */     { default:
/* 106 */         return LootTables.Q;
/*     */       case ORANGE:
/* 108 */         return LootTables.R;
/*     */       case MAGENTA:
/* 110 */         return LootTables.S;
/*     */       case LIGHT_BLUE:
/* 112 */         return LootTables.T;
/*     */       case YELLOW:
/* 114 */         return LootTables.U;
/*     */       case LIME:
/* 116 */         return LootTables.V;
/*     */       case PINK:
/* 118 */         return LootTables.W;
/*     */       case GRAY:
/* 120 */         return LootTables.X;
/*     */       case LIGHT_GRAY:
/* 122 */         return LootTables.Y;
/*     */       case CYAN:
/* 124 */         return LootTables.Z;
/*     */       case PURPLE:
/* 126 */         return LootTables.aa;
/*     */       case BLUE:
/* 128 */         return LootTables.ab;
/*     */       case BROWN:
/* 130 */         return LootTables.ac;
/*     */       case GREEN:
/* 132 */         return LootTables.ad;
/*     */       case RED:
/* 134 */         return LootTables.ae;
/*     */       case BLACK:
/* 136 */         break; }  return LootTables.af;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 143 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 145 */     if (itemstack.getItem() == Items.SHEARS) {
/* 146 */       if (!this.world.isClientSide && canShear()) {
/*     */         
/* 148 */         if (!CraftEventFactory.handlePlayerShearEntityEvent(entityhuman, this, itemstack, enumhand)) {
/* 149 */           return EnumInteractionResult.PASS;
/*     */         }
/*     */         
/* 152 */         shear(SoundCategory.PLAYERS);
/* 153 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */ 
/*     */         
/* 156 */         return EnumInteractionResult.SUCCESS;
/*     */       } 
/* 158 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/*     */     
/* 161 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(SoundCategory soundcategory) {
/* 167 */     this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_SHEEP_SHEAR, soundcategory, 1.0F, 1.0F);
/* 168 */     setSheared(true);
/* 169 */     int i = 1 + this.random.nextInt(3);
/*     */     
/* 171 */     for (int j = 0; j < i; j++) {
/* 172 */       this.forceDrops = true;
/* 173 */       EntityItem entityitem = a(bp.get(getColor()), 1);
/* 174 */       this.forceDrops = false;
/*     */       
/* 176 */       if (entityitem != null) {
/* 177 */         entityitem.setMot(entityitem.getMot().add(((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (this.random.nextFloat() * 0.05F), ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canShear() {
/* 185 */     return (isAlive() && !isSheared() && !isBaby());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 190 */     super.saveData(nbttagcompound);
/* 191 */     nbttagcompound.setBoolean("Sheared", isSheared());
/* 192 */     nbttagcompound.setByte("Color", (byte)getColor().getColorIndex());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 197 */     super.loadData(nbttagcompound);
/* 198 */     setSheared(nbttagcompound.getBoolean("Sheared"));
/* 199 */     setColor(EnumColor.fromColorIndex(nbttagcompound.getByte("Color")));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 204 */     return SoundEffects.ENTITY_SHEEP_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 209 */     return SoundEffects.ENTITY_SHEEP_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 214 */     return SoundEffects.ENTITY_SHEEP_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/* 219 */     playSound(SoundEffects.ENTITY_SHEEP_STEP, 0.15F, 1.0F);
/*     */   }
/*     */   
/*     */   public EnumColor getColor() {
/* 223 */     return EnumColor.fromColorIndex(((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & 0xF);
/*     */   }
/*     */   
/*     */   public void setColor(EnumColor enumcolor) {
/* 227 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bo)).byteValue();
/*     */     
/* 229 */     this.datawatcher.set(bo, Byte.valueOf((byte)(b0 & 0xF0 | enumcolor.getColorIndex() & 0xF)));
/*     */   }
/*     */   
/*     */   public boolean isSheared() {
/* 233 */     return ((((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   public void setSheared(boolean flag) {
/* 237 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bo)).byteValue();
/*     */     
/* 239 */     if (flag) {
/* 240 */       this.datawatcher.set(bo, Byte.valueOf((byte)(b0 | 0x10)));
/*     */     } else {
/* 242 */       this.datawatcher.set(bo, Byte.valueOf((byte)(b0 & 0xFFFFFFEF)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumColor a(Random random) {
/* 248 */     int i = random.nextInt(100);
/*     */     
/* 250 */     return (i < 5) ? EnumColor.BLACK : ((i < 10) ? EnumColor.GRAY : ((i < 15) ? EnumColor.LIGHT_GRAY : ((i < 18) ? EnumColor.BROWN : ((random.nextInt(500) == 0) ? EnumColor.PINK : EnumColor.WHITE))));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySheep createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 255 */     EntitySheep entitysheep = (EntitySheep)entityageable;
/* 256 */     EntitySheep entitysheep1 = EntityTypes.SHEEP.a(worldserver);
/*     */     
/* 258 */     entitysheep1.setColor(a(this, entitysheep));
/* 259 */     return entitysheep1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void blockEaten() {
/* 265 */     SheepRegrowWoolEvent event = new SheepRegrowWoolEvent((Sheep)getBukkitEntity());
/* 266 */     this.world.getServer().getPluginManager().callEvent((Event)event);
/*     */     
/* 268 */     if (event.isCancelled())
/*     */       return; 
/* 270 */     setSheared(false);
/* 271 */     if (isBaby()) {
/* 272 */       setAge(60);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 280 */     setColor(a(worldaccess.getRandom()));
/* 281 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   private EnumColor a(EntityAnimal entityanimal, EntityAnimal entityanimal1) {
/* 285 */     EnumColor enumcolor = ((EntitySheep)entityanimal).getColor();
/* 286 */     EnumColor enumcolor1 = ((EntitySheep)entityanimal1).getColor();
/* 287 */     InventoryCrafting inventorycrafting = a(enumcolor, enumcolor1);
/*     */ 
/*     */     
/* 290 */     Optional<Item> optional = this.world.getCraftingManager().<InventoryCrafting, RecipeCrafting>craft(Recipes.CRAFTING, inventorycrafting, this.world).map(recipecrafting -> recipecrafting.a(inventorycrafting)).map(ItemStack::getItem);
/*     */     
/* 292 */     ItemDye.class.getClass();
/* 293 */     Objects.requireNonNull(ItemDye.class); optional = optional.filter(ItemDye.class::isInstance);
/* 294 */     ItemDye.class.getClass();
/* 295 */     Objects.requireNonNull(ItemDye.class); return optional.map(ItemDye.class::cast).map(ItemDye::d).orElseGet(() -> this.world.random.nextBoolean() ? enumcolor : enumcolor1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static InventoryCrafting a(EnumColor enumcolor, EnumColor enumcolor1) {
/* 301 */     InventoryCrafting inventorycrafting = new InventoryCrafting(new Container((Containers)null, -1)
/*     */         {
/*     */           public boolean canUse(EntityHuman entityhuman) {
/* 304 */             return false;
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public InventoryView getBukkitView() {
/* 310 */             return null;
/*     */           }
/*     */         }2, 1);
/*     */ 
/*     */     
/* 315 */     inventorycrafting.setItem(0, new ItemStack(ItemDye.a(enumcolor)));
/* 316 */     inventorycrafting.setItem(1, new ItemStack(ItemDye.a(enumcolor1)));
/* 317 */     inventorycrafting.resultInventory = new InventoryCraftResult();
/* 318 */     return inventorycrafting;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 323 */     return 0.95F * entitysize.height;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySheep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */