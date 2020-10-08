/*     */ package org.bukkit.craftbukkit.v1_16_R2.boss;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.server.v1_16_R2.BossBattle;
/*     */ import net.minecraft.server.v1_16_R2.BossBattleServer;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.PacketPlayOutBoss;
/*     */ import org.bukkit.boss.BarColor;
/*     */ import org.bukkit.boss.BarFlag;
/*     */ import org.bukkit.boss.BarStyle;
/*     */ import org.bukkit.boss.BossBar;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class CraftBossBar implements BossBar {
/*     */   private final BossBattleServer handle;
/*     */   private Map<BarFlag, FlagContainer> flags;
/*     */   
/*     */   public CraftBossBar(String title, BarColor color, BarStyle style, BarFlag... flags) {
/*  28 */     this
/*     */ 
/*     */       
/*  31 */       .handle = new BossBattleServer(CraftChatMessage.fromString(title, true)[0], convertColor(color), convertStyle(style));
/*     */ 
/*     */     
/*  34 */     initialize();
/*     */     
/*  36 */     for (BarFlag flag : flags) {
/*  37 */       addFlag(flag);
/*     */     }
/*     */     
/*  40 */     setColor(color);
/*  41 */     setStyle(style);
/*     */   }
/*     */   
/*     */   public CraftBossBar(BossBattleServer bossBattleServer) {
/*  45 */     this.handle = bossBattleServer;
/*  46 */     initialize();
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  50 */     this.flags = new HashMap<>();
/*  51 */     Objects.requireNonNull(this.handle); Objects.requireNonNull(this.handle); this.flags.put(BarFlag.DARKEN_SKY, new FlagContainer(this.handle::isDarkenSky, this.handle::setDarkenSky));
/*  52 */     Objects.requireNonNull(this.handle); Objects.requireNonNull(this.handle); this.flags.put(BarFlag.PLAY_BOSS_MUSIC, new FlagContainer(this.handle::isPlayMusic, this.handle::setPlayMusic));
/*  53 */     Objects.requireNonNull(this.handle); Objects.requireNonNull(this.handle); this.flags.put(BarFlag.CREATE_FOG, new FlagContainer(this.handle::isCreateFog, this.handle::setCreateFog));
/*     */   }
/*     */   
/*     */   private BarColor convertColor(BossBattle.BarColor color) {
/*  57 */     BarColor bukkitColor = BarColor.valueOf(color.name());
/*  58 */     return (bukkitColor == null) ? BarColor.WHITE : bukkitColor;
/*     */   }
/*     */   
/*     */   private BossBattle.BarColor convertColor(BarColor color) {
/*  62 */     BossBattle.BarColor nmsColor = BossBattle.BarColor.valueOf(color.name());
/*  63 */     return (nmsColor == null) ? BossBattle.BarColor.WHITE : nmsColor;
/*     */   }
/*     */   
/*     */   private BossBattle.BarStyle convertStyle(BarStyle style) {
/*  67 */     switch (style)
/*     */     
/*     */     { default:
/*  70 */         return BossBattle.BarStyle.PROGRESS;
/*     */       case NOTCHED_6:
/*  72 */         return BossBattle.BarStyle.NOTCHED_6;
/*     */       case NOTCHED_10:
/*  74 */         return BossBattle.BarStyle.NOTCHED_10;
/*     */       case NOTCHED_12:
/*  76 */         return BossBattle.BarStyle.NOTCHED_12;
/*     */       case NOTCHED_20:
/*  78 */         break; }  return BossBattle.BarStyle.NOTCHED_20;
/*     */   }
/*     */ 
/*     */   
/*     */   private BarStyle convertStyle(BossBattle.BarStyle style) {
/*  83 */     switch (style)
/*     */     
/*     */     { default:
/*  86 */         return BarStyle.SOLID;
/*     */       case NOTCHED_6:
/*  88 */         return BarStyle.SEGMENTED_6;
/*     */       case NOTCHED_10:
/*  90 */         return BarStyle.SEGMENTED_10;
/*     */       case NOTCHED_12:
/*  92 */         return BarStyle.SEGMENTED_12;
/*     */       case NOTCHED_20:
/*  94 */         break; }  return BarStyle.SEGMENTED_20;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 100 */     return CraftChatMessage.fromComponent(this.handle.title);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTitle(String title) {
/* 105 */     this.handle.title = CraftChatMessage.fromString(title, true)[0];
/* 106 */     this.handle.sendUpdate(PacketPlayOutBoss.Action.UPDATE_NAME);
/*     */   }
/*     */ 
/*     */   
/*     */   public BarColor getColor() {
/* 111 */     return convertColor(this.handle.color);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(BarColor color) {
/* 116 */     this.handle.color = convertColor(color);
/* 117 */     this.handle.sendUpdate(PacketPlayOutBoss.Action.UPDATE_STYLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public BarStyle getStyle() {
/* 122 */     return convertStyle(this.handle.style);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStyle(BarStyle style) {
/* 127 */     this.handle.style = convertStyle(style);
/* 128 */     this.handle.sendUpdate(PacketPlayOutBoss.Action.UPDATE_STYLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFlag(BarFlag flag) {
/* 133 */     FlagContainer flagContainer = this.flags.get(flag);
/* 134 */     if (flagContainer != null) {
/* 135 */       flagContainer.set.accept(Boolean.valueOf(true));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFlag(BarFlag flag) {
/* 141 */     FlagContainer flagContainer = this.flags.get(flag);
/* 142 */     if (flagContainer != null) {
/* 143 */       flagContainer.set.accept(Boolean.valueOf(false));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasFlag(BarFlag flag) {
/* 149 */     FlagContainer flagContainer = this.flags.get(flag);
/* 150 */     if (flagContainer != null) {
/* 151 */       return ((Boolean)flagContainer.get.get()).booleanValue();
/*     */     }
/* 153 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setProgress(double progress) {
/* 158 */     Preconditions.checkArgument((progress >= 0.0D && progress <= 1.0D), "Progress must be between 0.0 and 1.0 (%s)", Double.valueOf(progress));
/* 159 */     this.handle.setProgress((float)progress);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/* 164 */     return this.handle.getProgress();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(Player player) {
/* 169 */     Preconditions.checkArgument((player != null), "player == null");
/* 170 */     Preconditions.checkArgument(((((CraftPlayer)player).getHandle()).playerConnection != null), "player is not fully connected (wait for PlayerJoinEvent)");
/*     */     
/* 172 */     this.handle.addPlayer(((CraftPlayer)player).getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayer(Player player) {
/* 177 */     Preconditions.checkArgument((player != null), "player == null");
/*     */     
/* 179 */     this.handle.removePlayer(((CraftPlayer)player).getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Player> getPlayers() {
/* 184 */     ImmutableList.Builder<Player> players = ImmutableList.builder();
/* 185 */     for (EntityPlayer p : this.handle.getPlayers()) {
/* 186 */       players.add(p.getBukkitEntity());
/*     */     }
/* 188 */     return (List<Player>)players.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 193 */     this.handle.setVisible(visible);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 198 */     return this.handle.visible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void show() {
/* 203 */     this.handle.setVisible(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void hide() {
/* 208 */     this.handle.setVisible(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAll() {
/* 213 */     for (Player player : getPlayers()) {
/* 214 */       removePlayer(player);
/*     */     }
/*     */   }
/*     */   
/*     */   private final class FlagContainer
/*     */   {
/*     */     private Supplier<Boolean> get;
/*     */     private Consumer<Boolean> set;
/*     */     
/*     */     private FlagContainer(Supplier<Boolean> get, Consumer<Boolean> set) {
/* 224 */       this.get = get;
/* 225 */       this.set = set;
/*     */     }
/*     */   }
/*     */   
/*     */   public BossBattleServer getHandle() {
/* 230 */     return this.handle;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\boss\CraftBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */