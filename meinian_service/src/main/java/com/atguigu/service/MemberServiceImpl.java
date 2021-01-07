package com.atguigu.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.MemberService;
import com.atguigu.dao.MemberMapper;
import com.atguigu.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public List<Integer> findMemberCountByMonth(List<String> list) {
        List<Integer> memberCountList = new ArrayList<Integer>();
        for (String months : list){
            //获取指定月份的最后一天
            String regTime = DateUtils.getLastDayOfMonth(months);
            //迭代过去12个月，每个月注册会员的数量，根据注册日期查询
            Integer memberCount = memberMapper.findMemberCountBeforDate(regTime);
            memberCountList.add(memberCount);
        }
        return memberCountList;
    }
}
