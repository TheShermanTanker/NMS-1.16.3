package com.destroystokyo.paper.entity.ai;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface MobGoals {
  <T extends org.bukkit.entity.Mob> void addGoal(@NotNull T paramT, int paramInt, @NotNull Goal<T> paramGoal);
  
  <T extends org.bukkit.entity.Mob> void removeGoal(@NotNull T paramT, @NotNull Goal<T> paramGoal);
  
  <T extends org.bukkit.entity.Mob> void removeAllGoals(@NotNull T paramT);
  
  <T extends org.bukkit.entity.Mob> void removeAllGoals(@NotNull T paramT, @NotNull GoalType paramGoalType);
  
  <T extends org.bukkit.entity.Mob> void removeGoal(@NotNull T paramT, @NotNull GoalKey<T> paramGoalKey);
  
  <T extends org.bukkit.entity.Mob> boolean hasGoal(@NotNull T paramT, @NotNull GoalKey<T> paramGoalKey);
  
  @Nullable
  <T extends org.bukkit.entity.Mob> Goal<T> getGoal(@NotNull T paramT, @NotNull GoalKey<T> paramGoalKey);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getGoals(@NotNull T paramT, @NotNull GoalKey<T> paramGoalKey);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoals(@NotNull T paramT);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoals(@NotNull T paramT, @NotNull GoalType paramGoalType);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getAllGoalsWithout(@NotNull T paramT, @NotNull GoalType paramGoalType);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoals(@NotNull T paramT);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoals(@NotNull T paramT, @NotNull GoalType paramGoalType);
  
  @NotNull
  <T extends org.bukkit.entity.Mob> Collection<Goal<T>> getRunningGoalsWithout(@NotNull T paramT, @NotNull GoalType paramGoalType);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\MobGoals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */