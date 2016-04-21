package com.newstar.file.handler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.newstar.file.util.Helper;


public class ProcessFileTest {
	
	private ProcessFile processFile;

	@Before
	public void setUp() throws Exception {
		processFile = new ProcessFile();
	}

	@Test
	public void testProcessFile() {
		processFile.processInputFile(Helper.FILE_NAME);
	}
	
	@Test
	public void testProcessFile_withError() {
		try {
			processFile.processInputFile("inavalidFile.txt");
			fail("Has to file file processing");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}

}
