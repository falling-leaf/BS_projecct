## BS_projecct
《BS体系软件设计》课程大作业

## 1. 启动方法

本项目提供Windows启动（手动启动）与docker启动（自动启动）两种方式。

### 1.1 Windows启动

在Windows启动中，可以通过`msgedgedriver.exe`实现阿里巴巴爬虫的启动。

需要基本配置环境如下：

- `Windows`操作系统
- `MySQL`
- `JDK 21`
- `npm`
- （如要使用阿里巴巴爬虫）`Microsoft Edge 128`

具体启动方式如下：

#### 1.1.1 前端

在`project_win\frontend\pricewise`工作目录下，分别运行如下命令：

```bash
$ npm install
$ npm start
```

之后，在本地的3000端口`http://localhost:3000`，可以看到前端页面。

#### 1.1.2 后端

首先在`project_win\backend\pricewise\src\main\resources\application.properties`中，进行数据库配置连接：

将其中的`spring.datasource.password`字段修改为主机MySQL对应的密码。

在`project_win\backend\pricewise`工作目录下，分别运行如下命令：

```bash
$ mvn clean package
$ java -jar target/pricewise-0.0.1-SNAPSHOT.jar
```

即可完成后端启动。

### 1.2 docker启动

docker启动中，将所有环境（包括数据库）配置在`docker-compose.yml`文件中，通过`docker-compose`命令启动。

需要基本配置环境如下：

- `docker`
- `docker-compose`

在`project_docker`目录下，直接运行如下命令：

```bash
$ docker-compose up --build
```

等待一段时间，直至命令行窗口显示`frontend-1`编译完成后，即可直接访问`http://localhost:3000`进行程序的使用。