/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class Tripwire
/*    */   extends MaterialData
/*    */ {
/*    */   public Tripwire() {
/* 15 */     super(Material.LEGACY_TRIPWIRE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public Tripwire(Material type, byte data) {
/* 25 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isActivated() {
/* 34 */     return ((getData() & 0x4) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setActivated(boolean act) {
/* 43 */     int dat = getData() & 0xB;
/* 44 */     if (act) {
/* 45 */       dat |= 0x4;
/*    */     }
/* 47 */     setData((byte)dat);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isObjectTriggering() {
/* 56 */     return ((getData() & 0x1) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setObjectTriggering(boolean trig) {
/* 65 */     int dat = getData() & 0xE;
/* 66 */     if (trig) {
/* 67 */       dat |= 0x1;
/*    */     }
/* 69 */     setData((byte)dat);
/*    */   }
/*    */ 
/*    */   
/*    */   public Tripwire clone() {
/* 74 */     return (Tripwire)super.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 79 */     return super.toString() + (isActivated() ? " Activated" : "") + (isObjectTriggering() ? " Triggered" : "");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Tripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */