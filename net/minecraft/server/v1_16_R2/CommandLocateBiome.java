/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandLocateBiome
/*    */ {
/*    */   public static final DynamicCommandExceptionType a;
/*    */   private static final DynamicCommandExceptionType b;
/*    */   
/*    */   static {
/* 21 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.locatebiome.invalid", new Object[] { var0 }));
/* 22 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.locatebiome.notFound", new Object[] { var0 }));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 28 */     var0.register(
/* 29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("locatebiome")
/* 30 */         .requires(var0 -> var0.hasPermission(2)))
/* 31 */         .then(
/* 32 */           CommandDispatcher.<T>a("biome", ArgumentMinecraftKeyRegistered.a())
/* 33 */           .suggests(CompletionProviders.d)
/* 34 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), (MinecraftKey)var0.getArgument("biome", MinecraftKey.class)))));
/*    */   }
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, MinecraftKey var1) throws CommandSyntaxException {
/* 39 */     BiomeBase var2 = (BiomeBase)var0.getServer().getCustomRegistry().<BiomeBase>b(IRegistry.ay).getOptional(var1).orElseThrow(() -> a.create(var0));
/*    */     
/* 41 */     BlockPosition var3 = new BlockPosition(var0.getPosition());
/* 42 */     BlockPosition var4 = var0.getWorld().a(var2, var3, 6400, 8);
/* 43 */     String var5 = var1.toString();
/* 44 */     if (var4 == null) {
/* 45 */       throw b.create(var5);
/*    */     }
/* 47 */     return CommandLocate.a(var0, var5, var3, var4, "commands.locatebiome.success");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandLocateBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */