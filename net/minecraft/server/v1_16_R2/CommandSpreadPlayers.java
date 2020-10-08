/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic4CommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class CommandSpreadPlayers {
/*     */   private static final Dynamic4CommandExceptionType a;
/*     */   
/*     */   static {
/*  22 */     a = new Dynamic4CommandExceptionType((object, object1, object2, object3) -> new ChatMessage("commands.spreadplayers.failed.teams", new Object[] { object, object1, object2, object3 }));
/*     */ 
/*     */     
/*  25 */     b = new Dynamic4CommandExceptionType((object, object1, object2, object3) -> new ChatMessage("commands.spreadplayers.failed.entities", new Object[] { object, object1, object2, object3 }));
/*     */   }
/*     */   private static final Dynamic4CommandExceptionType b;
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/*  30 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("spreadplayers").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*     */         
/*  32 */         .then(CommandDispatcher.<T>a("center", ArgumentVec2.a()).then(CommandDispatcher.<T>a("spreadDistance", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F)).then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("maxRange", (ArgumentType<T>)FloatArgumentType.floatArg(1.0F)).then(CommandDispatcher.<T>a("respectTeams", (ArgumentType<T>)BoolArgumentType.bool()).then(CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentVec2.a(commandcontext, "center"), FloatArgumentType.getFloat(commandcontext, "spreadDistance"), FloatArgumentType.getFloat(commandcontext, "maxRange"), 256, BoolArgumentType.getBool(commandcontext, "respectTeams"), ArgumentEntity.b(commandcontext, "targets"))))))
/*     */               
/*  34 */               .then(CommandDispatcher.a("under").then(CommandDispatcher.<T>a("maxHeight", (ArgumentType<T>)IntegerArgumentType.integer(0)).then(CommandDispatcher.<T>a("respectTeams", (ArgumentType<T>)BoolArgumentType.bool()).then(CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentVec2.a(commandcontext, "center"), FloatArgumentType.getFloat(commandcontext, "spreadDistance"), FloatArgumentType.getFloat(commandcontext, "maxRange"), IntegerArgumentType.getInteger(commandcontext, "maxHeight"), BoolArgumentType.getBool(commandcontext, "respectTeams"), ArgumentEntity.b(commandcontext, "targets")))))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Vec2F vec2f, float f, float f1, int i, boolean flag, Collection<? extends Entity> collection) throws CommandSyntaxException {
/*  40 */     Random random = new Random();
/*  41 */     double d0 = (vec2f.i - f1);
/*  42 */     double d1 = (vec2f.j - f1);
/*  43 */     double d2 = (vec2f.i + f1);
/*  44 */     double d3 = (vec2f.j + f1);
/*  45 */     a[] acommandspreadplayers_a = a(random, flag ? a(collection) : collection.size(), d0, d1, d2, d3);
/*     */     
/*  47 */     a(vec2f, f, commandlistenerwrapper.getWorld(), random, d0, d1, d2, d3, i, acommandspreadplayers_a, flag);
/*  48 */     double d4 = a(collection, commandlistenerwrapper.getWorld(), acommandspreadplayers_a, i, flag);
/*     */     
/*  50 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.spreadplayers.success." + (flag ? "teams" : "entities"), new Object[] { Integer.valueOf(acommandspreadplayers_a.length), Float.valueOf(vec2f.i), Float.valueOf(vec2f.j), String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(d4) }) }), true);
/*  51 */     return acommandspreadplayers_a.length;
/*     */   }
/*     */   
/*     */   private static int a(Collection<? extends Entity> collection) {
/*  55 */     Set<ScoreboardTeamBase> set = Sets.newHashSet();
/*  56 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/*  58 */     while (iterator.hasNext()) {
/*  59 */       Entity entity = iterator.next();
/*     */       
/*  61 */       if (entity instanceof EntityHuman) {
/*  62 */         set.add(entity.getScoreboardTeam()); continue;
/*     */       } 
/*  64 */       set.add((ScoreboardTeamBase)null);
/*     */     } 
/*     */ 
/*     */     
/*  68 */     return set.size();
/*     */   }
/*     */   
/*     */   private static void a(Vec2F vec2f, double d0, WorldServer worldserver, Random random, double d1, double d2, double d3, double d4, int i, a[] acommandspreadplayers_a, boolean flag) throws CommandSyntaxException {
/*  72 */     boolean flag1 = true;
/*  73 */     double d5 = 3.4028234663852886E38D;
/*     */     
/*     */     int j;
/*     */     
/*  77 */     for (j = 0; j < 10000 && flag1; j++) {
/*  78 */       flag1 = false;
/*  79 */       d5 = 3.4028234663852886E38D;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       for (int l = 0; l < acommandspreadplayers_a.length; l++) {
/*  85 */         a commandspreadplayers_a1 = acommandspreadplayers_a[l];
/*     */         
/*  87 */         int k = 0;
/*  88 */         a commandspreadplayers_a = new a();
/*     */         
/*  90 */         for (int i1 = 0; i1 < acommandspreadplayers_a.length; i1++) {
/*  91 */           if (l != i1) {
/*  92 */             a commandspreadplayers_a2 = acommandspreadplayers_a[i1];
/*  93 */             double d6 = commandspreadplayers_a1.a(commandspreadplayers_a2);
/*     */             
/*  95 */             d5 = Math.min(d6, d5);
/*  96 */             if (d6 < d0) {
/*  97 */               k++;
/*  98 */               commandspreadplayers_a.a = commandspreadplayers_a.a + commandspreadplayers_a2.a - commandspreadplayers_a1.a;
/*  99 */               commandspreadplayers_a.b = commandspreadplayers_a.b + commandspreadplayers_a2.b - commandspreadplayers_a1.b;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 104 */         if (k > 0) {
/* 105 */           commandspreadplayers_a.a = commandspreadplayers_a.a / k;
/* 106 */           commandspreadplayers_a.b = commandspreadplayers_a.b / k;
/* 107 */           double d7 = commandspreadplayers_a.b();
/*     */           
/* 109 */           if (d7 > 0.0D) {
/* 110 */             commandspreadplayers_a.a();
/* 111 */             commandspreadplayers_a1.b(commandspreadplayers_a);
/*     */           } else {
/* 113 */             commandspreadplayers_a1.a(random, d1, d2, d3, d4);
/*     */           } 
/*     */           
/* 116 */           flag1 = true;
/*     */         } 
/*     */         
/* 119 */         if (commandspreadplayers_a1.a(d1, d2, d3, d4)) {
/* 120 */           flag1 = true;
/*     */         }
/*     */       } 
/*     */       
/* 124 */       if (!flag1) {
/* 125 */         a[] acommandspreadplayers_a1 = acommandspreadplayers_a;
/* 126 */         int j1 = acommandspreadplayers_a.length;
/*     */         
/* 128 */         for (int k = 0; k < j1; k++) {
/* 129 */           a commandspreadplayers_a = acommandspreadplayers_a1[k];
/* 130 */           if (!commandspreadplayers_a.b(worldserver, i)) {
/* 131 */             commandspreadplayers_a.a(random, d1, d2, d3, d4);
/* 132 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 138 */     if (d5 == 3.4028234663852886E38D) {
/* 139 */       d5 = 0.0D;
/*     */     }
/*     */     
/* 142 */     if (j >= 10000) {
/* 143 */       if (flag) {
/* 144 */         throw a.create(Integer.valueOf(acommandspreadplayers_a.length), Float.valueOf(vec2f.i), Float.valueOf(vec2f.j), String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(d5) }));
/*     */       }
/* 146 */       throw b.create(Integer.valueOf(acommandspreadplayers_a.length), Float.valueOf(vec2f.i), Float.valueOf(vec2f.j), String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(d5) }));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static double a(Collection<? extends Entity> collection, WorldServer worldserver, a[] acommandspreadplayers_a, int i, boolean flag) {
/* 152 */     double d0 = 0.0D;
/* 153 */     int j = 0;
/* 154 */     Map<ScoreboardTeamBase, a> map = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */     
/* 158 */     for (Iterator<? extends Entity> iterator = collection.iterator(); iterator.hasNext(); d0 += d1) {
/* 159 */       a commandspreadplayers_a; Entity entity = iterator.next();
/*     */ 
/*     */       
/* 162 */       if (flag) {
/* 163 */         ScoreboardTeamBase scoreboardteambase = (entity instanceof EntityHuman) ? entity.getScoreboardTeam() : null;
/*     */         
/* 165 */         if (!map.containsKey(scoreboardteambase)) {
/* 166 */           map.put(scoreboardteambase, acommandspreadplayers_a[j++]);
/*     */         }
/*     */         
/* 169 */         commandspreadplayers_a = map.get(scoreboardteambase);
/*     */       } else {
/* 171 */         commandspreadplayers_a = acommandspreadplayers_a[j++];
/*     */       } 
/*     */       
/* 174 */       entity.enderTeleportAndLoad(MathHelper.floor(commandspreadplayers_a.a) + 0.5D, commandspreadplayers_a.a(worldserver, i), MathHelper.floor(commandspreadplayers_a.b) + 0.5D);
/* 175 */       double d1 = Double.MAX_VALUE;
/* 176 */       a[] acommandspreadplayers_a1 = acommandspreadplayers_a;
/* 177 */       int k = acommandspreadplayers_a.length;
/*     */       
/* 179 */       for (int l = 0; l < k; l++) {
/* 180 */         a commandspreadplayers_a1 = acommandspreadplayers_a1[l];
/*     */         
/* 182 */         if (commandspreadplayers_a != commandspreadplayers_a1) {
/* 183 */           double d2 = commandspreadplayers_a.a(commandspreadplayers_a1);
/*     */           
/* 185 */           d1 = Math.min(d2, d1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     if (collection.size() < 2) {
/* 191 */       return 0.0D;
/*     */     }
/* 193 */     d0 /= collection.size();
/* 194 */     return d0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static a[] a(Random random, int i, double d0, double d1, double d2, double d3) {
/* 199 */     a[] acommandspreadplayers_a = new a[i];
/*     */     
/* 201 */     for (int j = 0; j < acommandspreadplayers_a.length; j++) {
/* 202 */       a commandspreadplayers_a = new a();
/*     */       
/* 204 */       commandspreadplayers_a.a(random, d0, d1, d2, d3);
/* 205 */       acommandspreadplayers_a[j] = commandspreadplayers_a;
/*     */     } 
/*     */     
/* 208 */     return acommandspreadplayers_a;
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     private double a;
/*     */     
/*     */     private double b;
/*     */     
/*     */     double a(a commandspreadplayers_a) {
/* 219 */       double d0 = this.a - commandspreadplayers_a.a;
/* 220 */       double d1 = this.b - commandspreadplayers_a.b;
/*     */       
/* 222 */       return Math.sqrt(d0 * d0 + d1 * d1);
/*     */     }
/*     */     
/*     */     void a() {
/* 226 */       double d0 = b();
/*     */       
/* 228 */       this.a /= d0;
/* 229 */       this.b /= d0;
/*     */     }
/*     */     
/*     */     float b() {
/* 233 */       return MathHelper.sqrt(this.a * this.a + this.b * this.b);
/*     */     }
/*     */     
/*     */     public void b(a commandspreadplayers_a) {
/* 237 */       this.a -= commandspreadplayers_a.a;
/* 238 */       this.b -= commandspreadplayers_a.b;
/*     */     }
/*     */     
/*     */     public boolean a(double d0, double d1, double d2, double d3) {
/* 242 */       boolean flag = false;
/*     */       
/* 244 */       if (this.a < d0) {
/* 245 */         this.a = d0;
/* 246 */         flag = true;
/* 247 */       } else if (this.a > d2) {
/* 248 */         this.a = d2;
/* 249 */         flag = true;
/*     */       } 
/*     */       
/* 252 */       if (this.b < d1) {
/* 253 */         this.b = d1;
/* 254 */         flag = true;
/* 255 */       } else if (this.b > d3) {
/* 256 */         this.b = d3;
/* 257 */         flag = true;
/*     */       } 
/*     */       
/* 260 */       return flag;
/*     */     }
/*     */     
/*     */     public int a(IBlockAccess iblockaccess, int i) {
/* 264 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(this.a, (i + 1), this.b);
/* 265 */       boolean flag = iblockaccess.getType(blockposition_mutableblockposition).isAir();
/*     */       
/* 267 */       blockposition_mutableblockposition.c(EnumDirection.DOWN);
/*     */       
/*     */       boolean flag2;
/*     */       
/* 271 */       for (flag2 = iblockaccess.getType(blockposition_mutableblockposition).isAir(); blockposition_mutableblockposition.getY() > 0; flag2 = flag1) {
/* 272 */         blockposition_mutableblockposition.c(EnumDirection.DOWN);
/* 273 */         boolean flag1 = getType(iblockaccess, blockposition_mutableblockposition).isAir();
/* 274 */         if (!flag1 && flag2 && flag) {
/* 275 */           return blockposition_mutableblockposition.getY() + 1;
/*     */         }
/*     */         
/* 278 */         flag = flag2;
/*     */       } 
/*     */       
/* 281 */       return i + 1;
/*     */     }
/*     */     
/*     */     public boolean b(IBlockAccess iblockaccess, int i) {
/* 285 */       BlockPosition blockposition = new BlockPosition(this.a, (a(iblockaccess, i) - 1), this.b);
/* 286 */       IBlockData iblockdata = getType(iblockaccess, blockposition);
/* 287 */       Material material = iblockdata.getMaterial();
/*     */       
/* 289 */       return (blockposition.getY() < i && !material.isLiquid() && material != Material.FIRE);
/*     */     }
/*     */     
/*     */     public void a(Random random, double d0, double d1, double d2, double d3) {
/* 293 */       this.a = MathHelper.a(random, d0, d2);
/* 294 */       this.b = MathHelper.a(random, d1, d3);
/*     */     }
/*     */ 
/*     */     
/*     */     private static IBlockData getType(IBlockAccess iblockaccess, BlockPosition position) {
/* 299 */       ((WorldServer)iblockaccess).getChunkProvider().getChunkAt(position.getX() >> 4, position.getZ() >> 4, true);
/* 300 */       return iblockaccess.getType(position);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSpreadPlayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */