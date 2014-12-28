/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package storm.starter.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;


public class PrinterBolt extends BaseBasicBolt {

  /**
   *  execute()函数:<br>
   *     在接受到消息后会调用此函数，用户可以在其中执行自己想要的操作;<br>
   *     被动；<br>
   *<br>
   * 》》tuple：<br>
   *      一个tuple是一个基本数据单元；<br>
   *
   *
   * @param tuple
   * @param collector
   */
  @Override
  public void execute(Tuple tuple, BasicOutputCollector collector) {
    System.out.println(tuple);
  }

  @Override
  public void declareOutputFields(OutputFieldsDeclarer ofd) {
  }

}
