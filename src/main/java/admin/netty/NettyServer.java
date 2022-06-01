package admin.netty;

import admin.netty.decoder.RequestDecoder;
import admin.netty.encoder.ResponseEncoder;
import admin.netty.handler.ChannelHandlerCloseContext;
import admin.netty.handler.ChannelHandlerFireExceptionCaught;
import admin.netty.handler.ProcessingHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        new NettyServer(port).run();
    }

    private void run() throws Exception {
        NioEventLoopGroup bossG = new NioEventLoopGroup();
        NioEventLoopGroup workerG = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossG, workerG)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                           // ch.pipeline().addLast(new RequestDecoder(), new ResponseEncoder(), new ProcessingHandler());
                            ch.pipeline().addLast(new ChannelHandlerFireExceptionCaught(), new ChannelHandlerCloseContext());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerG.shutdownGracefully();
            bossG.shutdownGracefully();
        }

    }
}
