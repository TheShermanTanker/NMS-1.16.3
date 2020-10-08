/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.ObjectArraySet;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ public class BlockPropertyWood
/*    */ {
/*  9 */   private static final Set<BlockPropertyWood> i = (Set<BlockPropertyWood>)new ObjectArraySet();
/*    */   
/* 11 */   public static final BlockPropertyWood a = a(new BlockPropertyWood("oak"));
/* 12 */   public static final BlockPropertyWood b = a(new BlockPropertyWood("spruce"));
/* 13 */   public static final BlockPropertyWood c = a(new BlockPropertyWood("birch"));
/* 14 */   public static final BlockPropertyWood d = a(new BlockPropertyWood("acacia"));
/* 15 */   public static final BlockPropertyWood e = a(new BlockPropertyWood("jungle"));
/* 16 */   public static final BlockPropertyWood f = a(new BlockPropertyWood("dark_oak"));
/* 17 */   public static final BlockPropertyWood g = a(new BlockPropertyWood("crimson"));
/* 18 */   public static final BlockPropertyWood h = a(new BlockPropertyWood("warped"));
/*    */   
/*    */   private final String j;
/*    */   
/*    */   protected BlockPropertyWood(String var0) {
/* 23 */     this.j = var0;
/*    */   }
/*    */   
/*    */   private static BlockPropertyWood a(BlockPropertyWood var0) {
/* 27 */     i.add(var0);
/* 28 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */