/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class BlockWitherRose extends BlockFlowers {
/*    */   public BlockWitherRose(MobEffectList mobeffectlist, BlockBase.Info blockbase_info) {
/*  6 */     super(mobeffectlist, 8, blockbase_info);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 11 */     return (super.c(iblockdata, iblockaccess, blockposition) || iblockdata.a(Blocks.NETHERRACK) || iblockdata.a(Blocks.SOUL_SAND) || iblockdata.a(Blocks.SOUL_SOIL));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
/* 16 */     if (!world.isClientSide && world.getDifficulty() != EnumDifficulty.PEACEFUL && 
/* 17 */       entity instanceof EntityLiving) {
/* 18 */       EntityLiving entityliving = (EntityLiving)entity;
/*    */       
/* 20 */       if (!entityliving.isInvulnerable(DamageSource.WITHER))
/* 21 */         entityliving.addEffect(new MobEffect(MobEffects.WITHER, 40), EntityPotionEffectEvent.Cause.WITHER_ROSE); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWitherRose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */