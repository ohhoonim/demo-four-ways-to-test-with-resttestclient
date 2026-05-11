# demo-four-ways-to-test-with-resttestclient

RestTestClient를 이용한 4가지 테스트 유형 


## RestTestClient 핵심 요약

- 출시 버전: Spring Framework 7.0
- 목적: REST API 테스트를 더 쉽고 간결하게 작성
- 특징:
    - MockMvc와 WebTestClient의 장점을 결합
    - 빌더 스타일 API로 가독성 높은 테스트 코드 작성 가능
    - 단위 테스트, 통합 테스트, 엔드투엔드 테스트 모두 지원
    - 다양한 바인딩 옵션 제공 (Controller, ApplicationContext, RouterFunction, MockMvc, Live Server)


## 다양한 바인딩 방식

- bindToController: 특정 컨트롤러만 빠르게 테스트
- bindToApplicationContext: Spring MVC 설정을 로드해 테스트
- bindToRouterFunction: 함수형 엔드포인트 테스트
- bindTo(MockMvc): MockMvc 기반 테스트
- bindToServer: 실제 서버와 연결해 엔드투엔드 테스트


## 테스트 작성 편의성

- 직관적인 API (.get().uri("/users").exchange().expectStatus().isOk())
- 응답 상태, 헤더, 본문 검증을 체계적으로 지원
- JSON 응답 검증 및 타입 매핑 가능
