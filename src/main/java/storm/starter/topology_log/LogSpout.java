package storm.starter.topology_log;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

import java.io.*;
import java.util.Map;

/**
 * Created by mengka
 */
public class LogSpout extends BaseRichSpout {

    private static final String file = "";

    boolean _isDistributed;

    SpoutOutputCollector _collector;

    BufferedReader fileReader;

    Tuple tupleInfo;

    public LogSpout() {
        this(true);
    }

    public LogSpout(boolean isDistributed) {
        this._isDistributed = isDistributed;
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this._collector = spoutOutputCollector;

        try {
            fileReader = new BufferedReader(new FileReader(new File(file)));
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    @Override
    public void nextTuple() {
        String data = "";
        /**
         * spout发送新的消息到topology
         */
        this._collector.emit(new Values(new Object[]{data}));


    }

//    protected void ListenFile(File file) {
//        Utils.sleep(2000);
//        RandomAccessFile access = null;
//        String line = null;
//        try {
//            while ((line = access.readLine()) != null) {
//                if (line != null) {
//                    String[] fields = null;
//                    if (tupleInfo.getDelimiter().equals("|")) fields = line.split("\\" + tupleInfo.getDelimiter());
//                    else
//                        fields = line.split(tupleInfo.getDelimiter());
//                    if (tupleInfo.getFieldList().size() == fields.length) _collector.emit(new Values(fields));
//                }
//            }
//        } catch (IOException ex) {
//        }
//    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields(new String[]{"mengka-log"}));
    }
}
