package problem.asm;

import java.util.Arrays;
import java.util.HashMap;

public class UMLDrawer {
	private HashMap<String, SassyClass> classMap;
	
	public UMLDrawer(){
		classMap = new HashMap<String, SassyClass>();
		
	}
	public void toGraphViz(){
		StringBuilder build = new StringBuilder();
		//add stuff to build
		
		build.append("digraph sassy_uml{node [shape = \"record\"] ");
		for(String key : classMap.keySet()){
			SassyClass myClass = classMap.get(key);
			build.append(myClass.getClassName() + "[");
			build.append("label = \"{");
			if(myClass.getIsInterface()) {
				build.append("\\<\\<interface\\>\\>\n");
			}
			build.append(myClass.getClassName() + "|");
			this.addFields(myClass, build);
			build.append("| ");
			this.addMethods(myClass, build);
			
		}
		this.addExtensionArrows(build);
		this.addInterfaceArrows(build);
		build.append("}");
		System.out.println(build.toString()); 
	}
	
	public void addMethods(SassyClass myClass, StringBuilder b){
		for(SassyMethod method : myClass.getClassMethods()){
			if(!(method.getMethodName().equals("<clinit>") || method.getMethodName().equals("<init>"))) {
				b.append(method.getMethodAccess()+ " " + method.getMethodName()+"(");				
				String argTypes = Arrays.toString(method.getArgTypes().toArray());
				b.append(argTypes.substring(1, argTypes.length()-1));
				b.append(") : " + method.getMethodReturnType() + "\\l");
			}
			
		
		}
		b.append("}\"]");
		
	}
	
	public void addFields(SassyClass myClass, StringBuilder b){
		for(SassyField field : myClass.getFields()){
			b.append(field.getFieldAccess() + " " + field.getFieldName()+ " : " + field.getFieldType()+ "\\l");
			
		}
	}
	public void addClass(SassyClass myClass) {
		// TODO Auto-generated method stub
		if(!this.classMap.containsKey(myClass.getClassName())) {
			this.classMap.put(myClass.getClassName(), myClass);
		}
	}
	
	public void addExtensionArrows(StringBuilder b) {
		b.append("edge [arrowhead = \"empty\"]");
		for(String scName : this.classMap.keySet()){
			SassyClass sc = this.classMap.get(scName);
			String name = sc.getClassExtends().substring(sc.getClassExtends().lastIndexOf("/")+1);
			if(this.classMap.keySet().contains(name)){
				b.append(sc.getClassName()+ "->" + name + "\n");
			}
		}
	}
	
	public void addInterfaceArrows(StringBuilder b){
		b.append("edge [style = \"dashed\"]");
		for(String scName : this.classMap.keySet()){
			SassyClass sc = this.classMap.get(scName);
			for(String impl : sc.getClassImplements()){
				String name = impl.substring(impl.indexOf("/")+1);
				if(this.classMap.keySet().contains(name)) {
					b.append(sc.getClassName()+ "->" + name +"\n");					
				}
				
			}
		}
	}
	
	

}
