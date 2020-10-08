/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public class BlockStateEnum<T extends Enum<T> & INamable>
/*    */   extends IBlockState<T> {
/*    */   private final ImmutableSet<T> a;
/* 18 */   private final Map<String, T> b = Maps.newHashMap();
/*    */   
/*    */   protected BlockStateEnum(String s, Class<T> oclass, Collection<T> collection) {
/* 21 */     super(s, oclass);
/* 22 */     this.a = ImmutableSet.copyOf(collection);
/* 23 */     Iterator<T> iterator = collection.iterator();
/*    */     
/* 25 */     while (iterator.hasNext()) {
/* 26 */       Enum enum_ = (Enum)iterator.next();
/* 27 */       String s1 = ((INamable)enum_).getName();
/*    */       
/* 29 */       if (this.b.containsKey(s1)) {
/* 30 */         throw new IllegalArgumentException("Multiple values have the same name '" + s1 + "'");
/*    */       }
/*    */       
/* 33 */       this.b.put(s1, (T)enum_);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<T> getValues() {
/* 40 */     return (Collection<T>)this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<T> b(String s) {
/* 45 */     return Optional.ofNullable(this.b.get(s));
/*    */   }
/*    */   
/*    */   public String a(T t0) {
/* 49 */     return ((INamable)t0).getName();
/*    */   }
/*    */   
/*    */   public boolean equals_unused(Object object) {
/* 53 */     if (this == object)
/* 54 */       return true; 
/* 55 */     if (object instanceof BlockStateEnum && equals(object)) {
/* 56 */       BlockStateEnum<?> blockstateenum = (BlockStateEnum)object;
/*    */       
/* 58 */       return (this.a.equals(blockstateenum.a) && this.b.equals(blockstateenum.b));
/*    */     } 
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int b() {
/* 66 */     int i = super.b();
/*    */     
/* 68 */     i = 31 * i + this.a.hashCode();
/* 69 */     i = 31 * i + this.b.hashCode();
/* 70 */     return i;
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> of(String s, Class<T> oclass) {
/* 74 */     return a(s, oclass, (Predicate<T>)Predicates.alwaysTrue());
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> a(String s, Class<T> oclass, Predicate<T> predicate) {
/* 78 */     return a(s, oclass, (Collection<T>)Arrays.<T>stream(oclass.getEnumConstants()).filter(predicate).collect(Collectors.toList()));
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> of(String s, Class<T> oclass, T... at) {
/* 82 */     return a(s, oclass, Lists.newArrayList((Object[])at));
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> a(String s, Class<T> oclass, Collection<T> collection) {
/* 86 */     return new BlockStateEnum<>(s, oclass, collection);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStateEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */