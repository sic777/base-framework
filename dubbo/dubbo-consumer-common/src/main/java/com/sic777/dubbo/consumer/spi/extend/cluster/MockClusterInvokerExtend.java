package com.sic777.dubbo.consumer.spi.extend.cluster;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.cluster.Directory;
import com.alibaba.dubbo.rpc.cluster.support.wrapper.MockClusterInvoker;
import com.sic777.dubbo.exception.RpcExceptionType;
import com.sic777.dubbo.bean.RpcResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>集群容错扩展</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-06-01
 */
public class MockClusterInvokerExtend<T> extends MockClusterInvoker<T> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public MockClusterInvokerExtend(Directory<T> directory, Invoker<T> invoker) {
        super(directory, invoker);
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        try {
            return super.invoke(invocation);
        } catch (RpcException e) {
            RpcResponse<?> resp = new RpcResponse<>(RpcExceptionType.CLIENT_EXCEPTION, e.getMessage());
            logger.error("mock error:", resp.getException());
            return new RpcResult(resp);
        }
    }
}