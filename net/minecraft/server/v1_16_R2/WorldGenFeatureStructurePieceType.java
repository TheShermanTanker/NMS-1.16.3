/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Locale;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface WorldGenFeatureStructurePieceType
/*    */ {
/* 27 */   public static final WorldGenFeatureStructurePieceType a = a(WorldGenMineshaftCorridor::new, "MSCorridor");
/* 28 */   public static final WorldGenFeatureStructurePieceType b = a(WorldGenMineshaftCross::new, "MSCrossing");
/* 29 */   public static final WorldGenFeatureStructurePieceType c = a(WorldGenMineshaftRoom::new, "MSRoom");
/* 30 */   public static final WorldGenFeatureStructurePieceType d = a(WorldGenMineshaftStairs::new, "MSStairs");
/* 31 */   public static final WorldGenFeatureStructurePieceType e = a(WorldGenNetherPiece1::new, "NeBCr");
/* 32 */   public static final WorldGenFeatureStructurePieceType f = a(WorldGenNetherPiece2::new, "NeBEF");
/* 33 */   public static final WorldGenFeatureStructurePieceType g = a(WorldGenNetherPiece3::new, "NeBS");
/* 34 */   public static final WorldGenFeatureStructurePieceType h = a(WorldGenNetherPiece4::new, "NeCCS");
/* 35 */   public static final WorldGenFeatureStructurePieceType i = a(WorldGenNetherPiece5::new, "NeCTB");
/* 36 */   public static final WorldGenFeatureStructurePieceType j = a(WorldGenNetherPiece6::new, "NeCE");
/* 37 */   public static final WorldGenFeatureStructurePieceType k = a(WorldGenNetherPiece7::new, "NeSCSC");
/* 38 */   public static final WorldGenFeatureStructurePieceType l = a(WorldGenNetherPiece8::new, "NeSCLT");
/* 39 */   public static final WorldGenFeatureStructurePieceType m = a(WorldGenNetherPiece9::new, "NeSC");
/* 40 */   public static final WorldGenFeatureStructurePieceType n = a(WorldGenNetherPiece10::new, "NeSCRT");
/* 41 */   public static final WorldGenFeatureStructurePieceType o = a(WorldGenNetherPiece11::new, "NeCSR");
/* 42 */   public static final WorldGenFeatureStructurePieceType p = a(WorldGenNetherPiece12::new, "NeMT");
/* 43 */   public static final WorldGenFeatureStructurePieceType q = a(WorldGenNetherPiece13::new, "NeRC");
/* 44 */   public static final WorldGenFeatureStructurePieceType r = a(WorldGenNetherPiece14::new, "NeSR");
/* 45 */   public static final WorldGenFeatureStructurePieceType s = a(WorldGenNetherPiece15::new, "NeStart");
/* 46 */   public static final WorldGenFeatureStructurePieceType t = a(WorldGenStrongholdChestCorridor::new, "SHCC");
/* 47 */   public static final WorldGenFeatureStructurePieceType u = a(WorldGenStrongholdCorridor::new, "SHFC");
/* 48 */   public static final WorldGenFeatureStructurePieceType v = a(WorldGenStrongholdCrossing::new, "SH5C");
/* 49 */   public static final WorldGenFeatureStructurePieceType w = a(WorldGenStrongholdLeftTurn::new, "SHLT");
/* 50 */   public static final WorldGenFeatureStructurePieceType x = a(WorldGenStrongholdLibrary::new, "SHLi");
/* 51 */   public static final WorldGenFeatureStructurePieceType y = a(WorldGenStrongholdPortalRoom::new, "SHPR");
/* 52 */   public static final WorldGenFeatureStructurePieceType z = a(WorldGenStrongholdPrison::new, "SHPH");
/* 53 */   public static final WorldGenFeatureStructurePieceType A = a(WorldGenStrongholdRightTurn::new, "SHRT");
/* 54 */   public static final WorldGenFeatureStructurePieceType B = a(WorldGenStrongholdRoomCrossing::new, "SHRC");
/* 55 */   public static final WorldGenFeatureStructurePieceType C = a(WorldGenStrongholdStairs2::new, "SHSD");
/* 56 */   public static final WorldGenFeatureStructurePieceType D = a(WorldGenStrongholdStart::new, "SHStart");
/* 57 */   public static final WorldGenFeatureStructurePieceType E = a(WorldGenStrongholdStairs::new, "SHS");
/* 58 */   public static final WorldGenFeatureStructurePieceType F = a(WorldGenStrongholdStairsStraight::new, "SHSSD");
/* 59 */   public static final WorldGenFeatureStructurePieceType G = a(WorldGenJunglePyramidPiece::new, "TeJP");
/* 60 */   public static final WorldGenFeatureStructurePieceType H = a(a::new, "ORP");
/* 61 */   public static final WorldGenFeatureStructurePieceType I = a(a::new, "Iglu");
/* 62 */   public static final WorldGenFeatureStructurePieceType J = a(WorldGenFeatureRuinedPortalPieces::new, "RUPO");
/* 63 */   public static final WorldGenFeatureStructurePieceType K = a(WorldGenWitchHut::new, "TeSH");
/* 64 */   public static final WorldGenFeatureStructurePieceType L = a(WorldGenDesertPyramidPiece::new, "TeDP");
/* 65 */   public static final WorldGenFeatureStructurePieceType M = a(WorldGenMonumentPiece1::new, "OMB");
/* 66 */   public static final WorldGenFeatureStructurePieceType N = a(WorldGenMonumentPiece2::new, "OMCR");
/* 67 */   public static final WorldGenFeatureStructurePieceType O = a(WorldGenMonumentPiece3::new, "OMDXR");
/* 68 */   public static final WorldGenFeatureStructurePieceType P = a(WorldGenMonumentPiece4::new, "OMDXYR");
/* 69 */   public static final WorldGenFeatureStructurePieceType Q = a(WorldGenMonumentPiece5::new, "OMDYR");
/* 70 */   public static final WorldGenFeatureStructurePieceType R = a(WorldGenMonumentPiece6::new, "OMDYZR");
/* 71 */   public static final WorldGenFeatureStructurePieceType S = a(WorldGenMonumentPiece7::new, "OMDZR");
/* 72 */   public static final WorldGenFeatureStructurePieceType T = a(WorldGenMonumentPieceEntry::new, "OMEntry");
/* 73 */   public static final WorldGenFeatureStructurePieceType U = a(WorldGenMonumentPiecePenthouse::new, "OMPenthouse");
/* 74 */   public static final WorldGenFeatureStructurePieceType V = a(WorldGenMonumentPieceSimple::new, "OMSimple");
/* 75 */   public static final WorldGenFeatureStructurePieceType W = a(WorldGenMonumentPieceSimpleT::new, "OMSimpleT");
/* 76 */   public static final WorldGenFeatureStructurePieceType X = a(WorldGenMonumentPiece8::new, "OMWR");
/* 77 */   public static final WorldGenFeatureStructurePieceType Y = a(Piece::new, "ECP");
/* 78 */   public static final WorldGenFeatureStructurePieceType Z = a(i::new, "WMP");
/* 79 */   public static final WorldGenFeatureStructurePieceType aa = a(a::new, "BTP");
/* 80 */   public static final WorldGenFeatureStructurePieceType ab = a(a::new, "Shipwreck");
/* 81 */   public static final WorldGenFeatureStructurePieceType ac = a(a::new, "NeFos");
/* 82 */   public static final WorldGenFeatureStructurePieceType ad = a(WorldGenFeaturePillagerOutpostPoolPiece::new, "jigsaw");
/*    */ 
/*    */   
/*    */   StructurePiece load(DefinedStructureManager paramDefinedStructureManager, NBTTagCompound paramNBTTagCompound);
/*    */ 
/*    */   
/*    */   static WorldGenFeatureStructurePieceType a(WorldGenFeatureStructurePieceType var0, String var1) {
/* 89 */     return IRegistry.<WorldGenFeatureStructurePieceType>a(IRegistry.STRUCTURE_PIECE, var1.toLowerCase(Locale.ROOT), var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStructurePieceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */