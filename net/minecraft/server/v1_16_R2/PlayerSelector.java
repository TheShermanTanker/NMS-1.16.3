/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.ImmutableStringReader;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
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
/*     */ public class PlayerSelector
/*     */ {
/*  53 */   private static final Map<String, b> i = Maps.newHashMap(); public static final DynamicCommandExceptionType a; public static final DynamicCommandExceptionType b;
/*     */   static {
/*  55 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.entity.options.unknown", new Object[] { var0 }));
/*  56 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.entity.options.inapplicable", new Object[] { var0 }));
/*  57 */   } public static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.entity.options.distance.negative"));
/*  58 */   public static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("argument.entity.options.level.negative"));
/*  59 */   public static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("argument.entity.options.limit.toosmall")); public static final DynamicCommandExceptionType f; public static final DynamicCommandExceptionType g; public static final DynamicCommandExceptionType h; static {
/*  60 */     f = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.entity.options.sort.irreversible", new Object[] { var0 }));
/*  61 */     g = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.entity.options.mode.invalid", new Object[] { var0 }));
/*  62 */     h = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.entity.options.type.invalid", new Object[] { var0 }));
/*     */   }
/*     */   private static void a(String var0, a var1, Predicate<ArgumentParserSelector> var2, IChatBaseComponent var3) {
/*  65 */     i.put(var0, new b(var1, var2, var3));
/*     */   }
/*     */   
/*     */   public static void a() {
/*  69 */     if (!i.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  73 */     a("name", var0 -> { int var1 = var0.g().getCursor(); boolean var2 = var0.e(); String var3 = var0.g().readString(); if (var0.w() && !var2) { var0.g().setCursor(var1); throw b.createWithContext(var0.g(), "name"); }  if (var2) { var0.c(true); } else { var0.b(true); }  var0.a(()); }var0 -> !var0.v(), new ChatMessage("argument.entity.options.name.description"));
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
/*  89 */     a("distance", var0 -> { int var1 = var0.g().getCursor(); CriterionConditionValue.FloatRange var2 = CriterionConditionValue.FloatRange.a(var0.g()); if ((var2.a() != null && var2.a().floatValue() < 0.0F) || (var2.b() != null && var2.b().floatValue() < 0.0F)) { var0.g().setCursor(var1); throw c.createWithContext(var0.g()); }  var0.a(var2); var0.h(); }var0 -> var0.i().c(), new ChatMessage("argument.entity.options.distance.description"));
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
/* 100 */     a("level", var0 -> { int var1 = var0.g().getCursor(); CriterionConditionValue.IntegerRange var2 = CriterionConditionValue.IntegerRange.a(var0.g()); if ((var2.a() != null && var2.a().intValue() < 0) || (var2.b() != null && var2.b().intValue() < 0)) { var0.g().setCursor(var1); throw d.createWithContext(var0.g()); }  var0.a(var2); var0.a(false); }var0 -> var0.j().c(), new ChatMessage("argument.entity.options.level.description"));
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
/* 111 */     a("x", var0 -> { var0.h(); var0.a(var0.g().readDouble()); }var0 -> (var0.m() == null), new ChatMessage("argument.entity.options.x.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     a("y", var0 -> { var0.h(); var0.b(var0.g().readDouble()); }var0 -> (var0.n() == null), new ChatMessage("argument.entity.options.y.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     a("z", var0 -> { var0.h(); var0.c(var0.g().readDouble()); }var0 -> (var0.o() == null), new ChatMessage("argument.entity.options.z.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     a("dx", var0 -> { var0.h(); var0.d(var0.g().readDouble()); }var0 -> (var0.p() == null), new ChatMessage("argument.entity.options.dx.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 131 */     a("dy", var0 -> { var0.h(); var0.e(var0.g().readDouble()); }var0 -> (var0.q() == null), new ChatMessage("argument.entity.options.dy.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     a("dz", var0 -> { var0.h(); var0.f(var0.g().readDouble()); }var0 -> (var0.r() == null), new ChatMessage("argument.entity.options.dz.description"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 141 */     a("x_rotation", var0 -> var0.a(CriterionConditionRange.a(var0.g(), true, MathHelper::g)), var0 -> (var0.k() == CriterionConditionRange.a), new ChatMessage("argument.entity.options.x_rotation.description"));
/*     */ 
/*     */ 
/*     */     
/* 145 */     a("y_rotation", var0 -> var0.b(CriterionConditionRange.a(var0.g(), true, MathHelper::g)), var0 -> (var0.l() == CriterionConditionRange.a), new ChatMessage("argument.entity.options.y_rotation.description"));
/*     */ 
/*     */ 
/*     */     
/* 149 */     a("limit", var0 -> {
/*     */           int var1 = var0.g().getCursor();
/*     */           int var2 = var0.g().readInt();
/*     */           if (var2 < 1) {
/*     */             var0.g().setCursor(var1);
/*     */             throw e.createWithContext(var0.g());
/*     */           } 
/*     */           var0.a(var2);
/*     */           var0.d(true);
/* 158 */         }var0 -> (!var0.u() && !var0.x()), new ChatMessage("argument.entity.options.limit.description"));
/*     */     
/* 160 */     a("sort", var0 -> {
/*     */           BiConsumer<Vec3D, List<? extends Entity>> var3;
/*     */           int var1 = var0.g().getCursor();
/*     */           String var2 = var0.g().readUnquotedString();
/*     */           var0.a(());
/*     */           switch (var2) {
/*     */             case "nearest":
/*     */               var3 = ArgumentParserSelector.h;
/*     */               break;
/*     */             case "furthest":
/*     */               var3 = ArgumentParserSelector.i;
/*     */               break;
/*     */             case "random":
/*     */               var3 = ArgumentParserSelector.j;
/*     */               break;
/*     */             case "arbitrary":
/*     */               var3 = ArgumentParserSelector.g;
/*     */               break;
/*     */             default:
/*     */               var0.g().setCursor(var1);
/*     */               throw f.createWithContext(var0.g(), var2);
/*     */           } 
/*     */           var0.a(var3);
/*     */           var0.e(true);
/* 184 */         }var0 -> (!var0.u() && !var0.y()), new ChatMessage("argument.entity.options.sort.description"));
/*     */     
/* 186 */     a("gamemode", var0 -> { var0.a(()); int var1 = var0.g().getCursor(); boolean var2 = var0.e(); if (var0.A() && !var2) { var0.g().setCursor(var1); throw b.createWithContext(var0.g(), "gamemode"); }  String var3 = var0.g().readUnquotedString(); EnumGamemode var4 = EnumGamemode.a(var3, EnumGamemode.NOT_SET); if (var4 == EnumGamemode.NOT_SET) { var0.g().setCursor(var1); throw g.createWithContext(var0.g(), var3); }  var0.a(false); var0.a(()); if (var2) { var0.g(true); } else { var0.f(true); }  }var0 -> !var0.z(), new ChatMessage("argument.entity.options.gamemode.description"));
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
/*     */     
/* 242 */     a("team", var0 -> { boolean var1 = var0.e(); String var2 = var0.g().readUnquotedString(); var0.a(()); if (var1) { var0.i(true); } else { var0.h(true); }  }var0 -> !var0.B(), new ChatMessage("argument.entity.options.team.description"));
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
/* 261 */     a("type", var0 -> { var0.a(()); int var1 = var0.g().getCursor(); boolean var2 = var0.e(); if (var0.F() && !var2) { var0.g().setCursor(var1); throw b.createWithContext(var0.g(), "type"); }  if (var2) var0.D();  if (var0.f()) { MinecraftKey var3 = MinecraftKey.a(var0.g()); var0.a(()); } else { MinecraftKey var3 = MinecraftKey.a(var0.g()); EntityTypes<?> var4 = (EntityTypes)IRegistry.ENTITY_TYPE.getOptional(var3).orElseThrow(()); if (Objects.equals(EntityTypes.PLAYER, var4) && !var2) var0.a(false);  var0.a(()); if (!var2) var0.a(var4);  }  }var0 -> !var0.E(), new ChatMessage("argument.entity.options.type.description"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     a("tag", var0 -> { boolean var1 = var0.e(); String var2 = var0.g().readUnquotedString(); var0.a(()); }var0 -> true, new ChatMessage("argument.entity.options.tag.description"));
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
/* 315 */     a("nbt", var0 -> { boolean var1 = var0.e(); NBTTagCompound var2 = (new MojangsonParser(var0.g())).f(); var0.a(()); }var0 -> true, new ChatMessage("argument.entity.options.nbt.description"));
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
/* 331 */     a("scores", var0 -> { StringReader var1 = var0.g(); Map<String, CriterionConditionValue.IntegerRange> var2 = Maps.newHashMap(); var1.expect('{'); var1.skipWhitespace(); while (var1.canRead() && var1.peek() != '}') { var1.skipWhitespace(); String var3 = var1.readUnquotedString(); var1.skipWhitespace(); var1.expect('='); var1.skipWhitespace(); CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var1); var2.put(var3, var4); var1.skipWhitespace(); if (var1.canRead() && var1.peek() == ',') var1.skip();  }  var1.expect('}'); if (!var2.isEmpty()) var0.a(());  var0.j(true); }var0 -> !var0.G(), new ChatMessage("argument.entity.options.scores.description"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 377 */     a("advancements", var0 -> { StringReader var1 = var0.g(); Map<MinecraftKey, Predicate<AdvancementProgress>> var2 = Maps.newHashMap(); var1.expect('{'); var1.skipWhitespace(); while (var1.canRead() && var1.peek() != '}') { var1.skipWhitespace(); MinecraftKey var3 = MinecraftKey.a(var1); var1.skipWhitespace(); var1.expect('='); var1.skipWhitespace(); if (var1.canRead() && var1.peek() == '{') { Map<String, Predicate<CriterionProgress>> var4 = Maps.newHashMap(); var1.skipWhitespace(); var1.expect('{'); var1.skipWhitespace(); while (var1.canRead() && var1.peek() != '}') { var1.skipWhitespace(); String var5 = var1.readUnquotedString(); var1.skipWhitespace(); var1.expect('='); var1.skipWhitespace(); boolean var6 = var1.readBoolean(); var4.put(var5, ()); var1.skipWhitespace(); if (var1.canRead() && var1.peek() == ',') var1.skip();  }  var1.skipWhitespace(); var1.expect('}'); var1.skipWhitespace(); var2.put(var3, ()); } else { boolean var4 = var1.readBoolean(); var2.put(var3, ()); }  var1.skipWhitespace(); if (var1.canRead() && var1.peek() == ',') var1.skip();  }  var1.expect('}'); if (!var2.isEmpty()) { var0.a(()); var0.a(false); }  var0.k(true); }var0 -> !var0.H(), new ChatMessage("argument.entity.options.advancements.description"));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 457 */     a("predicate", var0 -> { boolean var1 = var0.e(); MinecraftKey var2 = MinecraftKey.a(var0.g()); var0.a(()); }var0 -> true, new ChatMessage("argument.entity.options.predicate.description"));
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
/*     */ 
/*     */   
/*     */   public static a a(ArgumentParserSelector var0, String var1, int var2) throws CommandSyntaxException {
/* 480 */     b var3 = i.get(var1);
/* 481 */     if (var3 != null) {
/* 482 */       if (var3.b.test(var0)) {
/* 483 */         return var3.a;
/*     */       }
/* 485 */       throw b.createWithContext(var0.g(), var1);
/*     */     } 
/*     */     
/* 488 */     var0.g().setCursor(var2);
/* 489 */     throw a.createWithContext(var0.g(), var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(ArgumentParserSelector var0, SuggestionsBuilder var1) {
/* 494 */     String var2 = var1.getRemaining().toLowerCase(Locale.ROOT);
/* 495 */     for (Map.Entry<String, b> var4 : i.entrySet()) {
/* 496 */       if (((b)var4.getValue()).b.test(var0) && ((String)var4.getKey()).toLowerCase(Locale.ROOT).startsWith(var2)) {
/* 497 */         var1.suggest((String)var4.getKey() + '=', ((b)var4.getValue()).c);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   static class b
/*     */   {
/*     */     public final PlayerSelector.a a;
/*     */     
/*     */     public final Predicate<ArgumentParserSelector> b;
/*     */     
/*     */     public final IChatBaseComponent c;
/*     */     
/*     */     private b(PlayerSelector.a var0, Predicate<ArgumentParserSelector> var1, IChatBaseComponent var2) {
/* 512 */       this.a = var0;
/* 513 */       this.b = var1;
/* 514 */       this.c = var2;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     void handle(ArgumentParserSelector param1ArgumentParserSelector) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PlayerSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */