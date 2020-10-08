/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public class ItemBow extends ItemProjectileWeapon implements ItemVanishable {
/*     */   public ItemBow(Item.Info item_info) {
/*   8 */     super(item_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {
/*  13 */     if (entityliving instanceof EntityHuman) {
/*  14 */       EntityHuman entityhuman = (EntityHuman)entityliving;
/*  15 */       boolean flag = (entityhuman.abilities.canInstantlyBuild || EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_INFINITE, itemstack) > 0);
/*  16 */       ItemStack itemstack1 = entityhuman.f(itemstack);
/*     */       
/*  18 */       if (!itemstack1.isEmpty() || flag) {
/*  19 */         if (itemstack1.isEmpty()) {
/*  20 */           itemstack1 = new ItemStack(Items.ARROW);
/*     */         }
/*     */         
/*  23 */         int j = e_(itemstack) - i;
/*  24 */         float f = a(j);
/*     */         
/*  26 */         if (f >= 0.1D) {
/*  27 */           boolean flag1 = (flag && itemstack1.getItem() == Items.ARROW);
/*     */           
/*  29 */           if (!world.isClientSide) {
/*  30 */             ItemArrow itemarrow = (itemstack1.getItem() instanceof ItemArrow) ? (ItemArrow)itemstack1.getItem() : (ItemArrow)Items.ARROW;
/*  31 */             EntityArrow entityarrow = itemarrow.a(world, itemstack1, entityhuman);
/*     */             
/*  33 */             entityarrow.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, f * 3.0F, 1.0F);
/*  34 */             if (f == 1.0F) {
/*  35 */               entityarrow.setCritical(true);
/*     */             }
/*     */             
/*  38 */             int k = EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_DAMAGE, itemstack);
/*     */             
/*  40 */             if (k > 0) {
/*  41 */               entityarrow.setDamage(entityarrow.getDamage() + k * 0.5D + 0.5D);
/*     */             }
/*     */             
/*  44 */             int l = EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_KNOCKBACK, itemstack);
/*     */             
/*  46 */             if (l > 0) {
/*  47 */               entityarrow.setKnockbackStrength(l);
/*     */             }
/*     */             
/*  50 */             if (EnchantmentManager.getEnchantmentLevel(Enchantments.ARROW_FIRE, itemstack) > 0) {
/*  51 */               entityarrow.setOnFire(100);
/*     */             }
/*     */             
/*  54 */             EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(entityhuman, itemstack, itemstack1, entityarrow, entityhuman.getRaisedHand(), f, !flag1);
/*  55 */             if (event.isCancelled()) {
/*  56 */               event.getProjectile().remove();
/*     */               return;
/*     */             } 
/*  59 */             flag1 = !event.shouldConsumeItem();
/*     */ 
/*     */             
/*  62 */             itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(entityhuman.getRaisedHand()));
/*     */ 
/*     */             
/*  65 */             if (flag1 || (entityhuman.abilities.canInstantlyBuild && (itemstack1.getItem() == Items.SPECTRAL_ARROW || itemstack1.getItem() == Items.TIPPED_ARROW))) {
/*  66 */               entityarrow.fromPlayer = EntityArrow.PickupStatus.CREATIVE_ONLY;
/*     */             }
/*     */ 
/*     */             
/*  70 */             if (event.getProjectile() == entityarrow.getBukkitEntity() && 
/*  71 */               !world.addEntity(entityarrow)) {
/*  72 */               if (entityhuman instanceof EntityPlayer) {
/*  73 */                 ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*     */               }
/*     */ 
/*     */               
/*     */               return;
/*     */             } 
/*     */           } 
/*     */           
/*  81 */           world.playSound((EntityHuman)null, entityhuman.locX(), entityhuman.locY(), entityhuman.locZ(), SoundEffects.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
/*  82 */           if (!flag1 && !entityhuman.abilities.canInstantlyBuild) {
/*  83 */             itemstack1.subtract(1);
/*  84 */             if (itemstack1.isEmpty()) {
/*  85 */               entityhuman.inventory.f(itemstack1);
/*     */             }
/*     */           } 
/*     */           
/*  89 */           entityhuman.b(StatisticList.ITEM_USED.b(this));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float a(int i) {
/*  96 */     float f = i / 20.0F;
/*     */     
/*  98 */     f = (f * f + f * 2.0F) / 3.0F;
/*  99 */     if (f > 1.0F) {
/* 100 */       f = 1.0F;
/*     */     }
/*     */     
/* 103 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int e_(ItemStack itemstack) {
/* 108 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAnimation d_(ItemStack itemstack) {
/* 113 */     return EnumAnimation.BOW;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 118 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 119 */     boolean flag = !entityhuman.f(itemstack).isEmpty();
/*     */     
/* 121 */     if (!entityhuman.abilities.canInstantlyBuild && !flag) {
/* 122 */       return InteractionResultWrapper.fail(itemstack);
/*     */     }
/* 124 */     entityhuman.c(enumhand);
/* 125 */     return InteractionResultWrapper.consume(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate<ItemStack> b() {
/* 131 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int d() {
/* 136 */     return 15;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */