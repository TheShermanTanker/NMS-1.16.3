/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.exception.ServerInternalException;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class WorldPersistentData
/*     */ {
/*  21 */   private static final Logger LOGGER = LogManager.getLogger();
/*  22 */   public final Map<String, PersistentBase> data = Maps.newHashMap();
/*     */   private final DataFixer c;
/*     */   private final File d;
/*     */   
/*     */   public WorldPersistentData(File file, DataFixer datafixer) {
/*  27 */     this.c = datafixer;
/*  28 */     this.d = file;
/*     */   }
/*     */   
/*     */   private File a(String s) {
/*  32 */     return new File(this.d, s + ".dat");
/*     */   }
/*     */   
/*     */   public <T extends PersistentBase> T a(Supplier<T> supplier, String s) {
/*  36 */     T t0 = b(supplier, s);
/*     */     
/*  38 */     if (t0 != null) {
/*  39 */       return t0;
/*     */     }
/*  41 */     PersistentBase persistentBase = (PersistentBase)supplier.get();
/*     */     
/*  43 */     a(persistentBase);
/*  44 */     return (T)persistentBase;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public <T extends PersistentBase> T b(Supplier<T> supplier, String s) {
/*  50 */     PersistentBase persistentBase = this.data.get(s);
/*     */     
/*  52 */     if (persistentBase == null && !this.data.containsKey(s)) {
/*  53 */       persistentBase = c(supplier, s);
/*  54 */       this.data.put(s, persistentBase);
/*     */     } 
/*     */     
/*  57 */     return (T)persistentBase;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private <T extends PersistentBase> T c(Supplier<T> supplier, String s) {
/*     */     try {
/*  63 */       File file = a(s);
/*     */       
/*  65 */       if (file.exists()) {
/*  66 */         PersistentBase persistentBase = (PersistentBase)supplier.get();
/*  67 */         NBTTagCompound nbttagcompound = a(s, SharedConstants.getGameVersion().getWorldVersion());
/*     */         
/*  69 */         persistentBase.a(nbttagcompound.getCompound("data"));
/*  70 */         return (T)persistentBase;
/*     */       } 
/*  72 */     } catch (Exception exception) {
/*  73 */       LOGGER.error("Error loading saved data: {}", s, exception);
/*     */     } 
/*     */     
/*  76 */     return null;
/*     */   }
/*     */   
/*     */   public void a(PersistentBase persistentbase) {
/*  80 */     this.data.put(persistentbase.getId(), persistentbase);
/*     */   }
/*     */   public NBTTagCompound a(String s, int i) throws IOException {
/*     */     Object object;
/*  84 */     File file = a(s);
/*  85 */     FileInputStream fileinputstream = new FileInputStream(file);
/*  86 */     Throwable throwable = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  91 */       PushbackInputStream pushbackinputstream = new PushbackInputStream(fileinputstream, 2);
/*  92 */       Throwable throwable1 = null;
/*     */       
/*     */       try {
/*     */         NBTTagCompound nbttagcompound;
/*     */         
/*  97 */         if (a(pushbackinputstream)) {
/*  98 */           nbttagcompound = NBTCompressedStreamTools.a(pushbackinputstream);
/*     */         } else {
/* 100 */           DataInputStream datainputstream = new DataInputStream(pushbackinputstream);
/*     */           
/* 102 */           Object object1 = null;
/*     */           
/*     */           try {
/* 105 */             nbttagcompound = NBTCompressedStreamTools.a(datainputstream);
/* 106 */           } catch (Throwable throwable2) {
/* 107 */             object1 = throwable2;
/* 108 */             throw throwable2;
/*     */           } finally {
/* 110 */             if (datainputstream != null) {
/* 111 */               if (object1 != null) {
/*     */                 try {
/* 113 */                   datainputstream.close();
/* 114 */                 } catch (Throwable throwable3) {
/* 115 */                   ((Throwable)object1).addSuppressed(throwable3);
/*     */                 } 
/*     */               } else {
/* 118 */                 datainputstream.close();
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 125 */         int j = nbttagcompound.hasKeyOfType("DataVersion", 99) ? nbttagcompound.getInt("DataVersion") : 1343;
/*     */         
/* 127 */         object = GameProfileSerializer.a(this.c, DataFixTypes.SAVED_DATA, nbttagcompound, j, i);
/* 128 */       } catch (Throwable throwable4) {
/* 129 */         throwable1 = throwable4;
/* 130 */         throw throwable4;
/*     */       } finally {
/* 132 */         if (pushbackinputstream != null) {
/* 133 */           if (throwable1 != null) {
/*     */             try {
/* 135 */               pushbackinputstream.close();
/* 136 */             } catch (Throwable throwable5) {
/* 137 */               throwable1.addSuppressed(throwable5);
/*     */             } 
/*     */           } else {
/* 140 */             pushbackinputstream.close();
/*     */           }
/*     */         
/*     */         }
/*     */       } 
/* 145 */     } catch (Throwable throwable6) {
/* 146 */       throwable = throwable6;
/* 147 */       ServerInternalException.reportInternalException(throwable);
/* 148 */       throw throwable6;
/*     */     } finally {
/* 150 */       if (fileinputstream != null) {
/* 151 */         if (throwable != null) {
/*     */           try {
/* 153 */             fileinputstream.close();
/* 154 */           } catch (Throwable throwable7) {
/* 155 */             throwable.addSuppressed(throwable7);
/*     */           } 
/*     */         } else {
/* 158 */           fileinputstream.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 164 */     return (NBTTagCompound)object;
/*     */   }
/*     */   
/*     */   private boolean a(PushbackInputStream pushbackinputstream) throws IOException {
/* 168 */     byte[] abyte = new byte[2];
/* 169 */     boolean flag = false;
/* 170 */     int i = pushbackinputstream.read(abyte, 0, 2);
/*     */     
/* 172 */     if (i == 2) {
/* 173 */       int j = (abyte[1] & 0xFF) << 8 | abyte[0] & 0xFF;
/*     */       
/* 175 */       if (j == 35615) {
/* 176 */         flag = true;
/*     */       }
/*     */     } 
/*     */     
/* 180 */     if (i != 0) {
/* 181 */       pushbackinputstream.unread(abyte, 0, i);
/*     */     }
/*     */     
/* 184 */     return flag;
/*     */   }
/*     */   
/*     */   public void a() {
/* 188 */     Iterator<PersistentBase> iterator = this.data.values().iterator();
/*     */     
/* 190 */     while (iterator.hasNext()) {
/* 191 */       PersistentBase persistentbase = iterator.next();
/*     */       
/* 193 */       if (persistentbase != null)
/* 194 */         persistentbase.a(a(persistentbase.getId())); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldPersistentData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */