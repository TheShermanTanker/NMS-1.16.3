/*      */ package org.bukkit.craftbukkit.libs.org.objectweb.asm;
/*      */ 
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ClassReader
/*      */ {
/*      */   public static final int SKIP_CODE = 1;
/*      */   public static final int SKIP_DEBUG = 2;
/*      */   public static final int SKIP_FRAMES = 4;
/*      */   public static final int EXPAND_FRAMES = 8;
/*      */   static final int EXPAND_ASM_INSNS = 256;
/*      */   private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
/*      */   @Deprecated
/*      */   public final byte[] b;
/*      */   final byte[] classFileBuffer;
/*      */   private final int[] cpInfoOffsets;
/*      */   private final String[] constantUtf8Values;
/*      */   private final ConstantDynamic[] constantDynamicValues;
/*      */   private final int[] bootstrapMethodOffsets;
/*      */   private final int maxStringLength;
/*      */   public final int header;
/*      */   
/*      */   public ClassReader(byte[] classFile) {
/*  163 */     this(classFile, 0, classFile.length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassReader(byte[] classFileBuffer, int classFileOffset, int classFileLength) {
/*  177 */     this(classFileBuffer, classFileOffset, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ClassReader(byte[] classFileBuffer, int classFileOffset, boolean checkClassVersion) {
/*  190 */     this.classFileBuffer = classFileBuffer;
/*  191 */     this.b = classFileBuffer;
/*      */ 
/*      */     
/*  194 */     if (checkClassVersion && readShort(classFileOffset + 6) > 59) {
/*  195 */       throw new IllegalArgumentException("Unsupported class file major version " + 
/*  196 */           readShort(classFileOffset + 6));
/*      */     }
/*      */ 
/*      */     
/*  200 */     int constantPoolCount = readUnsignedShort(classFileOffset + 8);
/*  201 */     this.cpInfoOffsets = new int[constantPoolCount];
/*  202 */     this.constantUtf8Values = new String[constantPoolCount];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  207 */     int currentCpInfoIndex = 1;
/*  208 */     int currentCpInfoOffset = classFileOffset + 10;
/*  209 */     int currentMaxStringLength = 0;
/*  210 */     boolean hasBootstrapMethods = false;
/*  211 */     boolean hasConstantDynamic = false;
/*      */     
/*  213 */     while (currentCpInfoIndex < constantPoolCount) {
/*  214 */       int cpInfoSize; this.cpInfoOffsets[currentCpInfoIndex++] = currentCpInfoOffset + 1;
/*      */       
/*  216 */       switch (classFileBuffer[currentCpInfoOffset]) {
/*      */         case 3:
/*      */         case 4:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*  223 */           cpInfoSize = 5;
/*      */           break;
/*      */         case 17:
/*  226 */           cpInfoSize = 5;
/*  227 */           hasBootstrapMethods = true;
/*  228 */           hasConstantDynamic = true;
/*      */           break;
/*      */         case 18:
/*  231 */           cpInfoSize = 5;
/*  232 */           hasBootstrapMethods = true;
/*      */           break;
/*      */         case 5:
/*      */         case 6:
/*  236 */           cpInfoSize = 9;
/*  237 */           currentCpInfoIndex++;
/*      */           break;
/*      */         case 1:
/*  240 */           cpInfoSize = 3 + readUnsignedShort(currentCpInfoOffset + 1);
/*  241 */           if (cpInfoSize > currentMaxStringLength)
/*      */           {
/*      */ 
/*      */             
/*  245 */             currentMaxStringLength = cpInfoSize;
/*      */           }
/*      */           break;
/*      */         case 15:
/*  249 */           cpInfoSize = 4;
/*      */           break;
/*      */         case 7:
/*      */         case 8:
/*      */         case 16:
/*      */         case 19:
/*      */         case 20:
/*  256 */           cpInfoSize = 3;
/*      */           break;
/*      */         default:
/*  259 */           throw new IllegalArgumentException();
/*      */       } 
/*  261 */       currentCpInfoOffset += cpInfoSize;
/*      */     } 
/*  263 */     this.maxStringLength = currentMaxStringLength;
/*      */     
/*  265 */     this.header = currentCpInfoOffset;
/*      */ 
/*      */     
/*  268 */     this.constantDynamicValues = hasConstantDynamic ? new ConstantDynamic[constantPoolCount] : null;
/*      */ 
/*      */     
/*  271 */     this
/*  272 */       .bootstrapMethodOffsets = hasBootstrapMethods ? readBootstrapMethodsAttribute(currentMaxStringLength) : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassReader(InputStream inputStream) throws IOException {
/*  284 */     this(readStream(inputStream, false));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassReader(String className) throws IOException {
/*  295 */     this(
/*  296 */         readStream(
/*  297 */           ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class"), true));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] readStream(InputStream inputStream, boolean close) throws IOException {
/*  310 */     if (inputStream == null)
/*  311 */       throw new IOException("Class not found"); 
/*      */     try {
/*  313 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); Throwable throwable = null;
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     finally {
/*      */ 
/*      */ 
/*      */       
/*  322 */       if (close) {
/*  323 */         inputStream.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAccess() {
/*  340 */     return readUnsignedShort(this.header);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClassName() {
/*  351 */     return readClass(this.header + 2, new char[this.maxStringLength]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSuperName() {
/*  363 */     return readClass(this.header + 4, new char[this.maxStringLength]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getInterfaces() {
/*  375 */     int currentOffset = this.header + 6;
/*  376 */     int interfacesCount = readUnsignedShort(currentOffset);
/*  377 */     String[] interfaces = new String[interfacesCount];
/*  378 */     if (interfacesCount > 0) {
/*  379 */       char[] charBuffer = new char[this.maxStringLength];
/*  380 */       for (int i = 0; i < interfacesCount; i++) {
/*  381 */         currentOffset += 2;
/*  382 */         interfaces[i] = readClass(currentOffset, charBuffer);
/*      */       } 
/*      */     } 
/*  385 */     return interfaces;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void accept(ClassVisitor classVisitor, int parsingOptions) {
/*  401 */     accept(classVisitor, new Attribute[0], parsingOptions);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void accept(ClassVisitor classVisitor, Attribute[] attributePrototypes, int parsingOptions) {
/*  422 */     Context context = new Context();
/*  423 */     context.attributePrototypes = attributePrototypes;
/*  424 */     context.parsingOptions = parsingOptions;
/*  425 */     context.charBuffer = new char[this.maxStringLength];
/*      */ 
/*      */     
/*  428 */     char[] charBuffer = context.charBuffer;
/*  429 */     int currentOffset = this.header;
/*  430 */     int accessFlags = readUnsignedShort(currentOffset);
/*  431 */     String thisClass = readClass(currentOffset + 2, charBuffer);
/*  432 */     String superClass = readClass(currentOffset + 4, charBuffer);
/*  433 */     String[] interfaces = new String[readUnsignedShort(currentOffset + 6)];
/*  434 */     currentOffset += 8;
/*  435 */     for (int i = 0; i < interfaces.length; i++) {
/*  436 */       interfaces[i] = readClass(currentOffset, charBuffer);
/*  437 */       currentOffset += 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  443 */     int innerClassesOffset = 0;
/*      */     
/*  445 */     int enclosingMethodOffset = 0;
/*      */     
/*  447 */     String signature = null;
/*      */     
/*  449 */     String sourceFile = null;
/*      */     
/*  451 */     String sourceDebugExtension = null;
/*      */     
/*  453 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/*  455 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/*  457 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/*  459 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */     
/*  461 */     int moduleOffset = 0;
/*      */     
/*  463 */     int modulePackagesOffset = 0;
/*      */     
/*  465 */     String moduleMainClass = null;
/*      */     
/*  467 */     String nestHostClass = null;
/*      */     
/*  469 */     int nestMembersOffset = 0;
/*      */     
/*  471 */     int permittedSubtypesOffset = 0;
/*      */     
/*  473 */     int recordOffset = 0;
/*      */ 
/*      */     
/*  476 */     Attribute attributes = null;
/*      */     
/*  478 */     int currentAttributeOffset = getFirstAttributeOffset();
/*  479 */     for (int j = readUnsignedShort(currentAttributeOffset - 2); j > 0; j--) {
/*      */       
/*  481 */       String attributeName = readUTF8(currentAttributeOffset, charBuffer);
/*  482 */       int attributeLength = readInt(currentAttributeOffset + 2);
/*  483 */       currentAttributeOffset += 6;
/*      */ 
/*      */       
/*  486 */       if ("SourceFile".equals(attributeName)) {
/*  487 */         sourceFile = readUTF8(currentAttributeOffset, charBuffer);
/*  488 */       } else if ("InnerClasses".equals(attributeName)) {
/*  489 */         innerClassesOffset = currentAttributeOffset;
/*  490 */       } else if ("EnclosingMethod".equals(attributeName)) {
/*  491 */         enclosingMethodOffset = currentAttributeOffset;
/*  492 */       } else if ("NestHost".equals(attributeName)) {
/*  493 */         nestHostClass = readClass(currentAttributeOffset, charBuffer);
/*  494 */       } else if ("NestMembers".equals(attributeName)) {
/*  495 */         nestMembersOffset = currentAttributeOffset;
/*  496 */       } else if ("PermittedSubtypes".equals(attributeName)) {
/*  497 */         permittedSubtypesOffset = currentAttributeOffset;
/*  498 */       } else if ("Signature".equals(attributeName)) {
/*  499 */         signature = readUTF8(currentAttributeOffset, charBuffer);
/*  500 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/*  501 */         runtimeVisibleAnnotationsOffset = currentAttributeOffset;
/*  502 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*  503 */         runtimeVisibleTypeAnnotationsOffset = currentAttributeOffset;
/*  504 */       } else if ("Deprecated".equals(attributeName)) {
/*  505 */         accessFlags |= 0x20000;
/*  506 */       } else if ("Synthetic".equals(attributeName)) {
/*  507 */         accessFlags |= 0x1000;
/*  508 */       } else if ("SourceDebugExtension".equals(attributeName)) {
/*      */         
/*  510 */         sourceDebugExtension = readUtf(currentAttributeOffset, attributeLength, new char[attributeLength]);
/*  511 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/*  512 */         runtimeInvisibleAnnotationsOffset = currentAttributeOffset;
/*  513 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*  514 */         runtimeInvisibleTypeAnnotationsOffset = currentAttributeOffset;
/*  515 */       } else if ("Record".equals(attributeName)) {
/*  516 */         recordOffset = currentAttributeOffset;
/*  517 */         accessFlags |= 0x10000;
/*  518 */       } else if ("Module".equals(attributeName)) {
/*  519 */         moduleOffset = currentAttributeOffset;
/*  520 */       } else if ("ModuleMainClass".equals(attributeName)) {
/*  521 */         moduleMainClass = readClass(currentAttributeOffset, charBuffer);
/*  522 */       } else if ("ModulePackages".equals(attributeName)) {
/*  523 */         modulePackagesOffset = currentAttributeOffset;
/*  524 */       } else if (!"BootstrapMethods".equals(attributeName)) {
/*      */ 
/*      */         
/*  527 */         Attribute attribute = readAttribute(attributePrototypes, attributeName, currentAttributeOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  535 */         attribute.nextAttribute = attributes;
/*  536 */         attributes = attribute;
/*      */       } 
/*  538 */       currentAttributeOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  543 */     classVisitor.visit(
/*  544 */         readInt(this.cpInfoOffsets[1] - 7), accessFlags, thisClass, signature, superClass, interfaces);
/*      */ 
/*      */     
/*  547 */     if ((parsingOptions & 0x2) == 0 && (sourceFile != null || sourceDebugExtension != null))
/*      */     {
/*  549 */       classVisitor.visitSource(sourceFile, sourceDebugExtension);
/*      */     }
/*      */ 
/*      */     
/*  553 */     if (moduleOffset != 0) {
/*  554 */       readModuleAttributes(classVisitor, context, moduleOffset, modulePackagesOffset, moduleMainClass);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  559 */     if (nestHostClass != null) {
/*  560 */       classVisitor.visitNestHost(nestHostClass);
/*      */     }
/*      */ 
/*      */     
/*  564 */     if (enclosingMethodOffset != 0) {
/*  565 */       String className = readClass(enclosingMethodOffset, charBuffer);
/*  566 */       int methodIndex = readUnsignedShort(enclosingMethodOffset + 2);
/*  567 */       String name = (methodIndex == 0) ? null : readUTF8(this.cpInfoOffsets[methodIndex], charBuffer);
/*  568 */       String type = (methodIndex == 0) ? null : readUTF8(this.cpInfoOffsets[methodIndex] + 2, charBuffer);
/*  569 */       classVisitor.visitOuterClass(className, name, type);
/*      */     } 
/*      */ 
/*      */     
/*  573 */     if (runtimeVisibleAnnotationsOffset != 0) {
/*  574 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/*  575 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/*  576 */       while (numAnnotations-- > 0) {
/*      */         
/*  578 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  579 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  582 */         currentAnnotationOffset = readElementValues(classVisitor
/*  583 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  591 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/*  592 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/*  593 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/*  594 */       while (numAnnotations-- > 0) {
/*      */         
/*  596 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  597 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  600 */         currentAnnotationOffset = readElementValues(classVisitor
/*  601 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  609 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/*  610 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/*  611 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/*  612 */       while (numAnnotations-- > 0) {
/*      */         
/*  614 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/*  616 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  617 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  620 */         currentAnnotationOffset = readElementValues(classVisitor
/*  621 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  633 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/*  634 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/*  635 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/*  636 */       while (numAnnotations-- > 0) {
/*      */         
/*  638 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/*  640 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  641 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  644 */         currentAnnotationOffset = readElementValues(classVisitor
/*  645 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  657 */     while (attributes != null) {
/*      */       
/*  659 */       Attribute nextAttribute = attributes.nextAttribute;
/*  660 */       attributes.nextAttribute = null;
/*  661 */       classVisitor.visitAttribute(attributes);
/*  662 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/*  666 */     if (nestMembersOffset != 0) {
/*  667 */       int numberOfNestMembers = readUnsignedShort(nestMembersOffset);
/*  668 */       int currentNestMemberOffset = nestMembersOffset + 2;
/*  669 */       while (numberOfNestMembers-- > 0) {
/*  670 */         classVisitor.visitNestMember(readClass(currentNestMemberOffset, charBuffer));
/*  671 */         currentNestMemberOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  676 */     if (permittedSubtypesOffset != 0) {
/*  677 */       int numberOfPermittedSubtypes = readUnsignedShort(permittedSubtypesOffset);
/*  678 */       int currentPermittedSubtypeOffset = permittedSubtypesOffset + 2;
/*  679 */       while (numberOfPermittedSubtypes-- > 0) {
/*  680 */         classVisitor.visitPermittedSubtypeExperimental(
/*  681 */             readClass(currentPermittedSubtypeOffset, charBuffer));
/*  682 */         currentPermittedSubtypeOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  687 */     if (innerClassesOffset != 0) {
/*  688 */       int numberOfClasses = readUnsignedShort(innerClassesOffset);
/*  689 */       int currentClassesOffset = innerClassesOffset + 2;
/*  690 */       while (numberOfClasses-- > 0) {
/*  691 */         classVisitor.visitInnerClass(
/*  692 */             readClass(currentClassesOffset, charBuffer), 
/*  693 */             readClass(currentClassesOffset + 2, charBuffer), 
/*  694 */             readUTF8(currentClassesOffset + 4, charBuffer), 
/*  695 */             readUnsignedShort(currentClassesOffset + 6));
/*  696 */         currentClassesOffset += 8;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  701 */     if (recordOffset != 0) {
/*  702 */       int recordComponentsCount = readUnsignedShort(recordOffset);
/*  703 */       recordOffset += 2;
/*  704 */       while (recordComponentsCount-- > 0) {
/*  705 */         recordOffset = readRecordComponent(classVisitor, context, recordOffset);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  710 */     int fieldsCount = readUnsignedShort(currentOffset);
/*  711 */     currentOffset += 2;
/*  712 */     while (fieldsCount-- > 0) {
/*  713 */       currentOffset = readField(classVisitor, context, currentOffset);
/*      */     }
/*  715 */     int methodsCount = readUnsignedShort(currentOffset);
/*  716 */     currentOffset += 2;
/*  717 */     while (methodsCount-- > 0) {
/*  718 */       currentOffset = readMethod(classVisitor, context, currentOffset);
/*      */     }
/*      */ 
/*      */     
/*  722 */     classVisitor.visitEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readModuleAttributes(ClassVisitor classVisitor, Context context, int moduleOffset, int modulePackagesOffset, String moduleMainClass) {
/*  747 */     char[] buffer = context.charBuffer;
/*      */ 
/*      */     
/*  750 */     int currentOffset = moduleOffset;
/*  751 */     String moduleName = readModule(currentOffset, buffer);
/*  752 */     int moduleFlags = readUnsignedShort(currentOffset + 2);
/*  753 */     String moduleVersion = readUTF8(currentOffset + 4, buffer);
/*  754 */     currentOffset += 6;
/*  755 */     ModuleVisitor moduleVisitor = classVisitor.visitModule(moduleName, moduleFlags, moduleVersion);
/*  756 */     if (moduleVisitor == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  761 */     if (moduleMainClass != null) {
/*  762 */       moduleVisitor.visitMainClass(moduleMainClass);
/*      */     }
/*      */ 
/*      */     
/*  766 */     if (modulePackagesOffset != 0) {
/*  767 */       int packageCount = readUnsignedShort(modulePackagesOffset);
/*  768 */       int currentPackageOffset = modulePackagesOffset + 2;
/*  769 */       while (packageCount-- > 0) {
/*  770 */         moduleVisitor.visitPackage(readPackage(currentPackageOffset, buffer));
/*  771 */         currentPackageOffset += 2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  776 */     int requiresCount = readUnsignedShort(currentOffset);
/*  777 */     currentOffset += 2;
/*  778 */     while (requiresCount-- > 0) {
/*      */       
/*  780 */       String requires = readModule(currentOffset, buffer);
/*  781 */       int requiresFlags = readUnsignedShort(currentOffset + 2);
/*  782 */       String requiresVersion = readUTF8(currentOffset + 4, buffer);
/*  783 */       currentOffset += 6;
/*  784 */       moduleVisitor.visitRequire(requires, requiresFlags, requiresVersion);
/*      */     } 
/*      */ 
/*      */     
/*  788 */     int exportsCount = readUnsignedShort(currentOffset);
/*  789 */     currentOffset += 2;
/*  790 */     while (exportsCount-- > 0) {
/*      */ 
/*      */       
/*  793 */       String exports = readPackage(currentOffset, buffer);
/*  794 */       int exportsFlags = readUnsignedShort(currentOffset + 2);
/*  795 */       int exportsToCount = readUnsignedShort(currentOffset + 4);
/*  796 */       currentOffset += 6;
/*  797 */       String[] exportsTo = null;
/*  798 */       if (exportsToCount != 0) {
/*  799 */         exportsTo = new String[exportsToCount];
/*  800 */         for (int i = 0; i < exportsToCount; i++) {
/*  801 */           exportsTo[i] = readModule(currentOffset, buffer);
/*  802 */           currentOffset += 2;
/*      */         } 
/*      */       } 
/*  805 */       moduleVisitor.visitExport(exports, exportsFlags, exportsTo);
/*      */     } 
/*      */ 
/*      */     
/*  809 */     int opensCount = readUnsignedShort(currentOffset);
/*  810 */     currentOffset += 2;
/*  811 */     while (opensCount-- > 0) {
/*      */       
/*  813 */       String opens = readPackage(currentOffset, buffer);
/*  814 */       int opensFlags = readUnsignedShort(currentOffset + 2);
/*  815 */       int opensToCount = readUnsignedShort(currentOffset + 4);
/*  816 */       currentOffset += 6;
/*  817 */       String[] opensTo = null;
/*  818 */       if (opensToCount != 0) {
/*  819 */         opensTo = new String[opensToCount];
/*  820 */         for (int i = 0; i < opensToCount; i++) {
/*  821 */           opensTo[i] = readModule(currentOffset, buffer);
/*  822 */           currentOffset += 2;
/*      */         } 
/*      */       } 
/*  825 */       moduleVisitor.visitOpen(opens, opensFlags, opensTo);
/*      */     } 
/*      */ 
/*      */     
/*  829 */     int usesCount = readUnsignedShort(currentOffset);
/*  830 */     currentOffset += 2;
/*  831 */     while (usesCount-- > 0) {
/*  832 */       moduleVisitor.visitUse(readClass(currentOffset, buffer));
/*  833 */       currentOffset += 2;
/*      */     } 
/*      */ 
/*      */     
/*  837 */     int providesCount = readUnsignedShort(currentOffset);
/*  838 */     currentOffset += 2;
/*  839 */     while (providesCount-- > 0) {
/*      */       
/*  841 */       String provides = readClass(currentOffset, buffer);
/*  842 */       int providesWithCount = readUnsignedShort(currentOffset + 2);
/*  843 */       currentOffset += 4;
/*  844 */       String[] providesWith = new String[providesWithCount];
/*  845 */       for (int i = 0; i < providesWithCount; i++) {
/*  846 */         providesWith[i] = readClass(currentOffset, buffer);
/*  847 */         currentOffset += 2;
/*      */       } 
/*  849 */       moduleVisitor.visitProvide(provides, providesWith);
/*      */     } 
/*      */ 
/*      */     
/*  853 */     moduleVisitor.visitEnd();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readRecordComponent(ClassVisitor classVisitor, Context context, int recordComponentOffset) {
/*  866 */     char[] charBuffer = context.charBuffer;
/*      */     
/*  868 */     int currentOffset = recordComponentOffset;
/*  869 */     String name = readUTF8(currentOffset, charBuffer);
/*  870 */     String descriptor = readUTF8(currentOffset + 2, charBuffer);
/*  871 */     currentOffset += 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  878 */     String signature = null;
/*      */     
/*  880 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/*  882 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/*  884 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/*  886 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */ 
/*      */     
/*  889 */     Attribute attributes = null;
/*      */     
/*  891 */     int attributesCount = readUnsignedShort(currentOffset);
/*  892 */     currentOffset += 2;
/*  893 */     while (attributesCount-- > 0) {
/*      */       
/*  895 */       String attributeName = readUTF8(currentOffset, charBuffer);
/*  896 */       int attributeLength = readInt(currentOffset + 2);
/*  897 */       currentOffset += 6;
/*      */ 
/*      */       
/*  900 */       if ("Signature".equals(attributeName)) {
/*  901 */         signature = readUTF8(currentOffset, charBuffer);
/*  902 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/*  903 */         runtimeVisibleAnnotationsOffset = currentOffset;
/*  904 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*  905 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/*  906 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/*  907 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/*  908 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*  909 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/*      */       } else {
/*      */         
/*  912 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  920 */         attribute.nextAttribute = attributes;
/*  921 */         attributes = attribute;
/*      */       } 
/*  923 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */     
/*  927 */     RecordComponentVisitor recordComponentVisitor = classVisitor.visitRecordComponent(name, descriptor, signature);
/*  928 */     if (recordComponentVisitor == null) {
/*  929 */       return currentOffset;
/*      */     }
/*      */ 
/*      */     
/*  933 */     if (runtimeVisibleAnnotationsOffset != 0) {
/*  934 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/*  935 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/*  936 */       while (numAnnotations-- > 0) {
/*      */         
/*  938 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  939 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  942 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/*  943 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  951 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/*  952 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/*  953 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/*  954 */       while (numAnnotations-- > 0) {
/*      */         
/*  956 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  957 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  960 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/*  961 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  969 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/*  970 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/*  971 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/*  972 */       while (numAnnotations-- > 0) {
/*      */         
/*  974 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/*  976 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/*  977 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/*  980 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/*  981 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  993 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/*  994 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/*  995 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/*  996 */       while (numAnnotations-- > 0) {
/*      */         
/*  998 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1000 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1001 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1004 */         currentAnnotationOffset = readElementValues(recordComponentVisitor
/* 1005 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1017 */     while (attributes != null) {
/*      */       
/* 1019 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1020 */       attributes.nextAttribute = null;
/* 1021 */       recordComponentVisitor.visitAttribute(attributes);
/* 1022 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1026 */     recordComponentVisitor.visitEnd();
/* 1027 */     return currentOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readField(ClassVisitor classVisitor, Context context, int fieldInfoOffset) {
/* 1040 */     char[] charBuffer = context.charBuffer;
/*      */ 
/*      */     
/* 1043 */     int currentOffset = fieldInfoOffset;
/* 1044 */     int accessFlags = readUnsignedShort(currentOffset);
/* 1045 */     String name = readUTF8(currentOffset + 2, charBuffer);
/* 1046 */     String descriptor = readUTF8(currentOffset + 4, charBuffer);
/* 1047 */     currentOffset += 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1052 */     Object constantValue = null;
/*      */     
/* 1054 */     String signature = null;
/*      */     
/* 1056 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/* 1058 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/* 1060 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1062 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */ 
/*      */     
/* 1065 */     Attribute attributes = null;
/*      */     
/* 1067 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1068 */     currentOffset += 2;
/* 1069 */     while (attributesCount-- > 0) {
/*      */       
/* 1071 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1072 */       int attributeLength = readInt(currentOffset + 2);
/* 1073 */       currentOffset += 6;
/*      */ 
/*      */       
/* 1076 */       if ("ConstantValue".equals(attributeName)) {
/* 1077 */         int constantvalueIndex = readUnsignedShort(currentOffset);
/* 1078 */         constantValue = (constantvalueIndex == 0) ? null : readConst(constantvalueIndex, charBuffer);
/* 1079 */       } else if ("Signature".equals(attributeName)) {
/* 1080 */         signature = readUTF8(currentOffset, charBuffer);
/* 1081 */       } else if ("Deprecated".equals(attributeName)) {
/* 1082 */         accessFlags |= 0x20000;
/* 1083 */       } else if ("Synthetic".equals(attributeName)) {
/* 1084 */         accessFlags |= 0x1000;
/* 1085 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/* 1086 */         runtimeVisibleAnnotationsOffset = currentOffset;
/* 1087 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/* 1088 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/* 1089 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/* 1090 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/* 1091 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/* 1092 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/*      */       } else {
/*      */         
/* 1095 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1103 */         attribute.nextAttribute = attributes;
/* 1104 */         attributes = attribute;
/*      */       } 
/* 1106 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1111 */     FieldVisitor fieldVisitor = classVisitor.visitField(accessFlags, name, descriptor, signature, constantValue);
/* 1112 */     if (fieldVisitor == null) {
/* 1113 */       return currentOffset;
/*      */     }
/*      */ 
/*      */     
/* 1117 */     if (runtimeVisibleAnnotationsOffset != 0) {
/* 1118 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/* 1119 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/* 1120 */       while (numAnnotations-- > 0) {
/*      */         
/* 1122 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1123 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1126 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1127 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1135 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/* 1136 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/* 1137 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/* 1138 */       while (numAnnotations-- > 0) {
/*      */         
/* 1140 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1141 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1144 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1145 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1153 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/* 1154 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/* 1155 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/* 1156 */       while (numAnnotations-- > 0) {
/*      */         
/* 1158 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1160 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1161 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1164 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1165 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1177 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/* 1178 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/* 1179 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/* 1180 */       while (numAnnotations-- > 0) {
/*      */         
/* 1182 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1184 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1185 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1188 */         currentAnnotationOffset = readElementValues(fieldVisitor
/* 1189 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1201 */     while (attributes != null) {
/*      */       
/* 1203 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1204 */       attributes.nextAttribute = null;
/* 1205 */       fieldVisitor.visitAttribute(attributes);
/* 1206 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1210 */     fieldVisitor.visitEnd();
/* 1211 */     return currentOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readMethod(ClassVisitor classVisitor, Context context, int methodInfoOffset) {
/* 1224 */     char[] charBuffer = context.charBuffer;
/*      */ 
/*      */     
/* 1227 */     int currentOffset = methodInfoOffset;
/* 1228 */     context.currentMethodAccessFlags = readUnsignedShort(currentOffset);
/* 1229 */     context.currentMethodName = readUTF8(currentOffset + 2, charBuffer);
/* 1230 */     context.currentMethodDescriptor = readUTF8(currentOffset + 4, charBuffer);
/* 1231 */     currentOffset += 6;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1236 */     int codeOffset = 0;
/*      */     
/* 1238 */     int exceptionsOffset = 0;
/*      */     
/* 1240 */     String[] exceptions = null;
/*      */     
/* 1242 */     boolean synthetic = false;
/*      */     
/* 1244 */     int signatureIndex = 0;
/*      */     
/* 1246 */     int runtimeVisibleAnnotationsOffset = 0;
/*      */     
/* 1248 */     int runtimeInvisibleAnnotationsOffset = 0;
/*      */     
/* 1250 */     int runtimeVisibleParameterAnnotationsOffset = 0;
/*      */     
/* 1252 */     int runtimeInvisibleParameterAnnotationsOffset = 0;
/*      */     
/* 1254 */     int runtimeVisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1256 */     int runtimeInvisibleTypeAnnotationsOffset = 0;
/*      */     
/* 1258 */     int annotationDefaultOffset = 0;
/*      */     
/* 1260 */     int methodParametersOffset = 0;
/*      */ 
/*      */     
/* 1263 */     Attribute attributes = null;
/*      */     
/* 1265 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1266 */     currentOffset += 2;
/* 1267 */     while (attributesCount-- > 0) {
/*      */       
/* 1269 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1270 */       int attributeLength = readInt(currentOffset + 2);
/* 1271 */       currentOffset += 6;
/*      */ 
/*      */       
/* 1274 */       if ("Code".equals(attributeName)) {
/* 1275 */         if ((context.parsingOptions & 0x1) == 0) {
/* 1276 */           codeOffset = currentOffset;
/*      */         }
/* 1278 */       } else if ("Exceptions".equals(attributeName)) {
/* 1279 */         exceptionsOffset = currentOffset;
/* 1280 */         exceptions = new String[readUnsignedShort(exceptionsOffset)];
/* 1281 */         int currentExceptionOffset = exceptionsOffset + 2;
/* 1282 */         for (int i = 0; i < exceptions.length; i++) {
/* 1283 */           exceptions[i] = readClass(currentExceptionOffset, charBuffer);
/* 1284 */           currentExceptionOffset += 2;
/*      */         } 
/* 1286 */       } else if ("Signature".equals(attributeName)) {
/* 1287 */         signatureIndex = readUnsignedShort(currentOffset);
/* 1288 */       } else if ("Deprecated".equals(attributeName)) {
/* 1289 */         context.currentMethodAccessFlags |= 0x20000;
/* 1290 */       } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
/* 1291 */         runtimeVisibleAnnotationsOffset = currentOffset;
/* 1292 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/* 1293 */         runtimeVisibleTypeAnnotationsOffset = currentOffset;
/* 1294 */       } else if ("AnnotationDefault".equals(attributeName)) {
/* 1295 */         annotationDefaultOffset = currentOffset;
/* 1296 */       } else if ("Synthetic".equals(attributeName)) {
/* 1297 */         synthetic = true;
/* 1298 */         context.currentMethodAccessFlags |= 0x1000;
/* 1299 */       } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
/* 1300 */         runtimeInvisibleAnnotationsOffset = currentOffset;
/* 1301 */       } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/* 1302 */         runtimeInvisibleTypeAnnotationsOffset = currentOffset;
/* 1303 */       } else if ("RuntimeVisibleParameterAnnotations".equals(attributeName)) {
/* 1304 */         runtimeVisibleParameterAnnotationsOffset = currentOffset;
/* 1305 */       } else if ("RuntimeInvisibleParameterAnnotations".equals(attributeName)) {
/* 1306 */         runtimeInvisibleParameterAnnotationsOffset = currentOffset;
/* 1307 */       } else if ("MethodParameters".equals(attributeName)) {
/* 1308 */         methodParametersOffset = currentOffset;
/*      */       } else {
/*      */         
/* 1311 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1319 */         attribute.nextAttribute = attributes;
/* 1320 */         attributes = attribute;
/*      */       } 
/* 1322 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1327 */     MethodVisitor methodVisitor = classVisitor.visitMethod(context.currentMethodAccessFlags, context.currentMethodName, context.currentMethodDescriptor, (signatureIndex == 0) ? null : 
/*      */ 
/*      */ 
/*      */         
/* 1331 */         readUtf(signatureIndex, charBuffer), exceptions);
/*      */     
/* 1333 */     if (methodVisitor == null) {
/* 1334 */       return currentOffset;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1341 */     if (methodVisitor instanceof MethodWriter) {
/* 1342 */       MethodWriter methodWriter = (MethodWriter)methodVisitor;
/* 1343 */       if (methodWriter.canCopyMethodAttributes(this, synthetic, ((context.currentMethodAccessFlags & 0x20000) != 0), 
/*      */ 
/*      */ 
/*      */           
/* 1347 */           readUnsignedShort(methodInfoOffset + 4), signatureIndex, exceptionsOffset)) {
/*      */ 
/*      */         
/* 1350 */         methodWriter.setMethodAttributesSource(methodInfoOffset, currentOffset - methodInfoOffset);
/* 1351 */         return currentOffset;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1356 */     if (methodParametersOffset != 0 && (context.parsingOptions & 0x2) == 0) {
/* 1357 */       int parametersCount = readByte(methodParametersOffset);
/* 1358 */       int currentParameterOffset = methodParametersOffset + 1;
/* 1359 */       while (parametersCount-- > 0) {
/*      */         
/* 1361 */         methodVisitor.visitParameter(
/* 1362 */             readUTF8(currentParameterOffset, charBuffer), 
/* 1363 */             readUnsignedShort(currentParameterOffset + 2));
/* 1364 */         currentParameterOffset += 4;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1369 */     if (annotationDefaultOffset != 0) {
/* 1370 */       AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
/* 1371 */       readElementValue(annotationVisitor, annotationDefaultOffset, null, charBuffer);
/* 1372 */       if (annotationVisitor != null) {
/* 1373 */         annotationVisitor.visitEnd();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1378 */     if (runtimeVisibleAnnotationsOffset != 0) {
/* 1379 */       int numAnnotations = readUnsignedShort(runtimeVisibleAnnotationsOffset);
/* 1380 */       int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
/* 1381 */       while (numAnnotations-- > 0) {
/*      */         
/* 1383 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1384 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1387 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1388 */             .visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1396 */     if (runtimeInvisibleAnnotationsOffset != 0) {
/* 1397 */       int numAnnotations = readUnsignedShort(runtimeInvisibleAnnotationsOffset);
/* 1398 */       int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
/* 1399 */       while (numAnnotations-- > 0) {
/*      */         
/* 1401 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1402 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1405 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1406 */             .visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1414 */     if (runtimeVisibleTypeAnnotationsOffset != 0) {
/* 1415 */       int numAnnotations = readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
/* 1416 */       int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
/* 1417 */       while (numAnnotations-- > 0) {
/*      */         
/* 1419 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1421 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1422 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1425 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1426 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1438 */     if (runtimeInvisibleTypeAnnotationsOffset != 0) {
/* 1439 */       int numAnnotations = readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
/* 1440 */       int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
/* 1441 */       while (numAnnotations-- > 0) {
/*      */         
/* 1443 */         currentAnnotationOffset = readTypeAnnotationTarget(context, currentAnnotationOffset);
/*      */         
/* 1445 */         String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 1446 */         currentAnnotationOffset += 2;
/*      */ 
/*      */         
/* 1449 */         currentAnnotationOffset = readElementValues(methodVisitor
/* 1450 */             .visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1462 */     if (runtimeVisibleParameterAnnotationsOffset != 0) {
/* 1463 */       readParameterAnnotations(methodVisitor, context, runtimeVisibleParameterAnnotationsOffset, true);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1468 */     if (runtimeInvisibleParameterAnnotationsOffset != 0) {
/* 1469 */       readParameterAnnotations(methodVisitor, context, runtimeInvisibleParameterAnnotationsOffset, false);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1477 */     while (attributes != null) {
/*      */       
/* 1479 */       Attribute nextAttribute = attributes.nextAttribute;
/* 1480 */       attributes.nextAttribute = null;
/* 1481 */       methodVisitor.visitAttribute(attributes);
/* 1482 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 1486 */     if (codeOffset != 0) {
/* 1487 */       methodVisitor.visitCode();
/* 1488 */       readCode(methodVisitor, context, codeOffset);
/*      */     } 
/*      */ 
/*      */     
/* 1492 */     methodVisitor.visitEnd();
/* 1493 */     return currentOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readCode(MethodVisitor methodVisitor, Context context, int codeOffset) {
/* 1510 */     int currentOffset = codeOffset;
/*      */ 
/*      */     
/* 1513 */     byte[] classBuffer = this.classFileBuffer;
/* 1514 */     char[] charBuffer = context.charBuffer;
/* 1515 */     int maxStack = readUnsignedShort(currentOffset);
/* 1516 */     int maxLocals = readUnsignedShort(currentOffset + 2);
/* 1517 */     int codeLength = readInt(currentOffset + 4);
/* 1518 */     currentOffset += 8;
/*      */ 
/*      */     
/* 1521 */     int bytecodeStartOffset = currentOffset;
/* 1522 */     int bytecodeEndOffset = currentOffset + codeLength;
/* 1523 */     Label[] labels = context.currentMethodLabels = new Label[codeLength + 1];
/* 1524 */     while (currentOffset < bytecodeEndOffset) {
/* 1525 */       int numTableEntries, numSwitchCases, bytecodeOffset = currentOffset - bytecodeStartOffset;
/* 1526 */       int opcode = classBuffer[currentOffset] & 0xFF;
/* 1527 */       switch (opcode) {
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*      */         case 13:
/*      */         case 14:
/*      */         case 15:
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32:
/*      */         case 33:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 59:
/*      */         case 60:
/*      */         case 61:
/*      */         case 62:
/*      */         case 63:
/*      */         case 64:
/*      */         case 65:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/*      */         case 76:
/*      */         case 77:
/*      */         case 78:
/*      */         case 79:
/*      */         case 80:
/*      */         case 81:
/*      */         case 82:
/*      */         case 83:
/*      */         case 84:
/*      */         case 85:
/*      */         case 86:
/*      */         case 87:
/*      */         case 88:
/*      */         case 89:
/*      */         case 90:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 94:
/*      */         case 95:
/*      */         case 96:
/*      */         case 97:
/*      */         case 98:
/*      */         case 99:
/*      */         case 100:
/*      */         case 101:
/*      */         case 102:
/*      */         case 103:
/*      */         case 104:
/*      */         case 105:
/*      */         case 106:
/*      */         case 107:
/*      */         case 108:
/*      */         case 109:
/*      */         case 110:
/*      */         case 111:
/*      */         case 112:
/*      */         case 113:
/*      */         case 114:
/*      */         case 115:
/*      */         case 116:
/*      */         case 117:
/*      */         case 118:
/*      */         case 119:
/*      */         case 120:
/*      */         case 121:
/*      */         case 122:
/*      */         case 123:
/*      */         case 124:
/*      */         case 125:
/*      */         case 126:
/*      */         case 127:
/*      */         case 128:
/*      */         case 129:
/*      */         case 130:
/*      */         case 131:
/*      */         case 133:
/*      */         case 134:
/*      */         case 135:
/*      */         case 136:
/*      */         case 137:
/*      */         case 138:
/*      */         case 139:
/*      */         case 140:
/*      */         case 141:
/*      */         case 142:
/*      */         case 143:
/*      */         case 144:
/*      */         case 145:
/*      */         case 146:
/*      */         case 147:
/*      */         case 148:
/*      */         case 149:
/*      */         case 150:
/*      */         case 151:
/*      */         case 152:
/*      */         case 172:
/*      */         case 173:
/*      */         case 174:
/*      */         case 175:
/*      */         case 176:
/*      */         case 177:
/*      */         case 190:
/*      */         case 191:
/*      */         case 194:
/*      */         case 195:
/* 1675 */           currentOffset++;
/*      */           continue;
/*      */         case 153:
/*      */         case 154:
/*      */         case 155:
/*      */         case 156:
/*      */         case 157:
/*      */         case 158:
/*      */         case 159:
/*      */         case 160:
/*      */         case 161:
/*      */         case 162:
/*      */         case 163:
/*      */         case 164:
/*      */         case 165:
/*      */         case 166:
/*      */         case 167:
/*      */         case 168:
/*      */         case 198:
/*      */         case 199:
/* 1695 */           createLabel(bytecodeOffset + readShort(currentOffset + 1), labels);
/* 1696 */           currentOffset += 3;
/*      */           continue;
/*      */         case 202:
/*      */         case 203:
/*      */         case 204:
/*      */         case 205:
/*      */         case 206:
/*      */         case 207:
/*      */         case 208:
/*      */         case 209:
/*      */         case 210:
/*      */         case 211:
/*      */         case 212:
/*      */         case 213:
/*      */         case 214:
/*      */         case 215:
/*      */         case 216:
/*      */         case 217:
/*      */         case 218:
/*      */         case 219:
/* 1716 */           createLabel(bytecodeOffset + readUnsignedShort(currentOffset + 1), labels);
/* 1717 */           currentOffset += 3;
/*      */           continue;
/*      */         case 200:
/*      */         case 201:
/*      */         case 220:
/* 1722 */           createLabel(bytecodeOffset + readInt(currentOffset + 1), labels);
/* 1723 */           currentOffset += 5;
/*      */           continue;
/*      */         case 196:
/* 1726 */           switch (classBuffer[currentOffset + 1] & 0xFF) {
/*      */             case 21:
/*      */             case 22:
/*      */             case 23:
/*      */             case 24:
/*      */             case 25:
/*      */             case 54:
/*      */             case 55:
/*      */             case 56:
/*      */             case 57:
/*      */             case 58:
/*      */             case 169:
/* 1738 */               currentOffset += 4;
/*      */               continue;
/*      */             case 132:
/* 1741 */               currentOffset += 6;
/*      */               continue;
/*      */           } 
/* 1744 */           throw new IllegalArgumentException();
/*      */ 
/*      */ 
/*      */         
/*      */         case 170:
/* 1749 */           currentOffset += 4 - (bytecodeOffset & 0x3);
/*      */           
/* 1751 */           createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1752 */           numTableEntries = readInt(currentOffset + 8) - readInt(currentOffset + 4) + 1;
/* 1753 */           currentOffset += 12;
/*      */           
/* 1755 */           while (numTableEntries-- > 0) {
/* 1756 */             createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1757 */             currentOffset += 4;
/*      */           } 
/*      */           continue;
/*      */         
/*      */         case 171:
/* 1762 */           currentOffset += 4 - (bytecodeOffset & 0x3);
/*      */           
/* 1764 */           createLabel(bytecodeOffset + readInt(currentOffset), labels);
/* 1765 */           numSwitchCases = readInt(currentOffset + 4);
/* 1766 */           currentOffset += 8;
/*      */           
/* 1768 */           while (numSwitchCases-- > 0) {
/* 1769 */             createLabel(bytecodeOffset + readInt(currentOffset + 4), labels);
/* 1770 */             currentOffset += 8;
/*      */           } 
/*      */           continue;
/*      */         case 16:
/*      */         case 18:
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */         case 58:
/*      */         case 169:
/*      */         case 188:
/* 1787 */           currentOffset += 2;
/*      */           continue;
/*      */         case 17:
/*      */         case 19:
/*      */         case 20:
/*      */         case 132:
/*      */         case 178:
/*      */         case 179:
/*      */         case 180:
/*      */         case 181:
/*      */         case 182:
/*      */         case 183:
/*      */         case 184:
/*      */         case 187:
/*      */         case 189:
/*      */         case 192:
/*      */         case 193:
/* 1804 */           currentOffset += 3;
/*      */           continue;
/*      */         case 185:
/*      */         case 186:
/* 1808 */           currentOffset += 5;
/*      */           continue;
/*      */         case 197:
/* 1811 */           currentOffset += 4;
/*      */           continue;
/*      */       } 
/* 1814 */       throw new IllegalArgumentException();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1820 */     int exceptionTableLength = readUnsignedShort(currentOffset);
/* 1821 */     currentOffset += 2;
/* 1822 */     while (exceptionTableLength-- > 0) {
/* 1823 */       Label start = createLabel(readUnsignedShort(currentOffset), labels);
/* 1824 */       Label end = createLabel(readUnsignedShort(currentOffset + 2), labels);
/* 1825 */       Label handler = createLabel(readUnsignedShort(currentOffset + 4), labels);
/* 1826 */       String catchType = readUTF8(this.cpInfoOffsets[readUnsignedShort(currentOffset + 6)], charBuffer);
/* 1827 */       currentOffset += 8;
/* 1828 */       methodVisitor.visitTryCatchBlock(start, end, handler, catchType);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1837 */     int stackMapFrameOffset = 0;
/*      */     
/* 1839 */     int stackMapTableEndOffset = 0;
/*      */     
/* 1841 */     boolean compressedFrames = true;
/*      */     
/* 1843 */     int localVariableTableOffset = 0;
/*      */     
/* 1845 */     int localVariableTypeTableOffset = 0;
/*      */ 
/*      */     
/* 1848 */     int[] visibleTypeAnnotationOffsets = null;
/*      */ 
/*      */     
/* 1851 */     int[] invisibleTypeAnnotationOffsets = null;
/*      */ 
/*      */     
/* 1854 */     Attribute attributes = null;
/*      */     
/* 1856 */     int attributesCount = readUnsignedShort(currentOffset);
/* 1857 */     currentOffset += 2;
/* 1858 */     while (attributesCount-- > 0) {
/*      */       
/* 1860 */       String attributeName = readUTF8(currentOffset, charBuffer);
/* 1861 */       int attributeLength = readInt(currentOffset + 2);
/* 1862 */       currentOffset += 6;
/* 1863 */       if ("LocalVariableTable".equals(attributeName)) {
/* 1864 */         if ((context.parsingOptions & 0x2) == 0) {
/* 1865 */           localVariableTableOffset = currentOffset;
/*      */           
/* 1867 */           int currentLocalVariableTableOffset = currentOffset;
/* 1868 */           int localVariableTableLength = readUnsignedShort(currentLocalVariableTableOffset);
/* 1869 */           currentLocalVariableTableOffset += 2;
/* 1870 */           while (localVariableTableLength-- > 0) {
/* 1871 */             int startPc = readUnsignedShort(currentLocalVariableTableOffset);
/* 1872 */             createDebugLabel(startPc, labels);
/* 1873 */             int length = readUnsignedShort(currentLocalVariableTableOffset + 2);
/* 1874 */             createDebugLabel(startPc + length, labels);
/*      */             
/* 1876 */             currentLocalVariableTableOffset += 10;
/*      */           } 
/*      */         } 
/* 1879 */       } else if ("LocalVariableTypeTable".equals(attributeName)) {
/* 1880 */         localVariableTypeTableOffset = currentOffset;
/*      */       
/*      */       }
/* 1883 */       else if ("LineNumberTable".equals(attributeName)) {
/* 1884 */         if ((context.parsingOptions & 0x2) == 0) {
/*      */           
/* 1886 */           int currentLineNumberTableOffset = currentOffset;
/* 1887 */           int lineNumberTableLength = readUnsignedShort(currentLineNumberTableOffset);
/* 1888 */           currentLineNumberTableOffset += 2;
/* 1889 */           while (lineNumberTableLength-- > 0) {
/* 1890 */             int startPc = readUnsignedShort(currentLineNumberTableOffset);
/* 1891 */             int lineNumber = readUnsignedShort(currentLineNumberTableOffset + 2);
/* 1892 */             currentLineNumberTableOffset += 4;
/* 1893 */             createDebugLabel(startPc, labels);
/* 1894 */             labels[startPc].addLineNumber(lineNumber);
/*      */           } 
/*      */         } 
/* 1897 */       } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
/*      */         
/* 1899 */         visibleTypeAnnotationOffsets = readTypeAnnotations(methodVisitor, context, currentOffset, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1906 */       else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
/*      */         
/* 1908 */         invisibleTypeAnnotationOffsets = readTypeAnnotations(methodVisitor, context, currentOffset, false);
/*      */       }
/* 1910 */       else if ("StackMapTable".equals(attributeName)) {
/* 1911 */         if ((context.parsingOptions & 0x4) == 0) {
/* 1912 */           stackMapFrameOffset = currentOffset + 2;
/* 1913 */           stackMapTableEndOffset = currentOffset + attributeLength;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1924 */       else if ("StackMap".equals(attributeName)) {
/* 1925 */         if ((context.parsingOptions & 0x4) == 0) {
/* 1926 */           stackMapFrameOffset = currentOffset + 2;
/* 1927 */           stackMapTableEndOffset = currentOffset + attributeLength;
/* 1928 */           compressedFrames = false;
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */         
/* 1936 */         Attribute attribute = readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, codeOffset, labels);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1944 */         attribute.nextAttribute = attributes;
/* 1945 */         attributes = attribute;
/*      */       } 
/* 1947 */       currentOffset += attributeLength;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1952 */     boolean expandFrames = ((context.parsingOptions & 0x8) != 0);
/* 1953 */     if (stackMapFrameOffset != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1957 */       context.currentFrameOffset = -1;
/* 1958 */       context.currentFrameType = 0;
/* 1959 */       context.currentFrameLocalCount = 0;
/* 1960 */       context.currentFrameLocalCountDelta = 0;
/* 1961 */       context.currentFrameLocalTypes = new Object[maxLocals];
/* 1962 */       context.currentFrameStackCount = 0;
/* 1963 */       context.currentFrameStackTypes = new Object[maxStack];
/* 1964 */       if (expandFrames) {
/* 1965 */         computeImplicitFrame(context);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1974 */       for (int offset = stackMapFrameOffset; offset < stackMapTableEndOffset - 2; offset++) {
/* 1975 */         if (classBuffer[offset] == 8) {
/* 1976 */           int potentialBytecodeOffset = readUnsignedShort(offset + 1);
/* 1977 */           if (potentialBytecodeOffset >= 0 && potentialBytecodeOffset < codeLength && (classBuffer[bytecodeStartOffset + potentialBytecodeOffset] & 0xFF) == 187)
/*      */           {
/*      */ 
/*      */             
/* 1981 */             createLabel(potentialBytecodeOffset, labels);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/* 1986 */     if (expandFrames && (context.parsingOptions & 0x100) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1993 */       methodVisitor.visitFrame(-1, maxLocals, null, 0, null);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2001 */     int currentVisibleTypeAnnotationIndex = 0;
/*      */ 
/*      */     
/* 2004 */     int currentVisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, 0);
/*      */ 
/*      */     
/* 2007 */     int currentInvisibleTypeAnnotationIndex = 0;
/*      */ 
/*      */     
/* 2010 */     int currentInvisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, 0);
/*      */ 
/*      */     
/* 2013 */     boolean insertFrame = false;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2018 */     int wideJumpOpcodeDelta = ((context.parsingOptions & 0x100) == 0) ? 33 : 0;
/*      */ 
/*      */     
/* 2021 */     currentOffset = bytecodeStartOffset;
/* 2022 */     while (currentOffset < bytecodeEndOffset) {
/* 2023 */       Label target, defaultLabel; int cpInfoOffset, low, numPairs, nameAndTypeCpInfoOffset, high, keys[]; String owner, name; Label[] table, values; String str1, descriptor; int i; String str2; int bootstrapMethodOffset; Handle handle; Object[] bootstrapMethodArguments; int j, currentBytecodeOffset = currentOffset - bytecodeStartOffset;
/*      */ 
/*      */       
/* 2026 */       Label currentLabel = labels[currentBytecodeOffset];
/* 2027 */       if (currentLabel != null) {
/* 2028 */         currentLabel.accept(methodVisitor, ((context.parsingOptions & 0x2) == 0));
/*      */       }
/*      */ 
/*      */       
/* 2032 */       while (stackMapFrameOffset != 0 && (context.currentFrameOffset == currentBytecodeOffset || context.currentFrameOffset == -1)) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2037 */         if (context.currentFrameOffset != -1) {
/* 2038 */           if (!compressedFrames || expandFrames) {
/* 2039 */             methodVisitor.visitFrame(-1, context.currentFrameLocalCount, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
/*      */ 
/*      */           
/*      */           }
/*      */           else {
/*      */ 
/*      */             
/* 2046 */             methodVisitor.visitFrame(context.currentFrameType, context.currentFrameLocalCountDelta, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 2055 */           insertFrame = false;
/*      */         } 
/* 2057 */         if (stackMapFrameOffset < stackMapTableEndOffset) {
/*      */           
/* 2059 */           stackMapFrameOffset = readStackMapFrame(stackMapFrameOffset, compressedFrames, expandFrames, context); continue;
/*      */         } 
/* 2061 */         stackMapFrameOffset = 0;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2067 */       if (insertFrame) {
/* 2068 */         if ((context.parsingOptions & 0x8) != 0) {
/* 2069 */           methodVisitor.visitFrame(256, 0, null, 0, null);
/*      */         }
/* 2071 */         insertFrame = false;
/*      */       } 
/*      */ 
/*      */       
/* 2075 */       int opcode = classBuffer[currentOffset] & 0xFF;
/* 2076 */       switch (opcode) {
/*      */         case 0:
/*      */         case 1:
/*      */         case 2:
/*      */         case 3:
/*      */         case 4:
/*      */         case 5:
/*      */         case 6:
/*      */         case 7:
/*      */         case 8:
/*      */         case 9:
/*      */         case 10:
/*      */         case 11:
/*      */         case 12:
/*      */         case 13:
/*      */         case 14:
/*      */         case 15:
/*      */         case 46:
/*      */         case 47:
/*      */         case 48:
/*      */         case 49:
/*      */         case 50:
/*      */         case 51:
/*      */         case 52:
/*      */         case 53:
/*      */         case 79:
/*      */         case 80:
/*      */         case 81:
/*      */         case 82:
/*      */         case 83:
/*      */         case 84:
/*      */         case 85:
/*      */         case 86:
/*      */         case 87:
/*      */         case 88:
/*      */         case 89:
/*      */         case 90:
/*      */         case 91:
/*      */         case 92:
/*      */         case 93:
/*      */         case 94:
/*      */         case 95:
/*      */         case 96:
/*      */         case 97:
/*      */         case 98:
/*      */         case 99:
/*      */         case 100:
/*      */         case 101:
/*      */         case 102:
/*      */         case 103:
/*      */         case 104:
/*      */         case 105:
/*      */         case 106:
/*      */         case 107:
/*      */         case 108:
/*      */         case 109:
/*      */         case 110:
/*      */         case 111:
/*      */         case 112:
/*      */         case 113:
/*      */         case 114:
/*      */         case 115:
/*      */         case 116:
/*      */         case 117:
/*      */         case 118:
/*      */         case 119:
/*      */         case 120:
/*      */         case 121:
/*      */         case 122:
/*      */         case 123:
/*      */         case 124:
/*      */         case 125:
/*      */         case 126:
/*      */         case 127:
/*      */         case 128:
/*      */         case 129:
/*      */         case 130:
/*      */         case 131:
/*      */         case 133:
/*      */         case 134:
/*      */         case 135:
/*      */         case 136:
/*      */         case 137:
/*      */         case 138:
/*      */         case 139:
/*      */         case 140:
/*      */         case 141:
/*      */         case 142:
/*      */         case 143:
/*      */         case 144:
/*      */         case 145:
/*      */         case 146:
/*      */         case 147:
/*      */         case 148:
/*      */         case 149:
/*      */         case 150:
/*      */         case 151:
/*      */         case 152:
/*      */         case 172:
/*      */         case 173:
/*      */         case 174:
/*      */         case 175:
/*      */         case 176:
/*      */         case 177:
/*      */         case 190:
/*      */         case 191:
/*      */         case 194:
/*      */         case 195:
/* 2184 */           methodVisitor.visitInsn(opcode);
/* 2185 */           currentOffset++;
/*      */           break;
/*      */         case 26:
/*      */         case 27:
/*      */         case 28:
/*      */         case 29:
/*      */         case 30:
/*      */         case 31:
/*      */         case 32:
/*      */         case 33:
/*      */         case 34:
/*      */         case 35:
/*      */         case 36:
/*      */         case 37:
/*      */         case 38:
/*      */         case 39:
/*      */         case 40:
/*      */         case 41:
/*      */         case 42:
/*      */         case 43:
/*      */         case 44:
/*      */         case 45:
/* 2207 */           opcode -= 26;
/* 2208 */           methodVisitor.visitVarInsn(21 + (opcode >> 2), opcode & 0x3);
/* 2209 */           currentOffset++;
/*      */           break;
/*      */         case 59:
/*      */         case 60:
/*      */         case 61:
/*      */         case 62:
/*      */         case 63:
/*      */         case 64:
/*      */         case 65:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/*      */         case 76:
/*      */         case 77:
/*      */         case 78:
/* 2231 */           opcode -= 59;
/* 2232 */           methodVisitor.visitVarInsn(54 + (opcode >> 2), opcode & 0x3);
/* 2233 */           currentOffset++;
/*      */           break;
/*      */         case 153:
/*      */         case 154:
/*      */         case 155:
/*      */         case 156:
/*      */         case 157:
/*      */         case 158:
/*      */         case 159:
/*      */         case 160:
/*      */         case 161:
/*      */         case 162:
/*      */         case 163:
/*      */         case 164:
/*      */         case 165:
/*      */         case 166:
/*      */         case 167:
/*      */         case 168:
/*      */         case 198:
/*      */         case 199:
/* 2253 */           methodVisitor.visitJumpInsn(opcode, labels[currentBytecodeOffset + 
/* 2254 */                 readShort(currentOffset + 1)]);
/* 2255 */           currentOffset += 3;
/*      */           break;
/*      */         case 200:
/*      */         case 201:
/* 2259 */           methodVisitor.visitJumpInsn(opcode - wideJumpOpcodeDelta, labels[currentBytecodeOffset + 
/*      */                 
/* 2261 */                 readInt(currentOffset + 1)]);
/* 2262 */           currentOffset += 5;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case 202:
/*      */         case 203:
/*      */         case 204:
/*      */         case 205:
/*      */         case 206:
/*      */         case 207:
/*      */         case 208:
/*      */         case 209:
/*      */         case 210:
/*      */         case 211:
/*      */         case 212:
/*      */         case 213:
/*      */         case 214:
/*      */         case 215:
/*      */         case 216:
/*      */         case 217:
/*      */         case 218:
/*      */         case 219:
/* 2289 */           opcode = (opcode < 218) ? (opcode - 49) : (opcode - 20);
/*      */ 
/*      */ 
/*      */           
/* 2293 */           target = labels[currentBytecodeOffset + readUnsignedShort(currentOffset + 1)];
/* 2294 */           if (opcode == 167 || opcode == 168) {
/*      */             
/* 2296 */             methodVisitor.visitJumpInsn(opcode + 33, target);
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 2301 */             opcode = (opcode < 167) ? ((opcode + 1 ^ 0x1) - 1) : (opcode ^ 0x1);
/* 2302 */             Label endif = createLabel(currentBytecodeOffset + 3, labels);
/* 2303 */             methodVisitor.visitJumpInsn(opcode, endif);
/* 2304 */             methodVisitor.visitJumpInsn(200, target);
/*      */ 
/*      */             
/* 2307 */             insertFrame = true;
/*      */           } 
/* 2309 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */         
/*      */         case 220:
/* 2314 */           methodVisitor.visitJumpInsn(200, labels[currentBytecodeOffset + 
/* 2315 */                 readInt(currentOffset + 1)]);
/*      */ 
/*      */ 
/*      */           
/* 2319 */           insertFrame = true;
/* 2320 */           currentOffset += 5;
/*      */           break;
/*      */         case 196:
/* 2323 */           opcode = classBuffer[currentOffset + 1] & 0xFF;
/* 2324 */           if (opcode == 132) {
/* 2325 */             methodVisitor.visitIincInsn(
/* 2326 */                 readUnsignedShort(currentOffset + 2), readShort(currentOffset + 4));
/* 2327 */             currentOffset += 6; break;
/*      */           } 
/* 2329 */           methodVisitor.visitVarInsn(opcode, readUnsignedShort(currentOffset + 2));
/* 2330 */           currentOffset += 4;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 170:
/* 2336 */           currentOffset += 4 - (currentBytecodeOffset & 0x3);
/*      */           
/* 2338 */           defaultLabel = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2339 */           low = readInt(currentOffset + 4);
/* 2340 */           high = readInt(currentOffset + 8);
/* 2341 */           currentOffset += 12;
/* 2342 */           table = new Label[high - low + 1];
/* 2343 */           for (i = 0; i < table.length; i++) {
/* 2344 */             table[i] = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2345 */             currentOffset += 4;
/*      */           } 
/* 2347 */           methodVisitor.visitTableSwitchInsn(low, high, defaultLabel, table);
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 171:
/* 2353 */           currentOffset += 4 - (currentBytecodeOffset & 0x3);
/*      */           
/* 2355 */           defaultLabel = labels[currentBytecodeOffset + readInt(currentOffset)];
/* 2356 */           numPairs = readInt(currentOffset + 4);
/* 2357 */           currentOffset += 8;
/* 2358 */           keys = new int[numPairs];
/* 2359 */           values = new Label[numPairs];
/* 2360 */           for (i = 0; i < numPairs; i++) {
/* 2361 */             keys[i] = readInt(currentOffset);
/* 2362 */             values[i] = labels[currentBytecodeOffset + readInt(currentOffset + 4)];
/* 2363 */             currentOffset += 8;
/*      */           } 
/* 2365 */           methodVisitor.visitLookupSwitchInsn(defaultLabel, keys, values);
/*      */           break;
/*      */         
/*      */         case 21:
/*      */         case 22:
/*      */         case 23:
/*      */         case 24:
/*      */         case 25:
/*      */         case 54:
/*      */         case 55:
/*      */         case 56:
/*      */         case 57:
/*      */         case 58:
/*      */         case 169:
/* 2379 */           methodVisitor.visitVarInsn(opcode, classBuffer[currentOffset + 1] & 0xFF);
/* 2380 */           currentOffset += 2;
/*      */           break;
/*      */         case 16:
/*      */         case 188:
/* 2384 */           methodVisitor.visitIntInsn(opcode, classBuffer[currentOffset + 1]);
/* 2385 */           currentOffset += 2;
/*      */           break;
/*      */         case 17:
/* 2388 */           methodVisitor.visitIntInsn(opcode, readShort(currentOffset + 1));
/* 2389 */           currentOffset += 3;
/*      */           break;
/*      */         case 18:
/* 2392 */           methodVisitor.visitLdcInsn(readConst(classBuffer[currentOffset + 1] & 0xFF, charBuffer));
/* 2393 */           currentOffset += 2;
/*      */           break;
/*      */         case 19:
/*      */         case 20:
/* 2397 */           methodVisitor.visitLdcInsn(readConst(readUnsignedShort(currentOffset + 1), charBuffer));
/* 2398 */           currentOffset += 3;
/*      */           break;
/*      */         
/*      */         case 178:
/*      */         case 179:
/*      */         case 180:
/*      */         case 181:
/*      */         case 182:
/*      */         case 183:
/*      */         case 184:
/*      */         case 185:
/* 2409 */           cpInfoOffset = this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
/* 2410 */           nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 2411 */           owner = readClass(cpInfoOffset, charBuffer);
/* 2412 */           str1 = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 2413 */           str2 = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 2414 */           if (opcode < 182) {
/* 2415 */             methodVisitor.visitFieldInsn(opcode, owner, str1, str2);
/*      */           } else {
/* 2417 */             boolean isInterface = (classBuffer[cpInfoOffset - 1] == 11);
/*      */             
/* 2419 */             methodVisitor.visitMethodInsn(opcode, owner, str1, str2, isInterface);
/*      */           } 
/* 2421 */           if (opcode == 185) {
/* 2422 */             currentOffset += 5; break;
/*      */           } 
/* 2424 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 186:
/* 2430 */           cpInfoOffset = this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)];
/* 2431 */           nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 2432 */           name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 2433 */           descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 2434 */           bootstrapMethodOffset = this.bootstrapMethodOffsets[readUnsignedShort(cpInfoOffset)];
/*      */           
/* 2436 */           handle = (Handle)readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/*      */           
/* 2438 */           bootstrapMethodArguments = new Object[readUnsignedShort(bootstrapMethodOffset + 2)];
/* 2439 */           bootstrapMethodOffset += 4;
/* 2440 */           for (j = 0; j < bootstrapMethodArguments.length; j++) {
/* 2441 */             bootstrapMethodArguments[j] = 
/* 2442 */               readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 2443 */             bootstrapMethodOffset += 2;
/*      */           } 
/* 2445 */           methodVisitor.visitInvokeDynamicInsn(name, descriptor, handle, bootstrapMethodArguments);
/*      */           
/* 2447 */           currentOffset += 5;
/*      */           break;
/*      */         
/*      */         case 187:
/*      */         case 189:
/*      */         case 192:
/*      */         case 193:
/* 2454 */           methodVisitor.visitTypeInsn(opcode, readClass(currentOffset + 1, charBuffer));
/* 2455 */           currentOffset += 3;
/*      */           break;
/*      */         case 132:
/* 2458 */           methodVisitor.visitIincInsn(classBuffer[currentOffset + 1] & 0xFF, classBuffer[currentOffset + 2]);
/*      */           
/* 2460 */           currentOffset += 3;
/*      */           break;
/*      */         case 197:
/* 2463 */           methodVisitor.visitMultiANewArrayInsn(
/* 2464 */               readClass(currentOffset + 1, charBuffer), classBuffer[currentOffset + 3] & 0xFF);
/* 2465 */           currentOffset += 4;
/*      */           break;
/*      */         default:
/* 2468 */           throw new AssertionError();
/*      */       } 
/*      */ 
/*      */       
/* 2472 */       while (visibleTypeAnnotationOffsets != null && currentVisibleTypeAnnotationIndex < visibleTypeAnnotationOffsets.length && currentVisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
/*      */ 
/*      */         
/* 2475 */         if (currentVisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
/*      */ 
/*      */           
/* 2478 */           int currentAnnotationOffset = readTypeAnnotationTarget(context, visibleTypeAnnotationOffsets[currentVisibleTypeAnnotationIndex]);
/*      */ 
/*      */           
/* 2481 */           String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 2482 */           currentAnnotationOffset += 2;
/*      */           
/* 2484 */           readElementValues(methodVisitor
/* 2485 */               .visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2495 */         currentVisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, ++currentVisibleTypeAnnotationIndex);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2500 */       while (invisibleTypeAnnotationOffsets != null && currentInvisibleTypeAnnotationIndex < invisibleTypeAnnotationOffsets.length && currentInvisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
/*      */ 
/*      */         
/* 2503 */         if (currentInvisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
/*      */ 
/*      */           
/* 2506 */           int currentAnnotationOffset = readTypeAnnotationTarget(context, invisibleTypeAnnotationOffsets[currentInvisibleTypeAnnotationIndex]);
/*      */ 
/*      */           
/* 2509 */           String annotationDescriptor = readUTF8(currentAnnotationOffset, charBuffer);
/* 2510 */           currentAnnotationOffset += 2;
/*      */           
/* 2512 */           readElementValues(methodVisitor
/* 2513 */               .visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2523 */         currentInvisibleTypeAnnotationBytecodeOffset = getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, ++currentInvisibleTypeAnnotationIndex);
/*      */       } 
/*      */     } 
/*      */     
/* 2527 */     if (labels[codeLength] != null) {
/* 2528 */       methodVisitor.visitLabel(labels[codeLength]);
/*      */     }
/*      */ 
/*      */     
/* 2532 */     if (localVariableTableOffset != 0 && (context.parsingOptions & 0x2) == 0) {
/*      */       
/* 2534 */       int[] typeTable = null;
/* 2535 */       if (localVariableTypeTableOffset != 0) {
/* 2536 */         typeTable = new int[readUnsignedShort(localVariableTypeTableOffset) * 3];
/* 2537 */         currentOffset = localVariableTypeTableOffset + 2;
/* 2538 */         int typeTableIndex = typeTable.length;
/* 2539 */         while (typeTableIndex > 0) {
/*      */           
/* 2541 */           typeTable[--typeTableIndex] = currentOffset + 6;
/* 2542 */           typeTable[--typeTableIndex] = readUnsignedShort(currentOffset + 8);
/* 2543 */           typeTable[--typeTableIndex] = readUnsignedShort(currentOffset);
/* 2544 */           currentOffset += 10;
/*      */         } 
/*      */       } 
/* 2547 */       int localVariableTableLength = readUnsignedShort(localVariableTableOffset);
/* 2548 */       currentOffset = localVariableTableOffset + 2;
/* 2549 */       while (localVariableTableLength-- > 0) {
/* 2550 */         int startPc = readUnsignedShort(currentOffset);
/* 2551 */         int length = readUnsignedShort(currentOffset + 2);
/* 2552 */         String name = readUTF8(currentOffset + 4, charBuffer);
/* 2553 */         String descriptor = readUTF8(currentOffset + 6, charBuffer);
/* 2554 */         int index = readUnsignedShort(currentOffset + 8);
/* 2555 */         currentOffset += 10;
/* 2556 */         String signature = null;
/* 2557 */         if (typeTable != null) {
/* 2558 */           for (int i = 0; i < typeTable.length; i += 3) {
/* 2559 */             if (typeTable[i] == startPc && typeTable[i + 1] == index) {
/* 2560 */               signature = readUTF8(typeTable[i + 2], charBuffer);
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/* 2565 */         methodVisitor.visitLocalVariable(name, descriptor, signature, labels[startPc], labels[startPc + length], index);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2571 */     if (visibleTypeAnnotationOffsets != null) {
/* 2572 */       for (int typeAnnotationOffset : visibleTypeAnnotationOffsets) {
/* 2573 */         int targetType = readByte(typeAnnotationOffset);
/* 2574 */         if (targetType == 64 || targetType == 65) {
/*      */ 
/*      */           
/* 2577 */           currentOffset = readTypeAnnotationTarget(context, typeAnnotationOffset);
/*      */           
/* 2579 */           String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2580 */           currentOffset += 2;
/*      */           
/* 2582 */           readElementValues(methodVisitor
/* 2583 */               .visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, true), currentOffset, true, charBuffer);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2599 */     if (invisibleTypeAnnotationOffsets != null) {
/* 2600 */       for (int typeAnnotationOffset : invisibleTypeAnnotationOffsets) {
/* 2601 */         int targetType = readByte(typeAnnotationOffset);
/* 2602 */         if (targetType == 64 || targetType == 65) {
/*      */ 
/*      */           
/* 2605 */           currentOffset = readTypeAnnotationTarget(context, typeAnnotationOffset);
/*      */           
/* 2607 */           String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2608 */           currentOffset += 2;
/*      */           
/* 2610 */           readElementValues(methodVisitor
/* 2611 */               .visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, false), currentOffset, true, charBuffer);
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2627 */     while (attributes != null) {
/*      */       
/* 2629 */       Attribute nextAttribute = attributes.nextAttribute;
/* 2630 */       attributes.nextAttribute = null;
/* 2631 */       methodVisitor.visitAttribute(attributes);
/* 2632 */       attributes = nextAttribute;
/*      */     } 
/*      */ 
/*      */     
/* 2636 */     methodVisitor.visitMaxs(maxStack, maxLocals);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Label readLabel(int bytecodeOffset, Label[] labels) {
/* 2650 */     if (labels[bytecodeOffset] == null) {
/* 2651 */       labels[bytecodeOffset] = new Label();
/*      */     }
/* 2653 */     return labels[bytecodeOffset];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Label createLabel(int bytecodeOffset, Label[] labels) {
/* 2666 */     Label label = readLabel(bytecodeOffset, labels);
/* 2667 */     label.flags = (short)(label.flags & 0xFFFFFFFE);
/* 2668 */     return label;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void createDebugLabel(int bytecodeOffset, Label[] labels) {
/* 2680 */     if (labels[bytecodeOffset] == null) {
/* 2681 */       (readLabel(bytecodeOffset, labels)).flags = (short)((readLabel(bytecodeOffset, labels)).flags | 0x1);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int runtimeTypeAnnotationsOffset, boolean visible) {
/* 2708 */     char[] charBuffer = context.charBuffer;
/* 2709 */     int currentOffset = runtimeTypeAnnotationsOffset;
/*      */     
/* 2711 */     int[] typeAnnotationsOffsets = new int[readUnsignedShort(currentOffset)];
/* 2712 */     currentOffset += 2;
/*      */     
/* 2714 */     for (int i = 0; i < typeAnnotationsOffsets.length; i++) {
/* 2715 */       int tableLength; typeAnnotationsOffsets[i] = currentOffset;
/*      */ 
/*      */       
/* 2718 */       int targetType = readInt(currentOffset);
/* 2719 */       switch (targetType >>> 24) {
/*      */ 
/*      */         
/*      */         case 64:
/*      */         case 65:
/* 2724 */           tableLength = readUnsignedShort(currentOffset + 1);
/* 2725 */           currentOffset += 3;
/* 2726 */           while (tableLength-- > 0) {
/* 2727 */             int startPc = readUnsignedShort(currentOffset);
/* 2728 */             int length = readUnsignedShort(currentOffset + 2);
/*      */             
/* 2730 */             currentOffset += 6;
/* 2731 */             createLabel(startPc, context.currentMethodLabels);
/* 2732 */             createLabel(startPc + length, context.currentMethodLabels);
/*      */           } 
/*      */           break;
/*      */         case 71:
/*      */         case 72:
/*      */         case 73:
/*      */         case 74:
/*      */         case 75:
/* 2740 */           currentOffset += 4;
/*      */           break;
/*      */         case 16:
/*      */         case 17:
/*      */         case 18:
/*      */         case 23:
/*      */         case 66:
/*      */         case 67:
/*      */         case 68:
/*      */         case 69:
/*      */         case 70:
/* 2751 */           currentOffset += 3;
/*      */           break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         default:
/* 2761 */           throw new IllegalArgumentException();
/*      */       } 
/*      */ 
/*      */       
/* 2765 */       int pathLength = readByte(currentOffset);
/* 2766 */       if (targetType >>> 24 == 66) {
/*      */         
/* 2768 */         TypePath path = (pathLength == 0) ? null : new TypePath(this.classFileBuffer, currentOffset);
/* 2769 */         currentOffset += 1 + 2 * pathLength;
/*      */         
/* 2771 */         String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2772 */         currentOffset += 2;
/*      */ 
/*      */         
/* 2775 */         currentOffset = readElementValues(methodVisitor
/* 2776 */             .visitTryCatchAnnotation(targetType & 0xFFFFFF00, path, annotationDescriptor, visible), currentOffset, true, charBuffer);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/* 2785 */         currentOffset += 3 + 2 * pathLength;
/*      */ 
/*      */ 
/*      */         
/* 2789 */         currentOffset = readElementValues(null, currentOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */     
/* 2793 */     return typeAnnotationsOffsets;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getTypeAnnotationBytecodeOffset(int[] typeAnnotationOffsets, int typeAnnotationIndex) {
/* 2808 */     if (typeAnnotationOffsets == null || typeAnnotationIndex >= typeAnnotationOffsets.length || 
/*      */       
/* 2810 */       readByte(typeAnnotationOffsets[typeAnnotationIndex]) < 67) {
/* 2811 */       return -1;
/*      */     }
/* 2813 */     return readUnsignedShort(typeAnnotationOffsets[typeAnnotationIndex] + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readTypeAnnotationTarget(Context context, int typeAnnotationOffset) {
/* 2827 */     int tableLength, i, currentOffset = typeAnnotationOffset;
/*      */     
/* 2829 */     int targetType = readInt(typeAnnotationOffset);
/* 2830 */     switch (targetType >>> 24) {
/*      */       case 0:
/*      */       case 1:
/*      */       case 22:
/* 2834 */         targetType &= 0xFFFF0000;
/* 2835 */         currentOffset += 2;
/*      */         break;
/*      */       case 19:
/*      */       case 20:
/*      */       case 21:
/* 2840 */         targetType &= 0xFF000000;
/* 2841 */         currentOffset++;
/*      */         break;
/*      */       case 64:
/*      */       case 65:
/* 2845 */         targetType &= 0xFF000000;
/* 2846 */         tableLength = readUnsignedShort(currentOffset + 1);
/* 2847 */         currentOffset += 3;
/* 2848 */         context.currentLocalVariableAnnotationRangeStarts = new Label[tableLength];
/* 2849 */         context.currentLocalVariableAnnotationRangeEnds = new Label[tableLength];
/* 2850 */         context.currentLocalVariableAnnotationRangeIndices = new int[tableLength];
/* 2851 */         for (i = 0; i < tableLength; i++) {
/* 2852 */           int startPc = readUnsignedShort(currentOffset);
/* 2853 */           int length = readUnsignedShort(currentOffset + 2);
/* 2854 */           int index = readUnsignedShort(currentOffset + 4);
/* 2855 */           currentOffset += 6;
/* 2856 */           context.currentLocalVariableAnnotationRangeStarts[i] = 
/* 2857 */             createLabel(startPc, context.currentMethodLabels);
/* 2858 */           context.currentLocalVariableAnnotationRangeEnds[i] = 
/* 2859 */             createLabel(startPc + length, context.currentMethodLabels);
/* 2860 */           context.currentLocalVariableAnnotationRangeIndices[i] = index;
/*      */         } 
/*      */         break;
/*      */       case 71:
/*      */       case 72:
/*      */       case 73:
/*      */       case 74:
/*      */       case 75:
/* 2868 */         targetType &= 0xFF0000FF;
/* 2869 */         currentOffset += 4;
/*      */         break;
/*      */       case 16:
/*      */       case 17:
/*      */       case 18:
/*      */       case 23:
/*      */       case 66:
/* 2876 */         targetType &= 0xFFFFFF00;
/* 2877 */         currentOffset += 3;
/*      */         break;
/*      */       case 67:
/*      */       case 68:
/*      */       case 69:
/*      */       case 70:
/* 2883 */         targetType &= 0xFF000000;
/* 2884 */         currentOffset += 3;
/*      */         break;
/*      */       default:
/* 2887 */         throw new IllegalArgumentException();
/*      */     } 
/* 2889 */     context.currentTypeAnnotationTarget = targetType;
/*      */     
/* 2891 */     int pathLength = readByte(currentOffset);
/* 2892 */     context.currentTypeAnnotationTargetPath = (pathLength == 0) ? null : new TypePath(this.classFileBuffer, currentOffset);
/*      */ 
/*      */     
/* 2895 */     return currentOffset + 1 + 2 * pathLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int runtimeParameterAnnotationsOffset, boolean visible) {
/* 2914 */     int currentOffset = runtimeParameterAnnotationsOffset;
/* 2915 */     int numParameters = this.classFileBuffer[currentOffset++] & 0xFF;
/* 2916 */     methodVisitor.visitAnnotableParameterCount(numParameters, visible);
/* 2917 */     char[] charBuffer = context.charBuffer;
/* 2918 */     for (int i = 0; i < numParameters; i++) {
/* 2919 */       int numAnnotations = readUnsignedShort(currentOffset);
/* 2920 */       currentOffset += 2;
/* 2921 */       while (numAnnotations-- > 0) {
/*      */         
/* 2923 */         String annotationDescriptor = readUTF8(currentOffset, charBuffer);
/* 2924 */         currentOffset += 2;
/*      */ 
/*      */         
/* 2927 */         currentOffset = readElementValues(methodVisitor
/* 2928 */             .visitParameterAnnotation(i, annotationDescriptor, visible), currentOffset, true, charBuffer);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readElementValues(AnnotationVisitor annotationVisitor, int annotationOffset, boolean named, char[] charBuffer) {
/* 2955 */     int currentOffset = annotationOffset;
/*      */     
/* 2957 */     int numElementValuePairs = readUnsignedShort(currentOffset);
/* 2958 */     currentOffset += 2;
/* 2959 */     if (named) {
/*      */       
/* 2961 */       while (numElementValuePairs-- > 0) {
/* 2962 */         String elementName = readUTF8(currentOffset, charBuffer);
/*      */         
/* 2964 */         currentOffset = readElementValue(annotationVisitor, currentOffset + 2, elementName, charBuffer);
/*      */       } 
/*      */     } else {
/*      */       
/* 2968 */       while (numElementValuePairs-- > 0)
/*      */       {
/* 2970 */         currentOffset = readElementValue(annotationVisitor, currentOffset, null, charBuffer);
/*      */       }
/*      */     } 
/* 2973 */     if (annotationVisitor != null) {
/* 2974 */       annotationVisitor.visitEnd();
/*      */     }
/* 2976 */     return currentOffset;
/*      */   }
/*      */   
/*      */   private int readElementValue(AnnotationVisitor annotationVisitor, int elementValueOffset, String elementName, char[] charBuffer) {
/*      */     int numValues;
/*      */     byte[] byteValues;
/*      */     int i;
/*      */     boolean[] booleanValues;
/*      */     int j;
/*      */     short[] shortValues;
/*      */     int k;
/*      */     char[] charValues;
/*      */     int m, intValues[], n;
/*      */     long[] longValues;
/*      */     int i1;
/*      */     float[] floatValues;
/*      */     int i2;
/*      */     double[] doubleValues;
/* 2994 */     int i3, currentOffset = elementValueOffset;
/* 2995 */     if (annotationVisitor == null) {
/* 2996 */       switch (this.classFileBuffer[currentOffset] & 0xFF) {
/*      */         case 101:
/* 2998 */           return currentOffset + 5;
/*      */         case 64:
/* 3000 */           return readElementValues(null, currentOffset + 3, true, charBuffer);
/*      */         case 91:
/* 3002 */           return readElementValues(null, currentOffset + 1, false, charBuffer);
/*      */       } 
/* 3004 */       return currentOffset + 3;
/*      */     } 
/*      */     
/* 3007 */     switch (this.classFileBuffer[currentOffset++] & 0xFF) {
/*      */       case 66:
/* 3009 */         annotationVisitor.visit(elementName, 
/* 3010 */             Byte.valueOf((byte)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)])));
/* 3011 */         currentOffset += 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3155 */         return currentOffset;case 67: annotationVisitor.visit(elementName, Character.valueOf((char)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]))); currentOffset += 2; return currentOffset;case 68: case 70: case 73: case 74: annotationVisitor.visit(elementName, readConst(readUnsignedShort(currentOffset), charBuffer)); currentOffset += 2; return currentOffset;case 83: annotationVisitor.visit(elementName, Short.valueOf((short)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]))); currentOffset += 2; return currentOffset;case 90: annotationVisitor.visit(elementName, (readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset)]) == 0) ? Boolean.FALSE : Boolean.TRUE); currentOffset += 2; return currentOffset;case 115: annotationVisitor.visit(elementName, readUTF8(currentOffset, charBuffer)); currentOffset += 2; return currentOffset;case 101: annotationVisitor.visitEnum(elementName, readUTF8(currentOffset, charBuffer), readUTF8(currentOffset + 2, charBuffer)); currentOffset += 4; return currentOffset;case 99: annotationVisitor.visit(elementName, Type.getType(readUTF8(currentOffset, charBuffer))); currentOffset += 2; return currentOffset;case 64: currentOffset = readElementValues(annotationVisitor.visitAnnotation(elementName, readUTF8(currentOffset, charBuffer)), currentOffset + 2, true, charBuffer); return currentOffset;case 91: numValues = readUnsignedShort(currentOffset); currentOffset += 2; if (numValues == 0) return readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer);  switch (this.classFileBuffer[currentOffset] & 0xFF) { case 66: byteValues = new byte[numValues]; for (i = 0; i < numValues; i++) { byteValues[i] = (byte)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, byteValues); return currentOffset;case 90: booleanValues = new boolean[numValues]; for (j = 0; j < numValues; j++) { booleanValues[j] = (readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]) != 0); currentOffset += 3; }  annotationVisitor.visit(elementName, booleanValues); return currentOffset;case 83: shortValues = new short[numValues]; for (k = 0; k < numValues; k++) { shortValues[k] = (short)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, shortValues); return currentOffset;case 67: charValues = new char[numValues]; for (m = 0; m < numValues; m++) { charValues[m] = (char)readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, charValues); return currentOffset;case 73: intValues = new int[numValues]; for (n = 0; n < numValues; n++) { intValues[n] = readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, intValues); return currentOffset;case 74: longValues = new long[numValues]; for (i1 = 0; i1 < numValues; i1++) { longValues[i1] = readLong(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)]); currentOffset += 3; }  annotationVisitor.visit(elementName, longValues); return currentOffset;case 70: floatValues = new float[numValues]; for (i2 = 0; i2 < numValues; i2++) { floatValues[i2] = Float.intBitsToFloat(readInt(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)])); currentOffset += 3; }  annotationVisitor.visit(elementName, floatValues); return currentOffset;case 68: doubleValues = new double[numValues]; for (i3 = 0; i3 < numValues; i3++) { doubleValues[i3] = Double.longBitsToDouble(readLong(this.cpInfoOffsets[readUnsignedShort(currentOffset + 1)])); currentOffset += 3; }  annotationVisitor.visit(elementName, doubleValues); return currentOffset; }  currentOffset = readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer); return currentOffset;
/*      */     } 
/*      */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void computeImplicitFrame(Context context) {
/* 3169 */     String methodDescriptor = context.currentMethodDescriptor;
/* 3170 */     Object[] locals = context.currentFrameLocalTypes;
/* 3171 */     int numLocal = 0;
/* 3172 */     if ((context.currentMethodAccessFlags & 0x8) == 0) {
/* 3173 */       if ("<init>".equals(context.currentMethodName)) {
/* 3174 */         locals[numLocal++] = Opcodes.UNINITIALIZED_THIS;
/*      */       } else {
/* 3176 */         locals[numLocal++] = readClass(this.header + 2, context.charBuffer);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 3181 */     int currentMethodDescritorOffset = 1;
/*      */     while (true) {
/* 3183 */       int currentArgumentDescriptorStartOffset = currentMethodDescritorOffset;
/* 3184 */       switch (methodDescriptor.charAt(currentMethodDescritorOffset++)) {
/*      */         case 'B':
/*      */         case 'C':
/*      */         case 'I':
/*      */         case 'S':
/*      */         case 'Z':
/* 3190 */           locals[numLocal++] = Opcodes.INTEGER;
/*      */           continue;
/*      */         case 'F':
/* 3193 */           locals[numLocal++] = Opcodes.FLOAT;
/*      */           continue;
/*      */         case 'J':
/* 3196 */           locals[numLocal++] = Opcodes.LONG;
/*      */           continue;
/*      */         case 'D':
/* 3199 */           locals[numLocal++] = Opcodes.DOUBLE;
/*      */           continue;
/*      */         case '[':
/* 3202 */           while (methodDescriptor.charAt(currentMethodDescritorOffset) == '[') {
/* 3203 */             currentMethodDescritorOffset++;
/*      */           }
/* 3205 */           if (methodDescriptor.charAt(currentMethodDescritorOffset) == 'L') {
/* 3206 */             currentMethodDescritorOffset++;
/* 3207 */             while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
/* 3208 */               currentMethodDescritorOffset++;
/*      */             }
/*      */           } 
/* 3211 */           locals[numLocal++] = methodDescriptor
/* 3212 */             .substring(currentArgumentDescriptorStartOffset, ++currentMethodDescritorOffset);
/*      */           continue;
/*      */         
/*      */         case 'L':
/* 3216 */           while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
/* 3217 */             currentMethodDescritorOffset++;
/*      */           }
/* 3219 */           locals[numLocal++] = methodDescriptor
/* 3220 */             .substring(currentArgumentDescriptorStartOffset + 1, currentMethodDescritorOffset++); continue;
/*      */       } 
/*      */       break;
/*      */     } 
/* 3224 */     context.currentFrameLocalCount = numLocal;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readStackMapFrame(int stackMapFrameOffset, boolean compressed, boolean expand, Context context) {
/* 3249 */     int frameType, offsetDelta, currentOffset = stackMapFrameOffset;
/* 3250 */     char[] charBuffer = context.charBuffer;
/* 3251 */     Label[] labels = context.currentMethodLabels;
/*      */     
/* 3253 */     if (compressed) {
/*      */       
/* 3255 */       frameType = this.classFileBuffer[currentOffset++] & 0xFF;
/*      */     } else {
/* 3257 */       frameType = 255;
/* 3258 */       context.currentFrameOffset = -1;
/*      */     } 
/*      */     
/* 3261 */     context.currentFrameLocalCountDelta = 0;
/* 3262 */     if (frameType < 64) {
/* 3263 */       offsetDelta = frameType;
/* 3264 */       context.currentFrameType = 3;
/* 3265 */       context.currentFrameStackCount = 0;
/* 3266 */     } else if (frameType < 128) {
/* 3267 */       offsetDelta = frameType - 64;
/*      */       
/* 3269 */       currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
/*      */       
/* 3271 */       context.currentFrameType = 4;
/* 3272 */       context.currentFrameStackCount = 1;
/* 3273 */     } else if (frameType >= 247) {
/* 3274 */       offsetDelta = readUnsignedShort(currentOffset);
/* 3275 */       currentOffset += 2;
/* 3276 */       if (frameType == 247) {
/*      */         
/* 3278 */         currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
/*      */         
/* 3280 */         context.currentFrameType = 4;
/* 3281 */         context.currentFrameStackCount = 1;
/* 3282 */       } else if (frameType >= 248 && frameType < 251) {
/* 3283 */         context.currentFrameType = 2;
/* 3284 */         context.currentFrameLocalCountDelta = 251 - frameType;
/* 3285 */         context.currentFrameLocalCount -= context.currentFrameLocalCountDelta;
/* 3286 */         context.currentFrameStackCount = 0;
/* 3287 */       } else if (frameType == 251) {
/* 3288 */         context.currentFrameType = 3;
/* 3289 */         context.currentFrameStackCount = 0;
/* 3290 */       } else if (frameType < 255) {
/* 3291 */         int local = expand ? context.currentFrameLocalCount : 0;
/* 3292 */         for (int k = frameType - 251; k > 0; k--)
/*      */         {
/* 3294 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local++, charBuffer, labels);
/*      */         }
/*      */         
/* 3297 */         context.currentFrameType = 1;
/* 3298 */         context.currentFrameLocalCountDelta = frameType - 251;
/* 3299 */         context.currentFrameLocalCount += context.currentFrameLocalCountDelta;
/* 3300 */         context.currentFrameStackCount = 0;
/*      */       } else {
/* 3302 */         int numberOfLocals = readUnsignedShort(currentOffset);
/* 3303 */         currentOffset += 2;
/* 3304 */         context.currentFrameType = 0;
/* 3305 */         context.currentFrameLocalCountDelta = numberOfLocals;
/* 3306 */         context.currentFrameLocalCount = numberOfLocals;
/* 3307 */         for (int local = 0; local < numberOfLocals; local++)
/*      */         {
/* 3309 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local, charBuffer, labels);
/*      */         }
/*      */         
/* 3312 */         int numberOfStackItems = readUnsignedShort(currentOffset);
/* 3313 */         currentOffset += 2;
/* 3314 */         context.currentFrameStackCount = numberOfStackItems;
/* 3315 */         for (int stack = 0; stack < numberOfStackItems; stack++)
/*      */         {
/* 3317 */           currentOffset = readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, stack, charBuffer, labels);
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 3322 */       throw new IllegalArgumentException();
/*      */     } 
/* 3324 */     context.currentFrameOffset += offsetDelta + 1;
/* 3325 */     createLabel(context.currentFrameOffset, labels);
/* 3326 */     return currentOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int readVerificationTypeInfo(int verificationTypeInfoOffset, Object[] frame, int index, char[] charBuffer, Label[] labels) {
/* 3349 */     int currentOffset = verificationTypeInfoOffset;
/* 3350 */     int tag = this.classFileBuffer[currentOffset++] & 0xFF;
/* 3351 */     switch (tag) {
/*      */       case 0:
/* 3353 */         frame[index] = Opcodes.TOP;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 3384 */         return currentOffset;case 1: frame[index] = Opcodes.INTEGER; return currentOffset;case 2: frame[index] = Opcodes.FLOAT; return currentOffset;case 3: frame[index] = Opcodes.DOUBLE; return currentOffset;case 4: frame[index] = Opcodes.LONG; return currentOffset;case 5: frame[index] = Opcodes.NULL; return currentOffset;case 6: frame[index] = Opcodes.UNINITIALIZED_THIS; return currentOffset;case 7: frame[index] = readClass(currentOffset, charBuffer); currentOffset += 2; return currentOffset;case 8: frame[index] = createLabel(readUnsignedShort(currentOffset), labels); currentOffset += 2; return currentOffset;
/*      */     } 
/*      */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final int getFirstAttributeOffset() {
/* 3401 */     int currentOffset = this.header + 8 + readUnsignedShort(this.header + 6) * 2;
/*      */ 
/*      */     
/* 3404 */     int fieldsCount = readUnsignedShort(currentOffset);
/* 3405 */     currentOffset += 2;
/*      */     
/* 3407 */     while (fieldsCount-- > 0) {
/*      */ 
/*      */ 
/*      */       
/* 3411 */       int attributesCount = readUnsignedShort(currentOffset + 6);
/* 3412 */       currentOffset += 8;
/*      */       
/* 3414 */       while (attributesCount-- > 0)
/*      */       {
/*      */ 
/*      */ 
/*      */         
/* 3419 */         currentOffset += 6 + readInt(currentOffset + 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3424 */     int methodsCount = readUnsignedShort(currentOffset);
/* 3425 */     currentOffset += 2;
/* 3426 */     while (methodsCount-- > 0) {
/* 3427 */       int attributesCount = readUnsignedShort(currentOffset + 6);
/* 3428 */       currentOffset += 8;
/* 3429 */       while (attributesCount-- > 0) {
/* 3430 */         currentOffset += 6 + readInt(currentOffset + 2);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 3435 */     return currentOffset + 2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] readBootstrapMethodsAttribute(int maxStringLength) {
/* 3446 */     char[] charBuffer = new char[maxStringLength];
/* 3447 */     int currentAttributeOffset = getFirstAttributeOffset();
/* 3448 */     int[] currentBootstrapMethodOffsets = null;
/* 3449 */     for (int i = readUnsignedShort(currentAttributeOffset - 2); i > 0; i--) {
/*      */       
/* 3451 */       String attributeName = readUTF8(currentAttributeOffset, charBuffer);
/* 3452 */       int attributeLength = readInt(currentAttributeOffset + 2);
/* 3453 */       currentAttributeOffset += 6;
/* 3454 */       if ("BootstrapMethods".equals(attributeName)) {
/*      */         
/* 3456 */         currentBootstrapMethodOffsets = new int[readUnsignedShort(currentAttributeOffset)];
/*      */         
/* 3458 */         int currentBootstrapMethodOffset = currentAttributeOffset + 2;
/* 3459 */         for (int j = 0; j < currentBootstrapMethodOffsets.length; j++) {
/* 3460 */           currentBootstrapMethodOffsets[j] = currentBootstrapMethodOffset;
/*      */ 
/*      */           
/* 3463 */           currentBootstrapMethodOffset += 4 + 
/* 3464 */             readUnsignedShort(currentBootstrapMethodOffset + 2) * 2;
/*      */         } 
/* 3466 */         return currentBootstrapMethodOffsets;
/*      */       } 
/* 3468 */       currentAttributeOffset += attributeLength;
/*      */     } 
/* 3470 */     throw new IllegalArgumentException();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attribute readAttribute(Attribute[] attributePrototypes, String type, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
/* 3501 */     for (Attribute attributePrototype : attributePrototypes) {
/* 3502 */       if (attributePrototype.type.equals(type)) {
/* 3503 */         return attributePrototype.read(this, offset, length, charBuffer, codeAttributeOffset, labels);
/*      */       }
/*      */     } 
/*      */     
/* 3507 */     return (new Attribute(type)).read(this, offset, length, null, -1, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItemCount() {
/* 3520 */     return this.cpInfoOffsets.length;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getItem(int constantPoolEntryIndex) {
/* 3534 */     return this.cpInfoOffsets[constantPoolEntryIndex];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxStringLength() {
/* 3545 */     return this.maxStringLength;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readByte(int offset) {
/* 3556 */     return this.classFileBuffer[offset] & 0xFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readUnsignedShort(int offset) {
/* 3567 */     byte[] classBuffer = this.classFileBuffer;
/* 3568 */     return (classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short readShort(int offset) {
/* 3579 */     byte[] classBuffer = this.classFileBuffer;
/* 3580 */     return (short)((classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int readInt(int offset) {
/* 3591 */     byte[] classBuffer = this.classFileBuffer;
/* 3592 */     return (classBuffer[offset] & 0xFF) << 24 | (classBuffer[offset + 1] & 0xFF) << 16 | (classBuffer[offset + 2] & 0xFF) << 8 | classBuffer[offset + 3] & 0xFF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long readLong(int offset) {
/* 3606 */     long l1 = readInt(offset);
/* 3607 */     long l0 = readInt(offset + 4) & 0xFFFFFFFFL;
/* 3608 */     return l1 << 32L | l0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readUTF8(int offset, char[] charBuffer) {
/* 3624 */     int constantPoolEntryIndex = readUnsignedShort(offset);
/* 3625 */     if (offset == 0 || constantPoolEntryIndex == 0) {
/* 3626 */       return null;
/*      */     }
/* 3628 */     return readUtf(constantPoolEntryIndex, charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   final String readUtf(int constantPoolEntryIndex, char[] charBuffer) {
/* 3641 */     String value = this.constantUtf8Values[constantPoolEntryIndex];
/* 3642 */     if (value != null) {
/* 3643 */       return value;
/*      */     }
/* 3645 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3646 */     this.constantUtf8Values[constantPoolEntryIndex] = 
/* 3647 */       readUtf(cpInfoOffset + 2, readUnsignedShort(cpInfoOffset), charBuffer); return readUtf(cpInfoOffset + 2, readUnsignedShort(cpInfoOffset), charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String readUtf(int utfOffset, int utfLength, char[] charBuffer) {
/* 3660 */     int currentOffset = utfOffset;
/* 3661 */     int endOffset = currentOffset + utfLength;
/* 3662 */     int strLength = 0;
/* 3663 */     byte[] classBuffer = this.classFileBuffer;
/* 3664 */     while (currentOffset < endOffset) {
/* 3665 */       int currentByte = classBuffer[currentOffset++];
/* 3666 */       if ((currentByte & 0x80) == 0) {
/* 3667 */         charBuffer[strLength++] = (char)(currentByte & 0x7F); continue;
/* 3668 */       }  if ((currentByte & 0xE0) == 192) {
/* 3669 */         charBuffer[strLength++] = (char)(((currentByte & 0x1F) << 6) + (classBuffer[currentOffset++] & 0x3F));
/*      */         continue;
/*      */       } 
/* 3672 */       charBuffer[strLength++] = (char)(((currentByte & 0xF) << 12) + ((classBuffer[currentOffset++] & 0x3F) << 6) + (classBuffer[currentOffset++] & 0x3F));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3679 */     return new String(charBuffer, 0, strLength);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String readStringish(int offset, char[] charBuffer) {
/* 3698 */     return readUTF8(this.cpInfoOffsets[readUnsignedShort(offset)], charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readClass(int offset, char[] charBuffer) {
/* 3713 */     return readStringish(offset, charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readModule(int offset, char[] charBuffer) {
/* 3728 */     return readStringish(offset, charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String readPackage(int offset, char[] charBuffer) {
/* 3743 */     return readStringish(offset, charBuffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ConstantDynamic readConstantDynamic(int constantPoolEntryIndex, char[] charBuffer) {
/* 3757 */     ConstantDynamic constantDynamic = this.constantDynamicValues[constantPoolEntryIndex];
/* 3758 */     if (constantDynamic != null) {
/* 3759 */       return constantDynamic;
/*      */     }
/* 3761 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3762 */     int nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 2)];
/* 3763 */     String name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 3764 */     String descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 3765 */     int bootstrapMethodOffset = this.bootstrapMethodOffsets[readUnsignedShort(cpInfoOffset)];
/* 3766 */     Handle handle = (Handle)readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 3767 */     Object[] bootstrapMethodArguments = new Object[readUnsignedShort(bootstrapMethodOffset + 2)];
/* 3768 */     bootstrapMethodOffset += 4;
/* 3769 */     for (int i = 0; i < bootstrapMethodArguments.length; i++) {
/* 3770 */       bootstrapMethodArguments[i] = readConst(readUnsignedShort(bootstrapMethodOffset), charBuffer);
/* 3771 */       bootstrapMethodOffset += 2;
/*      */     } 
/* 3773 */     this.constantDynamicValues[constantPoolEntryIndex] = new ConstantDynamic(name, descriptor, handle, bootstrapMethodArguments); return new ConstantDynamic(name, descriptor, handle, bootstrapMethodArguments);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object readConst(int constantPoolEntryIndex, char[] charBuffer) {
/*      */     int referenceKind, referenceCpInfoOffset, nameAndTypeCpInfoOffset;
/*      */     String owner, name, descriptor;
/*      */     boolean isInterface;
/* 3792 */     int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
/* 3793 */     switch (this.classFileBuffer[cpInfoOffset - 1]) {
/*      */       case 3:
/* 3795 */         return Integer.valueOf(readInt(cpInfoOffset));
/*      */       case 4:
/* 3797 */         return Float.valueOf(Float.intBitsToFloat(readInt(cpInfoOffset)));
/*      */       case 5:
/* 3799 */         return Long.valueOf(readLong(cpInfoOffset));
/*      */       case 6:
/* 3801 */         return Double.valueOf(Double.longBitsToDouble(readLong(cpInfoOffset)));
/*      */       case 7:
/* 3803 */         return Type.getObjectType(readUTF8(cpInfoOffset, charBuffer));
/*      */       case 8:
/* 3805 */         return readUTF8(cpInfoOffset, charBuffer);
/*      */       case 16:
/* 3807 */         return Type.getMethodType(readUTF8(cpInfoOffset, charBuffer));
/*      */       case 15:
/* 3809 */         referenceKind = readByte(cpInfoOffset);
/* 3810 */         referenceCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(cpInfoOffset + 1)];
/* 3811 */         nameAndTypeCpInfoOffset = this.cpInfoOffsets[readUnsignedShort(referenceCpInfoOffset + 2)];
/* 3812 */         owner = readClass(referenceCpInfoOffset, charBuffer);
/* 3813 */         name = readUTF8(nameAndTypeCpInfoOffset, charBuffer);
/* 3814 */         descriptor = readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
/* 3815 */         isInterface = (this.classFileBuffer[referenceCpInfoOffset - 1] == 11);
/*      */         
/* 3817 */         return new Handle(referenceKind, owner, name, descriptor, isInterface);
/*      */       case 17:
/* 3819 */         return readConstantDynamic(constantPoolEntryIndex, charBuffer);
/*      */     } 
/* 3821 */     throw new IllegalArgumentException();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\ClassReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */