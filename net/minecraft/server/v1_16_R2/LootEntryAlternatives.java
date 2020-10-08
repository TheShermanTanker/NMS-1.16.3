/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.function.Consumer;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*    */ 
/*    */ public class LootEntryAlternatives
/*    */   extends LootEntryChildrenAbstract
/*    */ {
/*    */   LootEntryAlternatives(LootEntryAbstract[] var0, LootItemCondition[] var1) {
/* 12 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 17 */     return LootEntries.f;
/*    */   }
/*    */ 
/*    */   
/*    */   protected LootEntryChildren a(LootEntryChildren[] var0) {
/* 22 */     switch (var0.length) {
/*    */       case 0:
/* 24 */         return a;
/*    */       case 1:
/* 26 */         return var0[0];
/*    */       case 2:
/* 28 */         return var0[0].b(var0[1]);
/*    */     } 
/* 30 */     return (var1, var2) -> {
/*    */         for (LootEntryChildren var6 : var0) {
/*    */           if (var6.expand(var1, var2)) {
/*    */             return true;
/*    */           }
/*    */         } 
/*    */         return false;
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(LootCollector var0) {
/* 43 */     super.a(var0);
/*    */     
/* 45 */     for (int var1 = 0; var1 < this.c.length - 1; var1++) {
/* 46 */       if (ArrayUtils.isEmpty((Object[])(this.c[var1]).d))
/* 47 */         var0.a("Unreachable entry!"); 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootEntryAbstract.a<a> {
/* 53 */     private final List<LootEntryAbstract> a = Lists.newArrayList();
/*    */     
/*    */     public a(LootEntryAbstract.a<?>... var0) {
/* 56 */       for (LootEntryAbstract.a<?> var4 : var0) {
/* 57 */         this.a.add(var4.b());
/*    */       }
/*    */     }
/*    */ 
/*    */     
/*    */     protected a d() {
/* 63 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public a a(LootEntryAbstract.a<?> var0) {
/* 68 */       this.a.add(var0.b());
/* 69 */       return this;
/*    */     }
/*    */ 
/*    */     
/*    */     public LootEntryAbstract b() {
/* 74 */       return new LootEntryAlternatives(this.a.<LootEntryAbstract>toArray(new LootEntryAbstract[0]), f());
/*    */     }
/*    */   }
/*    */   
/*    */   public static a a(LootEntryAbstract.a<?>... var0) {
/* 79 */     return new a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEntryAlternatives.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */