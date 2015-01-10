package storm.starter.topology_02;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.utils.Utils;
import storm.starter.topology_02.bolt.MengkaSocketBolt;
import storm.starter.topology_02.spout.MengkaSocketSpout;

/**
 *  storm����ģʽ��
 *  1. ����ģʽ��
 *  2. �ֲ�ʽģʽ��
 *
 * Created by mengka
 */
public class RealtimeLogTopology {


    public static void main(String[] args){

        if (args == null && args.length <= 0) {
            return;
        }

        String topologyName = args[0];
        String outPath = args[1];

        /**
         *   ����config
         *
         */
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);//ָ����Ⱥ������ٽ�����ִ��topology

        /**
         *  ����topology
         *
         */
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("mengka-socket-spout-1", new MengkaSocketSpout(), 10);
        builder.setBolt("mengka-socket-bolt-2", new MengkaSocketBolt(outPath), 3).shuffleGrouping("mengka-socket-spout-1");


        /**
         *  �ύtopology���������ļ�Ⱥ
         */
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topologyName, conf, builder.createTopology());

        Utils.sleep(10000);
        cluster.killTopology(topologyName);
        cluster.shutdown();
    }
}
