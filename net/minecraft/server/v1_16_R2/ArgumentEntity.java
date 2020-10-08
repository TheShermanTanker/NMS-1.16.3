/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ public class ArgumentEntity
/*     */   implements ArgumentType<EntitySelector> {
/*  19 */   private static final Collection<String> g = Arrays.asList(new String[] { "Player", "0123", "@e", "@e[type=foo]", "dd12be42-52a9-4a91-a8a1-11c01849e498" });
/*  20 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.entity.toomany"));
/*  21 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.player.toomany"));
/*  22 */   public static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.player.entities"));
/*  23 */   public static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("argument.entity.notfound.entity"));
/*  24 */   public static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("argument.entity.notfound.player"));
/*  25 */   public static final SimpleCommandExceptionType f = new SimpleCommandExceptionType(new ChatMessage("argument.entity.selector.not_allowed"));
/*     */   private final boolean h;
/*     */   private final boolean i;
/*     */   
/*     */   protected ArgumentEntity(boolean flag, boolean flag1) {
/*  30 */     this.h = flag;
/*  31 */     this.i = flag1;
/*     */   }
/*     */   
/*     */   public static ArgumentEntity a() {
/*  35 */     return new ArgumentEntity(true, false);
/*     */   }
/*     */   
/*     */   public static Entity a(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  39 */     return ((EntitySelector)commandcontext.getArgument(s, EntitySelector.class)).a((CommandListenerWrapper)commandcontext.getSource());
/*     */   }
/*     */   
/*     */   public static ArgumentEntity multipleEntities() {
/*  43 */     return new ArgumentEntity(false, false);
/*     */   }
/*     */   
/*     */   public static Collection<? extends Entity> b(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  47 */     Collection<? extends Entity> collection = c(commandcontext, s);
/*     */     
/*  49 */     if (collection.isEmpty()) {
/*  50 */       throw d.create();
/*     */     }
/*  52 */     return collection;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Collection<? extends Entity> c(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  57 */     return ((EntitySelector)commandcontext.getArgument(s, EntitySelector.class)).getEntities((CommandListenerWrapper)commandcontext.getSource());
/*     */   }
/*     */   
/*     */   public static Collection<EntityPlayer> d(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  61 */     return ((EntitySelector)commandcontext.getArgument(s, EntitySelector.class)).d((CommandListenerWrapper)commandcontext.getSource());
/*     */   }
/*     */   
/*     */   public static ArgumentEntity c() {
/*  65 */     return new ArgumentEntity(true, true);
/*     */   }
/*     */   
/*     */   public static EntityPlayer e(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  69 */     return ((EntitySelector)commandcontext.getArgument(s, EntitySelector.class)).c((CommandListenerWrapper)commandcontext.getSource());
/*     */   }
/*     */   
/*     */   public static ArgumentEntity d() {
/*  73 */     return new ArgumentEntity(false, true);
/*     */   }
/*     */   
/*     */   public static Collection<EntityPlayer> f(CommandContext<CommandListenerWrapper> commandcontext, String s) throws CommandSyntaxException {
/*  77 */     List<EntityPlayer> list = ((EntitySelector)commandcontext.getArgument(s, EntitySelector.class)).d((CommandListenerWrapper)commandcontext.getSource());
/*     */     
/*  79 */     if (list.isEmpty()) {
/*  80 */       throw e.create();
/*     */     }
/*  82 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySelector parse(StringReader stringreader) throws CommandSyntaxException {
/*  88 */     return parse(stringreader, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySelector parse(StringReader stringreader, boolean overridePermissions) throws CommandSyntaxException {
/*  93 */     boolean flag = false;
/*  94 */     ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader);
/*  95 */     EntitySelector entityselector = argumentparserselector.parse(overridePermissions);
/*     */     
/*  97 */     if (entityselector.a() > 1 && this.h) {
/*  98 */       if (this.i) {
/*  99 */         stringreader.setCursor(0);
/* 100 */         throw b.createWithContext(stringreader);
/*     */       } 
/* 102 */       stringreader.setCursor(0);
/* 103 */       throw a.createWithContext(stringreader);
/*     */     } 
/* 105 */     if (entityselector.b() && this.i && !entityselector.c()) {
/* 106 */       stringreader.setCursor(0);
/* 107 */       throw c.createWithContext(stringreader);
/*     */     } 
/* 109 */     return entityselector;
/*     */   }
/*     */ 
/*     */   
/*     */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
/* 114 */     if (commandcontext.getSource() instanceof ICompletionProvider) {
/* 115 */       StringReader stringreader = new StringReader(suggestionsbuilder.getInput());
/*     */       
/* 117 */       stringreader.setCursor(suggestionsbuilder.getStart());
/* 118 */       ICompletionProvider icompletionprovider = (ICompletionProvider)commandcontext.getSource();
/* 119 */       ArgumentParserSelector argumentparserselector = new ArgumentParserSelector(stringreader, icompletionprovider.hasPermission(2));
/*     */       
/*     */       try {
/* 122 */         argumentparserselector.parse();
/* 123 */       } catch (CommandSyntaxException commandSyntaxException) {}
/*     */ 
/*     */ 
/*     */       
/* 127 */       return argumentparserselector.a(suggestionsbuilder, suggestionsbuilder1 -> {
/*     */             Collection<String> collection = icompletionprovider.l();
/*     */             
/*     */             Iterable<String> iterable = this.i ? collection : Iterables.concat(collection, icompletionprovider.r());
/*     */             ICompletionProvider.b(iterable, suggestionsbuilder1);
/*     */           });
/*     */     } 
/* 134 */     return Suggestions.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/* 139 */     return g;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements ArgumentSerializer<ArgumentEntity>
/*     */   {
/*     */     public void a(ArgumentEntity argumententity, PacketDataSerializer packetdataserializer) {
/* 147 */       byte b0 = 0;
/*     */       
/* 149 */       if (argumententity.h) {
/* 150 */         b0 = (byte)(b0 | 0x1);
/*     */       }
/*     */       
/* 153 */       if (argumententity.i) {
/* 154 */         b0 = (byte)(b0 | 0x2);
/*     */       }
/*     */       
/* 157 */       packetdataserializer.writeByte(b0);
/*     */     }
/*     */ 
/*     */     
/*     */     public ArgumentEntity b(PacketDataSerializer packetdataserializer) {
/* 162 */       byte b0 = packetdataserializer.readByte();
/*     */       
/* 164 */       return new ArgumentEntity(((b0 & 0x1) != 0), ((b0 & 0x2) != 0));
/*     */     }
/*     */     
/*     */     public void a(ArgumentEntity argumententity, JsonObject jsonobject) {
/* 168 */       jsonobject.addProperty("amount", argumententity.h ? "single" : "multiple");
/* 169 */       jsonobject.addProperty("type", argumententity.i ? "players" : "entities");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */