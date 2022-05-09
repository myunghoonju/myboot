package admin.netty.handler;

import admin.netty.dto.RequestData;
import admin.netty.dto.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf tmp;

/*
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        log.error("handlerAdded");
        tmp = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        log.error("handlerRemoved");
        tmp.release();
        tmp = null;
    }
*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
/*
        ByteBuf src = (ByteBuf) msg;
        tmp.writeBytes(src);
        src.release();
        if (tmp.readableBytes() >= 4) {
            RequestData reqData = new RequestData();
            reqData.setIntValue(tmp.readInt());
            ResponseData resData = new ResponseData();
            resData.setIntValue(reqData.getIntValue() * 2);
            ChannelFuture future = ctx.writeAndFlush(resData);
            future.addListener(ChannelFutureListener.CLOSE);
        }
*/
        RequestData requestData = (RequestData) msg;
        ResponseData responseData = new ResponseData();
        responseData.setIntValue(requestData.getIntValue() * 2);
        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        log.error(requestData.toString());
    }
}
