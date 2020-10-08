/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class CriterionTriggerAbstract<T extends CriterionInstanceAbstract>
/*    */   implements CriterionTrigger<T>
/*    */ {
/*    */   public final void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<T> criteriontrigger_a) {
/* 21 */     ((Set<CriterionTrigger.a<T>>)advancementdataplayer.criterionData.computeIfAbsent(this, advancementdataplayer1 -> Sets.newHashSet()))
/*    */       
/* 23 */       .add(criteriontrigger_a);
/*    */   }
/*    */ 
/*    */   
/*    */   public final void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<T> criteriontrigger_a) {
/* 28 */     Set<CriterionTrigger.a<T>> set = (Set<CriterionTrigger.a<T>>)advancementdataplayer.criterionData.get(this);
/*    */     
/* 30 */     if (set != null) {
/* 31 */       set.remove(criteriontrigger_a);
/* 32 */       if (set.isEmpty()) {
/* 33 */         advancementdataplayer.criterionData.remove(this);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public final void a(AdvancementDataPlayer advancementdataplayer) {
/* 41 */     advancementdataplayer.criterionData.remove(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final T a(JsonObject jsonobject, LootDeserializationContext lootdeserializationcontext) {
/* 48 */     CriterionConditionEntity.b criterionconditionentity_b = CriterionConditionEntity.b.a(jsonobject, "player", lootdeserializationcontext);
/*    */     
/* 50 */     return b(jsonobject, criterionconditionentity_b, lootdeserializationcontext);
/*    */   }
/*    */   
/*    */   protected void a(EntityPlayer entityplayer, Predicate<T> predicate) {
/* 54 */     AdvancementDataPlayer advancementdataplayer = entityplayer.getAdvancementData();
/* 55 */     Set<CriterionTrigger.a<T>> set = (Set<CriterionTrigger.a<T>>)advancementdataplayer.criterionData.get(this);
/*    */     
/* 57 */     if (set != null && !set.isEmpty()) {
/* 58 */       LootTableInfo loottableinfo = CriterionConditionEntity.b(entityplayer, entityplayer);
/* 59 */       List<CriterionTrigger.a<T>> list = null;
/* 60 */       Iterator<CriterionTrigger.a<T>> iterator = set.iterator();
/*    */ 
/*    */ 
/*    */       
/* 64 */       while (iterator.hasNext()) {
/* 65 */         CriterionTrigger.a<CriterionInstanceAbstract> criteriontrigger_a = iterator.next();
/* 66 */         CriterionInstanceAbstract criterionInstanceAbstract = criteriontrigger_a.a();
/*    */         
/* 68 */         if (criterionInstanceAbstract.b().a(loottableinfo) && predicate.test((T)criterionInstanceAbstract)) {
/* 69 */           if (list == null) {
/* 70 */             list = Lists.newArrayList();
/*    */           }
/*    */           
/* 73 */           list.add(criteriontrigger_a);
/*    */         } 
/*    */       } 
/*    */       
/* 77 */       if (list != null) {
/* 78 */         iterator = list.iterator();
/*    */         
/* 80 */         while (iterator.hasNext()) {
/* 81 */           CriterionTrigger.a criteriontrigger_a = iterator.next();
/* 82 */           criteriontrigger_a.a(advancementdataplayer);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   protected abstract T b(JsonObject paramJsonObject, CriterionConditionEntity.b paramb, LootDeserializationContext paramLootDeserializationContext);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionTriggerAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */