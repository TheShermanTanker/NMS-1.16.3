package org.bukkit.craftbukkit.libs.org.objectweb.asm;

final class Context {
  Attribute[] attributePrototypes;
  
  int parsingOptions;
  
  char[] charBuffer;
  
  int currentMethodAccessFlags;
  
  String currentMethodName;
  
  String currentMethodDescriptor;
  
  Label[] currentMethodLabels;
  
  int currentTypeAnnotationTarget;
  
  TypePath currentTypeAnnotationTargetPath;
  
  Label[] currentLocalVariableAnnotationRangeStarts;
  
  Label[] currentLocalVariableAnnotationRangeEnds;
  
  int[] currentLocalVariableAnnotationRangeIndices;
  
  int currentFrameOffset;
  
  int currentFrameType;
  
  int currentFrameLocalCount;
  
  int currentFrameLocalCountDelta;
  
  Object[] currentFrameLocalTypes;
  
  int currentFrameStackCount;
  
  Object[] currentFrameStackTypes;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\objectweb\asm\Context.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */