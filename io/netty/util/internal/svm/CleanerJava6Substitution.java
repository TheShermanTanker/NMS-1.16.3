package io.netty.util.internal.svm;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;

@TargetClass(className = "io.netty.util.internal.CleanerJava6")
final class CleanerJava6Substitution {
  @Alias
  @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.FieldOffset, declClassName = "java.nio.DirectByteBuffer", name = "cleaner")
  private static long CLEANER_FIELD_OFFSET;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\nett\\util\internal\svm\CleanerJava6Substitution.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */