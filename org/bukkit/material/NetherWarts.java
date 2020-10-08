/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.NetherWartsState;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class NetherWarts
/*    */   extends MaterialData
/*    */ {
/*    */   public NetherWarts() {
/* 15 */     super(Material.LEGACY_NETHER_WARTS);
/*    */   }
/*    */   
/*    */   public NetherWarts(NetherWartsState state) {
/* 19 */     this();
/* 20 */     setState(state);
/*    */   }
/*    */   
/*    */   public NetherWarts(Material type) {
/* 24 */     super(type);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public NetherWarts(Material type, byte data) {
/* 34 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NetherWartsState getState() {
/* 43 */     switch (getData()) {
/*    */       case 0:
/* 45 */         return NetherWartsState.SEEDED;
/*    */       case 1:
/* 47 */         return NetherWartsState.STAGE_ONE;
/*    */       case 2:
/* 49 */         return NetherWartsState.STAGE_TWO;
/*    */     } 
/* 51 */     return NetherWartsState.RIPE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setState(NetherWartsState state) {
/* 61 */     switch (state) {
/*    */       case SEEDED:
/* 63 */         setData((byte)0);
/*    */         return;
/*    */       case STAGE_ONE:
/* 66 */         setData((byte)1);
/*    */         return;
/*    */       case STAGE_TWO:
/* 69 */         setData((byte)2);
/*    */         return;
/*    */       case RIPE:
/* 72 */         setData((byte)3);
/*    */         return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     return getState() + " " + super.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public NetherWarts clone() {
/* 84 */     return (NetherWarts)super.clone();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\NetherWarts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */