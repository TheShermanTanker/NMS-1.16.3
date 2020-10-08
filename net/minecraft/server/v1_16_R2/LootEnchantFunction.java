/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class LootEnchantFunction
/*     */   extends LootItemFunctionConditional {
/*     */   private final LootValueBounds a;
/*     */   private final int b;
/*     */   
/*     */   private LootEnchantFunction(LootItemCondition[] alootitemcondition, LootValueBounds lootvaluebounds, int i) {
/*  15 */     super(alootitemcondition);
/*  16 */     this.a = lootvaluebounds;
/*  17 */     this.b = i;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  22 */     return LootItemFunctions.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  27 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.KILLER_ENTITY);
/*     */   }
/*     */   
/*     */   private boolean c() {
/*  31 */     return (this.b > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
/*  36 */     Entity entity = loottableinfo.<Entity>getContextParameter(LootContextParameters.KILLER_ENTITY);
/*     */     
/*  38 */     if (entity instanceof EntityLiving) {
/*  39 */       int i = EnchantmentManager.g((EntityLiving)entity);
/*     */       
/*  41 */       if (loottableinfo.hasContextParameter(LootContextParameters.LOOTING_MOD)) {
/*  42 */         i = ((Integer)loottableinfo.<Integer>getContextParameter(LootContextParameters.LOOTING_MOD)).intValue();
/*     */       }
/*     */ 
/*     */       
/*  46 */       if (i <= 0) {
/*  47 */         return itemstack;
/*     */       }
/*     */       
/*  50 */       float f = i * this.a.b(loottableinfo.a());
/*     */       
/*  52 */       itemstack.add(Math.round(f));
/*  53 */       if (c() && itemstack.getCount() > this.b) {
/*  54 */         itemstack.setCount(this.b);
/*     */       }
/*     */     } 
/*     */     
/*  58 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static a a(LootValueBounds lootvaluebounds) {
/*  62 */     return new a(lootvaluebounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootEnchantFunction>
/*     */   {
/*     */     public void a(JsonObject jsonobject, LootEnchantFunction lootenchantfunction, JsonSerializationContext jsonserializationcontext) {
/*  70 */       super.a(jsonobject, lootenchantfunction, jsonserializationcontext);
/*  71 */       jsonobject.add("count", jsonserializationcontext.serialize(lootenchantfunction.a));
/*  72 */       if (lootenchantfunction.c()) {
/*  73 */         jsonobject.add("limit", jsonserializationcontext.serialize(Integer.valueOf(lootenchantfunction.b)));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public LootEnchantFunction b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
/*  80 */       int i = ChatDeserializer.a(jsonobject, "limit", 0);
/*     */       
/*  82 */       return new LootEnchantFunction(alootitemcondition, ChatDeserializer.<LootValueBounds>a(jsonobject, "count", jsondeserializationcontext, LootValueBounds.class), i);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a
/*     */     extends LootItemFunctionConditional.a<a> {
/*     */     private final LootValueBounds a;
/*  89 */     private int b = 0;
/*     */     
/*     */     public a(LootValueBounds lootvaluebounds) {
/*  92 */       this.a = lootvaluebounds;
/*     */     }
/*     */ 
/*     */     
/*     */     protected a d() {
/*  97 */       return this;
/*     */     }
/*     */     
/*     */     public a a(int i) {
/* 101 */       this.b = i;
/* 102 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/* 107 */       return new LootEnchantFunction(g(), this.a, this.b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootEnchantFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */