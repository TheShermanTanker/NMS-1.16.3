/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
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
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.function.BiFunction;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentAnchor
/*    */   implements ArgumentType<ArgumentAnchor.Anchor>
/*    */ {
/* 26 */   private static final Collection<String> a = Arrays.asList(new String[] { "eyes", "feet" }); private static final DynamicCommandExceptionType b; static {
/* 27 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.anchor.invalid", new Object[] { var0 }));
/*    */   }
/*    */   public static Anchor a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 30 */     return (Anchor)var0.getArgument(var1, Anchor.class);
/*    */   }
/*    */   
/*    */   public static ArgumentAnchor a() {
/* 34 */     return new ArgumentAnchor();
/*    */   }
/*    */ 
/*    */   
/*    */   public Anchor parse(StringReader var0) throws CommandSyntaxException {
/* 39 */     int var1 = var0.getCursor();
/* 40 */     String var2 = var0.readUnquotedString();
/* 41 */     Anchor var3 = Anchor.a(var2);
/* 42 */     if (var3 == null) {
/* 43 */       var0.setCursor(var1);
/* 44 */       throw b.createWithContext(var0, var2);
/*    */     } 
/* 46 */     return var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 51 */     return ICompletionProvider.b(Anchor.a().keySet(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 56 */     return a;
/*    */   }
/*    */   public enum Anchor { FEET, EYES; private static final Map<String, Anchor> c;
/*    */     static {
/* 60 */       FEET = new Anchor("FEET", 0, "feet", (var0, var1) -> var0);
/* 61 */       EYES = new Anchor("EYES", 1, "eyes", (var0, var1) -> new Vec3D(var0.x, var0.y + var1.getHeadHeight(), var0.z));
/*    */     } private final String d; private final BiFunction<Vec3D, Entity, Vec3D> e;
/*    */     static {
/* 64 */       c = SystemUtils.<Map<String, Anchor>>a(Maps.newHashMap(), var0 -> {
/*    */             for (Anchor var4 : values()) {
/*    */               var0.put(var4.d, var4);
/*    */             }
/*    */           });
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     Anchor(String var2, BiFunction<Vec3D, Entity, Vec3D> var3) {
/* 74 */       this.d = var2;
/* 75 */       this.e = var3;
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public static Anchor a(String var0) {
/* 80 */       return c.get(var0);
/*    */     }
/*    */     
/*    */     public Vec3D a(Entity var0) {
/* 84 */       return this.e.apply(var0.getPositionVector(), var0);
/*    */     }
/*    */     
/*    */     public Vec3D a(CommandListenerWrapper var0) {
/* 88 */       Entity var1 = var0.getEntity();
/* 89 */       if (var1 == null) {
/* 90 */         return var0.getPosition();
/*    */       }
/* 92 */       return this.e.apply(var0.getPosition(), var1);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentAnchor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */