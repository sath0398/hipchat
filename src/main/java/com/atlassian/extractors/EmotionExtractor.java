package com.atlassian.extractors;

import com.atlassian.visitor.Visitor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by satheish on 2/19/17.
 */

@Service
public class EmotionExtractor extends Extractor {

    private static final Pattern PATTERN = Pattern.compile("\\([\\p{Alnum}]{1,15}\\)",
            Pattern.UNICODE_CHARACTER_CLASS);


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }

    @Override
    public List<String> parseEmotions(String input) {
        String[] result = input.split("\\s");
        return Arrays.stream(result)
                .filter(s -> PATTERN.matcher(s).matches())
                .collect(Collectors.toList());


    }
}
