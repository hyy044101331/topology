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
 *  ȡ��spout������ݣ�Ȼ�󷢸�TbbMengkaBolt
 *
 * Created by mengka
 */
public class TaaMengkaBolt extends BaseRichBolt {

    OutputCollector _collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    /**
     *  ȡ��spout������ݣ�Ȼ����
     *
     *       [{{source=mengka-spout-word-aa2}}-mengka AAA--#taa-mengka#]
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //tuple������һ�����
        String source = tuple.getSourceComponent();

        String data = tuple.getString(0);
        String sendContent = "{{source=" + source + "}}" + data + "-#taa-mengka#";
        _collector.emit(tuple, new Values(sendContent));
        _collector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-word"));
    }
}
