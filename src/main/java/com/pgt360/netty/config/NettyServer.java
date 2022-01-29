package com.pgt360.netty.config;
import java.io.IOException;
import java.util.Properties;


import org.apache.log4j.Logger;

import com.pgt360.util.FileProperties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	private static Logger logger;
	private NioEventLoopGroup bossLoopGroup;
	private NioEventLoopGroup workerLoopGroup;
	private ServerBootstrap bootstrap;
	private Properties properties;
	private FileProperties fileProperties;
	private NettyInitializer nettyInitializer;
	private int port;
	
	public NettyServer() {
		this.bossLoopGroup = new NioEventLoopGroup();
		this.workerLoopGroup = new NioEventLoopGroup();
		this.bootstrap = new ServerBootstrap();
		this.fileProperties = new FileProperties();
		logger = fileProperties.getLogger(NettyServer.class);
	}
	public static void main(String[] args) {
		NettyServer nettyServer = new NettyServer();
		nettyServer.run();
	}
	
	public void run() {
		try {
			this.properties = this.fileProperties.getConfiguration();
			this.port = Integer.parseInt(this.properties.getProperty("port"));
			this.bootstrap.group(bossLoopGroup, workerLoopGroup);
			this.bootstrap.channel(NioServerSocketChannel.class);
			this.bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
			//this.bootstrap.option(ChannelOption.TCP_NODELAY, true);
			this.bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
			this.nettyInitializer = new NettyInitializer();
			this.bootstrap.childHandler(this.nettyInitializer);
			ChannelFuture future = this.bootstrap.bind(this.port).sync();
			if(future.isSuccess()) {
				logger.info("**********************************************************");
				logger.info("**********Servidor iniciado en el puerto: "+this.port+"************");
				logger.info("**********************************************************");
			} else {
				System.exit(-1);
			}
			future.channel().closeFuture().sync();
		} catch (IOException e) {
			logger.error("Error en lectura de archivo de propiedades");
		} catch (InterruptedException ie) {
			logger.error("Se interrumpio la ejecución del servidor");
		} catch(NumberFormatException nf) {
			logger.error("Error formato de numero");
			System.out.println("Error en el formato de numero: "+nf);
		} finally {
			bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
		}
		
	}
	
}
