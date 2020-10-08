/*    */ package net.md_5.bungee.api.chat;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ScoreComponent
/*    */   extends BaseComponent
/*    */ {
/*    */   private String name;
/*    */   private String objective;
/*    */   
/*    */   public void setName(String name) {
/* 27 */     this.name = name; } public void setObjective(String objective) { this.objective = objective; } public void setValue(String value) { this.value = value; }
/* 28 */   public String toString() { return "ScoreComponent(name=" + getName() + ", objective=" + getObjective() + ", value=" + getValue() + ")"; }
/* 29 */   public ScoreComponent(String name, String objective, String value) { this.name = name; this.objective = objective; this.value = value; }
/* 30 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof ScoreComponent)) return false;  ScoreComponent other = (ScoreComponent)o; if (!other.canEqual(this)) return false;  if (!super.equals(o)) return false;  Object this$name = getName(), other$name = other.getName(); if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name)) return false;  Object this$objective = getObjective(), other$objective = other.getObjective(); if ((this$objective == null) ? (other$objective != null) : !this$objective.equals(other$objective)) return false;  Object this$value = getValue(), other$value = other.getValue(); return !((this$value == null) ? (other$value != null) : !this$value.equals(other$value)); } protected boolean canEqual(Object other) { return other instanceof ScoreComponent; } public int hashCode() { int PRIME = 59; result = super.hashCode(); Object $name = getName(); result = result * 59 + (($name == null) ? 43 : $name.hashCode()); Object $objective = getObjective(); result = result * 59 + (($objective == null) ? 43 : $objective.hashCode()); Object $value = getValue(); return result * 59 + (($value == null) ? 43 : $value.hashCode()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 37 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getObjective() {
/* 42 */     return this.objective;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   private String value = ""; public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ScoreComponent(String name, String objective) {
/* 64 */     setName(name);
/* 65 */     setObjective(objective);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ScoreComponent(ScoreComponent original) {
/* 75 */     super(original);
/* 76 */     setName(original.getName());
/* 77 */     setObjective(original.getObjective());
/* 78 */     setValue(original.getValue());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ScoreComponent duplicate() {
/* 84 */     return new ScoreComponent(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toPlainText(StringBuilder builder) {
/* 90 */     builder.append(this.value);
/* 91 */     super.toPlainText(builder);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void toLegacyText(StringBuilder builder) {
/* 97 */     addFormat(builder);
/* 98 */     builder.append(this.value);
/* 99 */     super.toLegacyText(builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\ScoreComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */