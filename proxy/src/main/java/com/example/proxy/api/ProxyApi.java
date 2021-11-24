package com.example.proxy.api;

import com.example.proxy.common.CodeEnum;
import com.example.proxy.common.CommonResult;
import com.example.proxy.common.PageInfo;
import com.example.proxy.dao.ProxyDAO;
import com.example.proxy.domain.Proxy;
import com.example.proxy.domain.ProxyStats;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: ip-warehouse
 * @description:
 * @author: JayDragon
 * @create: 2021-11-24 10:50
 **/
public class ProxyApi {

    @Autowired
    private ProxyDAO proxyDAO;

    /**
    * @description: 统计当前代理池中的代理数和稳定代理数
    * @param: []
    * @return: com.example.proxy.common.CommonResult
    * @author: JayDragon
    * @date: 2021/11/24
    */
    public CommonResult stats() {
        long all = proxyDAO.count();
        long high = proxyDAO.countHigh();
        ProxyStats stats = new ProxyStats();
        stats.setAll(all);
        stats.setHigh(high);
        return CommonResult.ok().data(stats);
    }

    /**
    * @description: 随机获取一个代理，可能包含不稳定的代理
    * @param: []
    * @return: com.example.proxy.common.CommonResult
    * @author: JayDragon
    * @date: 2021/11/24
    */
    public CommonResult random() {

        String proxy = this.proxyDAO.random();
        if (StringUtils.isEmpty(proxy)) {
            return CommonResult.error(CodeEnum.NOT_FOUND);
        }
        return CommonResult.ok().data(proxy);
    }

    /**
    * @description: 随机获取一个稳定的代理
    * @param: []
    * @return: com.example.proxy.common.CommonResult
    * @author: JayDragon
    * @date: 2021/11/24
    */
    public CommonResult randomHigh() {

        String proxy = this.proxyDAO.randomGetHighAvailableProxy();
        if (StringUtils.isEmpty(proxy)) {
            return CommonResult.error(CodeEnum.NOT_FOUND);
        }
        return CommonResult.ok().data(proxy);
    }

    /**
    * @description: 分页获取当前代理池的所有代理
    * @param: [pageNum, pageSize]
    * @return: com.example.proxy.common.CommonResult
    * @author: JayDragon
    * @date: 2021/11/24
    */
    public CommonResult list(int pageNum,int pageSize) {
        PageInfo pageInfo = new PageInfo();
        int count = (int) this.proxyDAO.count();
        if (count > 0) {
            pageInfo.setPageNum(pageNum);
            pageInfo.setPageSize(pageSize);
            pageInfo.setTotal(count);

            List<Proxy> list = this.proxyDAO.batchQueryWithScore(pageInfo.getStart(), pageInfo.getEnd());
            pageInfo.setList(list);
        }
        return CommonResult.ok().data(pageInfo);
    }

    public HttpResponse myProxy(HttpRequest request){
        return null;
    }
}
