package top.panda421.spider.unit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import top.panda421.spider.Spider;
import top.panda421.spider.entity.ProxyIP;
import top.panda421.spider.net.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panda421
 * @date 2021-08-17 23:53
 */
@Slf4j
public class Ip89Spider implements Spider {

    private static final String PROXY_TAG = "89ip";
    private static final String URL = "https://www.89ip.cn/index_%s.html";
    private static final int MAX_PAGES = 30;
    private String localIp;

    @Override
    public List<ProxyIP> downloadProxyIps() {
        List<ProxyIP> proxyIps = new ArrayList<>();
        for (int i = 1; i <= MAX_PAGES; i++) {
            proxyIps.addAll(getProxyIpFromPage(Connector.get(String.format(URL, i), this.localIp)));
        }
        log.info("downloadProxyIps {} Size {}", PROXY_TAG, proxyIps.size());
        return proxyIps;
    }

    private List<ProxyIP> getProxyIpFromPage(String body) {
        List<ProxyIP> proxyIps = new ArrayList<>();
        if (null == body) {
            return proxyIps;
        }
        Document document = Jsoup.parse(body);
        Elements ipElements = document.select("div.layui-form > table > tbody > tr");
        ProxyIP proxyIp = new ProxyIP();
        for (Element rowElement : ipElements) {
            proxyIp = new ProxyIP();
            Elements fieldElements = rowElement.select("td");
            for (int i = 0; i < fieldElements.size(); i++) {
                if (i == 0) {
                    proxyIp.setIpAddress(fieldElements.get(i).text().trim());
                } else if (i == 1) {
                    proxyIp.setIpPort(Integer.valueOf(fieldElements.get(i).text().trim()));
                } else if (i == 2) {
                    proxyIp.setLocation(fieldElements.get(i).text().trim());
                }
                proxyIp.setAnonymity(true);
                proxyIp.setHttp(true);
                proxyIp.setHttps(true);
                proxyIp.setAvailable(true);
            }

            if (StringUtils.isNotEmpty(proxyIp.getIpAddress()) && proxyIp.getIpPort() != null) {
                proxyIp.setProxyId(String.format("%s:%s", proxyIp.getIpAddress(), proxyIp.getIpPort()));
                proxyIp.setDataSources(PROXY_TAG);
                proxyIps.add(proxyIp);
            }
        }
        return proxyIps;
    }
}
