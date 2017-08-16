package com.yong.member;

import com.yong.config.spring.AppConfig;
import com.yong.domain.Member;
import com.yong.repository.MemberRepository;
import com.yong.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        // given
        Member member = new Member("lee");

        // when
        Long id = memberService.join(member);

        // then
        assertThat(memberRepository.findOne(id), is(member));
    }
}