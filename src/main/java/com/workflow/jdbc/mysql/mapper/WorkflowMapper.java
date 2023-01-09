package com.workflow.jdbc.mysql.mapper;

import com.workflow.jdbc.mysql.entity.Workflow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static org.apache.naming.SelectorContext.prefix;

@Component("WorkflowMapper1")
public interface WorkflowMapper {

    @Insert("insert into workflow (workflow_id, parent_id, code, now_node, is_closed, create_date, app_id) values(#{workflowId},#{parentId},#{code},#{nowNode},#{isClosed},#{createDate}, #{appId})")
    Integer insert(Workflow workflow);

    @Update({
            "<script> ",
            "update workflow ",
            "<trim prefix=\"set\" suffixOverrides=\",\">",
            "<if test = \"code != null\"> ",
            "code=#{code}, ",
            "</if> ",
            "<if test = \"parentId != null\"> ",
            "parent_id=#{parent_id}, ",
            "</if> ",
            "<if test = \"nowNode != null\"> ",
            "now_node=#{nowNode}, ",
            "</if> ",
            "<if test = \"isClosed != null\"> ",
            "is_closed=#{isClosed}, ",
            "</if> ",
            "<if test = \"createDate != null\"> ",
            "create_date=#{createDate}, ",
            "</if> ",
            "</trim>",
            "WHERE workflow_id=#{workflowId}",
                    "</script>"
    })
    Integer update(Workflow workflow);

    @Insert("<script>" +
            "insert into workflow (workflow_id, parent_id, code, now_node, is_closed, create_date, app_id) VALUES " +
            "<foreach collection=\"list\" item=\"community\" index=\"index\"  separator=\",\"> " +
            "(#{workflowId},#{parentId},#{code},#{nowNode},#{isClosed},#{createDate},#{appId}}}) " +
            "</foreach> " +
            "</script>")
    boolean insertBatch(List<Workflow> list);

    @Delete("delete from workflow where workflow_id=#{id}")
    Integer delete(Serializable id);

    @Select("select * from workflow where workflow_id=#{id}")
    Workflow selectOne(Serializable id);

    @Select("<script>" +
            "SELECT * FROM workflow  where " +
            "app_id = #{appId}" +
            "</script>")
    List<Workflow> select(Map<String, Object> params);
}
