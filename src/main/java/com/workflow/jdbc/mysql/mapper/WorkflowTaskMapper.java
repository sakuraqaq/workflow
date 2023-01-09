package com.workflow.jdbc.mysql.mapper;

import com.workflow.jdbc.mysql.entity.WorkflowTask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
@Component("WorkflowTaskMapper1")
public interface WorkflowTaskMapper {

    @Insert("insert into workflow_task (workflow_task_id, workflow_id, code, node_type, state, create_date) values(#{workflowTaskId},#{workflowId},#{code},#{nodeType},#{state},#{createDate})")
    Integer insert(WorkflowTask workflowTask);

    @Update("update workflow_task set code=#{code},node_type=#{nodeType}, state=#{state}, workflow_id=#{workflowId}, create_date=#{createDate} where workflow_task_id=#{workflowTaskId}")
    Integer update(WorkflowTask workflowTask);

    @Delete("delete from workflow_task where workflow_task_id=#{id}}")
    Integer delete(Serializable id);

    @Select("select * from workflow_task where workflow_id=#{workflowId}")
    List<WorkflowTask> select(Serializable workflowId);

    @Insert("<script>" +
            "insert into workflow_task (workflow_task_id, workflow_id, code, node_type, state, create_date) VALUES " +
            "<foreach collection=\"list\" item=\"community\" index=\"index\"  separator=\",\"> " +
            "(#{workflowTaskId},#{workflowId},#{code},#{nodeType},#{state},#{createDate}}}) " +
            "</foreach> " +
            "</script>")
    boolean insertBatch(List<WorkflowTask> list);

}
