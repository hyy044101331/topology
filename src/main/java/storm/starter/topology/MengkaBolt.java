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
 * Created by mengka
 */
public class MengkaBolt extends BaseRichBolt {

    OutputCollector _collector;

    /**
     *  �򵥵ı�����OutputCollector��ʵ����Ϊ�˴������execute������ʹ��
     *
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
    }

    /**
     * execute()����:<br>
     * �ڽ��ܵ���Ϣ�����ô˺������û�����������ִ���Լ���Ҫ�Ĳ���;<br>
     * ������<br>
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //tuple������һ�����
        String source = tuple.getSourceComponent();

        String data = tuple.getString(0);
        _collector.emit(tuple, new Values(data + "#mengka#!!!"));
        _collector.ack(tuple);
    }

    /**
     *  bolt�ᷢ��һ���ֶ���Ϊ"mengka-word"��Ԫ��tuple
     *
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-word"));
    }

    /**
     *  �������ִ�еĸ�������;
     *  ����һ���߶˵Ļ��⣬��Configuration������˽⵽����;
     *
     * @return
     */
    public Map<String, Object> getComponentConfiguration(){
        return null;
    }

    /**
     *  bolt�ڹرյ�ʱ�򱻵��ã��ͷ���Դ
     */
    public void cleanup() {
    }
}
