package com.example.project2compose.core.generators.dockerfile;

import com.example.project2compose.core.generators.dockerfile.exceptions.DockerFileTemplateNotImplementedYetException;
import com.example.project2compose.core.generators.dockerfile.exceptions.NoDockerFileTemplateException;
import com.example.project2compose.core.generators.dockerfile.templates.NodeDockerfileTemplate;
import com.example.project2compose.core.models.BuildSystemType;
import com.example.project2compose.core.models.Image;


public class DockerfileGenerator {
    private static DockerfileGenerator instance;
    private static NodeDockerfileTemplate npmDockerfileTemplate;

    private DockerfileGenerator(){
        npmDockerfileTemplate = new NodeDockerfileTemplate();
    }

    public static DockerfileGenerator getInstance(){
        if(instance == null){
            instance = new DockerfileGenerator();
        }
        return instance;
    }

    public String generate(Image image){
        BuildSystemType buildFileType = image.getBuildSystemType();
        return switch (buildFileType) {
            case NODE_NPM, NODE_YARN -> npmDockerfileTemplate.generate(image);
            case MAVEN -> throw new DockerFileTemplateNotImplementedYetException(buildFileType);
            default -> throw new NoDockerFileTemplateException(buildFileType);
        };
    }
}
