/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.brigadier.ImmutableStringReader;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.Dynamic3CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ArgumentBlock {
/*  24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.block.tag.disallowed")); public static final DynamicCommandExceptionType b; public static final Dynamic2CommandExceptionType c; static {
/*  25 */     b = new DynamicCommandExceptionType(object -> new ChatMessage("argument.block.id.invalid", new Object[] { object }));
/*     */ 
/*     */     
/*  28 */     c = new Dynamic2CommandExceptionType((object, object1) -> new ChatMessage("argument.block.property.unknown", new Object[] { object, object1 }));
/*     */ 
/*     */     
/*  31 */     d = new Dynamic2CommandExceptionType((object, object1) -> new ChatMessage("argument.block.property.duplicate", new Object[] { object1, object }));
/*     */ 
/*     */     
/*  34 */     e = new Dynamic3CommandExceptionType((object, object1, object2) -> new ChatMessage("argument.block.property.invalid", new Object[] { object, object2, object1 }));
/*     */ 
/*     */     
/*  37 */     f = new Dynamic2CommandExceptionType((object, object1) -> new ChatMessage("argument.block.property.novalue", new Object[] { object, object1 }));
/*     */   }
/*     */   public static final Dynamic2CommandExceptionType d; public static final Dynamic3CommandExceptionType e; public static final Dynamic2CommandExceptionType f;
/*  40 */   public static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("argument.block.property.unclosed")); private static final BiFunction<SuggestionsBuilder, Tags<Block>, CompletableFuture<Suggestions>> h; private final StringReader i; private final boolean j; static {
/*  41 */     h = ((suggestionsbuilder, tags) -> suggestionsbuilder.buildFuture());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  46 */   private final Map<IBlockState<?>, Comparable<?>> k = Maps.newLinkedHashMap();
/*  47 */   private final Map<String, String> l = Maps.newHashMap();
/*  48 */   private MinecraftKey m = new MinecraftKey(""); private BlockStateList<Block, IBlockData> n; private IBlockData o; @Nullable private NBTTagCompound p; public final MinecraftKey getBlockKey() { return this.m; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private MinecraftKey q = new MinecraftKey("");
/*     */   private int r;
/*     */   private BiFunction<SuggestionsBuilder, Tags<Block>, CompletableFuture<Suggestions>> s;
/*     */   
/*     */   public ArgumentBlock(StringReader stringreader, boolean flag) {
/*  58 */     this.s = h;
/*  59 */     this.i = stringreader;
/*  60 */     this.j = flag;
/*     */   }
/*     */   
/*     */   public Map<IBlockState<?>, Comparable<?>> getStateMap() {
/*  64 */     return this.k;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getBlockData() {
/*  69 */     return this.o;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound c() {
/*  74 */     return this.p;
/*     */   } @Nullable
/*     */   public final MinecraftKey getTagKey() {
/*  77 */     return d();
/*     */   } @Nullable
/*     */   public MinecraftKey d() {
/*  80 */     return this.q;
/*     */   }
/*     */   public final ArgumentBlock parse(boolean parseTile) throws CommandSyntaxException {
/*  83 */     return a(parseTile);
/*     */   } public ArgumentBlock a(boolean flag) throws CommandSyntaxException {
/*  85 */     this.s = this::l;
/*  86 */     if (this.i.canRead() && this.i.peek() == '#') {
/*  87 */       f();
/*  88 */       this.s = this::i;
/*  89 */       if (this.i.canRead() && this.i.peek() == '[') {
/*  90 */         h();
/*  91 */         this.s = this::f;
/*     */       } 
/*     */     } else {
/*  94 */       e();
/*  95 */       this.s = this::j;
/*  96 */       if (this.i.canRead() && this.i.peek() == '[') {
/*  97 */         g();
/*  98 */         this.s = this::f;
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     if (flag && this.i.canRead() && this.i.peek() == '{') {
/* 103 */       this.s = h;
/* 104 */       i();
/*     */     } 
/*     */     
/* 107 */     return this;
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> b(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 111 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 112 */       suggestionsbuilder.suggest(String.valueOf(']'));
/*     */     }
/*     */     
/* 115 */     return d(suggestionsbuilder, tags);
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> c(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 119 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 120 */       suggestionsbuilder.suggest(String.valueOf(']'));
/*     */     }
/*     */     
/* 123 */     return e(suggestionsbuilder, tags);
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> d(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 127 */     String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);
/* 128 */     Iterator<IBlockState<?>> iterator = this.o.r().iterator();
/*     */     
/* 130 */     while (iterator.hasNext()) {
/* 131 */       IBlockState<?> iblockstate = iterator.next();
/*     */       
/* 133 */       if (!this.k.containsKey(iblockstate) && iblockstate.getName().startsWith(s)) {
/* 134 */         suggestionsbuilder.suggest(iblockstate.getName() + '=');
/*     */       }
/*     */     } 
/*     */     
/* 138 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> e(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 142 */     String s = suggestionsbuilder.getRemaining().toLowerCase(Locale.ROOT);
/*     */     
/* 144 */     if (this.q != null && !this.q.getKey().isEmpty()) {
/* 145 */       Tag<Block> tag = tags.a(this.q);
/*     */       
/* 147 */       if (tag != null) {
/* 148 */         Iterator<Block> iterator = tag.getTagged().iterator();
/*     */         
/* 150 */         while (iterator.hasNext()) {
/* 151 */           Block block = iterator.next();
/* 152 */           Iterator<IBlockState<?>> iterator1 = block.getStates().d().iterator();
/*     */           
/* 154 */           while (iterator1.hasNext()) {
/* 155 */             IBlockState<?> iblockstate = iterator1.next();
/*     */             
/* 157 */             if (!this.l.containsKey(iblockstate.getName()) && iblockstate.getName().startsWith(s)) {
/* 158 */               suggestionsbuilder.suggest(iblockstate.getName() + '=');
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> f(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 169 */     if (suggestionsbuilder.getRemaining().isEmpty() && a(tags)) {
/* 170 */       suggestionsbuilder.suggest(String.valueOf('{'));
/*     */     }
/*     */     
/* 173 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private boolean a(Tags<Block> tags) {
/* 177 */     if (this.o != null) {
/* 178 */       return this.o.getBlock().isTileEntity();
/*     */     }
/* 180 */     if (this.q != null) {
/* 181 */       Tag<Block> tag = tags.a(this.q);
/*     */       
/* 183 */       if (tag != null) {
/* 184 */         Iterator<Block> iterator = tag.getTagged().iterator();
/*     */         
/* 186 */         while (iterator.hasNext()) {
/* 187 */           Block block = iterator.next();
/*     */           
/* 189 */           if (block.isTileEntity()) {
/* 190 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 196 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private CompletableFuture<Suggestions> g(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 201 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 202 */       suggestionsbuilder.suggest(String.valueOf('='));
/*     */     }
/*     */     
/* 205 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> h(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 209 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 210 */       suggestionsbuilder.suggest(String.valueOf(']'));
/*     */     }
/*     */     
/* 213 */     if (suggestionsbuilder.getRemaining().isEmpty() && this.k.size() < this.o.r().size()) {
/* 214 */       suggestionsbuilder.suggest(String.valueOf(','));
/*     */     }
/*     */     
/* 217 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private static <T extends Comparable<T>> SuggestionsBuilder a(SuggestionsBuilder suggestionsbuilder, IBlockState<T> iblockstate) {
/* 221 */     Iterator<Comparable> iterator = iblockstate.getValues().iterator();
/*     */     
/* 223 */     while (iterator.hasNext()) {
/* 224 */       Comparable comparable = iterator.next();
/*     */       
/* 226 */       if (comparable instanceof Integer) {
/* 227 */         suggestionsbuilder.suggest(((Integer)comparable).intValue()); continue;
/*     */       } 
/* 229 */       suggestionsbuilder.suggest(iblockstate.a((T)comparable));
/*     */     } 
/*     */ 
/*     */     
/* 233 */     return suggestionsbuilder;
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> a(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags, String s) {
/* 237 */     boolean flag = false;
/*     */     
/* 239 */     if (this.q != null && !this.q.getKey().isEmpty()) {
/* 240 */       Tag<Block> tag = tags.a(this.q);
/*     */       
/* 242 */       if (tag != null) {
/* 243 */         Iterator<Block> iterator = tag.getTagged().iterator();
/*     */         
/* 245 */         while (iterator.hasNext()) {
/* 246 */           Block block = iterator.next();
/* 247 */           IBlockState<?> iblockstate = block.getStates().a(s);
/*     */           
/* 249 */           if (iblockstate != null) {
/* 250 */             a(suggestionsbuilder, iblockstate);
/*     */           }
/*     */           
/* 253 */           if (!flag) {
/* 254 */             Iterator<IBlockState<?>> iterator1 = block.getStates().d().iterator();
/*     */             
/* 256 */             while (iterator1.hasNext()) {
/* 257 */               IBlockState<?> iblockstate1 = iterator1.next();
/*     */               
/* 259 */               if (!this.l.containsKey(iblockstate1.getName())) {
/* 260 */                 flag = true;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 269 */     if (flag) {
/* 270 */       suggestionsbuilder.suggest(String.valueOf(','));
/*     */     }
/*     */     
/* 273 */     suggestionsbuilder.suggest(String.valueOf(']'));
/* 274 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> i(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 278 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 279 */       Tag<Block> tag = tags.a(this.q);
/*     */       
/* 281 */       if (tag != null) {
/* 282 */         int i; boolean flag = false;
/* 283 */         boolean flag1 = false;
/* 284 */         Iterator<Block> iterator = tag.getTagged().iterator();
/*     */         
/* 286 */         while (iterator.hasNext()) {
/* 287 */           Block block = iterator.next();
/*     */           
/* 289 */           i = flag | (!block.getStates().d().isEmpty() ? 1 : 0);
/* 290 */           flag1 |= block.isTileEntity();
/* 291 */           if (i != 0 && flag1) {
/*     */             break;
/*     */           }
/*     */         } 
/*     */         
/* 296 */         if (i != 0) {
/* 297 */           suggestionsbuilder.suggest(String.valueOf('['));
/*     */         }
/*     */         
/* 300 */         if (flag1) {
/* 301 */           suggestionsbuilder.suggest(String.valueOf('{'));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 306 */     return k(suggestionsbuilder, tags);
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> j(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 310 */     if (suggestionsbuilder.getRemaining().isEmpty()) {
/* 311 */       if (!this.o.getBlock().getStates().d().isEmpty()) {
/* 312 */         suggestionsbuilder.suggest(String.valueOf('['));
/*     */       }
/*     */       
/* 315 */       if (this.o.getBlock().isTileEntity()) {
/* 316 */         suggestionsbuilder.suggest(String.valueOf('{'));
/*     */       }
/*     */     } 
/*     */     
/* 320 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> k(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 324 */     return ICompletionProvider.a(tags.b(), suggestionsbuilder.createOffset(this.r).add(suggestionsbuilder));
/*     */   }
/*     */   
/*     */   private CompletableFuture<Suggestions> l(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 328 */     if (this.j) {
/* 329 */       ICompletionProvider.a(tags.b(), suggestionsbuilder, String.valueOf('#'));
/*     */     }
/*     */     
/* 332 */     ICompletionProvider.a(IRegistry.BLOCK.keySet(), suggestionsbuilder);
/* 333 */     return suggestionsbuilder.buildFuture();
/*     */   }
/*     */   
/*     */   public void e() throws CommandSyntaxException {
/* 337 */     int i = this.i.getCursor();
/*     */     
/* 339 */     this.m = MinecraftKey.a(this.i);
/* 340 */     Block block = (Block)IRegistry.BLOCK.getOptional(this.m).orElseThrow(() -> {
/*     */           this.i.setCursor(i);
/*     */           
/*     */           return b.createWithContext((ImmutableStringReader)this.i, this.m.toString());
/*     */         });
/* 345 */     this.n = block.getStates();
/* 346 */     this.o = block.getBlockData();
/*     */   }
/*     */   
/*     */   public void f() throws CommandSyntaxException {
/* 350 */     if (!this.j) {
/* 351 */       throw a.create();
/*     */     }
/* 353 */     this.s = this::k;
/* 354 */     this.i.expect('#');
/* 355 */     this.r = this.i.getCursor();
/* 356 */     this.q = MinecraftKey.a(this.i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void g() throws CommandSyntaxException {
/* 361 */     this.i.skip();
/* 362 */     this.s = this::b;
/* 363 */     this.i.skipWhitespace();
/*     */ 
/*     */     
/* 366 */     while (this.i.canRead() && this.i.peek() != ']') {
/* 367 */       this.i.skipWhitespace();
/* 368 */       int i = this.i.getCursor();
/* 369 */       String s = this.i.readString();
/* 370 */       IBlockState<?> iblockstate = this.n.a(s);
/*     */       
/* 372 */       if (iblockstate == null) {
/* 373 */         this.i.setCursor(i);
/* 374 */         throw c.createWithContext(this.i, this.m.toString(), s);
/*     */       } 
/*     */       
/* 377 */       if (this.k.containsKey(iblockstate)) {
/* 378 */         this.i.setCursor(i);
/* 379 */         throw d.createWithContext(this.i, this.m.toString(), s);
/*     */       } 
/*     */       
/* 382 */       this.i.skipWhitespace();
/* 383 */       this.s = this::g;
/* 384 */       if (!this.i.canRead() || this.i.peek() != '=') {
/* 385 */         throw f.createWithContext(this.i, this.m.toString(), s);
/*     */       }
/*     */       
/* 388 */       this.i.skip();
/* 389 */       this.i.skipWhitespace();
/* 390 */       this.s = ((suggestionsbuilder, tags) -> a(suggestionsbuilder, iblockstate).buildFuture());
/*     */ 
/*     */       
/* 393 */       int j = this.i.getCursor();
/*     */       
/* 395 */       a(iblockstate, this.i.readString(), j);
/* 396 */       this.s = this::h;
/* 397 */       this.i.skipWhitespace();
/* 398 */       if (!this.i.canRead()) {
/*     */         continue;
/*     */       }
/*     */       
/* 402 */       if (this.i.peek() == ',') {
/* 403 */         this.i.skip();
/* 404 */         this.s = this::d;
/*     */         
/*     */         continue;
/*     */       } 
/* 408 */       if (this.i.peek() != ']') {
/* 409 */         throw g.createWithContext(this.i);
/*     */       }
/*     */     } 
/*     */     
/* 413 */     if (this.i.canRead()) {
/* 414 */       this.i.skip();
/*     */       
/*     */       return;
/*     */     } 
/* 418 */     throw g.createWithContext(this.i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void h() throws CommandSyntaxException {
/* 423 */     this.i.skip();
/* 424 */     this.s = this::c;
/* 425 */     int i = -1;
/*     */     
/* 427 */     this.i.skipWhitespace();
/*     */ 
/*     */     
/* 430 */     while (this.i.canRead() && this.i.peek() != ']') {
/* 431 */       this.i.skipWhitespace();
/* 432 */       int j = this.i.getCursor();
/* 433 */       String s = this.i.readString();
/*     */       
/* 435 */       if (this.l.containsKey(s)) {
/* 436 */         this.i.setCursor(j);
/* 437 */         throw d.createWithContext(this.i, this.m.toString(), s);
/*     */       } 
/*     */       
/* 440 */       this.i.skipWhitespace();
/* 441 */       if (!this.i.canRead() || this.i.peek() != '=') {
/* 442 */         this.i.setCursor(j);
/* 443 */         throw f.createWithContext(this.i, this.m.toString(), s);
/*     */       } 
/*     */       
/* 446 */       this.i.skip();
/* 447 */       this.i.skipWhitespace();
/* 448 */       this.s = ((suggestionsbuilder, tags) -> a(suggestionsbuilder, tags, s));
/*     */ 
/*     */       
/* 451 */       i = this.i.getCursor();
/* 452 */       String s1 = this.i.readString();
/*     */       
/* 454 */       this.l.put(s, s1);
/* 455 */       this.i.skipWhitespace();
/* 456 */       if (!this.i.canRead()) {
/*     */         continue;
/*     */       }
/*     */       
/* 460 */       i = -1;
/* 461 */       if (this.i.peek() == ',') {
/* 462 */         this.i.skip();
/* 463 */         this.s = this::e;
/*     */         
/*     */         continue;
/*     */       } 
/* 467 */       if (this.i.peek() != ']') {
/* 468 */         throw g.createWithContext(this.i);
/*     */       }
/*     */     } 
/*     */     
/* 472 */     if (this.i.canRead()) {
/* 473 */       this.i.skip();
/*     */       
/*     */       return;
/*     */     } 
/* 477 */     if (i >= 0) {
/* 478 */       this.i.setCursor(i);
/*     */     }
/*     */     
/* 481 */     throw g.createWithContext(this.i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void i() throws CommandSyntaxException {
/* 486 */     this.p = (new MojangsonParser(this.i)).f();
/*     */   }
/*     */   
/*     */   private <T extends Comparable<T>> void a(IBlockState<T> iblockstate, String s, int i) throws CommandSyntaxException {
/* 490 */     Optional<T> optional = iblockstate.b(s);
/*     */     
/* 492 */     if (optional.isPresent()) {
/* 493 */       this.o = this.o.set(iblockstate, (Comparable)optional.get());
/* 494 */       this.k.put(iblockstate, (Comparable)optional.get());
/*     */     } else {
/* 496 */       this.i.setCursor(i);
/* 497 */       throw e.createWithContext(this.i, this.m.toString(), iblockstate.getName(), s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String a(IBlockData iblockdata) {
/* 502 */     StringBuilder stringbuilder = new StringBuilder(IRegistry.BLOCK.getKey(iblockdata.getBlock()).toString());
/*     */     
/* 504 */     if (!iblockdata.r().isEmpty()) {
/* 505 */       stringbuilder.append('[');
/* 506 */       boolean flag = false;
/*     */       
/* 508 */       for (UnmodifiableIterator unmodifiableiterator = iblockdata.getStateMap().entrySet().iterator(); unmodifiableiterator.hasNext(); flag = true) {
/* 509 */         Map.Entry<IBlockState<?>, Comparable<?>> entry = (Map.Entry<IBlockState<?>, Comparable<?>>)unmodifiableiterator.next();
/*     */         
/* 511 */         if (flag) {
/* 512 */           stringbuilder.append(',');
/*     */         }
/*     */         
/* 515 */         a(stringbuilder, (IBlockState<Comparable>)entry.getKey(), entry.getValue());
/*     */       } 
/*     */       
/* 518 */       stringbuilder.append(']');
/*     */     } 
/*     */     
/* 521 */     return stringbuilder.toString();
/*     */   }
/*     */   
/*     */   private static <T extends Comparable<T>> void a(StringBuilder stringbuilder, IBlockState<T> iblockstate, Comparable<?> comparable) {
/* 525 */     stringbuilder.append(iblockstate.getName());
/* 526 */     stringbuilder.append('=');
/* 527 */     stringbuilder.append(iblockstate.a((T)comparable));
/*     */   }
/*     */   
/*     */   public CompletableFuture<Suggestions> a(SuggestionsBuilder suggestionsbuilder, Tags<Block> tags) {
/* 531 */     return this.s.apply(suggestionsbuilder.createOffset(this.i.getCursor()), tags);
/*     */   }
/*     */   
/*     */   public Map<String, String> j() {
/* 535 */     return this.l;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */