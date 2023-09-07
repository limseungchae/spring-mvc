package com.sparta.springmvc.service;

import com.sparta.springmvc.domain.Member;
import com.sparta.springmvc.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // 테스트를 실행하기 전에 실행되는 메서드입니다.
        // MemberService와 MemoryMemberRepository 인스턴스를 생성하여 초기화합니다.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        // 각 테스트가 실행된 후에 실행되는 메서드입니다.
        // 저장된 회원 정보를 초기화하여 다음 테스트에 영향을 주지 않도록 합니다.
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        // 테스트를 위한 초기 상태를 설정합니다.
        Member member = new Member();
        member.setName("hello");

        // when
        // 실제 테스트 작업을 수행합니다. 회원 가입 메서드를 호출합니다.
        Long saveId = memberService.join(member);

        // then
        // 테스트 결과를 검증합니다. 저장된 회원을 조회하고 이름이 일치하는지 확인합니다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        // 중복 회원을 가입하기 위한 초기 상태를 설정합니다.
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        // 중복 회원 가입을 시도하고 예외가 발생하는지 확인합니다.
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        // 예외 발생 메시지가 예상과 일치하는지 확인합니다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */
    }

    @Test
    void findMembers() {
        // 회원 목록 조회 테스트입니다.
    }

    @Test
    void findOne() {
        // 개별 회원 조회 테스트입니다.
    }
}
