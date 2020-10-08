/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ public class ItemMonsterEgg extends Item {
/*  12 */   private static final Map<EntityTypes<?>, ItemMonsterEgg> a = Maps.newIdentityHashMap();
/*     */   private final int b;
/*     */   private final int c;
/*     */   private final EntityTypes<?> d;
/*     */   
/*     */   public ItemMonsterEgg(EntityTypes<?> entitytypes, int i, int j, Item.Info item_info) {
/*  18 */     super(item_info);
/*  19 */     this.d = entitytypes;
/*  20 */     this.b = i;
/*  21 */     this.c = j;
/*  22 */     a.put(entitytypes, this);
/*     */   }
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*     */     BlockPosition blockposition1;
/*  27 */     World world = itemactioncontext.getWorld();
/*     */     
/*  29 */     if (!(world instanceof WorldServer)) {
/*  30 */       return EnumInteractionResult.SUCCESS;
/*     */     }
/*  32 */     ItemStack itemstack = itemactioncontext.getItemStack();
/*  33 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/*  34 */     EnumDirection enumdirection = itemactioncontext.getClickedFace();
/*  35 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  37 */     if (iblockdata.a(Blocks.SPAWNER)) {
/*  38 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  40 */       if (tileentity instanceof TileEntityMobSpawner) {
/*  41 */         MobSpawnerAbstract mobspawnerabstract = ((TileEntityMobSpawner)tileentity).getSpawner();
/*  42 */         EntityTypes<?> entitytypes = a(itemstack.getTag());
/*     */         
/*  44 */         mobspawnerabstract.setMobName(entitytypes);
/*  45 */         tileentity.update();
/*  46 */         world.notify(blockposition, iblockdata, iblockdata, 3);
/*  47 */         itemstack.subtract(1);
/*  48 */         return EnumInteractionResult.CONSUME;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  54 */     if (iblockdata.getCollisionShape(world, blockposition).isEmpty()) {
/*  55 */       blockposition1 = blockposition;
/*     */     } else {
/*  57 */       blockposition1 = blockposition.shift(enumdirection);
/*     */     } 
/*     */     
/*  60 */     EntityTypes<?> entitytypes1 = a(itemstack.getTag());
/*     */     
/*  62 */     if (entitytypes1.spawnCreature((WorldServer)world, itemstack, itemactioncontext.getEntity(), blockposition1, EnumMobSpawn.SPAWN_EGG, true, (!Objects.equals(blockposition, blockposition1) && enumdirection == EnumDirection.UP)) != null) {
/*  63 */       itemstack.subtract(1);
/*     */     }
/*     */     
/*  66 */     return EnumInteractionResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  72 */     ItemStack itemstack = entityhuman.b(enumhand);
/*  73 */     MovingObjectPositionBlock movingobjectpositionblock = a(world, entityhuman, RayTrace.FluidCollisionOption.SOURCE_ONLY);
/*     */     
/*  75 */     if (movingobjectpositionblock.getType() != MovingObjectPosition.EnumMovingObjectType.BLOCK)
/*  76 */       return InteractionResultWrapper.pass(itemstack); 
/*  77 */     if (!(world instanceof WorldServer)) {
/*  78 */       return InteractionResultWrapper.success(itemstack);
/*     */     }
/*  80 */     MovingObjectPositionBlock movingobjectpositionblock1 = movingobjectpositionblock;
/*  81 */     BlockPosition blockposition = movingobjectpositionblock1.getBlockPosition();
/*     */     
/*  83 */     if (!(world.getType(blockposition).getBlock() instanceof BlockFluids))
/*  84 */       return InteractionResultWrapper.pass(itemstack); 
/*  85 */     if (world.a(entityhuman, blockposition) && entityhuman.a(blockposition, movingobjectpositionblock1.getDirection(), itemstack)) {
/*  86 */       EntityTypes<?> entitytypes = a(itemstack.getTag());
/*     */       
/*  88 */       if (entitytypes.spawnCreature((WorldServer)world, itemstack, entityhuman, blockposition, EnumMobSpawn.SPAWN_EGG, false, false) == null) {
/*  89 */         return InteractionResultWrapper.pass(itemstack);
/*     */       }
/*  91 */       if (!entityhuman.abilities.canInstantlyBuild) {
/*  92 */         itemstack.subtract(1);
/*     */       }
/*     */       
/*  95 */       entityhuman.b(StatisticList.ITEM_USED.b(this));
/*  96 */       return InteractionResultWrapper.consume(itemstack);
/*     */     } 
/*     */     
/*  99 */     return InteractionResultWrapper.fail(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(@Nullable NBTTagCompound nbttagcompound, EntityTypes<?> entitytypes) {
/* 105 */     return Objects.equals(a(nbttagcompound), entitytypes);
/*     */   }
/*     */   
/*     */   public static Iterable<ItemMonsterEgg> f() {
/* 109 */     return Iterables.unmodifiableIterable(a.values());
/*     */   }
/*     */   
/*     */   public EntityTypes<?> a(@Nullable NBTTagCompound nbttagcompound) {
/* 113 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("EntityTag", 10)) {
/* 114 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("EntityTag");
/*     */       
/* 116 */       if (nbttagcompound1.hasKeyOfType("id", 8)) {
/* 117 */         return EntityTypes.a(nbttagcompound1.getString("id")).orElse(this.d);
/*     */       }
/*     */     } 
/*     */     
/* 121 */     return this.d;
/*     */   }
/*     */   public Optional<EntityInsentient> a(EntityHuman entityhuman, EntityInsentient entityinsentient, EntityTypes<? extends EntityInsentient> entitytypes, WorldServer worldserver, Vec3D vec3d, ItemStack itemstack) {
/*     */     Object object;
/* 125 */     if (!a(itemstack.getTag(), entitytypes)) {
/* 126 */       return Optional.empty();
/*     */     }
/*     */ 
/*     */     
/* 130 */     if (entityinsentient instanceof EntityAgeable) {
/* 131 */       object = ((EntityAgeable)entityinsentient).createChild(worldserver, (EntityAgeable)entityinsentient);
/*     */     } else {
/* 133 */       object = entitytypes.a(worldserver);
/*     */     } 
/*     */     
/* 136 */     if (object == null) {
/* 137 */       return Optional.empty();
/*     */     }
/* 139 */     ((EntityInsentient)object).setBaby(true);
/* 140 */     if (!((EntityInsentient)object).isBaby()) {
/* 141 */       return Optional.empty();
/*     */     }
/* 143 */     ((EntityInsentient)object).setPositionRotation(vec3d.getX(), vec3d.getY(), vec3d.getZ(), 0.0F, 0.0F);
/* 144 */     worldserver.addAllEntities((Entity)object, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
/* 145 */     if (itemstack.hasName()) {
/* 146 */       ((EntityInsentient)object).setCustomName(itemstack.getName());
/*     */     }
/*     */     
/* 149 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 150 */       itemstack.subtract(1);
/*     */     }
/*     */     
/* 153 */     return Optional.of((EntityInsentient)object);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemMonsterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */