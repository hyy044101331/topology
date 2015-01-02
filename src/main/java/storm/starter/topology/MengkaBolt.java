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
     *  简单的保存了OutputCollector的实例，为了待会儿在execute方法中使用
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
     * execute()函数:<br>
     * 在接受到消息后会调用此函数，用户可以在其中执行自己想要的操作;<br>
     * 被动；<br>
     *
     * @param tuple
     */
    @Override
    public void execute(Tuple tuple) {
        //tuple来自哪一个组件
        String source = tuple.getSourceComponent();

        String data = tuple.getString(0);
        _collector.emit(tuple, new Values(data + "#mengka#!!!"));
        _collector.ack(tuple);
    }

    /**
     *  bolt会发送一个字段名为"mengka-word"的元组tuple
     *
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-word"));
    }

    /**
     *  配置组件执行的各个方面;
     *  这是一个高端的话题，在Configuration你可以了解到更多;
     *
     * @return
     */
    public Map<String, Object> getComponentConfiguration(){
        return null;
    }

    /**
     *  bolt在关闭的时候被调用，释放资源
     */
    public void cleanup() {
    }
}
