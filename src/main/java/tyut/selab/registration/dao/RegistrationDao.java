package tyut.selab.registration.dao;

import tyut.selab.registration.dto.RegistrationDto;
import tyut.selab.registration.view.RegistrationVo;

import java.util.List;

public interface RegistrationDao {

    /**
     *  增加一个报名表
     * @param registrationDto
     * @return
     */
    Integer insert(RegistrationDto registrationDto);

    /**
     *  修改报名表信息
     * @param registrationId
     * @return
     */
    Integer update(RegistrationDto registrationId);

    /**
     * 通过id查询报名表信息
     * @param registrationId
     * @return
     */
    RegistrationVo selectByRegistrationId(Integer registrationId);

    /**
     *  查询面试者的报名表
     * @param intervieweesName
     * @return
     */
    RegistrationVo selectByIntervieweesName(String intervieweesName);

    /**
     * 查询所有报名表
     * @return
     */
    List<RegistrationVo> selectAll();

}
