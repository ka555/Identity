package utils;

import models.CarDetails;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    private static final Pattern REG_PATTERN = Pattern.compile("\\b[A-Z]{2}\\d{2}\\s?[A-Z]{3}\\b");
    private static Path path;

    public static List<String> parseInputFile(String fileName) throws IOException {
        ClassLoader classLoader = FileParser.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        assert resource != null;
        path = Paths.get(resource.getPath());
        String content = Files.readString(path);
        return extractRegNumbers(content);
    }

    private static List<String> extractRegNumbers(String text) {
        Matcher matcher = REG_PATTERN.matcher(text);
        List<String> regNumbers = new ArrayList<>();
        while (matcher.find()) {
            regNumbers.add(normalizeReg(matcher.group()));
        }
        return regNumbers;
    }

    public static Map<String, CarDetails> parseOutputFile(String fileName) throws IOException {
        ClassLoader classLoader = FileParser.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        assert resource != null;
        path = Paths.get(resource.getPath());
        List<String> lines = Files.readAllLines(path);
        Map<String, CarDetails> detailsMap = new HashMap<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            String reg = normalizeReg(parts[0]);
            detailsMap.put(reg, new CarDetails((parts[1]), parts[2]));
        }
        return detailsMap;
    }

    private static String normalizeReg(String reg) {
        return reg.replaceAll("\\s+", "").toUpperCase();
    }
}
