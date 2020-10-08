/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ public class ArgumentChatComponent
/*    */   implements ArgumentType<IChatBaseComponent>
/*    */ {
/* 17 */   private static final Collection<String> b = Arrays.asList(new String[] { "\"hello world\"", "\"\"", "\"{\"text\":\"hello world\"}", "[\"\"]" }); static {
/* 18 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.component.invalid", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static IChatBaseComponent a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 24 */     return (IChatBaseComponent)var0.getArgument(var1, IChatBaseComponent.class);
/*    */   }
/*    */   
/*    */   public static ArgumentChatComponent a() {
/* 28 */     return new ArgumentChatComponent();
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent parse(StringReader var0) throws CommandSyntaxException {
/*    */     try {
/* 34 */       IChatBaseComponent var1 = IChatBaseComponent.ChatSerializer.a(var0);
/* 35 */       if (var1 == null) {
/* 36 */         throw a.createWithContext(var0, "empty");
/*    */       }
/* 38 */       return var1;
/* 39 */     } catch (JsonParseException var1) {
/* 40 */       String var2 = (var1.getCause() != null) ? var1.getCause().getMessage() : var1.getMessage();
/* 41 */       throw a.createWithContext(var0, var2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 47 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentChatComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */