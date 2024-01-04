package com.example.project2compose.core.generators.dockerfile.exceptions;

import com.example.project2compose.core.generators.exceptions.GeneratorException;
import com.example.project2compose.core.models.BuildSystemType;

public class NoDockerFileTemplateException extends GeneratorException {
    public NoDockerFileTemplateException(BuildSystemType buildFileType) {
        super("No DockerFile template found for build file of type" + buildFileType);
    }
}
