package com.newstar.file.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.newstar.file.dto.CategoryInfo;

public class Helper {
	public static final String PATH = "src/main/resources/inputFiles/";
	public static final String FILE_NAME = "inputFile.txt";
	public static final String BLANK = " ";
	public static final String NEW_LINE = "\n";
	public static final String SPACES = "         ";
	public static final List<String> CATEGORY = Arrays.asList("PERSON", "PLACE", "ANIMAL", "COMPUTER", "OTHER");
	public static final Predicate<CategoryInfo> CATEGORY_PREDICATE = input -> input != null && CATEGORY.contains(input.getName());
}
