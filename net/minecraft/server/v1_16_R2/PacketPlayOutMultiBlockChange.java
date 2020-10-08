/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.shorts.ShortIterator;
/*    */ import it.unimi.dsi.fastutil.shorts.ShortSet;
/*    */ import java.io.IOException;
/*    */ import java.util.function.BiConsumer;
/*    */ 
/*    */ public class PacketPlayOutMultiBlockChange
/*    */   implements Packet<PacketListenerPlayOut> {
/*    */   private SectionPosition a;
/*    */   private short[] b;
/*    */   private IBlockData[] c;
/*    */   private boolean d;
/*    */   
/*    */   public PacketPlayOutMultiBlockChange() {}
/*    */   
/*    */   public PacketPlayOutMultiBlockChange(SectionPosition sectionposition, ShortSet shortset, ChunkSection chunksection, boolean flag) {
/* 18 */     this.a = sectionposition;
/* 19 */     this.d = flag;
/* 20 */     a(shortset.size());
/* 21 */     int i = 0;
/*    */     
/* 23 */     for (ShortIterator shortiterator = shortset.iterator(); shortiterator.hasNext(); i++) {
/* 24 */       short short0 = shortiterator.next().shortValue();
/*    */       
/* 26 */       this.b[i] = short0;
/* 27 */       this.c[i] = (chunksection != null) ? chunksection.getType(SectionPosition.a(short0), SectionPosition.b(short0), SectionPosition.c(short0)) : Blocks.AIR.getBlockData();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void a(int i) {
/* 33 */     this.b = new short[i];
/* 34 */     this.c = new IBlockData[i];
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 39 */     this.a = SectionPosition.a(packetdataserializer.readLong());
/* 40 */     this.d = packetdataserializer.readBoolean();
/* 41 */     int i = packetdataserializer.i();
/*    */     
/* 43 */     a(i);
/*    */     
/* 45 */     for (int j = 0; j < this.b.length; j++) {
/* 46 */       long k = packetdataserializer.j();
/*    */       
/* 48 */       this.b[j] = (short)(int)(k & 0xFFFL);
/* 49 */       this.c[j] = Block.REGISTRY_ID.fromId((int)(k >>> 12L));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 56 */     packetdataserializer.writeLong(this.a.s());
/* 57 */     packetdataserializer.writeBoolean(this.d);
/* 58 */     packetdataserializer.d(this.b.length);
/*    */     
/* 60 */     for (int i = 0; i < this.b.length; i++) {
/* 61 */       packetdataserializer.b((Block.getCombinedId(this.c[i]) << 12 | this.b[i]));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 67 */     packetlistenerplayout.a(this);
/*    */   }
/*    */   
/*    */   public void a(BiConsumer<BlockPosition, IBlockData> biconsumer) {
/* 71 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*    */     
/* 73 */     for (int i = 0; i < this.b.length; i++) {
/* 74 */       short short0 = this.b[i];
/*    */       
/* 76 */       blockposition_mutableblockposition.d(this.a.d(short0), this.a.e(short0), this.a.f(short0));
/* 77 */       biconsumer.accept(blockposition_mutableblockposition, this.c[i]);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutMultiBlockChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */