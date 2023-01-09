# workflow
# 主要包结构

bpmn
> * 流程节点的实体类
> * 每个流程节点大致包含
    >   * id,
>   * name,
>   * sourceRef 流入节点,
>   * targetRef 流出目标节点，
>   * type, class, method 对Action的定义。
> * 解析是可以随便扩展的，json也可以需要自己写一份，只要最后变成Model实体类，提供给引擎使用就好。
> * 项目：resources中的 ktvExample.bpmn20 是一个例子，目的就是为了解析xml

process
> * 流程解析类
> * 通过 javax.xml.stream.XMLStreamReader 解析XML文件 获取流程。
> * class BpmnModelConverter.convertToModel() 转化.bpmn文件变为：BpmnModel。
> * process.converter.provider包下 class BpmnElementParserProvider.init() 注册所有的节点解析类
    >   * parser包下包含所有节点解析类 以及abstract class AbstractFlowElementParser父类。 子类比如：StartEventParser

runtime
>* 核心类：abstract class AbstractProcessRuntime
   >  * execute(ElementContainer<FlowElement, FlowNode> process, String method, Map<String, Object> context)方法
        >    * 第一参数为 Process 流程模型，里面包含流程所有节点信息。
>    * 第二个参数为 节点要执行的Method名称
>    * 第三个参数为 流程全局参数
>  * executeProcessInstance(ElementContainer<FlowElement, FlowNode> process, String methodName, Map<String, Object> context)
     >    * 通过传过来的Process流程模型，找到对应的流程执行类(bean)，通过反射调用Method()完成节点执行，返回true/false 判断是否继续递归执行。执行after()进行流程节点入库，executeNext()执行下一个节点任务。
>    * BaseProcessRuntime 实现了基本执行方法。
       >      * executeNode() 通过流程节点类型 找到对应的 节点Runtime类。所有执行类在runtime.impl包下
>      * getProcessInstance（） 通过外部业务人员实现ProcessInstance接口，注入Spring的bean，通过beanName获取到流程节点执行类。
>      * 每个符合bpmn规范的任务节点 都可继承BaseProcessRuntime 然后实现业务逻辑

jdbc
>* 提供给引擎的 持久化操作，还在开发中。

## 例子：
````java
   package com.workflow.controller;

import com.workflow.bpmn.BpmnModel;
import com.workflow.process.ProcessEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WorkflowTestController {


 private final ProcessEngine<BpmnModel> processEngine;

 @GetMapping()
 public String test() {
  processEngine.start("ktvExample", new HashMap<>());
  return "";
 }

 @GetMapping("/doAction")
 public String doAction(Long workflowId){
  Map<String, Object> map = new HashMap<>();
  map.put("payMoney", 1);
  processEngine.doAction(workflowId, map);
  return "";
 }
}



public class TestProcessService extends AbstractProcessInstance {

 public boolean sing(Map<String, Object> context) {
  System.out.println("sing()");
  return true;
 }

 public Boolean originalPrice(Map<String, Object> context) {
  System.out.println("originalPrice()");
  return true;
 }

 public Boolean promotionPrice(Map<String, Object> context) {
  System.out.println("promotionPrice()");
  return true;
 }

 public Boolean payMoney(Map<String, Object> context) {
  System.out.println("payMoney()");
  if (context.get("payMoney") != null) {
   return true;
  } else
   return false;
 }

}
````
