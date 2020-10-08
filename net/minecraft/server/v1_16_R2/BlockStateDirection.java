/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ public class BlockStateDirection
/*    */   extends BlockStateEnum<EnumDirection>
/*    */ {
/*    */   protected BlockStateDirection(String var0, Collection<EnumDirection> var1) {
/* 14 */     super(var0, EnumDirection.class, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BlockStateDirection a(String var0, Predicate<EnumDirection> var1) {
/* 22 */     return a(var0, (Collection<EnumDirection>)Arrays.<EnumDirection>stream(EnumDirection.values()).filter(var1).collect(Collectors.toList()));
/*    */   }
/*    */   
/*    */   public static BlockStateDirection a(String var0, EnumDirection... var1) {
/* 26 */     return a(var0, Lists.newArrayList((Object[])var1));
/*    */   }
/*    */   
/*    */   public static BlockStateDirection a(String var0, Collection<EnumDirection> var1) {
/* 30 */     return new BlockStateDirection(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStateDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */