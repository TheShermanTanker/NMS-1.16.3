/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class ScheduleActivity
/*    */ {
/* 12 */   private final List<ActivityFrame> a = Lists.newArrayList();
/*    */ 
/*    */   
/*    */   private int b;
/*    */ 
/*    */ 
/*    */   
/*    */   public ScheduleActivity a(int var0, float var1) {
/* 20 */     this.a.add(new ActivityFrame(var0, var1));
/* 21 */     b();
/* 22 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void b() {
/* 32 */     Int2ObjectAVLTreeMap int2ObjectAVLTreeMap = new Int2ObjectAVLTreeMap();
/* 33 */     this.a.forEach(var1 -> (ActivityFrame)var0.put(var1.a(), var1));
/*    */     
/* 35 */     this.a.clear();
/* 36 */     this.a.addAll((Collection<? extends ActivityFrame>)int2ObjectAVLTreeMap.values());
/*    */     
/* 38 */     this.b = 0;
/*    */   }
/*    */   
/*    */   public float a(int var0) {
/* 42 */     if (this.a.size() <= 0) {
/* 43 */       return 0.0F;
/*    */     }
/*    */     
/* 46 */     ActivityFrame var1 = this.a.get(this.b);
/* 47 */     ActivityFrame var2 = this.a.get(this.a.size() - 1);
/* 48 */     boolean var3 = (var0 < var1.a());
/*    */     
/* 50 */     int var4 = var3 ? 0 : this.b;
/* 51 */     float var5 = var3 ? var2.b() : var1.b();
/*    */     
/* 53 */     for (int var6 = var4; var6 < this.a.size(); var6++) {
/* 54 */       ActivityFrame var7 = this.a.get(var6);
/* 55 */       if (var7.a() > var0) {
/*    */         break;
/*    */       }
/* 58 */       this.b = var6;
/* 59 */       var5 = var7.b();
/*    */     } 
/*    */     
/* 62 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScheduleActivity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */