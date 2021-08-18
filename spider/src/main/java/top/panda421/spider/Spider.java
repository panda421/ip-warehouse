package top.panda421.spider;


import top.panda421.spider.entity.ProxyIP;

import java.util.List;

/**
 * @author panda421
 * @date 2021-08-17 23:15
 */
public interface Spider {

    public abstract List<ProxyIP> downloadProxyIps();
}
