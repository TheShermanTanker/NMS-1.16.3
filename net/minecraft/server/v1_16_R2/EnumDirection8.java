/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Arrays;
/*    */ import java.util.Set;
/*    */ 
/*    */ public enum EnumDirection8
/*    */ {
/*  9 */   NORTH(new EnumDirection[] { EnumDirection.NORTH }),
/* 10 */   NORTH_EAST(new EnumDirection[] { EnumDirection.NORTH, EnumDirection.EAST }),
/* 11 */   EAST(new EnumDirection[] { EnumDirection.EAST }),
/* 12 */   SOUTH_EAST(new EnumDirection[] { EnumDirection.SOUTH, EnumDirection.EAST }),
/* 13 */   SOUTH(new EnumDirection[] { EnumDirection.SOUTH }),
/* 14 */   SOUTH_WEST(new EnumDirection[] { EnumDirection.SOUTH, EnumDirection.WEST }),
/* 15 */   WEST(new EnumDirection[] { EnumDirection.WEST }),
/* 16 */   NORTH_WEST(new EnumDirection[] { EnumDirection.NORTH, EnumDirection.WEST }); private static final int i; private static final int j; private static final int k; private static final int l;
/*    */   static {
/* 18 */     i = 1 << NORTH_WEST.ordinal();
/* 19 */     j = 1 << WEST.ordinal();
/* 20 */     k = 1 << SOUTH_WEST.ordinal();
/* 21 */     l = 1 << SOUTH.ordinal();
/* 22 */     m = 1 << SOUTH_EAST.ordinal();
/* 23 */     n = 1 << EAST.ordinal();
/* 24 */     o = 1 << NORTH_EAST.ordinal();
/* 25 */     p = 1 << NORTH.ordinal();
/*    */   }
/*    */   private static final int m; private static final int n; private static final int o; private static final int p; private final Set<EnumDirection> q;
/*    */   
/*    */   EnumDirection8(EnumDirection... var2) {
/* 30 */     this.q = (Set<EnumDirection>)Sets.immutableEnumSet(Arrays.asList(var2));
/*    */   }
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
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<EnumDirection> a() {
/* 60 */     return this.q;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumDirection8.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */