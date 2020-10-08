/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ 
/*    */ public interface DefinedStructureStructureProcessorType<P extends DefinedStructureProcessor>
/*    */ {
/* 11 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorBlockIgnore> a = a("block_ignore", DefinedStructureProcessorBlockIgnore.a);
/* 12 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorRotation> b = a("block_rot", DefinedStructureProcessorRotation.a);
/* 13 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorGravity> c = a("gravity", DefinedStructureProcessorGravity.a);
/* 14 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorJigsawReplacement> d = a("jigsaw_replacement", DefinedStructureProcessorJigsawReplacement.a);
/* 15 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorRule> e = a("rule", DefinedStructureProcessorRule.a);
/* 16 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorNop> f = a("nop", DefinedStructureProcessorNop.a);
/* 17 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorBlockAge> g = a("block_age", DefinedStructureProcessorBlockAge.a);
/* 18 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorBlackstoneReplace> h = a("blackstone_replace", DefinedStructureProcessorBlackstoneReplace.a);
/* 19 */   public static final DefinedStructureStructureProcessorType<DefinedStructureProcessorLavaSubmergedBlock> i = a("lava_submerged_block", DefinedStructureProcessorLavaSubmergedBlock.a);
/*    */   
/* 21 */   public static final Codec<DefinedStructureProcessor> j = IRegistry.STRUCTURE_PROCESSOR.dispatch("processor_type", DefinedStructureProcessor::a, DefinedStructureStructureProcessorType::codec);
/*    */   
/* 23 */   public static final Codec<ProcessorList> k = j.listOf().xmap(ProcessorList::new, ProcessorList::a);
/*    */   
/*    */   public static final Codec<ProcessorList> l;
/*    */ 
/*    */   
/*    */   static {
/* 29 */     l = Codec.either(k.fieldOf("processors").codec(), k).xmap(var0 -> (ProcessorList)var0.map((), ()), Either::left);
/*    */   }
/*    */ 
/*    */   
/* 33 */   public static final Codec<Supplier<ProcessorList>> m = RegistryFileCodec.a(IRegistry.aw, l);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static <P extends DefinedStructureProcessor> DefinedStructureStructureProcessorType<P> a(String var0, Codec<P> var1) {
/* 39 */     return (DefinedStructureStructureProcessorType<P>)IRegistry.<DefinedStructureStructureProcessorType<?>>a(IRegistry.STRUCTURE_PROCESSOR, var0, () -> var0);
/*    */   }
/*    */   
/*    */   Codec<P> codec();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DefinedStructureStructureProcessorType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */