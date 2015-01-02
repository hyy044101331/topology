package storm.starter.topology;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import java.util.Map;

/**
 *  取出TaaMengkaBolt里面的数据
 *
 * Created by mengka
 */
public class TbbMengkaBolt extends BaseRichBolt {

    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    /**
     *
     * 在传输过来的tuple数据后加上"-#tbb-mengka#"：
     *
     *  [{{source=mengka-spout-word-aa2}}-mengka EEE--#mengka#!!!-#tbb-mengka#]
     *  [{{source=mengka-spout-word-aa2}}-mengka AAA--#taa-mengka#-#tbb-mengka#]
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        String data = tuple.getString(0);
        String sendContent = data + "-#tbb-mengka#";
        _collector.emit(tuple, new Values(sendContent));
        _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-word"));
    }
}
