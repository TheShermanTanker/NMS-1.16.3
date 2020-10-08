/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootContextParameterSet
/*    */ {
/*    */   private final Set<LootContextParameter<?>> a;
/*    */   private final Set<LootContextParameter<?>> b;
/*    */   
/*    */   private LootContextParameterSet(Set<LootContextParameter<?>> var0, Set<LootContextParameter<?>> var1) {
/* 17 */     this.a = (Set<LootContextParameter<?>>)ImmutableSet.copyOf(var0);
/* 18 */     this.b = (Set<LootContextParameter<?>>)ImmutableSet.copyOf((Collection)Sets.union(var0, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<LootContextParameter<?>> getRequired() {
/* 26 */     return this.a;
/*    */   }
/*    */   
/*    */   public Set<LootContextParameter<?>> getOptional() {
/* 30 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 35 */     return "[" + Joiner.on(", ").join(this.b.stream().map(var0 -> (this.a.contains(var0) ? "!" : "") + var0.a()).iterator()) + "]";
/*    */   }
/*    */   
/*    */   public void a(LootCollector var0, LootItemUser var1) {
/* 39 */     Set<LootContextParameter<?>> var2 = var1.a();
/* 40 */     Sets.SetView setView = Sets.difference(var2, this.b);
/* 41 */     if (!setView.isEmpty()) {
/* 42 */       var0.a("Parameters " + setView + " are not provided in this context");
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Builder
/*    */   {
/* 51 */     private final Set<LootContextParameter<?>> a = Sets.newIdentityHashSet();
/* 52 */     private final Set<LootContextParameter<?>> b = Sets.newIdentityHashSet();
/*    */     
/*    */     public Builder addRequired(LootContextParameter<?> var0) {
/* 55 */       if (this.b.contains(var0)) {
/* 56 */         throw new IllegalArgumentException("Parameter " + var0.a() + " is already optional");
/*    */       }
/* 58 */       this.a.add(var0);
/* 59 */       return this;
/*    */     }
/*    */     
/*    */     public Builder addOptional(LootContextParameter<?> var0) {
/* 63 */       if (this.a.contains(var0)) {
/* 64 */         throw new IllegalArgumentException("Parameter " + var0.a() + " is already required");
/*    */       }
/* 66 */       this.b.add(var0);
/* 67 */       return this;
/*    */     }
/*    */     
/*    */     public LootContextParameterSet build() {
/* 71 */       return new LootContextParameterSet(this.a, this.b);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootContextParameterSet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */