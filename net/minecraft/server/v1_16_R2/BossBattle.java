/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BossBattle
/*     */ {
/*     */   private final UUID h;
/*     */   public IChatBaseComponent title;
/*     */   protected float b;
/*     */   public BarColor color;
/*     */   public BarStyle style;
/*     */   protected boolean e;
/*     */   protected boolean f;
/*     */   protected boolean g;
/*     */   
/*     */   public BossBattle(UUID var0, IChatBaseComponent var1, BarColor var2, BarStyle var3) {
/*  19 */     this.h = var0;
/*  20 */     this.title = var1;
/*  21 */     this.color = var2;
/*  22 */     this.style = var3;
/*  23 */     this.b = 1.0F;
/*     */   }
/*     */   
/*     */   public UUID i() {
/*  27 */     return this.h;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent j() {
/*  31 */     return this.title;
/*     */   }
/*     */   
/*     */   public void a(IChatBaseComponent var0) {
/*  35 */     this.title = var0;
/*     */   }
/*     */   
/*     */   public float getProgress() {
/*  39 */     return this.b;
/*     */   }
/*     */   
/*     */   public void a(float var0) {
/*  43 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public BarColor l() {
/*  47 */     return this.color;
/*     */   }
/*     */   
/*     */   public void a(BarColor var0) {
/*  51 */     this.color = var0;
/*     */   }
/*     */   
/*     */   public BarStyle m() {
/*  55 */     return this.style;
/*     */   }
/*     */   
/*     */   public void a(BarStyle var0) {
/*  59 */     this.style = var0;
/*     */   }
/*     */   
/*     */   public boolean isDarkenSky() {
/*  63 */     return this.e;
/*     */   }
/*     */   
/*     */   public BossBattle a(boolean var0) {
/*  67 */     this.e = var0;
/*  68 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isPlayMusic() {
/*  72 */     return this.f;
/*     */   }
/*     */   
/*     */   public BossBattle b(boolean var0) {
/*  76 */     this.f = var0;
/*  77 */     return this;
/*     */   }
/*     */   
/*     */   public BossBattle c(boolean var0) {
/*  81 */     this.g = var0;
/*  82 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isCreateFog() {
/*  86 */     return this.g;
/*     */   }
/*     */   
/*     */   public enum BarColor {
/*  90 */     PINK("pink", EnumChatFormat.RED),
/*  91 */     BLUE("blue", EnumChatFormat.BLUE),
/*  92 */     RED("red", EnumChatFormat.DARK_RED),
/*  93 */     GREEN("green", EnumChatFormat.GREEN),
/*  94 */     YELLOW("yellow", EnumChatFormat.YELLOW),
/*  95 */     PURPLE("purple", EnumChatFormat.DARK_BLUE),
/*  96 */     WHITE("white", EnumChatFormat.WHITE);
/*     */     
/*     */     private final String h;
/*     */     
/*     */     private final EnumChatFormat i;
/*     */     
/*     */     BarColor(String var2, EnumChatFormat var3) {
/* 103 */       this.h = var2;
/* 104 */       this.i = var3;
/*     */     }
/*     */     
/*     */     public EnumChatFormat a() {
/* 108 */       return this.i;
/*     */     }
/*     */     
/*     */     public String b() {
/* 112 */       return this.h;
/*     */     }
/*     */     
/*     */     public static BarColor a(String var0) {
/* 116 */       for (BarColor var4 : values()) {
/* 117 */         if (var4.h.equals(var0)) {
/* 118 */           return var4;
/*     */         }
/*     */       } 
/* 121 */       return WHITE;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum BarStyle {
/* 126 */     PROGRESS("progress"),
/* 127 */     NOTCHED_6("notched_6"),
/* 128 */     NOTCHED_10("notched_10"),
/* 129 */     NOTCHED_12("notched_12"),
/* 130 */     NOTCHED_20("notched_20");
/*     */     
/*     */     private final String f;
/*     */ 
/*     */     
/*     */     BarStyle(String var2) {
/* 136 */       this.f = var2;
/*     */     }
/*     */     
/*     */     public String a() {
/* 140 */       return this.f;
/*     */     }
/*     */     
/*     */     public static BarStyle a(String var0) {
/* 144 */       for (BarStyle var4 : values()) {
/* 145 */         if (var4.f.equals(var0)) {
/* 146 */           return var4;
/*     */         }
/*     */       } 
/* 149 */       return PROGRESS;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BossBattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */