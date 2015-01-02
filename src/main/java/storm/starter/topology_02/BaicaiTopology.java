package storm.starter.topology_02;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.starter.topology.MengkaBolt;

/**
 *  storm����ģʽ��
 *  1. ����ģʽ��
 *  2. �ֲ�ʽģʽ��
 *
 * Created by mengka
 */
public class BaicaiTopology {

//    private static final Logger log = LoggerFactory.getLogger(BaicaiTopology.class);

    public static void main(String[] args){
        /**
         *  ����topology
         *
         */
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("mengka-spout-word-aa2", new TestWordSpout(), 10);
        builder.setBolt("mengka-bolt-exclaim-aa3", new MengkaBolt(), 3).shuffleGrouping("mengka-spout-word-aa2");
        builder.setBolt("mengka-bolt-exclaim-aa4", new MengkaBolt(), 2).shuffleGrouping("mengka-bolt-exclaim-aa3");

        /**
         *   ����config
         *
         */
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);//ָ����Ⱥ������ٽ�����ִ��topology


        if (args == null && args.length <= 0) {
//            log.error("-----------------, topology name is null!");
            return;
        }

        /**
         *  �ύtopology���������ļ�Ⱥ
         */
        String topologyName = args[0];
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topologyName, conf, builder.createTopology());

        Utils.sleep(10000);
        cluster.killTopology(topologyName);
        cluster.shutdown();
    }
}
