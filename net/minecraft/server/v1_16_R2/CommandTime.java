/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Iterator;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.world.TimeSkipEvent;
/*    */ 
/*    */ public class CommandTime {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/* 15 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("time").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*    */         
/* 17 */         .then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("set").then(CommandDispatcher.a("day").executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), 1000))))
/*    */           
/* 19 */           .then(CommandDispatcher.a("noon").executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), 6000))))
/*    */           
/* 21 */           .then(CommandDispatcher.a("night").executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), 13000))))
/*    */           
/* 23 */           .then(CommandDispatcher.a("midnight").executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), 18000))))
/*    */           
/* 25 */           .then(CommandDispatcher.<T>a("time", ArgumentTime.a()).executes(commandcontext -> a((CommandListenerWrapper)commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "time"))))))
/*    */         
/* 27 */         .then(CommandDispatcher.a("add").then(CommandDispatcher.<T>a("time", ArgumentTime.a()).executes(commandcontext -> b((CommandListenerWrapper)commandcontext.getSource(), IntegerArgumentType.getInteger(commandcontext, "time"))))))
/*    */         
/* 29 */         .then(((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("query").then(CommandDispatcher.a("daytime").executes(commandcontext -> c((CommandListenerWrapper)commandcontext.getSource(), a(((CommandListenerWrapper)commandcontext.getSource()).getWorld())))))
/*    */           
/* 31 */           .then(CommandDispatcher.a("gametime").executes(commandcontext -> c((CommandListenerWrapper)commandcontext.getSource(), (int)(((CommandListenerWrapper)commandcontext.getSource()).getWorld().getTime() % 2147483647L)))))
/*    */           
/* 33 */           .then(CommandDispatcher.a("day").executes(commandcontext -> c((CommandListenerWrapper)commandcontext.getSource(), (int)(((CommandListenerWrapper)commandcontext.getSource()).getWorld().getDayTime() / 24000L % 2147483647L))))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(WorldServer worldserver) {
/* 39 */     return (int)(worldserver.getDayTime() % 24000L);
/*    */   }
/*    */   
/*    */   private static int c(CommandListenerWrapper commandlistenerwrapper, int i) {
/* 43 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.time.query", new Object[] { Integer.valueOf(i) }), false);
/* 44 */     return i;
/*    */   }
/*    */   
/*    */   public static int a(CommandListenerWrapper commandlistenerwrapper, int i) {
/* 48 */     Iterator<WorldServer> iterator = commandlistenerwrapper.getServer().getWorlds().iterator();
/*    */     
/* 50 */     while (iterator.hasNext()) {
/* 51 */       WorldServer worldserver = iterator.next();
/*    */ 
/*    */       
/* 54 */       TimeSkipEvent event = new TimeSkipEvent((World)worldserver.getWorld(), TimeSkipEvent.SkipReason.COMMAND, i - worldserver.getDayTime());
/* 55 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 56 */       if (!event.isCancelled()) {
/* 57 */         worldserver.setDayTime(worldserver.getDayTime() + event.getSkipAmount());
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 62 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.time.set", new Object[] { Integer.valueOf(i) }), true);
/* 63 */     return a(commandlistenerwrapper.getWorld());
/*    */   }
/*    */   
/*    */   public static int b(CommandListenerWrapper commandlistenerwrapper, int i) {
/* 67 */     Iterator<WorldServer> iterator = commandlistenerwrapper.getServer().getWorlds().iterator();
/*    */     
/* 69 */     while (iterator.hasNext()) {
/* 70 */       WorldServer worldserver = iterator.next();
/*    */ 
/*    */       
/* 73 */       TimeSkipEvent event = new TimeSkipEvent((World)worldserver.getWorld(), TimeSkipEvent.SkipReason.COMMAND, i);
/* 74 */       Bukkit.getPluginManager().callEvent((Event)event);
/* 75 */       if (!event.isCancelled()) {
/* 76 */         worldserver.setDayTime(worldserver.getDayTime() + event.getSkipAmount());
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 81 */     int j = a(commandlistenerwrapper.getWorld());
/*    */     
/* 83 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.time.set", new Object[] { Integer.valueOf(j) }), true);
/* 84 */     return j;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */