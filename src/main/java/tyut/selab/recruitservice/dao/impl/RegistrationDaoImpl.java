package tyut.selab.recruitservice.dao.impl;

import tyut.selab.recruitservice.dao.RegistrationDao;
import tyut.selab.recruitservice.dto.RegistrationDto;
import tyut.selab.recruitservice.view.RegistrationVo;

import java.util.List;

public class RegistrationDaoImpl implements RegistrationDao {
    /**
     *  增加一个报名表
     * @param registrationDto
     * @return
     */
    public Integer insert(RegistrationDto registrationDto){
        return null;
    }

    /**
     *  修改报名表信息
     * @param registrationId
     * @return
     */
    public Integer update(RegistrationDto registrationId){
        return null;
    }

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    public RegistrationVo selectByRegistrationId(Integer registrationId){
        return null;
    }

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    public RegistrationVo selectByIntervieweesName(String intervieweesName){
        return null;
    }

    /**
     * 查询所有报名表
     * @return
     */
    public List<RegistrationVo> selectAll(){
        return null;
    }
}
