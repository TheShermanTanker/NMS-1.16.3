/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ 
/*    */ public class CommandDifficulty {
/*    */   static {
/*  9 */     a = new DynamicCommandExceptionType(object -> new ChatMessage("commands.difficulty.failure", new Object[] { object }));
/*    */   }
/*    */   private static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/* 14 */     LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = CommandDispatcher.a("difficulty");
/* 15 */     EnumDifficulty[] aenumdifficulty = EnumDifficulty.values();
/* 16 */     int i = aenumdifficulty.length;
/*    */     
/* 18 */     for (int j = 0; j < i; j++) {
/* 19 */       EnumDifficulty enumdifficulty = aenumdifficulty[j];
/*    */       
/* 21 */       literalargumentbuilder.then(CommandDispatcher.a(enumdifficulty.c()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), enumdifficulty)));
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 26 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)literalargumentbuilder.requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*    */         
/* 28 */         .executes(commandcontext -> {
/*    */             EnumDifficulty enumdifficulty1 = ((CommandListenerWrapper)commandcontext.getSource()).getWorld().getDifficulty();
/*    */             ((CommandListenerWrapper)commandcontext.getSource()).sendMessage(new ChatMessage("commands.difficulty.query", new Object[] { enumdifficulty1.b() }), false);
/*    */             return enumdifficulty1.a();
/*    */           }));
/*    */   }
/*    */ 
/*    */   
/*    */   public static int a(CommandListenerWrapper commandlistenerwrapper, EnumDifficulty enumdifficulty) throws CommandSyntaxException {
/* 37 */     MinecraftServer minecraftserver = commandlistenerwrapper.getServer();
/*    */     
/* 39 */     WorldServer world = commandlistenerwrapper.getWorld();
/* 40 */     if (world.worldDataServer.getDifficulty() == enumdifficulty) {
/* 41 */       throw a.create(enumdifficulty.c());
/*    */     }
/* 43 */     minecraftserver.a(world, enumdifficulty, true);
/* 44 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.difficulty.success", new Object[] { enumdifficulty.b() }), true);
/* 45 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */