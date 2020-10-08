/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AttributeMapBase
/*     */ {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  24 */   private final Map<AttributeBase, AttributeModifiable> b = Maps.newHashMap();
/*  25 */   private final Set<AttributeModifiable> c = Sets.newHashSet();
/*     */   private final AttributeProvider d;
/*     */   
/*     */   public AttributeMapBase(AttributeProvider var0) {
/*  29 */     this.d = var0;
/*     */   }
/*     */   
/*     */   private void a(AttributeModifiable var0) {
/*  33 */     if (var0.getAttribute().b()) {
/*  34 */       this.c.add(var0);
/*     */     }
/*     */   }
/*     */   
/*     */   public Set<AttributeModifiable> getAttributes() {
/*  39 */     return this.c;
/*     */   }
/*     */   
/*     */   public Collection<AttributeModifiable> b() {
/*  43 */     return (Collection<AttributeModifiable>)this.b.values().stream().filter(var0 -> var0.getAttribute().b()).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AttributeModifiable a(AttributeBase var0) {
/*  48 */     return this.b.computeIfAbsent(var0, var0 -> this.d.a(this::a, var0));
/*     */   }
/*     */   
/*     */   public boolean b(AttributeBase var0) {
/*  52 */     return (this.b.get(var0) != null || this.d.c(var0));
/*     */   }
/*     */   
/*     */   public boolean a(AttributeBase var0, UUID var1) {
/*  56 */     AttributeModifiable var2 = this.b.get(var0);
/*  57 */     return (var2 != null) ? ((var2.a(var1) != null)) : this.d.b(var0, var1);
/*     */   }
/*     */   
/*     */   public double c(AttributeBase var0) {
/*  61 */     AttributeModifiable var1 = this.b.get(var0);
/*  62 */     return (var1 != null) ? var1.getValue() : this.d.a(var0);
/*     */   }
/*     */   
/*     */   public double d(AttributeBase var0) {
/*  66 */     AttributeModifiable var1 = this.b.get(var0);
/*  67 */     return (var1 != null) ? var1.getBaseValue() : this.d.b(var0);
/*     */   }
/*     */   
/*     */   public double b(AttributeBase var0, UUID var1) {
/*  71 */     AttributeModifiable var2 = this.b.get(var0);
/*  72 */     return (var2 != null) ? var2.a(var1).getAmount() : this.d.a(var0, var1);
/*     */   }
/*     */   
/*     */   public void a(Multimap<AttributeBase, AttributeModifier> var0) {
/*  76 */     var0.asMap().forEach((var0, var1) -> {
/*     */           AttributeModifiable var2 = this.b.get(var0);
/*     */           if (var2 != null) {
/*     */             var1.forEach(var2::removeModifier);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(Multimap<AttributeBase, AttributeModifier> var0) {
/*  86 */     var0.forEach((var0, var1) -> {
/*     */           AttributeModifiable var2 = a(var0);
/*     */           if (var2 != null) {
/*     */             var2.removeModifier(var1);
/*     */             var2.b(var1);
/*     */           } 
/*     */         });
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
/*     */   public NBTTagList c() {
/* 106 */     NBTTagList var0 = new NBTTagList();
/* 107 */     for (AttributeModifiable var2 : this.b.values()) {
/* 108 */       var0.add(var2.g());
/*     */     }
/* 110 */     return var0;
/*     */   }
/*     */   
/*     */   public void a(NBTTagList var0) {
/* 114 */     for (int var1 = 0; var1 < var0.size(); var1++) {
/* 115 */       NBTTagCompound var2 = var0.getCompound(var1);
/* 116 */       String var3 = var2.getString("Name");
/* 117 */       SystemUtils.a(IRegistry.ATTRIBUTE.getOptional(MinecraftKey.a(var3)), var1 -> {
/*     */             AttributeModifiable var2 = a(var1);
/*     */             if (var2 != null)
/*     */               var2.a(var0); 
/*     */           }() -> LOGGER.warn("Ignoring unknown attribute '{}'", var0));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeMapBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */