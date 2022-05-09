package admin.netty.decoder;

import admin.netty.dto.RequestData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.Charset;
import java.util.List;

public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in,
                          List<Object> out) throws Exception {

        RequestData reqData = new RequestData();
        reqData.setIntValue(in.readInt());
        int strLen = in.readInt();
        reqData.setStringValue(in.readCharSequence(strLen, charset).toString());
        out.add(reqData);
    }
}
