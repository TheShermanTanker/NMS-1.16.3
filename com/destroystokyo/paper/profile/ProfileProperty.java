/*    */ package com.destroystokyo.paper.profile;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.Objects;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProfileProperty
/*    */ {
/*    */   private final String name;
/*    */   private final String value;
/*    */   private final String signature;
/*    */   
/*    */   public ProfileProperty(@NotNull String name, @NotNull String value) {
/* 18 */     this(name, value, null);
/*    */   }
/*    */   
/*    */   public ProfileProperty(@NotNull String name, @NotNull String value, @Nullable String signature) {
/* 22 */     this.name = (String)Preconditions.checkNotNull(name, "ProfileProperty name can not be null");
/* 23 */     this.value = (String)Preconditions.checkNotNull(value, "ProfileProperty value can not be null");
/* 24 */     this.signature = signature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getName() {
/* 32 */     return this.name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getValue() {
/* 40 */     return this.value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getSignature() {
/* 48 */     return this.signature;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSigned() {
/* 55 */     return (this.signature != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object o) {
/* 60 */     if (this == o) return true; 
/* 61 */     if (o == null || getClass() != o.getClass()) return false; 
/* 62 */     ProfileProperty that = (ProfileProperty)o;
/* 63 */     return (Objects.equals(this.name, that.name) && 
/* 64 */       Objects.equals(this.value, that.value) && 
/* 65 */       Objects.equals(this.signature, that.signature));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 70 */     return Objects.hash(new Object[] { this.name });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\profile\ProfileProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */