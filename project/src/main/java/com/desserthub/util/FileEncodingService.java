package com.desserthub.util;

import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileEncodingService {
    /**
     * 파일을 받아서 Base64로 인코딩한 문자열을 반환
     * 
     * @param file 업로드된 파일
     * @return Base64로 인코딩된 문자열
     * @throws IOException 파일 처리 중 오류가 발생하면 예외 발생
     */
    public String encodeFileToBase64(MultipartFile file) throws IOException {
        // 파일을 바이트 배열로 변환
        byte[] fileBytes = file.getBytes();

        // Base64로 인코딩하여 문자열로 반환
        return Base64Utils.encodeToString(fileBytes);
    }
}
