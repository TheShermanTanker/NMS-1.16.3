/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArraySet;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeModifiable
/*     */ {
/*     */   private final AttributeBase a;
/*  23 */   private final Map<AttributeModifier.Operation, Set<AttributeModifier>> b = Maps.newEnumMap(AttributeModifier.Operation.class);
/*  24 */   private final Map<UUID, AttributeModifier> c = (Map<UUID, AttributeModifier>)new Object2ObjectArrayMap();
/*  25 */   private final Set<AttributeModifier> d = (Set<AttributeModifier>)new ObjectArraySet();
/*     */   private double e;
/*     */   private boolean f = true;
/*     */   private double g;
/*     */   private final Consumer<AttributeModifiable> h;
/*     */   
/*     */   public AttributeModifiable(AttributeBase var0, Consumer<AttributeModifiable> var1) {
/*  32 */     this.a = var0;
/*  33 */     this.h = var1;
/*  34 */     this.e = var0.getDefault();
/*     */   }
/*     */   
/*     */   public AttributeBase getAttribute() {
/*  38 */     return this.a;
/*     */   }
/*     */   
/*     */   public double getBaseValue() {
/*  42 */     return this.e;
/*     */   }
/*     */   
/*     */   public void setValue(double var0) {
/*  46 */     if (var0 == this.e) {
/*     */       return;
/*     */     }
/*  49 */     this.e = var0;
/*  50 */     d();
/*     */   }
/*     */   
/*     */   public Set<AttributeModifier> a(AttributeModifier.Operation var0) {
/*  54 */     return this.b.computeIfAbsent(var0, var0 -> Sets.newHashSet());
/*     */   }
/*     */   
/*     */   public Set<AttributeModifier> getModifiers() {
/*  58 */     return (Set<AttributeModifier>)ImmutableSet.copyOf(this.c.values());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AttributeModifier a(UUID var0) {
/*  63 */     return this.c.get(var0);
/*     */   }
/*     */   
/*     */   public boolean a(AttributeModifier var0) {
/*  67 */     return (this.c.get(var0.getUniqueId()) != null);
/*     */   }
/*     */   
/*     */   private void e(AttributeModifier var0) {
/*  71 */     AttributeModifier var1 = this.c.putIfAbsent(var0.getUniqueId(), var0);
/*  72 */     if (var1 != null) {
/*  73 */       throw new IllegalArgumentException("Modifier is already applied on this attribute!");
/*     */     }
/*     */     
/*  76 */     a(var0.getOperation()).add(var0);
/*  77 */     d();
/*     */   }
/*     */   
/*     */   public void b(AttributeModifier var0) {
/*  81 */     e(var0);
/*     */   }
/*     */   
/*     */   public void addModifier(AttributeModifier var0) {
/*  85 */     e(var0);
/*  86 */     this.d.add(var0);
/*     */   }
/*     */   
/*     */   protected void d() {
/*  90 */     this.f = true;
/*  91 */     this.h.accept(this);
/*     */   }
/*     */   
/*     */   public void removeModifier(AttributeModifier var0) {
/*  95 */     a(var0.getOperation()).remove(var0);
/*  96 */     this.c.remove(var0.getUniqueId());
/*  97 */     this.d.remove(var0);
/*  98 */     d();
/*     */   }
/*     */   
/*     */   public void b(UUID var0) {
/* 102 */     AttributeModifier var1 = a(var0);
/* 103 */     if (var1 != null) {
/* 104 */       removeModifier(var1);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean c(UUID var0) {
/* 109 */     AttributeModifier var1 = a(var0);
/* 110 */     if (var1 != null && this.d.contains(var1)) {
/* 111 */       removeModifier(var1);
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getValue() {
/* 124 */     if (this.f) {
/* 125 */       this.g = h();
/* 126 */       this.f = false;
/*     */     } 
/*     */     
/* 129 */     return this.g;
/*     */   }
/*     */   
/*     */   private double h() {
/* 133 */     double var0 = getBaseValue();
/*     */     
/* 135 */     for (AttributeModifier var3 : b(AttributeModifier.Operation.ADDITION)) {
/* 136 */       var0 += var3.getAmount();
/*     */     }
/*     */     
/* 139 */     double var2 = var0;
/*     */     
/* 141 */     for (AttributeModifier var5 : b(AttributeModifier.Operation.MULTIPLY_BASE)) {
/* 142 */       var2 += var0 * var5.getAmount();
/*     */     }
/*     */     
/* 145 */     for (AttributeModifier var5 : b(AttributeModifier.Operation.MULTIPLY_TOTAL)) {
/* 146 */       var2 *= 1.0D + var5.getAmount();
/*     */     }
/*     */     
/* 149 */     return this.a.a(var2);
/*     */   }
/*     */   
/*     */   private Collection<AttributeModifier> b(AttributeModifier.Operation var0) {
/* 153 */     return this.b.getOrDefault(var0, Collections.emptySet());
/*     */   }
/*     */   
/*     */   public void a(AttributeModifiable var0) {
/* 157 */     this.e = var0.e;
/*     */     
/* 159 */     this.c.clear();
/* 160 */     this.c.putAll(var0.c);
/*     */     
/* 162 */     this.d.clear();
/* 163 */     this.d.addAll(var0.d);
/*     */     
/* 165 */     this.b.clear();
/* 166 */     var0.b.forEach((var0, var1) -> a(var0).addAll(var1));
/*     */ 
/*     */     
/* 169 */     d();
/*     */   }
/*     */   
/*     */   public NBTTagCompound g() {
/* 173 */     NBTTagCompound var0 = new NBTTagCompound();
/*     */     
/* 175 */     var0.setString("Name", IRegistry.ATTRIBUTE.getKey(this.a).toString());
/* 176 */     var0.setDouble("Base", this.e);
/*     */     
/* 178 */     if (!this.d.isEmpty()) {
/* 179 */       NBTTagList var1 = new NBTTagList();
/* 180 */       for (AttributeModifier var3 : this.d) {
/* 181 */         var1.add(var3.save());
/*     */       }
/* 183 */       var0.set("Modifiers", var1);
/*     */     } 
/* 185 */     return var0;
/*     */   }
/*     */   
/*     */   public void a(NBTTagCompound var0) {
/* 189 */     this.e = var0.getDouble("Base");
/* 190 */     if (var0.hasKeyOfType("Modifiers", 9)) {
/* 191 */       NBTTagList var1 = var0.getList("Modifiers", 10);
/*     */       
/* 193 */       for (int var2 = 0; var2 < var1.size(); var2++) {
/* 194 */         AttributeModifier var3 = AttributeModifier.a(var1.getCompound(var2));
/* 195 */         if (var3 != null) {
/*     */ 
/*     */           
/* 198 */           this.c.put(var3.getUniqueId(), var3);
/* 199 */           a(var3.getOperation()).add(var3);
/* 200 */           this.d.add(var3);
/*     */         } 
/*     */       } 
/* 203 */     }  d();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeModifiable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */