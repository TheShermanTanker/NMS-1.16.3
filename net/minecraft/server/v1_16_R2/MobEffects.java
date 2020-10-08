/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class MobEffects {
/*  5 */   public static final MobEffectList FASTER_MOVEMENT = a(1, "speed", (new MobEffectList(MobEffectInfo.BENEFICIAL, 8171462)).a(GenericAttributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*  6 */   public static final MobEffectList SLOWER_MOVEMENT = a(2, "slowness", (new MobEffectList(MobEffectInfo.HARMFUL, 5926017)).a(GenericAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*  7 */   public static final MobEffectList FASTER_DIG = a(3, "haste", (new MobEffectList(MobEffectInfo.BENEFICIAL, 14270531)).a(GenericAttributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3", 0.10000000149011612D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*  8 */   public static final MobEffectList SLOWER_DIG = a(4, "mining_fatigue", (new MobEffectList(MobEffectInfo.HARMFUL, 4866583)).a(GenericAttributes.ATTACK_SPEED, "55FCED67-E92A-486E-9800-B47F202C4386", -0.10000000149011612D, AttributeModifier.Operation.MULTIPLY_TOTAL));
/*  9 */   public static final MobEffectList INCREASE_DAMAGE = a(5, "strength", (new MobEffectAttackDamage(MobEffectInfo.BENEFICIAL, 9643043, 3.0D)).a(GenericAttributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.0D, AttributeModifier.Operation.ADDITION));
/* 10 */   public static final MobEffectList HEAL = a(6, "instant_health", new InstantMobEffect(MobEffectInfo.BENEFICIAL, 16262179));
/* 11 */   public static final MobEffectList HARM = a(7, "instant_damage", new InstantMobEffect(MobEffectInfo.HARMFUL, 4393481));
/* 12 */   public static final MobEffectList JUMP = a(8, "jump_boost", new MobEffectList(MobEffectInfo.BENEFICIAL, 2293580));
/* 13 */   public static final MobEffectList CONFUSION = a(9, "nausea", new MobEffectList(MobEffectInfo.HARMFUL, 5578058));
/* 14 */   public static final MobEffectList REGENERATION = a(10, "regeneration", new MobEffectList(MobEffectInfo.BENEFICIAL, 13458603));
/* 15 */   public static final MobEffectList RESISTANCE = a(11, "resistance", new MobEffectList(MobEffectInfo.BENEFICIAL, 10044730));
/* 16 */   public static final MobEffectList FIRE_RESISTANCE = a(12, "fire_resistance", new MobEffectList(MobEffectInfo.BENEFICIAL, 14981690));
/* 17 */   public static final MobEffectList WATER_BREATHING = a(13, "water_breathing", new MobEffectList(MobEffectInfo.BENEFICIAL, 3035801));
/* 18 */   public static final MobEffectList INVISIBILITY = a(14, "invisibility", new MobEffectList(MobEffectInfo.BENEFICIAL, 8356754));
/* 19 */   public static final MobEffectList BLINDNESS = a(15, "blindness", new MobEffectList(MobEffectInfo.HARMFUL, 2039587));
/* 20 */   public static final MobEffectList NIGHT_VISION = a(16, "night_vision", new MobEffectList(MobEffectInfo.BENEFICIAL, 2039713));
/* 21 */   public static final MobEffectList HUNGER = a(17, "hunger", new MobEffectList(MobEffectInfo.HARMFUL, 5797459));
/* 22 */   public static final MobEffectList WEAKNESS = a(18, "weakness", (new MobEffectAttackDamage(MobEffectInfo.HARMFUL, 4738376, -4.0D)).a(GenericAttributes.ATTACK_DAMAGE, "22653B89-116E-49DC-9B6B-9971489B5BE5", 0.0D, AttributeModifier.Operation.ADDITION));
/* 23 */   public static final MobEffectList POISON = a(19, "poison", new MobEffectList(MobEffectInfo.HARMFUL, 5149489));
/* 24 */   public static final MobEffectList WITHER = a(20, "wither", new MobEffectList(MobEffectInfo.HARMFUL, 3484199));
/* 25 */   public static final MobEffectList HEALTH_BOOST = a(21, "health_boost", (new MobEffectHealthBoost(MobEffectInfo.BENEFICIAL, 16284963)).a(GenericAttributes.MAX_HEALTH, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, AttributeModifier.Operation.ADDITION));
/* 26 */   public static final MobEffectList ABSORBTION = a(22, "absorption", new MobEffectAbsorption(MobEffectInfo.BENEFICIAL, 2445989));
/* 27 */   public static final MobEffectList SATURATION = a(23, "saturation", new InstantMobEffect(MobEffectInfo.BENEFICIAL, 16262179));
/* 28 */   public static final MobEffectList GLOWING = a(24, "glowing", new MobEffectList(MobEffectInfo.NEUTRAL, 9740385));
/* 29 */   public static final MobEffectList LEVITATION = a(25, "levitation", new MobEffectList(MobEffectInfo.HARMFUL, 13565951));
/* 30 */   public static final MobEffectList LUCK = a(26, "luck", (new MobEffectList(MobEffectInfo.BENEFICIAL, 3381504)).a(GenericAttributes.LUCK, "03C3C89D-7037-4B42-869F-B146BCB64D2E", 1.0D, AttributeModifier.Operation.ADDITION));
/* 31 */   public static final MobEffectList UNLUCK = a(27, "unluck", (new MobEffectList(MobEffectInfo.HARMFUL, 12624973)).a(GenericAttributes.LUCK, "CC5AF142-2BD2-4215-B636-2605AED11727", -1.0D, AttributeModifier.Operation.ADDITION));
/* 32 */   public static final MobEffectList SLOW_FALLING = a(28, "slow_falling", new MobEffectList(MobEffectInfo.BENEFICIAL, 16773073));
/* 33 */   public static final MobEffectList CONDUIT_POWER = a(29, "conduit_power", new MobEffectList(MobEffectInfo.BENEFICIAL, 1950417));
/* 34 */   public static final MobEffectList DOLPHINS_GRACE = a(30, "dolphins_grace", new MobEffectList(MobEffectInfo.BENEFICIAL, 8954814));
/* 35 */   public static final MobEffectList BAD_OMEN = a(31, "bad_omen", new MobEffectList(MobEffectInfo.NEUTRAL, 745784)
/*    */       {
/*    */         public boolean a(int i, int j) {
/* 38 */           return true;
/*    */         }
/*    */ 
/*    */         
/*    */         public void tick(EntityLiving entityliving, int i) {
/* 43 */           if (entityliving instanceof EntityPlayer && !entityliving.isSpectator()) {
/* 44 */             EntityPlayer entityplayer = (EntityPlayer)entityliving;
/* 45 */             WorldServer worldserver = entityplayer.getWorldServer();
/*    */             
/* 47 */             if (worldserver.getDifficulty() == EnumDifficulty.PEACEFUL) {
/*    */               return;
/*    */             }
/*    */             
/* 51 */             if (worldserver.a_(entityliving.getChunkCoordinates())) {
/* 52 */               worldserver.getPersistentRaid().a(entityplayer);
/*    */             }
/*    */           } 
/*    */         }
/*    */       });
/*    */   
/* 58 */   public static final MobEffectList HERO_OF_THE_VILLAGE = a(32, "hero_of_the_village", new MobEffectList(MobEffectInfo.BENEFICIAL, 4521796));
/*    */ 
/*    */   
/*    */   static {
/* 62 */     for (MobEffectList effect : IRegistry.MOB_EFFECT) {
/* 63 */       PotionEffectType.registerPotionEffectType((PotionEffectType)new CraftPotionEffectType(effect));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static MobEffectList a(int i, String s, MobEffectList mobeffectlist) {
/* 69 */     return IRegistry.<MobEffectList, MobEffectList>a(IRegistry.MOB_EFFECT, i, s, mobeffectlist);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */