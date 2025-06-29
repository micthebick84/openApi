package com.example.openapi.controller;

import com.example.openapi.dto.ApiResponse;
import com.example.openapi.dto.UserDto;
import com.example.openapi.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "사용자 관리 API")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    @Operation(summary = "모든 사용자 조회", description = "등록된 모든 사용자 목록을 조회합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 사용자 목록을 조회했습니다",
            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userMapper.selectAllUsers();
        return ResponseEntity.ok(ApiResponse.success("사용자 목록을 성공적으로 조회했습니다", users));
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회", description = "특정 ID의 사용자 정보를 조회합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 사용자를 조회했습니다"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다")
    })
    public ResponseEntity<ApiResponse<UserDto>> getUserById(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long id) {
        UserDto user = userMapper.selectUserById(id);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("ID " + id + "에 해당하는 사용자를 찾을 수 없습니다"));
        }

        return ResponseEntity.ok(ApiResponse.success("사용자를 성공적으로 조회했습니다", user));
    }

    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "성공적으로 사용자를 생성했습니다"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    public ResponseEntity<ApiResponse<UserDto>> createUser(
            @Parameter(description = "생성할 사용자 정보", required = true)
            @Valid @RequestBody UserDto userDto) {
        
        int result = userMapper.insertUser(userDto);
        
        if (result > 0) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("사용자가 성공적으로 생성되었습니다", userDto));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("사용자 생성에 실패했습니다"));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "사용자 수정", description = "기존 사용자 정보를 수정합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 사용자를 수정했습니다"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청 데이터")
    })
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long id,
            @Parameter(description = "수정할 사용자 정보", required = true)
            @Valid @RequestBody UserDto userDto) {
        
        userDto.setId(id);
        int result = userMapper.updateUser(userDto);
        
        if (result > 0) {
            return ResponseEntity.ok(ApiResponse.success("사용자가 성공적으로 수정되었습니다", userDto));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("ID " + id + "에 해당하는 사용자를 찾을 수 없습니다"));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 삭제", description = "특정 ID의 사용자를 삭제합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 사용자를 삭제했습니다"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다")
    })
    public ResponseEntity<ApiResponse<String>> deleteUser(
            @Parameter(description = "사용자 ID", example = "1") @PathVariable Long id) {
        
        int result = userMapper.deleteUser(id);
        
        if (result > 0) {
            return ResponseEntity.ok(ApiResponse.success("사용자가 성공적으로 삭제되었습니다", "ID: " + id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("ID " + id + "에 해당하는 사용자를 찾을 수 없습니다"));
        }
    }

    @GetMapping("/search")
    @Operation(summary = "사용자 검색", description = "이름으로 사용자를 검색합니다.")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공적으로 검색을 완료했습니다")
    })
    public ResponseEntity<ApiResponse<List<UserDto>>> searchUsers(
            @Parameter(description = "검색할 이름", example = "홍") @RequestParam String name) {
        
        List<UserDto> filteredUsers = userMapper.selectUsersByName(name);
        
        return ResponseEntity.ok(ApiResponse.success("검색이 완료되었습니다", filteredUsers));
    }
} 