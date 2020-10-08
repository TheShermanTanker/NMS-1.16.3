/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSaveOn
/*    */ {
/* 13 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.save.alreadyOn"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 16 */     var0.register(
/* 17 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("save-on")
/* 18 */         .requires(var0 -> var0.hasPermission(4)))
/* 19 */         .executes(var0 -> {
/*    */             CommandListenerWrapper var1 = (CommandListenerWrapper)var0.getSource();
/*    */             boolean var2 = false;
/*    */             for (WorldServer var4 : var1.getServer().getWorlds()) {
/*    */               if (var4 != null && var4.savingDisabled) {
/*    */                 var4.savingDisabled = false;
/*    */                 var2 = true;
/*    */               } 
/*    */             } 
/*    */             if (!var2)
/*    */               throw a.create(); 
/*    */             var1.sendMessage(new ChatMessage("commands.save.enabled"), true);
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSaveOn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */