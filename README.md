topology
========

<img src="http://imglf0.ph.126.net/jHF135rk0rgs29HuY9u4HQ==/3357152047328895525.jpg"/>


storm��<br>
ʵʱ�����ܣ���MapReduce���߼����ܻ������ֱ����ڽ����ͬ�����µ�����


<img src="http://imglf2.ph.126.net/TuoL2jKsDAk4cttP7b5gbg==/2441232473028864113.jpg"/>

<ul>
<li>Nimbus�� ���ڵ㣬������Դ�����������ȣ�</li>
<li>Supervisor�� �ӽڵ㣬�������nimbus���������������ֹͣ�����Լ������worker���̣�</li>
<li>Worker�� ���о��崦������߼��Ľ��̣�</li>
<li>Task�� worker��ÿһ��spout/bolt���̳߳�Ϊһ��task��</li>
</ul>

<img src="http://imglf0.ph.126.net/yftPlvFXiQzLGmDOS3pqeQ==/3357714997282319127.png"/>

Topology<br>
The following image depicts a Storm topology with a simple workflow.


����������<br>
http://storm-project.net/


�������룺<br>
https://github.com/apache/storm


���������<br>
mvn assembly:assembly


��������zookeeper��<br>
sh /Users/hyy044101331/java_tools/zk-jiqun-start.sh


��������storm:<br>
sh /Users/hyy044101331/java_tools/start-storm.sh


�����ύ��ҵ��<br>
 storm jar topology-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.ExclamationTopology "mengka-aa"


��������̨��<br>
http://127.0.0.1:8081/index.html

