package com.example.project2compose.core.generators.dockerfile.templates;

import com.example.project2compose.core.models.Image;

public interface IDockerfileTemplate {
    String generateTopComment(Image image);
    String generateInstallStage(Image image);
    String generateBuildStage(Image image);
    String generateRunStage(Image image);
    String generate(Image image);
}
