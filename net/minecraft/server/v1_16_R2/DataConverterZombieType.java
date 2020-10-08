/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterZombieType
/*    */   extends DataConverterEntityNameAbstract {
/*    */   public DataConverterZombieType(Schema var0, boolean var1) {
/* 11 */     super("EntityZombieSplitFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Dynamic<?>> a(String var0, Dynamic<?> var1) {
/* 16 */     if (Objects.equals("Zombie", var0))
/* 17 */     { String var2 = "Zombie";
/* 18 */       int var3 = var1.get("ZombieType").asInt(0);
/* 19 */       switch (var3)
/*    */       
/*    */       { 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         default:
/* 35 */           var1 = var1.remove("ZombieType");
/* 36 */           return Pair.of(var2, var1);
/*    */         case 1: case 2: case 3: case 4: case 5: var2 = "ZombieVillager"; var1 = var1.set("Profession", var1.createInt(var3 - 1));
/* 38 */         case 6: break; }  var2 = "Husk"; }  return Pair.of(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterZombieType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */