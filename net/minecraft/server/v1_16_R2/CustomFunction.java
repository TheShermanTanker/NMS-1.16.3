/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.ParseResults;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class CustomFunction {
/*     */   private final c[] a;
/*     */   
/*     */   public Timing getTiming() {
/*  19 */     if (this.timing == null) {
/*  20 */       this.timing = MinecraftTimings.getCommandFunctionTiming(this);
/*     */     }
/*  22 */     return this.timing;
/*     */   }
/*     */   private final MinecraftKey b; public Timing timing;
/*     */   
/*     */   public CustomFunction(MinecraftKey minecraftkey, c[] acustomfunction_c) {
/*  27 */     this.b = minecraftkey;
/*  28 */     this.a = acustomfunction_c;
/*     */   }
/*     */   public final MinecraftKey getMinecraftKey() {
/*  31 */     return a();
/*     */   } public MinecraftKey a() {
/*  33 */     return this.b;
/*     */   }
/*     */   
/*     */   public c[] b() {
/*  37 */     return this.a;
/*     */   }
/*     */   
/*     */   public static CustomFunction a(MinecraftKey minecraftkey, CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher, CommandListenerWrapper commandlistenerwrapper, List<String> list) {
/*  41 */     List<c> list1 = Lists.newArrayListWithCapacity(list.size());
/*     */     
/*  43 */     for (int i = 0; i < list.size(); i++) {
/*  44 */       int j = i + 1;
/*  45 */       String s = ((String)list.get(i)).trim();
/*  46 */       StringReader stringreader = new StringReader(s);
/*     */       
/*  48 */       if (stringreader.canRead() && stringreader.peek() != '#') {
/*  49 */         if (stringreader.peek() == '/') {
/*  50 */           stringreader.skip();
/*  51 */           if (stringreader.peek() == '/') {
/*  52 */             throw new IllegalArgumentException("Unknown or invalid command '" + s + "' on line " + j + " (if you intended to make a comment, use '#' not '//')");
/*     */           }
/*     */           
/*  55 */           String s1 = stringreader.readUnquotedString();
/*     */           
/*  57 */           throw new IllegalArgumentException("Unknown or invalid command '" + s + "' on line " + j + " (did you mean '" + s1 + "'? Do not use a preceding forwards slash.)");
/*     */         } 
/*     */         
/*     */         try {
/*  61 */           ParseResults<CommandListenerWrapper> parseresults = com_mojang_brigadier_commanddispatcher.parse(stringreader, commandlistenerwrapper);
/*     */           
/*  63 */           if (parseresults.getReader().canRead()) {
/*  64 */             throw CommandDispatcher.a(parseresults);
/*     */           }
/*     */           
/*  67 */           list1.add(new b(parseresults));
/*  68 */         } catch (CommandSyntaxException commandsyntaxexception) {
/*  69 */           throw new IllegalArgumentException("Whilst parsing command on line " + j + ": " + commandsyntaxexception.getMessage());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  74 */     return new CustomFunction(minecraftkey, list1.<c>toArray(new c[0]));
/*     */   }
/*     */   
/*     */   public static class a
/*     */   {
/*  79 */     public static final a a = new a((MinecraftKey)null);
/*     */     @Nullable
/*     */     private final MinecraftKey b;
/*     */     private boolean c;
/*  83 */     private Optional<CustomFunction> d = Optional.empty();
/*     */     
/*     */     public a(@Nullable MinecraftKey minecraftkey) {
/*  86 */       this.b = minecraftkey;
/*     */     }
/*     */     
/*     */     public a(CustomFunction customfunction) {
/*  90 */       this.c = true;
/*  91 */       this.b = null;
/*  92 */       this.d = Optional.of(customfunction);
/*     */     }
/*     */     
/*     */     public Optional<CustomFunction> a(CustomFunctionData customfunctiondata) {
/*  96 */       if (!this.c) {
/*  97 */         if (this.b != null) {
/*  98 */           this.d = customfunctiondata.a(this.b);
/*     */         }
/*     */         
/* 101 */         this.c = true;
/*     */       } 
/*     */       
/* 104 */       return this.d;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public MinecraftKey a() {
/* 109 */       return this.d.<MinecraftKey>map(customfunction -> customfunction.b)
/*     */         
/* 111 */         .orElse(this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class d
/*     */     implements c {
/*     */     private final CustomFunction.a a;
/*     */     
/*     */     public d(CustomFunction customfunction) {
/* 120 */       this.a = new CustomFunction.a(customfunction);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(CustomFunctionData customfunctiondata, CommandListenerWrapper commandlistenerwrapper, ArrayDeque<CustomFunctionData.a> arraydeque, int i) {
/* 125 */       this.a.a(customfunctiondata).ifPresent(customfunction -> {
/*     */             CustomFunction.c[] acustomfunction_c = customfunction.b();
/*     */             int j = i - arraydeque.size();
/*     */             int k = Math.min(acustomfunction_c.length, j);
/*     */             for (int l = k - 1; l >= 0; l--) {
/*     */               arraydeque.addFirst(new CustomFunctionData.a(customfunctiondata, commandlistenerwrapper, acustomfunction_c[l]));
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 138 */       return "function " + this.a.a();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class b
/*     */     implements c {
/*     */     private final ParseResults<CommandListenerWrapper> a;
/*     */     
/*     */     public b(ParseResults<CommandListenerWrapper> parseresults) {
/* 147 */       this.a = parseresults;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(CustomFunctionData customfunctiondata, CommandListenerWrapper commandlistenerwrapper, ArrayDeque<CustomFunctionData.a> arraydeque, int i) throws CommandSyntaxException {
/* 152 */       customfunctiondata.getCommandDispatcher().execute(new ParseResults(this.a.getContext().withSource(commandlistenerwrapper), this.a.getReader(), this.a.getExceptions()));
/*     */     }
/*     */     
/*     */     public String toString() {
/* 156 */       return this.a.getReader().getString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface c {
/*     */     void a(CustomFunctionData param1CustomFunctionData, CommandListenerWrapper param1CommandListenerWrapper, ArrayDeque<CustomFunctionData.a> param1ArrayDeque, int param1Int) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */