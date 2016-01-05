package problem.asm;

import java.util.ArrayList;

public class SassyClass {
	
	private String className;
	private ArrayList<SassyMethod> classMethods;
	private ArrayList<String> classImplements;
	private String classExtends;
	private ArrayList<SassyField> fields;
	private boolean isInterface;
	
	public SassyClass(){
		classMethods = new ArrayList<SassyMethod>();
		classImplements = new ArrayList<String>();
		fields = new ArrayList<SassyField>();
		className = "";
		classExtends = "";
		isInterface = false;
	}
	
	public ArrayList<SassyField> getFields(){
		return this.fields;
	}
	
	public void addField(SassyField field){
		this.fields.add(field);
	}
	
	public void setIsInterface(boolean isInterface){
		this.isInterface = isInterface;
	}
	
	public boolean getIsInterface(){
		return this.isInterface;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className.substring(className.lastIndexOf("/") + 1);
	}

	public ArrayList<SassyMethod> getClassMethods() {
		return classMethods;
	}

	public void addClassMethods(SassyMethod method) {
		this.classMethods.add(method); 
	}

	public ArrayList<String> getClassImplements() {
		return classImplements;
	}

	public void addClassImplements(String method) {
		this.classImplements.add(method);
	}

	public String getClassExtends() {
		return classExtends;
	}

	public void setClassExtends(String classExtends) {
		this.classExtends = classExtends;
	}
	
	

}
