/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IScoreboardCriteria
/*     */ {
/*  14 */   public static final Map<String, IScoreboardCriteria> criteria = Maps.newHashMap();
/*  15 */   public static final IScoreboardCriteria DUMMY = new IScoreboardCriteria("dummy");
/*  16 */   public static final IScoreboardCriteria TRIGGER = new IScoreboardCriteria("trigger");
/*  17 */   public static final IScoreboardCriteria DEATH_COUNT = new IScoreboardCriteria("deathCount");
/*  18 */   public static final IScoreboardCriteria PLAYER_KILL_COUNT = new IScoreboardCriteria("playerKillCount");
/*  19 */   public static final IScoreboardCriteria TOTAL_KILL_COUNT = new IScoreboardCriteria("totalKillCount");
/*  20 */   public static final IScoreboardCriteria HEALTH = new IScoreboardCriteria("health", true, EnumScoreboardHealthDisplay.HEARTS);
/*  21 */   public static final IScoreboardCriteria FOOD = new IScoreboardCriteria("food", true, EnumScoreboardHealthDisplay.INTEGER);
/*  22 */   public static final IScoreboardCriteria AIR = new IScoreboardCriteria("air", true, EnumScoreboardHealthDisplay.INTEGER);
/*  23 */   public static final IScoreboardCriteria ARMOR = new IScoreboardCriteria("armor", true, EnumScoreboardHealthDisplay.INTEGER);
/*  24 */   public static final IScoreboardCriteria XP = new IScoreboardCriteria("xp", true, EnumScoreboardHealthDisplay.INTEGER);
/*  25 */   public static final IScoreboardCriteria LEVEL = new IScoreboardCriteria("level", true, EnumScoreboardHealthDisplay.INTEGER);
/*  26 */   public static final IScoreboardCriteria[] m = new IScoreboardCriteria[] { new IScoreboardCriteria("teamkill." + EnumChatFormat.BLACK
/*  27 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_BLUE.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_GREEN
/*  28 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_AQUA.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_RED
/*  29 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_PURPLE.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GOLD
/*  30 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GRAY.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_GRAY
/*  31 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.BLUE.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GREEN
/*  32 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.AQUA.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.RED
/*  33 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.LIGHT_PURPLE.f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.YELLOW
/*  34 */         .f()), new IScoreboardCriteria("teamkill." + EnumChatFormat.WHITE.f()) };
/*     */   
/*  36 */   public static final IScoreboardCriteria[] n = new IScoreboardCriteria[] { new IScoreboardCriteria("killedByTeam." + EnumChatFormat.BLACK
/*  37 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_BLUE.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_GREEN
/*  38 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_AQUA.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_RED
/*  39 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_PURPLE.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GOLD
/*  40 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GRAY.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_GRAY
/*  41 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.BLUE.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GREEN
/*  42 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.AQUA.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.RED
/*  43 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.LIGHT_PURPLE.f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.YELLOW
/*  44 */         .f()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.WHITE.f()) };
/*     */   
/*     */   private final String o;
/*     */   
/*     */   private final boolean p;
/*     */   private final EnumScoreboardHealthDisplay q;
/*     */   
/*     */   public IScoreboardCriteria(String var0) {
/*  52 */     this(var0, false, EnumScoreboardHealthDisplay.INTEGER);
/*     */   }
/*     */   
/*     */   protected IScoreboardCriteria(String var0, boolean var1, EnumScoreboardHealthDisplay var2) {
/*  56 */     this.o = var0;
/*  57 */     this.p = var1;
/*  58 */     this.q = var2;
/*  59 */     criteria.put(var0, this);
/*     */   }
/*     */   
/*     */   public static Optional<IScoreboardCriteria> a(String var0) {
/*  63 */     if (criteria.containsKey(var0)) {
/*  64 */       return Optional.of(criteria.get(var0));
/*     */     }
/*  66 */     int var1 = var0.indexOf(':');
/*  67 */     if (var1 < 0) {
/*  68 */       return Optional.empty();
/*     */     }
/*  70 */     return IRegistry.STATS.getOptional(MinecraftKey.a(var0.substring(0, var1), '.'))
/*  71 */       .flatMap(var2 -> a(var2, MinecraftKey.a(var0.substring(var1 + 1), '.')));
/*     */   }
/*     */   
/*     */   private static <T> Optional<IScoreboardCriteria> a(StatisticWrapper<T> var0, MinecraftKey var1) {
/*  75 */     return var0.getRegistry().getOptional(var1).map(var0::b);
/*     */   }
/*     */   
/*     */   public String getName() {
/*  79 */     return this.o;
/*     */   }
/*     */   
/*     */   public boolean isReadOnly() {
/*  83 */     return this.p;
/*     */   }
/*     */   
/*     */   public EnumScoreboardHealthDisplay e() {
/*  87 */     return this.q;
/*     */   }
/*     */   
/*     */   public enum EnumScoreboardHealthDisplay {
/*  91 */     INTEGER("integer"),
/*  92 */     HEARTS("hearts");
/*     */     
/*     */     private final String c;
/*     */     private static final Map<String, EnumScoreboardHealthDisplay> d;
/*     */     
/*     */     EnumScoreboardHealthDisplay(String var2) {
/*  98 */       this.c = var2;
/*     */     }
/*     */     
/*     */     public String a() {
/* 102 */       return this.c;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 108 */       ImmutableMap.Builder<String, EnumScoreboardHealthDisplay> var0 = ImmutableMap.builder();
/* 109 */       for (EnumScoreboardHealthDisplay var4 : values()) {
/* 110 */         var0.put(var4.c, var4);
/*     */       }
/* 112 */       d = (Map<String, EnumScoreboardHealthDisplay>)var0.build();
/*     */     }
/*     */     
/*     */     public static EnumScoreboardHealthDisplay a(String var0) {
/* 116 */       return d.getOrDefault(var0, INTEGER);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IScoreboardCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */