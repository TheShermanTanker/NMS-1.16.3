/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class BlockStateBoolean
/*    */   extends IBlockState<Boolean> {
/*  9 */   private final ImmutableSet<Boolean> a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
/*    */   
/*    */   protected BlockStateBoolean(String s) {
/* 12 */     super(s, Boolean.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<Boolean> getValues() {
/* 17 */     return (Collection<Boolean>)this.a;
/*    */   }
/*    */   
/*    */   public static BlockStateBoolean of(String s) {
/* 21 */     return new BlockStateBoolean(s);
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Boolean> b(String s) {
/* 26 */     return (!"true".equals(s) && !"false".equals(s)) ? Optional.<Boolean>empty() : Optional.<Boolean>of(Boolean.valueOf(s));
/*    */   }
/*    */   
/*    */   public String a(Boolean obool) {
/* 30 */     return obool.toString();
/*    */   }
/*    */   
/*    */   public boolean equals_unused(Object object) {
/* 34 */     if (this == object)
/* 35 */       return true; 
/* 36 */     if (object instanceof BlockStateBoolean && equals(object)) {
/* 37 */       BlockStateBoolean blockstateboolean = (BlockStateBoolean)object;
/*    */       
/* 39 */       return this.a.equals(blockstateboolean.a);
/*    */     } 
/* 41 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int b() {
/* 47 */     return 31 * super.b() + this.a.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStateBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */