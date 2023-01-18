package Team2.com.util;


import Team2.com.item.entity.Item;
import Team2.com.item.repository.ItemRepository;
import Team2.com.member.entity.Member;
import Team2.com.member.entity.MemberRoleEnum;
import Team2.com.member.entity.Request;
import Team2.com.member.repository.MemberRepository;
import Team2.com.member.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class init implements ApplicationRunner {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Member member1 = new Member(1L, "chlalstlr1", passwordEncoder.encode("chlalstlr1"), MemberRoleEnum.CUSTOMER, 0, "010-1122-1353", "최민식");
        memberRepository.save(member1);
        Member member2 = new Member(2L, "dbsduswn1", passwordEncoder.encode("dbsduswn1"), MemberRoleEnum.CUSTOMER, 0, "010-1212-3852", "윤연주");
        memberRepository.save(member2);

        Member member3 = new Member(3L, "dlwodnjs1", passwordEncoder.encode("dlwodnjs1"), MemberRoleEnum.SELLER, 0, "010-1322-3250", "이재원");
        memberRepository.save(member3);
        Member member4 = new Member(4L, "gjtjdxo1", passwordEncoder.encode("gjtjdxo1"), MemberRoleEnum.SELLER, 0, "010-4222-3959", "허성태");
        memberRepository.save(member4);

        Member member5 = new Member(5L, "gjwjdan1", passwordEncoder.encode("gjwjdan1"), MemberRoleEnum.CUSTOMER, 0, "010-7122-3057", "허정무");
        memberRepository.save(member5);
        Member member6 = new Member(6L, "gksquddus1", passwordEncoder.encode("gksquddus1"), MemberRoleEnum.CUSTOMER, 0, "010-7122-7357", "한병연");
        memberRepository.save(member6);

        Member member7 = new Member(7L, "kiang18", passwordEncoder.encode("kiang18"), MemberRoleEnum.ADMIN, 0, "010-6122-3357", "김지환");
        memberRepository.save(member7);

        Member member8 = new Member(8L, "kiang970418", passwordEncoder.encode("kiang970418"), MemberRoleEnum.CUSTOMER, 0, "010-5122-3857", "김필립");
        memberRepository.save(member8);

        Member member9 = new Member(9L, "qkrtpdls1", passwordEncoder.encode("qkrtpdls1"), MemberRoleEnum.SELLER, 0, "010-1422-3157", "박세인");
        memberRepository.save(member9);
        Member member10 = new Member(10L, "qowlsdk1", passwordEncoder.encode("qowlsdk1"), MemberRoleEnum.SELLER, 0, "010-1322-3327", "배진아");
        memberRepository.save(member10);

        Member member11 = new Member(11L, "rladudgks1", passwordEncoder.encode("rladudgks1"), MemberRoleEnum.CUSTOMER, 0, "010-1829-3157", "김영한");
        memberRepository.save(member11);
        Member member12 = new Member(12L, "clfemfjs1", passwordEncoder.encode("clfemfjs1"), MemberRoleEnum.CUSTOMER, 0, "010-1029-3150", "칠드런");
        memberRepository.save(member12);

        Member member13 = new Member(13L, "rlarhksghks1", passwordEncoder.encode("rlarhksghks1"), MemberRoleEnum.SELLER, 0, "010-1122-3351", "김관한");
        memberRepository.save(member13);
        Member member14 = new Member(14L, "qkrtmfrl1", passwordEncoder.encode("qkrtmfrl1"), MemberRoleEnum.SELLER, 0, "010-1122-3535", "박슬기");
        memberRepository.save(member14);
        Member member15 = new Member(15L, "dbswjdtn1", passwordEncoder.encode("dbswjdtn1"), MemberRoleEnum.SELLER, 0, "010-1122-3354", "윤정수");
        memberRepository.save(member15);
        Member member16 = new Member(16L, "rlatmddus1", passwordEncoder.encode("rlatmddus1"), MemberRoleEnum.SELLER, 0, "010-1122-4351", "김승연");
        memberRepository.save(member16);

        Member member17 = new Member(17L, "rhkrdbsrl1", passwordEncoder.encode("rhkrdbsrl1"), MemberRoleEnum.CUSTOMER, 0, "010-1122-3452", "곽윤기");
        memberRepository.save(member17);
        Member member18 = new Member(18L, "wlsdnxk1", passwordEncoder.encode("wlsdnxk1"), MemberRoleEnum.SELLER, 0, "010-2120-3355", "진우타");
        memberRepository.save(member18);
        Member member19 = new Member(19L, "whtjdfkr1", passwordEncoder.encode("whtjdfkr1"), MemberRoleEnum.CUSTOMER, 0, "010-1192-3158", "조성락");
        memberRepository.save(member19);
        Member member20 = new Member(20L, "thstjrrn1", passwordEncoder.encode("thstjrrn1"), MemberRoleEnum.SELLER, 0, "010-1822-2357", "손석구");
        memberRepository.save(member20);
        Member member21 = new Member(21L, "rlaxodl1", passwordEncoder.encode("rlaxodl1"), MemberRoleEnum.CUSTOMER, 0, "010-1172-3357", "김태이");
        memberRepository.save(member21);
        Member member22 = new Member(22L, "rlawhdals1", passwordEncoder.encode("rlawhdals1"), MemberRoleEnum.CUSTOMER, 0, "010-1162-4354", "김종민");
        memberRepository.save(member22);
        Member member23 = new Member(23L, "rkdghehd1", passwordEncoder.encode("rkdghehd1"), MemberRoleEnum.SELLER, 0, "010-1522-3556", "강호동");
        memberRepository.save(member23);
        Member member24 = new Member(24L, "skarndtjd1", passwordEncoder.encode("skarndtjd1"), MemberRoleEnum.CUSTOMER, 0, "010-1422-7356", "남궁성");
        memberRepository.save(member24);
        Member member25 = new Member(25L, "qorrltjs1", passwordEncoder.encode("qorrltjs1"), MemberRoleEnum.SELLER, 0, "010-1322-7357", "백기선");
        memberRepository.save(member25);
        Member member26 = new Member(26L, "wjvkfrp1", passwordEncoder.encode("wjvkfrp1"), MemberRoleEnum.CUSTOMER, 0, "010-1222-3857", "저팔게");
        memberRepository.save(member26);
        Member member27 = new Member(27L, "chzhvkdl1", passwordEncoder.encode("chzhvkdl1"), MemberRoleEnum.CUSTOMER, 0, "010-1122-9358", "초코파이");
        memberRepository.save(member27);

        // 판매자 pk : 3,4,9,10,13,14,15,16,18,20,23,25 (12명)
        // 관리자 pk : 7 (1명)
        // 고객 pk : 1,2,5,6,8,11,12,17,19,21,22,24,26,27 (14명)



        // 권한 UP : 1, 5, 6, 11 ,17, 19, 21 ( 7명 ) C -> S
        // 권한 DOWN :3(S->C), 9(S->C), 13(S->C), 15(S->C),23(S->C) (5명) S-> C
        Request request1 = new Request(1L, "CUSTOMER", "UP");
        requestRepository.save(request1);
        Request request2 = new Request(3L, "SELLER", "DOWN");
        requestRepository.save(request2);
        Request request3 = new Request(5L, "CUSTOMER", "UP");
        requestRepository.save(request3);
        Request request4 = new Request(6L, "CUSTOMER", "UP");
        requestRepository.save(request4);
        Request request5 = new Request(9L, "SELLER", "DOWN");
        requestRepository.save(request5);
        Request request6 = new Request(11L, "CUSTOMER", "UP");
        requestRepository.save(request6);
        Request request7 = new Request(13L, "SELLER", "DOWN");
        requestRepository.save(request7);
        Request request8 = new Request(15L, "SELLER", "DOWN");
        requestRepository.save(request8);
        Request request9 = new Request(17L, "CUSTOMER", "UP");
        requestRepository.save(request9);
        Request request10 = new Request(19L, "CUSTOMER", "UP");
        requestRepository.save(request10);
        Request request11 = new Request(21L, "CUSTOMER", "UP");
        requestRepository.save(request11);
        Request request12 = new Request(23L, "SELLER", "DOWN");
        requestRepository.save(request12);

        // 판매자 pk : 3,4,8,9,13,14,15,16,18,19,23,25 (12명) (3, 9, 13, 15, 23 xxx)
        // 권한 DOWN :3(S->C), 9(S->C), 13(S->C), 15(S->C),23(S->C) (5명)
        Item item1 = new Item("사탕", "으악달다", member4, 500, 100);
        itemRepository.save(item1);
        Item item2 = new Item("갤럭시 Z 플립", "주운거임", member10, 300000, 1);
        itemRepository.save(item2);
        Item item3 = new Item("컴파일러의 이해", "으악으악으악", member25, 18000, 3);
        itemRepository.save(item3);
        Item item4 = new Item("달팽이 껍질", "한약재료", member18, 300, 300);
        itemRepository.save(item4);
        Item item5 = new Item("스포아의 포자", "동충하초", member18, 180, 150);
        itemRepository.save(item5);
        Item item6 = new Item("빨간 달팽이의 점액", "피부미용에 좋다", member18, 80, 25);
        itemRepository.save(item6);
        Item item7 = new Item("스카치 캔디", "맛있다", member20, 200, 500);
        itemRepository.save(item7);
        Item item8 = new Item("먹다남은 초코파이", "한입하시죠", member10, 600, 111);
        itemRepository.save(item8);
        Item item9 = new Item("데이터베이스 개론", "전공필수", member4, 22500, 3);
        itemRepository.save(item9);
        Item item10 = new Item("하리보 젤리", "이섞는다", member14, 3000, 5);
        itemRepository.save(item10);
        Item item11 = new Item("곰팡이 핀 햄버거", "맛있겠다", member14, 6800, 3);
        itemRepository.save(item11);
        Item item12 = new Item("자바의 정석", "교양서적", member25, 23500, 1);
        itemRepository.save(item12);
        Item item13 = new Item("포카칩 파란색", "취향존중", member25, 2500, 30);
        itemRepository.save(item13);


    }
}

