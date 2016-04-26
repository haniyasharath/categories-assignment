package com.newstar.file.handler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.newstar.file.util.Helper;


public class ProcessFileTest {
	
	private ProcessFile processFile;

	@Before
	public void setUp() throws Exception {
		this.processFile = new ProcessFile();
	}
	
	@After
	public void tearDown() throws Exception {
		this.processFile = null;
	}

	@Test
	public void testProcessFile() {
		processFile.processInputFile(Helper.DEFAULT_FILE_NAME, Helper.PATH);
	}
	
	@Test
	public void testProcessFile_withError() {
		try {
			processFile.processInputFile("inavalidFile.txt", Helper.PATH);
			fail("should get exception for file processing");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
			assertTrue(e.getMessage().contains("Error occured while reading file"));
		}
	}

}
