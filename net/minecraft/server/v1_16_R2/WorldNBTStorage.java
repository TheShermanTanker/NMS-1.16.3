/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ 
/*     */ 
/*     */ public class WorldNBTStorage
/*     */ {
/*  17 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final File playerDir;
/*     */   protected final DataFixer a;
/*     */   
/*     */   public WorldNBTStorage(Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer) {
/*  22 */     this.a = datafixer;
/*  23 */     this.playerDir = convertable_conversionsession.getWorldFolder(SavedFile.PLAYERDATA).toFile();
/*  24 */     this.playerDir.mkdirs();
/*     */   }
/*     */   
/*     */   public void save(EntityHuman entityhuman) {
/*  28 */     if (!PaperConfig.savePlayerData)
/*     */       return;  try {
/*  30 */       NBTTagCompound nbttagcompound = entityhuman.save(new NBTTagCompound());
/*  31 */       File file = File.createTempFile(entityhuman.getUniqueIDString() + "-", ".dat", this.playerDir);
/*     */       
/*  33 */       NBTCompressedStreamTools.a(nbttagcompound, file);
/*  34 */       File file1 = new File(this.playerDir, entityhuman.getUniqueIDString() + ".dat");
/*  35 */       File file2 = new File(this.playerDir, entityhuman.getUniqueIDString() + ".dat_old");
/*     */       
/*  37 */       SystemUtils.a(file1, file, file2);
/*  38 */     } catch (Exception exception) {
/*  39 */       LOGGER.error("Failed to save player data for {}", entityhuman.getName(), exception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NBTTagCompound load(EntityHuman entityhuman) {
/*  46 */     NBTTagCompound nbttagcompound = null;
/*     */     
/*     */     try {
/*  49 */       File file = new File(this.playerDir, entityhuman.getUniqueIDString() + ".dat");
/*     */       
/*  51 */       boolean usingWrongFile = false;
/*  52 */       if (Bukkit.getOnlineMode() && !file.exists()) {
/*     */         
/*  54 */         file = new File(this.playerDir, UUID.nameUUIDFromBytes(("OfflinePlayer:" + entityhuman.getName()).getBytes("UTF-8")).toString() + ".dat");
/*  55 */         if (file.exists()) {
/*     */           
/*  57 */           usingWrongFile = true;
/*  58 */           Bukkit.getServer().getLogger().warning("Using offline mode UUID file for player " + entityhuman.getName() + " as it is the only copy we can find.");
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*  63 */       if (file.exists() && file.isFile()) {
/*  64 */         nbttagcompound = NBTCompressedStreamTools.a(file);
/*     */       }
/*     */       
/*  67 */       if (usingWrongFile)
/*     */       {
/*  69 */         file.renameTo(new File(file.getPath() + ".offline-read"));
/*     */       }
/*     */     }
/*  72 */     catch (Exception exception) {
/*  73 */       LOGGER.warn("Failed to load player data for {}", entityhuman.getDisplayName().getString());
/*     */     } 
/*     */     
/*  76 */     if (nbttagcompound != null) {
/*     */       
/*  78 */       if (entityhuman instanceof EntityPlayer) {
/*  79 */         CraftPlayer player = (CraftPlayer)entityhuman.getBukkitEntity();
/*     */         
/*  81 */         long modified = (new File(this.playerDir, entityhuman.getUniqueID().toString() + ".dat")).lastModified();
/*  82 */         if (modified < player.getFirstPlayed()) {
/*  83 */           player.setFirstPlayed(modified);
/*     */         }
/*     */       } 
/*     */       
/*  87 */       int i = nbttagcompound.hasKeyOfType("DataVersion", 3) ? nbttagcompound.getInt("DataVersion") : -1;
/*     */       
/*  89 */       entityhuman.load(GameProfileSerializer.a(this.a, DataFixTypes.PLAYER, nbttagcompound, i));
/*     */     } 
/*     */     
/*  92 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getPlayerData(String s) {
/*     */     try {
/*  98 */       File file1 = new File(this.playerDir, s + ".dat");
/*     */       
/* 100 */       if (file1.exists()) {
/* 101 */         return NBTCompressedStreamTools.a(new FileInputStream(file1));
/*     */       }
/* 103 */     } catch (Exception exception) {
/* 104 */       LOGGER.warn("Failed to load player data for " + s);
/*     */     } 
/*     */     
/* 107 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getSeenPlayers() {
/* 112 */     String[] astring = this.playerDir.list();
/*     */     
/* 114 */     if (astring == null) {
/* 115 */       astring = new String[0];
/*     */     }
/*     */     
/* 118 */     for (int i = 0; i < astring.length; i++) {
/* 119 */       if (astring[i].endsWith(".dat")) {
/* 120 */         astring[i] = astring[i].substring(0, astring[i].length() - 4);
/*     */       }
/*     */     } 
/*     */     
/* 124 */     return astring;
/*     */   }
/*     */ 
/*     */   
/*     */   public File getPlayerDir() {
/* 129 */     return this.playerDir;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldNBTStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */