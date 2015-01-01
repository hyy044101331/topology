package storm.starter.topology;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by mengka
 */
public class MengkaTopology {

    public static void main(String[] args) throws Exception {

        /**
         *  构建topology
         *
         */
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("mengka-spout-word", new TestWordSpout(), 10);
        builder.setBolt("mengka-bolt-exclaim1", new MengkaBolt(), 3).shuffleGrouping("word");
        builder.setBolt("mengka-bolt-exclaim2", new MengkaBolt(), 2).shuffleGrouping("exclaim1");

        /**
         *   设置config
         *
         */
        Config conf = new Config();
        conf.setDebug(true);
        conf.setNumWorkers(3);

        /**
         *  提交topology作业
         *
         */
        if (args != null && args.length > 0) {
            StormSubmitter.submitTopologyWithProgressBar(args[0], conf, builder.createTopology());
        }
    }
}
