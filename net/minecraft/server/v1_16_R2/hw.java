/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class hw
/*    */   implements DebugReportProvider
/*    */ {
/* 17 */   private static final Gson b = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*    */   private final DebugReportGenerator c;
/*    */   
/*    */   public hw(DebugReportGenerator var0) {
/* 21 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) throws IOException {
/* 26 */     Path var1 = this.c.b().resolve("reports/commands.json");
/* 27 */     CommandDispatcher<CommandListenerWrapper> var2 = (new CommandDispatcher(CommandDispatcher.ServerType.ALL)).a();
/* 28 */     DebugReportProvider.a(b, var0, (JsonElement)ArgumentRegistry.a(var2, (CommandNode<CommandListenerWrapper>)var2.getRoot()), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 33 */     return "Command Syntax";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\hw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */