/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*    */ 
/*    */ public class EnchantmentWeaponDamage extends Enchantment {
/*  5 */   private static final String[] d = new String[] { "all", "undead", "arthropods" };
/*  6 */   private static final int[] e = new int[] { 1, 5, 5 };
/*  7 */   private static final int[] f = new int[] { 11, 8, 8 };
/*  8 */   private static final int[] g = new int[] { 20, 20, 20 };
/*    */   public final int a;
/*    */   
/*    */   public EnchantmentWeaponDamage(Enchantment.Rarity enchantment_rarity, int i, EnumItemSlot... aenumitemslot) {
/* 12 */     super(enchantment_rarity, EnchantmentSlotType.WEAPON, aenumitemslot);
/* 13 */     this.a = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(int i) {
/* 18 */     return e[this.a] + (i - 1) * f[this.a];
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(int i) {
/* 23 */     return a(i) + g[this.a];
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxLevel() {
/* 28 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(int i, EnumMonsterType enummonstertype) {
/* 33 */     return (this.a == 0) ? (1.0F + Math.max(0, i - 1) * 0.5F) : ((this.a == 1 && enummonstertype == EnumMonsterType.UNDEAD) ? (i * 2.5F) : ((this.a == 2 && enummonstertype == EnumMonsterType.ARTHROPOD) ? (i * 2.5F) : 0.0F));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(Enchantment enchantment) {
/* 38 */     return !(enchantment instanceof EnchantmentWeaponDamage);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEnchant(ItemStack itemstack) {
/* 43 */     return (itemstack.getItem() instanceof ItemAxe) ? true : super.canEnchant(itemstack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(EntityLiving entityliving, Entity entity, int i) {
/* 48 */     if (entity instanceof EntityLiving) {
/* 49 */       EntityLiving entityliving1 = (EntityLiving)entity;
/*    */       
/* 51 */       if (this.a == 2 && entityliving1.getMonsterType() == EnumMonsterType.ARTHROPOD) {
/* 52 */         int j = 20 + entityliving.getRandom().nextInt(10 * i);
/*    */         
/* 54 */         entityliving1.addEffect(new MobEffect(MobEffects.SLOWER_MOVEMENT, j, 3), EntityPotionEffectEvent.Cause.ATTACK);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentWeaponDamage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */