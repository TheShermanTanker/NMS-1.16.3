/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandLocate
/*    */ {
/* 24 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.locate.failed"));
/*    */ 
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 28 */     LiteralArgumentBuilder<CommandListenerWrapper> var1 = (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a("locate").requires(var0 -> var0.hasPermission(2));
/*    */     
/* 30 */     for (Map.Entry<String, StructureGenerator<?>> var3 : (Iterable<Map.Entry<String, StructureGenerator<?>>>)StructureGenerator.a.entrySet()) {
/* 31 */       var1 = (LiteralArgumentBuilder<CommandListenerWrapper>)var1.then(CommandDispatcher.a(var3.getKey()).executes(var1 -> a((CommandListenerWrapper)var1.getSource(), (StructureGenerator)var0.getValue())));
/*    */     }
/*    */     
/* 34 */     var0.register(var1);
/*    */   }
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, StructureGenerator<?> var1) throws CommandSyntaxException {
/* 38 */     BlockPosition var2 = new BlockPosition(var0.getPosition());
/* 39 */     BlockPosition var3 = var0.getWorld().a(var1, var2, 100, false);
/* 40 */     if (var3 == null) {
/* 41 */       throw a.create();
/*    */     }
/*    */     
/* 44 */     return a(var0, var1.i(), var2, var3, "commands.locate.success");
/*    */   }
/*    */   
/*    */   public static int a(CommandListenerWrapper var0, String var1, BlockPosition var2, BlockPosition var3, String var4) {
/* 48 */     int var5 = MathHelper.d(a(var2.getX(), var2.getZ(), var3.getX(), var3.getZ()));
/* 49 */     IChatBaseComponent var6 = ChatComponentUtils.a(new ChatMessage("chat.coordinates", new Object[] { Integer.valueOf(var3.getX()), "~", Integer.valueOf(var3.getZ()) })).format(var1 -> var1.setColor(EnumChatFormat.GREEN).setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/tp @s " + var0.getX() + " ~ " + var0.getZ())).setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatMessage("chat.coordinates.tooltip"))));
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 54 */     var0.sendMessage(new ChatMessage(var4, new Object[] { var1, var6, Integer.valueOf(var5) }), false);
/*    */     
/* 56 */     return var5;
/*    */   }
/*    */   
/*    */   private static float a(int var0, int var1, int var2, int var3) {
/* 60 */     int var4 = var2 - var0;
/* 61 */     int var5 = var3 - var1;
/* 62 */     return MathHelper.c((var4 * var4 + var5 * var5));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandLocate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */