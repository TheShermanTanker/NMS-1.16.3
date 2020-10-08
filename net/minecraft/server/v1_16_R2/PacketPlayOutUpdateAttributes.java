/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutUpdateAttributes
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private int a;
/*  19 */   private final List<AttributeSnapshot> b = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketPlayOutUpdateAttributes(int var0, Collection<AttributeModifiable> var1) {
/*  25 */     this.a = var0;
/*     */     
/*  27 */     for (AttributeModifiable var3 : var1) {
/*  28 */       this.b.add(new AttributeSnapshot(this, var3.getAttribute(), var3.getBaseValue(), var3.getModifiers()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  34 */     this.a = var0.i();
/*     */     
/*  36 */     int var1 = var0.readInt();
/*  37 */     for (int var2 = 0; var2 < var1; var2++) {
/*  38 */       MinecraftKey var3 = var0.p();
/*  39 */       AttributeBase var4 = IRegistry.ATTRIBUTE.get(var3);
/*  40 */       double var5 = var0.readDouble();
/*  41 */       List<AttributeModifier> var7 = Lists.newArrayList();
/*  42 */       int var8 = var0.i();
/*     */       
/*  44 */       for (int var9 = 0; var9 < var8; var9++) {
/*  45 */         UUID var10 = var0.k();
/*  46 */         var7.add(new AttributeModifier(var10, "Unknown synced attribute modifier", var0.readDouble(), AttributeModifier.Operation.a(var0.readByte())));
/*     */       } 
/*     */       
/*  49 */       this.b.add(new AttributeSnapshot(this, var4, var5, var7));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  55 */     var0.d(this.a);
/*  56 */     var0.writeInt(this.b.size());
/*     */     
/*  58 */     for (AttributeSnapshot var2 : this.b) {
/*  59 */       var0.a(IRegistry.ATTRIBUTE.getKey(var2.a()));
/*  60 */       var0.writeDouble(var2.b());
/*  61 */       var0.d(var2.c().size());
/*     */       
/*  63 */       for (AttributeModifier var4 : var2.c()) {
/*  64 */         var0.a(var4.getUniqueId());
/*  65 */         var0.writeDouble(var4.getAmount());
/*  66 */         var0.writeByte(var4.getOperation().a());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/*  73 */     var0.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketPlayOutUpdateAttributes() {}
/*     */ 
/*     */   
/*     */   public class AttributeSnapshot
/*     */   {
/*     */     private final AttributeBase b;
/*     */     
/*     */     private final double c;
/*     */     
/*     */     private final Collection<AttributeModifier> d;
/*     */ 
/*     */     
/*     */     public AttributeSnapshot(PacketPlayOutUpdateAttributes var0, AttributeBase var1, double var2, Collection<AttributeModifier> var4) {
/*  90 */       this.b = var1;
/*  91 */       this.c = var2;
/*  92 */       this.d = var4;
/*     */     }
/*     */     
/*     */     public AttributeBase a() {
/*  96 */       return this.b;
/*     */     }
/*     */     
/*     */     public double b() {
/* 100 */       return this.c;
/*     */     }
/*     */     
/*     */     public Collection<AttributeModifier> c() {
/* 104 */       return this.d;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutUpdateAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */