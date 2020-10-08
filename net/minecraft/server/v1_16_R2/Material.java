/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public final class Material {
/*   4 */   public static final Material AIR = a.a((new a(MaterialMapColor.b)).c()).b().e().h();
/*   5 */   public static final Material STRUCTURE_VOID = a.a((new a(MaterialMapColor.b)).c()).b().e().h();
/*   6 */   public static final Material PORTAL = a.a((new a(MaterialMapColor.b)).c()).b().g().h();
/*   7 */   public static final Material WOOL = a.a((new a(MaterialMapColor.e)).c()).b().d().h();
/*   8 */   public static final Material PLANT = a.a((new a(MaterialMapColor.i)).c()).b().f().h();
/*   9 */   public static final Material WATER_PLANT = a.a((new a(MaterialMapColor.n)).c()).b().f().h();
/*  10 */   public static final Material REPLACEABLE_PLANT = a.a((new a(MaterialMapColor.i)).c()).b().f().e().d().h();
/*  11 */   public static final Material h = a.a((new a(MaterialMapColor.i)).c()).b().f().e().h();
/*  12 */   public static final Material REPLACEABLE_WATER_PLANT = a.a((new a(MaterialMapColor.n)).c()).b().f().e().h();
/*  13 */   public static final Material WATER = a.a((new a(MaterialMapColor.n)).c()).b().f().e().a().h();
/*  14 */   public static final Material BUBBLE_COLUMN = a.a((new a(MaterialMapColor.n)).c()).b().f().e().a().h();
/*  15 */   public static final Material LAVA = a.a((new a(MaterialMapColor.f)).c()).b().f().e().a().h();
/*  16 */   public static final Material PACKED_ICE = a.a((new a(MaterialMapColor.j)).c()).b().f().e().h();
/*  17 */   public static final Material FIRE = a.a((new a(MaterialMapColor.b)).c()).b().f().e().h();
/*  18 */   public static final Material ORIENTABLE = a.a((new a(MaterialMapColor.b)).c()).b().f().h();
/*  19 */   public static final Material WEB = a.a((new a(MaterialMapColor.e)).c()).f().h();
/*     */   
/*  21 */   public static final Material BUILDABLE_GLASS = (new a(MaterialMapColor.b)).h();
/*  22 */   public static final Material CLAY = (new a(MaterialMapColor.k)).h();
/*  23 */   public static final Material EARTH = (new a(MaterialMapColor.l)).h();
/*  24 */   public static final Material GRASS = (new a(MaterialMapColor.c)).h();
/*  25 */   public static final Material SNOW_LAYER = (new a(MaterialMapColor.g)).h();
/*  26 */   public static final Material SAND = (new a(MaterialMapColor.d)).h();
/*  27 */   public static final Material SPONGE = (new a(MaterialMapColor.t)).h();
/*  28 */   public static final Material SHULKER_SHELL = (new a(MaterialMapColor.z)).h();
/*     */   
/*  30 */   public static final Material WOOD = (new a(MaterialMapColor.o)).d().h();
/*  31 */   public static final Material NETHER_WOOD = (new a(MaterialMapColor.o)).h();
/*  32 */   public static final Material BAMBOO_SAPLING = (new a(MaterialMapColor.o)).d().f().c().h();
/*  33 */   public static final Material BAMBOO = (new a(MaterialMapColor.o)).d().f().h();
/*  34 */   public static final Material CLOTH = (new a(MaterialMapColor.e)).d().h();
/*  35 */   public static final Material TNT = a.a((new a(MaterialMapColor.f)).d()).h();
/*  36 */   public static final Material LEAVES = a.a((new a(MaterialMapColor.i)).d()).f().h();
/*  37 */   public static final Material SHATTERABLE = a.a(new a(MaterialMapColor.b)).h();
/*  38 */   public static final Material ICE = a.a(new a(MaterialMapColor.g)).h();
/*  39 */   public static final Material CACTUS = a.a(new a(MaterialMapColor.i)).f().h();
/*     */   
/*  41 */   public static final Material STONE = (new a(MaterialMapColor.m)).h();
/*  42 */   public static final Material ORE = (new a(MaterialMapColor.h)).h();
/*  43 */   public static final Material SNOW_BLOCK = (new a(MaterialMapColor.j)).h();
/*     */   
/*  45 */   public static final Material HEAVY = (new a(MaterialMapColor.h)).g().h();
/*  46 */   public static final Material BANNER = (new a(MaterialMapColor.b)).g().h();
/*     */   
/*  48 */   public static final Material PISTON = (new a(MaterialMapColor.m)).g().h();
/*     */   
/*  50 */   public static final Material CORAL = (new a(MaterialMapColor.i)).f().h();
/*  51 */   public static final Material PUMPKIN = (new a(MaterialMapColor.i)).f().h();
/*  52 */   public static final Material DRAGON_EGG = (new a(MaterialMapColor.i)).f().h();
/*  53 */   public static final Material CAKE = (new a(MaterialMapColor.b)).f().h();
/*     */   
/*     */   private final MaterialMapColor S;
/*     */   private final EnumPistonReaction T;
/*     */   private final boolean U;
/*     */   private final boolean canBurn;
/*     */   private final boolean W;
/*     */   private final boolean X;
/*     */   private final boolean Y;
/*     */   private final boolean Z;
/*     */   
/*     */   public Material(MaterialMapColor var0, boolean var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6, EnumPistonReaction var7) {
/*  65 */     this.S = var0;
/*  66 */     this.W = var1;
/*  67 */     this.Z = var2;
/*  68 */     this.U = var3;
/*  69 */     this.X = var4;
/*  70 */     this.canBurn = var5;
/*  71 */     this.Y = var6;
/*  72 */     this.T = var7;
/*     */   }
/*     */   
/*     */   public boolean isLiquid() {
/*  76 */     return this.W;
/*     */   }
/*     */   
/*     */   public boolean isBuildable() {
/*  80 */     return this.Z;
/*     */   }
/*     */   
/*     */   public boolean isSolid() {
/*  84 */     return this.U;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurnable() {
/*  89 */     return this.canBurn;
/*     */   }
/*     */   
/*     */   public boolean isReplaceable() {
/*  93 */     return this.Y;
/*     */   }
/*     */   
/*     */   public boolean f() {
/*  97 */     return this.X;
/*     */   }
/*     */   
/*     */   public EnumPistonReaction getPushReaction() {
/* 101 */     return this.T;
/*     */   }
/*     */   
/*     */   public MaterialMapColor h() {
/* 105 */     return this.S;
/*     */   }
/*     */   
/*     */   public static class a {
/* 109 */     private EnumPistonReaction a = EnumPistonReaction.NORMAL;
/*     */     private boolean b = true;
/*     */     private boolean c;
/*     */     private boolean d;
/*     */     private boolean e;
/*     */     private boolean f = true;
/*     */     private final MaterialMapColor g;
/*     */     private boolean h = true;
/*     */     
/*     */     public a(MaterialMapColor var0) {
/* 119 */       this.g = var0;
/*     */     }
/*     */     
/*     */     public a a() {
/* 123 */       this.d = true;
/* 124 */       return this;
/*     */     }
/*     */     
/*     */     public a b() {
/* 128 */       this.f = false;
/* 129 */       return this;
/*     */     }
/*     */     
/*     */     public a c() {
/* 133 */       this.b = false;
/* 134 */       return this;
/*     */     }
/*     */     
/*     */     private a i() {
/* 138 */       this.h = false;
/* 139 */       return this;
/*     */     }
/*     */     
/*     */     protected a d() {
/* 143 */       this.c = true;
/* 144 */       return this;
/*     */     }
/*     */     
/*     */     public a e() {
/* 148 */       this.e = true;
/* 149 */       return this;
/*     */     }
/*     */     
/*     */     protected a f() {
/* 153 */       this.a = EnumPistonReaction.DESTROY;
/* 154 */       return this;
/*     */     }
/*     */     
/*     */     protected a g() {
/* 158 */       this.a = EnumPistonReaction.BLOCK;
/* 159 */       return this;
/*     */     }
/*     */     
/*     */     public Material h() {
/* 163 */       return new Material(this.g, this.d, this.f, this.b, this.h, this.c, this.e, this.a);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */