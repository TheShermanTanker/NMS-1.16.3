/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSword
/*    */   extends ItemToolMaterial
/*    */   implements ItemVanishable
/*    */ {
/*    */   private final float a;
/*    */   private final Multimap<AttributeBase, AttributeModifier> b;
/*    */   
/*    */   public ItemSword(ToolMaterial var0, int var1, float var2, Item.Info var3) {
/* 24 */     super(var0, var3);
/*    */     
/* 26 */     this.a = var1 + var0.c();
/*    */ 
/*    */     
/* 29 */     ImmutableMultimap.Builder<AttributeBase, AttributeModifier> var4 = ImmutableMultimap.builder();
/* 30 */     var4.put(GenericAttributes.ATTACK_DAMAGE, new AttributeModifier(f, "Weapon modifier", this.a, AttributeModifier.Operation.ADDITION));
/* 31 */     var4.put(GenericAttributes.ATTACK_SPEED, new AttributeModifier(g, "Weapon modifier", var2, AttributeModifier.Operation.ADDITION));
/* 32 */     this.b = (Multimap<AttributeBase, AttributeModifier>)var4.build();
/*    */   }
/*    */   
/*    */   public float f() {
/* 36 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData var0, World var1, BlockPosition var2, EntityHuman var3) {
/* 41 */     return !var3.isCreative();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack var0, IBlockData var1) {
/* 46 */     if (var1.a(Blocks.COBWEB)) {
/* 47 */       return 15.0F;
/*    */     }
/*    */     
/* 50 */     Material var2 = var1.getMaterial();
/* 51 */     if (var2 == Material.PLANT || var2 == Material.REPLACEABLE_PLANT || var2 == Material.CORAL || var1.a(TagsBlock.LEAVES) || var2 == Material.PUMPKIN) {
/* 52 */       return 1.5F;
/*    */     }
/* 54 */     return 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, EntityLiving var1, EntityLiving var2) {
/* 59 */     var0.damage(1, var2, var0 -> var0.broadcastItemBreak(EnumItemSlot.MAINHAND));
/* 60 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, World var1, IBlockData var2, BlockPosition var3, EntityLiving var4) {
/* 66 */     if (var2.h(var1, var3) != 0.0F) {
/* 67 */       var0.damage(2, var4, var0 -> var0.broadcastItemBreak(EnumItemSlot.MAINHAND));
/*    */     }
/* 69 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDestroySpecialBlock(IBlockData var0) {
/* 74 */     return var0.a(Blocks.COBWEB);
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot var0) {
/* 79 */     if (var0 == EnumItemSlot.MAINHAND) {
/* 80 */       return this.b;
/*    */     }
/* 82 */     return super.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */