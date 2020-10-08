/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class ScheduleBuilder {
/*    */   private final Schedule a;
/* 10 */   private final List<a> b = Lists.newArrayList();
/*    */   
/*    */   public ScheduleBuilder(Schedule var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public ScheduleBuilder a(int var0, Activity var1) {
/* 17 */     this.b.add(new a(var0, var1));
/* 18 */     return this;
/*    */   }
/*    */   
/*    */   public Schedule a() {
/* 22 */     ((Set)this.b.stream()
/* 23 */       .map(a::b)
/* 24 */       .collect(Collectors.toSet()))
/* 25 */       .forEach(this.a::a);
/*    */     
/* 27 */     this.b.forEach(var0 -> {
/*    */           Activity var1 = var0.b();
/*    */ 
/*    */ 
/*    */           
/*    */           this.a.c(var1).forEach(());
/*    */ 
/*    */           
/*    */           this.a.b(var1).a(var0.a(), 1.0F);
/*    */         });
/*    */ 
/*    */     
/* 39 */     return this.a;
/*    */   }
/*    */   
/*    */   static class a {
/*    */     private final int a;
/*    */     private final Activity b;
/*    */     
/*    */     public a(int var0, Activity var1) {
/* 47 */       this.a = var0;
/* 48 */       this.b = var1;
/*    */     }
/*    */     
/*    */     public int a() {
/* 52 */       return this.a;
/*    */     }
/*    */     
/*    */     public Activity b() {
/* 56 */       return this.b;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScheduleBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */