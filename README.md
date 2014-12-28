topology
========

<img src="http://imglf0.ph.126.net/jHF135rk0rgs29HuY9u4HQ==/3357152047328895525.jpg"/>


storm：
实时计算框架，与MapReduce离线计算框架互补，分别用于解决不同场景下的问题


<img src="http://imglf2.ph.126.net/TuoL2jKsDAk4cttP7b5gbg==/2441232473028864113.jpg"/>

Nimbus： 主节点，负责资源分配和任务调度；
Supervisor： 从节点，负责接受nimbus分配的任务，启动和停止属于自己管理的worker进程；
Worker： 运行具体处理组件逻辑的进程；
Task： worker中每一个spout/bolt的线程称为一个task；


<img src="http://imglf0.ph.126.net/yftPlvFXiQzLGmDOS3pqeQ==/3357714997282319127.png"/>

Topology
The following image depicts a Storm topology with a simple workflow.


》》官网：
http://storm-project.net/


》》代码：
https://github.com/apache/storm


》》打包：
mvn assembly:assembly


》》启动zookeeper：
sh /Users/hyy044101331/java_tools/zk-jiqun-start.sh


》》启动storm:
sh /Users/hyy044101331/java_tools/start-storm.sh


》》提交作业：
 storm jar topology-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.ExclamationTopology "mengka-aa"


》》控制台：
http://127.0.0.1:8081/index.html

