package com.workflow.jdbc.mysql.entity;

import com.workflow.jdbc.mysql.MybatisEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WorkflowParam extends MybatisEntity implements Serializable {

    private final long serialVersionUID = 4359709211352400087L;

    private Long workflowParamId;

    private Long workflowId;

    private Long workflowTaskId;

    private String params;

    private LocalDateTime createDate;
}
