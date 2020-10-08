/*     */ package org.bukkit.persistence;
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
/*     */ public interface PersistentDataType<T, Z>
/*     */ {
/*  51 */   public static final PersistentDataType<Byte, Byte> BYTE = new PrimitivePersistentDataType<>(Byte.class);
/*  52 */   public static final PersistentDataType<Short, Short> SHORT = new PrimitivePersistentDataType<>(Short.class);
/*  53 */   public static final PersistentDataType<Integer, Integer> INTEGER = new PrimitivePersistentDataType<>(Integer.class);
/*  54 */   public static final PersistentDataType<Long, Long> LONG = new PrimitivePersistentDataType<>(Long.class);
/*  55 */   public static final PersistentDataType<Float, Float> FLOAT = new PrimitivePersistentDataType<>(Float.class);
/*  56 */   public static final PersistentDataType<Double, Double> DOUBLE = new PrimitivePersistentDataType<>(Double.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static final PersistentDataType<String, String> STRING = new PrimitivePersistentDataType<>(String.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final PersistentDataType<byte[], byte[]> BYTE_ARRAY = (PersistentDataType)new PrimitivePersistentDataType<>((Class)byte[].class);
/*  67 */   public static final PersistentDataType<int[], int[]> INTEGER_ARRAY = (PersistentDataType)new PrimitivePersistentDataType<>((Class)int[].class);
/*  68 */   public static final PersistentDataType<long[], long[]> LONG_ARRAY = (PersistentDataType)new PrimitivePersistentDataType<>((Class)long[].class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = (PersistentDataType)new PrimitivePersistentDataType<>((Class)PersistentDataContainer[].class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public static final PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = new PrimitivePersistentDataType<>(PersistentDataContainer.class);
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
/*     */   T toPrimitive(@NotNull Z paramZ, @NotNull PersistentDataAdapterContext paramPersistentDataAdapterContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Z fromPrimitive(@NotNull T paramT, @NotNull PersistentDataAdapterContext paramPersistentDataAdapterContext);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PrimitivePersistentDataType<T>
/*     */     implements PersistentDataType<T, T>
/*     */   {
/*     */     private final Class<T> primitiveType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     PrimitivePersistentDataType(@NotNull Class<T> primitiveType) {
/* 131 */       this.primitiveType = primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Class<T> getPrimitiveType() {
/* 137 */       return this.primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Class<T> getComplexType() {
/* 143 */       return this.primitiveType;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public T toPrimitive(@NotNull T complex, @NotNull PersistentDataAdapterContext context) {
/* 149 */       return complex;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public T fromPrimitive(@NotNull T primitive, @NotNull PersistentDataAdapterContext context) {
/* 155 */       return primitive;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\persistence\PersistentDataType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */