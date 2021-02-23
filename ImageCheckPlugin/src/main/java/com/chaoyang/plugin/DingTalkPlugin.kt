package com.chaoyang.plugin

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project

class DingTalkPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val jiagu =
            project.extensions.create(
                "jiagu", JiaGuExt::class.java
            )

        //在gralde configuation完后（三个阶段）

        //在gralde configuation完后（三个阶段）
        project.afterEvaluate { project ->
            val userName = jiagu.userName
            val password = jiagu.password
            val keyStorePath = jiagu.keyStorePath
            val keyStorePass = jiagu.keyStorePass

            //动态获取生成的Apk文件
//            val android: AppExtension = project.extensions.getByName("android") as AppExtension
            val android: AppExtension = project.extensions.getByType(AppExtension::class.java)

            //android 变体
            val applicationVariants = android.applicationVariants
            applicationVariants.all(object : Action<ApplicationVariant> {
                override fun execute(variant: ApplicationVariant) {
                    // 获取到debug/release。。
                    val name = variant.name

                    variant.outputs.all(object :Action<BaseVariantOutput>{
                        override fun execute(p0: BaseVariantOutput) {
                            val outputFile = p0.outputFile
                            project.tasks.create("jiagu$name",JiaGuTask::class.java,
                            outputFile,jiagu)
                        }

                    })


                }

            })
        }
    }
}