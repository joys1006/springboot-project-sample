package com.kakao.api.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MockupService {
    public <T> T data(String fileName) {
        Gson gson = new Gson();

        try {
            InputStream resource = new ClassPathResource(fileName).getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
            String content = reader.lines().collect(Collectors.joining()).trim();

            if (content.charAt(0) == '[') {
                return (T) gson.fromJson(content, List.class);
            }

            return (T) gson.fromJson(content, Map.class);

        } catch (Exception e) {
            throw new RuntimeException("Something wrong in converting data");
        }
    }
}
