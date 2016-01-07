package problem.asm;

import java.util.ArrayList;

public class SassyMethod {
	
	private String methodName;
	private String methodReturnType; 
	private String methodAccess;
	private String signature;
	private ArrayList<String> argTypes;
	private ArrayList<String> localVars;
	public SassyMethod(){
		argTypes = new ArrayList<String>();
		localVars = new ArrayList<String>();
		methodName = "";
		methodReturnType = "";
		methodAccess = "";
		signature ="";
	}
	
	public void setSignature(String sig){
		this.signature = sig;
	}
	
	public String getSignature(){
		return this.signature;
	}
	
	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodReturnType() {
		return methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType) {
		this.methodReturnType = methodReturnType.substring(methodReturnType.lastIndexOf(".")+1);
	}

	public String getMethodAccess() {
		return methodAccess;
	}

	public void setMethodAccess(String methodAccess) {
		this.methodAccess = methodAccess;
	}

	public ArrayList<String> getArgTypes() {
		return argTypes;
	}

	public void addArgTypes(String argType) {
		this.argTypes.add(argType.substring(argType.lastIndexOf(".")+1));
	}
	
	public void addLocalVars(String var){
		this.localVars.add(var);
	}


}
