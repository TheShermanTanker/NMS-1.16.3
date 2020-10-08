/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class PersistentScoreboard extends PersistentBase {
/*  10 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private Scoreboard b;
/*     */   private NBTTagCompound c;
/*     */   
/*     */   public PersistentScoreboard() {
/*  15 */     super("scoreboard");
/*     */   }
/*     */   
/*     */   public void a(Scoreboard scoreboard) {
/*  19 */     this.b = scoreboard;
/*  20 */     if (this.c != null) {
/*  21 */       a(this.c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound nbttagcompound) {
/*  28 */     if (this.b == null) {
/*  29 */       this.c = nbttagcompound;
/*     */     } else {
/*  31 */       b(nbttagcompound.getList("Objectives", 10));
/*  32 */       this.b.a(nbttagcompound.getList("PlayerScores", 10));
/*  33 */       if (nbttagcompound.hasKeyOfType("DisplaySlots", 10)) {
/*  34 */         c(nbttagcompound.getCompound("DisplaySlots"));
/*     */       }
/*     */       
/*  37 */       if (nbttagcompound.hasKeyOfType("Teams", 9)) {
/*  38 */         a(nbttagcompound.getList("Teams", 10));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NBTTagList nbttaglist) {
/*  45 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  46 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*  47 */       String s = nbttagcompound.getString("Name");
/*     */       
/*  49 */       if (s.length() > 16) {
/*  50 */         s = s.substring(0, 16);
/*     */       }
/*     */       
/*  53 */       ScoreboardTeam scoreboardteam = this.b.createTeam(s);
/*  54 */       IChatMutableComponent ichatmutablecomponent = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("DisplayName"));
/*     */       
/*  56 */       if (ichatmutablecomponent != null) {
/*  57 */         scoreboardteam.setDisplayName(ichatmutablecomponent);
/*     */       }
/*     */       
/*  60 */       if (nbttagcompound.hasKeyOfType("TeamColor", 8)) {
/*  61 */         scoreboardteam.setColor(EnumChatFormat.b(nbttagcompound.getString("TeamColor")));
/*     */       }
/*     */       
/*  64 */       if (nbttagcompound.hasKeyOfType("AllowFriendlyFire", 99)) {
/*  65 */         scoreboardteam.setAllowFriendlyFire(nbttagcompound.getBoolean("AllowFriendlyFire"));
/*     */       }
/*     */       
/*  68 */       if (nbttagcompound.hasKeyOfType("SeeFriendlyInvisibles", 99)) {
/*  69 */         scoreboardteam.setCanSeeFriendlyInvisibles(nbttagcompound.getBoolean("SeeFriendlyInvisibles"));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  74 */       if (nbttagcompound.hasKeyOfType("MemberNamePrefix", 8)) {
/*  75 */         IChatMutableComponent ichatmutablecomponent1 = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("MemberNamePrefix"));
/*  76 */         if (ichatmutablecomponent1 != null) {
/*  77 */           scoreboardteam.setPrefix(ichatmutablecomponent1);
/*     */         }
/*     */       } 
/*     */       
/*  81 */       if (nbttagcompound.hasKeyOfType("MemberNameSuffix", 8)) {
/*  82 */         IChatMutableComponent ichatmutablecomponent1 = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("MemberNameSuffix"));
/*  83 */         if (ichatmutablecomponent1 != null) {
/*  84 */           scoreboardteam.setSuffix(ichatmutablecomponent1);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  90 */       if (nbttagcompound.hasKeyOfType("NameTagVisibility", 8)) {
/*  91 */         ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility = ScoreboardTeamBase.EnumNameTagVisibility.a(nbttagcompound.getString("NameTagVisibility"));
/*  92 */         if (scoreboardteambase_enumnametagvisibility != null) {
/*  93 */           scoreboardteam.setNameTagVisibility(scoreboardteambase_enumnametagvisibility);
/*     */         }
/*     */       } 
/*     */       
/*  97 */       if (nbttagcompound.hasKeyOfType("DeathMessageVisibility", 8)) {
/*  98 */         ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility = ScoreboardTeamBase.EnumNameTagVisibility.a(nbttagcompound.getString("DeathMessageVisibility"));
/*  99 */         if (scoreboardteambase_enumnametagvisibility != null) {
/* 100 */           scoreboardteam.setDeathMessageVisibility(scoreboardteambase_enumnametagvisibility);
/*     */         }
/*     */       } 
/*     */       
/* 104 */       if (nbttagcompound.hasKeyOfType("CollisionRule", 8)) {
/* 105 */         ScoreboardTeamBase.EnumTeamPush scoreboardteambase_enumteampush = ScoreboardTeamBase.EnumTeamPush.a(nbttagcompound.getString("CollisionRule"));
/*     */         
/* 107 */         if (scoreboardteambase_enumteampush != null) {
/* 108 */           scoreboardteam.setCollisionRule(scoreboardteambase_enumteampush);
/*     */         }
/*     */       } 
/*     */       
/* 112 */       a(scoreboardteam, nbttagcompound.getList("Players", 8));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(ScoreboardTeam scoreboardteam, NBTTagList nbttaglist) {
/* 118 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 119 */       this.b.addPlayerToTeam(nbttaglist.getString(i), scoreboardteam);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(NBTTagCompound nbttagcompound) {
/* 125 */     for (int i = 0; i < 19; i++) {
/* 126 */       if (nbttagcompound.hasKeyOfType("slot_" + i, 8)) {
/* 127 */         String s = nbttagcompound.getString("slot_" + i);
/* 128 */         ScoreboardObjective scoreboardobjective = this.b.getObjective(s);
/*     */         
/* 130 */         this.b.setDisplaySlot(i, scoreboardobjective);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(NBTTagList nbttaglist) {
/* 137 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 138 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*     */       
/* 140 */       IScoreboardCriteria.a(nbttagcompound.getString("CriteriaName")).ifPresent(iscoreboardcriteria -> {
/*     */             String s = nbttagcompound.getString("Name");
/*     */             if (s.length() > 16) {
/*     */               s = s.substring(0, 16);
/*     */             }
/*     */             IChatMutableComponent ichatmutablecomponent = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("DisplayName"));
/*     */             IScoreboardCriteria.EnumScoreboardHealthDisplay iscoreboardcriteria_enumscoreboardhealthdisplay = IScoreboardCriteria.EnumScoreboardHealthDisplay.a(nbttagcompound.getString("RenderType"));
/*     */             this.b.registerObjective(s, iscoreboardcriteria, ichatmutablecomponent, iscoreboardcriteria_enumscoreboardhealthdisplay);
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 158 */     if (this.b == null) {
/* 159 */       LOGGER.warn("Tried to save scoreboard without having a scoreboard...");
/* 160 */       return nbttagcompound;
/*     */     } 
/* 162 */     nbttagcompound.set("Objectives", e());
/* 163 */     nbttagcompound.set("PlayerScores", this.b.i());
/* 164 */     nbttagcompound.set("Teams", a());
/* 165 */     d(nbttagcompound);
/* 166 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NBTTagList a() {
/* 171 */     NBTTagList nbttaglist = new NBTTagList();
/* 172 */     Collection<ScoreboardTeam> collection = this.b.getTeams();
/* 173 */     Iterator<ScoreboardTeam> iterator = collection.iterator();
/*     */     
/* 175 */     while (iterator.hasNext()) {
/* 176 */       ScoreboardTeam scoreboardteam = iterator.next();
/* 177 */       if (!PaperConfig.saveEmptyScoreboardTeams && scoreboardteam.getPlayerNameSet().isEmpty())
/* 178 */         continue;  NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/* 180 */       nbttagcompound.setString("Name", scoreboardteam.getName());
/* 181 */       nbttagcompound.setString("DisplayName", IChatBaseComponent.ChatSerializer.a(scoreboardteam.getDisplayName()));
/* 182 */       if (scoreboardteam.getColor().b() >= 0) {
/* 183 */         nbttagcompound.setString("TeamColor", scoreboardteam.getColor().f());
/*     */       }
/*     */       
/* 186 */       nbttagcompound.setBoolean("AllowFriendlyFire", scoreboardteam.allowFriendlyFire());
/* 187 */       nbttagcompound.setBoolean("SeeFriendlyInvisibles", scoreboardteam.canSeeFriendlyInvisibles());
/* 188 */       nbttagcompound.setString("MemberNamePrefix", IChatBaseComponent.ChatSerializer.a(scoreboardteam.getPrefix()));
/* 189 */       nbttagcompound.setString("MemberNameSuffix", IChatBaseComponent.ChatSerializer.a(scoreboardteam.getSuffix()));
/* 190 */       nbttagcompound.setString("NameTagVisibility", (scoreboardteam.getNameTagVisibility()).e);
/* 191 */       nbttagcompound.setString("DeathMessageVisibility", (scoreboardteam.getDeathMessageVisibility()).e);
/* 192 */       nbttagcompound.setString("CollisionRule", (scoreboardteam.getCollisionRule()).e);
/* 193 */       NBTTagList nbttaglist1 = new NBTTagList();
/* 194 */       Iterator<String> iterator1 = scoreboardteam.getPlayerNameSet().iterator();
/*     */       
/* 196 */       while (iterator1.hasNext()) {
/* 197 */         String s = iterator1.next();
/*     */         
/* 199 */         nbttaglist1.add(NBTTagString.a(s));
/*     */       } 
/*     */       
/* 202 */       nbttagcompound.set("Players", nbttaglist1);
/* 203 */       nbttaglist.add(nbttagcompound);
/*     */     } 
/*     */     
/* 206 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   protected void d(NBTTagCompound nbttagcompound) {
/* 210 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 211 */     boolean flag = false;
/*     */     
/* 213 */     for (int i = 0; i < 19; i++) {
/* 214 */       ScoreboardObjective scoreboardobjective = this.b.getObjectiveForSlot(i);
/*     */       
/* 216 */       if (scoreboardobjective != null) {
/* 217 */         nbttagcompound1.setString("slot_" + i, scoreboardobjective.getName());
/* 218 */         flag = true;
/*     */       } 
/*     */     } 
/*     */     
/* 222 */     if (flag) {
/* 223 */       nbttagcompound.set("DisplaySlots", nbttagcompound1);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected NBTTagList e() {
/* 229 */     NBTTagList nbttaglist = new NBTTagList();
/* 230 */     Collection<ScoreboardObjective> collection = this.b.getObjectives();
/* 231 */     Iterator<ScoreboardObjective> iterator = collection.iterator();
/*     */     
/* 233 */     while (iterator.hasNext()) {
/* 234 */       ScoreboardObjective scoreboardobjective = iterator.next();
/*     */       
/* 236 */       if (scoreboardobjective.getCriteria() != null) {
/* 237 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */         
/* 239 */         nbttagcompound.setString("Name", scoreboardobjective.getName());
/* 240 */         nbttagcompound.setString("CriteriaName", scoreboardobjective.getCriteria().getName());
/* 241 */         nbttagcompound.setString("DisplayName", IChatBaseComponent.ChatSerializer.a(scoreboardobjective.getDisplayName()));
/* 242 */         nbttagcompound.setString("RenderType", scoreboardobjective.getRenderType().a());
/* 243 */         nbttaglist.add(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     return nbttaglist;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */