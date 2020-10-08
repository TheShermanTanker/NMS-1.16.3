/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ public class ItemArmorStand extends Item {
/*    */   public ItemArmorStand(Item.Info item_info) {
/*  8 */     super(item_info);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/* 13 */     EnumDirection enumdirection = itemactioncontext.getClickedFace();
/*    */     
/* 15 */     if (enumdirection == EnumDirection.DOWN) {
/* 16 */       return EnumInteractionResult.FAIL;
/*    */     }
/* 18 */     World world = itemactioncontext.getWorld();
/* 19 */     BlockActionContext blockactioncontext = new BlockActionContext(itemactioncontext);
/* 20 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/* 21 */     ItemStack itemstack = itemactioncontext.getItemStack();
/* 22 */     Vec3D vec3d = Vec3D.c(blockposition);
/* 23 */     AxisAlignedBB axisalignedbb = EntityTypes.ARMOR_STAND.l().a(vec3d.getX(), vec3d.getY(), vec3d.getZ());
/*    */     
/* 25 */     if (world.b((Entity)null, axisalignedbb, entity -> true) && world
/*    */       
/* 27 */       .getEntities((Entity)null, axisalignedbb).isEmpty()) {
/* 28 */       if (world instanceof WorldServer) {
/* 29 */         WorldServer worldserver = (WorldServer)world;
/* 30 */         EntityArmorStand entityarmorstand = EntityTypes.ARMOR_STAND.createCreature(worldserver, itemstack.getTag(), (IChatBaseComponent)null, itemactioncontext.getEntity(), blockposition, EnumMobSpawn.SPAWN_EGG, true, true);
/*    */         
/* 32 */         if (entityarmorstand == null) {
/* 33 */           return EnumInteractionResult.FAIL;
/*    */         }
/*    */ 
/*    */         
/* 37 */         float f = MathHelper.d((MathHelper.g(itemactioncontext.h() - 180.0F) + 22.5F) / 45.0F) * 45.0F;
/*    */         
/* 39 */         entityarmorstand.setPositionRotation(entityarmorstand.locX(), entityarmorstand.locY(), entityarmorstand.locZ(), f, 0.0F);
/* 40 */         a(entityarmorstand, world.random);
/*    */         
/* 42 */         if (CraftEventFactory.callEntityPlaceEvent(itemactioncontext, entityarmorstand).isCancelled()) {
/* 43 */           return EnumInteractionResult.FAIL;
/*    */         }
/*    */         
/* 46 */         worldserver.addAllEntities(entityarmorstand);
/* 47 */         world.playSound((EntityHuman)null, entityarmorstand.locX(), entityarmorstand.locY(), entityarmorstand.locZ(), SoundEffects.ENTITY_ARMOR_STAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
/*    */       } 
/*    */       
/* 50 */       itemstack.subtract(1);
/* 51 */       return EnumInteractionResult.a(world.isClientSide);
/*    */     } 
/* 53 */     return EnumInteractionResult.FAIL;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(EntityArmorStand entityarmorstand, Random random) {
/* 59 */     Vector3f vector3f = entityarmorstand.r();
/* 60 */     float f = random.nextFloat() * 5.0F;
/* 61 */     float f1 = random.nextFloat() * 20.0F - 10.0F;
/* 62 */     Vector3f vector3f1 = new Vector3f(vector3f.getX() + f, vector3f.getY() + f1, vector3f.getZ());
/*    */     
/* 64 */     entityarmorstand.setHeadPose(vector3f1);
/* 65 */     vector3f = entityarmorstand.t();
/* 66 */     f = random.nextFloat() * 10.0F - 5.0F;
/* 67 */     vector3f1 = new Vector3f(vector3f.getX(), vector3f.getY() + f, vector3f.getZ());
/* 68 */     entityarmorstand.setBodyPose(vector3f1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */