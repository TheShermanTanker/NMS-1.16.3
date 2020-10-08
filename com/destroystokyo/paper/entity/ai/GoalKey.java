/*    */ package com.destroystokyo.paper.entity.ai;
/*    */ 
/*    */ import com.google.common.base.Objects;
/*    */ import java.util.StringJoiner;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.entity.Mob;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GoalKey<T extends Mob>
/*    */ {
/*    */   private final Class<T> entityClass;
/*    */   private final NamespacedKey namespacedKey;
/*    */   
/*    */   private GoalKey(@NotNull Class<T> entityClass, @NotNull NamespacedKey namespacedKey) {
/* 24 */     this.entityClass = entityClass;
/* 25 */     this.namespacedKey = namespacedKey;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Class<T> getEntityClass() {
/* 30 */     return this.entityClass;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getNamespacedKey() {
/* 35 */     return this.namespacedKey;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 40 */     if (this == o) return true; 
/* 41 */     if (o == null || getClass() != o.getClass()) return false; 
/* 42 */     GoalKey<?> goalKey = (GoalKey)o;
/* 43 */     return (Objects.equal(this.entityClass, goalKey.entityClass) && 
/* 44 */       Objects.equal(this.namespacedKey, goalKey.namespacedKey));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 49 */     return Objects.hashCode(new Object[] { this.entityClass, this.namespacedKey });
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 54 */     return (new StringJoiner(", ", GoalKey.class.getSimpleName() + "[", "]"))
/* 55 */       .add("entityClass=" + this.entityClass)
/* 56 */       .add("namespacedKey=" + this.namespacedKey)
/* 57 */       .toString();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static <A extends Mob> GoalKey<A> of(@NotNull Class<A> entityClass, @NotNull NamespacedKey namespacedKey) {
/* 62 */     return new GoalKey<>(entityClass, namespacedKey);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\ai\GoalKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */