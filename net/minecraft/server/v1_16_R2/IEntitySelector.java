/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public final class IEntitySelector
/*     */ {
/*   9 */   public static final Predicate<Entity> a = Entity::isAlive; public static final Predicate<Entity> c; public static final Predicate<Entity> d; public static final Predicate<Entity> e; public static final Predicate<Entity> f;
/*  10 */   public static final Predicate<EntityLiving> b = EntityLiving::isAlive; public static final Predicate<Entity> g; public static Predicate<EntityHuman> isInsomniac; public static final Predicate<Entity> affectsSpawning; static {
/*  11 */     c = (entity -> 
/*  12 */       (entity.isAlive() && !entity.isVehicle() && !entity.isPassenger()));
/*     */ 
/*     */     
/*  15 */     d = (entity -> 
/*  16 */       (entity instanceof IInventory && entity.isAlive()));
/*     */     
/*  18 */     e = (entity -> 
/*  19 */       (!(entity instanceof EntityHuman) || (!entity.isSpectator() && !((EntityHuman)entity).isCreative())));
/*     */ 
/*     */     
/*  22 */     f = (entity -> 
/*  23 */       (!(entity instanceof EntityHuman) || (!entity.isSpectator() && !((EntityHuman)entity).isCreative() && entity.world.getDifficulty() != EnumDifficulty.PEACEFUL)));
/*     */     
/*  25 */     g = (entity -> !entity.isSpectator());
/*     */ 
/*     */     
/*  28 */     isInsomniac = (player -> (MathHelper.clamp(((EntityPlayer)player).getStatisticManager().getStatisticValue(StatisticList.CUSTOM.get((T)StatisticList.TIME_SINCE_REST)), 1, 2147483647) >= 72000));
/*     */ 
/*     */     
/*  31 */     affectsSpawning = (entity -> 
/*  32 */       (!entity.isSpectator() && entity.isAlive() && entity instanceof EntityPlayer && ((EntityPlayer)entity).affectsSpawning)); } public static final Predicate<Entity> isInventory() {
/*     */     return d;
/*     */   } public static Predicate<Entity> canAITarget() {
/*     */     return f;
/*     */   } public static Predicate<Entity> a(double d0, double d1, double d2, double d3) {
/*  37 */     double d4 = d3 * d3;
/*     */     
/*  39 */     return entity -> 
/*  40 */       (entity != null && entity.h(d0, d1, d2) <= d4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Predicate<Entity> a(Entity entity) {
/*  45 */     ScoreboardTeamBase scoreboardteambase = entity.getScoreboardTeam();
/*  46 */     ScoreboardTeamBase.EnumTeamPush scoreboardteambase_enumteampush = (scoreboardteambase == null) ? ScoreboardTeamBase.EnumTeamPush.ALWAYS : scoreboardteambase.getCollisionRule();
/*     */     
/*  48 */     return (scoreboardteambase_enumteampush == ScoreboardTeamBase.EnumTeamPush.NEVER) ? (Predicate<Entity>)Predicates.alwaysFalse() : g.and(entity1 -> {
/*     */           if (!entity1.canCollideWith(entity) || !entity.canCollideWith(entity1)) {
/*     */             return false;
/*     */           }
/*     */           if (entity.world.isClientSide && (!(entity1 instanceof EntityHuman) || !((EntityHuman)entity1).ey())) {
/*     */             return false;
/*     */           }
/*     */           ScoreboardTeamBase scoreboardteambase1 = entity1.getScoreboardTeam();
/*     */           ScoreboardTeamBase.EnumTeamPush scoreboardteambase_enumteampush1 = (scoreboardteambase1 == null) ? ScoreboardTeamBase.EnumTeamPush.ALWAYS : scoreboardteambase1.getCollisionRule();
/*     */           if (scoreboardteambase_enumteampush1 == ScoreboardTeamBase.EnumTeamPush.NEVER) {
/*     */             return false;
/*     */           }
/*  60 */           boolean flag = (scoreboardteambase != null && scoreboardteambase.isAlly(scoreboardteambase1));
/*     */           
/*  62 */           return ((scoreboardteambase_enumteampush == ScoreboardTeamBase.EnumTeamPush.PUSH_OWN_TEAM || scoreboardteambase_enumteampush1 == ScoreboardTeamBase.EnumTeamPush.PUSH_OWN_TEAM) && flag) ? false : (((scoreboardteambase_enumteampush != ScoreboardTeamBase.EnumTeamPush.PUSH_OTHER_TEAMS && scoreboardteambase_enumteampush1 != ScoreboardTeamBase.EnumTeamPush.PUSH_OTHER_TEAMS) || flag));
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Predicate<Entity> b(Entity entity) {
/*  69 */     return entity1 -> {
/*     */         while (entity1.isPassenger()) {
/*     */           entity1 = entity1.getVehicle();
/*     */           if (entity1 != entity) {
/*     */             continue;
/*     */           }
/*     */           return false;
/*     */         } 
/*     */         return true;
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class EntitySelectorEquipable
/*     */     implements Predicate<Entity>
/*     */   {
/*     */     private final ItemStack a;
/*     */ 
/*     */     
/*     */     public EntitySelectorEquipable(ItemStack itemstack) {
/*  90 */       this.a = itemstack;
/*     */     }
/*     */     
/*     */     public boolean test(@Nullable Entity entity) {
/*  94 */       if (!entity.isAlive())
/*  95 */         return false; 
/*  96 */       if (!(entity instanceof EntityLiving)) {
/*  97 */         return false;
/*     */       }
/*  99 */       EntityLiving entityliving = (EntityLiving)entity;
/*     */       
/* 101 */       return entityliving.e(this.a);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IEntitySelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */