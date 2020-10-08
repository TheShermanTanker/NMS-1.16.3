package org.sqlite;

public interface SQLiteCommitListener {
  void onCommit();
  
  void onRollback();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlite\SQLiteCommitListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */