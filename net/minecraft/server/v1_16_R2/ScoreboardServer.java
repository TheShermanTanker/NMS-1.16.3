/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ScoreboardServer
/*     */   extends Scoreboard {
/*     */   private final MinecraftServer a;
/*  14 */   private final Set<ScoreboardObjective> b = Sets.newHashSet();
/*  15 */   private Runnable[] c = new Runnable[0];
/*     */   
/*     */   public ScoreboardServer(MinecraftServer minecraftserver) {
/*  18 */     this.a = minecraftserver;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleScoreChanged(ScoreboardScore scoreboardscore) {
/*  23 */     super.handleScoreChanged(scoreboardscore);
/*  24 */     if (this.b.contains(scoreboardscore.getObjective())) {
/*  25 */       sendAll(new PacketPlayOutScoreboardScore(Action.CHANGE, scoreboardscore.getObjective().getName(), scoreboardscore.getPlayerName(), scoreboardscore.getScore()));
/*     */     }
/*     */     
/*  28 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePlayerRemoved(String s) {
/*  33 */     super.handlePlayerRemoved(s);
/*  34 */     sendAll(new PacketPlayOutScoreboardScore(Action.REMOVE, (String)null, s, 0));
/*  35 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(String s, ScoreboardObjective scoreboardobjective) {
/*  40 */     super.a(s, scoreboardobjective);
/*  41 */     if (this.b.contains(scoreboardobjective)) {
/*  42 */       sendAll(new PacketPlayOutScoreboardScore(Action.REMOVE, scoreboardobjective.getName(), s, 0));
/*     */     }
/*     */     
/*  45 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplaySlot(int i, @Nullable ScoreboardObjective scoreboardobjective) {
/*  50 */     ScoreboardObjective scoreboardobjective1 = getObjectiveForSlot(i);
/*     */     
/*  52 */     super.setDisplaySlot(i, scoreboardobjective);
/*  53 */     if (scoreboardobjective1 != scoreboardobjective && scoreboardobjective1 != null) {
/*  54 */       if (h(scoreboardobjective1) > 0) {
/*  55 */         sendAll(new PacketPlayOutScoreboardDisplayObjective(i, scoreboardobjective));
/*     */       } else {
/*  57 */         g(scoreboardobjective1);
/*     */       } 
/*     */     }
/*     */     
/*  61 */     if (scoreboardobjective != null) {
/*  62 */       if (this.b.contains(scoreboardobjective)) {
/*  63 */         sendAll(new PacketPlayOutScoreboardDisplayObjective(i, scoreboardobjective));
/*     */       } else {
/*  65 */         e(scoreboardobjective);
/*     */       } 
/*     */     }
/*     */     
/*  69 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addPlayerToTeam(String s, ScoreboardTeam scoreboardteam) {
/*  74 */     if (super.addPlayerToTeam(s, scoreboardteam)) {
/*  75 */       sendAll(new PacketPlayOutScoreboardTeam(scoreboardteam, Arrays.asList(new String[] { s }, ), 3));
/*  76 */       b();
/*  77 */       return true;
/*     */     } 
/*  79 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String s, ScoreboardTeam scoreboardteam) {
/*  85 */     super.removePlayerFromTeam(s, scoreboardteam);
/*  86 */     sendAll(new PacketPlayOutScoreboardTeam(scoreboardteam, Arrays.asList(new String[] { s }, ), 4));
/*  87 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleObjectiveAdded(ScoreboardObjective scoreboardobjective) {
/*  92 */     super.handleObjectiveAdded(scoreboardobjective);
/*  93 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleObjectiveChanged(ScoreboardObjective scoreboardobjective) {
/*  98 */     super.handleObjectiveChanged(scoreboardobjective);
/*  99 */     if (this.b.contains(scoreboardobjective)) {
/* 100 */       sendAll(new PacketPlayOutScoreboardObjective(scoreboardobjective, 2));
/*     */     }
/*     */     
/* 103 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleObjectiveRemoved(ScoreboardObjective scoreboardobjective) {
/* 108 */     super.handleObjectiveRemoved(scoreboardobjective);
/* 109 */     if (this.b.contains(scoreboardobjective)) {
/* 110 */       g(scoreboardobjective);
/*     */     }
/*     */     
/* 113 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTeamAdded(ScoreboardTeam scoreboardteam) {
/* 118 */     super.handleTeamAdded(scoreboardteam);
/* 119 */     sendAll(new PacketPlayOutScoreboardTeam(scoreboardteam, 0));
/* 120 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTeamChanged(ScoreboardTeam scoreboardteam) {
/* 125 */     super.handleTeamChanged(scoreboardteam);
/* 126 */     sendAll(new PacketPlayOutScoreboardTeam(scoreboardteam, 2));
/* 127 */     b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleTeamRemoved(ScoreboardTeam scoreboardteam) {
/* 132 */     super.handleTeamRemoved(scoreboardteam);
/* 133 */     sendAll(new PacketPlayOutScoreboardTeam(scoreboardteam, 1));
/* 134 */     b();
/*     */   }
/*     */   
/*     */   public void a(Runnable runnable) {
/* 138 */     this.c = Arrays.<Runnable>copyOf(this.c, this.c.length + 1);
/* 139 */     this.c[this.c.length - 1] = runnable;
/*     */   }
/*     */   
/*     */   protected void b() {
/* 143 */     Runnable[] arunnable = this.c;
/* 144 */     int i = arunnable.length;
/*     */     
/* 146 */     for (int j = 0; j < i; j++) {
/* 147 */       Runnable runnable = arunnable[j];
/*     */       
/* 149 */       runnable.run();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Packet<?>> getScoreboardScorePacketsForObjective(ScoreboardObjective scoreboardobjective) {
/* 155 */     List<Packet<?>> list = Lists.newArrayList();
/*     */     
/* 157 */     list.add(new PacketPlayOutScoreboardObjective(scoreboardobjective, 0));
/*     */     
/* 159 */     for (int i = 0; i < 19; i++) {
/* 160 */       if (getObjectiveForSlot(i) == scoreboardobjective) {
/* 161 */         list.add(new PacketPlayOutScoreboardDisplayObjective(i, scoreboardobjective));
/*     */       }
/*     */     } 
/*     */     
/* 165 */     Iterator<ScoreboardScore> iterator = getScoresForObjective(scoreboardobjective).iterator();
/*     */     
/* 167 */     while (iterator.hasNext()) {
/* 168 */       ScoreboardScore scoreboardscore = iterator.next();
/*     */       
/* 170 */       list.add(new PacketPlayOutScoreboardScore(Action.CHANGE, scoreboardscore.getObjective().getName(), scoreboardscore.getPlayerName(), scoreboardscore.getScore()));
/*     */     } 
/*     */     
/* 173 */     return list;
/*     */   }
/*     */   
/*     */   public void e(ScoreboardObjective scoreboardobjective) {
/* 177 */     List<Packet<?>> list = getScoreboardScorePacketsForObjective(scoreboardobjective);
/* 178 */     Iterator<EntityPlayer> iterator = this.a.getPlayerList().getPlayers().iterator();
/*     */     
/* 180 */     while (iterator.hasNext()) {
/* 181 */       EntityPlayer entityplayer = iterator.next();
/* 182 */       if (entityplayer.getBukkitEntity().getScoreboard().getHandle() != this)
/* 183 */         continue;  Iterator<Packet<?>> iterator1 = list.iterator();
/*     */       
/* 185 */       while (iterator1.hasNext()) {
/* 186 */         Packet<?> packet = iterator1.next();
/*     */         
/* 188 */         entityplayer.playerConnection.sendPacket(packet);
/*     */       } 
/*     */     } 
/*     */     
/* 192 */     this.b.add(scoreboardobjective);
/*     */   }
/*     */   
/*     */   public List<Packet<?>> f(ScoreboardObjective scoreboardobjective) {
/* 196 */     List<Packet<?>> list = Lists.newArrayList();
/*     */     
/* 198 */     list.add(new PacketPlayOutScoreboardObjective(scoreboardobjective, 1));
/*     */     
/* 200 */     for (int i = 0; i < 19; i++) {
/* 201 */       if (getObjectiveForSlot(i) == scoreboardobjective) {
/* 202 */         list.add(new PacketPlayOutScoreboardDisplayObjective(i, scoreboardobjective));
/*     */       }
/*     */     } 
/*     */     
/* 206 */     return list;
/*     */   }
/*     */   
/*     */   public void g(ScoreboardObjective scoreboardobjective) {
/* 210 */     List<Packet<?>> list = f(scoreboardobjective);
/* 211 */     Iterator<EntityPlayer> iterator = this.a.getPlayerList().getPlayers().iterator();
/*     */     
/* 213 */     while (iterator.hasNext()) {
/* 214 */       EntityPlayer entityplayer = iterator.next();
/* 215 */       if (entityplayer.getBukkitEntity().getScoreboard().getHandle() != this)
/* 216 */         continue;  Iterator<Packet<?>> iterator1 = list.iterator();
/*     */       
/* 218 */       while (iterator1.hasNext()) {
/* 219 */         Packet<?> packet = iterator1.next();
/*     */         
/* 221 */         entityplayer.playerConnection.sendPacket(packet);
/*     */       } 
/*     */     } 
/*     */     
/* 225 */     this.b.remove(scoreboardobjective);
/*     */   }
/*     */   
/*     */   public int h(ScoreboardObjective scoreboardobjective) {
/* 229 */     int i = 0;
/*     */     
/* 231 */     for (int j = 0; j < 19; j++) {
/* 232 */       if (getObjectiveForSlot(j) == scoreboardobjective) {
/* 233 */         i++;
/*     */       }
/*     */     } 
/*     */     
/* 237 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendAll(Packet<?> packet) {
/* 242 */     for (EntityPlayer entityplayer : (this.a.getPlayerList()).players) {
/* 243 */       if (entityplayer.getBukkitEntity().getScoreboard().getHandle() == this) {
/* 244 */         entityplayer.playerConnection.sendPacket(packet);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public enum Action
/*     */   {
/* 252 */     CHANGE, REMOVE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScoreboardServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */