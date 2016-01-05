package problem.asm;

import java.util.ArrayList;

public class SassyMethod {
	
	private String methodName;
	private String methodReturnType; 
	private String methodAccess;
	private ArrayList<String> argTypes;
	
	public SassyMethod(){
		argTypes = new ArrayList<String>();
		methodName = "";
		methodReturnType = "";
		methodAccess = "";
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


}
