/*      */ package com.mysql.jdbc;
/*      */ 
/*      */ import com.mysql.jdbc.log.Log;
/*      */ import com.mysql.jdbc.log.StandardLogger;
/*      */ import java.io.Serializable;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.lang.reflect.Field;
/*      */ import java.sql.DriverPropertyInfo;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TreeMap;
/*      */ import javax.naming.RefAddr;
/*      */ import javax.naming.Reference;
/*      */ import javax.naming.StringRefAddr;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ConnectionPropertiesImpl
/*      */   implements Serializable, ConnectionProperties
/*      */ {
/*      */   private static final long serialVersionUID = 4257801713007640580L;
/*      */   
/*      */   static class BooleanConnectionProperty
/*      */     extends ConnectionProperty
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 2540132501709159404L;
/*      */     
/*      */     BooleanConnectionProperty(String propertyNameToSet, boolean defaultValueToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*   62 */       super(propertyNameToSet, Boolean.valueOf(defaultValueToSet), null, 0, 0, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String[] getAllowableValues() {
/*   70 */       return new String[] { "true", "false", "yes", "no" };
/*      */     }
/*      */     
/*      */     boolean getValueAsBoolean() {
/*   74 */       return ((Boolean)this.valueAsObject).booleanValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasValueConstraints() {
/*   82 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void initializeFrom(String extractedValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*   90 */       if (extractedValue != null) {
/*   91 */         validateStringValues(extractedValue, exceptionInterceptor);
/*      */         
/*   93 */         this.valueAsObject = Boolean.valueOf((extractedValue.equalsIgnoreCase("TRUE") || extractedValue.equalsIgnoreCase("YES")));
/*   94 */         this.wasExplicitlySet = true;
/*      */       } else {
/*   96 */         this.valueAsObject = this.defaultValue;
/*      */       } 
/*   98 */       this.updateCount++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean isRangeBased() {
/*  106 */       return false;
/*      */     }
/*      */     
/*      */     void setValue(boolean valueFlag) {
/*  110 */       this.valueAsObject = Boolean.valueOf(valueFlag);
/*  111 */       this.wasExplicitlySet = true;
/*  112 */       this.updateCount++;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static abstract class ConnectionProperty
/*      */     implements Serializable
/*      */   {
/*      */     static final long serialVersionUID = -6644853639584478367L;
/*      */     
/*      */     String[] allowableValues;
/*      */     
/*      */     String categoryName;
/*      */     
/*      */     Object defaultValue;
/*      */     
/*      */     int lowerBound;
/*      */     
/*      */     int order;
/*      */     
/*      */     String propertyName;
/*      */     
/*      */     String sinceVersion;
/*      */     
/*      */     int upperBound;
/*      */     
/*      */     Object valueAsObject;
/*      */     
/*      */     boolean required;
/*      */     String description;
/*  142 */     int updateCount = 0;
/*      */ 
/*      */     
/*      */     boolean wasExplicitlySet = false;
/*      */ 
/*      */     
/*      */     public ConnectionProperty() {}
/*      */ 
/*      */     
/*      */     ConnectionProperty(String propertyNameToSet, Object defaultValueToSet, String[] allowableValuesToSet, int lowerBoundToSet, int upperBoundToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  152 */       this.description = descriptionToSet;
/*  153 */       this.propertyName = propertyNameToSet;
/*  154 */       this.defaultValue = defaultValueToSet;
/*  155 */       this.valueAsObject = defaultValueToSet;
/*  156 */       this.allowableValues = allowableValuesToSet;
/*  157 */       this.lowerBound = lowerBoundToSet;
/*  158 */       this.upperBound = upperBoundToSet;
/*  159 */       this.required = false;
/*  160 */       this.sinceVersion = sinceVersionToSet;
/*  161 */       this.categoryName = category;
/*  162 */       this.order = orderInCategory;
/*      */     }
/*      */     
/*      */     String[] getAllowableValues() {
/*  166 */       return this.allowableValues;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String getCategoryName() {
/*  173 */       return this.categoryName;
/*      */     }
/*      */     
/*      */     Object getDefaultValue() {
/*  177 */       return this.defaultValue;
/*      */     }
/*      */     
/*      */     int getLowerBound() {
/*  181 */       return this.lowerBound;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getOrder() {
/*  188 */       return this.order;
/*      */     }
/*      */     
/*      */     String getPropertyName() {
/*  192 */       return this.propertyName;
/*      */     }
/*      */     
/*      */     int getUpperBound() {
/*  196 */       return this.upperBound;
/*      */     }
/*      */     
/*      */     Object getValueAsObject() {
/*  200 */       return this.valueAsObject;
/*      */     }
/*      */     
/*      */     int getUpdateCount() {
/*  204 */       return this.updateCount;
/*      */     }
/*      */     
/*      */     boolean isExplicitlySet() {
/*  208 */       return this.wasExplicitlySet;
/*      */     }
/*      */     
/*      */     abstract boolean hasValueConstraints();
/*      */     
/*      */     void initializeFrom(Properties extractFrom, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  214 */       String extractedValue = extractFrom.getProperty(getPropertyName());
/*  215 */       extractFrom.remove(getPropertyName());
/*  216 */       initializeFrom(extractedValue, exceptionInterceptor);
/*      */     }
/*      */     
/*      */     void initializeFrom(Reference ref, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  220 */       RefAddr refAddr = ref.get(getPropertyName());
/*      */       
/*  222 */       if (refAddr != null) {
/*  223 */         String refContentAsString = (String)refAddr.getContent();
/*      */         
/*  225 */         initializeFrom(refContentAsString, exceptionInterceptor);
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     abstract void initializeFrom(String param1String, ExceptionInterceptor param1ExceptionInterceptor) throws SQLException;
/*      */ 
/*      */     
/*      */     abstract boolean isRangeBased();
/*      */ 
/*      */     
/*      */     void setCategoryName(String categoryName) {
/*  238 */       this.categoryName = categoryName;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void setOrder(int order) {
/*  246 */       this.order = order;
/*      */     }
/*      */     
/*      */     void setValueAsObject(Object obj) {
/*  250 */       this.valueAsObject = obj;
/*  251 */       this.updateCount++;
/*      */     }
/*      */     
/*      */     void storeTo(Reference ref) {
/*  255 */       if (getValueAsObject() != null) {
/*  256 */         ref.add(new StringRefAddr(getPropertyName(), getValueAsObject().toString()));
/*      */       }
/*      */     }
/*      */     
/*      */     DriverPropertyInfo getAsDriverPropertyInfo() {
/*  261 */       DriverPropertyInfo dpi = new DriverPropertyInfo(this.propertyName, null);
/*  262 */       dpi.choices = getAllowableValues();
/*  263 */       dpi.value = (this.valueAsObject != null) ? this.valueAsObject.toString() : null;
/*  264 */       dpi.required = this.required;
/*  265 */       dpi.description = this.description;
/*      */       
/*  267 */       return dpi;
/*      */     }
/*      */     
/*      */     void validateStringValues(String valueToValidate, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  271 */       String[] validateAgainst = getAllowableValues();
/*      */       
/*  273 */       if (valueToValidate == null) {
/*      */         return;
/*      */       }
/*      */       
/*  277 */       if (validateAgainst == null || validateAgainst.length == 0) {
/*      */         return;
/*      */       }
/*      */       
/*  281 */       for (int i = 0; i < validateAgainst.length; i++) {
/*  282 */         if (validateAgainst[i] != null && validateAgainst[i].equalsIgnoreCase(valueToValidate)) {
/*      */           return;
/*      */         }
/*      */       } 
/*      */       
/*  287 */       StringBuilder errorMessageBuf = new StringBuilder();
/*      */       
/*  289 */       errorMessageBuf.append("The connection property '");
/*  290 */       errorMessageBuf.append(getPropertyName());
/*  291 */       errorMessageBuf.append("' only accepts values of the form: ");
/*      */       
/*  293 */       if (validateAgainst.length != 0) {
/*  294 */         errorMessageBuf.append("'");
/*  295 */         errorMessageBuf.append(validateAgainst[0]);
/*  296 */         errorMessageBuf.append("'");
/*      */         
/*  298 */         for (int j = 1; j < validateAgainst.length - 1; j++) {
/*  299 */           errorMessageBuf.append(", ");
/*  300 */           errorMessageBuf.append("'");
/*  301 */           errorMessageBuf.append(validateAgainst[j]);
/*  302 */           errorMessageBuf.append("'");
/*      */         } 
/*      */         
/*  305 */         errorMessageBuf.append(" or '");
/*  306 */         errorMessageBuf.append(validateAgainst[validateAgainst.length - 1]);
/*  307 */         errorMessageBuf.append("'");
/*      */       } 
/*      */       
/*  310 */       errorMessageBuf.append(". The value '");
/*  311 */       errorMessageBuf.append(valueToValidate);
/*  312 */       errorMessageBuf.append("' is not in this set.");
/*      */       
/*  314 */       throw SQLError.createSQLException(errorMessageBuf.toString(), "S1009", exceptionInterceptor);
/*      */     }
/*      */   }
/*      */   
/*      */   static class IntegerConnectionProperty
/*      */     extends ConnectionProperty
/*      */     implements Serializable {
/*      */     private static final long serialVersionUID = -3004305481796850832L;
/*  322 */     int multiplier = 1;
/*      */ 
/*      */     
/*      */     public IntegerConnectionProperty(String propertyNameToSet, Object defaultValueToSet, String[] allowableValuesToSet, int lowerBoundToSet, int upperBoundToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  326 */       super(propertyNameToSet, defaultValueToSet, allowableValuesToSet, lowerBoundToSet, upperBoundToSet, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     IntegerConnectionProperty(String propertyNameToSet, int defaultValueToSet, int lowerBoundToSet, int upperBoundToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  332 */       super(propertyNameToSet, Integer.valueOf(defaultValueToSet), null, lowerBoundToSet, upperBoundToSet, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     IntegerConnectionProperty(String propertyNameToSet, int defaultValueToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  345 */       this(propertyNameToSet, defaultValueToSet, 0, 0, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     String[] getAllowableValues() {
/*  353 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getLowerBound() {
/*  361 */       return this.lowerBound;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     int getUpperBound() {
/*  369 */       return this.upperBound;
/*      */     }
/*      */     
/*      */     int getValueAsInt() {
/*  373 */       return ((Integer)this.valueAsObject).intValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasValueConstraints() {
/*  381 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void initializeFrom(String extractedValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  389 */       if (extractedValue != null) {
/*      */         
/*      */         try {
/*  392 */           int intValue = (int)(Double.valueOf(extractedValue).doubleValue() * this.multiplier);
/*      */           
/*  394 */           setValue(intValue, extractedValue, exceptionInterceptor);
/*  395 */         } catch (NumberFormatException nfe) {
/*  396 */           throw SQLError.createSQLException("The connection property '" + getPropertyName() + "' only accepts integer values. The value '" + extractedValue + "' can not be converted to an integer.", "S1009", exceptionInterceptor);
/*      */         } 
/*      */       } else {
/*      */         
/*  400 */         this.valueAsObject = this.defaultValue;
/*      */       } 
/*  402 */       this.updateCount++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean isRangeBased() {
/*  410 */       return (getUpperBound() != getLowerBound());
/*      */     }
/*      */     
/*      */     void setValue(int intValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  414 */       setValue(intValue, (String)null, exceptionInterceptor);
/*      */     }
/*      */     
/*      */     void setValue(int intValue, String valueAsString, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  418 */       if (isRangeBased() && (
/*  419 */         intValue < getLowerBound() || intValue > getUpperBound())) {
/*  420 */         throw SQLError.createSQLException("The connection property '" + getPropertyName() + "' only accepts integer values in the range of " + getLowerBound() + " - " + getUpperBound() + ", the value '" + ((valueAsString == null) ? Integer.valueOf(intValue) : valueAsString) + "' exceeds this range.", "S1009", exceptionInterceptor);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  427 */       this.valueAsObject = Integer.valueOf(intValue);
/*  428 */       this.wasExplicitlySet = true;
/*  429 */       this.updateCount++;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class LongConnectionProperty
/*      */     extends IntegerConnectionProperty
/*      */   {
/*      */     private static final long serialVersionUID = 6068572984340480895L;
/*      */     
/*      */     LongConnectionProperty(String propertyNameToSet, long defaultValueToSet, long lowerBoundToSet, long upperBoundToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  439 */       super(propertyNameToSet, Long.valueOf(defaultValueToSet), (String[])null, (int)lowerBoundToSet, (int)upperBoundToSet, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     LongConnectionProperty(String propertyNameToSet, long defaultValueToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  445 */       this(propertyNameToSet, defaultValueToSet, 0L, 0L, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */     
/*      */     void setValue(long longValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  449 */       setValue(longValue, (String)null, exceptionInterceptor);
/*      */     }
/*      */     
/*      */     void setValue(long longValue, String valueAsString, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  453 */       if (isRangeBased() && (
/*  454 */         longValue < getLowerBound() || longValue > getUpperBound())) {
/*  455 */         throw SQLError.createSQLException("The connection property '" + getPropertyName() + "' only accepts long integer values in the range of " + getLowerBound() + " - " + getUpperBound() + ", the value '" + ((valueAsString == null) ? Long.valueOf(longValue) : valueAsString) + "' exceeds this range.", "S1009", exceptionInterceptor);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  461 */       this.valueAsObject = Long.valueOf(longValue);
/*  462 */       this.wasExplicitlySet = true;
/*  463 */       this.updateCount++;
/*      */     }
/*      */     
/*      */     long getValueAsLong() {
/*  467 */       return ((Long)this.valueAsObject).longValue();
/*      */     }
/*      */ 
/*      */     
/*      */     void initializeFrom(String extractedValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  472 */       if (extractedValue != null) {
/*      */         
/*      */         try {
/*  475 */           long longValue = Double.valueOf(extractedValue).longValue();
/*      */           
/*  477 */           setValue(longValue, extractedValue, exceptionInterceptor);
/*  478 */         } catch (NumberFormatException nfe) {
/*  479 */           throw SQLError.createSQLException("The connection property '" + getPropertyName() + "' only accepts long integer values. The value '" + extractedValue + "' can not be converted to a long integer.", "S1009", exceptionInterceptor);
/*      */         } 
/*      */       } else {
/*      */         
/*  483 */         this.valueAsObject = this.defaultValue;
/*      */       } 
/*  485 */       this.updateCount++;
/*      */     }
/*      */   }
/*      */   
/*      */   static class MemorySizeConnectionProperty
/*      */     extends IntegerConnectionProperty
/*      */     implements Serializable
/*      */   {
/*      */     private static final long serialVersionUID = 7351065128998572656L;
/*      */     private String valueAsString;
/*      */     
/*      */     MemorySizeConnectionProperty(String propertyNameToSet, int defaultValueToSet, int lowerBoundToSet, int upperBoundToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  497 */       super(propertyNameToSet, defaultValueToSet, lowerBoundToSet, upperBoundToSet, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */     
/*      */     void initializeFrom(String extractedValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  502 */       this.valueAsString = extractedValue;
/*  503 */       this.multiplier = 1;
/*      */       
/*  505 */       if (extractedValue != null) {
/*  506 */         if (extractedValue.endsWith("k") || extractedValue.endsWith("K") || extractedValue.endsWith("kb") || extractedValue.endsWith("Kb") || extractedValue.endsWith("kB") || extractedValue.endsWith("KB")) {
/*      */           
/*  508 */           this.multiplier = 1024;
/*  509 */           int indexOfK = StringUtils.indexOfIgnoreCase(extractedValue, "k");
/*  510 */           extractedValue = extractedValue.substring(0, indexOfK);
/*  511 */         } else if (extractedValue.endsWith("m") || extractedValue.endsWith("M") || extractedValue.endsWith("mb") || extractedValue.endsWith("Mb") || extractedValue.endsWith("mB") || extractedValue.endsWith("MB")) {
/*      */           
/*  513 */           this.multiplier = 1048576;
/*  514 */           int indexOfM = StringUtils.indexOfIgnoreCase(extractedValue, "m");
/*  515 */           extractedValue = extractedValue.substring(0, indexOfM);
/*  516 */         } else if (extractedValue.endsWith("g") || extractedValue.endsWith("G") || extractedValue.endsWith("gb") || extractedValue.endsWith("Gb") || extractedValue.endsWith("gB") || extractedValue.endsWith("GB")) {
/*      */           
/*  518 */           this.multiplier = 1073741824;
/*  519 */           int indexOfG = StringUtils.indexOfIgnoreCase(extractedValue, "g");
/*  520 */           extractedValue = extractedValue.substring(0, indexOfG);
/*      */         } 
/*      */       }
/*      */       
/*  524 */       super.initializeFrom(extractedValue, exceptionInterceptor);
/*      */     }
/*      */     
/*      */     void setValue(String value, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  528 */       initializeFrom(value, exceptionInterceptor);
/*      */     }
/*      */     
/*      */     String getValueAsString() {
/*  532 */       return this.valueAsString;
/*      */     }
/*      */   }
/*      */   
/*      */   static class StringConnectionProperty
/*      */     extends ConnectionProperty
/*      */     implements Serializable {
/*      */     private static final long serialVersionUID = 5432127962785948272L;
/*      */     
/*      */     StringConnectionProperty(String propertyNameToSet, String defaultValueToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  542 */       this(propertyNameToSet, defaultValueToSet, (String[])null, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     StringConnectionProperty(String propertyNameToSet, String defaultValueToSet, String[] allowableValuesToSet, String descriptionToSet, String sinceVersionToSet, String category, int orderInCategory) {
/*  554 */       super(propertyNameToSet, defaultValueToSet, allowableValuesToSet, 0, 0, descriptionToSet, sinceVersionToSet, category, orderInCategory);
/*      */     }
/*      */     
/*      */     String getValueAsString() {
/*  558 */       return (String)this.valueAsObject;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean hasValueConstraints() {
/*  566 */       return (this.allowableValues != null && this.allowableValues.length > 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void initializeFrom(String extractedValue, ExceptionInterceptor exceptionInterceptor) throws SQLException {
/*  574 */       if (extractedValue != null) {
/*  575 */         validateStringValues(extractedValue, exceptionInterceptor);
/*      */         
/*  577 */         this.valueAsObject = extractedValue;
/*  578 */         this.wasExplicitlySet = true;
/*      */       } else {
/*  580 */         this.valueAsObject = this.defaultValue;
/*      */       } 
/*  582 */       this.updateCount++;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     boolean isRangeBased() {
/*  590 */       return false;
/*      */     }
/*      */     
/*      */     void setValue(String valueFlag) {
/*  594 */       this.valueAsObject = valueFlag;
/*  595 */       this.wasExplicitlySet = true;
/*  596 */       this.updateCount++;
/*      */     }
/*      */   }
/*      */   
/*  600 */   private static final String CONNECTION_AND_AUTH_CATEGORY = Messages.getString("ConnectionProperties.categoryConnectionAuthentication");
/*      */   
/*  602 */   private static final String NETWORK_CATEGORY = Messages.getString("ConnectionProperties.categoryNetworking");
/*      */   
/*  604 */   private static final String DEBUGING_PROFILING_CATEGORY = Messages.getString("ConnectionProperties.categoryDebuggingProfiling");
/*      */   
/*  606 */   private static final String HA_CATEGORY = Messages.getString("ConnectionProperties.categorryHA");
/*      */   
/*  608 */   private static final String MISC_CATEGORY = Messages.getString("ConnectionProperties.categoryMisc");
/*      */   
/*  610 */   private static final String PERFORMANCE_CATEGORY = Messages.getString("ConnectionProperties.categoryPerformance");
/*      */   
/*  612 */   private static final String SECURITY_CATEGORY = Messages.getString("ConnectionProperties.categorySecurity");
/*      */   
/*  614 */   private static final String[] PROPERTY_CATEGORIES = new String[] { CONNECTION_AND_AUTH_CATEGORY, NETWORK_CATEGORY, HA_CATEGORY, SECURITY_CATEGORY, PERFORMANCE_CATEGORY, DEBUGING_PROFILING_CATEGORY, MISC_CATEGORY };
/*      */ 
/*      */   
/*  617 */   private static final ArrayList<Field> PROPERTY_LIST = new ArrayList<Field>();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  622 */   private static final String STANDARD_LOGGER_NAME = StandardLogger.class.getName();
/*      */   
/*      */   protected static final String ZERO_DATETIME_BEHAVIOR_CONVERT_TO_NULL = "convertToNull";
/*      */   
/*      */   protected static final String ZERO_DATETIME_BEHAVIOR_EXCEPTION = "exception";
/*      */   
/*      */   protected static final String ZERO_DATETIME_BEHAVIOR_ROUND = "round";
/*      */   
/*      */   static {
/*      */     try {
/*  632 */       Field[] declaredFields = ConnectionPropertiesImpl.class.getDeclaredFields();
/*      */       
/*  634 */       for (int i = 0; i < declaredFields.length; i++) {
/*  635 */         if (ConnectionProperty.class.isAssignableFrom(declaredFields[i].getType())) {
/*  636 */           PROPERTY_LIST.add(declaredFields[i]);
/*      */         }
/*      */       } 
/*  639 */     } catch (Exception ex) {
/*  640 */       RuntimeException rtEx = new RuntimeException();
/*  641 */       rtEx.initCause(ex);
/*      */       
/*  643 */       throw rtEx;
/*      */     } 
/*      */   }
/*      */   
/*      */   public ExceptionInterceptor getExceptionInterceptor() {
/*  648 */     return null;
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
/*      */   protected static DriverPropertyInfo[] exposeAsDriverPropertyInfo(Properties info, int slotsToReserve) throws SQLException {
/*  666 */     return (new ConnectionPropertiesImpl() { private static final long serialVersionUID = 4257801713007640581L; }).exposeAsDriverPropertyInfoInternal(info, slotsToReserve);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*  671 */   private BooleanConnectionProperty allowLoadLocalInfile = new BooleanConnectionProperty("allowLoadLocalInfile", false, Messages.getString("ConnectionProperties.loadDataLocal"), "3.0.3", SECURITY_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  674 */   private BooleanConnectionProperty allowMultiQueries = new BooleanConnectionProperty("allowMultiQueries", false, Messages.getString("ConnectionProperties.allowMultiQueries"), "3.1.1", SECURITY_CATEGORY, 1);
/*      */ 
/*      */   
/*  677 */   private BooleanConnectionProperty allowNanAndInf = new BooleanConnectionProperty("allowNanAndInf", false, Messages.getString("ConnectionProperties.allowNANandINF"), "3.1.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  680 */   private BooleanConnectionProperty allowUrlInLocalInfile = new BooleanConnectionProperty("allowUrlInLocalInfile", false, Messages.getString("ConnectionProperties.allowUrlInLoadLocal"), "3.1.4", SECURITY_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  683 */   private BooleanConnectionProperty alwaysSendSetIsolation = new BooleanConnectionProperty("alwaysSendSetIsolation", true, Messages.getString("ConnectionProperties.alwaysSendSetIsolation"), "3.1.7", PERFORMANCE_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  686 */   private BooleanConnectionProperty autoClosePStmtStreams = new BooleanConnectionProperty("autoClosePStmtStreams", false, Messages.getString("ConnectionProperties.autoClosePstmtStreams"), "3.1.12", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  689 */   private StringConnectionProperty replicationConnectionGroup = new StringConnectionProperty("replicationConnectionGroup", null, Messages.getString("ConnectionProperties.replicationConnectionGroup"), "5.1.27", HA_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  692 */   private BooleanConnectionProperty allowMasterDownConnections = new BooleanConnectionProperty("allowMasterDownConnections", false, Messages.getString("ConnectionProperties.allowMasterDownConnections"), "5.1.27", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  695 */   private BooleanConnectionProperty allowSlaveDownConnections = new BooleanConnectionProperty("allowSlaveDownConnections", false, Messages.getString("ConnectionProperties.allowSlaveDownConnections"), "5.1.38", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  698 */   private BooleanConnectionProperty readFromMasterWhenNoSlaves = new BooleanConnectionProperty("readFromMasterWhenNoSlaves", false, Messages.getString("ConnectionProperties.readFromMasterWhenNoSlaves"), "5.1.38", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  701 */   private BooleanConnectionProperty autoDeserialize = new BooleanConnectionProperty("autoDeserialize", false, Messages.getString("ConnectionProperties.autoDeserialize"), "3.1.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  704 */   private BooleanConnectionProperty autoGenerateTestcaseScript = new BooleanConnectionProperty("autoGenerateTestcaseScript", false, Messages.getString("ConnectionProperties.autoGenerateTestcaseScript"), "3.1.9", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*      */   private boolean autoGenerateTestcaseScriptAsBoolean = false;
/*      */   
/*  709 */   private BooleanConnectionProperty autoReconnect = new BooleanConnectionProperty("autoReconnect", false, Messages.getString("ConnectionProperties.autoReconnect"), "1.1", HA_CATEGORY, 0);
/*      */ 
/*      */   
/*  712 */   private BooleanConnectionProperty autoReconnectForPools = new BooleanConnectionProperty("autoReconnectForPools", false, Messages.getString("ConnectionProperties.autoReconnectForPools"), "3.1.3", HA_CATEGORY, 1);
/*      */ 
/*      */   
/*      */   private boolean autoReconnectForPoolsAsBoolean = false;
/*      */   
/*  717 */   private MemorySizeConnectionProperty blobSendChunkSize = new MemorySizeConnectionProperty("blobSendChunkSize", 1048576, 0, 0, Messages.getString("ConnectionProperties.blobSendChunkSize"), "3.1.9", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  720 */   private BooleanConnectionProperty autoSlowLog = new BooleanConnectionProperty("autoSlowLog", true, Messages.getString("ConnectionProperties.autoSlowLog"), "5.1.4", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  723 */   private BooleanConnectionProperty blobsAreStrings = new BooleanConnectionProperty("blobsAreStrings", false, "Should the driver always treat BLOBs as Strings - specifically to work around dubious metadata returned by the server for GROUP BY clauses?", "5.0.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/*  727 */   private BooleanConnectionProperty functionsNeverReturnBlobs = new BooleanConnectionProperty("functionsNeverReturnBlobs", false, "Should the driver always treat data from functions returning BLOBs as Strings - specifically to work around dubious metadata returned by the server for GROUP BY clauses?", "5.0.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  732 */   private BooleanConnectionProperty cacheCallableStatements = new BooleanConnectionProperty("cacheCallableStmts", false, Messages.getString("ConnectionProperties.cacheCallableStatements"), "3.1.2", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  735 */   private BooleanConnectionProperty cachePreparedStatements = new BooleanConnectionProperty("cachePrepStmts", false, Messages.getString("ConnectionProperties.cachePrepStmts"), "3.0.10", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  738 */   private BooleanConnectionProperty cacheResultSetMetadata = new BooleanConnectionProperty("cacheResultSetMetadata", false, Messages.getString("ConnectionProperties.cacheRSMetadata"), "3.1.1", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*      */   private boolean cacheResultSetMetaDataAsBoolean;
/*      */   
/*  743 */   private StringConnectionProperty serverConfigCacheFactory = new StringConnectionProperty("serverConfigCacheFactory", PerVmServerConfigCacheFactory.class.getName(), Messages.getString("ConnectionProperties.serverConfigCacheFactory"), "5.1.1", PERFORMANCE_CATEGORY, 12);
/*      */ 
/*      */ 
/*      */   
/*  747 */   private BooleanConnectionProperty cacheServerConfiguration = new BooleanConnectionProperty("cacheServerConfiguration", false, Messages.getString("ConnectionProperties.cacheServerConfiguration"), "3.1.5", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  750 */   private IntegerConnectionProperty callableStatementCacheSize = new IntegerConnectionProperty("callableStmtCacheSize", 100, 0, 2147483647, Messages.getString("ConnectionProperties.callableStmtCacheSize"), "3.1.2", PERFORMANCE_CATEGORY, 5);
/*      */ 
/*      */   
/*  753 */   private BooleanConnectionProperty capitalizeTypeNames = new BooleanConnectionProperty("capitalizeTypeNames", true, Messages.getString("ConnectionProperties.capitalizeTypeNames"), "2.0.7", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  756 */   private StringConnectionProperty characterEncoding = new StringConnectionProperty("characterEncoding", null, Messages.getString("ConnectionProperties.characterEncoding"), "1.1g", MISC_CATEGORY, 5);
/*      */ 
/*      */   
/*  759 */   private String characterEncodingAsString = null;
/*      */   
/*      */   protected boolean characterEncodingIsAliasForSjis = false;
/*      */   
/*  763 */   private StringConnectionProperty characterSetResults = new StringConnectionProperty("characterSetResults", null, Messages.getString("ConnectionProperties.characterSetResults"), "3.0.13", MISC_CATEGORY, 6);
/*      */ 
/*      */   
/*  766 */   private StringConnectionProperty connectionAttributes = new StringConnectionProperty("connectionAttributes", null, Messages.getString("ConnectionProperties.connectionAttributes"), "5.1.25", MISC_CATEGORY, 7);
/*      */ 
/*      */   
/*  769 */   private StringConnectionProperty clientInfoProvider = new StringConnectionProperty("clientInfoProvider", "com.mysql.jdbc.JDBC4CommentClientInfoProvider", Messages.getString("ConnectionProperties.clientInfoProvider"), "5.1.0", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  772 */   private BooleanConnectionProperty clobberStreamingResults = new BooleanConnectionProperty("clobberStreamingResults", false, Messages.getString("ConnectionProperties.clobberStreamingResults"), "3.0.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  775 */   private StringConnectionProperty clobCharacterEncoding = new StringConnectionProperty("clobCharacterEncoding", null, Messages.getString("ConnectionProperties.clobCharacterEncoding"), "5.0.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  778 */   private BooleanConnectionProperty compensateOnDuplicateKeyUpdateCounts = new BooleanConnectionProperty("compensateOnDuplicateKeyUpdateCounts", false, Messages.getString("ConnectionProperties.compensateOnDuplicateKeyUpdateCounts"), "5.1.7", MISC_CATEGORY, -2147483648);
/*      */   
/*  780 */   private StringConnectionProperty connectionCollation = new StringConnectionProperty("connectionCollation", null, Messages.getString("ConnectionProperties.connectionCollation"), "3.0.13", MISC_CATEGORY, 7);
/*      */ 
/*      */   
/*  783 */   private StringConnectionProperty connectionLifecycleInterceptors = new StringConnectionProperty("connectionLifecycleInterceptors", null, Messages.getString("ConnectionProperties.connectionLifecycleInterceptors"), "5.1.4", CONNECTION_AND_AUTH_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  786 */   private IntegerConnectionProperty connectTimeout = new IntegerConnectionProperty("connectTimeout", 0, 0, 2147483647, Messages.getString("ConnectionProperties.connectTimeout"), "3.0.1", CONNECTION_AND_AUTH_CATEGORY, 9);
/*      */ 
/*      */   
/*  789 */   private BooleanConnectionProperty continueBatchOnError = new BooleanConnectionProperty("continueBatchOnError", true, Messages.getString("ConnectionProperties.continueBatchOnError"), "3.0.3", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  792 */   private BooleanConnectionProperty createDatabaseIfNotExist = new BooleanConnectionProperty("createDatabaseIfNotExist", false, Messages.getString("ConnectionProperties.createDatabaseIfNotExist"), "3.1.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  795 */   private IntegerConnectionProperty defaultFetchSize = new IntegerConnectionProperty("defaultFetchSize", 0, Messages.getString("ConnectionProperties.defaultFetchSize"), "3.1.9", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  800 */   private BooleanConnectionProperty detectServerPreparedStmts = new BooleanConnectionProperty("useServerPrepStmts", false, Messages.getString("ConnectionProperties.useServerPrepStmts"), "3.1.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  803 */   private BooleanConnectionProperty dontTrackOpenResources = new BooleanConnectionProperty("dontTrackOpenResources", false, Messages.getString("ConnectionProperties.dontTrackOpenResources"), "3.1.7", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  806 */   private BooleanConnectionProperty dumpQueriesOnException = new BooleanConnectionProperty("dumpQueriesOnException", false, Messages.getString("ConnectionProperties.dumpQueriesOnException"), "3.1.3", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  809 */   private BooleanConnectionProperty dynamicCalendars = new BooleanConnectionProperty("dynamicCalendars", false, Messages.getString("ConnectionProperties.dynamicCalendars"), "3.1.5", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  812 */   private BooleanConnectionProperty elideSetAutoCommits = new BooleanConnectionProperty("elideSetAutoCommits", false, Messages.getString("ConnectionProperties.eliseSetAutoCommit"), "3.1.3", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  815 */   private BooleanConnectionProperty emptyStringsConvertToZero = new BooleanConnectionProperty("emptyStringsConvertToZero", true, Messages.getString("ConnectionProperties.emptyStringsConvertToZero"), "3.1.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  818 */   private BooleanConnectionProperty emulateLocators = new BooleanConnectionProperty("emulateLocators", false, Messages.getString("ConnectionProperties.emulateLocators"), "3.1.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  821 */   private BooleanConnectionProperty emulateUnsupportedPstmts = new BooleanConnectionProperty("emulateUnsupportedPstmts", true, Messages.getString("ConnectionProperties.emulateUnsupportedPstmts"), "3.1.7", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  824 */   private BooleanConnectionProperty enablePacketDebug = new BooleanConnectionProperty("enablePacketDebug", false, Messages.getString("ConnectionProperties.enablePacketDebug"), "3.1.3", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  827 */   private BooleanConnectionProperty enableQueryTimeouts = new BooleanConnectionProperty("enableQueryTimeouts", true, Messages.getString("ConnectionProperties.enableQueryTimeouts"), "5.0.6", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  830 */   private BooleanConnectionProperty explainSlowQueries = new BooleanConnectionProperty("explainSlowQueries", false, Messages.getString("ConnectionProperties.explainSlowQueries"), "3.1.2", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  833 */   private StringConnectionProperty exceptionInterceptors = new StringConnectionProperty("exceptionInterceptors", null, Messages.getString("ConnectionProperties.exceptionInterceptors"), "5.1.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/*  837 */   private BooleanConnectionProperty failOverReadOnly = new BooleanConnectionProperty("failOverReadOnly", true, Messages.getString("ConnectionProperties.failoverReadOnly"), "3.0.12", HA_CATEGORY, 2);
/*      */ 
/*      */   
/*  840 */   private BooleanConnectionProperty gatherPerformanceMetrics = new BooleanConnectionProperty("gatherPerfMetrics", false, Messages.getString("ConnectionProperties.gatherPerfMetrics"), "3.1.2", DEBUGING_PROFILING_CATEGORY, 1);
/*      */ 
/*      */   
/*  843 */   private BooleanConnectionProperty generateSimpleParameterMetadata = new BooleanConnectionProperty("generateSimpleParameterMetadata", false, Messages.getString("ConnectionProperties.generateSimpleParameterMetadata"), "5.0.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*      */   private boolean highAvailabilityAsBoolean = false;
/*      */   
/*  848 */   private BooleanConnectionProperty holdResultsOpenOverStatementClose = new BooleanConnectionProperty("holdResultsOpenOverStatementClose", false, Messages.getString("ConnectionProperties.holdRSOpenOverStmtClose"), "3.1.7", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  851 */   private BooleanConnectionProperty includeInnodbStatusInDeadlockExceptions = new BooleanConnectionProperty("includeInnodbStatusInDeadlockExceptions", false, Messages.getString("ConnectionProperties.includeInnodbStatusInDeadlockExceptions"), "5.0.7", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  854 */   private BooleanConnectionProperty includeThreadDumpInDeadlockExceptions = new BooleanConnectionProperty("includeThreadDumpInDeadlockExceptions", false, Messages.getString("ConnectionProperties.includeThreadDumpInDeadlockExceptions"), "5.1.15", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  857 */   private BooleanConnectionProperty includeThreadNamesAsStatementComment = new BooleanConnectionProperty("includeThreadNamesAsStatementComment", false, Messages.getString("ConnectionProperties.includeThreadNamesAsStatementComment"), "5.1.15", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  860 */   private BooleanConnectionProperty ignoreNonTxTables = new BooleanConnectionProperty("ignoreNonTxTables", false, Messages.getString("ConnectionProperties.ignoreNonTxTables"), "3.0.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  863 */   private IntegerConnectionProperty initialTimeout = new IntegerConnectionProperty("initialTimeout", 2, 1, 2147483647, Messages.getString("ConnectionProperties.initialTimeout"), "1.1", HA_CATEGORY, 5);
/*      */ 
/*      */   
/*  866 */   private BooleanConnectionProperty isInteractiveClient = new BooleanConnectionProperty("interactiveClient", false, Messages.getString("ConnectionProperties.interactiveClient"), "3.1.0", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  869 */   private BooleanConnectionProperty jdbcCompliantTruncation = new BooleanConnectionProperty("jdbcCompliantTruncation", true, Messages.getString("ConnectionProperties.jdbcCompliantTruncation"), "3.1.2", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  872 */   private boolean jdbcCompliantTruncationForReads = this.jdbcCompliantTruncation.getValueAsBoolean();
/*      */   
/*  874 */   protected MemorySizeConnectionProperty largeRowSizeThreshold = new MemorySizeConnectionProperty("largeRowSizeThreshold", 2048, 0, 2147483647, Messages.getString("ConnectionProperties.largeRowSizeThreshold"), "5.1.1", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  877 */   private StringConnectionProperty loadBalanceStrategy = new StringConnectionProperty("loadBalanceStrategy", "random", null, Messages.getString("ConnectionProperties.loadBalanceStrategy"), "5.0.6", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  880 */   private StringConnectionProperty serverAffinityOrder = new StringConnectionProperty("serverAffinityOrder", "", null, Messages.getString("ConnectionProperties.serverAffinityOrder"), "5.1.43", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  883 */   private IntegerConnectionProperty loadBalanceBlacklistTimeout = new IntegerConnectionProperty("loadBalanceBlacklistTimeout", 0, 0, 2147483647, Messages.getString("ConnectionProperties.loadBalanceBlacklistTimeout"), "5.1.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  886 */   private IntegerConnectionProperty loadBalancePingTimeout = new IntegerConnectionProperty("loadBalancePingTimeout", 0, 0, 2147483647, Messages.getString("ConnectionProperties.loadBalancePingTimeout"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  889 */   private BooleanConnectionProperty loadBalanceValidateConnectionOnSwapServer = new BooleanConnectionProperty("loadBalanceValidateConnectionOnSwapServer", false, Messages.getString("ConnectionProperties.loadBalanceValidateConnectionOnSwapServer"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  892 */   private StringConnectionProperty loadBalanceConnectionGroup = new StringConnectionProperty("loadBalanceConnectionGroup", null, Messages.getString("ConnectionProperties.loadBalanceConnectionGroup"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  895 */   private StringConnectionProperty loadBalanceExceptionChecker = new StringConnectionProperty("loadBalanceExceptionChecker", "com.mysql.jdbc.StandardLoadBalanceExceptionChecker", null, Messages.getString("ConnectionProperties.loadBalanceExceptionChecker"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/*  899 */   private StringConnectionProperty loadBalanceSQLStateFailover = new StringConnectionProperty("loadBalanceSQLStateFailover", null, Messages.getString("ConnectionProperties.loadBalanceSQLStateFailover"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  902 */   private StringConnectionProperty loadBalanceSQLExceptionSubclassFailover = new StringConnectionProperty("loadBalanceSQLExceptionSubclassFailover", null, Messages.getString("ConnectionProperties.loadBalanceSQLExceptionSubclassFailover"), "5.1.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  905 */   private BooleanConnectionProperty loadBalanceEnableJMX = new BooleanConnectionProperty("loadBalanceEnableJMX", false, Messages.getString("ConnectionProperties.loadBalanceEnableJMX"), "5.1.13", MISC_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  908 */   private IntegerConnectionProperty loadBalanceHostRemovalGracePeriod = new IntegerConnectionProperty("loadBalanceHostRemovalGracePeriod", 15000, 0, 2147483647, Messages.getString("ConnectionProperties.loadBalanceHostRemovalGracePeriod"), "5.1.39", MISC_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*  911 */   private StringConnectionProperty loadBalanceAutoCommitStatementRegex = new StringConnectionProperty("loadBalanceAutoCommitStatementRegex", null, Messages.getString("ConnectionProperties.loadBalanceAutoCommitStatementRegex"), "5.1.15", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  914 */   private IntegerConnectionProperty loadBalanceAutoCommitStatementThreshold = new IntegerConnectionProperty("loadBalanceAutoCommitStatementThreshold", 0, 0, 2147483647, Messages.getString("ConnectionProperties.loadBalanceAutoCommitStatementThreshold"), "5.1.15", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  917 */   private StringConnectionProperty localSocketAddress = new StringConnectionProperty("localSocketAddress", null, Messages.getString("ConnectionProperties.localSocketAddress"), "5.0.5", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  920 */   private MemorySizeConnectionProperty locatorFetchBufferSize = new MemorySizeConnectionProperty("locatorFetchBufferSize", 1048576, 0, 2147483647, Messages.getString("ConnectionProperties.locatorFetchBufferSize"), "3.2.1", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  923 */   private StringConnectionProperty loggerClassName = new StringConnectionProperty("logger", STANDARD_LOGGER_NAME, Messages.getString("ConnectionProperties.logger", new Object[] { Log.class.getName(), STANDARD_LOGGER_NAME }), "3.1.1", DEBUGING_PROFILING_CATEGORY, 0);
/*      */ 
/*      */ 
/*      */   
/*  927 */   private BooleanConnectionProperty logSlowQueries = new BooleanConnectionProperty("logSlowQueries", false, Messages.getString("ConnectionProperties.logSlowQueries"), "3.1.2", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  930 */   private BooleanConnectionProperty logXaCommands = new BooleanConnectionProperty("logXaCommands", false, Messages.getString("ConnectionProperties.logXaCommands"), "5.0.5", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  933 */   private BooleanConnectionProperty maintainTimeStats = new BooleanConnectionProperty("maintainTimeStats", true, Messages.getString("ConnectionProperties.maintainTimeStats"), "3.1.9", PERFORMANCE_CATEGORY, 2147483647);
/*      */ 
/*      */   
/*      */   private boolean maintainTimeStatsAsBoolean = true;
/*      */   
/*  938 */   private IntegerConnectionProperty maxQuerySizeToLog = new IntegerConnectionProperty("maxQuerySizeToLog", 2048, 0, 2147483647, Messages.getString("ConnectionProperties.maxQuerySizeToLog"), "3.1.3", DEBUGING_PROFILING_CATEGORY, 4);
/*      */ 
/*      */   
/*  941 */   private IntegerConnectionProperty maxReconnects = new IntegerConnectionProperty("maxReconnects", 3, 1, 2147483647, Messages.getString("ConnectionProperties.maxReconnects"), "1.1", HA_CATEGORY, 4);
/*      */ 
/*      */   
/*  944 */   private IntegerConnectionProperty retriesAllDown = new IntegerConnectionProperty("retriesAllDown", 120, 0, 2147483647, Messages.getString("ConnectionProperties.retriesAllDown"), "5.1.6", HA_CATEGORY, 4);
/*      */ 
/*      */   
/*  947 */   private IntegerConnectionProperty maxRows = new IntegerConnectionProperty("maxRows", -1, -1, 2147483647, Messages.getString("ConnectionProperties.maxRows"), Messages.getString("ConnectionProperties.allVersions"), MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  950 */   private int maxRowsAsInt = -1;
/*      */   
/*  952 */   private IntegerConnectionProperty metadataCacheSize = new IntegerConnectionProperty("metadataCacheSize", 50, 1, 2147483647, Messages.getString("ConnectionProperties.metadataCacheSize"), "3.1.1", PERFORMANCE_CATEGORY, 5);
/*      */ 
/*      */   
/*  955 */   private IntegerConnectionProperty netTimeoutForStreamingResults = new IntegerConnectionProperty("netTimeoutForStreamingResults", 600, 0, 2147483647, Messages.getString("ConnectionProperties.netTimeoutForStreamingResults"), "5.1.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  958 */   private BooleanConnectionProperty noAccessToProcedureBodies = new BooleanConnectionProperty("noAccessToProcedureBodies", false, "When determining procedure parameter types for CallableStatements, and the connected user  can't access procedure bodies through \"SHOW CREATE PROCEDURE\" or select on mysql.proc  should the driver instead create basic metadata (all parameters reported as IN VARCHARs, but allowing registerOutParameter() to be called on them anyway) instead of throwing an exception?", "5.0.3", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  965 */   private BooleanConnectionProperty noDatetimeStringSync = new BooleanConnectionProperty("noDatetimeStringSync", false, Messages.getString("ConnectionProperties.noDatetimeStringSync"), "3.1.7", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  968 */   private BooleanConnectionProperty noTimezoneConversionForTimeType = new BooleanConnectionProperty("noTimezoneConversionForTimeType", false, Messages.getString("ConnectionProperties.noTzConversionForTimeType"), "5.0.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  971 */   private BooleanConnectionProperty noTimezoneConversionForDateType = new BooleanConnectionProperty("noTimezoneConversionForDateType", true, Messages.getString("ConnectionProperties.noTzConversionForDateType"), "5.1.35", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  974 */   private BooleanConnectionProperty cacheDefaultTimezone = new BooleanConnectionProperty("cacheDefaultTimezone", true, Messages.getString("ConnectionProperties.cacheDefaultTimezone"), "5.1.35", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  977 */   private BooleanConnectionProperty nullCatalogMeansCurrent = new BooleanConnectionProperty("nullCatalogMeansCurrent", true, Messages.getString("ConnectionProperties.nullCatalogMeansCurrent"), "3.1.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  980 */   private BooleanConnectionProperty nullNamePatternMatchesAll = new BooleanConnectionProperty("nullNamePatternMatchesAll", true, Messages.getString("ConnectionProperties.nullNamePatternMatchesAll"), "3.1.8", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  983 */   private IntegerConnectionProperty packetDebugBufferSize = new IntegerConnectionProperty("packetDebugBufferSize", 20, 1, 2147483647, Messages.getString("ConnectionProperties.packetDebugBufferSize"), "3.1.3", DEBUGING_PROFILING_CATEGORY, 7);
/*      */ 
/*      */   
/*  986 */   private BooleanConnectionProperty padCharsWithSpace = new BooleanConnectionProperty("padCharsWithSpace", false, Messages.getString("ConnectionProperties.padCharsWithSpace"), "5.0.6", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  989 */   private BooleanConnectionProperty paranoid = new BooleanConnectionProperty("paranoid", false, Messages.getString("ConnectionProperties.paranoid"), "3.0.1", SECURITY_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  992 */   private BooleanConnectionProperty pedantic = new BooleanConnectionProperty("pedantic", false, Messages.getString("ConnectionProperties.pedantic"), "3.0.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  995 */   private BooleanConnectionProperty pinGlobalTxToPhysicalConnection = new BooleanConnectionProperty("pinGlobalTxToPhysicalConnection", false, Messages.getString("ConnectionProperties.pinGlobalTxToPhysicalConnection"), "5.0.1", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*  998 */   private BooleanConnectionProperty populateInsertRowWithDefaultValues = new BooleanConnectionProperty("populateInsertRowWithDefaultValues", false, Messages.getString("ConnectionProperties.populateInsertRowWithDefaultValues"), "5.0.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1001 */   private IntegerConnectionProperty preparedStatementCacheSize = new IntegerConnectionProperty("prepStmtCacheSize", 25, 0, 2147483647, Messages.getString("ConnectionProperties.prepStmtCacheSize"), "3.0.10", PERFORMANCE_CATEGORY, 10);
/*      */ 
/*      */   
/* 1004 */   private IntegerConnectionProperty preparedStatementCacheSqlLimit = new IntegerConnectionProperty("prepStmtCacheSqlLimit", 256, 1, 2147483647, Messages.getString("ConnectionProperties.prepStmtCacheSqlLimit"), "3.0.10", PERFORMANCE_CATEGORY, 11);
/*      */ 
/*      */   
/* 1007 */   private StringConnectionProperty parseInfoCacheFactory = new StringConnectionProperty("parseInfoCacheFactory", PerConnectionLRUFactory.class.getName(), Messages.getString("ConnectionProperties.parseInfoCacheFactory"), "5.1.1", PERFORMANCE_CATEGORY, 12);
/*      */ 
/*      */   
/* 1010 */   private BooleanConnectionProperty processEscapeCodesForPrepStmts = new BooleanConnectionProperty("processEscapeCodesForPrepStmts", true, Messages.getString("ConnectionProperties.processEscapeCodesForPrepStmts"), "3.1.12", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1013 */   private StringConnectionProperty profilerEventHandler = new StringConnectionProperty("profilerEventHandler", "com.mysql.jdbc.profiler.LoggingProfilerEventHandler", Messages.getString("ConnectionProperties.profilerEventHandler"), "5.1.6", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1017 */   private StringConnectionProperty profileSql = new StringConnectionProperty("profileSql", null, Messages.getString("ConnectionProperties.profileSqlDeprecated"), "2.0.14", DEBUGING_PROFILING_CATEGORY, 3);
/*      */ 
/*      */   
/* 1020 */   private BooleanConnectionProperty profileSQL = new BooleanConnectionProperty("profileSQL", false, Messages.getString("ConnectionProperties.profileSQL"), "3.1.0", DEBUGING_PROFILING_CATEGORY, 1);
/*      */ 
/*      */   
/*      */   private boolean profileSQLAsBoolean = false;
/*      */   
/* 1025 */   private StringConnectionProperty propertiesTransform = new StringConnectionProperty("propertiesTransform", null, Messages.getString("ConnectionProperties.connectionPropertiesTransform"), "3.1.4", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1028 */   private IntegerConnectionProperty queriesBeforeRetryMaster = new IntegerConnectionProperty("queriesBeforeRetryMaster", 50, 0, 2147483647, Messages.getString("ConnectionProperties.queriesBeforeRetryMaster"), "3.0.2", HA_CATEGORY, 7);
/*      */ 
/*      */   
/* 1031 */   private BooleanConnectionProperty queryTimeoutKillsConnection = new BooleanConnectionProperty("queryTimeoutKillsConnection", false, Messages.getString("ConnectionProperties.queryTimeoutKillsConnection"), "5.1.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1034 */   private BooleanConnectionProperty reconnectAtTxEnd = new BooleanConnectionProperty("reconnectAtTxEnd", false, Messages.getString("ConnectionProperties.reconnectAtTxEnd"), "3.0.10", HA_CATEGORY, 4);
/*      */ 
/*      */   
/*      */   private boolean reconnectTxAtEndAsBoolean = false;
/*      */   
/* 1039 */   private BooleanConnectionProperty relaxAutoCommit = new BooleanConnectionProperty("relaxAutoCommit", false, Messages.getString("ConnectionProperties.relaxAutoCommit"), "2.0.13", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1042 */   private IntegerConnectionProperty reportMetricsIntervalMillis = new IntegerConnectionProperty("reportMetricsIntervalMillis", 30000, 0, 2147483647, Messages.getString("ConnectionProperties.reportMetricsIntervalMillis"), "3.1.2", DEBUGING_PROFILING_CATEGORY, 3);
/*      */ 
/*      */   
/* 1045 */   private BooleanConnectionProperty requireSSL = new BooleanConnectionProperty("requireSSL", false, Messages.getString("ConnectionProperties.requireSSL"), "3.1.0", SECURITY_CATEGORY, 3);
/*      */ 
/*      */   
/* 1048 */   private StringConnectionProperty resourceId = new StringConnectionProperty("resourceId", null, Messages.getString("ConnectionProperties.resourceId"), "5.0.1", HA_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1051 */   private IntegerConnectionProperty resultSetSizeThreshold = new IntegerConnectionProperty("resultSetSizeThreshold", 100, Messages.getString("ConnectionProperties.resultSetSizeThreshold"), "5.0.5", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1054 */   private BooleanConnectionProperty retainStatementAfterResultSetClose = new BooleanConnectionProperty("retainStatementAfterResultSetClose", false, Messages.getString("ConnectionProperties.retainStatementAfterResultSetClose"), "3.1.11", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1057 */   private BooleanConnectionProperty rewriteBatchedStatements = new BooleanConnectionProperty("rewriteBatchedStatements", false, Messages.getString("ConnectionProperties.rewriteBatchedStatements"), "3.1.13", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1060 */   private BooleanConnectionProperty rollbackOnPooledClose = new BooleanConnectionProperty("rollbackOnPooledClose", true, Messages.getString("ConnectionProperties.rollbackOnPooledClose"), "3.0.15", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1063 */   private BooleanConnectionProperty roundRobinLoadBalance = new BooleanConnectionProperty("roundRobinLoadBalance", false, Messages.getString("ConnectionProperties.roundRobinLoadBalance"), "3.1.2", HA_CATEGORY, 5);
/*      */ 
/*      */   
/* 1066 */   private BooleanConnectionProperty runningCTS13 = new BooleanConnectionProperty("runningCTS13", false, Messages.getString("ConnectionProperties.runningCTS13"), "3.1.7", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1069 */   private IntegerConnectionProperty secondsBeforeRetryMaster = new IntegerConnectionProperty("secondsBeforeRetryMaster", 30, 0, 2147483647, Messages.getString("ConnectionProperties.secondsBeforeRetryMaster"), "3.0.2", HA_CATEGORY, 8);
/*      */ 
/*      */   
/* 1072 */   private IntegerConnectionProperty selfDestructOnPingSecondsLifetime = new IntegerConnectionProperty("selfDestructOnPingSecondsLifetime", 0, 0, 2147483647, Messages.getString("ConnectionProperties.selfDestructOnPingSecondsLifetime"), "5.1.6", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1075 */   private IntegerConnectionProperty selfDestructOnPingMaxOperations = new IntegerConnectionProperty("selfDestructOnPingMaxOperations", 0, 0, 2147483647, Messages.getString("ConnectionProperties.selfDestructOnPingMaxOperations"), "5.1.6", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1078 */   private BooleanConnectionProperty replicationEnableJMX = new BooleanConnectionProperty("replicationEnableJMX", false, Messages.getString("ConnectionProperties.loadBalanceEnableJMX"), "5.1.27", HA_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1081 */   private StringConnectionProperty serverTimezone = new StringConnectionProperty("serverTimezone", null, Messages.getString("ConnectionProperties.serverTimezone"), "3.0.2", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1084 */   private StringConnectionProperty sessionVariables = new StringConnectionProperty("sessionVariables", null, Messages.getString("ConnectionProperties.sessionVariables"), "3.1.8", MISC_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1087 */   private IntegerConnectionProperty slowQueryThresholdMillis = new IntegerConnectionProperty("slowQueryThresholdMillis", 2000, 0, 2147483647, Messages.getString("ConnectionProperties.slowQueryThresholdMillis"), "3.1.2", DEBUGING_PROFILING_CATEGORY, 9);
/*      */ 
/*      */   
/* 1090 */   private LongConnectionProperty slowQueryThresholdNanos = new LongConnectionProperty("slowQueryThresholdNanos", 0L, Messages.getString("ConnectionProperties.slowQueryThresholdNanos"), "5.0.7", DEBUGING_PROFILING_CATEGORY, 10);
/*      */ 
/*      */   
/* 1093 */   private StringConnectionProperty socketFactoryClassName = new StringConnectionProperty("socketFactory", StandardSocketFactory.class.getName(), Messages.getString("ConnectionProperties.socketFactory"), "3.0.3", CONNECTION_AND_AUTH_CATEGORY, 4);
/*      */ 
/*      */   
/* 1096 */   private StringConnectionProperty socksProxyHost = new StringConnectionProperty("socksProxyHost", null, Messages.getString("ConnectionProperties.socksProxyHost"), "5.1.34", NETWORK_CATEGORY, 1);
/*      */ 
/*      */   
/* 1099 */   private IntegerConnectionProperty socksProxyPort = new IntegerConnectionProperty("socksProxyPort", SocksProxySocketFactory.SOCKS_DEFAULT_PORT, 0, 65535, Messages.getString("ConnectionProperties.socksProxyPort"), "5.1.34", NETWORK_CATEGORY, 2);
/*      */ 
/*      */   
/* 1102 */   private IntegerConnectionProperty socketTimeout = new IntegerConnectionProperty("socketTimeout", 0, 0, 2147483647, Messages.getString("ConnectionProperties.socketTimeout"), "3.0.1", CONNECTION_AND_AUTH_CATEGORY, 10);
/*      */ 
/*      */   
/* 1105 */   private StringConnectionProperty statementInterceptors = new StringConnectionProperty("statementInterceptors", null, Messages.getString("ConnectionProperties.statementInterceptors"), "5.1.1", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1108 */   private BooleanConnectionProperty strictFloatingPoint = new BooleanConnectionProperty("strictFloatingPoint", false, Messages.getString("ConnectionProperties.strictFloatingPoint"), "3.0.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1111 */   private BooleanConnectionProperty strictUpdates = new BooleanConnectionProperty("strictUpdates", true, Messages.getString("ConnectionProperties.strictUpdates"), "3.0.4", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1114 */   private BooleanConnectionProperty overrideSupportsIntegrityEnhancementFacility = new BooleanConnectionProperty("overrideSupportsIntegrityEnhancementFacility", false, Messages.getString("ConnectionProperties.overrideSupportsIEF"), "3.1.12", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1118 */   private BooleanConnectionProperty tcpNoDelay = new BooleanConnectionProperty("tcpNoDelay", Boolean.valueOf("true").booleanValue(), Messages.getString("ConnectionProperties.tcpNoDelay"), "5.0.7", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1122 */   private BooleanConnectionProperty tcpKeepAlive = new BooleanConnectionProperty("tcpKeepAlive", Boolean.valueOf("true").booleanValue(), Messages.getString("ConnectionProperties.tcpKeepAlive"), "5.0.7", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1126 */   private IntegerConnectionProperty tcpRcvBuf = new IntegerConnectionProperty("tcpRcvBuf", Integer.parseInt("0"), 0, 2147483647, Messages.getString("ConnectionProperties.tcpSoRcvBuf"), "5.0.7", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1130 */   private IntegerConnectionProperty tcpSndBuf = new IntegerConnectionProperty("tcpSndBuf", Integer.parseInt("0"), 0, 2147483647, Messages.getString("ConnectionProperties.tcpSoSndBuf"), "5.0.7", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1134 */   private IntegerConnectionProperty tcpTrafficClass = new IntegerConnectionProperty("tcpTrafficClass", Integer.parseInt("0"), 0, 255, Messages.getString("ConnectionProperties.tcpTrafficClass"), "5.0.7", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1138 */   private BooleanConnectionProperty tinyInt1isBit = new BooleanConnectionProperty("tinyInt1isBit", true, Messages.getString("ConnectionProperties.tinyInt1isBit"), "3.0.16", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1141 */   protected BooleanConnectionProperty traceProtocol = new BooleanConnectionProperty("traceProtocol", false, Messages.getString("ConnectionProperties.traceProtocol"), "3.1.2", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1144 */   private BooleanConnectionProperty treatUtilDateAsTimestamp = new BooleanConnectionProperty("treatUtilDateAsTimestamp", true, Messages.getString("ConnectionProperties.treatUtilDateAsTimestamp"), "5.0.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1147 */   private BooleanConnectionProperty transformedBitIsBoolean = new BooleanConnectionProperty("transformedBitIsBoolean", false, Messages.getString("ConnectionProperties.transformedBitIsBoolean"), "3.1.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1150 */   private BooleanConnectionProperty useBlobToStoreUTF8OutsideBMP = new BooleanConnectionProperty("useBlobToStoreUTF8OutsideBMP", false, Messages.getString("ConnectionProperties.useBlobToStoreUTF8OutsideBMP"), "5.1.3", MISC_CATEGORY, 128);
/*      */ 
/*      */   
/* 1153 */   private StringConnectionProperty utf8OutsideBmpExcludedColumnNamePattern = new StringConnectionProperty("utf8OutsideBmpExcludedColumnNamePattern", null, Messages.getString("ConnectionProperties.utf8OutsideBmpExcludedColumnNamePattern"), "5.1.3", MISC_CATEGORY, 129);
/*      */ 
/*      */   
/* 1156 */   private StringConnectionProperty utf8OutsideBmpIncludedColumnNamePattern = new StringConnectionProperty("utf8OutsideBmpIncludedColumnNamePattern", null, Messages.getString("ConnectionProperties.utf8OutsideBmpIncludedColumnNamePattern"), "5.1.3", MISC_CATEGORY, 129);
/*      */ 
/*      */   
/* 1159 */   private BooleanConnectionProperty useCompression = new BooleanConnectionProperty("useCompression", false, Messages.getString("ConnectionProperties.useCompression"), "3.0.17", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1162 */   private BooleanConnectionProperty useColumnNamesInFindColumn = new BooleanConnectionProperty("useColumnNamesInFindColumn", false, Messages.getString("ConnectionProperties.useColumnNamesInFindColumn"), "5.1.7", MISC_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1165 */   private StringConnectionProperty useConfigs = new StringConnectionProperty("useConfigs", null, Messages.getString("ConnectionProperties.useConfigs"), "3.1.5", CONNECTION_AND_AUTH_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1168 */   private BooleanConnectionProperty useCursorFetch = new BooleanConnectionProperty("useCursorFetch", false, Messages.getString("ConnectionProperties.useCursorFetch"), "5.0.0", PERFORMANCE_CATEGORY, 2147483647);
/*      */ 
/*      */   
/* 1171 */   private BooleanConnectionProperty useDynamicCharsetInfo = new BooleanConnectionProperty("useDynamicCharsetInfo", true, Messages.getString("ConnectionProperties.useDynamicCharsetInfo"), "5.0.6", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1174 */   private BooleanConnectionProperty useDirectRowUnpack = new BooleanConnectionProperty("useDirectRowUnpack", true, "Use newer result set row unpacking code that skips a copy from network buffers  to a MySQL packet instance and instead reads directly into the result set row data buffers.", "5.1.1", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1179 */   private BooleanConnectionProperty useFastIntParsing = new BooleanConnectionProperty("useFastIntParsing", true, Messages.getString("ConnectionProperties.useFastIntParsing"), "3.1.4", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1182 */   private BooleanConnectionProperty useFastDateParsing = new BooleanConnectionProperty("useFastDateParsing", true, Messages.getString("ConnectionProperties.useFastDateParsing"), "5.0.5", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1185 */   private BooleanConnectionProperty useHostsInPrivileges = new BooleanConnectionProperty("useHostsInPrivileges", true, Messages.getString("ConnectionProperties.useHostsInPrivileges"), "3.0.2", MISC_CATEGORY, -2147483648);
/*      */   
/* 1187 */   private BooleanConnectionProperty useInformationSchema = new BooleanConnectionProperty("useInformationSchema", false, Messages.getString("ConnectionProperties.useInformationSchema"), "5.0.0", MISC_CATEGORY, -2147483648);
/*      */   
/* 1189 */   private BooleanConnectionProperty useJDBCCompliantTimezoneShift = new BooleanConnectionProperty("useJDBCCompliantTimezoneShift", false, Messages.getString("ConnectionProperties.useJDBCCompliantTimezoneShift"), "5.0.0", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1192 */   private BooleanConnectionProperty useLocalSessionState = new BooleanConnectionProperty("useLocalSessionState", false, Messages.getString("ConnectionProperties.useLocalSessionState"), "3.1.7", PERFORMANCE_CATEGORY, 5);
/*      */ 
/*      */   
/* 1195 */   private BooleanConnectionProperty useLocalTransactionState = new BooleanConnectionProperty("useLocalTransactionState", false, Messages.getString("ConnectionProperties.useLocalTransactionState"), "5.1.7", PERFORMANCE_CATEGORY, 6);
/*      */ 
/*      */   
/* 1198 */   private BooleanConnectionProperty useLegacyDatetimeCode = new BooleanConnectionProperty("useLegacyDatetimeCode", true, Messages.getString("ConnectionProperties.useLegacyDatetimeCode"), "5.1.6", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1201 */   private BooleanConnectionProperty sendFractionalSeconds = new BooleanConnectionProperty("sendFractionalSeconds", true, Messages.getString("ConnectionProperties.sendFractionalSeconds"), "5.1.37", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1204 */   private BooleanConnectionProperty useNanosForElapsedTime = new BooleanConnectionProperty("useNanosForElapsedTime", false, Messages.getString("ConnectionProperties.useNanosForElapsedTime"), "5.0.7", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1207 */   private BooleanConnectionProperty useOldAliasMetadataBehavior = new BooleanConnectionProperty("useOldAliasMetadataBehavior", false, Messages.getString("ConnectionProperties.useOldAliasMetadataBehavior"), "5.0.4", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1210 */   private BooleanConnectionProperty useOldUTF8Behavior = new BooleanConnectionProperty("useOldUTF8Behavior", false, Messages.getString("ConnectionProperties.useOldUtf8Behavior"), "3.1.6", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*      */   private boolean useOldUTF8BehaviorAsBoolean = false;
/*      */   
/* 1215 */   private BooleanConnectionProperty useOnlyServerErrorMessages = new BooleanConnectionProperty("useOnlyServerErrorMessages", true, Messages.getString("ConnectionProperties.useOnlyServerErrorMessages"), "3.0.15", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1218 */   private BooleanConnectionProperty useReadAheadInput = new BooleanConnectionProperty("useReadAheadInput", true, Messages.getString("ConnectionProperties.useReadAheadInput"), "3.1.5", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1221 */   private BooleanConnectionProperty useSqlStateCodes = new BooleanConnectionProperty("useSqlStateCodes", true, Messages.getString("ConnectionProperties.useSqlStateCodes"), "3.1.3", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1224 */   private BooleanConnectionProperty useSSL = new BooleanConnectionProperty("useSSL", false, Messages.getString("ConnectionProperties.useSSL"), "3.0.2", SECURITY_CATEGORY, 2);
/*      */ 
/*      */   
/* 1227 */   private BooleanConnectionProperty useSSPSCompatibleTimezoneShift = new BooleanConnectionProperty("useSSPSCompatibleTimezoneShift", false, Messages.getString("ConnectionProperties.useSSPSCompatibleTimezoneShift"), "5.0.5", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1230 */   private BooleanConnectionProperty useStreamLengthsInPrepStmts = new BooleanConnectionProperty("useStreamLengthsInPrepStmts", true, Messages.getString("ConnectionProperties.useStreamLengthsInPrepStmts"), "3.0.2", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1233 */   private BooleanConnectionProperty useTimezone = new BooleanConnectionProperty("useTimezone", false, Messages.getString("ConnectionProperties.useTimezone"), "3.0.2", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1236 */   private BooleanConnectionProperty useUltraDevWorkAround = new BooleanConnectionProperty("ultraDevHack", false, Messages.getString("ConnectionProperties.ultraDevHack"), "2.0.3", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1239 */   private BooleanConnectionProperty useUnbufferedInput = new BooleanConnectionProperty("useUnbufferedInput", true, Messages.getString("ConnectionProperties.useUnbufferedInput"), "3.0.11", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1242 */   private BooleanConnectionProperty useUnicode = new BooleanConnectionProperty("useUnicode", true, Messages.getString("ConnectionProperties.useUnicode"), "1.1g", MISC_CATEGORY, 0);
/*      */ 
/*      */   
/*      */   private boolean useUnicodeAsBoolean = true;
/*      */ 
/*      */   
/* 1248 */   private BooleanConnectionProperty useUsageAdvisor = new BooleanConnectionProperty("useUsageAdvisor", false, Messages.getString("ConnectionProperties.useUsageAdvisor"), "3.1.1", DEBUGING_PROFILING_CATEGORY, 10);
/*      */ 
/*      */   
/*      */   private boolean useUsageAdvisorAsBoolean = false;
/*      */   
/* 1253 */   private BooleanConnectionProperty yearIsDateType = new BooleanConnectionProperty("yearIsDateType", true, Messages.getString("ConnectionProperties.yearIsDateType"), "3.1.9", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1256 */   private StringConnectionProperty zeroDateTimeBehavior = new StringConnectionProperty("zeroDateTimeBehavior", "exception", new String[] { "exception", "round", "convertToNull" }, Messages.getString("ConnectionProperties.zeroDateTimeBehavior", new Object[] { "exception", "round", "convertToNull" }), "3.1.4", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1262 */   private BooleanConnectionProperty useJvmCharsetConverters = new BooleanConnectionProperty("useJvmCharsetConverters", false, Messages.getString("ConnectionProperties.useJvmCharsetConverters"), "5.0.1", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1265 */   private BooleanConnectionProperty useGmtMillisForDatetimes = new BooleanConnectionProperty("useGmtMillisForDatetimes", false, Messages.getString("ConnectionProperties.useGmtMillisForDatetimes"), "3.1.12", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1268 */   private BooleanConnectionProperty dumpMetadataOnColumnNotFound = new BooleanConnectionProperty("dumpMetadataOnColumnNotFound", false, Messages.getString("ConnectionProperties.dumpMetadataOnColumnNotFound"), "3.1.13", DEBUGING_PROFILING_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 1273 */   private StringConnectionProperty clientCertificateKeyStoreUrl = new StringConnectionProperty("clientCertificateKeyStoreUrl", null, Messages.getString("ConnectionProperties.clientCertificateKeyStoreUrl"), "5.1.0", SECURITY_CATEGORY, 5);
/*      */ 
/*      */   
/* 1276 */   private StringConnectionProperty trustCertificateKeyStoreUrl = new StringConnectionProperty("trustCertificateKeyStoreUrl", null, Messages.getString("ConnectionProperties.trustCertificateKeyStoreUrl"), "5.1.0", SECURITY_CATEGORY, 8);
/*      */ 
/*      */   
/* 1279 */   private StringConnectionProperty clientCertificateKeyStoreType = new StringConnectionProperty("clientCertificateKeyStoreType", "JKS", Messages.getString("ConnectionProperties.clientCertificateKeyStoreType"), "5.1.0", SECURITY_CATEGORY, 6);
/*      */ 
/*      */   
/* 1282 */   private StringConnectionProperty clientCertificateKeyStorePassword = new StringConnectionProperty("clientCertificateKeyStorePassword", null, Messages.getString("ConnectionProperties.clientCertificateKeyStorePassword"), "5.1.0", SECURITY_CATEGORY, 7);
/*      */ 
/*      */   
/* 1285 */   private StringConnectionProperty trustCertificateKeyStoreType = new StringConnectionProperty("trustCertificateKeyStoreType", "JKS", Messages.getString("ConnectionProperties.trustCertificateKeyStoreType"), "5.1.0", SECURITY_CATEGORY, 9);
/*      */ 
/*      */   
/* 1288 */   private StringConnectionProperty trustCertificateKeyStorePassword = new StringConnectionProperty("trustCertificateKeyStorePassword", null, Messages.getString("ConnectionProperties.trustCertificateKeyStorePassword"), "5.1.0", SECURITY_CATEGORY, 10);
/*      */ 
/*      */   
/* 1291 */   private BooleanConnectionProperty verifyServerCertificate = new BooleanConnectionProperty("verifyServerCertificate", true, Messages.getString("ConnectionProperties.verifyServerCertificate"), "5.1.6", SECURITY_CATEGORY, 4);
/*      */ 
/*      */   
/* 1294 */   private BooleanConnectionProperty useAffectedRows = new BooleanConnectionProperty("useAffectedRows", false, Messages.getString("ConnectionProperties.useAffectedRows"), "5.1.7", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1297 */   private StringConnectionProperty passwordCharacterEncoding = new StringConnectionProperty("passwordCharacterEncoding", null, Messages.getString("ConnectionProperties.passwordCharacterEncoding"), "5.1.7", SECURITY_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1300 */   private IntegerConnectionProperty maxAllowedPacket = new IntegerConnectionProperty("maxAllowedPacket", -1, Messages.getString("ConnectionProperties.maxAllowedPacket"), "5.1.8", NETWORK_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1303 */   private StringConnectionProperty authenticationPlugins = new StringConnectionProperty("authenticationPlugins", null, Messages.getString("ConnectionProperties.authenticationPlugins"), "5.1.19", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1306 */   private StringConnectionProperty disabledAuthenticationPlugins = new StringConnectionProperty("disabledAuthenticationPlugins", null, Messages.getString("ConnectionProperties.disabledAuthenticationPlugins"), "5.1.19", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1309 */   private StringConnectionProperty defaultAuthenticationPlugin = new StringConnectionProperty("defaultAuthenticationPlugin", "com.mysql.jdbc.authentication.MysqlNativePasswordPlugin", Messages.getString("ConnectionProperties.defaultAuthenticationPlugin"), "5.1.19", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */ 
/*      */   
/* 1313 */   private BooleanConnectionProperty disconnectOnExpiredPasswords = new BooleanConnectionProperty("disconnectOnExpiredPasswords", true, Messages.getString("ConnectionProperties.disconnectOnExpiredPasswords"), "5.1.23", CONNECTION_AND_AUTH_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1316 */   private BooleanConnectionProperty getProceduresReturnsFunctions = new BooleanConnectionProperty("getProceduresReturnsFunctions", true, Messages.getString("ConnectionProperties.getProceduresReturnsFunctions"), "5.1.26", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1319 */   private BooleanConnectionProperty detectCustomCollations = new BooleanConnectionProperty("detectCustomCollations", false, Messages.getString("ConnectionProperties.detectCustomCollations"), "5.1.29", MISC_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1322 */   private StringConnectionProperty serverRSAPublicKeyFile = new StringConnectionProperty("serverRSAPublicKeyFile", null, Messages.getString("ConnectionProperties.serverRSAPublicKeyFile"), "5.1.31", SECURITY_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1325 */   private BooleanConnectionProperty allowPublicKeyRetrieval = new BooleanConnectionProperty("allowPublicKeyRetrieval", false, Messages.getString("ConnectionProperties.allowPublicKeyRetrieval"), "5.1.31", SECURITY_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1328 */   private BooleanConnectionProperty dontCheckOnDuplicateKeyUpdateInSQL = new BooleanConnectionProperty("dontCheckOnDuplicateKeyUpdateInSQL", false, Messages.getString("ConnectionProperties.dontCheckOnDuplicateKeyUpdateInSQL"), "5.1.32", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1331 */   private BooleanConnectionProperty readOnlyPropagatesToServer = new BooleanConnectionProperty("readOnlyPropagatesToServer", true, Messages.getString("ConnectionProperties.readOnlyPropagatesToServer"), "5.1.35", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/* 1334 */   private StringConnectionProperty enabledSSLCipherSuites = new StringConnectionProperty("enabledSSLCipherSuites", null, Messages.getString("ConnectionProperties.enabledSSLCipherSuites"), "5.1.35", SECURITY_CATEGORY, 11);
/*      */ 
/*      */   
/* 1337 */   private StringConnectionProperty enabledTLSProtocols = new StringConnectionProperty("enabledTLSProtocols", null, Messages.getString("ConnectionProperties.enabledTLSProtocols"), "5.1.44", SECURITY_CATEGORY, 12);
/*      */ 
/*      */   
/* 1340 */   private BooleanConnectionProperty enableEscapeProcessing = new BooleanConnectionProperty("enableEscapeProcessing", true, Messages.getString("ConnectionProperties.enableEscapeProcessing"), "5.1.37", PERFORMANCE_CATEGORY, -2147483648);
/*      */ 
/*      */   
/*      */   protected DriverPropertyInfo[] exposeAsDriverPropertyInfoInternal(Properties info, int slotsToReserve) throws SQLException {
/* 1344 */     initializeProperties(info);
/*      */     
/* 1346 */     int numProperties = PROPERTY_LIST.size();
/*      */     
/* 1348 */     int listSize = numProperties + slotsToReserve;
/*      */     
/* 1350 */     DriverPropertyInfo[] driverProperties = new DriverPropertyInfo[listSize];
/*      */     
/* 1352 */     for (int i = slotsToReserve; i < listSize; i++) {
/* 1353 */       Field propertyField = PROPERTY_LIST.get(i - slotsToReserve);
/*      */       
/*      */       try {
/* 1356 */         ConnectionProperty propToExpose = (ConnectionProperty)propertyField.get(this);
/*      */         
/* 1358 */         if (info != null) {
/* 1359 */           propToExpose.initializeFrom(info, getExceptionInterceptor());
/*      */         }
/*      */         
/* 1362 */         driverProperties[i] = propToExpose.getAsDriverPropertyInfo();
/* 1363 */       } catch (IllegalAccessException iae) {
/* 1364 */         throw SQLError.createSQLException(Messages.getString("ConnectionProperties.InternalPropertiesFailure"), "S1000", getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1369 */     return driverProperties;
/*      */   }
/*      */   
/*      */   protected Properties exposeAsProperties(Properties info) throws SQLException {
/* 1373 */     if (info == null) {
/* 1374 */       info = new Properties();
/*      */     }
/*      */     
/* 1377 */     int numPropertiesToSet = PROPERTY_LIST.size();
/*      */     
/* 1379 */     for (int i = 0; i < numPropertiesToSet; i++) {
/* 1380 */       Field propertyField = PROPERTY_LIST.get(i);
/*      */       
/*      */       try {
/* 1383 */         ConnectionProperty propToGet = (ConnectionProperty)propertyField.get(this);
/*      */         
/* 1385 */         Object propValue = propToGet.getValueAsObject();
/*      */         
/* 1387 */         if (propValue != null) {
/* 1388 */           info.setProperty(propToGet.getPropertyName(), propValue.toString());
/*      */         }
/* 1390 */       } catch (IllegalAccessException iae) {
/* 1391 */         throw SQLError.createSQLException("Internal properties failure", "S1000", getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */     
/* 1395 */     return info;
/*      */   }
/*      */   
/*      */   public Properties exposeAsProperties(Properties props, boolean explicitOnly) throws SQLException {
/* 1399 */     if (props == null) {
/* 1400 */       props = new Properties();
/*      */     }
/*      */     
/* 1403 */     int numPropertiesToSet = PROPERTY_LIST.size();
/*      */     
/* 1405 */     for (int i = 0; i < numPropertiesToSet; i++) {
/* 1406 */       Field propertyField = PROPERTY_LIST.get(i);
/*      */       
/*      */       try {
/* 1409 */         ConnectionProperty propToGet = (ConnectionProperty)propertyField.get(this);
/*      */         
/* 1411 */         Object propValue = propToGet.getValueAsObject();
/*      */         
/* 1413 */         if (propValue != null && (!explicitOnly || propToGet.isExplicitlySet())) {
/* 1414 */           props.setProperty(propToGet.getPropertyName(), propValue.toString());
/*      */         }
/* 1416 */       } catch (IllegalAccessException iae) {
/* 1417 */         throw SQLError.createSQLException("Internal properties failure", "S1000", iae, getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */     
/* 1421 */     return props;
/*      */   }
/*      */   
/*      */   class XmlMap {
/* 1425 */     protected Map<Integer, Map<String, ConnectionPropertiesImpl.ConnectionProperty>> ordered = new TreeMap<Integer, Map<String, ConnectionPropertiesImpl.ConnectionProperty>>();
/* 1426 */     protected Map<String, ConnectionPropertiesImpl.ConnectionProperty> alpha = new TreeMap<String, ConnectionPropertiesImpl.ConnectionProperty>();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String exposeAsXml() throws SQLException {
/* 1435 */     StringBuilder xmlBuf = new StringBuilder();
/* 1436 */     xmlBuf.append("<ConnectionProperties>");
/*      */     
/* 1438 */     int numPropertiesToSet = PROPERTY_LIST.size();
/*      */     
/* 1440 */     int numCategories = PROPERTY_CATEGORIES.length;
/*      */     
/* 1442 */     Map<String, XmlMap> propertyListByCategory = new HashMap<String, XmlMap>();
/*      */     
/* 1444 */     for (int i = 0; i < numCategories; i++) {
/* 1445 */       propertyListByCategory.put(PROPERTY_CATEGORIES[i], new XmlMap());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1452 */     StringConnectionProperty userProp = new StringConnectionProperty("user", null, Messages.getString("ConnectionProperties.Username"), Messages.getString("ConnectionProperties.allVersions"), CONNECTION_AND_AUTH_CATEGORY, -2147483647);
/*      */ 
/*      */     
/* 1455 */     StringConnectionProperty passwordProp = new StringConnectionProperty("password", null, Messages.getString("ConnectionProperties.Password"), Messages.getString("ConnectionProperties.allVersions"), CONNECTION_AND_AUTH_CATEGORY, -2147483646);
/*      */ 
/*      */ 
/*      */     
/* 1459 */     XmlMap connectionSortMaps = propertyListByCategory.get(CONNECTION_AND_AUTH_CATEGORY);
/* 1460 */     TreeMap<String, ConnectionProperty> userMap = new TreeMap<String, ConnectionProperty>();
/* 1461 */     userMap.put(userProp.getPropertyName(), userProp);
/*      */     
/* 1463 */     connectionSortMaps.ordered.put(Integer.valueOf(userProp.getOrder()), userMap);
/*      */     
/* 1465 */     TreeMap<String, ConnectionProperty> passwordMap = new TreeMap<String, ConnectionProperty>();
/* 1466 */     passwordMap.put(passwordProp.getPropertyName(), passwordProp);
/*      */     
/* 1468 */     connectionSortMaps.ordered.put(new Integer(passwordProp.getOrder()), passwordMap);
/*      */     
/*      */     try {
/* 1471 */       for (int k = 0; k < numPropertiesToSet; k++) {
/* 1472 */         Field propertyField = PROPERTY_LIST.get(k);
/* 1473 */         ConnectionProperty propToGet = (ConnectionProperty)propertyField.get(this);
/* 1474 */         XmlMap sortMaps = propertyListByCategory.get(propToGet.getCategoryName());
/* 1475 */         int orderInCategory = propToGet.getOrder();
/*      */         
/* 1477 */         if (orderInCategory == Integer.MIN_VALUE) {
/* 1478 */           sortMaps.alpha.put(propToGet.getPropertyName(), propToGet);
/*      */         } else {
/* 1480 */           Integer order = Integer.valueOf(orderInCategory);
/* 1481 */           Map<String, ConnectionProperty> orderMap = sortMaps.ordered.get(order);
/*      */           
/* 1483 */           if (orderMap == null) {
/* 1484 */             orderMap = new TreeMap<String, ConnectionProperty>();
/* 1485 */             sortMaps.ordered.put(order, orderMap);
/*      */           } 
/*      */           
/* 1488 */           orderMap.put(propToGet.getPropertyName(), propToGet);
/*      */         } 
/*      */       } 
/*      */       
/* 1492 */       for (int j = 0; j < numCategories; j++) {
/* 1493 */         XmlMap sortMaps = propertyListByCategory.get(PROPERTY_CATEGORIES[j]);
/*      */         
/* 1495 */         xmlBuf.append("\n <PropertyCategory name=\"");
/* 1496 */         xmlBuf.append(PROPERTY_CATEGORIES[j]);
/* 1497 */         xmlBuf.append("\">");
/*      */         
/* 1499 */         for (Map<String, ConnectionProperty> orderedEl : sortMaps.ordered.values()) {
/* 1500 */           for (ConnectionProperty propToGet : orderedEl.values()) {
/* 1501 */             xmlBuf.append("\n  <Property name=\"");
/* 1502 */             xmlBuf.append(propToGet.getPropertyName());
/* 1503 */             xmlBuf.append("\" required=\"");
/* 1504 */             xmlBuf.append(propToGet.required ? "Yes" : "No");
/*      */             
/* 1506 */             xmlBuf.append("\" default=\"");
/*      */             
/* 1508 */             if (propToGet.getDefaultValue() != null) {
/* 1509 */               xmlBuf.append(propToGet.getDefaultValue());
/*      */             }
/*      */             
/* 1512 */             xmlBuf.append("\" sortOrder=\"");
/* 1513 */             xmlBuf.append(propToGet.getOrder());
/* 1514 */             xmlBuf.append("\" since=\"");
/* 1515 */             xmlBuf.append(propToGet.sinceVersion);
/* 1516 */             xmlBuf.append("\">\n");
/* 1517 */             xmlBuf.append("    ");
/* 1518 */             String escapedDescription = propToGet.description;
/* 1519 */             escapedDescription = escapedDescription.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
/*      */             
/* 1521 */             xmlBuf.append(escapedDescription);
/* 1522 */             xmlBuf.append("\n  </Property>");
/*      */           } 
/*      */         } 
/*      */         
/* 1526 */         for (ConnectionProperty propToGet : sortMaps.alpha.values()) {
/* 1527 */           xmlBuf.append("\n  <Property name=\"");
/* 1528 */           xmlBuf.append(propToGet.getPropertyName());
/* 1529 */           xmlBuf.append("\" required=\"");
/* 1530 */           xmlBuf.append(propToGet.required ? "Yes" : "No");
/*      */           
/* 1532 */           xmlBuf.append("\" default=\"");
/*      */           
/* 1534 */           if (propToGet.getDefaultValue() != null) {
/* 1535 */             xmlBuf.append(propToGet.getDefaultValue());
/*      */           }
/*      */           
/* 1538 */           xmlBuf.append("\" sortOrder=\"alpha\" since=\"");
/* 1539 */           xmlBuf.append(propToGet.sinceVersion);
/* 1540 */           xmlBuf.append("\">\n");
/* 1541 */           xmlBuf.append("    ");
/* 1542 */           xmlBuf.append(propToGet.description);
/* 1543 */           xmlBuf.append("\n  </Property>");
/*      */         } 
/*      */         
/* 1546 */         xmlBuf.append("\n </PropertyCategory>");
/*      */       } 
/* 1548 */     } catch (IllegalAccessException iae) {
/* 1549 */       throw SQLError.createSQLException("Internal properties failure", "S1000", getExceptionInterceptor());
/*      */     } 
/*      */     
/* 1552 */     xmlBuf.append("\n</ConnectionProperties>");
/*      */     
/* 1554 */     return xmlBuf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAllowLoadLocalInfile() {
/* 1563 */     return this.allowLoadLocalInfile.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAllowMultiQueries() {
/* 1572 */     return this.allowMultiQueries.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAllowNanAndInf() {
/* 1581 */     return this.allowNanAndInf.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAllowUrlInLocalInfile() {
/* 1590 */     return this.allowUrlInLocalInfile.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAlwaysSendSetIsolation() {
/* 1599 */     return this.alwaysSendSetIsolation.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoDeserialize() {
/* 1608 */     return this.autoDeserialize.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoGenerateTestcaseScript() {
/* 1617 */     return this.autoGenerateTestcaseScriptAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoReconnectForPools() {
/* 1626 */     return this.autoReconnectForPoolsAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBlobSendChunkSize() {
/* 1635 */     return this.blobSendChunkSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCacheCallableStatements() {
/* 1644 */     return this.cacheCallableStatements.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCachePreparedStatements() {
/* 1653 */     return ((Boolean)this.cachePreparedStatements.getValueAsObject()).booleanValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCacheResultSetMetadata() {
/* 1662 */     return this.cacheResultSetMetaDataAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCacheServerConfiguration() {
/* 1671 */     return this.cacheServerConfiguration.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCallableStatementCacheSize() {
/* 1680 */     return this.callableStatementCacheSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCapitalizeTypeNames() {
/* 1689 */     return this.capitalizeTypeNames.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCharacterSetResults() {
/* 1698 */     return this.characterSetResults.getValueAsString();
/*      */   }
/*      */   
/*      */   public String getConnectionAttributes() {
/* 1702 */     return this.connectionAttributes.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setConnectionAttributes(String val) {
/* 1706 */     this.connectionAttributes.setValue(val);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getClobberStreamingResults() {
/* 1715 */     return this.clobberStreamingResults.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClobCharacterEncoding() {
/* 1724 */     return this.clobCharacterEncoding.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getConnectionCollation() {
/* 1733 */     return this.connectionCollation.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getConnectTimeout() {
/* 1742 */     return this.connectTimeout.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getContinueBatchOnError() {
/* 1751 */     return this.continueBatchOnError.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCreateDatabaseIfNotExist() {
/* 1760 */     return this.createDatabaseIfNotExist.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDefaultFetchSize() {
/* 1769 */     return this.defaultFetchSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDontTrackOpenResources() {
/* 1778 */     return this.dontTrackOpenResources.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDumpQueriesOnException() {
/* 1787 */     return this.dumpQueriesOnException.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDynamicCalendars() {
/* 1796 */     return this.dynamicCalendars.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getElideSetAutoCommits() {
/* 1806 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEmptyStringsConvertToZero() {
/* 1817 */     return this.emptyStringsConvertToZero.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEmulateLocators() {
/* 1826 */     return this.emulateLocators.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEmulateUnsupportedPstmts() {
/* 1835 */     return this.emulateUnsupportedPstmts.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEnablePacketDebug() {
/* 1844 */     return this.enablePacketDebug.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEncoding() {
/* 1853 */     return this.characterEncodingAsString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getExplainSlowQueries() {
/* 1862 */     return this.explainSlowQueries.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFailOverReadOnly() {
/* 1871 */     return this.failOverReadOnly.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getGatherPerformanceMetrics() {
/* 1880 */     return this.gatherPerformanceMetrics.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   protected boolean getHighAvailability() {
/* 1884 */     return this.highAvailabilityAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getHoldResultsOpenOverStatementClose() {
/* 1893 */     return this.holdResultsOpenOverStatementClose.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIgnoreNonTxTables() {
/* 1902 */     return this.ignoreNonTxTables.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInitialTimeout() {
/* 1911 */     return this.initialTimeout.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInteractiveClient() {
/* 1920 */     return this.isInteractiveClient.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIsInteractiveClient() {
/* 1929 */     return this.isInteractiveClient.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getJdbcCompliantTruncation() {
/* 1938 */     return this.jdbcCompliantTruncation.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLocatorFetchBufferSize() {
/* 1947 */     return this.locatorFetchBufferSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLogger() {
/* 1956 */     return this.loggerClassName.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLoggerClassName() {
/* 1965 */     return this.loggerClassName.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getLogSlowQueries() {
/* 1974 */     return this.logSlowQueries.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getMaintainTimeStats() {
/* 1983 */     return this.maintainTimeStatsAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxQuerySizeToLog() {
/* 1992 */     return this.maxQuerySizeToLog.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxReconnects() {
/* 2001 */     return this.maxReconnects.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxRows() {
/* 2010 */     return this.maxRowsAsInt;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMetadataCacheSize() {
/* 2019 */     return this.metadataCacheSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNoDatetimeStringSync() {
/* 2028 */     return this.noDatetimeStringSync.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNullCatalogMeansCurrent() {
/* 2037 */     return this.nullCatalogMeansCurrent.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNullNamePatternMatchesAll() {
/* 2046 */     return this.nullNamePatternMatchesAll.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPacketDebugBufferSize() {
/* 2055 */     return this.packetDebugBufferSize.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getParanoid() {
/* 2064 */     return this.paranoid.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPedantic() {
/* 2073 */     return this.pedantic.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreparedStatementCacheSize() {
/* 2082 */     return ((Integer)this.preparedStatementCacheSize.getValueAsObject()).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPreparedStatementCacheSqlLimit() {
/* 2091 */     return ((Integer)this.preparedStatementCacheSqlLimit.getValueAsObject()).intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getProfileSql() {
/* 2100 */     return this.profileSQLAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getProfileSQL() {
/* 2109 */     return this.profileSQL.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPropertiesTransform() {
/* 2118 */     return this.propertiesTransform.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getQueriesBeforeRetryMaster() {
/* 2127 */     return this.queriesBeforeRetryMaster.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getReconnectAtTxEnd() {
/* 2136 */     return this.reconnectTxAtEndAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRelaxAutoCommit() {
/* 2145 */     return this.relaxAutoCommit.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getReportMetricsIntervalMillis() {
/* 2154 */     return this.reportMetricsIntervalMillis.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRequireSSL() {
/* 2163 */     return this.requireSSL.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public boolean getRetainStatementAfterResultSetClose() {
/* 2167 */     return this.retainStatementAfterResultSetClose.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRollbackOnPooledClose() {
/* 2176 */     return this.rollbackOnPooledClose.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRoundRobinLoadBalance() {
/* 2185 */     return this.roundRobinLoadBalance.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRunningCTS13() {
/* 2194 */     return this.runningCTS13.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSecondsBeforeRetryMaster() {
/* 2203 */     return this.secondsBeforeRetryMaster.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getServerTimezone() {
/* 2212 */     return this.serverTimezone.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSessionVariables() {
/* 2221 */     return this.sessionVariables.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSlowQueryThresholdMillis() {
/* 2230 */     return this.slowQueryThresholdMillis.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSocketFactoryClassName() {
/* 2239 */     return this.socketFactoryClassName.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSocketTimeout() {
/* 2248 */     return this.socketTimeout.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getStrictFloatingPoint() {
/* 2257 */     return this.strictFloatingPoint.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getStrictUpdates() {
/* 2266 */     return this.strictUpdates.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTinyInt1isBit() {
/* 2275 */     return this.tinyInt1isBit.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTraceProtocol() {
/* 2284 */     return this.traceProtocol.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTransformedBitIsBoolean() {
/* 2293 */     return this.transformedBitIsBoolean.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseCompression() {
/* 2302 */     return this.useCompression.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseFastIntParsing() {
/* 2311 */     return this.useFastIntParsing.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseHostsInPrivileges() {
/* 2320 */     return this.useHostsInPrivileges.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseInformationSchema() {
/* 2329 */     return this.useInformationSchema.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseLocalSessionState() {
/* 2338 */     return this.useLocalSessionState.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseOldUTF8Behavior() {
/* 2347 */     return this.useOldUTF8BehaviorAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseOnlyServerErrorMessages() {
/* 2356 */     return this.useOnlyServerErrorMessages.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseReadAheadInput() {
/* 2365 */     return this.useReadAheadInput.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseServerPreparedStmts() {
/* 2374 */     return this.detectServerPreparedStmts.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseSqlStateCodes() {
/* 2383 */     return this.useSqlStateCodes.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseSSL() {
/* 2392 */     return this.useSSL.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUseSSLExplicit() {
/* 2401 */     return this.useSSL.wasExplicitlySet;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseStreamLengthsInPrepStmts() {
/* 2410 */     return this.useStreamLengthsInPrepStmts.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseTimezone() {
/* 2419 */     return this.useTimezone.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseUltraDevWorkAround() {
/* 2428 */     return this.useUltraDevWorkAround.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseUnbufferedInput() {
/* 2437 */     return this.useUnbufferedInput.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseUnicode() {
/* 2446 */     return this.useUnicodeAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseUsageAdvisor() {
/* 2455 */     return this.useUsageAdvisorAsBoolean;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getYearIsDateType() {
/* 2464 */     return this.yearIsDateType.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getZeroDateTimeBehavior() {
/* 2473 */     return this.zeroDateTimeBehavior.getValueAsString();
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
/*      */   protected void initializeFromRef(Reference ref) throws SQLException {
/* 2486 */     int numPropertiesToSet = PROPERTY_LIST.size();
/*      */     
/* 2488 */     for (int i = 0; i < numPropertiesToSet; i++) {
/* 2489 */       Field propertyField = PROPERTY_LIST.get(i);
/*      */       
/*      */       try {
/* 2492 */         ConnectionProperty propToSet = (ConnectionProperty)propertyField.get(this);
/*      */         
/* 2494 */         if (ref != null) {
/* 2495 */           propToSet.initializeFrom(ref, getExceptionInterceptor());
/*      */         }
/* 2497 */       } catch (IllegalAccessException iae) {
/* 2498 */         throw SQLError.createSQLException("Internal properties failure", "S1000", getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */     
/* 2502 */     postInitialization();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void initializeProperties(Properties info) throws SQLException {
/* 2513 */     if (info != null) {
/*      */       
/* 2515 */       String profileSqlLc = info.getProperty("profileSql");
/*      */       
/* 2517 */       if (profileSqlLc != null) {
/* 2518 */         info.put("profileSQL", profileSqlLc);
/*      */       }
/*      */       
/* 2521 */       Properties infoCopy = (Properties)info.clone();
/*      */       
/* 2523 */       infoCopy.remove("HOST");
/* 2524 */       infoCopy.remove("user");
/* 2525 */       infoCopy.remove("password");
/* 2526 */       infoCopy.remove("DBNAME");
/* 2527 */       infoCopy.remove("PORT");
/* 2528 */       infoCopy.remove("profileSql");
/*      */       
/* 2530 */       int numPropertiesToSet = PROPERTY_LIST.size();
/*      */       
/* 2532 */       for (int i = 0; i < numPropertiesToSet; i++) {
/* 2533 */         Field propertyField = PROPERTY_LIST.get(i);
/*      */         
/*      */         try {
/* 2536 */           ConnectionProperty propToSet = (ConnectionProperty)propertyField.get(this);
/*      */           
/* 2538 */           propToSet.initializeFrom(infoCopy, getExceptionInterceptor());
/* 2539 */         } catch (IllegalAccessException iae) {
/* 2540 */           throw SQLError.createSQLException(Messages.getString("ConnectionProperties.unableToInitDriverProperties") + iae.toString(), "S1000", getExceptionInterceptor());
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 2545 */       postInitialization();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void postInitialization() throws SQLException {
/* 2552 */     if (this.profileSql.getValueAsObject() != null) {
/* 2553 */       this.profileSQL.initializeFrom(this.profileSql.getValueAsObject().toString(), getExceptionInterceptor());
/*      */     }
/*      */     
/* 2556 */     this.reconnectTxAtEndAsBoolean = ((Boolean)this.reconnectAtTxEnd.getValueAsObject()).booleanValue();
/*      */ 
/*      */     
/* 2559 */     if (getMaxRows() == 0)
/*      */     {
/* 2561 */       this.maxRows.setValueAsObject(Integer.valueOf(-1));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2567 */     String testEncoding = (String)this.characterEncoding.getValueAsObject();
/*      */     
/* 2569 */     if (testEncoding != null) {
/*      */       
/*      */       try {
/* 2572 */         String testString = "abc";
/* 2573 */         StringUtils.getBytes(testString, testEncoding);
/* 2574 */       } catch (UnsupportedEncodingException UE) {
/* 2575 */         throw SQLError.createSQLException(Messages.getString("ConnectionProperties.unsupportedCharacterEncoding", new Object[] { testEncoding }), "0S100", getExceptionInterceptor());
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2582 */     if (((Boolean)this.cacheResultSetMetadata.getValueAsObject()).booleanValue()) {
/*      */       try {
/* 2584 */         Class.forName("java.util.LinkedHashMap");
/* 2585 */       } catch (ClassNotFoundException cnfe) {
/* 2586 */         this.cacheResultSetMetadata.setValue(false);
/*      */       } 
/*      */     }
/*      */     
/* 2590 */     this.cacheResultSetMetaDataAsBoolean = this.cacheResultSetMetadata.getValueAsBoolean();
/* 2591 */     this.useUnicodeAsBoolean = this.useUnicode.getValueAsBoolean();
/* 2592 */     this.characterEncodingAsString = (String)this.characterEncoding.getValueAsObject();
/* 2593 */     this.highAvailabilityAsBoolean = this.autoReconnect.getValueAsBoolean();
/* 2594 */     this.autoReconnectForPoolsAsBoolean = this.autoReconnectForPools.getValueAsBoolean();
/* 2595 */     this.maxRowsAsInt = ((Integer)this.maxRows.getValueAsObject()).intValue();
/* 2596 */     this.profileSQLAsBoolean = this.profileSQL.getValueAsBoolean();
/* 2597 */     this.useUsageAdvisorAsBoolean = this.useUsageAdvisor.getValueAsBoolean();
/* 2598 */     this.useOldUTF8BehaviorAsBoolean = this.useOldUTF8Behavior.getValueAsBoolean();
/* 2599 */     this.autoGenerateTestcaseScriptAsBoolean = this.autoGenerateTestcaseScript.getValueAsBoolean();
/* 2600 */     this.maintainTimeStatsAsBoolean = this.maintainTimeStats.getValueAsBoolean();
/* 2601 */     this.jdbcCompliantTruncationForReads = getJdbcCompliantTruncation();
/*      */     
/* 2603 */     if (getUseCursorFetch())
/*      */     {
/* 2605 */       setDetectServerPreparedStmts(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowLoadLocalInfile(boolean property) {
/* 2615 */     this.allowLoadLocalInfile.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowMultiQueries(boolean property) {
/* 2624 */     this.allowMultiQueries.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowNanAndInf(boolean flag) {
/* 2633 */     this.allowNanAndInf.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllowUrlInLocalInfile(boolean flag) {
/* 2642 */     this.allowUrlInLocalInfile.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAlwaysSendSetIsolation(boolean flag) {
/* 2651 */     this.alwaysSendSetIsolation.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoDeserialize(boolean flag) {
/* 2660 */     this.autoDeserialize.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoGenerateTestcaseScript(boolean flag) {
/* 2669 */     this.autoGenerateTestcaseScript.setValue(flag);
/* 2670 */     this.autoGenerateTestcaseScriptAsBoolean = this.autoGenerateTestcaseScript.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoReconnect(boolean flag) {
/* 2679 */     this.autoReconnect.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoReconnectForConnectionPools(boolean property) {
/* 2688 */     this.autoReconnectForPools.setValue(property);
/* 2689 */     this.autoReconnectForPoolsAsBoolean = this.autoReconnectForPools.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoReconnectForPools(boolean flag) {
/* 2698 */     this.autoReconnectForPools.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBlobSendChunkSize(String value) throws SQLException {
/* 2707 */     this.blobSendChunkSize.setValue(value, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCacheCallableStatements(boolean flag) {
/* 2716 */     this.cacheCallableStatements.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCachePreparedStatements(boolean flag) {
/* 2725 */     this.cachePreparedStatements.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCacheResultSetMetadata(boolean property) {
/* 2734 */     this.cacheResultSetMetadata.setValue(property);
/* 2735 */     this.cacheResultSetMetaDataAsBoolean = this.cacheResultSetMetadata.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCacheServerConfiguration(boolean flag) {
/* 2744 */     this.cacheServerConfiguration.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCallableStatementCacheSize(int size) throws SQLException {
/* 2753 */     this.callableStatementCacheSize.setValue(size, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCapitalizeDBMDTypes(boolean property) {
/* 2762 */     this.capitalizeTypeNames.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCapitalizeTypeNames(boolean flag) {
/* 2771 */     this.capitalizeTypeNames.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterEncoding(String encoding) {
/* 2780 */     this.characterEncoding.setValue(encoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCharacterSetResults(String characterSet) {
/* 2789 */     this.characterSetResults.setValue(characterSet);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClobberStreamingResults(boolean flag) {
/* 2798 */     this.clobberStreamingResults.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClobCharacterEncoding(String encoding) {
/* 2807 */     this.clobCharacterEncoding.setValue(encoding);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectionCollation(String collation) {
/* 2816 */     this.connectionCollation.setValue(collation);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setConnectTimeout(int timeoutMs) throws SQLException {
/* 2825 */     this.connectTimeout.setValue(timeoutMs, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setContinueBatchOnError(boolean property) {
/* 2834 */     this.continueBatchOnError.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCreateDatabaseIfNotExist(boolean flag) {
/* 2843 */     this.createDatabaseIfNotExist.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDefaultFetchSize(int n) throws SQLException {
/* 2852 */     this.defaultFetchSize.setValue(n, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDetectServerPreparedStmts(boolean property) {
/* 2861 */     this.detectServerPreparedStmts.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDontTrackOpenResources(boolean flag) {
/* 2870 */     this.dontTrackOpenResources.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDumpQueriesOnException(boolean flag) {
/* 2879 */     this.dumpQueriesOnException.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDynamicCalendars(boolean flag) {
/* 2888 */     this.dynamicCalendars.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setElideSetAutoCommits(boolean flag) {
/* 2897 */     this.elideSetAutoCommits.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmptyStringsConvertToZero(boolean flag) {
/* 2906 */     this.emptyStringsConvertToZero.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmulateLocators(boolean property) {
/* 2915 */     this.emulateLocators.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmulateUnsupportedPstmts(boolean flag) {
/* 2924 */     this.emulateUnsupportedPstmts.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEnablePacketDebug(boolean flag) {
/* 2933 */     this.enablePacketDebug.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncoding(String property) {
/* 2942 */     this.characterEncoding.setValue(property);
/* 2943 */     this.characterEncodingAsString = this.characterEncoding.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setExplainSlowQueries(boolean flag) {
/* 2952 */     this.explainSlowQueries.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFailOverReadOnly(boolean flag) {
/* 2961 */     this.failOverReadOnly.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGatherPerformanceMetrics(boolean flag) {
/* 2970 */     this.gatherPerformanceMetrics.setValue(flag);
/*      */   }
/*      */   
/*      */   protected void setHighAvailability(boolean property) {
/* 2974 */     this.autoReconnect.setValue(property);
/* 2975 */     this.highAvailabilityAsBoolean = this.autoReconnect.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHoldResultsOpenOverStatementClose(boolean flag) {
/* 2984 */     this.holdResultsOpenOverStatementClose.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIgnoreNonTxTables(boolean property) {
/* 2993 */     this.ignoreNonTxTables.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInitialTimeout(int property) throws SQLException {
/* 3002 */     this.initialTimeout.setValue(property, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIsInteractiveClient(boolean property) {
/* 3011 */     this.isInteractiveClient.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJdbcCompliantTruncation(boolean flag) {
/* 3020 */     this.jdbcCompliantTruncation.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocatorFetchBufferSize(String value) throws SQLException {
/* 3029 */     this.locatorFetchBufferSize.setValue(value, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLogger(String property) {
/* 3038 */     this.loggerClassName.setValueAsObject(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLoggerClassName(String className) {
/* 3047 */     this.loggerClassName.setValue(className);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLogSlowQueries(boolean flag) {
/* 3056 */     this.logSlowQueries.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaintainTimeStats(boolean flag) {
/* 3065 */     this.maintainTimeStats.setValue(flag);
/* 3066 */     this.maintainTimeStatsAsBoolean = this.maintainTimeStats.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxQuerySizeToLog(int sizeInBytes) throws SQLException {
/* 3075 */     this.maxQuerySizeToLog.setValue(sizeInBytes, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxReconnects(int property) throws SQLException {
/* 3084 */     this.maxReconnects.setValue(property, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMaxRows(int property) throws SQLException {
/* 3093 */     this.maxRows.setValue(property, getExceptionInterceptor());
/* 3094 */     this.maxRowsAsInt = this.maxRows.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setMetadataCacheSize(int value) throws SQLException {
/* 3103 */     this.metadataCacheSize.setValue(value, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNoDatetimeStringSync(boolean flag) {
/* 3112 */     this.noDatetimeStringSync.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNullCatalogMeansCurrent(boolean value) {
/* 3121 */     this.nullCatalogMeansCurrent.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNullNamePatternMatchesAll(boolean value) {
/* 3130 */     this.nullNamePatternMatchesAll.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPacketDebugBufferSize(int size) throws SQLException {
/* 3139 */     this.packetDebugBufferSize.setValue(size, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setParanoid(boolean property) {
/* 3148 */     this.paranoid.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPedantic(boolean property) {
/* 3157 */     this.pedantic.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPreparedStatementCacheSize(int cacheSize) throws SQLException {
/* 3166 */     this.preparedStatementCacheSize.setValue(cacheSize, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPreparedStatementCacheSqlLimit(int cacheSqlLimit) throws SQLException {
/* 3175 */     this.preparedStatementCacheSqlLimit.setValue(cacheSqlLimit, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProfileSql(boolean property) {
/* 3184 */     this.profileSQL.setValue(property);
/* 3185 */     this.profileSQLAsBoolean = this.profileSQL.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProfileSQL(boolean flag) {
/* 3194 */     this.profileSQL.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPropertiesTransform(String value) {
/* 3203 */     this.propertiesTransform.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setQueriesBeforeRetryMaster(int property) throws SQLException {
/* 3212 */     this.queriesBeforeRetryMaster.setValue(property, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReconnectAtTxEnd(boolean property) {
/* 3221 */     this.reconnectAtTxEnd.setValue(property);
/* 3222 */     this.reconnectTxAtEndAsBoolean = this.reconnectAtTxEnd.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRelaxAutoCommit(boolean property) {
/* 3231 */     this.relaxAutoCommit.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setReportMetricsIntervalMillis(int millis) throws SQLException {
/* 3240 */     this.reportMetricsIntervalMillis.setValue(millis, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRequireSSL(boolean property) {
/* 3249 */     this.requireSSL.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRetainStatementAfterResultSetClose(boolean flag) {
/* 3258 */     this.retainStatementAfterResultSetClose.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRollbackOnPooledClose(boolean flag) {
/* 3267 */     this.rollbackOnPooledClose.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRoundRobinLoadBalance(boolean flag) {
/* 3276 */     this.roundRobinLoadBalance.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRunningCTS13(boolean flag) {
/* 3285 */     this.runningCTS13.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSecondsBeforeRetryMaster(int property) throws SQLException {
/* 3294 */     this.secondsBeforeRetryMaster.setValue(property, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setServerTimezone(String property) {
/* 3303 */     this.serverTimezone.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSessionVariables(String variables) {
/* 3312 */     this.sessionVariables.setValue(variables);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSlowQueryThresholdMillis(int millis) throws SQLException {
/* 3321 */     this.slowQueryThresholdMillis.setValue(millis, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSocketFactoryClassName(String property) {
/* 3330 */     this.socketFactoryClassName.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSocketTimeout(int property) throws SQLException {
/* 3339 */     this.socketTimeout.setValue(property, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrictFloatingPoint(boolean property) {
/* 3348 */     this.strictFloatingPoint.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrictUpdates(boolean property) {
/* 3357 */     this.strictUpdates.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTinyInt1isBit(boolean flag) {
/* 3366 */     this.tinyInt1isBit.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTraceProtocol(boolean flag) {
/* 3375 */     this.traceProtocol.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTransformedBitIsBoolean(boolean flag) {
/* 3384 */     this.transformedBitIsBoolean.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseCompression(boolean property) {
/* 3393 */     this.useCompression.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseFastIntParsing(boolean flag) {
/* 3402 */     this.useFastIntParsing.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseHostsInPrivileges(boolean property) {
/* 3411 */     this.useHostsInPrivileges.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseInformationSchema(boolean flag) {
/* 3420 */     this.useInformationSchema.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseLocalSessionState(boolean flag) {
/* 3429 */     this.useLocalSessionState.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseOldUTF8Behavior(boolean flag) {
/* 3438 */     this.useOldUTF8Behavior.setValue(flag);
/* 3439 */     this.useOldUTF8BehaviorAsBoolean = this.useOldUTF8Behavior.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseOnlyServerErrorMessages(boolean flag) {
/* 3448 */     this.useOnlyServerErrorMessages.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseReadAheadInput(boolean flag) {
/* 3457 */     this.useReadAheadInput.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseServerPreparedStmts(boolean flag) {
/* 3466 */     this.detectServerPreparedStmts.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseSqlStateCodes(boolean flag) {
/* 3475 */     this.useSqlStateCodes.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseSSL(boolean property) {
/* 3484 */     this.useSSL.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseStreamLengthsInPrepStmts(boolean property) {
/* 3493 */     this.useStreamLengthsInPrepStmts.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseTimezone(boolean property) {
/* 3502 */     this.useTimezone.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseUltraDevWorkAround(boolean property) {
/* 3511 */     this.useUltraDevWorkAround.setValue(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseUnbufferedInput(boolean flag) {
/* 3520 */     this.useUnbufferedInput.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseUnicode(boolean flag) {
/* 3529 */     this.useUnicode.setValue(flag);
/* 3530 */     this.useUnicodeAsBoolean = this.useUnicode.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseUsageAdvisor(boolean useUsageAdvisorFlag) {
/* 3539 */     this.useUsageAdvisor.setValue(useUsageAdvisorFlag);
/* 3540 */     this.useUsageAdvisorAsBoolean = this.useUsageAdvisor.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setYearIsDateType(boolean flag) {
/* 3549 */     this.yearIsDateType.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setZeroDateTimeBehavior(String behavior) {
/* 3558 */     this.zeroDateTimeBehavior.setValue(behavior);
/*      */   }
/*      */   
/*      */   protected void storeToRef(Reference ref) throws SQLException {
/* 3562 */     int numPropertiesToSet = PROPERTY_LIST.size();
/*      */     
/* 3564 */     for (int i = 0; i < numPropertiesToSet; i++) {
/* 3565 */       Field propertyField = PROPERTY_LIST.get(i);
/*      */       
/*      */       try {
/* 3568 */         ConnectionProperty propToStore = (ConnectionProperty)propertyField.get(this);
/*      */         
/* 3570 */         if (ref != null) {
/* 3571 */           propToStore.storeTo(ref);
/*      */         }
/* 3573 */       } catch (IllegalAccessException iae) {
/* 3574 */         throw SQLError.createSQLException(Messages.getString("ConnectionProperties.errorNotExpected"), getExceptionInterceptor());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean useUnbufferedInput() {
/* 3585 */     return this.useUnbufferedInput.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseCursorFetch() {
/* 3594 */     return this.useCursorFetch.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseCursorFetch(boolean flag) {
/* 3603 */     this.useCursorFetch.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getOverrideSupportsIntegrityEnhancementFacility() {
/* 3612 */     return this.overrideSupportsIntegrityEnhancementFacility.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOverrideSupportsIntegrityEnhancementFacility(boolean flag) {
/* 3621 */     this.overrideSupportsIntegrityEnhancementFacility.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNoTimezoneConversionForTimeType() {
/* 3630 */     return this.noTimezoneConversionForTimeType.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNoTimezoneConversionForTimeType(boolean flag) {
/* 3639 */     this.noTimezoneConversionForTimeType.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNoTimezoneConversionForDateType() {
/* 3648 */     return this.noTimezoneConversionForDateType.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNoTimezoneConversionForDateType(boolean flag) {
/* 3657 */     this.noTimezoneConversionForDateType.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCacheDefaultTimezone() {
/* 3666 */     return this.cacheDefaultTimezone.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCacheDefaultTimezone(boolean flag) {
/* 3675 */     this.cacheDefaultTimezone.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseJDBCCompliantTimezoneShift() {
/* 3684 */     return this.useJDBCCompliantTimezoneShift.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseJDBCCompliantTimezoneShift(boolean flag) {
/* 3693 */     this.useJDBCCompliantTimezoneShift.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoClosePStmtStreams() {
/* 3702 */     return this.autoClosePStmtStreams.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAutoClosePStmtStreams(boolean flag) {
/* 3711 */     this.autoClosePStmtStreams.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getProcessEscapeCodesForPrepStmts() {
/* 3720 */     return this.processEscapeCodesForPrepStmts.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setProcessEscapeCodesForPrepStmts(boolean flag) {
/* 3729 */     this.processEscapeCodesForPrepStmts.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseGmtMillisForDatetimes() {
/* 3738 */     return this.useGmtMillisForDatetimes.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseGmtMillisForDatetimes(boolean flag) {
/* 3747 */     this.useGmtMillisForDatetimes.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getDumpMetadataOnColumnNotFound() {
/* 3756 */     return this.dumpMetadataOnColumnNotFound.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDumpMetadataOnColumnNotFound(boolean flag) {
/* 3765 */     this.dumpMetadataOnColumnNotFound.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getResourceId() {
/* 3774 */     return this.resourceId.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResourceId(String resourceId) {
/* 3783 */     this.resourceId.setValue(resourceId);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getRewriteBatchedStatements() {
/* 3792 */     return this.rewriteBatchedStatements.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRewriteBatchedStatements(boolean flag) {
/* 3801 */     this.rewriteBatchedStatements.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getJdbcCompliantTruncationForReads() {
/* 3810 */     return this.jdbcCompliantTruncationForReads;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJdbcCompliantTruncationForReads(boolean jdbcCompliantTruncationForReads) {
/* 3819 */     this.jdbcCompliantTruncationForReads = jdbcCompliantTruncationForReads;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseJvmCharsetConverters() {
/* 3828 */     return this.useJvmCharsetConverters.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseJvmCharsetConverters(boolean flag) {
/* 3837 */     this.useJvmCharsetConverters.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPinGlobalTxToPhysicalConnection() {
/* 3846 */     return this.pinGlobalTxToPhysicalConnection.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPinGlobalTxToPhysicalConnection(boolean flag) {
/* 3855 */     this.pinGlobalTxToPhysicalConnection.setValue(flag);
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
/*      */   public void setGatherPerfMetrics(boolean flag) {
/* 3869 */     setGatherPerformanceMetrics(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getGatherPerfMetrics() {
/* 3878 */     return getGatherPerformanceMetrics();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUltraDevHack(boolean flag) {
/* 3887 */     setUseUltraDevWorkAround(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUltraDevHack() {
/* 3896 */     return getUseUltraDevWorkAround();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInteractiveClient(boolean property) {
/* 3905 */     setIsInteractiveClient(property);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSocketFactory(String name) {
/* 3914 */     setSocketFactoryClassName(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSocketFactory() {
/* 3923 */     return getSocketFactoryClassName();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseServerPrepStmts(boolean flag) {
/* 3932 */     setUseServerPreparedStmts(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseServerPrepStmts() {
/* 3941 */     return getUseServerPreparedStmts();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCacheCallableStmts(boolean flag) {
/* 3950 */     setCacheCallableStatements(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCacheCallableStmts() {
/* 3959 */     return getCacheCallableStatements();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCachePrepStmts(boolean flag) {
/* 3968 */     setCachePreparedStatements(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getCachePrepStmts() {
/* 3977 */     return getCachePreparedStatements();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCallableStmtCacheSize(int cacheSize) throws SQLException {
/* 3986 */     setCallableStatementCacheSize(cacheSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCallableStmtCacheSize() {
/* 3995 */     return getCallableStatementCacheSize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrepStmtCacheSize(int cacheSize) throws SQLException {
/* 4004 */     setPreparedStatementCacheSize(cacheSize);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPrepStmtCacheSize() {
/* 4013 */     return getPreparedStatementCacheSize();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPrepStmtCacheSqlLimit(int sqlLimit) throws SQLException {
/* 4022 */     setPreparedStatementCacheSqlLimit(sqlLimit);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPrepStmtCacheSqlLimit() {
/* 4031 */     return getPreparedStatementCacheSqlLimit();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getNoAccessToProcedureBodies() {
/* 4040 */     return this.noAccessToProcedureBodies.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNoAccessToProcedureBodies(boolean flag) {
/* 4049 */     this.noAccessToProcedureBodies.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseOldAliasMetadataBehavior() {
/* 4058 */     return this.useOldAliasMetadataBehavior.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseOldAliasMetadataBehavior(boolean flag) {
/* 4067 */     this.useOldAliasMetadataBehavior.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientCertificateKeyStorePassword() {
/* 4076 */     return this.clientCertificateKeyStorePassword.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStorePassword(String value) {
/* 4085 */     this.clientCertificateKeyStorePassword.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientCertificateKeyStoreType() {
/* 4094 */     return this.clientCertificateKeyStoreType.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStoreType(String value) {
/* 4103 */     this.clientCertificateKeyStoreType.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientCertificateKeyStoreUrl() {
/* 4112 */     return this.clientCertificateKeyStoreUrl.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientCertificateKeyStoreUrl(String value) {
/* 4121 */     this.clientCertificateKeyStoreUrl.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTrustCertificateKeyStorePassword() {
/* 4130 */     return this.trustCertificateKeyStorePassword.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStorePassword(String value) {
/* 4139 */     this.trustCertificateKeyStorePassword.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTrustCertificateKeyStoreType() {
/* 4148 */     return this.trustCertificateKeyStoreType.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStoreType(String value) {
/* 4157 */     this.trustCertificateKeyStoreType.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTrustCertificateKeyStoreUrl() {
/* 4166 */     return this.trustCertificateKeyStoreUrl.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTrustCertificateKeyStoreUrl(String value) {
/* 4175 */     this.trustCertificateKeyStoreUrl.setValue(value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseSSPSCompatibleTimezoneShift() {
/* 4184 */     return this.useSSPSCompatibleTimezoneShift.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseSSPSCompatibleTimezoneShift(boolean flag) {
/* 4193 */     this.useSSPSCompatibleTimezoneShift.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTreatUtilDateAsTimestamp() {
/* 4202 */     return this.treatUtilDateAsTimestamp.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setTreatUtilDateAsTimestamp(boolean flag) {
/* 4211 */     this.treatUtilDateAsTimestamp.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseFastDateParsing() {
/* 4220 */     return this.useFastDateParsing.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseFastDateParsing(boolean flag) {
/* 4229 */     this.useFastDateParsing.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getLocalSocketAddress() {
/* 4238 */     return this.localSocketAddress.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLocalSocketAddress(String address) {
/* 4247 */     this.localSocketAddress.setValue(address);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseConfigs(String configs) {
/* 4256 */     this.useConfigs.setValue(configs);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getUseConfigs() {
/* 4265 */     return this.useConfigs.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getGenerateSimpleParameterMetadata() {
/* 4274 */     return this.generateSimpleParameterMetadata.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setGenerateSimpleParameterMetadata(boolean flag) {
/* 4283 */     this.generateSimpleParameterMetadata.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getLogXaCommands() {
/* 4292 */     return this.logXaCommands.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLogXaCommands(boolean flag) {
/* 4301 */     this.logXaCommands.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getResultSetSizeThreshold() {
/* 4310 */     return this.resultSetSizeThreshold.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResultSetSizeThreshold(int threshold) throws SQLException {
/* 4319 */     this.resultSetSizeThreshold.setValue(threshold, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNetTimeoutForStreamingResults() {
/* 4328 */     return this.netTimeoutForStreamingResults.getValueAsInt();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNetTimeoutForStreamingResults(int value) throws SQLException {
/* 4337 */     this.netTimeoutForStreamingResults.setValue(value, getExceptionInterceptor());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getEnableQueryTimeouts() {
/* 4346 */     return this.enableQueryTimeouts.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEnableQueryTimeouts(boolean flag) {
/* 4355 */     this.enableQueryTimeouts.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getPadCharsWithSpace() {
/* 4364 */     return this.padCharsWithSpace.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setPadCharsWithSpace(boolean flag) {
/* 4373 */     this.padCharsWithSpace.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getUseDynamicCharsetInfo() {
/* 4382 */     return this.useDynamicCharsetInfo.getValueAsBoolean();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setUseDynamicCharsetInfo(boolean flag) {
/* 4391 */     this.useDynamicCharsetInfo.setValue(flag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getClientInfoProvider() {
/* 4400 */     return this.clientInfoProvider.getValueAsString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setClientInfoProvider(String classname) {
/* 4409 */     this.clientInfoProvider.setValue(classname);
/*      */   }
/*      */   
/*      */   public boolean getPopulateInsertRowWithDefaultValues() {
/* 4413 */     return this.populateInsertRowWithDefaultValues.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setPopulateInsertRowWithDefaultValues(boolean flag) {
/* 4417 */     this.populateInsertRowWithDefaultValues.setValue(flag);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceStrategy() {
/* 4421 */     return this.loadBalanceStrategy.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceStrategy(String strategy) {
/* 4425 */     this.loadBalanceStrategy.setValue(strategy);
/*      */   }
/*      */   
/*      */   public String getServerAffinityOrder() {
/* 4429 */     return this.serverAffinityOrder.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setServerAffinityOrder(String hostsList) {
/* 4433 */     this.serverAffinityOrder.setValue(hostsList);
/*      */   }
/*      */   
/*      */   public boolean getTcpNoDelay() {
/* 4437 */     return this.tcpNoDelay.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setTcpNoDelay(boolean flag) {
/* 4441 */     this.tcpNoDelay.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getTcpKeepAlive() {
/* 4445 */     return this.tcpKeepAlive.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setTcpKeepAlive(boolean flag) {
/* 4449 */     this.tcpKeepAlive.setValue(flag);
/*      */   }
/*      */   
/*      */   public int getTcpRcvBuf() {
/* 4453 */     return this.tcpRcvBuf.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setTcpRcvBuf(int bufSize) throws SQLException {
/* 4457 */     this.tcpRcvBuf.setValue(bufSize, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getTcpSndBuf() {
/* 4461 */     return this.tcpSndBuf.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setTcpSndBuf(int bufSize) throws SQLException {
/* 4465 */     this.tcpSndBuf.setValue(bufSize, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getTcpTrafficClass() {
/* 4469 */     return this.tcpTrafficClass.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setTcpTrafficClass(int classFlags) throws SQLException {
/* 4473 */     this.tcpTrafficClass.setValue(classFlags, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public boolean getUseNanosForElapsedTime() {
/* 4477 */     return this.useNanosForElapsedTime.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseNanosForElapsedTime(boolean flag) {
/* 4481 */     this.useNanosForElapsedTime.setValue(flag);
/*      */   }
/*      */   
/*      */   public long getSlowQueryThresholdNanos() {
/* 4485 */     return this.slowQueryThresholdNanos.getValueAsLong();
/*      */   }
/*      */   
/*      */   public void setSlowQueryThresholdNanos(long nanos) throws SQLException {
/* 4489 */     this.slowQueryThresholdNanos.setValue(nanos, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public String getStatementInterceptors() {
/* 4493 */     return this.statementInterceptors.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setStatementInterceptors(String value) {
/* 4497 */     this.statementInterceptors.setValue(value);
/*      */   }
/*      */   
/*      */   public boolean getUseDirectRowUnpack() {
/* 4501 */     return this.useDirectRowUnpack.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseDirectRowUnpack(boolean flag) {
/* 4505 */     this.useDirectRowUnpack.setValue(flag);
/*      */   }
/*      */   
/*      */   public String getLargeRowSizeThreshold() {
/* 4509 */     return this.largeRowSizeThreshold.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLargeRowSizeThreshold(String value) throws SQLException {
/* 4513 */     this.largeRowSizeThreshold.setValue(value, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public boolean getUseBlobToStoreUTF8OutsideBMP() {
/* 4517 */     return this.useBlobToStoreUTF8OutsideBMP.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseBlobToStoreUTF8OutsideBMP(boolean flag) {
/* 4521 */     this.useBlobToStoreUTF8OutsideBMP.setValue(flag);
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpExcludedColumnNamePattern() {
/* 4525 */     return this.utf8OutsideBmpExcludedColumnNamePattern.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpExcludedColumnNamePattern(String regexPattern) {
/* 4529 */     this.utf8OutsideBmpExcludedColumnNamePattern.setValue(regexPattern);
/*      */   }
/*      */   
/*      */   public String getUtf8OutsideBmpIncludedColumnNamePattern() {
/* 4533 */     return this.utf8OutsideBmpIncludedColumnNamePattern.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setUtf8OutsideBmpIncludedColumnNamePattern(String regexPattern) {
/* 4537 */     this.utf8OutsideBmpIncludedColumnNamePattern.setValue(regexPattern);
/*      */   }
/*      */   
/*      */   public boolean getIncludeInnodbStatusInDeadlockExceptions() {
/* 4541 */     return this.includeInnodbStatusInDeadlockExceptions.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setIncludeInnodbStatusInDeadlockExceptions(boolean flag) {
/* 4545 */     this.includeInnodbStatusInDeadlockExceptions.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getBlobsAreStrings() {
/* 4549 */     return this.blobsAreStrings.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setBlobsAreStrings(boolean flag) {
/* 4553 */     this.blobsAreStrings.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getFunctionsNeverReturnBlobs() {
/* 4557 */     return this.functionsNeverReturnBlobs.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setFunctionsNeverReturnBlobs(boolean flag) {
/* 4561 */     this.functionsNeverReturnBlobs.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getAutoSlowLog() {
/* 4565 */     return this.autoSlowLog.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setAutoSlowLog(boolean flag) {
/* 4569 */     this.autoSlowLog.setValue(flag);
/*      */   }
/*      */   
/*      */   public String getConnectionLifecycleInterceptors() {
/* 4573 */     return this.connectionLifecycleInterceptors.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setConnectionLifecycleInterceptors(String interceptors) {
/* 4577 */     this.connectionLifecycleInterceptors.setValue(interceptors);
/*      */   }
/*      */   
/*      */   public String getProfilerEventHandler() {
/* 4581 */     return this.profilerEventHandler.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setProfilerEventHandler(String handler) {
/* 4585 */     this.profilerEventHandler.setValue(handler);
/*      */   }
/*      */   
/*      */   public boolean getVerifyServerCertificate() {
/* 4589 */     return this.verifyServerCertificate.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setVerifyServerCertificate(boolean flag) {
/* 4593 */     this.verifyServerCertificate.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseLegacyDatetimeCode() {
/* 4597 */     return this.useLegacyDatetimeCode.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseLegacyDatetimeCode(boolean flag) {
/* 4601 */     this.useLegacyDatetimeCode.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getSendFractionalSeconds() {
/* 4605 */     return this.sendFractionalSeconds.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setSendFractionalSeconds(boolean flag) {
/* 4609 */     this.sendFractionalSeconds.setValue(flag);
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingSecondsLifetime() {
/* 4613 */     return this.selfDestructOnPingSecondsLifetime.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingSecondsLifetime(int seconds) throws SQLException {
/* 4617 */     this.selfDestructOnPingSecondsLifetime.setValue(seconds, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getSelfDestructOnPingMaxOperations() {
/* 4621 */     return this.selfDestructOnPingMaxOperations.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setSelfDestructOnPingMaxOperations(int maxOperations) throws SQLException {
/* 4625 */     this.selfDestructOnPingMaxOperations.setValue(maxOperations, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public boolean getUseColumnNamesInFindColumn() {
/* 4629 */     return this.useColumnNamesInFindColumn.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseColumnNamesInFindColumn(boolean flag) {
/* 4633 */     this.useColumnNamesInFindColumn.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseLocalTransactionState() {
/* 4637 */     return this.useLocalTransactionState.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setUseLocalTransactionState(boolean flag) {
/* 4641 */     this.useLocalTransactionState.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getCompensateOnDuplicateKeyUpdateCounts() {
/* 4645 */     return this.compensateOnDuplicateKeyUpdateCounts.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setCompensateOnDuplicateKeyUpdateCounts(boolean flag) {
/* 4649 */     this.compensateOnDuplicateKeyUpdateCounts.setValue(flag);
/*      */   }
/*      */   
/*      */   public int getLoadBalanceBlacklistTimeout() {
/* 4653 */     return this.loadBalanceBlacklistTimeout.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceBlacklistTimeout(int loadBalanceBlacklistTimeout) throws SQLException {
/* 4657 */     this.loadBalanceBlacklistTimeout.setValue(loadBalanceBlacklistTimeout, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getLoadBalancePingTimeout() {
/* 4661 */     return this.loadBalancePingTimeout.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setLoadBalancePingTimeout(int loadBalancePingTimeout) throws SQLException {
/* 4665 */     this.loadBalancePingTimeout.setValue(loadBalancePingTimeout, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public void setRetriesAllDown(int retriesAllDown) throws SQLException {
/* 4669 */     this.retriesAllDown.setValue(retriesAllDown, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getRetriesAllDown() {
/* 4673 */     return this.retriesAllDown.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setUseAffectedRows(boolean flag) {
/* 4677 */     this.useAffectedRows.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getUseAffectedRows() {
/* 4681 */     return this.useAffectedRows.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setPasswordCharacterEncoding(String characterSet) {
/* 4685 */     this.passwordCharacterEncoding.setValue(characterSet);
/*      */   }
/*      */   
/*      */   public String getPasswordCharacterEncoding() {
/*      */     String encoding;
/* 4690 */     if ((encoding = this.passwordCharacterEncoding.getValueAsString()) != null) {
/* 4691 */       return encoding;
/*      */     }
/* 4693 */     if (getUseUnicode() && (encoding = getEncoding()) != null) {
/* 4694 */       return encoding;
/*      */     }
/* 4696 */     return "UTF-8";
/*      */   }
/*      */   
/*      */   public void setExceptionInterceptors(String exceptionInterceptors) {
/* 4700 */     this.exceptionInterceptors.setValue(exceptionInterceptors);
/*      */   }
/*      */   
/*      */   public String getExceptionInterceptors() {
/* 4704 */     return this.exceptionInterceptors.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setMaxAllowedPacket(int max) throws SQLException {
/* 4708 */     this.maxAllowedPacket.setValue(max, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getMaxAllowedPacket() {
/* 4712 */     return this.maxAllowedPacket.getValueAsInt();
/*      */   }
/*      */   
/*      */   public boolean getQueryTimeoutKillsConnection() {
/* 4716 */     return this.queryTimeoutKillsConnection.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setQueryTimeoutKillsConnection(boolean queryTimeoutKillsConnection) {
/* 4720 */     this.queryTimeoutKillsConnection.setValue(queryTimeoutKillsConnection);
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceValidateConnectionOnSwapServer() {
/* 4724 */     return this.loadBalanceValidateConnectionOnSwapServer.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceValidateConnectionOnSwapServer(boolean loadBalanceValidateConnectionOnSwapServer) {
/* 4728 */     this.loadBalanceValidateConnectionOnSwapServer.setValue(loadBalanceValidateConnectionOnSwapServer);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceConnectionGroup() {
/* 4732 */     return this.loadBalanceConnectionGroup.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceConnectionGroup(String loadBalanceConnectionGroup) {
/* 4736 */     this.loadBalanceConnectionGroup.setValue(loadBalanceConnectionGroup);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceExceptionChecker() {
/* 4740 */     return this.loadBalanceExceptionChecker.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceExceptionChecker(String loadBalanceExceptionChecker) {
/* 4744 */     this.loadBalanceExceptionChecker.setValue(loadBalanceExceptionChecker);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLStateFailover() {
/* 4748 */     return this.loadBalanceSQLStateFailover.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceSQLStateFailover(String loadBalanceSQLStateFailover) {
/* 4752 */     this.loadBalanceSQLStateFailover.setValue(loadBalanceSQLStateFailover);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceSQLExceptionSubclassFailover() {
/* 4756 */     return this.loadBalanceSQLExceptionSubclassFailover.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceSQLExceptionSubclassFailover(String loadBalanceSQLExceptionSubclassFailover) {
/* 4760 */     this.loadBalanceSQLExceptionSubclassFailover.setValue(loadBalanceSQLExceptionSubclassFailover);
/*      */   }
/*      */   
/*      */   public boolean getLoadBalanceEnableJMX() {
/* 4764 */     return this.loadBalanceEnableJMX.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceEnableJMX(boolean loadBalanceEnableJMX) {
/* 4768 */     this.loadBalanceEnableJMX.setValue(loadBalanceEnableJMX);
/*      */   }
/*      */   
/*      */   public void setLoadBalanceHostRemovalGracePeriod(int loadBalanceHostRemovalGracePeriod) throws SQLException {
/* 4772 */     this.loadBalanceHostRemovalGracePeriod.setValue(loadBalanceHostRemovalGracePeriod, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getLoadBalanceHostRemovalGracePeriod() {
/* 4776 */     return this.loadBalanceHostRemovalGracePeriod.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementThreshold(int loadBalanceAutoCommitStatementThreshold) throws SQLException {
/* 4780 */     this.loadBalanceAutoCommitStatementThreshold.setValue(loadBalanceAutoCommitStatementThreshold, getExceptionInterceptor());
/*      */   }
/*      */   
/*      */   public int getLoadBalanceAutoCommitStatementThreshold() {
/* 4784 */     return this.loadBalanceAutoCommitStatementThreshold.getValueAsInt();
/*      */   }
/*      */   
/*      */   public void setLoadBalanceAutoCommitStatementRegex(String loadBalanceAutoCommitStatementRegex) {
/* 4788 */     this.loadBalanceAutoCommitStatementRegex.setValue(loadBalanceAutoCommitStatementRegex);
/*      */   }
/*      */   
/*      */   public String getLoadBalanceAutoCommitStatementRegex() {
/* 4792 */     return this.loadBalanceAutoCommitStatementRegex.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadDumpInDeadlockExceptions(boolean flag) {
/* 4796 */     this.includeThreadDumpInDeadlockExceptions.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getIncludeThreadDumpInDeadlockExceptions() {
/* 4800 */     return this.includeThreadDumpInDeadlockExceptions.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setIncludeThreadNamesAsStatementComment(boolean flag) {
/* 4804 */     this.includeThreadNamesAsStatementComment.setValue(flag);
/*      */   }
/*      */   
/*      */   public boolean getIncludeThreadNamesAsStatementComment() {
/* 4808 */     return this.includeThreadNamesAsStatementComment.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setAuthenticationPlugins(String authenticationPlugins) {
/* 4812 */     this.authenticationPlugins.setValue(authenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getAuthenticationPlugins() {
/* 4816 */     return this.authenticationPlugins.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setDisabledAuthenticationPlugins(String disabledAuthenticationPlugins) {
/* 4820 */     this.disabledAuthenticationPlugins.setValue(disabledAuthenticationPlugins);
/*      */   }
/*      */   
/*      */   public String getDisabledAuthenticationPlugins() {
/* 4824 */     return this.disabledAuthenticationPlugins.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setDefaultAuthenticationPlugin(String defaultAuthenticationPlugin) {
/* 4828 */     this.defaultAuthenticationPlugin.setValue(defaultAuthenticationPlugin);
/*      */   }
/*      */   
/*      */   public String getDefaultAuthenticationPlugin() {
/* 4832 */     return this.defaultAuthenticationPlugin.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setParseInfoCacheFactory(String factoryClassname) {
/* 4836 */     this.parseInfoCacheFactory.setValue(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getParseInfoCacheFactory() {
/* 4840 */     return this.parseInfoCacheFactory.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setServerConfigCacheFactory(String factoryClassname) {
/* 4844 */     this.serverConfigCacheFactory.setValue(factoryClassname);
/*      */   }
/*      */   
/*      */   public String getServerConfigCacheFactory() {
/* 4848 */     return this.serverConfigCacheFactory.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setDisconnectOnExpiredPasswords(boolean disconnectOnExpiredPasswords) {
/* 4852 */     this.disconnectOnExpiredPasswords.setValue(disconnectOnExpiredPasswords);
/*      */   }
/*      */   
/*      */   public boolean getDisconnectOnExpiredPasswords() {
/* 4856 */     return this.disconnectOnExpiredPasswords.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public String getReplicationConnectionGroup() {
/* 4860 */     return this.replicationConnectionGroup.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setReplicationConnectionGroup(String replicationConnectionGroup) {
/* 4864 */     this.replicationConnectionGroup.setValue(replicationConnectionGroup);
/*      */   }
/*      */   
/*      */   public boolean getAllowMasterDownConnections() {
/* 4868 */     return this.allowMasterDownConnections.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setAllowMasterDownConnections(boolean connectIfMasterDown) {
/* 4872 */     this.allowMasterDownConnections.setValue(connectIfMasterDown);
/*      */   }
/*      */   
/*      */   public boolean getAllowSlaveDownConnections() {
/* 4876 */     return this.allowSlaveDownConnections.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setAllowSlaveDownConnections(boolean connectIfSlaveDown) {
/* 4880 */     this.allowSlaveDownConnections.setValue(connectIfSlaveDown);
/*      */   }
/*      */   
/*      */   public boolean getReadFromMasterWhenNoSlaves() {
/* 4884 */     return this.readFromMasterWhenNoSlaves.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setReadFromMasterWhenNoSlaves(boolean useMasterIfSlavesDown) {
/* 4888 */     this.readFromMasterWhenNoSlaves.setValue(useMasterIfSlavesDown);
/*      */   }
/*      */   
/*      */   public boolean getReplicationEnableJMX() {
/* 4892 */     return this.replicationEnableJMX.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setReplicationEnableJMX(boolean replicationEnableJMX) {
/* 4896 */     this.replicationEnableJMX.setValue(replicationEnableJMX);
/*      */   }
/*      */   
/*      */   public void setGetProceduresReturnsFunctions(boolean getProcedureReturnsFunctions) {
/* 4900 */     this.getProceduresReturnsFunctions.setValue(getProcedureReturnsFunctions);
/*      */   }
/*      */   
/*      */   public boolean getGetProceduresReturnsFunctions() {
/* 4904 */     return this.getProceduresReturnsFunctions.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setDetectCustomCollations(boolean detectCustomCollations) {
/* 4908 */     this.detectCustomCollations.setValue(detectCustomCollations);
/*      */   }
/*      */   
/*      */   public boolean getDetectCustomCollations() {
/* 4912 */     return this.detectCustomCollations.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public String getServerRSAPublicKeyFile() {
/* 4916 */     return this.serverRSAPublicKeyFile.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setServerRSAPublicKeyFile(String serverRSAPublicKeyFile) throws SQLException {
/* 4920 */     if (this.serverRSAPublicKeyFile.getUpdateCount() > 0) {
/* 4921 */       throw SQLError.createSQLException(Messages.getString("ConnectionProperties.dynamicChangeIsNotAllowed", new Object[] { "'serverRSAPublicKeyFile'" }), "S1009", null);
/*      */     }
/*      */     
/* 4924 */     this.serverRSAPublicKeyFile.setValue(serverRSAPublicKeyFile);
/*      */   }
/*      */   
/*      */   public boolean getAllowPublicKeyRetrieval() {
/* 4928 */     return this.allowPublicKeyRetrieval.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setAllowPublicKeyRetrieval(boolean allowPublicKeyRetrieval) throws SQLException {
/* 4932 */     if (this.allowPublicKeyRetrieval.getUpdateCount() > 0) {
/* 4933 */       throw SQLError.createSQLException(Messages.getString("ConnectionProperties.dynamicChangeIsNotAllowed", new Object[] { "'allowPublicKeyRetrieval'" }), "S1009", null);
/*      */     }
/*      */ 
/*      */     
/* 4937 */     this.allowPublicKeyRetrieval.setValue(allowPublicKeyRetrieval);
/*      */   }
/*      */   
/*      */   public void setDontCheckOnDuplicateKeyUpdateInSQL(boolean dontCheckOnDuplicateKeyUpdateInSQL) {
/* 4941 */     this.dontCheckOnDuplicateKeyUpdateInSQL.setValue(dontCheckOnDuplicateKeyUpdateInSQL);
/*      */   }
/*      */   
/*      */   public boolean getDontCheckOnDuplicateKeyUpdateInSQL() {
/* 4945 */     return this.dontCheckOnDuplicateKeyUpdateInSQL.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setSocksProxyHost(String socksProxyHost) {
/* 4949 */     this.socksProxyHost.setValue(socksProxyHost);
/*      */   }
/*      */   
/*      */   public String getSocksProxyHost() {
/* 4953 */     return this.socksProxyHost.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setSocksProxyPort(int socksProxyPort) throws SQLException {
/* 4957 */     this.socksProxyPort.setValue(socksProxyPort, (ExceptionInterceptor)null);
/*      */   }
/*      */   
/*      */   public int getSocksProxyPort() {
/* 4961 */     return this.socksProxyPort.getValueAsInt();
/*      */   }
/*      */   
/*      */   public boolean getReadOnlyPropagatesToServer() {
/* 4965 */     return this.readOnlyPropagatesToServer.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setReadOnlyPropagatesToServer(boolean flag) {
/* 4969 */     this.readOnlyPropagatesToServer.setValue(flag);
/*      */   }
/*      */   
/*      */   public String getEnabledSSLCipherSuites() {
/* 4973 */     return this.enabledSSLCipherSuites.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setEnabledSSLCipherSuites(String cipherSuites) {
/* 4977 */     this.enabledSSLCipherSuites.setValue(cipherSuites);
/*      */   }
/*      */   
/*      */   public String getEnabledTLSProtocols() {
/* 4981 */     return this.enabledTLSProtocols.getValueAsString();
/*      */   }
/*      */   
/*      */   public void setEnabledTLSProtocols(String protocols) {
/* 4985 */     this.enabledTLSProtocols.setValue(protocols);
/*      */   }
/*      */   
/*      */   public boolean getEnableEscapeProcessing() {
/* 4989 */     return this.enableEscapeProcessing.getValueAsBoolean();
/*      */   }
/*      */   
/*      */   public void setEnableEscapeProcessing(boolean flag) {
/* 4993 */     this.enableEscapeProcessing.setValue(flag);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\jdbc\ConnectionPropertiesImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */