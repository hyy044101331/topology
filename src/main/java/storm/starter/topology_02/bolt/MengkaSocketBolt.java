package storm.starter.topology_02.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import java.io.*;
import java.util.Map;

/**
 * Created by mengka
 */
public class MengkaSocketBolt extends BaseRichBolt {


    private OutputCollector _collector;

    private String outPath;

    private BufferedWriter writer;

    public MengkaSocketBolt(String outPath) {
        this.outPath = outPath;
    }

    /**
     * 为execute()准备
     *
     * @param map
     * @param topologyContext
     * @param outputCollector
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        _collector = outputCollector;
        try {
            File outFile = new File(outPath);
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outFile, true)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Tuple tuple) {
        //socket传送过来的数据
        String data = tuple.getString(0);
        writeDate(data);
        _collector.emit(new Values(data));
    }

    /**
     * 写入到文件中
     *
     * @param data
     */
    private void writeDate(String data) {
        try {
            writer.write(data + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-socket-line"));
    }
}
