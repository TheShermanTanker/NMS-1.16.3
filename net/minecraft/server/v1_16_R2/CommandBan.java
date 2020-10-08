/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ public class CommandBan
/*    */ {
/* 27 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.ban.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 30 */     var0.register(
/* 31 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("ban")
/* 32 */         .requires(var0 -> var0.hasPermission(3)))
/* 33 */         .then((
/* 34 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/* 35 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets"), null)))
/* 36 */           .then(
/* 37 */             CommandDispatcher.<T>a("reason", ArgumentChat.a())
/* 38 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets"), ArgumentChat.a(var0, "reason"))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<GameProfile> var1, @Nullable IChatBaseComponent var2) throws CommandSyntaxException {
/* 45 */     GameProfileBanList var3 = var0.getServer().getPlayerList().getProfileBans();
/* 46 */     int var4 = 0;
/*    */     
/* 48 */     for (GameProfile var6 : var1) {
/* 49 */       if (!var3.isBanned(var6)) {
/* 50 */         GameProfileBanEntry var7 = new GameProfileBanEntry(var6, null, var0.getName(), null, (var2 == null) ? null : var2.getString());
/* 51 */         var3.add(var7);
/* 52 */         var4++;
/* 53 */         var0.sendMessage(new ChatMessage("commands.ban.success", new Object[] { ChatComponentUtils.a(var6), var7.getReason() }), true);
/*    */         
/* 55 */         EntityPlayer var8 = var0.getServer().getPlayerList().getPlayer(var6.getId());
/* 56 */         if (var8 != null) {
/* 57 */           var8.playerConnection.disconnect(new ChatMessage("multiplayer.disconnect.banned"));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 62 */     if (var4 == 0) {
/* 63 */       throw a.create();
/*    */     }
/*    */     
/* 66 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandBan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */