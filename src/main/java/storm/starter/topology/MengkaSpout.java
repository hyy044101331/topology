package storm.starter.topology;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mengka
 */
public class MengkaSpout extends BaseRichSpout {

    public static Logger LOG = LoggerFactory.getLogger(MengkaSpout.class);

    boolean _isDistributed;

    SpoutOutputCollector _collector;

    public MengkaSpout() {
        this(true);
    }

    public MengkaSpout(boolean isDistributed) {
        this._isDistributed = isDistributed;
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this._collector = spoutOutputCollector;
    }

    /**
     * nextTuple()����:
     * storm��ܻ᲻ͣ�ص��ô˺������û�ֻҪ����������Դ���ݼ���;
     * ����;
     *
     * ÿ100ms���������һ��������ΪԪ��;
     */
    @Override
    public void nextTuple() {
        Utils.sleep(100L);
        String[] words = new String[]{"-mengka AAA-", "-mengka BBB-", "-mengka CCC-", "-mengka DDD-", "-mengka EEE-"};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];

        /**
         * spout�����µ���Ϣ��topology
         */
        this._collector.emit(new Values(new Object[]{word}));
    }

    /**
     *  spout�ᷢ��һ���ֶ���Ϊ"mengka-word"��Ԫ��tuple
     *
     * @param outputFieldsDeclarer
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(new String[]{"mengka-word"}));
    }

    public Map<String, Object> getComponentConfiguration() {
        if(!this._isDistributed) {
            HashMap ret = new HashMap();
            ret.put("topology.max.task.parallelism", Integer.valueOf(1));
            return ret;
        } else {
            return null;
        }
    }

    public void ack(Object msgId) {
    }

    public void fail(Object msgId) {
    }

    public void close() {
    }
}
