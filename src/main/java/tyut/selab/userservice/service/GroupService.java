package tyut.selab.userservice.service;

import tyut.selab.userservice.Dto.GroupDto;
import tyut.selab.userservice.domain.Group;
import tyut.selab.userservice.vo.GroupVo;
import tyut.selab.utils.Result;

import java.util.List;

public interface GroupService {

    public Integer insert(GroupDto groupDto);

    public Integer delete(Integer groupId);

    /**
     *  查询所有小组信息
     * @return
     */
    public List<GroupVo> selectAllGroup(Integer cur,Integer szie);

    /**
     *  修改小组信息
     * @param groupVo
     * @return
     */
    public Integer update(GroupVo groupVo);


}
