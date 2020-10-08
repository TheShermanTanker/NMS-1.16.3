/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ public class ScoreboardTeam
/*     */   extends ScoreboardTeamBase
/*     */ {
/*     */   private final Scoreboard a;
/*     */   private final String b;
/*  24 */   private final Set<String> c = Sets.newHashSet();
/*     */   private IChatBaseComponent d;
/*  26 */   private IChatBaseComponent e = ChatComponentText.d;
/*  27 */   private IChatBaseComponent f = ChatComponentText.d;
/*     */   private boolean g = true;
/*     */   private boolean h = true;
/*  30 */   private ScoreboardTeamBase.EnumNameTagVisibility i = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS;
/*  31 */   private ScoreboardTeamBase.EnumNameTagVisibility j = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS;
/*  32 */   private EnumChatFormat k = EnumChatFormat.RESET;
/*  33 */   private ScoreboardTeamBase.EnumTeamPush l = ScoreboardTeamBase.EnumTeamPush.ALWAYS;
/*     */   private final ChatModifier m;
/*     */   
/*     */   public ScoreboardTeam(Scoreboard var0, String var1) {
/*  37 */     this.a = var0;
/*  38 */     this.b = var1;
/*  39 */     this.d = new ChatComponentText(var1);
/*     */     
/*  41 */     this
/*     */       
/*  43 */       .m = ChatModifier.a.setInsertion(var1).setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatComponentText(var1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  52 */     return this.b;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getDisplayName() {
/*  56 */     return this.d;
/*     */   }
/*     */   
/*     */   public IChatMutableComponent d() {
/*  60 */     IChatMutableComponent var0 = ChatComponentUtils.a(this.d.mutableCopy().c(this.m));
/*     */     
/*  62 */     EnumChatFormat var1 = getColor();
/*  63 */     if (var1 != EnumChatFormat.RESET) {
/*  64 */       var0.a(var1);
/*     */     }
/*     */     
/*  67 */     return var0;
/*     */   }
/*     */   
/*     */   public void setDisplayName(IChatBaseComponent var0) {
/*  71 */     if (var0 == null) {
/*  72 */       throw new IllegalArgumentException("Name cannot be null");
/*     */     }
/*  74 */     this.d = var0;
/*  75 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */   
/*     */   public void setPrefix(@Nullable IChatBaseComponent var0) {
/*  79 */     this.e = (var0 == null) ? ChatComponentText.d : var0;
/*  80 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getPrefix() {
/*  84 */     return this.e;
/*     */   }
/*     */   
/*     */   public void setSuffix(@Nullable IChatBaseComponent var0) {
/*  88 */     this.f = (var0 == null) ? ChatComponentText.d : var0;
/*  89 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getSuffix() {
/*  93 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getPlayerNameSet() {
/*  98 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatMutableComponent getFormattedName(IChatBaseComponent var0) {
/* 103 */     IChatMutableComponent var1 = (new ChatComponentText("")).addSibling(this.e).addSibling(var0).addSibling(this.f);
/*     */     
/* 105 */     EnumChatFormat var2 = getColor();
/* 106 */     if (var2 != EnumChatFormat.RESET) {
/* 107 */       var1.a(var2);
/*     */     }
/*     */     
/* 110 */     return var1;
/*     */   }
/*     */   
/*     */   public static IChatMutableComponent a(@Nullable ScoreboardTeamBase var0, IChatBaseComponent var1) {
/* 114 */     if (var0 == null) {
/* 115 */       return var1.mutableCopy();
/*     */     }
/* 117 */     return var0.getFormattedName(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowFriendlyFire() {
/* 122 */     return this.g;
/*     */   }
/*     */   
/*     */   public void setAllowFriendlyFire(boolean var0) {
/* 126 */     this.g = var0;
/* 127 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSeeFriendlyInvisibles() {
/* 132 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setCanSeeFriendlyInvisibles(boolean var0) {
/* 136 */     this.h = var0;
/* 137 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardTeamBase.EnumNameTagVisibility getNameTagVisibility() {
/* 142 */     return this.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardTeamBase.EnumNameTagVisibility getDeathMessageVisibility() {
/* 147 */     return this.j;
/*     */   }
/*     */   
/*     */   public void setNameTagVisibility(ScoreboardTeamBase.EnumNameTagVisibility var0) {
/* 151 */     this.i = var0;
/* 152 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */   
/*     */   public void setDeathMessageVisibility(ScoreboardTeamBase.EnumNameTagVisibility var0) {
/* 156 */     this.j = var0;
/* 157 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardTeamBase.EnumTeamPush getCollisionRule() {
/* 162 */     return this.l;
/*     */   }
/*     */   
/*     */   public void setCollisionRule(ScoreboardTeamBase.EnumTeamPush var0) {
/* 166 */     this.l = var0;
/* 167 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */   
/*     */   public int packOptionData() {
/* 171 */     int var0 = 0;
/*     */     
/* 173 */     if (allowFriendlyFire()) {
/* 174 */       var0 |= 0x1;
/*     */     }
/* 176 */     if (canSeeFriendlyInvisibles()) {
/* 177 */       var0 |= 0x2;
/*     */     }
/*     */     
/* 180 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(EnumChatFormat var0) {
/* 189 */     this.k = var0;
/* 190 */     this.a.handleTeamChanged(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumChatFormat getColor() {
/* 195 */     return this.k;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScoreboardTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */