/*     */ package com.destroystokyo.paper.entity.ai;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.EnumSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.server.v1_16_R2.PathfinderGoal;
/*     */ import net.minecraft.server.v1_16_R2.PathfinderGoalSelector;
/*     */ import net.minecraft.server.v1_16_R2.PathfinderGoalWrapped;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMob;
/*     */ 
/*     */ 
/*     */ public class PaperMobGoals
/*     */   implements MobGoals
/*     */ {
/*  21 */   private final Map<PathfinderGoal, PaperVanillaGoal<?>> instanceCache = new HashMap<>();
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> void addGoal(T mob, int priority, Goal<T> goal) {
/*  25 */     CraftMob craftMob = (CraftMob)mob;
/*  26 */     getHandle(craftMob, goal.getTypes()).addGoal(priority, new PaperCustomGoal<>(goal));
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> void removeGoal(T mob, Goal<T> goal) {
/*  31 */     CraftMob craftMob = (CraftMob)mob;
/*  32 */     if (goal instanceof PaperCustomGoal) {
/*  33 */       getHandle(craftMob, goal.getTypes()).removeGoal((PathfinderGoal)goal);
/*  34 */     } else if (goal instanceof PaperVanillaGoal) {
/*  35 */       getHandle(craftMob, goal.getTypes()).removeGoal(((PaperVanillaGoal)goal).getHandle());
/*     */     } else {
/*  37 */       List<PathfinderGoal> toRemove = new LinkedList<>();
/*  38 */       for (PathfinderGoalWrapped item : getHandle(craftMob, goal.getTypes()).getTasks()) {
/*  39 */         if (item.getGoal() instanceof PaperCustomGoal)
/*     */         {
/*  41 */           if (((PaperCustomGoal<T>)item.getGoal()).getHandle() == goal) {
/*  42 */             toRemove.add(item.getGoal());
/*     */           }
/*     */         }
/*     */       } 
/*     */       
/*  47 */       for (PathfinderGoal g : toRemove) {
/*  48 */         getHandle(craftMob, goal.getTypes()).removeGoal(g);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> void removeAllGoals(T mob) {
/*  55 */     for (GoalType type : GoalType.values()) {
/*  56 */       removeAllGoals(mob, type);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> void removeAllGoals(T mob, GoalType type) {
/*  62 */     for (Goal<T> goal : getAllGoals(mob, type)) {
/*  63 */       removeGoal(mob, goal);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> void removeGoal(T mob, GoalKey<T> key) {
/*  69 */     for (Goal<T> goal : getGoals(mob, key)) {
/*  70 */       removeGoal(mob, goal);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> boolean hasGoal(T mob, GoalKey<T> key) {
/*  76 */     for (Goal<T> g : getAllGoals(mob)) {
/*  77 */       if (g.getKey().equals(key)) {
/*  78 */         return true;
/*     */       }
/*     */     } 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Goal<T> getGoal(T mob, GoalKey<T> key) {
/*  86 */     for (Goal<T> g : getAllGoals(mob)) {
/*  87 */       if (g.getKey().equals(key)) {
/*  88 */         return g;
/*     */       }
/*     */     } 
/*  91 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getGoals(T mob, GoalKey<T> key) {
/*  96 */     Set<Goal<T>> goals = new HashSet<>();
/*  97 */     for (Goal<T> g : getAllGoals(mob)) {
/*  98 */       if (g.getKey().equals(key)) {
/*  99 */         goals.add(g);
/*     */       }
/*     */     } 
/* 102 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoals(T mob) {
/* 107 */     Set<Goal<T>> goals = new HashSet<>();
/* 108 */     for (GoalType type : GoalType.values()) {
/* 109 */       goals.addAll(getAllGoals(mob, type));
/*     */     }
/* 111 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoals(T mob, GoalType type) {
/* 116 */     CraftMob craftMob = (CraftMob)mob;
/* 117 */     Set<Goal<T>> goals = new HashSet<>();
/* 118 */     for (PathfinderGoalWrapped item : getHandle(craftMob, type).getTasks()) {
/* 119 */       if (!item.getGoal().getGoalTypes().hasElement((Enum)MobGoalHelper.paperToVanilla(type))) {
/*     */         continue;
/*     */       }
/*     */       
/* 123 */       if (item.getGoal() instanceof PaperCustomGoal) {
/*     */         
/* 125 */         goals.add(((PaperCustomGoal<T>)item.getGoal()).getHandle());
/*     */         continue;
/*     */       } 
/* 128 */       goals.add((Goal<T>)this.instanceCache.computeIfAbsent(item.getGoal(), PaperVanillaGoal::new));
/*     */     } 
/*     */     
/* 131 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoalsWithout(T mob, GoalType type) {
/* 136 */     CraftMob craftMob = (CraftMob)mob;
/* 137 */     Set<Goal<T>> goals = new HashSet<>();
/* 138 */     for (GoalType internalType : GoalType.values()) {
/* 139 */       if (internalType != type)
/*     */       {
/*     */         
/* 142 */         for (PathfinderGoalWrapped item : getHandle(craftMob, internalType).getTasks()) {
/* 143 */           if (item.getGoal().getGoalTypes().hasElement((Enum)MobGoalHelper.paperToVanilla(type))) {
/*     */             continue;
/*     */           }
/*     */           
/* 147 */           if (item.getGoal() instanceof PaperCustomGoal) {
/*     */             
/* 149 */             goals.add(((PaperCustomGoal<T>)item.getGoal()).getHandle());
/*     */             continue;
/*     */           } 
/* 152 */           goals.add((Goal<T>)this.instanceCache.computeIfAbsent(item.getGoal(), PaperVanillaGoal::new));
/*     */         } 
/*     */       }
/*     */     } 
/* 156 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoals(T mob) {
/* 161 */     Set<Goal<T>> goals = new HashSet<>();
/* 162 */     for (GoalType type : GoalType.values()) {
/* 163 */       goals.addAll(getRunningGoals(mob, type));
/*     */     }
/* 165 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoals(T mob, GoalType type) {
/* 170 */     CraftMob craftMob = (CraftMob)mob;
/* 171 */     Set<Goal<T>> goals = new HashSet<>();
/* 172 */     getHandle(craftMob, type).getExecutingGoals()
/* 173 */       .filter(item -> item.getGoal().getGoalTypes().hasElement((Enum)MobGoalHelper.paperToVanilla(type)))
/* 174 */       .forEach(item -> {
/*     */           if (item.getGoal() instanceof PaperCustomGoal) {
/*     */             goals.add(((PaperCustomGoal)item.getGoal()).getHandle());
/*     */           } else {
/*     */             goals.add(this.instanceCache.computeIfAbsent(item.getGoal(), PaperVanillaGoal::new));
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 183 */     return goals;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoalsWithout(T mob, GoalType type) {
/* 188 */     CraftMob craftMob = (CraftMob)mob;
/* 189 */     Set<Goal<T>> goals = new HashSet<>();
/* 190 */     for (GoalType internalType : GoalType.values()) {
/* 191 */       if (internalType != type)
/*     */       {
/*     */         
/* 194 */         getHandle(craftMob, internalType).getExecutingGoals()
/* 195 */           .filter(item -> !item.getGoal().getGoalTypes().hasElement((Enum)MobGoalHelper.paperToVanilla(type)))
/* 196 */           .forEach(item -> {
/*     */               if (item.getGoal() instanceof PaperCustomGoal) {
/*     */                 goals.add(((PaperCustomGoal)item.getGoal()).getHandle());
/*     */               } else {
/*     */                 goals.add(this.instanceCache.computeIfAbsent(item.getGoal(), PaperVanillaGoal::new));
/*     */               } 
/*     */             });
/*     */       }
/*     */     } 
/*     */     
/* 206 */     return goals;
/*     */   }
/*     */   
/*     */   private PathfinderGoalSelector getHandle(CraftMob mob, EnumSet<GoalType> types) {
/* 210 */     if (types.contains(GoalType.TARGET)) {
/* 211 */       return (mob.getHandle()).targetSelector;
/*     */     }
/* 213 */     return (mob.getHandle()).goalSelector;
/*     */   }
/*     */ 
/*     */   
/*     */   private PathfinderGoalSelector getHandle(CraftMob mob, GoalType type) {
/* 218 */     if (type == GoalType.TARGET) {
/* 219 */       return (mob.getHandle()).targetSelector;
/*     */     }
/* 221 */     return (mob.getHandle()).goalSelector;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\PaperMobGoals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */