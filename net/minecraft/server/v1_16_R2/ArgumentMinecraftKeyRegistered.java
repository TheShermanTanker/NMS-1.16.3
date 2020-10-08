/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentMinecraftKeyRegistered
/*    */   implements ArgumentType<MinecraftKey>
/*    */ {
/*    */   private static final DynamicCommandExceptionType b;
/*    */   private static final DynamicCommandExceptionType c;
/* 23 */   private static final Collection<String> a = Arrays.asList(new String[] { "foo", "foo:bar", "012" }); static {
/* 24 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("advancement.advancementNotFound", new Object[] { var0 }));
/* 25 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("recipe.notFound", new Object[] { var0 }));
/* 26 */     d = new DynamicCommandExceptionType(var0 -> new ChatMessage("predicate.unknown", new Object[] { var0 }));
/* 27 */     e = new DynamicCommandExceptionType(var0 -> new ChatMessage("attribute.unknown", new Object[] { var0 }));
/*    */   }
/*    */   private static final DynamicCommandExceptionType d;
/*    */   private static final DynamicCommandExceptionType e;
/*    */   
/*    */   public static ArgumentMinecraftKeyRegistered a() {
/* 33 */     return new ArgumentMinecraftKeyRegistered();
/*    */   }
/*    */   
/*    */   public static Advancement a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 37 */     MinecraftKey var2 = (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/* 38 */     Advancement var3 = ((CommandListenerWrapper)var0.getSource()).getServer().getAdvancementData().a(var2);
/* 39 */     if (var3 == null) {
/* 40 */       throw b.create(var2);
/*    */     }
/* 42 */     return var3;
/*    */   }
/*    */   
/*    */   public static IRecipe<?> b(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 46 */     CraftingManager var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getCraftingManager();
/* 47 */     MinecraftKey var3 = (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/*    */     
/* 49 */     return var2.getRecipe(var3).<Throwable>orElseThrow(() -> c.create(var0));
/*    */   }
/*    */   
/*    */   public static LootItemCondition c(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 53 */     MinecraftKey var2 = (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/*    */     
/* 55 */     LootPredicateManager var3 = ((CommandListenerWrapper)var0.getSource()).getServer().getLootPredicateManager();
/* 56 */     LootItemCondition var4 = var3.a(var2);
/* 57 */     if (var4 == null) {
/* 58 */       throw d.create(var2);
/*    */     }
/* 60 */     return var4;
/*    */   }
/*    */   
/*    */   public static AttributeBase d(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 64 */     MinecraftKey var2 = (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/* 65 */     return (AttributeBase)IRegistry.ATTRIBUTE.getOptional(var2).orElseThrow(() -> e.create(var0));
/*    */   }
/*    */   
/*    */   public static MinecraftKey e(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 69 */     return (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public MinecraftKey parse(StringReader var0) throws CommandSyntaxException {
/* 74 */     return MinecraftKey.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 79 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentMinecraftKeyRegistered.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */