package com.github.saintukrainian.chatapp.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class QueryParser {

  public static final String AND_SYMBOL = "&";
  public static final String EQUALS_SYMBOL = "=";

  public static Map<String, String> parse(String query) {
    if (StringUtils.isEmpty(query)) {
      return Map.of();
    }
    return List.of(query.split(AND_SYMBOL)).stream()
        .map(keyValue -> keyValue.split(EQUALS_SYMBOL))
        .collect(Collectors.toMap(list -> list[0], list -> list[1]));
  }
}
