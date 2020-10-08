/*     */ package org.bukkit.craftbukkit.v1_16_R2.persistence;
/*     */ 
/*     */ import com.google.common.primitives.Primitives;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagByte;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagByteArray;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagDouble;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagFloat;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagInt;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagIntArray;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagLong;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagLongArray;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagShort;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagString;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CraftPersistentDataTypeRegistry
/*     */ {
/*  30 */   private final Function<Class, TagAdapter> CREATE_ADAPTER = this::createAdapter;
/*     */ 
/*     */   
/*     */   private class TagAdapter<T, Z extends NBTBase>
/*     */   {
/*     */     private final Function<T, Z> builder;
/*     */     private final Function<Z, T> extractor;
/*     */     private final Class<T> primitiveType;
/*     */     private final Class<Z> nbtBaseType;
/*     */     
/*     */     public TagAdapter(Class<T> primitiveType, Class<Z> nbtBaseType, Function<T, Z> builder, Function<Z, T> extractor) {
/*  41 */       this.primitiveType = primitiveType;
/*  42 */       this.nbtBaseType = nbtBaseType;
/*  43 */       this.builder = builder;
/*  44 */       this.extractor = extractor;
/*     */     }
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
/*     */     T extract(NBTBase base) {
/*  60 */       Validate.isInstanceOf(this.nbtBaseType, base, "The provided NBTBase was of the type %s. Expected type %s", new Object[] { base.getClass().getSimpleName(), this.nbtBaseType.getSimpleName() });
/*  61 */       return this.extractor.apply(this.nbtBaseType.cast(base));
/*     */     }
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
/*     */     Z build(Object value) {
/*  76 */       Validate.isInstanceOf(this.primitiveType, value, "The provided value was of the type %s. Expected type %s", new Object[] { value.getClass().getSimpleName(), this.primitiveType.getSimpleName() });
/*  77 */       return this.builder.apply(this.primitiveType.cast(value));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isInstance(NBTBase base) {
/*  88 */       return this.nbtBaseType.isInstance(base);
/*     */     }
/*     */   }
/*     */   
/*  92 */   private final Map<Class, TagAdapter> adapters = (Map)new HashMap<>();
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
/*     */   private <T> TagAdapter createAdapter(Class<T> type) {
/* 106 */     if (!Primitives.isWrapperType(type)) {
/* 107 */       type = Primitives.wrap(type);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     if (Objects.equals(Byte.class, type)) {
/* 114 */       return createAdapter(Byte.class, NBTTagByte.class, NBTTagByte::a, NBTTagByte::asByte);
/*     */     }
/* 116 */     if (Objects.equals(Short.class, type)) {
/* 117 */       return createAdapter(Short.class, NBTTagShort.class, NBTTagShort::a, NBTTagShort::asShort);
/*     */     }
/* 119 */     if (Objects.equals(Integer.class, type)) {
/* 120 */       return createAdapter(Integer.class, NBTTagInt.class, NBTTagInt::a, NBTTagInt::asInt);
/*     */     }
/* 122 */     if (Objects.equals(Long.class, type)) {
/* 123 */       return createAdapter(Long.class, NBTTagLong.class, NBTTagLong::a, NBTTagLong::asLong);
/*     */     }
/* 125 */     if (Objects.equals(Float.class, type)) {
/* 126 */       return createAdapter(Float.class, NBTTagFloat.class, NBTTagFloat::a, NBTTagFloat::asFloat);
/*     */     }
/* 128 */     if (Objects.equals(Double.class, type)) {
/* 129 */       return createAdapter(Double.class, NBTTagDouble.class, NBTTagDouble::a, NBTTagDouble::asDouble);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (Objects.equals(String.class, type)) {
/* 136 */       return createAdapter(String.class, NBTTagString.class, NBTTagString::a, NBTTagString::asString);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 142 */     if (Objects.equals(byte[].class, type)) {
/* 143 */       return createAdapter((Class)byte[].class, NBTTagByteArray.class, array -> new NBTTagByteArray(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getBytes(), n.size()));
/*     */     }
/* 145 */     if (Objects.equals(int[].class, type)) {
/* 146 */       return createAdapter((Class)int[].class, NBTTagIntArray.class, array -> new NBTTagIntArray(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getInts(), n.size()));
/*     */     }
/* 148 */     if (Objects.equals(long[].class, type)) {
/* 149 */       return createAdapter((Class)long[].class, NBTTagLongArray.class, array -> new NBTTagLongArray(Arrays.copyOf(array, array.length)), n -> Arrays.copyOf(n.getLongs(), n.size()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (Objects.equals(PersistentDataContainer[].class, type)) {
/* 156 */       return createAdapter((Class)PersistentDataContainer[].class, NBTTagList.class, containerArray -> {
/*     */             NBTTagList list = new NBTTagList();
/*     */             
/*     */             for (int i = 0; i < containerArray.length; i++) {
/*     */               list.add(((CraftPersistentDataContainer)containerArray[i]).toTagCompound());
/*     */             }
/*     */             
/*     */             return list;
/*     */           }tag -> {
/*     */             CraftPersistentDataContainer[] arrayOfCraftPersistentDataContainer = new CraftPersistentDataContainer[tag.size()];
/*     */             
/*     */             for (int i = 0; i < tag.size(); i++) {
/*     */               CraftPersistentDataContainer container = new CraftPersistentDataContainer(this);
/*     */               
/*     */               NBTTagCompound compound = tag.getCompound(i);
/*     */               
/*     */               for (String key : compound.getKeys()) {
/*     */                 container.put(key, compound.get(key));
/*     */               }
/*     */               
/*     */               arrayOfCraftPersistentDataContainer[i] = container;
/*     */             } 
/*     */             
/*     */             return (PersistentDataContainer[])arrayOfCraftPersistentDataContainer;
/*     */           });
/*     */     }
/* 182 */     if (Objects.equals(PersistentDataContainer.class, type)) {
/* 183 */       return createAdapter(CraftPersistentDataContainer.class, NBTTagCompound.class, CraftPersistentDataContainer::toTagCompound, tag -> {
/*     */             CraftPersistentDataContainer container = new CraftPersistentDataContainer(this);
/*     */             
/*     */             for (String key : tag.getKeys()) {
/*     */               container.put(key, tag.get(key));
/*     */             }
/*     */             return container;
/*     */           });
/*     */     }
/* 192 */     throw new IllegalArgumentException("Could not find a valid TagAdapter implementation for the requested type " + type.getSimpleName());
/*     */   }
/*     */   
/*     */   private <T, Z extends NBTBase> TagAdapter<T, Z> createAdapter(Class<T> primitiveType, Class<Z> nbtBaseType, Function<T, Z> builder, Function<Z, T> extractor) {
/* 196 */     return new TagAdapter<>(primitiveType, nbtBaseType, builder, extractor);
/*     */   }
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
/*     */   public <T> NBTBase wrap(Class<T> type, T value) {
/* 212 */     return (NBTBase)((TagAdapter)this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER)).build(value);
/*     */   }
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
/*     */   public <T> boolean isInstanceOf(Class<T> type, NBTBase base) {
/* 228 */     return ((TagAdapter)this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER)).isInstance(base);
/*     */   }
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
/*     */   public <T> T extract(Class<T> type, NBTBase tag) throws ClassCastException, IllegalArgumentException {
/* 249 */     TagAdapter adapter = this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER);
/* 250 */     Validate.isTrue(adapter.isInstance(tag), "`The found tag instance cannot store %s as it is a %s", new Object[] { type.getSimpleName(), tag.getClass().getSimpleName() });
/*     */     
/* 252 */     Object foundValue = adapter.extract(tag);
/* 253 */     Validate.isInstanceOf(type, foundValue, "The found object is of the type %s. Expected type %s", new Object[] { foundValue.getClass().getSimpleName(), type.getSimpleName() });
/* 254 */     return type.cast(foundValue);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\persistence\CraftPersistentDataTypeRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */