/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Consumer;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class AttributeProvider
/*    */ {
/*    */   private final Map<AttributeBase, AttributeModifiable> a;
/*    */   
/*    */   public AttributeProvider(Map<AttributeBase, AttributeModifiable> var0) {
/* 16 */     this.a = (Map<AttributeBase, AttributeModifiable>)ImmutableMap.copyOf(var0);
/*    */   }
/*    */   
/*    */   private AttributeModifiable d(AttributeBase var0) {
/* 20 */     AttributeModifiable var1 = this.a.get(var0);
/* 21 */     if (var1 == null) {
/* 22 */       throw new IllegalArgumentException("Can't find attribute " + IRegistry.ATTRIBUTE.getKey(var0));
/*    */     }
/* 24 */     return var1;
/*    */   }
/*    */   
/*    */   public double a(AttributeBase var0) {
/* 28 */     return d(var0).getValue();
/*    */   }
/*    */   
/*    */   public double b(AttributeBase var0) {
/* 32 */     return d(var0).getBaseValue();
/*    */   }
/*    */   
/*    */   public double a(AttributeBase var0, UUID var1) {
/* 36 */     AttributeModifier var2 = d(var0).a(var1);
/* 37 */     if (var2 == null) {
/* 38 */       throw new IllegalArgumentException("Can't find modifier " + var1 + " on attribute " + IRegistry.ATTRIBUTE.getKey(var0));
/*    */     }
/* 40 */     return var2.getAmount();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AttributeModifiable a(Consumer<AttributeModifiable> var0, AttributeBase var1) {
/* 45 */     AttributeModifiable var2 = this.a.get(var1);
/* 46 */     if (var2 == null) {
/* 47 */       return null;
/*    */     }
/* 49 */     AttributeModifiable var3 = new AttributeModifiable(var1, var0);
/* 50 */     var3.a(var2);
/* 51 */     return var3;
/*    */   }
/*    */   
/*    */   public static Builder a() {
/* 55 */     return new Builder();
/*    */   }
/*    */   
/*    */   public boolean c(AttributeBase var0) {
/* 59 */     return this.a.containsKey(var0);
/*    */   }
/*    */   
/*    */   public boolean b(AttributeBase var0, UUID var1) {
/* 63 */     AttributeModifiable var2 = this.a.get(var0);
/* 64 */     return (var2 != null && var2.a(var1) != null);
/*    */   }
/*    */   
/*    */   public static class Builder {
/* 68 */     private final Map<AttributeBase, AttributeModifiable> a = Maps.newHashMap();
/*    */     private boolean b;
/*    */     
/*    */     private AttributeModifiable b(AttributeBase var0) {
/* 72 */       AttributeModifiable var1 = new AttributeModifiable(var0, var1 -> {
/*    */             if (this.b) {
/*    */               throw new UnsupportedOperationException("Tried to change value for default attribute instance: " + IRegistry.ATTRIBUTE.getKey(var0));
/*    */             }
/*    */           });
/* 77 */       this.a.put(var0, var1);
/* 78 */       return var1;
/*    */     }
/*    */     
/*    */     public Builder a(AttributeBase var0) {
/* 82 */       b(var0);
/* 83 */       return this;
/*    */     }
/*    */     
/*    */     public Builder a(AttributeBase var0, double var1) {
/* 87 */       AttributeModifiable var3 = b(var0);
/* 88 */       var3.setValue(var1);
/* 89 */       return this;
/*    */     }
/*    */     
/*    */     public AttributeProvider a() {
/* 93 */       this.b = true;
/* 94 */       return new AttributeProvider(this.a);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */