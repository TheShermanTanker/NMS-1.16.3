/*     */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*     */ import co.aikar.timings.MinecraftTimings;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.function.Consumer;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.IScoreboardCriteria;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.PacketPlayOutScoreboardObjective;
/*     */ import net.minecraft.server.v1_16_R2.Scoreboard;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardObjective;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardScore;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardServer;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardTeam;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.WeakCollection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.ScoreboardManager;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public final class CraftScoreboardManager implements ScoreboardManager {
/*     */   private final CraftScoreboard mainScoreboard;
/*  28 */   private final Collection<CraftScoreboard> scoreboards = (Collection<CraftScoreboard>)new WeakCollection(); private final MinecraftServer server;
/*  29 */   private final Map<CraftPlayer, CraftScoreboard> playerBoards = new HashMap<>();
/*     */   
/*     */   public CraftScoreboardManager(MinecraftServer minecraftserver, Scoreboard scoreboardServer) {
/*  32 */     this.mainScoreboard = new CraftScoreboard(scoreboardServer);
/*  33 */     this.server = minecraftserver;
/*  34 */     this.scoreboards.add(this.mainScoreboard);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftScoreboard getMainScoreboard() {
/*  39 */     return this.mainScoreboard;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftScoreboard getNewScoreboard() {
/*  44 */     AsyncCatcher.catchOp("scoreboard creation");
/*  45 */     CraftScoreboard scoreboard = new CraftScoreboard((Scoreboard)new ScoreboardServer(this.server));
/*  46 */     this.scoreboards.add(scoreboard);
/*  47 */     return scoreboard;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftScoreboard getPlayerBoard(CraftPlayer player) {
/*  52 */     CraftScoreboard board = this.playerBoards.get(player);
/*  53 */     return (board == null) ? getMainScoreboard() : board;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPlayerBoard(CraftPlayer player, Scoreboard bukkitScoreboard) throws IllegalArgumentException {
/*  58 */     Validate.isTrue(bukkitScoreboard instanceof CraftScoreboard, "Cannot set player scoreboard to an unregistered Scoreboard");
/*     */     
/*  60 */     CraftScoreboard scoreboard = (CraftScoreboard)bukkitScoreboard;
/*  61 */     Scoreboard oldboard = getPlayerBoard(player).getHandle();
/*  62 */     Scoreboard newboard = scoreboard.getHandle();
/*  63 */     EntityPlayer entityplayer = player.getHandle();
/*     */     
/*  65 */     if (oldboard == newboard) {
/*     */       return;
/*     */     }
/*     */     
/*  69 */     if (scoreboard == this.mainScoreboard) {
/*  70 */       this.playerBoards.remove(player);
/*     */     } else {
/*  72 */       this.playerBoards.put(player, scoreboard);
/*     */     } 
/*     */ 
/*     */     
/*  76 */     HashSet<ScoreboardObjective> removed = new HashSet<>();
/*  77 */     for (int i = 0; i < 3; i++) {
/*  78 */       ScoreboardObjective scoreboardobjective = oldboard.getObjectiveForSlot(i);
/*  79 */       if (scoreboardobjective != null && !removed.contains(scoreboardobjective)) {
/*  80 */         entityplayer.playerConnection.sendPacket((Packet)new PacketPlayOutScoreboardObjective(scoreboardobjective, 1));
/*  81 */         removed.add(scoreboardobjective);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  86 */     Iterator<?> iterator = oldboard.getTeams().iterator();
/*  87 */     while (iterator.hasNext()) {
/*  88 */       ScoreboardTeam scoreboardteam = (ScoreboardTeam)iterator.next();
/*  89 */       entityplayer.playerConnection.sendPacket((Packet)new PacketPlayOutScoreboardTeam(scoreboardteam, 1));
/*     */     } 
/*     */ 
/*     */     
/*  93 */     this.server.getPlayerList().sendScoreboard((ScoreboardServer)newboard, player.getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayer(Player player) {
/*  98 */     this.playerBoards.remove(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getScoreboardScores(IScoreboardCriteria criteria, String name, Consumer<ScoreboardScore> consumer) {
/* 105 */     MinecraftTimings.scoreboardScoreSearch.startTimingIfSync();
/*     */     
/*     */     try {
/* 108 */       for (CraftScoreboard scoreboard : this.scoreboards) {
/* 109 */         Scoreboard board = scoreboard.board;
/* 110 */         board.getObjectivesForCriteria(criteria, name, score -> consumer.accept(score));
/*     */       } 
/*     */     } finally {
/* 113 */       MinecraftTimings.scoreboardScoreSearch.stopTimingIfSync();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftScoreboardManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */