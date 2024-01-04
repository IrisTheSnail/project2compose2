package com.example.project2compose.core.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Service {
    private String name;
    private String version;
    private String port;
    private Image image;
    private String[] env;
}
