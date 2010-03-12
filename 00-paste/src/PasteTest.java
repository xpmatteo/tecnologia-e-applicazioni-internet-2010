import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;



public class PasteTest {

	@Test
	public void simple() throws Exception {
		assertTrue("Socrate è un mortale", true);
	}

	@Test
	public void pasteLinesFromArrays() throws Exception {
		List<String> a = Arrays.asList("aa", "bb");
		List<String> b = Arrays.asList("xx", "zz");
		
		List<String> result = new ArrayList();
		
		Concatenator concatenator = new Concatenator();
		concatenator.concatenate(result, a, b);
		assertEquals(Arrays.asList("aaxx", "bbzz"), result);
	}
	
	@Test
	public void listsCanBeOfDifferentLength() throws Exception {
		List<String> a = Arrays.asList("a");
		List<String> b = Arrays.asList("b", "c");
		
		List<String> result = new ArrayList();
		
		Concatenator concatenator = new Concatenator();
		concatenator.concatenate(result, a, b);
		assertEquals(Arrays.asList("ab", "c"), result);
	}
	
	http://matteo.vaccari.name/tai
	
	@Test
	public void testname() throws Exception {
		InputStream stream = new ByteArrayInputStream("aaa\nbbb\n".getBytes());
		StreamToLines streamToLines = new StreamToLines(stream);
		streamToLines.getLines();
	}
	
//	public void comeAprireUnFile() throws Exception {
//		InputStream stream = new FileInputStream("/etc/passwd");
//	}
//	
//	public void comeLeggereUnoStreamUnaRigaAllaVolta() throws Exception {
//		InputStream stream = new FileInputStream("/etc/passwd");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//		String line = null;
//		while (null != (line = reader.readLine())) {
//			// process line...
//		}
//	}
}
