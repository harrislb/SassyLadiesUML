package problem.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor{

	SassyClass myClass;
	
	public ClassFieldVisitor(int api){
		super(api);

	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, SassyClass myClass) {
		super(api, decorated);
		this.myClass = myClass;
	}
	

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		SassyField field = new SassyField();
		field.setFieldAccess(access);
		field.setFieldName(name);
		field.setFieldType(type);
		myClass.addField(field);

		return toDecorate;
	};

}
