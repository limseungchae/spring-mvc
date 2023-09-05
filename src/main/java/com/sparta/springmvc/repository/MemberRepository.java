package com.sparta.springmvc.repository;

import com.sparta.springmvc.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 멤버 정보를 저장하는 메서드
    Member save(Member member);

    // 주어진 ID로 멤버 정보를 조회하는 메서드
    // @param id: 조회할 멤버의 고유 식별자
    // @return: ID에 해당하는 멤버 객체를 Optional로 감싼 결과
    Optional<Member> findById(Long id);

    // 주어진 이름으로 멤버 정보를 조회하는 메서드
    // @param name: 조회할 멤버의 이름
    // @return: 이름에 해당하는 멤버 객체를 Optional로 감싼 결과
    Optional<Member> findByName(String name);

    // 모든 멤버 정보를 조회하는 메서드
    // @return: 모든 멤버 정보를 담은 리스트
    List<Member> findAll();
}
