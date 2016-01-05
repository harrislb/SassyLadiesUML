package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor {
	
	private SassyClass myClass;
	
	public ClassMethodVisitor(int api){
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated, SassyClass myClass) {
		super(api, decorated);
		this.myClass = myClass;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		SassyMethod method = new SassyMethod();
		method.setMethodName(name);
		
		
		
		// DONE: create an internal representation of the current method and pass it to the methods below
		addAccessLevel(access, method);
		addReturnType(desc, method);
		addArguments(desc, method);
		
		
		
	    // DONE: add the current method to your internal representation of the current class
		// What is a good way for the code to remember what the current class is?
		myClass.addClassMethods(method);

		return toDecorate;
	}
	
	void addAccessLevel(int access, SassyMethod method){
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
		
		method.setMethodAccess(level);
	}
	
	void addReturnType(String desc, SassyMethod method){
		String returnType = Type.getReturnType(desc).getClassName();
		method.setMethodReturnType(returnType);
	}
	
	void addArguments(String desc, SassyMethod method){
		Type[] args = Type.getArgumentTypes(desc);
	    for(int i=0; i< args.length; i++){
	    	String arg=args[i].getClassName();
	    	method.addArgTypes(arg);
	    }
	}
}
