/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTool
/*    */   extends ItemToolMaterial
/*    */   implements ItemVanishable
/*    */ {
/*    */   private final Set<Block> a;
/*    */   protected final float b;
/*    */   private final float c;
/*    */   private final Multimap<AttributeBase, AttributeModifier> d;
/*    */   
/*    */   protected ItemTool(float var0, float var1, ToolMaterial var2, Set<Block> var3, Item.Info var4) {
/* 24 */     super(var2, var4);
/* 25 */     this.a = var3;
/* 26 */     this.b = var2.b();
/* 27 */     this.c = var0 + var2.c();
/*    */ 
/*    */     
/* 30 */     ImmutableMultimap.Builder<AttributeBase, AttributeModifier> var5 = ImmutableMultimap.builder();
/* 31 */     var5.put(GenericAttributes.ATTACK_DAMAGE, new AttributeModifier(f, "Tool modifier", this.c, AttributeModifier.Operation.ADDITION));
/* 32 */     var5.put(GenericAttributes.ATTACK_SPEED, new AttributeModifier(g, "Tool modifier", var1, AttributeModifier.Operation.ADDITION));
/* 33 */     this.d = (Multimap<AttributeBase, AttributeModifier>)var5.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack var0, IBlockData var1) {
/* 38 */     return this.a.contains(var1.getBlock()) ? this.b : 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, EntityLiving var1, EntityLiving var2) {
/* 43 */     var0.damage(2, var2, var0 -> var0.broadcastItemBreak(EnumItemSlot.MAINHAND));
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack var0, World var1, IBlockData var2, BlockPosition var3, EntityLiving var4) {
/* 50 */     if (!var1.isClientSide && var2.h(var1, var3) != 0.0F) {
/* 51 */       var0.damage(1, var4, var0 -> var0.broadcastItemBreak(EnumItemSlot.MAINHAND));
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot var0) {
/* 58 */     if (var0 == EnumItemSlot.MAINHAND) {
/* 59 */       return this.d;
/*    */     }
/* 61 */     return super.a(var0);
/*    */   }
/*    */   
/*    */   public float d() {
/* 65 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */