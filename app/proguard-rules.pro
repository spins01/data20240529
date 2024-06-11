# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

###################### Gson start ######################

# Gson uses generic type information stored in a class file when working with
# fields. Proguard removes such information by default, keep it.
-keepattributes Signature

# This is also needed for R8 in compat mode since multiple
# optimizations will remove the generic signature such as class
# merging and argument removal. See:
# https://r8.googlesource.com/r8/+/refs/heads/main/compatibility-faq.md#troubleshooting-gson-gson
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken

# Optional. For using GSON @Expose annotation
-keepattributes AnnotationDefault,RuntimeVisibleAnnotations

###################### Gson end ######################

###################### 阿里云 oss start ######################

-keep class com.alibaba.sdk.android.oss.** { *; }
-dontwarn okio.**
-dontwarn org.apache.commons.codec.binary.**

######################  阿里云 oss end ######################

###################### 推送 start ######################

# oppo 的
-keep public class * extends android.app.Service
-keep interface com.heytap.msp.** { *;}
-keep class com.heytap.msp.** { *;}

###################### 推送 end   ######################

######################云刷脸混淆规则   faceverify-BEGIN#######################
####
#不混淆内部类
-keepattributes InnerClasses

#不混淆jni调用类
-keepclasseswithmembers class *{
    native <methods>;
}

###################### 网易七鱼客服-BEGIN ###########################

-dontwarn com.qiyukf.**
-keep class com.qiyukf.** {*;}
-dontwarn com.netease.**
-keep class com.netease.** {*;}
-dontwarn org.slf4j.**
-keep class org.slf4j.** { *; }

###################### 网易七鱼客服-end ###########################

###################### faceverify-BEGIN ###########################
-ignorewarnings
-keep public class com.tencent.ytcommon.**{*;}
-keep class com.tencent.turingfd.sdk.mfa.TNative$aa { public *; }
-keep class com.tencent.turingfd.sdk.mfa.TNative$aa$bb { public *;}
-keep class com.tencent.turingcam.** {*;}
-keep class com.tencent.turingfd.** {*;}

-keep public class com.tencent.youtu.ytagreflectlivecheck.jni.**{*;}
-keep public class com.tencent.youtu.ytagreflectlivecheck.YTAGReflectLiveCheckInterface{
    public <methods>;
}
-keep public class com.tencent.youtu.ytposedetect.jni.**{*;}
-keep public class com.tencent.youtu.ytposedetect.data.**{*;}
-keep public class com.tencent.youtu.liveness.YTFaceTracker{*;}
-keep public class com.tencent.youtu.liveness.YTFaceTracker$*{*;}

-keep public class com.tencent.cloud.huiyansdkface.facelight.net.*$*{
    *;
}
-keep public class com.tencent.cloud.huiyansdkface.facelight.net.**{
    *;
}
-keep public class com.tencent.cloud.huiyansdkface.facelight.config.cdn.WbUiTips{
    *;
}

#================数据上报混淆规则 start===========================
#实体类
-keep class com.tencent.cloud.huiyansdkface.analytics.EventSender{
    *;
}
-keep class com.tencent.cloud.huiyansdkface.analytics.EventSender$*{
    *;
}
-keep class com.tencent.cloud.huiyansdkface.analytics.WBSAEvent{
     *;
}
-keep class com.tencent.cloud.huiyansdkface.analytics.WBSAParam{
     *;
}
#================数据上报混淆规则 end===========================

#######################faceverify-END#############################

####################### normal混淆规则-BEGIN#############################
#不混淆内部类
-keepattributes InnerClasses
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions

-keep public class com.tencent.cloud.huiyansdkface.normal.net.*$*{
    *;
}
-keep public class com.tencent.cloud.huiyansdkface.normal.net.*{
    *;
}
#bugly
-keep class com.tencent.bugly.idasc.**{
    *;
}
#wehttp混淆规则
-dontwarn com.tencent.cloud.huiyansdkface.okio.**
-keep class com.tencent.cloud.huiyansdkface.okio.**{
    *;
}
-dontwarn com.tencent.cloud.huiyansdkface.okhttp3.OkHttpClient$Builder

####################### normal混淆规则-END #############################

# ------------------------ 为确保对堆栈轨迹进行轨迹还原时清楚明确, Bugly 文档中抄的 ------------------------

# -keepattributes LineNumberTable,SourceFile
# -renamesourcefileattribute SourceFile

# ------------------------ 为确保对堆栈轨迹进行轨迹还原时清楚明确, Bugly 文档中抄的 ------------------------

# ------------------------ 网易一键登录 ------------------------

-dontwarn com.cmic.sso.sdk.**
-keep class com.cmic.sso.**{*;}
-dontwarn com.sdk.**
-keep class com.sdk.** { *;}
-keep class cn.com.chinatelecom.account.**{*;}
-keep public class * extends android.view.View
-keep class com.netease.nis.quicklogin.entity.**{*;}
-keep class com.netease.nis.quicklogin.listener.**{*;}
-keep class com.netease.nis.quicklogin.QuickLogin{
    public <methods>;
    public <fields>;
}
-keep class com.netease.nis.quicklogin.helper.UnifyUiConfig{*;}
-keep class com.netease.nis.quicklogin.helper.UnifyUiConfig$Builder{
     public <methods>;
     public <fields>;
 }
-keep class com.netease.nis.quicklogin.utils.LoginUiHelper$CustomViewListener{
     public <methods>;
     public <fields>;
}
-keep class com.netease.nis.basesdk.**{
    public *;
    protected *;
}

# ------------------------ 网易一键登录 ------------------------

# ------------------------ UCrop https://github.com/Yalantis/uCrop ----------------------
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }
# ------------------------ UCrop ----------------------

# ------------------------ RxFFmpeg ----------------------
-dontwarn io.microshow.rxffmpeg.**
-keep class io.microshow.rxffmpeg.**{*;}
# ------------------------ RxFFmpeg ----------------------

# ------------------------ SVGAPlayer ----------------------
# https://github.com/svga/SVGAPlayer-Android
-keep class com.squareup.wire.** { *; }
-keep class com.opensource.svgaplayer.proto.** { *; }
# ------------------------ SVGAPlayer ----------------------

# ------------------------ bugly ----------------------
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# ------------------------ bugly ----------------------

# ------------------------ im 腾讯 ----------------------
-keep class com.tencent.imsdk.** { *; }
# ------------------------ im 腾讯 ----------------------

# ------------------------ 微信 腾讯 ----------------------

# 所有本包下面的类和接口都不混淆
-keep class com.tencent.** {*;}
-keep interface com.tencent.** {*;}

-keep class com.tencent.mm.opensdk.** {
    *;
}
-keep class com.tencent.wxop.** {
    *;
}
-keep class com.tencent.mm.sdk.** {
    *;
}
# ------------------------ 微信 腾讯 ----------------------

# ------------------------ 即构 ----------------------
-keep class **.zego.**{*;}
# ------------------------ 即构 ----------------------

# ------------------------ retrofit ----------------------

# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# With R8 full mode generic signatures are stripped for classes that are not kept.
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# ------------------------ retrofit ----------------------

# ------------------------ okhttp ----------------------

# JSR 305 annotations are for embedding nullability information.
-dontwarn javax.annotation.**

# A resource is loaded with a relative path so the package of this class must be preserved.
-adaptresourcefilenames okhttp3/internal/publicsuffix/PublicSuffixDatabase.gz

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt and other security providers are available.
-dontwarn okhttp3.internal.platform.**
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# ------------------------ okhttp ----------------------

# ------------------------ okio ----------------------

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# ------------------------ okio ----------------------

# ------------------------ 小金子mvvm reactive 框架 start ----------------------
# 忽略 com.xiaojinzi.reactive.anno.IntentProcess 注解标记的所有方法
-keepclasseswithmembers class * {
    @com.xiaojinzi.reactive.anno.IntentProcess *;
}
# ------------------------ 小金子mvvm reactive 框架 end ----------------------

# ------------------------ 小金子组件化框架 不要警告 ----------------------
-dontwarn com.xiaojinzi.component.**
# 所有本包下面的类和接口都不混淆
-keep class com.xiaojinzi.component.** {*;}
-keep interface com.xiaojinzi.component.** {*;}
#这些是让被标记的类不被混淆
-keep @com.xiaojinzi.component.anno.InterceptorAnno class * {*;}
-keep @com.xiaojinzi.component.anno.GlobalInterceptorAnno class * {*;}
-keep @com.xiaojinzi.component.anno.ConditionalAnno class * {*;}
-keep @com.xiaojinzi.component.anno.FragmentAnno class * {*;}
-keep @com.xiaojinzi.component.anno.ServiceAnno class * {*;}
-keep @com.xiaojinzi.component.anno.support.ComponentGeneratedAnno class * {*;}
-keep @com.xiaojinzi.component.anno.router.RouterApiAnno interface * {*;}
#保留生成的 Router Api
-keep class **.**RouterApiGenerated {*;}
#几个用户自定义或者自动生成到其他包下的应该不混淆
-keep class * implements com.xiaojinzi.component.impl.RouterInterceptor{*;}
-keep class * implements com.xiaojinzi.component.support.IBaseLifecycle{*;}
-keep class * implements com.xiaojinzi.component.application.IApplicationLifecycle{*;}
-keep class * implements com.xiaojinzi.component.application.IComponentApplication{*;}
-keep class * implements com.xiaojinzi.component.support.Inject{*;}
# ------------------------ 小金子组件化框架 不要警告 ----------------------

# ------------------------ 其他 ----------------------

-dontwarn com.tencent.qimei.codez.FalconSdk
-dontwarn com.tencent.qimei.codez.IFalconSdk
-dontwarn com.tencent.qimei.codez.shell.UserInfoType
-dontwarn com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension
-dontwarn com.tencent.smtt.sdk.WebSettings
-dontwarn com.tencent.smtt.sdk.WebView
-dontwarn com.tencent.smtt.sdk.WebViewClient

# ------------------------ 其他 ----------------------