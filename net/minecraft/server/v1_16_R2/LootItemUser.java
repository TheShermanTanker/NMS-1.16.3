/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public interface LootItemUser
/*    */ {
/*    */   default Set<LootContextParameter<?>> a() {
/* 10 */     return (Set<LootContextParameter<?>>)ImmutableSet.of();
/*    */   }
/*    */   
/*    */   default void a(LootCollector var0) {
/* 14 */     var0.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemUser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */