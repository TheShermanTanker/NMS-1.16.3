/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CreativeModeTab
/*     */ {
/*  15 */   public static final CreativeModeTab[] a = new CreativeModeTab[12];
/*  16 */   public static final CreativeModeTab b = (new CreativeModeTab(0, "buildingBlocks")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  21 */     }).b("building_blocks");
/*  22 */   public static final CreativeModeTab c = new CreativeModeTab(1, "decorations")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  28 */   public static final CreativeModeTab d = new CreativeModeTab(2, "redstone")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  34 */   public static final CreativeModeTab e = new CreativeModeTab(3, "transportation")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  40 */   public static final CreativeModeTab f = new CreativeModeTab(6, "misc")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  46 */   public static final CreativeModeTab g = (new CreativeModeTab(5, "search")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  51 */     }).a("item_search.png");
/*     */   
/*  53 */   public static final CreativeModeTab h = new CreativeModeTab(7, "food")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  59 */   public static final CreativeModeTab i = (new CreativeModeTab(8, "tools")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  64 */     }).a(new EnchantmentSlotType[] { EnchantmentSlotType.VANISHABLE, EnchantmentSlotType.DIGGER, EnchantmentSlotType.FISHING_ROD, EnchantmentSlotType.BREAKABLE });
/*  65 */   public static final CreativeModeTab j = (new CreativeModeTab(9, "combat")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  70 */     }).a(new EnchantmentSlotType[] { EnchantmentSlotType.VANISHABLE, EnchantmentSlotType.ARMOR, EnchantmentSlotType.ARMOR_FEET, EnchantmentSlotType.ARMOR_HEAD, EnchantmentSlotType.ARMOR_LEGS, EnchantmentSlotType.ARMOR_CHEST, EnchantmentSlotType.BOW, EnchantmentSlotType.WEAPON, EnchantmentSlotType.WEARABLE, EnchantmentSlotType.BREAKABLE, 
/*  71 */         EnchantmentSlotType.TRIDENT, EnchantmentSlotType.CROSSBOW }); public static final CreativeModeTab k = new CreativeModeTab(10, "brewing")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */   
/*  77 */   public static final CreativeModeTab l = f;
/*  78 */   public static final CreativeModeTab m = new CreativeModeTab(4, "hotbar")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public static final CreativeModeTab n = (new CreativeModeTab(11, "inventory")
/*     */     {
/*     */ 
/*     */ 
/*     */     
/*  99 */     }).a("inventory.png").j().h();
/*     */   
/*     */   private final int o;
/*     */   private final String p;
/*     */   private final IChatBaseComponent q;
/*     */   private String r;
/* 105 */   private String s = "items.png";
/*     */   private boolean t = true;
/*     */   private boolean u = true;
/* 108 */   private EnchantmentSlotType[] v = new EnchantmentSlotType[0];
/*     */   private ItemStack w;
/*     */   
/*     */   public CreativeModeTab(int var0, String var1) {
/* 112 */     this.o = var0;
/* 113 */     this.p = var1;
/* 114 */     this.q = new ChatMessage("itemGroup." + var1);
/* 115 */     this.w = ItemStack.b;
/*     */     
/* 117 */     a[var0] = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/* 125 */     return (this.r == null) ? this.p : this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab a(String var0) {
/* 146 */     this.s = var0;
/* 147 */     return this;
/*     */   }
/*     */   
/*     */   public CreativeModeTab b(String var0) {
/* 151 */     this.r = var0;
/* 152 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab h() {
/* 160 */     this.u = false;
/* 161 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CreativeModeTab j() {
/* 169 */     this.t = false;
/* 170 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnchantmentSlotType[] n() {
/* 186 */     return this.v;
/*     */   }
/*     */   
/*     */   public CreativeModeTab a(EnchantmentSlotType... var0) {
/* 190 */     this.v = var0;
/* 191 */     return this;
/*     */   }
/*     */   
/*     */   public boolean a(@Nullable EnchantmentSlotType var0) {
/* 195 */     if (var0 != null) {
/* 196 */       for (EnchantmentSlotType var4 : this.v) {
/* 197 */         if (var4 == var0) {
/* 198 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 203 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CreativeModeTab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */