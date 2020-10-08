/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class ChatMessage
/*     */   extends ChatBaseComponent
/*     */   implements ChatComponentContextual
/*     */ {
/*  17 */   private static final Object[] d = new Object[0];
/*     */   
/*  19 */   private static final IChatFormatted e = IChatFormatted.b("%");
/*  20 */   private static final IChatFormatted f = IChatFormatted.b("null");
/*     */   
/*     */   private final String key;
/*     */   
/*     */   private final Object[] args;
/*     */   @Nullable
/*     */   private LocaleLanguage i;
/*  27 */   private final List<IChatFormatted> j = Lists.newArrayList();
/*     */   
/*  29 */   private static final Pattern k = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");
/*     */   
/*     */   public ChatMessage(String var0) {
/*  32 */     this.key = var0;
/*  33 */     this.args = d;
/*     */   }
/*     */   
/*     */   public ChatMessage(String var0, Object... var1) {
/*  37 */     this.key = var0;
/*  38 */     this.args = var1;
/*     */   }
/*     */   
/*     */   private void k() {
/*  42 */     LocaleLanguage var0 = LocaleLanguage.a();
/*  43 */     if (var0 == this.i) {
/*     */       return;
/*     */     }
/*  46 */     this.i = var0;
/*  47 */     this.j.clear();
/*     */     
/*  49 */     String var1 = var0.a(this.key);
/*     */     try {
/*  51 */       d(var1);
/*  52 */     } catch (ChatMessageException var2) {
/*  53 */       this.j.clear();
/*  54 */       this.j.add(IChatFormatted.b(var1));
/*     */     } 
/*     */   }
/*     */   
/*     */   private void d(String var0) {
/*  59 */     Matcher var1 = k.matcher(var0);
/*     */     
/*     */     try {
/*  62 */       int var2 = 0;
/*  63 */       int var3 = 0;
/*     */       
/*  65 */       while (var1.find(var3)) {
/*  66 */         int var4 = var1.start();
/*  67 */         int var5 = var1.end();
/*     */         
/*  69 */         if (var4 > var3) {
/*  70 */           String str = var0.substring(var3, var4);
/*  71 */           if (str.indexOf('%') != -1) {
/*  72 */             throw new IllegalArgumentException();
/*     */           }
/*  74 */           this.j.add(IChatFormatted.b(str));
/*     */         } 
/*     */         
/*  77 */         String var6 = var1.group(2);
/*  78 */         String var7 = var0.substring(var4, var5);
/*     */ 
/*     */         
/*  81 */         if ("%".equals(var6) && "%%".equals(var7)) {
/*  82 */           this.j.add(e);
/*  83 */         } else if ("s".equals(var6)) {
/*  84 */           String var8 = var1.group(1);
/*  85 */           int var9 = (var8 != null) ? (Integer.parseInt(var8) - 1) : var2++;
/*  86 */           if (var9 < this.args.length) {
/*  87 */             this.j.add(b(var9));
/*     */           }
/*     */         } else {
/*  90 */           throw new ChatMessageException(this, "Unsupported format: '" + var7 + "'");
/*     */         } 
/*     */         
/*  93 */         var3 = var5;
/*     */       } 
/*     */       
/*  96 */       if (var3 < var0.length()) {
/*  97 */         String var4 = var0.substring(var3);
/*  98 */         if (var4.indexOf('%') != -1) {
/*  99 */           throw new IllegalArgumentException();
/*     */         }
/* 101 */         this.j.add(IChatFormatted.b(var4));
/*     */       } 
/* 103 */     } catch (IllegalArgumentException var2) {
/* 104 */       throw new ChatMessageException(this, var2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private IChatFormatted b(int var0) {
/* 109 */     if (var0 >= this.args.length) {
/* 110 */       throw new ChatMessageException(this, var0);
/*     */     }
/*     */     
/* 113 */     Object var1 = this.args[var0];
/*     */     
/* 115 */     if (var1 instanceof IChatBaseComponent) {
/* 116 */       return (IChatBaseComponent)var1;
/*     */     }
/* 118 */     return (var1 == null) ? f : IChatFormatted.b(var1.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChatMessage g() {
/* 124 */     return new ChatMessage(this.key, this.args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> Optional<T> b(IChatFormatted.a<T> var0) {
/* 143 */     k();
/*     */     
/* 145 */     for (IChatFormatted var2 : this.j) {
/* 146 */       Optional<T> var3 = var2.a(var0);
/* 147 */       if (var3.isPresent()) {
/* 148 */         return var3;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return Optional.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatMutableComponent a(@Nullable CommandListenerWrapper var0, @Nullable Entity var1, int var2) throws CommandSyntaxException {
/* 157 */     Object[] var3 = new Object[this.args.length];
/*     */     
/* 159 */     for (int var4 = 0; var4 < var3.length; var4++) {
/* 160 */       Object var5 = this.args[var4];
/* 161 */       if (var5 instanceof IChatBaseComponent) {
/* 162 */         var3[var4] = ChatComponentUtils.filterForDisplay(var0, (IChatBaseComponent)var5, var1, var2);
/*     */       } else {
/* 164 */         var3[var4] = var5;
/*     */       } 
/*     */     } 
/* 167 */     return new ChatMessage(this.key, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 172 */     if (this == var0) {
/* 173 */       return true;
/*     */     }
/*     */     
/* 176 */     if (var0 instanceof ChatMessage) {
/* 177 */       ChatMessage var1 = (ChatMessage)var0;
/* 178 */       return (Arrays.equals(this.args, var1.args) && this.key.equals(var1.key) && super.equals(var0));
/*     */     } 
/*     */     
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 186 */     int var0 = super.hashCode();
/* 187 */     var0 = 31 * var0 + this.key.hashCode();
/* 188 */     var0 = 31 * var0 + Arrays.hashCode(this.args);
/* 189 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 194 */     return "TranslatableComponent{key='" + this.key + '\'' + ", args=" + 
/*     */       
/* 196 */       Arrays.toString(this.args) + ", siblings=" + this.siblings + ", style=" + 
/*     */       
/* 198 */       getChatModifier() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public String getKey() {
/* 203 */     return this.key;
/*     */   }
/*     */   
/*     */   public Object[] getArgs() {
/* 207 */     return this.args;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */