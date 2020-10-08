/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ public interface LootValue
/*    */ {
/*  8 */   public static final MinecraftKey a = new MinecraftKey("constant");
/*  9 */   public static final MinecraftKey b = new MinecraftKey("uniform");
/* 10 */   public static final MinecraftKey c = new MinecraftKey("binomial");
/*    */   
/*    */   int a(Random paramRandom);
/*    */   
/*    */   MinecraftKey a();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */