/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Locale;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandDataStorage
/*    */   implements CommandDataAccessor
/*    */ {
/*    */   private static final SuggestionProvider<CommandListenerWrapper> b;
/*    */   public static final Function<String, CommandData.c> a;
/*    */   private final PersistentCommandStorage c;
/*    */   private final MinecraftKey d;
/*    */   
/*    */   static {
/* 25 */     b = ((var0, var1) -> ICompletionProvider.a(b(var0).a(), var1));
/*    */ 
/*    */     
/* 28 */     a = (var0 -> new CommandData.c(var0)
/*    */       {
/*    */         public CommandDataAccessor a(CommandContext<CommandListenerWrapper> var0) {
/* 31 */           return new CommandDataStorage(CommandDataStorage.a(var0), ArgumentMinecraftKeyRegistered.e(var0, this.a));
/*    */         }
/*    */ 
/*    */         
/*    */         public ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder var0, Function<RequiredArgumentBuilder, ArgumentBuilder> var1) {
/* 36 */           return var0.then(CommandDispatcher.a("storage").then(var1.apply(CommandDispatcher.<T>a(this.a, ArgumentMinecraftKeyRegistered.a()).suggests(CommandDataStorage.c()))));
/*    */         }
/*    */       });
/*    */   }
/*    */   private static PersistentCommandStorage b(CommandContext<CommandListenerWrapper> var0) {
/* 41 */     return ((CommandListenerWrapper)var0.getSource()).getServer().aH();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private CommandDataStorage(PersistentCommandStorage var0, MinecraftKey var1) {
/* 48 */     this.c = var0;
/* 49 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NBTTagCompound var0) {
/* 54 */     this.c.a(this.d, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public NBTTagCompound a() {
/* 59 */     return this.c.a(this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent b() {
/* 64 */     return new ChatMessage("commands.data.storage.modified", new Object[] { this.d });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(NBTBase var0) {
/* 69 */     return new ChatMessage("commands.data.storage.query", new Object[] { this.d, var0.l() });
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent a(ArgumentNBTKey.h var0, double var1, int var3) {
/* 74 */     return new ChatMessage("commands.data.storage.get", new Object[] { var0, this.d, String.format(Locale.ROOT, "%.2f", new Object[] { Double.valueOf(var1) }), Integer.valueOf(var3) });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDataStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */