/*     */ package org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.analysis;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Opcodes;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.AbstractInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.IincInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.InsnList;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.JumpInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LabelNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.LookupSwitchInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.MethodNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TableSwitchInsnNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.TryCatchBlockNode;
/*     */ import org.bukkit.craftbukkit.libs.org.objectweb.asm.tree.VarInsnNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Analyzer<V extends Value>
/*     */   implements Opcodes
/*     */ {
/*     */   private final Interpreter<V> interpreter;
/*     */   private InsnList insnList;
/*     */   private int insnListSize;
/*     */   private List<TryCatchBlockNode>[] handlers;
/*     */   private Frame<V>[] frames;
/*     */   private Subroutine[] subroutines;
/*     */   private boolean[] inInstructionsToProcess;
/*     */   private int[] instructionsToProcess;
/*     */   private int numInstructionsToProcess;
/*     */   
/*     */   public Analyzer(Interpreter<V> interpreter) {
/*  89 */     this.interpreter = interpreter;
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
/*     */   public Frame<V>[] analyze(String owner, MethodNode method) throws AnalyzerException {
/* 105 */     if ((method.access & 0x500) != 0) {
/* 106 */       this.frames = (Frame<V>[])new Frame[0];
/* 107 */       return this.frames;
/*     */     } 
/* 109 */     this.insnList = method.instructions;
/* 110 */     this.insnListSize = this.insnList.size();
/* 111 */     this.handlers = (List<TryCatchBlockNode>[])new List[this.insnListSize];
/* 112 */     this.frames = (Frame<V>[])new Frame[this.insnListSize];
/* 113 */     this.subroutines = new Subroutine[this.insnListSize];
/* 114 */     this.inInstructionsToProcess = new boolean[this.insnListSize];
/* 115 */     this.instructionsToProcess = new int[this.insnListSize];
/* 116 */     this.numInstructionsToProcess = 0;
/*     */ 
/*     */ 
/*     */     
/* 120 */     for (int i = 0; i < method.tryCatchBlocks.size(); i++) {
/* 121 */       TryCatchBlockNode tryCatchBlock = method.tryCatchBlocks.get(i);
/* 122 */       int startIndex = this.insnList.indexOf((AbstractInsnNode)tryCatchBlock.start);
/* 123 */       int endIndex = this.insnList.indexOf((AbstractInsnNode)tryCatchBlock.end);
/* 124 */       for (int k = startIndex; k < endIndex; k++) {
/* 125 */         List<TryCatchBlockNode> insnHandlers = this.handlers[k];
/* 126 */         if (insnHandlers == null) {
/* 127 */           insnHandlers = new ArrayList<TryCatchBlockNode>();
/* 128 */           this.handlers[k] = insnHandlers;
/*     */         } 
/* 130 */         insnHandlers.add(tryCatchBlock);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 136 */     Subroutine main = new Subroutine(null, method.maxLocals, null);
/* 137 */     List<AbstractInsnNode> jsrInsns = new ArrayList<AbstractInsnNode>();
/* 138 */     findSubroutine(0, main, jsrInsns);
/*     */ 
/*     */     
/* 141 */     Map<LabelNode, Subroutine> jsrSubroutines = new HashMap<LabelNode, Subroutine>();
/* 142 */     while (!jsrInsns.isEmpty()) {
/* 143 */       JumpInsnNode jsrInsn = (JumpInsnNode)jsrInsns.remove(0);
/* 144 */       Subroutine subroutine = jsrSubroutines.get(jsrInsn.label);
/* 145 */       if (subroutine == null) {
/* 146 */         subroutine = new Subroutine(jsrInsn.label, method.maxLocals, jsrInsn);
/* 147 */         jsrSubroutines.put(jsrInsn.label, subroutine);
/* 148 */         findSubroutine(this.insnList.indexOf((AbstractInsnNode)jsrInsn.label), subroutine, jsrInsns); continue;
/*     */       } 
/* 150 */       subroutine.callers.add(jsrInsn);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 155 */     for (int j = 0; j < this.insnListSize; j++) {
/* 156 */       if (this.subroutines[j] != null && (this.subroutines[j]).start == null) {
/* 157 */         this.subroutines[j] = null;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 162 */     Frame<V> currentFrame = computeInitialFrame(owner, method);
/* 163 */     merge(0, currentFrame, null);
/* 164 */     init(owner, method);
/*     */ 
/*     */     
/* 167 */     while (this.numInstructionsToProcess > 0) {
/*     */       
/* 169 */       int insnIndex = this.instructionsToProcess[--this.numInstructionsToProcess];
/* 170 */       Frame<V> oldFrame = this.frames[insnIndex];
/* 171 */       Subroutine subroutine = this.subroutines[insnIndex];
/* 172 */       this.inInstructionsToProcess[insnIndex] = false;
/*     */ 
/*     */       
/* 175 */       AbstractInsnNode insnNode = null;
/*     */       try {
/* 177 */         insnNode = method.instructions.get(insnIndex);
/* 178 */         int insnOpcode = insnNode.getOpcode();
/* 179 */         int insnType = insnNode.getType();
/*     */         
/* 181 */         if (insnType == 8 || insnType == 15 || insnType == 14) {
/*     */ 
/*     */           
/* 184 */           merge(insnIndex + 1, oldFrame, subroutine);
/* 185 */           newControlFlowEdge(insnIndex, insnIndex + 1);
/*     */         } else {
/* 187 */           currentFrame.init(oldFrame).execute(insnNode, this.interpreter);
/* 188 */           subroutine = (subroutine == null) ? null : new Subroutine(subroutine);
/*     */           
/* 190 */           if (insnNode instanceof JumpInsnNode) {
/* 191 */             JumpInsnNode jumpInsn = (JumpInsnNode)insnNode;
/* 192 */             if (insnOpcode != 167 && insnOpcode != 168) {
/* 193 */               currentFrame.initJumpTarget(insnOpcode, null);
/* 194 */               merge(insnIndex + 1, currentFrame, subroutine);
/* 195 */               newControlFlowEdge(insnIndex, insnIndex + 1);
/*     */             } 
/* 197 */             int jumpInsnIndex = this.insnList.indexOf((AbstractInsnNode)jumpInsn.label);
/* 198 */             currentFrame.initJumpTarget(insnOpcode, jumpInsn.label);
/* 199 */             if (insnOpcode == 168) {
/* 200 */               merge(jumpInsnIndex, currentFrame, new Subroutine(jumpInsn.label, method.maxLocals, jumpInsn));
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 205 */               merge(jumpInsnIndex, currentFrame, subroutine);
/*     */             } 
/* 207 */             newControlFlowEdge(insnIndex, jumpInsnIndex);
/* 208 */           } else if (insnNode instanceof LookupSwitchInsnNode) {
/* 209 */             LookupSwitchInsnNode lookupSwitchInsn = (LookupSwitchInsnNode)insnNode;
/* 210 */             int targetInsnIndex = this.insnList.indexOf((AbstractInsnNode)lookupSwitchInsn.dflt);
/* 211 */             currentFrame.initJumpTarget(insnOpcode, lookupSwitchInsn.dflt);
/* 212 */             merge(targetInsnIndex, currentFrame, subroutine);
/* 213 */             newControlFlowEdge(insnIndex, targetInsnIndex);
/* 214 */             for (int k = 0; k < lookupSwitchInsn.labels.size(); k++) {
/* 215 */               LabelNode label = lookupSwitchInsn.labels.get(k);
/* 216 */               targetInsnIndex = this.insnList.indexOf((AbstractInsnNode)label);
/* 217 */               currentFrame.initJumpTarget(insnOpcode, label);
/* 218 */               merge(targetInsnIndex, currentFrame, subroutine);
/* 219 */               newControlFlowEdge(insnIndex, targetInsnIndex);
/*     */             } 
/* 221 */           } else if (insnNode instanceof TableSwitchInsnNode) {
/* 222 */             TableSwitchInsnNode tableSwitchInsn = (TableSwitchInsnNode)insnNode;
/* 223 */             int targetInsnIndex = this.insnList.indexOf((AbstractInsnNode)tableSwitchInsn.dflt);
/* 224 */             currentFrame.initJumpTarget(insnOpcode, tableSwitchInsn.dflt);
/* 225 */             merge(targetInsnIndex, currentFrame, subroutine);
/* 226 */             newControlFlowEdge(insnIndex, targetInsnIndex);
/* 227 */             for (int k = 0; k < tableSwitchInsn.labels.size(); k++) {
/* 228 */               LabelNode label = tableSwitchInsn.labels.get(k);
/* 229 */               currentFrame.initJumpTarget(insnOpcode, label);
/* 230 */               targetInsnIndex = this.insnList.indexOf((AbstractInsnNode)label);
/* 231 */               merge(targetInsnIndex, currentFrame, subroutine);
/* 232 */               newControlFlowEdge(insnIndex, targetInsnIndex);
/*     */             } 
/* 234 */           } else if (insnOpcode == 169) {
/* 235 */             if (subroutine == null) {
/* 236 */               throw new AnalyzerException(insnNode, "RET instruction outside of a subroutine");
/*     */             }
/* 238 */             for (int k = 0; k < subroutine.callers.size(); k++) {
/* 239 */               JumpInsnNode caller = subroutine.callers.get(k);
/* 240 */               int jsrInsnIndex = this.insnList.indexOf((AbstractInsnNode)caller);
/* 241 */               if (this.frames[jsrInsnIndex] != null) {
/* 242 */                 merge(jsrInsnIndex + 1, this.frames[jsrInsnIndex], currentFrame, this.subroutines[jsrInsnIndex], subroutine.localsUsed);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 248 */                 newControlFlowEdge(insnIndex, jsrInsnIndex + 1);
/*     */               } 
/*     */             } 
/* 251 */           } else if (insnOpcode != 191 && (insnOpcode < 172 || insnOpcode > 177)) {
/* 252 */             if (subroutine != null) {
/* 253 */               if (insnNode instanceof VarInsnNode) {
/* 254 */                 int var = ((VarInsnNode)insnNode).var;
/* 255 */                 subroutine.localsUsed[var] = true;
/* 256 */                 if (insnOpcode == 22 || insnOpcode == 24 || insnOpcode == 55 || insnOpcode == 57)
/*     */                 {
/*     */ 
/*     */                   
/* 260 */                   subroutine.localsUsed[var + 1] = true;
/*     */                 }
/* 262 */               } else if (insnNode instanceof IincInsnNode) {
/* 263 */                 int var = ((IincInsnNode)insnNode).var;
/* 264 */                 subroutine.localsUsed[var] = true;
/*     */               } 
/*     */             }
/* 267 */             merge(insnIndex + 1, currentFrame, subroutine);
/* 268 */             newControlFlowEdge(insnIndex, insnIndex + 1);
/*     */           } 
/*     */         } 
/*     */         
/* 272 */         List<TryCatchBlockNode> insnHandlers = this.handlers[insnIndex];
/* 273 */         if (insnHandlers != null) {
/* 274 */           for (TryCatchBlockNode tryCatchBlock : insnHandlers) {
/*     */             Type catchType;
/* 276 */             if (tryCatchBlock.type == null) {
/* 277 */               catchType = Type.getObjectType("java/lang/Throwable");
/*     */             } else {
/* 279 */               catchType = Type.getObjectType(tryCatchBlock.type);
/*     */             } 
/* 281 */             if (newControlFlowExceptionEdge(insnIndex, tryCatchBlock)) {
/* 282 */               Frame<V> handler = newFrame(oldFrame);
/* 283 */               handler.clearStack();
/* 284 */               handler.push(this.interpreter.newExceptionValue(tryCatchBlock, handler, catchType));
/* 285 */               merge(this.insnList.indexOf((AbstractInsnNode)tryCatchBlock.handler), handler, subroutine);
/*     */             } 
/*     */           } 
/*     */         }
/* 289 */       } catch (AnalyzerException e) {
/* 290 */         throw new AnalyzerException(e.node, "Error at instruction " + insnIndex + ": " + e
/* 291 */             .getMessage(), e);
/* 292 */       } catch (RuntimeException e) {
/*     */         
/* 294 */         throw new AnalyzerException(insnNode, "Error at instruction " + insnIndex + ": " + e
/* 295 */             .getMessage(), e);
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     return this.frames;
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
/*     */   private void findSubroutine(int insnIndex, Subroutine subroutine, List<AbstractInsnNode> jsrInsns) throws AnalyzerException {
/* 316 */     ArrayList<Integer> instructionIndicesToProcess = new ArrayList<Integer>();
/* 317 */     instructionIndicesToProcess.add(Integer.valueOf(insnIndex));
/* 318 */     while (!instructionIndicesToProcess.isEmpty()) {
/*     */       
/* 320 */       int currentInsnIndex = ((Integer)instructionIndicesToProcess.remove(instructionIndicesToProcess.size() - 1)).intValue();
/* 321 */       if (currentInsnIndex < 0 || currentInsnIndex >= this.insnListSize) {
/* 322 */         throw new AnalyzerException(null, "Execution can fall off the end of the code");
/*     */       }
/* 324 */       if (this.subroutines[currentInsnIndex] != null) {
/*     */         continue;
/*     */       }
/* 327 */       this.subroutines[currentInsnIndex] = new Subroutine(subroutine);
/* 328 */       AbstractInsnNode currentInsn = this.insnList.get(currentInsnIndex);
/*     */ 
/*     */       
/* 331 */       if (currentInsn instanceof JumpInsnNode) {
/* 332 */         if (currentInsn.getOpcode() == 168) {
/*     */           
/* 334 */           jsrInsns.add(currentInsn);
/*     */         } else {
/* 336 */           JumpInsnNode jumpInsn = (JumpInsnNode)currentInsn;
/* 337 */           instructionIndicesToProcess.add(Integer.valueOf(this.insnList.indexOf((AbstractInsnNode)jumpInsn.label)));
/*     */         } 
/* 339 */       } else if (currentInsn instanceof TableSwitchInsnNode) {
/* 340 */         TableSwitchInsnNode tableSwitchInsn = (TableSwitchInsnNode)currentInsn;
/* 341 */         findSubroutine(this.insnList.indexOf((AbstractInsnNode)tableSwitchInsn.dflt), subroutine, jsrInsns);
/* 342 */         for (int i = tableSwitchInsn.labels.size() - 1; i >= 0; i--) {
/* 343 */           LabelNode labelNode = tableSwitchInsn.labels.get(i);
/* 344 */           instructionIndicesToProcess.add(Integer.valueOf(this.insnList.indexOf((AbstractInsnNode)labelNode)));
/*     */         } 
/* 346 */       } else if (currentInsn instanceof LookupSwitchInsnNode) {
/* 347 */         LookupSwitchInsnNode lookupSwitchInsn = (LookupSwitchInsnNode)currentInsn;
/* 348 */         findSubroutine(this.insnList.indexOf((AbstractInsnNode)lookupSwitchInsn.dflt), subroutine, jsrInsns);
/* 349 */         for (int i = lookupSwitchInsn.labels.size() - 1; i >= 0; i--) {
/* 350 */           LabelNode labelNode = lookupSwitchInsn.labels.get(i);
/* 351 */           instructionIndicesToProcess.add(Integer.valueOf(this.insnList.indexOf((AbstractInsnNode)labelNode)));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 356 */       List<TryCatchBlockNode> insnHandlers = this.handlers[currentInsnIndex];
/* 357 */       if (insnHandlers != null) {
/* 358 */         for (TryCatchBlockNode tryCatchBlock : insnHandlers) {
/* 359 */           instructionIndicesToProcess.add(Integer.valueOf(this.insnList.indexOf((AbstractInsnNode)tryCatchBlock.handler)));
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 364 */       switch (currentInsn.getOpcode()) {
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
/*     */           continue;
/*     */       } 
/* 378 */       instructionIndicesToProcess.add(Integer.valueOf(currentInsnIndex + 1));
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
/*     */   private Frame<V> computeInitialFrame(String owner, MethodNode method) {
/* 392 */     Frame<V> frame = newFrame(method.maxLocals, method.maxStack);
/* 393 */     int currentLocal = 0;
/* 394 */     boolean isInstanceMethod = ((method.access & 0x8) == 0);
/* 395 */     if (isInstanceMethod) {
/* 396 */       Type ownerType = Type.getObjectType(owner);
/* 397 */       frame.setLocal(currentLocal, this.interpreter
/* 398 */           .newParameterValue(isInstanceMethod, currentLocal, ownerType));
/* 399 */       currentLocal++;
/*     */     } 
/* 401 */     Type[] argumentTypes = Type.getArgumentTypes(method.desc);
/* 402 */     for (Type argumentType : argumentTypes) {
/* 403 */       frame.setLocal(currentLocal, this.interpreter
/*     */           
/* 405 */           .newParameterValue(isInstanceMethod, currentLocal, argumentType));
/* 406 */       currentLocal++;
/* 407 */       if (argumentType.getSize() == 2) {
/* 408 */         frame.setLocal(currentLocal, this.interpreter.newEmptyValue(currentLocal));
/* 409 */         currentLocal++;
/*     */       } 
/*     */     } 
/* 412 */     while (currentLocal < method.maxLocals) {
/* 413 */       frame.setLocal(currentLocal, this.interpreter.newEmptyValue(currentLocal));
/* 414 */       currentLocal++;
/*     */     } 
/* 416 */     frame.setReturn(this.interpreter.newReturnTypeValue(Type.getReturnType(method.desc)));
/* 417 */     return frame;
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
/*     */   public Frame<V>[] getFrames() {
/* 429 */     return this.frames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TryCatchBlockNode> getHandlers(int insnIndex) {
/* 439 */     return this.handlers[insnIndex];
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
/*     */   protected void init(String owner, MethodNode method) throws AnalyzerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Frame<V> newFrame(int numLocals, int numStack) {
/* 462 */     return new Frame<V>(numLocals, numStack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Frame<V> newFrame(Frame<? extends V> frame) {
/* 472 */     return new Frame<V>(frame);
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
/*     */   protected void newControlFlowEdge(int insnIndex, int successorIndex) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean newControlFlowExceptionEdge(int insnIndex, int successorIndex) {
/* 500 */     return true;
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
/*     */   protected boolean newControlFlowExceptionEdge(int insnIndex, TryCatchBlockNode tryCatchBlock) {
/* 517 */     return newControlFlowExceptionEdge(insnIndex, this.insnList.indexOf((AbstractInsnNode)tryCatchBlock.handler));
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
/*     */   private void merge(int insnIndex, Frame<V> frame, Subroutine subroutine) throws AnalyzerException {
/*     */     boolean bool;
/* 536 */     Frame<V> oldFrame = this.frames[insnIndex];
/* 537 */     if (oldFrame == null) {
/* 538 */       this.frames[insnIndex] = newFrame(frame);
/* 539 */       bool = true;
/*     */     } else {
/* 541 */       bool = oldFrame.merge(frame, this.interpreter);
/*     */     } 
/* 543 */     Subroutine oldSubroutine = this.subroutines[insnIndex];
/* 544 */     if (oldSubroutine == null) {
/* 545 */       if (subroutine != null) {
/* 546 */         this.subroutines[insnIndex] = new Subroutine(subroutine);
/* 547 */         bool = true;
/*     */       }
/*     */     
/* 550 */     } else if (subroutine != null) {
/* 551 */       bool |= oldSubroutine.merge(subroutine);
/*     */     } 
/*     */     
/* 554 */     if (bool && !this.inInstructionsToProcess[insnIndex]) {
/* 555 */       this.inInstructionsToProcess[insnIndex] = true;
/* 556 */       this.instructionsToProcess[this.numInstructionsToProcess++] = insnIndex;
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
/*     */   private void merge(int insnIndex, Frame<V> frameBeforeJsr, Frame<V> frameAfterRet, Subroutine subroutineBeforeJsr, boolean[] localsUsed) throws AnalyzerException {
/*     */     boolean changed;
/* 584 */     frameAfterRet.merge(frameBeforeJsr, localsUsed);
/*     */ 
/*     */     
/* 587 */     Frame<V> oldFrame = this.frames[insnIndex];
/* 588 */     if (oldFrame == null) {
/* 589 */       this.frames[insnIndex] = newFrame(frameAfterRet);
/* 590 */       changed = true;
/*     */     } else {
/* 592 */       changed = oldFrame.merge(frameAfterRet, this.interpreter);
/*     */     } 
/* 594 */     Subroutine oldSubroutine = this.subroutines[insnIndex];
/* 595 */     if (oldSubroutine != null && subroutineBeforeJsr != null) {
/* 596 */       changed |= oldSubroutine.merge(subroutineBeforeJsr);
/*     */     }
/* 598 */     if (changed && !this.inInstructionsToProcess[insnIndex]) {
/* 599 */       this.inInstructionsToProcess[insnIndex] = true;
/* 600 */       this.instructionsToProcess[this.numInstructionsToProcess++] = insnIndex;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\tree\analysis\Analyzer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */