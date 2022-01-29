package com.pgt360.util;

import com.pgt360.dto.FlujoQueryDto;

public class FlowProcess {
	String ack = "06";
    Double montoBOB = 0.00;
    String reciboTRA = "";
    String respHost = "";
    String respHostJson = "";
    boolean isAck1;
    boolean isAck2;
    boolean isAck3;
    boolean isAck4;
    boolean isAck5;
    CommunicationPos communicationPos;
    ProcessPos processPos;
    
    public FlowProcess(){
        this.isAck1 = false;
        this.isAck2 = false;
        this.isAck3 = false;
        this.isAck4 = false;
        this.isAck5 = false;
        this.communicationPos = new CommunicationPos();
        this.processPos = new ProcessPos();
    }
    
    public void flujoInicializar(String pStrReply, FlujoQueryDto pFlujoQueryDto){
        String resp = "";
        switch(pFlujoQueryDto.getStep()){
            case 1:
                if(this.isAck1 && pFlujoQueryDto.getSize() == 40){
                    resp = this.communicationPos.sendAck();
                    resp = this.communicationPos.sendTransRevNo();
                    pFlujoQueryDto.setStep(2);
                    pFlujoQueryDto.setSize(0);
                    break;
                } else if(this.isAck(pStrReply)){
                    this.isAck1 = true;
                    pFlujoQueryDto.setSize(0);
                    break;
                } else{
                    this.respHost = pStrReply;
                    break;
                }
            default:
                break;
        }     
    }
    
    public void flujoChip(String pStrReply, FlujoQueryDto pFlowQueryDto){
        String resp = "";
        switch(pFlowQueryDto.getStep()){
            case 1:
                if(isAck1 && pFlowQueryDto.getSize() == 40){
                    resp = communicationPos.sendAck();
                    
                }
        }
        
    }
    public boolean isAck(String pStr){
        if(pStr.equals(this.ack)){
            return true;
        } else{
            return false;
        }
    }
}
