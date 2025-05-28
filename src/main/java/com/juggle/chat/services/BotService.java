package com.juggle.chat.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.protobuf.ServiceException;
import com.juggle.chat.apimodels.AiBotInfo;
import com.juggle.chat.apimodels.AiBotInfos;
import com.juggle.chat.exceptions.JimException;
import com.juggle.chat.interceptors.RequestContext;
import com.juggle.chat.mappers.BotConfMapper;
import com.juggle.chat.models.BotConf;
import com.juggle.chat.utils.N3d;

@Service
public class BotService {
    @Resource
    private BotConfMapper botMapper;

    public AiBotInfos qryAiBots(long limit, String offset)throws JimException{
        String appkey = RequestContext.getAppkeyFromCtx();
        long startId = 0;
        if(offset!=null&&!offset.isEmpty()){
            try {
                startId = N3d.decode(offset);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        AiBotInfos ret = new AiBotInfos();
        List<BotConf> items = botMapper.qryBotConfsWithStatus(appkey, 1, startId, limit);
        if(items!=null&&items.size()>0){
            ret.setItems(new ArrayList<AiBotInfo>());
            for (BotConf item : items) {
                String newOffset = "";
                try {
                    newOffset = N3d.encode(item.getId());
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                ret.setOffset(newOffset);
                AiBotInfo botInfo = new AiBotInfo();
                botInfo.setBotId(item.getBotId());
                botInfo.setNickname(item.getNickname());
                botInfo.setAvatar(item.getBotPortrait());
                botInfo.setBotType(item.getBotType());
                ret.getItems().add(botInfo);
            }
        }
        return ret;
    }
}
