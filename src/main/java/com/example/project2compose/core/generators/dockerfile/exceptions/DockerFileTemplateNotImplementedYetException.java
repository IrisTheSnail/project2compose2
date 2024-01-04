package com.example.project2compose.core.generators.dockerfile.exceptions;

import com.example.project2compose.core.generators.exceptions.GeneratorException;
import com.example.project2compose.core.models.BuildSystemType;

public class DockerFileTemplateNotImplementedYetException extends GeneratorException {
    public DockerFileTemplateNotImplementedYetException(BuildSystemType buildFileType) {
        super("DockerFile template not implemented for build file of type" + buildFileType);
    }
}
