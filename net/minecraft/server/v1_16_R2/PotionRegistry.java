/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public class PotionRegistry
/*    */ {
/*    */   private final String a;
/*    */   private final ImmutableList<MobEffect> b;
/*    */   
/*    */   public static PotionRegistry a(String var0) {
/* 15 */     return IRegistry.POTION.get(MinecraftKey.a(var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public PotionRegistry(MobEffect... var0) {
/* 21 */     this(null, var0);
/*    */   }
/*    */   
/*    */   public PotionRegistry(@Nullable String var0, MobEffect... var1) {
/* 25 */     this.a = var0;
/* 26 */     this.b = ImmutableList.copyOf((Object[])var1);
/*    */   }
/*    */   
/*    */   public String b(String var0) {
/* 30 */     return var0 + ((this.a == null) ? IRegistry.POTION.getKey(this).getKey() : this.a);
/*    */   }
/*    */   
/*    */   public List<MobEffect> a() {
/* 34 */     return (List<MobEffect>)this.b;
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 38 */     if (!this.b.isEmpty()) {
/* 39 */       for (UnmodifiableIterator<MobEffect> unmodifiableIterator = this.b.iterator(); unmodifiableIterator.hasNext(); ) { MobEffect var1 = unmodifiableIterator.next();
/* 40 */         if (var1.getMobEffect().isInstant()) {
/* 41 */           return true;
/*    */         } }
/*    */     
/*    */     }
/*    */     
/* 46 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PotionRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */