package com.workflow.bpmn;

import com.workflow.common.FlowStreamSource;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileFlowStreamSource implements FlowStreamSource {

    private File file;

    public static FileFlowStreamSource of(File file) {
        FileFlowStreamSource fileFlowStreamSource = new FileFlowStreamSource();
        fileFlowStreamSource.setFile(file);
        return fileFlowStreamSource;
    }


    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public InputStream getFlow() {
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件未找到" + file, e);
        }
    }
}
