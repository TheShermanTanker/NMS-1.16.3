/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandTeam
/*     */ {
/*     */   private static final DynamicCommandExceptionType b;
/*  39 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.team.add.duplicate")); static {
/*  40 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.team.add.longName", new Object[] { var0 }));
/*  41 */   } private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.team.empty.unchanged"));
/*  42 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.name.unchanged"));
/*  43 */   private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.color.unchanged"));
/*  44 */   private static final SimpleCommandExceptionType f = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.friendlyfire.alreadyEnabled"));
/*  45 */   private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.friendlyfire.alreadyDisabled"));
/*  46 */   private static final SimpleCommandExceptionType h = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.seeFriendlyInvisibles.alreadyEnabled"));
/*  47 */   private static final SimpleCommandExceptionType i = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.seeFriendlyInvisibles.alreadyDisabled"));
/*  48 */   private static final SimpleCommandExceptionType j = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.nametagVisibility.unchanged"));
/*  49 */   private static final SimpleCommandExceptionType k = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.deathMessageVisibility.unchanged"));
/*  50 */   private static final SimpleCommandExceptionType l = new SimpleCommandExceptionType(new ChatMessage("commands.team.option.collisionRule.unchanged"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  53 */     var0.register(
/*  54 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("team")
/*  55 */         .requires(var0 -> var0.hasPermission(2)))
/*  56 */         .then((
/*  57 */           (LiteralArgumentBuilder)CommandDispatcher.a("list")
/*  58 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource())))
/*  59 */           .then(
/*  60 */             CommandDispatcher.<T>a("team", ArgumentScoreboardTeam.a())
/*  61 */             .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"))))))
/*     */ 
/*     */         
/*  64 */         .then(
/*  65 */           CommandDispatcher.a("add")
/*  66 */           .then((
/*  67 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("team", (ArgumentType<T>)StringArgumentType.word())
/*  68 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "team"))))
/*  69 */             .then(
/*  70 */               CommandDispatcher.<T>a("displayName", ArgumentChatComponent.a())
/*  71 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "team"), ArgumentChatComponent.a(var0, "displayName")))))))
/*     */ 
/*     */ 
/*     */         
/*  75 */         .then(
/*  76 */           CommandDispatcher.a("remove")
/*  77 */           .then(
/*  78 */             CommandDispatcher.<T>a("team", ArgumentScoreboardTeam.a())
/*  79 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"))))))
/*     */ 
/*     */         
/*  82 */         .then(
/*  83 */           CommandDispatcher.a("empty")
/*  84 */           .then(
/*  85 */             CommandDispatcher.<T>a("team", ArgumentScoreboardTeam.a())
/*  86 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"))))))
/*     */ 
/*     */         
/*  89 */         .then(
/*  90 */           CommandDispatcher.a("join")
/*  91 */           .then((
/*  92 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("team", ArgumentScoreboardTeam.a())
/*  93 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), Collections.singleton(((CommandListenerWrapper)var0.getSource()).g().getName()))))
/*  94 */             .then(
/*  95 */               CommandDispatcher.<T>a("members", ArgumentScoreholder.b())
/*  96 */               .suggests(ArgumentScoreholder.a)
/*  97 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ArgumentScoreholder.c(var0, "members")))))))
/*     */ 
/*     */ 
/*     */         
/* 101 */         .then(
/* 102 */           CommandDispatcher.a("leave")
/* 103 */           .then(
/* 104 */             CommandDispatcher.<T>a("members", ArgumentScoreholder.b())
/* 105 */             .suggests(ArgumentScoreholder.a)
/* 106 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "members"))))))
/*     */ 
/*     */         
/* 109 */         .then(
/* 110 */           CommandDispatcher.a("modify")
/* 111 */           .then((
/* 112 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("team", ArgumentScoreboardTeam.a())
/* 113 */             .then(
/* 114 */               CommandDispatcher.a("displayName")
/* 115 */               .then(
/* 116 */                 CommandDispatcher.<T>a("displayName", ArgumentChatComponent.a())
/* 117 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ArgumentChatComponent.a(var0, "displayName"))))))
/*     */ 
/*     */             
/* 120 */             .then(
/* 121 */               CommandDispatcher.a("color")
/* 122 */               .then(
/* 123 */                 CommandDispatcher.<T>a("value", ArgumentChatFormat.a())
/* 124 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ArgumentChatFormat.a(var0, "value"))))))
/*     */ 
/*     */             
/* 127 */             .then(
/* 128 */               CommandDispatcher.a("friendlyFire")
/* 129 */               .then(
/* 130 */                 CommandDispatcher.<T>a("allowed", (ArgumentType<T>)BoolArgumentType.bool())
/* 131 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), BoolArgumentType.getBool(var0, "allowed"))))))
/*     */ 
/*     */             
/* 134 */             .then(
/* 135 */               CommandDispatcher.a("seeFriendlyInvisibles")
/* 136 */               .then(
/* 137 */                 CommandDispatcher.<T>a("allowed", (ArgumentType<T>)BoolArgumentType.bool())
/* 138 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), BoolArgumentType.getBool(var0, "allowed"))))))
/*     */ 
/*     */             
/* 141 */             .then((
/* 142 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("nametagVisibility")
/* 143 */               .then(CommandDispatcher.a("never").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.NEVER))))
/* 144 */               .then(CommandDispatcher.a("hideForOtherTeams").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS))))
/* 145 */               .then(CommandDispatcher.a("hideForOwnTeam").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM))))
/* 146 */               .then(CommandDispatcher.a("always").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS)))))
/*     */             
/* 148 */             .then((
/* 149 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("deathMessageVisibility")
/* 150 */               .then(CommandDispatcher.a("never").executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.NEVER))))
/* 151 */               .then(CommandDispatcher.a("hideForOtherTeams").executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS))))
/* 152 */               .then(CommandDispatcher.a("hideForOwnTeam").executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM))))
/* 153 */               .then(CommandDispatcher.a("always").executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS)))))
/*     */             
/* 155 */             .then((
/* 156 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("collisionRule")
/* 157 */               .then(CommandDispatcher.a("never").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumTeamPush.NEVER))))
/* 158 */               .then(CommandDispatcher.a("pushOwnTeam").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumTeamPush.PUSH_OWN_TEAM))))
/* 159 */               .then(CommandDispatcher.a("pushOtherTeams").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumTeamPush.PUSH_OTHER_TEAMS))))
/* 160 */               .then(CommandDispatcher.a("always").executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ScoreboardTeamBase.EnumTeamPush.ALWAYS)))))
/*     */             
/* 162 */             .then(
/* 163 */               CommandDispatcher.a("prefix")
/* 164 */               .then(
/* 165 */                 CommandDispatcher.<T>a("prefix", ArgumentChatComponent.a())
/* 166 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ArgumentChatComponent.a(var0, "prefix"))))))
/*     */ 
/*     */             
/* 169 */             .then(
/* 170 */               CommandDispatcher.a("suffix")
/* 171 */               .then(
/* 172 */                 CommandDispatcher.<T>a("suffix", ArgumentChatComponent.a())
/* 173 */                 .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardTeam.a(var0, "team"), ArgumentChatComponent.a(var0, "suffix"))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<String> var1) {
/* 182 */     Scoreboard var2 = var0.getServer().getScoreboard();
/*     */     
/* 184 */     for (String var4 : var1) {
/* 185 */       var2.removePlayerFromTeam(var4);
/*     */     }
/*     */     
/* 188 */     if (var1.size() == 1) {
/* 189 */       var0.sendMessage(new ChatMessage("commands.team.leave.success.single", new Object[] { var1.iterator().next() }), true);
/*     */     } else {
/* 191 */       var0.sendMessage(new ChatMessage("commands.team.leave.success.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 194 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, Collection<String> var2) {
/* 198 */     Scoreboard var3 = var0.getServer().getScoreboard();
/*     */     
/* 200 */     for (String var5 : var2) {
/* 201 */       var3.addPlayerToTeam(var5, var1);
/*     */     }
/*     */     
/* 204 */     if (var2.size() == 1) {
/* 205 */       var0.sendMessage(new ChatMessage("commands.team.join.success.single", new Object[] { var2.iterator().next(), var1.d() }), true);
/*     */     } else {
/* 207 */       var0.sendMessage(new ChatMessage("commands.team.join.success.multiple", new Object[] { Integer.valueOf(var2.size()), var1.d() }), true);
/*     */     } 
/*     */     
/* 210 */     return var2.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, ScoreboardTeamBase.EnumNameTagVisibility var2) throws CommandSyntaxException {
/* 214 */     if (var1.getNameTagVisibility() == var2) {
/* 215 */       throw j.create();
/*     */     }
/* 217 */     var1.setNameTagVisibility(var2);
/* 218 */     var0.sendMessage(new ChatMessage("commands.team.option.nametagVisibility.success", new Object[] { var1.d(), var2.b() }), true);
/* 219 */     return 0;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, ScoreboardTeam var1, ScoreboardTeamBase.EnumNameTagVisibility var2) throws CommandSyntaxException {
/* 223 */     if (var1.getDeathMessageVisibility() == var2) {
/* 224 */       throw k.create();
/*     */     }
/* 226 */     var1.setDeathMessageVisibility(var2);
/* 227 */     var0.sendMessage(new ChatMessage("commands.team.option.deathMessageVisibility.success", new Object[] { var1.d(), var2.b() }), true);
/* 228 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, ScoreboardTeamBase.EnumTeamPush var2) throws CommandSyntaxException {
/* 232 */     if (var1.getCollisionRule() == var2) {
/* 233 */       throw l.create();
/*     */     }
/* 235 */     var1.setCollisionRule(var2);
/* 236 */     var0.sendMessage(new ChatMessage("commands.team.option.collisionRule.success", new Object[] { var1.d(), var2.b() }), true);
/* 237 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, boolean var2) throws CommandSyntaxException {
/* 241 */     if (var1.canSeeFriendlyInvisibles() == var2) {
/* 242 */       if (var2) {
/* 243 */         throw h.create();
/*     */       }
/* 245 */       throw i.create();
/*     */     } 
/*     */ 
/*     */     
/* 249 */     var1.setCanSeeFriendlyInvisibles(var2);
/* 250 */     var0.sendMessage(new ChatMessage("commands.team.option.seeFriendlyInvisibles." + (var2 ? "enabled" : "disabled"), new Object[] { var1.d() }), true);
/*     */     
/* 252 */     return 0;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, ScoreboardTeam var1, boolean var2) throws CommandSyntaxException {
/* 256 */     if (var1.allowFriendlyFire() == var2) {
/* 257 */       if (var2) {
/* 258 */         throw f.create();
/*     */       }
/* 260 */       throw g.create();
/*     */     } 
/*     */ 
/*     */     
/* 264 */     var1.setAllowFriendlyFire(var2);
/* 265 */     var0.sendMessage(new ChatMessage("commands.team.option.friendlyfire." + (var2 ? "enabled" : "disabled"), new Object[] { var1.d() }), true);
/*     */     
/* 267 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, IChatBaseComponent var2) throws CommandSyntaxException {
/* 271 */     if (var1.getDisplayName().equals(var2)) {
/* 272 */       throw d.create();
/*     */     }
/*     */     
/* 275 */     var1.setDisplayName(var2);
/* 276 */     var0.sendMessage(new ChatMessage("commands.team.option.name.success", new Object[] { var1.d() }), true);
/* 277 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1, EnumChatFormat var2) throws CommandSyntaxException {
/* 281 */     if (var1.getColor() == var2) {
/* 282 */       throw e.create();
/*     */     }
/* 284 */     var1.setColor(var2);
/* 285 */     var0.sendMessage(new ChatMessage("commands.team.option.color.success", new Object[] { var1.d(), var2.f() }), true);
/* 286 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardTeam var1) throws CommandSyntaxException {
/* 290 */     Scoreboard var2 = var0.getServer().getScoreboard();
/* 291 */     Collection<String> var3 = Lists.newArrayList(var1.getPlayerNameSet());
/*     */     
/* 293 */     if (var3.isEmpty()) {
/* 294 */       throw c.create();
/*     */     }
/*     */     
/* 297 */     for (String var5 : var3) {
/* 298 */       var2.removePlayerFromTeam(var5, var1);
/*     */     }
/*     */     
/* 301 */     var0.sendMessage(new ChatMessage("commands.team.empty.success", new Object[] { Integer.valueOf(var3.size()), var1.d() }), true);
/*     */     
/* 303 */     return var3.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, ScoreboardTeam var1) {
/* 307 */     Scoreboard var2 = var0.getServer().getScoreboard();
/* 308 */     var2.removeTeam(var1);
/* 309 */     var0.sendMessage(new ChatMessage("commands.team.remove.success", new Object[] { var1.d() }), true);
/* 310 */     return var2.getTeams().size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1) throws CommandSyntaxException {
/* 314 */     return a(var0, var1, new ChatComponentText(var1));
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1, IChatBaseComponent var2) throws CommandSyntaxException {
/* 318 */     Scoreboard var3 = var0.getServer().getScoreboard();
/* 319 */     if (var3.getTeam(var1) != null) {
/* 320 */       throw a.create();
/*     */     }
/* 322 */     if (var1.length() > 16) {
/* 323 */       throw b.create(Integer.valueOf(16));
/*     */     }
/*     */     
/* 326 */     ScoreboardTeam var4 = var3.createTeam(var1);
/* 327 */     var4.setDisplayName(var2);
/*     */     
/* 329 */     var0.sendMessage(new ChatMessage("commands.team.add.success", new Object[] { var4.d() }), true);
/*     */     
/* 331 */     return var3.getTeams().size();
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, ScoreboardTeam var1) {
/* 335 */     Collection<String> var2 = var1.getPlayerNameSet();
/* 336 */     if (var2.isEmpty()) {
/* 337 */       var0.sendMessage(new ChatMessage("commands.team.list.members.empty", new Object[] { var1.d() }), false);
/*     */     } else {
/* 339 */       var0.sendMessage(new ChatMessage("commands.team.list.members.success", new Object[] { var1.d(), Integer.valueOf(var2.size()), ChatComponentUtils.a(var2) }), false);
/*     */     } 
/* 341 */     return var2.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 345 */     Collection<ScoreboardTeam> var1 = var0.getServer().getScoreboard().getTeams();
/* 346 */     if (var1.isEmpty()) {
/* 347 */       var0.sendMessage(new ChatMessage("commands.team.list.teams.empty"), false);
/*     */     } else {
/* 349 */       var0.sendMessage(new ChatMessage("commands.team.list.teams.success", new Object[] { Integer.valueOf(var1.size()), ChatComponentUtils.b(var1, ScoreboardTeam::d) }), false);
/*     */     } 
/* 351 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, ScoreboardTeam var1, IChatBaseComponent var2) {
/* 355 */     var1.setPrefix(var2);
/* 356 */     var0.sendMessage(new ChatMessage("commands.team.option.prefix.success", new Object[] { var2 }), false);
/* 357 */     return 1;
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, ScoreboardTeam var1, IChatBaseComponent var2) {
/* 361 */     var1.setSuffix(var2);
/* 362 */     var0.sendMessage(new ChatMessage("commands.team.option.suffix.success", new Object[] { var2 }), false);
/* 363 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */