package com.hcoj.hcoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.domain.TopicContent;
import com.hcoj.hcoj.mapper.TopicContentMapper;
import com.hcoj.hcoj.mapper.TopicMapper;
import com.hcoj.hcoj.service.TopicService;
import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service("topicService")
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private TopicContentMapper topicContentMapper;

    @Override
    public IPage<Topic> SelectPageTopic(Page<Topic> page,Integer state) {
        return topicMapper.selectPageVo(page,state);
    }

    @Override
    public IPage<Topic> SelectPageAllTopic(Page<Topic> page) {
        return topicMapper.selectPageAll(page);
    }


    @Override
    public TopicContent getTopicContentById(String tpc_id) {
        return topicContentMapper.selectById(tpc_id);
    }

    @Override
    public String getTopicIdRandom() {
        String lastId=topicMapper.selectLastId();
        int b = Integer.parseInt(lastId.substring(2,6));
        Random random=new Random();
        b=random.nextInt(b);
        if(b==0) b=1;//random有可能随机到0
        char[] id={'H','C','0','0','0','0'};
        int i=5;
        while(b>0){
            int x=b%10;
            id[i]= (char) (x+'0');
            b=b/10;
            i--;
        }
        return String.valueOf(id);
    }

    @Override
    public Topic getTopicById(String tpc_id) {
        return topicMapper.selectById(tpc_id);
    }

    @Override
    public List<Topic> getTopicByCts(int cts_id) {
        QueryWrapper<Topic> wrapper = Wrappers.query();
        wrapper.eq("cts_id",cts_id);
        return topicMapper.selectList(wrapper);
    }

    @Override
    public void delTopicCts(List<Topic> lt) {
        List<Topic> topicList=lt;
        for(Topic topic:topicList){
            topic.setCtsId(null);
            topicMapper.updateById(topic);
        }
    }

    @Override
    public List<Topic> SelectTopicNoOpen() {
        List<Topic> lt=topicMapper.selectAllNoOpen();
        if(lt.isEmpty())
            return null;
        else
            return lt;
    }

    @Override
    public void addTopicSub(String tpcId) {
        Topic topic=topicMapper.selectById(tpcId);
        topic.setTpcSub(topic.getTpcSub()+1);
        topicMapper.updateById(topic);
    }

    @Override
    public void addTopicAc(String tpcId) {
        Topic topic=topicMapper.selectById(tpcId);
        topic.setTpcAc(topic.getTpcAc()+1);
        topicMapper.updateById(topic);
    }

    @Override
    public boolean conTopicCts(String[] tpcIds,Integer ctsId) {
        try {
            for(String tpcId:tpcIds){
                Topic topic=topicMapper.selectById(tpcId);
                topic.setCtsId(ctsId);
                topicMapper.updateById(topic);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addTopic(Topic topic,TopicContent topicContent) {
        try{
            topic.setTpcId(getLastId());
            topicContent.setTpcId(getLastId());
            topic.setTpcAc(0);
            topic.setTpcSub(0);
            topicMapper.insert(topic);
            topicContentMapper.insert(topicContent);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delTopic(String tpcId) {
        try {
            topicMapper.deleteById(tpcId);
            topicContentMapper.deleteById(tpcId);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editTopic(Topic topic, TopicContent topicContent) {
        try {
            topicMapper.updateById(topic);
            topicContentMapper.updateById(topicContent);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //获取最后一项id值并+1
    public String getLastId(){
        String lastId=topicMapper.selectLastId();
        int b = Integer.parseInt(lastId.substring(2,6))+1;
        char[] id={'H','C','0','0','0','0'};
        int i=5;
        while(b>0){
            int x=b%10;
            id[i]= (char) (x+'0');
            b=b/10;
            i--;
        }
        return String.valueOf(id);
    }

}
