/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ 
/*     */ public class FoodMetaData {
/*   5 */   public int foodLevel = 20;
/*   6 */   public float saturationLevel = 5.0F;
/*     */   public float exhaustionLevel;
/*     */   private int foodTickTimer;
/*     */   private EntityHuman entityhuman;
/*  10 */   private int e = 20;
/*     */   public FoodMetaData() {
/*  12 */     throw new AssertionError("Whoopsie, we missed the bukkit.");
/*     */   }
/*     */   
/*     */   public FoodMetaData(EntityHuman entityhuman) {
/*  16 */     Validate.notNull(entityhuman);
/*  17 */     this.entityhuman = entityhuman;
/*     */   }
/*     */ 
/*     */   
/*     */   public void eat(int i, float f) {
/*  22 */     this.foodLevel = Math.min(i + this.foodLevel, 20);
/*  23 */     this.saturationLevel = Math.min(this.saturationLevel + i * f * 2.0F, this.foodLevel);
/*     */   }
/*     */   
/*     */   public void a(Item item, ItemStack itemstack) {
/*  27 */     if (item.isFood()) {
/*  28 */       FoodInfo foodinfo = item.getFoodInfo();
/*     */       
/*  30 */       int oldFoodLevel = this.foodLevel;
/*     */       
/*  32 */       FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(this.entityhuman, foodinfo.getNutrition() + oldFoodLevel, itemstack);
/*     */       
/*  34 */       if (!event.isCancelled()) {
/*  35 */         eat(event.getFoodLevel() - oldFoodLevel, foodinfo.getSaturationModifier());
/*     */       }
/*     */       
/*  38 */       ((EntityPlayer)this.entityhuman).getBukkitEntity().sendHealthUpdate();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(EntityHuman entityhuman) {
/*  45 */     EnumDifficulty enumdifficulty = entityhuman.world.getDifficulty();
/*     */     
/*  47 */     this.e = this.foodLevel;
/*  48 */     if (this.exhaustionLevel > 4.0F) {
/*  49 */       this.exhaustionLevel -= 4.0F;
/*  50 */       if (this.saturationLevel > 0.0F) {
/*  51 */         this.saturationLevel = Math.max(this.saturationLevel - 1.0F, 0.0F);
/*  52 */       } else if (enumdifficulty != EnumDifficulty.PEACEFUL) {
/*     */         
/*  54 */         FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, Math.max(this.foodLevel - 1, 0));
/*     */         
/*  56 */         if (!event.isCancelled()) {
/*  57 */           this.foodLevel = event.getFoodLevel();
/*     */         }
/*     */         
/*  60 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutUpdateHealth(((EntityPlayer)entityhuman).getBukkitEntity().getScaledHealth(), this.foodLevel, this.saturationLevel));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  65 */     boolean flag = entityhuman.world.getGameRules().getBoolean(GameRules.NATURAL_REGENERATION);
/*     */     
/*  67 */     if (flag && this.saturationLevel > 0.0F && entityhuman.eI() && this.foodLevel >= 20) {
/*  68 */       this.foodTickTimer++;
/*  69 */       if (this.foodTickTimer >= 10) {
/*  70 */         float f = Math.min(this.saturationLevel, 6.0F);
/*     */         
/*  72 */         entityhuman.heal(f / 6.0F, EntityRegainHealthEvent.RegainReason.SATIATED, true);
/*  73 */         a(f);
/*  74 */         this.foodTickTimer = 0;
/*     */       } 
/*  76 */     } else if (flag && this.foodLevel >= 18 && entityhuman.eI()) {
/*  77 */       this.foodTickTimer++;
/*  78 */       if (this.foodTickTimer >= 80) {
/*  79 */         entityhuman.heal(1.0F, EntityRegainHealthEvent.RegainReason.SATIATED);
/*  80 */         a(entityhuman.world.spigotConfig.regenExhaustion);
/*  81 */         this.foodTickTimer = 0;
/*     */       } 
/*  83 */     } else if (this.foodLevel <= 0) {
/*  84 */       this.foodTickTimer++;
/*  85 */       if (this.foodTickTimer >= 80) {
/*  86 */         if (entityhuman.getHealth() > 10.0F || enumdifficulty == EnumDifficulty.HARD || (entityhuman.getHealth() > 1.0F && enumdifficulty == EnumDifficulty.NORMAL)) {
/*  87 */           entityhuman.damageEntity(DamageSource.STARVE, 1.0F);
/*     */         }
/*     */         
/*  90 */         this.foodTickTimer = 0;
/*     */       } 
/*     */     } else {
/*  93 */       this.foodTickTimer = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  99 */     if (nbttagcompound.hasKeyOfType("foodLevel", 99)) {
/* 100 */       this.foodLevel = nbttagcompound.getInt("foodLevel");
/* 101 */       this.foodTickTimer = nbttagcompound.getInt("foodTickTimer");
/* 102 */       this.saturationLevel = nbttagcompound.getFloat("foodSaturationLevel");
/* 103 */       this.exhaustionLevel = nbttagcompound.getFloat("foodExhaustionLevel");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(NBTTagCompound nbttagcompound) {
/* 109 */     nbttagcompound.setInt("foodLevel", this.foodLevel);
/* 110 */     nbttagcompound.setInt("foodTickTimer", this.foodTickTimer);
/* 111 */     nbttagcompound.setFloat("foodSaturationLevel", this.saturationLevel);
/* 112 */     nbttagcompound.setFloat("foodExhaustionLevel", this.exhaustionLevel);
/*     */   }
/*     */   
/*     */   public int getFoodLevel() {
/* 116 */     return this.foodLevel;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 120 */     return (this.foodLevel < 20);
/*     */   }
/*     */   
/*     */   public void a(float f) {
/* 124 */     this.exhaustionLevel = Math.min(this.exhaustionLevel + f, 40.0F);
/*     */   }
/*     */   
/*     */   public float getSaturationLevel() {
/* 128 */     return this.saturationLevel;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 132 */     this.foodLevel = i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FoodMetaData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */