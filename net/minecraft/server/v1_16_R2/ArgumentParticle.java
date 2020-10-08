/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentParticle
/*    */   implements ArgumentType<ParticleParam>
/*    */ {
/*    */   public static final DynamicCommandExceptionType a;
/* 23 */   private static final Collection<String> b = Arrays.asList(new String[] { "foo", "foo:bar", "particle with options" }); static {
/* 24 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("particle.notFound", new Object[] { var0 }));
/*    */   }
/*    */   public static ArgumentParticle a() {
/* 27 */     return new ArgumentParticle();
/*    */   }
/*    */   
/*    */   public static ParticleParam a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 31 */     return (ParticleParam)var0.getArgument(var1, ParticleParam.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public ParticleParam parse(StringReader var0) throws CommandSyntaxException {
/* 36 */     return b(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 41 */     return b;
/*    */   }
/*    */   
/*    */   public static ParticleParam b(StringReader var0) throws CommandSyntaxException {
/* 45 */     MinecraftKey var1 = MinecraftKey.a(var0);
/* 46 */     Particle<?> var2 = (Particle)IRegistry.PARTICLE_TYPE.getOptional(var1).orElseThrow(() -> a.create(var0));
/* 47 */     return (ParticleParam)a(var0, var2);
/*    */   }
/*    */   
/*    */   private static <T extends ParticleParam> T a(StringReader var0, Particle<T> var1) throws CommandSyntaxException {
/* 51 */     return var1.d().b(var1, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 56 */     return ICompletionProvider.a(IRegistry.PARTICLE_TYPE.keySet(), var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */