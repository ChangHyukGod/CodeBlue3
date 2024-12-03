package com.simplecoding.simpledms.mapper.admin;

import com.simplecoding.simpledms.vo.admin.Admin;
import com.simplecoding.simpledms.vo.common.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdminMapper {
    public List<?> selectAdminList(Criteria searchVO);          // 전체조회
    public int selectAdminListTotCnt(Criteria searchVO);        // 총건수구하기
    public int insert(Admin admin);                             // 게시글 생성
    public Optional<Admin> selectAdmin(int title);              // 상세조회
    public int update(Admin admin);                             // 게시글 수정
    public int delete(int title);                               // 게시글 삭제
}
