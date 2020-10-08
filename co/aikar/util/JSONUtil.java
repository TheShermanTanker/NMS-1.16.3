/*     */ package co.aikar.util;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class JSONUtil
/*     */ {
/*     */   @NotNull
/*     */   public static JSONPair pair(@NotNull String key, @Nullable Object obj) {
/*  32 */     return new JSONPair(key, obj);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static JSONPair pair(long key, @Nullable Object obj) {
/*  37 */     return new JSONPair(String.valueOf(key), obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Map<String, Object> createObject(@NotNull JSONPair... data) {
/*  48 */     return appendObjectData(new LinkedHashMap<>(), data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Map<String, Object> appendObjectData(@NotNull Map<String, Object> parent, @NotNull JSONPair... data) {
/*  60 */     for (JSONPair jSONPair : data) {
/*  61 */       parent.put(jSONPair.key, jSONPair.val);
/*     */     }
/*  63 */     return parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List toArray(@NotNull Object... data) {
/*  74 */     return Lists.newArrayList(data);
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
/*     */   @NotNull
/*     */   public static <E> List toArrayMapper(@NotNull E[] collection, @NotNull Function<E, Object> mapper) {
/*  87 */     return toArrayMapper(Lists.newArrayList((Object[])collection), mapper);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static <E> List toArrayMapper(@NotNull Iterable<E> collection, @NotNull Function<E, Object> mapper) {
/*  92 */     List<Object> array = Lists.newArrayList();
/*  93 */     for (E e : collection) {
/*  94 */       Object object = mapper.apply(e);
/*  95 */       if (object != null) {
/*  96 */         array.add(object);
/*     */       }
/*     */     } 
/*  99 */     return array;
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
/*     */   @NotNull
/*     */   public static <E> Map toObjectMapper(@NotNull E[] collection, @NotNull Function<E, JSONPair> mapper) {
/* 112 */     return toObjectMapper(Lists.newArrayList((Object[])collection), mapper);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static <E> Map toObjectMapper(@NotNull Iterable<E> collection, @NotNull Function<E, JSONPair> mapper) {
/* 117 */     Map<String, Object> object = Maps.newLinkedHashMap();
/* 118 */     for (E e : collection) {
/* 119 */       JSONPair JSONPair = (JSONPair)mapper.apply(e);
/* 120 */       if (JSONPair != null) {
/* 121 */         object.put(JSONPair.key, JSONPair.val);
/*     */       }
/*     */     } 
/* 124 */     return object;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class JSONPair
/*     */   {
/*     */     final String key;
/*     */     
/*     */     final Object val;
/*     */ 
/*     */     
/*     */     JSONPair(@NotNull String key, @NotNull Object val) {
/* 136 */       this.key = key;
/* 137 */       this.val = val;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aika\\util\JSONUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */