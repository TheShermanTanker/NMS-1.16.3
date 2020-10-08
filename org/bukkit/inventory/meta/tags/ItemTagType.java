/*     */ package org.bukkit.inventory.meta.tags;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public interface ItemTagType<T, Z>
/*     */ {
/*  55 */   public static final ItemTagType<Byte, Byte> BYTE = new PrimitiveTagType<>(Byte.class);
/*  56 */   public static final ItemTagType<Short, Short> SHORT = new PrimitiveTagType<>(Short.class);
/*  57 */   public static final ItemTagType<Integer, Integer> INTEGER = new PrimitiveTagType<>(Integer.class);
/*  58 */   public static final ItemTagType<Long, Long> LONG = new PrimitiveTagType<>(Long.class);
/*  59 */   public static final ItemTagType<Float, Float> FLOAT = new PrimitiveTagType<>(Float.class);
/*  60 */   public static final ItemTagType<Double, Double> DOUBLE = new PrimitiveTagType<>(Double.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static final ItemTagType<String, String> STRING = new PrimitiveTagType<>(String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final ItemTagType<byte[], byte[]> BYTE_ARRAY = (ItemTagType)new PrimitiveTagType<>((Class)byte[].class);
/*  71 */   public static final ItemTagType<int[], int[]> INTEGER_ARRAY = (ItemTagType)new PrimitiveTagType<>((Class)int[].class);
/*  72 */   public static final ItemTagType<long[], long[]> LONG_ARRAY = (ItemTagType)new PrimitiveTagType<>((Class)long[].class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final ItemTagType<CustomItemTagContainer, CustomItemTagContainer> TAG_CONTAINER = new PrimitiveTagType<>(CustomItemTagContainer.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Class<T> getPrimitiveType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Class<Z> getComplexType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   T toPrimitive(@NotNull Z paramZ, @NotNull ItemTagAdapterContext paramItemTagAdapterContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Z fromPrimitive(@NotNull T paramT, @NotNull ItemTagAdapterContext paramItemTagAdapterContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PrimitiveTagType<T>
/*     */     implements ItemTagType<T, T>
/*     */   {
/*     */     private final Class<T> primitiveType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     PrimitiveTagType(@NotNull Class<T> primitiveType) {
/* 130 */       this.primitiveType = primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Class<T> getPrimitiveType() {
/* 136 */       return this.primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Class<T> getComplexType() {
/* 142 */       return this.primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public T toPrimitive(@NotNull T complex, @NotNull ItemTagAdapterContext context) {
/* 148 */       return complex;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public T fromPrimitive(@NotNull T primitive, @NotNull ItemTagAdapterContext context) {
/* 154 */       return primitive;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\meta\tags\ItemTagType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */