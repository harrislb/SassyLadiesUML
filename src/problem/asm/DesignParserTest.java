package problem.asm;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class DesignParserTest {
	/**
	 * Test a basic class to see if output of fields is correct.
	 * @throws Exception 
	 */
	@Test
	public void testSassyFieldClass() throws Exception{
		String expected = "digraph sassy_uml{node [shape = \"record\"] SassyField[label = \"{SassyField|- fieldName : "
				+ "java.lang.String\\l- fieldAccess : java.lang.String\\l- fieldType : java.lang.String\\l| + getFieldName()"
				+ " : String\\l+ setFieldName(String) : void\\l+ getFieldAccess() : String\\l+ getFieldType() : String\\l+ "
				+ "setFieldType(String) : void\\l+ setFieldAccess(int) : void\\l}\"]edge [arrowhead = \"empty\"]edge "
				+ "[style = \"dashed\"]}";
		checkOutput(expected);		

	}
	
//	@Test
//	public void testMultipleClass() throws Exception{
//		String expected = "digraph sassy_uml{node [shape = \"record\"] SassyField[label = \"{SassyField|- fieldName :"
//				+ " java.lang.String\\l- fieldAccess : java.lang.String\\l- fieldType : java.lang.String\\l| + "
//				+ "getFieldName() : String\\l+ setFieldName(String) : void\\l+ getFieldAccess() : String\\l+ "
//				+ "getFieldType() : String\\l+ setFieldType(String) : void\\l+ setFieldAccess(int) : void\\l}\"]"
//				+ "DesignParser[label = \"{DesignParser|+ classes : java.lang.String[]\\l| + main(String[]) : void\\l}\"]\\"
//						+ "edge [arrowhead = \"empty\"]edge [style = \"dashed\"]}";
//
//		checkOutput(expected);
//	}
	
	
	private final void checkOutput(String expected)
			throws Exception {
		// redirect stdin to read in the input string
		//System.setIn(new ByteArrayInputStream(input.getBytes()));

		// redirect stdout to save the result to a byte array
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		// run main
		DesignParser dp = new DesignParser();
		dp.main(new String[0]);

		// see if the expected text is somewhere in the output
		String actual = new String(out.toByteArray());
		boolean result = actual.contains(expected);
		assertTrue("Expected to contain: " + expected + System.lineSeparator()
				+ " Actually is: " + actual, result);

	}
}
