/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*    */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentTime
/*    */   implements ArgumentType<Integer>
/*    */ {
/* 23 */   private static final Collection<String> a = Arrays.asList(new String[] { "0d", "0s", "0t", "0" }); private static final DynamicCommandExceptionType c;
/* 24 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.time.invalid_unit")); static {
/* 25 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.time.invalid_tick_count", new Object[] { var0 }));
/*    */   }
/* 27 */   private static final Object2IntMap<String> d = (Object2IntMap<String>)new Object2IntOpenHashMap();
/*    */   
/*    */   static {
/* 30 */     d.put("d", 24000);
/* 31 */     d.put("s", 20);
/* 32 */     d.put("t", 1);
/* 33 */     d.put("", 1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ArgumentTime a() {
/* 40 */     return new ArgumentTime();
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer parse(StringReader var0) throws CommandSyntaxException {
/* 45 */     float var1 = var0.readFloat();
/* 46 */     String var2 = var0.readUnquotedString();
/* 47 */     int var3 = d.getOrDefault(var2, 0);
/* 48 */     if (var3 == 0) {
/* 49 */       throw b.create();
/*    */     }
/*    */     
/* 52 */     int var4 = Math.round(var1 * var3);
/* 53 */     if (var4 < 0) {
/* 54 */       throw c.create(Integer.valueOf(var4));
/*    */     }
/*    */     
/* 57 */     return Integer.valueOf(var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 62 */     StringReader var2 = new StringReader(var1.getRemaining());
/*    */     try {
/* 64 */       var2.readFloat();
/* 65 */     } catch (CommandSyntaxException var3) {
/* 66 */       return var1.buildFuture();
/*    */     } 
/*    */     
/* 69 */     return ICompletionProvider.b((Iterable<String>)d.keySet(), var1.createOffset(var1.getStart() + var2.getCursor()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 74 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */