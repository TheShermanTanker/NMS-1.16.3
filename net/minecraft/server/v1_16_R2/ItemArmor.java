/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockDispenseArmorEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ItemArmor extends Item implements ItemWearable {
/*  16 */   private static final UUID[] j = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };
/*  17 */   public static final IDispenseBehavior a = new DispenseBehaviorItem()
/*     */     {
/*     */       protected ItemStack a(ISourceBlock isourceblock, ItemStack itemstack) {
/*  20 */         return ItemArmor.a(isourceblock, itemstack) ? itemstack : super.a(isourceblock, itemstack);
/*     */       }
/*     */     };
/*     */   protected final EnumItemSlot b;
/*     */   private final int k;
/*     */   private final float l;
/*     */   protected final float c;
/*     */   protected final ArmorMaterial d;
/*     */   private final Multimap<AttributeBase, AttributeModifier> m;
/*     */   
/*     */   public static boolean a(ISourceBlock isourceblock, ItemStack itemstack) {
/*  31 */     BlockPosition blockposition = isourceblock.getBlockPosition().shift((EnumDirection)isourceblock.getBlockData().get(BlockDispenser.FACING));
/*  32 */     List<EntityLiving> list = isourceblock.getWorld().a(EntityLiving.class, new AxisAlignedBB(blockposition), (Predicate)IEntitySelector.g.and(new IEntitySelector.EntitySelectorEquipable(itemstack)));
/*     */     
/*  34 */     if (list.isEmpty()) {
/*  35 */       return false;
/*     */     }
/*  37 */     EntityLiving entityliving = list.get(0);
/*  38 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*  39 */     ItemStack itemstack1 = itemstack.cloneAndSubtract(1);
/*     */     
/*  41 */     World world = isourceblock.getWorld();
/*  42 */     Block block = world.getWorld().getBlockAt(isourceblock.getBlockPosition().getX(), isourceblock.getBlockPosition().getY(), isourceblock.getBlockPosition().getZ());
/*  43 */     CraftItemStack craftItem = CraftItemStack.asCraftMirror(itemstack1);
/*     */     
/*  45 */     BlockDispenseArmorEvent event = new BlockDispenseArmorEvent(block, (ItemStack)craftItem.clone(), (LivingEntity)entityliving.getBukkitEntity());
/*  46 */     if (!BlockDispenser.eventFired) {
/*  47 */       world.getServer().getPluginManager().callEvent((Event)event);
/*     */     }
/*     */     
/*  50 */     if (event.isCancelled()) {
/*  51 */       itemstack.add(1);
/*  52 */       return false;
/*     */     } 
/*     */     
/*  55 */     if (!event.getItem().equals(craftItem)) {
/*  56 */       itemstack.add(1);
/*     */       
/*  58 */       ItemStack eventStack = CraftItemStack.asNMSCopy(event.getItem());
/*  59 */       IDispenseBehavior idispensebehavior = BlockDispenser.REGISTRY.get(eventStack.getItem());
/*  60 */       if (idispensebehavior != IDispenseBehavior.NONE && idispensebehavior != a) {
/*  61 */         idispensebehavior.dispense(isourceblock, eventStack);
/*  62 */         return true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  67 */     entityliving.setSlot(enumitemslot, itemstack1);
/*  68 */     if (entityliving instanceof EntityInsentient) {
/*  69 */       ((EntityInsentient)entityliving).a(enumitemslot, 2.0F);
/*  70 */       ((EntityInsentient)entityliving).setPersistent();
/*     */     } 
/*     */     
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemArmor(ArmorMaterial armormaterial, EnumItemSlot enumitemslot, Item.Info item_info) {
/*  78 */     super(item_info.b(armormaterial.a(enumitemslot)));
/*  79 */     this.d = armormaterial;
/*  80 */     this.b = enumitemslot;
/*  81 */     this.k = armormaterial.b(enumitemslot);
/*  82 */     this.l = armormaterial.e();
/*  83 */     this.c = armormaterial.f();
/*  84 */     BlockDispenser.a(this, a);
/*  85 */     ImmutableMultimap.Builder<AttributeBase, AttributeModifier> builder = ImmutableMultimap.builder();
/*  86 */     UUID uuid = j[enumitemslot.b()];
/*     */     
/*  88 */     builder.put(GenericAttributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.k, AttributeModifier.Operation.ADDITION));
/*  89 */     builder.put(GenericAttributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.l, AttributeModifier.Operation.ADDITION));
/*  90 */     if (armormaterial == EnumArmorMaterial.NETHERITE) {
/*  91 */       builder.put(GenericAttributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", this.c, AttributeModifier.Operation.ADDITION));
/*     */     }
/*     */     
/*  94 */     this.m = (Multimap<AttributeBase, AttributeModifier>)builder.build();
/*     */   }
/*     */   
/*     */   public EnumItemSlot b() {
/*  98 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/* 103 */     return this.d.a();
/*     */   }
/*     */   
/*     */   public ArmorMaterial ab_() {
/* 107 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 112 */     return (this.d.c().test(itemstack1) || super.a(itemstack, itemstack1));
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 117 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 118 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/* 119 */     ItemStack itemstack1 = entityhuman.getEquipment(enumitemslot);
/*     */     
/* 121 */     if (itemstack1.isEmpty()) {
/* 122 */       entityhuman.setSlot(enumitemslot, itemstack.cloneItemStack());
/* 123 */       itemstack.setCount(0);
/* 124 */       return InteractionResultWrapper.a(itemstack, world.s_());
/*     */     } 
/* 126 */     return InteractionResultWrapper.fail(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot enumitemslot) {
/* 132 */     return (enumitemslot == this.b) ? this.m : super.a(enumitemslot);
/*     */   }
/*     */   
/*     */   public int e() {
/* 136 */     return this.k;
/*     */   }
/*     */   
/*     */   public float f() {
/* 140 */     return this.l;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */