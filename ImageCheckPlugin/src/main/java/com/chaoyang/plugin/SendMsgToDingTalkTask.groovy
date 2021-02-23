package com.chaoyang.plugin

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import groovy.json.JsonOutput
import sun.security.x509.Extension


class SendMsgToDingTalkTask extends DefaultTask {
    @Input
    public BaseVariant variant;
    @Input
    public Project targetProject;

    public void setup() {
        description "Send message to DingTalk"
        group "release helper"
    }

    @TaskAction
    def SendMessageToDingTalk() {
        Extension extension = Extension.getConfig(targetProject);

        def requestData = JsonOutput.toJson([actionCard: [title         : extension.msgTitle,
                                                          text          : extension.msgContent,
                                                          hideAvatar    : 0,
                                                          btnOrientation: 0,
                                                          singleTitle   : extension.singleButtonTitle,
                                                          singleURL     : extension.singleButtonUrl], "msgtype": "actionCard"]);
        def requestUrl = 'https://oapi.dingtalk.com/robot/send?access_token=' + extension.apiToken;
        def url = new URL(requestUrl)
        def urlConnection = url.openConnection()

        urlConnection.setDoOutput(true)
        urlConnection.setRequestMethod("POST")
        urlConnection.setRequestProperty("Authorization", "Basic")
        urlConnection.setRequestProperty("Content-Type", "application/json")

        def httpRequestBodyWriter = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()))
        httpRequestBodyWriter.write(requestData)
        httpRequestBodyWriter.close()

        def httpResponseScanner = new Scanner(urlConnection.getInputStream())
        while (httpResponseScanner.hasNextLine()) {
            println(httpResponseScanner.nextLine())
        }
        httpResponseScanner.close()
    }
}