/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.AnnotationVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Attribute;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.ClassVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Handle;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.TypePath;
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
/*     */ public class MethodNode
/*     */   extends MethodVisitor
/*     */ {
/*     */   public int access;
/*     */   public String name;
/*     */   public String desc;
/*     */   public String signature;
/*     */   public List<String> exceptions;
/*     */   public List<ParameterNode> parameters;
/*     */   public List<AnnotationNode> visibleAnnotations;
/*     */   public List<AnnotationNode> invisibleAnnotations;
/*     */   public List<TypeAnnotationNode> visibleTypeAnnotations;
/*     */   public List<TypeAnnotationNode> invisibleTypeAnnotations;
/*     */   public List<Attribute> attrs;
/*     */   public Object annotationDefault;
/*     */   public int visibleAnnotableParameterCount;
/*     */   public List<AnnotationNode>[] visibleParameterAnnotations;
/*     */   public int invisibleAnnotableParameterCount;
/*     */   public List<AnnotationNode>[] invisibleParameterAnnotations;
/*     */   public InsnList instructions;
/*     */   public List<TryCatchBlockNode> tryCatchBlocks;
/*     */   public int maxStack;
/*     */   public int maxLocals;
/*     */   public List<LocalVariableNode> localVariables;
/*     */   public List<LocalVariableAnnotationNode> visibleLocalVariableAnnotations;
/*     */   public List<LocalVariableAnnotationNode> invisibleLocalVariableAnnotations;
/*     */   private boolean visited;
/*     */   
/*     */   public MethodNode() {
/* 158 */     this(524288);
/* 159 */     if (getClass() != MethodNode.class) {
/* 160 */       throw new IllegalStateException();
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
/*     */   public MethodNode(int api) {
/* 172 */     super(api);
/* 173 */     this.instructions = new InsnList();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodNode(int access, String name, String descriptor, String signature, String[] exceptions) {
/* 195 */     this(524288, access, name, descriptor, signature, exceptions);
/* 196 */     if (getClass() != MethodNode.class) {
/* 197 */       throw new IllegalStateException();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodNode(int api, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 222 */     super(api);
/* 223 */     this.access = access;
/* 224 */     this.name = name;
/* 225 */     this.desc = descriptor;
/* 226 */     this.signature = signature;
/* 227 */     this.exceptions = Util.asArrayList(exceptions);
/* 228 */     if ((access & 0x400) == 0) {
/* 229 */       this.localVariables = new ArrayList<LocalVariableNode>(5);
/*     */     }
/* 231 */     this.tryCatchBlocks = new ArrayList<TryCatchBlockNode>();
/* 232 */     this.instructions = new InsnList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitParameter(String name, int access) {
/* 241 */     if (this.parameters == null) {
/* 242 */       this.parameters = new ArrayList<ParameterNode>(5);
/*     */     }
/* 244 */     this.parameters.add(new ParameterNode(name, access));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotationDefault() {
/* 250 */     return new AnnotationNode(new ArrayList(0)
/*     */         {
/*     */           public boolean add(Object o)
/*     */           {
/* 254 */             MethodNode.this.annotationDefault = o;
/* 255 */             return super.add(o);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
/* 262 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 263 */     if (visible) {
/* 264 */       this.visibleAnnotations = Util.add(this.visibleAnnotations, annotation);
/*     */     } else {
/* 266 */       this.invisibleAnnotations = Util.add(this.invisibleAnnotations, annotation);
/*     */     } 
/* 268 */     return annotation;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 274 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 275 */     if (visible) {
/* 276 */       this.visibleTypeAnnotations = Util.add(this.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 278 */       this.invisibleTypeAnnotations = Util.add(this.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 280 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAnnotableParameterCount(int parameterCount, boolean visible) {
/* 285 */     if (visible) {
/* 286 */       this.visibleAnnotableParameterCount = parameterCount;
/*     */     } else {
/* 288 */       this.invisibleAnnotableParameterCount = parameterCount;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitParameterAnnotation(int parameter, String descriptor, boolean visible) {
/* 296 */     AnnotationNode annotation = new AnnotationNode(descriptor);
/* 297 */     if (visible) {
/* 298 */       if (this.visibleParameterAnnotations == null) {
/* 299 */         int params = (Type.getArgumentTypes(this.desc)).length;
/* 300 */         this.visibleParameterAnnotations = (List<AnnotationNode>[])new List[params];
/*     */       } 
/* 302 */       this.visibleParameterAnnotations[parameter] = 
/* 303 */         Util.add(this.visibleParameterAnnotations[parameter], annotation);
/*     */     } else {
/* 305 */       if (this.invisibleParameterAnnotations == null) {
/* 306 */         int params = (Type.getArgumentTypes(this.desc)).length;
/* 307 */         this.invisibleParameterAnnotations = (List<AnnotationNode>[])new List[params];
/*     */       } 
/* 309 */       this.invisibleParameterAnnotations[parameter] = 
/* 310 */         Util.add(this.invisibleParameterAnnotations[parameter], annotation);
/*     */     } 
/* 312 */     return annotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitAttribute(Attribute attribute) {
/* 317 */     this.attrs = Util.add(this.attrs, attribute);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitCode() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFrame(int type, int numLocal, Object[] local, int numStack, Object[] stack) {
/* 332 */     this.instructions.add(new FrameNode(type, numLocal, (local == null) ? null : 
/*     */ 
/*     */ 
/*     */           
/* 336 */           getLabelNodes(local), numStack, (stack == null) ? null : 
/*     */           
/* 338 */           getLabelNodes(stack)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitInsn(int opcode) {
/* 343 */     this.instructions.add(new InsnNode(opcode));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIntInsn(int opcode, int operand) {
/* 348 */     this.instructions.add(new IntInsnNode(opcode, operand));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitVarInsn(int opcode, int var) {
/* 353 */     this.instructions.add(new VarInsnNode(opcode, var));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitTypeInsn(int opcode, String type) {
/* 358 */     this.instructions.add(new TypeInsnNode(opcode, type));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
/* 364 */     this.instructions.add(new FieldInsnNode(opcode, owner, name, descriptor));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitMethodInsn(int opcodeAndSource, String owner, String name, String descriptor, boolean isInterface) {
/* 374 */     if (this.api < 327680 && (opcodeAndSource & 0x100) == 0) {
/*     */       
/* 376 */       super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
/*     */       return;
/*     */     } 
/* 379 */     int opcode = opcodeAndSource & 0xFFFFFEFF;
/*     */     
/* 381 */     this.instructions.add(new MethodInsnNode(opcode, owner, name, descriptor, isInterface));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
/* 390 */     this.instructions.add(new InvokeDynamicInsnNode(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 397 */     this.instructions.add(new JumpInsnNode(opcode, getLabelNode(label)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLabel(Label label) {
/* 402 */     this.instructions.add(getLabelNode(label));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLdcInsn(Object value) {
/* 407 */     this.instructions.add(new LdcInsnNode(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitIincInsn(int var, int increment) {
/* 412 */     this.instructions.add(new IincInsnNode(var, increment));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTableSwitchInsn(int min, int max, Label dflt, Label... labels) {
/* 418 */     this.instructions.add(new TableSwitchInsnNode(min, max, getLabelNode(dflt), getLabelNodes(labels)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
/* 423 */     this.instructions.add(new LookupSwitchInsnNode(getLabelNode(dflt), keys, getLabelNodes(labels)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMultiANewArrayInsn(String descriptor, int numDimensions) {
/* 428 */     this.instructions.add(new MultiANewArrayInsnNode(descriptor, numDimensions));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitInsnAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 435 */     AbstractInsnNode currentInsn = this.instructions.getLast();
/* 436 */     while (currentInsn.getOpcode() == -1) {
/* 437 */       currentInsn = currentInsn.getPrevious();
/*     */     }
/*     */     
/* 440 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 441 */     if (visible) {
/* 442 */       currentInsn
/* 443 */         .visibleTypeAnnotations = Util.add(currentInsn.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 445 */       currentInsn
/* 446 */         .invisibleTypeAnnotations = Util.add(currentInsn.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 448 */     return typeAnnotation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
/* 455 */     TryCatchBlockNode tryCatchBlock = new TryCatchBlockNode(getLabelNode(start), getLabelNode(end), getLabelNode(handler), type);
/* 456 */     this.tryCatchBlocks = Util.add(this.tryCatchBlocks, tryCatchBlock);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationVisitor visitTryCatchAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
/* 462 */     TryCatchBlockNode tryCatchBlock = this.tryCatchBlocks.get((typeRef & 0xFFFF00) >> 8);
/* 463 */     TypeAnnotationNode typeAnnotation = new TypeAnnotationNode(typeRef, typePath, descriptor);
/* 464 */     if (visible) {
/* 465 */       tryCatchBlock
/* 466 */         .visibleTypeAnnotations = Util.add(tryCatchBlock.visibleTypeAnnotations, typeAnnotation);
/*     */     } else {
/* 468 */       tryCatchBlock
/* 469 */         .invisibleTypeAnnotations = Util.add(tryCatchBlock.invisibleTypeAnnotations, typeAnnotation);
/*     */     } 
/* 471 */     return typeAnnotation;
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
/*     */   public void visitLocalVariable(String name, String descriptor, String signature, Label start, Label end, int index) {
/* 484 */     LocalVariableNode localVariable = new LocalVariableNode(name, descriptor, signature, getLabelNode(start), getLabelNode(end), index);
/* 485 */     this.localVariables = Util.add(this.localVariables, localVariable);
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
/*     */   public AnnotationVisitor visitLocalVariableAnnotation(int typeRef, TypePath typePath, Label[] start, Label[] end, int[] index, String descriptor, boolean visible) {
/* 499 */     LocalVariableAnnotationNode localVariableAnnotation = new LocalVariableAnnotationNode(typeRef, typePath, getLabelNodes(start), getLabelNodes(end), index, descriptor);
/* 500 */     if (visible) {
/* 501 */       this
/* 502 */         .visibleLocalVariableAnnotations = Util.add(this.visibleLocalVariableAnnotations, localVariableAnnotation);
/*     */     } else {
/* 504 */       this
/* 505 */         .invisibleLocalVariableAnnotations = Util.add(this.invisibleLocalVariableAnnotations, localVariableAnnotation);
/*     */     } 
/* 507 */     return localVariableAnnotation;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitLineNumber(int line, Label start) {
/* 512 */     this.instructions.add(new LineNumberNode(line, getLabelNode(start)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitMaxs(int maxStack, int maxLocals) {
/* 517 */     this.maxStack = maxStack;
/* 518 */     this.maxLocals = maxLocals;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void visitEnd() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LabelNode getLabelNode(Label label) {
/* 535 */     if (!(label.info instanceof LabelNode)) {
/* 536 */       label.info = new LabelNode();
/*     */     }
/* 538 */     return (LabelNode)label.info;
/*     */   }
/*     */   
/*     */   private LabelNode[] getLabelNodes(Label[] labels) {
/* 542 */     LabelNode[] labelNodes = new LabelNode[labels.length];
/* 543 */     for (int i = 0, n = labels.length; i < n; i++) {
/* 544 */       labelNodes[i] = getLabelNode(labels[i]);
/*     */     }
/* 546 */     return labelNodes;
/*     */   }
/*     */   
/*     */   private Object[] getLabelNodes(Object[] objects) {
/* 550 */     Object[] labelNodes = new Object[objects.length];
/* 551 */     for (int i = 0, n = objects.length; i < n; i++) {
/* 552 */       Object o = objects[i];
/* 553 */       if (o instanceof Label) {
/* 554 */         o = getLabelNode((Label)o);
/*     */       }
/* 556 */       labelNodes[i] = o;
/*     */     } 
/* 558 */     return labelNodes;
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
/*     */   public void check(int api) {
/* 574 */     if (api == 262144) {
/* 575 */       if (this.parameters != null && !this.parameters.isEmpty()) {
/* 576 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 578 */       if (this.visibleTypeAnnotations != null && !this.visibleTypeAnnotations.isEmpty()) {
/* 579 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 581 */       if (this.invisibleTypeAnnotations != null && !this.invisibleTypeAnnotations.isEmpty()) {
/* 582 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 584 */       if (this.tryCatchBlocks != null) {
/* 585 */         for (int j = this.tryCatchBlocks.size() - 1; j >= 0; j--) {
/* 586 */           TryCatchBlockNode tryCatchBlock = this.tryCatchBlocks.get(j);
/* 587 */           if (tryCatchBlock.visibleTypeAnnotations != null && 
/* 588 */             !tryCatchBlock.visibleTypeAnnotations.isEmpty()) {
/* 589 */             throw new UnsupportedClassVersionException();
/*     */           }
/* 591 */           if (tryCatchBlock.invisibleTypeAnnotations != null && 
/* 592 */             !tryCatchBlock.invisibleTypeAnnotations.isEmpty()) {
/* 593 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       }
/* 597 */       for (int i = this.instructions.size() - 1; i >= 0; i--) {
/* 598 */         AbstractInsnNode insn = this.instructions.get(i);
/* 599 */         if (insn.visibleTypeAnnotations != null && !insn.visibleTypeAnnotations.isEmpty()) {
/* 600 */           throw new UnsupportedClassVersionException();
/*     */         }
/* 602 */         if (insn.invisibleTypeAnnotations != null && !insn.invisibleTypeAnnotations.isEmpty()) {
/* 603 */           throw new UnsupportedClassVersionException();
/*     */         }
/* 605 */         if (insn instanceof MethodInsnNode) {
/* 606 */           boolean isInterface = ((MethodInsnNode)insn).itf;
/* 607 */           if (isInterface != ((insn.opcode == 185))) {
/* 608 */             throw new UnsupportedClassVersionException();
/*     */           }
/* 610 */         } else if (insn instanceof LdcInsnNode) {
/* 611 */           Object value = ((LdcInsnNode)insn).cst;
/* 612 */           if (value instanceof Handle || (value instanceof Type && ((Type)value)
/* 613 */             .getSort() == 11)) {
/* 614 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       } 
/* 618 */       if (this.visibleLocalVariableAnnotations != null && !this.visibleLocalVariableAnnotations.isEmpty()) {
/* 619 */         throw new UnsupportedClassVersionException();
/*     */       }
/* 621 */       if (this.invisibleLocalVariableAnnotations != null && 
/* 622 */         !this.invisibleLocalVariableAnnotations.isEmpty()) {
/* 623 */         throw new UnsupportedClassVersionException();
/*     */       }
/*     */     } 
/* 626 */     if (api < 458752) {
/* 627 */       for (int i = this.instructions.size() - 1; i >= 0; i--) {
/* 628 */         AbstractInsnNode insn = this.instructions.get(i);
/* 629 */         if (insn instanceof LdcInsnNode) {
/* 630 */           Object value = ((LdcInsnNode)insn).cst;
/* 631 */           if (value instanceof org.bukkit.craftbukkit.libs.org.objectweb.asm.ConstantDynamic) {
/* 632 */             throw new UnsupportedClassVersionException();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(ClassVisitor classVisitor) {
/* 645 */     String[] exceptionsArray = (this.exceptions == null) ? null : this.exceptions.<String>toArray(new String[0]);
/*     */     
/* 647 */     MethodVisitor methodVisitor = classVisitor.visitMethod(this.access, this.name, this.desc, this.signature, exceptionsArray);
/* 648 */     if (methodVisitor != null) {
/* 649 */       accept(methodVisitor);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void accept(MethodVisitor methodVisitor) {
/* 660 */     if (this.parameters != null) {
/* 661 */       for (int i = 0, n = this.parameters.size(); i < n; i++) {
/* 662 */         ((ParameterNode)this.parameters.get(i)).accept(methodVisitor);
/*     */       }
/*     */     }
/*     */     
/* 666 */     if (this.annotationDefault != null) {
/* 667 */       AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
/* 668 */       AnnotationNode.accept(annotationVisitor, null, this.annotationDefault);
/* 669 */       if (annotationVisitor != null) {
/* 670 */         annotationVisitor.visitEnd();
/*     */       }
/*     */     } 
/* 673 */     if (this.visibleAnnotations != null) {
/* 674 */       for (int i = 0, n = this.visibleAnnotations.size(); i < n; i++) {
/* 675 */         AnnotationNode annotation = this.visibleAnnotations.get(i);
/* 676 */         annotation.accept(methodVisitor.visitAnnotation(annotation.desc, true));
/*     */       } 
/*     */     }
/* 679 */     if (this.invisibleAnnotations != null) {
/* 680 */       for (int i = 0, n = this.invisibleAnnotations.size(); i < n; i++) {
/* 681 */         AnnotationNode annotation = this.invisibleAnnotations.get(i);
/* 682 */         annotation.accept(methodVisitor.visitAnnotation(annotation.desc, false));
/*     */       } 
/*     */     }
/* 685 */     if (this.visibleTypeAnnotations != null) {
/* 686 */       for (int i = 0, n = this.visibleTypeAnnotations.size(); i < n; i++) {
/* 687 */         TypeAnnotationNode typeAnnotation = this.visibleTypeAnnotations.get(i);
/* 688 */         typeAnnotation.accept(methodVisitor
/* 689 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, true));
/*     */       } 
/*     */     }
/*     */     
/* 693 */     if (this.invisibleTypeAnnotations != null) {
/* 694 */       for (int i = 0, n = this.invisibleTypeAnnotations.size(); i < n; i++) {
/* 695 */         TypeAnnotationNode typeAnnotation = this.invisibleTypeAnnotations.get(i);
/* 696 */         typeAnnotation.accept(methodVisitor
/* 697 */             .visitTypeAnnotation(typeAnnotation.typeRef, typeAnnotation.typePath, typeAnnotation.desc, false));
/*     */       } 
/*     */     }
/*     */     
/* 701 */     if (this.visibleAnnotableParameterCount > 0) {
/* 702 */       methodVisitor.visitAnnotableParameterCount(this.visibleAnnotableParameterCount, true);
/*     */     }
/* 704 */     if (this.visibleParameterAnnotations != null)
/* 705 */       for (int i = 0, n = this.visibleParameterAnnotations.length; i < n; i++) {
/* 706 */         List<AnnotationNode> parameterAnnotations = this.visibleParameterAnnotations[i];
/* 707 */         if (parameterAnnotations != null)
/*     */         {
/*     */           
/* 710 */           for (int j = 0, m = parameterAnnotations.size(); j < m; j++) {
/* 711 */             AnnotationNode annotation = parameterAnnotations.get(j);
/* 712 */             annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.desc, true));
/*     */           } 
/*     */         }
/*     */       }  
/* 716 */     if (this.invisibleAnnotableParameterCount > 0) {
/* 717 */       methodVisitor.visitAnnotableParameterCount(this.invisibleAnnotableParameterCount, false);
/*     */     }
/* 719 */     if (this.invisibleParameterAnnotations != null) {
/* 720 */       for (int i = 0, n = this.invisibleParameterAnnotations.length; i < n; i++) {
/* 721 */         List<AnnotationNode> parameterAnnotations = this.invisibleParameterAnnotations[i];
/* 722 */         if (parameterAnnotations != null)
/*     */         {
/*     */           
/* 725 */           for (int j = 0, m = parameterAnnotations.size(); j < m; j++) {
/* 726 */             AnnotationNode annotation = parameterAnnotations.get(j);
/* 727 */             annotation.accept(methodVisitor.visitParameterAnnotation(i, annotation.desc, false));
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/* 732 */     if (this.visited) {
/* 733 */       this.instructions.resetLabels();
/*     */     }
/* 735 */     if (this.attrs != null) {
/* 736 */       for (int i = 0, n = this.attrs.size(); i < n; i++) {
/* 737 */         methodVisitor.visitAttribute(this.attrs.get(i));
/*     */       }
/*     */     }
/*     */     
/* 741 */     if (this.instructions.size() > 0) {
/* 742 */       methodVisitor.visitCode();
/*     */       
/* 744 */       if (this.tryCatchBlocks != null) {
/* 745 */         for (int i = 0, n = this.tryCatchBlocks.size(); i < n; i++) {
/* 746 */           ((TryCatchBlockNode)this.tryCatchBlocks.get(i)).updateIndex(i);
/* 747 */           ((TryCatchBlockNode)this.tryCatchBlocks.get(i)).accept(methodVisitor);
/*     */         } 
/*     */       }
/*     */       
/* 751 */       this.instructions.accept(methodVisitor);
/*     */       
/* 753 */       if (this.localVariables != null) {
/* 754 */         for (int i = 0, n = this.localVariables.size(); i < n; i++) {
/* 755 */           ((LocalVariableNode)this.localVariables.get(i)).accept(methodVisitor);
/*     */         }
/*     */       }
/*     */       
/* 759 */       if (this.visibleLocalVariableAnnotations != null) {
/* 760 */         for (int i = 0, n = this.visibleLocalVariableAnnotations.size(); i < n; i++) {
/* 761 */           ((LocalVariableAnnotationNode)this.visibleLocalVariableAnnotations.get(i)).accept(methodVisitor, true);
/*     */         }
/*     */       }
/* 764 */       if (this.invisibleLocalVariableAnnotations != null) {
/* 765 */         for (int i = 0, n = this.invisibleLocalVariableAnnotations.size(); i < n; i++) {
/* 766 */           ((LocalVariableAnnotationNode)this.invisibleLocalVariableAnnotations.get(i)).accept(methodVisitor, false);
/*     */         }
/*     */       }
/* 769 */       methodVisitor.visitMaxs(this.maxStack, this.maxLocals);
/* 770 */       this.visited = true;
/*     */     } 
/* 772 */     methodVisitor.visitEnd();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\MethodNode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */