/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ public class TagsInstance
/*    */ {
/*    */   private static volatile ITagRegistry a;
/*    */   
/*    */   static {
/* 12 */     a = ITagRegistry.a(
/* 13 */         Tags.a((Map<MinecraftKey, Tag<Block>>)TagsBlock.b().stream().collect(Collectors.toMap(Tag.e::a, var0 -> var0))), 
/* 14 */         Tags.a((Map<MinecraftKey, Tag<Item>>)TagsItem.b().stream().collect(Collectors.toMap(Tag.e::a, var0 -> var0))), 
/* 15 */         Tags.a((Map<MinecraftKey, Tag<FluidType>>)TagsFluid.b().stream().collect(Collectors.toMap(Tag.e::a, var0 -> var0))), 
/* 16 */         Tags.a((Map<MinecraftKey, Tag<EntityTypes<?>>>)TagsEntity.b().stream().collect(Collectors.toMap(Tag.e::a, var0 -> var0))));
/*    */   }
/*    */   
/*    */   public static ITagRegistry a() {
/* 20 */     return a;
/*    */   }
/*    */   
/*    */   public static void a(ITagRegistry var0) {
/* 24 */     a = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagsInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */