# MCP-Gradle

Minecraft 1.8.9 Optifine-M6_pre2 的完整源代码，与普通 MCP 不同的是，它使用 Gradle 构建系统。

该项目已预配置 Gradle 国内镜像源，便于中国开发者们更快速地进行构建。

## 分支说明

游戏本身的源代码来自 https://github.com/GNU-Pattor-Team/Optifine-Sources

- master 分支：未经任何代码修改的原始源代码，仅配置了基本的 Gradle 构建流程。
- optimized 分支：基于 master 分支，已在 IntelliJ IDEA 中进行代码格式化和清理，优化了代码结构，用起来更省心，看起来更赏心悦目。推荐使用。

## 如何使用

首先你需要同步 Gradle 构建系统。

- 运行客户端：执行 Gradle 任务 `runClient` 启动 Minecraft 客户端。
- 打包客户端：运行 Gradle 任务 `shadowJar` 以生成可分发的客户端 JAR 文件。
