/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Suppliers;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VillagePlaceType
/*     */ {
/*  26 */   private static final Supplier<Set<VillagePlaceType>> y = (Supplier<Set<VillagePlaceType>>)Suppliers.memoize(() -> (Set)IRegistry.VILLAGER_PROFESSION.g().map(VillagerProfession::b).collect(Collectors.toSet())); static {
/*  27 */     a = (var0 -> ((Set)y.get()).contains(var0));
/*     */   }
/*     */   
/*     */   public static final Predicate<VillagePlaceType> a;
/*     */   public static final Predicate<VillagePlaceType> b = var0 -> true;
/*     */   private static final Set<IBlockData> z;
/*     */   
/*     */   static {
/*  35 */     z = (Set<IBlockData>)ImmutableList.of(Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, (Object[])new Block[] { Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED }).stream().flatMap(var0 -> var0.getStates().a().stream()).filter(var0 -> (var0.get(BlockBed.PART) == BlockPropertyBedPart.HEAD)).collect(ImmutableSet.toImmutableSet());
/*     */   }
/*  37 */   private static final Map<IBlockData, VillagePlaceType> A = Maps.newHashMap();
/*     */ 
/*     */   
/*  40 */   public static final VillagePlaceType c = a("unemployed", (Set<IBlockData>)ImmutableSet.of(), 1, a, 1);
/*  41 */   public static final VillagePlaceType d = a("armorer", a(Blocks.BLAST_FURNACE), 1, 1);
/*  42 */   public static final VillagePlaceType e = a("butcher", a(Blocks.SMOKER), 1, 1);
/*  43 */   public static final VillagePlaceType f = a("cartographer", a(Blocks.CARTOGRAPHY_TABLE), 1, 1);
/*  44 */   public static final VillagePlaceType g = a("cleric", a(Blocks.BREWING_STAND), 1, 1);
/*  45 */   public static final VillagePlaceType h = a("farmer", a(Blocks.COMPOSTER), 1, 1);
/*  46 */   public static final VillagePlaceType i = a("fisherman", a(Blocks.BARREL), 1, 1);
/*  47 */   public static final VillagePlaceType j = a("fletcher", a(Blocks.FLETCHING_TABLE), 1, 1);
/*  48 */   public static final VillagePlaceType k = a("leatherworker", a(Blocks.CAULDRON), 1, 1);
/*  49 */   public static final VillagePlaceType l = a("librarian", a(Blocks.LECTERN), 1, 1);
/*  50 */   public static final VillagePlaceType m = a("mason", a(Blocks.STONECUTTER), 1, 1);
/*  51 */   public static final VillagePlaceType n = a("nitwit", (Set<IBlockData>)ImmutableSet.of(), 1, 1);
/*  52 */   public static final VillagePlaceType o = a("shepherd", a(Blocks.LOOM), 1, 1);
/*  53 */   public static final VillagePlaceType p = a("toolsmith", a(Blocks.SMITHING_TABLE), 1, 1);
/*  54 */   public static final VillagePlaceType q = a("weaponsmith", a(Blocks.GRINDSTONE), 1, 1);
/*  55 */   public static final VillagePlaceType r = a("home", z, 1, 1);
/*  56 */   public static final VillagePlaceType s = a("meeting", a(Blocks.BELL), 32, 6);
/*  57 */   public static final VillagePlaceType t = a("beehive", a(Blocks.BEEHIVE), 0, 1);
/*  58 */   public static final VillagePlaceType u = a("bee_nest", a(Blocks.BEE_NEST), 0, 1);
/*  59 */   public static final VillagePlaceType v = a("nether_portal", a(Blocks.NETHER_PORTAL), 0, 1);
/*  60 */   public static final VillagePlaceType w = a("lodestone", a(Blocks.LODESTONE), 0, 1);
/*     */   
/*  62 */   protected static final Set<IBlockData> x = (Set<IBlockData>)new ObjectOpenHashSet(A.keySet());
/*     */   
/*     */   private final String B;
/*     */   private final Set<IBlockData> C;
/*     */   private final int D;
/*     */   private final Predicate<VillagePlaceType> E;
/*     */   private final int F;
/*     */   
/*     */   private static Set<IBlockData> a(Block var0) {
/*  71 */     return (Set<IBlockData>)ImmutableSet.copyOf((Collection)var0.getStates().a());
/*     */   }
/*     */   
/*     */   private VillagePlaceType(String var0, Set<IBlockData> var1, int var2, Predicate<VillagePlaceType> var3, int var4) {
/*  75 */     this.B = var0;
/*  76 */     this.C = (Set<IBlockData>)ImmutableSet.copyOf(var1);
/*  77 */     this.D = var2;
/*  78 */     this.E = var3;
/*  79 */     this.F = var4;
/*     */   }
/*     */   
/*     */   private VillagePlaceType(String var0, Set<IBlockData> var1, int var2, int var3) {
/*  83 */     this.B = var0;
/*  84 */     this.C = (Set<IBlockData>)ImmutableSet.copyOf(var1);
/*  85 */     this.D = var2;
/*  86 */     this.E = (var0 -> (var0 == this));
/*  87 */     this.F = var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*  95 */     return this.D;
/*     */   }
/*     */   
/*     */   public Predicate<VillagePlaceType> c() {
/*  99 */     return this.E;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() {
/* 107 */     return this.F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 112 */     return this.B;
/*     */   }
/*     */   
/*     */   private static VillagePlaceType a(String var0, Set<IBlockData> var1, int var2, int var3) {
/* 116 */     return a(IRegistry.<VillagePlaceType, VillagePlaceType>a(IRegistry.POINT_OF_INTEREST_TYPE, new MinecraftKey(var0), new VillagePlaceType(var0, var1, var2, var3)));
/*     */   }
/*     */   
/*     */   private static VillagePlaceType a(String var0, Set<IBlockData> var1, int var2, Predicate<VillagePlaceType> var3, int var4) {
/* 120 */     return a(IRegistry.<VillagePlaceType, VillagePlaceType>a(IRegistry.POINT_OF_INTEREST_TYPE, new MinecraftKey(var0), new VillagePlaceType(var0, var1, var2, var3, var4)));
/*     */   }
/*     */   
/*     */   private static VillagePlaceType a(VillagePlaceType var0) {
/* 124 */     var0.C.forEach(var1 -> {
/*     */           VillagePlaceType var2 = A.put(var1, var0);
/*     */           
/*     */           if (var2 != null) {
/*     */             throw (IllegalStateException)SystemUtils.c(new IllegalStateException(String.format("%s is defined in too many tags", new Object[] { var1 })));
/*     */           }
/*     */         });
/* 131 */     return var0;
/*     */   }
/*     */   
/*     */   public static Optional<VillagePlaceType> b(IBlockData var0) {
/* 135 */     return Optional.ofNullable(A.get(var0));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagePlaceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */