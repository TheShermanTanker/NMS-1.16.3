/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public class DataConverterZombie
/*    */   extends DataConverterNamedEntity
/*    */ {
/*    */   public DataConverterZombie(Schema var0, boolean var1) {
/* 14 */     super(var0, var1, "EntityZombieVillagerTypeFix", DataConverterTypes.ENTITY, "Zombie");
/*    */   }
/*    */   
/* 17 */   private static final Random a = new Random();
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 20 */     if (var0.get("IsVillager").asBoolean(false)) {
/* 21 */       if (!var0.get("ZombieType").result().isPresent()) {
/* 22 */         int var1 = a(var0.get("VillagerProfession").asInt(-1));
/* 23 */         if (var1 == -1) {
/* 24 */           var1 = a(a.nextInt(6));
/*    */         }
/*    */         
/* 27 */         var0 = var0.set("ZombieType", var0.createInt(var1));
/*    */       } 
/*    */       
/* 30 */       var0 = var0.remove("IsVillager");
/*    */     } 
/* 32 */     return var0;
/*    */   }
/*    */   
/*    */   private int a(int var0) {
/* 36 */     if (var0 < 0 || var0 >= 6) {
/* 37 */       return -1;
/*    */     }
/* 39 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 44 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */