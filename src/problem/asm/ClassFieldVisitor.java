package problem.asm;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

public class ClassFieldVisitor extends ClassVisitor{

	private SassyClass myClass;
	private ArrayList<String> allClasses;
	
	public ClassFieldVisitor(int api){
		super(api);
	}
	
	public ClassFieldVisitor(int api, ClassVisitor decorated, SassyClass myClass, ArrayList<String> allClasses) {
		super(api, decorated);
		this.myClass = myClass;
		this.allClasses = allClasses;
	}
	

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		SassyField field = new SassyField();
		field.setFieldAccess(access);
		field.setFieldName(name);
		if(signature == null) {
			//TODO: Check if classes are substring of field before adding it to the class
			for(String c : this.allClasses) {
				if(type.contains(c)) {
					this.myClass.addAssociationClasses(c);
					System.out.println("Added Association Class: " + c);
				}
			}
			field.setFieldType(type);
		}
		else {
			for(String c : this.allClasses) {
				if(signature.contains(c)) {
					this.myClass.addAssociationClasses(c);
					System.out.println("Added Asociation Class: " + c);
				}
			}
			field.setFieldType(signature);
		}
		myClass.addField(field);
		return toDecorate;
	}

}
