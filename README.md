# java-lotto-precourse
사용자가 구매한 로또 번호를 당첨 번호와 보너스 번호로 비교해 당첨 내역과 수익률을 계산하는 프로그램

### 컨벤션
- README를 상세히 작성한다.
- 유연한 설계를 한다. - 구현중 코드 변경에 영향을 너무 받는 기능목록을 작성하지 않는다.
- 유효검증과 기능 목록은 구현중 지속적으로 업데이트한다.
- 값을 하드코딩 하지 않는다.
- 메서드는 15라인을 넘지 않도록한다.
- depth는 2까지만 허용한다.
- else 를 사용하지 않는다.
- switch/case를 사용하지 않는다.
- Enum을 적용한다
- UI를 제외한 기능에 대해 단위테스트를 작성한다.
- missionutils 외 라이브러리는 사용하지 않는다.
- 프로그래링 요구사항3에서 제공한 Lotto클래스를 사용하여 구현한다.

### 목표
- 헥사고날 아키텍처를 이해한다.
- 도메인 객체를 이해한다.
- VO를 이해한다.
- TDD를 이해한다.
- 커스텀 exception을 생성한다.
- 공통화를 시도한다.

---

### 주요 비지니스
- 판매된 티켓에 따라 랜덤 로또를 생성한다.
- 로또 번호와 랜덤 로또를 매칭하여 당첨 통계를 계산한다.
- 수익률을 계산한다.
### 기능 요구 사항
- 로또 번호의 숫자 범위는 1~45까지이다.
- 로또 번호는 중복되지 않는다.
- 1개의 로또를 발행할 6개의 숫자를 뽑는다.
- 당첨 번호 추첨 시 숫자 6개와 보너스 번호 1개를 입력받는다.
- 로또 구입 금액은 1,000원이고 구입 금액에 해당하는 만큼 로또를 발행해야 한다.
- 당첨은 1등부터 5등까지 있다.
```
- 1등: 6개 번호 일치 / 2,000,000,000원
- 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
- 3등: 5개 번호 일치 / 1,500,000원
- 4등: 4개 번호 일치 / 50,000원
- 5등: 3개 번호 일치 / 5,000원
```
- 사용자가 구매한 로또 번호와 당첨 번호를 비교하여 당첨 내역 및 수익률을 출력하고 로또 게임을 종료한다.
- 금액은 1,000의 형태로 출력한다.
---

### 사용자 시점 플로우
1. 구입금액을 입력해 주세요.
2. 발행한 로또 수량 및 번호를 출력한다. 로또 번호는 오름차순으로 출력
3. 당첨 번호를 입력해 주세요.
4. 보너스 번호를 입력해 주세요.
5. 당첨통계 + 수익률 출력
### 애플리케이션 시점 플로우
- 구입 금액을 입력받음
    - 유효검증
    - 구입 금액에 따라 로또 티켓 생성
    - 티켓의 개수만큼 랜덤 로또 생성
- 발행한 로또 수량 및 번호를 오름차순으로 출력.
- 당첨 번호 6자리를 입력받음
    - 유효검증
- 보너스 번호를 입력받음
    - 유효검증
- 당첨 통계 계산
- 수익률 계산
- 당첨 내역과 수익률 출력

---

### 유효검증
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시키고, 
"[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
- `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.

#### 구입 금액
- null, isBlank가 아닌가?
- 양의 정수인가? - 0도 안됨
- 1,000으로 나누어 떨어지는가?
- MAX 검증
#### 메인 로또 번호
- null, isBlank가 아닌가?
- 구분자가 ,(콤마)인가?
- 6개인가?
- 1~45까지의 숫자로 이루어져있는가?
- 중복된 숫자가 존재하는가?
### 보너스 번호
- null, isBlank가 아닌가?
- 1~45까지의 숫자로 이루어져있는가?
- 메인 로또와 중복된 숫자가 아닌가?
---
### 패키지 구조
```
src/main/
└── java
    └── lotto
        ├── Application.java
        ├── application
        │   └── LottoController.java
        ├── common
        │   ├── constants
        │   │   ├── LottoPolicy.java
        │   │   ├── Rank.java
        │   │   └── YieldPolicy.java
        │   ├── exception
        │   │   ├── BusinessException.java
        │   │   ├── LottoError.java
        │   │   └── RereadRequestException.java
        │   └── util
        │       ├── NumberValidator.java
        │       └── RereadExecutor.java
        ├── domain
        │   ├── model
        │   │   ├── Lotto.java
        │   │   ├── PurchasedLottos.java
        │   │   └── UserLotto.java
        │   ├── port
        │   │   ├── inbound
        │   │   │   └── LottoUseCase.java
        │   │   └── outbound
        │   │       └── LottoNumberMakerPort.java
        │   ├── service
        │   │   └── LottoService.java
        │   └── vo
        │       ├── Money.java
        │       └── WinningStatistics.java
        ├── infrastructure
        │   └── RandomNumberMaker.java
        └── ui
            ├── ExceptionHandler.java
            ├── InputVIew.java
            ├── NumberParser.java
            ├── OutputView.java
            └── Reader.java
```