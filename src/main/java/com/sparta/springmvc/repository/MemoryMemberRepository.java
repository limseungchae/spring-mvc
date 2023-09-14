package com.sparta.springmvc.repository;

import com.sparta.springmvc.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 회원 정보를 저장할 맵
    private static Map<Long, Member> store = new HashMap<>();

    // 회원 ID 생성을 위한 시퀀스 값
    private static long sequence = 0L;

    // 회원 정보 저장 메서드
    @Override
    public Member save(Member member) {
        // 회원에게 ID 할당 및 시퀀스 증가
        member.setId(++sequence);
        // 맵에 회원 정보 저장
        store.put(member.getId(), member);
        // 저장된 회원 정보 반환
        return member;
    }

    // ID로 회원 조회 메서드
    @Override
    public Optional<Member> findById(Long id) {
        // ID를 이용하여 맵에서 회원 정보 조회 후 Optional로 감싸서 반환
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 회원 조회 메서드
    @Override
    public Optional<Member> findByName(String name) {
        // 람다식을 사용하여 이름으로 회원을 찾아 Optional로 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 회원 조회 메서드
    @Override
    public List<Member> findAll() {
        // 저장된 모든 회원 정보를 리스트로 반환
        return new ArrayList<>(store.values());
    }

    // 저장된 회원 정보 초기화 메서드
    public void clearStore() {
        store.clear();
    }
}
