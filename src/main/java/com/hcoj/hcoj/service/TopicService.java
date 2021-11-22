package com.hcoj.hcoj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hcoj.hcoj.domain.Topic;
import com.hcoj.hcoj.domain.TopicContent;
import net.sf.jsqlparser.statement.select.Top;

import java.util.List;

public interface TopicService {
    //分页获取公开题目列表信息
    public IPage<Topic> SelectPageTopic(Page<Topic> page,Integer state);
    //分页获取所有列表信息
    public IPage<Topic> SelectPageAllTopic(Page<Topic> page);
    //根据id获取题目具体信息
    public TopicContent getTopicContentById(String tpc_id);
    //随机获取题目id
    public String getTopicIdRandom();
    //根据id获取题目信息
    public Topic getTopicById(String tpc_id);
    //根据比赛id获取题目信息
    public List<Topic> getTopicByCts(int cts_id);
    //删除比赛时，清除绑定的题目信息
    public void delTopicCts(List<Topic> lt);
    //获取未公开且未绑定比赛的题目信息
    public List<Topic> SelectTopicNoOpen();
    //添加题目提交数
    public void addTopicSub(String tpcId);
    //添加题目Ac数
    public void addTopicAc(String tpcId);
    //为题目绑定比赛
    public boolean conTopicCts(String[] tpcIds,Integer ctsId);
    //添加题目
    public boolean addTopic(Topic topic,TopicContent topicContent);
    //删除题目
    public boolean delTopic(String tpcId);
    //编辑题目
    public boolean editTopic(Topic topic,TopicContent topicContent);
}
