/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TagsEntity
/*    */ {
/*  9 */   protected static final TagUtil<EntityTypes<?>> a = TagStatic.a(new MinecraftKey("entity_type"), ITagRegistry::getEntityTags);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 14 */   public static final Tag.e<EntityTypes<?>> SKELETONS = a("skeletons");
/* 15 */   public static final Tag.e<EntityTypes<?>> RADIERS = a("raiders");
/* 16 */   public static final Tag.e<EntityTypes<?>> BEEHIVE_INHABITORS = a("beehive_inhabitors");
/* 17 */   public static final Tag.e<EntityTypes<?>> ARROWS = a("arrows");
/* 18 */   public static final Tag.e<EntityTypes<?>> IMPACT_PROJECTILES = a("impact_projectiles");
/*    */   
/*    */   private static Tag.e<EntityTypes<?>> a(String var0) {
/* 21 */     return a.a(var0);
/*    */   }
/*    */   
/*    */   public static Tags<EntityTypes<?>> a() {
/* 25 */     return a.b();
/*    */   }
/*    */   
/*    */   public static List<? extends Tag.e<EntityTypes<?>>> b() {
/* 29 */     return a.c();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagsEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */