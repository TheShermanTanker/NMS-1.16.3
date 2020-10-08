/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDropItemEvent;
/*     */ 
/*     */ public class BehaviorUtil {
/*     */   public static void a(EntityLiving entityliving, EntityLiving entityliving1, float f) {
/*  13 */     d(entityliving, entityliving1);
/*  14 */     b(entityliving, entityliving1, f);
/*     */   }
/*     */   
/*     */   public static boolean a(BehaviorController<?> behaviorcontroller, EntityLiving entityliving) {
/*  18 */     return behaviorcontroller.<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_MOBS).filter(list -> list.contains(entityliving))
/*     */       
/*  20 */       .isPresent();
/*     */   }
/*     */   
/*     */   public static boolean a(BehaviorController<?> behaviorcontroller, MemoryModuleType<? extends EntityLiving> memorymoduletype, EntityTypes<?> entitytypes) {
/*  24 */     return a(behaviorcontroller, memorymoduletype, entityliving -> (entityliving.getEntityType() == entitytypes));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(BehaviorController<?> behaviorcontroller, MemoryModuleType<? extends EntityLiving> memorymoduletype, Predicate<EntityLiving> predicate) {
/*  30 */     return behaviorcontroller.<EntityLiving>getMemory((MemoryModuleType)memorymoduletype).filter(predicate).filter(EntityLiving::isAlive).filter(entityliving -> a(behaviorcontroller, entityliving))
/*     */       
/*  32 */       .isPresent();
/*     */   }
/*     */   
/*     */   private static void d(EntityLiving entityliving, EntityLiving entityliving1) {
/*  36 */     a(entityliving, entityliving1);
/*  37 */     a(entityliving1, entityliving);
/*     */   }
/*     */   
/*     */   public static void a(EntityLiving entityliving, EntityLiving entityliving1) {
/*  41 */     entityliving.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(entityliving1, true));
/*     */   }
/*     */   
/*     */   private static void b(EntityLiving entityliving, EntityLiving entityliving1, float f) {
/*  45 */     boolean flag = true;
/*     */     
/*  47 */     a(entityliving, entityliving1, f, 2);
/*  48 */     a(entityliving1, entityliving, f, 2);
/*     */   }
/*     */   
/*     */   public static void a(EntityLiving entityliving, Entity entity, float f, int i) {
/*  52 */     MemoryTarget memorytarget = new MemoryTarget(new BehaviorPositionEntity(entity, false), f, i);
/*     */     
/*  54 */     entityliving.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(entity, true));
/*  55 */     entityliving.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, memorytarget);
/*     */   }
/*     */   
/*     */   public static void a(EntityLiving entityliving, BlockPosition blockposition, float f, int i) {
/*  59 */     MemoryTarget memorytarget = new MemoryTarget(new BehaviorTarget(blockposition), f, i);
/*     */     
/*  61 */     entityliving.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorTarget(blockposition));
/*  62 */     entityliving.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, memorytarget);
/*     */   }
/*     */   
/*     */   public static void a(EntityLiving entityliving, ItemStack itemstack, Vec3D vec3d) {
/*  66 */     if (itemstack.isEmpty())
/*  67 */       return;  double d0 = entityliving.getHeadY() - 0.30000001192092896D;
/*  68 */     EntityItem entityitem = new EntityItem(entityliving.world, entityliving.locX(), d0, entityliving.locZ(), itemstack);
/*  69 */     float f = 0.3F;
/*  70 */     Vec3D vec3d1 = vec3d.d(entityliving.getPositionVector());
/*     */     
/*  72 */     vec3d1 = vec3d1.d().a(0.30000001192092896D);
/*  73 */     entityitem.setMot(vec3d1);
/*  74 */     entityitem.defaultPickupDelay();
/*     */     
/*  76 */     EntityDropItemEvent event = new EntityDropItemEvent((Entity)entityliving.getBukkitEntity(), (Item)entityitem.getBukkitEntity());
/*  77 */     entityitem.world.getServer().getPluginManager().callEvent((Event)event);
/*  78 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     entityliving.world.addEntity(entityitem);
/*     */   }
/*     */   
/*     */   public static SectionPosition a(WorldServer worldserver, SectionPosition sectionposition, int i) {
/*  86 */     int j = worldserver.b(sectionposition);
/*  87 */     Stream<SectionPosition> stream = SectionPosition.a(sectionposition, i).filter(sectionposition1 -> (worldserver.b(sectionposition1) < j));
/*     */ 
/*     */ 
/*     */     
/*  91 */     worldserver.getClass();
/*  92 */     Objects.requireNonNull(worldserver); return stream.min(Comparator.comparingInt(worldserver::b)).orElse(sectionposition);
/*     */   }
/*     */   
/*     */   public static boolean a(EntityInsentient entityinsentient, EntityLiving entityliving, int i) {
/*  96 */     Item item = entityinsentient.getItemInMainHand().getItem();
/*     */     
/*  98 */     if (item instanceof ItemProjectileWeapon && entityinsentient.a((ItemProjectileWeapon)item)) {
/*  99 */       int j = ((ItemProjectileWeapon)item).d() - i;
/*     */       
/* 101 */       return entityinsentient.a(entityliving, j);
/*     */     } 
/* 103 */     return b(entityinsentient, entityliving);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(EntityLiving entityliving, EntityLiving entityliving1) {
/* 108 */     double d0 = entityliving.h(entityliving1.locX(), entityliving1.locY(), entityliving1.locZ());
/* 109 */     double d1 = (entityliving.getWidth() * 2.0F * entityliving.getWidth() * 2.0F + entityliving1.getWidth());
/*     */     
/* 111 */     return (d0 <= d1);
/*     */   }
/*     */   
/*     */   public static boolean a(EntityLiving entityliving, EntityLiving entityliving1, double d0) {
/* 115 */     Optional<EntityLiving> optional = entityliving.getBehaviorController().getMemory(MemoryModuleType.ATTACK_TARGET);
/*     */     
/* 117 */     if (!optional.isPresent()) {
/* 118 */       return false;
/*     */     }
/* 120 */     double d1 = entityliving.e(((EntityLiving)optional.get()).getPositionVector());
/* 121 */     double d2 = entityliving.e(entityliving1.getPositionVector());
/*     */     
/* 123 */     return (d2 > d1 + d0 * d0);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean c(EntityLiving entityliving, EntityLiving entityliving1) {
/* 128 */     BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
/*     */     
/* 130 */     return !behaviorcontroller.hasMemory(MemoryModuleType.VISIBLE_MOBS) ? false : ((List)behaviorcontroller.<List>getMemory((MemoryModuleType)MemoryModuleType.VISIBLE_MOBS).get()).contains(entityliving1);
/*     */   }
/*     */   
/*     */   public static EntityLiving a(EntityLiving entityliving, Optional<EntityLiving> optional, EntityLiving entityliving1) {
/* 134 */     return !optional.isPresent() ? entityliving1 : a(entityliving, optional.get(), entityliving1);
/*     */   }
/*     */   
/*     */   public static EntityLiving a(EntityLiving entityliving, EntityLiving entityliving1, EntityLiving entityliving2) {
/* 138 */     Vec3D vec3d = entityliving1.getPositionVector();
/* 139 */     Vec3D vec3d1 = entityliving2.getPositionVector();
/*     */     
/* 141 */     return (entityliving.e(vec3d) < entityliving.e(vec3d1)) ? entityliving1 : entityliving2;
/*     */   }
/*     */   
/*     */   public static Optional<EntityLiving> a(EntityLiving entityliving, MemoryModuleType<UUID> memorymoduletype) {
/* 145 */     Optional<UUID> optional = entityliving.getBehaviorController().getMemory(memorymoduletype);
/*     */     
/* 147 */     return optional.map(uuid -> (EntityLiving)((WorldServer)entityliving.world).getEntity(uuid));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Stream<EntityVillager> a(EntityVillager entityvillager, Predicate<EntityVillager> predicate) {
/* 153 */     return entityvillager.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.MOBS).map(list -> list.stream().filter(()).map(()).filter(EntityLiving::isAlive).filter(predicate))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       .orElseGet(Stream::empty);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */