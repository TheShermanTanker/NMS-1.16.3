/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.primitives.Doubles;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.ToDoubleFunction;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ArgumentParserSelector {
/*  24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.entity.invalid")); public static final DynamicCommandExceptionType b; static {
/*  25 */     b = new DynamicCommandExceptionType(object -> new ChatMessage("argument.entity.selector.unknown", new Object[] { object }));
/*     */   }
/*     */   
/*  28 */   public static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.entity.selector.not_allowed"));
/*  29 */   public static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("argument.entity.selector.missing"));
/*  30 */   public static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("argument.entity.options.unterminated")); public static final DynamicCommandExceptionType f;
/*  31 */   static { f = new DynamicCommandExceptionType(object -> new ChatMessage("argument.entity.options.valueless", new Object[] { object })); }
/*     */    public static final BiConsumer<Vec3D, List<? extends Entity>> g = (vec3d, list) -> {
/*     */     
/*     */     }; public static final BiConsumer<Vec3D, List<? extends Entity>> h;
/*     */   static {
/*  36 */     h = ((vec3d, list) -> list.sort(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  41 */     i = ((vec3d, list) -> list.sort(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  46 */     j = ((vec3d, list) -> Collections.shuffle(list));
/*     */ 
/*     */     
/*  49 */     k = ((suggestionsbuilder, consumer) -> suggestionsbuilder.buildFuture());
/*     */   }
/*     */   public static final BiConsumer<Vec3D, List<? extends Entity>> i; public static final BiConsumer<Vec3D, List<? extends Entity>> j; public static final BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> k;
/*     */   private final StringReader l;
/*     */   private final boolean m;
/*     */   private int n;
/*     */   private boolean o;
/*     */   private boolean p;
/*     */   private CriterionConditionValue.FloatRange q;
/*     */   private CriterionConditionValue.IntegerRange r;
/*     */   @Nullable
/*     */   private Double s;
/*     */   @Nullable
/*     */   private Double t;
/*     */   @Nullable
/*     */   private Double u;
/*     */   @Nullable
/*     */   private Double v;
/*     */   @Nullable
/*     */   private Double w;
/*     */   @Nullable
/*     */   private Double x;
/*     */   private CriterionConditionRange y;
/*     */   private CriterionConditionRange z;
/*     */   private Predicate<Entity> A;
/*     */   private BiConsumer<Vec3D, List<? extends Entity>> B;
/*     */   private boolean C;
/*     */   @Nullable
/*     */   private String D;
/*     */   private int E;
/*     */   @Nullable
/*     */   private UUID F;
/*     */   private BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> G;
/*     */   private boolean H;
/*     */   private boolean I;
/*     */   private boolean J;
/*     */   private boolean K;
/*     */   private boolean L;
/*     */   private boolean M;
/*     */   private boolean N;
/*     */   private boolean O;
/*     */   @Nullable
/*     */   private EntityTypes<?> P;
/*     */   private boolean Q;
/*     */   private boolean R;
/*     */   private boolean S;
/*     */   private boolean checkPermissions;
/*     */   
/*     */   public ArgumentParserSelector(StringReader stringreader) {
/*  98 */     this(stringreader, true);
/*     */   }
/*     */   
/*     */   public ArgumentParserSelector(StringReader stringreader, boolean flag) {
/* 102 */     this.q = CriterionConditionValue.FloatRange.e;
/* 103 */     this.r = CriterionConditionValue.IntegerRange.e;
/* 104 */     this.y = CriterionConditionRange.a;
/* 105 */     this.z = CriterionConditionRange.a;
/* 106 */     this.A = (entity -> true);
/*     */ 
/*     */     
/* 109 */     this.B = g;
/* 110 */     this.G = k;
/* 111 */     this.l = stringreader;
/* 112 */     this.m = flag;
/*     */   }
/*     */   
/*     */   public EntitySelector a() {
/*     */     AxisAlignedBB axisalignedbb;
/*     */     Function<Vec3D, Vec3D> function;
/* 118 */     if (this.v == null && this.w == null && this.x == null) {
/* 119 */       if (this.q.b() != null) {
/* 120 */         float f = this.q.b().floatValue();
/*     */         
/* 122 */         axisalignedbb = new AxisAlignedBB(-f, -f, -f, (f + 1.0F), (f + 1.0F), (f + 1.0F));
/*     */       } else {
/* 124 */         axisalignedbb = null;
/*     */       } 
/*     */     } else {
/* 127 */       axisalignedbb = a((this.v == null) ? 0.0D : this.v.doubleValue(), (this.w == null) ? 0.0D : this.w.doubleValue(), (this.x == null) ? 0.0D : this.x.doubleValue());
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 132 */     if (this.s == null && this.t == null && this.u == null) {
/* 133 */       function = (vec3d -> vec3d);
/*     */     }
/*     */     else {
/*     */       
/* 137 */       function = (vec3d -> new Vec3D((this.s == null) ? vec3d.x : this.s.doubleValue(), (this.t == null) ? vec3d.y : this.t.doubleValue(), (this.u == null) ? vec3d.z : this.u.doubleValue()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 142 */     return new EntitySelector(this.n, this.o, this.p, this.A, this.q, function, axisalignedbb, this.B, this.C, this.D, this.F, this.P, this.checkPermissions);
/*     */   }
/*     */   
/*     */   private AxisAlignedBB a(double d0, double d1, double d2) {
/* 146 */     boolean flag = (d0 < 0.0D);
/* 147 */     boolean flag1 = (d1 < 0.0D);
/* 148 */     boolean flag2 = (d2 < 0.0D);
/* 149 */     double d3 = flag ? d0 : 0.0D;
/* 150 */     double d4 = flag1 ? d1 : 0.0D;
/* 151 */     double d5 = flag2 ? d2 : 0.0D;
/* 152 */     double d6 = (flag ? 0.0D : d0) + 1.0D;
/* 153 */     double d7 = (flag1 ? 0.0D : d1) + 1.0D;
/* 154 */     double d8 = (flag2 ? 0.0D : d2) + 1.0D;
/*     */     
/* 156 */     return new AxisAlignedBB(d3, d4, d5, d6, d7, d8);
/*     */   }
/*     */   
/*     */   private void I() {
/* 160 */     if (this.y != CriterionConditionRange.a) {
/* 161 */       this.A = this.A.and(a(this.y, entity -> entity.pitch));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 166 */     if (this.z != CriterionConditionRange.a) {
/* 167 */       this.A = this.A.and(a(this.z, entity -> entity.yaw));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 172 */     if (!this.r.c()) {
/* 173 */       this.A = this.A.and(entity -> !(entity instanceof EntityPlayer) ? false : this.r.d(((EntityPlayer)entity).expLevel));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Predicate<Entity> a(CriterionConditionRange criterionconditionrange, ToDoubleFunction<Entity> todoublefunction) {
/* 181 */     double d0 = MathHelper.g((criterionconditionrange.a() == null) ? 0.0F : criterionconditionrange.a().floatValue());
/* 182 */     double d1 = MathHelper.g((criterionconditionrange.b() == null) ? 359.0F : criterionconditionrange.b().floatValue());
/*     */     
/* 184 */     return entity -> {
/*     */         double d2 = MathHelper.g(todoublefunction.applyAsDouble(entity));
/*     */         
/* 187 */         return (d0 > d1) ? ((d2 >= d0 || d2 <= d1)) : ((d2 >= d0 && d2 <= d1));
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parseSelector(boolean overridePermissions) throws CommandSyntaxException {
/* 193 */     this.checkPermissions = !overridePermissions;
/*     */     
/* 195 */     this.G = this::d;
/* 196 */     if (!this.l.canRead()) {
/* 197 */       throw d.createWithContext(this.l);
/*     */     }
/* 199 */     int i = this.l.getCursor();
/* 200 */     char c0 = this.l.read();
/*     */     
/* 202 */     if (c0 == 'p') {
/* 203 */       this.n = 1;
/* 204 */       this.o = false;
/* 205 */       this.B = h;
/* 206 */       a(EntityTypes.PLAYER);
/* 207 */     } else if (c0 == 'a') {
/* 208 */       this.n = Integer.MAX_VALUE;
/* 209 */       this.o = false;
/* 210 */       this.B = g;
/* 211 */       a(EntityTypes.PLAYER);
/* 212 */     } else if (c0 == 'r') {
/* 213 */       this.n = 1;
/* 214 */       this.o = false;
/* 215 */       this.B = j;
/* 216 */       a(EntityTypes.PLAYER);
/* 217 */     } else if (c0 == 's') {
/* 218 */       this.n = 1;
/* 219 */       this.o = true;
/* 220 */       this.C = true;
/*     */     } else {
/* 222 */       if (c0 != 'e') {
/* 223 */         this.l.setCursor(i);
/* 224 */         throw b.createWithContext(this.l, '@' + String.valueOf(c0));
/*     */       } 
/*     */       
/* 227 */       this.n = Integer.MAX_VALUE;
/* 228 */       this.o = true;
/* 229 */       this.B = g;
/* 230 */       this.A = Entity::isAlive;
/*     */     } 
/*     */     
/* 233 */     this.G = this::e;
/* 234 */     if (this.l.canRead() && this.l.peek() == '[') {
/* 235 */       this.l.skip();
/* 236 */       this.G = this::f;
/* 237 */       d();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c() throws CommandSyntaxException {
/* 244 */     if (this.l.canRead()) {
/* 245 */       this.G = this::c;
/*     */     }
/*     */     
/* 248 */     int i = this.l.getCursor();
/* 249 */     String s = this.l.readString();
/*     */     
/*     */     try {
/* 252 */       this.F = UUID.fromString(s);
/* 253 */       this.o = true;
/* 254 */     } catch (IllegalArgumentException illegalargumentexception) {
/* 255 */       if (s.isEmpty() || s.length() > 16) {
/* 256 */         this.l.setCursor(i);
/* 257 */         throw a.createWithContext(this.l);
/*     */       } 
/*     */       
/* 260 */       this.o = false;
/* 261 */       this.D = s;
/*     */     } 
/*     */     
/* 264 */     this.n = 1;
/*     */   }
/*     */   
/*     */   protected void d() throws CommandSyntaxException {
/* 268 */     this.G = this::g;
/* 269 */     this.l.skipWhitespace();
/*     */ 
/*     */     
/* 272 */     while (this.l.canRead() && this.l.peek() != ']') {
/* 273 */       this.l.skipWhitespace();
/* 274 */       int i = this.l.getCursor();
/* 275 */       String s = this.l.readString();
/* 276 */       PlayerSelector.a playerselector_a = PlayerSelector.a(this, s, i);
/*     */       
/* 278 */       this.l.skipWhitespace();
/* 279 */       if (!this.l.canRead() || this.l.peek() != '=') {
/* 280 */         this.l.setCursor(i);
/* 281 */         throw f.createWithContext(this.l, s);
/*     */       } 
/*     */       
/* 284 */       this.l.skip();
/* 285 */       this.l.skipWhitespace();
/* 286 */       this.G = k;
/* 287 */       playerselector_a.handle(this);
/* 288 */       this.l.skipWhitespace();
/* 289 */       this.G = this::h;
/* 290 */       if (!this.l.canRead()) {
/*     */         continue;
/*     */       }
/*     */       
/* 294 */       if (this.l.peek() == ',') {
/* 295 */         this.l.skip();
/* 296 */         this.G = this::g;
/*     */         
/*     */         continue;
/*     */       } 
/* 300 */       if (this.l.peek() != ']') {
/* 301 */         throw e.createWithContext(this.l);
/*     */       }
/*     */     } 
/*     */     
/* 305 */     if (this.l.canRead()) {
/* 306 */       this.l.skip();
/* 307 */       this.G = k;
/*     */       
/*     */       return;
/*     */     } 
/* 311 */     throw e.createWithContext(this.l);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e() {
/* 316 */     this.l.skipWhitespace();
/* 317 */     if (this.l.canRead() && this.l.peek() == '!') {
/* 318 */       this.l.skip();
/* 319 */       this.l.skipWhitespace();
/* 320 */       return true;
/*     */     } 
/* 322 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f() {
/* 327 */     this.l.skipWhitespace();
/* 328 */     if (this.l.canRead() && this.l.peek() == '#') {
/* 329 */       this.l.skip();
/* 330 */       this.l.skipWhitespace();
/* 331 */       return true;
/*     */     } 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public StringReader g() {
/* 338 */     return this.l;
/*     */   }
/*     */   
/*     */   public void a(Predicate<Entity> predicate) {
/* 342 */     this.A = this.A.and(predicate);
/*     */   }
/*     */   
/*     */   public void h() {
/* 346 */     this.p = true;
/*     */   }
/*     */   
/*     */   public CriterionConditionValue.FloatRange i() {
/* 350 */     return this.q;
/*     */   }
/*     */   
/*     */   public void a(CriterionConditionValue.FloatRange criterionconditionvalue_floatrange) {
/* 354 */     this.q = criterionconditionvalue_floatrange;
/*     */   }
/*     */   
/*     */   public CriterionConditionValue.IntegerRange j() {
/* 358 */     return this.r;
/*     */   }
/*     */   
/*     */   public void a(CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
/* 362 */     this.r = criterionconditionvalue_integerrange;
/*     */   }
/*     */   
/*     */   public CriterionConditionRange k() {
/* 366 */     return this.y;
/*     */   }
/*     */   
/*     */   public void a(CriterionConditionRange criterionconditionrange) {
/* 370 */     this.y = criterionconditionrange;
/*     */   }
/*     */   
/*     */   public CriterionConditionRange l() {
/* 374 */     return this.z;
/*     */   }
/*     */   
/*     */   public void b(CriterionConditionRange criterionconditionrange) {
/* 378 */     this.z = criterionconditionrange;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double m() {
/* 383 */     return this.s;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double n() {
/* 388 */     return this.t;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double o() {
/* 393 */     return this.u;
/*     */   }
/*     */   
/*     */   public void a(double d0) {
/* 397 */     this.s = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   public void b(double d0) {
/* 401 */     this.t = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   public void c(double d0) {
/* 405 */     this.u = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   public void d(double d0) {
/* 409 */     this.v = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   public void e(double d0) {
/* 413 */     this.w = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   public void f(double d0) {
/* 417 */     this.x = Double.valueOf(d0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double p() {
/* 422 */     return this.v;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double q() {
/* 427 */     return this.w;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Double r() {
/* 432 */     return this.x;
/*     */   }
/*     */   
/*     */   public void a(int i) {
/* 436 */     this.n = i;
/*     */   }
/*     */   
/*     */   public void a(boolean flag) {
/* 440 */     this.o = flag;
/*     */   }
/*     */   
/*     */   public void a(BiConsumer<Vec3D, List<? extends Entity>> biconsumer) {
/* 444 */     this.B = biconsumer;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySelector parse() throws CommandSyntaxException {
/* 449 */     return parse(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySelector parse(boolean overridePermissions) throws CommandSyntaxException {
/* 454 */     this.E = this.l.getCursor();
/* 455 */     this.G = this::b;
/* 456 */     if (this.l.canRead() && this.l.peek() == '@') {
/* 457 */       if (!this.m) {
/* 458 */         throw c.createWithContext(this.l);
/*     */       }
/*     */       
/* 461 */       this.l.skip();
/* 462 */       parseSelector(overridePermissions);
/*     */     } else {
/* 464 */       c();
/*     */     } 
/*     */     
/* 467 */     I();
/* 468 */     return a();
/*     */   }
/*     */   
/*     */   private static void a(SuggestionsBuilder suggestionsbuilder) {
/* 472 */     suggestionsbuilder.suggest("@p", new ChatMessage("argument.entity.selector.nearestPlayer"));
/* 473 */     suggestionsbuilder.suggest("@a", new ChatMessage("argument.entity.selector.allPlayers"));
/* 474 */     suggestionsbuilder.suggest("@r", new ChatMessage("argument.entity.selector.randomPlayer"));
/* 475 */     suggestionsbuilder.suggest("@s", new ChatMessage("argument.entity.selector.self"));
/* 476 */     suggestionsbuilder.suggest("@e", new ChatMessage("argument.entity.selector.allEntities"));
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> b(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 480 */     consumer.accept(suggestionsbuilder);
/* 481 */     if (this.m) {
/* 482 */       a(suggestionsbuilder);
/*     */     }
/*     */     
/* 485 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> c(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 489 */     SuggestionsBuilder suggestionsbuilder1 = suggestionsbuilder.createOffset(this.E);
/*     */     
/* 491 */     consumer.accept(suggestionsbuilder1);
/* 492 */     return suggestionsbuilder.add(suggestionsbuilder1).buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> d(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 496 */     SuggestionsBuilder suggestionsbuilder1 = suggestionsbuilder.createOffset(suggestionsbuilder.getStart() - 1);
/*     */     
/* 498 */     a(suggestionsbuilder1);
/* 499 */     suggestionsbuilder.add(suggestionsbuilder1);
/* 500 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> e(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 504 */     suggestionsbuilder.suggest(String.valueOf('['));
/* 505 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> f(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 509 */     suggestionsbuilder.suggest(String.valueOf(']'));
/* 510 */     PlayerSelector.a(this, suggestionsbuilder);
/* 511 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> g(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 515 */     PlayerSelector.a(this, suggestionsbuilder);
/* 516 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> h(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 520 */     suggestionsbuilder.suggest(String.valueOf(','));
/* 521 */     suggestionsbuilder.suggest(String.valueOf(']'));
/* 522 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   public boolean u() {
/* 526 */     return this.C;
/*     */   }
/*     */   
/*     */   public void a(BiFunction<SuggestionsBuilder, Consumer<SuggestionsBuilder>, CompletableFuture<Suggestions>> bifunction) {
/* 530 */     this.G = bifunction;
/*     */   }
/*     */   
/*     */   public CompletableFuture<Suggestions> a(SuggestionsBuilder suggestionsbuilder, Consumer<SuggestionsBuilder> consumer) {
/* 534 */     return this.G.apply(suggestionsbuilder.createOffset(this.l.getCursor()), consumer);
/*     */   }
/*     */   
/*     */   public boolean v() {
/* 538 */     return this.H;
/*     */   }
/*     */   
/*     */   public void b(boolean flag) {
/* 542 */     this.H = flag;
/*     */   }
/*     */   
/*     */   public boolean w() {
/* 546 */     return this.I;
/*     */   }
/*     */   
/*     */   public void c(boolean flag) {
/* 550 */     this.I = flag;
/*     */   }
/*     */   
/*     */   public boolean x() {
/* 554 */     return this.J;
/*     */   }
/*     */   
/*     */   public void d(boolean flag) {
/* 558 */     this.J = flag;
/*     */   }
/*     */   
/*     */   public boolean y() {
/* 562 */     return this.K;
/*     */   }
/*     */   
/*     */   public void e(boolean flag) {
/* 566 */     this.K = flag;
/*     */   }
/*     */   
/*     */   public boolean z() {
/* 570 */     return this.L;
/*     */   }
/*     */   
/*     */   public void f(boolean flag) {
/* 574 */     this.L = flag;
/*     */   }
/*     */   
/*     */   public boolean A() {
/* 578 */     return this.M;
/*     */   }
/*     */   
/*     */   public void g(boolean flag) {
/* 582 */     this.M = flag;
/*     */   }
/*     */   
/*     */   public boolean B() {
/* 586 */     return this.N;
/*     */   }
/*     */   
/*     */   public void h(boolean flag) {
/* 590 */     this.N = flag;
/*     */   }
/*     */   
/*     */   public void i(boolean flag) {
/* 594 */     this.O = flag;
/*     */   }
/*     */   
/*     */   public void a(EntityTypes<?> entitytypes) {
/* 598 */     this.P = entitytypes;
/*     */   }
/*     */   
/*     */   public void D() {
/* 602 */     this.Q = true;
/*     */   }
/*     */   
/*     */   public boolean E() {
/* 606 */     return (this.P != null);
/*     */   }
/*     */   
/*     */   public boolean F() {
/* 610 */     return this.Q;
/*     */   }
/*     */   
/*     */   public boolean G() {
/* 614 */     return this.R;
/*     */   }
/*     */   
/*     */   public void j(boolean flag) {
/* 618 */     this.R = flag;
/*     */   }
/*     */   
/*     */   public boolean H() {
/* 622 */     return this.S;
/*     */   }
/*     */   
/*     */   public void k(boolean flag) {
/* 626 */     this.S = flag;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentParserSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */