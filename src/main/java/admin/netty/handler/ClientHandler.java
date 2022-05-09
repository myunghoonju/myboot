package admin.netty.handler;

import admin.netty.dto.RequestData;
import admin.netty.dto.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData reqData = new RequestData();
        reqData.setIntValue(123);
        reqData.setStringValue("i don't know what i'm writing of");
        ChannelFuture future = ctx.writeAndFlush(reqData);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ResponseData res = (ResponseData) msg;
        log.error("-> {}", res.getIntValue());
        ctx.close();
    }
}
