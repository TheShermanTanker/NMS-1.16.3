/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerInventoryChanged
/*     */   extends CriterionTriggerAbstract<CriterionTriggerInventoryChanged.a>
/*     */ {
/*  16 */   private static final MinecraftKey a = new MinecraftKey("inventory_changed");
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  20 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public a b(JsonObject var0, CriterionConditionEntity.b var1, LootDeserializationContext var2) {
/*  25 */     JsonObject var3 = ChatDeserializer.a(var0, "slots", new JsonObject());
/*  26 */     CriterionConditionValue.IntegerRange var4 = CriterionConditionValue.IntegerRange.a(var3.get("occupied"));
/*  27 */     CriterionConditionValue.IntegerRange var5 = CriterionConditionValue.IntegerRange.a(var3.get("full"));
/*  28 */     CriterionConditionValue.IntegerRange var6 = CriterionConditionValue.IntegerRange.a(var3.get("empty"));
/*  29 */     CriterionConditionItem[] var7 = CriterionConditionItem.b(var0.get("items"));
/*  30 */     return new a(var1, var4, var5, var6, var7);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer var0, PlayerInventory var1, ItemStack var2) {
/*  34 */     int var3 = 0;
/*  35 */     int var4 = 0;
/*  36 */     int var5 = 0;
/*     */     
/*  38 */     for (int var6 = 0; var6 < var1.getSize(); var6++) {
/*  39 */       ItemStack var7 = var1.getItem(var6);
/*  40 */       if (var7.isEmpty()) {
/*  41 */         var4++;
/*     */       } else {
/*  43 */         var5++;
/*  44 */         if (var7.getCount() >= var7.getMaxStackSize()) {
/*  45 */           var3++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     a(var0, var1, var2, var3, var4, var5);
/*     */   }
/*     */   
/*     */   private void a(EntityPlayer var0, PlayerInventory var1, ItemStack var2, int var3, int var4, int var5) {
/*  54 */     a(var0, var5 -> var5.a(var0, var1, var2, var3, var4));
/*     */   }
/*     */   
/*     */   public static class a extends CriterionInstanceAbstract {
/*     */     private final CriterionConditionValue.IntegerRange a;
/*     */     private final CriterionConditionValue.IntegerRange b;
/*     */     private final CriterionConditionValue.IntegerRange c;
/*     */     private final CriterionConditionItem[] d;
/*     */     
/*     */     public a(CriterionConditionEntity.b var0, CriterionConditionValue.IntegerRange var1, CriterionConditionValue.IntegerRange var2, CriterionConditionValue.IntegerRange var3, CriterionConditionItem[] var4) {
/*  64 */       super(CriterionTriggerInventoryChanged.b(), var0);
/*  65 */       this.a = var1;
/*  66 */       this.b = var2;
/*  67 */       this.c = var3;
/*  68 */       this.d = var4;
/*     */     }
/*     */     
/*     */     public static a a(CriterionConditionItem... var0) {
/*  72 */       return new a(CriterionConditionEntity.b.a, CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, var0);
/*     */     }
/*     */     
/*     */     public static a a(IMaterial... var0) {
/*  76 */       CriterionConditionItem[] var1 = new CriterionConditionItem[var0.length];
/*  77 */       for (int var2 = 0; var2 < var0.length; var2++) {
/*  78 */         var1[var2] = new CriterionConditionItem(null, var0[var2].getItem(), CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, CriterionConditionEnchantments.b, CriterionConditionEnchantments.b, null, CriterionConditionNBT.a);
/*     */       }
/*  80 */       return a(var1);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonObject a(LootSerializationContext var0) {
/*  85 */       JsonObject var1 = super.a(var0);
/*     */       
/*  87 */       if (!this.a.c() || !this.b.c() || !this.c.c()) {
/*  88 */         JsonObject var2 = new JsonObject();
/*  89 */         var2.add("occupied", this.a.d());
/*  90 */         var2.add("full", this.b.d());
/*  91 */         var2.add("empty", this.c.d());
/*  92 */         var1.add("slots", (JsonElement)var2);
/*     */       } 
/*     */       
/*  95 */       if (this.d.length > 0) {
/*  96 */         JsonArray var2 = new JsonArray();
/*  97 */         for (CriterionConditionItem var6 : this.d) {
/*  98 */           var2.add(var6.a());
/*     */         }
/* 100 */         var1.add("items", (JsonElement)var2);
/*     */       } 
/*     */       
/* 103 */       return var1;
/*     */     }
/*     */     
/*     */     public boolean a(PlayerInventory var0, ItemStack var1, int var2, int var3, int var4) {
/* 107 */       if (!this.b.d(var2)) {
/* 108 */         return false;
/*     */       }
/* 110 */       if (!this.c.d(var3)) {
/* 111 */         return false;
/*     */       }
/* 113 */       if (!this.a.d(var4)) {
/* 114 */         return false;
/*     */       }
/*     */       
/* 117 */       int var5 = this.d.length;
/* 118 */       if (var5 == 0) {
/* 119 */         return true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 124 */       if (var5 == 1) {
/* 125 */         return (!var1.isEmpty() && this.d[0].a(var1));
/*     */       }
/*     */       
/* 128 */       ObjectArrayList objectArrayList = new ObjectArrayList((Object[])this.d);
/* 129 */       int var7 = var0.getSize();
/* 130 */       for (int var8 = 0; var8 < var7; var8++) {
/* 131 */         if (objectArrayList.isEmpty()) {
/* 132 */           return true;
/*     */         }
/*     */         
/* 135 */         ItemStack var9 = var0.getItem(var8);
/* 136 */         if (!var9.isEmpty()) {
/* 137 */           objectArrayList.removeIf(var1 -> var1.a(var0));
/*     */         }
/*     */       } 
/* 140 */       return objectArrayList.isEmpty();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerInventoryChanged.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */