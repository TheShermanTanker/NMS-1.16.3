/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.List;
/*    */ import java.util.regex.Matcher;
/*    */ import java.util.regex.Pattern;
/*    */ import javax.annotation.Nullable;
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
/*    */ public class CommandBanIp
/*    */ {
/* 28 */   public static final Pattern a = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
/* 29 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.banip.invalid"));
/* 30 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.banip.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 33 */     var0.register(
/* 34 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("ban-ip")
/* 35 */         .requires(var0 -> var0.hasPermission(3)))
/* 36 */         .then((
/* 37 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("target", (ArgumentType<T>)StringArgumentType.word())
/* 38 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "target"), null)))
/* 39 */           .then(
/* 40 */             CommandDispatcher.<T>a("reason", ArgumentChat.a())
/* 41 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "target"), ArgumentChat.a(var0, "reason"))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, String var1, @Nullable IChatBaseComponent var2) throws CommandSyntaxException {
/* 48 */     Matcher var3 = a.matcher(var1);
/* 49 */     if (var3.matches()) {
/* 50 */       return b(var0, var1, var2);
/*    */     }
/* 52 */     EntityPlayer var4 = var0.getServer().getPlayerList().getPlayer(var1);
/* 53 */     if (var4 != null) {
/* 54 */       return b(var0, var4.v(), var2);
/*    */     }
/*    */     
/* 57 */     throw b.create();
/*    */   }
/*    */   
/*    */   private static int b(CommandListenerWrapper var0, String var1, @Nullable IChatBaseComponent var2) throws CommandSyntaxException {
/* 61 */     IpBanList var3 = var0.getServer().getPlayerList().getIPBans();
/* 62 */     if (var3.a(var1)) {
/* 63 */       throw c.create();
/*    */     }
/* 65 */     List<EntityPlayer> var4 = var0.getServer().getPlayerList().b(var1);
/* 66 */     IpBanEntry var5 = new IpBanEntry(var1, null, var0.getName(), null, (var2 == null) ? null : var2.getString());
/* 67 */     var3.add(var5);
/*    */     
/* 69 */     var0.sendMessage(new ChatMessage("commands.banip.success", new Object[] { var1, var5.getReason() }), true);
/* 70 */     if (!var4.isEmpty()) {
/* 71 */       var0.sendMessage(new ChatMessage("commands.banip.info", new Object[] { Integer.valueOf(var4.size()), EntitySelector.a((List)var4) }), true);
/*    */     }
/*    */     
/* 74 */     for (EntityPlayer var7 : var4) {
/* 75 */       var7.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.ip_banned"));
/*    */     }
/*    */     
/* 78 */     return var4.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandBanIp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */