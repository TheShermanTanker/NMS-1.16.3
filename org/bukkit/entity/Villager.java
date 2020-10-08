/*     */ package org.bukkit.entity;
/*     */ 
/*     */ import com.destroystokyo.paper.entity.villager.Reputation;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ 
/*     */ 
/*     */ public interface Villager
/*     */   extends AbstractVillager
/*     */ {
/*     */   @NotNull
/*     */   Profession getProfession();
/*     */   
/*     */   void setProfession(@NotNull Profession paramProfession);
/*     */   
/*     */   @NotNull
/*     */   Type getVillagerType();
/*     */   
/*     */   void setVillagerType(@NotNull Type paramType);
/*     */   
/*     */   int getVillagerLevel();
/*     */   
/*     */   void setVillagerLevel(int paramInt);
/*     */   
/*     */   int getVillagerExperience();
/*     */   
/*     */   void setVillagerExperience(int paramInt);
/*     */   
/*     */   int getRestocksToday();
/*     */   
/*     */   void setRestocksToday(int paramInt);
/*     */   
/*     */   boolean sleep(@NotNull Location paramLocation);
/*     */   
/*     */   void wakeup();
/*     */   
/*     */   @Nullable
/*     */   Reputation getReputation(@NotNull UUID paramUUID);
/*     */   
/*     */   @NotNull
/*     */   Map<UUID, Reputation> getReputations();
/*     */   
/*     */   void setReputation(@NotNull UUID paramUUID, @NotNull Reputation paramReputation);
/*     */   
/*     */   void setReputations(@NotNull Map<UUID, Reputation> paramMap);
/*     */   
/*     */   void clearReputations();
/*     */   
/*     */   public enum Type
/*     */     implements Keyed
/*     */   {
/* 122 */     DESERT,
/* 123 */     JUNGLE,
/* 124 */     PLAINS,
/* 125 */     SAVANNA,
/* 126 */     SNOW,
/* 127 */     SWAMP,
/* 128 */     TAIGA;
/*     */     private final NamespacedKey key;
/*     */     
/*     */     Type() {
/* 132 */       this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public NamespacedKey getKey() {
/* 138 */       return this.key;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Profession
/*     */     implements Keyed
/*     */   {
/* 147 */     NONE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     ARMORER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     BUTCHER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     CARTOGRAPHER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     CLERIC,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     FARMER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 178 */     FISHERMAN,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     FLETCHER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     LEATHERWORKER,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     LIBRARIAN,
/*     */ 
/*     */ 
/*     */     
/* 197 */     MASON,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 202 */     NITWIT,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     SHEPHERD,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 212 */     TOOLSMITH,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     WEAPONSMITH;
/*     */     private final NamespacedKey key;
/*     */     
/*     */     Profession() {
/* 221 */       this.key = NamespacedKey.minecraft(name().toLowerCase(Locale.ROOT));
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public NamespacedKey getKey() {
/* 227 */       return this.key;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Villager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */