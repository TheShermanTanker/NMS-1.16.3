/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentChat
/*     */   implements ArgumentType<ArgumentChat.a>
/*     */ {
/*  22 */   private static final Collection<String> a = Arrays.asList(new String[] { "Hello world!", "foo", "@e", "Hello @p :)" });
/*     */   
/*     */   public static ArgumentChat a() {
/*  25 */     return new ArgumentChat();
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  29 */     return ((a)var0.getArgument(var1, a.class)).a((CommandListenerWrapper)var0.getSource(), ((CommandListenerWrapper)var0.getSource()).hasPermission(2));
/*     */   }
/*     */ 
/*     */   
/*     */   public a parse(StringReader var0) throws CommandSyntaxException {
/*  34 */     return a.a(var0, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/*  39 */     return a;
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     private final String a;
/*     */     private final ArgumentChat.b[] b;
/*     */     
/*     */     public a(String var0, ArgumentChat.b[] var1) {
/*  47 */       this.a = var0;
/*  48 */       this.b = var1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public IChatBaseComponent a(CommandListenerWrapper var0, boolean var1) throws CommandSyntaxException {
/*  60 */       if (this.b.length == 0 || !var1) {
/*  61 */         return new ChatComponentText(this.a);
/*     */       }
/*     */       
/*  64 */       IChatMutableComponent var2 = new ChatComponentText(this.a.substring(0, this.b[0].a()));
/*  65 */       int var3 = this.b[0].a();
/*     */       
/*  67 */       for (ArgumentChat.b var7 : this.b) {
/*  68 */         IChatBaseComponent var8 = var7.a(var0);
/*  69 */         if (var3 < var7.a()) {
/*  70 */           var2.c(this.a.substring(var3, var7.a()));
/*     */         }
/*  72 */         if (var8 != null) {
/*  73 */           var2.addSibling(var8);
/*     */         }
/*  75 */         var3 = var7.b();
/*     */       } 
/*     */       
/*  78 */       if (var3 < this.a.length()) {
/*  79 */         var2.c(this.a.substring(var3, this.a.length()));
/*     */       }
/*     */       
/*  82 */       return var2;
/*     */     }
/*     */     
/*     */     public static a a(StringReader var0, boolean var1) throws CommandSyntaxException {
/*  86 */       String var2 = var0.getString().substring(var0.getCursor(), var0.getTotalLength());
/*     */       
/*  88 */       if (!var1) {
/*  89 */         var0.setCursor(var0.getTotalLength());
/*  90 */         return new a(var2, new ArgumentChat.b[0]);
/*     */       } 
/*     */       
/*  93 */       List<ArgumentChat.b> var3 = Lists.newArrayList();
/*  94 */       int var4 = var0.getCursor();
/*     */       
/*  96 */       while (var0.canRead()) {
/*  97 */         if (var0.peek() == '@') {
/*  98 */           EntitySelector var6; int var5 = var0.getCursor();
/*     */           
/*     */           try {
/* 101 */             ArgumentParserSelector var7 = new ArgumentParserSelector(var0);
/* 102 */             var6 = var7.parse();
/* 103 */           } catch (CommandSyntaxException var7) {
/* 104 */             if (var7.getType() == ArgumentParserSelector.d || var7.getType() == ArgumentParserSelector.b) {
/* 105 */               var0.setCursor(var5 + 1);
/*     */               continue;
/*     */             } 
/* 108 */             throw var7;
/*     */           } 
/* 110 */           var3.add(new ArgumentChat.b(var5 - var4, var0.getCursor() - var4, var6)); continue;
/*     */         } 
/* 112 */         var0.skip();
/*     */       } 
/*     */ 
/*     */       
/* 116 */       return new a(var2, var3.<ArgumentChat.b>toArray(new ArgumentChat.b[var3.size()]));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class b {
/*     */     private final int a;
/*     */     private final int b;
/*     */     private final EntitySelector c;
/*     */     
/*     */     public b(int var0, int var1, EntitySelector var2) {
/* 126 */       this.a = var0;
/* 127 */       this.b = var1;
/* 128 */       this.c = var2;
/*     */     }
/*     */     
/*     */     public int a() {
/* 132 */       return this.a;
/*     */     }
/*     */     
/*     */     public int b() {
/* 136 */       return this.b;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     public IChatBaseComponent a(CommandListenerWrapper var0) throws CommandSyntaxException {
/* 145 */       return EntitySelector.a(this.c.getEntities(var0));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */