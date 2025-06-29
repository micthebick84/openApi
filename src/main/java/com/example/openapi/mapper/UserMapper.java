package com.example.openapi.mapper;

import com.example.openapi.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    
    /**
     * 모든 사용자 조회
     */
    List<UserDto> selectAllUsers();
    
    /**
     * ID로 사용자 조회
     */
    UserDto selectUserById(Long id);
    
    /**
     * 사용자 생성
     */
    int insertUser(UserDto user);
    
    /**
     * 사용자 수정
     */
    int updateUser(UserDto user);
    
    /**
     * 사용자 삭제
     */
    int deleteUser(Long id);
    
    /**
     * 이름으로 사용자 검색
     */
    List<UserDto> selectUsersByName(String name);
} 