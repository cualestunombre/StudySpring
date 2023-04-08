MemberRepositoryV0 : 단순 커넥션 획득 반복 repository
MemberRepositoryV1 : 데이터 소스 활용 repository 
MemberServiceV1 : 트랜잭션 처리가 없는 송금 service
MemberRepositoryV2 : connection을 인자로 받을 수 있는 메서드 포함 (비효율적)
MemberServiceV2 : 트랜잭션 처리가 있는 송금 service, connection을 받아야 함 
MemberRepositoryV3 : MemberRepositoryV1에서 동기화 매니져에게 반납할 지, 아니면 커넥션을 끊을 지(반납) 처리하는 기능 추가
MemberServiceV3 : 트랜잭션 매니져를 추가하여, 서비스 계층이 JDBC기술에 종속되는 것을 막은 서비스
MemberServiceV3_2 : 트랜잭션 템플릿을 사용하여, 패턴의 반복을 줄임
MemberServiceV3_3 : 트랜잭션 AOP를 사용하여, 서비스 계층에서 트랜잭션 관련 코드를 없애버림

테스트 : 
MemberRepositoryV1Test : 데이터 소스 활용 repository 테스트
DBConnectionUtilTest : 단순 커넥션 획득 반복 test
MembeServiceV1Test :  트랜잭션 처리가 없는 송금 service테스트
MembeServiceV2Test : 트랜잭션 처리가 있는 송금 service테스트
MemberServiceV3_1Test : 트랜잭션 매니져를 추가하여, 서비스 계층이 JDBC기술에 종속되는 것을 막은 서비스에 대한 테스트
MemberServiceV3_2Test : 트랜잭션 템플릿 테스트
MemberServiceV3_3Test : 트랜잭션 AOP 테스트
MemberServiceV3_4Test : 트랜잭션 매니져 및 데이터 소스 자동등록 
CheckedTest : 체크예외에 대한 테스트
UncheckedTest : 언체크예외(런타임)에 대한 테스트
CheckedAppTest : 체크예외app에 대한 테스트 (문제점 1. 어차피 해결 못함 2. 종속성 발생)
UncheckedAppTest : 언체크예외app에 대한 테스트(어차피 해결 못할 예외 그냥 명시하지 않고, 공통처리하는 것이 좋음)