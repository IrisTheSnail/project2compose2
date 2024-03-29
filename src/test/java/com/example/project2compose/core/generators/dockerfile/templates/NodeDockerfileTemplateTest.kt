package com.example.project2compose.core.generators.dockerfile.templates;

import com.example.project2compose.core.generators.dockerfile.exceptions.InvalidBuildSystemTypeException
import com.example.project2compose.core.models.BuildSystemType
import com.example.project2compose.core.models.Image
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.assertThrows

class NodeDockerfileTemplateTest {

    @Test
    fun `should generate top comment`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
            BuildSystemType.NODE_NPM,
            "/project",
            "node",
            "node",
            "node"
        )

        val topComment = nodeDockerfileTemplate.generateTopComment(image)
        assertThat(topComment).isEqualTo("""# THIS IS AN AUTOGENERATED DOCKERFILE
            # Template: npm
            
        """.trimIndent())
    }

    @Test
    fun `should generate install stage for NODE_NPM `() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node:latest",
                "node",
                "node"
        )

        val installStage = nodeDockerfileTemplate.generateInstallStage(image)
        assertThat(installStage).isEqualTo("""# Stage 1: install
            FROM node:latest AS install
            WORKDIR /project
            COPY package.json .
            COPY package-lock.json .
            RUN npm install
            
        """.trimIndent())
    }

    @Test
    fun `should generate install stage for NODE_YARN `() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_YARN,
                "/project",
                "node:latest",
                "node",
                "node"
        )

        val installStage = nodeDockerfileTemplate.generateInstallStage(image)
        assertThat(installStage).isEqualTo("""# Stage 1: install
            FROM node:latest AS install
            WORKDIR /project
            COPY package.json .
            COPY yarn.lock .
            RUN yarn
            
        """.trimIndent())
    }

    @Test
    fun `should fail to generate install stage for a non-node build system`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.MAVEN,
                "/project",
                "node",
                "node",
                "node"
        )

        assertThrows<InvalidBuildSystemTypeException> {
            nodeDockerfileTemplate.generateInstallStage(image)
        }
    }

    @Test
    fun `should generate the build stage`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node",
                "node:latest",
                "node"
        )

        val buildStage = nodeDockerfileTemplate.generateBuildStage(image)
        assertThat(buildStage).isEqualTo("""# Stage 2: build
                FROM node:latest AS build
                
        """.trimIndent())
    }

    @Test
    fun `should base the build stage on the install one if needed`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node:latest",
                null,
                "node"
        )

        val buildStage = nodeDockerfileTemplate.generateBuildStage(image)
        assertThat(buildStage).isEqualTo("""# Stage 2: build
                FROM install AS build
                
        """.trimIndent())
    }

    @Test
    fun `should fail to generate build stage for a non-node build system`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.MAVEN,
                "/project",
                "node",
                "node",
                "node"
        )

        assertThrows<InvalidBuildSystemTypeException> {
            nodeDockerfileTemplate.generateBuildStage(image)
        }
    }

    @Test
    fun `should generate the run stage when build system is NODE_NPM`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node",
                null,
                "node:latest"
        )

        val buildStage = nodeDockerfileTemplate.generateRunStage(image)
        assertThat(buildStage).isEqualTo("""# Stage 3: run
                FROM node:latest AS run
                RUN npm start
                
        """.trimIndent())
    }

    @Test
    fun `should generate the run stage when build system is NODE_YARN`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_YARN,
                "/project",
                "node",
                null,
                "node:latest"
        )

        val buildStage = nodeDockerfileTemplate.generateRunStage(image)
        assertThat(buildStage).isEqualTo("""# Stage 3: run
                FROM node:latest AS run
                RUN yarn dev
                
        """.trimIndent())
    }

    @Test
    fun `should base the run stage one the build stage if needed`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node",
                "node:latest",
                null
        )

        val buildStage = nodeDockerfileTemplate.generateRunStage(image)
        assertThat(buildStage).isEqualTo("""# Stage 3: run
                FROM build AS run
                RUN npm start
                
        """.trimIndent())
    }

    @Test
    fun `should fail to generate run stage for a non-node build system`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.MAVEN,
                "/project",
                "node",
                "node",
                "node"
        )

        assertThrows<InvalidBuildSystemTypeException> {
            nodeDockerfileTemplate.generateRunStage(image)
        }
    }

    @Test
    fun `should generate dockerfile`() {
        val nodeDockerfileTemplate = NodeDockerfileTemplate();

        val image = Image(
                BuildSystemType.NODE_NPM,
                "/project",
                "node:latest",
                null,
                null
        )

        val dockerfile = nodeDockerfileTemplate.generate(image)
        assertThat(dockerfile).isEqualTo("""
            # THIS IS AN AUTOGENERATED DOCKERFILE
            # Template: npm
            
            # Stage 1: install
            FROM node:latest AS install
            WORKDIR /project
            COPY package.json .
            COPY package-lock.json .
            RUN npm install
            
            # Stage 2: build
            FROM install AS build
            
            # Stage 3: run
            FROM build AS run
            RUN npm start
            
        """.trimIndent())
    }


}
