/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterEntityRavagerRename
/*    */   extends DataConverterEntityRenameAbstract {
/* 10 */   public static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 11 */     .put("minecraft:illager_beast_spawn_egg", "minecraft:ravager_spawn_egg")
/* 12 */     .build();
/*    */   
/*    */   public DataConverterEntityRavagerRename(Schema var0, boolean var1) {
/* 15 */     super("EntityRavagerRenameFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(String var0) {
/* 20 */     return Objects.equals("minecraft:illager_beast", var0) ? "minecraft:ravager" : var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityRavagerRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */