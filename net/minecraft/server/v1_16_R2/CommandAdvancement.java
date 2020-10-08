/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandAdvancement
/*     */ {
/*     */   private static final SuggestionProvider<CommandListenerWrapper> a;
/*     */   
/*     */   static {
/*  28 */     a = ((var0, var1) -> {
/*     */         Collection<Advancement> var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getAdvancementData().getAdvancements();
/*     */         return ICompletionProvider.a(var2.stream().map(Advancement::getName), var1);
/*     */       });
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  34 */     var0.register(
/*  35 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("advancement")
/*  36 */         .requires(var0 -> var0.hasPermission(2)))
/*  37 */         .then(
/*  38 */           CommandDispatcher.a("grant")
/*  39 */           .then((
/*  40 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  41 */             .then(
/*  42 */               CommandDispatcher.a("only")
/*  43 */               .then((
/*  44 */                 (RequiredArgumentBuilder)CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/*  45 */                 .suggests(a)
/*  46 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.ONLY))))
/*  47 */                 .then(
/*  48 */                   CommandDispatcher.<T>a("criterion", (ArgumentType<T>)StringArgumentType.greedyString())
/*  49 */                   .suggests((var0, var1) -> ICompletionProvider.b(ArgumentMinecraftKeyRegistered.a(var0, "advancement").getCriteria().keySet(), var1))
/*  50 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, ArgumentMinecraftKeyRegistered.a(var0, "advancement"), StringArgumentType.getString(var0, "criterion")))))))
/*     */ 
/*     */ 
/*     */             
/*  54 */             .then(
/*  55 */               CommandDispatcher.a("from")
/*  56 */               .then(
/*  57 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/*  58 */                 .suggests(a)
/*  59 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.FROM))))))
/*     */ 
/*     */             
/*  62 */             .then(
/*  63 */               CommandDispatcher.a("until")
/*  64 */               .then(
/*  65 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/*  66 */                 .suggests(a)
/*  67 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.UNTIL))))))
/*     */ 
/*     */             
/*  70 */             .then(
/*  71 */               CommandDispatcher.a("through")
/*  72 */               .then(
/*  73 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/*  74 */                 .suggests(a)
/*  75 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.THROUGH))))))
/*     */ 
/*     */             
/*  78 */             .then(
/*  79 */               CommandDispatcher.a("everything")
/*  80 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.GRANT, ((CommandListenerWrapper)var0.getSource()).getServer().getAdvancementData().getAdvancements()))))))
/*     */ 
/*     */ 
/*     */         
/*  84 */         .then(
/*  85 */           CommandDispatcher.a("revoke")
/*  86 */           .then((
/*  87 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  88 */             .then(
/*  89 */               CommandDispatcher.a("only")
/*  90 */               .then((
/*  91 */                 (RequiredArgumentBuilder)CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/*  92 */                 .suggests(a)
/*  93 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.ONLY))))
/*  94 */                 .then(
/*  95 */                   CommandDispatcher.<T>a("criterion", (ArgumentType<T>)StringArgumentType.greedyString())
/*  96 */                   .suggests((var0, var1) -> ICompletionProvider.b(ArgumentMinecraftKeyRegistered.a(var0, "advancement").getCriteria().keySet(), var1))
/*  97 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, ArgumentMinecraftKeyRegistered.a(var0, "advancement"), StringArgumentType.getString(var0, "criterion")))))))
/*     */ 
/*     */ 
/*     */             
/* 101 */             .then(
/* 102 */               CommandDispatcher.a("from")
/* 103 */               .then(
/* 104 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/* 105 */                 .suggests(a)
/* 106 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.FROM))))))
/*     */ 
/*     */             
/* 109 */             .then(
/* 110 */               CommandDispatcher.a("until")
/* 111 */               .then(
/* 112 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/* 113 */                 .suggests(a)
/* 114 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.UNTIL))))))
/*     */ 
/*     */             
/* 117 */             .then(
/* 118 */               CommandDispatcher.a("through")
/* 119 */               .then(
/* 120 */                 CommandDispatcher.<T>a("advancement", ArgumentMinecraftKeyRegistered.a())
/* 121 */                 .suggests(a)
/* 122 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, a(ArgumentMinecraftKeyRegistered.a(var0, "advancement"), Filter.THROUGH))))))
/*     */ 
/*     */             
/* 125 */             .then(
/* 126 */               CommandDispatcher.a("everything")
/* 127 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), Action.REVOKE, ((CommandListenerWrapper)var0.getSource()).getServer().getAdvancementData().getAdvancements()))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, Action var2, Collection<Advancement> var3) {
/* 135 */     int var4 = 0;
/* 136 */     for (EntityPlayer var6 : var1) {
/* 137 */       var4 += var2.a(var6, var3);
/*     */     }
/*     */     
/* 140 */     if (var4 == 0) {
/* 141 */       if (var3.size() == 1) {
/* 142 */         if (var1.size() == 1) {
/* 143 */           throw new CommandException(new ChatMessage(var2.a() + ".one.to.one.failure", new Object[] { ((Advancement)var3.iterator().next()).j(), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }));
/*     */         }
/* 145 */         throw new CommandException(new ChatMessage(var2.a() + ".one.to.many.failure", new Object[] { ((Advancement)var3.iterator().next()).j(), Integer.valueOf(var1.size()) }));
/*     */       } 
/*     */       
/* 148 */       if (var1.size() == 1) {
/* 149 */         throw new CommandException(new ChatMessage(var2.a() + ".many.to.one.failure", new Object[] { Integer.valueOf(var3.size()), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }));
/*     */       }
/* 151 */       throw new CommandException(new ChatMessage(var2.a() + ".many.to.many.failure", new Object[] { Integer.valueOf(var3.size()), Integer.valueOf(var1.size()) }));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (var3.size() == 1) {
/* 157 */       if (var1.size() == 1) {
/* 158 */         var0.sendMessage(new ChatMessage(var2.a() + ".one.to.one.success", new Object[] { ((Advancement)var3.iterator().next()).j(), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */       } else {
/* 160 */         var0.sendMessage(new ChatMessage(var2.a() + ".one.to.many.success", new Object[] { ((Advancement)var3.iterator().next()).j(), Integer.valueOf(var1.size()) }), true);
/*     */       }
/*     */     
/* 163 */     } else if (var1.size() == 1) {
/* 164 */       var0.sendMessage(new ChatMessage(var2.a() + ".many.to.one.success", new Object[] { Integer.valueOf(var3.size()), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 166 */       var0.sendMessage(new ChatMessage(var2.a() + ".many.to.many.success", new Object[] { Integer.valueOf(var3.size()), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */ 
/*     */     
/* 170 */     return var4;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, Action var2, Advancement var3, String var4) {
/* 174 */     int var5 = 0;
/*     */     
/* 176 */     if (!var3.getCriteria().containsKey(var4)) {
/* 177 */       throw new CommandException(new ChatMessage("commands.advancement.criterionNotFound", new Object[] { var3.j(), var4 }));
/*     */     }
/*     */     
/* 180 */     for (EntityPlayer var7 : var1) {
/* 181 */       if (var2.a(var7, var3, var4)) {
/* 182 */         var5++;
/*     */       }
/*     */     } 
/*     */     
/* 186 */     if (var5 == 0) {
/* 187 */       if (var1.size() == 1) {
/* 188 */         throw new CommandException(new ChatMessage(var2.a() + ".criterion.to.one.failure", new Object[] { var4, var3.j(), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }));
/*     */       }
/* 190 */       throw new CommandException(new ChatMessage(var2.a() + ".criterion.to.many.failure", new Object[] { var4, var3.j(), Integer.valueOf(var1.size()) }));
/*     */     } 
/*     */ 
/*     */     
/* 194 */     if (var1.size() == 1) {
/* 195 */       var0.sendMessage(new ChatMessage(var2.a() + ".criterion.to.one.success", new Object[] { var4, var3.j(), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 197 */       var0.sendMessage(new ChatMessage(var2.a() + ".criterion.to.many.success", new Object[] { var4, var3.j(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 200 */     return var5;
/*     */   }
/*     */   
/*     */   private static List<Advancement> a(Advancement var0, Filter var1) {
/* 204 */     List<Advancement> var2 = Lists.newArrayList();
/* 205 */     if (Filter.a(var1)) {
/* 206 */       Advancement var3 = var0.b();
/* 207 */       while (var3 != null) {
/* 208 */         var2.add(var3);
/* 209 */         var3 = var3.b();
/*     */       } 
/*     */     } 
/* 212 */     var2.add(var0);
/* 213 */     if (Filter.b(var1)) {
/* 214 */       a(var0, var2);
/*     */     }
/* 216 */     return var2;
/*     */   }
/*     */   
/*     */   private static void a(Advancement var0, List<Advancement> var1) {
/* 220 */     for (Advancement var3 : var0.e()) {
/* 221 */       var1.add(var3);
/* 222 */       a(var3, var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   enum Action {
/* 227 */     GRANT("grant")
/*     */     {
/*     */       protected boolean a(EntityPlayer var0, Advancement var1) {
/* 230 */         AdvancementProgress var2 = var0.getAdvancementData().getProgress(var1);
/* 231 */         if (var2.isDone()) {
/* 232 */           return false;
/*     */         }
/* 234 */         for (String var4 : var2.getRemainingCriteria()) {
/* 235 */           var0.getAdvancementData().grantCriteria(var1, var4);
/*     */         }
/* 237 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       protected boolean a(EntityPlayer var0, Advancement var1, String var2) {
/* 242 */         return var0.getAdvancementData().grantCriteria(var1, var2);
/*     */       }
/*     */     },
/* 245 */     REVOKE("revoke")
/*     */     {
/*     */       protected boolean a(EntityPlayer var0, Advancement var1) {
/* 248 */         AdvancementProgress var2 = var0.getAdvancementData().getProgress(var1);
/* 249 */         if (!var2.b()) {
/* 250 */           return false;
/*     */         }
/* 252 */         for (String var4 : var2.getAwardedCriteria()) {
/* 253 */           var0.getAdvancementData().revokeCritera(var1, var4);
/*     */         }
/* 255 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       protected boolean a(EntityPlayer var0, Advancement var1, String var2) {
/* 260 */         return var0.getAdvancementData().revokeCritera(var1, var2);
/*     */       }
/*     */     };
/*     */ 
/*     */     
/*     */     private final String c;
/*     */     
/*     */     Action(String var2) {
/* 268 */       this.c = "commands.advancement." + var2;
/*     */     }
/*     */     
/*     */     public int a(EntityPlayer var0, Iterable<Advancement> var1) {
/* 272 */       int var2 = 0;
/* 273 */       for (Advancement var4 : var1) {
/* 274 */         if (a(var0, var4)) {
/* 275 */           var2++;
/*     */         }
/*     */       } 
/* 278 */       return var2;
/*     */     }
/*     */     
/*     */     protected abstract boolean a(EntityPlayer param1EntityPlayer, Advancement param1Advancement);
/*     */     
/*     */     protected abstract boolean a(EntityPlayer param1EntityPlayer, Advancement param1Advancement, String param1String);
/*     */     
/*     */     protected String a() {
/* 286 */       return this.c;
/*     */     }
/*     */   }
/*     */   
/*     */   enum Filter {
/* 291 */     ONLY(false, false),
/* 292 */     THROUGH(true, true),
/* 293 */     FROM(false, true),
/* 294 */     UNTIL(true, false),
/* 295 */     EVERYTHING(true, true);
/*     */     
/*     */     private final boolean f;
/*     */     
/*     */     private final boolean g;
/*     */     
/*     */     Filter(boolean var2, boolean var3) {
/* 302 */       this.f = var2;
/* 303 */       this.g = var3;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandAdvancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */