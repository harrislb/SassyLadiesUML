package problem.asm;

import org.objectweb.asm.Opcodes;

public class SassyField {
	private String fieldName;
	private String fieldAccess;
	private String fieldType;
	
	public SassyField(){
		fieldName = "";
		fieldAccess = "";
		fieldType = "";
	}
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldAccess() {
		return fieldAccess;
	}
	
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	//copied from classmethodvisitor
	public void setFieldAccess(int access){
		String level="";
		if((access&Opcodes.ACC_PUBLIC)!=0){
			level="+";
		}else if((access&Opcodes.ACC_PROTECTED)!=0){
			level="#";
		}else if((access&Opcodes.ACC_PRIVATE)!=0){
			level="-";
		}else{
			level="";
		}
		this.fieldAccess = level;
	}
	
}
