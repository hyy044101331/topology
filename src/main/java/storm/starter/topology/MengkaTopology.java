package storm.starter.topology;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

/**
 * ����ģʽ��
 *    storm jar topology-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.topology.MengkaTopology "mengka-bb"
 * <br>
 *    storm kill mengka-ww
 * <br/>
 *
 * Created by mengka
 */
public class MengkaTopology {

    public static void main(String[] args) throws Exception {

        /**
         *  ����topology
         *
         */
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("mengka-spout-word-aa2", new MengkaSpout(), 10);
        builder.setBolt("mengka-bolt-exclaim-aa3", new MengkaBolt(), 3).shuffleGrouping("mengka-spout-word-aa2");
        builder.setBolt("mengka-bolt-exclaim-aa5", new TaaMengkaBolt(), 3).shuffleGrouping("mengka-spout-word-aa2");
        builder.setBolt("mengka-bolt-exclaim-aa6", new TbbMengkaBolt(), 3).shuffleGrouping("mengka-bolt-exclaim-aa5");
        builder.setBolt("mengka-bolt-exclaim-aa4", new TbbMengkaBolt(), 3).shuffleGrouping("mengka-bolt-exclaim-aa3");

        /**
         *   ����config
         *
         */
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);

        /**
         *  �ύtopology��ҵ
         *
         */
        if (args != null && args.length > 0) {
            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        }
    }
}
