package com.simplecoding.simpledms.controller.admin;

import com.simplecoding.simpledms.service.admin.AdminService;
import com.simplecoding.simpledms.vo.admin.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import com.simplecoding.simpledms.vo.common.Criteria;
import com.simplecoding.simpledms.vo.dto.ResultDto;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


        // 전체 조회
        @GetMapping("/api/admin")
        public ResponseEntity<?> selectAdminList(Criteria searchVO) {
            List<Admin> admins = AdminService.selectAdminList(searchVO);
            ResultDto resultDto = new ResultDto(admins, searchVO.getTotalItems());
            return new ResponseEntity<>(resultDto, HttpStatus.OK);
        }

        // Admin 생성
        @PostMapping("/api/admin/admin")
        public ResponseEntity<?> insert(
                @RequestParam(defaultValue = "") String title,
                @RequestParam(defaultValue = "") String question,
                @RequestParam(defaultValue = "") String answer,
                @RequestParam(required = false) MultipartFile image
        ) throws Exception {
            byte[] imageData = (image != null) ? image.getBytes() : null;
            Admin admin = new Admin(null, title, question, answer, imageData);
            adminService.insert(admin);
            return new ResponseEntity<>("Admin created successfully", HttpStatus.CREATED);
        }

        // 이미지 다운로드
        @GetMapping("/api/admin/{fno}/image")
        public ResponseEntity<byte[]> findDownload(@PathVariable int fno) throws Exception {
            Admin admin = adminService.selectAdmin(fno)
                    .orElseThrow(() -> new FileNotFoundException("Data not found."));

            byte[] imageData = admin.getFaqData();
            if (imageData == null) {
                throw new FileNotFoundException("Image data not found.");
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "image_" + admin.getFno());
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        }

        // 상세 조회
        @GetMapping("/api/admin/{fno}")
        public ResponseEntity<?> selectAdmin(@PathVariable int fno) {
            Optional<Admin> admin = adminService.selectAdmin(fno);
            if (admin.isEmpty()) {
                return new ResponseEntity<>("Admin not found", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(admin.get(), HttpStatus.OK);
        }

        // 수정
        @PutMapping("/api/admin/{fno}")
        public ResponseEntity<?> update(
                @PathVariable int fno,
                @RequestParam(defaultValue = "") String title,
                @RequestParam(defaultValue = "") String question,
                @RequestParam(defaultValue = "") String answer,
                @RequestParam(required = false) MultipartFile image
        ) throws Exception {
            byte[] imageData = (image != null) ? image.getBytes() : null;
            Admin admin = new Admin(fno, title, question, answer, imageData);
            adminService.update(admin);
            return new ResponseEntity<>("Admin updated successfully", HttpStatus.OK);
        }

        // 삭제
        @DeleteMapping("/api/admin/{fno}")
        public ResponseEntity<?> delete(@PathVariable int fno) {
            adminService.delete(fno);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

