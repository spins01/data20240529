plugins {
    id("moduleBusinessPlugin")
}

android {
    namespace = "com.module.net"
    buildFeatures {
        buildConfig=true
    }

    dependencies {
        implementation ("com.github.liujingxing.rxhttp:rxhttp:3.2.7")
//        注解处理器在编译期间仅会在直接依赖它的module去检索以下5个注解
//        @DefaultDomain、@Doman、@Param、@Parser、@OkClient、@Converter
        ksp ("com.github.liujingxing.rxhttp:rxhttp-compiler:3.2.7")
    }
}
