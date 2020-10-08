/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class CommandReload {
/* 12 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public static void a(Collection<String> collection, CommandListenerWrapper commandlistenerwrapper) {
/* 15 */     commandlistenerwrapper.getServer().a(collection).exceptionally(throwable -> {
/*    */           LOGGER.warn("Failed to execute reload", throwable);
/*    */           commandlistenerwrapper.sendFailureMessage(new ChatMessage("commands.reload.failure"));
/*    */           return null;
/*    */         });
/*    */   }
/*    */   
/*    */   private static Collection<String> a(ResourcePackRepository resourcepackrepository, SaveData savedata, Collection<String> collection) {
/* 23 */     resourcepackrepository.a();
/* 24 */     Collection<String> collection1 = Lists.newArrayList(collection);
/* 25 */     Collection<String> collection2 = savedata.D().b();
/* 26 */     Iterator<String> iterator = resourcepackrepository.b().iterator();
/*    */     
/* 28 */     while (iterator.hasNext()) {
/* 29 */       String s = iterator.next();
/*    */       
/* 31 */       if (!collection2.contains(s) && !collection1.contains(s)) {
/* 32 */         collection1.add(s);
/*    */       }
/*    */     } 
/*    */     
/* 36 */     return collection1;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void reload(MinecraftServer minecraftserver) {
/* 41 */     ResourcePackRepository resourcepackrepository = minecraftserver.getResourcePackRepository();
/* 42 */     SaveData savedata = minecraftserver.getSaveData();
/* 43 */     Collection<String> collection = resourcepackrepository.d();
/* 44 */     Collection<String> collection1 = a(resourcepackrepository, savedata, collection);
/* 45 */     minecraftserver.a(collection1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/* 50 */     com_mojang_brigadier_commanddispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("reload").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2)))
/*    */         
/* 52 */         .executes(commandcontext -> {
/*    */             CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper)commandcontext.getSource();
/*    */             MinecraftServer minecraftserver = commandlistenerwrapper.getServer();
/*    */             ResourcePackRepository resourcepackrepository = minecraftserver.getResourcePackRepository();
/*    */             SaveData savedata = minecraftserver.getSaveData();
/*    */             Collection<String> collection = resourcepackrepository.d();
/*    */             Collection<String> collection1 = a(resourcepackrepository, savedata, collection);
/*    */             commandlistenerwrapper.sendMessage(new ChatMessage("commands.reload.success"), true);
/*    */             a(collection1, commandlistenerwrapper);
/*    */             return 0;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandReload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */