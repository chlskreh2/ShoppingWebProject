package com.shopping.dto.api;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 카카오 페이 api 결제 대기
 */
@Getter
@Setter
public class ReadyPaymentDto {

    //결제 고유 번호, 20자
    private	String tid;
    private boolean tms_result;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    //요청한 클라이언트가 PC 웹일 경우 카카오톡으로 결제 요청 메시지(TMS)를 보내기 위한 사용자 정보 입력 화면 Redirect URL
    private String next_redirect_pc_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    //결제 준비 요청 시간
    private LocalDateTime created_at;
}
