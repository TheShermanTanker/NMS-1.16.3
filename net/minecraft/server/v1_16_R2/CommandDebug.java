/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.FileSystem;
/*    */ import java.nio.file.Files;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.attribute.FileAttribute;
/*    */ import java.nio.file.spi.FileSystemProvider;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Date;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandDebug
/*    */ {
/* 31 */   private static final Logger LOGGER = LogManager.getLogger();
/* 32 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.debug.notRunning")); @Nullable
/* 33 */   private static final FileSystemProvider d; private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.debug.alreadyRunning"));
/*    */   
/*    */   static {
/* 36 */     d = FileSystemProvider.installedProviders().stream().filter(var0 -> var0.getScheme().equalsIgnoreCase("jar")).findFirst().orElse(null);
/*    */   }
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 39 */     var0.register(
/* 40 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("debug")
/* 41 */         .requires(var0 -> var0.hasPermission(3)))
/* 42 */         .then(CommandDispatcher.a("start").executes(var0 -> a((CommandListenerWrapper)var0.getSource()))))
/* 43 */         .then(CommandDispatcher.a("stop").executes(var0 -> b((CommandListenerWrapper)var0.getSource()))))
/* 44 */         .then(CommandDispatcher.a("report").executes(var0 -> c((CommandListenerWrapper)var0.getSource()))));
/*    */   }
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0) throws CommandSyntaxException {
/* 49 */     MinecraftServer var1 = var0.getServer();
/* 50 */     if (var1.aR()) {
/* 51 */       throw c.create();
/*    */     }
/* 53 */     var1.aS();
/* 54 */     var0.sendMessage(new ChatMessage("commands.debug.started", new Object[] { "Started the debug profiler. Type '/debug stop' to stop it." }), true);
/* 55 */     return 0;
/*    */   }
/*    */   
/*    */   private static int b(CommandListenerWrapper var0) throws CommandSyntaxException {
/* 59 */     MinecraftServer var1 = var0.getServer();
/* 60 */     if (!var1.aR()) {
/* 61 */       throw b.create();
/*    */     }
/* 63 */     MethodProfilerResults var2 = var1.aT();
/* 64 */     File var3 = new File(var1.c("debug"), "profile-results-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + ".txt");
/*    */     
/* 66 */     var2.a(var3);
/*    */     
/* 68 */     float var4 = (float)var2.g() / 1.0E9F;
/* 69 */     float var5 = var2.f() / var4;
/* 70 */     var0.sendMessage(new ChatMessage("commands.debug.stopped", new Object[] { String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(var4) }), Integer.valueOf(var2.f()), String.format("%.2f", new Object[] { Float.valueOf(var5) }) }), true);
/*    */     
/* 72 */     return MathHelper.d(var5);
/*    */   }
/*    */   
/*    */   private static int c(CommandListenerWrapper var0) {
/* 76 */     MinecraftServer var1 = var0.getServer();
/* 77 */     String var2 = "debug-report-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date());
/*    */ 
/*    */     
/*    */     try {
/* 81 */       Path var4 = var1.c("debug").toPath();
/* 82 */       Files.createDirectories(var4, (FileAttribute<?>[])new FileAttribute[0]);
/* 83 */       if (SharedConstants.d || d == null) {
/* 84 */         Path var3 = var4.resolve(var2);
/* 85 */         var1.a(var3);
/*    */       } else {
/* 87 */         Path var3 = var4.resolve(var2 + ".zip");
/* 88 */         try (FileSystem var5 = d.newFileSystem(var3, (Map<String, ?>)ImmutableMap.of("create", "true"))) {
/* 89 */           var1.a(var5.getPath("/", new String[0]));
/*    */         } 
/*    */       } 
/*    */       
/* 93 */       var0.sendMessage(new ChatMessage("commands.debug.reportSaved", new Object[] { var2 }), false);
/* 94 */       return 1;
/* 95 */     } catch (IOException var3) {
/* 96 */       LOGGER.error("Failed to save debug dump", var3);
/* 97 */       var0.sendFailureMessage(new ChatMessage("commands.debug.reportFailed"));
/* 98 */       return 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */