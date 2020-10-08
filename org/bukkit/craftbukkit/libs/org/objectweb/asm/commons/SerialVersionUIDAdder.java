/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutput;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.FieldVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerialVersionUIDAdder
/*     */   extends ClassVisitor
/*     */ {
/*     */   private static final String CLINIT = "<clinit>";
/*     */   private boolean computeSvuid;
/*     */   private boolean hasSvuid;
/*     */   private int access;
/*     */   private String name;
/*     */   private String[] interfaces;
/*     */   private Collection<Item> svuidFields;
/*     */   private boolean hasStaticInitializer;
/*     */   private Collection<Item> svuidConstructors;
/*     */   private Collection<Item> svuidMethods;
/*     */   
/*     */   public SerialVersionUIDAdder(ClassVisitor classVisitor) {
/* 153 */     this(524288, classVisitor);
/* 154 */     if (getClass() != SerialVersionUIDAdder.class) {
/* 155 */       throw new IllegalStateException();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SerialVersionUIDAdder(int api, ClassVisitor classVisitor) {
/* 168 */     super(api, classVisitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/* 185 */     this.computeSvuid = ((access & 0x4000) == 0);
/*     */     
/* 187 */     if (this.computeSvuid) {
/* 188 */       this.name = name;
/* 189 */       this.access = access;
/* 190 */       this.interfaces = (String[])interfaces.clone();
/* 191 */       this.svuidFields = new ArrayList<Item>();
/* 192 */       this.svuidConstructors = new ArrayList<Item>();
/* 193 */       this.svuidMethods = new ArrayList<Item>();
/*     */     } 
/*     */     
/* 196 */     super.visit(version, access, name, signature, superName, interfaces);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 208 */     if (this.computeSvuid) {
/* 209 */       if ("<clinit>".equals(name)) {
/* 210 */         this.hasStaticInitializer = true;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 215 */       int mods = access & 0xD3F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 227 */       if ((access & 0x2) == 0) {
/* 228 */         if ("<init>".equals(name)) {
/* 229 */           this.svuidConstructors.add(new Item(name, mods, descriptor));
/* 230 */         } else if (!"<clinit>".equals(name)) {
/* 231 */           this.svuidMethods.add(new Item(name, mods, descriptor));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 236 */     return super.visitMethod(access, name, descriptor, signature, exceptions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
/* 248 */     if (this.computeSvuid) {
/* 249 */       if ("serialVersionUID".equals(name)) {
/*     */         
/* 251 */         this.computeSvuid = false;
/* 252 */         this.hasSvuid = true;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 257 */       if ((access & 0x2) == 0 || (access & 0x88) == 0) {
/*     */         
/* 259 */         int mods = access & 0xDF;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 268 */         this.svuidFields.add(new Item(name, mods, desc));
/*     */       } 
/*     */     } 
/*     */     
/* 272 */     return super.visitField(access, name, desc, signature, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInnerClass(String innerClassName, String outerName, String innerName, int innerClassAccess) {
/* 285 */     if (this.name != null && this.name.equals(innerClassName)) {
/* 286 */       this.access = innerClassAccess;
/*     */     }
/* 288 */     super.visitInnerClass(innerClassName, outerName, innerName, innerClassAccess);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 294 */     if (this.computeSvuid && !this.hasSvuid) {
/*     */       try {
/* 296 */         addSVUID(computeSVUID());
/* 297 */       } catch (IOException e) {
/* 298 */         throw new IllegalStateException("Error while computing SVUID for " + this.name, e);
/*     */       } 
/*     */     }
/*     */     
/* 302 */     super.visitEnd();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSVUID() {
/* 317 */     return this.hasSvuid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addSVUID(long svuid) {
/* 328 */     FieldVisitor fieldVisitor = super.visitField(24, "serialVersionUID", "J", null, 
/* 329 */         Long.valueOf(svuid));
/* 330 */     if (fieldVisitor != null) {
/* 331 */       fieldVisitor.visitEnd();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected long computeSVUID() throws IOException {
/* 343 */     long svuid = 0L;
/*     */     
/* 345 */     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); Throwable throwable = null; 
/* 346 */     try { DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
/*     */       Throwable throwable1 = null;
/*     */       
/* 349 */       try { dataOutputStream.writeUTF(this.name.replace('/', '.'));
/*     */ 
/*     */         
/* 352 */         int mods = this.access;
/* 353 */         if ((mods & 0x200) != 0)
/*     */         {
/* 355 */           mods = this.svuidMethods.isEmpty() ? (mods & 0xFFFFFBFF) : (mods | 0x400);
/*     */         }
/* 357 */         dataOutputStream.writeInt(mods & 0x611);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 365 */         Arrays.sort((Object[])this.interfaces);
/* 366 */         for (String interfaceName : this.interfaces) {
/* 367 */           dataOutputStream.writeUTF(interfaceName.replace('/', '.'));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 377 */         writeItems(this.svuidFields, dataOutputStream, false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 383 */         if (this.hasStaticInitializer) {
/* 384 */           dataOutputStream.writeUTF("<clinit>");
/* 385 */           dataOutputStream.writeInt(8);
/* 386 */           dataOutputStream.writeUTF("()V");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 393 */         writeItems(this.svuidConstructors, dataOutputStream, true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 399 */         writeItems(this.svuidMethods, dataOutputStream, true);
/*     */         
/* 401 */         dataOutputStream.flush();
/*     */ 
/*     */ 
/*     */         
/* 405 */         byte[] hashBytes = computeSHAdigest(byteArrayOutputStream.toByteArray());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 410 */         for (int i = Math.min(hashBytes.length, 8) - 1; i >= 0; i--)
/* 411 */           svuid = svuid << 8L | (hashBytes[i] & 0xFF);  }
/*     */       catch (Throwable throwable2) { throwable1 = throwable2 = null; throw throwable2; }
/* 413 */       finally { if (dataOutputStream != null) if (throwable1 != null) { try { dataOutputStream.close(); } catch (Throwable throwable2) {} } else { dataOutputStream.close(); }   }  } catch (Throwable throwable1) { throwable = throwable1 = null; throw throwable1; } finally { if (byteArrayOutputStream != null) if (throwable != null) { try { byteArrayOutputStream.close(); } catch (Throwable throwable1) {} } else { byteArrayOutputStream.close(); }
/*     */           }
/* 415 */      return svuid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] computeSHAdigest(byte[] value) {
/*     */     try {
/* 427 */       return MessageDigest.getInstance("SHA").digest(value);
/* 428 */     } catch (NoSuchAlgorithmException e) {
/* 429 */       throw new UnsupportedOperationException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeItems(Collection<Item> itemCollection, DataOutput dataOutputStream, boolean dotted) throws IOException {
/* 446 */     Item[] items = itemCollection.<Item>toArray(new Item[0]);
/* 447 */     Arrays.sort((Object[])items);
/* 448 */     for (Item item : items) {
/* 449 */       dataOutputStream.writeUTF(item.name);
/* 450 */       dataOutputStream.writeInt(item.access);
/* 451 */       dataOutputStream.writeUTF(dotted ? item.descriptor.replace('/', '.') : item.descriptor);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class Item
/*     */     implements Comparable<Item>
/*     */   {
/*     */     final String name;
/*     */     
/*     */     final int access;
/*     */     
/*     */     final String descriptor;
/*     */     
/*     */     Item(String name, int access, String descriptor) {
/* 466 */       this.name = name;
/* 467 */       this.access = access;
/* 468 */       this.descriptor = descriptor;
/*     */     }
/*     */ 
/*     */     
/*     */     public int compareTo(Item item) {
/* 473 */       int result = this.name.compareTo(item.name);
/* 474 */       if (result == 0) {
/* 475 */         result = this.descriptor.compareTo(item.descriptor);
/*     */       }
/* 477 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 482 */       if (other instanceof Item) {
/* 483 */         return (compareTo((Item)other) == 0);
/*     */       }
/* 485 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 490 */       return this.name.hashCode() ^ this.descriptor.hashCode();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\SerialVersionUIDAdder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */