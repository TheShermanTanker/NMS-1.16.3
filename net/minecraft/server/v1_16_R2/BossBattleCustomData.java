/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossBattleCustomData
/*    */ {
/* 14 */   private final Map<MinecraftKey, BossBattleCustom> a = Maps.newHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BossBattleCustom a(MinecraftKey var0) {
/* 21 */     return this.a.get(var0);
/*    */   }
/*    */   
/*    */   public BossBattleCustom register(MinecraftKey var0, IChatBaseComponent var1) {
/* 25 */     BossBattleCustom var2 = new BossBattleCustom(var0, var1);
/* 26 */     this.a.put(var0, var2);
/* 27 */     return var2;
/*    */   }
/*    */   
/*    */   public void remove(BossBattleCustom var0) {
/* 31 */     this.a.remove(var0.getKey());
/*    */   }
/*    */   
/*    */   public Collection<MinecraftKey> a() {
/* 35 */     return this.a.keySet();
/*    */   }
/*    */   
/*    */   public Collection<BossBattleCustom> getBattles() {
/* 39 */     return this.a.values();
/*    */   }
/*    */   
/*    */   public NBTTagCompound save() {
/* 43 */     NBTTagCompound var0 = new NBTTagCompound();
/*    */     
/* 45 */     for (BossBattleCustom var2 : this.a.values()) {
/* 46 */       var0.set(var2.getKey().toString(), var2.f());
/*    */     }
/*    */     
/* 49 */     return var0;
/*    */   }
/*    */   
/*    */   public void load(NBTTagCompound var0) {
/* 53 */     for (String var2 : var0.getKeys()) {
/* 54 */       MinecraftKey var3 = new MinecraftKey(var2);
/* 55 */       this.a.put(var3, BossBattleCustom.a(var0.getCompound(var2), var3));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a(EntityPlayer var0) {
/* 60 */     for (BossBattleCustom var2 : this.a.values()) {
/* 61 */       var2.c(var0);
/*    */     }
/*    */   }
/*    */   
/*    */   public void b(EntityPlayer var0) {
/* 66 */     for (BossBattleCustom var2 : this.a.values())
/* 67 */       var2.d(var0); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BossBattleCustomData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */