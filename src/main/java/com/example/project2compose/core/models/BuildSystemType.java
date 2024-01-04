package com.example.project2compose.core.models;

public enum BuildSystemType {
    NODE_NPM("NODE_NPM", "NPM"),
    NODE_YARN("NODE_YARN", "NPM+YARN"),
    // bash yskt
    MAVEN("MAVEN", "MAVEN");


    private final String buildSystem;
    private final String buildFile;

    BuildSystemType(final String buildSystem, final String buildFile) {
        this.buildSystem = buildSystem;
        this.buildFile = buildFile;
    }

    @Override
    public String toString() {
        return buildSystem + "_" + buildFile;
    }
}
