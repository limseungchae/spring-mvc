package com.sparta.springmvc.repository;

import com.sparta.springmvc.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    // 테스트에 사용할 MemoryMemberRepository 인스턴스 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 메서드 실행 후에 실행되는 메서드 (각 테스트의 실행이 끝날 때마다 저장된 데이터 초기화)
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // save() 테스트 메서드
    @Test
    public void save() {
        // 회원 객체 생성
        Member member = new Member();
        member.setName("spring");

        // 회원 정보 저장
        repository.save(member);

        // 저장된 회원 정보 조회
        Member result = repository.findById(member.getId()).get();

        // System.out.println("result= " + (result == member));
        // Assertions.assertEquals(member, result);

        // 에러
        // assertThat(member).isEqualTo(null);

        // 통과
        // 저장한 회원과 조회한 회원을 비교하여 검증
        assertThat(member).isEqualTo(result);
    }

    // findByName() 테스트 메서드
    @Test
    public void findByName() {
        // 회원 객체 생성 및 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // SHFT + F6 실행시 member2 관련 동시 수정가능
        // 다른 회원 객체 생성 및 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 이름으로 회원 조회
        Member result = repository.findByName("spring1").get();

        // 조회한 회원과 저장한 회원을 비교하여 검증
        assertThat(result).isEqualTo(member1);
    }

    // findAll() 테스트 메서드
    @Test
    public void findAll() {
        // 회원 객체 생성 및 저장
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 다른 회원 객체 생성 및 저장
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // 모든 회원 정보 조회
        List<Member> result = repository.findAll();

        // 조회한 회원 수를 비교하여 검증
        assertThat(result.size()).isEqualTo(2);
    }
}
