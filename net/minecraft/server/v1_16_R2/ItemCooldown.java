/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class ItemCooldown
/*    */ {
/* 10 */   public final Map<Item, Info> cooldowns = Maps.newHashMap();
/*    */   public int currentTick;
/*    */   
/*    */   public boolean hasCooldown(Item var0) {
/* 14 */     return (a(var0, 0.0F) > 0.0F);
/*    */   }
/*    */   
/*    */   public float a(Item var0, float var1) {
/* 18 */     Info var2 = this.cooldowns.get(var0);
/*    */     
/* 20 */     if (var2 != null) {
/* 21 */       float var3 = (Info.a(var2) - Info.b(var2));
/* 22 */       float var4 = Info.a(var2) - this.currentTick + var1;
/* 23 */       return MathHelper.a(var4 / var3, 0.0F, 1.0F);
/*    */     } 
/*    */     
/* 26 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public void a() {
/* 30 */     this.currentTick++;
/*    */     
/* 32 */     if (!this.cooldowns.isEmpty()) {
/* 33 */       for (Iterator<Map.Entry<Item, Info>> var0 = this.cooldowns.entrySet().iterator(); var0.hasNext(); ) {
/* 34 */         Map.Entry<Item, Info> var1 = var0.next();
/* 35 */         if (Info.a((Info)var1.getValue()) <= this.currentTick) {
/* 36 */           var0.remove();
/* 37 */           c(var1.getKey());
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void setCooldown(Item var0, int var1) {
/* 44 */     this.cooldowns.put(var0, new Info(this.currentTick, this.currentTick + var1));
/* 45 */     b(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void b(Item var0, int var1) {}
/*    */ 
/*    */   
/*    */   protected void c(Item var0) {}
/*    */ 
/*    */   
/*    */   public class Info
/*    */   {
/*    */     private final int b;
/*    */     
/*    */     public final int endTick;
/*    */ 
/*    */     
/*    */     private Info(ItemCooldown this$0, int var1, int var2) {
/* 64 */       this.b = var1;
/* 65 */       this.endTick = var2;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemCooldown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */