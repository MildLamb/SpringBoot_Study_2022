package cn.engulf.service;

import cn.engulf.properties.IpCountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class IpCountService {

    private Map<String,Integer> ipCountMap = new HashMap<>();

    @Autowired
    // 当前的request对象的注入工作，由使用当前starter的项目提供自动装配
    private HttpServletRequest httpServletRequest;

    /**
     * 统计 ip 访问次数
     */
    public void count(){
        // 每次调用当前操作，就记录当前访问的IP，然后累加访问次数
        // 1. 获取当前操作的IP地址
        String ip = httpServletRequest.getRemoteAddr();
        // 2. 根据IP地址从Map中取值，并递增,再重新放回集合中
        Integer ipCount = ipCountMap.get(ip);
        if (ipCount == null){
            ipCountMap.put(ip,1);
        }else{
            ipCountMap.put(ip,(ipCount + 1));
        }
    }


    /**
     * 展示统计数据
     */
    @Autowired
    @Qualifier("ipProperties")
    private IpCountProperties ipCountProperties;

//    @Scheduled(cron = "0/${tools.ip.cycle:5} * * * * ?")

//    @Scheduled(cron = "0/#{beanId.attr} * * * * ?")
    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?")
    public void showIpCounts(){
        System.out.println("<----------         IP 访问监控       --------------->");
        if (ipCountProperties.getModel().equals(IpCountProperties.LogModel.DETAIL.getValue())){

            System.out.println("+-------- ip_address ---------+----- ip_counts -----+");
            if (ipCountMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : ipCountMap.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    System.out.println(String.format("|%26s   |         %-12d|", key, value));
                }
            }else {
                System.out.println("|            Nobody           |          0         |");
            }
            System.out.println("+-----------------------------+---------------------+");
        } else if(ipCountProperties.getModel().equals(IpCountProperties.LogModel.SIMPLE.getValue())){
            System.out.println("+-------- ip_address ---------+");
            for (String key : ipCountMap.keySet()) {
                System.out.println(String.format("|%26s   |",key));
            }
            System.out.println("+-----------------------------+");
        }



        // 如果设置重置
        if (ipCountProperties.getCountFlush()){
            ipCountMap.clear();
        }
    }

    public static void main(String[] args) {
        new IpCountService().showIpCounts();
    }
}
