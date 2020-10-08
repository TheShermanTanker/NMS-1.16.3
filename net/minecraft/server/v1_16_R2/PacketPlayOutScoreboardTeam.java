/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class PacketPlayOutScoreboardTeam implements Packet<PacketListenerPlayOut> {
/*  10 */   private String a = "";
/*     */   private IChatBaseComponent b;
/*     */   private IChatBaseComponent c;
/*     */   private IChatBaseComponent d;
/*     */   private String e;
/*     */   private String f;
/*     */   private EnumChatFormat g;
/*     */   private final Collection<String> h;
/*     */   private int i;
/*     */   private int j;
/*     */   
/*     */   public PacketPlayOutScoreboardTeam() {
/*  22 */     this.b = ChatComponentText.d;
/*  23 */     this.c = ChatComponentText.d;
/*  24 */     this.d = ChatComponentText.d;
/*  25 */     this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
/*  26 */     this.f = ScoreboardTeamBase.EnumTeamPush.ALWAYS.e;
/*  27 */     this.g = EnumChatFormat.RESET;
/*  28 */     this.h = Lists.newArrayList();
/*     */   }
/*     */   
/*     */   public PacketPlayOutScoreboardTeam(ScoreboardTeam scoreboardteam, int i) {
/*  32 */     this.b = ChatComponentText.d;
/*  33 */     this.c = ChatComponentText.d;
/*  34 */     this.d = ChatComponentText.d;
/*  35 */     this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
/*  36 */     this.f = ScoreboardTeamBase.EnumTeamPush.ALWAYS.e;
/*  37 */     this.g = EnumChatFormat.RESET;
/*  38 */     this.h = Lists.newArrayList();
/*  39 */     this.a = scoreboardteam.getName();
/*  40 */     this.i = i;
/*  41 */     if (i == 0 || i == 2) {
/*  42 */       this.b = scoreboardteam.getDisplayName();
/*  43 */       this.j = scoreboardteam.packOptionData();
/*  44 */       this.e = (scoreboardteam.getNameTagVisibility()).e;
/*  45 */       this.f = (scoreboardteam.getCollisionRule()).e;
/*  46 */       this.g = scoreboardteam.getColor();
/*  47 */       this.c = scoreboardteam.getPrefix();
/*  48 */       this.d = scoreboardteam.getSuffix();
/*     */     } 
/*     */     
/*  51 */     if (i == 0) {
/*  52 */       this.h.addAll(scoreboardteam.getPlayerNameSet());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketPlayOutScoreboardTeam(ScoreboardTeam scoreboardteam, Collection<String> collection, int i) {
/*  58 */     this.b = ChatComponentText.d;
/*  59 */     this.c = ChatComponentText.d;
/*  60 */     this.d = ChatComponentText.d;
/*  61 */     this.e = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS.e;
/*  62 */     this.f = ScoreboardTeamBase.EnumTeamPush.ALWAYS.e;
/*  63 */     this.g = EnumChatFormat.RESET;
/*  64 */     this.h = Lists.newArrayList();
/*  65 */     if (i != 3 && i != 4)
/*  66 */       throw new IllegalArgumentException("Method must be join or leave for player constructor"); 
/*  67 */     if (collection != null && !collection.isEmpty()) {
/*  68 */       this.i = i;
/*  69 */       this.a = scoreboardteam.getName();
/*  70 */       this.h.addAll(collection);
/*     */     } else {
/*  72 */       throw new IllegalArgumentException("Players cannot be null/empty");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/*  78 */     this.a = packetdataserializer.e(16);
/*  79 */     this.i = packetdataserializer.readByte();
/*  80 */     if (this.i == 0 || this.i == 2) {
/*  81 */       this.b = packetdataserializer.h();
/*  82 */       this.j = packetdataserializer.readByte();
/*  83 */       this.e = packetdataserializer.e(40);
/*  84 */       this.f = packetdataserializer.e(40);
/*  85 */       this.g = packetdataserializer.<EnumChatFormat>a(EnumChatFormat.class);
/*  86 */       this.c = packetdataserializer.h();
/*  87 */       this.d = packetdataserializer.h();
/*     */     } 
/*     */     
/*  90 */     if (this.i == 0 || this.i == 3 || this.i == 4) {
/*  91 */       int i = packetdataserializer.i();
/*     */       
/*  93 */       for (int j = 0; j < i; j++) {
/*  94 */         this.h.add(packetdataserializer.e(40));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 102 */     packetdataserializer.a(this.a);
/* 103 */     packetdataserializer.writeByte(this.i);
/* 104 */     if (this.i == 0 || this.i == 2) {
/* 105 */       packetdataserializer.a(this.b);
/* 106 */       packetdataserializer.writeByte(this.j);
/* 107 */       packetdataserializer.a(this.e);
/* 108 */       packetdataserializer.a(!PaperConfig.enablePlayerCollisions ? "never" : this.f);
/* 109 */       packetdataserializer.a(this.g);
/* 110 */       packetdataserializer.a(this.c);
/* 111 */       packetdataserializer.a(this.d);
/*     */     } 
/*     */     
/* 114 */     if (this.i == 0 || this.i == 3 || this.i == 4) {
/* 115 */       packetdataserializer.d(this.h.size());
/* 116 */       Iterator<String> iterator = this.h.iterator();
/*     */       
/* 118 */       while (iterator.hasNext()) {
/* 119 */         String s = iterator.next();
/*     */         
/* 121 */         packetdataserializer.a(s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 128 */     packetlistenerplayout.a(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutScoreboardTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */