package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassMethodVisitor extends ClassVisitor {
	
	private SassyClass myClass;
	private ArrayList<String> allClasses;
	
	public ClassMethodVisitor(int api){
		super(api);
	}
	
	public ClassMethodVisitor(int api, ClassVisitor decorated, SassyClass myClass, ArrayList<String> allClasses) {
		super(api, decorated);
		this.myClass = myClass;
		this.allClasses = allClasses;
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions){
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		//added this line
		SassyMethodVisitor toDecorate2 = new SassyMethodVisitor(Opcodes.ASM5, toDecorate, this.myClass);
		
		//end new add
		
		SassyMethod method = new SassyMethod();
		method.setMethodName(name);
		
		// DONE: create an internal representation of the current method and pass it to the methods below
		addAccessLevel(access, method);
		if(signature == null){
			addReturnType(desc, method);
			addArguments(desc, method);
		}
		else{
			addReturnType(signature,method);
			addArguments(signature, method);
		}
		//addReturnType((signature == null) ? desc : signature, method);
//		addArguments(desc, method);
		
	    // DONE: add the current method to your internal representation of the current class
		// What is a good way for the code to remember what the current class is?
		myClass.addClassMethods(method);

		return toDecorate2;
	}
	
	void addAccessLevel(int access, SassyMethod method){
		String level="";
		if((access&Opcodes.ACC_PUBLIC)!=0) {
			level="+";
		}else if((access&Opcodes.ACC_PROTECTED)!=0) {
			level="#";
		}else if((access&Opcodes.ACC_PRIVATE)!=0) {
			level="-";
		}else{
			level="";
		}
		method.setMethodAccess(level);
	}
	
	void addReturnType(String desc, SassyMethod method){
		String returnType = Type.getReturnType(desc).getClassName();
		System.out.println(returnType);
		for(String c: allClasses) {
			returnType.contains(c);
			
		}
		//TODO: (1) Parse return type Ex: ()Ljava/util/ArrayList<Lproblem/asm/SassyMethod;>; -> ArrayList<SassyMethod>
		method.setMethodReturnType(returnType);
	}
	
	void addArguments(String desc, SassyMethod method){
		System.out.println("desc is: "+ desc);
//		String [] token = desc.split(";");
//		for(String c: token){
//				c = c.substring(c.lastIndexOf("//"));	
//		}
		
		if(desc.contains("ArrayList")){
			desc = desc.replace('<', 't');
			desc = desc.replace('>','$');
			desc = desc.substring(0, desc.indexOf('$'))+ ')';
			System.out.println("New desc is: " + desc);
		}
		
		Type[] args = Type.getArgumentTypes(desc);
		System.out.println("length " + args.length);
	    for(int i=0; i< args.length; i++){
	    	System.out.println(i);
	    	System.out.println("arg added is: " + args[i].getClassName());
	    	String arg=args[i].getClassName();
	    	method.addArgTypes(arg);
	    }
	}
}
