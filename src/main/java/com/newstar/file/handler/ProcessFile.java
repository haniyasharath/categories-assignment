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

import static com.newstar.file.util.Helper.*;

public class ProcessFile {

	public static void main(String[] args) {
		ProcessFile pf = new ProcessFile();
		pf.processInputFile(FILE_NAME);
	}

	public void processInputFile(String fName) {

		final Path filePath = Paths.get(PATH, fName);

		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
			Objects.requireNonNull(lines);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("Error occured while reading file");
		}
		LinkedHashSet<String> validPairs = Sets.newLinkedHashSet(lines);

		Map<String, Integer> categoryCountDetails = new HashMap<String, Integer>();
		initializeCountDetails(categoryCountDetails);
		List<CategoryInfo> orderedCategoryList = new ArrayList<CategoryInfo>();
		CategoryInfo categoryInfo;

		int count;
		String key;
		String value;
		for (String str : validPairs) {
			count = 1;
			key = StringUtils.substringBefore(str, BLANK);
			value = StringUtils.substringAfter(str, BLANK);
			categoryInfo = new CategoryInfo(key, value);
			orderedCategoryList.add(categoryInfo);
			if(CATEGORY.contains(key)) {
				if (categoryCountDetails.containsKey(key)) {
					count = categoryCountDetails.get(key);
					categoryCountDetails.put(key, ++count);
				} else {
					categoryCountDetails.put(key, count);
				}
			}

		}
		orderedCategoryList = orderedCategoryList.stream().filter(CATEGORY_PREDICATE).collect(Collectors.toList());
		displayResult(categoryCountDetails, orderedCategoryList);
	}

	private void initializeCountDetails(Map<String, Integer> categoryCountDetails) {
		CATEGORY.forEach(category -> {
			categoryCountDetails.put(category, 0);
		});
	}

	private void displayResult(Map<String, Integer> categoryCountDetails, List<CategoryInfo> orderedCategoryList) {
		StringBuilder builder = new StringBuilder();
		builder.append("CATEGORY").append(SPACES);
		builder.append("COUNT").append(NEW_LINE);
		for (String cat : CATEGORY) {
			builder.append(cat).append(SPACES).append(categoryCountDetails.get(cat)).append(NEW_LINE);
		}
		builder.append(NEW_LINE);
		for (CategoryInfo info : orderedCategoryList) {
			builder.append(info.getName()).append(" ").append(info.getSubCategory()).append(NEW_LINE);
		}
		System.out.println(builder.toString());
	}
}
