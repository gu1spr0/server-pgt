package com.pgt360.netty.config;

import java.io.IOException;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NettyInitializer extends ChannelInitializer<SocketChannel>{
	private final NettyServerHandler nettyServerHandler;
	
	public NettyInitializer() throws IOException {
		this.nettyServerHandler = new NettyServerHandler();
	}
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("handler", nettyServerHandler);
    }
}
