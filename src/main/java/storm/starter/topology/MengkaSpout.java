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
     * nextTuple()函数:
     * storm框架会不停地调用此函数，用户只要在其中生成源数据即可;
     * 主动;
     *
     * 每100ms发送随机的一个单词作为元组;
     */
    @Override
    public void nextTuple() {
        Utils.sleep(100L);
        String[] words = new String[]{"-mengka AAA-", "-mengka BBB-", "-mengka CCC-", "-mengka DDD-", "-mengka EEE-"};
        Random rand = new Random();
        String word = words[rand.nextInt(words.length)];

        /**
         * spout发送新的消息到topology
         */
        this._collector.emit(new Values(new Object[]{word}));
    }

    /**
     *  spout会发送一个字段名为"mengka-word"的元组tuple
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
