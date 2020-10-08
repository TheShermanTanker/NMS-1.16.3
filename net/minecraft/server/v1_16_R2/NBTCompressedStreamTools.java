/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import org.spigotmc.LimitStream;
/*     */ 
/*     */ public class NBTCompressedStreamTools {
/*     */   public static NBTTagCompound a(File file) throws IOException {
/*     */     NBTTagCompound nbttagcompound;
/*  22 */     FileInputStream fileinputstream = new FileInputStream(file);
/*  23 */     Throwable throwable = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  28 */       nbttagcompound = a(fileinputstream);
/*  29 */     } catch (Throwable throwable1) {
/*  30 */       throwable = throwable1;
/*  31 */       throw throwable1;
/*     */     } finally {
/*  33 */       if (fileinputstream != null) {
/*  34 */         if (throwable != null) {
/*     */           try {
/*  36 */             fileinputstream.close();
/*  37 */           } catch (Throwable throwable2) {
/*  38 */             throwable.addSuppressed(throwable2);
/*     */           } 
/*     */         } else {
/*  41 */           fileinputstream.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  47 */     return nbttagcompound;
/*     */   }
/*     */   
/*  50 */   public static NBTTagCompound readNBT(InputStream inputstream) throws IOException { return a(inputstream); } public static NBTTagCompound a(InputStream inputstream) throws IOException {
/*     */     NBTTagCompound nbttagcompound;
/*  52 */     DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new GZIPInputStream(inputstream)));
/*  53 */     Throwable throwable = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  58 */       nbttagcompound = a(datainputstream, NBTReadLimiter.a);
/*  59 */     } catch (Throwable throwable1) {
/*  60 */       throwable = throwable1;
/*  61 */       throw throwable1;
/*     */     } finally {
/*  63 */       if (datainputstream != null) {
/*  64 */         if (throwable != null) {
/*     */           try {
/*  66 */             datainputstream.close();
/*  67 */           } catch (Throwable throwable2) {
/*  68 */             throwable.addSuppressed(throwable2);
/*     */           } 
/*     */         } else {
/*  71 */           datainputstream.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  77 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public static void a(NBTTagCompound nbttagcompound, File file) throws IOException {
/*  81 */     FileOutputStream fileoutputstream = new FileOutputStream(file);
/*  82 */     Throwable throwable = null;
/*     */     
/*     */     try {
/*  85 */       a(nbttagcompound, fileoutputstream);
/*  86 */     } catch (Throwable throwable1) {
/*  87 */       throwable = throwable1;
/*  88 */       throw throwable1;
/*     */     } finally {
/*  90 */       if (fileoutputstream != null) {
/*  91 */         if (throwable != null) {
/*     */           try {
/*  93 */             fileoutputstream.close();
/*  94 */           } catch (Throwable throwable2) {
/*  95 */             throwable.addSuppressed(throwable2);
/*     */           } 
/*     */         } else {
/*  98 */           fileoutputstream.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void writeNBT(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
/* 106 */     a(nbttagcompound, outputstream);
/*     */   } public static void a(NBTTagCompound nbttagcompound, OutputStream outputstream) throws IOException {
/* 108 */     DataOutputStream dataoutputstream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(outputstream)));
/* 109 */     Throwable throwable = null;
/*     */     
/*     */     try {
/* 112 */       a(nbttagcompound, dataoutputstream);
/* 113 */     } catch (Throwable throwable1) {
/* 114 */       throwable = throwable1;
/* 115 */       throw throwable1;
/*     */     } finally {
/* 117 */       if (dataoutputstream != null) {
/* 118 */         if (throwable != null) {
/*     */           try {
/* 120 */             dataoutputstream.close();
/* 121 */           } catch (Throwable throwable2) {
/* 122 */             throwable.addSuppressed(throwable2);
/*     */           } 
/*     */         } else {
/* 125 */           dataoutputstream.close();
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound readNBT(DataInput datainput) throws IOException {
/* 133 */     return a(datainput);
/*     */   } public static NBTTagCompound a(DataInput datainput) throws IOException {
/* 135 */     return a(datainput, NBTReadLimiter.a);
/*     */   }
/*     */ 
/*     */   
/*     */   public static NBTTagCompound a(DataInput datainput, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 140 */     if (datainput instanceof io.netty.buffer.ByteBufInputStream)
/*     */     {
/* 142 */       datainput = new DataInputStream((InputStream)new LimitStream((InputStream)datainput, nbtreadlimiter));
/*     */     }
/*     */     
/* 145 */     NBTBase nbtbase = a(datainput, 0, nbtreadlimiter);
/*     */     
/* 147 */     if (nbtbase instanceof NBTTagCompound) {
/* 148 */       return (NBTTagCompound)nbtbase;
/*     */     }
/* 150 */     throw new IOException("Root tag must be a named compound tag");
/*     */   }
/*     */   
/*     */   public static void writeNBT(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
/* 154 */     a(nbttagcompound, dataoutput);
/*     */   } public static void a(NBTTagCompound nbttagcompound, DataOutput dataoutput) throws IOException {
/* 156 */     a(nbttagcompound, dataoutput);
/*     */   }
/*     */   
/*     */   private static void a(NBTBase nbtbase, DataOutput dataoutput) throws IOException {
/* 160 */     dataoutput.writeByte(nbtbase.getTypeId());
/* 161 */     if (nbtbase.getTypeId() != 0) {
/* 162 */       dataoutput.writeUTF("");
/* 163 */       nbtbase.write(dataoutput);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static NBTBase a(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 168 */     byte b0 = datainput.readByte();
/*     */     
/* 170 */     if (b0 == 0) {
/* 171 */       return NBTTagEnd.b;
/*     */     }
/* 173 */     datainput.readUTF();
/*     */     
/*     */     try {
/* 176 */       return (NBTBase)NBTTagTypes.a(b0).b(datainput, i, nbtreadlimiter);
/* 177 */     } catch (IOException ioexception) {
/* 178 */       CrashReport crashreport = CrashReport.a(ioexception, "Loading NBT data");
/* 179 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("NBT Tag");
/*     */       
/* 181 */       crashreportsystemdetails.a("Tag type", Byte.valueOf(b0));
/* 182 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTCompressedStreamTools.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */