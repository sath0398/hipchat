package com.atlassian.extractors;

import com.atlassian.visitor.Visitor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MentionExtractor extends Extractor {

    private static final Pattern PATTERN = Pattern.compile("@\\w+", Pattern.UNICODE_CHARACTER_CLASS);

    @Override
    public void accept(Visitor vistor) {
        vistor.visit(this);
    }

    @Override
    public List<String> parseMentions(String input) {
        String[] result = input.split("\\s");
        return Arrays.stream(result)
                .filter(s -> PATTERN.matcher(s).matches())
                .collect(Collectors.toList());

    }
}
