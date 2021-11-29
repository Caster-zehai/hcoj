package com.hcoj.hcoj.service.impl;

import com.hcoj.hcoj.domain.Total;
import com.hcoj.hcoj.mapper.TotalMapper;
import com.hcoj.hcoj.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("totalService")
public class TotalServiceImpl implements TotalService {

    @Autowired
    private TotalMapper totalMapper;

    @Override
    public Total selectTotal() {
        return totalMapper.selectById(1);
    }

    @Override
    public void userTotalAdd() {
        Total total=totalMapper.selectById(1);
        total.setTolUser(total.getTolUser()+1);
        totalMapper.updateById(total);
    }

    @Override
    public void userTotalDel() {
        Total total=totalMapper.selectById(1);
        total.setTolUser(total.getTolUser()-1);
        totalMapper.updateById(total);
    }

    @Override
    public void topicTotalAdd(String tpcDiff) {
        Total total=totalMapper.selectById(1);
        total.setTolTopic(total.getTolTopic()+1);
        switch (tpcDiff){
            case "SS":
                total.setTolSs(total.getTolSs()+1);
                break;
            case "S":
                total.setTolS(total.getTolS()+1);
                break;
            case "A":
                total.setTolA(total.getTolA()+1);
                break;
            case "B":
                total.setTolB(total.getTolB()+1);
                break;
            case "C":
                total.setTolC(total.getTolC()+1);
                break;
            case "D":
                total.setTolD(total.getTolD()+1);
                break;
        }
        totalMapper.updateById(total);
    }

    @Override
    public void topicTotalDel(String tpcDiff) {
        Total total=totalMapper.selectById(1);
        total.setTolTopic(total.getTolTopic()-1);
        switch (tpcDiff){
            case "SS":
                total.setTolSs(total.getTolSs()-1);
                break;
            case "S":
                total.setTolS(total.getTolS()-1);
                break;
            case "A":
                total.setTolA(total.getTolA()-1);
                break;
            case "B":
                total.setTolB(total.getTolB()-1);
                break;
            case "C":
                total.setTolC(total.getTolC()-1);
                break;
            case "D":
                total.setTolD(total.getTolD()-1);
                break;
        }
        totalMapper.updateById(total);
    }

    @Override
    public void updatesub() {
        Total total=totalMapper.selectById(1);
        total.setTolSub(total.getTolSub()+1);
        totalMapper.updateById(total);
    }

    @Override
    public void updateac() {
        Total total=totalMapper.selectById(1);
        total.setTolAc(total.getTolAc()+1);
        totalMapper.updateById(total);
    }

    @Override
    public void updateresult(Integer result) {
        Total total=totalMapper.selectById(1);
        switch (result){
            case 2:total.setTolWa(total.getTolWa()+1);break;
            case 3:total.setTolTle(total.getTolTle()+1);break;
            case 4:total.setTolMle(total.getTolMle()+1);break;
            case 5:total.setTolRe(total.getTolRe()+1);break;
            case 6:total.setTolOle(total.getTolOle()+1);break;
            case 7:total.setTolCe(total.getTolCe()+1);break;
            case 8:total.setTolPc(total.getTolPc()+1);break;
            case 9:total.setTolUe(total.getTolUe()+1);break;
        }

        totalMapper.updateById(total);
    }


}
