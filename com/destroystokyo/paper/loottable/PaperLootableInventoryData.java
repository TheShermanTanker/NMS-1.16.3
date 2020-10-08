/*     */ package com.destroystokyo.paper.loottable;
/*     */ import com.destroystokyo.paper.PaperWorldConfig;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.loot.LootTable;
/*     */ 
/*     */ public class PaperLootableInventoryData {
/*  16 */   private static final Random RANDOM = new Random();
/*     */   
/*  18 */   private long lastFill = -1L;
/*  19 */   private long nextRefill = -1L;
/*  20 */   private int numRefills = 0;
/*     */   private Map<UUID, Long> lootedPlayers;
/*     */   private final PaperLootableInventory lootable;
/*     */   
/*     */   public PaperLootableInventoryData(PaperLootableInventory lootable) {
/*  25 */     this.lootable = lootable;
/*     */   }
/*     */   
/*     */   long getLastFill() {
/*  29 */     return this.lastFill;
/*     */   }
/*     */   
/*     */   long getNextRefill() {
/*  33 */     return this.nextRefill;
/*     */   }
/*     */   
/*     */   long setNextRefill(long nextRefill) {
/*  37 */     long prev = this.nextRefill;
/*  38 */     this.nextRefill = nextRefill;
/*  39 */     return prev;
/*     */   }
/*     */   
/*     */   public boolean shouldReplenish(@Nullable EntityHuman player) {
/*  43 */     LootTable table = this.lootable.getLootTable();
/*     */ 
/*     */     
/*  46 */     if (table == null) {
/*  47 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  51 */     if (this.lastFill == -1L || !(this.lootable.getNMSWorld()).paperConfig.autoReplenishLootables) {
/*  52 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  56 */     if (player == null) {
/*  57 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  61 */     if (this.nextRefill == -1L) {
/*  62 */       return false;
/*     */     }
/*     */     
/*  65 */     PaperWorldConfig paperConfig = (this.lootable.getNMSWorld()).paperConfig;
/*     */ 
/*     */     
/*  68 */     if (paperConfig.maxLootableRefills != -1 && this.numRefills >= paperConfig.maxLootableRefills) {
/*  69 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  73 */     if (this.nextRefill > System.currentTimeMillis()) {
/*  74 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  78 */     Player bukkitPlayer = (Player)player.getBukkitEntity();
/*  79 */     LootableInventoryReplenishEvent event = new LootableInventoryReplenishEvent(bukkitPlayer, this.lootable.getAPILootableInventory());
/*  80 */     if (paperConfig.restrictPlayerReloot && hasPlayerLooted(player.getUniqueID())) {
/*  81 */       event.setCancelled(true);
/*     */     }
/*  83 */     return event.callEvent();
/*     */   }
/*     */   public void processRefill(@Nullable EntityHuman player) {
/*  86 */     this.lastFill = System.currentTimeMillis();
/*  87 */     PaperWorldConfig paperConfig = (this.lootable.getNMSWorld()).paperConfig;
/*  88 */     if (paperConfig.autoReplenishLootables) {
/*  89 */       int min = paperConfig.lootableRegenMin;
/*  90 */       int max = paperConfig.lootableRegenMax;
/*  91 */       this.nextRefill = this.lastFill + (min + RANDOM.nextInt(max - min + 1)) * 1000L;
/*  92 */       this.numRefills++;
/*  93 */       if (paperConfig.changeLootTableSeedOnFill) {
/*  94 */         this.lootable.setSeed(0L);
/*     */       }
/*  96 */       if (player != null) {
/*  97 */         setPlayerLootedState(player.getUniqueID(), true);
/*     */       }
/*     */     } else {
/* 100 */       this.lootable.clearLootTable();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadNbt(NBTTagCompound base) {
/* 106 */     if (!base.hasKeyOfType("Paper.LootableData", 10)) {
/*     */       return;
/*     */     }
/* 109 */     NBTTagCompound comp = base.getCompound("Paper.LootableData");
/* 110 */     if (comp.hasKey("lastFill")) {
/* 111 */       this.lastFill = comp.getLong("lastFill");
/*     */     }
/* 113 */     if (comp.hasKey("nextRefill")) {
/* 114 */       this.nextRefill = comp.getLong("nextRefill");
/*     */     }
/*     */     
/* 117 */     if (comp.hasKey("numRefills")) {
/* 118 */       this.numRefills = comp.getInt("numRefills");
/*     */     }
/* 120 */     if (comp.hasKeyOfType("lootedPlayers", 9)) {
/* 121 */       NBTTagList list = comp.getList("lootedPlayers", 10);
/* 122 */       int size = list.size();
/* 123 */       if (size > 0) {
/* 124 */         this.lootedPlayers = new HashMap<>(list.size());
/*     */       }
/* 126 */       for (int i = 0; i < size; i++) {
/* 127 */         NBTTagCompound cmp = list.getCompound(i);
/* 128 */         this.lootedPlayers.put(cmp.getUUID("UUID"), Long.valueOf(cmp.getLong("Time")));
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void saveNbt(NBTTagCompound base) {
/* 133 */     NBTTagCompound comp = new NBTTagCompound();
/* 134 */     if (this.nextRefill != -1L) {
/* 135 */       comp.setLong("nextRefill", this.nextRefill);
/*     */     }
/* 137 */     if (this.lastFill != -1L) {
/* 138 */       comp.setLong("lastFill", this.lastFill);
/*     */     }
/* 140 */     if (this.numRefills != 0) {
/* 141 */       comp.setInt("numRefills", this.numRefills);
/*     */     }
/* 143 */     if (this.lootedPlayers != null && !this.lootedPlayers.isEmpty()) {
/* 144 */       NBTTagList list = new NBTTagList();
/* 145 */       for (Map.Entry<UUID, Long> entry : this.lootedPlayers.entrySet()) {
/* 146 */         NBTTagCompound cmp = new NBTTagCompound();
/* 147 */         cmp.setUUID("UUID", entry.getKey());
/* 148 */         cmp.setLong("Time", ((Long)entry.getValue()).longValue());
/* 149 */         list.add(cmp);
/*     */       } 
/* 151 */       comp.set("lootedPlayers", (NBTBase)list);
/*     */     } 
/*     */     
/* 154 */     if (!comp.isEmpty()) {
/* 155 */       base.set("Paper.LootableData", (NBTBase)comp);
/*     */     }
/*     */   }
/*     */   
/*     */   void setPlayerLootedState(UUID player, boolean looted) {
/* 160 */     if (looted && this.lootedPlayers == null) {
/* 161 */       this.lootedPlayers = new HashMap<>();
/*     */     }
/* 163 */     if (looted) {
/* 164 */       if (!this.lootedPlayers.containsKey(player)) {
/* 165 */         this.lootedPlayers.put(player, Long.valueOf(System.currentTimeMillis()));
/*     */       }
/* 167 */     } else if (this.lootedPlayers != null) {
/* 168 */       this.lootedPlayers.remove(player);
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean hasPlayerLooted(UUID player) {
/* 173 */     return (this.lootedPlayers != null && this.lootedPlayers.containsKey(player));
/*     */   }
/*     */   
/*     */   Long getLastLooted(UUID player) {
/* 177 */     return (this.lootedPlayers != null) ? this.lootedPlayers.get(player) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\loottable\PaperLootableInventoryData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */