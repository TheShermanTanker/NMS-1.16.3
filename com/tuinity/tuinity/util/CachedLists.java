/*    */ package com.tuinity.tuinity.util;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.AxisAlignedBB;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.UnsafeList;
/*    */ 
/*    */ public class CachedLists
/*    */ {
/* 11 */   static final UnsafeList<AxisAlignedBB> TEMP_COLLISION_LIST = new UnsafeList(1024);
/*    */   static boolean tempCollisionListInUse;
/*    */   
/*    */   public static List<AxisAlignedBB> getTempCollisionList() {
/* 15 */     if (!Bukkit.isPrimaryThread() || tempCollisionListInUse) {
/* 16 */       return (List<AxisAlignedBB>)new UnsafeList(16);
/*    */     }
/* 18 */     tempCollisionListInUse = true;
/* 19 */     return (List<AxisAlignedBB>)TEMP_COLLISION_LIST;
/*    */   }
/*    */   
/*    */   public static void returnTempCollisionList(List<AxisAlignedBB> list) {
/* 23 */     if (list != TEMP_COLLISION_LIST) {
/*    */       return;
/*    */     }
/* 26 */     ((UnsafeList)list).setSize(0);
/* 27 */     tempCollisionListInUse = false;
/*    */   }
/*    */   
/* 30 */   static final UnsafeList<Entity> TEMP_GET_ENTITIES_LIST = new UnsafeList(1024);
/*    */   static boolean tempGetEntitiesListInUse;
/*    */   
/*    */   public static List<Entity> getTempGetEntitiesList() {
/* 34 */     if (!Bukkit.isPrimaryThread() || tempGetEntitiesListInUse) {
/* 35 */       return (List<Entity>)new UnsafeList(16);
/*    */     }
/* 37 */     tempGetEntitiesListInUse = true;
/* 38 */     return (List<Entity>)TEMP_GET_ENTITIES_LIST;
/*    */   }
/*    */   
/*    */   public static void returnTempGetEntitiesList(List<Entity> list) {
/* 42 */     if (list != TEMP_GET_ENTITIES_LIST) {
/*    */       return;
/*    */     }
/* 45 */     ((UnsafeList)list).setSize(0);
/* 46 */     tempGetEntitiesListInUse = false;
/*    */   }
/*    */   
/*    */   public static void reset() {
/* 50 */     TEMP_COLLISION_LIST.completeReset();
/* 51 */     TEMP_GET_ENTITIES_LIST.completeReset();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinit\\util\CachedLists.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */