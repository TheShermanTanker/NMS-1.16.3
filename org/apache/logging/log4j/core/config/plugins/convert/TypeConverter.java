package org.apache.logging.log4j.core.config.plugins.convert;

public interface TypeConverter<T> {
  T convert(String paramString) throws Exception;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\config\plugins\convert\TypeConverter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */