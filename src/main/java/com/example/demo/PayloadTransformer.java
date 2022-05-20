package com.example.demo;

import java.util.Map;
import com.rollbar.api.payload.data.Data;
import com.rollbar.api.payload.data.body.Body;
import com.rollbar.api.payload.data.body.Message;
import com.rollbar.notifier.transformer.Transformer;

import com.rollbar.api.payload.data.Request;

import java.util.HashMap;

public class PayloadTransformer implements Transformer {


    @Override
    public Data transform(Data data) {

      Data newData = data;

      // Modify request headers if there is a request
      Request req = newData.getRequest();
      if(req != null) {
      
        Map<String, String> headers = req.getHeaders();
        headers.put("authorization", "*****");
        headers.put("host", "*****");
  
  
        Request scrubbedRequest =  new Request.Builder(req)
                                              .headers(headers)
                                              .build();
  
        newData = new Data.Builder(newData)
                          .request(scrubbedRequest)
                          .build();
      }

      // create a new Hashmap for custom data
      // if no custom data is in the payload
      Map<String,Object> map = newData.getCustom();
      if(map == null) {
        map = new HashMap<String, Object>();
      }
      
      // add a custom field in the transform method
      map.put("customer_id", "cust_6789");
      map.put("request_id", "req_1234567890");

      newData = new Data.Builder(newData)
                        .custom(map)
                        .build();
      
      return newData;
    }
  }