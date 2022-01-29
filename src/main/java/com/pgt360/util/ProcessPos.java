package com.pgt360.util;

import com.pgt360.dto.ResponseDto;
import com.pgt360.dto.VentaQueryDto;

public class ProcessPos {
	public VentaQueryDto respuestaHostVenta(String pRespHost){
        ResponseDto vResponseDto = new ResponseDto();
        vResponseDto.setCodAutoriz(pRespHost.substring(50,12));
        vResponseDto.setMontoCompra(pRespHost.substring(72, 24));
        vResponseDto.setNumRecibo(pRespHost.substring(106, 12));
        vResponseDto.setRrn(pRespHost.substring(128, 24));
        vResponseDto.setTerminalId(pRespHost.substring(162,16));
        vResponseDto.setFechaTransac(pRespHost.substring(188, 8));
        vResponseDto.setHoraTransac(pRespHost.substring(206,8));
        vResponseDto.setCodRespuesta(pRespHost.substring(224, 4));
        vResponseDto.setTipoCuenta(pRespHost.substring(238,4));
        vResponseDto.setNumCuotas(pRespHost.substring(252, 4));
        vResponseDto.setUltFourDigits(pRespHost.substring(266, 8));
        vResponseDto.setMsgError(pRespHost.substring(284, 138));
        vResponseDto.setBinTarjeta(pRespHost.substring(432,12));
        
        VentaQueryDto vVentaQueryDto = new VentaQueryDto();
        vVentaQueryDto.setCodAutoriz(Util.hex2a(vResponseDto.getCodAutoriz()));
        vVentaQueryDto.setMontoCompra(Double.parseDouble(Util.hex2a(vResponseDto.getMontoCompra())));
        vVentaQueryDto.setNumRecibo(Util.hex2a(vResponseDto.getNumRecibo()));
        vVentaQueryDto.setRrn(Util.hex2a(vResponseDto.getRrn()));
        vVentaQueryDto.setTerminalId(Integer.parseInt(Util.hex2a(vResponseDto.getTerminalId())));
        vVentaQueryDto.setFechaTransac(Util.hex2a(vResponseDto.getFechaTransac()));
        vVentaQueryDto.setHoraTransac(Util.hex2a(vResponseDto.getHoraTransac()));
        vVentaQueryDto.setCodRespuesta(Integer.parseInt(Util.hex2a(vResponseDto.getCodRespuesta())));
        vVentaQueryDto.setTipoCuenta(Util.hex2a(vResponseDto.getTipoCuenta()));
        vVentaQueryDto.setNumCuotas(Integer.parseInt(Util.hex2a(vResponseDto.getNumCuotas())));
        vVentaQueryDto.setUltFourDigits(Integer.parseInt(Util.hex2a(vResponseDto.getUltFourDigits())));
        vVentaQueryDto.setMsgError(Util.hex2a(vResponseDto.getMsgError()));
        vVentaQueryDto.setBinTarjeta(Integer.parseInt(Util.hex2a(vResponseDto.getBinTarjeta())));
        return vVentaQueryDto;
    }
	
    public VentaQueryDto respuestaHosInicializacion(String pRespHost){
        ResponseDto vResponseDto = new ResponseDto();
        vResponseDto.setCodRespuesta(pRespHost.substring(50,4));
        
        VentaQueryDto vVentaQueryDto = new VentaQueryDto();
        vVentaQueryDto.setCodRespuesta(Integer.parseInt(Util.hex2a(vResponseDto.getCodRespuesta())));
        return vVentaQueryDto;
    }
}
