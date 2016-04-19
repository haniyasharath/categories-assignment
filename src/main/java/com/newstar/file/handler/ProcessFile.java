package com.newstar.file.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Sets;
import com.newstar.file.dto.CategoryInfo;

public class ProcessFile {

	private static final String PATH = "src/main/resources/inputFiles/";

	private static final String BLANK = " ";
	private static final String NEW_LINE = "\n";
	private static final String SPACE = "       ";

	private static final List<String> CATEGORY = Arrays.asList("PERSON", "PLACE", "ANIMAL", "COMPUTER", "OTHER");
	public static final Predicate<CategoryInfo> CATEGORY_PREDICATE = input -> input != null && CATEGORY.contains(input.getName());

	public static void main(String[] args) {
		ProcessFile pf = new ProcessFile();
		StringBuilder output = pf.processInputFile("inputFile.txt");
		System.out.println(output.toString());
	}

	public StringBuilder processInputFile(String fName) {

		final Path filePath = Paths.get(PATH, fName);

		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
			Objects.requireNonNull(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LinkedHashSet<String> validPairs = Sets.newLinkedHashSet(lines);

		Map<String, Integer> categoryInfo = new HashMap<String, Integer>();
		List<CategoryInfo> orderedCategoryList = new ArrayList<CategoryInfo>();
		CategoryInfo catInfo;

		int count;
		String key;
		String value;
		for (String str : validPairs) {
			count = 1;
			key = StringUtils.substringBefore(str, BLANK);
			value = StringUtils.substringAfter(str, BLANK);
			catInfo = new CategoryInfo();
			catInfo.setName(key);
			catInfo.setSubCategory(value);
			orderedCategoryList.add(catInfo);
			if (categoryInfo.containsKey(key)) {
				count = categoryInfo.get(key);
				categoryInfo.put(key, ++count);
			} else {
				categoryInfo.put(key, count);
			}

		}
		orderedCategoryList = orderedCategoryList.stream().filter(CATEGORY_PREDICATE).collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		builder.append("CATEGORY").append(SPACE);
		builder.append("COUNT").append(NEW_LINE);
		for (String cat : CATEGORY) {
			builder.append(cat).append(SPACE).append(categoryInfo.get(cat)).append(NEW_LINE);
		}
		builder.append(NEW_LINE);
		for (CategoryInfo info : orderedCategoryList) {
			builder.append(info.getName()).append(" ").append(info.getSubCategory()).append(NEW_LINE);
		}
		return builder;
	}

}
