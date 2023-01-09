package com.workflow.jdbc.mysql.entity;

import com.workflow.jdbc.mysql.MybatisEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WorkflowTask extends MybatisEntity implements Serializable {

    private final long serialVersionUID = 4359709211352400087L;

    private Long workflowTaskId;

    private String code;

    private Long workflowId;

    private String nodeType;

    private Integer state;

    private LocalDateTime createDate;


}
