/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PersistentBase
/*    */ {
/* 13 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private final String id;
/*    */   private boolean c;
/*    */   
/*    */   public PersistentBase(String var0) {
/* 18 */     this.id = var0;
/*    */   }
/*    */   
/*    */   public abstract void a(NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   public abstract NBTTagCompound b(NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   public void b() {
/* 26 */     a(true);
/*    */   }
/*    */   
/*    */   public void a(boolean var0) {
/* 30 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 34 */     return this.c;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void a(File var0) {
/* 42 */     if (!c()) {
/*    */       return;
/*    */     }
/*    */     
/* 46 */     NBTTagCompound var1 = new NBTTagCompound();
/* 47 */     var1.set("data", b(new NBTTagCompound()));
/* 48 */     var1.setInt("DataVersion", SharedConstants.getGameVersion().getWorldVersion());
/*    */     
/*    */     try {
/* 51 */       NBTCompressedStreamTools.a(var1, var0);
/* 52 */     } catch (IOException var2) {
/* 53 */       LOGGER.error("Could not save data {}", this, var2);
/*    */     } 
/* 55 */     a(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PersistentBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */