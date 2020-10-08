/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Statistic<T>
/*    */   extends IScoreboardCriteria
/*    */ {
/*    */   private final Counter o;
/*    */   private final T p;
/*    */   private final StatisticWrapper<T> q;
/*    */   
/*    */   protected Statistic(StatisticWrapper<T> var0, T var1, Counter var2) {
/* 16 */     super(a(var0, var1));
/* 17 */     this.q = var0;
/* 18 */     this.o = var2;
/* 19 */     this.p = var1;
/*    */   }
/*    */   
/*    */   public static <T> String a(StatisticWrapper<T> var0, T var1) {
/* 23 */     return a(IRegistry.STATS.getKey(var0)) + ":" + a(var0.getRegistry().getKey(var1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static <T> String a(@Nullable MinecraftKey var0) {
/* 29 */     return var0.toString().replace(':', '.');
/*    */   }
/*    */   
/*    */   public StatisticWrapper<T> getWrapper() {
/* 33 */     return this.q;
/*    */   }
/*    */   
/*    */   public T b() {
/* 37 */     return this.p;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 46 */     return (this == var0 || (var0 instanceof Statistic && Objects.equals(getName(), ((Statistic)var0).getName())));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 51 */     return getName().hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     return "Stat{name=" + 
/* 57 */       getName() + ", formatter=" + this.o + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Statistic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */