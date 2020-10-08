/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.lang.reflect.Array;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*    */ 
/*    */ public class ShapeDetectorBuilder
/*    */ {
/* 16 */   private static final Joiner a = Joiner.on(",");
/*    */   
/* 18 */   private final List<String[]> b = Lists.newArrayList();
/* 19 */   private final Map<Character, Predicate<ShapeDetectorBlock>> c = Maps.newHashMap();
/*    */   private int d;
/*    */   private int e;
/*    */   
/*    */   private ShapeDetectorBuilder() {
/* 24 */     this.c.put(Character.valueOf(' '), Predicates.alwaysTrue());
/*    */   }
/*    */   
/*    */   public ShapeDetectorBuilder a(String... var0) {
/* 28 */     if (ArrayUtils.isEmpty((Object[])var0) || StringUtils.isEmpty(var0[0])) {
/* 29 */       throw new IllegalArgumentException("Empty pattern for aisle");
/*    */     }
/*    */     
/* 32 */     if (this.b.isEmpty()) {
/* 33 */       this.d = var0.length;
/* 34 */       this.e = var0[0].length();
/*    */     } 
/*    */     
/* 37 */     if (var0.length != this.d) {
/* 38 */       throw new IllegalArgumentException("Expected aisle with height of " + this.d + ", but was given one with a height of " + var0.length + ")");
/*    */     }
/*    */     
/* 41 */     for (String var4 : var0) {
/* 42 */       if (var4.length() != this.e) {
/* 43 */         throw new IllegalArgumentException("Not all rows in the given aisle are the correct width (expected " + this.e + ", found one with " + var4.length() + ")");
/*    */       }
/* 45 */       for (char var8 : var4.toCharArray()) {
/* 46 */         if (!this.c.containsKey(Character.valueOf(var8))) {
/* 47 */           this.c.put(Character.valueOf(var8), null);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     this.b.add(var0);
/*    */     
/* 54 */     return this;
/*    */   }
/*    */   
/*    */   public static ShapeDetectorBuilder a() {
/* 58 */     return new ShapeDetectorBuilder();
/*    */   }
/*    */   
/*    */   public ShapeDetectorBuilder a(char var0, Predicate<ShapeDetectorBlock> var1) {
/* 62 */     this.c.put(Character.valueOf(var0), var1);
/*    */     
/* 64 */     return this;
/*    */   }
/*    */   
/*    */   public ShapeDetector b() {
/* 68 */     return new ShapeDetector(c());
/*    */   }
/*    */ 
/*    */   
/*    */   private Predicate<ShapeDetectorBlock>[][][] c() {
/* 73 */     d();
/*    */     
/* 75 */     Predicate[][][] arrayOfPredicate = (Predicate[][][])Array.newInstance(Predicate.class, new int[] { this.b.size(), this.d, this.e });
/*    */     
/* 77 */     for (int var1 = 0; var1 < this.b.size(); var1++) {
/* 78 */       for (int var2 = 0; var2 < this.d; var2++) {
/* 79 */         for (int var3 = 0; var3 < this.e; var3++) {
/* 80 */           arrayOfPredicate[var1][var2][var3] = this.c.get(Character.valueOf(((String[])this.b.get(var1))[var2].charAt(var3)));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 85 */     return (Predicate<ShapeDetectorBlock>[][][])arrayOfPredicate;
/*    */   }
/*    */   
/*    */   private void d() {
/* 89 */     List<Character> var0 = Lists.newArrayList();
/*    */     
/* 91 */     for (Map.Entry<Character, Predicate<ShapeDetectorBlock>> var2 : this.c.entrySet()) {
/* 92 */       if (var2.getValue() == null) {
/* 93 */         var0.add(var2.getKey());
/*    */       }
/*    */     } 
/*    */     
/* 97 */     if (!var0.isEmpty())
/* 98 */       throw new IllegalStateException("Predicates for character(s) " + a.join(var0) + " are missing"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ShapeDetectorBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */