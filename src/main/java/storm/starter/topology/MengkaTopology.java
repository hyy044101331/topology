package storm.starter.topology;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;

/**
 *
 *  storm jar topology-0.0.1-SNAPSHOT-jar-with-dependencies.jar storm.starter.topology.MengkaTopology "mengka-bb"
 *  <br><br/>
 * Created by mengka
 */
public class MengkaTopology {

    public static void main(String[] args) throws Exception {

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
