package com.google.common.base;

import com.google.common.annotations.GwtCompatible;

@GwtCompatible
abstract class CommonMatcher {
  abstract boolean matches();
  
  abstract boolean find();
  
  abstract boolean find(int paramInt);
  
  abstract String replaceAll(String paramString);
  
  abstract int end();
  
  abstract int start();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\google\common\base\CommonMatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */