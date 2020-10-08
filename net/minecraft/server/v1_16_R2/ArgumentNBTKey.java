/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentNBTKey
/*     */   implements ArgumentType<ArgumentNBTKey.h>
/*     */ {
/*  32 */   private static final Collection<String> c = Arrays.asList(new String[] { "foo", "foo.bar", "foo[0]", "[0]", "[]", "{foo=bar}" });
/*  33 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("arguments.nbtpath.node.invalid")); public static final DynamicCommandExceptionType b; static {
/*  34 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.nbtpath.nothing_found", new Object[] { var0 }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArgumentNBTKey a() {
/*  42 */     return new ArgumentNBTKey();
/*     */   }
/*     */   
/*     */   public static h a(CommandContext<CommandListenerWrapper> var0, String var1) {
/*  46 */     return (h)var0.getArgument(var1, h.class);
/*     */   }
/*     */ 
/*     */   
/*     */   public h parse(StringReader var0) throws CommandSyntaxException {
/*  51 */     List<i> var1 = Lists.newArrayList();
/*  52 */     int var2 = var0.getCursor();
/*     */     
/*  54 */     Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap();
/*  55 */     boolean var4 = true;
/*  56 */     while (var0.canRead() && var0.peek() != ' ') {
/*  57 */       i var5 = a(var0, var4);
/*  58 */       var1.add(var5);
/*  59 */       object2IntOpenHashMap.put(var5, var0.getCursor() - var2);
/*  60 */       var4 = false;
/*  61 */       if (var0.canRead()) {
/*  62 */         char var6 = var0.peek();
/*  63 */         if (var6 != ' ' && var6 != '[' && var6 != '{') {
/*  64 */           var0.expect('.');
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     return new h(var0.getString().substring(var2, var0.getCursor()), var1.<i>toArray(new i[0]), (Object2IntMap<i>)object2IntOpenHashMap);
/*     */   } private static i a(StringReader var0, boolean var1) throws CommandSyntaxException {
/*     */     NBTTagCompound nBTTagCompound;
/*     */     int i, var3;
/*  73 */     switch (var0.peek()) {
/*     */       case '{':
/*  75 */         if (!var1) {
/*  76 */           throw a.createWithContext(var0);
/*     */         }
/*  78 */         nBTTagCompound = (new MojangsonParser(var0)).f();
/*  79 */         return new g(nBTTagCompound);
/*     */       
/*     */       case '[':
/*  82 */         var0.skip();
/*  83 */         i = var0.peek();
/*  84 */         if (i == 123) {
/*  85 */           NBTTagCompound nBTTagCompound1 = (new MojangsonParser(var0)).f();
/*  86 */           var0.expect(']');
/*  87 */           return new e(nBTTagCompound1);
/*  88 */         }  if (i == 93) {
/*  89 */           var0.skip();
/*  90 */           return a.a;
/*     */         } 
/*     */         
/*  93 */         var3 = var0.readInt();
/*  94 */         var0.expect(']');
/*  95 */         return new c(var3);
/*     */       
/*     */       case '"':
/*  98 */         var2 = var0.readString();
/*  99 */         return a(var0, var2);
/*     */     } 
/*     */     
/* 102 */     String var2 = b(var0);
/* 103 */     return a(var0, var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static i a(StringReader var0, String var1) throws CommandSyntaxException {
/* 109 */     if (var0.canRead() && var0.peek() == '{') {
/* 110 */       NBTTagCompound var2 = (new MojangsonParser(var0)).f();
/* 111 */       return new f(var1, var2);
/*     */     } 
/* 113 */     return new b(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String b(StringReader var0) throws CommandSyntaxException {
/* 118 */     int var1 = var0.getCursor();
/* 119 */     while (var0.canRead() && a(var0.peek())) {
/* 120 */       var0.skip();
/*     */     }
/* 122 */     if (var0.getCursor() == var1) {
/* 123 */       throw a.createWithContext(var0);
/*     */     }
/* 125 */     return var0.getString().substring(var1, var0.getCursor());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/* 130 */     return c;
/*     */   }
/*     */   
/*     */   private static boolean a(char var0) {
/* 134 */     return (var0 != ' ' && var0 != '"' && var0 != '[' && var0 != ']' && var0 != '.' && var0 != '{' && var0 != '}');
/*     */   }
/*     */   
/*     */   public static class h {
/*     */     private final String a;
/*     */     private final Object2IntMap<ArgumentNBTKey.i> b;
/*     */     private final ArgumentNBTKey.i[] c;
/*     */     
/*     */     public h(String var0, ArgumentNBTKey.i[] var1, Object2IntMap<ArgumentNBTKey.i> var2) {
/* 143 */       this.a = var0;
/* 144 */       this.c = var1;
/* 145 */       this.b = var2;
/*     */     }
/*     */     
/*     */     public List<NBTBase> a(NBTBase var0) throws CommandSyntaxException {
/* 149 */       List<NBTBase> var1 = Collections.singletonList(var0);
/* 150 */       for (ArgumentNBTKey.i var5 : this.c) {
/* 151 */         var1 = var5.a(var1);
/* 152 */         if (var1.isEmpty()) {
/* 153 */           throw a(var5);
/*     */         }
/*     */       } 
/* 156 */       return var1;
/*     */     }
/*     */     
/*     */     public int b(NBTBase var0) {
/* 160 */       List<NBTBase> var1 = Collections.singletonList(var0);
/* 161 */       for (ArgumentNBTKey.i var5 : this.c) {
/* 162 */         var1 = var5.a(var1);
/* 163 */         if (var1.isEmpty()) {
/* 164 */           return 0;
/*     */         }
/*     */       } 
/* 167 */       return var1.size();
/*     */     }
/*     */     
/*     */     private List<NBTBase> d(NBTBase var0) throws CommandSyntaxException {
/* 171 */       List<NBTBase> var1 = Collections.singletonList(var0);
/*     */       
/* 173 */       for (int var2 = 0; var2 < this.c.length - 1; var2++) {
/* 174 */         ArgumentNBTKey.i var3 = this.c[var2];
/* 175 */         int var4 = var2 + 1;
/* 176 */         var1 = var3.a(var1, this.c[var4]::a);
/* 177 */         if (var1.isEmpty()) {
/* 178 */           throw a(var3);
/*     */         }
/*     */       } 
/* 181 */       return var1;
/*     */     }
/*     */     
/*     */     public List<NBTBase> a(NBTBase var0, Supplier<NBTBase> var1) throws CommandSyntaxException {
/* 185 */       List<NBTBase> var2 = d(var0);
/*     */       
/* 187 */       ArgumentNBTKey.i var3 = this.c[this.c.length - 1];
/* 188 */       return var3.a(var2, var1);
/*     */     }
/*     */     
/*     */     private static int a(List<NBTBase> var0, Function<NBTBase, Integer> var1) {
/* 192 */       return ((Integer)var0.stream().<Integer>map(var1).reduce(Integer.valueOf(0), (var0, var1) -> Integer.valueOf(var0.intValue() + var1.intValue()))).intValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int b(NBTBase var0, Supplier<NBTBase> var1) throws CommandSyntaxException {
/* 200 */       List<NBTBase> var2 = d(var0);
/*     */       
/* 202 */       ArgumentNBTKey.i var3 = this.c[this.c.length - 1];
/* 203 */       return a(var2, var2 -> Integer.valueOf(var0.a(var2, var1)));
/*     */     }
/*     */     
/*     */     public int c(NBTBase var0) {
/* 207 */       List<NBTBase> var1 = Collections.singletonList(var0);
/*     */       
/* 209 */       for (int j = 0; j < this.c.length - 1; j++) {
/* 210 */         var1 = this.c[j].a(var1);
/*     */       }
/*     */       
/* 213 */       ArgumentNBTKey.i var2 = this.c[this.c.length - 1];
/* 214 */       return a(var1, var2::a);
/*     */     }
/*     */     
/*     */     private CommandSyntaxException a(ArgumentNBTKey.i var0) {
/* 218 */       int var1 = this.b.getInt(var0);
/* 219 */       return ArgumentNBTKey.b.create(this.a.substring(0, var1));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 224 */       return this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   private static Predicate<NBTBase> b(NBTTagCompound var0) {
/* 229 */     return var1 -> GameProfileSerializer.a(var0, var1, true);
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
/*     */   static interface i
/*     */   {
/*     */     default List<NBTBase> a(List<NBTBase> var0) {
/* 244 */       return a(var0, this::a);
/*     */     }
/*     */     
/*     */     default List<NBTBase> a(List<NBTBase> var0, Supplier<NBTBase> var1) {
/* 248 */       return a(var0, (var1, var2) -> a(var1, var0, var2));
/*     */     } void a(NBTBase param1NBTBase, List<NBTBase> param1List); void a(NBTBase param1NBTBase, Supplier<NBTBase> param1Supplier, List<NBTBase> param1List);
/*     */     NBTBase a();
/*     */     default List<NBTBase> a(List<NBTBase> var0, BiConsumer<NBTBase, List<NBTBase>> var1) {
/* 252 */       List<NBTBase> var2 = Lists.newArrayList();
/*     */       
/* 254 */       for (NBTBase var4 : var0) {
/* 255 */         var1.accept(var4, var2);
/*     */       }
/*     */       
/* 258 */       return var2;
/*     */     }
/*     */     
/*     */     int a(NBTBase param1NBTBase, Supplier<NBTBase> param1Supplier);
/*     */     
/*     */     int a(NBTBase param1NBTBase); }
/*     */   
/*     */   static class b implements i { public b(String var0) {
/* 266 */       this.a = var0;
/*     */     }
/*     */     private final String a;
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 271 */       if (var0 instanceof NBTTagCompound) {
/* 272 */         NBTBase var2 = ((NBTTagCompound)var0).get(this.a);
/* 273 */         if (var2 != null) {
/* 274 */           var1.add(var2);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 281 */       if (var0 instanceof NBTTagCompound) {
/* 282 */         NBTBase var4; NBTTagCompound var3 = (NBTTagCompound)var0;
/*     */         
/* 284 */         if (var3.hasKey(this.a)) {
/* 285 */           var4 = var3.get(this.a);
/*     */         } else {
/* 287 */           var4 = var1.get();
/* 288 */           var3.set(this.a, var4);
/*     */         } 
/*     */         
/* 291 */         var2.add(var4);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 297 */       return new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 302 */       if (var0 instanceof NBTTagCompound) {
/* 303 */         NBTTagCompound var2 = (NBTTagCompound)var0;
/* 304 */         NBTBase var3 = var1.get();
/* 305 */         NBTBase var4 = var2.set(this.a, var3);
/* 306 */         if (!var3.equals(var4)) {
/* 307 */           return 1;
/*     */         }
/*     */       } 
/*     */       
/* 311 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 316 */       if (var0 instanceof NBTTagCompound) {
/* 317 */         NBTTagCompound var1 = (NBTTagCompound)var0;
/* 318 */         if (var1.hasKey(this.a)) {
/* 319 */           var1.remove(this.a);
/* 320 */           return 1;
/*     */         } 
/*     */       } 
/*     */       
/* 324 */       return 0;
/*     */     } }
/*     */ 
/*     */   
/*     */   static class c implements i {
/*     */     private final int a;
/*     */     
/*     */     public c(int var0) {
/* 332 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 337 */       if (var0 instanceof NBTList) {
/* 338 */         NBTList<?> var2 = (NBTList)var0;
/*     */         
/* 340 */         int var3 = var2.size();
/* 341 */         int var4 = (this.a < 0) ? (var3 + this.a) : this.a;
/*     */         
/* 343 */         if (0 <= var4 && var4 < var3) {
/* 344 */           var1.add((NBTBase)var2.get(var4));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 351 */       a(var0, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 356 */       return new NBTTagList();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 361 */       if (var0 instanceof NBTList) {
/* 362 */         NBTList<?> var2 = (NBTList)var0;
/* 363 */         int var3 = var2.size();
/* 364 */         int var4 = (this.a < 0) ? (var3 + this.a) : this.a;
/*     */         
/* 366 */         if (0 <= var4 && var4 < var3) {
/* 367 */           NBTBase var5 = (NBTBase)var2.get(var4);
/* 368 */           NBTBase var6 = var1.get();
/* 369 */           if (!var6.equals(var5) && var2.a(var4, var6)) {
/* 370 */             return 1;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 375 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 380 */       if (var0 instanceof NBTList) {
/* 381 */         NBTList<?> var1 = (NBTList)var0;
/* 382 */         int var2 = var1.size();
/* 383 */         int var3 = (this.a < 0) ? (var2 + this.a) : this.a;
/*     */         
/* 385 */         if (0 <= var3 && var3 < var2) {
/* 386 */           var1.remove(var3);
/* 387 */           return 1;
/*     */         } 
/*     */       } 
/*     */       
/* 391 */       return 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class e
/*     */     implements i
/*     */   {
/*     */     private final NBTTagCompound a;
/*     */     
/*     */     private final Predicate<NBTBase> b;
/*     */     
/*     */     public e(NBTTagCompound var0) {
/* 404 */       this.a = var0;
/* 405 */       this.b = ArgumentNBTKey.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 410 */       if (var0 instanceof NBTTagList) {
/* 411 */         NBTTagList var2 = (NBTTagList)var0;
/* 412 */         var2.stream().filter(this.b).forEach(var1::add);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 418 */       MutableBoolean var3 = new MutableBoolean();
/* 419 */       if (var0 instanceof NBTTagList) {
/* 420 */         NBTTagList var4 = (NBTTagList)var0;
/* 421 */         var4.stream().filter(this.b).forEach(var2 -> {
/*     */               var0.add(var2);
/*     */               
/*     */               var1.setTrue();
/*     */             });
/* 426 */         if (var3.isFalse()) {
/* 427 */           NBTTagCompound var5 = this.a.clone();
/* 428 */           var4.add(var5);
/* 429 */           var2.add(var5);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 436 */       return new NBTTagList();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 441 */       int var2 = 0;
/* 442 */       if (var0 instanceof NBTTagList) {
/* 443 */         NBTTagList var3 = (NBTTagList)var0;
/* 444 */         int var4 = var3.size();
/* 445 */         if (var4 == 0) {
/* 446 */           var3.add(var1.get());
/* 447 */           var2++;
/*     */         } else {
/* 449 */           for (int var5 = 0; var5 < var4; var5++) {
/* 450 */             NBTBase var6 = var3.get(var5);
/* 451 */             if (this.b.test(var6)) {
/* 452 */               NBTBase var7 = var1.get();
/* 453 */               if (!var7.equals(var6) && var3.a(var5, var7)) {
/* 454 */                 var2++;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 461 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 466 */       int var1 = 0;
/* 467 */       if (var0 instanceof NBTTagList) {
/* 468 */         NBTTagList var2 = (NBTTagList)var0;
/* 469 */         for (int var3 = var2.size() - 1; var3 >= 0; var3--) {
/* 470 */           if (this.b.test(var2.get(var3))) {
/* 471 */             var2.remove(var3);
/* 472 */             var1++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 477 */       return var1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */     implements i
/*     */   {
/* 485 */     public static final a a = new a();
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 489 */       if (var0 instanceof NBTList) {
/* 490 */         var1.addAll((NBTList)var0);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 496 */       if (var0 instanceof NBTList) {
/* 497 */         NBTList<?> var3 = (NBTList)var0;
/* 498 */         if (var3.isEmpty()) {
/* 499 */           NBTBase var4 = var1.get();
/* 500 */           if (var3.b(0, var4)) {
/* 501 */             var2.add(var4);
/*     */           }
/*     */         } else {
/* 504 */           var2.addAll(var3);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 511 */       return new NBTTagList();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 516 */       if (var0 instanceof NBTList) {
/* 517 */         NBTList<?> var2 = (NBTList)var0;
/* 518 */         int var3 = var2.size();
/* 519 */         if (var3 == 0) {
/* 520 */           var2.b(0, var1.get());
/* 521 */           return 1;
/*     */         } 
/* 523 */         NBTBase var4 = var1.get();
/* 524 */         int var5 = var3 - (int)var2.stream().filter(var4::equals).count();
/* 525 */         if (var5 == 0) {
/* 526 */           return 0;
/*     */         }
/* 528 */         var2.clear();
/* 529 */         if (!var2.b(0, var4)) {
/* 530 */           return 0;
/*     */         }
/* 532 */         for (int var6 = 1; var6 < var3; var6++) {
/* 533 */           var2.b(var6, var1.get());
/*     */         }
/*     */         
/* 536 */         return var5;
/*     */       } 
/*     */       
/* 539 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 544 */       if (var0 instanceof NBTList) {
/* 545 */         NBTList<?> var1 = (NBTList)var0;
/* 546 */         int var2 = var1.size();
/* 547 */         if (var2 > 0) {
/* 548 */           var1.clear();
/* 549 */           return var2;
/*     */         } 
/*     */       } 
/*     */       
/* 553 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   static class f implements i {
/*     */     private final String a;
/*     */     private final NBTTagCompound b;
/*     */     private final Predicate<NBTBase> c;
/*     */     
/*     */     public f(String var0, NBTTagCompound var1) {
/* 563 */       this.a = var0;
/* 564 */       this.b = var1;
/* 565 */       this.c = ArgumentNBTKey.a(var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 570 */       if (var0 instanceof NBTTagCompound) {
/* 571 */         NBTBase var2 = ((NBTTagCompound)var0).get(this.a);
/* 572 */         if (this.c.test(var2)) {
/* 573 */           var1.add(var2);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 580 */       if (var0 instanceof NBTTagCompound) {
/* 581 */         NBTTagCompound var3 = (NBTTagCompound)var0;
/* 582 */         NBTBase var4 = var3.get(this.a);
/* 583 */         if (var4 == null) {
/* 584 */           var4 = this.b.clone();
/* 585 */           var3.set(this.a, var4);
/* 586 */           var2.add(var4);
/* 587 */         } else if (this.c.test(var4)) {
/* 588 */           var2.add(var4);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 595 */       return new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 600 */       if (var0 instanceof NBTTagCompound) {
/* 601 */         NBTTagCompound var2 = (NBTTagCompound)var0;
/* 602 */         NBTBase var3 = var2.get(this.a);
/* 603 */         if (this.c.test(var3)) {
/* 604 */           NBTBase var4 = var1.get();
/* 605 */           if (!var4.equals(var3)) {
/* 606 */             var2.set(this.a, var4);
/* 607 */             return 1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 612 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 617 */       if (var0 instanceof NBTTagCompound) {
/* 618 */         NBTTagCompound var1 = (NBTTagCompound)var0;
/* 619 */         NBTBase var2 = var1.get(this.a);
/* 620 */         if (this.c.test(var2)) {
/* 621 */           var1.remove(this.a);
/* 622 */           return 1;
/*     */         } 
/*     */       } 
/*     */       
/* 626 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   static class g implements i {
/*     */     private final Predicate<NBTBase> a;
/*     */     
/*     */     public g(NBTTagCompound var0) {
/* 634 */       this.a = ArgumentNBTKey.a(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, List<NBTBase> var1) {
/* 639 */       if (var0 instanceof NBTTagCompound && this.a.test(var0)) {
/* 640 */         var1.add(var0);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(NBTBase var0, Supplier<NBTBase> var1, List<NBTBase> var2) {
/* 646 */       a(var0, var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public NBTBase a() {
/* 651 */       return new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0, Supplier<NBTBase> var1) {
/* 656 */       return 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(NBTBase var0) {
/* 661 */       return 0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentNBTKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */