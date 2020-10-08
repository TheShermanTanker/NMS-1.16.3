/*      */ package org.bukkit.craftbukkit.libs.org.objectweb.asm;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ClassWriter
/*      */   extends ClassVisitor
/*      */ {
/*      */   public static final int COMPUTE_MAXS = 1;
/*      */   public static final int COMPUTE_FRAMES = 2;
/*      */   private int version;
/*      */   private final SymbolTable symbolTable;
/*      */   private int accessFlags;
/*      */   private int thisClass;
/*      */   private int superClass;
/*      */   private int interfaceCount;
/*      */   private int[] interfaces;
/*      */   private FieldWriter firstField;
/*      */   private FieldWriter lastField;
/*      */   private MethodWriter firstMethod;
/*      */   private MethodWriter lastMethod;
/*      */   private int numberOfInnerClasses;
/*      */   private ByteVector innerClasses;
/*      */   private int enclosingClassIndex;
/*      */   private int enclosingMethodIndex;
/*      */   private int signatureIndex;
/*      */   private int sourceFileIndex;
/*      */   private ByteVector debugExtension;
/*      */   private AnnotationWriter lastRuntimeVisibleAnnotation;
/*      */   private AnnotationWriter lastRuntimeInvisibleAnnotation;
/*      */   private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
/*      */   private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
/*      */   private ModuleWriter moduleWriter;
/*      */   private int nestHostClassIndex;
/*      */   private int numberOfNestMemberClasses;
/*      */   private ByteVector nestMemberClasses;
/*      */   private int numberOfPermittedSubtypeClasses;
/*      */   private ByteVector permittedSubtypeClasses;
/*      */   private RecordComponentWriter firstRecordComponent;
/*      */   private RecordComponentWriter lastRecordComponent;
/*      */   private Attribute firstAttribute;
/*      */   private int compute;
/*      */   
/*      */   public ClassWriter(int flags) {
/*  229 */     this(null, flags);
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
/*      */   public ClassWriter(ClassReader classReader, int flags) {
/*  257 */     super(524288);
/*  258 */     this.symbolTable = (classReader == null) ? new SymbolTable(this) : new SymbolTable(this, classReader);
/*  259 */     if ((flags & 0x2) != 0) {
/*  260 */       this.compute = 4;
/*  261 */     } else if ((flags & 0x1) != 0) {
/*  262 */       this.compute = 1;
/*      */     } else {
/*  264 */       this.compute = 0;
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
/*      */   public final void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
/*  280 */     this.version = version;
/*  281 */     this.accessFlags = access;
/*  282 */     this.thisClass = this.symbolTable.setMajorVersionAndClassName(version & 0xFFFF, name);
/*  283 */     if (signature != null) {
/*  284 */       this.signatureIndex = this.symbolTable.addConstantUtf8(signature);
/*      */     }
/*  286 */     this.superClass = (superName == null) ? 0 : (this.symbolTable.addConstantClass(superName)).index;
/*  287 */     if (interfaces != null && interfaces.length > 0) {
/*  288 */       this.interfaceCount = interfaces.length;
/*  289 */       this.interfaces = new int[this.interfaceCount];
/*  290 */       for (int i = 0; i < this.interfaceCount; i++) {
/*  291 */         this.interfaces[i] = (this.symbolTable.addConstantClass(interfaces[i])).index;
/*      */       }
/*      */     } 
/*  294 */     if (this.compute == 1 && (version & 0xFFFF) >= 51) {
/*  295 */       this.compute = 2;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitSource(String file, String debug) {
/*  301 */     if (file != null) {
/*  302 */       this.sourceFileIndex = this.symbolTable.addConstantUtf8(file);
/*      */     }
/*  304 */     if (debug != null) {
/*  305 */       this.debugExtension = (new ByteVector()).encodeUtf8(debug, 0, 2147483647);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final ModuleVisitor visitModule(String name, int access, String version) {
/*  312 */     return this
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  317 */       .moduleWriter = new ModuleWriter(this.symbolTable, (this.symbolTable.addConstantModule(name)).index, access, (version == null) ? 0 : this.symbolTable.addConstantUtf8(version));
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitNestHost(String nestHost) {
/*  322 */     this.nestHostClassIndex = (this.symbolTable.addConstantClass(nestHost)).index;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitOuterClass(String owner, String name, String descriptor) {
/*  328 */     this.enclosingClassIndex = (this.symbolTable.addConstantClass(owner)).index;
/*  329 */     if (name != null && descriptor != null) {
/*  330 */       this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(name, descriptor);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/*  336 */     if (visible) {
/*  337 */       return this
/*  338 */         .lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeVisibleAnnotation);
/*      */     }
/*  340 */     return this
/*  341 */       .lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeInvisibleAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/*  348 */     if (visible) {
/*  349 */       return this
/*  350 */         .lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeVisibleTypeAnnotation);
/*      */     }
/*      */     
/*  353 */     return this
/*  354 */       .lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeInvisibleTypeAnnotation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitAttribute(Attribute attribute) {
/*  362 */     attribute.nextAttribute = this.firstAttribute;
/*  363 */     this.firstAttribute = attribute;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void visitNestMember(String nestMember) {
/*  368 */     if (this.nestMemberClasses == null) {
/*  369 */       this.nestMemberClasses = new ByteVector();
/*      */     }
/*  371 */     this.numberOfNestMemberClasses++;
/*  372 */     this.nestMemberClasses.putShort((this.symbolTable.addConstantClass(nestMember)).index);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public final void visitPermittedSubtypeExperimental(String permittedSubtype) {
/*  384 */     if (this.permittedSubtypeClasses == null) {
/*  385 */       this.permittedSubtypeClasses = new ByteVector();
/*      */     }
/*  387 */     this.numberOfPermittedSubtypeClasses++;
/*  388 */     this.permittedSubtypeClasses.putShort((this.symbolTable.addConstantClass(permittedSubtype)).index);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void visitInnerClass(String name, String outerName, String innerName, int access) {
/*  394 */     if (this.innerClasses == null) {
/*  395 */       this.innerClasses = new ByteVector();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  403 */     Symbol nameSymbol = this.symbolTable.addConstantClass(name);
/*  404 */     if (nameSymbol.info == 0) {
/*  405 */       this.numberOfInnerClasses++;
/*  406 */       this.innerClasses.putShort(nameSymbol.index);
/*  407 */       this.innerClasses.putShort((outerName == null) ? 0 : (this.symbolTable.addConstantClass(outerName)).index);
/*  408 */       this.innerClasses.putShort((innerName == null) ? 0 : this.symbolTable.addConstantUtf8(innerName));
/*  409 */       this.innerClasses.putShort(access);
/*  410 */       nameSymbol.info = this.numberOfInnerClasses;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
/*  419 */     RecordComponentWriter recordComponentWriter = new RecordComponentWriter(this.symbolTable, name, descriptor, signature);
/*      */     
/*  421 */     if (this.firstRecordComponent == null) {
/*  422 */       this.firstRecordComponent = recordComponentWriter;
/*      */     } else {
/*  424 */       this.lastRecordComponent.delegate = recordComponentWriter;
/*      */     } 
/*  426 */     return this.lastRecordComponent = recordComponentWriter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
/*  436 */     FieldWriter fieldWriter = new FieldWriter(this.symbolTable, access, name, descriptor, signature, value);
/*      */     
/*  438 */     if (this.firstField == null) {
/*  439 */       this.firstField = fieldWriter;
/*      */     } else {
/*  441 */       this.lastField.fv = fieldWriter;
/*      */     } 
/*  443 */     return this.lastField = fieldWriter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
/*  453 */     MethodWriter methodWriter = new MethodWriter(this.symbolTable, access, name, descriptor, signature, exceptions, this.compute);
/*      */     
/*  455 */     if (this.firstMethod == null) {
/*  456 */       this.firstMethod = methodWriter;
/*      */     } else {
/*  458 */       this.lastMethod.mv = methodWriter;
/*      */     } 
/*  460 */     return this.lastMethod = methodWriter;
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
/*      */   public final void visitEnd() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public byte[] toByteArray() {
/*  484 */     int size = 24 + 2 * this.interfaceCount;
/*  485 */     int fieldsCount = 0;
/*  486 */     FieldWriter fieldWriter = this.firstField;
/*  487 */     while (fieldWriter != null) {
/*  488 */       fieldsCount++;
/*  489 */       size += fieldWriter.computeFieldInfoSize();
/*  490 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  492 */     int methodsCount = 0;
/*  493 */     MethodWriter methodWriter = this.firstMethod;
/*  494 */     while (methodWriter != null) {
/*  495 */       methodsCount++;
/*  496 */       size += methodWriter.computeMethodInfoSize();
/*  497 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*      */ 
/*      */     
/*  501 */     int attributesCount = 0;
/*  502 */     if (this.innerClasses != null) {
/*  503 */       attributesCount++;
/*  504 */       size += 8 + this.innerClasses.length;
/*  505 */       this.symbolTable.addConstantUtf8("InnerClasses");
/*      */     } 
/*  507 */     if (this.enclosingClassIndex != 0) {
/*  508 */       attributesCount++;
/*  509 */       size += 10;
/*  510 */       this.symbolTable.addConstantUtf8("EnclosingMethod");
/*      */     } 
/*  512 */     if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
/*  513 */       attributesCount++;
/*  514 */       size += 6;
/*  515 */       this.symbolTable.addConstantUtf8("Synthetic");
/*      */     } 
/*  517 */     if (this.signatureIndex != 0) {
/*  518 */       attributesCount++;
/*  519 */       size += 8;
/*  520 */       this.symbolTable.addConstantUtf8("Signature");
/*      */     } 
/*  522 */     if (this.sourceFileIndex != 0) {
/*  523 */       attributesCount++;
/*  524 */       size += 8;
/*  525 */       this.symbolTable.addConstantUtf8("SourceFile");
/*      */     } 
/*  527 */     if (this.debugExtension != null) {
/*  528 */       attributesCount++;
/*  529 */       size += 6 + this.debugExtension.length;
/*  530 */       this.symbolTable.addConstantUtf8("SourceDebugExtension");
/*      */     } 
/*  532 */     if ((this.accessFlags & 0x20000) != 0) {
/*  533 */       attributesCount++;
/*  534 */       size += 6;
/*  535 */       this.symbolTable.addConstantUtf8("Deprecated");
/*      */     } 
/*  537 */     if (this.lastRuntimeVisibleAnnotation != null) {
/*  538 */       attributesCount++;
/*  539 */       size += this.lastRuntimeVisibleAnnotation
/*  540 */         .computeAnnotationsSize("RuntimeVisibleAnnotations");
/*      */     } 
/*      */     
/*  543 */     if (this.lastRuntimeInvisibleAnnotation != null) {
/*  544 */       attributesCount++;
/*  545 */       size += this.lastRuntimeInvisibleAnnotation
/*  546 */         .computeAnnotationsSize("RuntimeInvisibleAnnotations");
/*      */     } 
/*      */     
/*  549 */     if (this.lastRuntimeVisibleTypeAnnotation != null) {
/*  550 */       attributesCount++;
/*  551 */       size += this.lastRuntimeVisibleTypeAnnotation
/*  552 */         .computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
/*      */     } 
/*      */     
/*  555 */     if (this.lastRuntimeInvisibleTypeAnnotation != null) {
/*  556 */       attributesCount++;
/*  557 */       size += this.lastRuntimeInvisibleTypeAnnotation
/*  558 */         .computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
/*      */     } 
/*      */     
/*  561 */     if (this.symbolTable.computeBootstrapMethodsSize() > 0) {
/*  562 */       attributesCount++;
/*  563 */       size += this.symbolTable.computeBootstrapMethodsSize();
/*      */     } 
/*  565 */     if (this.moduleWriter != null) {
/*  566 */       attributesCount += this.moduleWriter.getAttributeCount();
/*  567 */       size += this.moduleWriter.computeAttributesSize();
/*      */     } 
/*  569 */     if (this.nestHostClassIndex != 0) {
/*  570 */       attributesCount++;
/*  571 */       size += 8;
/*  572 */       this.symbolTable.addConstantUtf8("NestHost");
/*      */     } 
/*  574 */     if (this.nestMemberClasses != null) {
/*  575 */       attributesCount++;
/*  576 */       size += 8 + this.nestMemberClasses.length;
/*  577 */       this.symbolTable.addConstantUtf8("NestMembers");
/*      */     } 
/*  579 */     if (this.permittedSubtypeClasses != null) {
/*  580 */       attributesCount++;
/*  581 */       size += 8 + this.permittedSubtypeClasses.length;
/*  582 */       this.symbolTable.addConstantUtf8("PermittedSubtypes");
/*      */     } 
/*  584 */     int recordComponentCount = 0;
/*  585 */     int recordSize = 0;
/*  586 */     if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
/*  587 */       RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  588 */       while (recordComponentWriter != null) {
/*  589 */         recordComponentCount++;
/*  590 */         recordSize += recordComponentWriter.computeRecordComponentInfoSize();
/*  591 */         recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */       } 
/*  593 */       attributesCount++;
/*  594 */       size += 8 + recordSize;
/*  595 */       this.symbolTable.addConstantUtf8("Record");
/*      */     } 
/*  597 */     if (this.firstAttribute != null) {
/*  598 */       attributesCount += this.firstAttribute.getAttributeCount();
/*  599 */       size += this.firstAttribute.computeAttributesSize(this.symbolTable);
/*      */     } 
/*      */ 
/*      */     
/*  603 */     size += this.symbolTable.getConstantPoolLength();
/*  604 */     int constantPoolCount = this.symbolTable.getConstantPoolCount();
/*  605 */     if (constantPoolCount > 65535) {
/*  606 */       throw new ClassTooLargeException(this.symbolTable.getClassName(), constantPoolCount);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  611 */     ByteVector result = new ByteVector(size);
/*  612 */     result.putInt(-889275714).putInt(this.version);
/*  613 */     this.symbolTable.putConstantPool(result);
/*  614 */     int mask = ((this.version & 0xFFFF) < 49) ? 4096 : 0;
/*  615 */     result.putShort(this.accessFlags & (mask ^ 0xFFFFFFFF)).putShort(this.thisClass).putShort(this.superClass);
/*  616 */     result.putShort(this.interfaceCount);
/*  617 */     for (int i = 0; i < this.interfaceCount; i++) {
/*  618 */       result.putShort(this.interfaces[i]);
/*      */     }
/*  620 */     result.putShort(fieldsCount);
/*  621 */     fieldWriter = this.firstField;
/*  622 */     while (fieldWriter != null) {
/*  623 */       fieldWriter.putFieldInfo(result);
/*  624 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  626 */     result.putShort(methodsCount);
/*  627 */     boolean hasFrames = false;
/*  628 */     boolean hasAsmInstructions = false;
/*  629 */     methodWriter = this.firstMethod;
/*  630 */     while (methodWriter != null) {
/*  631 */       hasFrames |= methodWriter.hasFrames();
/*  632 */       hasAsmInstructions |= methodWriter.hasAsmInstructions();
/*  633 */       methodWriter.putMethodInfo(result);
/*  634 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*      */     
/*  637 */     result.putShort(attributesCount);
/*  638 */     if (this.innerClasses != null) {
/*  639 */       result
/*  640 */         .putShort(this.symbolTable.addConstantUtf8("InnerClasses"))
/*  641 */         .putInt(this.innerClasses.length + 2)
/*  642 */         .putShort(this.numberOfInnerClasses)
/*  643 */         .putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
/*      */     }
/*  645 */     if (this.enclosingClassIndex != 0) {
/*  646 */       result
/*  647 */         .putShort(this.symbolTable.addConstantUtf8("EnclosingMethod"))
/*  648 */         .putInt(4)
/*  649 */         .putShort(this.enclosingClassIndex)
/*  650 */         .putShort(this.enclosingMethodIndex);
/*      */     }
/*  652 */     if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
/*  653 */       result.putShort(this.symbolTable.addConstantUtf8("Synthetic")).putInt(0);
/*      */     }
/*  655 */     if (this.signatureIndex != 0) {
/*  656 */       result
/*  657 */         .putShort(this.symbolTable.addConstantUtf8("Signature"))
/*  658 */         .putInt(2)
/*  659 */         .putShort(this.signatureIndex);
/*      */     }
/*  661 */     if (this.sourceFileIndex != 0) {
/*  662 */       result
/*  663 */         .putShort(this.symbolTable.addConstantUtf8("SourceFile"))
/*  664 */         .putInt(2)
/*  665 */         .putShort(this.sourceFileIndex);
/*      */     }
/*  667 */     if (this.debugExtension != null) {
/*  668 */       int length = this.debugExtension.length;
/*  669 */       result
/*  670 */         .putShort(this.symbolTable.addConstantUtf8("SourceDebugExtension"))
/*  671 */         .putInt(length)
/*  672 */         .putByteArray(this.debugExtension.data, 0, length);
/*      */     } 
/*  674 */     if ((this.accessFlags & 0x20000) != 0) {
/*  675 */       result.putShort(this.symbolTable.addConstantUtf8("Deprecated")).putInt(0);
/*      */     }
/*  677 */     AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, result);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  684 */     this.symbolTable.putBootstrapMethods(result);
/*  685 */     if (this.moduleWriter != null) {
/*  686 */       this.moduleWriter.putAttributes(result);
/*      */     }
/*  688 */     if (this.nestHostClassIndex != 0) {
/*  689 */       result
/*  690 */         .putShort(this.symbolTable.addConstantUtf8("NestHost"))
/*  691 */         .putInt(2)
/*  692 */         .putShort(this.nestHostClassIndex);
/*      */     }
/*  694 */     if (this.nestMemberClasses != null) {
/*  695 */       result
/*  696 */         .putShort(this.symbolTable.addConstantUtf8("NestMembers"))
/*  697 */         .putInt(this.nestMemberClasses.length + 2)
/*  698 */         .putShort(this.numberOfNestMemberClasses)
/*  699 */         .putByteArray(this.nestMemberClasses.data, 0, this.nestMemberClasses.length);
/*      */     }
/*  701 */     if (this.permittedSubtypeClasses != null) {
/*  702 */       result
/*  703 */         .putShort(this.symbolTable.addConstantUtf8("PermittedSubtypes"))
/*  704 */         .putInt(this.permittedSubtypeClasses.length + 2)
/*  705 */         .putShort(this.numberOfPermittedSubtypeClasses)
/*  706 */         .putByteArray(this.permittedSubtypeClasses.data, 0, this.permittedSubtypeClasses.length);
/*      */     }
/*  708 */     if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
/*  709 */       result
/*  710 */         .putShort(this.symbolTable.addConstantUtf8("Record"))
/*  711 */         .putInt(recordSize + 2)
/*  712 */         .putShort(recordComponentCount);
/*  713 */       RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  714 */       while (recordComponentWriter != null) {
/*  715 */         recordComponentWriter.putRecordComponentInfo(result);
/*  716 */         recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */       } 
/*      */     } 
/*  719 */     if (this.firstAttribute != null) {
/*  720 */       this.firstAttribute.putAttributes(this.symbolTable, result);
/*      */     }
/*      */ 
/*      */     
/*  724 */     if (hasAsmInstructions) {
/*  725 */       return replaceAsmInstructions(result.data, hasFrames);
/*      */     }
/*  727 */     return result.data;
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
/*      */   private byte[] replaceAsmInstructions(byte[] classFile, boolean hasFrames) {
/*  742 */     Attribute[] attributes = getAttributePrototypes();
/*  743 */     this.firstField = null;
/*  744 */     this.lastField = null;
/*  745 */     this.firstMethod = null;
/*  746 */     this.lastMethod = null;
/*  747 */     this.lastRuntimeVisibleAnnotation = null;
/*  748 */     this.lastRuntimeInvisibleAnnotation = null;
/*  749 */     this.lastRuntimeVisibleTypeAnnotation = null;
/*  750 */     this.lastRuntimeInvisibleTypeAnnotation = null;
/*  751 */     this.moduleWriter = null;
/*  752 */     this.nestHostClassIndex = 0;
/*  753 */     this.numberOfNestMemberClasses = 0;
/*  754 */     this.nestMemberClasses = null;
/*  755 */     this.numberOfPermittedSubtypeClasses = 0;
/*  756 */     this.permittedSubtypeClasses = null;
/*  757 */     this.firstRecordComponent = null;
/*  758 */     this.lastRecordComponent = null;
/*  759 */     this.firstAttribute = null;
/*  760 */     this.compute = hasFrames ? 3 : 0;
/*  761 */     (new ClassReader(classFile, 0, false))
/*  762 */       .accept(this, attributes, (hasFrames ? 8 : 0) | 0x100);
/*      */ 
/*      */ 
/*      */     
/*  766 */     return toByteArray();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Attribute[] getAttributePrototypes() {
/*  775 */     Attribute.Set attributePrototypes = new Attribute.Set();
/*  776 */     attributePrototypes.addAttributes(this.firstAttribute);
/*  777 */     FieldWriter fieldWriter = this.firstField;
/*  778 */     while (fieldWriter != null) {
/*  779 */       fieldWriter.collectAttributePrototypes(attributePrototypes);
/*  780 */       fieldWriter = (FieldWriter)fieldWriter.fv;
/*      */     } 
/*  782 */     MethodWriter methodWriter = this.firstMethod;
/*  783 */     while (methodWriter != null) {
/*  784 */       methodWriter.collectAttributePrototypes(attributePrototypes);
/*  785 */       methodWriter = (MethodWriter)methodWriter.mv;
/*      */     } 
/*  787 */     RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
/*  788 */     while (recordComponentWriter != null) {
/*  789 */       recordComponentWriter.collectAttributePrototypes(attributePrototypes);
/*  790 */       recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
/*      */     } 
/*  792 */     return attributePrototypes.toArray();
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
/*      */   public int newConst(Object value) {
/*  809 */     return (this.symbolTable.addConstant(value)).index;
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
/*      */   public int newUTF8(String value) {
/*  822 */     return this.symbolTable.addConstantUtf8(value);
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
/*      */   public int newClass(String value) {
/*  834 */     return (this.symbolTable.addConstantClass(value)).index;
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
/*      */   public int newMethodType(String methodDescriptor) {
/*  846 */     return (this.symbolTable.addConstantMethodType(methodDescriptor)).index;
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
/*      */   public int newModule(String moduleName) {
/*  858 */     return (this.symbolTable.addConstantModule(moduleName)).index;
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
/*      */   public int newPackage(String packageName) {
/*  870 */     return (this.symbolTable.addConstantPackage(packageName)).index;
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
/*      */   @Deprecated
/*      */   public int newHandle(int tag, String owner, String name, String descriptor) {
/*  892 */     return newHandle(tag, owner, name, descriptor, (tag == 9));
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
/*      */   public int newHandle(int tag, String owner, String name, String descriptor, boolean isInterface) {
/*  916 */     return (this.symbolTable.addConstantMethodHandle(tag, owner, name, descriptor, isInterface)).index;
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
/*      */   public int newConstantDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  935 */     return (this.symbolTable.addConstantDynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)).index;
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
/*      */   public int newInvokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/*  956 */     return (this.symbolTable.addConstantInvokeDynamic(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)).index;
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
/*      */   public int newField(String owner, String name, String descriptor) {
/*  972 */     return (this.symbolTable.addConstantFieldref(owner, name, descriptor)).index;
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
/*      */   public int newMethod(String owner, String name, String descriptor, boolean isInterface) {
/*  988 */     return (this.symbolTable.addConstantMethodref(owner, name, descriptor, isInterface)).index;
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
/*      */   public int newNameType(String name, String descriptor) {
/* 1001 */     return this.symbolTable.addConstantNameAndType(name, descriptor);
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
/*      */   protected String getCommonSuperClass(String type1, String type2) {
/*      */     Class<?> class1, class2;
/* 1021 */     ClassLoader classLoader = getClassLoader();
/*      */     
/*      */     try {
/* 1024 */       class1 = Class.forName(type1.replace('/', '.'), false, classLoader);
/* 1025 */     } catch (ClassNotFoundException e) {
/* 1026 */       throw new TypeNotPresentException(type1, e);
/*      */     } 
/*      */     
/*      */     try {
/* 1030 */       class2 = Class.forName(type2.replace('/', '.'), false, classLoader);
/* 1031 */     } catch (ClassNotFoundException e) {
/* 1032 */       throw new TypeNotPresentException(type2, e);
/*      */     } 
/* 1034 */     if (class1.isAssignableFrom(class2)) {
/* 1035 */       return type1;
/*      */     }
/* 1037 */     if (class2.isAssignableFrom(class1)) {
/* 1038 */       return type2;
/*      */     }
/* 1040 */     if (class1.isInterface() || class2.isInterface()) {
/* 1041 */       return "java/lang/Object";
/*      */     }
/*      */     while (true) {
/* 1044 */       class1 = class1.getSuperclass();
/* 1045 */       if (class1.isAssignableFrom(class2)) {
/* 1046 */         return class1.getName().replace('.', '/');
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
/*      */   protected ClassLoader getClassLoader() {
/* 1058 */     return getClass().getClassLoader();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\ClassWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */