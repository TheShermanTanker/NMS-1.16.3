/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class SavedFile {
/*  4 */   public static final SavedFile ADVANCEMENTS = new SavedFile("advancements");
/*  5 */   public static final SavedFile STATS = new SavedFile("stats");
/*  6 */   public static final SavedFile PLAYERDATA = new SavedFile("playerdata");
/*  7 */   public static final SavedFile PLAYERS = new SavedFile("players");
/*  8 */   public static final SavedFile LEVEL_DAT = new SavedFile("level.dat");
/*  9 */   public static final SavedFile GENERATED = new SavedFile("generated");
/* 10 */   public static final SavedFile DATAPACKS = new SavedFile("datapacks");
/* 11 */   public static final SavedFile RESOURCES_ZIP = new SavedFile("resources.zip");
/* 12 */   public static final SavedFile ROOT = new SavedFile(".");
/*    */   
/*    */   private final String j;
/*    */   
/*    */   private SavedFile(String var0) {
/* 17 */     this.j = var0;
/*    */   }
/*    */   
/*    */   public String a() {
/* 21 */     return this.j;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "/" + this.j;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SavedFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */