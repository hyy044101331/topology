package storm.starter.topology_wordCount;

import backtype.storm.task.ShellBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import java.util.Map;

/**
 *  将收到的句子拆分成单词发送出去
 *  <hr>
 *  Bolt可以使用任意语言来定义;
 *  使用其他语言写的Bolt将作为子进程执行，Storm通过标准输入输出使用JSON格式的消息和它们进行通讯;
 *  storm已经提供了ruby,python和fancy三种语言的库;
 *
 * Created by mengka
 */
public class SplitSentence extends ShellBolt implements IRichBolt {

    /**
     *  用python执行splitsentence.py
     */
    public SplitSentence() {
        super("python", "splitsentence.py");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
