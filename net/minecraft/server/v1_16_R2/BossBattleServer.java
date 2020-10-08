/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BossBattleServer
/*     */   extends BossBattle
/*     */ {
/*  16 */   private final Set<EntityPlayer> h = Sets.newHashSet();
/*  17 */   private final Set<EntityPlayer> i = Collections.unmodifiableSet(this.h);
/*     */   public boolean visible = true;
/*     */   
/*     */   public BossBattleServer(IChatBaseComponent var0, BossBattle.BarColor var1, BossBattle.BarStyle var2) {
/*  21 */     super(MathHelper.a(), var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(float var0) {
/*  26 */     if (var0 != this.b) {
/*  27 */       a(var0);
/*  28 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PCT);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BossBattle.BarColor var0) {
/*  34 */     if (var0 != this.color) {
/*  35 */       super.a(var0);
/*  36 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_STYLE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BossBattle.BarStyle var0) {
/*  42 */     if (var0 != this.style) {
/*  43 */       super.a(var0);
/*  44 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_STYLE);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattle setDarkenSky(boolean var0) {
/*  50 */     if (var0 != this.e) {
/*  51 */       a(var0);
/*  52 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  54 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattle setPlayMusic(boolean var0) {
/*  59 */     if (var0 != this.f) {
/*  60 */       b(var0);
/*  61 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BossBattle setCreateFog(boolean var0) {
/*  68 */     if (var0 != this.g) {
/*  69 */       c(var0);
/*  70 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_PROPERTIES);
/*     */     } 
/*  72 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IChatBaseComponent var0) {
/*  77 */     if (!Objects.equal(var0, this.title)) {
/*  78 */       super.a(var0);
/*  79 */       sendUpdate(PacketPlayOutBoss.Action.UPDATE_NAME);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void sendUpdate(PacketPlayOutBoss.Action var0) {
/*  84 */     if (this.visible) {
/*  85 */       PacketPlayOutBoss var1 = new PacketPlayOutBoss(var0, this);
/*  86 */       for (EntityPlayer var3 : this.h) {
/*  87 */         var3.playerConnection.sendPacket(var1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addPlayer(EntityPlayer var0) {
/*  93 */     if (this.h.add(var0) && this.visible) {
/*  94 */       var0.playerConnection.sendPacket(new PacketPlayOutBoss(PacketPlayOutBoss.Action.ADD, this));
/*     */     }
/*     */   }
/*     */   
/*     */   public void removePlayer(EntityPlayer var0) {
/*  99 */     if (this.h.remove(var0) && this.visible) {
/* 100 */       var0.playerConnection.sendPacket(new PacketPlayOutBoss(PacketPlayOutBoss.Action.REMOVE, this));
/*     */     }
/*     */   }
/*     */   
/*     */   public void b() {
/* 105 */     if (!this.h.isEmpty()) {
/* 106 */       for (EntityPlayer var1 : Lists.newArrayList(this.h)) {
/* 107 */         removePlayer(var1);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean g() {
/* 113 */     return this.visible;
/*     */   }
/*     */   
/*     */   public void setVisible(boolean var0) {
/* 117 */     if (var0 != this.visible) {
/* 118 */       this.visible = var0;
/*     */       
/* 120 */       for (EntityPlayer var2 : this.h) {
/* 121 */         var2.playerConnection.sendPacket(new PacketPlayOutBoss(var0 ? PacketPlayOutBoss.Action.ADD : PacketPlayOutBoss.Action.REMOVE, this));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public Collection<EntityPlayer> getPlayers() {
/* 127 */     return this.i;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BossBattleServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */