package com.sparta.springmvc.service;

import com.sparta.springmvc.domain.Member;
import com.sparta.springmvc.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    // MemberService에서 사용할 MemberRepository 인스턴스를 생성합니다.
    private final MemberRepository memberRepository;

    /**
     * MemberService 클래스의 생성자입니다.
     *
     * @param memberRepository MemberRepository 인스턴스입니다. 이 인스턴스를 통해 회원 관련 데이터를 처리합니다.
     */
    // 생성자를 통한 의존성 주입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     *
     * @param member 회원 객체
     * @return 저장된 회원의 고유 ID
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원을 허용하지 않습니다.
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member); // 회원 저장
        return member.getId(); // 저장된 회원의 고유 ID 반환
    }

    /**
     * 중복 회원 검증
     *
     * @param member 회원 객체
     */
    private void validateDuplicateMember(Member member) {
        // 중복된 이름이 있는지 검사합니다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     * @return 전체 회원 목록
     */
    public List<Member> findMembers() {
        // 저장된 모든 회원을 조회합니다.
        return memberRepository.findAll();
    }

    /**
     * 회원 ID로 회원 조회
     *
     * @param memberId 회원 ID
     * @return 회원 객체 (회원이 존재하지 않을 경우 빈 결과 Optional)
     */
    public Optional<Member> findOne(Long memberId) {
        // 회원 ID를 이용하여 회원을 조회합니다.
        return memberRepository.findById(memberId);
    }
}
