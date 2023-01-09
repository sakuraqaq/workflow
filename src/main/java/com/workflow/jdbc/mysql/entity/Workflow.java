package com.workflow.jdbc.mysql.entity;

import com.workflow.jdbc.mysql.MybatisEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Workflow extends MybatisEntity implements Serializable {

    private final long serialVersionUID = 4359709211352400087L;

    private Long workflowId;

    private Long parentId;

    private String code;

    private String nowNode;

    private Integer isClosed;

    private LocalDateTime createDate;

    private Long appId;
}
