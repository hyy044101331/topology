package storm.starter.topology_02.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import storm.starter.topology_02.Constant;
import storm.starter.util.StringUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 建立socket连接，接受数据
 * <p/>
 * Created by mengka
 */
public class MengkaSocketSpout extends BaseRichSpout {


    static Socket socket = null;

    static BufferedReader inReader = null;

    private SpoutOutputCollector _collector;

    private String content;

    public MengkaSocketSpout() {
    }


    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this._collector = spoutOutputCollector;
        connect();
    }

    @Override
    public void nextTuple() {
        if (socket == null) {
            connect();
        }
        while (true) {
            try {
                content = inReader.readLine();
                if (StringUtils.isNotBlank(content) && content.contains("mengkaManager")) {
                    System.out.println("--------------, content = " + content);
                    String data = getData(content);
                    _collector.emit(new Values(data));
                }
                if ("end".equals(content)) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getData(String content){
        Pattern pattern = Pattern.compile("#([A-Za-z]*-[0-9]*)#?");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String data = matcher.group(1);
            return data;
        }
        return null;
    }

    /**
     * 建立socket连接
     */
    public void connect() {
        try {
            socket = new Socket(Constant.DATA_IP, Constant.DATA_PORT);
            inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("mengka-socket-line"));
    }
}
