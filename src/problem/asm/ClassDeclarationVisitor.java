package problem.asm;

import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {
	private SassyClass myClass;
	public ClassDeclarationVisitor(int api, SassyClass myClass){
		super(api);
		this.myClass = myClass;
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces){
		
		myClass.setClassName(name);
		myClass.setClassExtends(superName);
		for(int i = 0; i<interfaces.length; i++){
			myClass.addClassImplements(interfaces[i]);
		}
		
		super.visit(version, access, name, signature, superName, interfaces);
		
	}
}
