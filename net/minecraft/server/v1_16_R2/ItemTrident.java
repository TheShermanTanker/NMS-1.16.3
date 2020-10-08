/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerRiptideEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ItemTrident extends Item implements ItemVanishable {
/*     */   public ItemTrident(Item.Info item_info) {
/*  12 */     super(item_info);
/*  13 */     ImmutableMultimap.Builder<AttributeBase, AttributeModifier> builder = ImmutableMultimap.builder();
/*     */     
/*  15 */     builder.put(GenericAttributes.ATTACK_DAMAGE, new AttributeModifier(f, "Tool modifier", 8.0D, AttributeModifier.Operation.ADDITION));
/*  16 */     builder.put(GenericAttributes.ATTACK_SPEED, new AttributeModifier(g, "Tool modifier", -2.9000000953674316D, AttributeModifier.Operation.ADDITION));
/*  17 */     this.a = (Multimap<AttributeBase, AttributeModifier>)builder.build();
/*     */   }
/*     */   private final Multimap<AttributeBase, AttributeModifier> a;
/*     */   
/*     */   public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
/*  22 */     return !entityhuman.isCreative();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAnimation d_(ItemStack itemstack) {
/*  27 */     return EnumAnimation.SPEAR;
/*     */   }
/*     */ 
/*     */   
/*     */   public int e_(ItemStack itemstack) {
/*  32 */     return 72000;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {
/*  37 */     if (entityliving instanceof EntityHuman) {
/*  38 */       EntityHuman entityhuman = (EntityHuman)entityliving;
/*  39 */       int j = e_(itemstack) - i;
/*     */       
/*  41 */       if (j >= 10) {
/*  42 */         int k = EnchantmentManager.g(itemstack);
/*     */         
/*  44 */         if (k <= 0 || entityhuman.isInWaterOrRain()) {
/*  45 */           if (!world.isClientSide)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  52 */             if (k == 0) {
/*  53 */               EntityThrownTrident entitythrowntrident = new EntityThrownTrident(world, entityhuman, itemstack);
/*     */               
/*  55 */               entitythrowntrident.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 2.5F + k * 0.5F, 1.0F);
/*  56 */               if (entityhuman.abilities.canInstantlyBuild) {
/*  57 */                 entitythrowntrident.fromPlayer = EntityArrow.PickupStatus.CREATIVE_ONLY;
/*     */               }
/*     */ 
/*     */               
/*  61 */               if (!world.addEntity(entitythrowntrident)) {
/*  62 */                 if (entityhuman instanceof EntityPlayer) {
/*  63 */                   ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*     */                 }
/*     */                 
/*     */                 return;
/*     */               } 
/*  68 */               itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(entityliving.getRaisedHand()));
/*     */ 
/*     */               
/*  71 */               entitythrowntrident.trident = itemstack.cloneItemStack();
/*     */ 
/*     */               
/*  74 */               world.playSound((EntityHuman)null, entitythrowntrident, SoundEffects.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*  75 */               if (!entityhuman.abilities.canInstantlyBuild) {
/*  76 */                 entityhuman.inventory.f(itemstack);
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  81 */               itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(entityliving.getRaisedHand()));
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  88 */           entityhuman.b(StatisticList.ITEM_USED.b(this));
/*  89 */           if (k > 0) {
/*     */             SoundEffect soundeffect;
/*  91 */             PlayerRiptideEvent event = new PlayerRiptideEvent((Player)entityhuman.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack));
/*  92 */             event.getPlayer().getServer().getPluginManager().callEvent((Event)event);
/*     */             
/*  94 */             float f = entityhuman.yaw;
/*  95 */             float f1 = entityhuman.pitch;
/*  96 */             float f2 = -MathHelper.sin(f * 0.017453292F) * MathHelper.cos(f1 * 0.017453292F);
/*  97 */             float f3 = -MathHelper.sin(f1 * 0.017453292F);
/*  98 */             float f4 = MathHelper.cos(f * 0.017453292F) * MathHelper.cos(f1 * 0.017453292F);
/*  99 */             float f5 = MathHelper.c(f2 * f2 + f3 * f3 + f4 * f4);
/* 100 */             float f6 = 3.0F * (1.0F + k) / 4.0F;
/*     */             
/* 102 */             f2 *= f6 / f5;
/* 103 */             f3 *= f6 / f5;
/* 104 */             f4 *= f6 / f5;
/* 105 */             entityhuman.i(f2, f3, f4);
/* 106 */             entityhuman.r(20);
/* 107 */             if (entityhuman.isOnGround()) {
/* 108 */               float f7 = 1.1999999F;
/*     */               
/* 110 */               entityhuman.move(EnumMoveType.SELF, new Vec3D(0.0D, 1.1999999284744263D, 0.0D));
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/* 115 */             if (k >= 3) {
/* 116 */               soundeffect = SoundEffects.ITEM_TRIDENT_RIPTIDE_3;
/* 117 */             } else if (k == 2) {
/* 118 */               soundeffect = SoundEffects.ITEM_TRIDENT_RIPTIDE_2;
/*     */             } else {
/* 120 */               soundeffect = SoundEffects.ITEM_TRIDENT_RIPTIDE_1;
/*     */             } 
/*     */             
/* 123 */             world.playSound((EntityHuman)null, entityhuman, soundeffect, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 133 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 135 */     if (itemstack.getDamage() >= itemstack.h() - 1)
/* 136 */       return InteractionResultWrapper.fail(itemstack); 
/* 137 */     if (EnchantmentManager.g(itemstack) > 0 && !entityhuman.isInWaterOrRain()) {
/* 138 */       return InteractionResultWrapper.fail(itemstack);
/*     */     }
/* 140 */     entityhuman.c(enumhand);
/* 141 */     return InteractionResultWrapper.consume(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
/* 147 */     itemstack.damage(1, entityliving1, entityliving2 -> entityliving2.broadcastItemBreak(EnumItemSlot.MAINHAND));
/*     */ 
/*     */     
/* 150 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
/* 155 */     if (iblockdata.h(world, blockposition) != 0.0D) {
/* 156 */       itemstack.damage(2, entityliving, entityliving1 -> entityliving1.broadcastItemBreak(EnumItemSlot.MAINHAND));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot enumitemslot) {
/* 166 */     return (enumitemslot == EnumItemSlot.MAINHAND) ? this.a : super.a(enumitemslot);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 171 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemTrident.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */