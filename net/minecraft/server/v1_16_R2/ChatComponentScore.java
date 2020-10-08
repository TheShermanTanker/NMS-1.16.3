/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class ChatComponentScore
/*     */   extends ChatBaseComponent
/*     */   implements ChatComponentContextual
/*     */ {
/*     */   private final String d;
/*     */   @Nullable
/*     */   private final EntitySelector e;
/*     */   private final String f;
/*     */   
/*     */   @Nullable
/*     */   private static EntitySelector d(String var0) {
/*     */     try {
/*  29 */       return (new ArgumentParserSelector(new StringReader(var0))).parse();
/*  30 */     } catch (CommandSyntaxException commandSyntaxException) {
/*     */       
/*  32 */       return null;
/*     */     } 
/*     */   }
/*     */   public ChatComponentScore(String var0, String var1) {
/*  36 */     this(var0, d(var0), var1);
/*     */   }
/*     */   
/*     */   private ChatComponentScore(String var0, @Nullable EntitySelector var1, String var2) {
/*  40 */     this.d = var0;
/*  41 */     this.e = var1;
/*  42 */     this.f = var2;
/*     */   }
/*     */   
/*     */   public String h() {
/*  46 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String j() {
/*  55 */     return this.f;
/*     */   }
/*     */   
/*     */   private String a(CommandListenerWrapper var0) throws CommandSyntaxException {
/*  59 */     if (this.e != null) {
/*  60 */       List<? extends Entity> var1 = this.e.getEntities(var0);
/*  61 */       if (!var1.isEmpty()) {
/*  62 */         if (var1.size() != 1) {
/*  63 */           throw ArgumentEntity.a.create();
/*     */         }
/*  65 */         return ((Entity)var1.get(0)).getName();
/*     */       } 
/*     */     } 
/*  68 */     return this.d;
/*     */   }
/*     */   
/*     */   private String a(String var0, CommandListenerWrapper var1) {
/*  72 */     MinecraftServer var2 = var1.getServer();
/*  73 */     if (var2 != null) {
/*  74 */       Scoreboard var3 = var2.getScoreboard();
/*  75 */       ScoreboardObjective var4 = var3.getObjective(this.f);
/*  76 */       if (var3.b(var0, var4)) {
/*  77 */         ScoreboardScore var5 = var3.getPlayerScoreForObjective(var0, var4);
/*  78 */         return Integer.toString(var5.getScore());
/*     */       } 
/*     */     } 
/*  81 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatComponentScore g() {
/*  86 */     return new ChatComponentScore(this.d, this.e, this.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatMutableComponent a(@Nullable CommandListenerWrapper var0, @Nullable Entity var1, int var2) throws CommandSyntaxException {
/*  91 */     if (var0 == null) {
/*  92 */       return new ChatComponentText("");
/*     */     }
/*     */     
/*  95 */     String var3 = a(var0);
/*  96 */     String var4 = (var1 != null && var3.equals("*")) ? var1.getName() : var3;
/*  97 */     return new ChatComponentText(a(var4, var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 102 */     if (this == var0) {
/* 103 */       return true;
/*     */     }
/*     */     
/* 106 */     if (var0 instanceof ChatComponentScore) {
/* 107 */       ChatComponentScore var1 = (ChatComponentScore)var0;
/* 108 */       return (this.d.equals(var1.d) && this.f.equals(var1.f) && super.equals(var0));
/*     */     } 
/*     */     
/* 111 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     return "ScoreComponent{name='" + this.d + '\'' + "objective='" + this.f + '\'' + ", siblings=" + this.siblings + ", style=" + 
/*     */ 
/*     */ 
/*     */       
/* 120 */       getChatModifier() + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatComponentScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */