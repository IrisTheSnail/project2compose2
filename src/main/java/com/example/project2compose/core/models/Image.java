package com.example.project2compose.core.models;

//import lombok.AllArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Image {
    // TODO: Change these to private
    public BuildSystemType buildSystemType;
    public String workdir;
    public String installStageImage;
    public String buildStageImage;
    public String runStageImage;
}
