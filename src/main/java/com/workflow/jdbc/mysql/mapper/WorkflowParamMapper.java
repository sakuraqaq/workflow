package com.workflow.jdbc.mysql.mapper;

import com.workflow.jdbc.mysql.entity.WorkflowParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
@Component("WorkflowParamMapper1")
public interface WorkflowParamMapper {

    @Insert("insert into workflow_param (workflow_param_id, workflow_id, workflow_task_id, params, create_date) values(#{workflowParamId},#{workflowId},#{workflowTaskId},#{params},#{createDate})")
    Integer insert(WorkflowParam workflowParam);

    @Delete("delete from workflow_param where workflow_param_id=#{id}")
    Integer delete(Serializable id);

    @Update("update workflow_param set params=#{params} where workflow_param_id=#{workflowParamId}")
    Integer update(WorkflowParam workflowParam);

    @Select("select workflow_param_id, workflow_id, workflow_task_id, params, create_date from workflow_param where workflow_task_id=#{id}")
    WorkflowParam selectByTaskId(Serializable id);

    @Select("select workflow_param_id, workflow_id, workflow_task_id, params, create_date from workflow_param where workflow_id=#{workflowId}}")
    List<WorkflowParam> select(Map<String, Object> params);


}
