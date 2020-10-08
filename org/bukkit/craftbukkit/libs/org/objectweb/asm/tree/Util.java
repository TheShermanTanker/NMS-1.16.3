/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ final class Util
/*     */ {
/*     */   static <T> List<T> add(List<T> list, T element) {
/*  44 */     List<T> newList = (list == null) ? new ArrayList<T>(1) : list;
/*  45 */     newList.add(element);
/*  46 */     return newList;
/*     */   }
/*     */   
/*     */   static <T> List<T> asArrayList(int length) {
/*  50 */     List<T> list = new ArrayList<T>(length);
/*  51 */     for (int i = 0; i < length; i++) {
/*  52 */       list.add(null);
/*     */     }
/*  54 */     return list;
/*     */   }
/*     */   
/*     */   static <T> List<T> asArrayList(T[] array) {
/*  58 */     if (array == null) {
/*  59 */       return new ArrayList<T>();
/*     */     }
/*  61 */     ArrayList<T> list = new ArrayList<T>(array.length);
/*  62 */     for (T t : array) {
/*  63 */       list.add(t);
/*     */     }
/*  65 */     return list;
/*     */   }
/*     */   
/*     */   static List<Byte> asArrayList(byte[] byteArray) {
/*  69 */     if (byteArray == null) {
/*  70 */       return new ArrayList<Byte>();
/*     */     }
/*  72 */     ArrayList<Byte> byteList = new ArrayList<Byte>(byteArray.length);
/*  73 */     for (byte b : byteArray) {
/*  74 */       byteList.add(Byte.valueOf(b));
/*     */     }
/*  76 */     return byteList;
/*     */   }
/*     */   
/*     */   static List<Boolean> asArrayList(boolean[] booleanArray) {
/*  80 */     if (booleanArray == null) {
/*  81 */       return new ArrayList<Boolean>();
/*     */     }
/*  83 */     ArrayList<Boolean> booleanList = new ArrayList<Boolean>(booleanArray.length);
/*  84 */     for (boolean b : booleanArray) {
/*  85 */       booleanList.add(Boolean.valueOf(b));
/*     */     }
/*  87 */     return booleanList;
/*     */   }
/*     */   
/*     */   static List<Short> asArrayList(short[] shortArray) {
/*  91 */     if (shortArray == null) {
/*  92 */       return new ArrayList<Short>();
/*     */     }
/*  94 */     ArrayList<Short> shortList = new ArrayList<Short>(shortArray.length);
/*  95 */     for (short s : shortArray) {
/*  96 */       shortList.add(Short.valueOf(s));
/*     */     }
/*  98 */     return shortList;
/*     */   }
/*     */   
/*     */   static List<Character> asArrayList(char[] charArray) {
/* 102 */     if (charArray == null) {
/* 103 */       return new ArrayList<Character>();
/*     */     }
/* 105 */     ArrayList<Character> charList = new ArrayList<Character>(charArray.length);
/* 106 */     for (char c : charArray) {
/* 107 */       charList.add(Character.valueOf(c));
/*     */     }
/* 109 */     return charList;
/*     */   }
/*     */   
/*     */   static List<Integer> asArrayList(int[] intArray) {
/* 113 */     if (intArray == null) {
/* 114 */       return new ArrayList<Integer>();
/*     */     }
/* 116 */     ArrayList<Integer> intList = new ArrayList<Integer>(intArray.length);
/* 117 */     for (int i : intArray) {
/* 118 */       intList.add(Integer.valueOf(i));
/*     */     }
/* 120 */     return intList;
/*     */   }
/*     */   
/*     */   static List<Float> asArrayList(float[] floatArray) {
/* 124 */     if (floatArray == null) {
/* 125 */       return new ArrayList<Float>();
/*     */     }
/* 127 */     ArrayList<Float> floatList = new ArrayList<Float>(floatArray.length);
/* 128 */     for (float f : floatArray) {
/* 129 */       floatList.add(Float.valueOf(f));
/*     */     }
/* 131 */     return floatList;
/*     */   }
/*     */   
/*     */   static List<Long> asArrayList(long[] longArray) {
/* 135 */     if (longArray == null) {
/* 136 */       return new ArrayList<Long>();
/*     */     }
/* 138 */     ArrayList<Long> longList = new ArrayList<Long>(longArray.length);
/* 139 */     for (long l : longArray) {
/* 140 */       longList.add(Long.valueOf(l));
/*     */     }
/* 142 */     return longList;
/*     */   }
/*     */   
/*     */   static List<Double> asArrayList(double[] doubleArray) {
/* 146 */     if (doubleArray == null) {
/* 147 */       return new ArrayList<Double>();
/*     */     }
/* 149 */     ArrayList<Double> doubleList = new ArrayList<Double>(doubleArray.length);
/* 150 */     for (double d : doubleArray) {
/* 151 */       doubleList.add(Double.valueOf(d));
/*     */     }
/* 153 */     return doubleList;
/*     */   }
/*     */   
/*     */   static <T> List<T> asArrayList(int length, T[] array) {
/* 157 */     List<T> list = new ArrayList<T>(length);
/* 158 */     for (int i = 0; i < length; i++) {
/* 159 */       list.add(array[i]);
/*     */     }
/* 161 */     return list;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\Util.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */