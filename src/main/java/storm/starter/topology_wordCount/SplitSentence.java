package storm.starter.topology_wordCount;

import backtype.storm.task.ShellBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import java.util.Map;

/**
 *  ���յ��ľ��Ӳ�ֳɵ��ʷ��ͳ�ȥ
 *  <hr>
 *  Bolt����ʹ����������������;
 *  ʹ����������д��Bolt����Ϊ�ӽ���ִ�У�Stormͨ����׼�������ʹ��JSON��ʽ����Ϣ�����ǽ���ͨѶ;
 *  storm�Ѿ��ṩ��ruby,python��fancy�������ԵĿ�;
 *
 * Created by mengka
 */
public class SplitSentence extends ShellBolt implements IRichBolt {

    /**
     *  ��pythonִ��splitsentence.py
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
