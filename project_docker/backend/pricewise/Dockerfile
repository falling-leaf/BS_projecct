# 使用 JDK 21 的基础镜像
FROM openjdk:21-jdk-slim

# 设置工作目录
WORKDIR /app

# 将构建好的 Spring Boot JAR 文件复制到容器中
COPY target/pricewise-0.0.1-SNAPSHOT.jar app.jar

# 将 src 目录的内容复制到容器中的 /app/src 目录
COPY src /app/src

# 暴露应用的端口（通常是 8080）
EXPOSE 8080

# 运行 Spring Boot 应用
ENTRYPOINT ["java", "-jar", "app.jar"]