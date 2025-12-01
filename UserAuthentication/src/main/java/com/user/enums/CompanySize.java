package com.user.enums;

import java.util.Arrays;

import com.user.handler.BadRequestException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public enum CompanySize {
	Small ("0-20"),
	Medium ("21-50"),
	Large ("51-120");
	
	private final String range;

    public static CompanySize findByRange(String range){
        return Arrays.stream(CompanySize.values())
                .filter(size -> size.getRange().equals(range))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Company size not found for range: {}", range);
                    return new BadRequestException("Company size not found for range: " + range);
                });
    }
}
