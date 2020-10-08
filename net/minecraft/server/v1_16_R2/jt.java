/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.nio.file.Path;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class jt
/*    */   extends jw<EntityTypes<?>>
/*    */ {
/*    */   public jt(DebugReportGenerator var0) {
/* 13 */     super(var0, IRegistry.ENTITY_TYPE);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b() {
/* 18 */     a(TagsEntity.SKELETONS).a((EntityTypes<?>[])new EntityTypes[] { EntityTypes.SKELETON, EntityTypes.STRAY, EntityTypes.WITHER_SKELETON });
/* 19 */     a(TagsEntity.RADIERS).a((EntityTypes<?>[])new EntityTypes[] { EntityTypes.EVOKER, EntityTypes.PILLAGER, EntityTypes.RAVAGER, EntityTypes.VINDICATOR, EntityTypes.ILLUSIONER, EntityTypes.WITCH });
/* 20 */     a(TagsEntity.BEEHIVE_INHABITORS).a(EntityTypes.BEE);
/* 21 */     a(TagsEntity.ARROWS).a((EntityTypes<?>[])new EntityTypes[] { EntityTypes.ARROW, EntityTypes.SPECTRAL_ARROW });
/* 22 */     a(TagsEntity.IMPACT_PROJECTILES).a(TagsEntity.ARROWS).a(new EntityTypes[] { EntityTypes.SNOWBALL, EntityTypes.FIREBALL, EntityTypes.SMALL_FIREBALL, EntityTypes.EGG, EntityTypes.TRIDENT, EntityTypes.DRAGON_FIREBALL, EntityTypes.WITHER_SKULL });
/*    */   }
/*    */ 
/*    */   
/*    */   protected Path a(MinecraftKey var0) {
/* 27 */     return this.b.b().resolve("data/" + var0.getNamespace() + "/tags/entity_types/" + var0.getKey() + ".json");
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 32 */     return "Entity Type Tags";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */