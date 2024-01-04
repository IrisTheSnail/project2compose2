package com.example.project2compose.core.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Project {
    private String name;
    private String version;
    private Service[] services;
}
