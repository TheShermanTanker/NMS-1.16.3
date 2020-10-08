/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import io.netty.channel.ChannelFuture;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ 
/*     */ 
/*     */ public class PacketPlayOutLightUpdate
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   private List<byte[]> g;
/*     */   private List<byte[]> h;
/*     */   private boolean i;
/*     */   Runnable cleaner1;
/*     */   Runnable cleaner2;
/*  25 */   AtomicInteger remainingSends = new AtomicInteger(0);
/*     */ 
/*     */   
/*     */   public void onPacketDispatch(EntityPlayer player) {
/*  29 */     this.remainingSends.incrementAndGet();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPacketDispatchFinish(EntityPlayer player, ChannelFuture future) {
/*  34 */     if (this.remainingSends.decrementAndGet() <= 0)
/*     */     {
/*  36 */       MCUtil.scheduleTask(5, () -> { if (this.remainingSends.get() == 0) { this.cleaner1.run(); this.cleaner2.run(); }  }"Light Packet Release");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFinishListener() {
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketPlayOutLightUpdate(ChunkCoordIntPair chunkcoordintpair, LightEngine lightengine, boolean flag) {
/*  54 */     this.a = chunkcoordintpair.x;
/*  55 */     this.b = chunkcoordintpair.z;
/*  56 */     this.i = flag;
/*  57 */     this.g = Lists.newArrayList(); this.cleaner1 = MCUtil.registerListCleaner(this, (List)this.g, NibbleArray::releaseBytes);
/*  58 */     this.h = Lists.newArrayList(); this.cleaner2 = MCUtil.registerListCleaner(this, (List)this.h, NibbleArray::releaseBytes);
/*     */     
/*  60 */     for (int i = 0; i < 18; i++) {
/*  61 */       NibbleArray nibblearray = lightengine.a(EnumSkyBlock.SKY).a(SectionPosition.a(chunkcoordintpair, -1 + i));
/*  62 */       NibbleArray nibblearray1 = lightengine.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(chunkcoordintpair, -1 + i));
/*     */       
/*  64 */       if (nibblearray != null) {
/*  65 */         if (nibblearray.c()) {
/*  66 */           this.e |= 1 << i;
/*     */         } else {
/*  68 */           this.c |= 1 << i;
/*  69 */           this.g.add(nibblearray.getCloneIfSet());
/*     */         } 
/*     */       }
/*     */       
/*  73 */       if (nibblearray1 != null) {
/*  74 */         if (nibblearray1.c()) {
/*  75 */           this.f |= 1 << i;
/*     */         } else {
/*  77 */           this.d |= 1 << i;
/*  78 */           this.h.add(nibblearray1.getCloneIfSet());
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PacketPlayOutLightUpdate(ChunkCoordIntPair chunkcoordintpair, LightEngine lightengine, int i, int j, boolean flag) {
/*  86 */     this.a = chunkcoordintpair.x;
/*  87 */     this.b = chunkcoordintpair.z;
/*  88 */     this.i = flag;
/*  89 */     this.c = i;
/*  90 */     this.d = j;
/*  91 */     this.g = Lists.newArrayList(); this.cleaner1 = MCUtil.registerListCleaner(this, (List)this.g, NibbleArray::releaseBytes);
/*  92 */     this.h = Lists.newArrayList(); this.cleaner2 = MCUtil.registerListCleaner(this, (List)this.h, NibbleArray::releaseBytes);
/*     */     
/*  94 */     for (int k = 0; k < 18; k++) {
/*     */ 
/*     */       
/*  97 */       if ((this.c & 1 << k) != 0) {
/*  98 */         NibbleArray nibblearray = lightengine.a(EnumSkyBlock.SKY).a(SectionPosition.a(chunkcoordintpair, -1 + k));
/*  99 */         if (nibblearray != null && !nibblearray.c()) {
/* 100 */           this.g.add(nibblearray.getCloneIfSet());
/*     */         } else {
/* 102 */           this.c &= 1 << k ^ 0xFFFFFFFF;
/* 103 */           if (nibblearray != null) {
/* 104 */             this.e |= 1 << k;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 109 */       if ((this.d & 1 << k) != 0) {
/* 110 */         NibbleArray nibblearray = lightengine.a(EnumSkyBlock.BLOCK).a(SectionPosition.a(chunkcoordintpair, -1 + k));
/* 111 */         if (nibblearray != null && !nibblearray.c()) {
/* 112 */           this.h.add(nibblearray.getCloneIfSet());
/*     */         } else {
/* 114 */           this.d &= 1 << k ^ 0xFFFFFFFF;
/* 115 */           if (nibblearray != null) {
/* 116 */             this.f |= 1 << k;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 126 */     this.a = packetdataserializer.i();
/* 127 */     this.b = packetdataserializer.i();
/* 128 */     this.i = packetdataserializer.readBoolean();
/* 129 */     this.c = packetdataserializer.i();
/* 130 */     this.d = packetdataserializer.i();
/* 131 */     this.e = packetdataserializer.i();
/* 132 */     this.f = packetdataserializer.i();
/* 133 */     this.g = Lists.newArrayList();
/*     */     
/*     */     int i;
/*     */     
/* 137 */     for (i = 0; i < 18; i++) {
/* 138 */       if ((this.c & 1 << i) != 0) {
/* 139 */         this.g.add(packetdataserializer.b(2048));
/*     */       }
/*     */     } 
/*     */     
/* 143 */     this.h = Lists.newArrayList();
/*     */     
/* 145 */     for (i = 0; i < 18; i++) {
/* 146 */       if ((this.d & 1 << i) != 0) {
/* 147 */         this.h.add(packetdataserializer.b(2048));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 155 */     packetdataserializer.d(this.a);
/* 156 */     packetdataserializer.d(this.b);
/* 157 */     packetdataserializer.writeBoolean(this.i);
/* 158 */     packetdataserializer.d(this.c);
/* 159 */     packetdataserializer.d(this.d);
/* 160 */     packetdataserializer.d(this.e);
/* 161 */     packetdataserializer.d(this.f);
/* 162 */     Iterator<byte> iterator = this.g.iterator();
/*     */ 
/*     */ 
/*     */     
/* 166 */     while (iterator.hasNext()) {
/* 167 */       byte[] abyte = (byte[])iterator.next();
/* 168 */       packetdataserializer.a(abyte);
/*     */     } 
/*     */     
/* 171 */     iterator = this.h.iterator();
/*     */     
/* 173 */     while (iterator.hasNext()) {
/* 174 */       byte[] abyte = (byte[])iterator.next();
/* 175 */       packetdataserializer.a(abyte);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 181 */     packetlistenerplayout.a(this);
/*     */   }
/*     */   
/*     */   public PacketPlayOutLightUpdate() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutLightUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */