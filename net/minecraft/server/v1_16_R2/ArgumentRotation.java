/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ public class ArgumentRotation
/*    */   implements ArgumentType<IVectorPosition>
/*    */ {
/* 15 */   private static final Collection<String> b = Arrays.asList(new String[] { "0 0", "~ ~", "~-5 ~5" });
/* 16 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.rotation.incomplete"));
/*    */   
/*    */   public static ArgumentRotation a() {
/* 19 */     return new ArgumentRotation();
/*    */   }
/*    */   
/*    */   public static IVectorPosition a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 23 */     return (IVectorPosition)var0.getArgument(var1, IVectorPosition.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public IVectorPosition parse(StringReader var0) throws CommandSyntaxException {
/* 28 */     int var1 = var0.getCursor();
/* 29 */     if (!var0.canRead()) {
/* 30 */       throw a.createWithContext(var0);
/*    */     }
/* 32 */     ArgumentParserPosition var2 = ArgumentParserPosition.a(var0, false);
/* 33 */     if (!var0.canRead() || var0.peek() != ' ') {
/* 34 */       var0.setCursor(var1);
/* 35 */       throw a.createWithContext(var0);
/*    */     } 
/* 37 */     var0.skip();
/* 38 */     ArgumentParserPosition var3 = ArgumentParserPosition.a(var0, false);
/* 39 */     return new VectorPosition(var3, var2, new ArgumentParserPosition(true, 0.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 44 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */