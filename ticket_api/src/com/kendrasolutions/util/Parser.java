package com.kendrasolutions.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.common.base.Strings;

public class Parser {
	public Map<String, List<String>> splitQuery(URL url) {
	    if (Strings.isNullOrEmpty(url.getQuery())) {
	        return Collections.emptyMap();
	    }
	    return Arrays.stream(url.getQuery().split("&"))
	            .map(this::splitQueryParameter)
	            .collect(Collectors.groupingBy(SimpleImmutableEntry::getKey, LinkedHashMap::new, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
	}

	public SimpleImmutableEntry<String, String> splitQueryParameter(String it) {
		it = decode(it);
	    final int idx = it.indexOf("=");
	    final String key = idx > 0 ? it.substring(0, idx) : it;
	    final String value = idx > 0 && it.length() > idx + 1 ? it.substring(idx + 1) : null;
	    return new SimpleImmutableEntry<>(key, value);
	}
	
	private String decode(String value) {
	    try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static long getTimestampDiffMin(Timestamp currentTime, Timestamp oldTime){
		long milliseconds1 = oldTime.getTime();
		long milliseconds2 = currentTime.getTime();
		long diff = milliseconds2 - milliseconds1;
		long diffMinutes = diff / (60 * 1000);
		return diffMinutes;
	}
	
	public static long getTimestampDiffDay(Timestamp currentTime, Timestamp oldTime){
		long milliseconds1 = oldTime.getTime();
		long milliseconds2 = currentTime.getTime();
		long diff = milliseconds2 - milliseconds1;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;
	}

}
