# Maven

Maven 是一款用于管理和构建 Java 项目的工具，是 Apache 旗下的一个开源项目。

+ 依赖管理：方便快捷的管理项目依赖的资源（jar 包）；
+ 项目构建：标准的跨平台（Linux、Windows、MacOS）的自动化项目构建方式；
+ 统一项目结构：提供标准、统一的项目结构；
+ 基于项目对象模型（POM）的概念，通过一小段描述信息来管理项目的构建。

## 安装
![](https://cdn.nlark.com/yuque/0/2025/jpeg/29092218/1746601833460-ef442804-2a0a-4f92-835a-701470cac1ba.jpeg)

### settings.xml 配置
```xml
<mirrors>
  <!-- 添加如下子标签 -->
  <mirror>
    <id>aliyunmaven</id>
    <mirrorOf>*</mirrorOf>
    <name>阿里云公共仓库</name>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

```xml
<profiles>
  <!-- 添加如下子标签 -->
  <!-- 可配可不配，如果 JDK 版本变动，这里也需要修改 -->
  <profile>
    <id>jdk-21</id>
    <activation>
      <activeByDefault>true</activeByDefault>
      <jdk>21</jdk>
    </activation>
    <properties>
      <maven.compiler.source>21</maven.compiler.source>
      <maven.compiler.target>21</maven.compiler.target>
      <maven.compiler.compilerVersion>21</maven.compiler.compilerVersion>
    </properties>
  </profile>
</profiles>
```

## IDEA 集成
建议 Maven 和 JDK 版本统一。

### Maven 全局配置
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746602720839-27ac20c4-be1d-41b3-a527-115f39b6ad04.png)

### Maven Runner JRE 配置
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746603178511-e06dc326-890b-4ba5-bb44-ec375b585ebd.png)

### Java Compiler 版本配置
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746603168049-69f4db53-844e-4215-a72f-ac538c071198.png)

## 创建 Maven 项目
### Maven 坐标
+ 坐标是资源（jar）的唯一标识，通过该坐标可以唯一定位资源位置；
+ 使用坐标来定义项目或引入项目中需要的依赖；

#### 坐标组成
1. `groupId`定义当前 Maven 项目隶属组织名称（通常是域名反写，例如：com.xxx）；
2. `artifactId`定义当前 Maven 项目名称（通常是模块名称，例如 goods-service）；
3. `version`定义当前项目版本号；

SNAPSHOT：功能不稳定，尚处于开发中的版本（快照版本）；

RELEASE：功能趋于稳定，当前更新停止，可用于发行的版本；

```xml
<groupId>com.aa</groupId>
<artifactId>goods-service</artifactId>
<version>1.0-SNAPSHOT</version>
```

#### 引入依赖
```xml
<dependencies>
  <!-- 需要安装的依赖 -->
  <dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.12.0</version>
  </dependency>
</dependencies>
```

### 创建 Project
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746603951555-d4a8605e-d192-45ad-b038-b594879de298.png)

### 创建 Module


![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746606281560-acb390dd-3ce6-49ad-8dc7-d90879972cb8.png)

## 导入 Maven 项目模块
### 方式一：Import Module
先将需要导入的项目拷贝至当前项目根目录下，然后再使用 `Import Module`导入 `pom.xml`文件。

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746630173462-ad266610-07eb-4411-9812-3bac58cb8e6a.png)

### 方式二： Maven 面板
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746630599263-099dfa02-bd48-4b6a-b8ef-4d26fd3bd5d6.png)

### 方式三： Add as Maven Project
先将需要导入的项目拷贝至当前项目根目录下，然后右键选择 `Add as Maven Project`

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746631168532-4699e50a-f6d1-4aa4-b886-5dd13b48b56b.png)

## 依赖管理
### 依赖配置
1. 在 pom.xml 中编写`<dependencies>`标签；
2. 在`<dependencies>`标签中 使用`<dependency>`引入坐标；
3. 定义坐标的 groupId、artifactId、version；
4. 点击刷新按钮，引入最新加入的坐标。

如果不知道依赖的坐标信息，可以到 [https://mvnrepository.com/](https://mvnrepository.com) 中搜索。

### 依赖传递
+ 依赖具有传递性，A 依赖 B，B 依赖 C，......
+ 直接依赖：在当前项目中通过依赖配置建立的依赖关系；
+ 间接依赖：被依赖的资源如果依赖其他资源，当前项目间接依赖其他资源；

#### 排除依赖
指主动断开依赖的资源，被排除的资源无需指定版本。

+ \<exclusion> 排除

```xml
<dependency>
  <exclusions>
    <!-- 排除不想要的依赖 -->
    <exclusion>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```

+ `Dependency Analyzer` 插件排除

`File`→`Settings`→`Plugins` → 查找 `Maven Helper`安装

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746667469665-07b27fed-c337-49b0-a134-9d9e112b435a.png)

### 生命周期
+ 对所有的 Maven 项目构建过程进行抽象和统一；
+ Maven 中有三套相互独立的生命周期：Clean、Default、Site；
+ 每套生命周期都包含一些阶段，阶段是有顺序的，后面的阶段依赖于前面的阶段；

#### Clean 周期
+ 用于清理工作，如清理缓存；
+ 有：`pre-clean`、`<font style="color:#DF2A3F;">clean</font>`、`post-clean`三个阶段；
+ <font style="color:#DF2A3F;">clean</font>：移除上一次构建生成的文件

#### Default 周期
+ 核心工作，如：编译、测试、打包、安装、部署等；
+ 有`validate`、`<font style="color:#DF2A3F;">compile</font>`、`<font style="color:#DF2A3F;">test</font>`、`<font style="color:#DF2A3F;">package</font>`、`verify`、`<font style="color:#DF2A3F;">install</font>`、`deploy`等20个阶段；
+ <font style="color:#DF2A3F;">compile</font>：编译项目源代码；
+ <font style="color:#DF2A3F;">test</font>：使用合适的单元测试框架进行测试（Junit）；
+ <font style="color:#DF2A3F;">package</font>：将编译后的文件打包，如：jar、war等；
+ <font style="color:#DF2A3F;">install</font>：安装项目到本地仓库；

#### Site 周期
+ 生成报告，发布站点等；
+ 有：`pre-site`、`site`、`post-site`、`site-deploy` 四个阶段

#### 执行生命周期
+ 方式一：右侧 Maven 工具栏，展开`Lifecycle`选择生命周期，双击执行

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746669084800-81ce1f95-0b3c-433b-b2c0-ba1cd5e04b32.png)

+ 方式二：命令行

进入包含 pom.xml 文件的目录结构，打开命令行。

```bash
mvn clean
```

## Junit5 单元测试
[中文文档](https://doczhcn.gitbook.io/junit5/index)

1. 在 `pom.xml`中引入 JUnit 依赖；

```xml
<dependencies>
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <version>5.10.4</version>
      <scope>test</scope>
  </dependency>
</dependencies>
```

2. 在 `src`文件夹下新建 `Directory`，选择 `test\java`;
3. 创建测试类，编写对应测试方法，并在方法上声明`@Test`注解；
4. 命名规范：类名-`XxxxTest`，方法名-`public void testXxxx()`；

### 常用注解
| 注解 | 说明 | 备注 |
| --- | --- | --- |
| @Test | 测试类中的方法用它修饰才能成为测试方法，才能启动执行 | 单元测试 |
| @BeforeEach | 用来修饰一个实例方法，该方法会在<font style="color:#DF2A3F;">每一个</font>测试方法执行之前执行一次 | 初始化资源(准备工作) |
| @AfterEach | 用来修饰一个实例方法，该方法会在<font style="color:#DF2A3F;">每一个</font>测试方法执行之后执行一次 | 释放资源(清理工作) |
| @BeforeAll | 用来修饰一个静态方法，该方法会在所有测试方法之前<font style="color:#DF2A3F;">只执行一次</font> | 初始化资源(准备工作) |
| @AfterAll | 用来修饰一个静态方法，该方法会在所有测试方法之后<font style="color:#DF2A3F;">只执行一次</font> | 释放资源(清理工作) |
| @ParameterizedTest | 参数化测试的注解(可以让单个测试运行多次，每次运行时仅参数不同) | 用了该注解，就不需要@Test注解了 |
| @ValueSource | 参数化测试的参数来源，赋予测试方法参数 | 与参数化测试注解配合使用 |
| @DisplayName | 指定测试类、测试方法显示的名称(默认为类名、方法名) | |


### 参数化测试
1. 参数化测试允许单元测试执行多次，每次使用不同的参数；
2. `@ParameterizedTest`注解用于声明参数化测试；
3. `@ValueSource`注解用于设置参数值，可以接受不同类型的数组（如 String、Integer 等）；
4. 通过结合使用 `@ParameterizedTest`和 `@ValueSource`，可以实现参数化测试。

```java
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("测试身份证信息类")
public class UserServiceTest {
    @DisplayName("测试年龄")
    @Test
    public void testGetAge() {
        int age = new UserService().getAge("320123199001014567");
        System.out.println("年龄：" + age);
    }

    @DisplayName("测试性别")
    @Test
    public void testGetGender1() {
        String gender = new UserService().getGender("320123199001014567");
        System.out.println("性别：" + gender);
    }

    @DisplayName("参数化测试性别")
    @ParameterizedTest
    @ValueSource(strings = {"320123199001014567", "320123199210104599"})
    public void testGetGender2(String idCard) {
        String gender = new UserService().getGender(idCard);
        System.out.println("性别：" + gender);
    }
}
```

### 断言
Junit 提供了一些辅助方法，用来确定被测试的方法是否按照预期的效果正常工作，这种方式被称为断言。

| 断言方法 | 描述 |
| --- | --- |
| assertEquals(Object exp, 0bject act, String msg) | 检查两个值是否相等，不相等就报错 |
| assertNotEquals(Object unexp, 0bject act, String msg) | 检查两个值是否不相等，相等就报错 |
| assertNull(Object act, String msg) | 检查对象是否为 null，不为 null，就报错 |
| assertNotNull(Object act, String msg) | 检查对象是否不为 null，为 null，就报错 |
| assertTrue(boolean condition, String msg) | 检查条件是否为 true，不为 true，就报错 |
| assertFalse(boolean condition, String msg) | 检查条件是否为 false，不为 false，就报错 |
| assertSame(0bject exp，0bject act，String msg) | 检查两个对象引用是否相等，不相等，就报错 |


**注意事项：** 最后一个参数 msg，表示错误提示信息，可以不指定（有对应的重载方法）。

```java
@DisplayName("测试性别")
@Test
public void testGetGender1() {
    String gender = new UserService().getGender("320123199001014567");
    // 断言
    assertEquals("女", gender, "性别计算错误");
}
```

### 依赖范围
+ 依赖的 jar 包，默认情况下，可以在任何地方使用。可以通过 <scope>...</scope>设置其作用范围；
+ 作用范围：
    - 主程序范围有效（main 文件夹范围内）；
    - 测试程序范围有效（test 文件夹范围内）；
    - 是否参与打包运行（package 指令范围内）；

scope 值以及对应的范围：

| <scope> 值 | 主程序 | 测试程序 | 打包（运行） | 范例 |
| --- | --- | --- | --- | --- |
| compile（默认） | Y | Y | Y | log4j |
| test | N | Y | N | junit |
| provided | Y | Y | N | servlet-api |
| runtime | N | Y | Y | jdbc 驱动 |


```xml
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.10.4</version>
  <!-- 修改 scope 的值可以改变依赖包的使用范围 -->
  <scope>test</scope>
</dependency>
```

## Maven 常见解决方案
### 报红无法下载
**场景：** Maven 项目中添加的依赖，未正确下载，造成右侧 Maven 面板中的依赖报红，即使 reload 也不会重新下载。

**原因：** 由于网络原因，依赖没有下载完整导致的，在 Maven 仓库中生成了 xxx.lastUpdated 文件，该文件不删除，不会重新下载。

**解决：**

1. 根据 Maven 依赖的坐标，找到仓库对应的 `xxx.lastUpdated`文件，删除之后重新加载项目；
2. 通过命令 `del /s *.lastUpdated` 批量递归删除指定目录下的 `xxx.lastUpdated` 文件，再重新加载。

```powershell
del /s *.lastUpdated
```

3. 重新加载之后 Maven 面板可能还会报红，此时清空缓存重新打开 IDEA 加载此项目。

![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746691533853-fbcef596-1f0f-4af1-a778-53afb65ac15a.png)

