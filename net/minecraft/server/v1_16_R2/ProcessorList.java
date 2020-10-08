/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ProcessorList {
/*    */   private final List<DefinedStructureProcessor> a;
/*    */   
/*    */   public ProcessorList(List<DefinedStructureProcessor> var0) {
/*  9 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public List<DefinedStructureProcessor> a() {
/* 13 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 18 */     return "ProcessorList[" + this.a + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ProcessorList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */