/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ public abstract class ItemProjectileWeapon extends Item {
/*    */   public static final Predicate<ItemStack> a;
/*    */   public static final Predicate<ItemStack> b;
/*    */   
/*    */   static {
/* 10 */     a = (var0 -> var0.getItem().a(TagsItem.ARROWS));
/* 11 */     b = a.or(var0 -> (var0.getItem() == Items.FIREWORK_ROCKET));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemProjectileWeapon(Item.Info var0) {
/* 19 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<ItemStack> e() {
/* 24 */     return b();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ItemStack a(EntityLiving var0, Predicate<ItemStack> var1) {
/* 31 */     if (var1.test(var0.b(EnumHand.OFF_HAND))) {
/* 32 */       return var0.b(EnumHand.OFF_HAND);
/*    */     }
/* 34 */     if (var1.test(var0.b(EnumHand.MAIN_HAND))) {
/* 35 */       return var0.b(EnumHand.MAIN_HAND);
/*    */     }
/* 37 */     return ItemStack.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public int c() {
/* 42 */     return 1;
/*    */   }
/*    */   
/*    */   public abstract Predicate<ItemStack> b();
/*    */   
/*    */   public abstract int d();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemProjectileWeapon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */