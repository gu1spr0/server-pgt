package com.pgt360.netty.config;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.pgt360.dto.ConexionAddDto;
import com.pgt360.service.ConexionService;
import com.pgt360.service.ConexionServiceLocal;
import com.pgt360.service.FlujoService;
import com.pgt360.service.FlujoServiceLocal;
import com.pgt360.util.Constants;
import com.pgt360.util.FileProperties;
import com.pgt360.util.FlowProcess;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter{
	private static Logger logger;
	public static  ChannelHandlerContext ctx;
    private Properties properties;
    private FileProperties fileProperties;
    private FlujoServiceLocal flujoServiceLocal;
    private ConexionServiceLocal conexionServiceLocal;
    int paso = 1;
    int tam = 0;
    String ack = "06";
    Double montoBOB = 0.00;
    String reciboTRA = "";
    String respHost = "";
    String respHostJson = "";
    boolean pagoChip = false;
    boolean pagoChipMulti = false;
    boolean pagoCtl = false;
    boolean pagoCtlMulti = false;
    boolean anulacionTrans = false;
    boolean anulacionTransMulti = false;
    boolean cierrePos = false;
    boolean cierrePosMulti = false;
    boolean inicializarPos = false;

    boolean isAck1 = false;
    boolean isAck2 = false;
    boolean isAck3 = false;
    boolean isAck4 = false;
    boolean isAck5 = false;
    
    public NettyServerHandler() throws IOException, IOException{
        this.fileProperties = new FileProperties();
        this.properties = this.fileProperties.getConfiguration();
        logger = this.fileProperties.getLogger(NettyServerHandler.class);
        this.conexionServiceLocal = new ConexionService();
    }
    
    @Override
    public void handlerAdded(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        NettyServerHandler.ctx = ctx;
        logger.info("[SERVER]-"+incoming.remoteAddress()+" SE CONECT� DISPOSITIVO CON EL ID:"+incoming.id());
        
    }
    
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx){
        Channel incoming = ctx.channel();
        NettyServerHandler.ctx = null;
        logger.info("[SERVER] - "+incoming.remoteAddress() + " SE DESCONECT� DISPOSITIVO CON EL ID:"+incoming.id()+"\n");
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        byte[] data = (byte[]) msg;
        Long idFlujo = 0L;
        if(data.length == 8){
            idFlujo = new BigInteger(data).longValue();
            logger.info("Id Flujo enviado por el canal :"+ctx.channel().id()+" es: "+idFlujo);
        }
        
        //TODO: REALIZAR LA CONSULTA CON BASE DE DATOS
        
        switch(1){
            case Constants.NUMBER_FLOW_INIT:
                break;
            case Constants.NUMBER_FLOW_CHIP:
                break;
            case Constants.NUMBER_FLOW_CHIP_MULTI:
                break;
            case Constants.NUMBER_FLOW_CTL:
                break;
            case Constants.NUMBER_FLOW_CTL_MULTI:
                break;
            case Constants.NUMBER_FLOW_DELETED:
                break;
            case Constants.NUMBER_FLOW_DELETED_MULTI:
                break;
            case Constants.NUMBER_FLOW_CLOSE:
                break;
            case Constants.NUMBER_FLOW_CLOSE_MULTI:
                FlowProcess vFlowProcess = new FlowProcess();
                //vFlowProcess.flujoInicializar(Util.bytesToHex(data));
                break;
            default:
                break;
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Canal con id:"+ctx.channel().id()+" activo");
    	ConexionAddDto vConexionAddDto = new ConexionAddDto();
    	vConexionAddDto.setIdCanal(ctx.channel().id().toString());
    	vConexionAddDto.setState(Constants.STATE_ACTIVE);
    	vConexionAddDto.setIdDispositivo(2L);
    	Date vDate = new Date();
    	vConexionAddDto.setDateConnection(vDate);
    	vConexionAddDto.setHourConnection(vDate);
    	this.conexionServiceLocal.agregarConexion(vConexionAddDto);
    	logger.info("Se agreg� canal a la base de datos");
        
        
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Canal con id:"+ctx.channel().id()+" inactivo");
        //TODO: Dar de baja en la base de datos.
    	logger.info("Se di� de baja al dispositivo en la base de datos");
    }
    
    public static void sendMessage(String msg){
        if (NettyServerHandler.ctx == null)
            return;
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeCharSequence(msg,Charset.defaultCharset());
        ctx.write(buf);
        ctx.flush();
    }
}
