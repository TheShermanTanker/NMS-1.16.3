/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ 
/*    */ public class ItemBoat extends Item {
/*  9 */   private static final Predicate<Entity> a = IEntitySelector.g.and(Entity::isInteractable);
/*    */   private final EntityBoat.EnumBoatType b;
/*    */   
/*    */   public ItemBoat(EntityBoat.EnumBoatType entityboat_enumboattype, Item.Info item_info) {
/* 13 */     super(item_info);
/* 14 */     this.b = entityboat_enumboattype;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 19 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 20 */     MovingObjectPositionBlock movingobjectpositionblock = a(world, entityhuman, RayTrace.FluidCollisionOption.ANY);
/*    */     
/* 22 */     if (movingobjectpositionblock.getType() == MovingObjectPosition.EnumMovingObjectType.MISS) {
/* 23 */       return InteractionResultWrapper.pass(itemstack);
/*    */     }
/* 25 */     Vec3D vec3d = entityhuman.f(1.0F);
/* 26 */     double d0 = 5.0D;
/* 27 */     List<Entity> list = world.getEntities(entityhuman, entityhuman.getBoundingBox().b(vec3d.a(5.0D)).g(1.0D), a);
/*    */     
/* 29 */     if (!list.isEmpty()) {
/* 30 */       Vec3D vec3d1 = entityhuman.j(1.0F);
/* 31 */       Iterator<Entity> iterator = list.iterator();
/*    */       
/* 33 */       while (iterator.hasNext()) {
/* 34 */         Entity entity = iterator.next();
/* 35 */         AxisAlignedBB axisalignedbb = entity.getBoundingBox().g(entity.bf());
/*    */         
/* 37 */         if (axisalignedbb.d(vec3d1)) {
/* 38 */           return InteractionResultWrapper.pass(itemstack);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 43 */     if (movingobjectpositionblock.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/*    */       
/* 45 */       PlayerInteractEvent event = CraftEventFactory.callPlayerInteractEvent(entityhuman, Action.RIGHT_CLICK_BLOCK, movingobjectpositionblock.getBlockPosition(), movingobjectpositionblock.getDirection(), itemstack, enumhand);
/*    */       
/* 47 */       if (event.isCancelled()) {
/* 48 */         return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */       }
/*    */       
/* 51 */       EntityBoat entityboat = new EntityBoat(world, (movingobjectpositionblock.getPos()).x, (movingobjectpositionblock.getPos()).y, (movingobjectpositionblock.getPos()).z);
/*    */       
/* 53 */       entityboat.setType(this.b);
/* 54 */       entityboat.yaw = entityhuman.yaw;
/* 55 */       if (!world.getCubes(entityboat, entityboat.getBoundingBox().g(-0.1D))) {
/* 56 */         return InteractionResultWrapper.fail(itemstack);
/*    */       }
/* 58 */       if (!world.isClientSide) {
/*    */         
/* 60 */         if (CraftEventFactory.callEntityPlaceEvent(world, movingobjectpositionblock.getBlockPosition(), movingobjectpositionblock.getDirection(), entityhuman, entityboat).isCancelled()) {
/* 61 */           return InteractionResultWrapper.fail(itemstack);
/*    */         }
/*    */         
/* 64 */         if (!world.addEntity(entityboat)) {
/* 65 */           return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
/*    */         }
/*    */         
/* 68 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 69 */           itemstack.subtract(1);
/*    */         }
/*    */       } 
/*    */       
/* 73 */       entityhuman.b(StatisticList.ITEM_USED.b(this));
/* 74 */       return InteractionResultWrapper.a(itemstack, world.s_());
/*    */     } 
/*    */     
/* 77 */     return InteractionResultWrapper.pass(itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */