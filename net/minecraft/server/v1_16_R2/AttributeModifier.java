/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import io.netty.util.internal.ThreadLocalRandom;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class AttributeModifier {
/*     */   private final double b;
/*     */   private final Operation c;
/*  15 */   private static final Logger LOGGER = LogManager.getLogger(); private final Supplier<String> d;
/*     */   private final UUID e;
/*     */   
/*  18 */   public enum Operation { ADDITION(0),
/*  19 */     MULTIPLY_BASE(1),
/*  20 */     MULTIPLY_TOTAL(2);
/*     */     
/*  22 */     private static final Operation[] d = new Operation[] { ADDITION, MULTIPLY_BASE, MULTIPLY_TOTAL };
/*     */     
/*     */     private final int e;
/*     */     
/*     */     Operation(int var2) {
/*  27 */       this.e = var2;
/*     */     } static {
/*     */     
/*     */     } public int a() {
/*  31 */       return this.e;
/*     */     }
/*     */     
/*     */     public static Operation a(int var0) {
/*  35 */       if (var0 < 0 || var0 >= d.length) {
/*  36 */         throw new IllegalArgumentException("No operation with value " + var0);
/*     */       }
/*     */       
/*  39 */       return d[var0];
/*     */     } }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeModifier(String var0, double var1, Operation var3) {
/*  49 */     this(MathHelper.a((Random)ThreadLocalRandom.current()), () -> var0, var1, var3);
/*     */   }
/*     */   
/*     */   public AttributeModifier(UUID var0, String var1, double var2, Operation var4) {
/*  53 */     this(var0, () -> var0, var2, var4);
/*     */   }
/*     */   
/*     */   public AttributeModifier(UUID var0, Supplier<String> var1, double var2, Operation var4) {
/*  57 */     this.e = var0;
/*  58 */     this.d = var1;
/*  59 */     this.b = var2;
/*  60 */     this.c = var4;
/*     */   }
/*     */   
/*     */   public UUID getUniqueId() {
/*  64 */     return this.e;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  68 */     return this.d.get();
/*     */   }
/*     */   
/*     */   public Operation getOperation() {
/*  72 */     return this.c;
/*     */   }
/*     */   
/*     */   public double getAmount() {
/*  76 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  81 */     if (this == var0) {
/*  82 */       return true;
/*     */     }
/*  84 */     if (var0 == null || getClass() != var0.getClass()) {
/*  85 */       return false;
/*     */     }
/*     */     
/*  88 */     AttributeModifier var1 = (AttributeModifier)var0;
/*     */     
/*  90 */     return Objects.equals(this.e, var1.e);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  95 */     return this.e.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 100 */     return "AttributeModifier{amount=" + this.b + ", operation=" + this.c + ", name='" + (String)this.d
/*     */ 
/*     */       
/* 103 */       .get() + '\'' + ", id=" + this.e + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save() {
/* 109 */     NBTTagCompound var0 = new NBTTagCompound();
/* 110 */     var0.setString("Name", getName());
/* 111 */     var0.setDouble("Amount", this.b);
/* 112 */     var0.setInt("Operation", this.c.a());
/* 113 */     var0.a("UUID", this.e);
/* 114 */     return var0;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static AttributeModifier a(NBTTagCompound var0) {
/*     */     try {
/* 120 */       UUID var1 = var0.a("UUID");
/* 121 */       Operation var2 = Operation.a(var0.getInt("Operation"));
/* 122 */       return new AttributeModifier(var1, var0.getString("Name"), var0.getDouble("Amount"), var2);
/* 123 */     } catch (Exception var1) {
/* 124 */       LOGGER.warn("Unable to create attribute: {}", var1.getMessage());
/* 125 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AttributeModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */