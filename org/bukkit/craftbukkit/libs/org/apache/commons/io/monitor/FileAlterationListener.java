package org.bukkit.craftbukkit.libs.org.apache.commons.io.monitor;

import java.io.File;

public interface FileAlterationListener {
  void onStart(FileAlterationObserver paramFileAlterationObserver);
  
  void onDirectoryCreate(File paramFile);
  
  void onDirectoryChange(File paramFile);
  
  void onDirectoryDelete(File paramFile);
  
  void onFileCreate(File paramFile);
  
  void onFileChange(File paramFile);
  
  void onFileDelete(File paramFile);
  
  void onStop(FileAlterationObserver paramFileAlterationObserver);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\io\monitor\FileAlterationListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */