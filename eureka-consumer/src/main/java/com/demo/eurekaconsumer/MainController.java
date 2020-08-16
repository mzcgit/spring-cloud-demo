package com.demo.eurekaconsumer;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author mzc
 */
@RestController
public class MainController {

    @Autowired
    DiscoveryClient discoveryClient; //SpringCloud官方封装的

    @Autowired
    EurekaClient eurekaClient; //Netflix 封装的实现

    @Autowired
    LoadBalancerClient loadBalancerClient;


    @GetMapping("/getProviderServer")
    public String getProviderServer(){
//        List<String> services = discoveryClient.getServices();
//        for (String service : services) {
//            System.out.println(service);
//        }
//        List<ServiceInstance> provider1 = discoveryClient.getInstances("provider");
//        for (ServiceInstance serviceInstance : provider1) {
//            System.out.println(serviceInstance);
//
//        }
//        List<InstanceInfo> provider = eurekaClient.getInstancesById("192.168.2.102:provider:81");
        List<InstanceInfo> provider = eurekaClient.getInstancesByVipAddress("provider", false);
        for (InstanceInfo instanceInfo : provider) {
            System.out.println(instanceInfo.toString());
        }
        String url = "";
        String forObject = "";
        if (provider.size()>0) {
            InstanceInfo instanceInfo = provider.get(0);
            url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort()+"/getHi";
            System.out.println(url);
            RestTemplate restTemplate = new RestTemplate();
            forObject = restTemplate.getForObject(url, String.class);

        }
        return forObject;

    }

    @GetMapping("/getProviderServer2")
    public String getProviderServer2(){
        //ribbon 负载均衡过滤掉down的节点
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort()+"/getHi";
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    @GetMapping("/getProviderServer3")
    public String getProviderServer3(){
        //ribbon 负载均衡过滤掉down的节点
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider");
        String url = "http://localhost:81/getHi";
        System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }
}
