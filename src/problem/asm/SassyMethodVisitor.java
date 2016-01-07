package problem.asm;

import org.objectweb.asm.MethodVisitor;

public class SassyMethodVisitor extends MethodVisitor {

	private SassyClass myClass;
	public SassyMethodVisitor(int api) {
		super(api);
		// TODO Auto-generated constructor stub
	}

	public SassyMethodVisitor(int api, MethodVisitor mv, SassyClass myClass) {
		super(api, mv);
		this.myClass = myClass;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc, boolean itf) {
		// TODO Auto-generated method stub
//		System.out.println("-----------------Visit Method-----------------------");
//		System.out.println("name is: " + name);
//		System.out.println("opcode is: "+ opcode);
//		System.out.println("owner is: " + owner);
//		System.out.println("desc is: " + desc);		
//		
//		System.out.println("class is: " + this.myClass.getClassName());
		this.myClass.addUsesClasses(owner);
		
	}	

}
