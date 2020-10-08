/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.commons;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Label;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.MethodVisitor;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InsnList;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.JumpInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LabelNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LocalVariableNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LookupSwitchInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TableSwitchInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TryCatchBlockNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSRInlinerAdapter
/*     */   extends MethodNode
/*     */   implements Opcodes
/*     */ {
/*  65 */   private final BitSet mainSubroutineInsns = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   private final Map<LabelNode, BitSet> subroutinesInsns = new HashMap<LabelNode, BitSet>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   final BitSet sharedSubroutineInsns = new BitSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSRInlinerAdapter(MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 101 */     this(524288, methodVisitor, access, name, descriptor, signature, exceptions);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (getClass() != JSRInlinerAdapter.class) {
/* 110 */       throw new IllegalStateException();
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
/*     */ 
/*     */   
/*     */   protected JSRInlinerAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String signature, String[] exceptions) {
/* 137 */     super(api, access, name, descriptor, signature, exceptions);
/* 138 */     this.mv = methodVisitor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitJumpInsn(int opcode, Label label) {
/* 143 */     super.visitJumpInsn(opcode, label);
/* 144 */     LabelNode labelNode = ((JumpInsnNode)this.instructions.getLast()).label;
/* 145 */     if (opcode == 168 && !this.subroutinesInsns.containsKey(labelNode)) {
/* 146 */       this.subroutinesInsns.put(labelNode, new BitSet());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 152 */     if (!this.subroutinesInsns.isEmpty()) {
/*     */       
/* 154 */       findSubroutinesInsns();
/* 155 */       emitCode();
/*     */     } 
/* 157 */     if (this.mv != null) {
/* 158 */       accept(this.mv);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void findSubroutinesInsns() {
/* 165 */     BitSet visitedInsns = new BitSet();
/* 166 */     findSubroutineInsns(0, this.mainSubroutineInsns, visitedInsns);
/*     */     
/* 168 */     for (Map.Entry<LabelNode, BitSet> entry : this.subroutinesInsns.entrySet()) {
/* 169 */       LabelNode jsrLabelNode = entry.getKey();
/* 170 */       BitSet subroutineInsns = entry.getValue();
/* 171 */       findSubroutineInsns(this.instructions.indexOf((AbstractInsnNode)jsrLabelNode), subroutineInsns, visitedInsns);
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
/*     */   private void findSubroutineInsns(int startInsnIndex, BitSet subroutineInsns, BitSet visitedInsns) {
/*     */     boolean applicableHandlerFound;
/* 189 */     findReachableInsns(startInsnIndex, subroutineInsns, visitedInsns);
/*     */ 
/*     */     
/*     */     do {
/* 193 */       applicableHandlerFound = false;
/* 194 */       for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks)
/*     */       {
/* 196 */         int handlerIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.handler);
/* 197 */         if (subroutineInsns.get(handlerIndex)) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 203 */         int startIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.start);
/* 204 */         int endIndex = this.instructions.indexOf((AbstractInsnNode)tryCatchBlockNode.end);
/* 205 */         int firstSubroutineInsnAfterTryCatchStart = subroutineInsns.nextSetBit(startIndex);
/* 206 */         if (firstSubroutineInsnAfterTryCatchStart >= startIndex && firstSubroutineInsnAfterTryCatchStart < endIndex)
/*     */         {
/* 208 */           findReachableInsns(handlerIndex, subroutineInsns, visitedInsns);
/* 209 */           applicableHandlerFound = true;
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 214 */     } while (applicableHandlerFound);
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
/*     */   private void findReachableInsns(int insnIndex, BitSet subroutineInsns, BitSet visitedInsns) {
/* 233 */     int currentInsnIndex = insnIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     while (currentInsnIndex < this.instructions.size()) {
/*     */       
/* 241 */       if (subroutineInsns.get(currentInsnIndex)) {
/*     */         return;
/*     */       }
/* 244 */       subroutineInsns.set(currentInsnIndex);
/*     */ 
/*     */       
/* 247 */       if (visitedInsns.get(currentInsnIndex)) {
/* 248 */         this.sharedSubroutineInsns.set(currentInsnIndex);
/*     */       }
/* 250 */       visitedInsns.set(currentInsnIndex);
/*     */       
/* 252 */       AbstractInsnNode currentInsnNode = this.instructions.get(currentInsnIndex);
/* 253 */       if (currentInsnNode.getType() == 7 && currentInsnNode
/* 254 */         .getOpcode() != 168) {
/*     */         
/* 256 */         JumpInsnNode jumpInsnNode = (JumpInsnNode)currentInsnNode;
/* 257 */         findReachableInsns(this.instructions.indexOf((AbstractInsnNode)jumpInsnNode.label), subroutineInsns, visitedInsns);
/* 258 */       } else if (currentInsnNode.getType() == 11) {
/* 259 */         TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode)currentInsnNode;
/* 260 */         findReachableInsns(this.instructions
/* 261 */             .indexOf((AbstractInsnNode)tableSwitchInsnNode.dflt), subroutineInsns, visitedInsns);
/* 262 */         for (LabelNode labelNode : tableSwitchInsnNode.labels) {
/* 263 */           findReachableInsns(this.instructions.indexOf((AbstractInsnNode)labelNode), subroutineInsns, visitedInsns);
/*     */         }
/* 265 */       } else if (currentInsnNode.getType() == 12) {
/* 266 */         LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode)currentInsnNode;
/* 267 */         findReachableInsns(this.instructions
/* 268 */             .indexOf((AbstractInsnNode)lookupSwitchInsnNode.dflt), subroutineInsns, visitedInsns);
/* 269 */         for (LabelNode labelNode : lookupSwitchInsnNode.labels) {
/* 270 */           findReachableInsns(this.instructions.indexOf((AbstractInsnNode)labelNode), subroutineInsns, visitedInsns);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 275 */       switch (this.instructions.get(currentInsnIndex).getOpcode()) {
/*     */         case 167:
/*     */         case 169:
/*     */         case 170:
/*     */         case 171:
/*     */         case 172:
/*     */         case 173:
/*     */         case 174:
/*     */         case 175:
/*     */         case 176:
/*     */         case 177:
/*     */         case 191:
/*     */           return;
/*     */       } 
/*     */ 
/*     */       
/* 291 */       currentInsnIndex++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void emitCode() {
/* 302 */     LinkedList<Instantiation> worklist = new LinkedList<Instantiation>();
/*     */     
/* 304 */     worklist.add(new Instantiation(null, this.mainSubroutineInsns));
/*     */ 
/*     */     
/* 307 */     InsnList newInstructions = new InsnList();
/* 308 */     List<TryCatchBlockNode> newTryCatchBlocks = new ArrayList<TryCatchBlockNode>();
/* 309 */     List<LocalVariableNode> newLocalVariables = new ArrayList<LocalVariableNode>();
/* 310 */     while (!worklist.isEmpty()) {
/* 311 */       Instantiation instantiation = worklist.removeFirst();
/* 312 */       emitInstantiation(instantiation, worklist, newInstructions, newTryCatchBlocks, newLocalVariables);
/*     */     } 
/*     */     
/* 315 */     this.instructions = newInstructions;
/* 316 */     this.tryCatchBlocks = newTryCatchBlocks;
/* 317 */     this.localVariables = newLocalVariables;
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
/*     */   private void emitInstantiation(Instantiation instantiation, List<Instantiation> worklist, InsnList newInstructions, List<TryCatchBlockNode> newTryCatchBlocks, List<LocalVariableNode> newLocalVariables) {
/* 339 */     LabelNode previousLabelNode = null;
/* 340 */     for (int i = 0; i < this.instructions.size(); i++) {
/* 341 */       AbstractInsnNode insnNode = this.instructions.get(i);
/* 342 */       if (insnNode.getType() == 8) {
/*     */         
/* 344 */         LabelNode labelNode = (LabelNode)insnNode;
/* 345 */         LabelNode clonedLabelNode = instantiation.getClonedLabel(labelNode);
/* 346 */         if (clonedLabelNode != previousLabelNode) {
/* 347 */           newInstructions.add((AbstractInsnNode)clonedLabelNode);
/* 348 */           previousLabelNode = clonedLabelNode;
/*     */         } 
/* 350 */       } else if (instantiation.findOwner(i) == instantiation) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 355 */         if (insnNode.getOpcode() == 169) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 360 */           LabelNode retLabel = null;
/* 361 */           Instantiation retLabelOwner = instantiation;
/* 362 */           for (; retLabelOwner != null; 
/* 363 */             retLabelOwner = retLabelOwner.parent) {
/* 364 */             if (retLabelOwner.subroutineInsns.get(i)) {
/* 365 */               retLabel = retLabelOwner.returnLabel;
/*     */             }
/*     */           } 
/* 368 */           if (retLabel == null)
/*     */           {
/*     */             
/* 371 */             throw new IllegalArgumentException("Instruction #" + i + " is a RET not owned by any subroutine");
/*     */           }
/*     */           
/* 374 */           newInstructions.add((AbstractInsnNode)new JumpInsnNode(167, retLabel));
/* 375 */         } else if (insnNode.getOpcode() == 168) {
/* 376 */           LabelNode jsrLabelNode = ((JumpInsnNode)insnNode).label;
/* 377 */           BitSet subroutineInsns = this.subroutinesInsns.get(jsrLabelNode);
/* 378 */           Instantiation newInstantiation = new Instantiation(instantiation, subroutineInsns);
/* 379 */           LabelNode clonedJsrLabelNode = newInstantiation.getClonedLabelForJumpInsn(jsrLabelNode);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 384 */           newInstructions.add((AbstractInsnNode)new InsnNode(1));
/* 385 */           newInstructions.add((AbstractInsnNode)new JumpInsnNode(167, clonedJsrLabelNode));
/* 386 */           newInstructions.add((AbstractInsnNode)newInstantiation.returnLabel);
/*     */           
/* 388 */           worklist.add(newInstantiation);
/*     */         } else {
/* 390 */           newInstructions.add(insnNode.clone(instantiation));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 396 */     for (TryCatchBlockNode tryCatchBlockNode : this.tryCatchBlocks) {
/* 397 */       LabelNode start = instantiation.getClonedLabel(tryCatchBlockNode.start);
/* 398 */       LabelNode end = instantiation.getClonedLabel(tryCatchBlockNode.end);
/* 399 */       if (start != end) {
/*     */         
/* 401 */         LabelNode handler = instantiation.getClonedLabelForJumpInsn(tryCatchBlockNode.handler);
/* 402 */         if (start == null || end == null || handler == null) {
/* 403 */           throw new AssertionError("Internal error!");
/*     */         }
/* 405 */         newTryCatchBlocks.add(new TryCatchBlockNode(start, end, handler, tryCatchBlockNode.type));
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 410 */     for (LocalVariableNode localVariableNode : this.localVariables) {
/* 411 */       LabelNode start = instantiation.getClonedLabel(localVariableNode.start);
/* 412 */       LabelNode end = instantiation.getClonedLabel(localVariableNode.end);
/* 413 */       if (start != end) {
/* 414 */         newLocalVariables.add(new LocalVariableNode(localVariableNode.name, localVariableNode.desc, localVariableNode.signature, start, end, localVariableNode.index));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class Instantiation
/*     */     extends AbstractMap<LabelNode, LabelNode>
/*     */   {
/*     */     final Instantiation parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final BitSet subroutineInsns;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final Map<LabelNode, LabelNode> clonedLabels;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     final LabelNode returnLabel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Instantiation(Instantiation parent, BitSet subroutineInsns) {
/* 455 */       Instantiation instantiation = parent;
/* 456 */       for (; instantiation != null; 
/* 457 */         instantiation = instantiation.parent) {
/* 458 */         if (instantiation.subroutineInsns == subroutineInsns) {
/* 459 */           throw new IllegalArgumentException("Recursive invocation of " + subroutineInsns);
/*     */         }
/*     */       } 
/*     */       
/* 463 */       this.parent = parent;
/* 464 */       this.subroutineInsns = subroutineInsns;
/* 465 */       this.returnLabel = (parent == null) ? null : new LabelNode();
/* 466 */       this.clonedLabels = new HashMap<LabelNode, LabelNode>();
/*     */ 
/*     */ 
/*     */       
/* 470 */       LabelNode clonedLabelNode = null;
/* 471 */       for (int insnIndex = 0; insnIndex < JSRInlinerAdapter.this.instructions.size(); insnIndex++) {
/* 472 */         AbstractInsnNode insnNode = JSRInlinerAdapter.this.instructions.get(insnIndex);
/* 473 */         if (insnNode.getType() == 8) {
/* 474 */           LabelNode labelNode = (LabelNode)insnNode;
/*     */           
/* 476 */           if (clonedLabelNode == null) {
/* 477 */             clonedLabelNode = new LabelNode();
/*     */           }
/* 479 */           this.clonedLabels.put(labelNode, clonedLabelNode);
/* 480 */         } else if (findOwner(insnIndex) == this) {
/*     */ 
/*     */           
/* 483 */           clonedLabelNode = null;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Instantiation findOwner(int insnIndex) {
/* 507 */       if (!this.subroutineInsns.get(insnIndex)) {
/* 508 */         return null;
/*     */       }
/* 510 */       if (!JSRInlinerAdapter.this.sharedSubroutineInsns.get(insnIndex)) {
/* 511 */         return this;
/*     */       }
/* 513 */       Instantiation owner = this;
/* 514 */       Instantiation instantiation = this.parent;
/* 515 */       for (; instantiation != null; 
/* 516 */         instantiation = instantiation.parent) {
/* 517 */         if (instantiation.subroutineInsns.get(insnIndex)) {
/* 518 */           owner = instantiation;
/*     */         }
/*     */       } 
/* 521 */       return owner;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     LabelNode getClonedLabelForJumpInsn(LabelNode labelNode) {
/* 534 */       return (findOwner(JSRInlinerAdapter.this.instructions.indexOf((AbstractInsnNode)labelNode))).clonedLabels.get(labelNode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     LabelNode getClonedLabel(LabelNode labelNode) {
/* 546 */       return this.clonedLabels.get(labelNode);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Set<Map.Entry<LabelNode, LabelNode>> entrySet() {
/* 553 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public LabelNode get(Object key) {
/* 558 */       return getClonedLabelForJumpInsn((LabelNode)key);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object other) {
/* 563 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 568 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\commons\JSRInlinerAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */