/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class EntityDamageSourceIndirect
/*    */   extends EntityDamageSource {
/*    */   private final Entity owner;
/*    */   
/*    */   public EntityDamageSourceIndirect(String s, Entity entity, @Nullable Entity entity1) {
/* 10 */     super(s, entity);
/* 11 */     this.owner = entity1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity j() {
/* 17 */     return this.w;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getEntity() {
/* 23 */     return this.owner;
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
/* 28 */     IChatBaseComponent ichatbasecomponent = (this.owner == null) ? this.w.getScoreboardDisplayName() : this.owner.getScoreboardDisplayName();
/* 29 */     ItemStack itemstack = (this.owner instanceof EntityLiving) ? ((EntityLiving)this.owner).getItemInMainHand() : ItemStack.b;
/* 30 */     String s = "death.attack." + this.translationIndex;
/* 31 */     String s1 = s + ".item";
/*    */     
/* 33 */     return (!itemstack.isEmpty() && itemstack.hasName()) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), ichatbasecomponent, itemstack.C() }) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName(), ichatbasecomponent });
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getProximateDamageSource() {
/* 38 */     return super.getEntity();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityDamageSourceIndirect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */