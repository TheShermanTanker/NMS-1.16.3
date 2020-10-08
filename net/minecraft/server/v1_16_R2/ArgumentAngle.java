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
/*    */ 
/*    */ 
/*    */ public class ArgumentAngle
/*    */   implements ArgumentType<ArgumentAngle.a>
/*    */ {
/* 17 */   private static final Collection<String> b = Arrays.asList(new String[] { "0", "~", "~-5" });
/* 18 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.angle.incomplete"));
/*    */   
/*    */   public static ArgumentAngle a() {
/* 21 */     return new ArgumentAngle();
/*    */   }
/*    */   
/*    */   public static float a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 25 */     return ((a)var0.getArgument(var1, a.class)).a((CommandListenerWrapper)var0.getSource());
/*    */   }
/*    */ 
/*    */   
/*    */   public a parse(StringReader var0) throws CommandSyntaxException {
/* 30 */     if (!var0.canRead()) {
/* 31 */       throw a.createWithContext(var0);
/*    */     }
/*    */     
/* 34 */     boolean var1 = ArgumentParserPosition.b(var0);
/* 35 */     float var2 = (var0.canRead() && var0.peek() != ' ') ? var0.readFloat() : 0.0F;
/* 36 */     return new a(var2, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 41 */     return b;
/*    */   }
/*    */   
/*    */   public static final class a {
/*    */     private final float a;
/*    */     private final boolean b;
/*    */     
/*    */     private a(float var0, boolean var1) {
/* 49 */       this.a = var0;
/* 50 */       this.b = var1;
/*    */     }
/*    */     
/*    */     public float a(CommandListenerWrapper var0) {
/* 54 */       return MathHelper.g(this.b ? (this.a + (var0.i()).j) : this.a);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentAngle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */