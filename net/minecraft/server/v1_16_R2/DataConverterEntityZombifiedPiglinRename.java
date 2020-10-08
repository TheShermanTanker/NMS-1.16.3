/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterEntityZombifiedPiglinRename
/*    */   extends DataConverterEntityRenameAbstract {
/* 10 */   public static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 11 */     .put("minecraft:zombie_pigman_spawn_egg", "minecraft:zombified_piglin_spawn_egg")
/* 12 */     .build();
/*    */   
/*    */   public DataConverterEntityZombifiedPiglinRename(Schema var0) {
/* 15 */     super("EntityZombifiedPiglinRenameFix", var0, true);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(String var0) {
/* 20 */     return Objects.equals("minecraft:zombie_pigman", var0) ? "minecraft:zombified_piglin" : var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityZombifiedPiglinRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */