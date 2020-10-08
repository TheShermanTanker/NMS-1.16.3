/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ 
/*    */ 
/*    */ public class ArgumentTag
/*    */   implements ArgumentType<ArgumentTag.a>
/*    */ {
/*    */   private static final DynamicCommandExceptionType b;
/*    */   private static final DynamicCommandExceptionType c;
/* 21 */   private static final Collection<String> a = Arrays.asList(new String[] { "foo", "foo:bar", "#foo" }); static {
/* 22 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.function.tag.unknown", new Object[] { var0 }));
/* 23 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.function.unknown", new Object[] { var0 }));
/*    */   }
/*    */   public static ArgumentTag a() {
/* 26 */     return new ArgumentTag();
/*    */   }
/*    */ 
/*    */   
/*    */   public a parse(StringReader var0) throws CommandSyntaxException {
/* 31 */     if (var0.canRead() && var0.peek() == '#') {
/* 32 */       var0.skip();
/* 33 */       MinecraftKey minecraftKey = MinecraftKey.a(var0);
/* 34 */       return new a(this, minecraftKey)
/*    */         {
/*    */           public Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 37 */             Tag<CustomFunction> var1 = ArgumentTag.a(var0, this.a);
/* 38 */             return var1.getTagged();
/*    */           }
/*    */ 
/*    */           
/*    */           public Pair<MinecraftKey, Either<CustomFunction, Tag<CustomFunction>>> b(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 43 */             return Pair.of(this.a, Either.right(ArgumentTag.a(var0, this.a)));
/*    */           }
/*    */         };
/*    */     } 
/*    */     
/* 48 */     MinecraftKey var1 = MinecraftKey.a(var0);
/* 49 */     return new a(this, var1)
/*    */       {
/*    */         public Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 52 */           return Collections.singleton(ArgumentTag.b(var0, this.a));
/*    */         }
/*    */ 
/*    */         
/*    */         public Pair<MinecraftKey, Either<CustomFunction, Tag<CustomFunction>>> b(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 57 */           return Pair.of(this.a, Either.left(ArgumentTag.b(var0, this.a)));
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   private static CustomFunction c(CommandContext<CommandListenerWrapper> var0, MinecraftKey var1) throws CommandSyntaxException {
/* 63 */     return ((CommandListenerWrapper)var0.getSource()).getServer().getFunctionData().a(var1)
/* 64 */       .<Throwable>orElseThrow(() -> c.create(var0.toString()));
/*    */   }
/*    */   
/*    */   private static Tag<CustomFunction> d(CommandContext<CommandListenerWrapper> var0, MinecraftKey var1) throws CommandSyntaxException {
/* 68 */     Tag<CustomFunction> var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getFunctionData().b(var1);
/* 69 */     if (var2 == null) {
/* 70 */       throw b.create(var1.toString());
/*    */     }
/* 72 */     return var2;
/*    */   }
/*    */   
/*    */   public static Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 76 */     return ((a)var0.getArgument(var1, a.class)).a(var0);
/*    */   }
/*    */   
/*    */   public static Pair<MinecraftKey, Either<CustomFunction, Tag<CustomFunction>>> b(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 80 */     return ((a)var0.getArgument(var1, a.class)).b(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 85 */     return a;
/*    */   }
/*    */   
/*    */   public static interface a {
/*    */     Collection<CustomFunction> a(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*    */     
/*    */     Pair<MinecraftKey, Either<CustomFunction, Tag<CustomFunction>>> b(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */