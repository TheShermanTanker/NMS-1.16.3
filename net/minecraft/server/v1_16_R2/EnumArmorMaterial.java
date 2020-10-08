/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumArmorMaterial
/*    */   implements ArmorMaterial
/*    */ {
/* 12 */   LEATHER("leather", 5, new int[] { 1, 2, 3, 1 }, 15, SoundEffects.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.LEATHER })),
/* 13 */   CHAIN("chainmail", 15, new int[] { 1, 4, 5, 2 }, 12, SoundEffects.ITEM_ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.IRON_INGOT })),
/* 14 */   IRON("iron", 15, new int[] { 2, 5, 6, 2 }, 9, SoundEffects.ITEM_ARMOR_EQUIP_IRON, 0.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.IRON_INGOT })),
/* 15 */   GOLD("gold", 7, new int[] { 1, 3, 5, 2 }, 25, SoundEffects.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.GOLD_INGOT })),
/* 16 */   DIAMOND("diamond", 33, new int[] { 3, 6, 8, 3 }, 10, SoundEffects.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.DIAMOND })),
/* 17 */   TURTLE("turtle", 25, new int[] { 2, 5, 6, 2 }, 9, SoundEffects.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> RecipeItemStack.a(new IMaterial[] { Items.SCUTE })),
/* 18 */   NETHERITE("netherite", 37, new int[] { 3, 6, 8, 3 }, 15, SoundEffects.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> RecipeItemStack.a(new IMaterial[] { Items.NETHERITE_INGOT }));
/*    */   private static final int[] h;
/*    */   
/*    */   static {
/* 22 */     h = new int[] { 13, 15, 16, 11 };
/*    */   }
/*    */ 
/*    */   
/*    */   private final String i;
/*    */   private final int j;
/*    */   private final int[] k;
/*    */   private final int l;
/*    */   private final SoundEffect m;
/*    */   private final float n;
/*    */   private final float o;
/*    */   private final LazyInitVar<RecipeItemStack> p;
/*    */   
/*    */   EnumArmorMaterial(String var2, int var3, int[] var4, int var5, SoundEffect var6, float var7, float var8, Supplier<RecipeItemStack> var9) {
/* 36 */     this.i = var2;
/* 37 */     this.j = var3;
/* 38 */     this.k = var4;
/* 39 */     this.l = var5;
/* 40 */     this.m = var6;
/* 41 */     this.n = var7;
/* 42 */     this.o = var8;
/* 43 */     this.p = new LazyInitVar<>(var9);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(EnumItemSlot var0) {
/* 48 */     return h[var0.b()] * this.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(EnumItemSlot var0) {
/* 53 */     return this.k[var0.b()];
/*    */   }
/*    */ 
/*    */   
/*    */   public int a() {
/* 58 */     return this.l;
/*    */   }
/*    */ 
/*    */   
/*    */   public SoundEffect b() {
/* 63 */     return this.m;
/*    */   }
/*    */ 
/*    */   
/*    */   public RecipeItemStack c() {
/* 68 */     return this.p.a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float e() {
/* 78 */     return this.n;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 83 */     return this.o;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumArmorMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */