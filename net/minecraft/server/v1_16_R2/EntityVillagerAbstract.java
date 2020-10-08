/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchant;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftMerchantRecipe;
/*     */ import org.bukkit.entity.AbstractVillager;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.VillagerAcquireTradeEvent;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.MerchantRecipe;
/*     */ 
/*     */ public abstract class EntityVillagerAbstract
/*     */   extends EntityAgeable
/*     */   implements NPC, IMerchant {
/*     */   private CraftMerchant craftMerchant;
/*     */   
/*     */   public CraftMerchant getCraftMerchant() {
/*  22 */     return (this.craftMerchant == null) ? (this.craftMerchant = new CraftMerchant(this)) : this.craftMerchant;
/*     */   }
/*     */   
/*  25 */   private static final DataWatcherObject<Integer> bp = DataWatcher.a((Class)EntityVillagerAbstract.class, DataWatcherRegistry.b);
/*     */   @Nullable
/*     */   private EntityHuman tradingPlayer;
/*     */   @Nullable
/*     */   protected MerchantRecipeList trades;
/*  30 */   private final InventorySubcontainer inventory = new InventorySubcontainer(8, (InventoryHolder)getBukkitEntity());
/*     */   
/*     */   public EntityVillagerAbstract(EntityTypes<? extends EntityVillagerAbstract> entitytypes, World world) {
/*  33 */     super((EntityTypes)entitytypes, world);
/*  34 */     a(PathType.DANGER_FIRE, 16.0F);
/*  35 */     a(PathType.DAMAGE_FIRE, -1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  40 */     if (groupdataentity == null) {
/*  41 */       groupdataentity = new EntityAgeable.a(false);
/*     */     }
/*     */     
/*  44 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   public final int getUnhappy() {
/*  47 */     return eK();
/*     */   } public int eK() {
/*  49 */     return ((Integer)this.datawatcher.<Integer>get(bp)).intValue();
/*     */   }
/*     */   public final void setUnhappy(int i) {
/*  52 */     s(i);
/*     */   } public void s(int i) {
/*  54 */     this.datawatcher.set(bp, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperience() {
/*  59 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  64 */     return isBaby() ? 0.81F : 1.62F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  69 */     super.initDatawatcher();
/*  70 */     this.datawatcher.register(bp, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTradingPlayer(@Nullable EntityHuman entityhuman) {
/*  75 */     this.tradingPlayer = entityhuman;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityHuman getTrader() {
/*  81 */     return this.tradingPlayer;
/*     */   }
/*     */   
/*     */   public boolean eN() {
/*  85 */     return (this.tradingPlayer != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public MerchantRecipeList getOffers() {
/*  90 */     if (this.trades == null) {
/*  91 */       this.trades = new MerchantRecipeList();
/*  92 */       eW();
/*     */     } 
/*     */     
/*  95 */     return this.trades;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setForcedExperience(int i) {}
/*     */ 
/*     */   
/*     */   public void a(MerchantRecipe merchantrecipe) {
/* 103 */     merchantrecipe.increaseUses();
/* 104 */     this.e = -D();
/* 105 */     b(merchantrecipe);
/* 106 */     if (this.tradingPlayer instanceof EntityPlayer) {
/* 107 */       CriterionTriggers.s.a((EntityPlayer)this.tradingPlayer, this, merchantrecipe.getSellingItem());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected abstract void b(MerchantRecipe paramMerchantRecipe);
/*     */ 
/*     */   
/*     */   public boolean isRegularVillager() {
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void k(ItemStack itemstack) {
/* 121 */     if (!this.world.isClientSide && this.e > -D() + 20) {
/* 122 */       this.e = -D();
/* 123 */       playSound(t(!itemstack.isEmpty()), getSoundVolume(), dG());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEffect getTradeSound() {
/* 130 */     return SoundEffects.ENTITY_VILLAGER_YES;
/*     */   }
/*     */   
/*     */   protected SoundEffect t(boolean flag) {
/* 134 */     return flag ? SoundEffects.ENTITY_VILLAGER_YES : SoundEffects.ENTITY_VILLAGER_NO;
/*     */   }
/*     */   
/*     */   public void eR() {
/* 138 */     playSound(SoundEffects.ENTITY_VILLAGER_CELEBRATE, getSoundVolume(), dG());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 143 */     super.saveData(nbttagcompound);
/* 144 */     MerchantRecipeList merchantrecipelist = getOffers();
/*     */     
/* 146 */     if (!merchantrecipelist.isEmpty()) {
/* 147 */       nbttagcompound.set("Offers", merchantrecipelist.a());
/*     */     }
/*     */     
/* 150 */     nbttagcompound.set("Inventory", this.inventory.g());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 155 */     super.loadData(nbttagcompound);
/* 156 */     if (nbttagcompound.hasKeyOfType("Offers", 10)) {
/* 157 */       this.trades = new MerchantRecipeList(nbttagcompound.getCompound("Offers"));
/*     */     }
/*     */     
/* 160 */     this.inventory.a(nbttagcompound.getList("Inventory", 10));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity b(WorldServer worldserver) {
/* 166 */     eT();
/* 167 */     return super.b(worldserver);
/*     */   }
/*     */   
/*     */   protected void eT() {
/* 171 */     setTradingPlayer((EntityHuman)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource damagesource) {
/* 176 */     super.die(damagesource);
/* 177 */     eT();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 182 */     return false;
/*     */   }
/*     */   
/*     */   public InventorySubcontainer getInventory() {
/* 186 */     return this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 191 */     if (super.a_(i, itemstack)) {
/* 192 */       return true;
/*     */     }
/* 194 */     int j = i - 300;
/*     */     
/* 196 */     if (j >= 0 && j < this.inventory.getSize()) {
/* 197 */       this.inventory.setItem(j, itemstack);
/* 198 */       return true;
/*     */     } 
/* 200 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public World getWorld() {
/* 207 */     return this.world;
/*     */   }
/*     */   
/*     */   protected abstract void eW();
/*     */   
/*     */   protected void a(MerchantRecipeList merchantrecipelist, VillagerTrades.IMerchantRecipeOption[] avillagertrades_imerchantrecipeoption, int i) {
/* 213 */     Set<Integer> set = Sets.newHashSet();
/*     */     
/* 215 */     if (avillagertrades_imerchantrecipeoption.length > i) {
/* 216 */       while (set.size() < i) {
/* 217 */         set.add(Integer.valueOf(this.random.nextInt(avillagertrades_imerchantrecipeoption.length)));
/*     */       }
/*     */     } else {
/* 220 */       for (int j = 0; j < avillagertrades_imerchantrecipeoption.length; j++) {
/* 221 */         set.add(Integer.valueOf(j));
/*     */       }
/*     */     } 
/*     */     
/* 225 */     Iterator<Integer> iterator = set.iterator();
/*     */     
/* 227 */     while (iterator.hasNext()) {
/* 228 */       Integer integer = iterator.next();
/* 229 */       VillagerTrades.IMerchantRecipeOption villagertrades_imerchantrecipeoption = avillagertrades_imerchantrecipeoption[integer.intValue()];
/* 230 */       MerchantRecipe merchantrecipe = villagertrades_imerchantrecipeoption.a(this, this.random);
/*     */       
/* 232 */       if (merchantrecipe != null) {
/*     */         
/* 234 */         VillagerAcquireTradeEvent event = new VillagerAcquireTradeEvent((AbstractVillager)getBukkitEntity(), (MerchantRecipe)merchantrecipe.asBukkit());
/*     */         
/* 236 */         if (this.valid) {
/* 237 */           Bukkit.getPluginManager().callEvent((Event)event);
/*     */         }
/* 239 */         if (!event.isCancelled())
/* 240 */           merchantrecipelist.add(CraftMerchantRecipe.fromBukkit(event.getRecipe()).toMinecraft()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityVillagerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */