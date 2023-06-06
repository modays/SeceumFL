package com.webank.ai.fate.serving.sdk.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.webank.ai.fate.serving.core.bean.BatchInferenceRequest;
import com.webank.ai.fate.serving.core.bean.BatchInferenceResult;
import com.webank.ai.fate.serving.core.bean.InferenceRequest;
import com.webank.ai.fate.serving.core.bean.ReturnResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 该类主要演示不带注册中心的客户端如何使用
 */
public class SimpleClientExample {


    static SimpleClient client  =ClientBuilder.getClientWithoutRegister();


    /**
     * 构建单笔预测请求
     * @return
     */
    static InferenceRequest buildInferenceRequest(){
            InferenceRequest  inferenceRequest = new  InferenceRequest();
            inferenceRequest.setServiceId("lr-test");
            Map<String,Object> featureData = Maps.newHashMap();
                featureData.put("x0", 0.100016);
                featureData.put("x1", 1.210);
                featureData.put("x2", 2.321);
                featureData.put("x3", 3.432);
                featureData.put("x4", 4.543);
                featureData.put("x5", 5.654);
                featureData.put("x6", 5.654);
                featureData.put("x7", 0.102345);
            inferenceRequest.setFeatureData(featureData);
            Map<String,Object>  sendToRemote = Maps.newHashMap();
            sendToRemote.put("device_id","helloworld");
        /**
         * sendToRemote 数据会发送到host ，需要谨慎检查是否是敏感数据
         */
        inferenceRequest.setSendToRemoteFeatureData(sendToRemote);
            return  inferenceRequest;
        }

    /**
     * 构建批量预测请求
     * @return
     */
    static BatchInferenceRequest buildBatchInferenceRequest(){
        BatchInferenceRequest  batchInferenceRequest = new  BatchInferenceRequest();
        batchInferenceRequest.setServiceId("lr-test");
        List<BatchInferenceRequest.SingleInferenceData>  singleInferenceDataList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            BatchInferenceRequest.SingleInferenceData singleInferenceData = new BatchInferenceRequest.SingleInferenceData();
            singleInferenceData.getFeatureData().put("x0", 0.100016);
            singleInferenceData.getFeatureData().put("x1", 1.210);
            singleInferenceData.getFeatureData().put("x2", 2.321);
            singleInferenceData.getFeatureData().put("x3", 3.432);
            singleInferenceData.getFeatureData().put("x4", 4.543);
            singleInferenceData.getFeatureData().put("x5", 5.654);
            singleInferenceData.getFeatureData().put("x6", 5.654);
            singleInferenceData.getFeatureData().put("x7", 0.102345);
            /**
             * sendToRemote 数据会发送到host ，需要谨慎检查是否是敏感数据
             */
            singleInferenceData.getSendToRemoteFeatureData().put("device_id","helloworld");
            /**
             * 这里的序号从0开始 ，序号很重要，不可以重复
             */
            singleInferenceData.setIndex(i);
            singleInferenceDataList.add(singleInferenceData);

        }
        batchInferenceRequest.setBatchDataList(singleInferenceDataList);
        batchInferenceRequest.setServiceId("lr-test");
        return  batchInferenceRequest;
    }

        public  static  void main(String[] args) throws IOException, InterruptedException {
            InferenceRequest inferenceRequest = buildInferenceRequest();
            BatchInferenceRequest batchInferenceRequest = buildBatchInferenceRequest();
            try {

                /**
                 * 使用ip端口的方式进行rpc调用
                 */
                ReturnResult returnResult2 = client.singleInference("localhost",8000,inferenceRequest);
                System.err.println(returnResult2);

                /**
                 * 指定ip端口批量预测
                 */
                BatchInferenceResult BatchInferenceResult2 = client.batchInference("localhost",8000,batchInferenceRequest);
                System.err.println(BatchInferenceResult2);


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.err.println("over");
        }


}
