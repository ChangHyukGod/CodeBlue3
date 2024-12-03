package com.simplecoding.simpledms.service.admin;

import com.simplecoding.simpledms.mapper.admin.AdminMapper;
import com.simplecoding.simpledms.vo.admin.Admin;
import com.simplecoding.simpledms.vo.common.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    // 게시물 목록 조회
    public List<?> selectAdminList(Criteria searchVO) {
        List<?> page = AdminMapper.selectAdminList(searchVO);  // adminMapper 사용

        int count = AdminMapper.selectAdminListTotCnt(searchVO);  // adminMapper 사용
        searchVO.setTotalItems(count);  // 총 항목 수 설정
        return page;
    }

    // 게시물 추가
    public void insert(Admin admin) {
        AdminMapper.insert(admin);  // adminMapper 사용
    }

    // 게시물 조회
    public Optional<Admin> selectAdmin(int fno) {
        return AdminMapper.selectAdmin(fno);  // adminMapper 사용
    }

    // 게시물 수정
    public void update(Admin admin) {
        AdminMapper.update(admin);  // adminMapper 사용
    }

    // 게시물 삭제
    public void delete(int fno) {
        AdminMapper.delete(fno);  // adminMapper 사용
    }
}
