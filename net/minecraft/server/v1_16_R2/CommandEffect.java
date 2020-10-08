/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class CommandEffect {
/*  17 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.effect.give.failed"));
/*  18 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.effect.clear.everything.failed"));
/*  19 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.effect.clear.specific.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/*  22 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("effect").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*     */         
/*  24 */         .then(((LiteralArgumentBuilder)CommandDispatcher.a("clear").executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), (Collection<? extends Entity>)ImmutableList.of(((CommandListenerWrapper)commandcontext.getSource()).g()))))
/*     */           
/*  26 */           .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"))))
/*     */             
/*  28 */             .then(CommandDispatcher.<T>a("effect", ArgumentMobEffect.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentMobEffect.a(commandcontext, "effect")))))))
/*     */         
/*  30 */         .then(CommandDispatcher.a("give").then(CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities()).then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("effect", ArgumentMobEffect.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentMobEffect.a(commandcontext, "effect"), (Integer)null, 0, true)))
/*     */               
/*  32 */               .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("seconds", (ArgumentType<T>)IntegerArgumentType.integer(1, 1000000)).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentMobEffect.a(commandcontext, "effect"), Integer.valueOf(IntegerArgumentType.getInteger(commandcontext, "seconds")), 0, true)))
/*     */                 
/*  34 */                 .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("amplifier", (ArgumentType<T>)IntegerArgumentType.integer(0, 255)).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentMobEffect.a(commandcontext, "effect"), Integer.valueOf(IntegerArgumentType.getInteger(commandcontext, "seconds")), IntegerArgumentType.getInteger(commandcontext, "amplifier"), true)))
/*     */                   
/*  36 */                   .then(CommandDispatcher.<T>a("hideParticles", (ArgumentType<T>)BoolArgumentType.bool()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), ArgumentEntity.b(commandcontext, "targets"), ArgumentMobEffect.a(commandcontext, "effect"), Integer.valueOf(IntegerArgumentType.getInteger(commandcontext, "seconds")), IntegerArgumentType.getInteger(commandcontext, "amplifier"), !BoolArgumentType.getBool(commandcontext, "hideParticles"))))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, MobEffectList mobeffectlist, @Nullable Integer integer, int i, boolean flag) throws CommandSyntaxException {
/*  42 */     int k, j = 0;
/*     */ 
/*     */     
/*  45 */     if (integer != null) {
/*  46 */       if (mobeffectlist.isInstant()) {
/*  47 */         k = integer.intValue();
/*     */       } else {
/*  49 */         k = integer.intValue() * 20;
/*     */       } 
/*  51 */     } else if (mobeffectlist.isInstant()) {
/*  52 */       k = 1;
/*     */     } else {
/*  54 */       k = 600;
/*     */     } 
/*     */     
/*  57 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/*  59 */     while (iterator.hasNext()) {
/*  60 */       Entity entity = iterator.next();
/*     */       
/*  62 */       if (entity instanceof EntityLiving) {
/*  63 */         MobEffect mobeffect = new MobEffect(mobeffectlist, k, i, false, flag);
/*     */         
/*  65 */         if (((EntityLiving)entity).addEffect(mobeffect, EntityPotionEffectEvent.Cause.COMMAND)) {
/*  66 */           j++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     if (j == 0) {
/*  72 */       throw a.create();
/*     */     }
/*  74 */     if (collection.size() == 1) {
/*  75 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.give.success.single", new Object[] { mobeffectlist.d(), ((Entity)collection.iterator().next()).getScoreboardDisplayName(), Integer.valueOf(k / 20) }), true);
/*     */     } else {
/*  77 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.give.success.multiple", new Object[] { mobeffectlist.d(), Integer.valueOf(collection.size()), Integer.valueOf(k / 20) }), true);
/*     */     } 
/*     */     
/*  80 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection) throws CommandSyntaxException {
/*  85 */     int i = 0;
/*  86 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/*  88 */     while (iterator.hasNext()) {
/*  89 */       Entity entity = iterator.next();
/*     */       
/*  91 */       if (entity instanceof EntityLiving && ((EntityLiving)entity).removeAllEffects(EntityPotionEffectEvent.Cause.COMMAND)) {
/*  92 */         i++;
/*     */       }
/*     */     } 
/*     */     
/*  96 */     if (i == 0) {
/*  97 */       throw b.create();
/*     */     }
/*  99 */     if (collection.size() == 1) {
/* 100 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.clear.everything.success.single", new Object[] { ((Entity)collection.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 102 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.clear.everything.success.multiple", new Object[] { Integer.valueOf(collection.size()) }), true);
/*     */     } 
/*     */     
/* 105 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper commandlistenerwrapper, Collection<? extends Entity> collection, MobEffectList mobeffectlist) throws CommandSyntaxException {
/* 110 */     int i = 0;
/* 111 */     Iterator<? extends Entity> iterator = collection.iterator();
/*     */     
/* 113 */     while (iterator.hasNext()) {
/* 114 */       Entity entity = iterator.next();
/*     */       
/* 116 */       if (entity instanceof EntityLiving && ((EntityLiving)entity).removeEffect(mobeffectlist, EntityPotionEffectEvent.Cause.COMMAND)) {
/* 117 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 121 */     if (i == 0) {
/* 122 */       throw c.create();
/*     */     }
/* 124 */     if (collection.size() == 1) {
/* 125 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.clear.specific.success.single", new Object[] { mobeffectlist.d(), ((Entity)collection.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 127 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.effect.clear.specific.success.multiple", new Object[] { mobeffectlist.d(), Integer.valueOf(collection.size()) }), true);
/*     */     } 
/*     */     
/* 130 */     return i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */