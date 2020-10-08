/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.brigadier.AsyncPlayerSendCommandsEvent;
/*     */ import com.google.common.base.Joiner;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.ParseResults;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import com.mojang.brigadier.tree.RootCommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerCommandSendEvent;
/*     */ import org.bukkit.event.server.ServerCommandEvent;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class CommandDispatcher {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger();
/*  33 */   private final com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> b = new com.mojang.brigadier.CommandDispatcher();
/*     */   
/*     */   public CommandDispatcher(ServerType commanddispatcher_servertype) {
/*  36 */     this();
/*  37 */     CommandAdvancement.a(this.b);
/*  38 */     CommandAttribute.a(this.b);
/*  39 */     CommandExecute.a(this.b);
/*  40 */     CommandBossBar.a(this.b);
/*  41 */     CommandClear.a(this.b);
/*  42 */     CommandClone.a(this.b);
/*  43 */     CommandData.a(this.b);
/*  44 */     CommandDatapack.a(this.b);
/*  45 */     CommandDebug.a(this.b);
/*  46 */     CommandGamemodeDefault.a(this.b);
/*  47 */     CommandDifficulty.a(this.b);
/*  48 */     CommandEffect.a(this.b);
/*  49 */     CommandMe.a(this.b);
/*  50 */     CommandEnchant.a(this.b);
/*  51 */     CommandXp.a(this.b);
/*  52 */     CommandFill.a(this.b);
/*  53 */     CommandForceload.a(this.b);
/*  54 */     CommandFunction.a(this.b);
/*  55 */     CommandGamemode.a(this.b);
/*  56 */     CommandGamerule.a(this.b);
/*  57 */     CommandGive.a(this.b);
/*  58 */     CommandHelp.a(this.b);
/*  59 */     CommandKick.a(this.b);
/*  60 */     CommandKill.a(this.b);
/*  61 */     CommandList.a(this.b);
/*  62 */     CommandLocate.a(this.b);
/*  63 */     CommandLocateBiome.a(this.b);
/*  64 */     CommandLoot.a(this.b);
/*  65 */     CommandTell.a(this.b);
/*  66 */     CommandParticle.a(this.b);
/*  67 */     CommandPlaySound.a(this.b);
/*  68 */     CommandReload.a(this.b);
/*  69 */     CommandRecipe.a(this.b);
/*  70 */     CommandReplaceItem.a(this.b);
/*  71 */     CommandSay.a(this.b);
/*  72 */     CommandSchedule.a(this.b);
/*  73 */     CommandScoreboard.a(this.b);
/*  74 */     CommandSeed.a(this.b, (commanddispatcher_servertype != ServerType.INTEGRATED));
/*  75 */     CommandSetBlock.a(this.b);
/*  76 */     CommandSpawnpoint.a(this.b);
/*  77 */     CommandSetWorldSpawn.a(this.b);
/*  78 */     CommandSpectate.a(this.b);
/*  79 */     CommandSpreadPlayers.a(this.b);
/*  80 */     CommandStopSound.a(this.b);
/*  81 */     CommandSummon.a(this.b);
/*  82 */     CommandTag.a(this.b);
/*  83 */     CommandTeam.a(this.b);
/*  84 */     CommandTeamMsg.a(this.b);
/*  85 */     CommandTeleport.a(this.b);
/*  86 */     CommandTellRaw.a(this.b);
/*  87 */     CommandTime.a(this.b);
/*  88 */     CommandTitle.a(this.b);
/*  89 */     CommandTrigger.a(this.b);
/*  90 */     CommandWeather.a(this.b);
/*  91 */     CommandWorldBorder.a(this.b);
/*  92 */     if (SharedConstants.d) {
/*  93 */       GameTestHarnessTestCommand.a(this.b);
/*     */     }
/*     */     
/*  96 */     if (commanddispatcher_servertype.e) {
/*  97 */       CommandBanIp.a(this.b);
/*  98 */       CommandBanList.a(this.b);
/*  99 */       CommandBan.a(this.b);
/* 100 */       CommandDeop.a(this.b);
/* 101 */       CommandOp.a(this.b);
/* 102 */       CommandPardon.a(this.b);
/* 103 */       CommandPardonIP.a(this.b);
/* 104 */       CommandSaveAll.a(this.b);
/* 105 */       CommandSaveOff.a(this.b);
/* 106 */       CommandSaveOn.a(this.b);
/* 107 */       CommandIdleTimeout.a(this.b);
/* 108 */       CommandStop.a(this.b);
/* 109 */       CommandWhitelist.a(this.b);
/*     */     } 
/*     */     
/* 112 */     if (commanddispatcher_servertype.d) {
/* 113 */       CommandPublish.a(this.b);
/*     */     }
/*     */     
/* 116 */     this.b.findAmbiguities((commandnode, commandnode1, commandnode2, collection) -> {
/*     */         
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandDispatcher() {
/* 123 */     this.b.setConsumer((commandcontext, flag1, i) -> ((CommandListenerWrapper)commandcontext.getSource()).a(commandcontext, flag1, i));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int dispatchServerCommand(CommandListenerWrapper sender, String command) {
/* 129 */     Joiner joiner = Joiner.on(" ");
/* 130 */     if (command.startsWith("/")) {
/* 131 */       command = command.substring(1);
/*     */     }
/*     */     
/* 134 */     ServerCommandEvent event = new ServerCommandEvent(sender.getBukkitSender(), command);
/* 135 */     Bukkit.getPluginManager().callEvent((Event)event);
/* 136 */     if (event.isCancelled()) {
/* 137 */       return 0;
/*     */     }
/* 139 */     command = event.getCommand();
/*     */     
/* 141 */     String[] args = command.split(" ");
/*     */     
/* 143 */     String cmd = args[0];
/* 144 */     if (cmd.startsWith("minecraft:")) cmd = cmd.substring("minecraft:".length()); 
/* 145 */     if (cmd.startsWith("bukkit:")) cmd = cmd.substring("bukkit:".length());
/*     */ 
/*     */     
/* 148 */     if (cmd.equalsIgnoreCase("stop") || cmd.equalsIgnoreCase("kick") || cmd.equalsIgnoreCase("op") || cmd
/* 149 */       .equalsIgnoreCase("deop") || cmd.equalsIgnoreCase("ban") || cmd.equalsIgnoreCase("ban-ip") || cmd
/* 150 */       .equalsIgnoreCase("pardon") || cmd.equalsIgnoreCase("pardon-ip") || cmd.equalsIgnoreCase("reload")) {
/* 151 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 155 */     if (sender.getWorld().getServer().getCommandBlockOverride(args[0])) {
/* 156 */       args[0] = "minecraft:" + args[0];
/*     */     }
/*     */     
/* 159 */     return a(sender, joiner.join((Object[])args));
/*     */   }
/*     */   
/*     */   public int a(CommandListenerWrapper commandlistenerwrapper, String s) {
/* 163 */     return a(commandlistenerwrapper, s, s);
/*     */   }
/*     */   
/*     */   public int a(CommandListenerWrapper commandlistenerwrapper, String s, String label) {
/*     */     byte b0;
/* 168 */     StringReader stringreader = new StringReader(s);
/*     */     
/* 170 */     if (stringreader.canRead() && stringreader.peek() == '/') {
/* 171 */       stringreader.skip();
/*     */     }
/*     */     
/* 174 */     commandlistenerwrapper.getServer().getMethodProfiler().enter(s);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 182 */       int i = this.b.execute(stringreader, commandlistenerwrapper);
/*     */       
/* 184 */       return i;
/* 185 */     } catch (CommandException commandexception) {
/* 186 */       commandlistenerwrapper.sendFailureMessage(commandexception.a());
/* 187 */       byte b1 = 0;
/* 188 */       return b1;
/* 189 */     } catch (CommandSyntaxException commandsyntaxexception) {
/* 190 */       commandlistenerwrapper.sendFailureMessage(ChatComponentUtils.a(commandsyntaxexception.getRawMessage()));
/* 191 */       if (commandsyntaxexception.getInput() != null && commandsyntaxexception.getCursor() >= 0) {
/* 192 */         int j = Math.min(commandsyntaxexception.getInput().length(), commandsyntaxexception.getCursor());
/* 193 */         IChatMutableComponent ichatmutablecomponent = (new ChatComponentText("")).a(EnumChatFormat.GRAY).format(chatmodifier -> chatmodifier.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, label)));
/*     */ 
/*     */ 
/*     */         
/* 197 */         if (j > 10) {
/* 198 */           ichatmutablecomponent.c("...");
/*     */         }
/*     */         
/* 201 */         ichatmutablecomponent.c(commandsyntaxexception.getInput().substring(Math.max(0, j - 10), j));
/* 202 */         if (j < commandsyntaxexception.getInput().length()) {
/* 203 */           IChatMutableComponent ichatmutablecomponent1 = (new ChatComponentText(commandsyntaxexception.getInput().substring(j))).a(new EnumChatFormat[] { EnumChatFormat.RED, EnumChatFormat.UNDERLINE });
/*     */           
/* 205 */           ichatmutablecomponent.addSibling(ichatmutablecomponent1);
/*     */         } 
/*     */         
/* 208 */         ichatmutablecomponent.addSibling((new ChatMessage("command.context.here")).a(new EnumChatFormat[] { EnumChatFormat.RED, EnumChatFormat.ITALIC }));
/* 209 */         commandlistenerwrapper.sendFailureMessage(ichatmutablecomponent);
/*     */       } 
/*     */       
/* 212 */       byte b1 = 0;
/* 213 */       return b1;
/* 214 */     } catch (Exception exception) {
/* 215 */       ChatComponentText chatcomponenttext = new ChatComponentText((exception.getMessage() == null) ? exception.getClass().getName() : exception.getMessage());
/*     */       
/* 217 */       if (LOGGER.isDebugEnabled()) {
/* 218 */         LOGGER.error("Command exception: {}", s, exception);
/* 219 */         StackTraceElement[] astacktraceelement = exception.getStackTrace();
/*     */         
/* 221 */         for (int k = 0; k < Math.min(astacktraceelement.length, 3); k++) {
/* 222 */           chatcomponenttext.c("\n\n").c(astacktraceelement[k].getMethodName()).c("\n ").c(astacktraceelement[k].getFileName()).c(":").c(String.valueOf(astacktraceelement[k].getLineNumber()));
/*     */         }
/*     */       } 
/*     */       
/* 226 */       commandlistenerwrapper.sendFailureMessage((new ChatMessage("command.failed")).format(chatmodifier -> chatmodifier.setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)chatcomponenttext))));
/*     */ 
/*     */       
/* 229 */       if (SharedConstants.d) {
/* 230 */         commandlistenerwrapper.sendFailureMessage(new ChatComponentText(SystemUtils.d(exception)));
/* 231 */         LOGGER.error("'" + s + "' threw an exception", exception);
/*     */       } 
/*     */       
/* 234 */       b0 = 0;
/*     */     } finally {
/*     */       
/* 237 */       commandlistenerwrapper.getServer().getMethodProfiler().exit();
/*     */     } 
/*     */     
/* 240 */     return b0;
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 244 */     if (SpigotConfig.tabComplete < 0) {
/*     */       return;
/*     */     }
/*     */     
/* 248 */     ForkJoinPool.commonPool().execute(() -> sendAsync(entityplayer));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendAsync(EntityPlayer entityplayer) {
/* 255 */     Map<CommandNode<CommandListenerWrapper>, CommandNode<ICompletionProvider>> map = Maps.newIdentityHashMap();
/* 256 */     RootCommandNode vanillaRoot = new RootCommandNode();
/*     */     
/* 258 */     RootCommandNode<CommandListenerWrapper> vanilla = entityplayer.server.vanillaCommandDispatcher.a().getRoot();
/* 259 */     map.put(vanilla, vanillaRoot);
/* 260 */     a((CommandNode<CommandListenerWrapper>)vanilla, (CommandNode<ICompletionProvider>)vanillaRoot, entityplayer.getCommandListener(), map);
/*     */ 
/*     */     
/* 263 */     RootCommandNode<ICompletionProvider> rootcommandnode = new RootCommandNode();
/*     */     
/* 265 */     map.put(this.b.getRoot(), rootcommandnode);
/* 266 */     a((CommandNode<CommandListenerWrapper>)this.b.getRoot(), (CommandNode<ICompletionProvider>)rootcommandnode, entityplayer.getCommandListener(), map);
/*     */     
/* 268 */     Collection<String> bukkit = new LinkedHashSet<>();
/* 269 */     for (CommandNode node : rootcommandnode.getChildren()) {
/* 270 */       bukkit.add(node.getName());
/*     */     }
/*     */     
/* 273 */     (new AsyncPlayerSendCommandsEvent((Player)entityplayer.getBukkitEntity(), rootcommandnode, false)).callEvent();
/* 274 */     MinecraftServer.getServer().execute(() -> runSync(entityplayer, bukkit, rootcommandnode));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void runSync(EntityPlayer entityplayer, Collection<String> bukkit, RootCommandNode<ICompletionProvider> rootcommandnode) {
/* 281 */     (new AsyncPlayerSendCommandsEvent((Player)entityplayer.getBukkitEntity(), rootcommandnode, false)).callEvent();
/* 282 */     PlayerCommandSendEvent event = new PlayerCommandSendEvent((Player)entityplayer.getBukkitEntity(), new LinkedHashSet<>(bukkit));
/* 283 */     event.getPlayer().getServer().getPluginManager().callEvent((Event)event);
/*     */ 
/*     */     
/* 286 */     for (String orig : bukkit) {
/* 287 */       if (!event.getCommands().contains(orig)) {
/* 288 */         rootcommandnode.removeCommand(orig);
/*     */       }
/*     */     } 
/*     */     
/* 292 */     entityplayer.playerConnection.sendPacket(new PacketPlayOutCommands(rootcommandnode));
/*     */   }
/*     */   
/*     */   private void a(CommandNode<CommandListenerWrapper> commandnode, CommandNode<ICompletionProvider> commandnode1, CommandListenerWrapper commandlistenerwrapper, Map<CommandNode<CommandListenerWrapper>, CommandNode<ICompletionProvider>> map) {
/* 296 */     Iterator<CommandNode<CommandListenerWrapper>> iterator = commandnode.getChildren().iterator();
/*     */     
/* 298 */     while (iterator.hasNext()) {
/* 299 */       CommandNode<CommandListenerWrapper> commandnode2 = iterator.next();
/* 300 */       if (!SpigotConfig.sendNamespaced && commandnode2.getName().contains(":"))
/*     */         continue; 
/* 302 */       if (commandnode2.canUse(commandlistenerwrapper)) {
/* 303 */         ArgumentBuilder argumentbuilder = commandnode2.createBuilder();
/*     */         
/* 305 */         argumentbuilder.requires(icompletionprovider -> true);
/*     */ 
/*     */         
/* 308 */         if (argumentbuilder.getCommand() != null) {
/* 309 */           argumentbuilder.executes(commandcontext -> 0);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 314 */         if (argumentbuilder instanceof RequiredArgumentBuilder) {
/* 315 */           RequiredArgumentBuilder<ICompletionProvider, ?> requiredargumentbuilder = (RequiredArgumentBuilder<ICompletionProvider, ?>)argumentbuilder;
/*     */           
/* 317 */           if (requiredargumentbuilder.getSuggestionsProvider() != null) {
/* 318 */             requiredargumentbuilder.suggests(CompletionProviders.b(requiredargumentbuilder.getSuggestionsProvider()));
/*     */           }
/*     */         } 
/*     */         
/* 322 */         if (argumentbuilder.getRedirect() != null) {
/* 323 */           argumentbuilder.redirect(map.get(argumentbuilder.getRedirect()));
/*     */         }
/*     */         
/* 326 */         CommandNode<ICompletionProvider> commandnode3 = argumentbuilder.build();
/*     */         
/* 328 */         map.put(commandnode2, commandnode3);
/* 329 */         commandnode1.addChild(commandnode3);
/* 330 */         if (!commandnode2.getChildren().isEmpty()) {
/* 331 */           a(commandnode2, commandnode3, commandlistenerwrapper, map);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static LiteralArgumentBuilder<CommandListenerWrapper> a(String s) {
/* 339 */     return LiteralArgumentBuilder.literal(s);
/*     */   }
/*     */   
/*     */   public static <T> RequiredArgumentBuilder<CommandListenerWrapper, T> a(String s, ArgumentType<T> argumenttype) {
/* 343 */     return RequiredArgumentBuilder.argument(s, argumenttype);
/*     */   }
/*     */   
/*     */   public static Predicate<String> a(b commanddispatcher_b) {
/* 347 */     return s -> {
/*     */         try {
/*     */           commanddispatcher_b.parse(new StringReader(s));
/*     */           return true;
/* 351 */         } catch (CommandSyntaxException commandsyntaxexception) {
/*     */           return false;
/*     */         } 
/*     */       };
/*     */   }
/*     */   
/*     */   public com.mojang.brigadier.CommandDispatcher<CommandListenerWrapper> a() {
/* 358 */     return this.b;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <S> CommandSyntaxException a(ParseResults<S> parseresults) {
/* 363 */     return !parseresults.getReader().canRead() ? null : ((parseresults.getExceptions().size() == 1) ? parseresults.getExceptions().values().iterator().next() : (parseresults.getContext().getRange().isEmpty() ? CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownCommand().createWithContext(parseresults.getReader()) : CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().createWithContext(parseresults.getReader())));
/*     */   }
/*     */   
/*     */   public static void b() {
/* 367 */     RootCommandNode<CommandListenerWrapper> rootcommandnode = (new CommandDispatcher(ServerType.ALL)).a().getRoot();
/* 368 */     Set<ArgumentType<?>> set = ArgumentRegistry.a((CommandNode<CommandListenerWrapper>)rootcommandnode);
/*     */ 
/*     */     
/* 371 */     Set<ArgumentType<?>> set1 = (Set<ArgumentType<?>>)set.stream().filter(argumenttype -> !ArgumentRegistry.a(argumenttype)).collect(Collectors.toSet());
/*     */     
/* 373 */     if (!set1.isEmpty()) {
/* 374 */       LOGGER.warn("Missing type registration for following arguments:\n {}", set1.stream().map(argumenttype -> "\t" + argumenttype)
/*     */           
/* 376 */           .collect(Collectors.joining(",\n")));
/* 377 */       throw new IllegalStateException("Unregistered argument types");
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum ServerType
/*     */   {
/* 383 */     ALL(true, true), DEDICATED(false, true), INTEGRATED(true, false);
/*     */     
/*     */     private final boolean e;
/*     */     private final boolean d;
/*     */     
/*     */     ServerType(boolean flag, boolean flag1) {
/* 389 */       this.d = flag;
/* 390 */       this.e = flag1;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface b {
/*     */     void parse(StringReader param1StringReader) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDispatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */