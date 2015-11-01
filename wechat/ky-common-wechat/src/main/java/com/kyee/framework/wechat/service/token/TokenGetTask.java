package com.kyee.framework.wechat.service.token;

import com.kyee.framework.core.exception.KyeeRuntimeException;
import com.kyee.framework.core.web.Result;
import com.kyee.framework.wechat.annotation.condition.TokenCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author 程峰
 * 创建时间:15/8/17 下午4:48
 * 任务号：MOBILEDEVELOP-10067
 * 创建说明：从中间件获取token的定时器
 */
@Service
@Conditional(TokenCondition.class)
public class TokenGetTask {

    @Autowired
    private GatewayToken gatewayToken;

    @Value("${gateway.user.username}")
    private String username;
    @Value("${gateway.user.password}")
    private String password;
    @Value("${gateway.token.url}")
    private String url;

    private static final int MAX_RETRY_TIMES = 5;
    private static int retryTimes = 0;
    private static Log LOG = LogFactory.getLog(TokenGetTask.class);

    private RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRateString= "${gateway.token.refreshInterval:6600000}")
    public void run() {
        if(retryTimes >= MAX_RETRY_TIMES){
            LOG.error("无法连接到中间件获取token!");
        }
        String tokenUrl = String.format(url,username,password);
        Result result = restTemplate.getForObject(tokenUrl,Result.class);
        if(result.isSuccess()) {
            gatewayToken.setToken((String)result.getValue());
            retryTimes=0;
        }else{
            try {
                retryTimes++;
                LOG.error("第"+retryTimes+"次尝试获取中间件token失败,等待1000ms继续尝试.....");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new KyeeRuntimeException("获取微信token失败时尝试等待1000ms再试，等待时线程被中断",e);
            }
            run();
        }
    }

}
