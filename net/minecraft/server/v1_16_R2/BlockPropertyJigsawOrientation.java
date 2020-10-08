/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ 
/*    */ public enum BlockPropertyJigsawOrientation
/*    */   implements INamable {
/*  8 */   DOWN_EAST("down_east", EnumDirection.DOWN, EnumDirection.EAST),
/*  9 */   DOWN_NORTH("down_north", EnumDirection.DOWN, EnumDirection.NORTH),
/* 10 */   DOWN_SOUTH("down_south", EnumDirection.DOWN, EnumDirection.SOUTH),
/* 11 */   DOWN_WEST("down_west", EnumDirection.DOWN, EnumDirection.WEST),
/*    */   
/* 13 */   UP_EAST("up_east", EnumDirection.UP, EnumDirection.EAST),
/* 14 */   UP_NORTH("up_north", EnumDirection.UP, EnumDirection.NORTH),
/* 15 */   UP_SOUTH("up_south", EnumDirection.UP, EnumDirection.SOUTH),
/* 16 */   UP_WEST("up_west", EnumDirection.UP, EnumDirection.WEST),
/*    */   
/* 18 */   WEST_UP("west_up", EnumDirection.WEST, EnumDirection.UP),
/* 19 */   EAST_UP("east_up", EnumDirection.EAST, EnumDirection.UP),
/* 20 */   NORTH_UP("north_up", EnumDirection.NORTH, EnumDirection.UP),
/* 21 */   SOUTH_UP("south_up", EnumDirection.SOUTH, EnumDirection.UP);
/*    */   
/*    */   static {
/* 24 */     m = (Int2ObjectMap<BlockPropertyJigsawOrientation>)new Int2ObjectOpenHashMap((values()).length);
/*    */ 
/*    */     
/* 27 */     for (BlockPropertyJigsawOrientation var3 : values())
/* 28 */       m.put(b(var3.o, var3.p), var3); 
/*    */   }
/*    */   
/*    */   private static final Int2ObjectMap<BlockPropertyJigsawOrientation> m;
/*    */   private final String n;
/*    */   private final EnumDirection o;
/*    */   private final EnumDirection p;
/*    */   
/*    */   private static int b(EnumDirection var0, EnumDirection var1) {
/* 37 */     return var0.ordinal() << 3 | var1.ordinal();
/*    */   }
/*    */   
/*    */   BlockPropertyJigsawOrientation(String var2, EnumDirection var3, EnumDirection var4) {
/* 41 */     this.n = var2;
/* 42 */     this.p = var3;
/* 43 */     this.o = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 48 */     return this.n;
/*    */   }
/*    */   
/*    */   public static BlockPropertyJigsawOrientation a(EnumDirection var0, EnumDirection var1) {
/* 52 */     int var2 = b(var1, var0);
/* 53 */     return (BlockPropertyJigsawOrientation)m.get(var2);
/*    */   }
/*    */   
/*    */   public EnumDirection b() {
/* 57 */     return this.p;
/*    */   }
/*    */   
/*    */   public EnumDirection c() {
/* 61 */     return this.o;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyJigsawOrientation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */