package com.example.project2compose.core.generators.dockerfile.exceptions;

import com.example.project2compose.core.generators.exceptions.GeneratorException;
import com.example.project2compose.core.models.BuildSystemType;

public class InvalidBuildSystemTypeException extends GeneratorException {
    public InvalidBuildSystemTypeException(String dockerFileTemplate, BuildSystemType BuildFileType){
        super("DockerFile template " + dockerFileTemplate + " found an unexpected build system type" + BuildFileType);
    }
}
