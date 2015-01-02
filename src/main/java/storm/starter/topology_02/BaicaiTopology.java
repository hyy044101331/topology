package storm.starter.topology_02;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.starter.topology.MengkaBolt;

/**
 *  storm工作模式：
 *  1. 本地模式；
 *  2. 分布式模式；
 *
 * Created by mengka
 */
public class BaicaiTopology {

//    private static final Logger log = LoggerFactory.getLogger(BaicaiTopology.class);

    public static void main(String[] args){
        /**
         *  构建topology
         *
         */
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("mengka-spout-word-aa2", new TestWordSpout(), 10);
        builder.setBolt("mengka-bolt-exclaim-aa3", new MengkaBolt(), 3).shuffleGrouping("mengka-spout-word-aa2");
        builder.setBolt("mengka-bolt-exclaim-aa4", new MengkaBolt(), 2).shuffleGrouping("mengka-bolt-exclaim-aa3");

        /**
         *   设置config
         *
         */
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);//指定集群分配多少进程来执行topology


        if (args == null && args.length <= 0) {
//            log.error("-----------------, topology name is null!");
            return;
        }

        /**
         *  提交topology到这个虚拟的集群
         */
        String topologyName = args[0];
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topologyName, conf, builder.createTopology());

        Utils.sleep(10000);
        cluster.killTopology(topologyName);
        cluster.shutdown();
    }
}
