package problem.asm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class DesignParser {
	public static final String[] classes = {
//		"problem.asm.ClassDeclarationVisitor", "problem.asm.ClassFieldVisitor", "problem.asm.ClassMethodVisitor", 
//		"problem.asm.DesignParser", "problem.asm.FirstASM", "problem.asm.SassyClass", "problem.asm.SassyField", 
//		"problem.asm.SassyMethod", "problem.asm.UMLDrawer"
		
		"problem.asm.ClassDeclarationVisitor"
	};
	/**
	 * Reads in a list of Java Classes and reverse engineers their design.
	 * 
	 * @param args: the names of the classes, separated by spaces. 
	 * 		For example: java DesignParser java.lang.String edu.rosehulman.csse374.ClassFieldVisitor java.lang.Math
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		
		UMLDrawer parser = new UMLDrawer();
		
		ArrayList<String> allClasses = new ArrayList<String>();
		for(String className: classes){
			allClasses.add(className.substring(className.lastIndexOf(".")+1));			
		}
		System.out.println(Arrays.toString(allClasses.toArray()));
		for(String className: classes){
			SassyClass myClass = new SassyClass();
			// ASM's ClassReader does the heavy lifting of parsing the compiled Java class
			ClassReader reader=new ClassReader(className);
			
			// make class declaration visitor to get superclass and interfaces
			ClassVisitor decVisitor = new ClassDeclarationVisitor(Opcodes.ASM5, myClass);
			
			// DECORATE declaration visitor with field visitor
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, decVisitor, myClass, allClasses);
			
			
			// DECORATE field visitor with method visitor
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor, myClass, allClasses);

			// TODO: add more DECORATORS here in later milestones to accomplish specific tasks
			
			
			// Tell the Reader to use our (heavily decorated) ClassVisitor to visit the class
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
			parser.addClass(myClass);
		}
		parser.toGraphViz();
	}
}
