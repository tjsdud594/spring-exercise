package com.example.springexercise.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

import com.example.springexercise.domain.Member;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberRepository implements MemberRepositoryInter {
    private final JdbcTemplate jdbcTemplate;
    public  MemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Member save(Member member){
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        System.out.println("저장함수 실행");
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        parameters.put("email", member.getEmail());
        System.out.println("이멜이랑 아이디는 추가됬지?");
        parameters.put("password", member.getPassword());
        System.out.println("패스워드 가져와" + parameters.get("password"));
        parameters.put("salt", member.getSalt());
        System.out.println("MemberRepository 저장될 값 확인  -> " + "salt : " + parameters.get("salt") + "//  pass : " + parameters.get("password"));

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

//    jdbcTemplate.query 대한 설명 : https://jaehoney.tistory.com/34
    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> result = jdbcTemplate.query("select * from user where email = ?", memberRowMapper(), email);
        System.out.println("이메일로 서치해서 찾은 값 나오나 id조회" + result.stream().findAny().get().getId());
//        System.out.println("이메일로 서치해서 찾은 값 나오나 salt값 확인" + result.stream().findAny().get());
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from user", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setEmail(rs.getString("email"));
            member.setPassword(rs.getString("password"));
            try {
                member.setSalt(rs.getString("salt"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return member;
        };
    }
}
